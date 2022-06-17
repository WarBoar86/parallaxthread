/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuparalax;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Paulo Carucio
 */
public class MeuParalax extends JFrame {

    /**
     * @param args the command line arguments
     */
    ImageIcon ceuIcon = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/ceu.png");

    ImageIcon d1 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/d1.png");
    ImageIcon d2 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/d2.png");
    ImageIcon d3 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/d3.png");
    ImageIcon d4 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/d4.png");

    ImageIcon hillIcon1 = new ImageIcon("D:/FATEC/3 semestre/SO/MeuParalax/src/Imagens/montanhas2.png");
    
    ImageIcon chaoIcon=new ImageIcon("D:/FATEC/3 semestre/SO/MeuParalax/src/Imagens/chao.png");
    
    ImageIcon nuvIcon1 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/Nuvem1.png");
    ImageIcon nuvIcon2 = new ImageIcon("D:/FATEC/3 semestre/SO/Atv_Thread/src/Imagens/Nuvem2.png");
//----------------------------------------------------------------------------------------------------
    Image ceu = ceuIcon.getImage();
    Image montanha1 = hillIcon1.getImage();
    Image montanha2 = hillIcon1.getImage();
    Image nuvem1 = nuvIcon1.getImage();
    Image nuvem2 = nuvIcon2.getImage();
    Image chao=chaoIcon.getImage();

    Image[] dragao = new Image[4];

    JLabel lblDragao = new JLabel();
    JLabel lblCeu = new JLabel(new ImageIcon(ceu));
    JLabel lblChao1 = new JLabel(new ImageIcon(chao));
    JLabel lblChao2 = new JLabel(new ImageIcon(chao));
    JLabel lblNuvem1 = new JLabel(new ImageIcon(nuvem1));
    JLabel lblNuvem2 = new JLabel(new ImageIcon(nuvem2));
    JLabel lblMontanha1 = new JLabel(new ImageIcon(montanha1));
    JLabel lblMontanha2 = new JLabel(new ImageIcon(montanha2));
//------------------------------------------------------------------------------------------------------------
    int Xnuvem1 = 0, Xnuvem2 = 500, sleepNuvem = 3,
            Xmontanha1 = -40, Xmontanha2 = 500, sleepMontanha = 3,
            sleepDragao = 5, frameDragao = 0,
            Xchao1=0,Xchao2=500,sleepChao=2;

    boolean[] pause = {false, true, true,true};
    
    //-----------------------------------------------------------CONSTRUTOR---------------------------

    public MeuParalax() {

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;

        this.setSize(500, 305);
        this.setResizable(false);
        this.setTitle("Paralax Scroll com Threads");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(500, 100);

        dragao[0] = d1.getImage();
        dragao[1] = d2.getImage();
        dragao[2] = d3.getImage();
        dragao[3] = d4.getImage();

        add(lblDragao, cons);
        add(lblChao1, cons);
        add(lblChao2,cons);
        add(lblMontanha1, cons);
        add(lblMontanha2, cons);
        add(lblNuvem1, cons);
        add(lblNuvem2, cons);
        
        add(lblCeu, cons);

    }

    //---------------------------------------------------------------THREADS
    class AnimarDragao implements Runnable {

        @Override
        public synchronized void run() {

            //lblDragao.setLocation(140, 90);
            while (true) {

                if (frameDragao < 3 && !pause[0]) {
                    frameDragao++;

                } else {
                    frameDragao = 0;

                }

                lblDragao.setIcon(new ImageIcon(dragao[frameDragao]));
                //System.out.println("Frame dragao=" + frameDragao);
                try {
                    Thread.sleep(sleepDragao);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuParalax.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    //--------------------------------------------------------------------------------------------------
    class MoverNuvens implements Runnable {

       
        @Override
        public synchronized void run() {
            while (true) {

                if (!pause[2]) {
                    if (Xnuvem1 > -500) {
                        Xnuvem1--;

                    } else {
                        Xnuvem1 = 500;
                    }
                    if (Xnuvem2 > -500) {
                        Xnuvem2--;

                    } else {
                        Xnuvem2 = 500;
                    }
                }

                lblNuvem1.setLocation(Xnuvem1, 110);
                lblNuvem2.setLocation(Xnuvem2, 110);

                //System.out.println("pos Nuvem=" + Xnuvem1 + ", " + Xnuvem2);
                try {
                    Thread.sleep(sleepNuvem);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuParalax.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    //-----------------------------------------------------------------------------------------------------------
    class MoverMontanhas implements Runnable {

        boolean rodar = true;

        public void run() {

            while (rodar) {
                
                if(!pause[1]){
                if (Xmontanha1 > -540) {
                    Xmontanha1--;

                } else {
                    Xmontanha1 = 500;
                }
                if (Xmontanha2 > -540) {
                    Xmontanha2--;

                } else {
                    Xmontanha2 = 500;
                }
                
                }
                lblMontanha1.setLocation(Xmontanha1, 185);
                lblMontanha2.setLocation(Xmontanha2, 185);
                //System.out.println("pos Montanha=" + Xmontanha1 + ", " + Xmontanha2);

                try {
                    Thread.sleep(sleepMontanha);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuParalax.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    //-------------------------------------------------------------------------------------------
    
    

    class MoverChao implements Runnable {

       
        @Override
        public synchronized void run() {
            while (true) {

                if (!pause[3]) {
                    if (Xchao1 > -500) {
                        Xchao1--;

                    } else {
                        Xchao1 = 500;
                    }
                    if (Xchao2 > -500 ) {
                        Xchao2--;

                    } else {
                        Xchao2 = 500;
                    }
                }

                lblChao1.setLocation(Xchao1, 235);
                lblChao2.setLocation(Xchao2, 235);

                //System.out.println("pos Nuvem=" + Xnuvem1 + ", " + Xnuvem2);
                try {
                    Thread.sleep(sleepChao);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuParalax.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    //-----------------------------------------------------------------------------------------------------------
}
