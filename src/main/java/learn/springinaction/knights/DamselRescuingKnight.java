package learn.springinaction.knights;

public class DamselRescuingKnight implements Knight {
    private Quest quest;

    public DamselRescuingKnight() {
        this.quest = new RescueDamselQuest(System.out);
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
