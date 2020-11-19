package pr2.uebung05;
public class LinearProbing implements Probing {

    private int factor = 1;
    private boolean switchSign = true;
    static final private int jump = 1;

    @Override
    public int nextNum() {
        int value = factor * jump;
        if(switchSign) {
            factor++;
        	switchSign = false;
            return (-1) * value ;
        } else {
        	switchSign = true;
            return value;
        }
    }

    @Override
    public void startProbing() {
        factor = 1;
        switchSign = true;
    }
}