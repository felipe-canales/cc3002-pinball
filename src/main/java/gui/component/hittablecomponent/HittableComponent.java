package gui.component.hittablecomponent;

import com.almasb.fxgl.time.TimerAction;
import logic.gameelements.Hittable;

/**
 * Interface of a {@link com.almasb.fxgl.entity.component.Component} that connects an
 * {@link com.almasb.fxgl.entity.Entity;} to a {@link logic.gameelements.Hittable}.
 *
 * Allows for interactions between the FXGL Game World and Hittable game elements.
 *
 * @author Felipe Canales
 * @see BumperComponent
 * @see TargetComponent
 */
public interface HittableComponent {
    /**
     * Hits the hittable game element contained in the implementation.
     */
    void hit();

    /**
     * For Hittables that can have two or more states, this method will return False if it is in the default state
     * (eg: not upgraded for {@link logic.gameelements.bumper.Bumper}s, active for {@link logic.gameelements.target.Target}s).
     * Otherwise, this method will always return True.
     *
     * @return if the hittable contained is in an alternate state.
     */
    boolean isAlternateState();

    /**
     * Sets the contained Hittable, to its default state.
     */
    void resetState();

    /**
     * Changes the figure that represents the Hittable in the game according to its current state.
     */
    void changeView();

    /**
     * Saves a {@link TimerAction} reference affecting this Hittable. This is used if said TimerAction has to be
     * discarded.
     *
     * @param timerAction Action affecting the hittable contained.
     */
    void setTimerAction(TimerAction timerAction);

    /**
     * Discards the saved TimerAction, preventing it from being executed.
     */
    void expireTimerAction();

    /**
     * Hittable getter.
     *
     * @return the Hittable contained.
     */
    Hittable getHittable();
}
