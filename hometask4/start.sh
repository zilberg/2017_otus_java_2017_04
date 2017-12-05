#!/usr/bin/env bash
MEMORY="-Xms512m -Xmx512m"
#Young generation GC
COPYGC="-XX:+UseSerialGC"
SCAVENGEGC="-XX:+UseParallelGC"
PARNEWGC="-XX:+UseParNewGC"
G1GC="-XX:+UseG1GC"
#Old generation GC
#MARKSWEEPCOMPACT="-XX:+UseSerialGC"
PSMARKSWEEP="-XX:+UseParallelOldGC"
CONCURRENTMARKSWEEP="-XX:+UseConcMarkSweepGC"
#Parameter
COUNT="180000"
#Start Young GC
java  $MEMORY $COPYGC -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/0copy_gc.txt
java  $MEMORY $SCAVENGEGC -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/0scavenge_gc.txt
java  $MEMORY $PARNEWGC -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/0par_new_gc.txt
java  $MEMORY $G1GC -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/0g1_gc.txt
#Start Old GC
#java  $MEMORY $MARKSWEEPCOMPACT -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/1mark_sweep_gc.txt
java  $MEMORY $PSMARKSWEEP -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/1ps_mark_sweep_gc.txt
java  $MEMORY $CONCURRENTMARKSWEEP -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/hometask4.jar $COUNT >>./logs/1concurrent_mark_sweep.txt


