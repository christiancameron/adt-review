package impl;

import java.util.Iterator;

import adt.Bag;
import adt.List;

/**
 * ListBag
 * 
 * An implementation of Bag using a List as the underlying
 * implementation.
 * 
 * Recall that our Bag interface differs from what Sedgewick 
 * calls a "bag" (but he's wrong). 
 * 
 * CSCI 345, Wheaton College
 * Spring 2018
 * @param <E> The base-type of the bag
 */
public class ListBag<E> implements Bag<E> {

    /**
     * The internal representation (can be any implementation of
     * List)
     */
    private List<E> internal;

    public ListBag() {
            internal = new MapList<E>();
    }
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
   public Iterator<E> iterator() {
        return internal.iterator();
    }

    /**
     * Add an item to the bag, increasing its count if it's
     * already there.
     * @param item The item to add
     */
    public void add(E item) {
    	if(item==null)
    		return;
    	
    	int firstOcc = firstOccurrence(item);
    	
    	//This is the first time this item will be added to the list.
    	if(firstOcc==-1)
    		internal.add(item);
    	else
    		internal.insert(firstOcc+1, item);
    }
    
    /**
     * Helper method to return the index of the first occurrence of a certain item.
     * @param item, the item to look at
     * @return an index in range if item occurs, else -1
     */
    private int firstOccurrence(E item) {
    	if(item==null)
    		return -1;
    	
    	int i = 0;
    	Iterator<E> it = iterator();
    	while(it.hasNext()) {
    		E tmp = it.next();
        	if(tmp.equals(item))
        		return i;
        	i++;
    	}
    	return -1;
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of occurrences of this item in the bag
     */
    public int count(E item) {
    	if(item==null)
    		return 0;
    	
    	int count = 0;
    	//Count the occurrences of a given item
    	Iterator<E> it = iterator();
        while(it.hasNext()) {
        	if(it.next().equals(item))
        		count++;
        	//The group of items are already counted, so exit the loop.
        	else if(count!=0)
        		break;
        }
        return count;
    }

    /**
     * Remove (all occurrences of) an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
		if (item == null || isEmpty())
			return;
		
		//Remove all occurrences of the item
		for(int i = 0; i < internal.size(); i++) {
			if(internal.get(i).equals(item)) {
				//Remove shifts elements down
				internal.remove(i);
				//Repeat for the same i
				i--;
			}
		}
    } 

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
    	return internal.size();
    }

    /**
     * Is the bag empty?
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
    	return internal.size()==0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (E item : this) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
