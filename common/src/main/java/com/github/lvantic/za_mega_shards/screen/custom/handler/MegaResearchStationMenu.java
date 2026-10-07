package com.github.lvantic.za_mega_shards.screen.custom.handler;

import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.block.entity.MegaResearchStationBlockEntity;
import com.github.lvantic.za_mega_shards.item.ZAMSItems;
import com.github.lvantic.za_mega_shards.screen.ZAMSMenuTypes;
import com.github.lvantic.za_mega_shards.util.MegaStoneTierHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class MegaResearchStationMenu extends AbstractContainerMenu {

    private static final int INPUT_MENU_SLOT = 0;
    private static final int RESULT_MENU_SLOT = 1;

    private static final int PLAYER_INVENTORY_START = 2;
    private static final int PLAYER_INVENTORY_END = 29;

    private static final int HOTBAR_START = 29;
    private static final int HOTBAR_END = 38;

    private final Container inputContainer;
    private final ResultContainer resultContainer =
            new ResultContainer();

    private final ContainerLevelAccess access;

    /*
     * Client-side constructor.
     */
    public MegaResearchStationMenu(
            int containerId,
            Inventory playerInventory
    ) {
        this(
                containerId,
                playerInventory,
                new SimpleContainer(
                        MegaResearchStationBlockEntity.CONTAINER_SIZE
                ),
                ContainerLevelAccess.NULL
        );
    }

    /*
     * Server-side constructor.
     */
    public MegaResearchStationMenu(
            int containerId,
            Inventory playerInventory,
            Container inputContainer,
            ContainerLevelAccess access
    ) {
        super(
                ZAMSMenuTypes.MEGA_RESEARCH_STATION.get(),
                containerId
        );

        checkContainerSize(
                inputContainer,
                MegaResearchStationBlockEntity.CONTAINER_SIZE
        );

        this.inputContainer = inputContainer;
        this.access = access;

        /*
         * Mega Stone input slot.
         */
        this.addSlot(
                new Slot(
                        inputContainer,
                        MegaResearchStationBlockEntity.INPUT_SLOT,
                        62,
                        36
                ) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return MegaStoneTierHelper.isMegaStone(stack);
                    }

                    @Override
                    public void setChanged() {
                        super.setChanged();
                        MegaResearchStationMenu.this.updateResult();
                    }
                }
        );

        /*
         * Virtual result slot.
         *
         * The Mega Shards shown here are NOT stored
         * inside the Block Entity.
         */
        this.addSlot(
                new Slot(
                        this.resultContainer,
                        0,
                        98,
                        36
                ) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return false;
                    }

                    @Override
                    public void onTake(
                            Player player,
                            ItemStack stack
                    ) {
                        MegaResearchStationMenu.this.consumeInput();

                        super.onTake(
                                player,
                                stack
                        );
                    }
                }
        );

        /*
         * Player inventory.
         */
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(
                        new Slot(
                                playerInventory,
                                column + row * 9 + 9,
                                8 + column * 18,
                                84 + row * 18
                        )
                );
            }
        }

        /*
         * Player hotbar.
         */
        for (int column = 0; column < 9; column++) {
            this.addSlot(
                    new Slot(
                            playerInventory,
                            column,
                            8 + column * 18,
                            142
                    )
            );
        }

        /*
         * If a Mega Stone was already inside the machine
         * when the GUI opened, immediately show its result.
         */
        this.updateResult();
    }

    private void updateResult() {
        ItemStack inputStack =
                this.inputContainer.getItem(
                        MegaResearchStationBlockEntity.INPUT_SLOT
                );

        if (
                inputStack.isEmpty()
                        || !MegaStoneTierHelper.isMegaStone(inputStack)
        ) {
            this.resultContainer.setItem(
                    0,
                    ItemStack.EMPTY
            );

            this.broadcastChanges();
            return;
        }

        int shardAmount =
                MegaStoneTierHelper.getShardReturn(inputStack);

        if (shardAmount <= 0) {
            this.resultContainer.setItem(
                    0,
                    ItemStack.EMPTY
            );

            this.broadcastChanges();
            return;
        }

        this.resultContainer.setItem(
                0,
                new ItemStack(
                        ZAMSItems.MEGA_SHARD.get(),
                        shardAmount
                )
        );

        this.broadcastChanges();
    }

    private void consumeInput() {
        ItemStack inputStack =
                this.inputContainer.getItem(
                        MegaResearchStationBlockEntity.INPUT_SLOT
                );

        if (
                inputStack.isEmpty()
                        || !MegaStoneTierHelper.isMegaStone(inputStack)
        ) {
            return;
        }

        /*
         * Consume exactly one Mega Stone only when
         * the player actually takes the result.
         */
        inputStack.shrink(1);

        if (inputStack.isEmpty()) {
            this.inputContainer.setItem(
                    MegaResearchStationBlockEntity.INPUT_SLOT,
                    ItemStack.EMPTY
            );
        } else {
            this.inputContainer.setItem(
                    MegaResearchStationBlockEntity.INPUT_SLOT,
                    inputStack
            );
        }

        this.inputContainer.setChanged();

        this.access.execute((level, pos) -> {

            level.playSound(
                    null,
                    pos,
                    SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.BLOCKS,
                    0.8F,
                    1.15F
            );

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.ELECTRIC_SPARK,
                        pos.getX() + 0.5D,
                        pos.getY() + 1.0D,
                        pos.getZ() + 0.5D,
                        8,
                        0.25D,
                        0.15D,
                        0.25D,
                        0.02D
                );
            }
        });

        /*
         * If another Mega Stone remains in the stack,
         * prepare its next result.
         */
        this.updateResult();
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                this.access,
                player,
                ZAMSBlocks.MEGA_RESEARCH_STATION.get()
        );
    }

    @Override
    public ItemStack quickMoveStack(
            Player player,
            int index
    ) {
        Slot slot = this.slots.get(index);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        /*
         * Result -> Player inventory.
         */
        if (index == RESULT_MENU_SLOT) {

            /*
             * Only allow shift-clicking if the entire
             * shard payout fits into the player's inventory.
             */
            if (!this.canFullyMoveToPlayerInventory(stack)) {
                return ItemStack.EMPTY;
            }

            if (!this.moveItemStackTo(
                    stack,
                    PLAYER_INVENTORY_START,
                    HOTBAR_END,
                    true
            )) {
                return ItemStack.EMPTY;
            }

            slot.onTake(
                    player,
                    original
            );

            return original;
        }

        /*
         * Input -> Player inventory.
         */
        if (index == INPUT_MENU_SLOT) {

            if (!this.moveItemStackTo(
                    stack,
                    PLAYER_INVENTORY_START,
                    HOTBAR_END,
                    true
            )) {
                return ItemStack.EMPTY;
            }
        }

        /*
         * Player -> Mega Stone input.
         */
        else if (MegaStoneTierHelper.isMegaStone(stack)) {

            if (!this.moveItemStackTo(
                    stack,
                    INPUT_MENU_SLOT,
                    INPUT_MENU_SLOT + 1,
                    false
            )) {
                return ItemStack.EMPTY;
            }
        }

        /*
         * Main inventory -> Hotbar.
         */
        else if (
                index >= PLAYER_INVENTORY_START
                        && index < PLAYER_INVENTORY_END
        ) {

            if (!this.moveItemStackTo(
                    stack,
                    HOTBAR_START,
                    HOTBAR_END,
                    false
            )) {
                return ItemStack.EMPTY;
            }
        }

        /*
         * Hotbar -> Main inventory.
         */
        else if (
                index >= HOTBAR_START
                        && index < HOTBAR_END
        ) {

            if (!this.moveItemStackTo(
                    stack,
                    PLAYER_INVENTORY_START,
                    PLAYER_INVENTORY_END,
                    false
            )) {
                return ItemStack.EMPTY;
            }
        }

        else {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (stack.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(
                player,
                stack
        );

        return original;
    }

    private boolean canFullyMoveToPlayerInventory(
            ItemStack stack
    ) {
        int remaining = stack.getCount();

        for (
                int i = PLAYER_INVENTORY_START;
                i < HOTBAR_END;
                i++
        ) {
            Slot playerSlot = this.slots.get(i);
            ItemStack existingStack = playerSlot.getItem();

            if (existingStack.isEmpty()) {
                remaining -= Math.min(
                        stack.getMaxStackSize(),
                        remaining
                );
            }

            else if (
                    ItemStack.isSameItemSameComponents(
                            existingStack,
                            stack
                    )
            ) {
                int availableSpace =
                        existingStack.getMaxStackSize()
                                - existingStack.getCount();

                if (availableSpace > 0) {
                    remaining -= Math.min(
                            availableSpace,
                            remaining
                    );
                }
            }

            if (remaining <= 0) {
                return true;
            }
        }

        return false;
    }

    public Container getInputContainer() {
        return this.inputContainer;
    }

    public ResultContainer getResultContainer() {
        return this.resultContainer;
    }
}