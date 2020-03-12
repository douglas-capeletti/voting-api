## Running Locally

`./gradlew bootRun -Dspring.profiles.active=test`

## Running with Docker

#### Build to local Registry command
Docker local are required

`./gradlew jibDockerBuild`

and now you can...

#### Run the application as container
The application will run with local docker

`docker-compose up`