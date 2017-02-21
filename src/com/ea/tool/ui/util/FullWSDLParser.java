package com.ea.tool.ui.util;

import com.predic8.schema.Appinfo;
import com.predic8.schema.Attribute;
import com.predic8.schema.ComplexContent;
import com.predic8.schema.ComplexType;
import com.predic8.schema.Derivation;
import com.predic8.schema.Documentation;
import com.predic8.schema.Element;
import com.predic8.schema.Import;
import com.predic8.schema.Include;
import com.predic8.schema.ModelGroup;
import com.predic8.schema.Schema;
import com.predic8.schema.SchemaComponent;
import com.predic8.schema.SchemaParser;
import com.predic8.schema.SimpleType;
import com.predic8.schema.restriction.BaseRestriction;
import com.predic8.schema.restriction.facet.EnumerationFacet;
import com.predic8.wsdl.AbstractSOAPBinding;
import com.predic8.wsdl.Binding;
import com.predic8.wsdl.BindingOperation;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Fault;
import com.predic8.wsdl.Message;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Part;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;

public class FullWSDLParser {
	 
    public static void main(String[] args) {
        WSDLParser parser = new WSDLParser();
 
        Definitions defs = parser.parse("C:\\Users\\Chika\\workspace\\SwingDev\\iEATool\\resources\\wsdl\\BusinessCommerce\\FinanceService.wsdl");
        //"http://www.webservicex.net/sendsmsworld.asmx?WSDL");
        //"C:\\Users\\Chika\\workspace\\SwingDev\\iEATool\\resources\\wsdl\\Weather.wsdl");
 
        out("-------------- WSDL Details --------------");
        out("TargenNamespace: \t" + defs.getTargetNamespace());
        //out("Style: \t\t\t" + defs.getStyle());
        if (defs.getDocumentation() != null) {
            out("Documentation: \t\t" + defs.getDocumentation());
        }
        out("\n");
 
        /* For detailed schema information see the FullSchemaParser.java sample.*/
        out("Schemas: ");
        for (Schema schema : defs.getSchemas()) {
            out("  TargetNamespace: \t" + schema.getTargetNamespace());
        }
        out("\n");
         
        out("Messages: ");
        for (Message msg : defs.getMessages()) {
            out("  Message Name: " + msg.getName());
            out("  Message Parts: ");
            for (Part part : msg.getParts()) {
                out("    Part Name: " + part.getName());
                out("    Part Element: " + ((part.getElement() != null) ? part.getElement() : "not available!"));
                //part.getElement().getType().get
                out("    Part Type: " + ((part.getType() != null) ? part.getType() : "not available!" ));
                out("");
            }
        }
        
        parseSchema(defs.getTypes().get(0).getSchemas().get(0));
        
        out("");
 
        out("PortTypes: ");
        for (PortType pt : defs.getPortTypes()) {
            out("  PortType Name: " + pt.getName());
            out("  PortType Operations: ");
            for (Operation op : pt.getOperations()) {
                out("    Operation Name: " + op.getName());
                out("    Operation Input Name: "
                    + ((op.getInput().getName() != null) ? op.getInput().getName() : "not available!"));
                out("    Operation Input Message: "
                    + op.getInput().getMessage().getQname());
                out("    Operation Output Name: "
                    + ((op.getOutput().getName() != null) ? op.getOutput().getName() : "not available!"));
                out("    Operation Output Message: "
                    + op.getOutput().getMessage().getQname());
                out("    Operation Faults: ");
                if (op.getFaults().size() > 0) {
                    for (Fault fault : op.getFaults()) {
                        out("      Fault Name: " + fault.getName());
                        out("      Fault Message: " + fault.getMessage().getQname());
                    }
                } else out("      There are no faults available!");
                 
            }
            out("");
        }
        out("");
 
        out("Bindings: ");
        for (Binding bnd : defs.getBindings()) {
            out("  Binding Name: " + bnd.getName());
            out("  Binding Type: " + bnd.getPortType().getName());
            out("  Binding Protocol: " + bnd.getBinding().getProtocol());
            if(bnd.getBinding() instanceof AbstractSOAPBinding) out("  Style: " + (((AbstractSOAPBinding)bnd.getBinding()).getStyle()));
            out("  Binding Operations: ");
            for (BindingOperation bop : bnd.getOperations()) {
                out("    Operation Name: " + bop.getName());
                if(bnd.getBinding() instanceof AbstractSOAPBinding) {
                    out("    Operation SoapAction: " + bop.getOperation().getSoapAction());
                    out("    SOAP Body Use: " + bop.getInput().getBindingElements().get(0).getUse());
                }
            }
            out("");
        }
        out("");
 
        out("Services: ");
        for (Service service : defs.getServices()) {
            out("  Service Name: " + service.getName());
            out("  Service Potrs: ");
            for (Port port : service.getPorts()) {
                out("    Port Name: " + port.getName());
                out("    Port Binding: " + port.getBinding().getName());
                out("    Port Address Location: " + port.getAddress().getLocation()
                    + "\n");
            }
        }
        out("");
    }
 
