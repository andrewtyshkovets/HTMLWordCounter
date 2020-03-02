package com.andrew;

public class Main {

    public static void main(String[] args) throws Exception {

       // "https://stihi-rus.ru/World/Shekspir/1.htm"

       View view = new View();
       Model model = new Model();
       Controller controller = new Controller(view,model);
       controller.runController();
       //controller.runHomeTask();
    }
}
