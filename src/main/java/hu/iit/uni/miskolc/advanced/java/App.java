package hu.iit.uni.miskolc.advanced.java;

import hu.iit.uni.miskolc.advanced.java.service.PrimeService;
import hu.iit.uni.miskolc.advanced.java.service.PrimeServiceImpl;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        PrimeService service = new PrimeServiceImpl();
    }
}
