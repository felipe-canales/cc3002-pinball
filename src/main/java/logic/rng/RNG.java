package logic.rng;

import java.util.Random;

public class RNG {
    private Random r;

    public RNG() {
        r = new Random();
    }

    public void setSeed(int newSeed) {
        r = new Random(newSeed);
    }

    public double probability() {
        return r.nextDouble();
    }
}
