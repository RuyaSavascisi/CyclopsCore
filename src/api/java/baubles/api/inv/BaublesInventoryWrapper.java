package baubles.api.inv;

import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaublesInventoryWrapper implements IInventory {
	
	final IBaublesItemHandler handler;	
	final PlayerEntity player;
	
	public BaublesInventoryWrapper(IBaublesItemHandler handler) {
		super();
		this.handler = handler;
		this.player = null;
	}

	public BaublesInventoryWrapper(IBaublesItemHandler handler, PlayerEntity player) {
		super();
		this.handler = handler;
		this.player = player;
	}

	@Override
	public int getSizeInventory() {
		return handler.getSlots();
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return handler.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return handler.extractItem(index, count, false);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack out = this.getStackInSlot(index);
		handler.setStackInSlot(index, ItemStack.EMPTY);
		return out;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		handler.setStackInSlot(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}
	
	@Override
	public void openInventory(PlayerEntity player) {	}

	@Override
	public void closeInventory(PlayerEntity player) {	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return handler.isItemValidForSlot(index, stack, player);
	}

	@Override
	public void clear() {	
		for (int i = 0; i < this.getSizeInventory(); ++i)
        {
			this.setInventorySlotContents(i, ItemStack.EMPTY);
        }
	}

	

}