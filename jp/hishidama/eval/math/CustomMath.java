import java.lang.Math;

public class CustomMath {

    public static double log(double bottom, double value) {
        return Math.log(value) / Math.log(bottom);
    }

    public static double acos(double value) {
        return Math.acos(value);
    }

    public static double asin(double value) {
        return Math.asin(value);
    }

    public static double atan(double value) {
        return Math.atan(value);
    }

    public static double nrt(double n, double value) {
        return Math.pow(value, (1 / n));
    }

    public static double sin(double theta) {
        return Math.sin(theta);
    }

    public static double cos(double theta) {
        return Math.cos(theta);
    }

    public static double tan(double theta) {
        return Math.tan(theta);
    }

    public static double cosh(double theta) {
        return Math.cosh(theta);
    }

    public static double sinh(double theta) {
        return Math.sinh(theta);
    }

    public static double tanh(double theta) {
        return Math.tanh(theta);
    }

    public static double exp(double n) {
        return Math.exp(n);
    }

    public static double log(double value) {
        return Math.log(value);
    }

    public static double pow(double value, double n) {
        return Math.pow(value, n);
    }
}