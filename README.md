# Pinball - Tarea CC3002

This is the second assignment for **CC3002 - Metodologías de Diseño y Programación**, and represents the logic of a pinball game.

## Implementation

Given the base code for the assingment, the project was developed by implementing the interfaces and adding a class.

### Bumper

For the implementation of the Bumper classes, and abstract class named *AbstractBumper* was created.
This class gathers all the common elements between both *PopBumper* and *KickBumper*. 

Both of *PopBumper* and *KickBumper* inherit most of their functionality from *AbstractBumper*, only changing the `getScore` method.

Found in src/main/java/logic/gameelements/bumper

### Target

In a similar manner, Targets are implemented by an abstract super-class named *AbstractTarget*, from which
both *SpotTarget* and *DropTarget* inherit their behaviour.

In these case the Bonuses triggered differ between these classes. These Bonuses are triggered through the `acceptTriggerer` method.

Found in src/main/java/logic/gameelements/target

### Bonus

This is the last interface that required a common abstract class for all its implementations. Again, there is an *AbstractBonus*
class, that has *ExtraBallBonus*, *JackPotBonus* and *DropTargetBonus* as sub-classes.

The method `trigger` is the only difference between these three.

Found in src/main/java/logic/bonus

### Table

There are two classes that are based on this interface. *NullTable*, the first, acts as a placeholder for the second, being an implementation
of the **Null Object pattern**. *PlayableTable* is the main Table class, satisfying all the behaviour requirements explicited.

Found in src/main/java/logic/table

### BonusTriggerer

This is an extra class, used for the implementation of the **Visitor pattern**. For this reason, *Hittable* and *Table* had the method
`acceptTriggerer` added, which accepts a BonusTriggerer.

An instance of BonusTriggerer is created everytime a Bonus has to be triggered, and discarded after.

Found in src/main/java/controller

### Game

*Game* is the controller class of the project, and as such, it needs to be able to know when a *Hittable* has been hit.
This is implemented with the **Observer pattern**, being the *Game* the observer, and the *Hittables* the observables.

In addition to this, *Game* has a variety of methods that are used by *HomeworkTwoFacade's* methods. 

Found in src/main/java/controller