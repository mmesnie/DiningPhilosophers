all:
	@echo "usage: make <applet|standalone>"

standalone: clean
	ln -sf DiningPhilosopherStandalone.java DiningPhilosopher.java
	javac DiningPhilosopher.java
	java DiningPhilosopher

applet: clean
	ln -sf DiningPhilosopherApplet.java DiningPhilosopher.java
	javac DiningPhilosopher.java
	appletviewer ./index.html

clean:
	\rm -f *.class DiningPhilosopher.java
