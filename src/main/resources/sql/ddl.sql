CREATE TABLE BASE_USER (
    uid VARCHAR(100) PRIMARY KEY,
    email VARCHAR(50),
    logintype VARCHAR(10) NOT NULL,
    nickname VARCHAR(50) NOT NULL
);

create table PARK_DATA(
	prkplceNo VARCHAR(100) primary key,
    prkplceNm VARCHAR(100),
    prkplceSe VARCHAR(100),
    prkplceType VARCHAR(100),
    rdnmadr VARCHAR(100),
    lnmadr VARCHAR(100),
    prkcmprt VARCHAR(100),
    feedingSe VARCHAR(100),
    enforceSe VARCHAR(100),
    operDay VARCHAR(100),
    weekdayOperOpenHhmm VARCHAR(100),
    weekdayOperCloseHhmm VARCHAR(100),
    satOperOperOpenHhmm VARCHAR(100),
    satOperCloseHhmm VARCHAR(100),
    holidayOperOpenHhmm VARCHAR(100),
    holidayCloseOpenHhmm VARCHAR(100),
    parkingchrgeInfo VARCHAR(100),
    basicTime VARCHAR(100),
    basicCharge VARCHAR(100),
    addUnitTime VARCHAR(100),
    addUnitCharge VARCHAR(100),
    dayCmmtktAdjTime VARCHAR(100),
    dayCmmtkt VARCHAR(100),
    monthCmmtkt VARCHAR(100),
    metpay VARCHAR(100),
    spcmnt VARCHAR(255),
    institutionNm VARCHAR(200),
    phoneNumber VARCHAR(200),
    latitude LONG,
    longitude LONG,
    referenceDate VARCHAR(100),
    insttCode VARCHAR(100),
    insttNm VARCHAR(100)
);


-- Safe Mode Off
SET SQL_SAFE_UPDATES = 0;

-- Safe Mode On
SET SQL_SAFE_UPDATES = 1;