package parser;

public class HospitalDB{
    private String dutyAddr;
    private String dutyEmcls;
    private String dutyEmclsName;
    private String dutyName;
    private String dutyTel1;
    private String dutyTel3;
    private String wgs84Lat;
    private String wgs84Lon;

    public HospitalDB(String dutyAddr, String dutyEmcls, String dutyEmclsName, String dutyName,
                      String dutyTel1, String dutyTel3, String wgs84Lat, String wgs84Lon){
        super();
        this.dutyAddr = dutyAddr;
        this.dutyEmcls = dutyEmcls;
        this.dutyEmclsName = dutyEmclsName;
        this.dutyName = dutyName;
        this.dutyTel1 = dutyTel1;
        this.dutyTel3 = dutyTel3;
        this.wgs84Lat = wgs84Lat;
        this.wgs84Lon = wgs84Lon;
    }


}


