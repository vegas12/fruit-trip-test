This is built with java version 11.0.17.

Build and run with ./build_and_run.sh

To build and run tests use ./build_and_run_tests.sh

TODOS:\
Use some other method to read and persist data - Sqlite or file.\
Add more unit tests.\
Handle invalid data in repository\
The use case should return some object so that unit testing can be done and Application can decide which text to print.
The repository should be injected into the use case so that it can be mocked in unit tests.