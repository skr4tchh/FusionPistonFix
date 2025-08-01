package fun.fusionmine.fusionpistonfix;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class FusionPistonFix extends JavaPlugin implements Listener {

    private List<String> excludedBlocks;

    public void onEnable() {
        saveDefaultConfig();
        loadConfiguration();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private void loadConfiguration() {
        this.excludedBlocks = getConfig().getStringList("excluded-blocks");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        event.setCancelled(false);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockRetract(BlockPistonRetractEvent event) {
        for (Block block : event.getBlocks()) {
            if (!excludedBlocks.contains(block.getType().toString())) {
                event.setCancelled(false);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemMove(InventoryMoveItemEvent event) {
        event.setCancelled(false);
    }

}
