public class Main {
    public static void main(String[] args) {
        Car c = new Car(1500, 100);
        Boat b = new Boat(30, 2);

        System.out.println("The car has: "+ c.horsePower() + " hp or " + (long) ConvertUnits.hpToWatts(c.horsePower())+" Watts.");
        System.out.println("The boat has: "+ b.horsePower() + " hp or " + (long) ConvertUnits.hpToWatts(b.horsePower())+" Watts.");
    }
}