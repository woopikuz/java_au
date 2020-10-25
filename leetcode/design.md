# Design

+ [Flatten Nested List Iterator](#flatten-nested-list-iterator)
+ [LRU Cache](#lru-cache)
+ [Min Stack](#min-stack)

## Flatten Nested List Iterator

https://leetcode.com/problems/flatten-nested-list-iterator/

```java
public class NestedIterator implements Iterator<Integer> {

    private Queue<Integer> queue = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        addToQueue(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void addToQueue(List<NestedInteger> nestedList){
        if(nestedList.isEmpty()){
            return;
        }

        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                queue.offer(i.getInteger());
            }else{
                addToQueue(i.getList());
            }
        }
    }
}
```

## LRU Cache

https://leetcode.com/problems/lru-cache/

```java

import java.util.Hashtable;

public class LRUCache {

class DLinkedNode {
  int key;
  int value;
  DLinkedNode pre;
  DLinkedNode post;
}

private void addNode(DLinkedNode node) {
  node.pre = head;
  node.post = head.post;
  head.post.pre = node;
  head.post = node;
}

private void removeNode(DLinkedNode node){
  DLinkedNode pre = node.pre;
  DLinkedNode post = node.post;
  pre.post = post;
  post.pre = pre;
}

private void moveToHead(DLinkedNode node){
  this.removeNode(node);
  this.addNode(node);
}

private DLinkedNode popTail(){
  DLinkedNode res = tail.pre;
  this.removeNode(res);
  return res;
}

private Hashtable<Integer, DLinkedNode>
  cache = new Hashtable<Integer, DLinkedNode>();
private int count;
private int capacity;
private DLinkedNode head, tail;

public LRUCache(int capacity) {
  this.count = 0;
  this.capacity = capacity;
  head = new DLinkedNode();
  head.pre = null;
  tail = new DLinkedNode();
  tail.post = null;
  head.post = tail;
  tail.pre = head;
}

public int get(int key) {

  DLinkedNode node = cache.get(key);
  if(node == null){
    return -1;
  }
  this.moveToHead(node);
  return node.value;
}

public void put(int key, int value) {
  DLinkedNode node = cache.get(key);
  if(node == null){
    DLinkedNode newNode = new DLinkedNode();
    newNode.key = key;
    newNode.value = value;
    this.cache.put(key, newNode);
    this.addNode(newNode);
    ++count;
    if(count > capacity){
      DLinkedNode tail = this.popTail();
      this.cache.remove(tail.key);
      --count;
    }
  }
  else{
    node.value = value;
    this.moveToHead(node);
  }
}
}
```

## Min Stack

https://leetcode.com/problems/min-stack/

```java
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        if(x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
```
