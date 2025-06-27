import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Instrument.Factory factory = new Instrument.Factory();
        Instrument violin = factory.create("Violin");
        Instrument drums  = factory.create("Drums");

        Instrument.Part bow  = violin.new Part("Bow");
        Instrument.Part stick = drums.new Part("Drumstick");
        System.out.println(bow.info());
        System.out.println(stick.info());

        Musician m1 = new Musician("Luna", violin);
        Musician m2 = new Musician("Kai", drums);

        Orchestra orchestra = new Orchestra();
        orchestra.addMusician(m1);
        orchestra.addMusician(m2);

        class SoundCheck {
            void check(Musician m) {
                System.out.println("Sound check for " + m.getName());
                m.play();
            }
        }
        SoundCheck sc = new SoundCheck();
        sc.check(m1);
        sc.check(m2);

        System.out.println("--- Orchestra Performance ---");
        orchestra.perform();
    }
}

class Instrument {
    private final String name;

    Instrument(String name) {
        this.name = name;
    }

    void play() {
        System.out.println(name + " is playing.");
    }

    public class Part {
        private final String partName;

        Part(String partName) {
            this.partName = partName;
        }

        String info() {
            return partName + " belongs to " + name;
        }
    }

    public static class Factory {
        public Instrument create(String name) {
            return new Instrument(name);
        }
    }
}

class Musician {
    private final String name;
    private final Instrument instrument;

    Musician(String name, Instrument instrument) {
        this.name = name;
        this.instrument = instrument;
    }

    void play() {
        System.out.print(name + ": ");
        instrument.play();
    }

    String getName() {
        return name;
    }
}

class Orchestra {
    private final List<Musician> musicians = new ArrayList<>();

    void addMusician(Musician m) {
        musicians.add(m);
    }

    void perform() {
        for (Musician m : musicians) {
            m.play();
        }
    }
}
