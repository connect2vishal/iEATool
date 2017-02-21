/**
 * 
 */
package com.ea.tool.data.entity;

/**
 * @author Chika
 *
 */
public class Product{
	
	private int ProductID; 
	private String Name; 
	private String ProductNumber; 
	private String Color; 
	private double SafetyStockLevel; 
	private double ListPrice; 
	private String Size;
	
	public Product(int productID, String name, String productNumber, String color, double safetyStockLevel,
			double listPrice, String size) {
		super();
		ProductID = productID;
		Name = name;
		ProductNumber = productNumber;
		Color = color;
		SafetyStockLevel = safetyStockLevel;
		ListPrice = listPrice;
		Size = size;
	}

	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public int getProductID() {
		return ProductID;
	}


	public void setProductID(int productID) {
		ProductID = productID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getProductNumber() {
		return ProductNumber;
	}


	public void setProductNumber(String productNumber) {
		ProductNumber = productNumber;
	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	public double getSafetyStockLevel() {
		return SafetyStockLevel;
	}


	public void setSafetyStockLevel(double safetyStockLevel) {
		SafetyStockLevel = safetyStockLevel;
	}


	public double getListPrice() {
		return ListPrice;
	}


	public void setListPrice(double listPrice) {
		ListPrice = listPrice;
	}


	public String getSize() {
		return Size;
	}


	public void setSize(String size) {
		Size = size;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Color == null) {
			if (other.Color != null)
				return false;
		} else if (!Color.equals(other.Color))
			return false;
		if (Double.doubleToLongBits(ListPrice) != Double.doubleToLongBits(other.ListPrice))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (ProductID != other.ProductID)
			return false;
		if (ProductNumber == null) {
			if (other.ProductNumber != null)
				return false;
		} else if (!ProductNumber.equals(other.ProductNumber))
			return false;
		if (Double.doubleToLongBits(SafetyStockLevel) != Double.doubleToLongBits(other.SafetyStockLevel))
			return false;
		if (Size != other.Size)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Product [ProductID=" + ProductID + ", Name=" + Name + ", ProductNumber=" + ProductNumber + ", Color="
				+ Color + ", SafetyStockLevel=" + SafetyStockLevel + ", ListPrice=" + ListPrice + ", Size=" + Size
				+ "]";
	}
	
	
}
