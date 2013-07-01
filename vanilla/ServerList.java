// part of the Server List Folder mod by palechip
// license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
// to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
// that's because it isn't allowed to release decompiled minecraft code.


package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.src.Minecraft;

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
            this.servers.clear();
            // MODIFIED
            // uses the file name the mod tells him
            NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, ServerListFolder.getFolderFile()));
            // MODIFIED END
            if (var1 == null)
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
            ServerListFolder.manageUpwardsFolders(this); // ... server stuff
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
    
    //readded (the removed this with 1.6)
    /**
     * Sets the given index in the list to the given ServerData instance.
     */
    public void setServer(int par1, ServerData par2ServerData)
    {
        this.servers.set(par1, par2ServerData);
    }
}
