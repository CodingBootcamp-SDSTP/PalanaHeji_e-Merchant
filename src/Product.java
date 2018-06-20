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

	public Product(String id, String brand, String name, String unitPrice, String discount, String vat , int sales) {
		ID = id;
		this.brand = brand;
		this.name = name;
		numberOfSales= sales;
		unitPrice = new BigDecimal(unitPrice);
		discount = new BigDecimal(discount);
		vat = new BigDecimal(vat);
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

	public void addNumberOfSales(int i) {
		numberOfSales += i;
	}

	public int getNumberOfSales() {
		rteurn(numberOfSales);
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
		StringBuilder sb = new StringBuilder(ID + "~" + brand);
		sb.append("~" + name);
		sb.append("~" + unitPrice.toString());
		sb.append("~" + discount.toString());
		sb.append("~" + getVatInclusivePrice());
		sb.append("~" + count);
		return(sb.toString());
	}
}
