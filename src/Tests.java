import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class Tests {
    private static final Gen_Samples gs = new Gen_Samples();;
    private List<Map.Entry<Integer, Integer>> samples;
    private Map<Integer, Integer> ctrl;
    Skip_List sk;
    CSV_Logger logger;
    public void initialise_samples(int n, String measure){
        //flush();
        this.samples = gs.Generate(n);
        this.ctrl = new TreeMap<>();
        this.sk = new Skip_List(16);
        logger = new CSV_Logger(measure, measure);
    }

    public void initialise_samples(String measure){
        //flush();
        this.ctrl = new TreeMap<>();
        this.sk = new Skip_List(16);
        logger = new CSV_Logger(measure, measure);
    }

    public void Test_op(int n, String op){
        this.samples = gs.Generate(n);
        long sk_start;
        long sk_end;
        long map_start;
        long map_end;
        if (op.equals("insert"))
        {
            sk_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.sk.insert(item.getKey(), item.getValue());
            }
            sk_end = System.nanoTime();

            map_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.ctrl.put(item.getKey(), item.getValue());
            }
            map_end = System.nanoTime();
        }
        else if (op.equals("search"))
        {
            sk_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.sk.search(item.getKey());
            }
            sk_end = System.nanoTime();

            map_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.ctrl.get(item.getKey());
            }
            map_end = System.nanoTime();
        }
        else if(op.equals("delete"))
        {
            sk_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.sk.delete(item.getKey());
            }
            sk_end = System.nanoTime();

            map_start = System.nanoTime();
            for(Map.Entry<Integer, Integer> item : this.samples){
                this.ctrl.remove(item.getKey());
            }
            map_end = System.nanoTime();
        }
        else{
            sk_start = 0;
            sk_end = 0;
            map_start = 0;
            map_end = 0;
        }

        logger.log(String.valueOf(n),op,String.valueOf(sk_end - sk_start), String.valueOf(map_end - map_start));
    }

    public void Test_op(int start, int stop, String op){
        long sk_start;
        long sk_end;
        long map_start;
        long map_end;
        if (op.equals("insert"))
            {
                sk_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.sk.insert(item.getKey(), item.getValue());
                }
                sk_end = System.nanoTime();

                map_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.ctrl.put(item.getKey(), item.getValue());
                }
                map_end = System.nanoTime();
            }
        else if (op.equals("search"))
            {
                sk_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.sk.search(item.getKey());
                }
                sk_end = System.nanoTime();

                map_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.ctrl.get(item.getKey());
                }
                map_end = System.nanoTime();
            }
        else if(op.equals("delete"))
            {
                sk_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.sk.delete(item.getKey());
                }
                sk_end = System.nanoTime();

                map_start = System.nanoTime();
                for(Map.Entry<Integer, Integer> item : this.samples.subList(start, stop)){
                    this.ctrl.remove(item.getKey());
                }
                map_end = System.nanoTime();
            }
        else{
            sk_start = 0;
            sk_end = 0;
            map_start = 0;
            map_end = 0;
        }

        logger.log(String.valueOf(stop),op,String.valueOf(sk_end - sk_start), String.valueOf(map_end - map_start));
    }

    //profiling purpose

    public void Test_Insert(int start, int stop){
        Test_op(start, stop, "insert");
    }
    public void Test_Search(int start, int stop){
        Test_op(start, stop,"search");
    }

    public void Test_Delete(int start, int stop){
        Test_op(start, stop, "delete");
    }
    public void Test_Delete(int n){
        Test_op(n, "delete");
    }

    public void Test_Insert(int n){
        Test_op(n, "insert");
    }
    public void Test_Search(int n){
        Test_op(n,"search");
    }

}
