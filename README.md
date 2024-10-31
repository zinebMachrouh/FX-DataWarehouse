# FX-DataWareHouse Project

## Description

This project is a simple data warehouse that stores the deals that are sent to it and returns them when requested.

## Technologies

- Java 11 
- Spring Boot
- Docker
- Postgres
- Junit
- Mockito
- Maven
- Lombok
- Makefile

## API DOCS

### get all deals

`/api/v1/deal`

| method | payload    | response                                                                                                            |
|--------|------------|---------------------------------------------------------------------------------------------------------------------|
| `GET`  | -          | [{id : `string?`, fromCurrency: `String`, toCurrency: `String`, amount: `double`, deal_timestamp: `LocalDateTime`}] |


### send a single deal

`/api/v1/deal`

|method|payload|response|
| -----|-------|-------|
| `POST`| {id : `string?`, fromCurrency: `String`, toCurrency: `String`, amount: `double` } | {id : `string?`, fromCurrency: `String`, toCurrency: `String`, amount: `double`, deal_timestamp: `LocalDateTime`}|

### send a list of deals(batch save)

`/api/v1/deal/batchSave`

|method|payload|response|
| -----|-------|-------|
| `POST`| {id : `string?`, fromCurrency: `String`, toCurrency: `String`, amount: `double` }[] | {id : `string?`, fromCurrency: `String`, toCurrency: `String`, amount: `double`, deal_timestamp: `LocalDateTime`}[]|

## Deploymenet

use only one command `makefile run-project` and voila! the project is runned

- if you want to run it without docker you can replace the config of the application.yml with your own config
- for better docker optimization i used the multstaging in order to reduce the size of the project image

