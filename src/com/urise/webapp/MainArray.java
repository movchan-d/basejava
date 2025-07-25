package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for com.urise.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;
        while (true) {
            System.out.println("""
                    Введите одну из команд :
                    list | size | save uuid | update uuid | delete uuid | get uuid | clear | exit):
                    """);

            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list" -> printAll();
                case "size" -> System.out.println(ARRAY_STORAGE.size());
                case "save" -> {
                    resume = new Resume(uuid);
                    ARRAY_STORAGE.save(resume);
                    printAll();
                }
                case "update" -> {
                    resume = new Resume(uuid);
                    ARRAY_STORAGE.update(resume);
                    printAll();
                }
                case "delete" -> {
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                }
                case "get" -> System.out.println(ARRAY_STORAGE.get(uuid));
                case "clear" -> {
                    ARRAY_STORAGE.clear();
                    printAll();
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Неверная команда.");
            }
        }
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
