# protobuf-vs-json

---
The conclusions of this experiment, for the proposed payload structure, are the following:
- TIME: the serialization/deserialization time is similar using either JSON(Jackson2) or Protocol Buffers v3 (around 2 millis, for a payload sent via http using restTemplate);
- SIZE: the payload byte count for the protobuf message is about half of the JSON message (e.g. proto_size=202 bytes, json_size=520 bytes)

Checkout [payload.proto schema](https://github.com/mcicu/protobuf-vs-json/blob/master/src/main/resources/proto/payload.proto) in order to be aware of the proposed schema for the data model.

###Requirements for current implementation

NONE. You can run the tests or start the http service directly.


###Requirements for future development

Download and install `protoc` compiler from https://github.com/protocolbuffers/protobuf/releases/tag/v3.11.4
(be sure to match the `protobuf-java` library version from pom.xml).
The `protoc` compiler will be used to compile the `.proto` files into java classes.

To compile the `.proto` files, go to `/protobuf-vs-json/src/main` folder and run
```shell script
protoc --java_out=java resources/proto/*.proto
```
(!) Note that the `payload.proto schema` is already compiled and the generated class is located in `com.mcicu.protobufvsjson.protobuf` package.


