package component;

import com.almasb.fxgl.time.TimerAction;
import visitor.Visitor;

public interface HittableComponent {
    void hit();
    boolean isAlternateState();
    void resetState();
    void changeView();
    void setTimerAction(TimerAction timerAction);
    void expireTimerAction();
    void accept(Visitor v);
}
