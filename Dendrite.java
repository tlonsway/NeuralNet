public class Dendrite {
    Neuron n1;
    Neuron n2;
    double weight;
    double originalweight;
    double lrate;
    public Dendrite(Neuron o, Neuron t, double lr) {
        n1=o;
        n2=t;
        weight=Math.random();
        originalweight=weight;
        lrate=lr;
    }
    public Dendrite(Neuron o, Neuron t, double w, double lr) {
        n1=o;
        n2=t;
        weight=w;
        originalweight=weight;
        lrate=lr;
    }
    public void compute() {
        n2.add(weight*n1.getValue());
    }
    public double getWeight() {
        return weight;
    }
    public Neuron getOne() {
        return n1;
    }
    public Neuron getTwo() {
        return n2;
    }
    public void setWeight(double d) {
        weight=d;
    }
    public double getOriginalWeight() {
        return originalweight;
    }
    public void setOriginalWeight(double d) {
        originalweight=d;
    }
    public void backCompute() {
        n1.backAdd(weight*n2.getErr());
    }
    public void backWeight() {
        originalweight=weight;
        weight=weight+(lrate*n1.getValue()*n2.getErr());
    }
    public void setLRate(double l) {
        lrate=l;
    }
}