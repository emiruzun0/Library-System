JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Library.java \
		  Admin.java \
		  Test.java \



default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Test