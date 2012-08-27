#/bin/sh
JPATH="jenkins.war"

# Jenkins home
JHOME=$(readlink -f `dirname "$0"`)

# Look for Jenkins archive
if [ ! -f "$JPATH" ]; then
  JPATH="../$JPATH"
  if [ ! -f "$JPATH" ]; then
    JPATH="$JHOME/$JPATH"
  fi
fi

# Daemonize
#JDAEMON="--daemon"

# Default port 8080
JHTTPPORT="--httpPort=9090"

java -DJENKINS_HOME="$JHOME" -jar "$JPATH" $JDAEMON $JHTTPPORT
