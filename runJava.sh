JAVA_PATH=/usr/local/bin
$JAVA_PATH/javac Test.java
HEAP=5g


#Step 1: Minimum setup
#$JAVA_PATH/java -Xmx$HEAP -Xms$HEAP -Xloggc:gc.log Test

#Step 2: Full setup
$JAVA_PATH/java -Xmx$HEAP -Xms$HEAP -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+UseG1GC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -Dcom.sun.management.jmxremote.port=11091 -Dcom.sun.management.jmxremote.authenticate=false -Xloggc:gc.log Test
