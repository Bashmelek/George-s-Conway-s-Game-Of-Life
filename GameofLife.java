import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.URL;
import java.applet.*;
import java.net.MalformedURLException;
import java.io.*;
import java.net.*;
import java.util.*;

class TestCell
{
   public TestCell()
   {
   }
}

class GameCell
{
   private int side;
   private int status;
   private int startx;
   private int starty;
   private static int size = 30;
   
   private int adjupind;
   private int adjdownind;
   private int adjrightind;
   private int adjleftind;
   
   private int adjneind;
   private int adjseind;
   private int adjswind;
   private int adjnwind;
   
   
   //=================================
   //
   // List of all of the different states:
   //
   // 0. The dead state.
   //
   //=================================
   public GameCell()
   {
      adjupind = -1;
      adjdownind = -1;
      adjrightind = -1;
      adjleftind = -1;
   
      adjneind = -1;
      adjseind = -1;
      adjswind = -1;
      adjnwind = -1;
      side = 0;
      //size = 30;
      setStatus(0);
   }
   
   public void setLoc(int ix, int iy)
   {
      startx = ix;
      starty = iy;
   }
   
   public void setStatus(int changeto)
   {
      status = changeto;
   }
   
   public int getStatus()
   {
      return status;
   }
   public void setAdj(int up, int down, int left, int right, int ne, int se, int sw, int nw)
   {
      adjupind = up;
      adjdownind = down;
      adjrightind = right;
      adjleftind = left;
   
      adjneind = ne;
      adjseind = se;
      adjswind = sw;
      adjnwind = nw;
   }
   
   public static int getSize()
   {
      return size;
   }
   
   public int getCoord(char xy)
   {
      int coord;
      if(xy == 'x')
      {
         coord = startx;
      }
      else
      {
         coord = starty;
      }
      
      return coord;
   }
   
   public int getAdjIndex(int whichone)
   {
      int indexer = -1;
      
      if(whichone == 0)
      {
         indexer = adjupind;
      }
      else if(whichone == 1)
      {
         indexer = adjdownind;
      }
      else if(whichone == 2)
      {
         indexer = adjleftind;
      }
      else if(whichone == 3)
      {
         indexer = adjrightind;
      }
      else if(whichone == 4)
      {
         indexer = adjneind;
      }
      else if(whichone == 5)
      {
         indexer = adjseind;
      }
      else if(whichone == 6)
      {
         indexer = adjswind;
      }
      else if(whichone == 7)
      {
         indexer = adjnwind;
      }
            
      return indexer;
   }
}

class CellGrid
{
   private int startx = 20;
   private int starty = 20;
   private int spacex = 3;
   private int spacey = 3;
   private int thistype = 0;
   private GameCell[] thegrid;
   private int CellCols = 20;
   private int CellRows = 20;
   private int diagadj;
   private int stepcount;
   
