#/bin/sh

# Jenkins home
JHOME=$(readlink -f `dirname "$0"`)

# Daemonize
#JDAEMON="--daemon"

# Default port 8080
JHTTPPORT="--httpPort=9090"

java -DJENKINS_HOME="$JHOME" -jar jenkins.war $JDAEMON $JHTTPPORT
