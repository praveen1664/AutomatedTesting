package com.optum.acoe.ApplicationFunLib;

public class ErrorData {

	
	private String businessgroupOption;
	private String assetsgroupOption;
	private String appname;
	private String colwithnullValue;
	private int page;
	private int rowindex;
	private int columnindex;
	
	public ErrorData(String businessgroupOption, String assetsgroupOption, String appname, String colwithnullValue,
			int page, int rowindex, int columnindex) {
		super();
		this.businessgroupOption = businessgroupOption;
		this.assetsgroupOption = assetsgroupOption;
		this.appname = appname;
		this.colwithnullValue = colwithnullValue;
		this.page = page;
		this.rowindex = rowindex;
		this.columnindex = columnindex;
	}
	
	@Override
	public String toString() {
		return "ErrorData [businessgroupOption=" + businessgroupOption + ", assetsgroupOption=" + assetsgroupOption
				+ ", appname=" + appname + ", colwithnullValue=" + colwithnullValue + ", page=" + page + ", rowindex="
				+ rowindex + ", columnindex=" + columnindex + "]";
	}

	public String getBusinessgroupOption() {
		return businessgroupOption;
	}

	public String getAssetsgroupOption() {
		return assetsgroupOption;
	}

	public String getAppname() {
		return appname;
	}

	public String getColwithnullValue() {
		return colwithnullValue;
	}

	public int getPage() {
		return page;
	}

	public int getRowindex() {
		return rowindex;
	}

	public int getColumnindex() {
		return columnindex;
	}
	
}
