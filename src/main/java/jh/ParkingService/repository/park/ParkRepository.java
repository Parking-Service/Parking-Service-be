package jh.ParkingService.repository.park;

import jh.ParkingService.domain.park.Park;

import java.util.List;

public interface ParkRepository {
    void save();
    List<Park> findByAddr(String address, String lat, String lng);
    List<Park> findByLocation(String lat, String lng);
    List<Park> findAll();

    //save : 주차장 csv를 끌어와서 DB에 저장

    //findByAddr : 입력어가 포함된 주차장 데이터를 DB에서 추출하여 리턴
    //Ex) "경기도" 입력 시 주소에 "경기도"가 포함 된 주차장 정보 리턴

    //findByLocation : 입력받은 위도(lat) 경도(lng)의 (+-n)값 사이에 있는 주차장 데이터를 DB에서 추출하여 리턴

    //findAll : DB에서 모든 주차장 데이터를 추출하여 리턴










}