/**
 * Created by tyz on 2016/11/26.
 */
public class ConstId {

    private String IdName;
    private double IdValue;
    public ConstId(String IdName,double IdValue){
        this.IdName = IdName;
        this.IdValue = IdValue;
    }

    public String getIdName() {
        return IdName;
    }

    public void setIdName(String idName) {
        IdName = idName;
    }

    public double getIdValue() {
        return IdValue;
    }

    public void setIdValue(double idValue) {
        IdValue = idValue;
    }
}
