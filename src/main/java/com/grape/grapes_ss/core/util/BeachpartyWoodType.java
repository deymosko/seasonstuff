package com.grape.grapes_ss.core.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import com.grape.grapes_ss.Beachparty;

public class BeachpartyWoodType {
    public static final WoodType PALM = WoodType.register(new WoodType(new ResourceLocation(Beachparty.MOD_ID, "palm").toString(), BlockSetType.OAK));
}