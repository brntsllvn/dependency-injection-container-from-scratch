package com.brent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SingletonFactory factory = new SingletonFactory();
        SingletonTester.test(factory); // verify factory is threadsafe

        Sunscreen sunscreen;
        sunscreen = (Sunscreen) factory.getSingleton(Sunscreen.class);
        System.out.println(sunscreen.hashCode());
    }
}

class SingletonFactory {
    private Map<String, Object> singletons = new ConcurrentHashMap<>();

    public Object getSingleton(Class clazz) {
        Object singleton = null;
        synchronized (this.singletons) {
            String singletonName = clazz.getName().toLowerCase();
            if (!singletons.containsKey(singletonName)) {
                singleton = makeSingleton(clazz);
                this.singletons.put(singletonName, singleton);
            } else {
                singleton = this.singletons.get(singletonName);
            }
        }
        return singleton;
    }

    private Object makeSingleton(Class<?> clazz) {
        Constructor<?> sunscreenConstructor = clazz.getConstructors()[0];
        Object obj = null;
        try {
            obj = sunscreenConstructor.newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return obj;
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


//    Constructor<?> sunscreenConstructor = Sunscreen.class.getConstructors()[0];
//    Sunscreen sunscreen = null;
//        try {
//                sunscreen = (Sunscreen) sunscreenConstructor.newInstance();
//                } catch (InstantiationException ex) {
//                ex.printStackTrace();
//                } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//                } catch (InvocationTargetException ex) {
//                ex.printStackTrace();
//                }
//
//                System.out.println(sunscreen.toString());

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