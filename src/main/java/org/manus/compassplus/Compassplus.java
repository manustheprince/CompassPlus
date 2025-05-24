package org.manus.compassplus;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod("compassplus")
public class Compassplus {
    public static final String MODID = "compassplus";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Compassplus() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "compassplus-common.toml");
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            Minecraft instance = Minecraft.getInstance();
            Player player = instance.player;

            if (player != null) {
                if (player.getMainHandItem().getItem() == Items.COMPASS ||
                    player.getOffhandItem().getItem() == Items.COMPASS) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();

                    String format = Config.COORDINATES_FORMAT.get().toString();
                    String coords = format.replace("{x}", String.format("%.1f", x))
                            .replace("{y}", String.format("%.1f", y))
                            .replace("{z}", String.format("%.1f", z));

                    instance.gui.setOverlayMessage(Component.literal(coords), false);
                }
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Hello!");
    }
}
