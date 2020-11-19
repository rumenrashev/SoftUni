package cardealer.domain.dtos.out;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SaleDto {

    @Expose
    private CarSaleExportDto car;
    @Expose
    private String customerName;
    @Expose
    @SerializedName("Discount")
    private int discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithoutDiscount;

    public SaleDto() {
    }

    public CarSaleExportDto getCar() {
        return car;
    }

    public void setCar(CarSaleExportDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(BigDecimal priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }
}
