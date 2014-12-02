
import java.awt.event.KeyEvent;
import net.useobjects.draw.drawable.CircleView;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.samples.Aircraft;
import net.useobjects.frame.MainWindow;
import net.useobjects.geom.Position;
import net.useobjects.timer.SimpleTimer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Marian
 */
public class cv01Lietadielka {
    public static void main(String[] args) throws InterruptedException {

        final int WINDOW_WIDTH = 1360;
        final int WINDOW_HEIGHT = 680;
        
        MainWindow window = new MainWindow("Bojova hra",WINDOW_WIDTH,WINDOW_HEIGHT);
        GroupView group = window.getRootGroup();
        Aircraft aircraft1 = new Aircraft(group,WINDOW_WIDTH-50, WINDOW_HEIGHT-50,3.9);
        Aircraft aircraft2 = new Aircraft(group,50,50,0.8);
        CircleView bullet1 = new CircleView(group, WINDOW_WIDTH, WINDOW_HEIGHT, 2);
        CircleView bullet2 = new CircleView(group, WINDOW_WIDTH, WINDOW_HEIGHT, 2);
        
        aircraft2.setColorToneFilter(0.5f, 1);
        aircraft1.setColorToneFilter(1f, 1);
        
        final double DEFAULT_SPEED =0;
        final double SPEED_CHANGE = 0.2;
        double speed1 = DEFAULT_SPEED;
        double speed2 = DEFAULT_SPEED;
        final double MAX_SPEED= 7;
        double pocet_guliek = 0;
        double rotacia_gulky = 0;
        
        while (true) { 
            final double ROTATION_CHANGE=0.1;
            
            double rotation1 = 0;
            double rotation2 = 0;
            double positionX1=aircraft1.getPositionX();
            double positionY1=aircraft1.getPositionY();
            
            double positionX2=aircraft2.getPositionX();
            double positionY2=aircraft2.getPositionY();
            
            if(window.isKeyDown(KeyEvent.VK_RIGHT))
            {
                rotation1 += ROTATION_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_LEFT))
            {
                rotation1 -= ROTATION_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_UP) && speed1 < MAX_SPEED)
            {
                speed1 += SPEED_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_DOWN) && speed1 > 0)
            {
                speed1 -= SPEED_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_D))
            {
                rotation2 += ROTATION_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_A))
            {
                rotation2 -= ROTATION_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_W) && speed2 < MAX_SPEED)
            {
                speed2 += SPEED_CHANGE;
            }
            if(window.isKeyDown(KeyEvent.VK_S) && speed2 > 0)
            {
                speed2 -= SPEED_CHANGE;
            }
            
            if(window.isKeyDown(KeyEvent.VK_SHIFT))
            {
               if((bullet1.getPositionX() < 0) || (bullet1.getPositionY()) < 0 || 
                    (bullet1.getPositionY() > WINDOW_HEIGHT) ||(bullet1.getPositionX() > WINDOW_WIDTH))
               {
               group.remove(bullet1);   
               bullet1 = new CircleView(group, positionX1, positionY1, 2);
               bullet1.rotate(aircraft1.getRotation());
               }

            }
            
              if(window.isKeyDown(KeyEvent.VK_SPACE))
            {
               if((bullet2.getPositionX() < 0) || (bullet2.getPositionY()) < 0 || 
                    (bullet2.getPositionY() > WINDOW_HEIGHT) ||(bullet2.getPositionX() > WINDOW_WIDTH))
               {
               group.remove(bullet2);   
               bullet2 = new CircleView(group, positionX2, positionY2, 2);
               bullet2.rotate(aircraft2.getRotation());
               }

            } 
            
            bullet1.moveForwards(30);
            bullet2.moveForwards(30);
            
            if(positionX1 < 0 )
            {
                aircraft1.setPositionX(WINDOW_WIDTH);
            }
            
            else if(positionX1 > WINDOW_WIDTH )
            {
                aircraft1.setPositionX(0);
            }
            
            else if(positionY1 > WINDOW_HEIGHT )
            {
                aircraft1.setPositionY(0);
            }
            
            else if(positionY1 < 0 )
            {
                aircraft1.setPositionY(WINDOW_HEIGHT);
            }
            
            
            
            
            
            if(positionX2 < 0 )
            {
                aircraft2.setPositionX(WINDOW_WIDTH);
            }
         
            if(positionX2 > WINDOW_WIDTH )
            {
                aircraft2.setPositionX(0);
            }
  
            if(positionY2 > WINDOW_HEIGHT )
            {
                aircraft2.setPositionY(0);
            }
            
            if(positionY2 < 0 )
            {
                aircraft2.setPositionY(WINDOW_HEIGHT);
            }
            if (Position.distance(aircraft2.getPosition(),bullet1.getPosition()) < 30)
            {
                group.remove(aircraft2);
                group.remove(aircraft1);
                aircraft1 = new Aircraft(group,WINDOW_WIDTH-50, WINDOW_HEIGHT-50,3.9);
                aircraft2 = new Aircraft(group,50,50,0.8);
                aircraft2.setColorToneFilter(0.5f, 1);
                aircraft1.setColorToneFilter(1f, 1);
            }
            if (Position.distance(aircraft1.getPosition(),bullet2.getPosition()) < 30)
            {
                group.remove(aircraft1);
                group.remove(aircraft2);
                aircraft1 = new Aircraft(group,WINDOW_WIDTH-50, WINDOW_HEIGHT-50,3.9);
                aircraft2 = new Aircraft(group,50,50,0.8);
                aircraft2.setColorToneFilter(0.5f, 1);
                aircraft1.setColorToneFilter(1f, 1);
            }
            
            SimpleTimer.sleep(30);
            aircraft1.moveForwards(speed1);
            aircraft1.rotate(rotation1);
            aircraft2.moveForwards(speed2);
            aircraft2.rotate(rotation2);
            
           
        }
        
        
    }
}
