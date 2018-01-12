package cars;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Cars {

    public static String[] menu = {
            "\n1.Получить список машин\n" +
                    "2.Получить отсортированный по цене по возрастанию список\n" +
                    "3.Получить отсортированный по цене по убыванию список\n" +
                    "4.Найти машины нужной марки\n" +
                    "5.Найти машины нужной модели\n" +
                    "6.Найти машины нужной марки и модели\n" +
                    "7.Найти машины нужной марки,модели и цвета\n" +
                    "8.Найти машину по коду\n" +
                    "9.Получить список доступных марок автомобилей\n"+
                    "10.Получить список доступных моделей для марки\n"+
                    "0.Выход\n"
    };
    public static String inputError = "Введено некорректное значение";

    public static void main(String[] args) throws IOException {
        CarServiceImpl carService = new CarServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println(Arrays.toString(menu));
        int num;
        String mark="", color="", model="", code="";
        if (sc.hasNextInt()) {
            num = sc.nextInt();
        } else {
            System.out.print(inputError);
            sc.close();
            return;
        }
        while (num >= 0 && num < 11) {
            sc.nextLine();
            if (num == 1) {
                carService.printMachinesAll();
            }
            if (num == 2) {
                carService.sortPrice(1);
                carService.printMachinesAll();
            }
            if (num == 3) {
                carService.sortPrice(-1);
                carService.printMachinesAll();
            }
            if (num == 4) {
                System.out.print("Введите название марки:");
                if(sc.hasNextLine()) {
                    mark = sc.nextLine().trim();
                }
                carService.findMark(mark);
                carService.printMachinesFinded();
            }
            if (num == 5) {
                System.out.print("Введите название модели:");
                if(sc.hasNextLine()) {
                    model = sc.nextLine().trim();
                }
                carService.findModel(model);
                carService.printMachinesFinded();
            }
            if (num == 6) {
                System.out.print("Введите название марки:");
                if(sc.hasNextLine()) {
                    mark = sc.nextLine().trim();
                }
                System.out.print("Введите название модели:");
                if(sc.hasNextLine()) {
                    model = sc.nextLine().trim();
                }
                carService.find(model, mark);
                carService.printMachinesFinded();
            }
            if (num == 7) {
                System.out.print("Введите название марки:");
                mark = sc.nextLine().trim();
                System.out.print("Введите название модели:");
                model = sc.nextLine().trim();
                System.out.print("Введите цвет:");
                if(sc.hasNextLine()) {
                    color = sc.nextLine().trim();
                }
                carService.find(model, mark, color);
                carService.printMachinesFinded();
            }
            if (num == 8) {
                System.out.print("Введите уникальный код машины:");
                code = sc.nextLine().trim();
                carService.find(code);
                carService.printMachinesFinded();
            }
            if(num==9){
                System.out.println("Доступные марки:");
                carService.printAvailableMarks();
            }
            if(num==10){
                System.out.println("Введите марку,для которой хотите посмотреть список моделей");
                if(sc.hasNextLine()) {
                    model = sc.nextLine().trim();
                }
                System.out.println("Список моделей для "+model+":");
                carService.printAvailableModel(model);
            }
            if (num == 0) {
                sc.close();
                return;
            }
            System.out.println(Arrays.toString(menu));

            if (sc.hasNextInt()) {
                num = sc.nextInt();
            } else {
                sc.close();
                System.out.println(inputError);
                return;
            }
        }
    }
}