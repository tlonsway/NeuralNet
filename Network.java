import java.util.*;
public class Network {
    ArrayList<Neuron> inputs;
    ArrayList<ArrayList<Neuron>> hiddenLayers;
    ArrayList<Neuron> outputs;
    ArrayList<Dendrite> in_hidden_dendrite;
    ArrayList<ArrayList<Dendrite>> hiddenDendriteLayers;
    ArrayList<Dendrite> hidden_out_dendrite;
    double lrate;
    public Network(int in_num, int hidl_num, int[] hidnl_amt, int out_num, double lrn_r) {
        lrate=lrn_r;
        //input validation
        if (hidl_num!=hidnl_amt.length) {
            System.out.println("hidnl_amt arr should be same length as hidl_num");
            System.exit(1);
        }
        System.out.println("[BUILDING NETWORK MODEL]");
        //init arraylists
        inputs = new ArrayList<Neuron>();
        hiddenLayers = new ArrayList<ArrayList<Neuron>>();
        outputs = new ArrayList<Neuron>();
        in_hidden_dendrite = new ArrayList<Dendrite>();
        hiddenDendriteLayers = new ArrayList<ArrayList<Dendrite>>();
        hidden_out_dendrite = new ArrayList<Dendrite>();     
        //initialize input neurons
        for(int i=0;i<in_num;i++) {
            inputs.add(new Neuron());
        }
        //create layers of hidden neurons
        for(int i=0;i<hidl_num;i++) {
            hiddenLayers.add(new ArrayList<Neuron>());
        }
        //initialize neurons in hidden layers
        for(int i=0;i<hidl_num;i++) {
            for(int j=0;j<hidnl_amt[i];j++) {
                hiddenLayers.get(i).add(new Neuron());
            }
        }
        //initialize output neurons
        for(int i=0;i<out_num;i++) {
            outputs.add(new Neuron());
        }
        //build in_hidden dendrite layer
        for(Neuron n1 : inputs) {
            for(Neuron n2 : hiddenLayers.get(0)) {
                in_hidden_dendrite.add(new Dendrite(n1,n2,lrate));
            }
        }
        //create layers of hidden dendrites
        for(int i=0;i<hidl_num-1;i++) {
            hiddenDendriteLayers.add(new ArrayList<Dendrite>());
        }
        //build internal hidden dendrite layers
        for(int i=0;i<hidl_num-1;i++) {
            for(Neuron n1 : hiddenLayers.get(i)) {
                for(Neuron n2 : hiddenLayers.get(i+1)) {
                    hiddenDendriteLayers.get(i).add(new Dendrite(n1,n2,lrate));
                }
            }
        }
        //build hidden_out dendrite layer
        for(Neuron n1 : hiddenLayers.get(hidl_num-1)) {
            for(Neuron n2 : outputs) {
                hidden_out_dendrite.add(new Dendrite(n1,n2,lrate));
            }
        }
        System.out.println("[NETWORK MODEL CONSTRUCTION COMPLETE]");
    }
    public double[] feedForward(double[] inps, int verbose) {
        if (verbose>0) {
            System.out.println("[PERFORMING FEED FORWARD ON NETWORK]");
        }
        //reset neuron sums
        this.reset();
        //validate input
        if (inps.length!=inputs.size()) {
            System.out.println("array of inputs is wrong size");
            System.exit(1);
        }
        //set input neurons
        for(int i=0;i<inps.length;i++) {
            inputs.get(i).setValue(inps[i]);
        }
        //feed input neurons to hidden layer 0
        for(Dendrite d : in_hidden_dendrite) {
            d.compute();
        }
        //calculate activation function on hidden neuron first layer
        for(Neuron n : hiddenLayers.get(0)) {
            n.calculate();
        }
        //calculate hidden layer weights and activations
        for(int i=0;i<hiddenLayers.size()-1;i++) {
            for(Dendrite d : hiddenDendriteLayers.get(i)) {
                d.compute();   
            }
            for(Neuron n : hiddenLayers.get(i+1)) {
                n.calculate();
            }
        }
        //calculate hidden_out weights
        for(Dendrite d : hidden_out_dendrite) {
            d.compute();
        }
        //calculate activation function on outputs
        for(Neuron n : outputs) {
            n.calculate();
        }
        //printing results
        if (verbose>0) {
            System.out.println("[FEED FORWARD COMPUTATION COMPLETE]");
            System.out.println("output neurons(top-bottom)");
            for(Neuron n : outputs) {
                System.out.println(n.getValue());
            }
        }
        //return output neuron values
        double[] outs = new double[outputs.size()];
        for(int i=0;i<outs.length;i++) {
            outs[i]=outputs.get(i).getValue();
        }
        return outs;
    }
    public void train(double[] ins, double[] targets) {
        //calculate feed forward on network
        double[] ffoutput = feedForward(ins, 0);
        //calculate error on output neurons
        for(int i=0;i<ffoutput.length;i++) {
            outputs.get(i).setExpected(targets[i]);
            outputs.get(i).backOutErr();
        }
        //feed weights backwards to sum
        for(Dendrite d : hidden_out_dendrite) {
            d.backCompute();
        }
        //calculate dsig/cost going backwards through hidden layers
        for(int i=hiddenLayers.size()-1;i>0;i--) {
            for(Neuron n : hiddenLayers.get(i)) {
                n.backCalculate();
            }
            for(Dendrite d : hiddenDendriteLayers.get(i-1)) {
                d.backCompute();
            }
        }
        //calculate dsig/cost of first hidden neuron layer
        for(Neuron n : hiddenLayers.get(0)) {
            n.backCalculate();
        }
        //changing weights based on gradient descent
        for(Dendrite d : hidden_out_dendrite) {
            d.backWeight();
        }
        for(int i=hiddenDendriteLayers.size()-1;i>-1;i--) {
            for(Dendrite d : hiddenDendriteLayers.get(i)) {
                d.backWeight();
            }
        }
        for(Dendrite d : in_hidden_dendrite) {
            d.backWeight();
        }
    }
    public void reset() {
        //set all sums to 0
        for(Neuron n : inputs) {
            n.setTotal(0);
            n.setBackTotal(0);
        }
        for(int i=0;i<hiddenLayers.size();i++) {
            for(Neuron n : hiddenLayers.get(i)) {
                n.setTotal(0);
                n.setBackTotal(0);
            }
        }
        for(Neuron n : outputs) {
            n.setTotal(0);
            n.setBackTotal(0);
        }
    }
}    