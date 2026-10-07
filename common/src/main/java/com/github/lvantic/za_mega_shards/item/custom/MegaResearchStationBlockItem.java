package com.github.lvantic.za_mega_shards.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MegaResearchStationBlockItem extends BlockItem {

    public MegaResearchStationBlockItem(
            Block block,
            Item.Properties properties
    ) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            Item.TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ) {
        tooltipComponents.add(
                Component.translatable(
                        "tooltip.za_mega_shards.mega_research_station.1"
                ).withStyle(ChatFormatting.GRAY)
        );

        tooltipComponents.add(
                Component.translatable(
                        "tooltip.za_mega_shards.mega_research_station.2"
                ).withStyle(ChatFormatting.GRAY)
        );

        super.appendHoverText(
                stack,
                context,
                tooltipComponents,
                tooltipFlag
        );
    }
}