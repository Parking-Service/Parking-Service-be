package jh.ParkingService.domain.parking;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Getter @Setter
@Table(name = "PARK_DATA")
@Entity
public class Park {

    @Id
    @SerializedName("prkplceNo")
    @Column(name = "prkplce_no")
    private String prkplce_no;

    @SerializedName("prkplceNm")
    @Column(name = "prkplce_nm")
    private String prkplce_nm;

    @Column(name = "prkplce_se")
    private String prkplce_se;

    @Column(name = "prkplce_type")
    private String prkplce_type;

    @Column(name = "rdnmadr")
    private String rdnmadr;

    @Column(name = "lnmadr")
    private String lnmadr;

    @Column(name = "prkcmprt")
    private String prkcmprt;

    @Column(name = "feeding_se")
    private String feeding_se;

    @Column(name = "enforce_se")
    private String enforce_se;

    @Column(name = "oper_day")
    private String oper_day;

    @Column(name = "weekday_oper_open_hhmm")
    private String weekday_oper_open_hhmm;

    @Column(name = "weekday_oper_close_hhmm")
    private String weekday_oper_close_hhmm;

    @Column(name = "sat_oper_oper_open_hhmm")
    private String sat_oper_oper_open_hhmm;

    @Column(name = "sat_oper_close_hhmm")
    private String sat_oper_close_hhmm;

    @Column(name = "holiday_oper_open_hhmm")
    private String holiday_oper_open_hhmm;

    @Column(name = "holiday_close_open_hhmm")
    private String holiday_close_open_hhmm;

    @Column(name = "parkingchrge_info")
    private String parkingchrge_info;

    @Column(name = "basic_time")
    private String basic_time;

    @Column(name = "basic_charge")
    private String basic_charge;

    @Column(name = "add_unit_time")
    private String add_unit_time;

    @Column(name = "add_unit_charge")
    private String add_unit_charge;

    @Column(name = "day_cmmtkt_adj_time")
    private String day_cmmtkt_adj_time;

    @Column(name = "day_cmmtkt")
    private String day_cmmtkt;

    @Column(name = "month_cmmtkt")
    private String month_cmmtkt;

    @Column(name = "metpay")
    private String metpay;

    @Column(name = "spcmnt")
    private String spcmnt;

    @Column(name = "institution_nm")
    private String institution_nm;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "reference_date")
    private String reference_date;

    @Column(name = "instt_code")
    private String instt_code;

    @Column(name = "instt_nm")
    private String instt_nm;
}
