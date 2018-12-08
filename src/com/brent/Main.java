package com.brent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Constructor<?> sunscreenConstructor = Sunscreen.class.getConstructors()[0];
        Sunscreen sunscreen = null;
        try {
            sunscreen = (Sunscreen) sunscreenConstructor.newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }

        System.out.println(sunscreen.toString());
    }
}

class Sunscreen {
    Integer sunProtectionFactor;
    public Sunscreen() {
        this.sunProtectionFactor = 45;
    }
    public String toString() {
        return String.format("This sunscreen has SPF: %s", this.sunProtectionFactor);
    }
}

//class Vacation {
//    private PlaneTickets tickets;
//    private Sunscreen sunscreen;
//    private Luggage luggage;
//}
//
//class PlaneTickets {
//    private Airline airline;
//}
//
//class Airline {
//    public
//}
//
//class Sunscreen {
//}
//
//class Luggage {
//    private Sandals sandals;
//    private HawaiianShirt shirt;
//}