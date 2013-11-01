package net.pslice.song;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JPanel implements ActionListener, ItemListener, ChangeListener{

    static JFrame baseFrame;

    JLabel titleLabel;
    JTextField title;

    JLabel tempoLabel;
    JSlider tempo;

    JLabel instrumentLabel;
    JTextField inst1;
    JTextField inst2;

    JLabel keyLabel;
    JComboBox key;
    JComboBox keyType;

    JCheckBox verbose;

    JButton runButton;

    String[] keyList = {"G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G",};
    String[] keyTypeList = {"Major", "Minor"};

    String songTitle;
    String songKey;
    int songTempo;
    int bgi;
    int mdi;
    boolean isVerbose = false;

    public static void newWindow(){
        baseFrame = new JFrame("Music Writer");

        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new Window();
        newContentPane.setPreferredSize(new Dimension(300, 150));
        newContentPane.setOpaque(true);
        baseFrame.setContentPane(newContentPane);

        baseFrame.setMinimumSize(new Dimension(300, 200));
        baseFrame.setMaximumSize(new Dimension(300, 200));

        baseFrame.pack();
        baseFrame.setVisible(true);
    }

    public Window(){

        titleLabel = new JLabel("Title:");
        title = new JTextField(20);
        title.setToolTipText("Song title");

        tempoLabel = new JLabel("Tempo: 120");
        tempo = new JSlider(JSlider.HORIZONTAL, 60, 180, 120);
        tempo.setMajorTickSpacing(20);
        tempo.setMinorTickSpacing(2);
        tempo.setPaintTicks(true);
        tempo.setPaintLabels(true);
        tempo.setToolTipText("Song tempo");

        instrumentLabel = new JLabel("Instruments:");
        inst1 = new JTextField("0", 1);
        inst2 = new JTextField("0", 1);

        keyLabel = new JLabel("Key:");
        key = new JComboBox(keyList);
        key.setSelectedIndex(4);
        key.setToolTipText("Song key");

        keyType = new JComboBox(keyTypeList);
        keyType.setSelectedIndex(0);

        verbose = new JCheckBox("Verbose");
        verbose.setSelected(false);
        verbose.setToolTipText("Enable verbose");


        runButton = new JButton("Write song!");
        runButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        runButton.setHorizontalTextPosition(AbstractButton.RIGHT);
        runButton.setActionCommand("Launch");
        runButton.setToolTipText("Create the song");

        tempo.addChangeListener(this);
        key.addActionListener(this);
        keyType.addActionListener(this);
        verbose.addItemListener(this);
        runButton.addActionListener(this);

        add(titleLabel);
        add(title);
        add(tempoLabel);
        add(tempo);
        add(instrumentLabel);
        add(inst1);
        add(inst2);
        add(keyLabel);
        add(key);
        add(keyType);
        add(verbose);
        add(runButton);
    }

    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Launch")){
            songTitle = title.getText();

            bgi = Integer.parseInt(inst1.getText());
            mdi = Integer.parseInt(inst2.getText());

            if (songTitle.equals(""))
                songTitle = "Demo";
            try {
                Launcher.beginSong(songTitle, isVerbose, songKey, bgi, mdi);
            } catch (IOException e) {
                e.printStackTrace();
            }
            baseFrame.dispose();
            return;
        }
        JComboBox cb = (JComboBox)evt.getSource();
        songKey = (String)cb.getSelectedItem();
    }

    public void itemStateChanged(ItemEvent evt){
        Object source = evt.getItemSelectable();

        if (source == verbose)
            isVerbose = !isVerbose;
    }

    public void stateChanged(ChangeEvent evt){
        JSlider source = (JSlider)evt.getSource();
        if (!source.getValueIsAdjusting()) {
            songTempo = source.getValue();
            tempoLabel.setText("Tempo: " + String.valueOf(songTempo));
        }
    }
}