   public CellGrid()
   {
      stepcount = 0;
      thegrid = new GameCell[400];
      diagadj = 0;
      int[] tempadj = new int[8];
      
      for(int i = 0; i < 400; i++)
      {
         thegrid[i] = new GameCell();
      }
      
      if(thistype == 0)
      {
         for(int c = 0; c < 400; c++)
         {
            //GameCell celly = new GameCell();//thegrid[c];
            //celly.setLoc(10, 10); 
            //System.out.print(c);
            thegrid[c].setLoc(startx + GameCell.getSize() *(c % 20), starty + GameCell.getSize() *(c / 20)  );
            
            if(diagadj == 0)
            {
               tempadj[4] = -1;
               tempadj[5] = -1;
               tempadj[6] = -1;
               tempadj[7] = -1;
               if(c < 20)
               {
                  tempadj[0] = -1;
               }
               else
               {
                  tempadj[0] = c - 20;
               }
               
               if(c >= 380)
               {
                  tempadj[1] = -1;
               }
               else
               {
                  tempadj[1] = c + 20;
               }
               if(c % 20 == 0)
               {
                  tempadj[2] = -1;
               }
               else
               {
                  tempadj[2] = c - 1;
               }
               if(c % 20 == 19)
               {
                  tempadj[3] = -1;
               }
               else
               {
                  tempadj[3] = c + 1;
               }
            }
            else
            {
               if(c < 20)
               {
                  tempadj[0] = -1;
                  tempadj[4] = -1;
                  tempadj[7] = -1;
                  tempadj[1] = c + 20;
                  
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[6] = -1;
                     tempadj[3] = c + 1;
                     tempadj[5] = c + 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[3] = -1;
                     tempadj[5] = -1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[5] = c + 20 + 1;
                  }
               }
               else if(c >= 380)
               {
                  tempadj[1] = -1;
                  tempadj[5] = -1;
                  tempadj[6] = -1;
                  tempadj[0] = c - 20;
                  
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[7] = -1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = -1;//c + 1;
                     tempadj[4] = -1;//c - 20 + 1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                  }
               }
               else
               {
                  tempadj[0] = c - 20;
                  tempadj[1] = c + 20;
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[6] = -1;
                     tempadj[7] = -1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                     tempadj[5] = c + 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = -1;//c + 1;
                     tempadj[4] = -1;//c - 20 + 1;
                     tempadj[5] = -1;//c + 20 + 1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                     tempadj[5] = c + 20 + 1;
                  }
               }
               //------------------------
               /*
               if(c >= 380)
               {
                  tempadj[1] = -1;
               }
               else
               {
                  tempadj[1] = c + 20;
               }
               if(c % 20 == 0)
               {
                  tempadj[2] = -1;
               }
               else
               {
                  tempadj[2] = c - 1;
               }
               if(c % 20 == 19)
               {
                  tempadj[3] = -1;
               }
               else
               {
                  tempadj[3] = c + 1;
               }
               */
               //-----------------------------------
            }//----------------------------------------end of diagonal case
            
            thegrid[c].setAdj(tempadj[0], tempadj[1], tempadj[2], tempadj[3], tempadj[4], tempadj[5], tempadj[6], tempadj[7]);
         }//end of for loop initializing cells
         
      }
   }
   
   public CellGrid(int type)
   {
   }
   
   public int getType()
   {
      return thistype;
   }
   
   public void setdiagadj(int newdiag)
   {
      diagadj = newdiag;
      redoAdj();
   }
   
   private void redoAdj()
   {
      int[] tempadj = new int[8];
      for(int c = 0; c < 400; c++)
         {
            //GameCell celly = new GameCell();//thegrid[c];
            //celly.setLoc(10, 10); 
            //System.out.print(c);
            //thegrid[c].setLoc(startx + GameCell.getSize() *(c % 20), starty + GameCell.getSize() *(c / 20)  );
            
            if(diagadj == 0)
            {
               tempadj[4] = -1;
               tempadj[5] = -1;
               tempadj[6] = -1;
               tempadj[7] = -1;
               if(c < 20)
               {
                  tempadj[0] = -1;
               }
               else
               {
                  tempadj[0] = c - 20;
               }
               
               if(c >= 380)
               {
                  tempadj[1] = -1;
               }
               else
               {
                  tempadj[1] = c + 20;
               }
               if(c % 20 == 0)
               {
                  tempadj[2] = -1;
               }
               else
               {
                  tempadj[2] = c - 1;
               }
               if(c % 20 == 19)
               {
                  tempadj[3] = -1;
               }
               else
               {
                  tempadj[3] = c + 1;
               }
            }
            else
            {
               if(c < 20)
               {
                  tempadj[0] = -1;
                  tempadj[4] = -1;
                  tempadj[7] = -1;
                  tempadj[1] = c + 20;
                  
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[6] = -1;
                     tempadj[3] = c + 1;
                     tempadj[5] = c + 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[3] = -1;
                     tempadj[5] = -1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[5] = c + 20 + 1;
                  }
               }
               else if(c >= 380)
               {
                  tempadj[1] = -1;
                  tempadj[5] = -1;
                  tempadj[6] = -1;
                  tempadj[0] = c - 20;
                  
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[7] = -1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = -1;//c + 1;
                     tempadj[4] = -1;//c - 20 + 1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                  }
               }
               else
               {
                  tempadj[0] = c - 20;
                  tempadj[1] = c + 20;
                  if(c % 20 == 0)
                  {
                     tempadj[2] = -1;
                     tempadj[6] = -1;
                     tempadj[7] = -1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                     tempadj[5] = c + 20 + 1;
                  }
                  else if(c % 20 == 19)
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = -1;//c + 1;
                     tempadj[4] = -1;//c - 20 + 1;
                     tempadj[5] = -1;//c + 20 + 1;
                  }
                  else
                  {
                     tempadj[2] = c - 1;
                     tempadj[6] = c + 20 - 1;
                     tempadj[7] = c - 20 - 1;
                     tempadj[3] = c + 1;
                     tempadj[4] = c - 20 + 1;
                     tempadj[5] = c + 20 + 1;
                  }
               }
               //------------------------
               /*
               if(c >= 380)
               {
                  tempadj[1] = -1;
               }
               else
               {
                  tempadj[1] = c + 20;
               }
               if(c % 20 == 0)
               {
                  tempadj[2] = -1;
               }
               else
               {
                  tempadj[2] = c - 1;
               }
               if(c % 20 == 19)
               {
                  tempadj[3] = -1;
               }
               else
               {
                  tempadj[3] = c + 1;
               }
               */
               //-----------------------------------
            }//----------------------------------------end of diagonal case
            
            thegrid[c].setAdj(tempadj[0], tempadj[1], tempadj[2], tempadj[3], tempadj[4], tempadj[5], tempadj[6], tempadj[7]);
         }
   }
   
   public int getCellStatus(int cellno)
   {
      int cellstatus = -1;
      
      cellstatus = thegrid[cellno].getStatus();
      
      return cellstatus;
   }
   
   public int getCellAtLocIndex(int x, int y)
   {
      int indexer = -1;
      int xslot;//represents which number 0-19 this is (in the order of left to right) among columns
      int yslot;//0-19 among rows
      if(x >= startx)
      {
         xslot = (x - startx) / GameCell.getSize();// + 0;
         if(xslot >= 20)
         {
            xslot = -1;
         }
      }
      else
      {
         xslot = -1;
      }
      //System.out.print(xslot);
      if(y >= starty)
      {
         yslot = (y - starty) / GameCell.getSize();
         if(yslot >= 20)
         {
            yslot = -1;
         }
      }
      else
      {
         yslot = -1;
      }
      if(yslot == -1 || xslot == -1)
      {
         indexer = -1;
      }
      else
      {
         indexer = CellCols * yslot + xslot;
      }
      System.out.print(indexer);
      return indexer;
   }
   
   public int getCellAtIndexStatus(int indexer)
   {
      int thestatus = thegrid[indexer].getStatus();
      return thestatus;
   }
   
   public void setCellAtIndexStatus(int indexer, int newstat)
   {
      thegrid[indexer].setStatus(newstat);
   }
   
   public int getCellAtIndexCoord(char xy, int indexer)
   {
      int thecoord = thegrid[indexer].getCoord(xy);
      
      return thecoord;
      
   }
   
   public int updateGrid()
   {
      int totalstats = 12;//6;//including death
   
      int problems = 0;
      int[] adjstats = new int[totalstats];
      
      GameCell[] oldgrid = new GameCell[400];
      
      for(int b = 0; b < 400; b++)
      {
         oldgrid[b] = new GameCell();
         oldgrid[b].setStatus( thegrid[b].getStatus() );
      }
      
      int tempdex = -1;
      int tempdextoo = -1;
      for(int v = 0; v < 400; v++)
      {
         for(int p = 0; p < totalstats; p++)
         {
            adjstats[p] = 0;
         }
      
         for(int d = 0; d < 8; d++)
         {
            tempdex = -1;
            tempdextoo = -1;
            tempdextoo = thegrid[v].getAdjIndex(d);
            if(v == 1)
            {
               //System.out.print(tempdextoo);
            }
            if(tempdextoo != -1)
            {
               tempdex = oldgrid[tempdextoo].getStatus();
            }
            if(tempdex != -1)
            {
               adjstats[tempdex] = adjstats[tempdex] + 1;
               if(v == 1)
               {
                  //System.out.print(" Box 1 neighbor is: ");
                  //System.out.print(tempdex);
                  System.out.print(adjstats[tempdex]);
                  System.out.print(" ");
               }
            }
         }
         if(v == 1)
         {
         //System.out.print("\n");
         //System.out.print(v);
         //System.out.print("# box is in state: ");
         //System.out.print(thegrid[v].getStatus());
         //System.out.print(" has living neighbors: ");
         //System.out.print(adjstats[0]);
         }
         if(oldgrid[v].getStatus() == 0)//--------------------DEAD RULES
         {
            if( (adjstats[1] + adjstats[2] + adjstats[3]) == 3)// + adjstats[3]
            { //System.out.print(" box 1 back to life");
               thegrid[v].setStatus(1);
            }
            else if(adjstats[5] == 3)
            {
               thegrid[v].setStatus(5);
            }
            else if(adjstats[4] == 2)
            {
               thegrid[v].setStatus(4);
               //System.out.print(" box 1 stays dead ");
            }
            else if(adjstats[9] == 1)
            {
               thegrid[v].setStatus(9);
            }
            //else if(adjstats[1] > 3)
         }
         else if(oldgrid[v].getStatus() == 1)//-------------LIVING RULES
         {
            
            if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[5]) < 2)
            {
               thegrid[v].setStatus(0);
            }
            else if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[5] + adjstats[10]) > 3)
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[6] > 0)
            {
               thegrid[v].setStatus(7);
            }
            else if(adjstats[8] > 0)
            {
               thegrid[v].setStatus(8);
            }
            else if(adjstats[2] > 0)
            {
               thegrid[v].setStatus(2);
            }
            else if(adjstats[5] > 0)
            {
               thegrid[v].setStatus(5);
            }
            else if(adjstats[3] > 0)
            {
               //thegrid[v].setStatus(3);
            }
         }
         else if(oldgrid[v].getStatus() == 2)//--------------INFECTED RULES
         {
            thegrid[v].setStatus(0);
         }
         else if(oldgrid[v].getStatus() == 3)//--------------SAGE RULES
         {
            if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[10]) > 2)//3
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[5] > 0)
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[6] > 0)
            {
               thegrid[v].setStatus(7);
            }
            else if(adjstats[8] > 0)
            {
               thegrid[v].setStatus(8);
            }
            else if(adjstats[2] > 0)
            {
               thegrid[v].setStatus(2);
            }
         }
         else if(oldgrid[v].getStatus() == 4)//------------FAERIE RULES
         {
            if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4]) < 2)
            {
               thegrid[v].setStatus(0);
            }
            else if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[10]) > 2)
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[5] > 0)
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[6] > 0)
            {
               thegrid[v].setStatus(7);
            }
            else if(adjstats[8] > 0)
            {
               thegrid[v].setStatus(8);
            }
            else if(adjstats[2] > 0)
            {
               thegrid[v].setStatus(2);
            }
         }
         else if(oldgrid[v].getStatus() == 5)//------------SATYR RULES
         {
            if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[5]) < 3)
            {
               thegrid[v].setStatus(0);
            }
            else if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[5] + adjstats[10]) > 5)
            {
               thegrid[v].setStatus(0);
            }
            else if(adjstats[6] > 0)
            {
               thegrid[v].setStatus(7);
            }
            else if(adjstats[8] > 0)
            {
               thegrid[v].setStatus(8);
            }
            else if(adjstats[2] > 0)
            {
               thegrid[v].setStatus(2);
            }
         }
         else if(oldgrid[v].getStatus() == 6)//------------GORGON RULES
         {
            
         }
         else if(oldgrid[v].getStatus() == 7)//------------STONE RULES
         {
            
         }
         else if(oldgrid[v].getStatus() == 8)//------------VAMPIRE RULES
         {
            if(adjstats[6] > 0)
            {
               thegrid[v].setStatus(7);
            }
         }
         else if(oldgrid[v].getStatus() == 9)//------------GARGOYLE RULES
         {
            if( (adjstats[1] + adjstats[2] + adjstats[3] + adjstats[4] + adjstats[5] + adjstats[6] + adjstats[8]) > 0)
            {
               thegrid[v].setStatus(7);
            }
         }
         else if(oldgrid[v].getStatus() == 10)//------------CURSEDLAND RULES
         {
            
         }
      }
      
      stepcount++;
      return problems;
   }
   
   public int getStepCount()
   {
      return stepcount;
   }
   
   public void setStepCount(int newcount)
   {
      stepcount = newcount;
   }
   
   public void insertVirus(Random rander)
   {
      int livecount = 0;
      
      for(int i = 0; i < 400; i++)
      {
         if(thegrid[i].getStatus() > 0)
         {
            livecount++;
         }
      }
      
      int viruscell = rander.nextInt(livecount);
      int counttoo = 0;
      
      for(int c = 0; c < 400; c++)
      {
         if(thegrid[c].getStatus() > 0)
         {
            if(counttoo == viruscell)
            {
               thegrid[c].setStatus(2);
            }
            counttoo++;
         }
      }
      
   }
}

