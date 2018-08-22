package Utilities.MultiThreading;

/**
 * Author: Honey Kakkar
 * Project: Coding Practice in JAVA
 * Date created: 11/22/2016
 */
public class DeadLock2 {

    static class Friend {
        private final String name;

        Friend(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }

        synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            System.out.format("%s is waiting to bow back.....%n", this.name);
            bower.bowBack(this);
            // Waiting for the release of bower's intrinsic lock which has been acquired by bow method of bower.
            // The bow method of bower waits for the release of this object's intrinsic lock. Thus, DEADLOCK !!!
        }

        synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();

        new Thread(() -> gaston.bow(alphonse)).start();
    }
}
