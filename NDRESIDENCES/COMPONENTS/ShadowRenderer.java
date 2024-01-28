package NDRESIDENCES.COMPONENTS;

import java.awt.Color;
import java.awt.image.BufferedImage;

// ShadowRenderer class for creating a shadow effect on a BufferedImage
public class ShadowRenderer {

    private int size = 5; // Default shadow size
    private float opacity = 0.5f; // Default shadow opacity
    private Color color = Color.BLACK; // Default shadow color

    // Default constructor using default values
    public ShadowRenderer() {
        this(5, 0.5f, Color.BLACK);
    }

    // Parameterized constructor to set size, opacity, and color
    public ShadowRenderer(final int size, final float opacity, final Color color) {
        this.size = size;
        this.opacity = opacity;
        this.color = color;
    }

    // Getter for the shadow color
    public Color getColor() {
        return color;
    }

    // Getter for the shadow opacity
    public float getOpacity() {
        return opacity;
    }

    // Getter for the shadow size
    public int getSize() {
        return size;
    }

    // Method to create a shadow on the provided BufferedImage
    public BufferedImage createShadow(final BufferedImage image) {
        // Initialize variables and arrays for shadow creation
        int shadowSize = size * 2;
        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();
        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;
        int left = size;
        int right = shadowSize - left;
        int yStop = dstHeight - right;
        int shadowRgb = color.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int historyIdx;
        int aSum;
        BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);
        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];
        
        // Copy pixels from the source image to the source buffer
        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);
        int lastPixelOffset = right * dstWidth;
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;
        int[] hSumLookup = new int[256 * shadowSize];
        
        // Initialize horizontal sum lookup table
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }
        int[] vSumLookup = new int[256 * shadowSize];
        
        // Initialize vertical sum lookup table
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }
        int srcOffset;
        
        // Iterate through each row of the source image
        for (int srcY = 0, dstOffset = left * dstWidth; srcY < srcHeight; srcY++) {
            // Initialize alpha history for each row
            for (historyIdx = 0; historyIdx < shadowSize;) {
                aHistory[historyIdx++] = 0;
            }
            aSum = 0;
            historyIdx = 0;
            srcOffset = srcY * srcWidth;
            
            // Iterate through each pixel in the row
            for (int srcX = 0; srcX < srcWidth; srcX++) {
                // Calculate and store horizontal sum
                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;
                aSum -= aHistory[historyIdx];
                a = srcBuffer[srcOffset + srcX] >>> 24;
                aHistory[historyIdx] = a;
                aSum += a;
                
                // Update history index
                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
            
            // Add extra pixels to handle the shadow at the end of the row
            for (int i = 0; i < shadowSize; i++) {
                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;
                aSum -= aHistory[historyIdx];
                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        // Iterate through each column to add vertical shadow
        for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {
            aSum = 0;
            
            // Initialize alpha history for each column
            for (historyIdx = 0; historyIdx < left;) {
                aHistory[historyIdx++] = 0;
            }
            
            // Add top part of the shadow
            for (int y = 0; y < right; y++, bufferOffset += dstWidth) {
                int a = dstBuffer[bufferOffset] >>> 24;
                aHistory[historyIdx++] = a;
                aSum += a;
            }
            
            bufferOffset = x;
            historyIdx = 0;
            
            // Add middle part of the shadow
            for (int y = 0; y < yStop; y++, bufferOffset += dstWidth) {
                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;
                aSum -= aHistory[historyIdx];
                a = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;
                aHistory[historyIdx] = a;
                aSum += a;
                
                // Update history index
                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
            
            // Add bottom part of the shadow
            for (int y = yStop; y < dstHeight; y++, bufferOffset += dstWidth) {
                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;
                aSum -= aHistory[historyIdx];
                
                // Update history index
                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }
        
        // Set pixels of the destination image from the buffer
        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        
        // Return the resulting BufferedImage with the shadow effect
        return dst;
    }
}
