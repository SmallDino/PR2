package pr2.uebung05;

public class QuadraticProbing implements Probing {	
    private int factor = 1;
    private boolean switchSign = true;

    @Override
    public int nextNum() {
        int value = (factor * factor);
        if(switchSign) {
            factor++;
        	switchSign = false;
            return value;
        } else {
        	switchSign = true;
            return (-1) * value;
        }
    }

    @Override
    public void startProbing() {
        this.factor = 1;
        this.switchSign = true;
    }
}