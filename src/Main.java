import com.sun.javadoc.SeeTag;
import com.sun.jndi.cosnaming.CNCtx;
import com.sun.tools.internal.jxc.ap.Const;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by tyz on 2016/11/26.
 */
public class Main {
    //异常定义
    static ConstToken ERROR = new ConstToken("ERROR");
    //函数部分
    static ConstToken SIN = new ConstToken("SIN");
    static ConstToken COS = new ConstToken("COS");
    static ConstToken TAN = new ConstToken("TAN");
    static ConstToken LN = new ConstToken("LN");
    static ConstToken EXP = new ConstToken("EXP");
    static ConstToken SQRT = new ConstToken("SQRT");
    //关键字保留部分
    static ConstToken DOT = new ConstToken(".");
    static ConstToken ORIGIN = new ConstToken("ORIGIN");
    static ConstToken SCALE = new ConstToken("SCALE");
    static ConstToken ROT = new ConstToken("ROT");
    static ConstToken IS = new ConstToken("IS");
    static ConstToken FOR = new ConstToken("FOR");
    static ConstToken FROM = new ConstToken("FROM");
    static ConstToken TO = new ConstToken("TO");
    static ConstToken STEP = new ConstToken("STEP");
    static ConstToken DRAW = new ConstToken("DRAW");
    static ConstToken SEMICO = new ConstToken(";");
    static ConstToken L_BRACKET = new ConstToken("(");
    static ConstToken R_BRACKET = new ConstToken(")");
    static ConstToken COMMA = new ConstToken(",");
    static ConstToken PLUS = new ConstToken("+");
    static ConstToken MINUS = new ConstToken("-");
    static ConstToken MUL = new ConstToken("*");
    static ConstToken DIV = new ConstToken("/");
    static ConstToken POWER = new ConstToken("**");
    //常量
    static ConstToken PI = new ConstToken("PI",3.1415926);
    static ConstToken E = new ConstToken("E",2.71828);
    static ConstToken T = new ConstToken("T",0);

