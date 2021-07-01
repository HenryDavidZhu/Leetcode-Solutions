class Element {
    Integer index = -1;
    Integer value = -1;
    
    Element(Integer index, Integer value) {
        this.index = index;
        this.value = value;
    }
}

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Element> monoDecStack = new Stack<Element>();
        boolean[] addedToStack = new boolean[nums.length];
        boolean circled = false;
        int index = 0;
        
        int[] nxtGreaterElements = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nxtGreaterElements[i] = -1;
        }
        
        while (index <= nums.length - 1) {
            if (!addedToStack[index]) {
                if (monoDecStack.isEmpty() || nums[index] <= monoDecStack.peek().value) {
                    monoDecStack.add(new Element(index, nums[index]));
                    addedToStack[index] = true;
                }
            }
            
            boolean isNxtGreaterElement = false;
            while (!monoDecStack.isEmpty() && nums[index] > monoDecStack.peek().value) {
                isNxtGreaterElement = true;
                Element topElement = monoDecStack.pop();
                        
                if (nxtGreaterElements[topElement.index] == -1) {
                    nxtGreaterElements[topElement.index] = nums[index];
                }
            }
            
            if (isNxtGreaterElement) {
                monoDecStack.add(new Element(index, nums[index]));
                addedToStack[index] = true;
            }
            
            index++;
            if (index == nums.length && !circled) {
                index = 0;
                circled = true;
            }
        }
        
        return nxtGreaterElements;
    }
}