/**
 * 
 */
package com.ea.tool.main;

import java.io.File;

import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.WSDLParser;

/**
 * @author Chika
 *
 */
public class EAMain {

	private String getResourceWSDL(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource("wsdl/BLZService.wsdl").getFile();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EAMain eamain = new EAMain();
		WSDLParser parser = new WSDLParser();
		 
		
		
	    Definitions defs = parser.parse(eamain.getResourceWSDL());
	    System.out.println(" resource folder "+ eamain.getResourceWSDL());
	 
	    System.out.println(defs.getServices().get(0).getPorts().get(0).getAddress());
	    
	    for (PortType pt : defs.getPortTypes()) {
	      System.out.println(pt.getName());
	      for (Operation op : pt.getOperations()) {
		        System.out.println(" -" + op.getName());
		      }
	    }
	    
	      for (Binding binding : defs.getBindings()) {
		        System.out.println(" -" + binding.getName() + " ] - [ "+ binding.getNamespaceUri());
		      }
	}

}
