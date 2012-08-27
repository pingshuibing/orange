# Dependencies downloader for ORANGE project
# Author: Quoc-Viet Nguyen

GAE=appengine-java-sdk
GAE_ARC=appengine-java-sdk-1.7.1.zip
GAE_URL=http://googleappengine.googlecode.com/files/appengine-java-sdk-1.7.1.zip

JENKINS=jenkins.war
JENKINS_URL=http://mirrors.jenkins-ci.org/war/1.478/jenkins.war

JUNIT=junit.jar
JUNIT_URL=http://github.com/downloads/KentBeck/junit/junit-4.10.jar

COBERTURA=cobertura
COBERTURA_ARC=cobertura-1.9.4.1-bin.zip
COBERTURA_URL=http://downloads.sourceforge.net/project/cobertura/cobertura/1.9.4.1/cobertura-1.9.4.1-bin.zip

# Verify checksum
# #1: md5 file, #2: archieve, #3: url
CHKSUM=\
  md5sum --status -c "$(1)";            \
  if [ $$? -ne 0 ]; then                \
    wget -O "$(2)" "$(3)";              \
    md5sum --status -c "$(1)";          \
  fi;

all: $(GAE) $(JUNIT) $(COBERTURA) $(JENKINS)

$(GAE):
	$(call CHKSUM,$@.md5,$(GAR_ARC),$(GAE_URL))
	unzip "$(GAE_ARC)"
	mv "$(GAE_ARC:.zip=)" "$@"

$(JENKINS):
	$(call CHKSUM,$@.md5,$@,$(JENKINS_URL))

$(JUNIT):
	$(call CHKSUM,$@.md5,$@,$(JUNIT_URL))

$(COBERTURA):
	$(call CHKSUM,$@.md5,$(COBERTURA_ARC),$(COBERTURA_URL))
	unzip "$(COBERTURA_ARC)"
	mv "$(COBERTURA_ARC:-bin.zip=)" "$@"
