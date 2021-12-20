package jh.ParkingService.repository.park;


import jh.ParkingService.domain.park.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Primary
@Component
public class JpaParkRepository implements ParkRepository {

    private final EntityManager em;

    @Autowired
    public JpaParkRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save() {
        //PARK_DATA 초기화
        Query q = em.createQuery("DELETE FROM Park");
        q.executeUpdate();

        CSVParser parser = new CSVParser(em);
        parser.read();
    }

    @Override
    public List<Park> findByAddr(String addr, String lat, String lng) {

        /* 소재지도로명주소, 소재지지번주소, 주차장이름 컬럼 중 입력받은 문자열(addr)을 포함하는 값이 있으면
        위도,경도 기준 가까운 거리 순으로 오름차순 정렬하여 리스트 형태로 저장 */
        String replaceAddr = addr.replace("주차장", "").trim();    //검색어에서 주차장이라는 단어 빼고 양쪽 끝에 공백 제거 Ex)"A 주차장" -> "A"
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.rdnmadr LIKE ?1 OR p.lnmadr LIKE ?1 OR p.prkplceNm LIKE ?1 ORDER BY abs(p.latitude - ?2) + abs(p.longitude - ?3)")
                .setParameter(1, '%'+replaceAddr+'%')
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
    public List<Park> findByTel(String telnum) {

        //입력받은 전화번호가 포함된 주차장 검색
        List<Park> parkList = em.createQuery("SELECT p FROM Park p WHERE p.phoneNumber LIKE ?1")
                .setParameter(1, telnum+'%')
                .getResultList();

        return parkList;    //받은 주차장 리스트 리턴
    }

    @Override
    public List<Park> findAll() {

        //테이블의 모든 주차장 정보 리스트형태로 리턴
        return em.createQuery("SELECT p FROM Park p").getResultList();
    }

}
