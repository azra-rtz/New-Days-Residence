# New-Days-Residence
New Days Residence, the Rental Estate Tracker, empowers users with a suite of essential features. These include the ability to effortlessly add or remove apartments, facilitate property leases, manage property vacancies, and oversee transactions, providing a streamlined solution for effective and user-friendly rental property management.

## How to use
1. **Install JAR Files:**
   - Ensure all required JAR files are available for the New Days Residence application.
   - Copy the necessary JAR files into the project directory.
2. **Configure Build Path:**
   - If using an IDE like Eclipse or IntelliJ, configure the build path to include the JAR files.
   - For Eclipse:
     1. Right-click on the project.
     2. Select "Build Path" and choose "Configure Build Path."
     3. Under the "Libraries" tab, click "Add JARs" or "Add External JARs" to include the necessary JAR files.
   - For IntelliJ:
     1. Go to "File" > "Project Structure."
     2. Navigate to the "Modules" section.
     3. Under the "Dependencies" tab, click the "+" button to add JAR files to the project.

   - For Visual Studio Code (VS Code):
     1. Inside your project folder, create a new folder named "lib."
     2. Copy the required JAR files into the "lib" folder.
     3. Create a `.vscode` folder in the root of your project (if not already present).
     4. Inside the `.vscode` folder, create a `settings.json` file.
     5. Add the following configuration to include the JAR files:
        ```json
        {
            "java.project.referencedLibraries": [
                "lib/your-library1.jar",
                "lib/your-library2.jar"
            ]
        }
        ```
        Replace "your-library1.jar" and "your-library2.jar" with the actual names of your JAR files.
     6. Save the changes to the `settings.json` file.
     7. Reload your VS Code window.

By following these steps, you can configure the build path for JAR files in different IDEs, including Eclipse, IntelliJ, and Visual Studio Code.

3. Download the files in this repository.
