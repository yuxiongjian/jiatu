package com.jt.pojo;

import java.util.Date;

import project.pojo.Bpojo;

/**
 * 
 *工单状态变化表，对应i_msg表
 */
public class WorkOrderMessage extends Bpojo {
	/**
	 * 
	 */
	protected  static final long serialVersionUID = WorkOrderMessage.class.hashCode();
	protected  java.lang.Integer sysid;	/** sysid 工单顺序号 **/
	protected  java.lang.String status;	/** MsgSts 消息状态 **/
	protected  Date updateddate;	/** sysdate 最后更新时间 **/
	

	public java.lang.String getStatus() {
		return status;
	}
	

	public java.lang.Integer getSysid() {
		return sysid;
	}

	public void setSysid(java.lang.Integer sysid) {
		this.sysid = sysid;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sysid == null) ? 0 : sysid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrderMessage other = (WorkOrderMessage) obj;
		if (sysid == null) {
			if (other.sysid != null)
				return false;
		} else if (!sysid.equals(other.sysid))
			return false;
		return true;
	}

	

	@Override
	public WorkOrderMessage clone() throws CloneNotSupportedException {

		return (WorkOrderMessage) super.clone();
	}

}
