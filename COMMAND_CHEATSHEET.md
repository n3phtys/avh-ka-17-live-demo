# Command Cheatsheet


## HTML / CSS / JS

npm install http-server -g

http-server ./HTML_CSS_JS -o -p 8070


## Java / Kotlin

gradlew run


## SQL 

sqlite3

.read myfile.sql

CREATE TABLE huettens(vorname VARCHAR(30), nachname VARCHAR(30));

INSERT INTO huettens VALUES('gruin', 'kaag');

INSERT INTO huettens VALUES('doofie', 'malcher');

SELECT * FROM huettens;


## Rust

cargo run


## Golang

go run main.go

## C Lang

gcc main.c

gcc -S main.c

./a.out

clang -S -emit-llvm main.c

clang -emit-llvm -o main.bc -c main.c


## LLVM Pass

WIP
