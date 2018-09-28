import java.util.Arrays;
import java.util.Scanner;

public class Code128{

    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        int kdNr = readIn(sc, "Kundennummer: ");
        int artNr = readIn(sc, "Artikelnummer: ");
        int nettoGw = readIn(sc, "Nettogewicht: ");
        int bruttoGw = readIn(sc, "Bruttogewicht: ");
        sc.close();
        char[] code = new char[24];
        int cur = 0;

        for(int i=23; i>=0; i--){
            switch(i){
                case 23: cur = bruttoGw; break;
                case 17: cur = artNr; break;
                case 13: cur = nettoGw; break;
                case 7:  cur = kdNr; break;
            }
            code[i] = (char)(cur % 10);
            code[i] += '0';
            cur /= 10;
        }

        int error = 0;

        for(int i=0; i<24; ++i){
            if(code[i] < '0' || code[i] > '9'){
                error = 1;
                break;
            }
        }
        if(nettoGw > bruttoGw){
            error = 2;
        }
        System.out.println(error + "\ncode: " + new String(code));

        //String code = generateCode128(kdNr, artNr, nettoGw, bruttoGw);
        //System.out.println("Code128: "+code);
    }

    public static int readIn(Scanner sc, String out){
        System.out.print(out);
        return sc.nextInt();
    }



    ///////////////////////////////////////////
    //Alternative Solution
    ///////////////////////////////////////////
    public static final int CODE_LENGTH = 24;
    public static final int POS_KDNR = 7;
    public static final int POS_NETTOGW = 13;
    public static final int POS_ARTNR = 17;
    public static final int POS_BRUTTOGW = 23;

    public static final int NO_ERROR = 0;
    public static final int ERROR_CHAR = 1;
    public static final int ERROR_WEIGHT = 2;

    public static String generateCode128(int kdNr, int artNr, int nettoGw, int bruttoGw){
        int cur = 0;
        char[] cCode = new char[CODE_LENGTH];
        for(int i=cCode.length-1; i>=0; i--){
            switch(i){
                case POS_BRUTTOGW: cur = bruttoGw;  break;
                case POS_ARTNR:    cur = artNr;     break;
                case POS_NETTOGW:  cur = nettoGw;   break;
                case POS_KDNR:     cur = kdNr;      break;
            }

            cCode[i] = (char)(cur % 10);
            cCode[i] += '0';
            cur /= 10;
        }

        String strCode = new String(cCode);
        int errorCode = validateCode128(strCode);
        if(nettoGw > bruttoGw){
            errorCode = ERROR_WEIGHT;
        }

        System.out.println("error: "+errorCode);
        return strCode;
    }


    public static int validateCode128(String code){
        if (code.matches("[0-9]+")) {
            return ERROR_CHAR;
        }
        else {
            return NO_ERROR;
        }
    }

}