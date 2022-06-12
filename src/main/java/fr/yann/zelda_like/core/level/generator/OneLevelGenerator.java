package fr.yann.zelda_like.core.level.generator;

import fr.yann.zelda_like.api.block.TeleportBlock;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.block.*;
import fr.yann.zelda_like.core.entity.ImplPlayerEntity;
import fr.yann.zelda_like.core.entity.LezardMonsterEntity;
import fr.yann.zelda_like.core.level.ImplLocation;
import fr.yann.zelda_like.core.updater.entity.EntityPathUpdater;

import java.util.Random;

public class OneLevelGenerator implements LevelGenerator {
    @Override
    public void generate(Level level) {
        final Random random = new Random();
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                if (x == 0 || x == level.getWidth() - 1 || y == 0 || y == level.getHeight() - 1) {
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (y == 5 && x >= 7 && x <= 24) {
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (x == 7 && y > 5 && y < 12) {
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (x == 24 && y > 5 && y < 9) {
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (y == 9 && x >= 14 && x <= 24) {
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (x == 22 && y == 7) {
                    final TeleportBlock teleportBlock = level.setBlock(ImplTeleporterBlock.class, ImplLocation.create(x, y));
                    teleportBlock.setTeleportLocation(ImplLocation.create(23, 13, Location.Orientation.SOUTH));
                    continue;
                }
                if (y == 12 && x >= 7) {
                    if (x == 26) {
                        level.setBlock(DoorBlock.class, ImplLocation.create(x, y));
                        continue;
                    }
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (y < 5 && x > 6 && x < 25 && random.nextInt(100) > 85) {
                    level.setBlock(BombBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                level.setBlock(GroundBlock.class, ImplLocation.create(x, y));
            }
        }
        level.spawn(ImplPlayerEntity.class, ImplLocation.create(23, 13, Location.Orientation.SOUTH));
        MonsterEntity monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(7, 13, Location.Orientation.EAST)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.VERTICAL, 1, 5));
    }
}

