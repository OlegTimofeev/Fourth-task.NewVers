package cars;

import java.util.ArrayList;
import java.util.List;

public class Mark  {
    private String code;
    private String name;
    private List<Model> modelList;


    public Mark(String name,String code) {
        this.name = name;
        this.code=code;
        modelList=new ArrayList<Model>();
    }

    public Mark(String name){
        this.name=name;
        modelList=new ArrayList<Model>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToList(Model model) {
        modelList.add(model);
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public Model findModel(Model model){
        for(Model i:modelList){
            if(i.getName().equalsIgnoreCase(model.getName()));
        }
        return null;
    }
}
