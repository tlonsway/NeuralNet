public class Neuron {
    double value;
    double total;
    double expect;
    double sum;
    double err;
    double backval;
    double backtotal;
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
    public static double dsigmoid(double x) {
        return sigmoid(x)*(1-sigmoid(x));
    }
    public void add(double d) {
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
    public double getExpected() {
        return expect;
    }
    public void setExpected(double d) {
        expect=d;
    }
    public void setSum(double d) {
        sum=d;
    }
    public double getSum() {
        return sum;
    }
    public void setErr(double num) {
        err=num;
    }
    public double getErr() {
        return err;
    }
    public void backCalculate() {
        err=dsigmoid(value)*backtotal;
    }
    public void backOutErr() {
        err=expect-value;
    }
    public void backAdd(double ad) {
        backtotal+=ad;
    }
    public void setBackTotal(double bt) {
        backtotal=bt;
    }
}