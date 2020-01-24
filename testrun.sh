#!/bin/sh

#Startup Manager
cd cloud-storage-manager
ls
echo running manager build
#removes carriage returns on win systems
sed -i.bak 's/\r$//' gradlew

./gradlew clean build
cd build/libs
echo starting manager
java -jar cloud-storage-manager-0.0.1-SNAPSHOT &

#Startup 4 Nodes
cd ../../../cloud-storage-node
mvn clean package
cd target
mkdir node1
mkdir node2
mkdir node3
mkdir node4

cp ./cloud-storage-node-1.jar ./node1/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node2/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node3/cloud-storage-node-1.jar
cp ./cloud-storage-node-1.jar ./node4/cloud-storage-node-1.jar

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
curl http://localhost:8080/test
rm -rf node1
rm -rf node2
rm -rf node3
rm -rf node4

