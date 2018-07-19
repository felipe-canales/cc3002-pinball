package component;

public interface HittableComponent {
    void hit();
    boolean isAlternateState();
    void resetState();
}
