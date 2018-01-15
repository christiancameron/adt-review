package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

/**
 * ArrayMap
 * 
 * Class to implement the Map ADT using an array. * 
 * 
 * (Unlike Stack and Queue, Map is not specified to
 * throw NoSuchElementException when get() or remove()
 * are called with non-existent keys. Instead get()
 * returns null and remove() does nothing. The only
 * reason for this decision is that that's what the tests
 * for Maps that I already had assumed. Similarly put() doesn't
 * throw a FullContainerException.)
 * 
 * CSCI 345, Wheaton College
 * Spring 2016
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class ArrayMap<K, V> implements Map<K, V> {

    /**
     * Class for key-value pairs. This map implementation
     * is essentially an array of these.
     */
    private static class Association<K, V> {
        K key;
        V val;
        Association(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
        	return "key"+"="+val;
        }
    }

    /**
     * An array of key-value associations, the internal
     * representation of this map.
     */
    private Association<K,V>[] internal;

    /**
     * Plain constructor. 
     */
    @SuppressWarnings("unchecked")
    public ArrayMap() {
        // 100 as length of the initial array is an arbitrary choice.
        internal = (Association<K,V>[]) new Association[100];
         
    }

    /**
     * Cause the internal array to double in size.
     */
    private void grow() {
        @SuppressWarnings("unchecked")
        Association<K,V>[] temp = (Association<K,V>[]) new Association[internal.length * 2];
        for (int i = 0; i < internal.length; i++)
            temp[i] = internal[i];
        internal = temp;
    }

    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<K> iterator() {
        return new Iterator<K>() {
        	int i = 0;
			@Override
			public boolean hasNext() {
				return i < internal.length && internal[i] != null;
			}

			@Override
			public K next() {
				K key = internal[i++].key;
				return key;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
        	
        };
    }
    
    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) {
    	if(key==null)
    		return;
    	//The new Association to be inserted into the internal array.
    	Association<K, V> newAsso = new Association<K, V>(key,val);
    	int i;
    	for(i = 0; i < internal.length; i++) {
    		if(internal[i]==null) {
    	        internal[i] = newAsso;
    	        return;
    		}
    		//Key is already in the list, so return
    		else if(internal[i].key.equals(key)) {
    			internal[i].val = val;
    			return;
    		}
    			
    	}
    	//The internal array is full. So grow it.
    	grow();
    	internal[i] = newAsso;
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
    	//Precondition
    	if(key==null)
    		return null;
    	for(int i = 0; i < internal.length; i++) {
    		if(internal[i]==null)
    	        return null;
    		//If the two keys are identical, then return;
    		if(internal[i].key.equals(key)) {
    			return internal[i].val;
    		}
    		
    	}
    	
    	return null;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
        for(int i = 0; i < internal.length; i++) {
        	if(internal[i]==null)
        		return false;
        	if(internal[i].key.equals(key))
        		return true;
        }
    	return false;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
    	//Precondition
    	if(key==null)
    		return;
    	
    	for(int i = 0; i < internal.length; i++) {
    		if(internal[i]==null)
    	        return;
    		
    		/**
    		 * If the two keys are identical, then replace the last association in internal
    		 * with the index of the removed association.
    		 */
    		else if(internal[i].key.equals(key)) {
    			/**
    			 * Inner loop to find the last value in the internal array.
    			 */
    			for(int j = i+1; j < internal.length; j++) {
    				//Found the last value.
    				if(internal[j]==null) {
    					//Check to see if the item to remove is the last item.
    					if(j-1==i) {
    						//Remove the last item.
    						internal[i] = null;
    						return;
    					}
    					//Swap and remove the association at i.
    					Association<K, V> lastValue = internal[j-1];
    					internal[j-1] = null;
    					internal[i] = lastValue;
    					return;
    				}
    			}
    		}
    		
    	}
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (Association<K,V> item : internal) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
