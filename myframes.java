import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
public class myframes extends JFrame{
	Container contentPane;
	private JTextField textvar;
	private JTextField textx;
	private JTextField texty;
	private JTextField textw;
	private JTextField texth;
	private JTextField texttext;
	private JComboBox<String> texttype;
	private JToolBar bar;
	private int objnum = 0;//index number
	private int currentobj = 0;
	private JToggleButton Togmouse;
	private JToggleButton Toglabel;
	private JToggleButton Togbutton;
	private JToggleButton Togtext;
	JPanel EditorPanel;
	int shapenum=0;
	String fname;
	Controller C= new Controller();
	MyPanel[] mktemp = new MyPanel[100];
	Model[] model = new Model[100];
	myframes()
	{
		setTitle("project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		createToolBar();
		createAttribute();
		createEditor();
		setLocation(300,200);
		setSize(550,350);
		setVisible(true);
	}
	private void createMenu() {
		JMenuBar mb=new JMenuBar();
		
		JMenu fileMenu=new JMenu("FILE");
		JMenuItem[] fileitem =new JMenuItem[4]; 
		String[] fileitem1 = {"new", "닫기", "열기", ".java만들기"};
		for(int i=0;i<fileitem.length;i++)
		{
			fileitem[i]=new JMenuItem(fileitem1[i]);
			fileitem[i].addActionListener(new fileAction());
			fileMenu.add(fileitem[i]);
			fileMenu.addSeparator();
		}
		mb.add(fileMenu);
		
		JMenu saveMenu=new JMenu("저장");
		JMenuItem[] saveitem =new JMenuItem[2]; 
		String[] saveitem1 = {"저장", "다른이름으로 저장"};
		for(int i=0;i<saveitem.length;i++)
		{
			saveitem[i]=new JMenuItem(saveitem1[i]);
			saveitem[i].addActionListener(new SaveAction());
			saveMenu.add(saveitem[i]);
			saveMenu.addSeparator();
		}
		mb.add(saveMenu);

		JMenu shapeMenu=new JMenu("컴포넌트");
		JMenuItem[] shapeitem =new JMenuItem[5]; 
		String[] item2 = {"label", "button", "textfield", "마우스", "지우기"};
		for(int i=0; i<shapeitem.length; i++)
		{
			shapeitem[i]=new JMenuItem(item2[i]);
			shapeitem[i].addActionListener(new ShapeAction());
			shapeMenu.add(shapeitem[i]);
			shapeMenu.addSeparator();
		}
		mb.add(shapeMenu);
		setJMenuBar(mb);
	}
	private void createToolBar()
	{
		bar = new JToolBar("tool menu");
		bar.setFloatable(false);
		bar.setBounds(0, 0, 684, 29);
		bar.setBackground(Color.LIGHT_GRAY);
		ButtonGroup bg = new ButtonGroup(); 
		
		JButton newBtn = new JButton(new ImageIcon("images/new.png"));
		newBtn.addActionListener(new action());
		newBtn.setToolTipText("new");
		bar.add(newBtn);
		
		JButton changeBtn = new JButton(new ImageIcon("images/change.jpg"));
		changeBtn.addActionListener(new action());
		changeBtn.setToolTipText("change");
		bar.add(changeBtn);
		
		JButton saveBtn = new JButton(new ImageIcon("images/save.jpg"));
		saveBtn.addActionListener(new action());
		saveBtn.setToolTipText("save");
		bar.add(saveBtn);
		
		JButton openBtn = new JButton(new ImageIcon("images/load.png"));
		openBtn.addActionListener(new action());
		openBtn.setToolTipText("load");
		bar.add(openBtn);
		
		JButton removeBtn = new JButton(new ImageIcon("images/remove.png"));
		removeBtn.addActionListener(new action());
		removeBtn.setToolTipText("remove");
		bar.add(removeBtn);
		
		Togmouse = new JToggleButton(new ImageIcon("Images/mouse.jpg"));
		Togmouse.setToolTipText("mouse");
		Togmouse.addActionListener(new toggleaction());
		bar.add(Togmouse);
		
		Toglabel = new JToggleButton("label");
		Toglabel.setToolTipText("label");
		Toglabel.addActionListener(new toggleaction());
		bar.add(Toglabel);
		
		Togbutton = new JToggleButton("button");
		Togbutton.setToolTipText("button");
		Togbutton.addActionListener(new toggleaction());
		bar.add(Togbutton);
		
		Togtext=new JToggleButton("textfield");
		Togtext.setToolTipText("textfield");
		Togtext.addActionListener(new toggleaction());
		bar.add(Togtext);
		
		bg.add(Togmouse);
		bg.add(Toglabel);
		bg.add(Togbutton);
		bg.add(Togtext);
		
		getContentPane().add(bar);
	}
	private void createAttribute()
	{	
		JPanel AttributePanel = new JPanel();
		AttributePanel.setBounds(0, 0, 110, 250);
		getContentPane().add(AttributePanel);
		AttributePanel.setLayout(null);
		
		JLabel TypeLabel = new JLabel("TYPE");
		TypeLabel.setBounds(10, 50, 57, 15);
		AttributePanel.add(TypeLabel);
		
		texttype=new JComboBox<String>();
		texttype.setBounds(50, 50, 57, 21);
		texttype.setBackground(Color.white);
		texttype.addItem("");
		texttype.addItem("Label");
		texttype.addItem("Button");
		texttype.addItem("TextField");
		AttributePanel.add(texttype);

		JLabel XLabel = new JLabel("X");
		XLabel.setBounds(10, 75, 57, 15);
		AttributePanel.add(XLabel);
		
		textx = new JTextField();
		textx.setBounds(50, 75, 57, 21);
		textx.setColumns(10);
		AttributePanel.add(textx);
		
		JLabel YLabel = new JLabel("Y");
		YLabel.setBounds(10, 100, 57, 15);
		AttributePanel.add(YLabel);
		
		texty = new JTextField();
		texty.setBounds(50, 100, 57, 21);
		texty.setColumns(10);
		AttributePanel.add(texty);
		
		JLabel WLabel = new JLabel("W");
		WLabel.setBounds(10, 125, 57, 15);
		AttributePanel.add(WLabel);
		
		textw = new JTextField();
		textw.setBounds(50, 125, 57, 21);
		textw.setColumns(10);
		AttributePanel.add(textw);
		
		JLabel HLabel = new JLabel("H");
		HLabel.setBounds(10, 150, 57, 15);
		AttributePanel.add(HLabel);
		
		texth = new JTextField();
		texth.setBounds(50, 150, 57, 21);
		texth.setColumns(10);
		AttributePanel.add(texth);
		
		JLabel VARLabel = new JLabel("VAR");
		VARLabel.setBounds(10, 175, 57, 15);
		AttributePanel.add(VARLabel);
		
		textvar = new JTextField();
		textvar.setBounds(50, 175, 57, 21);
		textvar.setColumns(10);
		AttributePanel.add(textvar);
		
		JLabel TEXTLabel=new JLabel("text");
		TEXTLabel.setBounds(10,  200, 57, 15);
		AttributePanel.add(TEXTLabel);
		
		texttext = new JTextField();
		texttext.setBounds(50,200,57,21);
		texttext.setColumns(10);
		AttributePanel.add(texttext);
		
		JButton btnChange = new JButton("change");
		btnChange.setBounds(20, 225, 82, 23);
		btnChange.addActionListener(new changeaction());
		AttributePanel.add(btnChange);
		
		getContentPane().setLayout(null);	
	}
	private void createEditor()
	{
		EditorPanel = new JPanel();
		EditorPanel.setBackground(Color.WHITE);
		EditorPanel.setForeground(Color.LIGHT_GRAY);
		EditorPanel.setBounds(128, 39, 355, 203);
		EditorPanel.setLayout(null);
		getContentPane().add(EditorPanel);
		EditorPanel.addMouseListener(
				new MyMouseListener());
		EditorPanel.setVisible(true);
	}
	class MyPanel extends JPanel
	{
		private int w, h, num;
		private int x, y;
		private int Serial;
		private int a, b, c;
		private String var;
		private String text;
		JButton buttonedit;
		JLabel labeledit;
		JTextField textedit;
	
		MyPanel(int x, int y, int w, int h, int num, int Serial)
		{
			this.x = x;
			this.y = y;
		this.w = w;
		this.h = h;
		this.Serial = Serial;
		this.var = "Default";
		this.text = "Default";
		buttonedit = new JButton();
		labeledit = new JLabel();
		textedit =new JTextField();
//		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(0, 0, w, h);
		this.setVisible(true);
		MyMouseListener2 tool = new MyMouseListener2(x, y, Serial);
		this.addMouseListener(tool);
		this.addMouseMotionListener(tool);
		this.num = num;
		if(num == 1){
			this.a = 134;
			this.b = 229;
			this.c = 127;
		}
		else if(num == 2){
			this.a = 178;
			this.b = 235;
			this.c = 244;
		}
		else{
			this.a = 246;
			this.b = 36;
			this.c = 36;
		}
		this.setBackground(new Color(a,b,c));
		this.mkcomponent(num);
		model[Serial] = new Model();
		C.setX(model[Serial], x);
		C.setY(model[Serial], y);
		C.setWidth(model[Serial], w);
		C.setHeight(model[Serial], h);
		C.setVar(model[Serial], "Default");
		C.setText(model[Serial], "Default");
		C.setSerial(model[Serial], Serial);
		if(num == 1){
			C.setNum(model[Serial], 1);
		}
		else if(num == 2){
			C.setNum(model[Serial], 2);
		}
		else{
			C.setNum(model[Serial], 3);
		}
	}
		void mkcomponent(int shapenum)
		{
			if(shapenum==1)
			{
				this.setBackground(new Color(a,b,c));
			}
			else if(shapenum==2)
			{
				this.setBackground(new Color(a,b,c));
			}
			else if(shapenum==3)
			{
				this.setBackground(new Color(a,b,c));
			}
		}
		public void changeColor()
		{
			if(this.num == 1)
			{
				if(this.a == 134)
				{
					this.a = 255;
					this.b = 255;
					this.c = 36;
				}
				else
				{
					this.a = 134;
					this.b = 229;
					this.c = 127;
					
				}
				this.setBackground(new Color(a,b,c));
			}
			else if(this.num == 2)
			{
				if(this.a == 178)
				{	
					this.a = 255;
					this.b = 255;
					this.c = 136;	
				}
				else
				{	
					this.a = 178;
					this.b = 235;
					this.c = 244;	
				}
				this.setBackground(new Color(a,b,c));
			}
			else if(this.num == 3)
			{
				if(this.a == 246)
				{
					this.a = 255;
					this.b = 255;
					this.c = 36;	
				}
				else
				{
					this.a = 246;
					this.b = 36;
					this.c = 36;	
				}
				this.setBackground(new Color(a,b,c));
			}
		}
		public int getX(){
			return this.x;
		}
		public int getY(){
			return this.y;
		}
		public int getWidth(){
			return this.w;
		}
		public int getHeight(){
			return this.h;
		}
		public void sfX(int x){
			this.x = x;
			C.setX(model[Serial], x);
		}
		public void sfY(int y){
			this.y = y;
			C.setY(model[Serial], y);
		}
		public void sfW(int w){
			this.w = w;
			C.setWidth(model[Serial], w);
		}
		public void sfH(int h){
			this.h = h;
			C.setHeight(model[Serial], h);
		}
		public void sfN(int num){
			this.num = num;
			if(num == 1){
				C.setNum(model[Serial], 1);
			}
			else if(num == 2){
				C.setNum(model[Serial], 2);
			}
			else{
				C.setNum(model[Serial], 3);
			}
		}
		public void sfV(String var){
			this.var = var;
			C.setVar(model[Serial], var);
		}
		public void sfT(String text){
			this.text = text;
			C.setText(model[Serial], text);
		}
		public void changebutton(){
			C.setWidth(model[Serial], w);
			C.setHeight(model[Serial], h);
		}
	
		public void changetextfield(){
			C.setWidth(model[Serial], w);
			C.setHeight(model[Serial], h);
		}
	}
	class MyMouseListener implements MouseListener, MouseMotionListener{
		private int x1, y1, x2, y2;
		public void mousePressed(MouseEvent e){
			x1 = e.getX();
			y1 = e.getY();
		}
		public void mouseReleased(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
			int xf , yf;
			xf = x1 - x2;
			yf = y1 - y2;	
			if(shapenum != 0)
			{
				if(objnum>=100)
				{
					JOptionPane.showMessageDialog(null, "더 이상 만들 수 없습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				if(xf<=0 && yf>=0)
				{
					xf*= -1;
					mktemp[objnum] = new MyPanel(x1, y2, xf,yf,shapenum, objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(x1 ,y2);
				}
				else if(xf<0 && yf<0)
				{
					xf*= -1;
					yf*= -1;
					mktemp[objnum] = new MyPanel(x1, y1, xf,yf,shapenum, objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(x1, y1);
				}
				else if(xf>0 && yf >0)
				{
					mktemp[objnum] = new MyPanel(x2, y2, xf,yf,shapenum, objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(x2, y2);
				}
				else 
				{
					yf*= -1;
					mktemp[objnum] = new MyPanel(x2, y1, xf,yf,shapenum,objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(x2, y1);
				}
				objnum += 1;
				}
				}
		}
		public void mouseExited(MouseEvent e){
		}
		public void mouseClicked(MouseEvent e){
		}
		public void mouseEntered(MouseEvent e){
		}
		public void mouseDragged(MouseEvent e){
			
		}
		public void mouseMoved(MouseEvent e){
		}
	}
	class MyMouseListener2 implements MouseListener, MouseMotionListener{
		private int x1, y1, x2, y2, xd, yd;
		private int xt, yt;
		private int Serial;
		private int w1, h1;
		MyMouseListener2(int x, int y, int Serial){
			this.xt = x;
			this.yt = y;
			this.Serial = Serial;
		}
		public void mousePressed(MouseEvent e){
			x1 = e.getX();
			y1 = e.getY();
			if(Serial == (currentobj - 1) ){
				w1 = mktemp[Serial].getWidth();
				h1 = mktemp[Serial].getHeight();
			}
		}
		public void mouseReleased(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
			int xf , yf;
			xf = x1 - x2;
			yf = y1 - y2;
			int xk1, yk1, xk2, yk2;
			xk1 = x1 + xt;
			xk2 = x2 + xt;
			yk1 = y1 + yt;
			yk2 = y2 + yt;
			if(shapenum != 0)
			{
				if(objnum>=100)
				{
					JOptionPane.showMessageDialog(null, "더 이상 만들 수 없습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				if(xf<=0 && yf>=0){
					xf*= -1;
					mktemp[objnum] = new MyPanel(xk1, yk2, xf,yf,shapenum,objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(xk1,yk2);
				}
				else if(xf<0 && yf<0){
					xf*= -1;
					yf*= -1;
					mktemp[objnum] = new MyPanel(xk1, yk1, xf,yf,shapenum,objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(xk1, yk1);
					}
				else if(xf>0 && yf >0){
					mktemp[objnum] = new MyPanel(xk2, yk2, xf,yf,shapenum,objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(xk2, yk2);
				}
				else {
					yf*= -1;
					mktemp[objnum] = new MyPanel(xk2, yk1, xf,yf,shapenum,objnum);
					EditorPanel.add(mktemp[objnum]);
					mktemp[objnum].setLocation(xk2, yk1);
				}
				objnum += 1;
				}
			}
			else{
				
			}
		}
		public void mouseExited(MouseEvent e){
			setCursor(Cursor.getDefaultCursor());
		}
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				if(shapenum == 0){
					if(currentobj == 0)
					{
							mktemp[Serial].setVisible(false);
							mktemp[Serial].changeColor();
							mktemp[Serial].setVisible(true);
							currentobj = Serial + 1;
							if(C.getNum(model[currentobj-1])==1)
							{
								texttype.setSelectedIndex(1);
							}
							else if(C.getNum(model[currentobj-1])==2)
							{
								texttype.setSelectedIndex(2);
							}
							else if(C.getNum(model[currentobj-1])==3)
							{
								texttype.setSelectedIndex(3);
							}
							textx.setText(Integer.toString(C.getX(model[currentobj-1])));
							texty.setText(Integer.toString(C.getY(model[currentobj-1])));
							textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
							texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
							textvar.setText(C.getVar(model[currentobj-1]));
							texttext.setText(C.getText(model[currentobj-1]));
					}
					else{
						if((currentobj - 1)== Serial)
						{
							mktemp[Serial].setVisible(false);
							mktemp[Serial].changeColor();
							mktemp[Serial].setVisible(true);
							currentobj = 0;
							texttype.setSelectedIndex(0);
							textx.setText(null);
							texty.setText(null);
							textw.setText(null);
							texth.setText(null);
							textvar.setText(null);
							texttext.setText(null);	
						}
						else{
							mktemp[currentobj-1].setVisible(false);
							mktemp[currentobj-1].changeColor();
							mktemp[currentobj-1].setVisible(true);
							mktemp[Serial].setVisible(false);
							mktemp[Serial].changeColor();
							mktemp[Serial].setVisible(true);
							currentobj = Serial + 1;
							if(C.getNum(model[currentobj-1])==1)
							{
								texttype.setSelectedIndex(1);
							}
							else if(C.getNum(model[currentobj-1])==2)
							{
								texttype.setSelectedIndex(2);
							}
							else if(C.getNum(model[currentobj-1])==3)
							{
								texttype.setSelectedIndex(3);
							}
							textx.setText(Integer.toString(C.getX(model[currentobj-1])));
							texty.setText(Integer.toString(C.getY(model[currentobj-1])));
							textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
							texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
							textvar.setText(C.getVar(model[currentobj-1]));
							texttext.setText(C.getText(model[currentobj-1]));
						}
					}
				}
			}
		}
		public void mouseEntered(MouseEvent e){
		}
		public void mouseDragged(MouseEvent e){
			xd = e.getX();
			yd = e.getY();
			int xf , yf;
			xf = x1 - xd;
			yf = y1 - yd;
			int xk1, yk1, xk2, yk2;
			xk1 = x1 + xt;
			xk2 = xd + xt;
			yk1 = y1 + yt;
			yk2 = yd + yt;
			
			if(shapenum == 0){
				if((currentobj-1) == Serial){
					if( ((w1 - 5) < x1)&&(h1 - 5 < y1)){
						mktemp[Serial].setVisible(false);
						mktemp[currentobj-1].sfW(w1 - xf);
						mktemp[currentobj-1].sfH(h1 - yf);
						mktemp[currentobj-1].setBounds(mktemp[currentobj-1].getX(),mktemp[currentobj-1].getY(),mktemp[currentobj-1].getWidth(),mktemp[currentobj-1].getHeight());
						mktemp[currentobj-1].setLocation(mktemp[Serial].getX(),mktemp[Serial].getY());
						if(C.getNum(model[currentobj-1]) == 2){
							mktemp[currentobj-1].changebutton();
						}
						else if(C.getNum(model[currentobj-1])==3){
							mktemp[currentobj-1].changetextfield();
						}
						textx.setText(Integer.toString(C.getX(model[currentobj-1])));
						texty.setText(Integer.toString(C.getY(model[currentobj-1])));
						textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
						texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
						textvar.setText(C.getVar(model[currentobj-1]));
						texttext.setText(C.getText(model[currentobj-1]));
						mktemp[Serial].setVisible(true);
					}
					else if(h1 - 5 < y1){
						mktemp[Serial].setVisible(false);
						mktemp[currentobj-1].sfH(h1 - yf);
						mktemp[currentobj-1].setBounds(mktemp[currentobj-1].getX(),mktemp[currentobj-1].getY(),mktemp[currentobj-1].getWidth(),mktemp[currentobj-1].getHeight());
						mktemp[currentobj-1].setLocation(mktemp[Serial].getX(),mktemp[Serial].getY());
						if(C.getNum(model[currentobj-1]) == 2){
							mktemp[currentobj-1].changebutton();
						}
						else if(C.getNum(model[currentobj-1])==3){
							mktemp[currentobj-1].changetextfield();
						}
						textx.setText(Integer.toString(C.getX(model[currentobj-1])));
						texty.setText(Integer.toString(C.getY(model[currentobj-1])));
						textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
						texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
						textvar.setText(C.getVar(model[currentobj-1]));
						texttext.setText(C.getText(model[currentobj-1]));
						mktemp[Serial].setVisible(true);
					}
					else if((w1 - 5) < x1){
						mktemp[Serial].setVisible(false);
						mktemp[currentobj-1].sfW(w1 - xf);
						mktemp[currentobj-1].setBounds(mktemp[currentobj-1].getX(),mktemp[currentobj-1].getY(),mktemp[currentobj-1].getWidth(),mktemp[currentobj-1].getHeight());
						mktemp[currentobj-1].setLocation(mktemp[Serial].getX(),mktemp[Serial].getY());
						if(C.getNum(model[currentobj-1]) == 2){
							mktemp[currentobj-1].changebutton();
						}
						else if(C.getNum(model[currentobj-1])==3){
							mktemp[currentobj-1].changetextfield();
						}
						textx.setText(Integer.toString(C.getX(model[currentobj-1])));
						texty.setText(Integer.toString(C.getY(model[currentobj-1])));
						textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
						texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
						textvar.setText(C.getVar(model[currentobj-1]));
						texttext.setText(C.getText(model[currentobj-1]));
						mktemp[Serial].setVisible(true);
					}
					else{
						mktemp[Serial].setVisible(false);
						mktemp[Serial].sfX(mktemp[Serial].getX()-xf);
						mktemp[Serial].sfY(mktemp[Serial].getY()-yf);
						mktemp[Serial].setLocation(mktemp[Serial].getX(),mktemp[Serial].getY());
						textx.setText(Integer.toString(C.getX(model[currentobj-1])));
						texty.setText(Integer.toString(C.getY(model[currentobj-1])));
						textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
						texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
						textvar.setText(C.getVar(model[currentobj-1]));
						texttext.setText(C.getText(model[currentobj-1]));
						mktemp[Serial].setVisible(true);			
					}
				}
			}
		}
		public void mouseMoved(MouseEvent e){
			if(shapenum == 0){
				
				if((currentobj-1) == Serial){
					if( ((mktemp[Serial].getWidth() - 5) < e.getX())&&((mktemp[Serial].getHeight() - 5) < e.getY())){
						setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						
					}
					else if( (mktemp[Serial].getWidth() - 5) < e.getX()){
						setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					}
					else if((mktemp[Serial].getHeight() - 5) < e.getY()){
						setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					}
					else{
						setCursor(Cursor.getDefaultCursor());
					}
				}
			}
		}
	}
	void removeall()
	{
		for(int i = 0; i < objnum; i++)
		{
			if((mktemp[i]!=null) && (model[i]!=null)){
			mktemp[i].setVisible(false);
			mktemp[i] = null;
			model[i] = null;
			}
		}
		fname = null;
		objnum = 0;
		currentobj = 0;
	    texttype.setSelectedIndex(0);
	    textx.setText(null);
	    texty.setText(null);
	    textw.setText(null);
	    texth.setText(null);
	    textvar.setText(null);
	    texttext.setText(null);
	}
	void removethis()
	{
		if(currentobj!=0)
		{
			mktemp[currentobj-1].setVisible(false);
			mktemp[currentobj-1]=null;
			model[currentobj-1]=null;
			currentobj=0;
		}
		texttype.setSelectedIndex(0);
		textx.setText(null);
		texty.setText(null);
		textw.setText(null);
		texth.setText(null);
		textvar.setText(null);
		texttext.setText(null);	
	}

	class fileAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String cmd=e.getActionCommand();
			if(cmd.equals("new"))
			{
				removeall();
			}
			else if(cmd.equals("닫기"))
			{
				System.exit(0);
			}
			else if(cmd.equals("열기"))
			{
				fopen();
			}
			else if(cmd.equals(".java만들기"))
			{
				fmake();
			}
		}
	}
	class action implements ActionListener
	{
			public void actionPerformed(ActionEvent e) 
			{
				JButton cmd2=(JButton) e.getSource();
				if(cmd2.getToolTipText().equals("new"))
				{
					removeall();
				}
				else if(cmd2.getToolTipText().equals("change"))
				{
					changeshape();
				}
				else if(cmd2.getToolTipText().equals("remove"))
				{
					removethis();
				}
				else if(cmd2.getToolTipText().equals("save"))
				{
					fsave();
				}
				else if(cmd2.getToolTipText().equals("load"))
				{
					fopen();
				}
			}
	}
	class toggleaction implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JToggleButton cmd=(JToggleButton) e.getSource();
			if(cmd.getToolTipText().equals("mouse"))
			{
				shapenum=0;
			}
			else if(cmd.getToolTipText().equals("label"))
			{
				shapenum=1;
			}
			else if(cmd.getToolTipText().equals("button"))
			{
				shapenum=2;
			}
			else if(cmd.getToolTipText().equals("textfield"))
			{
				shapenum=3;
			}
		}
	}
	class changeaction implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeshape();
		}
	}
	 class ShapeAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cmd=e.getActionCommand();
			if(cmd.equals("label"))
			{
				shapenum=1;
				Toglabel.setSelected(true);
			}
			else if(cmd.equals("button"))
			{
				shapenum=2;			
				Togbutton.setSelected(true);
			}
			else if(cmd.equals("textfield"))
			{
				shapenum=3;
				Togtext.setSelected(true);
			}
			else if(cmd.equals("마우스"))
			{
				shapenum=0;
				Togmouse.setSelected(true);
			}
			else if(cmd.equals("지우기"))
			{
				removethis();
			}
		}
	}	
		class SaveAction implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				String cmd=e.getActionCommand();
				if(cmd.equals("저장"))
				{
					fsave();
				}
				else if(cmd.equals("다른이름으로 저장"))
				{
					fnamesave();
				}
			}
		}
		void fmake(){
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("java", "java");
			file.setFileFilter(filter);
			int filenum = file.showSaveDialog(null);
			if(filenum==JFileChooser.APPROVE_OPTION)
			{
			try {
				fname = file.getSelectedFile().getPath();
				int a = fname.lastIndexOf("\\");
				int b = fname.lastIndexOf(".");
				FileWriter fout=new FileWriter(fname);
				fout.write("import javax.swing.*;\n");
				fout.write("import java.awt.*;\n");
				fout.write("public class "+fname.substring(a+1,b)
						+ " extends JFrame {\n");
				fout.write(fname.substring(a+1,b) + "(){\n");
				fout.write("Container c = getContentPane();\n");
				fout.write("c.setLayout(null);\n");
				fout.write("c.setBackground(Color.WHITE);\n");
				fout.write("setSize(371, 242);\n");
				int k = 0;
				for(int i = 0; i < 100 ;i++){
					if(model[i]!= null){		
						String var;
						if(C.getVar(model[i]).equals("Default")){
							var = "var" + k++;
						}
						else{
							var = "" + C.getVar(model[i]);
						}
						if(C.getNum(model[i])==1){
							fout.write("JLabel " + var);
							fout.write(" = new JLabel(\"");
							fout.write(C.getText(model[i]) + "\");");
							fout.write('\n');
							fout.write(var + ".setBounds(" + C.getX(model[i])
								+ ','+C.getY(model[i])+','+C.getWidth(model[i])
								+','+C.getHeight(model[i])+");");
							fout.write('\n');
							fout.write("c.add("+var+");");
							fout.write('\n');
						}
						else if(C.getNum(model[i])==2){
							fout.write("JButton " + var);
							fout.write(" = new JButton(\"");
							fout.write(C.getText(model[i]) + "\");");
							fout.write('\n');
							fout.write(var + ".setBounds(" + C.getX(model[i])
								+ ','+C.getY(model[i])+','+C.getWidth(model[i])
								+','+C.getHeight(model[i])+");");
							fout.write('\n');
							fout.write("c.add("+var+");");
							fout.write('\n');
						}
						else if(C.getNum(model[i])==3){
							fout.write("JTextField " + var);
							fout.write(" = new JTextField(\"");
							fout.write(C.getText(model[i]) + "\");");
							fout.write('\n');
							fout.write(var + ".setBounds(" + C.getX(model[i])
								+ ','+C.getY(model[i])+','+C.getWidth(model[i])
								+','+C.getHeight(model[i])+");");
							fout.write('\n');
							fout.write("c.add("+var+");");
							fout.write('\n');
						}
						fout.write('\n');
					}
				}
				fout.write("setVisible(true);\n");
				fout.write("}\n");
				fout.write("public static void main"
						+ "(String[] args) {\n");
				fout.write("new "+ fname.substring(a+1,b)+"();\n");
				fout.write("}\n }\n");
				fout.flush();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
		void fsave()
		{
			JFileChooser file = new JFileChooser();
			int filenum;
			if(fname==null)
			{
				fnamesave();
			}
			else
			{
			try {
				JSONArray jarray=new JSONArray();
				for(int i=0;i<model.length;i++)
				{
					if((mktemp[i]!=null)&&(model[i]!=null))
					{		
						JSONObject obj=new JSONObject();
						obj.put("x", C.getX(model[i]));
						obj.put("y", C.getY(model[i]));
						obj.put("w", C.getWidth(model[i]));
						obj.put("h", C.getHeight(model[i]));
						obj.put("num", C.getNum(model[i]));
						obj.put("Serial", C.getSerial(model[i]));
						obj.put("var", C.getVar(model[i]));
						obj.put("text", C.getText(model[i]));
						jarray.add(obj);
					}
					else{
						JSONObject obj=new JSONObject();
						obj.put("w", 0);
						jarray.add(obj);
					}
				}
				FileWriter fout=new FileWriter(fname);
				fout.write(jarray.toString());
				fout.flush();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
		void fnamesave()
		{
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("json", "json");
			file.setFileFilter(filter);
			int filenum = file.showSaveDialog(null);
			if(filenum==JFileChooser.APPROVE_OPTION)
			{
			fname=file.getSelectedFile().getPath();
			try {
				JSONArray jarray=new JSONArray();
				for(int i=0;i<mktemp.length;i++)
				{
					if((mktemp[i]!=null)&&(model[i]!=null))
					{
						JSONObject obj=new JSONObject();
						obj.put("x", C.getX(model[i]));
						obj.put("y", C.getY(model[i]));
						obj.put("w", C.getWidth(model[i]));
						obj.put("h", C.getHeight(model[i]));
						obj.put("num", C.getNum(model[i]));
						obj.put("Serial", C.getSerial(model[i]));
						obj.put("var", C.getVar(model[i]));
						obj.put("text", C.getText(model[i]));
						jarray.add(obj);
					}
					else{
						JSONObject obj=new JSONObject();
						obj.put("w", 0);
						jarray.add(obj);
					}
				}
				FileWriter fout=new FileWriter(fname);
				fout.write(jarray.toString());
				fout.flush();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
		void fopen()
		{
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter=new FileNameExtensionFilter("json", "json");
			file.setFileFilter(filter);
			file.setFileSelectionMode(JFileChooser.FILES_ONLY);
			file.setMultiSelectionEnabled(false);
			int filenum = file.showOpenDialog(null);
			if(filenum==JFileChooser.APPROVE_OPTION)
			{	
				JSONParser jfile=new JSONParser();
				try {
					removeall();
					fname = file.getSelectedFile().getPath();
					JSONArray jarray = (JSONArray)jfile.parse(new FileReader(fname));
					for(int i=0;i<jarray.size();i++)
					{
						JSONObject jobj = (JSONObject) jarray.get(i);
						int w = Integer.parseInt(jobj.get("w").toString());
						if(w!=0){
						int x = Integer.parseInt(jobj.get("x").toString());
						int y = Integer.parseInt(jobj.get("y").toString());
						int h = Integer.parseInt(jobj.get("h").toString());
						int num = Integer.parseInt(jobj.get("num").toString());
						int Serial = Integer.parseInt(jobj.get("Serial").toString());
						String var = jobj.get("var").toString();
						String text = jobj.get("text").toString();		
						mktemp[i] = new MyPanel(x,y,w,h,num,Serial);
						mktemp[i].sfV(var);
						mktemp[i].sfT(text);
						EditorPanel.add(mktemp[i]);
						mktemp[i].setLocation(x, y);
						objnum = i + 1;			
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		void changeshape()
		{
			if(currentobj!=0)
			{
			String STYPE=texttype.getSelectedItem().toString();
			String SX=textx.getText();
			String SY=texty.getText();
			String SW=textw.getText();
			String SH=texth.getText();
			String SV=textvar.getText();
			String STEXT=texttext.getText();
			try{
			if(Integer.parseInt(SX)<0||Integer.parseInt(SY)<0||Integer.parseInt(SW)<0||Integer.parseInt(SH)<0)
			{
				JOptionPane.showMessageDialog(null, "음수로 줄일 수 없습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textx.setText(Integer.toString(C.getX(model[currentobj-1])));
				texty.setText(Integer.toString(C.getY(model[currentobj-1])));
				textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
				texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
			}
			else
			{
			if(STYPE.equals("Label"))
			{
				mktemp[currentobj-1].sfN(1);
			}
			else if(STYPE.equals("Button"))
			{
				mktemp[currentobj-1].sfN(2);
			}
			else if(STYPE.equals("TextField"))
			{
				mktemp[currentobj-1].sfN(3);
			}
			mktemp[currentobj-1].sfX(Integer.parseInt(SX));
			mktemp[currentobj-1].sfY(Integer.parseInt(SY));
			mktemp[currentobj-1].sfW(Integer.parseInt(SW));
			mktemp[currentobj-1].sfH(Integer.parseInt(SH));
			mktemp[currentobj-1].sfV(SV);
			mktemp[currentobj-1].sfT(STEXT);
			mktemp[currentobj-1].setBounds(Integer.parseInt(SX), Integer.parseInt(SY),Integer.parseInt(SW), Integer.parseInt(SH));
			mktemp[currentobj-1].setVisible(false);
			mktemp[currentobj-1].mkcomponent(C.getNum(model[currentobj-1]));
			if(C.getNum(model[currentobj-1]) == 2){
				mktemp[currentobj-1].changebutton();
			}
			else if(C.getNum(model[currentobj-1])==3){
				mktemp[currentobj-1].changetextfield();
			}
			mktemp[currentobj-1].setVisible(true);
			}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "입력값이 올바르지 않습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
				textx.setText(Integer.toString(C.getX(model[currentobj-1])));
				texty.setText(Integer.toString(C.getY(model[currentobj-1])));
				textw.setText(Integer.toString(C.getWidth(model[currentobj-1])));
				texth.setText(Integer.toString(C.getHeight(model[currentobj-1])));
			}
			}
		}
	public static void main(String[] args) {
		new myframes();
	}
}
