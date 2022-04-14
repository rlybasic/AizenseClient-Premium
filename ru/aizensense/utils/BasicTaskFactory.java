/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.aizensense.utils.BasicTask;
import ru.aizensense.utils.TaskFactory;

public abstract class BasicTaskFactory<T extends BasicTask>
implements TaskFactory<T> {
    private final List<T> tasks = new ArrayList<T>();
    private BasicTask currentTask = null;

    public void addTask(T task) {
        this.tasks.add(task);
    }

    @Override
    public void removeTask(String name) {
        this.tasks.remove(this.getTask(name));
    }

    @Override
    public void removeTask(T task) {
        this.tasks.remove(task);
    }

    @Override
    public List<T> getTasks() {
        return this.tasks;
    }

    public boolean startTask(T task) {
        if (this.currentTask == task) {
            this.currentTask.setOnline(true);
            return true;
        }
        if (this.isCurrentlyTasking()) {
            if (this.currentTask.getPriority() < ((BasicTask)task).getPriority()) {
                this.currentTask.setOnline(false);
                this.currentTask = task;
                this.currentTask.setOnline(true);
                return true;
            }
            return false;
        }
        if (!this.isCurrentlyTasking()) {
            this.currentTask = task;
            this.currentTask.setOnline(true);
        }
        return true;
    }

    public void finishTask(T task) {
        if (this.currentTask == task) {
            this.currentTask.setOnline(false);
            this.currentTask = null;
        }
    }

    public T getTask(String taskName) {
        BasicTask basicTask = null;
        for (BasicTask basicTask1 : this.getTasks()) {
            if (!basicTask1.getName().equalsIgnoreCase(taskName)) continue;
            basicTask = basicTask1;
            break;
        }
        return (T)basicTask;
    }

    public boolean comparePriority(T task) {
        return ((BasicTask)task).getPriority() >= this.currentTask.getPriority();
    }

    public boolean isCurrentlyTasking() {
        return Objects.nonNull(this.currentTask);
    }
}