   //申明一个栈
    static Stack<StringBuffer> words = new Stack();
    static Stack<StringBuffer> words2 = new Stack();
    //判断一个字符串是否是数字
    public static boolean isInteger(String str) {
        Boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
        return strResult;
    }
    //第一次处理
    public static void WordAnasis(String args){
        char temp = '#';
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<args.length();i++){
            char c = args.charAt(i);
            if (c!=' '&c != (',')&c != (';')&c != ('/')&c != ('+')&c != ('-')&c != ('(')&c != (')')&c!='*'){
                if (temp==','|temp==';'|temp=='/'|temp==';'|temp=='+'|temp=='-'|temp=='('|temp==')'|temp=='*') {
                    words.push(stringBuffer);
                    stringBuffer = new StringBuffer();
                }
                stringBuffer.append(c);
            }else if (c == (',')|c == (';')|c == ('/')|c == ('+')|c == ('-')|c == ('(')|c == (')')|c==('*')) {
                if (stringBuffer.length()!=0)
                    words.push(stringBuffer);
                stringBuffer = new StringBuffer();
                stringBuffer.append(c);
            }else{
                if (temp!=' ')
                    words.push(stringBuffer);
                    stringBuffer = new StringBuffer();
            }
            temp = c;
        }
        if (stringBuffer.length()!=0){
            words.push(stringBuffer);
        }
    }
    //第二次处理
    public static void StackPop(){
        StringBuffer tmp ;
        StringBuffer last = new StringBuffer();
        StringBuffer digitalNumber = new StringBuffer();
        while (!words.empty()){

            tmp = words.pop();
            if (tmp.toString().toUpperCase().equals("PI"))
                tmp = new StringBuffer("3.1415926");
            else if (tmp.toString().toUpperCase().equals("E"))
                tmp = new StringBuffer("2.71828");

            words2.push(tmp);
            if (!(isInteger(String.valueOf(tmp)))&isInteger(String.valueOf(last))){
                words2.pop();
                words2.push(digitalNumber);
                if (tmp.toString().toUpperCase().equals("PI"))
                    tmp = new StringBuffer("3.1415926");
                else if (tmp.toString().toUpperCase().equals("E"))
                    tmp = new StringBuffer("2.71828");

                words2.push(tmp);
                digitalNumber = new StringBuffer();
            }
            boolean t = ((tmp.toString().equals("*"))&(last.toString().equals("*")));
            if (t){
                words2.pop();
                words2.pop();
                words2.push(new StringBuffer("**"));
            }
            if (isInteger(String.valueOf(tmp))) {
                digitalNumber.append(tmp);
                words2.pop();
            }
            if (tmp.toString().equals(".")){
                digitalNumber.append(tmp);
                words2.pop();
            }

            last = tmp;
        }
    }

    public static ConstToken TokenAnasisJudge(String string){
        ConstToken number ;
        if (string.equals("SIN"))
            return SIN;
        else if (string.equals("COS"))
            return COS;
        else if (string.equals("STEP"))
            return STEP;
        else if (string.equals("DRAW"))
            return DRAW;
        else if (string.equals("TO"))
            return TO;
        else if (string.equals("TAN"))
            return TAN;
        else if (string.equals("LN"))
            return LN;
        else if (string.equals("EXP"))
            return EXP;
        else if (string.equals("SQRT"))
            return SQRT;
        else if (string.equals("ORIGIN"))
            return SCALE;
        else if (string.equals("ROT"))
            return ROT;
        else if (string.equals("IS"))
            return IS;
        else if (string.equals("FOR"))
            return FOR;
        else if (string.equals("FROM"))
            return FROM;
        else if (string.equals("TO"))
            return STEP;
        else if (string.equals("COS"))
            return DRAW;
        else if (string.equals(";"))
            return SEMICO;
        else if (string.equals("("))
            return L_BRACKET;
        else if (string.equals(")"))
            return R_BRACKET;
        else if (string.equals(","))
            return COMMA;
        else if (string.equals("+"))
            return PLUS;
        else if (string.equals("-"))
            return MINUS;
        else if (string.equals("*"))
            return MUL;
        else if (string.equals("/"))
            return DIV;
        else if (string.equals("**"))
            return POWER;
        else if (string.equals("PI"))
            return PI;
        else if (string.equals("E"))
            return E;
        else if (string.equals("T"))
            return T;
        else if (string.equals("ORIGIN"))
            return ORIGIN;
        else if (isInteger(string)){
            number = new ConstToken(string,Double.parseDouble(string));
            return number;
        }

        return ERROR;
    }

    public static void TokenAnasis(){
        ConstToken tmp = new ConstToken("tmp");
        while (!words2.empty()){
            tmp = TokenAnasisJudge(String.valueOf(words2.pop()).toUpperCase());
            System.out.println(tmp.getConstTokenName());
        }
    }

    public static void OriginSentenceHold(){
        StringBuffer X = new StringBuffer();
        StringBuffer Y = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        StringBuffer last = new StringBuffer();
        while (!words2.empty()){
            tmp = words2.pop();
            if (last.toString().toUpperCase().equals("(")){
                X = tmp;
                while(!words2.peek().toString().toUpperCase().equals(",")){
                    tmp = words2.pop();
                    X.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals(",")){
                Y = tmp;
                while (!words2.empty()){
                    tmp = words2.pop();
                    Y.append(tmp);
                }
                Y.deleteCharAt(Y.length()-1);
                last = tmp;
                continue;
            }
            last = tmp;
        }
        System.out.println(X);
        System.out.println(Y);
    }

    public static void RotSentenceHold(){
        StringBuffer movement = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        StringBuffer last = new StringBuffer();
        while (!words2.empty()){
            tmp = words2.pop();
            if (last.toString().toUpperCase().equals("IS")){
                movement = tmp;
                while (!words2.empty()){
                    tmp = words2.pop();
                    movement.append(tmp);
                }
                last = tmp;
                continue;
            }
            last = tmp;
        }
        System.out.println(movement);
    }
    public static void ScaleSentenceHold(){
        StringBuffer X = new StringBuffer();
        StringBuffer Y = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        StringBuffer last = new StringBuffer();
        while (!words2.empty()){
            tmp = words2.pop();
            if (last.toString().toUpperCase().equals("(")){
                X = tmp;
                while(!words2.peek().toString().toUpperCase().equals(",")){
                    tmp = words2.pop();
                    X.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals(",")){
                Y = tmp;
                while (!words2.empty()){
                    tmp = words2.pop();
                    Y.append(tmp);
                }
                Y.deleteCharAt(Y.length()-1);
                last = tmp;
                continue;
            }
            last = tmp;
        }
        System.out.println(X);
        System.out.println(Y);
    }

    public static void ForSentenceHold() throws IOException {
        StringBuffer tmp,last,start,end,step,X,Y;
        start = new StringBuffer();
        end = new StringBuffer();
        step = new StringBuffer();
        X = new StringBuffer();
        Y = new StringBuffer();
        last = new StringBuffer();
        while(!words2.empty()){
            tmp = words2.pop();
            if (last.toString().toUpperCase().equals("FROM")){
                start = tmp;
                while (!words2.peek().toString().toUpperCase().equals("TO")){
                    tmp = words2.pop();
                    start.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals("TO")){
                end = tmp;
                while (!words2.peek().toString().toUpperCase().equals("STEP")){
                    tmp = words2.pop();
                    end.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals("STEP")){
                step = tmp;
                while (!words2.peek().toString().toUpperCase().equals("DRAW")){
                    tmp = words2.pop();
                    step.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals("DRAW")&tmp.toString().toUpperCase().equals("(")){
                tmp = words2.pop();
                X = tmp;
                while (!words2.peek().toString().toUpperCase().equals(",")){
                    tmp=words2.pop();
                    X.append(tmp);
                }
                last = tmp;
                continue;
            }else if (last.toString().toUpperCase().equals(",")){
                Y = tmp;
                while (!words2.empty()){
                    tmp = words2.pop();
                    Y.append(tmp);
                }
                Y.deleteCharAt(Y.length()-1);
                last = tmp;
                continue;
            }
            last = tmp;
        }

        double dstart = FunctionHold(ExpressionHasFunc(start));
        double dend = FunctionHold(ExpressionHasFunc(end));
        double dstep = FunctionHold(ExpressionHasFunc(step));

        File writename = new File("/Users/tyz/IdeaProjects/CompilePra/src/graphdata");
        writename.createNewFile();
        BufferedWriter bf = new BufferedWriter(new FileWriter(writename));




        while (dstart<dend){
            String s = X.toString().toUpperCase();
            if (s.contains("X"))
                s=s.replace("X",String.valueOf(dstart));
            double dx = FunctionHold(ExpressionHasFunc(new StringBuffer(s)));
            System.out.println(dx);
            String ss = Y.toString().toUpperCase();
            if (ss.contains("X"))
                ss = ss.replace("X",String.valueOf(dstart));
            double dy = FunctionHold(ExpressionHasFunc(new StringBuffer(ss)));
            System.out.println(dy);

            bf.write(dx+" "+dy+"\n");
            dstart+=dstep;
        }
        bf.flush();
        bf.close();
    }

    public static StringBuffer ExpressionHasFunc(StringBuffer stringBuffer){
        StringBuffer exp = new StringBuffer();

        int expnumber = 0;
        exp = new StringBuffer();
        for (int i=0;i<stringBuffer.length();i++){
            int start=0;
            if (stringBuffer.charAt(i)=='C'|stringBuffer.charAt(i)=='S'|stringBuffer.charAt(i)=='T'|stringBuffer.charAt(i)=='L'|stringBuffer.charAt(i)=='E'){
                exp.append(stringBuffer.charAt(i));
                start=i;
                i++;
                int R=0;
                int L= 0;
                while (i<stringBuffer.length()){
                    if (stringBuffer.charAt(i)=='(')
                        R++;
                    else if (stringBuffer.charAt(i)==')')
                        L++;
                    if ((R==L)&R!=0&L!=0){
                        expnumber++;
                        exp.append(stringBuffer.charAt(i));
                        stringBuffer.replace(start,i+1,String.valueOf(FunctionHold(exp)));
                        break;
                    }
                    exp.append(stringBuffer.charAt(i));
                    i++;
                }
            }

        }
        return stringBuffer;
    }
    public static void DrawGraph(){

    }
    public static double FunctionHold(StringBuffer stringBuffer){
        if (stringBuffer.length()>2){
        if (stringBuffer.toString().toUpperCase().charAt(0) == 'C'){
            String string = new String();
            if (stringBuffer.length()>6)
                string= stringBuffer.substring(3,stringBuffer.length());
            else
                string = stringBuffer.substring(3);
            return COS.function(Calculator.conversion(string));
        }else if (stringBuffer.toString().toUpperCase().charAt(0) == 'S'&stringBuffer.toString().toUpperCase().charAt(1)=='I'){
            String string = new String();
            if (stringBuffer.length()>6)
                string= stringBuffer.substring(3,stringBuffer.length());
            else
                string = stringBuffer.substring(3);
            return SIN.function(Calculator.conversion(string));
        }else if (stringBuffer.toString().toUpperCase().charAt(0) == 'T'){
            String string = new String();
            if (stringBuffer.length()>6)
                string= stringBuffer.substring(3,stringBuffer.length());
            else
                string = stringBuffer.substring(3);
            return TAN.function(Calculator.conversion(string));
        }else if (stringBuffer.toString().toUpperCase().charAt(0) == 'L'){
            String string = new String();
            if (stringBuffer.length()>5)
                string= stringBuffer.substring(2,stringBuffer.length());
            else
                string = stringBuffer.substring(2);
            return LN.function(Calculator.conversion(string));
        }else if (stringBuffer.toString().toUpperCase().charAt(0) == 'E'){
            String string = new String();
            if (stringBuffer.length()>6)
                string= stringBuffer.substring(3,stringBuffer.length());
            else
                string = stringBuffer.substring(3);
            return EXP.function(Calculator.conversion(string));
        }else if (stringBuffer.toString().toUpperCase().charAt(0) == 'S'&stringBuffer.toString().toUpperCase().charAt(1)=='Q'){
            String string = new String();
            if (stringBuffer.length()>6)
                string= stringBuffer.substring(3,stringBuffer.length());
            else
                string = stringBuffer.substring(3);
            return SQRT.function(Calculator.conversion(string));
        }

        }
        return Calculator.conversion(stringBuffer.toString());
    }
    public static void main(String[] args) throws IOException {
        WordAnasis("for x from 0 to 200 step cos(1+2-3) draw (x,x+2)");
        StackPop();
        //System.out.println(words2.size());
        ForSentenceHold();
        Runtime.getRuntime().exec("python /Users/tyz/IdeaProjects/CompilePra/src/Draw.py");
        //System.out.println(FunctionHold(new StringBuffer("cos(1)")));
        //RotSentenceHold();
//        ExpressionHasFunc(new StringBuffer("2+COS(2+3)+SIN(2)"));
//        System.out.println(Calculator.conversion("2+0.28366218546322625+-0.4161468365471424"))

    }
}
