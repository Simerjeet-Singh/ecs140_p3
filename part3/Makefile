all: t2 t3 

t2:
	javac Test2.java; java Test2 > Test2.output; diff Test2.correct Test2.output

t3:
	javac Test3.java; java Test3 > Test3.output; diff Test3.correct Test3.output

clean:
	/bin/rm -f *.class core *.output
