package com.maddog.articket.venuerental.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.venuetimeslot.entity.VenueTimeSlot;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.venue.entity.Venue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "venuerental")
public class VenueRental implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueRentalID")
	private Integer venueRentalID;  // 修改為 Integer

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueID", referencedColumnName = "venueID")
	private Venue venue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "partnerID", referencedColumnName = "partnerID")
	private PartnerMember partnerMember;

	@NotEmpty(message = "活動名稱不能為空")
	@Size(max = 255, message = "活動名稱不能超過 255 個字符")
	@Column(name = "activityName", length = 255)
	private String activityName;

	@Lob
	@Column(name = "proposal")
	private byte[] proposal;

	@Column(name = "venueRentalStatus")
	private Integer venueRentalStatus;  // 修改為 Integer

	@Column(name = "venueRentalStartDate")
	private Date venueRentalStartDate;

	@Column(name = "venueRentalEndDate")
	private Date venueRentalEndDate;

	@Column(name = "venueRentalCreateTime", updatable = false, insertable = false)
	private Timestamp venueRentalCreateTime;

	@Column(name = "venueRentalCode", length = 255)
	private String venueRentalCode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueRental")
	@OrderBy("venueTimeSlotID asc")
	private Set<VenueTimeSlot> venueTimeSlots;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "venueRental")
	private Activity activity;

	public VenueRental() {
	}

	public Integer getVenueRentalID() {  // 修改為 Integer
		return venueRentalID;
	}

	public void setVenueRentalID(Integer venueRentalID) {  // 修改為 Integer
		this.venueRentalID = venueRentalID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public PartnerMember getPartnerMember() {
		return partnerMember;
	}

	public void setPartnerMember(PartnerMember partnerMember) {
		this.partnerMember = partnerMember;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public byte[] getProposal() {
		return proposal;
	}

	public void setProposal(byte[] proposal) {
		this.proposal = proposal;
	}

	public Integer getVenueRentalStatus() {  // 修改為 Integer
		return venueRentalStatus;
	}

	public void setVenueRentalStatus(Integer venueRentalStatus) {  // 修改為 Integer
		this.venueRentalStatus = venueRentalStatus;
	}

	public Date getVenueRentalStartDate() {
		return venueRentalStartDate;
	}

	public void setVenueRentalStartDate(Date venueRentalStartDate) {
		this.venueRentalStartDate = venueRentalStartDate;
	}

	public Date getVenueRentalEndDate() {
		return venueRentalEndDate;
	}

	public void setVenueRentalEndDate(Date venueRentalEndDate) {
		this.venueRentalEndDate = venueRentalEndDate;
	}

	public Timestamp getVenueRentalCreateTime() {
		return venueRentalCreateTime;
	}

	public void setVenueRentalCreateTime(Timestamp venueRentalCreateTime) {
		this.venueRentalCreateTime = venueRentalCreateTime;
	}

	public String getVenueRentalCode() {
		return venueRentalCode;
	}

	public void setVenueRentalCode(String venueRentalCode) {
		this.venueRentalCode = venueRentalCode;
	}

	public Set<VenueTimeSlot> getVenueTimeSlots() {
		return venueTimeSlots;
	}

	public void setVenueTimeSlots(Set<VenueTimeSlot> venueTimeSlots) {
		this.venueTimeSlots = venueTimeSlots;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	// 將場地租借狀態轉換為中文描述
    public String getStatusInChinese() {
        switch (this.venueRentalStatus) {
            case 0:
                return "不通過";
            case 1:
                return "通過";
            case 2:
                return "審核中";
            case 3:
                return "取消中";
            case 4:
                return "已取消";
            default:
                return "未知狀態";
        }
    }
}
