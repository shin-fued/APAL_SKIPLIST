public class SL_Node {
    public SL_Node[] next;
    private int key;
    private int val;
    private int level;

    public SL_Node(int key, int val, int level) {
        this.key = key;
        this.val = val;
        this.level = level;
        this.next = new SL_Node[level+1];
    }
    public int getKey() {
        return this.key;
    }
    public void update(int key, int value) {
        this.val =value;
    }
    public int getVal() {
        return this.val;
    }
    public int getLevel() {
        return this.level;
    }
    @Override
    public String toString() {
        return "[ level " + this.level + " | key "+this.key + " ]";
    }
}
