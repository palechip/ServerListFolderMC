package net.minecraft.src;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class ServerListFolderClipboard {
	
	private ArrayList<ServerData> clipboard;
	
	public ServerListFolderClipboard(){
		clipboard = new ArrayList<ServerData>();
	}
	
	public void Copy(int selectedServer, ServerList serverList, boolean add){
		if(selectedServer >= 0 && selectedServer < serverList.countServers()){
			if(!add){//if you don't press shift, the clipboard will be cleared.
				clipboard.clear();
			}
			clipboard.add(serverList.getServerData(selectedServer));
		}
	}
	
	public void Paste(int selectedServer, ServerList serverList){
		try{
		//add the servers at the end
		if(selectedServer == -1 || selectedServer == serverList.countServers()-1){
			for(int c=0;c<clipboard.size();c++){
				serverList.addServerData(clipboard.get(c));
			}
		}
		else{
			int cacheSize = serverList.countServers() - (selectedServer +1);
			ArrayList<ServerData> cache = new ArrayList<ServerData>(cacheSize);
			//cache the servers which will be overwritten
			for(int c = selectedServer+1;c < serverList.countServers();c++){
				cache.add(serverList.getServerData(c));
			}
			int clipboardIndex = 0;
			int inListIndex = selectedServer+1;
			//insert the servers from the clipboard
			while(clipboardIndex < clipboard.size()){
				if(inListIndex < serverList.countServers()){
					serverList.setServer(inListIndex, clipboard.get(clipboardIndex++));
				}
				else{
					serverList.addServerData(clipboard.get(clipboardIndex++));
				}
				inListIndex++;
			}
			//readd the servers from the cache
			int cacheIndex = 0;
			while(cacheIndex < cache.size()){
				if(inListIndex < serverList.countServers()){
					serverList.setServer(inListIndex, cache.get(cacheIndex++));
				}
				else{
					serverList.addServerData(cache.get(cacheIndex++));
				}
				inListIndex++;
			}
			
		}
		serverList.saveServerList(); //save the list
		}catch(Exception e){
		//do nothing, but don't make Minecraft crash.
		}
	}

}
