package learn.springinaction.soundsystem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
@ContextConfiguration(classes=SoundSystemConfig.class)
public class CDPlayerTest {

    @Rule
    public SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private MediaPlayer cdPlayer;

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(compactDisc);
    }

    @Test
    public void play() {
        cdPlayer.play();
        assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band"
                + " by The Beatles" + System.lineSeparator(), log.getLog());
    }
}
