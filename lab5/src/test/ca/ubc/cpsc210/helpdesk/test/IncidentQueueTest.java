package ca.ubc.cpsc210.helpdesk.test;


import ca.ubc.cpsc210.helpdesk.model.Incident;
import ca.ubc.cpsc210.helpdesk.model.IncidentQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidentQueueTest {
    // Design your unit tests for the IncidentQueue class here
    private IncidentQueue queue;
    private Incident first;

    @BeforeEach
    public void runBefore() {
        queue = new IncidentQueue();
        first = new Incident(1, "");
        queue.addIncident(first);

        for (int i = 2; i < 9; i++) {
            Incident incident = new Incident(i, "");
            queue.addIncident(incident);
        }
    }

    @Test
    public void testAddIncident() {
        Incident i = new Incident(10, "");
        assertTrue(queue.addIncident(i));

        Incident ii = new Incident(11, "");
        queue.addIncident(i);
        assertFalse(queue.addIncident(ii));
    }

    @Test
    public void testGetNextIncident() {
        assertEquals(first, queue.getNextIncident());
    }

    @Test
    public void testGetPositionInQueueOfCaseNumber() {
        assertEquals(2, queue.getPositionInQueueOfCaseNumber(2));
        assertEquals(-1, queue.getPositionInQueueOfCaseNumber(15));
    }

    @Test
    public void testLength() {
        assertEquals(8, queue.length());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(queue.isEmpty());

        queue = new IncidentQueue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(queue.isFull());

        Incident i = new Incident(10, "");
        Incident ii = new Incident(11, "");
        queue.addIncident(i);
        queue.addIncident(ii);

        assertTrue(queue.isFull());
    }
}