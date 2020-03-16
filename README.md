# protobuf-vs-json

###Requirements
Download and install `protoc` compiler from https://github.com/protocolbuffers/protobuf/releases/tag/v3.11.4
(be sure to match the `protobuf-java` library version from pom.xml).
The `protoc` compiler will be used to compile the `.proto` files into java classes.

To compile the actual `.proto` files, go to `/protobuf-vs-json/src/main` folder and run
```shell script
protoc --java_out=java resources/proto/*.proto
```
