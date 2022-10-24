public class Boat implements Vehicle{
    private final double length;
    private final double transomWidth;

    public Boat(double length, double transomWidth) {
        this.length = length;
        this.transomWidth = transomWidth;
    }

    @Override
    public Number horsePower() {
        double factor = length*transomWidth;
        if(factor>0 && factor<=35){
            return 3;
        }else if(factor>35 && factor<=39) {
            return 5;
        }else if(factor>39 && factor<=42) {
            return 7.5;
        }else if(factor>42 && factor<=45) {
            return 10;
        }else if(factor>45){
            return 15;
        }
        return -1;
    }
}
