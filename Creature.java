import java.util.*;
public class Creature implements Comparable<Creature>{
    ArrayList<Neuron> in = new ArrayList<Neuron>();
    ArrayList<Neuron> hidden = new ArrayList<Neuron>();
    ArrayList<Neuron> out = new ArrayList<Neuron>();
    ArrayList<Dendrite> in_hidden = new ArrayList<Dendrite>();
    ArrayList<Dendrite> hidden_out = new ArrayList<Dendrite>();
    int score = 0;
    public Creature() {
        for(int i=0;i<2;i++) {
            in.add(new Neuron());
        }
        for(int i=0;i<10;i++) {
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
    }
    public Creature(ArrayList<String> w) {
        for(int i=0;i<2;i++) {
            in.add(new Neuron());
        }
        for(int i=0;i<10;i++) {
            hidden.add(new Neuron());
        }
        for(int i=0;i<2;i++) {
            out.add(new Neuron());
        }        
        int n=0;
        for(Neuron n1 : in) {
            for(Neuron n2 : hidden) {
                in_hidden.add(new Dendrite(n1,n2,Double.parseDouble(w.get(n))));
                n++;
            }
        }
        for(Neuron n1 : hidden) {
            for(Neuron n2 : out) {
                hidden_out.add(new Dendrite(n1,n2,Double.parseDouble(w.get(n))));
                n++;
            }
        }
    }
    public int compute(double one, double two) {
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
        int out1 = (int)(out.get(0).getValue());   
        int out2 = (int)(out.get(1).getValue());   
        if (out1>out2) {
            return 1;
        } else  {
            return 2;
        }
    }
    public void reset() {
        for(Neuron n : in) {
            n.setTotal(0);
        }
        for(Neuron n : hidden) {
            n.setTotal(0);
        }
        for(Neuron n : out) {
            n.setTotal(0);
        } 
    }
    public int getScore() {
        boolean work=true;
        int attempts=-1;
        while(work) {
            attempts++;
            double rand1=Math.random();
            double rand2=Math.random();
            rand1=((double)((int)(rand1*1000)))/1000;
            rand2=((double)((int)(rand2*1000)))/1000;     
            if (rand1==rand2) {
                rand2+=.02;
            }
            int dec = this.compute(rand1,rand2);
            work=false;
            if(rand1>rand2&&dec==1) {
                work=true;
            }
            if (rand2>rand1&&dec==2) {
                work=true;
            }
            if(attempts>5000) {
                break;
            }
                                for(Neuron n : in) {
                        System.out.println("input neuron value: " + n.getValue());
                    }
                    for(Dendrite d : in_hidden) {
                        System.out.println("dendrite l-r from in-hidden weight: " + d.getWeight());
                    }
                    for (Neuron n : hidden) {
                        System.out.println("hidden neuron total: " + n.getTotal());
                        System.out.println("hidden neuron value: " + n.getValue());
                    }
                    for(Dendrite d : hidden_out) {
                        System.out.println("dendrite l-r from hidden-out weight: " + d.getWeight());
                    }
                    for(Neuron n : out) {
                        System.out.println("output neuron total: " + n.getTotal()); 
                        System.out.println("output neuron value: " + n.getValue());
                    }
            this.reset();
            
        }
        score=attempts;
        return attempts;
    }
    public ArrayList<String> getWeights() {
        ArrayList<String> ret = new ArrayList<String>();
        for(Dendrite d : in_hidden) {
            ret.add(""+d.getWeight());
        }
        for(Dendrite d : hidden_out) {
            ret.add(""+d.getWeight());
        }
        return ret;
    }
    public ArrayList<String> getWeights1() {
        ArrayList<String> ret = new ArrayList<String>();
        for(Dendrite d : in_hidden) {
            ret.add(""+d.getWeight());
        }
        return ret;
    }
    public ArrayList<String> getWeights2() {
        ArrayList<String> ret = new ArrayList<String>();
        for(Dendrite d : hidden_out) {
            ret.add(""+d.getWeight());
        }
        return ret;
    }
    public int getScoreValue() {
        return score;
    }
    public int compareTo(Creature o) {
        int ts = this.getScore();
        int os = ((Creature)(o)).getScore();
        if (ts>os) {
            return -1;
        } else if (os>ts) {
            return 1;
        } else {
            return 0;
        }
        //return os-ts;
    }
}