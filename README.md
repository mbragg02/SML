SML
===

##OODP 2014. Assignment Two: Simple Machine Language


###Michael Bragg and David North. CS Full time

###Configuration:
Names of classes for different instructions are stored in an external file, "sml.properties".<br />

Other instruction classes can be created, with their names put into the properties file.<br />
The Translator class is able create a new instance of the class with the appropriate arguments.The format of arguments accepted are:

    (label, s1)
    (label, r, s1)
    (label, s1, x)
    (label, r, s1, s2)
Where "label and "x" are strings, s1, r, s2 are integers.

###External Libraries:

    mockikto >= 1.9.5
    Junit 4
    lombok >= 1.12.6

###Operation:
Provide a valid SML program text file as a program argument.

