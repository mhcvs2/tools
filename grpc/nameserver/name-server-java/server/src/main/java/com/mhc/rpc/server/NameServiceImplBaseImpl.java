package com.mhc.rpc.server;

import io.grpc.stub.StreamObserver;
import os.mhc.rpc.server.Ip;
import os.mhc.rpc.server.Name;
import os.mhc.rpc.server.ServiceGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NameServiceImplBaseImpl extends ServiceGrpc.ServiceImplBase {

    private Map<String, String> map = new HashMap<String, String>();

    private Logger logger = Logger.getLogger(NameServiceImplBaseImpl.class.getName());

    public NameServiceImplBaseImpl() {
        map.put("sunny", "111.222.333.444");
        map.put("David", "112.344.122.111");
    }

    @Override
    public void getIpByName(Name request, StreamObserver<Ip> responseObserver) {
        logger.log(Level.INFO, "request is coming. args=" + request.getName());
        Ip ip = Ip.newBuilder().setIp(getName(request.getName())).build();
        responseObserver.onNext(ip);
        responseObserver.onCompleted();
    }

    private String getName(String name) {
        String ip = map.get(name);
        if (ip == null) {
            return "0.0.0.0";
        }
        return ip;
    }
}
