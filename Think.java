import java.util.*;
import cs1.*;
public class Think {
    public static void main(String[] args) {
        int num=-1;
        int best=0;
        while(true) {
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
            while(worked) {
                for(Neuron n : in) {
                    n.setTotal(0);
                }
                for(Neuron n : hidden) {
                    n.setTotal(0);
                }
                for(Neuron n : out) {
                    n.setTotal(0);
                }
                attempts++;
                double rand1=Math.random();
                double rand2=Math.random();
                rand1=((double)((int)(rand1*1000)))/1000;
                rand2=((double)((int)(rand2*1000)))/1000;
                if(rand1==rand2)
                    rand2+=.02;
                
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
                worked=false;
                /*System.out.println("rand1: " + rand1);
                System.out.println("rand2: " + rand2);
                System.out.println("out1: " + out1);
                System.out.println("out2: " + out2);*/  
                if(out1>out2&&rand1>rand2) {
                    //System.out.println("worked");
                    worked=true;
                }
                if(out2>out1&&rand2>rand1) {
                    //System.out.println("worked");
                    worked=true;
                }
                /*if(worked) {
                    System.out.println("rand1: " + rand1);
                    System.out.println("rand2: " + rand2);
                    System.out.println("out1: " + out1);
                    System.out.println("out2: " + out2);            
                }*/
                /*if(attempts>50) {
                    System.out.println("fully working");
                    System.out.println("rand1: " + rand1);
                    System.out.println("rand2: " + rand2);
                    System.out.println("out1: " + out1);
                    System.out.println("out2: " + out2);  
                }*/
                if (attempts>1100) {
                    System.out.println("enter variables to test");
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
                }
                if(attempts>1000) {
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
                }
                if(attempts>best&&worked) {
                    best=attempts;
                    System.out.println("new best: " + best + " + with network " + num);
                    /*for(Neuron n : in) {
                        System.out.println("input neuron value: " + n.getValue());
                    }
                    for(Dendrite d : in_hidden) {
                        System.out.println("dendrite l-r from in-hidden weight: " + d.getWeight());
                    }
                    for (Neuron n : hidden) {
                        System.out.println("hidden neuron total: " + n.getTotal());
                        System.out.println("hidden neuron value: "  + n.getValue());
                    }
                    for(Dendrite d : hidden_out) {
                        System.out.println("dendrite l-r from hidden-out weight: " + d.getWeight());
                    }
                    for(Neuron n : out) {
                        System.out.println("output neuron total: " + n.getTotal()); 
                        System.out.println("output neuron value: " + n.getValue());
                    }*/
                }
            }
        }
    }
}
