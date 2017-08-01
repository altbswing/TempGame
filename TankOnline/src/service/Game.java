package service;

import animation.Missile;
import message.Player;
import message.Shot;
import message.Wind;

public interface Game {

    public static final String VERSION = "2.5";

    public static final double FPS = 40.0; // 桢/秒
    public static final long SLEEP = (long) (1000 / FPS);

    public static final int NOT_START = -1;
    public static final int GO_ON = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;
    public static final int DRAW = 3;

    /**
     * 从控制器接收增加角度指令
     * 
     * @author xiaoE
     * */
    public void angleUp();

    /**
     * 从控制器接收减小角度指令
     * 
     * @author xiaoE
     * */
    public void angleDown();

    /**
     * 从控制器接收移动指令
     * 
     * @author xiaoE
     * */
    public void move(int i);

    /**
     * 从控制器接收增加力量指令
     * 
     * @author xiaoE
     * */
    public void powerUp();

    /**
     * 从控制器接收发射指令
     * 
     * @author xiaoE
     * */
    public void shot();

    /**
     * 是否可控制
     * 
     * @author xiaoE
     * */
    public boolean canCtrl();

    /**
     * 获得风向
     * 
     * @author xiaoE
     * */
    public Wind wind();

    /**
     * 获得发射参数
     * 
     * @author xiaoE
     * */
    public Shot getShot();

    /**
     * 获得导弹对象
     * 
     * @author xiaoE
     * */
    public Missile missile();

    /**
     * 导弹爆炸后告诉服务器：我射完了
     * 
     * @author xiaoE
     * */
    public void shotFinish();

    /**
     * 击中
     * 
     * @author xiaoE
     * */
    public void hit(int mesX);

    /**
     * 是否主机玩家行动
     * 
     * @author xiaoE
     * */
    boolean isP1Action();

    /**
     * 判断游戏是否已开始
     * 
     * @author xiaoE
     * */
    public int getWinner();

    /**
     * 开始游戏
     * 
     * @author xiaoE
     * */
    public void start();

    public Player p1();

    public Player p2();

    /**
     * 技能-双倍攻击
     * 
     * @author xiaoE
     * */
    public void skillDouble();

    /**
     * 技能-狂暴
     * 
     * @author xiaoE
     * */
    public void skillRage();

    /**
     * 技能-护盾
     * 
     * @author xiaoE
     * */
    public void skillShield();

    /**
     * 技能-加速
     * 
     * @author xiaoE
     * */
    public void skillSpeed();

}
