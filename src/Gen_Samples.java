import java.util.*;

public class Gen_Samples {
    List<Map.Entry<Integer, Integer>> list;
    public List<Map.Entry<Integer, Integer>> Generate(int n){
        Random rand = new Random();
        this.list = new ArrayList<Map.Entry<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(rand.nextInt(100_000_000), rand.nextInt(100_00_000));
            this.list.add(pair);
        }
        return this.list;
    }

    public int sample_size(){
        return this.list.size();
    }

    public void print_list(){
        for(Map.Entry<Integer, Integer> pair : this.list){
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
