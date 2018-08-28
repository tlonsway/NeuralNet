public class NetworkTest {
    public static void main(String[] args) {
        Network n = new Network(2,2,new int[]{3,3},2,.1);
        for(int i=0;i<5000;i++) {
            double rand1 = Math.random();
            double rand2 = Math.random();
            double expec1 = 0;
            double expec2 = 0;
            if (rand1==rand2) {
                expec1=1;
                expec2=1;
            }
            if (rand1>rand2) {
                expec1=1;
                expec2=0;
            }
            if (rand2>rand1) {
                expec1=0;
                expec2=1;
            }
            n.train(new double[]{rand1,rand2}, new double[]{expec1,expec2});
        }
        for(int i=0;i<15;i++) {
            double in1=Math.random();
            double in2=Math.random();
            double[] out = n.feedForward(new double[]{in1,in2}, 0);
            System.out.println("in1: " + in1);
            System.out.println("in2: " + in2);
            System.out.println("out1: " + out[0]);
            System.out.println("out2: " + out[1]);
        }
    }
}