class GamePanel extends JPanel
{
   private int level;
   private int infectable;
   private int gamestate;
   private CellGrid thegrid;
   private int paintcell;
   //private int timedelay;
   private javax.swing.Timer timer;
   private Random randomer = new Random();
   
   public GamePanel()
   {
      ClickListener thelistener = new ClickListener();
      addMouseListener(thelistener);
      thegrid = new CellGrid();
      gamestate = 0;
      level = 0;
      infectable = 0;
      paintcell = 1;
      
      timer = new javax.swing.Timer(200, new ClockListener() );//(1000, new ClockListener() );
   }
   
   public void setGameState(int setto)
   {
      gamestate = setto;
      if(gamestate == 1)
      {
         System.out.print("u made it");
         timer.start();
      }
   }
   
   public void setdiag(int newdiag)
   {
      thegrid.setdiagadj(newdiag);
   }
   
   @Override
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      int numcells = 400;
      int initx;
      int inity;
      Polygon cellsquare = new Polygon();
      for(int c = 0; c < numcells; c++)
      {
         cellsquare = new Polygon();
         initx = thegrid.getCellAtIndexCoord('x', c);
         inity = thegrid.getCellAtIndexCoord('y', c);
         cellsquare.addPoint( initx, inity);
         cellsquare.addPoint( initx + 28, inity);
         cellsquare.addPoint( initx + 28, inity + 28);
         cellsquare.addPoint( initx, inity + 28);
         
         //g.setColor(Color.gray);
         //g.fillPolygon(cellsquare);
         chooseCellColor(c, g, cellsquare);
         g.drawPolygon(cellsquare);
      }
   }
   
   private void chooseCellColor(int cellnum, Graphics g, Polygon poly)
   {
      int cellstate = thegrid.getCellAtIndexStatus(cellnum);
      if(cellstate == 0)
      {
         g.setColor(Color.gray);
         g.fillPolygon(poly);
      }
      else if(cellstate == 1)
      {
         g.setColor(Color.green);
         g.fillPolygon(poly);
      }
      else if(cellstate == 2)
      {
         g.setColor(Color.red);
         g.fillPolygon(poly);
      }
      else if(cellstate == 3)
      {
         g.setColor(Color.blue);
         g.fillPolygon(poly);
      }
      else if(cellstate == 4)
      {
         g.setColor(Color.yellow);
         g.fillPolygon(poly);
      }
      else if(cellstate == 5)
      {
         g.setColor(Color.orange);
         g.fillPolygon(poly);
      }
      else if(cellstate == 6)
      {
         g.setColor( new Color(50,50,140) );//(147,112,219) );//Color.purple);
         g.fillPolygon(poly);
      }
      else if(cellstate == 7)
      {
         g.setColor(Color.darkGray);
         g.fillPolygon(poly);
      }
      else if(cellstate == 8)
      {
         g.setColor( new Color(140,50,50) );//(147,112,219) );//Color.purple);
         g.fillPolygon(poly);
      }
      else if(cellstate == 9)
      {
         g.setColor( new Color(50,140,50) );//(147,112,219) );//Color.purple);
         g.fillPolygon(poly);
      }
      else if(cellstate == 10)
      {
         g.setColor(Color.black);//(147,112,219) );//Color.purple);
         g.fillPolygon(poly);
      }

   }
   
   public void randomizer(int seed)
   {
      if(gamestate == 0)
      {
         int checkval = 0;
         for(int c = 0; c < 400; c++)
         {
            checkval = randomer.nextInt(100);
            if(checkval < seed)
            {
               thegrid.setCellAtIndexStatus(c,paintcell);
            }
            else
            {
               if(paintcell == thegrid.getCellAtIndexStatus(c) )//!= 0)//--------------------------check here for mistakes in randomizing
               {
                  thegrid.setCellAtIndexStatus(c,0);
               }
            }
         }
         
         repaint();
      }
   }
   
   public void callUpdate()
   {
      thegrid.updateGrid();
      if(level == 0)
      {
         if(thegrid.getStepCount() == 1000 && infectable == 1)//stepcount was 10
         {
            thegrid.insertVirus(randomer);
         }
      }
      repaint();
   }
   
   public int getRandom(int roof)
   {
      int randint = randomer.nextInt(roof);
      
      return randint;
   }
   
   public int getInfectable()
   {
      return infectable;
   }
   
   public void setInfectable(int newinfect)
   {
      infectable = newinfect;
   }
   
   public void setTimerDelay(int newdelay)
   {
      timer.setDelay(newdelay);
   }
   
   public void clearBoard()
   {
      for(int c = 0; c < 400; c++)
      {
         setGameState(0);
         thegrid.setCellAtIndexStatus(c, 0);
      }
      thegrid.setStepCount(0);
      repaint();
   }
   
   public void setPaintCell(int newpaint)
   {
      paintcell = newpaint;
   }
   
   class ClickListener implements MouseListener
   {
      @Override
      public void mouseClicked(MouseEvent e)
      {
         int clickindex;
         int xxx = e.getX();
         int yyy = e.getY();
         //if(xxx > 50)
         //{
            //System.out.print("urin...hehe");
         int indexer = thegrid.getCellAtLocIndex(xxx,yyy);
         if(indexer >= 0 && gamestate == 0)
         {
            thegrid.setCellAtIndexStatus(indexer, paintcell);
            repaint();
         }
         //}
         //return clickindex;
      }
      
      @Override
      public void mouseExited(MouseEvent e)
      {
         //System.out.print("this is mouse exited");
      }
      
      @Override
      public void mouseEntered(MouseEvent e)
      {
         //System.out.print("this is mouse entered");
      }
      
      @Override
      public void mousePressed(MouseEvent e)
      {
         //System.out.print("this is mouse entered");
      }
      
      @Override
      public void mouseReleased(MouseEvent e)
      {
         //System.out.print("this is mouse entered");
      }

   }
   
   
   class ClockListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         //thegrid.updateGrid();
         if(gamestate == 1)
         {
            callUpdate();
            repaint();
         }
      }
   }
}

