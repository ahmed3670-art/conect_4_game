/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;

/**
 *
 * @author
 */
class game_play extends JFrame {

    GLCanvas glcanvas;
    private Listener listener;
    private Animator animator;
    String p1, p2;

    public game_play() {
        listener = new Listener(glcanvas);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(30);
        animator.add(glcanvas);
        animator.start();
        setTitle("connect4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();

        MyTimer t = new MyTimer();
    }

    public void cc() {
        dispose();
    }

    public static void main(String[] args) {
        new game_play();

    }

    public class Listener extends javax.swing.JFrame implements GLEventListener, MouseListener {

        GLCanvas glcanvas;
        boolean turn = false;
        private int width = 65;
        private int height = 60;

        float x;
        boolean red, yellow, click;
        double xp, yp;
        int circles[][] = new int[6][7];
        int yS[][] = new int[6][7];
        String path = "C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\image\\";
        String textureNames[] = {"connect4.png", "red.png", "yellow.png"};
        TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
        int textures[] = new int[textureNames.length];
        Rectangle rect[][] = new Rectangle[6][7];
        Date start;
        long mSecond;
        long second;

        boolean running = false;

        public Listener(GLCanvas glcanvas) {
            this.glcanvas = glcanvas;
        }

        @Override
        public void init(GLAutoDrawable glad) {
            GL gl = glad.getGL();

            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            gl.glEnable(GL.GL_TEXTURE_2D);
            gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
            gl.glGenTextures(textureNames.length, textures, 0);
//        play("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav",0);

            for (int i = 0; i < textureNames.length; i++) {
                try {
                    texture[i] = TextureReader.readTexture(path + textureNames[i], true);
                    gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                    new GLU().gluBuild2DMipmaps(
                            GL.GL_TEXTURE_2D,
                            GL.GL_RGBA,
                            texture[i].getWidth(), texture[i].getHeight(),
                            GL.GL_RGBA,
                            GL.GL_UNSIGNED_BYTE,
                            texture[i].getPixels()
                    );
                } catch (IOException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
            gl.glLoadIdentity();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    yS[i][j] = 100;
                }
            }
        }
        int y = 0;
        boolean flag = true;
        boolean f = true;
        int xx[][] = new int[6][7];
        int y1[] = {-590, -496, -402, -308, -214, -120};

        @Override
        public void display(GLAutoDrawable glad) {
            GL gl = glad.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//        Rectangle rect3 = rect(-350, y);
            for (int i = 0; i < 6; i++) {
                if (circles[i][0] == 0) {
                    continue;
                }
                if (yS[i][0] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][0] = 1;
                }

                if (circles[i][0] != 0) {
                    addlines(gl, -74, yS[i][0], circles[i][0]);

                    if (xx[i][0] != 1) {
                        f = false;
                        yS[i][0] -= 13;
                    }
                }

            }
            //--------------------------------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][1] == 0) {
                    continue;
                }
                if (yS[i][1] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][1] = 1;
                }

