import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=20;
    static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    int bodyParts=6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction='D';
    boolean running=false;
    Timer timer;
    Random random;

    GamePanel()
    {
        random=new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame ()
    {
        newApple();
        running=true;
        timer=new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g)  // This Method is used for making a grid.And For creating and setting an apple.
    {
        if(running)
        {
            ///////This block of code is for  making GRID and its optional.////////

                    /*for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                        g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); // For making Grid.
                        g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }*/
            g.setColor(Color.RED);// For Creating and Setting Apple position Apple.
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //Creating the Snake and its positions.
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }  else {
                    // g.setColor(new Color(100, 200, 0));
                    //This is for generating random colors in snake.Or MULTICOLOR SNAKE.
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }
            }
            //For showing Score on the top of the screen.
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free",Font.BOLD,30));
            FontMetrics matrics=getFontMetrics(g.getFont());
            g.drawString("Score : "+applesEaten,(SCREEN_WIDTH - matrics.stringWidth("Score : "+applesEaten))/2,g.getFont().getSize());

        }
        else
        {
            gameOver(g);
        }
    }//draw Method ends here.

    public void newApple() // This Method is used to set the locations of the apple randomly in the grid.
    {
        appleX=random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY=random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }

    public void move() //Designing the snake positions.
    {
        for(int i=bodyParts; i>0; i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction)
        {
            case 'W':
                y[0]=y[0] - UNIT_SIZE;
                break;
            case 'S':
                y[0]=y[0] + UNIT_SIZE;
                break;
            case 'A':
                x[0]=x[0] - UNIT_SIZE;
                break;
            case 'D':
                x[0]=x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple()
    {
        if((x[0]==appleX)&& (y[0]==appleY))
        {
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }
    public void checkCollision()
    {
        //Checks if head collides with the body
        for(int i=bodyParts; i>0; i--)
        {
            if((x[0]==x[i])&&(y[0]==y[i]))
            {
                running=false;
            }
        }
        //Checks if the head touches with left border.
        if(x[0] < 0)
        {
            running=false;
        }
        //Checks if the head touched with right border.
        if(x[0] > SCREEN_WIDTH)
        {
            running=false;
        }
        //Checks if head touches the Top border.
        if(y[0] < 0)
        {
            running=false;
        }
        //Checks if head touches the Bottom of border.
        if(y[0] > SCREEN_HEIGHT)
        {
            running=false;
        }
        if(!running)
        {
            timer.stop();
        }
    }
    public void gameOver(Graphics g)
    {
        //For Showing Score on the game_over Screen.
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free",Font.ITALIC,30));
        FontMetrics matrics1=getFontMetrics(g.getFont());
        g.drawString("Score : "+applesEaten,(SCREEN_WIDTH - matrics1.stringWidth("Score : "+applesEaten))/2,g.getFont().getSize());

        //For Showing game over Screen.
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics matrics2=getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH - matrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if(running)// This is made to move the snake and ensure the apple and collisions.
        {
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())  // This switch is made for key to works.
            {
                case KeyEvent.VK_LEFT:
                    if(direction !='D') {
                        direction = 'A';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction !='A')
                    {
                        direction='D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction !='S') {
                        direction='W';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction !='W') {
                        direction='S';
                    }
                    break;
            }//Switch ends here.
        }
    }
}

