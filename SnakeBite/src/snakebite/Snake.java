
package snakebite;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.*;
import java.util.*;


/**
 *
 * @author rezar
 */
public class Snake extends JFrame implements KeyListener,Runnable{
    JPanel p1,p2;
    JButton[] lb = new JButton [200];
    JButton bounsfood;
    JTextArea t;
    int x = 1000,y= 500,gu = 2,directionx=1,directiony=0,speed=100,difference=0,oldx,oldy,score=0;
    int[]lbx=new int[600];
    int[]lby=new int[600];
    Point[]lbp=new Point[600];
    Point bfp=new Point();
    Thread myt;
    boolean food=false,runl=false,runr=true,runu=true,rund=true,bounsflag=true;
    Random r=new Random();
    JMenuBar mymbar;
    JMenu game,help,level;
    public void initializeValues()
    {
        gu=3;
        lbx[0]= 200;
        lby[0]= 300;
        directionx=10;
        directiony=0;
        score=0;
        food=false;
        runl=false;
        runr=true;
        runu=true;
        rund=true;
        bounsflag=true;
    }
    Snake()
    {
        super("snake");
        setSize(1000,590);
        
        //create menubar with fuction
        createbar();
        
        //initialize all variable
        initializeValues();
        p1=new JPanel();
        p2=new JPanel();
        
        //t will view score
        t=new JTextArea("Score==>"+score);
        t.setEnabled(false);
        t.setBackground(Color.black);
        
        //snake have to eat bouncefood to grow
        bounsfood=new JButton();
        bounsfood.setEnabled(false);
        
        //first snake
        createFirstSnake();
        p1.setLayout(null);
        p2.setLayout(new GridLayout(0,1));
        p1.setBounds(0,0,x,y);
        p1.setBackground(Color.blue);
        p2.setBounds(0,y,x,30);
        p2.setBackground(Color.red);
        
        p2.add(t);
        
        getContentPane().setLayout(null);
        getContentPane().add(p1);
        getContentPane().add(p2);
        show();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        //start thread
        
        myt=new Thread(this);
        myt.start();//go to run () method
                
                
        
        
                
        
        
      
           
    }
    private void createFirstSnake() {
    //initially the snake has lenght 3
    for (int i=0;i<3;i++)
    {
      lb[i]=new JButton("lb"+10);
      lb[i].setEnabled(false);
      p1.add(lb[i]);
      lb[i].setBounds(lbx[i],lby[i],10,10);
      lbx[i+1]=lbx[i]-10;
      lby[i+1]=lby[i];
               
    }    
    }
    
    public void createbar(){
        mymbar=new JMenuBar();
        game=new JMenu("GAME");
        JMenuItem newgame=new JMenuItem("new game");
        JMenuItem exit=new JMenuItem("Exit");
        newgame.addActionListener((ActionEvent e) -> {
            reset();
        });
    
    
    exit.addActionListener((ActionEvent e) -> {
        System.exit(0);
        });
    game.add(newgame);
    game.addSeparator();
    game.add(exit);
    mymbar.add(game);
    level=new JMenu("Level");
    help=new JMenu("Help");
    JMenuItem creator =new JMenuItem("Creator");
    JMenuItem instruction=new JMenuItem("instruction");
    creator.addActionListener((ActionEvent e) -> {
        JOptionPane.showMessageDialog(p2,"Name"+":Reza Radhitya Irham");
        });
    help.add(creator);
    help.add(instruction);
    mymbar.add(help);
    setJMenuBar(mymbar);
    }
    void reset(){
    initializeValues();
    p1.removeAll();
    myt.stop();
    
   creatFristSnake();
   t.setText("Score==>"+score);
   myt=new Thread(this);
   myt.start();
    }
    void growup(){
    lb[gu]=new JButton();
    lb[gu].setEnabled(false);
    p1.add(lb[gu]);
    int a=10+(10*r.nextInt(48));
    int b=10+(10*r.nextInt(23));
    lbx[gu]=a;
    lby[gu]=b;
    lb[gu].setBounds(a,b,10,10);
    gu++;
    
    
    }
    void moveForward()
    {
        for (int i=0;i<gu;i++)
       
        {
            lbp[i]=lb[i].getLocation();
            
        }
        lbx[0]+=directionx;
        lby[0]+=directiony;
        lb[0].setBounds(lbx[0],lby[0],10,10);
        for (int i=1;i<gu;i++)
        {
            lb[i].setLocation(lbp[i-1]);
            
        }
        if(lbx[0]==x)
        {
            lbx[0]=10;
        }
        else if (lbx[0]==0)
        {lbx[0]=x=10;}
        else if (lby[0]==y)
        {
           lby[0]=10; 
        }
        else if (lby[0]==0){
            lby[0]=y-10;
        }
        if (lbx[0]==lbx[gu-1]&&lby[0]==lby[gu-1])
        {
            food=false;
            score+=5;
            t.setText("Score==>"+score);
            if(score%50==0&& bounsflag==true)
        {
             p1.add(bounsfood);
             bounsfood.setBounds((10*r.nextInt(50)),(10*nextInt(25)),15,15);
             bfp=bounsfood.getLocation();
             bounsflag=false;
        }    
        }
        
        if (bounsflag==false)
            
       
          
            p1.remove(bounsfood);
            score+=100;
            t.setText("score==>"+score);
            bounsflag=true;
        }
        

    
        
              
    {
        if(food==false)
        {
            growup();
            food=true;
        }
        else{
            lb[gu-1].setBounds(lbx[gu-1],lby[gu-1],10,10);
            
            
        }
        for(int i=1;i<gu;i++)
        {
            if(lbp[0]==lbp[i]){
                t.setText("GAME OVER -"+score);
            try{
                myt.join();
                
            }   
            catch(InterruptedException ie){}
            break;
            
            }
        }
        p1.repaint();
        show();
    }

    
       
    public void KeyPressed(KeyEvent e){
        //snake move to left when left arrow pressed
        
        if(runl==true && e.getKeyCode()==37){
            directionx=-10;
            directiony=0;
            runr=false;
            runu=true;
            rund=true;
        }
        //snake move up when player press up arrow
        if(runu==true && e.getKeyCode ()==38){
            directionx=0;
            directiony=-10;
            rund=false;
            runr=true;
            runl=true;
        }
        //snake move right when player press right arrow
        if (runr==true && e.getKeyCode()==39)
        {
            directionx=+10;
            directiony=0;
            runr=false;
            runu=true;
            rund=true;
        }
        //snake move down when player pressed down arrow
        if(rund==true && e.getKeyCode()==40)
        {
        directionx=0;
        directiony=+10;
        runu=false;
        runr=true;
        runl=true;
        }
    }
    public void KeyReleased(KeyEvent e){}
    public void KeyTyped(KeyEvent e){}
    public void run(){
        for(;;){
        moveForward();
        try{
        Thread.sleep(speed);
        }
        catch(InterruptedException ie){
        }        
        }
    }

    private int nextInt(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void creatFristSnake() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    }



        
        
    
            
        
      
        
    

    

