package final_project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HangmanGame extends JFrame implements ActionListener{
    String[] words = new String[201];
    private int[] checked = new int [201]; // 나왔던 단어 체크하는 배열
    private int word_length; // 단어의 길이
    private int Num; // 맞추는 횟수
    private int level; // 게임 난이도
    char[] word1 = new char[12];
    String[] word2 = new String[12];
    String check_word;
    double wins;
    double looses;
    JButton []alpha=new JButton[26]; //a~z

    JButton begin = new JButton("BEGIN");
    JButton easy = new JButton("EASY");
    JButton medium = new JButton("HARD");
    JLabel text = new JLabel("level: ", JLabel.LEFT); // 레벨 레이블로 띄우기

    JPanel displayTOP = new JPanel();
    JPanel display1 = new JPanel();
    JPanel display2 = new JPanel();


    public HangmanGame() {
        setTitle("hangman game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850,750);
        setVisible(true);

        for(int i=0;i<alpha.length;i++)
        {
            char ch=(char)(65+i);
            alpha[i] = new JButton(Character.toString(ch));
        }
    }

    private void buildGUI() {
        for(int i=0;i<alpha.length;i++)
            alpha[i].addActionListener(this);

        begin.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);



        GridLayout all = new GridLayout(3,0); // 전체 panel에 대한 layout 설정
        FlowLayout beg = new FlowLayout();  // displayTOP에 대한 layout // begin버튼
        FlowLayout c1 = new FlowLayout(); // display2에 대한 layout
        GridLayout alphabet = new GridLayout(3,9); // display1에 대한 layout // 알파벳

        Container root = getContentPane(); // 컨테이너 타입의 객체 root
        root.setLayout(all); //grid
        root.setBackground(Color.white);

        displayTOP.add(begin);
        displayTOP.setLayout(beg);
        displayTOP.setBackground(Color.white);

        root.add(displayTOP); // TOP을 컨테이너에 등록
        display1.setLayout(alphabet);
        display1.setBackground(Color.white);

        for(int i=0;i<alpha.length;i++)
            alpha[i].setBackground(Color.GREEN);

        for(int i=0;i<alpha.length;i++)
            display1.add(alpha[i]);

        root.add(display1);

        display2.setLayout(c1);
        display2.setBackground(Color.white);
        display2.add(text);
        display2.add(easy);
        display2.add(medium);
        root.add(display2);
        setContentPane(root);

        for(int i=0;i<alpha.length;i++)
            alpha[i].setEnabled(false);

        // begin이 눌리면 그때부터 활성화 (true)
        easy.setEnabled(false);
        medium.setEnabled(true);

        words=new String[] {//철자 4~12
                "eclipse"
                ,"fair"
                ,"pain"
                ,"method"
                ,"link"
                ,"aloud"
                ,"article"
                ,"price"
                ,"stamp"
                ,"rotate"
                ,"turtle"
                ,"valley"
                ,"wonder"
                ,"ocean"
                ,"shore"
                ,"awake"
                ,"ability"
                ,"toilet"
                ,"fair"
                ,"project"
                ,"equal"
                ,"however"
                ,"item"
                ,"brick"
                ,"uniform"
                ,"earn"
                ,"photograph"
                ,"cartoon"
                ,"korea"
                ,"trust"
                ,"source"
                ,"mushroom"
                ,"violet"
                ,"belong"
                ,"period"
                ,"even"
                ,"respect"
                ,"dialog"
                ,"cotton"
                ,"dessert"
                ,"manner"
                ,"expect"
                ,"audio"
                ,"band"
                ,"challenge"
                ,"believe"
                ,"patient"
                ,"principal"
                ,"between"
                ,"delicious"
                ,"lake"
                ,"knock"
                ,"allow"
                ,"social"
                ,"anxious"
                ,"correct"
                ,"custom"
                ,"order"
                ,"lift"
                ,"receive"
                ,"swallow"
                ,"shake"
                ,"therefore"
                ,"basement"
                ,"decorate"
                ,"bark"
                ,"departure"
                ,"dozen"
                ,"exchange"
                ,"recent"
                ,"experience"
                ,"crazy"
                ,"difficult"
                ,"future"
                ,"must"
                ,"hope"
                ,"most"
                ,"report"
                ,"sour"
                ,"straight"
                ,"eraser"
                ,"fight"
                ,"gesture"
                ,"dictionary"
                ,"moment"
                ,"prince"
                ,"thick"
                ,"history"
                ,"hospital"
                ,"request"
                ,"cage"
                ,"climb"
                ,"example"
                ,"intend"
                ,"cheek"
                ,"hide"
                ,"actor"
                ,"loyal"
                ,"against"
                ,"attend"
                ,"except"
                ,"shelf"
                ,"advance"
                ,"image"
                ,"silver"
                ,"exercise"
                ,"before"
                ,"decide"
                ,"popular"
                ,"such"
                ,"along"
                ,"century"
                ,"return"
                ,"common"
                ,"guess"
                ,"religion"
                ,"rough"
                ,"already"
                ,"kingdom"
                ,"field"
                ,"forward"
                ,"although"
                ,"divide"
                ,"culture"
                ,"daily"
                ,"emperor"
                ,"intelligent"
                ,"luck"
                ,"capital"
                ,"insect"
                ,"include"
                ,"admire"
                ,"various"
                ,"medicine"
                ,"mind"
                ,"root"
                ,"journey"
                ,"proper"
                ,"anymore"
                ,"harm"
                ,"comfortable"
                ,"cabbage"
                ,"smooth"
                ,"develop"
                ,"build"
                ,"still"
                ,"issue"
                ,"curve"
                ,"regular"
                ,"movement"
                ,"stupid"
                ,"booth"
                ,"castle"
                ,"contest"
                ,"speech"
                ,"garden"
                ,"seed"
                ,"medical"
                ,"problem"
                ,"practice"
                ,"escape"
                ,"spread"
                ,"local"
                ,"supply"
                ,"amount"
                ,"final"
                ,"loaf"
                ,"blink"
                ,"command"
                ,"sink"
                ,"empty"
                ,"gate"
                ,"succeed"
                ,"abroad"
                ,"swan"
                ,"ceramic"
                ,"climate"
                ,"toward"
                ,"calm"
                ,"compare"
                ,"bill"
                ,"terminal"
                ,"downtown"
                ,"avoid"
                ,"nation"
                ,"ground"
                ,"handle"
                ,"beside"
                ,"commercial"
                ,"raise"
                ,"actual"
                ,"attention"
                ,"auditory"
                ,"direct"
                ,"mathematics"
                ,"mean"
                ,"standard"
                ,"trend"
                ,"convenience"
                ,"connect"
                ,"scale"
                ,"range"
                ,"benefit"
        };

        for(int i=0;i<checked.length;i++) {
            checked[i]=0; // 아직 선택되지 않은 단어 (0)으로 초기화
        }

        for(int i=0;i<12;i++) {
            word1[i]=' '; // character // 프로그램 안에서 맞는지 틀린지
            word2[i]=" "; // string // 화면에 내보낼 때
        }

        /* 필요한 변수들의 초기치 설정 */
        wins = 0;
        looses =0 ;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D screen2D = (Graphics2D) g;

        screen2D.drawLine(70,60,130,60);
        screen2D.drawLine(70,60,70,80);
        screen2D.drawLine(130,60,130,170);
        screen2D.drawLine(60,170,160,170);

        if(level == 0) {
            switch(Num) {
                case 1:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    break;
                case 2:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    break;
                case 3:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    break;
                case 4:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    break;
                case 5:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    break;
                case 6:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    break;
                case 7:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    break;
                case 8:
                    screen2D.drawOval(60,80,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    screen2D.drawLine(70,110,90,110); // 다른 팔
                    break;
                case 9:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    screen2D.drawLine(70,110,90,110); // 다른 팔
                    screen2D.drawLine(65,160,55,160); // 왼발
                    break;
                case 10:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    screen2D.drawLine(70,110,90,110); // 다른 팔
                    screen2D.drawLine(65,160,55,160); // 왼발
                    screen2D.drawLine(75,160,85,160); // 오른발
                    break;
            }
        }


        if(level == 1) {
            switch(Num) {
                case 1:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    break;
                case 2:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    break;
                case 3:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    break;
                case 4:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    break;
                case 5:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    break;
                case 6:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    break;
                case 7:
                    screen2D.drawOval(60,80,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    screen2D.drawLine(70,110,90,110); // 다른 팔
                    break;
                case 8:
                    screen2D.drawOval(70,90,20,20); // 얼굴
                    screen2D.drawLine(70,100,70,130); // 몸통
                    screen2D.drawLine(50,110,70,110); // 팔
                    screen2D.drawLine(70,130,55,150); // 왼 다리
                    screen2D.drawLine(70,130,85,150); // 오른 다리
                    screen2D.drawLine(85,150,75,160); // 오른 다리아래
                    screen2D.drawLine(55,150,65,160); // 왼 다리아래
                    screen2D.drawLine(70,110,90,110); // 다른 팔
                    screen2D.drawLine(65,160,55,160); // 왼발
                    screen2D.drawLine(75,160,85,160); // 오른발
                    break;
            }
        }

        screen2D.setColor(Color.MAGENTA);
        screen2D.drawString(Integer.toString(Num)+" remain times", 340, 240 );
        screen2D.setColor(Color.BLACK);
        screen2D.drawString("win: ", 290, 200 );
        screen2D.drawString(Integer.toString((int)wins), 320, 200 );
        screen2D.drawString("lose: ", 390, 200 );
        screen2D.drawString(Integer.toString((int)looses), 430, 200 );

        //답이 틀린 단어에 대한 화면 표시
        //답이 맞은 단어에 대한 화면 표시
        for(int i=4;i<=12;i++){
            screen2D.setColor(Color.BLACK);
            for(int j=0;j<word_length;j++){
                screen2D.drawString(word2[j], 300+(j*20), 160 );
            }
        }
    }
    private void wordSelect() {
        double sel_num = Math.random() * 201;// 0~200.xx
        int selection = (int) Math.floor(sel_num); // 0~200
        while(true) {  /* 이미 선택된 단어가 다시 선택되는 경우는 배제해야 함 */ // 나왔던 단어가 또 나오면 안됨
            if(checked[selection] == 0) { // 아직 뽑힌 단어가 아니라면 0
                check_word=words[selection];
                checked[selection]=1;
                break;
            }
            else {
                selection = (int) Math.floor(Math.random() * 4);
            }
        }
        String sel_Word;
        if(words[selection] != null) { // 고른 단어가 null이 아닐때까지
            sel_Word = words[selection].toLowerCase();
            word_length = sel_Word.length();

            char[] temp = sel_Word.toCharArray();   // character 배열로 변환
            for(int index1 = 0; index1 < word_length; index1++) {
                word1[index1] = temp[index1];
            }
            for(int index2 = 0; index2 < word_length; index2++) {
                word2[index2] = "_"; // .또는 _로 유저에게 단어의 철자 수를 알려줌
            }
        }
    }
    public void word_reset() {
        for(int i=0;i<12;i++) {
            word2[i]="_";
        }
        wordSelect();
    }
    public void spell_check(char spell) {
        int check_key = 0;
        for(int i=0; i<12; i++) {
            if(word1[i] != ' ') {
                if(word1[i] == spell) {
                    word2[i] = "" + spell;
                    check_key = 1;
                    repaint();
                }
            }
        }

        if(check_key == 0) {
            Num--;
            repaint();
        }
        display();
        repaint();
    }

    public void display() {
        int count=0;
        while (word2[count]!="_"){
            count++;
        }
        if(count==word_length){
            for(int i=0;i<alpha.length;i++)
                alpha[i].setEnabled(false);

            begin.setEnabled(true);
            if(level == 0) {
                medium.setEnabled(true);
            } else if(level == 1) {
                easy.setEnabled(true);
            }
            wins++;
            repaint();

        }

        if(Num <= 0) {  // 단어 추정 실패

            // 버튼 눌릴 수 없게 만듬
            for(int i=0;i<alpha.length;i++)
                alpha[i].setEnabled(false);

            // 정답을 화면에 표시
            for(int i=0;i<12;i++){
                word2[i] = "" + word1[i];
            }

            begin.setEnabled(true);
            // level에 따른 버튼 활성화 작업
            if(level == 0) {
                medium.setEnabled(true);
            } else if(level == 1) {
                easy.setEnabled(true);
            }
            looses++;
            repaint();

        }

    }

    public void actionPerformed(ActionEvent event) {
        String typed = event.getActionCommand(); // 어떤 버튼을 눌렀는지 알려줌
        if(typed.equals("BEGIN")) {
            for(int i=0; i<12; i++){
                word1[i] = ' ';
                word2[i] = "_";
            }

            easy.setEnabled(false);
            medium.setEnabled(false);

            if(level == 0) {
                Num = 10;
            }
            else if(level == 1) {
                Num = 8;
            }

            repaint();
            for(int i=0;i<alpha.length;i++)
                alpha[i].setEnabled(true);

            begin.setEnabled(false);
            word_reset();


        }
        for(int i=0;i<alpha.length;i++) {
            if (typed.equals(alpha[i].getText())) {
                alpha[i].setEnabled(false);
                spell_check((char)(97+i));
            }
        }

        if(typed.equals("EASY..")) {
            try {
                Thread.sleep(50); //스레드, 5초뒤에 시작
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            easy.setEnabled(false);
            medium.setEnabled(true);
            level = 0;
            repaint();
        }
        if(typed.equals("HARD..")) {
            try {
                Thread.sleep(50); //스레드, 5초뒤에 시작
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            easy.setEnabled(true);
            medium.setEnabled(false);
            level = 1;
            repaint();
        }
    }
    public static void main(String [] args) {
        HangmanGame h = new HangmanGame();
        h.buildGUI();
    }
}