package com.smithsmodding.smithscore.core;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * The FML Plugin for SmithsCore
 */
@IFMLLoadingPlugin.MCVersion(value = ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(value = 5000)
@IFMLLoadingPlugin.TransformerExclusions(value = "smithscore.core.")
public class SmithsCoreFMLPlugin implements IFMLLoadingPlugin
{

    public static final class TokenProvider implements IEnvironmentTokenProvider
    {

        @Override
        public int getPriority()
        {
            return IEnvironmentTokenProvider.DEFAULT_PRIORITY;
        }

        @Override
        public Integer getToken(String token, MixinEnvironment env)
        {
            if ("FORGE".equals(token))
            {
                return Integer.valueOf(ForgeVersion.getBuildVersion());
            }
            else if ("FML".equals(token))
            {
                String fmlVersion = Loader.instance().getFMLVersionString();
                int build = Integer.parseInt(fmlVersion.substring(fmlVersion.lastIndexOf('.') + 1));
                return Integer.valueOf(build);
            }
            else if ("MC_FORGE".equals(token))
            {
                return ForgeVersion.minorVersion;
            }
            return null;
        }
    }

    public SmithsCoreFMLPlugin()
    {
        InitMixin();
    }

    /**
     * Return a list of classes that implements the IClassTransformer interface
     *
     * @return a list of classes that implements the IClassTransformer interface
     */
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {};
    }

    /**
     * Return a class name that implements "ModContainer" for injection into the mod list
     * The "getName" function should return a name that other mods can, if need be,
     * depend on.
     * Trivially, this modcontainer will be loaded before all regular mod containers,
     * which means it will be forced to be "immutable" - not susceptible to normal
     * sorting behaviour.
     * All other mod behaviours are available however- this container can receive and handle
     * normal loading events
     */
    @Override
    public String getModContainerClass()
    {
        return "com.smithsmodding.smithscore.core.SmithsCoreCoreMod";
    }

    /**
     * Return the class name of an implementor of "IFMLCallHook", that will be run, in the
     * main thread, to perform any additional setup this coremod may require. It will be
     * run <strong>prior</strong> to Minecraft starting, so it CANNOT operate on minecraft
     * itself. The game will deliberately crash if this code is detected to trigger a
     * minecraft class loading
     * TODO: implement crash ;)
     */
    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    /**
     * Inject coremod data into this coremod
     * This data includes:
     * "mcLocation" : the location of the minecraft directory,
     * "coremodList" : the list of coremods
     * "coremodLocation" : the file this coremod loaded from,
     */
    @Override
    public void injectData(final Map<String, Object> data)
    {
        //No data needs to be injected.
    }

    /**
     * Return an optional access transformer class for this coremod. It will be injected post-deobf
     * so ensure your ATs conform to the new srgnames scheme.
     *
     * @return the name of an access transformer class or null if none is provided
     */
    @Override
    public String getAccessTransformerClass()
    {
        //No additional Acces Transformer.
        return null;
    }

    public static void InitMixin()
    {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.smithscore.json");
        MixinEnvironment.getDefaultEnvironment().registerTokenProviderClass(
          "com.smithsmodding.smithscore.core.SmithsCoreFMLPlugin$TokenProvider");
    }
}
