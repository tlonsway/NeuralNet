public class Neuron {
    double value;
    double total;
    public Neuron() {
        value=0;
        total=0;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double d) {
        value=d;
    }
    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }
    public void add(double d) {;
        total+=d;
    }
    public void calculate() {
        value=sigmoid(total);
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double t) {
        total=t;
    }
}