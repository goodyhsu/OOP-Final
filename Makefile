.PHONY: all clean
CC=javac
SRC=src/
OUT=out/

all:
	$(CC) -sourcepath $(SRC) -d $(OUT) $(SRC)*.java

run:
	java -classpath out Main

clean:
	rm -rf $(OUT)

