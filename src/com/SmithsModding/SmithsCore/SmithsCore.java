package com.SmithsModding.SmithsCore;

import com.SmithsModding.SmithsCore.Util.CoreReferences;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Orion
 * Created on 25.10.2015
 * 21:46
 * <p/>
 * Copyrighted according to Project specific license
 */

@Mod(modid = CoreReferences.General.MOD_ID, name = "SmithsCore", version = CoreReferences.General.VERSION,
        dependencies = "required-after:Forge@[10.13,)")
public class SmithsCore {

    // Instance of this mod use for internal and Forge references
    @Mod.Instance(CoreReferences.General.MOD_ID)
    public static SmithsCore instance;

    private static Logger iLogger = LogManager.getLogger("SmithsCore");

    // Proxies used to register stuff client and server side.
    //@SidedProxy(clientSide = "com.SmithsModding.Armory.Client.ArmoryClientProxy", serverSide = "com.SmithsModding.Armory.Common.ArmoryCommonProxy")
    //public static ArmoryCommonProxy proxy;

    public static final Logger getLogger() {
        return iLogger;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();

        watch.stop();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();

        watch.stop();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();

        watch.stop();
    }
}