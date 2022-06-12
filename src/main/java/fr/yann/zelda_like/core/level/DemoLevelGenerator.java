package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.block.TeleportBlock;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.block.*;
import fr.yann.zelda_like.core.entity.DemoMonsterEntity;
import fr.yann.zelda_like.core.entity.ImplItemEntity;
import fr.yann.zelda_like.core.entity.ImplPlayerEntity;
import fr.yann.zelda_like.core.inventory.DemoItem;
import fr.yann.zelda_like.core.inventory.DemoTwoItem;
import fr.yann.zelda_like.core.inventory.MoneyItem;
import fr.yann.zelda_like.core.updater.entity.EntityPathUpdater;
import fr.yann.zelda_like.core.updater.entity.EntityRoundPathUpdater;

import java.util.Random;

public class DemoLevelGenerator implements LevelGenerator {
    @Override
    public void generate(Level level) {
        final Random random = new Random();
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                if (random.nextInt(100) > 25) {
                    if (random.nextInt(100) > 1) {
                        level.setBlock(GroundBlock.class, ImplLocation.create(x, y));
                        continue;
                    }
                    level.setBlock(DemoThreeBlock.class, ImplLocation.create(x,y));
                    continue;
                }

                level.setBlock(WallBlock.class, ImplLocation.create(x, y));
            }
        }
        level.setBlock(BombBlock.class, ImplLocation.create(8, 8));

        for (int y = 2; y < level.getHeight(); y++) {
            level.setBlock(GroundBlock.class, ImplLocation.create(3, y));
        }

        TeleportBlock teleporterBlock = level.setBlock(ImplTeleporterBlock.class, ImplLocation.create(3, 3));
        teleporterBlock.setTeleportLocation(ImplLocation.create(3, 8));

        level.setBlock(DoorBlock.class, ImplLocation.create(9, 7));


        level.spawn(ImplPlayerEntity.class, ImplLocation.create(level.getWidth() / 2, level.getHeight() / 2));
        ItemEntity itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(1, 2));
        itemEntity.setItem(new DemoItem());

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(5, 7));
        itemEntity.setItem(new DemoTwoItem());

        DemoMonsterEntity demoMonsterEntity = level.spawn(DemoMonsterEntity.class, ImplLocation.create(10, 3, Location.Orientation.SOUTH));
        // demoMonsterEntity.getUpdaterManager().add(new EntityPathUpdater(EntityPathUpdater.Direction.HORIZONTAL, 3, 4));
        demoMonsterEntity.getUpdaterManager().add(new EntityRoundPathUpdater(1, 5));

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(14, 8));
        itemEntity.setItem(new MoneyItem());

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(2, 6));
        itemEntity.setItem(new MoneyItem());
    }
}
