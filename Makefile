all:
	@echo "usage: make <applet|standalone>"

standalone:
	ln -sf DiningPhilosopherStandalone.java DiningPhilosopher.java
	javac DiningPhilosopher.java
	java DiningPhilosopher

applet:
	ln -sf DiningPhilosopherApplet.java DiningPhilosopher.java
	javac DiningPhilosopher.java
	appletviewer ./index.html

clean:
	\rm -f *.class DiningPhilosopher.java
