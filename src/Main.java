import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Tests t = new Tests();

        t.initialise_samples("time");
        //inserting/seraching/deleting the same amount as the size go up
//        for (i = 1000; i<=10_000_000; ){
//            t.Test_Insert(i-1000, i);
//            t.Test_Search(i-1000, i);
//            i += 1000;
//        }
//        int temp = 1000;
//        for (int j = i-1000; j>=0; ){
//            if (temp>10_000_000){
//                break; //101 ass code
//            }
//            t.Test_Delete(temp-1000, temp);
//            temp += 1000;
//            j-=1000;
//        }

        //inserting at scale ie inserting 100 vs 1000 vs .. 10_000_000
        for (int j=0; j<50; j++){
            System.gc();
            for (int i = 10; i <= 5_000_000; ){
                t.Test_Insert( i);
                t.Test_Search(i);
                t.Test_Delete(i);
                i*=2;
            }
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
