# run the app
- take the jar file from target folder
- run the file from command line
> (example) java -jar simbirsoft-test-jar-with-dependencies.jar \[arg0] \[arg1]

keep in mind: it takes exactly two arguments - url (make sure it has http/https protocol) and display/logging predefined constants (see section below)

# display/logging predefined constants
- 0: error logging into the console/display result in console
- 1: logging into the file/display result in console
- 2: logging into the console/save result in db (make sure you have MySQL server up and running locally with baseschema schema, root/root and parse_results table)
- 3: logging into the file/save result in db (make sure you have MySQL server up and running locally with baseschema schema, root/root and parse_results table)

# example
> java -jar simbirsoft-test-jar-with-dependencies.jar https://www.simbirsoft.com 1

# tests
- test can be found in test folder, developed with help of JUnit 5 library

# jar
- jar archive was built with help of Maven