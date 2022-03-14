package com.star.sharding.entity;

public class Udict {

    private Long dictid;

    private String ustatus;

    private String uvalue;

    public Long getDictid() {
        return dictid;
    }

    public void setDictid(Long dictid) {
        this.dictid = dictid;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUvalue() {
        return uvalue;
    }

    public void setUvalue(String uvalue) {
        this.uvalue = uvalue;
    }

    @Override
    public String toString() {
        return "Udict{" +
                "dictid=" + dictid +
                ", ustatus='" + ustatus + '\'' +
                ", uvalue='" + uvalue + '\'' +
                '}';
    }
}
