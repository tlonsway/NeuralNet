public class Dendrite {
    Neuron n1;
    Neuron n2;
    double weight;
    public Dendrite(Neuron o, Neuron t) {
        n1=o;
        n2=t;
        weight=Math.random();
    }
    public void compute() {
        n2.add(weight*n1.getValue());
    }
    public double getWeight() {
        return weight;
    }
}