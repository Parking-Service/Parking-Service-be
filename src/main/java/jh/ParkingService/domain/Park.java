package jh.ParkingService.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@NoArgsConstructor
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

    @Builder
    public Park(String prkplceNo, String prkplceNm, String prkplceSe, String prkplceType, String rdnmadr, String lnmadr, String prkcmprt, String feedingSe, String enforceSe, String operDay, String weekdayOperOpenHhmm, String weekdayOperCloseHhmm, String satOperOperOpenHhmm, String satOperCloseHhmm, String holidayOperOpenHhmm, String holidayCloseOpenHhmm, String parkingchrgeInfo, String basicTime, String basicCharge, String addUnitTime, String addUnitCharge, String dayCmmtktAdjTime, String dayCmmtkt, String monthCmmtkt, String metpay, String spcmnt, String institutionNm, String phoneNumber, String latitude, String longitude, String referenceDate, String insttCode, String insttNm) {
        this.prkplceNo = prkplceNo;
        this.prkplceNm = prkplceNm;
        this.prkplceSe = prkplceSe;
        this.prkplceType = prkplceType;
        this.rdnmadr = rdnmadr;
        this.lnmadr = lnmadr;
        this.prkcmprt = prkcmprt;
        this.feedingSe = feedingSe;
        this.enforceSe = enforceSe;
        this.operDay = operDay;
        this.weekdayOperOpenHhmm = weekdayOperOpenHhmm;
        this.weekdayOperCloseHhmm = weekdayOperCloseHhmm;
        this.satOperOperOpenHhmm = satOperOperOpenHhmm;
        this.satOperCloseHhmm = satOperCloseHhmm;
        this.holidayOperOpenHhmm = holidayOperOpenHhmm;
        this.holidayCloseOpenHhmm = holidayCloseOpenHhmm;
        this.parkingchrgeInfo = parkingchrgeInfo;
        this.basicTime = basicTime;
        this.basicCharge = basicCharge;
        this.addUnitTime = addUnitTime;
        this.addUnitCharge = addUnitCharge;
        this.dayCmmtktAdjTime = dayCmmtktAdjTime;
        this.dayCmmtkt = dayCmmtkt;
        this.monthCmmtkt = monthCmmtkt;
        this.metpay = metpay;
        this.spcmnt = spcmnt;
        this.institutionNm = institutionNm;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.referenceDate = referenceDate;
        this.insttCode = insttCode;
        this.insttNm = insttNm;
    }
}
