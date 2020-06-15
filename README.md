## Interpolation Tool
### Info
This application has been developed using Java using OpenJDK 11 and built using Maven.
Downloads for both are below.
Alternatively, if you have Docker installed then you could ignore the Java/Maven requirements and run the application in a container, without needing to build it yourself - skip to the "Running with Docker" section.
- https://openjdk.java.net/install
- https://maven.apache.org/install.html

### Compilation and building
Once both have been installed, the following Maven commands can be used:
- mvn test - runs unit tests
- mvn package - compile and package the code in a distributable format
- mvn verify - runs integration tests

As test comes before package in the build lifecycle, running "mvn package" will also run unit tests and running "mvn verify" will also package the application.

### Running
After packaging, the following Java command will run the application:
- java -jar interpolation-tool.jar 

While the application is running, HTTP requests can be posted to the tool which be default runs on port 8080.
The following curl is an example for sending data to the application and accepting output.
Replace "input-data.csv" with the name of the file to send, and replace "output-data.csv" with the desired filename of the resulting data:
- curl -X POST -H 'Content-Type: text/plain' 'http://localhost:8080' --data-binary '@input-data.csv' -o 'output-data.csv'

### Running with Docker
A Docker image of the application is available and can be run using the following command (after selecting a port to run on):
- docker run -d -p XXXX:8080 wills287/interpolation-tool:1.0.0

Once running, the previous curl should work in the same way, making sure to update the port if running on an alternate port.

### Considerations
Overall, Java isn't really the best language for something like this due to the nature of strong typing and needing to convert between different data types throughout.
However, it is the language that I'm the most comfortable setting up an entire project in.

This tool is not an "optimal" solution - the different methods in DataProcessingService separately loop through the input data and could be combined to run in a single pass.
Writing it this way allows me to demonstrate how I might write tests and structure an application like this.

At the moment, the entire dataset loads into memory which isn't much of a problem for a 5x5 set.
However, processing millions of rows and columns would be troublesome.
To improve the scalability of the tool, we can stream through the input reading a row at a time.
If we hold three rows at any one time then we can process the "current" row while looking at the previous and next rows for adjacent values.
