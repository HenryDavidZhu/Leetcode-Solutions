class RandomizedSet {
    List<Integer> elementList;
    Map<Integer, Integer> elementToIndex;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.elementList = new ArrayList<Integer>();
        this.elementToIndex = new HashMap<Integer, Integer>();
        this.random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!elementToIndex.containsKey(val)) {
            elementList.add(val);
            elementToIndex.put(val, elementList.size() - 1);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (elementToIndex.containsKey(val)) {
            if (elementList.size() == 1) {
                elementToIndex.remove(val);
                elementList.remove(elementList.size() - 1);
            }
            else {
                int removeIndex = elementToIndex.get(val);
                int lastElement = elementList.get(elementList.size() - 1);
                
                elementList.set(removeIndex, lastElement);
                elementToIndex.put(lastElement, removeIndex);

                elementList.remove(elementList.size() - 1);
                elementToIndex.remove(val);
            }
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        // Generate random index
        int randomIndex = random.nextInt(elementList.size());
        return elementList.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */