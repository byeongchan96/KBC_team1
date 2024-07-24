package bitc.fullstack405.bitcteam3prj.database.dto.movie;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResultDTO {
    String DOCID;
    String movieId;
    String movieSeq;
    String title;
    String titleEng;
    String titleOrg;
    String titleEtc;
    String prodYear;
    DirectorsDTO directors;
    ActorsDTO actors;
    String nation;
    String company;
    PlotsDTO plots;
    String runtime;
    String rating;
    String genre;
    String kmdbUrl;
    String type;
    String use;
    String episodes;
    String ratedYn;
    String repRatDate;
    String repRlsDate;
    Ratings ratings;
    String keywords;
    String posters;
    String stills;
    Staffs staffs;
    Vods vods;
    String openThtr;
    ArrayList<Stat> stat;
    String screenArea;
    String screenCnt;
    String salesAcc;
    String audiAcc;
    String statSouce;
    String statDate;
    String themeSong;
    String soundtrack;
    String fLocation;
    String Awards1;
    String Awards2;
    String regDate;
    String modDate;
    Codes Codes;
    CommCodes CommCodes;
    String ALIAS;
}

