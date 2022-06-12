package fr.yann.zelda_like.api.block;

public interface DoorBlock extends Block {
    boolean isOpen();
    void setOpen(boolean open);
}
