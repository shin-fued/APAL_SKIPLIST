import static jdk.jfr.internal.JVM.flush;

public class Main {
    public static void main(String[] args) {
        Tests t = new Tests();
        //t.initialise_samples();
        //t.Test_insert();
        //t.Test_delete();
        //t.Test_search();
        Skip_List s_list = new Skip_List(16);
        Gen_Samples sp = new Gen_Samples();
        sp.Generate(16);
        s_list.print_level();
    }
}
