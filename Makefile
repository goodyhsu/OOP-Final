.PHONY: all clean
CC=javac
SRC=src/
OUT=out/

all:
	$(CC) -s $(SRC) -sourcepath $(SRC) -d $(OUT) $(SRC)*.java $(SRC)*/*.java

run:

	$(CC) -s $(SRC) -sourcepath $(SRC) -d $(OUT) $(SRC)*.java $(SRC)*/*.java
	java -classpath $(OUT) Main

clean:
	rm -rf $(OUT)

