package com.optum.acoe.ApplicationFunLib;



public class BookedValue {
	
	public BookedValue(String capability, int numOfApps, float tcosum) {
		super();
		this.capability = capability;
		this.numOfApps = numOfApps;
		this.tcosum = tcosum;
	}
	
	private String capability;
	private int numOfApps;
	private float tcosum;
	
	public String getCapability() {
		return capability;
	}
	
	public int getApps() {
		return numOfApps;
	}
	
	public float getTco() {
		return tcosum;
	}
	
	@Override
	public String toString() {
		return "BookedValue [capability=" + capability + ", apps=" + numOfApps + ", tco=" + tcosum + "]";
	}
	
	public boolean compare(BookedValue b) {
		
		try {
			org.junit.Assert.assertEquals(this, b);
		}
		catch(AssertionError e) {
			return false;
		}
		return true;
	}
}
//change