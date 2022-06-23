package fr.yann.zelda_like.core.level.generator;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.block.TeleportBlock;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.entity.MerchantEntity;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import fr.yann.zelda_like.api.entity.VillagerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.objective.ObjectiveManager;
import fr.yann.zelda_like.core.block.*;
import fr.yann.zelda_like.core.dialog.ImplDialog;
import fr.yann.zelda_like.core.entity.*;
import fr.yann.zelda_like.core.inventory.*;
import fr.yann.zelda_like.core.level.ImplLocation;
import fr.yann.zelda_like.core.updater.block.LevelOneVictoryUpdater;
import fr.yann.zelda_like.core.updater.entity.EntityPathUpdater;
import fr.yann.zelda_like.core.updater.objective.CompleteAllObjectiveUpdater;
import fr.yann.zelda_like.core.updater.objective.DoorOpenObjectiveUpdater;
import fr.yann.zelda_like.core.updater.objective.KeyObjectiveUpdater;
import fr.yann.zelda_like.core.updater.objective.KillMonsterObjectiveUpdater;

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
                        Block door = level.setBlock(SimpleDoorBlock.class, ImplLocation.create(x, y));
                        door.getUpdaterManager().add(new LevelOneVictoryUpdater());
                        continue;
                    }
                    level.setBlock(WallBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                if (y < 5 && x > 6 && x < 25 && random.nextInt(100) > 87) {
                    level.setBlock(BombBlock.class, ImplLocation.create(x, y));
                    continue;
                }
                level.setBlock(GroundBlock.class, ImplLocation.create(x, y));
            }
        }
        level.spawn(ImplPlayerEntity.class, ImplLocation.create(23, 13, Location.Orientation.SOUTH));

        ItemEntity itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(23, 15));
        itemEntity.setItem(new CapeItem());

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(25, 15));
        itemEntity.setItem(new AngleKillerItem());

        itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(21, 15));
        itemEntity.setItem(new KillEntityItem());

        level.spawn(AngleEntity.class, ImplLocation.create(27, 15));

        MonsterEntity monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(7, 13, Location.Orientation.EAST)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.VERTICAL, 3, 5));

        monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(1, 12, Location.Orientation.SOUTH)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.HORIZONTAL, 5, 5));

        monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(4, 5, Location.Orientation.SOUTH)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.HORIZONTAL, 5, 5));

        monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(14, 11, Location.Orientation.EAST)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.VERTICAL, 1, 5));

        monsterEntity = level.spawn(
                LezardMonsterEntity.class,
                ImplLocation.create(8, 6, Location.Orientation.SOUTH)
        );
        monsterEntity.getUpdaterManager()
                .add(new EntityPathUpdater(EntityPathUpdater.Direction.HORIZONTAL, 5, 5));
        VillagerEntity villagerEntity = level.spawn(SimpleVillagerEntity.class, ImplLocation.create(23, 16));
        villagerEntity.addDialog(
                ImplDialog.create("Bonjour jeune aventurier ! Va parler au villageois et ramène la clef")
        );

        villagerEntity = level.spawn(ClearerVillagerEntity.class, ImplLocation.create(29, 15));
        villagerEntity.addDialog(ImplDialog.create("Oups, je t'ai fait disperser tous tes items."));

        villagerEntity = level.spawn(KeeperVillagerEntity.class, ImplLocation.create(29, 13));
        villagerEntity.addDialog(ImplDialog.create("Très bien, je te garde ton item"));

        MerchantEntity merchantEntity = level.spawn(
                MerchantVillagerEntity.class,
                ImplLocation.create(1, 3, Location.Orientation.EAST)
        );
        merchantEntity.setSoldItem(new PotionHealthItem());
        merchantEntity.setPrice(3);
        merchantEntity.setStock(10000);
        merchantEntity.addDialog(
                ImplDialog.create(
                    "Je te donne 1 "
                        + merchantEntity.getSoldItem().getName()
                        + " contre "
                        + merchantEntity.getPrice()
                        + " coins !"
                )
        );
        merchantEntity.addDialog(ImplDialog.create("Attention, le chemin vers l'est a été piégé!"));
        MerchantEntity questVillager = level.spawn(
                QuestVillagerEntity.class,
                ImplLocation.create(level.getWidth() - 2, 6)
        );
        questVillager.setSoldItem(new KeyItem());
        questVillager.setStock(1);
        questVillager.addDialog(ImplDialog.create("Tu as besoin de la clef? tiens, prends la !"));
        questVillager.addDialog(
                ImplDialog.create(
                        "Tu ne peux ouvrir la porte que de l'autre coté, prends le teleporter,\n il te ramènera la bas!"
                )
        );

        Objective objective = level.getObjectiveManager()
            .create("Récupérer la clef", "Aller parler au villageois");
        objective.getUpdaterManager().add(new KeyObjectiveUpdater());
        level.getObjectiveManager().add(objective);

        objective = level.getObjectiveManager()
                .create("Tuer tous les monstres", "Attaquer avec SPACE");
        objective.getUpdaterManager().add(new KillMonsterObjectiveUpdater());
        level.getObjectiveManager().add(objective);

        objective = level.getObjectiveManager().create("Terminer les objectifs", "Finir les objectifs");
        objective.setHidden(true);
        objective.getUpdaterManager()
            .add(new CompleteAllObjectiveUpdater((zeldaLike) -> {
                final ObjectiveManager objectiveManager = zeldaLike.getLevelManager().get().getObjectiveManager();
                final Objective newObjective = objectiveManager.create(
                    "Ouvrir la porte",
                    "Passer la porte du début"
                );
                newObjective.getUpdaterManager().add(new DoorOpenObjectiveUpdater(ImplLocation.create(26, 12)));
                objectiveManager.add(newObjective);
            }));
        level.getObjectiveManager().add(objective);
    }
}

