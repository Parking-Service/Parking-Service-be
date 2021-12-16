package jh.ParkingService.repository.park;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jh.ParkingService.domain.park.Park;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {

    private final EntityManager em;
    boolean isDuplicate = false;

    public CsvParser(EntityManager em) {
        this.em = em;
    }


    public void read() {

        //csv파일의 절대경로 구하기
        String path = System.getProperty("user.dir");   //csv파일 path 저장
        System.out.println("path = " + path);

        //저장했던 Park 객체를 저장하는 리스트 ( 중복 검사에 사용 )
        ArrayList<Park> parkList = new ArrayList<>();

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
                        Park park = new Park();   //Park 객체 생성하기

                        park.setPrkplceNo(parkInfo[0]);      //객체에 값 저장하기
                        park.setPrkplceNm(parkInfo[1]);
                        park.setPrkplceSe(parkInfo[2]);
                        park.setPrkplceType(parkInfo[3]);
                        park.setRdnmadr(parkInfo[4]);
                        park.setLnmadr(parkInfo[5]);
                        park.setPrkcmprt(parkInfo[6]);
                        park.setFeedingSe(parkInfo[7]);
                        park.setEnforceSe(parkInfo[8]);
                        park.setOperDay(parkInfo[9]);
                        park.setWeekdayOperOpenHhmm(parkInfo[10]);
                        park.setWeekdayOperCloseHhmm(parkInfo[11]);
                        park.setSatOperOperOpenHhmm(parkInfo[12]);
                        park.setSatOperCloseHhmm(parkInfo[13]);
                        park.setHolidayOperOpenHhmm(parkInfo[14]);
                        park.setHolidayCloseOpenHhmm(parkInfo[15]);
                        park.setParkingchrgeInfo(parkInfo[16]);
                        park.setBasicTime(parkInfo[17]);
                        park.setBasicCharge(parkInfo[18]);
                        park.setAddUnitTime(parkInfo[19]);
                        park.setAddUnitCharge(parkInfo[20]);
                        park.setDayCmmtktAdjTime(parkInfo[21]);
                        park.setDayCmmtkt(parkInfo[22]);
                        park.setMonthCmmtkt(parkInfo[23]);
                        park.setMetpay(parkInfo[24]);
                        park.setSpcmnt(parkInfo[25]);
                        park.setInstitutionNm(parkInfo[26]);
                        park.setPhoneNumber(parkInfo[27].replace("-", ""));
                        park.setLatitude(parkInfo[28]);
                        park.setLongitude(parkInfo[29]);
                        park.setReferenceDate(parkInfo[30]);
                        park.setInsttCode(parkInfo[31]);
                        park.setInsttNm(parkInfo[32]);

                        parkList.add(park);   //리스트에 Park 객체 저장하기
                        em.persist(park);   //park 객체를 DB에 INSERT
                    }
                }
            } while (parkInfo != null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (EntityExistsException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


    private boolean checkDuplicate(String prkplceNo, List<Park> list) {
        for (Park data : list) {
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
