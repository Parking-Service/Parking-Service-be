package jh.ParkingService.repository.park;


import jh.ParkingService.domain.park.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class JpaParkingRepository implements ParkingRepository {


    private final EntityManager em;
    boolean isDuplicate = false;

    @Autowired
    public JpaParkingRepository(EntityManager em) {
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

        ArrayList<Park> tempLotList = new ArrayList<>();

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

                    if (parkInfo[28] == null || parkInfo[29] == null || parkInfo[28].isEmpty() || parkInfo[29].isEmpty()) {
                        continue;
                    } else if (checkDuplicate(parkInfo[0], tempLotList)) {
                        continue;
                    } else {

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

                        tempLotList.add(park);   //리스트에 Park 객체 저장하기

                        try {
                            em.persist(park);
                            System.out.println("park.getPrkplceNo() = " + park.getPrkplceNo());
                        } catch (DataIntegrityViolationException e) {
                            e.printStackTrace();
                        }
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
    public List<Park> findByAdrs(String adrs, String lat, String lng) {
//        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.rdnmadr LIKE %:address% OR p.lnmadr LIKE %:address% OR p.prkplceNm LIKE %:address% ORDER BY abs(p.latitude - :latitude) + abs(p.longitude - :longitude)")
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.prkplceNm LIKE '%:address%'")
                .setParameter("address", adrs)
//                .setParameter("latitude", Double.parseDouble(lat))
//                .setParameter("longitude", Double.parseDouble(lng))
                .getResultList();

        return parkList;

    }


    @Override
    public List<Park> findByLocation(String lat, String lng) {

        // +-0.001 위도/경도에 있는 주차장 찾아서 가까운 순서대로 정렬해서 리스트 형태로 리턴
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.latitude BETWEEN :latitude -0.001 AND :latitude +0.001 AND p.longitude BETWEEN :longitude -0.001 AND :longitude +0.001")
                .setParameter("latitude", Double.parseDouble(lat))
                .setParameter("longitude", Double.parseDouble(lng))
                .getResultList();



        //받은 주차장 리스트 리턴
        return parkList;

    }


    @Override
    public List<Park> findAll() {
        return em.createQuery("SELECT p FROM Park p").getResultList();
    }

    private boolean checkDuplicate(String parkNum, List<Park> list) {
        isDuplicate = false;
        list.forEach(data -> {
            isDuplicate = (data.getPrkplceNo().equals(parkNum));
            if (isDuplicate) {
                return;
            }
        });
        System.out.print("parkNum = " + parkNum + ", ");
        System.out.println("isDuplicate = " + isDuplicate);
        return isDuplicate;
    }

}
