package com.example.springstompchat.config;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomUserManager {
    private static final HashMap<String, AtomicInteger> map;

    static {
        map = new HashMap<String, AtomicInteger>();
    }

    public static void addUser(String id) {
        AtomicInteger roomUsers = map.get(id);
        if (roomUsers == null) {
            roomUsers = createRoom(id);
        }
        roomUsers.getAndIncrement();
    }

    public static void removeUser(String id) {
        AtomicInteger roomUsers = map.get(id);
        if (roomUsers == null) {
            roomUsers = createRoom(id);
        }
        roomUsers.getAndDecrement();
    }

    public static synchronized AtomicInteger createRoom(String id) {
        AtomicInteger atomicInteger = map.get(id);
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            map.put(id, atomicInteger);
        }
        return atomicInteger;
    }

    public static AtomicInteger getRoom(String id) {
        return map.get(id);
    }
}
