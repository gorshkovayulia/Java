package hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void impossibleToSetNegativeCapacity() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new HashTable(-1));
        assertEquals("Capacity cannot be less or equal to zero!", e.getMessage());
    }

    @Test
    public void impossibleToSetZeroCapacity() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new HashTable(0));
        assertEquals("Capacity cannot be less or equal to zero!", e.getMessage());
    }

    @Test
    public void possibleToSetPositiveCapacity() {
        new HashTable(1);
    }

    @Test
    public void possibleToPutSeveralElementsIntoTheSameSlot() {
        HashTable table = new HashTable(10);
        table.put("t", 15);
        table.put(6, 20);
        assertEquals("(6,20)->(t,15)", table.printSlot(6));
    }

    @Test
    public void valueIsUpdatedIfOldAndNewObjectsHaveTheSameKeys() {
        HashTable table = new HashTable(16);
        table.put("t", 15);
        table.put("t", 20);
        assertEquals(20, table.getValue("t"));
    }

    @Test
    public void capacityIsIncreasedIfCalculatedLoadFactorIsMoreThanDefaultLoadFactor() {
        HashTable table = new HashTable(5);
        table.put("t", 15);
        table.put(6, 20);
        table.put(7, 20);
        table.put(8, 20);
        assertEquals(10, table.getSize());
    }

    @Test
    public void capacityIsNotIncreasedIfCalculatedLoadFactorEqualsToDefaultLoadFactor() {
        HashTable table = new HashTable(4);
        table.put("t", 15);
        table.put(6, 20);
        table.put(7, 20);
        assertEquals(4, table.getSize());
    }

    @Test
    public void capacityIsNotIncreasedIfCalculatedLoadFactorIsLessThanDefaultLoadFactor() {
        HashTable table = new HashTable(4);
        table.put("t", 15);
        table.put(6, 20);
        assertEquals(4, table.getSize());
    }

    @Test
    public void impossibleToRemoveElementWithNullKey() {
        HashTable table = new HashTable(10);
        Exception e = assertThrows(IllegalArgumentException.class, () -> table.remove(null));
        assertEquals("Key cannot be null!", e.getMessage());
    }

    @Test(expected = AssertionError.class)
    public void impossibleToRemoveNotExistingElement() {
        HashTable table = new HashTable(10);
        table.remove("test");
    }

    @Test
    public void possibleToRemoveElementHavingPreviousNode() {
        HashTable table = new HashTable(10);
        table.put("t", 15);
        table.put(6, 20);
        table.remove(6);
        assertEquals("(t,15)", table.printSlot(6));
    }

    @Test
    public void possibleToRemoveElementHavingNextNode() {
        HashTable table = new HashTable(10);
        table.put("t", 15);
        table.put(6, 20);
        table.remove("t");
        assertEquals("(6,20)", table.printSlot(6));
    }

    @Test
    public void possibleToRemoveTheOnlyElementInSlot() {
        HashTable table = new HashTable(10);
        table.put("t", 15);
        table.remove("t");
        assertEquals("null", table.printSlot(6));
    }

    @Test
    public void returnsNullValueForNullKey() {
        HashTable table = new HashTable(10);
        assertEquals(null, table.getValue(null));
    }

    @Test
    public void returnsNullValueForNonExistingKey() {
        HashTable table = new HashTable(10);
        assertEquals(null, table.getValue(1));
    }

    @Test
    public void returnsTheFirstValue() {
        HashTable table = new HashTable(10);
        table.put(0, 10);
        table.put(1, 20);
        assertEquals(10, table.getValue(0));
    }

    @Test
    public void returnsValuesAddedBeforeLast() {
        HashTable table = new HashTable(10);
        table.put(0, 20);
        table.put("teeeeeeeeeeee", 15);
        table.put("teee", 20);
        assertEquals(15, table.getValue("teeeeeeeeeeee"));
    }

    @Test
    public void returnsTheLastValue() {
        HashTable table = new HashTable(10);
        table.put(0, 15);
        table.put("teee", 20);
        assertEquals(20, table.getValue("teee"));
    }
}