    private static void parseSchema(Schema schema) {
//    	SchemaParser parser = new SchemaParser();
//        Schema schema = parser.parse("samples/xsd/human-resources.xsd");
 
        out("-------------- Schema Information --------------");
        out("  Schema TargetNamespace: " + schema.getTargetNamespace());
        out("  AttributeFormDefault: " + schema.getAttributeFormDefault());
        out("  ElementFormDefault: " + schema.getElementFormDefault());
        out("");
 
        if (schema.getImports().size() > 0) {
            out("  Schema Imports: ");
            for (Import imp : schema.getImports()) {
                out("    Import Namespace: " + imp.getNamespace());
                out("    Import Location: " + imp.getSchemaLocation());
            }
            out("");
        }
 
        if (schema.getIncludes().size() > 0) {
            out("  Schema Includes: ");
            for (Include inc : schema.getIncludes()) {
                out("    Include Location: " + inc.getSchemaLocation());
            }
            out("");
        }
 
        out("  Schema Elements: ");
        for (Element e : schema.getAllElements()) {
            out("    Element Name: " + e.getName());
            if (e.getType() != null) {
                /*
                 * schema.getType() delivers a TypeDefinition (SimpleType orComplexType)
                 * object.
                 */
                out("    Element Type Name: " + schema.getType(e.getType()).getName());
                out("    Element minoccurs: " + e.getMinOccurs());
                out("    Element maxoccurs: " + e.getMaxOccurs());
                if (e.getAnnotation() != null)
                    annotationOut(e);
            }
        }
        out("");
 
        out("  Schema ComplexTypes: ");
        for (ComplexType ct : schema.getComplexTypes()) {
            out("    ComplexType Name: " + ct.getName());
            if (ct.getAnnotation() != null)
                annotationOut(ct);
            if (ct.getAttributes().size() > 0) {
                out("    ComplexType Attributes: ");
                /*
                 * If available, attributeGroup could be read as same as attribute in
                 * the following.
                 */
                for (Attribute attr : ct.getAttributes()) {
                    out("      Attribute Name: " + attr.getName());
                    out("      Attribute Form: " + attr.getForm());
                    out("      Attribute ID: " + attr.getId());
                    out("      Attribute Use: " + attr.getUse());
                    out("      Attribute FixedValue: " + attr.getFixedValue());
                    out("      Attribute DefaultValue: " + attr.getDefaultValue());
                }
            }
            /*
             * ct.getModel() delivers the child element used in complexType. In case
             * of 'sequence' you can also use the getSequence() method.
             */
            out("    ComplexType Model: " + ct.getModel().getClass().getSimpleName());
            if (ct.getModel() instanceof ModelGroup) {
                out("    Model Particles: ");
                for (SchemaComponent sc : ((ModelGroup) ct.getModel()).getParticles()) {
                    out("      Particle Kind: " + sc.getClass().getSimpleName());
                    out("      Particle Name: " + sc.getName() + "\n");
                }
            }
 
            if (ct.getModel() instanceof ComplexContent) {
                Derivation der = ((ComplexContent) ct.getModel()).getDerivation();
                out("      ComplexConten Derivation: " + der.getClass().getSimpleName());
                out("      Derivation Base: " + der.getBase());
            }
 
            if (ct.getAbstractAttr() != null)
                out("    ComplexType AbstractAttribute: " + ct.getAbstractAttr());
            if (ct.getAnyAttribute() != null)
                out("    ComplexType AnyAttribute: " + ct.getAnyAttribute());
 
            out("");
        }
 
        if (schema.getSimpleTypes().size() > 0) {
            out("  Schema SimpleTypes: ");
            for (SimpleType st : schema.getSimpleTypes()) {
                out("    SimpleType Name: " + st.getName());
                
                String enumString = "";
                for(EnumerationFacet  enumFacet : st.getRestriction().getEnumerationFacets()){
                	enumString = enumString + enumFacet.getValue() + ", ";//AsString();
                }
                out("    SimpleType Restriction: " + enumString); //st.getRestriction());
                out("    SimpleType Union: " + st.getUnion());
                out("    SimpleType List: " + st.getList());
            }
        }
    }
 
private static void annotationOut(SchemaComponent sc) {
    if (sc.getAnnotation().getAppinfos().size() > 0) {
        System.out
            .print("    Annotation (appinfos) available with the content: ");
        for (Appinfo appinfo : sc.getAnnotation().getAppinfos()) {
            out(appinfo.getContent());
        }
    } else {
        System.out
            .print("    Annotation (documentation) available with the content: ");
        for (Documentation doc : sc.getAnnotation().getDocumentations()) {
            out(doc.getContent());
        }
    }
}
	private static void out(String str) {
        System.out.println(str);
    }
}