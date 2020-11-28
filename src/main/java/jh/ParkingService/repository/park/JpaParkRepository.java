package jh.ParkingService.repository.park;


import jh.ParkingService.domain.park.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JpaParkRepository implements ParkRepository {


    private final EntityManager em;
    boolean isDuplicate = false;

    @Autowired
    public JpaParkRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save() {
        //PARK_DATA 초기화
        Query q = em.createQuery("DELETE FROM Park");
        q.executeUpdate();

        //csv파일의 절대경로 구하기
        String path = System.getProperty("user.dir");   //csv파일 path 저장
        System.out.println("path = " + path);

        //저장했던 Park 객체를 저장하는 리스트 ( 중복 검사에 사용 )
        ArrayList<Park> parkList = new ArrayList<>();

        FileReader in = null;
        BufferedReader bufIn = null;
        try {

            in = new FileReader(path + "\\src\\main\\java\\jh\\ParkingService\\repository\\park\\data.csv");
            bufIn = new BufferedReader(in);
            bufIn.readLine(); // 컬럼명은 저장되지 않도록 한 줄 읽기

            String data;
            do {//파일에서 데이터를 읽어 파싱하고 Park 객체로 만들어 ArrayList에 넣는다.
                data = bufIn.readLine();  //한 라인 읽기
                if (data != null) {
                    String[] parkInfo = data.split(",");  //콤마로 분리하기


                    if (parkInfo[28] == null || parkInfo[29] == null || parkInfo[28].isEmpty() || parkInfo[29].isEmpty()) {  //읽어온 데이터의 위도, 경도 값이 없거나 null 이면 저장하지 않고 넘김
                        continue;
                    } else if (checkDuplicate(parkInfo[0], parkList)) {  //주차장 코드가 중복(checkDuplicate 값이 true)이면 저장하지 않고 넘김
                        continue;
                    } else {  //위의 두 조건에 해당사항이 없으면 데이터를 객체에 저장 후 임시 저장 ArrayList에 삽입

                        Park park = new Park();   //Park 객체 생성하기

                        park.setPrkplceNo(parkInfo[0].isEmpty() ? "" : parkInfo[0]);      //객체에 값 저장하기
                        park.setPrkplceNm(parkInfo[1].isEmpty() ? "" : parkInfo[1]);
                        park.setPrkplceSe(parkInfo[2].isEmpty() ? "" : parkInfo[2]);
                        park.setPrkplceType(parkInfo[3].isEmpty() ? "" : parkInfo[3]);
                        park.setRdnmadr(parkInfo[4].isEmpty() ? "" : parkInfo[4]);
                        park.setLnmadr(parkInfo[5].isEmpty() ? "" : parkInfo[5]);
                        park.setPrkcmprt(parkInfo[6].isEmpty() ? "" : parkInfo[6]);
                        park.setFeedingSe(parkInfo[7].isEmpty() ? "" : parkInfo[7]);
                        park.setEnforceSe(parkInfo[8].isEmpty() ? "" : parkInfo[8]);
                        park.setOperDay(parkInfo[9].isEmpty() ? "" : parkInfo[9]);
                        park.setWeekdayOperOpenHhmm(parkInfo[10].isEmpty() ? "" : parkInfo[10]);
                        park.setWeekdayOperCloseHhmm(parkInfo[11].isEmpty() ? "" : parkInfo[11]);
                        park.setSatOperOperOpenHhmm(parkInfo[12].isEmpty() ? "" : parkInfo[12]);
                        park.setSatOperCloseHhmm(parkInfo[13].isEmpty() ? "" : parkInfo[13]);
                        park.setHolidayOperOpenHhmm(parkInfo[14].isEmpty() ? "" : parkInfo[14]);
                        park.setHolidayCloseOpenHhmm(parkInfo[15].isEmpty() ? "" : parkInfo[15]);
                        park.setParkingchrgeInfo(parkInfo[16].isEmpty() ? "" : parkInfo[16]);
                        park.setBasicTime(parkInfo[17].isEmpty() ? "" : parkInfo[17]);
                        park.setBasicCharge(parkInfo[18].isEmpty() ? "" : parkInfo[18]);
                        park.setAddUnitTime(parkInfo[19].isEmpty() ? "" : parkInfo[19]);
                        park.setAddUnitCharge(parkInfo[20].isEmpty() ? "" : parkInfo[20]);
                        park.setDayCmmtktAdjTime(parkInfo[21].isEmpty() ? "" : parkInfo[21]);
                        park.setDayCmmtkt(parkInfo[22].isEmpty() ? "" : parkInfo[22]);
                        park.setMonthCmmtkt(parkInfo[23].isEmpty() ? "" : parkInfo[23]);
                        park.setMetpay(parkInfo[24].isEmpty() ? "" : parkInfo[24]);
                        park.setSpcmnt(parkInfo[25].isEmpty() ? "" : parkInfo[25]);
                        park.setInstitutionNm(parkInfo[26].isEmpty() ? "" : parkInfo[26]);
                        park.setPhoneNumber(parkInfo[27].isEmpty() ? "" : parkInfo[27]);
                        park.setLatitude(parkInfo[28].isEmpty() ? "" : parkInfo[28]);
                        park.setLongitude(parkInfo[29].isEmpty() ? "" : parkInfo[29]);
                        park.setReferenceDate(parkInfo[30].isEmpty() ? "" : parkInfo[30]);
                        park.setInsttCode(parkInfo[31].isEmpty() ? "" : parkInfo[31]);
                        park.setInsttNm(parkInfo[32].isEmpty() ? "" : parkInfo[32]);

                        parkList.add(park);   //리스트에 Park 객체 저장하기

                        em.persist(park);   //park 객체를 DB에 INSERT
                    }
                }
            } while (data != null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (EntityExistsException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
            try {
                bufIn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Park> findByAddr(String addr, String lat, String lng) {

        /* 소재지도로명주소, 소재지지번주소, 주차장이름 컬럼 중 입력받은 문자열(addr)을 포함하는 값이 있으면
        위도,경도 기준 가까운 거리 순으로 오름차순 정렬하여 리스트 형태로 저장 */
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.rdnmadr LIKE ?1 OR p.lnmadr LIKE ?1 OR p.prkplceNm LIKE ?1 ORDER BY abs(p.latitude - ?2) + abs(p.longitude - ?3)")
                .setParameter(1, '%'+addr+'%')
                .setParameter(2, lat)
                .setParameter(3, lng)
                .getResultList();

        return parkList;    //받은 주차장 리스트 리턴
    }


    @Override
    public List<Park> findByLocation(String lat, String lng) {

        Double n = 0.02;
        // +-n 위도/경도에 있는 주차장 찾아서 리스트 형태 저장
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.latitude BETWEEN ?1 - ?3 AND ?1 + ?3 AND p.longitude BETWEEN ?2 - ?3 AND ?2 + ?3")
                .setParameter(1, Double.parseDouble(lat))
                .setParameter(2, Double.parseDouble(lng))
                .setParameter(3, n)
                .getResultList();

        return parkList;    //받은 주차장 리스트 리턴
    }


    @Override
    public List<Park> findAll() {

        //테이블의 모든 주차장 정보 리스트형태로 리턴
        return em.createQuery("SELECT p FROM Park p").getResultList();
    }

    private boolean checkDuplicate(String prkplceNo, List<Park> list) {
        isDuplicate = false;    //중복 여부
        list.forEach(data -> {  //list에 저장된 객체중 주차장 코드(prkplceNo) 중복이 있는지 검사 (중복이면 true 없으면 false 리턴)
            isDuplicate = (data.getPrkplceNo().equals(prkplceNo));
            if (isDuplicate) {  //중복 발견시 반복문 종료
                return;
            }
        });
        System.out.print("prkplceNo = " + prkplceNo);
        System.out.println(" isDuplicate = " + isDuplicate);
        return isDuplicate; //중복 여부 리턴
    }

}
