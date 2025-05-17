package org.manus.compassplus;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ConfigValue COORDINATES_FORMAT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Compass Plus Mod Settings");
        builder.push("general");

        COORDINATES_FORMAT = builder
                .comment("Format for the coordinates display in Action Bar.",
                        "Use {x}, {y}, {z} for coordinates.",
                        "Example: 'X: {x}, Y: {y}, Z: {z}'")
                .define("coordinates_format", "{x} | {y} | {z}");

        builder.pop();
        SPEC = builder.build();
    }
}