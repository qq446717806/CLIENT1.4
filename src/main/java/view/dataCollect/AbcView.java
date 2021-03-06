package view.dataCollect;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import domain.PointBean;
import domain.UnitBean;
import service.SysUnitService;
import view.ModifiedFlowLayout;
import domain.DataBean;
import domain.SensorAttr;

public class AbcView extends JPanel {

    private List<AbcUnitView> units;
    private JComponent content;

    public List<AbcUnitView> getUnits() {
        return units;
    }

    public AbcView(String type) {
        units = new ArrayList<AbcUnitView>();
//		if (type.equals(SensorAttr.Sensor_SSJ)) {
//			this.setLayout(new GridLayout(2, 2, 12, 12));
//			this.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
//			content = this;
//		} else {
        content = new JPanel(new ModifiedFlowLayout(FlowLayout.LEFT, 6, 6));
        JScrollPane jsp = new JScrollPane(content);
        jsp.setBorder(null);
        this.setLayout(new BorderLayout());
        this.add(jsp);
//		}
        // this.setBackground(new Color(241, 241, 241));
    }

    public void setAbcUnitss(List<AbcUnitView> units) {
        this.units.clear();
        this.units = units;
        for (AbcUnitView unit : units) {
            content.add(unit);
        }
    }

    public void addAbcUnit(AbcUnitView unit) {
        units.add(unit);
        content.add(unit);
    }

    public void clearAbcUnits() {
        units.clear();
        content.removeAll();
    }

    public void addData(DataBean data) {
        UnitBean unit = SysUnitService.getUnitBean(data.getUnitType(), data.getUnitNumber());
        if (unit == null) {
            return;
        }
        for (AbcUnitView aunit : units) {
//            System.out.println(data);
//            System.out.println(aunit.getPointBean());
            if (unit.getPoint() == aunit.getPointBean().getPoint()) {
                aunit.addData(data);
                break;
            }
//            if (aunit.matchData(data)) {
//                aunit.addData(data);
//                break;
//            }
        }
    }


    public void refresh(UnitBean unitBean) {
        for (AbcUnitView unit : units) {
//            if (unit.getUnitBean().equals(unitBean)) {
//                unit.refresh(unitBean);
//                break;
//            }
        }
    }

    public void clearData() {
        for (AbcUnitView unit : units) {
            unit.clearData();
        }
    }

    public void setTitle(PointBean pointBean) {
        for (AbcUnitView auv : units) {
            if (auv.getPointBean().getPoint() == pointBean.getPoint()) {
                auv.setTitle(pointBean.getPlace());
            }
        }
    }

    public void sort() {
//        Collections.sort(units);
        for (AbcUnitView unit : units) {
            content.add(unit);
        }
    }
}
