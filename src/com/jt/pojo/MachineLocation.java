package com.jt.pojo;

import java.util.Date;

import project.pojo.Bpojo;
import android.location.Location;

public class MachineLocation extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = MachineLocation.class.hashCode();
	protected Integer machineid;// 机器ID,必须
	protected String location_type;
	protected String barcode; // 机器条码，必须
	protected Double latitude; // 维度，必须
	protected Double longitude; // 经度，必须
	protected Double radius; // 误差，必须

	protected Date loc_time; // 定位时间，必须
	protected String updatedby;
	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	

	public Integer getMachine_id() {
		return machineid;
	}

	public void setMachine_id(Integer machine_id) {
		this.machineid = machine_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Date getLoc_time() {
		return loc_time;
	}

	public void setLoc_time(Date loc_time) {
		this.loc_time = loc_time;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	
	@Override
	public MachineLocation clone() throws CloneNotSupportedException {
		return (MachineLocation) super.clone();
	}

	public void setLocaton(Location ll) {
        if( ll == null)
        	return;
		this.latitude = ll.getLatitude();
		this.longitude = ll.getLongitude();
		this.radius = (double) ll.getBearing();
		this.location_type = ll.getProvider();
		// TODO Auto-generated method stub

	}

}
