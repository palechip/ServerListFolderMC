//part of the Server List Folder mod by palechip
//license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
//to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
//that's because it isn't allowed to release decompiled minecraft code.


package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiScreenAddServer extends GuiScreen
{
    // more code in the decopmiled Minecraft
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        // some more code in the decompiled Minecraft
        this.serverAddress.setText(this.newServerData.serverIP);
        // MODIFIED
        // don't disable the done button if the server IP is empty
        ((GuiButton)this.buttonList.get(0)).enabled = /*this.serverAddress.getText().length() > 0 &&*/ this.serverAddress.getText().split(":").length > 0 && this.serverName.getText().length() > 0;
        // MODIFIED END
    }


    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        // some more code in the decompiled minecraft
        if (par1 == 13)
        {
            this.actionPerformed((GuiButton)this.controlList.get(0));
        }

        // MODIFIED
        // don't disable the done button if the server IP is empty
        ((GuiButton)this.buttonList.get(0)).enabled = /*this.serverAddress.getText().length() > 0 &&*/ this.serverAddress.getText().split(":").length > 0 && this.serverName.getText().length() > 0;
        // MODIFIED END
    }

}
