package cn.encmys.ykdz.forest.realthirst.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThirstinessChangeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemStack item;
    private float now;
    private boolean isCancelled;

    public ThirstinessChangeEvent(@NotNull Player player, @Nullable ItemStack item, float now) {
        this.player = player;
        this.item = item;
        this.now = now;
        this.isCancelled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public float getNow() {
        return now;
    }

    public void setNow(float now) {
        this.now = now;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
