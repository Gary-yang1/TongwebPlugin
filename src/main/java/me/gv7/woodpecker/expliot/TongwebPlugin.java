package me.gv7.woodpecker.expliot;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;

public class TongwebPlugin implements IVulPlugin {
    public static IVulPluginCallbacks vulPluginCallbacks;
    public static IPluginHelper pluginHelper;
    @Override
    public void VulPluginMain(IVulPluginCallbacks iVulPluginCallbacks) {
        vulPluginCallbacks = iVulPluginCallbacks;
        pluginHelper = vulPluginCallbacks.getPluginHelper();
        vulPluginCallbacks.setVulPluginName("TongWeb Ejb利用工具");
        vulPluginCallbacks.setVulName("TongWeb");
        vulPluginCallbacks.setVulPluginAuthor("GaryYang");
        vulPluginCallbacks.setVulPluginVersion("1.0");
        vulPluginCallbacks.setVulProduct("");
        vulPluginCallbacks.setVulSeverity("Rce");
        vulPluginCallbacks.setVulId("");
        ArrayList<IPayloadGenerator> exploits = new ArrayList<>();
        exploits.add(new TongwebAttack());
        vulPluginCallbacks.registerPayloadGenerator(exploits);
    }
}
