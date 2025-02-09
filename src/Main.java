import java.util.List;
import java.util.Map;

import static jdk.jfr.internal.JVM.flush;

public class Main {
    public static void main(String[] args) {
        Tests t = new Tests();
        t.initialise_samples("time");
        for (int i = 100; i <=10_000_000; ){
            i*=10;
            t.Test_Insert(i);
            t.Test_Search(i);
            t.Test_Delete(i);
        }

/*        Skip_List s_list = new Skip_List(16);
        Gen_Samples sp = new Gen_Samples();
        List<Map.Entry<Integer, Integer>> test = sp.Generate(16);
        for (Map.Entry<Integer, Integer> item : test ) {
            s_list.insert(item.getKey(), item.getValue());
        }
        s_list.print_level();*/
    }
}
