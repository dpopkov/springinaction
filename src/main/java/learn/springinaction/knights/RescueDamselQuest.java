package learn.springinaction.knights;

import java.io.PrintStream;

public class RescueDamselQuest implements Quest {
    private PrintStream stream;

    public RescueDamselQuest(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void embark() {
        stream.println("Embarking on quest to save the damsel!");
    }
}
