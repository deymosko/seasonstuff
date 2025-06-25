package com.grape.grapes_ss.core.util;

import net.minecraft.resources.ResourceLocation;
import com.grape.grapes_ss.Beachparty;

public class BeachpartyIdentifier extends ResourceLocation {
    public BeachpartyIdentifier(String path) {
        super(Beachparty.MOD_ID, path);
    }
}
