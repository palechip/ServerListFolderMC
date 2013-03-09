//part of the Server List Folder mod by palechip
//license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
//to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
//that's because it isn't allowed to release decompiled minecraft code.


package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen
{
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.controlList.clear();

        if (!this.field_74024_A)
        {
            this.field_74024_A = true;
            //MODIFIED
            ServerListFolder.resetToMainServerList(); //tells it to load the main server list
            //MODIFIED END
            this.internetServerList = new ServerList(this.mc);
			//some more code in the decompiled Minecraft
    }



    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
			//more code in the decompiled Minecraft
            else if (par1GuiButton.id == 8)
            {
            	//MODIFIED
            	ServerListFolder.lockNextReset(); //stays the same folder although reinitialising
            	//MODIFIED END
                this.mc.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
            }
			//more code in the decompiled Minecraft
        }
    }

    /**
     * Join server by slot index
     */
    private void joinServer(int par1)
    {
        if (par1 < this.internetServerList.countServers())
        {
			//MODIFIED
        	ServerData serverToCheck = this.internetServerList.getServerData(par1);
        	if(ServerListFolder.checkIfNewFolder(serverToCheck)){
        		internetServerList.setServer(par1, serverToCheck);//checkIfNewFolder edited the serverToCheck
        		internetServerList.saveServerList();
        	}
        	if(ServerListFolder.checkIfFolder(serverToCheck)){
        		this.internetServerList = new ServerList(this.mc);
        		this.internetServerList.loadServerList();
				this.selectedServer = -1; //makes no server selected
                this.serverSlotContainer = new GuiSlotServer(this);//needs to be reinitialised
                if(internetServerList.countServers() == 0){ //tests for an empty = new server
                	internetServerList.saveServerList();//this will automatically add a ... folder
                }
        		return;//cancel the connection
        	}
        	//MODIFIED END
            this.connectToServer(this.internetServerList.getServerData(par1));
        }
        else
        {
			//some more code in the decompiled Minecraft
        }
    }
}
