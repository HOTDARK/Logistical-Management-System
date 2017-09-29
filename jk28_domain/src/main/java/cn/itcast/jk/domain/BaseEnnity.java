package cn.itcast.jk.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEnnity implements Serializable {
	protected String createBy;//创建者得ID
	protected String createDept;//创建者所属的部门ID
	protected Date createTime;//创建的时间
	
	protected String updateBy;//更新着的ID
	protected Date updateTime;//更新时间
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
