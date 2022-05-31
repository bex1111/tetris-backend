# Tetris backend

[Project board](https://github.com/bex1111/tetris-backend/projects/1)

# Use full command

## Mutation test

### First run

`mvn clean install -DskipTests && mvn org.pitest:pitest-maven:mutationCoverage`

### Second and after

`mvn install -DskipTests && mvn -DwithHistory org.pitest:pitest-maven:mutationCoverage`

# Leírás

## Általános

- Az elemek mindig középen jönnek létre
- A pontot az üres elemek számossága adja egy ütközésnél.

## Végpontok

- `/startGame` játék indításához szükséges
    - **username**-t vár
    - **token**-t add vissza amivel lehetséges az irányítás
    - a játékok maximum számossága korlátozva van
- `/control` az elem iránytását teszi lehetővé
    - queue alapú működése van maximum 30 lépés határozható meg előre
    - törlésre nincs lehetőség
    - ami bent van a queue-ban az a követkző lépésben végre fog hajtódni
    - **token** és **username** alapján azonosít
- `/tetris` socketes végpont
    - az összes játék információját visszadja
    - csak akkor kommunikál ha van folyamatban játék

## Elemek:

### Square:

OO\
OO

### L

O\
O\
OO

### Pyramid

O\
OO\
O

### Straight

O\
O\
O

## Végszó

**Ami ezen leírásból nem derül ki arról beszéljen a kód és a tesztek.**