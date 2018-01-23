package com.flc.id;

/**
 * 生成唯一id
 *
 * @author thinkpad
 *
 */
public class ID {
    public String type;
    public String id;

    public static ID parseID(String strDuid) {
        ID idx = new ID();
        idx.type = strDuid.substring(0, 2);
        idx.id = strDuid.substring(2);
        return idx;
    }

    /**
     * 获取DItem UItem id
     *
     * @param idtype
     * @return
     */
    public synchronized static String getDUID(String idtype) {
        String sno = com.flc.util.SNO.getSNO();
        return idtype.toString() + sno;
    }

    /**
     * 判断当前id的类型是否为指定的idtype
     *
     * @param id
     * @param idtype IDType.xxx
     * @return
     */
    public static boolean isIDType(String id, String idtype) {
        if(id == null || id.equals("")) return false;
        String prefix = id.substring(0, 2);
        if(prefix.equals(idtype)) return true;
        else return false;
    }

    public static boolean isDataType(String id, String datatype) {
        if(id == null || id.equals("")) return false;
        if(id.indexOf("_" + datatype + "_") != -1) return true;
        else return false;
    }

}
