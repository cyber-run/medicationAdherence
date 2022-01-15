## Medication Adherence – Javacakes

- This is an Imperial Bioengineering Programming 3 group project: to design an app to track and display medication adherence statistics provided by a pill box
- The app is designed to be deployed onto a web server with a postgresql table, and is built using Vaadin v22

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different 
IDEs]

## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/medicationadherence-1.0-SNAPSHOT.jar`


