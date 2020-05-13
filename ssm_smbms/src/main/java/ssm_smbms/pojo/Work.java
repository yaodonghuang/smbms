package ssm_smbms.pojo;

import java.util.Date;

public class Work {
	private int id;// 主键id
	private Date date;// 日期
	private String workName;// 工程名称
	private String workContent;// 工程量
	private String phone;// 工程联系电话
	private String workPeople;// 人员安排
	private String remark;// 备注
	private String ifWork;// 是否去工地

	@Override
	public String toString() {
		return "Work [id=" + id + ", date=" + date + ", workName=" + workName + ", workContent=" + workContent
				+ ", phone=" + phone + ", workPeople=" + workPeople + ", remark=" + remark + ", ifWork=" + ifWork + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkPeople() {
		return workPeople;
	}

	public void setWorkPeople(String workPeople) {
		this.workPeople = workPeople;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIfWork() {
		return ifWork;
	}

	public void setIfWork(String ifWork) {
		this.ifWork = ifWork;
	}
}
