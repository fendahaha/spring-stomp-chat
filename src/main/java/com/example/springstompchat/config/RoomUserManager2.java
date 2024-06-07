package com.example.springstompchat.config;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RoomUserManager2 {
    private static final ConcurrentHashMap<String, HashMap<String, Boolean>> map;

    static {
        map = new ConcurrentHashMap<String, HashMap<String, Boolean>>();
    }

    public static void addUser(String roomId, String sessionId) {
        HashMap<String, Boolean> room = map.get(roomId);
        if (room == null) {
            room = createRoom(roomId);
        }
        room.put(sessionId, true);
    }

    public static void removeUser(String roomId, String sessionId) {
        HashMap<String, Boolean> room = map.get(roomId);
        if (room == null) {
            room = createRoom(roomId);
        }
        room.remove(sessionId);
    }

    public static synchronized HashMap<String, Boolean> createRoom(String roomId) {
        HashMap<String, Boolean> room = map.computeIfAbsent(roomId, k -> new HashMap<String, Boolean>());
        return room;
    }

    public static Integer getRoomUsers(String roomId) {
        HashMap<String, Boolean> room = map.get(roomId);
        if (room != null) {
            return room.values().stream().filter(e -> e).toList().size();
        }
        return 0;
    }
}
