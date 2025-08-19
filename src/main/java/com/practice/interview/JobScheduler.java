package com.practice.interview;

import java.util.*;

class JobScheduler {

    static Queue<Patient> waitingRoom = new LinkedList();
    static int attendTime = 10;
    static int time = 0;

    public static void main (String[] args) {
        List<Patient> patients = new LinkedList();
        patients.add(new Patient("Abigail", 0, 1));
        patients.add(new Patient("Brian", 0, 10));
        patients.add(new Patient("Christina", 5, 3));
        patients.add(new Patient("David", 20, 3));

        TreeSet<Patient> nextAttendants = new TreeSet<Patient>((Patient p1, Patient p2) -> {
            if(p1.priority == p2.priority) return Integer.compare(p1.t, p2.t);
            else return Integer.compare(p2.priority, p1.priority);
        });

        getNextPatient(patients, nextAttendants);
        int count = 0;

        while(!waitingRoom.isEmpty()){
            Patient nextPatient = waitingRoom.remove();
            int currentTime = count++ * attendTime;
            System.out.println(nextPatient.name
                    + " time:"  + Math.max(currentTime, nextPatient.t)
                    + " priority:" + nextPatient.priority
            );
        }
    }

    static public void getNextPatient(List<Patient> patients, TreeSet<Patient> nextAttendants){

        for(int i = 0; i < patients.size(); i++){
            Patient patient = patients.get(i);
            if(patient.t <= time){
                nextAttendants.add(patient);
//                patients.remove(patient);
            }
        }
        patients.removeAll(nextAttendants);
        time += attendTime;
        waitingRoom.add(nextAttendants.pollFirst());
        if(patients.size() > 0){
            getNextPatient(patients, nextAttendants);
        } else {
            while(!nextAttendants.isEmpty()){
                waitingRoom.add(nextAttendants.pollFirst());
            }
        }


    }


    static class Patient {
        String name;
        int t;
        int priority;

        Patient(String name, int t, int priority){
            this.name = name;
            this.t = t;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Patient{" +
                    "name='" + name + '\'' +
                    ", t=" + t +
                    ", priority=" + priority +
                    '}';
        }
    }
}