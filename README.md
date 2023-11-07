# Unique File Name Search Application

## Description
This project offers a simple API for users to search for unique files in a specified directory and retrieve details of their previous searches. The API is straightforward to use and allows for checking the uniqueness of files in the provided directory.

## Running

- The source code of the application is available on GitHub:
- https://github.com/promlock/uniquefinder

- An image file of the application is also available here:
- https://hub.docker.com/repository/docker/kovacsp22/uniquefinder/general

- After pulling the application, you can start it using the 'make' command. It runs in two instances, which can be accessed here:
- http://localhost:8081/swagger-ui/index.html
- http://localhost:8082/swagger-ui/index.html

- After usage 'make clean' command available to stop pod and remove containers.


## Endpoints

The API offers two main endpoints:

### /unique-files
- Description: Through this endpoint, users can specify a directory path, and the API checks for the uniqueness of files in that directory.
- HTTP method: GET
- Example call: GET /unique-files?path=/path/to/directory
- Response: The response includes a list of unique files found in the specified directory.

### /history
- Description: Through this endpoint, users can retrieve details of their previous searches, including the search timestamp, user name, and the directory path they searched for.
- As a default setting, pages are returned in reverse chronological order.
- HTTP method: GET
- Example call: GET /history?page=0&size=10&sortField=requestDate&sortDirection=desc
- Response: The response includes details of previous searches paginated.
