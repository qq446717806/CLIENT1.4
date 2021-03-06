/*
 * 用户登录界面
 * 验证用户名和密码，并返回用户权限limit
 * 将权限传递给主窗口函数
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import service.UserService;

import mytools.MyButton2;
import mytools.MyPanel;

import com.DefaultUIManager;
import com.MyLgoInfo;

import domain.UserBean;

public class Login extends JFrame {
    JPanel jpBackground, jpLoad, jpLogin;
    JLabel jlbSoftname, jlbCopyright, jlbPageInfo;
    private JProgressBar jpbProgress;
    private JLabel jlbProgress;
    // 登录、取消按钮
    private JButton jbtApply;
    // 用户输入区域
    private JTextField jtfUserName;
    // 密码框
    private JPasswordField jpfPSW;

    // 构造方法
    public Login() {

        JPanel ContentPane = new JPanel(new BorderLayout());
        ContentPane.setBorder(BorderFactory.createLineBorder(new Color(44, 46,
                54)));
        Image image = new ImageIcon(this.getClass().getResource("load.jpg"))
                .getImage();
        jpBackground = new MyPanel(image, 1.0f);
        jpBackground.setLayout(null);
        ContentPane.add(jpBackground);

        Font font = MyLgoInfo.getTitleFont("微软雅黑", Font.PLAIN, MyLgoInfo.SoftName, 380, 40);
        // jlbSoftname = new JLabel(new ImageIcon(MyUtil.getArtTitle(
        // MyLgoInfo.SoftName, font, Color.WHITE, null, 0, Color.GRAY)));
        jlbSoftname = new JLabel(MyLgoInfo.SoftName, JLabel.CENTER);
        jlbSoftname.setForeground(Color.WHITE);
        jlbSoftname.setFont(font);
        jlbSoftname.setBounds(10, 10, 380, 40);
        jpBackground.add(jlbSoftname);

        jlbPageInfo = new JLabel("系统初始化");
        jlbPageInfo.setBounds(40, 80, 120, 22);
        jlbPageInfo.setForeground(new Color(50, 80, 150));
        jlbPageInfo.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jpBackground.add(jlbPageInfo);

        font = MyLgoInfo.getTitleFont("微软雅黑", Font.PLAIN, "© CopyRight "
                + MyLgoInfo.CopyrightName, 380, 14);
        jlbCopyright = new JLabel("© CopyRight " + MyLgoInfo.CopyrightName, JLabel.CENTER);
        jlbCopyright.setBounds(10, 280, 380, 20);
        jlbCopyright.setFont(font);
        jlbCopyright.setForeground(Color.WHITE);
        jpBackground.add(jlbCopyright);

        this.setIconImages(DefaultUIManager.icons);
        this.setTitle(MyLgoInfo.SoftName);
        // 去除JDialog边框
        this.setUndecorated(true);
        // 透明
        // AWTUtilities.setWindowOpaque(this, false);
        // 设置JDialog尺寸为背景图片大小
        this.setSize(image.getWidth(null) + 10, image.getHeight(null) + 10);
        // 设置窗体居中
        this.setLocationRelativeTo(null);
        this.setContentPane(ContentPane);
        this.initLoad();
        this.initLogin();
        this.setVisible(true);

    }

    private void initLoad() {
        jpLoad = new JPanel(null);
        jpLoad.setOpaque(false);
        jpLoad.setBounds(80, 120, 240, 120);
        jpBackground.add(jpLoad);

        JLabel jlbBridge = new JLabel(new ImageIcon(this.getClass().getResource("bridge.png")));
        jlbBridge.setBounds(0, 0, 240, 80);
        jpLoad.add(jlbBridge);

        jlbProgress = new JLabel();
        jlbProgress.setHorizontalAlignment(JLabel.CENTER);// 文字居中
        jlbProgress.setForeground(new Color(50, 80, 150));
        jlbProgress.setBounds(0, 90, 240, 18);
        jpLoad.add(jlbProgress);

        jpbProgress = new JProgressBar(0, 100);
        jpbProgress.setStringPainted(true);// 显示百分比字符
        jpbProgress.setIndeterminate(false); // 不确定的进度条
        jpbProgress.setForeground(new Color(150, 170, 240));// 颜色
        jpbProgress.setBorderPainted(false);// 取消边框
        jpbProgress.setOpaque(false);// 设置透明背景
        jpbProgress.setBounds(0, 108, 240, 12);
        jpLoad.add(jpbProgress);
    }

    private void initLogin() {
        jpLogin = new JPanel(null);
        jpLogin.setOpaque(false);
        jpLogin.setBounds(80, 120, 240, 120);
        jpLogin.setVisible(false);
        jpBackground.add(jpLogin);

        Font fontL = new Font("微软雅黑", Font.PLAIN, 18);
        JLabel jlbName = new JLabel("用户名:", JLabel.RIGHT);
        jlbName.setBounds(0, 10, 75, 20);
        jlbName.setFont(fontL);
        jpLogin.add(jlbName);

        Font font = new Font("Arial", Font.PLAIN, 16);
        Color color = new Color(239, 242, 248);
        jtfUserName = new JTextField();
        jtfUserName.setBounds(80, 10, 160, 20);
        jtfUserName.setBackground(color);
        jtfUserName.setFont(font);
        jpLogin.add(jtfUserName);

        JLabel jlbPSW = new JLabel("密    码:", JLabel.RIGHT);
        jlbPSW.setBounds(0, 40, 75, 20);
        jlbPSW.setFont(fontL);
        jpLogin.add(jlbPSW);

        jpfPSW = new JPasswordField();
        jpfPSW.setBounds(80, 40, 160, 20);
        jpfPSW.setBackground(color);
        jpfPSW.setFont(font);
        jpfPSW.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jbtApply.doClick();
                } else
                    return;
            }
        });
        jpLogin.add(jpfPSW);

        jbtApply = new MyButton2("确定", new ImageIcon("images/apply.png"));
        jbtApply.setBounds(30, 80, 90, 30);
        jbtApply.setFont(fontL);
        jbtApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    String[] info = checkInput();
                    UserBean user = UserService.getInstance().checkUser(info);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "密码错误或者用户不存在",
                                "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    setVisible(false);
                    dispose();
                    Shell.getInstance().setVisible(true);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "提示",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jpLogin.add(jbtApply);

        JButton jbtCancel = new MyButton2("取消", new ImageIcon(
                "images/cancel.png"));
        jbtCancel.setBounds(130, 80, 90, 30);
        jbtCancel.setFont(fontL);
        jpLogin.add(jbtCancel);
        jbtCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.this.setVisible(false);
                Login.this.dispose();
                System.exit(0);
            }
        });
    }

    // 加载进度显示
    public void loading(int percent, String loading) {
        jpbProgress.setValue(percent);
        jlbProgress.setText(loading);
    }

    public void toLogin() {
        jlbPageInfo.setText("用户登录");
        jpLoad.setVisible(false);
        jpLogin.setVisible(true);
        jtfUserName.requestFocus();
        jpBackground.repaint();
        jpBackground.revalidate();
    }

    // 槛车输入是否为空，并返回输入信息
    public String[] checkInput() throws Exception {
        String userName = jtfUserName.getText().trim().toLowerCase();
        if (userName.equals("")) {
            throw new Exception("请输入用户名");
        }
        String password = new String(jpfPSW.getPassword()).toLowerCase();
        if (password.equals("")) {
            throw new Exception("密码不能为空");
        }
        return new String[]{userName, password};
    }
}
