package haodong.com.latte_core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by linghaoDo on 2018/8/6
 * 约束
 * interface or abstract
 */
public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity>  ENTITIES=new ArrayList<>();
    private String mJsonData=null;
    public abstract ArrayList<MultipleItemEntity>convert();
    public DataConverter setJsonData(String json){
        this.mJsonData=json;
        return this;
    }
    protected String getJsonData(){
        if(mJsonData==null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL OR DATA IS EMPTY");
        }
        return mJsonData;
    }
}
