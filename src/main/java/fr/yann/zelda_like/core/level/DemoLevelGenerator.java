package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.block.DemoBlock;
import fr.yann.zelda_like.core.block.DemoThreeBlock;
import fr.yann.zelda_like.core.block.DemoTwoBlock;
import fr.yann.zelda_like.core.entity.DemoMonsterEntity;
import fr.yann.zelda_like.core.entity.ImplItemEntity;
import fr.yann.zelda_like.core.entity.ImplPlayerEntity;
import fr.yann.zelda_like.core.inventory.DemoItem;
import fr.yann.zelda_like.core.inventory.DemoTwoItem;
import fr.yann.zelda_like.core.updater.entity.EntityPathUpdater;

import java.util.Random;

public class DemoLevelGenerator implements LevelGenerator {
    @Override
    public void generate(Level level) {
        final Random random = new Random();
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                level.setBlock(
                    random.nextInt(100) > 25
                        ? random.nextInt(100) > 1
                            ? DemoBlock.class
                            : DemoThreeBlock.class
                        : DemoTwoBlock.class,
                    ImplLocation.create(x, y)
                );
            }
        }

        level.spawn(ImplPlayerEntity.class, ImplLocation.create(level.getWidth() / 2, level.getHeight() / 2));
        ItemEntity itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(1, 2));
        itemEntity.setItem(new DemoItem());

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(5, 7));
        itemEntity.setItem(new DemoTwoItem());

        DemoMonsterEntity demoMonsterEntity = level.spawn(DemoMonsterEntity.class, ImplLocation.create(10, 3, Location.Orientation.SOUTH));
        demoMonsterEntity.getUpdaterManager().add(new EntityPathUpdater(EntityPathUpdater.Direction.HORIZONTAL, 3, 4));
    }
}
