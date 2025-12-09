package dev.vesper.substrate.mixin;

import dev.vesper.substrate.Substrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? neoforge{
/*import net.neoforged.fml.ModList;
*///?}

import java.awt.*;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

	@Contract(value = "-> fail", pure = true)
	private MinecraftClientMixin(){
		throw new AssertionError("No instances.");
	}

	@Inject(method = "updateLevelInEngines", at = @At("RETURN"))
	private void afterLoadLevel(ClientLevel clientLevel, CallbackInfo ci) {
		if (clientLevel == null) return;

		Minecraft.getInstance().execute(() ->{
			DimensionType dimension = clientLevel.dimensionType();
			final ResourceLocation dimID = dimension.effectsLocation();

			int newFloor = Integer.MIN_VALUE;
			int newCeiling = Integer.MAX_VALUE;

			if (dimID.equals(BuiltinDimensionTypes.OVERWORLD_EFFECTS)){
				newFloor = dimension.minY();
				newCeiling = Integer.MAX_VALUE;
			}
			if (dimID.equals(BuiltinDimensionTypes.NETHER_EFFECTS)){
				newFloor = dimension.minY();
				//? fabric{
				newCeiling = dimension.logicalHeight() - 1;
				//?}
				//? neoforge{
				/*newCeiling = dimension.logicalHeight() - 2;
				if (ModList.get().isLoaded("incendium")){
					newCeiling = 192;
				}
				*///?}
			}

			if (newFloor != Substrate.floorY.get() || newCeiling != Substrate.ceilingY.get()){}
		});
	}

}
