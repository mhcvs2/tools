package com.mhc.rpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import os.mhc.rpc.server.Ip;
import os.mhc.rpc.server.Name;
import os.mhc.rpc.server.ServiceGrpc;

import java.util.concurrent.TimeUnit;

public class NameClient {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8080;

    private ManagedChannel managedChannel;

    private ServiceGrpc.ServiceBlockingStub serviceBlockingStub;

    public NameClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build());
    }

    public NameClient(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
        this.serviceBlockingStub = ServiceGrpc.newBlockingStub(managedChannel);
    }

    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String getIpByName(String n) {
        Name name = Name.newBuilder().setName(n).build();
        Ip ip = serviceBlockingStub.getIpByName(name);
        return ip.getIp();
    }

    public static void main(String[] args) {
        NameClient nameClient = new NameClient(DEFAULT_HOST, DEFAULT_PORT);
        System.out.println(nameClient.getIpByName("sunny"));
    }

}
