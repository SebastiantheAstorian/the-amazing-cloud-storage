#!/bin/sh

rm -rf cloud-storage-node/target/node1
rm -rf cloud-storage-node/target/node2
rm -rf cloud-storage-node/target/node3
rm -rf cloud-storage-node/target/node4
#Startup Manager
cd cloud-storage-manager
ls
echo running manager build
./gradlew clean build
cd build/libs
echo starting manager
java -jar cloud-storage-manager-0.0.1-SNAPSHOT.jar &

#Startup 4 Nodes
cd ../../../cloud-storage-node
echo compiling node
ls
mvn clean package
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
ls
echo running nodes
cd node1 
java -jar -Dserver.port=4040 cloud-storage-node-1.jar &
cd ../node2
java -jar -Dserver.port=5050 cloud-storage-node-1.jar &
cd ../node3
java -jar -Dserver.port=6060 cloud-storage-node-1.jar &
cd ../node4
java -jar -Dserver.port=7070 cloud-storage-node-1.jar &
cd ..

echo starting test
sleep 10
curl http://localhost:8080/test

pkill java
