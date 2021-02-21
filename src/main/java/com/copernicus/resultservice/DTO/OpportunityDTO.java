package com.copernicus.resultservice.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OpportunityDTO {
    @Min(value = 1, message = "Minimum value for opportunity id is 1")
    private Integer id;
    @NotEmpty(message = "Product type is required")
    private String product;
    @Min(value = 1, message = "Minimum quantity is 1")
    @NotNull(message = "Product quantity is required")
    private Integer quantity;
    @Min(value = 1, message = "Minimum value for contact id is 1")
    @NotNull
    private Integer contactId;
    @Min(value = 1, message = "Minimum value for sales rep id is 1")
    @NotNull
    private Integer salesRepId;
    @Min(value = 1, message = "Minimum value for account id is 1")
    @NotNull
    private Integer accountId;

    private String status;

    public OpportunityDTO() {
    }

    public OpportunityDTO(@Min(value = 1, message = "Minimum value for opportunity id is 1") Integer id,
                          @NotEmpty(message = "Product type is required") String product,
                          @Min(value = 1, message = "Minimum quantity is 1")
                          @NotNull(message = "Product quantity is required") Integer quantity,
                          @Min(value = 1, message = "Minimum value for contact id is 1")
                          @NotNull Integer contactId,
                          @Min(value = 1, message = "Minimum value for sales rep id is 1")
                          @NotNull Integer salesRepId,
                          @Min(value = 1, message = "Minimum value for account id is 1")
                          @NotNull Integer accountId,
                          String status) {
        setId(id);
        setProduct(product);
        setQuantity(quantity);
        setContactId(contactId);
        setSalesRepId(salesRepId);
        setAccountId(accountId);
        setStatus(status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
