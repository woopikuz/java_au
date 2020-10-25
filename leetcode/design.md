# Design

+ [Flatten Nested List Iterator](#flatten-nested-list-iterator)

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