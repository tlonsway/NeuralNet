import java.util.*;
public class Evolve {
    public static void main(String[] args) {
        int gens=0;
        Generation g = new Generation(100);
        int attempts=0;
        while(true) {
            attempts++;
            ArrayList<Creature> data = g.getBestList();
            ArrayList<Creature> next = new ArrayList<Creature>();
            //for(int i=0;i<data.size()-1;i+=2) {
            //Creature c1 = data.get(i);
            //Creature c2 = data.get(i+1);


            /*for(int amt=0;amt<98;amt++) {
                nextw = new ArrayList<String>();
                for(int i2=0;i2<weight1.size();i2++) {
                    int o = (int)(Math.random()*2);
                    int r = (int)(Math.random()*101);
                    if (o>=1) {
                        nextw.add(weight1.get(i2));
                    } else {
                        nextw.add(weight2.get(i2));
                    }
                    if (r>90) {
                        nextw.set(i2,""+Math.random());
                    }
                }
                /*System.out.println("parent1\tparent2\toffspring");
                for(int i=0;i<nextw.size();i++) {
                    System.out.println(weight1.get(i)+" "+weight2.get(i)+" "+nextw.get(i));
                }
                next.add(new Creature(nextw));
            }*/
            for(int i=0;i<10;i++) {
                ArrayList<String> weight1 = data.get(i).getWeights();
                ArrayList<String> weight2 = data.get(i+1).getWeights();
                ArrayList<String> nextw = new ArrayList<String>();
                next.add(new Creature(weight1));
                next.add(new Creature(weight2));        
                for(int i2=0;i2<weight1.size();i2++) {
                    int o = (int)(Math.random()*2);
                    int r = (int)(Math.random()*101);
                    if (o>=1) {
                        nextw.add(weight1.get(i2));
                    } else {
                        nextw.add(weight2.get(i2));
                    }
                    if (r>95) {
                        nextw.set(i2,""+Math.random());
                    }
                }                
                next.add(new Creature(nextw));
            }
            
            
            
            
            //next.add(new Creature(cr));
            //next.add(new Creature(cr2));
            //next.add(new Creature(c1.getWeights()));
            //next.add(new Creature(c2.getWeights()));
            //}
            double average=0;
            ArrayList<Creature> all = g.getBestList();
            int tot=0;
            for(Creature c : all) {
                tot++;
                average+=c.getScore();
            }
            double finave=average/tot;
            if(attempts>1) {
                System.out.println("generation best was: " + g.getBest().getScoreValue());
                System.out.println("generation average was: " + finave);
            }
            //System.out.println("generation size is: " + data.size());
            g=new Generation(next);
        }
    }
}