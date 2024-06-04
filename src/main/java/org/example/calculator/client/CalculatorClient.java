package org.example.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Need 2 arguments to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        switch (args[0]) {
            case "sum": doSum(channel);
                break;
            default:
                System.out.println("Keyword invalid: " + args[0]);
        }

        System.out.println("shutting down");
        channel.shutdown();


    }

    private static void doSum(ManagedChannel channel){
        System.out.println("Entered doSum!");
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);
        SumResponse response = stub.sum(SumRequest.newBuilder().setFirstNum(3).setSecondNum(10).build());

        System.out.println("sum of 3 + 10: " + response.getResult());
    }
}
