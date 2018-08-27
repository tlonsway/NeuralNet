import java.util.*;
import cs1.*;
public class Learn {
    public static void main(String[] args) {
        int num=-1;
        int best=0;
        double lrate = .05;
        //while(true) {
        boolean worked=true;
        num++;
        int attempts=0;
        ArrayList<Neuron> in = new ArrayList<Neuron>();
        ArrayList<Neuron> hidden = new ArrayList<Neuron>();
        ArrayList<Neuron> out = new ArrayList<Neuron>();
        ArrayList<Dendrite> in_hidden = new ArrayList<Dendrite>();
        ArrayList<Dendrite> hidden_out = new ArrayList<Dendrite>();
        for(int i=0;i<2;i++) {
            in.add(new Neuron());
        }
        for(int i=0;i<5;i++) {
            hidden.add(new Neuron());
        }
        for(int i=0;i<2;i++) {
            out.add(new Neuron());
        }
        for(Neuron n1 : in) {
            for(Neuron n2 : hidden) {
                in_hidden.add(new Dendrite(n1,n2));
            }
        }
        for(Neuron n1 : hidden) {
            for(Neuron n2 : out) {
                hidden_out.add(new Dendrite(n1,n2));
            }
        }     
        int ITERATIONS = 10000;
        for(int i=0;i<ITERATIONS;i++) {
            for(Neuron n : in) {
                n.setTotal(0);
                n.setBackTotal(0);
            }
            for(Neuron n : hidden) {
                n.setTotal(0);
                n.setBackTotal(0);
            }
            for(Neuron n : out) {
                n.setTotal(0);
                n.setBackTotal(0);
            }
            attempts++;
            double rand1=Math.random();
            double rand2=Math.random();
            ///rand1=((double)((int)(rand1*1000)))/1000;
            //rand2=((double)((int)(rand2*1000)))/1000;
            if(rand1==rand2)
                rand2+=.02;
            double largest=rand1;
            double out1target=1;
            double out2target=0;
            if (rand2>largest) {
                largest=rand2;
                out1target=0;
                out2target=1;
            }
            in.get(0).setValue(rand1);
            in.get(1).setValue(rand2);
            for(Dendrite d : in_hidden) {
                d.setLRate(lrate);
                d.compute();
            }
            for(Neuron n2 : hidden) {
                n2.calculate();
            }
            for(Dendrite d : hidden_out) {
                d.setLRate(lrate);
                d.compute();
            }
            for(Neuron n2 : out) {
                n2.calculate();
            }
            double out1 = out.get(0).getValue();
            double out2 = out.get(1).getValue();
            System.out.println("-----------------------------------------------");
            System.out.println("in1: " + rand1);
            System.out.println("in2: " + rand2);
            System.out.println("out1: " + out1);
            System.out.println("out2: " + out2);
            System.out.println("out1target: " + out1target);
            System.out.println("out2target: " + out2target);
            double out1err = out1target-out1;
            double out2err = out2target-out2;
            out.get(0).setExpected(out1target);
            out.get(1).setExpected(out2target);
            //calculate errors
            for(Neuron n : out) {
                n.backOutErr();
            }
            for(Dendrite d : hidden_out) {
                d.backCompute();
            }
            for(Neuron n : hidden) {
                n.backCalculate();
            }
            //calculate new weights
            for(Dendrite d : hidden_out) {
                d.backWeight();
            }
            for(Dendrite d : in_hidden) {
                d.backWeight();
            }
        }
        System.out.println("Network is now trained, enter custom values");
        while(true) {
            System.out.print("var1:");
            double one = Keyboard.readDouble();
            System.out.print("var2:");
            double two = Keyboard.readDouble();
            for(Neuron n : in) {
                n.setTotal(0);
            }
            for(Neuron n : hidden) {
                n.setTotal(0);
            }
            for(Neuron n : out) {
                n.setTotal(0);
            }
            double largest2=-1;
            if (one>largest2) {
                largest2=one;
            }
            if (two>largest2) {
                largest2=two;
            }
            in.get(0).setValue(one);
            in.get(1).setValue(two);
            for(Dendrite d : in_hidden) {
                d.compute();
            }
            for(Neuron n2 : hidden) {
                n2.calculate();
            }
            for(Dendrite d : hidden_out) {
                d.compute();
            }
            for(Neuron n2 : out) {
                n2.calculate();
            }
            System.out.println("out1: " + out.get(0).getValue());
            System.out.println("out2: " + out.get(1).getValue());                                        
        }
        
        //}
    }
}