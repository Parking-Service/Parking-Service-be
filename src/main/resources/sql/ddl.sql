CREATE TABLE BASE_USER (
    uid VARCHAR(100) PRIMARY KEY,
    email VARCHAR(50),
    logintype VARCHAR(10) NOT NULL,
    nickname VARCHAR(50) NOT NULL
);

create table PARK_DATA(
	prkplce_no VARCHAR(15) PRIMARY KEY,
    prkplce_nm VARCHAR(50),
    prkplce_se VARCHAR(3),
    prkplce_type VARCHAR(3),
    rdnmadr VARCHAR(50),
    lnmadr VARCHAR(50),
    prkcmprt VARCHAR(5),
    feeding_se VARCHAR(3),
    enforce_se VARCHAR(5),
    oper_day VARCHAR(20),
    weekday_oper_open_hhmm VARCHAR(10),
    weekday_oper_colse_hhmm VARCHAR(10),
    sat_oper_oper_open_hhmm VARCHAR(10),
    sat_oper_close_hhmm VARCHAR(10),
    holiday_oper_open_hhmm VARCHAR(10),
    holiday_close_open_hhmm VARCHAR(10),
    parkingchrge_info VARCHAR(5),
    basic_time VARCHAR(5),
    basic_charge VARCHAR(10),
    add_unit_time VARCHAR(10),
    add_unit_charge VARCHAR(10),
    day_cmmtkt_adj_time VARCHAR(10),
    day_cmmtkt VARCHAR(10),
    month_cmmtkt VARCHAR(20),
    metpay VARCHAR(80),
    spcmnt VARCHAR(20),
    institution_nm VARCHAR(20),
    phone_number VARCHAR(15),
    latitude VARCHAR(15),
    longitude VARCHAR(15),
    reference_date VARCHAR(10),
    instt_code VARCHAR(7),
    instt_nm VARCHAR(20)
);


-- Safe Mode Off
SET SQL_SAFE_UPDATES = 0;

-- Safe Mode On
SET SQL_SAFE_UPDATES = 1;