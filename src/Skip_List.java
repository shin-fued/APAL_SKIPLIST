import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skip_List {
    private final int max_level;
    private SL_Node head;
    private SL_Node tail;
    private Random rand = new Random();
    private int size=0;
    private int curr_max;

    private int head_count(){
        int count = 0;
        while(rand.nextInt(2)!=0 && count < max_level){
            count++;
        }
        if(curr_max==count){
            curr_max = count+1;
        }
        return count;
    }
    public Skip_List(int max_level) {
        this.max_level = max_level;
        this.head = new SL_Node(Integer.MIN_VALUE, Integer.MIN_VALUE, this.max_level);
        this.tail = new SL_Node(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        this.size = 0;
        this.curr_max = 1;
    }
    public void insert(int key,int value) {
        int level = head_count();
        SL_Node newNode = new SL_Node(key, value, level);
        SL_Node current = head;
        for (int i = curr_max-1; i >= 0; i--) { //vertical component
            while (current.next[i] != null && current.next[i].getKey()<key) {
                current = current.next[i]; //horizontal walk
            }
            if (i <= level){ //populating the column
                newNode.next[i] = current.next[i];
                current.next[i] = newNode;
            }
        }
        tail = head.next[0];
        size++;
    }

    public int get_size(){
        return this.size;
    }

    public int search(int key) {
        SL_Node current = head;
        for (int i = curr_max-1; i >= 0; i--) {
            while (null != current.next[i]) {
                // when at bottom level, i is always 0, needs to find the right node to stop
                if (current.next[i].getKey() > key) {
                    break;
                }
                if (current.next[i].getKey() == key) {
                    return current.next[i].getVal();
                }
                current = current.next[i];
            }
        }

        return -1;
    }

    public boolean delete(int key) {
        SL_Node current = head;
        boolean found = false;
        for (int i = curr_max-1; i >= 0; i--) {
            while (null != current.next[i]) {
                if(current.next[i].getKey() < key){
                    break;
                }
                if (current.next[i].getKey()==key){
                    current.next[i] = current.next[i].next[i];
                    found = true;
                    size--;
                    break;
                }
                current = current.next[i];
            }
        }
        return found;
    }

    //directly taken from https://github.com/YiYeHuang/SkipList/blob/master/src/main/java/impl/SkipList.java
    public void print_level() {
        SL_Node current = head;
        int start = max_level - 1;
        while(null!=current.next[start]){
            start--;
        }

        // collect all node
        current = head;
        List<SL_Node> ref = new ArrayList<>();
        while (null != current) {
            ref.add(current);
            current = current.next[0];
        }

        for (int i = 0; i <= start; i++) {

            current = head;
            current = current.next[i];
            System.out.print( "Layer "+ i + " | level " + max_level + " | head |");

            int levelIndex = 1;
            while (null != current) {


                if (i > 0) {
                    while (ref.get(levelIndex).getVal() != current.getVal()) {
                        levelIndex++;
                        System.out.print( "--------------------------");
                    }
                    levelIndex++;
                }

                System.out.print( "---> " + current);
                current = current.next[i];
            }

            System.out.println();
        }
    }

    public int search_up(int key) {
        SL_Node current = tail;
        int i = 0;
        while(current!=null && current.getKey()<key){
            //go up if you can
            if (current.next.length > i+1 && current.next[i+1]!=null){
                i++;
                current = current.next[i];
            } else if (current.next[i] != null) {
                current = current.next[i];
            }
            else if (i>0){
                i--;
                current = current.next[i];
            }
        }
        if (current!=null && current.getKey()==key) {
            return current.getVal();
        }
        else{
            return -1;
        }
    }

}
