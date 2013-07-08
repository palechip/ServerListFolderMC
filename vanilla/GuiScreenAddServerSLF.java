// part of the Server List Folder mod by palechip
// license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
// to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
// that's because it isn't allowed to release decompiled minecraft code.

package net.minecraft.src;

public class GuiScreenAddServerSLF extends GuiScreenAddServer {
    public GuiScreenAddServerSLF(GuiScreen par1GuiScreen,
	    ServerData par2ServerData) {
	super(par1GuiScreen, par2ServerData);
    }
    
    @Override
    public void initGui() {
	super.initGui();
        ((GuiButton)this.buttonList.get(0)).enabled = true;
    }
    
    @Override
    protected void keyTyped(char par1, int par2)
    {
	super.keyTyped(par1, par2);
        // don't disable the done button if the server IP is empty
        ((GuiButton)this.buttonList.get(0)).enabled = true;
    }
}
