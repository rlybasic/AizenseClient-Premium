/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.utils;

import ru.aizensense.utils.Task;

public abstract class BasicTask
implements Task {
    private String name;
    private int priority;
    private boolean online;

    public BasicTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public boolean isOnline() {
        return this.online;
    }

    @Override
    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

