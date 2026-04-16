package com.nightwielder.bicshieldcompat;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod(BicShieldCompat.MODID)
public class BicShieldCompat
{
    public static final String MODID = "bic_shield_compat";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String BIC_MODID = "born_in_chaos_v1";

    private static final ResourceLocation SKELETON_THRASHER = new ResourceLocation(BIC_MODID, "skeleton_thrasher");
    private static final ResourceLocation DOOR_KNIGHT = new ResourceLocation(BIC_MODID, "door_knight");

    private static final TagKey<EntityType<?>> SHIELD_USERS =
            TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", "shield_users"));
    private static final TagKey<EntityType<?>> SHIELD_USERS_FALLBACK =
            TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", "entities/shield_users"));

    public BicShieldCompat(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTagsUpdated(TagsUpdatedEvent event)
    {
        if (event.getUpdateCause() != TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) return;
        if (!ModList.get().isLoaded(BIC_MODID)) return;

        injectShieldUserTags(SKELETON_THRASHER);
        injectShieldUserTags(DOOR_KNIGHT);
    }

    private static void injectShieldUserTags(ResourceLocation id)
    {
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(id);
        if (type == null)
        {
            LOGGER.warn("[bic_shield_compat] Entity type {} not found; skipping tag injection", id);
            return;
        }

        Holder.Reference<EntityType<?>> holder = type.builtInRegistryHolder();
        Set<TagKey<EntityType<?>>> merged = new HashSet<>(holder.tags().toList());
        merged.add(SHIELD_USERS);
        merged.add(SHIELD_USERS_FALLBACK);
        holder.bindTags(merged);

        LOGGER.info("[bic_shield_compat] Injected forge shield_users tags onto {}", id);
    }
}
