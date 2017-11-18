package learn.springinaction.knights;

public class DamselRescuingKnight implements Knight {
    private Quest quest;

    public DamselRescuingKnight(Quest quest) {
//        this.quest = new RescueDamselQuest(System.out);
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
