package jh.ParkingService.domain.park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {

    @Query("SELECT p FROM Park p WHERE p.rdnmadr LIKE ?1 OR p.lnmadr LIKE ?1 OR p.prkplceNm LIKE ?1 ORDER BY abs(p.latitude - ?2) + abs(p.longitude - ?3)")
    List<Park> findByAddr(String address, String lat, String lng);  //findByAddr : 입력어가 포함된 주차장 데이터를 DB에서 추출하여 리턴

    //Ex) "경기도" 입력 시 주소에 "경기도"가 포함 된 주차장 정보 리턴
    @Query("SELECT p FROM Park p WHERE p.latitude BETWEEN ?1 - ?3 AND ?1 + ?3 AND p.longitude BETWEEN ?2 - ?3 AND ?2 + ?3")
    List<Park> findByLocation(Double lat, Double lng, Double n);  //findByLocation : 입력받은 위도(lat) 경도(lng)의 (+-n)값 사이에 있는 주차장 데이터를 DB에서 추출하여 리턴

    //findByTel : 전화번호로 주차장 검색
    List<Park> findByPhoneNumberLike(String phonenumber);

    //findAll : DB에서 모든 주차장 데이터를 추출하여 리턴
    List<Park> findAll();




















}
