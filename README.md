## Running Locally

- `./gradlew bootRun -Dspring.profiles.active=test`

## Running with Docker

#### Build to local Registry command
Docker local are required

- `./gradlew jibDockerBuild`

and now you can...

#### Run the application as container
The application will run with local docker

- `docker-compose up`


## Points of attention

#### Duration serialization/deserialization
The duration of the java has a specific format of serialization/deserialization to be applied which will be briefly explained here.

##### This string format are based on the ISO-8601 duration format

---

Syntax:
1. The string starts with an optional negative sign (-).
2. The letter "P" is next in upper or lower case.
3. There are then four sections, each consisting of a number and a suffix.
    1. The suffixes are (upper or lower case).
        - "D": Days
        - "H": Hours
        - "M": Minutes
        - "S": Seconds
    2. The suffixes must occur in order
    3. The letter "T" must occur before the first occurrence, if any, of an hour, minute or second section.
    4. The number may be prefixed by the negative or positive symbol.
4. The decimal point may be either a dot or a comma.

---

Example:

| Notation      | Value                                                         |
| ------------- |---------------------------------------------------------------| 
|"PT15M"        | parses as "15 minutes" (where a minute is 60 seconds)         |
|"PT10H"        | parses as "10 hours" (where an hour is 3600 seconds)          |
|"P2D"          | parses as "2 days" (where a day is 24 hours or 86400 seconds) |
|"P2DT3H4M"     | parses as "2 days, 3 hours and 4 minutes"                     |
|"PT-6H3M"      | parses as "-6 hours and +3 minutes"                           |
|"-PT6H3M"      | parses as "-6 hours and -3 minutes"                           |
|"-PT-6H+3M"    | parses as "+6 hours and -3 minutes"                           |
