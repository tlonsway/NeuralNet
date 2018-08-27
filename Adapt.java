    import java.util.*;
public class Adapt {
    public static void main(String[] args) {
        ArrayList<Neuron> in = new ArrayList<Neuron>();
        ArrayList<Neuron> hidden = new ArrayList<Neuron>();
        ArrayList<Neuron> out = new ArrayList<Neuron>();
        ArrayList<Dendrite> in_hidden = new ArrayList<Dendrite>();
        ArrayList<Dendrite> hidden_out = new ArrayList<Dendrite>();
        ArrayList<Dendrite> out_hidden = new ArrayList<Dendrite>();
        ArrayList<Dendrite> hidden_in = new ArrayList<Dendrite>();
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
        for(Neuron n1 : out) {
            for(Neuron n2 : hidden) {
                out_hidden.add(new Dendrite(n1,n2));
            }
        }
        for(Neuron n1 : hidden) {
            for(Neuron n2 : in) {
                hidden_in.add(new Dendrite(n1,n2));
            }
        }
        int attempts=0;
        while(true) {
            attempts++;
            for(Neuron n : in) {
                n.setTotal(0);
            }
            for(Neuron n : hidden) {
                n.setTotal(0);
            }
            for(Neuron n : out) {
                n.setTotal(0);
            }        
            for(Dendrite d : in_hidden) {
                d.setOriginalWeight(d.getWeight());
            }
            for(Dendrite d : hidden_out) {
                d.setOriginalWeight(d.getWeight());
            }
            for(Dendrite d : out_hidden) {
                d.setOriginalWeight(d.getWeight());
            }
            for(Dendrite d : hidden_in) {
                d.setOriginalWeight(d.getWeight());
            }
            double rand1=Math.random();
            double rand2=Math.random();
            rand1=((double)((int)(rand1*1000)))/1000;
            rand2=((double)((int)(rand2*1000)))/1000;        
            if (rand1==rand2) {
                rand2+=.02;
            }
            double largest=-1;
            if (rand1>largest) {
                largest=rand1;
            }
            if (rand2>largest) {
                largest=rand2;
            }
            in.get(0).setValue(rand1);
            in.get(1).setValue(rand2);
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
            double out1 = out.get(0).getValue();
            double out2 = out.get(1).getValue();       
            double eone = 0;
            double etwo = 0;
            System.out.println("rand1: " + rand1);
            System.out.println("rand2: " + rand2);
            System.out.println("out1: " + out1);
            System.out.println("out2: " + out2);          
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
            if (rand1>rand2) {
                eone=1;
            } else {
                etwo=1;
            }
            out.get(0).setExpected(eone);
            out.get(1).setExpected(etwo);
            for(Dendrite d : out_hidden) {
                Neuron n1 = d.getOne();
                Neuron n2 = d.getTwo();
                double doutsum = Neuron.dsigmoid(n1.getTotal())*(n1.getExpected()-n1.getTotal());
                n1.setSum(doutsum);
                d.setWeight(d.getWeight()-doutsum/n2.getValue());
            }
            int i=0;
            in.get(0).setSum(out.get(0).getSum());
            in.get(1).setSum(out.get(1).getSum());
            for(Dendrite d : in_hidden) {
                Neuron n1 = d.getOne();
                Neuron n2 = d.getTwo();
                double dhidsum = n1.getSum()/hidden_out.get(i).getOriginalWeight()*Neuron.dsigmoid(n2.getTotal());
                System.out.println("n1sum:" + n1.getSum() + " odendrite:" + hidden_out.get(i).getOriginalWeight() + " dsigmoid:" + Neuron.dsigmoid(n2.getTotal()));
                
                
                d.setWeight(d.getWeight()-dhidsum/n1.getValue());
                i++;
            }
            
        }
        
        
        
    }
}