package jh.ParkingService.domain.park;

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
    @Column(name = "prkplceNo")
    private String prkplceNo;

    @Column(name = "prkplceNm")
    private String prkplceNm;

    @Column(name = "prkplceSe")
    private String prkplceSe;

    @Column(name = "prkplceType")
    private String prkplceType;

    @Column(name = "rdnmadr")
    private String rdnmadr;

    @Column(name = "lnmadr")
    private String lnmadr;

    @Column(name = "prkcmprt")
    private String prkcmprt;

    @Column(name = "feedingSe")
    private String feedingSe;

    @Column(name = "enforceSe")
    private String enforceSe;

    @Column(name = "operDay")
    private String operDay;

    @Column(name = "weekdayOperOpenHhmm")
    private String weekdayOperOpenHhmm;

    @Column(name = "weekdayOperCloseHhmm")
    private String weekdayOperCloseHhmm;

    @Column(name = "satOperOperOpenHhmm")
    private String satOperOperOpenHhmm;

    @Column(name = "satOperCloseHhmm")
    private String satOperCloseHhmm;

    @Column(name = "holidayOperOpenHhmm")
    private String holidayOperOpenHhmm;

    @Column(name = "holidayCloseOpenHhmm")
    private String holidayCloseOpenHhmm;

    @Column(name = "parkingchrgeInfo")
    private String parkingchrgeInfo;

    @Column(name = "basicTime")
    private String basicTime;

    @Column(name = "basicCharge")
    private String basicCharge;

    @Column(name = "addUnitTime")
    private String addUnitTime;

    @Column(name = "addUnitCharge")
    private String addUnitCharge;

    @Column(name = "dayCmmtktAdjTime")
    private String dayCmmtktAdjTime;

    @Column(name = "dayCmmtkt")
    private String dayCmmtkt;

    @Column(name = "monthCmmtkt")
    private String monthCmmtkt;

    @Column(name = "metpay")
    private String metpay;

    @Column(name = "spcmnt")
    private String spcmnt;

    @Column(name = "institutionNm")
    private String institutionNm;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "referenceDate")
    private String referenceDate;

    @Column(name = "insttCode")
    private String insttCode;

    @Column(name = "insttNm")
    private String insttNm;
}
