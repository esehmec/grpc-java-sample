package org.example.calculator.server;

import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver){
        responseObserver.onNext(SumResponse.newBuilder().setResult(request.getFirstNum() + request.getSecondNum()).build());
        responseObserver.onCompleted();
    }




}
