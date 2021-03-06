package com.liyang.domain.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liyang.annotation.Info;

/**
 * 账户信息
 * 
 * @author Administrator
 *
 */
@Table(name = "account")
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 54L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	@NotNull(message = "请填写真实姓名")
	private String realName;

	// @NotNull(message="手机号码不能为空")
	// @Pattern(regexp="^1\\d{10}$",message="手机号码格式不正确")
	private String phone;

	// 打款账户平台
	// @NotNull(message="支付平台不能为空")
	// @Pattern(regexp="支付宝",message="现在支持的平台有支付宝")
	private String payAccountPlatform;

	// 打款账户号
	// @NotNull(message = "支付账号不能为空")
	private String payAccountId;

	@Info(label = "累计收益")
	@Column(columnDefinition = "decimal(10,2)")
	private double totalBalance = 0;

	@Info(label = "可提现余额")
	@Column(columnDefinition = "decimal(10,2)")
	private double balance = 0;

	@Info(label = "累计已提现金额")
	@Column(columnDefinition = "decimal(10,2)")
	private Double sumWithdrawed = 0.0;

	@Info(label = "结算中金额")
	@Column(columnDefinition = "decimal(10,2)")
	private Double sumSettling = 0.0;

	@Info(label = "累计团队收益")
	@Column(columnDefinition = "decimal(10,2)")
	private Double sumTeamProfit = 0.0;

	@Info(label = "累计奖励金")
	@Column(columnDefinition = "decimal(10,2)")
	private Double sumBounty = 0.0;

	@Info(label = "账户状态", help = "0:正常, 1:冻结")
	private int status;

	// 身份证号
	@Pattern(regexp = "^\\d{15}$|^\\d{17}[0-9Xx]$", message = "请输入正确的身份证号")
	private String identity;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	// --------------------银行卡字段---------------------------

	@Info(label = "短信验证码")
	private String verificationCode;

	@Info(label = "短信验证码失效时间")
	private Date codeExpireTime;

	@Info(label = "是否绑定银行卡")
	private Boolean bankcardBinded = false;

	@Info(label = "银行卡号")
	private String bankcardNo;

	@Info(label = "发卡行")
	private String bankName;

	@Info(label = "支行名称")
	private String subbranch;

	@Info(label = "卡种名称", secret = "e.g. ：牡丹灵通卡，保留字段，暂未使用 ")
	private String bankcardName;

	@Info(label = "银行图标")
	private String bankIcon;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPayAccountPlatform() {
		return payAccountPlatform;
	}

	public void setPayAccountPlatform(String payAccountPlatform) {
		this.payAccountPlatform = payAccountPlatform;
	}

	public String getPayAccountId() {
		return payAccountId;
	}

	public void setPayAccountId(String payAccountId) {
		this.payAccountId = payAccountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Boolean getBankcardBinded() {
		return bankcardBinded;
	}

	public void setBankcardBinded(Boolean bankcardBinded) {
		this.bankcardBinded = bankcardBinded;
	}

	public String getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSubbranch() {
		return subbranch;
	}

	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}

	public String getBankcardName() {
		return bankcardName;
	}

	public void setBankcardName(String bankcardName) {
		this.bankcardName = bankcardName;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getCodeExpireTime() {
		return codeExpireTime;
	}

	public void setCodeExpireTime(Date codeExpireTime) {
		this.codeExpireTime = codeExpireTime;
	}

	public Double getSumWithdrawed() {
		return sumWithdrawed;
	}

	public void setSumWithdrawed(Double sumWithdrawed) {
		this.sumWithdrawed = sumWithdrawed;
	}

	public Double getSumSettling() {
		return sumSettling;
	}

	public void setSumSettling(Double sumSettling) {
		this.sumSettling = sumSettling;
	}

	public Double getSumTeamProfit() {
		return sumTeamProfit;
	}

	public void setSumTeamProfit(Double sumTeamProfit) {
		this.sumTeamProfit = sumTeamProfit;
	}

	public Double getSumBounty() {
		return sumBounty;
	}

	public void setSumBounty(Double sumBounty) {
		this.sumBounty = sumBounty;
	}

}
