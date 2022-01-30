package jh.ParkingService.repository.park;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jh.ParkingService.dto.ParkDto;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CSVParser {

    private final EntityManager em;
    boolean isDuplicate = false;

    public CSVParser(EntityManager em) {
        this.em = em;
    }


    public void read() {

        //csv파일의 절대경로 구하기
        String path = System.getProperty("user.dir");   //csv파일 path 저장
        System.out.println("path = " + path);

        //저장했던 Park 객체를 저장하는 리스트 ( 중복 검사에 사용 )
        ArrayList<ParkDto> parkList = new ArrayList<>();

        String[] parkInfo;

        try {
            //utf-8 형태로 csv 파일 파싱
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(path + "\\src\\main\\java\\jh\\ParkingService\\repository\\park\\data.csv"), "EUC-KR"));
            csvReader.readNext(); // 컬럼명은 저장되지 않도록 한 줄 읽기

            do {    //파일에서 데이터를 읽어 파싱하고 Park 객체로 만들어 ArrayList 에 넣는다.
                parkInfo = csvReader.readNext();    //한 라인 읽기 (자동으로 콤마 분리해서 배열에 저장 됌)

                if (parkInfo != null) {
                    if (parkInfo[28] == null || parkInfo[29] == null || parkInfo[28].isEmpty() || parkInfo[29].isEmpty())  //읽어온 데이터의 위도, 경도 값이 없거나 null 이면 저장하지 않고 넘김
                        continue;
                    else if (checkDuplicate(parkInfo[0], parkList))  //주차장 코드가 중복(checkDuplicate 값이 true)이면 저장하지 않고 넘김
                        continue;
                    else {  //위의 두 조건에 해당사항이 없으면 데이터를 객체에 저장 후 임시 저장 ArrayList에 삽입

                        //ParkDto 객체 생성하기
                        ParkDto parkDto = new ParkDto();
                        parkDto.setPrkplceNo(parkInfo[0]);
                        parkDto.setPrkplceNm(parkInfo[1]);
                        parkDto.setPrkplceSe(parkInfo[2]);
                        parkDto.setPrkplceType(parkInfo[3]);
                        parkDto.setRdnmadr(parkInfo[4]);
                        parkDto.setLnmadr(parkInfo[5]);
                        parkDto.setPrkcmprt(parkInfo[6]);
                        parkDto.setFeedingSe(parkInfo[7]);
                        parkDto.setPrkcmprt(parkInfo[6]);
                        parkDto.setPrkcmprt(parkInfo[6]);
                        parkDto.setEnforceSe(parkInfo[8]);
                        parkDto.setOperDay(parkInfo[9]);
                        parkDto.setWeekdayOperOpenHhmm(parkInfo[10]);
                        parkDto.setWeekdayOperCloseHhmm(parkInfo[11]);
                        parkDto.setSatOperOperOpenHhmm(parkInfo[12]);
                        parkDto.setSatOperCloseHhmm(parkInfo[13]);
                        parkDto.setHolidayOperOpenHhmm(parkInfo[14]);
                        parkDto.setHolidayCloseOpenHhmm(parkInfo[15]);
                        parkDto.setParkingchrgeInfo(parkInfo[16]);
                        parkDto.setBasicTime(parkInfo[17]);
                        parkDto.setBasicCharge(parkInfo[18]);
                        parkDto.setAddUnitTime(parkInfo[19]);
                        parkDto.setAddUnitCharge(parkInfo[20]);
                        parkDto.setDayCmmtktAdjTime(parkInfo[21]);
                        parkDto.setDayCmmtkt(parkInfo[22]);
                        parkDto.setMonthCmmtkt(parkInfo[23]);
                        parkDto.setMetpay(parkInfo[24]);
                        parkDto.setSpcmnt(parkInfo[25]);
                        parkDto.setInstitutionNm(parkInfo[26]);
                        parkDto.setPhoneNumber(parkInfo[27].replace("-", ""));
                        parkDto.setLatitude(parkInfo[28]);
                        parkDto.setLongitude(parkInfo[29]);
                        parkDto.setReferenceDate(parkInfo[30]);
                        parkDto.setInsttCode(parkInfo[31]);
                        parkDto.setInsttNm(parkInfo[32]);

                        parkList.add(parkDto);   //리스트에 Park 객체 저장하기
                        em.persist(parkDto.toEntity());   //park 객체를 DB에 INSERT
                    }
                }
            } while (parkInfo != null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (EntityExistsException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


    private boolean checkDuplicate(String prkplceNo, List<ParkDto> list) {
        for (ParkDto data : list) {
            isDuplicate = (data.getPrkplceNo().equals(prkplceNo));
            if (isDuplicate) {  //중복 발견시 반복문 종료
                break;
            }
        }
        System.out.print("prkplceNo = " + prkplceNo);
        System.out.println(" isDuplicate = " + isDuplicate);
        return isDuplicate; //중복 여부 리턴
    }

}
