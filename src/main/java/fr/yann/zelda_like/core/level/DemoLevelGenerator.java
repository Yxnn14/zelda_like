package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.core.block.DemoBlock;
import fr.yann.zelda_like.core.block.DemoTwoBlock;

public class DemoLevelGenerator implements LevelGenerator {
    @Override
    public void generate(Level level) {
        boolean xSwitch = false;
        for (int x = 0; x < level.getWidth(); x++) {
            xSwitch = !xSwitch;
            boolean ySwitch = !xSwitch;
            for (int y = 0; y < level.getHeight(); y++) {
                ySwitch = !ySwitch;
                level.setBlock(ySwitch ? DemoBlock.class : DemoTwoBlock.class, ImplLocation.create(x, y));
            }
        }
    }
}
