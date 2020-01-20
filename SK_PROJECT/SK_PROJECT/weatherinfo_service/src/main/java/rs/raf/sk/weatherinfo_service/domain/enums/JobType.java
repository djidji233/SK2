package rs.raf.sk.weatherinfo_service.domain.enums;

public enum JobType {

    MEASUREMENT("0 0 0/1 1/1 * ? *"),

//    NOTIFICATION("0 0 12 1/1 * ? *");
    NOTIFICATION("0 0/1 * 1/1 * ? *");

    public final String defValue;

    JobType(String defValue) {
        this.defValue = defValue;
    }

}
