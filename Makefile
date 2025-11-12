
nativeExecutable:
	./gradlew nativeBuild

clean:
	./gradlew clean

present:
	pympress presentation/presentationSlideshowWithNotes.pdf

executableInfo:
	find build/native/nativeCompile/ -type f  | xargs -I{} file {}
	@echo
	@echo
	find build/native/nativeCompile/ -type f  | xargs -I{} ls -lh {}

define pause_and_run
	@echo "+ $1"
	@read -p "Prese Enter to run..."
	@$1
endef

#d1:
#	(time scripts/run) || true
#
#d2:
#	time (scripts/run 2>/dev/null; echo $$?)
#
d1:
	$(call pause_and_run, time (scripts/run -h))

d2:
	$(call pause_and_run, time (scripts/run filesys ls . | head -n 10))

d3:
	$(call pause_and_run, time (scripts/run filesys find . | head -n 10))

d4:
	$(call pause_and_run, time (scripts/run -v filesys ls .))

d5:
	$(call pause_and_run, time (scripts/run foobar fizzbuzz baz))

d6:
	$(call pause_and_run, time (scripts/run history show | perl -ne 'if($$. <= 5){print STDERR $$_} else {print}' | tail -n 5))
