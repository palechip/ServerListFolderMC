// part of the Server List Folder mod by palechip
// license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
// to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
// that's because it isn't allowed to release decompiled minecraft code.

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Calendar;

import net.minecraft.src.Minecraft;

public class ServerListFolder {
    // saves the opened hierarchy
    private static ArrayList<String> openedFolders;
    private static int index;
    private static ServerListFolderClipboard clipboard;
    public static ServerListFolderConfig config;
    private static boolean april1st;
    // makes the refresh button not changing to the main servers
    private static Boolean lockNextReset;

    static {
        info("Loading the mod...");
        openedFolders = new ArrayList<String>(3);
        openedFolders.add(0, "servers");
        openedFolders.add(1, "");
        openedFolders.add(2, "");
        // I don't think many people will have a deeper folder structure.
        index = 0;
        lockNextReset = false;

        clipboard = new ServerListFolderClipboard();

        config = new ServerListFolderConfig();

        Calendar cal = Calendar.getInstance();

        if(cal.get(2) == 3 && cal.get(5) == 1) {
            ServerListFolder.april1st = true;
        } else {
            ServerListFolder.april1st = false;
        }
    }

    /**
     * returns the correct folder file(FOLDER-foldername.dat) of the currently opened folder
     */
    public static String getFolderFile() {
        // if the users uses dir:servers, he will get to the main servers.
        if(openedFolders.get(index).equals("servers")) {
            return "servers.dat"; 
        } else  {
            return "FOLDER-"+ openedFolders.get(index) + ".dat";
        }
    }

    /** 
     * returns true if it converted the serverToCheck from a server without IP to one with a dir:-IP
     */
    public static Boolean checkIfNewFolder(ServerData serverToCheck) {
        if(serverToCheck.serverIP.isEmpty()) {
            serverToCheck.serverIP = "dir:"+ serverToCheck.serverName;
            serverToCheck.setHideAddress(true); //Automatically hides the adress
            return true;
        }
        return false;
    }

    /**
     * returns true if the joined server is a folder and automatically sets it to the current folder
     */
    public static Boolean checkIfFolder(ServerData serverToCheck){
        if(serverToCheck.serverIP.startsWith("dir:")) { // dir: is the prefix for all folders
            if(serverToCheck.serverIP.substring(4).equals("...")) { // dir:... is the command to get upwards in the hierarchy
                if(index <= 0) {
                    return true; // although you can't get upwards, it needs to be true to cancel the connection.
                } else {
                    index--; // go upwards
                }
            } else if(serverToCheck.serverIP.substring(4).equals("servers")) { // dir:servers changes to the main servers.
                index=0;
            } else {
                index++;
                if(openedFolders.size() <= index) { // checks if the array has already space for the folder
                    openedFolders.add(serverToCheck.serverIP.substring(4)); // the substing cuts the "dir:"
                } else {
                    openedFolders.set(index,serverToCheck.serverIP.substring(4));
                }
            }
            return true;
        }
        return false; // not a folder
    }

    /**
     *  this function adds a ... folder if there is no and makes sure that the ... folder is always the last one.
     */
    public static void manageUpwardsFolders(ServerList list) {
        if(index != 0) { // index == 0 means that you are in the main server list, so no ... server will be added
            ServerData upwardsServer = new ServerData("...", "dir:...");
            upwardsServer.setHideAddress(true);
            if(list.countServers() == 0) { // new list created?
                list.addServerData(upwardsServer);
                return;
            }

            boolean upwardServerFound = false;
            for(int c=0;c<list.countServers();c++) {
                if(list.getServerData(c).serverIP.equals("dir:...")) {
                    if(c == list.countServers()-1) { // tests for the last server in the list
                        upwardServerFound = true;
                        break;
                    } else {
                        list.removeServerData(c); // any ... folder above the last will be removed
                    }
                }
            }

            if(!upwardServerFound){
                list.addServerData(upwardsServer);
            }
        }
    }

    /**
     *  when the server list gets refreshed or reopened this function gets called
     */
    public static void resetToMainServerList() {
        // if not a refresh
        if(lockNextReset) {
            lockNextReset = false;
        } else {
            // go to the main server list
            index = 0;
        }
    }

    /**
     *  this gets only called when the list gets refreshed, so it doesn't reset to the main server
     */
    public static void lockNextReset() {
        lockNextReset = true;
    }

    public static ServerListFolderClipboard getClipboard() {
        return clipboard;
    }

    /**
     * no comment :P
     */
    public static boolean isApril1st() {
        return april1st;
    }

    /**
     * Prints a message to the console
     */
    public static void info(String message) {
        Minecraft.getMinecraft().getLogAgent().logInfo("[ServerListFolder] " + message);
    }

    /**
     * Prints a message as a warning to the console
     */
    public static void warn(String message) {
        Minecraft.getMinecraft().getLogAgent().logWarning("[ServerListFolder] " + message);
    }
}