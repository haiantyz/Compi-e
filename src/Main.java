import com.sun.javadoc.SeeTag;
import com.sun.jndi.cosnaming.CNCtx;
import com.sun.tools.internal.jxc.ap.Const;

import java.util.Stack;

/**
 * Created by tyz on 2016/11/26.
 */
public class Main {
    //函数部分
    static ConstToken SIN = new ConstToken("SIN");
    static ConstToken COS = new ConstToken("COS");
    static ConstToken TAN = new ConstToken("TAN");
    static ConstToken LN = new ConstToken("LN");
    static ConstToken EXP = new ConstToken("EXP");
    static ConstToken SQRT = new ConstToken("SQRT");
    //关键字保留部分
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
    static Stack<StringBuffer> words = new Stack();
    public static void WordAnasis(String args){
        char temp = '#';
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<args.length();i++){
            char c = args.charAt(i);
            if (c!=' '&c != (',')&c != (';')&c != ('/')&c != ('+')&c != ('-')&c != ('(')&c != (')')){
                if (temp==','|temp==';'|temp=='/'|temp==';'|temp=='+'|temp=='-'|temp=='('|temp==')') {
                    words.push(stringBuffer);
                    stringBuffer = new StringBuffer();
                }
                stringBuffer.append(c);
            }else if (c == (',')|c == (';')|c == ('/')|c == ('+')|c == ('-')|c == ('(')|c == (')')) {
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

    public static void StackPop(){
        while (!words.empty()){
            System.out.println(words.pop());
        }
    }

    public static void main(String[] args){
        WordAnasis("adf/a gg      ");
        System.out.println("------------------------");
        System.out.println(words.size());
        StackPop();
    }
}
