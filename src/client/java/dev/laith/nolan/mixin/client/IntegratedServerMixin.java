package dev.laith.nolan.mixin.client;

import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("no-lan");

    @Inject(method = "openToLan", at = @At("HEAD"), cancellable = true)
    private void nolan$blockLanPublish(GameMode gameMode, boolean cheatsAllowed, int port,
                                       CallbackInfoReturnable<Boolean> cir) {
        LOGGER.info("Blocked LAN publish attempt (mode={}, cheats={}, port={})", gameMode, cheatsAllowed, port);
        cir.setReturnValue(false);
    }
}
