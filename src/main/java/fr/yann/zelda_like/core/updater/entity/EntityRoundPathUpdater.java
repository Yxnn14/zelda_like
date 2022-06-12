package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.AbstractUpdater;

import java.util.*;

public class EntityRoundPathUpdater extends AbstractUpdater<Entity> {

    private final Random random = new Random();
    private final int range;

    private Location originLocation;

    private boolean oriented;

    public EntityRoundPathUpdater(int range, int tickUpdater) {
        super(tickUpdater);
        this.range = range;
    }

    @Override
    public void onUpdate(ZeldaLike zeldaLike, Entity entity) {
        if (this.originLocation == null) {
            this.originLocation = entity.getLocation().clone();
        }

        final Level level = zeldaLike.getLevelManager().get();
        final int rate = this.random.nextInt(100);
        int orientedRate = this.oriented ? 80 : 60;

        int[] coordinateView = this.getCoordinateView(entity.getLocation().getOrientation());
        if (!level.getBlockAt(entity.getLocation().add(coordinateView[0], coordinateView[1])).isTransparent()) {
            orientedRate = 0;
        }

        if (rate >= orientedRate) {
            final List<Location.Orientation> orientations = new ArrayList<>();
            Collections.addAll(orientations, Location.Orientation.values());
            orientations.remove(entity.getLocation().getOrientation());
            while (!orientations.isEmpty()){
                final Location.Orientation orientation = orientations.get(this.random.nextInt(orientations.size()));
                coordinateView = this.getCoordinateView(orientation);
                if (level.getBlockAt(entity.getLocation().add(coordinateView[0], coordinateView[1])).isTransparent()) {
                    entity.getLocation().setOrientation(orientation);
                    oriented = true;
                    break;
                }
                orientations.remove(orientation);
            }
            return;
        }

        if (rate >= 30) {
            coordinateView = this.getCoordinateView(entity.getLocation().getOrientation());
            final Location destination = entity.getLocation().add(coordinateView[0], coordinateView[1]);
            if (
                Math.abs(this.originLocation.getX() - destination.getX()) <= this.range
                    && Math.abs(this.originLocation.getY() - destination.getY()) <= this.range
            ) {
                level.moveEntity(entity, destination);
            }
            this.oriented = false;
        }
    }

    private int[] getCoordinateView(Location.Orientation orientation) {
        return switch (orientation) {
            case NORTH -> new int[]{0 , -1};
            case SOUTH -> new int[]{0 , 1};
            case EAST -> new int[]{1 , 0};
            case WEST -> new int[]{-1 , 0};
        };
    }
}
