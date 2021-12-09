package jh.ParkingService.repository.parking;


import jh.ParkingService.domain.parking.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            in = new FileReader(path + "\\src\\main\\java\\jh\\ParkingService\\repository\\parking\\data.csv");
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

                        park.setPrkplce_no(parkInfo[0].isEmpty() ? "" : parkInfo[0]);      //객체에 값 저장하기
                        park.setPrkplce_nm(parkInfo[1].isEmpty() ? "" : parkInfo[1]);
                        park.setPrkplce_se(parkInfo[2].isEmpty() ? "" : parkInfo[2]);
                        park.setPrkplce_type(parkInfo[3].isEmpty() ? "" : parkInfo[3]);
                        park.setRdnmadr(parkInfo[4].isEmpty() ? "" : parkInfo[4]);
                        park.setLnmadr(parkInfo[5].isEmpty() ? "" : parkInfo[5]);
                        park.setPrkcmprt(parkInfo[6].isEmpty() ? "" : parkInfo[6]);
                        park.setFeeding_se(parkInfo[7].isEmpty() ? "" : parkInfo[7]);
                        park.setEnforce_se(parkInfo[8].isEmpty() ? "" : parkInfo[8]);
                        park.setOper_day(parkInfo[9].isEmpty() ? "" : parkInfo[9]);
                        park.setWeekday_oper_open_hhmm(parkInfo[10].isEmpty() ? "" : parkInfo[10]);
                        park.setWeekday_oper_close_hhmm(parkInfo[11].isEmpty() ? "" : parkInfo[11]);
                        park.setSat_oper_oper_open_hhmm(parkInfo[12].isEmpty() ? "" : parkInfo[12]);
                        park.setSat_oper_close_hhmm(parkInfo[13].isEmpty() ? "" : parkInfo[13]);
                        park.setHoliday_oper_open_hhmm(parkInfo[14].isEmpty() ? "" : parkInfo[14]);
                        park.setHoliday_close_open_hhmm(parkInfo[15].isEmpty() ? "" : parkInfo[15]);
                        park.setParkingchrge_info(parkInfo[16].isEmpty() ? "" : parkInfo[16]);
                        park.setBasic_time(parkInfo[17].isEmpty() ? "" : parkInfo[17]);
                        park.setBasic_charge(parkInfo[18].isEmpty() ? "" : parkInfo[18]);
                        park.setAdd_unit_time(parkInfo[19].isEmpty() ? "" : parkInfo[19]);
                        park.setAdd_unit_charge(parkInfo[20].isEmpty() ? "" : parkInfo[20]);
                        park.setDay_cmmtkt_adj_time(parkInfo[21].isEmpty() ? "" : parkInfo[21]);
                        park.setDay_cmmtkt(parkInfo[22].isEmpty() ? "" : parkInfo[22]);
                        park.setMonth_cmmtkt(parkInfo[23].isEmpty() ? "" : parkInfo[23]);
                        park.setMetpay(parkInfo[24].isEmpty() ? "" : parkInfo[24]);
                        park.setSpcmnt(parkInfo[25].isEmpty() ? "" : parkInfo[25]);
                        park.setInstitution_nm(parkInfo[26].isEmpty() ? "" : parkInfo[26]);
                        park.setPhone_number(parkInfo[27].isEmpty() ? "" : parkInfo[27]);
                        park.setLatitude(parkInfo[28].isEmpty() ? "" : parkInfo[28]);
                        park.setLongitude(parkInfo[29].isEmpty() ? "" : parkInfo[29]);
                        park.setReference_date(parkInfo[30].isEmpty() ? "" : parkInfo[30]);
                        park.setInstt_code(parkInfo[31].isEmpty() ? "" : parkInfo[31]);
                        park.setInstt_nm(parkInfo[32].isEmpty() ? "" : parkInfo[32]);

                        tempLotList.add(park);   //리스트에 Park 객체 저장하기

                        try {
                            em.persist(park);
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
    public Optional<Park> findByAdrs(String address) {
        return Optional.empty();
    }

    @Override
    public List<Park> findByLocation(String lat, String lng) {

        // +-0.001 위도/경도에 있는 주차장 찾아서 리스트 형태로 리턴
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.latitude BETWEEN :latitude -0.001 AND :latitude +0.001 AND p.longitude BETWEEN :longitude -0.001 AND :longitude +0.001")
                .setParameter("latitude", Double.parseDouble(lat))
                .setParameter("longitude", Double.parseDouble(lng))
                .getResultList();

        //받은 주차장 리스트 출력
        parkList.forEach(data -> {
            System.out.println("data = " + data);
        });

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
            isDuplicate = (data.getPrkplce_no().equals(parkNum));
            if (isDuplicate) {
                return;
            }
        });
        System.out.print("parkNum = " + parkNum + ", ");
        System.out.println("isDuplicate = " + isDuplicate);
        return isDuplicate;
    }

}
