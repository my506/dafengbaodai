package com.liyang.domain.journal;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liyang.annotation.Info;
import com.liyang.domain.base.AbstractAuditorEntity;
import com.liyang.domain.base.LogRepository;
import com.liyang.domain.customer.Customer;
import com.liyang.domain.insuranceresult.InsuranceResult;
import com.liyang.domain.product.Product;
import com.liyang.domain.team.Team;
import com.liyang.enums.InsuranceType;
import com.liyang.service.AbstractAuditorService;

/**
 * @author Adam
 * @version 创建时间：2018年1月31日 下午2:21:37 类说明：customer账户流水记录
 */
@Entity
public class Journal extends AbstractAuditorEntity<JournalState, JournalAct, JournalLog> {

	private static final long serialVersionUID = 8917571540218573572L;

	@Transient
	private static AbstractAuditorService<Journal, JournalState, JournalAct, JournalLog> service;
	@Transient
	private static LogRepository<JournalLog> logRepository;

	@Override
	@JsonIgnore
	public AbstractAuditorService<Journal, JournalState, JournalAct, JournalLog> getService() {
		return service;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setService(AbstractAuditorService service) {
		Journal.service = service;
	}

	@Override
	@JsonIgnore
	public JournalLog getLogInstance() {
		return new JournalLog();
	}

	@Override
	@JsonIgnore
	public LogRepository<JournalLog> getLogRepository() {
		return logRepository;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setLogRepository(LogRepository logRepository) {
		Journal.logRepository = logRepository;
	}

	@Info(label = "账户流水类型", help = "1:车险返佣，2:团队收益，3:鼓励金，4:提现 ，...")
	private Integer type;

	// @Info(label = "返佣总金额")
	// @Column(columnDefinition = "decimal(10,2)")
	// private Double commission;
	@Info(label = "流水总金额")
	@Column(columnDefinition = "decimal(10,2)")
	private Double amount;

	@Info(label = "车船税返佣")
	@Column(columnDefinition = "decimal(10,2)")
	private Double vehicleCommission = 0.0;

	@Info(label = "车船税返佣比例")
	@Column(columnDefinition = "decimal(5,2)")
	private Double vehicleCommissionRate;

	@Info(label = "交强险返佣")
	@Column(columnDefinition = "decimal(10,2)")
	private Double compulsoryCommission = 0.0;

	@Info(label = "交强险返佣比例")
	@Column(columnDefinition = "decimal(5,2)")
	private Double compulsoryCommissionRate;

	@Info(label = "商业险返佣")
	@Column(columnDefinition = "decimal(10,2)")
	private Double commercialCommission = 0.0;

	@Info(label = "商业险返佣比例")
	@Column(columnDefinition = "decimal(5,2)")
	private Double commercialCommissionRate;

	@Info(label = "返佣比例")
	@Column(columnDefinition = "decimal(5,2)")
	private Double commissionRate;

	@Info(label = "团队返佣比例")
	@Column(columnDefinition = "decimal(5,2)")
	private Double teamCommissionRate;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "customer_id", nullable = false)
	@Info(label = "所属用户")
	private Customer customer;

	@Enumerated(EnumType.STRING)
	@Info(label = "险种类型")
	private InsuranceType insuranceType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id", nullable = false)
	@Info(label = "保险产品", help = "含 险种名称、保险公司、返佣比例")
	private Product product;

	@Info(label = "订单号")
	// @Column(nullable = false)
	private String orderId;

	@Info(label = "承保车牌")
	private String carLicense;

	@Info(label = "被保人")
	private String insuredName;

	@Info(label = "承保时间")
	private Date insureTime;

	@Info(label = "承保结果")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "journal")
	private InsuranceResult insuranceResult;

	// -----------------------------提现相关字段---------------------------------

	@Info(label = "提现拒绝理由")
	private String rejectReason;

	@Info(label = "提现审批时间")
	// private Date examineTime;
	private Date reviewTime;

	@Info(label = "财务审核操作时间")
	// private Date financeTime;
	private Date remitTime;

