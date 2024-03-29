package impl;

import java.util.Iterator;

import adt.Map;
import adt.Set;

/**
 * MapSet
 * 
 * An implementation of Set that uses a Map as its
 * underlying implementation
 * 
 * CSCI 345, Wheaton College
 * Spring 2016
 * @param <E> The base-type of the set
 */
public class MapSet<E> implements Set<E> {

    /**
     * The internal representation. Note this can be any 
     * map implementation. 
     */
    private Map<E, String> internal;
    
    public MapSet() {
        this.internal = new ArrayMap<E, String>();
    }
    
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
    	return internal.iterator();
    }

    /**
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
    	if(item==null)
    		return;
    	
    	//Item was not found in the set, so add the item
    	internal.put(item, null);
    	
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(E item) {
    	if(item==null)
    		return false;

        return internal.containsKey(item);

    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
	public void remove(E item) {
		if(item==null)
    		return;
		
		internal.remove(item);	
	}

   /**
    * The number of items in the set, runs N times
    * @return The number of items.
    */
    public int size() {
    	Iterator<E> it = internal.iterator();
    	int size = 0;
    	while(it.hasNext()) {
    		size++;
    		it.next();
    	}
    	return size;
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
         return size() <= 0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	for (E item : this) {
    		toReturn += item + ", ";
    	}
    	return toReturn +"]";
    }

}
