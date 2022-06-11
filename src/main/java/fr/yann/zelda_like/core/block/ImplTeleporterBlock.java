package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.TeleportBlock;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.block.TeleportUpdater;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImplTeleporterBlock extends AbstractBlock implements TeleportBlock {

    private static final Image TEXTURE = new Image("assets/textures/teleporter.png");
    private Location teleportLocation;

    public ImplTeleporterBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0.5, 0, 0.5));
        this.getUpdaterManager().add(new TeleportUpdater());
    }
    @Override
    public Location getTeleportLocation() {
        return this.teleportLocation;
    }
    @Override
    public void setTeleportLocation(Location location) {
        this.teleportLocation = location;
    }

    @Override
    public Image getTexture() {
        return ImplTeleporterBlock.TEXTURE;
    }
}
