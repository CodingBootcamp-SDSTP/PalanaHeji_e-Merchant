import java.math.BigDecimal;
import java.math.MathContext;

public class Product implements DetailProvider
{
	private final String ID;
	private String brand;
	private String name;
	private int numberOfSales = 0;
	private BigDecimal unitPrice;
	private BigDecimal vat;
	private BigDecimal discount;
	private BigDecimal vatInclusivePrice = new BigDecimal("0");

	public Product(String id, String brand, String name, String up, String d, String v , int sales) {
		ID = id;
		this.brand = brand;
		this.name = name;
		numberOfSales= sales;
		unitPrice = new BigDecimal(up);
		discount = new BigDecimal(d);
		vat = new BigDecimal(v);
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

	public BigDecimal getUnitPrice() {
		return(unitPrice);
	}

	public BigDecimal getDiscount() {
		return(discount);
	}

	public void addNumberOfSales(int i) {
		numberOfSales += i;
	}

	public int getNumberOfSales() {
		return(numberOfSales);
	}

	public BigDecimal getVatInclusivePrice() {
		//TOTAL PRICE = PRICE*(1 + (VAT%/100))
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal one = new BigDecimal("1");
		hundred = vat.divide(hundred);
		one =  hundred.add(one);
		vatInclusivePrice = unitPrice.multiply(one);
		return(vatInclusivePrice);
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder(ID);
		sb.append("~" + brand);
		sb.append("~" + name);
		sb.append("~" + unitPrice.toString());
		sb.append("~" + discount.toString());
		sb.append("~" + numberOfSales);
		sb.append("~" + getVatInclusivePrice());
		return(sb.toString());
	}
}
