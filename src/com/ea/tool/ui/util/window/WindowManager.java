package com.ea.tool.ui.util.window;

import java.util.Map;

import javax.swing.JPanel;


public abstract class WindowManager {

	private Map<WindowName, JPanel> singleInstanceWindowMap;
	private Map<WindowName, JPanel> multiInstanceWindowMap;
	
	public abstract void addWindow(JPanel window);

	public Map<WindowName, JPanel> getSingleInstanceWindowMap() {
		return singleInstanceWindowMap;
	}

	public void setSingleInstanceWindowMap(Map<WindowName, JPanel> singleInstanceWindowMap) {
		this.singleInstanceWindowMap = singleInstanceWindowMap;
	}

	public Map<WindowName, JPanel> getMultiInstanceWindowMap() {
		return multiInstanceWindowMap;
	}

	public void setMultiInstanceWindowMap(Map<WindowName, JPanel> multiInstanceWindowMap) {
		this.multiInstanceWindowMap = multiInstanceWindowMap;
	}
	
	
}
