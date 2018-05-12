package logic.gameelements;

import controller.BonusTriggerer;

import java.util.Observer;

/**
 * Interface that represents a hittable object.
 *
 * <p>Objects that are game elements should implement this interface.</p>
 *
 * @author Juan-Pablo Silva
 * @see logic.gameelements.bumper.Bumper
 * @see logic.gameelements.target.Target
 */
public interface Hittable {
    /**
     * Defines that an object have been hit.
     * Implementations should consider the events that a hit to an object can trigger.
     *
     * @return the score the player obtained hitting the object
     */
    int hit();

    /**
     * Defines that a hittable object has to have a score when it is hit.
     *
     * @return the current score of the object when hit
     */
    int getScore();

    /**
     * Receives a {@link BonusTriggerer} and, if appropiate, triggers a {@link logic.bonus.Bonus}.
     *
     * @param bonusTriggerer Instance of {@link BonusTriggerer}
     */
    void acceptTriggerer(BonusTriggerer bonusTriggerer);

    /**
     * Adds an {@link java.util.Observer} as a listener of this hittable.
     *
     * @param o
     */
    void addObserver(Observer o);
}
