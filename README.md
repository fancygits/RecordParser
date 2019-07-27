# RecordParser
A command line app that takes as input a file with a set of records in one of three formats described below, and outputs (to the screen) the set of records sorted in one of three ways.

## Command-Line Options



## Input Record formats
A record consists of the following 5 fields: last name, first name, gender, date of birth and favorite color. The input is 3 files, each containing records stored in a different format. You may generate these files yourself, and you can make certain assumptions if it makes solving your problem easier.

- The pipe-delimited file lists each record as follows: 

`LastName | FirstName | Gender | FavoriteColor | DateOfBirth`

- The comma-delimited file looks like this: 

`LastName, FirstName, Gender, FavoriteColor, DateOfBirth`

-	The space-delimited file looks like this: 

`LastName FirstName Gender FavoriteColor DateOfBirth`

## Sort Options

-	"gender" â€“ sorted by gender (females before males) then by last name ascending.

-	"birthdate" â€“ (DEFAULT) sorted by birth date, ascending.

-	"lastname" â€“ sorted by last name, descending.

Display dates in the format M/D/YYYY.


## Assumptions
- DateOfBirth is formatted either as "M/d/y", "y-M-d", "y.M.d".
- For the scope of this project, any gender other than "m", "male", "f", or "female" will be labeled as "other".
