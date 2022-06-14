package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.dialog.Dialog;

public interface VillagerEntity extends Entity {
    VillagerEntity addDialog(Dialog dialog);
}
