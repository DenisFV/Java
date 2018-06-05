import static java.lang.Math.abs;

public class Calculator {

    Integer toInt(String[] a){ return Integer.valueOf(Integer.parseInt(a[0])); }
    Double toDob (String[] a) { return Double.valueOf(Double.parseDouble(a[0])); }

    <T> T type0(T[] a){
        if (a[1] == "I") return (T)toInt((String[]) a);
        else return (T)toDob((String[]) a);
    }

    <T> String type(String[] a, String[] b){
        if((T)type0(a) instanceof Integer && (T)type0(b) instanceof Integer) return "I";
        if((T)type0(a) instanceof Integer && (T)type0(b) instanceof Double) return "ID";
        if((T)type0(a) instanceof Double && (T)type0(b) instanceof Integer) return "DI";
        return "D";
    }

    void sum(String[] a, String[] b){
        if(type(a,b)=="I") System.out.println(toInt(a)+toInt(b));
        if(type(a,b)=="ID") System.out.println(toInt(a)+toDob(b));
        if(type(a,b)=="DI") System.out.println(toDob(a)+toInt(b));
        if(type(a,b)=="D") System.out.println(toDob(a)+toDob(b));
    }

    void sub(String[] a, String[] b){
        if(type(a,b)=="I") System.out.println(toInt(a)-toInt(b));
        if(type(a,b)=="ID") System.out.println(toInt(a)-toDob(b));
        if(type(a,b)=="DI") System.out.println(toDob(a)-toInt(b));
        if(type(a,b)=="D") System.out.println(toDob(a)-toDob(b));
    }

    void mul(String[] a, String[] b){
        if(type(a,b)=="I") System.out.println(toInt(a)*toInt(b));
        if(type(a,b)=="ID") System.out.println(toInt(a)*toDob(b));
        if(type(a,b)=="DI") System.out.println(toDob(a)*toInt(b));
        if(type(a,b)=="D") System.out.println(toDob(a)*toDob(b));
    }

    void del(String[] a, String[] b){
        if(type(a,b)=="I") {
            if (toInt(a)%toInt(b) == 0) System.out.println(toInt(a)/toInt(b));
            else System.out.println(toInt(a).doubleValue()/toInt(b));
        }
        if(type(a,b)=="ID") System.out.println(toInt(a)/toDob(b));
        if(type(a,b)=="DI") System.out.println(toDob(a)/toInt(b));
        if(type(a,b)=="D") System.out.println(toDob(a)/toDob(b));
    }

    void exp(String[] a, String[] b){
        if(type(a,b)=="I") {
            int sum=1;
            if (toInt(b)>0) {
                for (int i = 0; i < toInt(b); i++) sum *= toInt(a);
                System.out.println(sum);
            }
            else System.out.println(Math.pow((Double)(toInt(a).doubleValue()), (Double)(toInt(b).doubleValue())));
        }
        if(type(a,b)=="ID") System.out.println(Math.pow((Double)(toInt(a).doubleValue()),toDob(b)));
        if(type(a,b)=="DI") System.out.println(Math.pow(toDob(a), (Double)(toInt(b).doubleValue())));
        if(type(a,b)=="D") System.out.println(Math.pow(toDob(a),toDob(b)));
    }

    void fact(String[] a){
        if(type(a,a)=="I") {
            int sum=1;  for (int i=1; i<=toInt(a); i++) sum*=i;
            if (toInt(a)<0) System.out.println("факториал вычисляется только на положительных числах");
            else System.out.println(sum);
        }
        if(type(a,a)=="D") {
            int sum=1, rez, c=(Integer)(toDob(a).intValue());
            if (abs(c-toDob(a))>=0.5) rez = c+1;
            else rez = c;
            for (int i=1; i<=rez; i++) sum*=i;
            if (rez<0) System.out.println("факториал вычисляется только на положительных числах");
            else  System.out.println("Фактроиал считается только для целых чисел, поэтому дробное число будет округляться\n"+sum);
        }
    }

    void ost(String[] a, String[] b){
        if(type(a,b)=="I") System.out.println(toInt(a)%toInt(b));
        if(type(a,b)=="ID") System.out.println(toInt(a)%toDob(b));
        if(type(a,b)=="DI") System.out.println(toDob(a)%toInt(b));
        if(type(a,b)=="D") System.out.println(toDob(a)%toDob(b));
    }

    void mod(String[] a){
        if(type(a,a)=="I") System.out.println(abs (toInt(a)));
        if(type(a,a)=="D") System.out.println(abs (toDob(a)));
    }
}
