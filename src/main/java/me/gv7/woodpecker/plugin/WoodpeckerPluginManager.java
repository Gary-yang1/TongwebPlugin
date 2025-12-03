package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.expliot.TongwebPlugin;

public class WoodpeckerPluginManager implements IPluginManager{
    @Override
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks iPluginManagerCallbacks) {
        final TongwebPlugin ruoyiRcePlugin = new TongwebPlugin();
        iPluginManagerCallbacks.registerVulPlugin(ruoyiRcePlugin);
    }
}
