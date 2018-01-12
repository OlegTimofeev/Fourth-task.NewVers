package cars;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarsDAOImpl implements CarsDAO {
    private List<String> code;
    private List<Car> cars;
    private List<Model> models;
    private List<Mark> marks;

    public CarsDAOImpl() throws IOException {
        cars = new ArrayList<Car>();
        code = new ArrayList<String>();
        models = new ArrayList<Model>();
        marks = new ArrayList<Mark>();
        getAllCars();
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String countOfCars(Scanner sc) {
        String str = "";
        while (sc.hasNext()) {
            str = str.concat(sc.nextLine());
        }
        return str;
    }

    public void fromStringToCar(String str) {
        String[] carPart = str.split("[;]+");
        boolean createCar=false;
        for(Color i: Color.values()){
            if(carPart[2].trim().equalsIgnoreCase(i.toString())){
                createCar=true;
            }
        }
        if(!code.contains(carPart[1])&&createCar) {
            boolean containMark=false;
            boolean containModel=false;
            code.add(carPart[1]);
            Car car = new Car();
            car.setCode(carPart[1]);
            car.setColor(carPart[2].trim().toUpperCase());
            Mark mark = new Mark(carPart[3].trim(), carPart[1].trim());
            Model model = new Model(carPart[4].trim(), carPart[1].trim());
            Model modelToCheck = null;
            for(Model i:models){
                if(i.getName().equalsIgnoreCase(model.getName())){
                    modelToCheck=i;
                    break;
                }
            }
            for(Mark m:marks){
                if(m.getName().equalsIgnoreCase(mark.getName())){
                    containMark=true;
                }
                if(modelToCheck!=null&&modelToCheck.getMark().getName().equalsIgnoreCase(m.getName())){
                    for(Model mod:m.getModelList()){
                        if(modelToCheck.getName().equalsIgnoreCase(mod.getName())){
                            containModel=true;
                        }
                    }
                }
            }
            if(modelToCheck!=null&&modelToCheck.getMark().getName().equalsIgnoreCase(mark.getName())||modelToCheck==null){
                model.setMark(mark);
                models.add(model);
                if(!containModel) {
                    mark.addToList(model);
                }
                if(!containMark){
                    marks.add(mark);
                }
                car.setMark(mark);
                car.setModel(model);
                car.setYear(Integer.parseInt(carPart[5].trim()));
                car.setPrice(BigDecimal.valueOf(Double.parseDouble(carPart[6].trim())));
                cars.add(car);
            }
        }
    }


    public void getAllCars() {
        FileReader fr=null;
        Scanner cs=null;
        try{ fr = new FileReader("cars.dat");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fr);
        String str="";
        while (sc.hasNextLine()) {
            str = sc.nextLine();
            fromStringToCar(str);
        }
        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}