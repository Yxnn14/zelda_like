package fr.yann.zelda_like.api.block;


import fr.yann.zelda_like.api.level.Location;

public interface TeleportBlock extends Block {
    Location getTeleportLocation();
    void setTeleportLocation(Location location);
}
