package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        // Если элемента нет, binarySearch вернёт (-(insertionPoint) - 1)
        int insertIndex = -index - 1;
        // Сдвинем массив в insertIndex
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        // Заполняем новый элемент
        storage[insertIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
    }
}
