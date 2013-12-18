package com.liu.sqlite;

public class Perinformation {
	private String name;
	private String phone;
	private String QQ;
	private String weixin;
	private String microblog;
	private String maillbox;
	private String job;
	private String addresslistgroup;
	private String sex;
	private String address;
	private String remarks;
	private byte[] headimage;
	private byte[] qrcodeimage;
	private int isqrcodepro;

	public Perinformation(String names, String phones, String QQs,
			String weixins, String microblogs, String maillboxs, String jobs,
			String addresslistgroups, String sexs, String addresss,
			String remarkss, byte[] headimages) {

		this.name = names;
		this.phone = phones;
		if (QQs.equals("Œ¥…Ë÷√"))
			this.QQ = null;
		else
			this.QQ = QQs;
		if (weixins.equals("Œ¥…Ë÷√"))
			this.weixin = null;
		else
			this.weixin = weixins;
		if (microblogs.equals("Œ¥…Ë÷√"))
			this.microblog = null;
		else
			this.microblog = microblogs;
		if (maillboxs.equals("Œ¥…Ë÷√"))
			this.maillbox = null;
		else
			this.maillbox = maillboxs;
		if (jobs.equals("Œ¥…Ë÷√"))
			this.job = null;
		else
			this.job = jobs;
		if (addresslistgroups.equals("Œ¥…Ë÷√"))
			this.addresslistgroup = null;
		else
			this.addresslistgroup = addresslistgroups;
		if (sexs.equals("Œ¥…Ë÷√"))
			this.sex = null;
		else
			this.sex = sexs;
		if (addresss.equals("Œ¥…Ë÷√"))
			this.address = null;
		else
			this.address = addresss;
		if (remarkss.equals("Œ¥…Ë÷√"))
			this.remarks = null;
		else
			this.remarks = remarkss;
		if (QQs.equals("Œ¥…Ë÷√"))
			this.QQ = null;
		else
			this.QQ = QQs;

		this.headimage = headimages;

	}

	public Perinformation(String names, byte[] qrcodeimages, int isqrcodepros) {
		name = names;
		qrcodeimage = qrcodeimages;
		isqrcodepro = isqrcodepros;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getQQ() {
		return QQ;
	}

	public String getWeixin() {
		return weixin;
	}

	public String getMicroblog() {
		return microblog;
	}

	public String getMaillbox() {
		return maillbox;
	}

	public String getJob() {
		return job;
	}

	public String getAddresslistgroup() {
		return addresslistgroup;
	}

	public String getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public String getRemarks() {
		return remarks;
	}

	public byte[] getHeadimage() {
		return headimage;
	}

	public byte[] getQrcodeimage() {
		return qrcodeimage;
	}

	public int getIsqrcodepro() {
		return isqrcodepro;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}

	public void setMaillbox(String maillbox) {
		this.maillbox = maillbox;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setAddresslistgroup(String addresslistgroup) {
		this.addresslistgroup = addresslistgroup;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setHeadimage(byte[] headimage) {
		this.headimage = headimage;
	}

	public void setQrcodeimage(byte[] qrcodeimage) {
		this.qrcodeimage = qrcodeimage;
	}

	public void setIsqrcodepro(int isqrcodepro) {
		this.isqrcodepro = isqrcodepro;
	}

}
