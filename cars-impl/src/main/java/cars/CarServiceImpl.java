package cars;

import javax.management.ObjectName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarServiceImpl implements CarService {

    private CarsDAOImpl carsDAOImpl;
    private List<Car> findedCars;
    private List<Car> cars;

    public CarServiceImpl() throws IOException {
        carsDAOImpl=new CarsDAOImpl();
        cars=carsDAOImpl.getCars();
    }

    public void sortPrice( final int down) {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car car, Car t1) {
                return down*car.getPrice().compareTo(t1.getPrice());
            }
        });
    }

    public List<Car> findMark(String mark){
        findedCars= new ArrayList<Car>();
        for(Car i:cars){
            if(i.getMark().getName().equalsIgnoreCase(mark)){
                findedCars.add(i);
            }
        }
        return findedCars;

    }

    public List<Car> findModel(String model){
        findedCars= new ArrayList<Car>();
        for(Car i:cars){
            if(i.getModel().getName().equalsIgnoreCase(model)){
                findedCars.add(i);
            }
        }
        return findedCars;
    }

    public List<Car> find(String model,String mark){
        findedCars= new ArrayList<Car>();
        for(Car i:cars){
            if(i.getModel().getName().equalsIgnoreCase(model)&&i.getMark().getName().equalsIgnoreCase(mark)){
                findedCars.add(i);
            }
        }
        return findedCars;
    }

    public List<Car> find(String model, String mark, String color){
        findedCars= new ArrayList<Car>();
        for(Car i:cars){
            if(i.getModel().getName().equalsIgnoreCase(model)&&i.getMark().getName().equalsIgnoreCase(mark)&&i.getColor().toString().equalsIgnoreCase(color)){
                findedCars.add(i);
            }
        }
        return findedCars;
    }

    public Car find(String code){
        Car carToReturn = null;
        for(Car i :cars){
            if(i.getCode().equalsIgnoreCase(code)){
                carToReturn=i;
            }
        }
        return carToReturn;
    }

    public void printAvailableMarks(){
        for(Mark m:carsDAOImpl.getMarks()){
            System.out.println(m.getName());
        }
    }

    public void printAvailableModel(String mark){
        Mark markForPrint=new Mark(mark);
        for(Mark m:carsDAOImpl.getMarks()){
            if(m.getName().equalsIgnoreCase(mark)){
                markForPrint=m;
                break;
            }
        }
        for(Model model:markForPrint.getModelList()){
            System.out.println(model.getName());
        }
    }

    public void printMachinesAll(){
        for(Car car:cars) {
            System.out.println(car.getMark().getName() + " " + car.getModel().getName() + " " + car.getColor() + " " + car.getYear() + " " + car.getPrice());
        }
    }

    public void printMachinesFinded(){
        for(Car car:findedCars) {
            System.out.println("Марка:"+car.getMark().getName() + " " +"Модель:"+ car.getModel().getName() + " " + "Цвет:"+car.getColor() + " " + "Год выпуска:" +car.getYear() + " " +"Цена:"+ car.getPrice());
        }
    }

}
