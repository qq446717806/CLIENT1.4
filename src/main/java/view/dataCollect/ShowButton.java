package view.dataCollect;


import domain.DataBean;
import domain.UnitBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class ShowButton extends JButton {

    private java.util.List<UnitBean> unitList;
    private JPanel jPanel;

    public ShowButton() {
        this(null, null);
    }

    ShowButton(Icon icon) {
        this(null, icon);
    }

    public ShowButton(String text) {
        this(text, null);
    }

    private JLabel titleLabel;

    private JLabel jlA;
    private JLabel jlB;
    private JLabel jlC;
    private JLabel jldw;

    private JPanel center;

    private LadderFrame ladderFrame;

    JPanel getjPanel() {
        return jPanel;
    }

    private ShowButton(String text, Icon icon) {
        super(text, icon);
        Font font = new Font(null, Font.PLAIN, 30);
        unitList = new ArrayList<>();
        jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(new BorderLayout());
        jPanel.setBounds(0, 0, 200, 120);
        jPanel.setVisible(false);

        titleLabel = new JLabel("位置名称", JLabel.CENTER);
        titleLabel.setFont(font);
        jPanel.add(titleLabel, BorderLayout.NORTH);

        jlA = new JLabel("A", JLabel.CENTER);
        jlA.setFont(font);
        jlB = new JLabel("B", JLabel.CENTER);
        jlB.setFont(font);
        jlC = new JLabel("C", JLabel.CENTER);
        jlC.setFont(font);
        JLabel jlsub1 = new JLabel("--", JLabel.CENTER);
        jlsub1.setFont(font);
        JLabel jlsub2 = new JLabel("--", JLabel.CENTER);
        jlsub2.setFont(font);
        jldw = new JLabel("", JLabel.CENTER);
        jldw.setFont(font);


        center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        center.add(jlA);
        center.add(jlsub1);
        center.add(jlB);
        center.add(jlsub2);
        center.add(jlC);

        jPanel.add(center, BorderLayout.CENTER);
        jPanel.add(jldw, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ladderFrame.getjPanel().setFlag(true);
                ladderFrame.getjPanel().clear();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ladderFrame.setHeadTitle(titleLabel.getText());
                ladderFrame.setUnitBeanList(unitList);
                ladderFrame.getjPanel().setFlag(false);
                ladderFrame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel.setVisible(false);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        ladderFrame = LadderFrame.getInstance();
    }


    void addUnit(UnitBean unitBean) {
        unitList.add(unitBean);
        if (unitBean.getPlace() != null && !unitBean.getPlace().equals("")) {
            titleLabel.setText(unitBean.getPlace());
        }
        switch (unitBean.getType()) {
            case 1:
                jldw.setText("单位:MPa");
                break;
            case 2:
                jldw.setText("单位:mm");
                break;
            case 3:
                jldw.setText("单位:℃");
                break;
        }
    }

    void addData(DataBean dataBean) {
        String str = "";
        switch (dataBean.getUnitType()) {
            case 1:
                str = String.valueOf(dataBean.getPres());
                break;
            case 2:
                str = String.valueOf(dataBean.getVari());
                break;
            case 3:
                str = String.valueOf(dataBean.getTemp());
                break;
        }

        String xw = getXw(dataBean);
        switch (xw) {
            case "A":
                jlA.setText(str);
                break;
            case "B":
                jlB.setText(str);
                break;
            case "C":
                jlC.setText(str);
                break;
        }
    }

    private String getXw(DataBean dataBean) {
        String xw = "";
        for (UnitBean unit : unitList) {
            if (dataBean.getUnitType() == unit.getType() && dataBean.getUnitNumber() == unit.getNumber()) {
                xw = unit.getXw();
            }
        }
        return xw;
    }

}