package com.devsuperior.dsmeta.dto;

public class SaleSummaryDTO {
    private String sellerName;
    private Double totalSales;

    public SaleSummaryDTO() {
    }

    // Constructor using fields (for JPQL query)
    public SaleSummaryDTO(String sellerName, Double totalSales) {
        this.sellerName = sellerName;
        this.totalSales = totalSales;
    }

    // Getters and Setters
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    
    @Override
    public String toString() {
        return "SummarySaleDTO [sellerName=" + sellerName + ", totalSales=" + totalSales + "]";
    }
}