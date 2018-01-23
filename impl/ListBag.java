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
 * Spring 2016
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
         internal.add(item);
    }
    
    /**
     * Helper method to return the index of the last occurence of a certain item.
     * @param item, the item to look at
     * @return an index in range if item occurs, else -1
     */
    private int lastOccurence(E item) {
    	int i = -1;
    	while(iterator().hasNext()) {
        	if(iterator().next().equals(item)) {
        		i++;
        	}
        		
        }
    	return i;
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of occurences of this item in the bag
     */
    public int count(E item) {
    	int count = 0;
    	//Count the occurences of a given item
        while(iterator().hasNext()) {
        	if(iterator().next().equals(item))
        		count++;
        }
        return count;
    }

    /**
     * Remove (all occurrences of) an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
		if (item == null)
			return;

		// Find the first occurrence of the item, to find index.
		int i = 0;
		while (iterator().hasNext()) {
			if (iterator().next().equals(item)) {
				break;
			}
			i++;
		}
		
		//Now remove all occurrences of the item
		while(true) {
			if(internal.remove(i)==item)
				i++;
			else {
				//Removed one element too many, insert it back.
				internal.insert(i, item);
				break;
			}
		}
     
         
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
		int count = 0;
		//Count the number of unique items.
		E prevItem = null;
		while(iterator().hasNext()) {
			E currItem = iterator().next();
			if(currItem==null)
				break;
			
			else if(!currItem.equals(prevItem)) {
				count++;
			}
			
			prevItem = currItem;
		}
		return count;
    }

    /**
     * Is the bag empty?
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
         throw new UnsupportedOperationException();
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
