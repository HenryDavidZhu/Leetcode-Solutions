class DoubleLinkedList {
    // In a doubly linked list, we keep track of the head and the tail.
    public Node head, tail = null;
    
    // Add a node to the beginning of the DoubleLinkedList.
    public void addNodeToHead(Node node) {
        // If the head is null, that means the list is empty.
        if (head == null) {
            node.next = null;
            node.prev = null;
            
            head = node;
            tail = node;
        }
        // List is not empty.
        else {
            Node originalHead = head;
            
            node.next = originalHead;
            originalHead.prev = node;
            
            head = node;
        }
    }
    
    // Remove any node from the DoubleLinkedList.
    public void removeAnyNode(Node node) {
        // Edge Case: The node is the head.
        if (head == node) {
            head = node.next;
            return;
        }
        
        Node previous = node.prev;
        Node next = node.next;
        
        if (previous != null) {
            previous.next = next;
            
            if (previous.next == null) {
                tail = previous;
            }
        }
        if (next != null) {
            next.prev = previous;
        }
    }
}

// In a doubly linked list, each node's payload contains both the previous
// and the subsequent node (prev and next).
class Node {
    int key;
    int value;
    Node prev, next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    HashMap<Integer, Node> cache;
    int capacity = 0;
    DoubleLinkedList callHistory;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<Integer, Node>();
        this.callHistory = new DoubleLinkedList();
    }
    
    public int get(int key) {
        // Update the call history (if the key exists in our map).
        if (cache.containsKey(key)) {
            // Retrieve the corresponding node from our cache.
            Node node = cache.get(key);
            
            // Update the call history with the following 2 steps.
            // 1. Remove the node from DLL.
            this.callHistory.removeAnyNode(node);
            
            // 2. Add the node to the beginning of the DLL.
            this.callHistory.addNodeToHead(node);
            
            return this.cache.get(key).value;
            
        }
        
        return -1;
    }
    
    public void put(int key, int value) {
        // See if we already have a node corresponding to the key in the cache.
        if (!this.cache.containsKey(key)) {
            // If not, then we need to create a new node.
            Node node = new Node(key, value);
            
            // Update the cache with our newly created node.
            this.cache.put(key, node);
            
            // Update the call history with the following 2 steps.
            // 1. Remove the node from DLL.
            this.callHistory.removeAnyNode(node);
            
            // 2. Add the node to the beginning of the DLL.
            this.callHistory.addNodeToHead(node);
            
            // Check that we exceeded the capacity.
            if (this.cache.size() > capacity) {
                // Determine what element was least recently used.
                // The element that was LRU is the tail of the Doubly Linked List.
                Node lruNode = this.callHistory.tail;
                
                // Remove from the cache.
                this.cache.remove(lruNode.key);
                
                // Remove from the call history DLL.
                this.callHistory.removeAnyNode(lruNode);
            }
        }
        // If the node already exists, we just need to update it.
        else {
            // Retrieve the node from the cache.
            Node existingNode = this.cache.get(key);
            
            // Update the node.
            existingNode.value = value;
            
            // Update the call history with the following 2 steps.
            // 1. Remove the node from DLL.
            this.callHistory.removeAnyNode(existingNode);
            
            // 2. Add the node to the beginning of the DLL.
            this.callHistory.addNodeToHead(existingNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */