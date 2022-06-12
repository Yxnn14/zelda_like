package fr.yann.zelda_like.core.updater.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.block.DoorBlock;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.updater.Updater;

public class DoorOpenObjectiveUpdater implements Updater<Objective> {
    private final Location location;

    public DoorOpenObjectiveUpdater(Location location) {
        this.location = location;
    }

    @Override
    public void update(ZeldaLike zeldaLike, Objective objective) {
        if (objective.isComplete()) {
            objective.getUpdaterManager().remove(this.getClass());
            return;
        }

        final Block block = zeldaLike.getLevelManager().get().getBlockAt(this.location);
        if (block instanceof DoorBlock doorBlock) {
            objective.setComplete(doorBlock.isOpen());
        }
    }
}
