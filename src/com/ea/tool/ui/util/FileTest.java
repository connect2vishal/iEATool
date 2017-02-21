package com.ea.tool.ui.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\Chika\\workspace\\SwingDev\\iEATool\\resources\\wsdl");

		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".wsdl");
			}
		};

		File[] files = f.listFiles(textFilter);
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.print("directory:");
			} else {
				System.out.print("     file: --->>>   ");
			}
			System.out.println(file.getCanonicalPath());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			System.out.println(file.getName());
			System.out.println(file.getParent());
		}

	}
}