class GameControl extends JPanel
{
   private JButton StartButton = new JButton("Run");
   private JButton StepButton = new JButton("Step");
   //private JButton RunButton = new JButton("Run");
   private JButton PauseButton = new JButton("Pause");
   private JButton ClearButton = new JButton("Clear");
   private JSlider timeslider = new JSlider(JSlider.HORIZONTAL);
   private GamePanel thegame;
   
   public GameControl()
   
   
   {
      add(StartButton);
      add(PauseButton);//StepButton);
      //add(RunButton);
      add(StepButton);//PauseButton);
      add(ClearButton);
      add(timeslider);
      
      ButtonListener buttonlistener = new ButtonListener();
      SpeedListener speedlistener = new SpeedListener();
      StartButton.addActionListener(buttonlistener);
      StepButton.addActionListener(buttonlistener);
      PauseButton.addActionListener(buttonlistener);
      ClearButton.addActionListener(buttonlistener);
      //StartButton.addActionListener(buttonlistener);
      timeslider.addChangeListener(speedlistener);
   }
   
   public void setGame(GamePanel newgame)
   {
      thegame = newgame;
   }
   
   class ButtonListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == StartButton)
         {
            thegame.setGameState(1);
         }
         if(e.getSource() == PauseButton)
         {
            thegame.setGameState(0);
         }
         if(e.getSource() == StepButton)
         { //System.out.print("CLEAR");
            thegame.callUpdate();
         }
         if(e.getSource() == ClearButton)
         {
            thegame.clearBoard();
         }
      }
   }
   
   class SpeedListener implements ChangeListener
   {
      @Override
      public void stateChanged(ChangeEvent e)
      {
         if(e.getSource() == timeslider)
         {
            int newdelay = timeslider.getValue();
            
            if(newdelay == 0)
            {
               newdelay = 1;
            }
            newdelay = 10000 / newdelay;//10000
            thegame.setTimerDelay(newdelay);
         }
      }  
   }

}

