# Pinball - Tarea CC3002

This is the second assignment for **CC3002 - Metodologías de Diseño y Programación**, and represents the logic of a pinball game.

## Dependencies

This project is structured with maven and requires the FXGL game library to run.

## How to run

Run gui.GUI class main method.

## Implementation

Given the base code for the assingment, the project was developed by implementing the interfaces, adding Visitor classes for functionality and constructing a FXGL based GUI.

The features implemented are:

- Different state (Mayor feature): The Bumpers and Target change colour when upgraded/deactivated and downgraded/activated.

- Hitsounds (Mayor feature): When hitting a Bumper or a Target, a sound is played. There is a different sound for every game element and the Bumper sounds change pitch when upgraded.

- Timers (Mayor feature): Some time after upgrading a Bumper or deactivating a Target, it will reset back to its original state. The time depends on the type of element.

- Separated Flippers (Minor Feature): The left flipper uses the A key, the right one uses the S key.

### Bumper

For the implementation of the Bumper classes, and abstract class named *AbstractBumper* was created.
This class gathers all the common elements between both *PopBumper* and *KickBumper*. 

Both of *PopBumper* and *KickBumper* inherit most of their functionality from *AbstractBumper*, only changing the `getScore` method.

Found in src/main/java/logic/gameelements/bumper

### Target

In a similar manner, Targets are implemented by an abstract super-class named *AbstractTarget*, from which
both *SpotTarget* and *DropTarget* inherit their behaviour.

In these case the Bonuses triggered differ between these classes. These Bonuses are triggered through the `acceptVisitor` method when accepting a BonusTriggerer.

Found in src/main/java/logic/gameelements/target

### Bonus

This is the last of the included interfaces. Again, there is an *AbstractBonus*
class, that has *ExtraBallBonus*, *JackPotBonus* and *DropTargetBonus* as sub-classes.

The method `trigger` is the only difference between these three.

Found in src/main/java/logic/bonus

### Table

There are two classes that are based on this interface. *NullTable*, the first, acts as a placeholder for the second, being an implementation
of the **Null Object pattern**. *PlayableTable* is the main Table class, satisfying all the behaviour requirements explicited.

Found in src/main/java/logic/table

### HittableVisitor

This created interface allows for different behaviour every hittable element. Implementations if the interface are ShapePicker,
HittableSoundPicker and ResetTime.

Found in src/main/java/visitor

### BonusTriggerer

Implements an extension of the previous interface that allows for visiting Tables as well as Hittables. Visits a Hittable and triggers
a Bonus when appropiate conditions are met.

An instance of BonusTriggerer is created everytime a Bonus has to be triggered, and discarded after.

Found in src/main/java/visitor/hittableandtablevisitor

### Game

*Game* is the controller class of the project, and as such, it needs to be able to know when a *Hittable* has been hit.
This is implemented with the **Observer pattern**, being the *Game* the observer, and the *Hittables* the observables.

In addition to this, *Game* has a variety of methods that are used by *HomeworkTwoFacade's* methods. 

Found in src/main/java/controller

### FlipperComponent

Sub-class of a FXGL component. Is added to Entities that represents flippers and allows them to act in that way. This class
is responsible of the rotation of the flippers, restricting their angle and setting the rotation velocity.

Found in src/main/java/component	

### HittableComponent

Interface that connects an Entity to a Hittable and allows iteractions between the two. Has methods to hit, reset and get the
current state of the hittable.

Found in src/main/java/component/hittablecomponent

### InteractiveEntityFactory and StaticElementFactory

Classes providing methods to create new Entities. StaticElementFactory allows for easy creation of a background, border walls
and solid fixed blocks. InteractiveEntityFactory has methods for initialization of flippers, balls, bumpers and targets

Found in src/main/java/gui/gamefactory

### GUI

Main class. Responsible for the graphic representation of the game and executing logic according to the user interactions.

Found in src/main/gui