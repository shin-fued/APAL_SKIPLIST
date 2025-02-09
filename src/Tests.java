import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.lang.instrument.Instrumentation;


public class Tests {
    private static Gen_Samples gs;
    private List<Map.Entry<Integer, Integer>> samples;
    private Map<Integer, Integer> ctrl;
    Skip_List sk;
    public void initialise_samples(int n){
        //flush();
        gs = new Gen_Samples();
        samples = gs.Generate(n);
        ctrl = new TreeMap<>();
        sk = new Skip_List(16);
    }

    public void Test_insert(){

        long startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            sk.insert(item.getKey(), item.getValue());
        }
        long endTime = System.nanoTime();
        System.out.printf("time of insert skip list of size %d is %d ns\n", gs.sample_size(),endTime - startTime);
        startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            ctrl.put(item.getKey(), item.getValue());
        }
        endTime = System.nanoTime();
        System.out.printf("time of ctrl %d ns\n", endTime - startTime);
    }
    public void Test_delete(){
        long startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            sk.delete(item.getKey());
        }
        long endTime = System.nanoTime();
        System.out.printf("time of delete skip list %d ns\n", endTime - startTime);
        startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            ctrl.put(item.getKey(), item.getValue());
        }
        endTime = System.nanoTime();
        System.out.printf("time of delete ctrl %d ns\n", endTime - startTime);
    }
    public void Test_search(){
        long startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            sk.search(item.getKey());
        }
        long endTime = System.nanoTime();
        System.out.printf("time search ctrl %d ns\n", endTime - startTime);
        startTime = System.nanoTime();
        for(Map.Entry<Integer, Integer> item : samples){
            ctrl.get(item.getKey());
        }
        endTime = System.nanoTime();
        System.out.printf("time search ctrl %d ns\n", endTime - startTime);
    }
}
