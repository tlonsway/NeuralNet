import java.util.*;
public class Generation {
    ArrayList<Creature> creatures = new ArrayList<Creature>();
    public Generation(ArrayList<Creature> c) {
        creatures=c;
    }
    public Generation(int amt) {
        for(int i=0;i<amt;i++) {
            creatures.add(new Creature());
        }
    }
    public Creature getBest() {
        int best=0;
        Creature bestcreature = null;
        for(Creature c : creatures) {
            int score=c.getScore();
            if (score>best) {
                best=score;
                bestcreature=c;
            }
        }
        //System.out.println("best score was : " + best);
        return bestcreature;
    }   
    public Creature getBest2() {
        int best=0;
        int best2=0;
        Creature bestcreature = this.getBest();
        Creature secondbest = creatures.get(0);
        for(Creature c : creatures) {
            int score=c.getScore();
            if (score>best) {
                secondbest=bestcreature;
                best2=best;
                best=score;
                bestcreature=c;
            }
        }
        //System.out.println("second best score was : " + best2);
        return secondbest;        
    }
    public ArrayList<Creature> getBestList() {
        //System.setProperty("java.util.Arrays.useLegacyMergeSort","true");
        //Collections.sort(creatures);
        //Arrays.sort(creatures, Creature.score);
        ArrayList<Creature> temp = new ArrayList<Creature>();
        for(Creature c : creatures) {
            temp.add(c);
        }
        ArrayList<Creature> ret = new ArrayList<Creature>();
        int siz=creatures.size();
        for(int i=0;i<siz;i++) {
            if (creatures.size()>0) {
                int largestval = creatures.get(0).getScore();
                int largestindex = 0;
                for(int c=0;c<creatures.size();c++) {
                    Creature cr = creatures.get(c);
                    int score = cr.getScore();
                    if (score>largestval) {
                        largestval=score;
                        largestindex=c;
                    }
                }
                ret.add(creatures.get(largestindex));
                creatures.remove(largestindex);
            }
        }
        creatures=temp;
        return ret;
    }
}