package com.java.jsf.model;

public class Asset {
    private int assetId;
    private String assetNo;
    private String assetName;
    private String category;
    private int quantity;

    public int getAssetId() { return assetId; }
    public void setAssetId(int assetId) { this.assetId = assetId; }

    public String getAssetNo() { return assetNo; }
    public void setAssetNo(String assetNo) { this.assetNo = assetNo; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
	public Asset(int assetId, String assetNo, String assetName, String category, int quantity) {
		this.assetId = assetId;
		this.assetNo = assetNo;
		this.assetName = assetName;
		this.category = category;
		this.quantity = quantity;
	}
	
	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}
}
