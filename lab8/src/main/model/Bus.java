package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Represents a bus having an id, capacity, chaperone and set of students assigned to travel on bus
public class Bus {

    private int id;
    private int capacity;
    private Chaperone chaperone;
    private Set<Student> students;

    // EFFECTS: constructs bus with id and capacity
    public Bus(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        students = new HashSet<>();
    }

    public int getId() {
        return id;   // stub
    }

    public int getCapacity() {
        return capacity;   // stub
    }

    public Chaperone getChaperone() {
        return chaperone;   // stub
    }

    // EFFECTS: returns true if bus has a chaperone assigned, false otherwise
    public boolean hasChaperone() {
        return (!(getChaperone() == null));   // stub
    }

    // EFFECTS: returns an unmodifiable set of students assigned to travel on this bus
    public Set<Student> getStudents() {
        Set<Student> unStudents = Collections.unmodifiableSet(students);
        return unStudents;    // stub
    }

    // MODIFIES: this
    // EFFECTS: assigns the chaperone for this bus
    public void setChaperone(Chaperone chaperone) {
        this.chaperone = chaperone;
    }

    // EFFECTS: returns true if bus is full, false otherwise
    public boolean isFull() {
        return students.size() == capacity;    // stub
    }

    // REQUIRES: !isFull()
    // MODIFIES: this, student
    // EFFECTS: adds student to this bus
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            if (student.isAssignedToBus()) {
                student.getAssignedBus().removeStudent(student);
            }
            students.add(student);
            student.assignToBus(this);
        }
    }

    // MODIFIES: this, student
    // EFFECTS: removes student from this bus
    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.removeFromBus();
        }
    }
}
