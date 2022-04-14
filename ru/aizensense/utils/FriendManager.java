/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.utils;

import java.util.ArrayList;

public class FriendManager {
    public static ArrayList<String> friendsList = new ArrayList();

    public static void addFriend(String friendname) {
        if (!friendsList.contains(friendname)) {
            friendsList.add(friendname);
        }
    }

    public static void removeFriend(String friendname) {
        if (friendsList.contains(friendname)) {
            friendsList.remove(friendname);
        }
    }

    public static void clear() {
        if (!friendsList.isEmpty()) {
            friendsList.clear();
        }
    }
}

