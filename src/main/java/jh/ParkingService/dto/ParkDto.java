package jh.ParkingService.dto;

import jh.ParkingService.entity.Park;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ParkDto {

    public ParkDto() {
    }

    private String prkplceNo;
    private String prkplceNm;
    private String prkplceSe;
    private String prkplceType;
    private String rdnmadr;
    private String lnmadr;
    private String prkcmprt;
    private String feedingSe;
    private String enforceSe;
    private String operDay;
    private String weekdayOperOpenHhmm;
    private String weekdayOperCloseHhmm;
    private String satOperOperOpenHhmm;
    private String satOperCloseHhmm;
    private String holidayOperOpenHhmm;
    private String holidayCloseOpenHhmm;
    private String parkingchrgeInfo;
    private String basicTime;
    private String basicCharge;
    private String addUnitTime;
    private String addUnitCharge;
    private String dayCmmtktAdjTime;
    private String dayCmmtkt;
    private String monthCmmtkt;
    private String metpay;
    private String spcmnt;
    private String institutionNm;
    private String phoneNumber;
    private String latitude;
    private String longitude;
    private String referenceDate;
    private String insttCode;
    private String insttNm;




    public Park toEntity(){
        return Park.builder()
                .prkplceNo(prkplceNo)
                .prkplceNm(prkplceNm)
                .prkplceSe(prkplceSe)
                .prkplceType(prkplceType)
                .rdnmadr(rdnmadr)
                .lnmadr(lnmadr)
                .prkcmprt(prkcmprt)
                .feedingSe(feedingSe)
                .enforceSe(enforceSe)
                .operDay(operDay)
                .weekdayOperOpenHhmm(weekdayOperOpenHhmm)
                .weekdayOperCloseHhmm(weekdayOperCloseHhmm)
                .satOperOperOpenHhmm(satOperOperOpenHhmm)
                .satOperCloseHhmm(satOperCloseHhmm)
                .holidayOperOpenHhmm(holidayOperOpenHhmm)
                .holidayCloseOpenHhmm(holidayCloseOpenHhmm)
                .parkingchrgeInfo(parkingchrgeInfo)
                .basicTime(basicTime)
                .basicCharge(basicCharge)
                .dayCmmtktAdjTime(dayCmmtktAdjTime)
                .dayCmmtkt(dayCmmtkt)
                .monthCmmtkt(monthCmmtkt)
                .metpay(metpay)
                .spcmnt(spcmnt)
                .institutionNm(institutionNm)
                .phoneNumber(phoneNumber)
                .latitude(latitude)
                .longitude(longitude)
                .referenceDate(referenceDate)
                .insttCode(insttCode)
                .insttNm(insttNm)
                .build();
    }
}
