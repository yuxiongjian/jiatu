package com.jt.pojo;

import hardware.config.Handphone;

import java.util.Date;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

import project.pojo.Bpojo;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.impl.BaseDaoImpl;
import android.content.Context;
import android.location.Location;
@Table(name = "t_mobilelocation")
public class MobileLocation extends Bpojo {
	/**
	 * 
	 */
	
	public static class MobileLocationImpl extends BaseDaoImpl<MobileLocation> {
		public MobileLocationImpl(Context context) {
			super(new DBHelper(context),MobileLocation.class);
		}
	}

	public static final long serialVersionUID = MobileLocation.class.hashCode();
	@Id
	@Column(name = "id" )
	protected  Integer line_id; // 主键，自增类型，可选，前台无需提供
	@Column(name = "loc_time")
	protected  Date loc_time; // 定位时间，必须
	
	@Column(name = "location_type")
	protected  String location_type; // 定位类型，必须，GPS,AGPS
	@Column(name = "latitude")
	protected  Double latitude; // 维度，必须
	@Column(name = "longitude")
	protected  Double longitude; // 经度，必须
	@Column(name = "radius")
	protected  Double radius; // 误差，必须
	@Column(name = "speed")
	protected  Double speed; // 速度，必须
	@Column(name = "direction")
	protected  Double direction; // 方向，必须
	protected  String address; // 反向地址，可选
	@Column(name = "username")
	protected  String username; // 用户名，必须
	protected  String mobile; // 电话号码，冗余字段
	@Column(name = "deviceid")
	protected  String deviceid; // 设备串号，必须
	protected  String description; // 其他描述，冗余字段
	
	protected  Date created_date;// 记录日期，可选，

	public MobileLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MobileLocation(User uo, Location lo) {
		super();
		
		if (lo == null) {
			setObjectValue();
			location_type = "GPS";
			//this.longitude=121.46587420239;
			//this.latitude=31.2685455656;
		} else {
			this.latitude = lo.getLatitude();
			this.longitude = lo.getLongitude();
			this.radius = (double) lo.getAccuracy();
			this.speed = (double) lo.getSpeed();
			this.loc_time = new Date();
			this.direction = (double) lo.getBearing();
			location_type = lo.getProvider();
		}
		
		if( uo != null ){
			this.deviceid = uo.getDeviceid();
			this.username = uo.getUsername();
		}else
			this.deviceid = Handphone.handphone.hp.deviceid;
		// TODO Auto-generated constructor stub
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public Date getLoc_time() {
		return loc_time;
	}

	public void setLoc_time(Date loc_time) {
		this.loc_time = loc_time;
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

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getDirection() {
		return direction;
	}

	public void setDirection(Double direction) {
		this.direction = direction;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Override
	public MobileLocation clone() throws CloneNotSupportedException {
		return (MobileLocation) super.clone();
	}

	

}
