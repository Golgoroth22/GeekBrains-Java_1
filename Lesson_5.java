//package java_1.lesson_5;

/**
 * Created by Валентин Фалин on 19.10.2016.
 * Урок 5. Продвинутое ООП.
 * Задание:
 * 1. Создать несколько классов: Собака, Лошадь, Кот - с наследованием от родительского класса Животное; 
 * все классы должны иметь возможность выполнять действия: бежать, плыть, перепрыгивать препятствие; 
 * у каждого класса свои ограничения на каждое из действий (например, высота прыжка, собака может перепрыгнуть максимум полуметровое препятствие, кот - 1-2 метровое; 
 * считаем что коты не умеют плавать и т.д. Каждое действие должно возвращать результат. 
 * Классы необходимо поместить в массив и вызывать их методы, перебирая в цикле.
 */
class Lesson_5 {
    public static void main(String[] args) {
        Animal[] animalMass = new Animal[3];
        animalMass[0] = new Dog();
        animalMass[1] = new Horse();
        animalMass[2] = new Cat();
        for (Animal animal : animalMass) {
            System.out.printf("The %s %s" + (animal.canSwim() ? " Can " : " Can`t ") +
                    "swim. And can jump up about %.1fm.\n", animal.animalType(), animal.run(), animal.getJumpHeight());
        }
    }
}

abstract class Animal {
    abstract String run();

    abstract boolean canSwim();

    abstract double getJumpHeight();

    abstract String animalType();
}

class Dog extends Animal {
    boolean canSwim = true;
    double jumpHeight = 0.5;

    @Override
    String run() {
        return "can run about 10km per hour.";
    }

    @Override
    boolean canSwim() {
        return canSwim;
    }

    @Override
    double getJumpHeight() {
        return jumpHeight;
    }

    @Override
    String animalType() {
        return "Dog";
    }
}

class Horse extends Animal {
    boolean canSwim = true;
    double jumpHeight = 2.3;

    @Override
    String run() {
        return "can run about 45km per hour.";
    }

    @Override
    boolean canSwim() {
        return canSwim;
    }

    @Override
    double getJumpHeight() {
        return jumpHeight;
    }

    @Override
    String animalType() {
        return "Horse";
    }
}

class Cat extends Animal {
    boolean canSwim = false;
    double jumpHeight = 1.5;

    @Override
    String run() {
        return "can run about 13km per hour.";
    }

    @Override
    boolean canSwim() {
        return canSwim;
    }

    @Override
    double getJumpHeight() {
        return jumpHeight;
    }

    @Override
    String animalType() {
        return "Cat";
    }
}

