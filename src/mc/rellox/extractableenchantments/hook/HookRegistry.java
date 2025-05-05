package mc.rellox.extractableenchantments.hook;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import mc.rellox.extractableenchantments.api.item.enchantment.IEnchantmentReader;
import mc.rellox.extractableenchantments.item.enchantment.EnchantmentRegistry;

public final class HookRegistry {
	
	private static final Map<String, IHook> HOOKS = new HashMap<>();
	
	public static final EconomyHook economy = new EconomyHook();
	
	static {
		HOOKS.put(economy.name(), economy);
	}
	
	public static void initialize() {
		HOOKS.values().stream()
		.filter(IHook::load)
		.peek(IHook::enable)
		.forEach(hook -> {
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "[EE] "
					+ ChatColor.DARK_BLUE + hook.name() + " has been found!");
			if(hook instanceof IEnchantmentReader reader)
				EnchantmentRegistry.submit(reader);
		});
	}

}
