
nativeExecutable:
	./gradlew nativeBuild

clean:
	./gradlew clean

d1:
	(time scripts/run) || true

d2:
	(scripts/run 2>/dev/null; echo $$?)

d3:
	scripts/run -h

d4:
	scripts/run filesys ls . | head -n 10

d5:
	scripts/run filesys find . | head -n 10

d6:
	scripts/run foobar fizzbuzz baz

d7:
	scripts/run history show | perl -ne 'if($$. <= 5){print STDERR $$_} else {print}' | tail -n 5
