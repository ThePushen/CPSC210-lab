package ca.ubc.cpsc210.helpdesk.model;

import java.util.LinkedList;

// Represents a queue of incidents to be handled by helpdesk
// with maximum size MAX_SIZE
public class IncidentQueue {
    public static final int MAX_SIZE = 10;
    // TODO: complete the design of the IncidentQueue class

    private LinkedList<Incident> queue;

    // EFFECTS: initializes each newly created IncidentQueue as an empty queue
    public IncidentQueue() {
        queue = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the queue is not full, adds Incident to the end of the queue and returns true
    //          if the queue is full, the method returns false
    public boolean addIncident(Incident incident) {
        if (queue.size() >= MAX_SIZE) {
            return false;
        } else {
            queue.addLast(incident);
            return true;
        }
    }

    // MODIFIES: this
    // REQUIRES: the queue is not empty
    // EFFECTS: removes the Incident at the front of the queue and returns it
    public Incident getNextIncident() {
        Incident first = queue.getFirst();
        queue.removeFirst();
        return first;
    }

    // EFFECTS: takes a case number (an int) as an argument and returns the position (an int) in the queue of the
    //          Incident with that case number (case numbers are unique!)
    //          If there is no incident in the queue with the given case number, the method must return the value -1
    public int getPositionInQueueOfCaseNumber(int caseNumber) {
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).getCaseNum() == caseNumber) {
                return i + 1;
            }
        }
        return -1;
    }

    // EFFECTS: returns an int that represents the number of incidents currently in the queue
    public int length() {
        return queue.size();
    }

    // EFFECTS: returns a boolean: true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // EFFECTS: returns a boolean: true if the queue is full, false otherwise
    public boolean isFull() {
        return queue.size() == MAX_SIZE;
    }
}
