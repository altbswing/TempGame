package start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import service.Client;
import service.Game;
import service.Server;
import util.IPUtil;
import util.MyTextArea;

/**
 * 连接对话框
 * 
 * @author 小翼
 * */
public class FrameLink extends JFrame implements ActionListener {
	private static final long serialVersionUID = 5521153304420086882L;

	private Client client;
	private Server server;

	private JLabel mainIp; // 主机IP地址
	private JTextField mainPort; // 主机端口号
	private JTextField mainName; // 主机名字
	private JTextField joinIp; // 加入IP地址
	private JTextField joinName; // 加入端口号
	private JTextField joinPort; // 副机名字

	private JButton cGame; // 创建游戏
	private JButton jGame; // 加入游戏
	private JButton startLocolGame; // 单机娱乐
	private JButton readyNetGame; // 准备就绪（副机按纽）
	private JButton startNetGame; // 开始游戏（主机按纽）

	private MyTextArea messageArea; // 连接信息

	private Font font = new Font("仿宋", Font.BOLD, 14);

	/**
	 * 构造方法
	 * 
	 * @author 小翼
	 */
	public FrameLink() {
		init();
		createButtons();
		createTextFields();
		addCom();
		this.setVisible(true);
	}

	/**
	 * Frame参数设置
	 * 
	 * @author 小翼
	 */
	private void init() {
		this.setLayout(new BorderLayout());
		this.setTitle("Java玩具坦克v" + Game.VERSION + " by小翼");
		this.setIconImage(new ImageIcon("Graphics/icon/icon.png").getImage());
		this.setSize(720, 384);
		this.center();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * 居中方法
	 * 
	 * @author 刘苍松
	 * */
	private void center() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		Dimension w = this.getSize();
		int x = (screen.width - w.width) / 2;
		int y = (screen.height - w.height) / 2;
		this.setLocation(x, y - 16);
	}

	/**
	 * 创建按钮
	 * 
	 * @author 小翼
	 */
	private void createButtons() {
		cGame = new JButton("创建游戏");
		jGame = new JButton("加入游戏");
		startLocolGame = new JButton("单机娱乐");
		readyNetGame = new JButton("准备就绪");
		readyNetGame.setEnabled(false);
		startNetGame = new JButton("开始游戏");
		startNetGame.setEnabled(false);
	}

	/**
	 * 创建文本域
	 * 
	 * @author 小翼
	 */
	private void createTextFields() {
		mainIp = new JLabel(IPUtil.IP());
		mainPort = myTextField("12345", 5);
		joinIp = myTextField("127.0.0.1", 16);
		joinPort = myTextField("12345", 5);
		mainName = myTextField("黄金主", 12);
		joinName = myTextField("秒退专业户", 12);
	}

	/**
	 * 添加组件
	 * 
	 * @author 小翼
	 */
	private void addCom() {
		this.add(createPanel(), BorderLayout.CENTER);
		cGame.addActionListener(this);
		jGame.addActionListener(this);
		readyNetGame.addActionListener(this);
		startNetGame.addActionListener(this);
		startLocolGame.addActionListener(this);
	}

