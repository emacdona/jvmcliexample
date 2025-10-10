
nativeExecutable:
	./gradlew nativeBuild

clean:
	./gradlew clean

#d1:
#	(time scripts/run) || true
#
#d2:
#	time (scripts/run 2>/dev/null; echo $$?)
#
d1:
	time (scripts/run -h)

d2:
	time (scripts/run filesys ls . | head -n 10)

d3:
	time (scripts/run filesys find . | head -n 10)

d4:
	time (scripts/run foobar fizzbuzz baz)

d5:
	time (scripts/run history show | perl -ne 'if($$. <= 5){print STDERR $$_} else {print}' | tail -n 5)
