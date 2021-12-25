CREATE TABLE BASE_USER (
    uid VARCHAR(100) PRIMARY KEY,
    email VARCHAR(50),
    logintype VARCHAR(10) NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL
);

create table PARK_DATA(
	prkplceNo VARCHAR(200) primary key,
    prkplceNm VARCHAR(200),
    prkplceSe VARCHAR(200),
    prkplceType VARCHAR(200),
    rdnmadr VARCHAR(200),
    lnmadr VARCHAR(200),
    prkcmprt VARCHAR(200),
    feedingSe VARCHAR(200),
    enforceSe VARCHAR(200),
    operDay VARCHAR(200),
    weekdayOperOpenHhmm VARCHAR(200),
    weekdayOperCloseHhmm VARCHAR(200),
    satOperOperOpenHhmm VARCHAR(200),
    satOperCloseHhmm VARCHAR(200),
    holidayOperOpenHhmm VARCHAR(200),
    holidayCloseOpenHhmm VARCHAR(200),
    parkingchrgeInfo VARCHAR(200),
    basicTime VARCHAR(200),
    basicCharge VARCHAR(200),
    addUnitTime VARCHAR(200),
    addUnitCharge VARCHAR(200),
    dayCmmtktAdjTime VARCHAR(200),
    dayCmmtkt VARCHAR(200),
    monthCmmtkt VARCHAR(200),
    metpay VARCHAR(200),
    spcmnt VARCHAR(255),
    institutionNm VARCHAR(200),
    phoneNumber VARCHAR(200),
    latitude LONG,
    longitude LONG,
    referenceDate VARCHAR(200),
    insttCode VARCHAR(200),
    insttNm VARCHAR(200)
);

create table REVIEW(
	reviewUid INT PRIMARY KEY,
    reviewerUid VARCHAR(100),
    parkCode VARCHAR(200),
    reviewerNickName VARCHAR(50),
	reviewImageUrl VARCHAR(255),
    reviewText TEXT,
    reviewData VARCHAR(255),
    likeCount SMALLINT,
    reviewRate TINYINT,
    FOREIGN KEY (reviewerUid) REFERENCES BASE_USER(uid),
    FOREIGN KEY (reviewerNickName) REFERENCES BASE_USER(nickname),
    FOREIGN KEY (parkCode) REFERENCES PARK_DATA(prkplceNo)
);


-- Safe Mode Off
SET SQL_SAFE_UPDATES = 0;

-- Safe Mode On
SET SQL_SAFE_UPDATES = 1;