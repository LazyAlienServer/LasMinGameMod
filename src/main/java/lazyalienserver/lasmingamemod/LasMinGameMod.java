package lazyalienserver.lasmingamemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LasMinGameMod implements ModInitializer {
    public static final String MOD_ID="lasmingamemod";
    public static final String MOD_NAME="LasMinGameMod";
    public static final String MOD_VERSION="1.1.1";

    public static final Logger logger = LoggerFactory.getLogger(MOD_NAME);
    public static MinecraftServer Server;
    @Override
    public void onInitialize() {
        logger.info("LasMinGameMod loaded");
    }

    public static void init(MinecraftServer server){
        Server=server;
    }

}
