package rabbit;
/**
 * @author: rabbit
 * @Desc:
 * @create: 2023-07-21 13:12
 **/
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage { //��ҳ��
	static JFrame mainJFrame=new JFrame("ѧ���������ϵͳ");
	static Container con=mainJFrame.getContentPane();
	static boolean flag; //�Ƿ�Ϊ����Ա
	static Connection connection=null;
	
	public static void main(String[] args) {
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setResizable(false);
		con.setLayout(null);
		welcomePage();
	}
	
	public static void welcomePage() { //��ӭ����
		mainJFrame.setSize(550,400);
		mainJFrame.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setSize(550,400);
		pn.setLayout(null);
		JLabel lb1=new JLabel("��ӭ��¼"),lb2=new JLabel("ѧ���������ϵͳ");
		JButton bt1=new JButton("ѧ����¼"),bt2=new JButton("����Ա��¼");
		lb1.setFont(new Font("����",0,35));
		lb1.setBounds(200,30,150,100);
		lb2.setFont(new Font("����",0,35));
		lb2.setBounds(128,100,300,80);
		bt1.setFont(new Font("����",0,22));
		bt1.setBounds(70,210,170,70);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt2.setFont(new Font("����",0,22));
		bt2.setBounds(310,210,170,70);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		pn.add(lb1);
		pn.add(lb2);

		pn.add(bt1);
		pn.add(bt2);

		con.add(pn);
		mainJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() { //����ѧ����¼����
			public void actionPerformed(ActionEvent e) {
				flag=false;
				con.remove(pn);
				mainJFrame.repaint();
				loginPage();
				mainJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //�������Ա��¼����
			public void actionPerformed(ActionEvent e) {
				flag=true;
				con.remove(pn);
				mainJFrame.repaint();
				loginPage();
				mainJFrame.validate();
			}
		});

	}
	
	public static void loginPage() { //��¼����
		mainJFrame.setSize(550,400);
		mainJFrame.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setSize(550,400);
		pn.setLayout(null);
		JButton bt1=new JButton("����",new ImageIcon("image/����.png")),bt2=new JButton("��  ¼");
		JTextField tf=new JTextField();
		JPasswordField pf=new JPasswordField();
		JLabel lb1,lb2=new JLabel("�˺ţ�"),lb3=new JLabel("���룺");
		if(flag) {
			lb1=new JLabel("����Ա��¼");
			lb1.setFont(new Font("����",0,35));
			lb1.setBounds(185,30,180,100);
		}
		else {
			lb1=new JLabel("ѧ����¼");
			lb1.setFont(new Font("����",0,35));
			lb1.setBounds(200,30,150,100);
		}
		lb2.setFont(new Font("����",0,25));
		lb2.setBounds(100,100,80,100);
		lb3.setFont(new Font("����",0,25));
		lb3.setBounds(100,150,80,100);
		tf.setFont(new Font("����",0,25));
		tf.setBounds(170,130,230,40);
		pf.setFont(new Font(null,0,25));
		pf.setBounds(170,180,230,40);
		bt2.setFont(new Font("����",0,25));
		bt2.setBounds(200,250,150,60);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt1.setFont(new Font("����",0,17));
		bt1.setBounds(1,10,92,25);
		bt1.setContentAreaFilled(false);
		bt1.setBorderPainted(false);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(lb1);
		pn.add(lb2);
		pn.add(lb3);
		pn.add(tf);
		pn.add(pf);
		pn.add(bt1);
		pn.add(bt2);
		con.add(pn);
		bt1.addMouseListener(new MouseListener() { //���ص���ӭ����
			public void mouseEntered(MouseEvent arg0) {
				bt1.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt1.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				con.remove(pn);
				mainJFrame.repaint();
				welcomePage();
				mainJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt2.addActionListener(new ActionListener() { //��¼���������ݿ��ȡ�˺����벢���飬��ȷ����빦�ܽ���
			public void actionPerformed(ActionEvent e) {
				databaseConnection(); //����MySQL���ݿ�
				if(flag) //���й���Ա�˺��������
					new Login().adminLogin(tf.getText(),String.valueOf(pf.getPassword()));
				else //����ѧ���˺��������
					new Login().studentLogin(tf.getText(),String.valueOf(pf.getPassword()));;
			}
		});
	}
	
	public static void databaseConnection() { //����MySQL���ݿ�
		final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver"; //JDBC������
	    final String DB_URL="jdbc:mysql://localhost:3306/dormitory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; //���ݿ�URL
	    final String USER="root"; //���ݿ��û���
	    final String PASS="123456"; //���ݿ�����
	    try {
	    	Class.forName(JDBC_DRIVER); //����JDBC����
	    	connection=DriverManager.getConnection(DB_URL,USER,PASS); //�������ݿ�
	    }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
}