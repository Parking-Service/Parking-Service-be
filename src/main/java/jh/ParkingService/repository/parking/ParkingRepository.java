package jh.ParkingService.repository.parking;

import jh.ParkingService.domain.parking.Park;

import java.util.List;
import java.util.Optional;

public interface ParkingRepository {
    void save();
    Optional<Park> findByAdrs(String address);
    List<Park> findByLocation(String lat, String lng);
    List<Park> findAll();

    //1.save:주차장 csv끌어와서 저장
    //2.findByLocation:주소로 검색
    //3.findByWG:위도 경도로 검색
    //6.findAll: 전부 검색











}
