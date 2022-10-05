public class Car implements Vehicle{
    private final double weight;
    private final double speed;

    public Car(double weight, double speed) {
        this.weight = weight;
        this.speed = speed;
    }

    @Override
    public Number horsePower() {
        return (int) (ConvertUnits.poundToKg(weight)*Math.pow(ConvertUnits.milesToKm(speed)/234, 3));
    }

}
