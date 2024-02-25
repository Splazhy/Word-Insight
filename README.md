# Word Insight

A containerized service that fetches meanings of English words and turn it into images.

## Prerequisites

Before you get started, make sure you have the following installed on your system:
- Docker
- Maven (optional, if you want to build locally)

## Getting Started

In the `` Main.java `` file, write the Java code for your service.
```bash
mvn compile # This command compiles your Java source code.
```

```bash
mvn package # This command packages your service into a JAR file. The JAR file will be created in the ``target`` directory.
```

To run your Java service, use the following command:
```bash
java -jar target/work-insight-1.0.0.jar
```


### Building the Docker Image
To build a Docker image for Work Insight, follow these steps:
```bash
# Navigate to the project directory (where your Dockerfile is located)
cd /path/to/your/project

# Build the Docker image
docker build -t your-service-image .
```
This command will build a Docker image based on the instructions in your Dockerfile.

### Running the Docker Container
You can run a Docker container from the image using the following command:
```bash
docker run -d -p 8080:80 your-service-image
```
This command will start a Docker container running your service and expose it on port 8080.

### Tagging the Image
If you want to tag the image before pushing it to a registry, use the following command:
```bash
docker tag your-service-image username/your-service-image:version
```
Replace `username` with your Docker Hub username (or the name of the registry) and `version` with the desired version.

### Pushing the Image
To push the Docker image to a registry (e.g., Docker Hub), use the following command:
```bash
docker push username/your-service-image:version
```
This command will upload the image to the registry for others to use.

### Pulling the Image
To pull the image from the registry to another system, use the following command:
```bash
docker pull username/your-service-image:version
```
This command will download the image to your local Docker environment.

### Running the Pulled Image
To run the Docker container from the pulled image, use the following command:
```bash
docker run -d -p 8080:80 username/your-service-image:version
```
This command will start a container with the pulled image.

## Output Example

![meaning of operating system](word-insight_operating%20system.png)

## Dependencies
- **com.fasterxml.jackson.core** - JSON utilities library

## Special Thanks
[**meetDeveloper**](https://github.com/meetDeveloper) for providing a great free public dictionary API that doesn't require API key authentication.

[the dictionary website](https://www.dictionaryapi.dev/)\
[the github repository](https://github.com/meetDeveloper/freeDictionaryAPI)