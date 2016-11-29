/**
 * Created by tyz on 2016/11/26.
 */
public class ConstToken {

    private String constTokenName ;


    public double function(double value){
        if (constTokenName=="SIN"){
            return Math.sin(value);
        }else if (constTokenName=="COS"){
            return Math.cos(value);
        }else if (constTokenName=="LN"){
            return Math.log(value);
        }else if (constTokenName=="TAN"){
            return Math.tan(value);
        }else if (constTokenName=="EXP"){
            return Math.exp(value);
        }else if (constTokenName=="SQRT"){
            return Math.sqrt(value);
        }
        return 0;
    }
    private double value;
    public ConstToken(String constTokenName){
        this.constTokenName = constTokenName;
    }
    public ConstToken(String constTokenName,double value){
        this.constTokenName = constTokenName;
        this.value = value;
    }

    public String getConstTokenName(){
        return constTokenName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
