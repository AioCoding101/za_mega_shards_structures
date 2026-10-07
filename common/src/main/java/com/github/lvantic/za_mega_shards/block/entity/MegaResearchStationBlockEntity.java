package com.github.lvantic.za_mega_shards.block.entity;

import com.github.lvantic.za_mega_shards.block.ZAMSBlockEntities;
import com.github.lvantic.za_mega_shards.screen.custom.handler.MegaResearchStationMenu;
import com.github.lvantic.za_mega_shards.util.MegaStoneTierHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MegaResearchStationBlockEntity extends BaseContainerBlockEntity {

    public static final int INPUT_SLOT = 0;
    public static final int CONTAINER_SIZE = 1;

    private NonNullList<ItemStack> items =
            NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public MegaResearchStationBlockEntity(BlockPos pos, BlockState state) {
        super(
                ZAMSBlockEntities.MEGA_RESEARCH_STATION.get(),
                pos,
                state
        );
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == INPUT_SLOT
                && MegaStoneTierHelper.isMegaStone(stack);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(
                "container.za_mega_shards.mega_research_station"
        );
    }

    @Override
    protected @Nullable AbstractContainerMenu createMenu(
            int containerId,
            Inventory inventory
    ) {
        Level level = this.getLevel();

        if (level == null) {
            return null;
        }

        return new MegaResearchStationMenu(
                containerId,
                inventory,
                this,
                ContainerLevelAccess.create(
                        level,
                        this.getBlockPos()
                )
        );
    }

    @Override
    protected void loadAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.loadAdditional(tag, registries);

        this.items = NonNullList.withSize(
                CONTAINER_SIZE,
                ItemStack.EMPTY
        );

        ContainerHelper.loadAllItems(
                tag,
                this.items,
                registries
        );
    }

    @Override
    protected void saveAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.saveAdditional(tag, registries);

        ContainerHelper.saveAllItems(
                tag,
                this.items,
                registries
        );
    }
}