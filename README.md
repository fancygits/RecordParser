# RecordParser
A command line app that takes as input a file with a set of records in one of three formats described below, and outputs (to the screen) the set of records sorted in one of three ways.

[RecordParser JAR hosted on Google Drive](https://drive.google.com/file/d/1TnIIYTOtV1RO7xXxDb15z6jfm-ildqaq/view?usp=sharing "Download RecordParser JAR")

----

## Command-Line Options

\* *One or more filenames with records to import*

`java -jar RecordParser.jar records.txt people.csv`

--record <RECORD> *- A single delimited record to import*

`java -jar RecordParser.jar --record "Macy, William H, male, blue, 1950-03-13"`

--sort <METHOD> *- The method to sort records by (see below for options)*

`java -jar RecordParser.jar --sort gender "records.txt" "people.csv"`

--output <FILENAME> *- A desired filename to output records to*

`java -jar RecordParser.jar input.txt records.txt --sort birthdate --output combined.txt`

--server *- Starts a web server on port 4567 to GET and POST records* (**NOTE**: *If server flag isn't provided, records output to standard out*)

`java -jar RecordParser.jar --server piped-records.txt`

--help *- Displays the options*

`java -jar RecordParser.jar --help`

----

## Input Record formats
A record consists of the following 5 fields: *last name, first name, gender, favorite color, and date of birth*.

The pipe-delimited file lists each record as follows: 

`LastName | FirstName | Gender | FavoriteColor | DateOfBirth`

The comma-delimited file looks like this: 

`LastName, FirstName, Gender, FavoriteColor, DateOfBirth`

The space-delimited file looks like this: 

`LastName FirstName Gender FavoriteColor DateOfBirth`


## Sort Options

-	"gender" - sorted by gender (females before males) then by last name ascending.

-	"birthdate" - (DEFAULT) sorted by birth date, ascending.

-	"lastname" - sorted by last name, descending.

Dates display in the format M/D/YYYY.

----

## Assumptions
- DateOfBirth is formatted either as "M/d/y", "y-M-d", "y.M.d".
- For the scope of this project, any gender other than "m", "male", "f", or "female" will be labeled as "other".