	/**
	 * 创建主面板
	 * 
	 * @author 小翼
	 */
	private JPanel createPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		panel.add(linkPanel(), BorderLayout.WEST);
		panel.add(gamePanel(), BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 西：连接管理面板
	 * 
	 * @author 小翼
	 */
	private JPanel linkPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ipPanel("我是主机", "我的Ip：", mainIp, mainPort, mainName, cGame),
				BorderLayout.NORTH);
		panel.add(ipPanel("我要加入", "加入Ip：", joinIp, joinPort, joinName, jGame),
				BorderLayout.CENTER);
		panel.add(localGaemPanel(), BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * 西：北/中 创建单用户连接管理面版
	 * 
	 * @author 小翼
	 * */
	private JPanel ipPanel(String title, String lbName, JComponent tfIp,
			JTextField tfPort, JTextField tfName, JButton btnGame) {
		JPanel panelIp = createIp(lbName, tfIp, tfPort);
		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelName.add(new JLabel("我的名字："));
		panelName.add(tfName);
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelButton.add(btnGame);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder(title));
		panel.add(panelName, BorderLayout.NORTH);
		panel.add(panelIp, BorderLayout.CENTER);
		panel.add(panelButton, BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * 西：北/中：中 Ip显示
	 * 
	 * @author 小翼
	 * */
	private JPanel createIp(String lbName, JComponent tfIp, JTextField tfPort) {
		JPanel panelIp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelIp.add(new JLabel(lbName));
		panelIp.add(tfIp);
		JPanel panelPort = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelPort.add(new JLabel("："));
		panelPort.add(tfPort);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelIp, BorderLayout.WEST);
		panel.add(panelPort, BorderLayout.EAST);
		return panel;
	}

	/**
	 * 西：南：单机娱乐面板
	 * 
	 * @author 小翼
	 * */
	private JPanel localGaemPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("单机娱乐"));
		panel.add(startLocolGame);
		return panel;
	}

	/**
	 * 中：信息面板
	 * 
	 * @author 小翼
	 */
	private JPanel gamePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("信息"));
		panel.add(messagePanel(), BorderLayout.CENTER);
		panel.add(btnPanel(), BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * 中：中：信息文本域
	 * 
	 * @author 小翼
	 */
	private JScrollPane messagePanel() {
		JScrollPane panel = new JScrollPane();
		messageArea = new MyTextArea();
		messageArea.setBorder(BorderFactory.createLoweredBevelBorder());
		messageArea.setLineWrap(true);
		messageArea.setFont(font);
		addVersionMessage();
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.getViewport().add(messageArea);
		return panel;
	}

	/**
	 * 添加游戏教程版本信息
	 * 
	 * @author xiaoE
	 * */
	private void addVersionMessage() {
		messageArea.setFont(font);
		messageArea.append("Java玩具坦克v" + Game.VERSION + " ——小翼出品");
		messageArea.append("\n");
		messageArea.append("\n★操作方法：");
		messageArea.append("\n左右或AD键移动，上下或WS键调整角度");
		messageArea.append("\n按住空格蓄力，放开空格发射");
		messageArea.append("\n数字1：强攻，50MP，本回合造成2倍伤害");
		messageArea.append("\n数字2：狂暴，20MP，本回合造成2倍伤害，受到2倍伤害");
		messageArea.append("\n数字3：护盾，30MP，使一次伤害减半(瞬发技能)");
		messageArea.append("\n数字4：加速，10MP，本回合移动速度提高100%");
		messageArea.append("\n注：增加伤害类技能不能叠加");
		messageArea.append("\n数字5-9：切换背景。");
		messageArea.append("\n欢迎提交bug，QQ:65445746");
		messageArea.append("\n==============================================\n");
	}

	/**
	 * 中：南：按钮
	 * 
	 * @author 小翼
	 */
	private JPanel btnPanel() {
		JPanel panel = new JPanel();
		panel.add(readyNetGame);
		panel.add(startNetGame);
		return panel;
	}

	/**
	 * 文本域样式
	 * 
	 * @author 小翼
	 */
	private JTextField myTextField(String str, int size) {
		JTextField jtf = new JTextField(str, size);
		jtf.setFont(this.font);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		return jtf;
	}

	/**
	 * 事件监听
	 * 
	 * @author 小翼
	 * */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cGame) {
			if (rightName(mainName.getText())) {
				createGame();
			}
		} else if (e.getSource() == jGame) {
			if (rightName(joinName.getText())) {
				joinGame();
			}
		} else if (e.getSource() == startLocolGame) {
			localGame();
		} else if (e.getSource() == readyNetGame) {
			readyGame();
		} else if (e.getSource() == startNetGame) {
			startGame();
		}
	}

	private boolean rightName(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) <= 122) {
				length += 1;
			} else {
				length += 2;
			}
			if (length > 10) {
				showMessage("您的名字过长");
				return false;
			}
		}
		if (length <= 0) {
			showMessage("名字不能为空");
			return false;
		}
		return true;
	}

	/**
	 * 显示信息，一般外部调用
	 * 
	 * @author xiaoE
	 * */
	public void showMessage(String str) {
		messageArea.showMessage(str);
	}

	/**
	 * 创建游戏
	 * 
	 * @author 小翼
	 * */
	private void createGame() {
		linkButtonSwitch(false);
		server = new Server(this);
		server.setMainName(mainName.getText());
	}

	/**
	 * 加入游戏
	 * 
	 * @author 小翼
	 * */
	private void joinGame() {
		linkButtonSwitch(false);
		try {
			client = new Client(this);
			readyNetGame.setEnabled(true);
		} catch (ConnectException e) {
			showMessage("错误：无法连接到 " + joinIp.getText() + ":"
					+ joinPort.getText());
			linkButtonSwitch(true);
		} catch (Exception e) {
			showMessage(e.toString());
			linkButtonSwitch(true);
		}
	}

	/**
	 * 本地游戏
	 * 
	 * @author 小翼
	 * */
	private void localGame() {
		// linkButtonSwitch(false);
		messageArea.showMessage("单机模块暂未创建");
	}

	/**
	 * 开始游戏
	 * 
	 * @author 小翼
	 * */
	private void startGame() {
		server.startGame();
	}

	/**
	 * 准备就绪
	 * 
	 * @author 小翼
	 * */
	private void readyGame() {
		try {
			client.ready();
			readyNetGame.setEnabled(false);
		} catch (Exception e) {
			messageArea.showMessage(e.toString());
		}
	}

	/**
	 * 开关按纽
	 * 
	 * @author xiaoE
	 * */
	public void linkButtonSwitch(boolean b) {
		cGame.setEnabled(b);
		jGame.setEnabled(b);
		startLocolGame.setEnabled(b);
	}

	// ======================= get方法 ============================
	/**
	 * 获得客户端Ip和端口号
	 * 
	 * @author xiaoE
	 * */
	public String[] joinIp() {
		String[] ipp = { this.joinIp.getText().toString(),
				this.joinPort.getText().toString() };
		return ipp;
	}

	/**
	 * 获得主机Ip和端口号
	 * 
	 * @author xiaoE
	 * */
	public String[] mainIp() {
		String[] ipp = { this.mainIp.getText().toString(),
				this.mainPort.getText().toString() };
		return ipp;
	}

	/**
	 * 获得加入玩家名字
	 * 
	 * @author xiaoE
	 * */
	public String joinName() {
		return this.joinName.getText();
	}

	/**
	 * 获得主机玩家名字
	 * 
	 * @author xiaoE
	 * */
	public String mainName() {
		return this.mainName.getText();
	}

	public void startOn() {
		this.startNetGame.setEnabled(true);
	}
}
