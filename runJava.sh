JAVA_PATH=/export/apps/jdk/JDK-1_8_0_5/bin
$JAVA_PATH/javac Test.java

#10g heap is suggested
$JAVA_PATH/java -Xmx$1 -Xms$1 -Xloggc:gc.log -XX:+UseG1GC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps Test


