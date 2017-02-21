/**
 * 
 */
package com.ea.tool.ui.constants;

/**
 * @author Chika
 *
 */
public enum Screen {
		
	SoruceManager("Source Manager", "Source Manager is a screent to set the source of the WSDL. ITcan be a file path or datasource");

	private String title;
	private String desc;

	Screen(String title, String desc) {
		this.setTitle(title);
		this.setDesc(desc);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
