package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected final static int MAX_SIZE = 10000;
    protected final Resume[] storage = new Resume[MAX_SIZE];
    protected int size;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " does not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
