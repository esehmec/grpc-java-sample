package org.example.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = ServerBuilder
                .forPort(port)
                .addService(new CalculatorServiceImpl())
                .build();

        server.start();
        System.out.println("Calc Server started!");
        System.out.println("Calc Listening on port: " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Calc Received Shutdown request");
            server.shutdown();
            System.out.println("Calc Server stopped!");
        }));

        server.awaitTermination();
    }
}
