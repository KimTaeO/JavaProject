import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Game extends JFrame implements ActionListener, Runnable {
    ImageIcon target = new ImageIcon(Game.class.getResource("./image/target.png"));
    private JButton targetButton[] = new JButton[60];
    private JButton start = new JButton("시작");
    private JButton end = new JButton("종료");
    private JLabel scoreIndicate = new JLabel("점수 : 0");
    private JLabel remainTimeSpace = new JLabel("시간 => 0:10");
    private BorderLayout entireSpace = new BorderLayout(10, 10);
    private JPanel buttonSpace = new JPanel();
    private GridLayout ButtonReplaceSpace = new GridLayout(6, 10);
    private JPanel jp2 = new JPanel();
    private GridLayout gl2 = new GridLayout(1, 2);
    private JPanel jp21 = new JPanel();
    private FlowLayout fl21 = new FlowLayout(FlowLayout.RIGHT);
    private int random = 0;
    private int count = -1;

    public Game(String title) {

        super(title);
        this.init();
        this.start();

        super.setSize(520, 390);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int xpos = (int) (screen.getWidth() / 2 - super.getWidth() / 2);

        int ypos = (int) (screen.getHeight() / 2 - super.getHeight() / 2);

        super.setLocation(xpos, ypos);

        super.setResizable(false);

        super.setVisible(true);

    }

    public void init() {

        Container con = this.getContentPane();
        buttonSpace.setBackground(Color.WHITE);
        con.setLayout(entireSpace);
        con.add("North", remainTimeSpace);
        con.add("Center", buttonSpace);
        buttonSpace.setLayout(ButtonReplaceSpace);

        for (int i = 0; i < 60; ++i) {
            targetButton[i] = new JButton();
            targetButton[i].setFocusPainted(false);
            targetButton[i].setContentAreaFilled(false);
            buttonSpace.add(targetButton[i]);
        }

        off_button();
        con.add("South", jp2);
        jp2.setLayout(gl2);
        jp2.add(scoreIndicate);
        jp2.add(jp21);
        jp21.setLayout(fl21);
        jp21.add(start);
        jp21.add(end);

    }

    public void start() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.addActionListener(this);
        end.addActionListener(this);
        for (int i = 0; i < 60; ++i) {
            targetButton[i].addActionListener(this);

        }

    } //end


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {

            remainTimeSpace.setText("시간 => 0:10");
            scoreIndicate.setText("점수 : 0");
            count = -1;
            Thread th = new Thread(this);
            th.start();
            on_button();
            random(0);

        } else if (e.getSource() == end) {
            System.exit(0);
        }

        for (int i = 0; i < 60; ++i) {
            if (e.getSource() == targetButton[i]) {
                random(i);
            }
        }

    }


    public void off_button() {
        for (int i = 0; i < 60; ++i) {
            targetButton[i].setEnabled(false);
        }
    }


    public void on_button() {
        for (int i = 0; i < 60; ++i) {
            targetButton[i].setEnabled(true);
        }

    }


    public void run() {
        int time = 10;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            time--;
            if (time == 0) {
                remainTimeSpace.setText("게임이 끝났습니다.");
                off_button();
                break;
            }
            remainTimeSpace.setText("시간 => 0:0" + time);

        }

    }


    public void random(int i) {
        if (i != random) return;
        count++;
        targetButton[random].setIcon(null);
        random = (int) (Math.random() * 60);
        targetButton[random].setIcon(target);
        scoreIndicate.setText("점수 : " + count);
    }
}
