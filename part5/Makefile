all: t2 t3 t4 t5 

t2:
	javac Test2.java; java Test2 > Test2.output; diff Test2.correct Test2.output

t3:
	javac Test3.java; java Test3 > Test3.output; diff Test3.correct Test3.output

t4:
	javac Test4.java; java Test4 > Test4.output; diff Test4.correct Test4.output

t5:
	javac Test5.java; java Test5 > Test5.output; diff Test5.correct Test5.output


clean:
	/bin/rm -f *.class core *.output
