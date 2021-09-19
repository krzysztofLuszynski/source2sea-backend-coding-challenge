# Source2Sea Backend Coding Challenge

Application for Source2Sea backend coding challenge.
For details about the task see TaskDefinition.rtf in the same folder as this README.md

## Installation
```bash
# Run from main project directory
gradle bootRun
```

## Usage

```bash
curl --header "Content-Type: application/json" --request POST --data "[\"001\", \"002\", \"001\", \"004\", \"003\"]"  http://localhost:8080/checkout
```

## Timelog

### Environment preparation - 1h
#### Downloaded and installed git 2.33 for windows.
#### Downloaded and installed java 11.0.12 for windows.
#### Downloaded and installed gradle 7.2.
#### Downloaded and installed IntelliJ 2021.2.2 for windows.
#### Created new public repository on github.
#### Generation of access token and first commit to repo with README.md and TaskDefinition.rtf

### First domain classes - 1h
#### Created gradle project
#### Created Watch in domain package with unit tests
#### Created Discount in domain package with unit tests

### Domain - calculation of watch prices - 30mins
#### Created WatchPriceCalculator for one watch type with unit tests
#### Created method for many watch types with unit tests

### Exposed rest service - 1h 30mins
#### Moved project to spring boot application
#### Added checkout POST endpoint
#### Added basic IT tests for CheckoutController

### First version of working solution - 1h
#### Added repository in memory with unit tests
#### Connected endpoint with repository and calculator

### TODO
#### Change to use JPA Repository with initial data instead of in memory repository
#### Change to return http status 400 when providing non existing watch id
#### Add logging
#### Add continous integration of this project on some free CI service

