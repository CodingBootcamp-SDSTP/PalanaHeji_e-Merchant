import java.math.BigDecimal;

public class Product
{
	private static int count = 0;
	private final String ID;
	private String brand;
	private String name;
	private BigDecimal unitPrice;
	private BigDecimal discount = new BigDecimal("0");

	public Product(String brand, String name, BigDecimal unitPrice, BigDecimal discount) {
		ID = "Product#" + ++count;
		this.brand = brand;
		this.name = name;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}

	public String getID() {
		return(ID);
	}

	public void setBrand(String b) {
		brand = b;
	}

	public void setName(String n) {
		name = n;
	}

	public void setUnitPrice(BigDecimal p) {
		unitPrice = p;
	}

	public void setDiscount(BigDecimal d) {
		discount = d;
	}

	public String getBrand() {
		return(brand);
	}

	public String getName() {
		return(name);
	}

	public BigDecimal getunitPrice() {
		return(unitPrice);
	}

	public BigDecimal getDiscount() {
		return(discount);
	}

	public BigDecimal getDiscountedPrice() {
		BigDecimal d = unitPrice.multiply(discount.divide(new BigDecimal("100")));
		return(unitPrice.subtract(d));
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder(ID + "~" + brand);
		sb.append("~" + name);
		sb.append("~" + unitPrice);
		sb.append("~" + discount);
		sb.append("~" + getDiscountedPrice());
		sb.append("~" + count);
		return(sb.toString());
	}
}
