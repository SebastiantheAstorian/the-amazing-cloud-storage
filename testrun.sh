#!/bin/sh

rm -rf cloud-storage-node/target/node1
rm -rf cloud-storage-node/target/node2
rm -rf cloud-storage-node/target/node3
rm -rf cloud-storage-node/target/node4
#Startup Manager
cd cloud-storage-manager
echo running manager build
./gradlew clean build > /dev/null 2>&1
cd build/libs
echo starting manager
java -jar cloud-storage-manager-0.0.1-SNAPSHOT.jar & > /dev/null 2>&1

#Startup 4 Nodes
cd ../../../cloud-storage-node
echo compiling node

mvn clean package > dev/null 2>&1
echo creating directories for nodes
cd target
mkdir node1
mkdir node2
mkdir node3
mkdir node4
echo copying jar to node directories
cp ./cloud-storage-node-1.jar ./node1/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node2/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node3/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node4/cloud-storage-node-1.jar
echo running nodes
cd node1 
java -jar -Dserver.port=4040 cloud-storage-node-1.jar & > /dev/null 2>&1
cd ../node2
java -jar -Dserver.port=5050 cloud-storage-node-1.jar & > /dev/null 2>&1
cd ../node3
java -jar -Dserver.port=6060 cloud-storage-node-1.jar & > /dev/null 2>&1
cd ../node4
java -jar -Dserver.port=7070 cloud-storage-node-1.jar & > /dev/null 2>&1
cd ../../..

echo starting test in 10 seconds
sleep 10
curl http://localhost:8080/test | tee storagelog.log

echo Output logged in storage-log.log
pkill java > /dev/null 2>&1
