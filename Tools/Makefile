#!/bin/sh
# Dependencies downloader for ORANGE project
# Author: Quoc-Viet Nguyen

GAE=appengine-java-sdk
GAE_ARC=appengine-java-sdk-1.7.1.zip
GAE_URL=http://googleappengine.googlecode.com/files/appengine-java-sdk-1.7.1.zip

JENKINS=jenkin.war
JENKINS_URL=http://mirrors.jenkins-ci.org/war/1.478/jenkins.war

JUNIT=junit.jar
JUNIT_URL=http://github.com/downloads/KentBeck/junit/junit-4.10.jar

COBERTURA=cobertura
COBERTURA_ARC=cobertura-1.9.4.1-bin.zip
COBERTURA_URL=http://downloads.sourceforge.net/project/cobertura/cobertura/1.9.4.1/cobertura-1.9.4.1-bin.zip

all: $(GAE) $(JUNIT) $(COBERTURA)

$(GAE):
	@md5sum --status -c "$@.md5"; if [ $$? -ne 0 ]; then \
	  wget -O "$(GAE_ARC)" "$(GAE_URL)"; \
	  md5sum --status -c "$@.md5"; fi;
	unzip "$(GAE_ARC)"
	mv "$(GAE_ARC:.zip=)" "$@"

$(JENKINS):
	md5sum --status -c "$@.md5"; if [ $$? -ne 0 ]; then \
	  wget -O "$@" "$(JENKINS_URL)"; \
	  md5sum --status -c "$@.md5"; fi;

$(JUNIT):
	md5sum --status -c "$@.md5"; if [ $$? -ne 0 ]; then \
	  wget -O "$@" "$(JUNIT_URL)"; \
	  md5sum --status -c "$@.md5"; fi;

$(COBERTURA):
	md5sum --status -c "$@.md5"; if [ $$? -ne 0 ]; then \
	  wget -O "$(COBERTURA_ARC)" "$(COBERTURA_URL)"; \
	  md5sum --status -c "$@.md5"; fi;
	unzip "$(COBERTURA_ARC)"
	mv "$(COBERTURA_ARC:-bin.zip=)" "$@"