                if (circles[i][1] != 0) {
                    addlines(gl, -49, yS[i][1], circles[i][1]);

                    if (xx[i][1] != 1) {
                        f = false;
                        yS[i][1] -= 13;
                    }
                }

            }

            //--------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][2] == 0) {
                    continue;
                }
                if (yS[i][2] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][2] = 1;
                }

                if (circles[i][2] != 0) {
                    addlines(gl, -24, yS[i][2], circles[i][2]);

                    if (xx[i][2] != 1) {
                        f = false;
                        yS[i][2] -= 13;
                    }
                }

            }
            //--------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][3] == 0) {
                    continue;
                }
                if (yS[i][3] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][3] = 1;
                }

                if (circles[i][3] != 0) {
                    addlines(gl, 1, yS[i][3], circles[i][3]);

                    if (xx[i][3] != 1) {
                        f = false;
                        yS[i][3] -= 13;
                    }
                }

            }

            //--------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][4] == 0) {
                    continue;
                }
                if (yS[i][4] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][4] = 1;
                }

                if (circles[i][4] != 0) {
                    addlines(gl, 26, yS[i][4], circles[i][4]);

                    if (xx[i][4] != 1) {
                        f = false;
                        yS[i][4] -= 13;
                    }
                }

            }
            //--------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][5] == 0) {
                    continue;
                }
                if (yS[i][5] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][5] = 1;
                }

                if (circles[i][5] != 0) {

                    addlines(gl, 50, yS[i][5], circles[i][5]);

                    if (xx[i][5] != 1) {
                        f = false;
                        yS[i][5] -= 13;
                    }
                }

            }
            //--------------------------
            for (int i = 0; i < 6; i++) {
                if (circles[i][6] == 0) {
                    continue;
                }
                if (yS[i][6] < y1[5 - i]) {
                    flag = false;
                    f = true;
                    xx[i][6] = 1;
                }

                if (circles[i][6] != 0) {
                    addlines(gl, 75, yS[i][6], circles[i][6]);

                    if (xx[i][6] != 1) {
                        f = false;
                        yS[i][6] -= 13;
                    }
                }

            }
            //--------------------------

            DrawBackground(gl, x);

        }
        String sound = "C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav";

    public void check_win() {
      for (int i = 5; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    if (circles[i][j] == 1) {
                        if (circles[i][j] == circles[i][j + 1]) {

                            if (circles[i][j + 1] == circles[i][j + 2]) {
                                if (circles[i][j + 2] == circles[i][j + 3]) {
                                    play1(sound, 0);

                                    winRed r = new winRed();
                                    r.show();
                                    cc();

                                }
                            }
                        }
                    }
                    if (circles[i][j] == 2) {
                        if (circles[i][j] == circles[i][j + 1]) {
                            if (circles[i][j + 1] == circles[i][j + 2]) {
                                if (circles[i][j + 2] == circles[i][j + 3]) {
                                    play1(sound, 0);
                                    winYellow y = new winYellow();
                                    y.show();
                                    cc();
                                }
                            }
                        }
                    }

                }
            }
            // horizontal right to left
            for (int i = 5; i >= 0; i--) {
                for (int j = 6; j > 3; j--) {
                    if (circles[i][j] == 1) {
                        if (circles[i][j] == circles[i][j - 1]) {

                            if (circles[i][j - 1] == circles[i][j - 2]) {

                                if (circles[i][j - 2] == circles[i][j - 3]) {
                            play1(sound, 0);
                                    winRed r = new winRed();
                                    r.show();
                                    cc();
                                }
                            }
                        }
                    }
                    if (circles[i][j] == 2) {
                        if (circles[i][j] == circles[i][j - 1]) {

                            if (circles[i][j - 1] == circles[i][j - 2]) {

                                if (circles[i][j - 2] == circles[i][j - 3]) {
                                     play1(sound, 0);
                                    winYellow y = new winYellow();
                                    y.show();
                                    cc();
                                }
                            }
                        }
                    }
                }
            }

            // vertical 
            for (int i = 0; i <= 6; i++) {
                for (int j = 2; j >= 0; j--) {
                    if (circles[j][i] == 1) {
                        if (circles[j][i] == circles[j + 1][i]) {
                            if (circles[j + 1][i] == circles[j + 2][i]) {
                                if (circles[j + 2][i] == circles[j + 3][i]) {
                                    play1(sound, 0);
                                    winRed r = new winRed();
                                    r.show();
                                    cc();
                                }
                            }
                        }

                    }
                    if (circles[j][i] == 2) {
                        if (circles[j][i] == circles[j + 1][i]) {
                            if (circles[j + 1][i] == circles[j + 2][i]) {
                                if (circles[j + 2][i] == circles[j + 3][i]) {
                                     play1(sound, 0);
                                    winYellow y = new winYellow();
                                    y.show();
                                    cc();
                                }
                            }
                        }

                    }
                }
            }

            // diagonal left to right
            for (int i = 5; i > 2; i--) {
                for (int j = 0; j < 4; j++) {
                    if (circles[i][j] == 1) {
                        if (circles[i][j] == circles[i - 1][j + 1]) {
                            if (circles[i - 1][j + 1] == circles[i - 2][j + 2]) {
                                if (circles[i - 2][j + 2] == circles[i - 3][j + 3]) {
                                    play1(sound, 0);
                                    winRed r = new winRed();
                                    r.show();
                                    cc();
                                }
                            }
                        }
                    }
                    if (circles[i][j] == 2) {
                        if (circles[i][j] == circles[i - 1][j + 1]) {
                            if (circles[i - 1][j + 1] == circles[i - 2][j + 2]) {
                                if (circles[i - 2][j + 2] == circles[i - 3][j + 3]) {
                                    play1(sound, 0);
                                    winYellow y = new winYellow();
                                    y.show();
                                    cc();
                                }
                            }
                        }
                    }
                }
            }
            // diagonal raight to left
            for (int i = 5; i > 2; i--) {
                for (int j = 6; j > 2; j--) {

                    if (circles[i][j] == 1) {
                        if (circles[i][j] == circles[i - 1][j - 1]) {
                            if (circles[i - 1][j - 1] == circles[i - 2][j - 2]) {
                                if (circles[i - 2][j - 2] == circles[i - 3][j - 3]) {
                                    play1(sound, 0);
                                    winRed r = new winRed();
                                    r.show();
                                    cc();
                                }
                            }
                        }
                    }
                    if (circles[i][j] == 2) {
                        if (circles[i][j] == circles[i - 1][j - 1]) {
                            if (circles[i - 1][j - 1] == circles[i - 2][j - 2]) {
                                if (circles[i - 2][j - 2] == circles[i - 3][j - 3]) {
                                  play1(sound, 0);
                                    winYellow y = new winYellow();
                                    y.show();
                                    cc();
                                }
                            }
                        }
                    }

                }
            }    }
        @Override
        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
            System.out.println("reshape");
        }

        @Override
        public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        int type = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("mouseClicked");
            play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\ss.wav", 0);
            int xx = 0;

            click = true;
            if (f) {
                check_win();
                type = (type % 2) + 1;
                
                double x = e.getX();
                double y = e.getY();
                Component c = e.getComponent();
                double width = c.getWidth();
                double height = c.getHeight();
                System.out.println(x + " " + y);
                xp = (int) ((x / width) * 100);
                yp = ((int) ((y / height) * 100));
                System.out.println("xp = " + xp + "  yp = " + yp);
                if (xp > 1 && xp < 13) {
                    xx = 0;
                } else if (xp > 15 && xp < 27) {
                    xx = 1;
                } else if (xp > 30 && xp < 42) {
                    xx = 2;
                } else if (xp > 43 && xp < 55) {
                    xx = 3;
                } else if (xp > 58 && xp < 70) {
                    xx = 4;
                } else if (xp > 72 && xp < 84) {
                    xx = 5;
                } else if (xp > 86 && xp < 98) {
                    xx = 6;
                }
                for (int i = 0; i < 6; i++) {
                    if (i == 5) {
                        circles[5][xx] = type;
                        break;
                    }
                    if (circles[i + 1][xx] != 0) {
                        circles[i][xx] = type;
                        break;
                    }
                }
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        System.out.print(circles[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println("type" + type);
                
               // check_win();
                
            }

            /// horizontal  left to right
//            for (int i = 5; i > 0; i--) {
//                for (int j = 0; j < 3; j++) {
//                    if (circles[i][j] == 1) {
//                        if (circles[i][j] == circles[i][j + 1]) {
//
//                            if (circles[i][j + 1] == circles[i][j + 2]) {
//                                if (circles[i][j + 2] == circles[i][j + 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//
//                                    winRed r = new winRed();
//                                    r.show();
//                                    cc();
//
//                                }
//                            }
//                        }
//                    }
//                    if (circles[i][j] == 2) {
//                        if (circles[i][j] == circles[i][j + 1]) {
//                            if (circles[i][j + 1] == circles[i][j + 2]) {
//                                if (circles[i][j + 2] == circles[i][j + 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winYellow y = new winYellow();
//                                    y.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//            // horizontal right to left
//            for (int i = 5; i > 0; i--) {
//                for (int j = 6; j > 3; j--) {
//                    if (circles[i][j] == 1) {
//                        if (circles[i][j] == circles[i][j - 1]) {
//
//                            if (circles[i][j - 1] == circles[i][j - 2]) {
//
//                                if (circles[i][j - 2] == circles[i][j - 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winRed r = new winRed();
//                                    r.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//                    if (circles[i][j] == 2) {
//                        if (circles[i][j] == circles[i][j - 1]) {
//
//                            if (circles[i][j - 1] == circles[i][j - 2]) {
//
//                                if (circles[i][j - 2] == circles[i][j - 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winYellow y = new winYellow();
//                                    y.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // vertical 
//            for (int i = 0; i < 6; i++) {
//                for (int j = 2; j > 0; j--) {
//                    if (circles[j][i] == 1) {
//                        if (circles[j][i] == circles[j + 1][i]) {
//                            if (circles[j + 1][i] == circles[j + 2][i]) {
//                                if (circles[j + 2][i] == circles[j + 3][i]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winRed r = new winRed();
//                                    r.show();
//                                    cc();
//                                }
//                            }
//                        }
//
//                    }
//                    if (circles[j][i] == 2) {
//                        if (circles[j][i] == circles[j + 1][i]) {
//                            if (circles[j + 1][i] == circles[j + 2][i]) {
//                                if (circles[j + 2][i] == circles[j + 3][i]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winYellow y = new winYellow();
//                                    y.show();
//                                    cc();
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//
//            // diagonal left to right
//            for (int i = 5; i > 2; i--) {
//                for (int j = 0; j < 4; j++) {
//                    if (circles[i][j] == 1) {
//                        if (circles[i][j] == circles[i - 1][j + 1]) {
//                            if (circles[i - 1][j + 1] == circles[i - 2][j + 2]) {
//                                if (circles[i - 2][j + 2] == circles[i - 3][j + 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winRed r = new winRed();
//                                    r.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//                    if (circles[i][j] == 2) {
//                        if (circles[i][j] == circles[i - 1][j + 1]) {
//                            if (circles[i - 1][j + 1] == circles[i - 2][j + 2]) {
//                                if (circles[i - 2][j + 2] == circles[i - 3][j + 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winYellow y = new winYellow();
//                                    y.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            // diagonal raight to left
//            for (int i = 5; i > 2; i--) {
//                for (int j = 6; j > 2; j--) {
//
//                    if (circles[i][j] == 1) {
//                        if (circles[i][j] == circles[i - 1][j - 1]) {
//                            if (circles[i - 1][j - 1] == circles[i - 2][j - 2]) {
//                                if (circles[i - 2][j - 2] == circles[i - 3][j - 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winRed r = new winRed();
//                                    r.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//                    if (circles[i][j] == 2) {
//                        if (circles[i][j] == circles[i - 1][j - 1]) {
//                            if (circles[i - 1][j - 1] == circles[i - 2][j - 2]) {
//                                if (circles[i - 2][j - 2] == circles[i - 3][j - 3]) {
//                                    play1("C:\\Users\\asemz\\OneDrive\\Documents\\NetBeansProjects\\c44\\src\\game\\sound.wav", 0);
//                                    winYellow y = new winYellow();
//                                    y.show();
//                                    cc();
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
        }

        @Override
        public void mousePressed(MouseEvent me) {

            System.out.println("mousePressed");

        }

        @Override
        public void mouseReleased(MouseEvent me) {
            System.out.println("mouseReleased");
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            System.out.println("mouseEntered");
        }

        @Override
        public void mouseExited(MouseEvent me) {
            System.out.println("mouseExited");
        }

        public void DrawBackground(GL gl, float x) {
            gl.glEnable(GL.GL_BLEND);
            gl.glPushMatrix();

            gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);	// Turn Blending On

            gl.glTranslated(x, 0, 0);
            // gl.glScalef(maxWidth / 2, maxHeight / 2, 1);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -1.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
            gl.glPopMatrix();
            gl.glDisable(GL.GL_BLEND);
        }

        public void addlines(GL gl, int x, int y1, int type) {

            gl.glEnable(GL.GL_BLEND);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textures[type]);
            gl.glPushMatrix();
            gl.glScaled(.1, .1, 1);
            gl.glScaled(.115, .035, 1);
            gl.glTranslated(x, y1, 0);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
            gl.glVertex3f(-10.0f, 400, -1.0f);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(10.0f, 400, -1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(10.0f, 320, -1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-10.0f, 320, -1.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glEnd();
            gl.glPopMatrix();

        }

        public Rectangle rect(float x, float y) {
            return new Rectangle((int) x, (int) y, 80, 75);
        }

        public void play(String s, int count) { //Plays the background music
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(s)));
                clip.start();
                if (!clip.isRunning()) {
                    clip.loop(count);
                }
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }

        public void play1(String s, int count) { //Plays the background music
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(s)));
                clip.start();

                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }

        private void menuQuitGameActionPerformed(java.awt.event.ActionEvent evt) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));

        }

    }

    class MyTimer {

        Date start;
        long mSecond;
        long second;
        boolean running = false;

        public MyTimer() {
            start = new Date();
            this.running = true;
        }

        public long getTime() {
            if (running) {
                Date now = new Date();
                mSecond = now.getTime() - start.getTime();
                second = mSecond / 1000;
            }
            return second;
        }

        public void stop() {
            this.running = false;
        }

        public void reset() {
            start = new Date();
            this.running = true;
        }

    }

}
