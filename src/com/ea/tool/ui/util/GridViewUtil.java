/**
 * 
 */
package com.ea.tool.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;

/**
 * @author Chika
 *
 */
public class GridViewUtil {

	public static List<File> getWSDLFiles() {

		File wsdlDirectory = new File("C:\\Users\\Chika\\workspace\\SwingDev\\iEATool\\resources\\wsdl");
		String[] extensions = new String[] { "wsdl" };
		
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".wsdl");
			}
		};
		
		List<File> files = (List<File>) FileUtils.listFiles(wsdlDirectory, extensions, true);//, TrueFileFilter.INSTANCE);

		//File[] files = f.listFiles(textFilter);
		return files;
	}

	public static DefaultTableModel getTabelModel(DefaultTableModel tableModel) {

		System.out.println("File Parsing Started.....");
		WSDLParser parser = new WSDLParser();
		Definitions defs = null;// parser.parse("C:\\Users\\Chika\\workspace\\SwingDev\\iEATool\\resources\\wsdl\\BLZService.wsdl");
		Vector ServiceDetailList = new Vector();
		for (File file : getWSDLFiles()) {
			try {
				System.out.println("Starting File : " + file.getCanonicalPath());
				defs = parser.parse(file.getCanonicalPath().toString());//new FileInputStream(file));
			} catch (IOException e) {
				System.out.println("File cannot be found n parsed....");
				System.out.println("Message : " + e.getLocalizedMessage());
			}
			
			String name = file.getName(); 
			String folderName = "";
			try {
				 folderName = file.getParentFile().getName();
			} catch (Exception e) {
				folderName = "--N/A--";
			} 
			tableModel.addRow(convertDefinitiontoTableModel(name, folderName, tableModel, defs));
			
			//break;
		}

		System.out.println("File Parse Finished.....");

		return tableModel;
	}

	public static Vector<String> convertDefinitiontoTableModel(String name, String folderName, DefaultTableModel currentTableModel, Definitions def) {// initionList){

		// currentTableModel = new DefaultTableModel();

		Vector<String> serviceDetail;
		// 2 prepare table model
		// for(Definitions def : definitionList){

		serviceDetail = new Vector<String>();
		serviceDetail.add("<Group>");
		serviceDetail.add(folderName);

		// add wsdl name
		serviceDetail.add(name+".WSDL");

		// add service name
		StringBuilder serviceListString = new StringBuilder("S: ");
		StringBuilder portListString = new StringBuilder("Port :");
		StringBuilder bindingOperationListString = new StringBuilder(" Ops : ");
		StringBuilder opsInputListString = new StringBuilder("Input : ");
		StringBuilder opsOutputListString = new StringBuilder("Output : ");

		for (Service serv : def.getServices()) {
			serviceListString.append(serv.getName() + "\n");

			// add ports
			for (Port port : serv.getPorts()) {
				try {
					portListString.append(port.getName() + "\n ");// + "\n - PBinding" + port.getBinding().getName() + "\n");

					// add operations
					for (Operation ops : port.getBinding().getPortType().getOperations()){//BindingOperation bdngOps : port.getBinding().getOperations()) {
						bindingOperationListString.append(ops.getName() + "\n");

						// add Input
//						opsInputListString.append(ops.getInput().getName()+"\n");
//						for(Part parts : ops.getInput().getMessage().getParts()){
//							parts.getElement().
//						}

						// add Output
						// opsOutputListString.append(bdngOps.getOutput().getName()+"\n");
					}
				} catch (Exception ex) {
					portListString.append("N/A");
					bindingOperationListString.append("N/A" + "\n");
				}
				
				//Find Binding Port type and get its operation & its input output
				
				 //PortType portType = port.getBinding().getPortType().getOperations();
				 
			}
		}

		serviceDetail.add(serviceListString.toString());
		serviceDetail.add(portListString.toString());
		serviceDetail.add(bindingOperationListString.toString());
		serviceDetail.add(opsInputListString.toString());
		serviceDetail.add(opsOutputListString.toString());

		// add operations
		/*
		 * for (PortType pt : defs.getPortTypes()) {
		 * System.out.println(pt.getName()); for (Operation op :
		 * pt.getOperations()) { System.out.println(" -" + op.getName()); } }
		 */
		// serviceDetail.add(def.getP);

		// add attribute elements
		// serviceDetail.add("");

		// add elements constraints
		// serviceDetail.add("");

		// 3 Add Row to tabelModel
		// currentTableModel.setColum
		// System.out.println("check params......");
		// System.out.println("serviceDetail - "+serviceDetail.toString());
		// currentTableModel.addRow(serviceDetail);
		// ServiceDetailList.add(serviceDetail);
		// currentTableModel = new
		// DefaultTableModel(ServiceDetailList,getDefaultColumn());

		// }
		return serviceDetail;// currentTableModel;
	}

	public static DefaultTableModel convertWSDLtoTableModel(Definitions definitions) {
		return null;

	}

	private static Vector getDefaultColumn() {
		// Vector columnRow = new Vector();
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Service Group");
		columnNames.add("Service Engine");
		columnNames.add("WSDL File");
		columnNames.add("Service Name");
		columnNames.add("port");
		columnNames.add("Binding Operation");
		columnNames.add("Operation Input");
		columnNames.add("Operation Output");

		// columnRow.add(columnNames);
		// {"Service Group", "Service Engine", "WSDL File", "Service
		// Name","Service Operation", "Operation Field", "Field Constrains"};

		return columnNames;
	}

}
