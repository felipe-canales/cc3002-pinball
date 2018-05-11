package logic.util;

import java.util.Random;

public class RNG {
    private Random r;

    public RNG() {
        r = new Random();
    }

    public RNG(int seed) {
        r = new Random(seed);
    }

    public void changeSeed(int newSeed) {
        r = new Random(newSeed);
    }

    public double probability() {
        return r.nextDouble();
    }
}