	@Info(label = "打款凭证", help = "多张图片逗号分割")
	private String proofImgUrl;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "team_id")
	private Team team;

	public Journal() {
		super();
	}

	/**
	 * 构造提现
	 * 
	 * @param type
	 * @param amount
	 * @param customer
	 */
	public Journal(Integer type, Double amount, Customer customer) {
		super();
		this.type = type;
		this.amount = amount;
		this.customer = customer;
	}

	public Journal(Integer type, Double vehicleCommission, Double vehicleCommissionRate, Double compulsoryCommission,
			Double compulsoryCommissionRate, Double commercialCommission, Double commercialCommissionRate,
			Double amount, Double commissionRate, Customer customer, InsuranceType insuranceType, Product product,
			String orderId, String carLicense, String insuredName, Date insureTime, String rejectReason,
			InsuranceResult insuranceResult) {
		this(type, vehicleCommission, vehicleCommissionRate, compulsoryCommission, compulsoryCommissionRate,
				commercialCommission, commercialCommissionRate, amount, commissionRate, customer, insuranceType,
				product, orderId, carLicense, insuredName, insureTime, rejectReason, insuranceResult, null);
	}

	public Journal(Integer type, Double vehicleCommission, Double vehicleCommissionRate, Double compulsoryCommission,
			Double compulsoryCommissionRate, Double commercialCommission, Double commercialCommissionRate,
			Double amount, Double commissionRate, Customer customer, InsuranceType insuranceType, Product product,
			String orderId, String carLicense, String insuredName, Date insureTime, String rejectReason,
			InsuranceResult insuranceResult, Team team) {
		super();
		this.type = type;
		this.vehicleCommission = vehicleCommission;
		this.vehicleCommissionRate = vehicleCommissionRate;
		this.compulsoryCommission = compulsoryCommission;
		this.compulsoryCommissionRate = compulsoryCommissionRate;
		this.commercialCommission = commercialCommission;
		this.commercialCommissionRate = commercialCommissionRate;
		this.amount = amount;
		this.commissionRate = commissionRate;
		this.customer = customer;
		this.insuranceType = insuranceType;
		this.product = product;
		this.orderId = orderId;
		this.carLicense = carLicense;
		this.insuredName = insuredName;
		this.insureTime = insureTime;
		this.rejectReason = rejectReason;
		this.insuranceResult = insuranceResult;
		this.team = team;
	}

	public Journal(Integer type, Double amount, Double vehicleCommission, Double vehicleCommissionRate,
			Double compulsoryCommission, Double compulsoryCommissionRate, Double commercialCommission,
			Double commercialCommissionRate, Double commissionRate, Customer customer, InsuranceType insuranceType,
			Product product, String orderId, String carLicense, String insuredName, Date insureTime,
			InsuranceResult insuranceResult, String rejectReason, Date reviewTime, Date remitTime, String proofImgUrl,
			Team team) {
		super();
		this.type = type;
		this.amount = amount;
		this.vehicleCommission = vehicleCommission;
		this.vehicleCommissionRate = vehicleCommissionRate;
		this.compulsoryCommission = compulsoryCommission;
		this.compulsoryCommissionRate = compulsoryCommissionRate;
		this.commercialCommission = commercialCommission;
		this.commercialCommissionRate = commercialCommissionRate;
		this.commissionRate = commissionRate;
		this.customer = customer;
		this.insuranceType = insuranceType;
		this.product = product;
		this.orderId = orderId;
		this.carLicense = carLicense;
		this.insuredName = insuredName;
		this.insureTime = insureTime;
		this.insuranceResult = insuranceResult;
		this.rejectReason = rejectReason;
		this.reviewTime = reviewTime;
		this.remitTime = remitTime;
		this.proofImgUrl = proofImgUrl;
		this.team = team;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getVehicleCommission() {
		return vehicleCommission;
	}

	public void setVehicleCommission(Double vehicleCommission) {
		this.vehicleCommission = vehicleCommission;
	}

	public Double getCompulsoryCommission() {
		return compulsoryCommission;
	}

	public void setCompulsoryCommission(Double compulsoryCommission) {
		this.compulsoryCommission = compulsoryCommission;
	}

	public Double getCommercialCommission() {
		return commercialCommission;
	}

	public void setCommercialCommission(Double commercialCommission) {
		this.commercialCommission = commercialCommission;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Date getInsureTime() {
		return insureTime;
	}

	public void setInsureTime(Date insureTime) {
		this.insureTime = insureTime;
	}

	public Double getVehicleCommissionRate() {
		return vehicleCommissionRate;
	}

	public void setVehicleCommissionRate(Double vehicleCommissionRate) {
		this.vehicleCommissionRate = vehicleCommissionRate;
	}

	public Double getCompulsoryCommissionRate() {
		return compulsoryCommissionRate;
	}

	public void setCompulsoryCommissionRate(Double compulsoryCommissionRate) {
		this.compulsoryCommissionRate = compulsoryCommissionRate;
	}

	public Double getCommercialCommissionRate() {
		return commercialCommissionRate;
	}

	public void setCommercialCommissionRate(Double commercialCommissionRate) {
		this.commercialCommissionRate = commercialCommissionRate;
	}

	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public InsuranceResult getInsuranceResult() {
		return insuranceResult;
	}

	public void setInsuranceResult(InsuranceResult insuranceResult) {
		this.insuranceResult = insuranceResult;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Double getTeamCommissionRate() {
		return teamCommissionRate;
	}

	public void setTeamCommissionRate(Double teamCommissionRate) {
		this.teamCommissionRate = teamCommissionRate;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Date getRemitTime() {
		return remitTime;
	}

	public void setRemitTime(Date remitTime) {
		this.remitTime = remitTime;
	}

	public String getProofImgUrl() {
		return proofImgUrl;
	}

	public void setProofImgUrl(String proofImgUrl) {
		this.proofImgUrl = proofImgUrl;
	}

}
