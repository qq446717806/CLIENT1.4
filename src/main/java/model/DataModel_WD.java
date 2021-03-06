package model;

import java.util.Date;
import java.util.Vector;

/**
 * 温度表模型
 */
public class DataModel_WD extends DataManageModel {

    private static DataModel_WD DM = null;

    private DataModel_WD() {
        initDefault();
    }

    @Override
    protected void initDefault() {
        super.initDefault();
        Vector<String> column = new Vector<String>();
        column.add("ID");
//        column.add("网关类型");
//        column.add("网关编号");
//        column.add("单元类型");
//        column.add("单元编号");
        column.add("监测点");
        column.add("测点相位");
        column.add("温度℃");
        column.add("电压Ｖ");
        column.add("时间");
        this.setDataVector(row, column);
    }

    public static DataModel_WD getInstance() {
        if (DM == null) {
            synchronized (DataModel_WD.class) {
                if (DM == null)
                    DM = new DataModel_WD();
            }
        }
        return DM;
    }

    /**
     * 重写getColumnClass方法，实现排序对列类型的区分
     * 这里根据数据库表中各个列类型，自定义返回每列的类型(用于解决数据库中NULL处理抛出异常)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // id,gt,gn,ut,un,pres,temp,den,batlv
        if (column == 0) {
            return Integer.class;
        } else if (column == 1 || column == 2) {
            return String.class;
        } else if (column == 5) {
            return java.util.Date.class;
        } else {
            return Float.class;
        }
    }

    @Override
    public String getSelectSQL() {
        return "SELECT\n" +
                "  i.id as id,\n" +
//                "  g.type AS gt,\n" +
//                "  g.number AS gn,\n" +
//                "  u.type AS ut,\n" +
//                "  u.number AS un,\n" +
                "  p.place AS us,\n" +
                "  u.xw AS ux,\n" +
                "  i.Temp AS temp,\n" +
                "  i.BatLv AS batlv,\n" +
                "  i.date\n" +
                "FROM\n" +
                "  gateway g\n" +
                "    JOIN unit u\n" +
                "    JOIN data i\n" +
                "    JOIN point p\n" +
                "WHERE\n" +
                "  g.type = u.gatewaytype AND u.type = i.unittype AND u.type = 3 AND g.number = u.gatewaynumber AND u.number = i.unitnumber AND u.point = p.point";
    }
}
