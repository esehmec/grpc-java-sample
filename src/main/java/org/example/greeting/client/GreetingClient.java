package org.example.greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Need argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        switch (args[0]) {
            case "greet": doGreet(channel);
            break;
            default:
                System.out.println("Keyword invalid: " + args[0]);
        }

        System.out.println("shutting down");
        channel.shutdown();


    }

    private static void doGreet(ManagedChannel channel){
        System.out.println("Entered doGreet!");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingResponse response = stub.greet(GreetingRequest.newBuilder().setFirstName("Eyyup").build());

        System.out.println("Greeting: " + response.getResult());
    }

}
