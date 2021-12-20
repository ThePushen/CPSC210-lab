package model;

import java.util.Objects;

// Represents a student with an id, name, the grade in which the student is registered and bus to which
// student is assigned to travel
public class Student {

    private int id;
    private String name;
    private int grade;
    private Bus bus;

    // EFFECTS: constructs student with id, name and registered grade
    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        // stub
    }

    public int getId() {
        return id;   // stub
    }

    public String getName() {
        return name;    // stub
    }

    public int getGrade() {
        return grade;    // stub
    }

    public Bus getAssignedBus() {
        return bus;   // stub
    }

    // EFFECTS: returns true if student is assigned to a bus, false otherwise
    public boolean isAssignedToBus() {
        return !(getAssignedBus() == null);   // stub
    }

    // REQUIRES: !bus.isFull()
    // MODIFIES: this, bus
    // EFFECTS: assigns student to travel on bus
    public void assignToBus(Bus bus) {
        if (!(getAssignedBus() == bus) && isAssignedToBus()) {
            getAssignedBus().removeStudent(this);
            // stub
        }
        this.bus = bus;
        bus.addStudent(this);
    }

    // MODIFIES: this, Bus b = getAssignedBus()
    // EFFECTS: if student is assigned to a bus, removes student from assigned bus;
    // otherwise has no effect
    public void removeFromBus() {
        if (isAssignedToBus()) {
            bus.removeStudent(this);
            bus = null;
        }
    }
}
