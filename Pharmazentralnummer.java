import java.util.Formatter;

public class Pharmazentralnummer{

    public static void main(String... args){
        int[] values = {
            7728561,
            10201099,
            40554,
            40548,
            3806873,
            1894063,
            10203595,
            10203603,
            1743631,
            3227112,
            3464237,
            4356248
        };

        for(int v : values){
            int last = v;//getLatestPZN();
            int pzn = last / 10;
            int check = 10;
            do{
                pzn = pzn + 1;
                int val = pzn;
                int sum = 0;
                for(int i = 7; i > 0; i = i-1){
                    int tmp = val % 10;
                    val = val / 10;
                    sum = tmp * i + sum;
                }
                check = sum % 11;
            }while(check == 10);
            pzn = pzn * 10;
            pzn = pzn + check;
            String out = String.format("last: %08d next: %08d",last,pzn);
            System.out.println(out);
        }
    }


}