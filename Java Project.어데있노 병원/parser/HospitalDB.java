package parser;

public class HospitalDB{
    private String dutyAddr;
    private String dutyEmcls;
    private String dutyEmclsName;
    private String dutyName;
    private String dutyTel1;
    private String wgs84Lat;
    private String wgs84Lon;

    public HospitalDB(String dutyAddr, String dutyEmcls, String dutyEmclsName, String dutyName,
                      String dutyTel1, String wgs84Lat, String wgs84Lon){
        super();
        this.dutyAddr = dutyAddr;
        this.dutyEmcls = dutyEmcls;
        this.dutyEmclsName = dutyEmclsName;
        this.dutyName = dutyName;
        this.dutyTel1 = dutyTel1;
        this.wgs84Lat = wgs84Lat;
        this.wgs84Lon = wgs84Lon;
    }

    public String getDutyAddr() {
        return dutyAddr;
    }

    public String getDutyEmcls() {
        return dutyEmcls;
    }

    public String getDutyEmclsName() {
        return dutyEmclsName;
    }

    public String getDutyTel1() {
        return dutyTel1;
    }


    public String getDutyName() {
        return dutyName;
    }

    public String getWgs84Lat() {
        return wgs84Lat;
    }

    public String getWgs84Lon() {
        return wgs84Lon;
    }
}

