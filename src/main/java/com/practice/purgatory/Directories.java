package com.practice.purgatory;

import java.util.*;

public class Directories {
    public static void main(String [] args){
        Directory root = new Directory("root", "AAA");
        Directory testFolder = new Directory("testFolder", "AAABBBCCC");
        Directory testFolder2 = new Directory("testFolder2", "AAABBB");

        root = insertDirectory(root, testFolder);
        root = insertDirectory(root, testFolder2);
        System.out.println(root.name);
    }

    static Directory insertDirectory(Directory parent, Directory current) {
        if (current.hierarchicalPath.length() > 0){
            String currentPath = current.hierarchicalPath.substring(0, 3);
            if(parent.hierarchicalPath.equals(currentPath)){
                current.hierarchicalPath = current.hierarchicalPath.substring(3);
                currentPath = current.hierarchicalPath.substring(0, 3);
                if(parent.directories.containsKey(currentPath)){
                    if(current.hierarchicalPath.length() == 3){
                        current.hierarchicalPath = "";
                    }
                    parent.directories.put(currentPath, current);
                    insertDirectory(parent.directories.get(currentPath), current);
                } else {
                    Directory newDirectory = current.clone();
                    if(newDirectory.hierarchicalPath.length() == 3){
                        newDirectory.hierarchicalPath = "";
                        parent.directories.put(currentPath, newDirectory);
                    } else {
                        parent.directories.put(currentPath, new Directory(null, currentPath));
                    }
                    insertDirectory(parent.directories.get(currentPath), newDirectory);
                }
            }
        }

        return parent;
    }

    static class Directory implements Cloneable {
        String name;
        String hierarchicalPath;
        Map<String, Directory> directories = new HashMap<>();
        Directory(String name, String hierarchicalPath){
            this.name = name;
            this.hierarchicalPath = hierarchicalPath;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Directory directory = (Directory) o;
            return Objects.equals(hierarchicalPath, directory.hierarchicalPath);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hierarchicalPath);
        }

        @Override
        protected Directory clone() {
            return new Directory(this.name, this.hierarchicalPath);
        }
    }
}
