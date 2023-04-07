/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.archer;

import java.awt.*;

/**
 *
 * @author Artem_Sukharev
 */

public class JPanel extends javax.swing.JPanel {
    
    //Arrow description:
    int initArrX = 50;
    int arrX = -100;
    int arrY = -100;
    int arrSz = 7;
    int speedArr=7;
    
    //Target description:
    int r = 20;
    int t1X = -50;
    int t1Y = -50; 
    int t2X = -50;
    int t2Y = -50;
    int deltaT1 = 2;
    int deltaT2 = deltaT1*2;
    
    Thread mainThread = null;
    int flagArrow = 0;
    int flagGame = 0;
    
    /**
     * Creates new form JPanel
     */
    public JPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jButton2.setText("Выстрел");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton1.setText("Начать новую игру");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Счет :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("0");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Выстрелов:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("0");

        jButton3.setText("Завершить игру");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(250, 250, 250)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(mainThread == null || flagArrow == 1)
           return;
        jLabel4.setText(String.valueOf(Integer.parseInt(jLabel4.getText())+1));
        flagArrow = 1;
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(mainThread!=null){
           mainThread.interrupt();
           mainThread = null;
        }
        jLabel2.setText("0");
        jLabel4.setText("0");
        
        initPicture();
        flagGame = 1;
        mainThread = new Thread(()->{
            while(flagGame==1){
                moveTargets();
                if(flagArrow == 1){
                    moveArrow();
                    int fl = 0;
                    if(Math.abs(arrX-t1X)<=speedArr/2){
                        if(t1Y-2*r<=arrY&&arrY<=t1Y+2*r){
                            jLabel2.setText(String.valueOf(Integer.parseInt(jLabel2.getText())+1));
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException ex){  
                                flagGame = 0;
                                break;
                            }
                            fl = 1;
                        }
                    }
                    if(Math.abs(arrX-t2X)<=speedArr/2){
                        if(t2Y-r<=arrY&&arrY<=t2Y+r){
                            jLabel2.setText(String.valueOf(Integer.parseInt(jLabel2.getText())+2));
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException ex){ 
                                flagGame = 0;
                                break;
                            }
                            fl = 1;
                            
                        }
                    }
                    if(arrX>this.getWidth()+10*arrSz){
                        fl = 1;
                    }
                    if(fl == 1){
                        flagArrow = 0;
                        arrX = initArrX;
                    }
                }
                try{
                    Thread.sleep(20);
                }catch(InterruptedException ex){  
                    flagGame = 0;
                }
            }
        });
        mainThread.start();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(mainThread == null)
            return;
        mainThread.interrupt();
        flagGame = 0;
        mainThread = null;
        flagArrow = 0;
        initPicture();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void moveArrow(){
        if(arrX<this.getWidth()+11*arrSz){
            arrX+=speedArr;
        }
        this.repaint();
    }
    
    private void moveTargets(){
        t1Y+=deltaT1;
        if(t1Y<2*r){
            t1Y=2*r;
            deltaT1*=-1;
        }
        if(t1Y>getHeight()-2*r){
            t1Y=getHeight()-2*r;
            deltaT1*=-1;
        }
        
        t2Y+=deltaT2;
        if(t2Y<r){
            t2Y=r;
            deltaT2*=-1;
        }
        if(t2Y>getHeight()-r){
            t2Y=getHeight()-r;
            deltaT2*=-1;
        }
        this.repaint();  
    }
    
    protected void drawArrow(Graphics2D g2d){
        Polygon triengle = new Polygon();
        triengle.addPoint(arrX-arrSz*2,arrY-arrSz);
        triengle.addPoint(arrX-arrSz*2,arrY+arrSz);
        triengle.addPoint(arrX,arrY);
        g2d.fillPolygon(triengle);
        g2d.drawLine(arrX-arrSz*8,arrY ,arrX-arrSz*2, arrY);
        Polygon shaft = new Polygon();
        shaft.addPoint(arrX-arrSz*8+arrSz*7/5,arrY);
        shaft.addPoint(arrX-arrSz*8+arrSz*2/5,arrY+arrSz*3/4);
        shaft.addPoint(arrX-arrSz*8-arrSz*7/5,arrY+arrSz*3/4);
        shaft.addPoint(arrX-arrSz*8,arrY);
        shaft.addPoint(arrX-arrSz*8-arrSz*7/5,arrY-arrSz*3/4);
        shaft.addPoint(arrX-arrSz*8+arrSz*2/5,arrY-arrSz*3/4);
        g2d.fillPolygon(shaft);
        
    }
    protected void drawTargets(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0));
        g2d.drawOval(t1X-r, t1Y-2*r, 2*r, 4*r);
        g2d.setColor(new Color(200,0,0,70));
        g2d.fillOval(t1X-r, t1Y-2*r, 2*r, 4*r);
        
        g2d.setColor(new Color(200,0,0,100));
        g2d.fillOval(t1X-r/2, t1Y-r, r, 2*r);  
        g2d.setColor(new Color(0,0,0));
        
        g2d.drawLine(t1X,t1Y-2*r ,t1X ,0 );
        g2d.drawLine(t1X,t1Y+2*r ,t1X ,getHeight() );
        /////
        g2d.setColor(new Color(0,0,0));
        g2d.drawOval(t2X-r/2, t2Y-r, r, 2*r);
        g2d.setColor(new Color(200,0,0,70));
        g2d.fillOval(t2X-r/2, t2Y-r, r, 2*r);
        
        g2d.setColor(new Color(200,0,0,80));
        g2d.fillOval(t2X-r/4, t2Y-r/2, r/2, r);  
        g2d.setColor(new Color(0,0,0));
        
        g2d.drawLine(t2X,t2Y-r ,t2X ,0 );
        g2d.drawLine(t2X,t2Y+r ,t2X ,getHeight() );
    }
    protected void drawPlayer(Graphics2D g2d,int X,int Y){
        g2d.setColor(new Color(102,255,102));
        g2d.fillRect(0, 0,X,getHeight());
        if(flagArrow==0){
            g2d.setColor(new Color(0,0,255));
            Polygon triengle = new Polygon();
            triengle.addPoint(X*9/10,Y);
            triengle.addPoint(X/10,Y-20);
            triengle.addPoint(X/10,Y+20);
            g2d.fillPolygon(triengle);
        }
        g2d.setColor(new Color(0,0,0));
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawTargets(g2d);
        drawArrow(g2d);
        drawPlayer(g2d,60,getHeight()/2);
    }
    
    public void initPicture(){
        arrX=initArrX;
        arrY=getHeight()/2;
        
        t1Y=getHeight()/2;
        t2Y=getHeight()/2;
        
        t1X=getWidth()-Math.min(r*7,120);
        t2X=getWidth()-Math.min(r*5/2,70);
        
        this.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
