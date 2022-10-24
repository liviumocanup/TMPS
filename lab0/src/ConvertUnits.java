public class ConvertUnits {
    public static double poundToKg(Number pound){
        return pound.doubleValue()*0.45359237;
    }

    public static double hpToWatts(Number hp){
        return hp.doubleValue()*745.699872;
    }

    public static double milesToKm(Number miles){
        return miles.doubleValue()*1.609344;
    }
}
