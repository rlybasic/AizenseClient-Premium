/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.utils;

import java.util.List;
import ru.aizensense.utils.BasicTask;

public interface TaskFactory<T extends BasicTask> {
    public void removeTask(String var1);

    public void removeTask(T var1);

    public List<T> getTasks();
}

