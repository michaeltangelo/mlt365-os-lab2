###################################################
# Variables:
###################################################

# main program to run
main=Lab2

# default command line arguments
args=1111 16 2
args=

###################################################
# Targets:
###################################################
default: jc

jc javac c compile: 
	test -d bin || mkdir bin
	javac -d bin src/*.java

j java r run: 
	java -classpath bin ${main} ${args}

cr: compile run

###################################################
# Housekeeping:
###################################################
clean:
	-rm -f bin/*.class *~

zip:	clean
	cd ..; zip mlt365-lab02.zip -r mlt365-lab02

###################################################
# End
###################################################