class RandControl extends JPanel
{
   private JButton randomize = new JButton("Randomize w/ seed"); 
   private JSlider seedslider = new JSlider(JSlider.HORIZONTAL);
   private String[] unitsavail = {"Living", "Dead", "Infected", "Sage", "Faerie", "Satyr", "Gorgon", "Stone", "Vampire", "Gargoyle", "Cursed Land"};
   private JComboBox units = new JComboBox(unitsavail);
   private GamePanel thegame;
   private int randseed;
   
   public RandControl()
   {
      randseed = 50;
      ClickListener buttonlistener = new ClickListener();
      SliderListener slidelistener = new SliderListener();
      UnitListener paintlistener = new UnitListener();
      
      add(randomize);
      add(seedslider);
      add(units);
      
      randomize.addActionListener(buttonlistener);
      seedslider.addChangeListener(slidelistener);
      units.addItemListener(paintlistener);
   }
   
   public void setGame(GamePanel newgame)
   {
      thegame = newgame;
   }
   
   class ClickListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == randomize)
         {
            thegame.randomizer(randseed);
         }
      }
   }
   
   class SliderListener implements ChangeListener
   {
      @Override
      public void stateChanged(ChangeEvent e)
      {
         randseed = seedslider.getValue();
      }
   }
   
   class UnitListener implements ItemListener
   {
      @Override
      public void itemStateChanged(ItemEvent e)
      {
         int chosen = units.getSelectedIndex();
         if(chosen == 0)
         {
            chosen = 1;
         }
         else if(chosen == 1)
         {
            chosen = 0;
         }
         
         thegame.setPaintCell(chosen);
      }
   }
}

