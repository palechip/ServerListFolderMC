//part of the Server List Folder mod by palechip
//license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
//to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
//that's because it isn't allowed to release decompiled minecraft code.


package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class ServerList
{

    /**
     * Loads a list of servers from servers.dat, by running ServerData.getServerDataFromNBTCompound on each NBT compound
     * found in the "servers" tag list.
     */
    public void loadServerList()
    {
        try
        {
            // MODIFIED
            // uses the file name the mod tells him
            NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, ServerListFolder.getFolderFile()));
            // MODIFIED END
            NBTTagList var2 = var1.getTagList("servers");
            // some more code in the decompiled Minecraft
        }
        catch (Exception var4)
        {
        }
    }

    /**
     * Runs getNBTCompound on each ServerData instance, puts everything into a "servers" NBT list and writes it to
     * servers.dat.
     */
    public void saveServerList()
    {
        try
        {
            // MODIFIED
            ServerListFolder.ManageUpwardsFolders(this); // ... server stuff
            // MODIFIED END
        	
            NBTTagList var1 = new NBTTagList();
            //some more code in the decompiled Minecraft
            var5.setTag("servers", var1);
            // CHANGED
            // uses the file name the mod tells him. 
            CompressedStreamTools.safeWrite(var5, new File(this.mc.mcDataDir, ServerListFolder.getFolderFile()));
            // CHANGED END
        }
        catch (Exception var4)
        {
        }
    }
}