class OptionsPanel extends JPanel
{
   private JCheckBox diagcheck = new JCheckBox("Use Diagonals", false);
   private JCheckBox viruscheck = new JCheckBox("Insert Virus at 1000", false);
   private GamePanel thegame;
   
   public OptionsPanel()
   {
      add(diagcheck);
      add(viruscheck);
      BoxChecker diagwaiter = new BoxChecker();
      //BoxChecker
      
      diagcheck.addActionListener(diagwaiter);
      viruscheck.addActionListener(diagwaiter);
   }
   
   public void setGame(GamePanel newgame)
   {
      thegame = newgame;
   }
   
   class BoxChecker implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == diagcheck)
         {
         if(diagcheck.isSelected())
         {
            //System.out.print("TURNING ON");
            thegame.setdiag(1);
         }
         else
         {//System.out.print("SHUTTING OFF");
            thegame.setdiag(0);
         }
         }
         
         if(e.getSource() == viruscheck)
         {
            if(viruscheck.isSelected())
            {
               thegame.setInfectable(1);
            }
            else
            {
               thegame.setInfectable(0);
            }
         }
      }
   }
}

class GameWindow extends JFrame
{
   public GameWindow()
   {
      setLayout(new GridLayout(1,2,5,5) );
      
      GamePanel gamepan = new GamePanel();
      JPanel bigcontrol = new JPanel();
      bigcontrol.setLayout( new GridLayout(6,1,5,5) );
      
      //OptionsPanel
      GameControl controller = new GameControl();
      RandControl rander = new RandControl();
      OptionsPanel options =new OptionsPanel();
      add(gamepan);
      add(bigcontrol);//controller);
      bigcontrol.add(controller);
      bigcontrol.add(rander);
      bigcontrol.add(options);
      controller.setGame(gamepan);
      rander.setGame(gamepan);
      options.setGame(gamepan);
   }
}

public class GameofLife
{
   public static void main(String[] args)
   {
      GameWindow theGame = new GameWindow();
      
      theGame.setTitle("George's Conway's Life's Game!");
      theGame.setSize(1280, 675);//900
      theGame.setLocationRelativeTo(null);
      theGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      theGame.setResizable(false);
      theGame.setVisible(true);
   }
}