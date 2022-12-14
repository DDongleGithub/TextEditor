import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


//1:26
public class TextEditor implements ActionListener {
    //Frame for text editor
    JFrame frame;
    //Create a constructor of class textEditor

    // menubar for containing menus
    JMenuBar menuBar;

    // Create menues or declare
    JMenu file, edit, exit;

    // Add menu items to the file menu
    JMenuItem newFile, openFile, saveFile;

    // menu items for edit menu
    JMenuItem cut, copy, paste, selectAll;

    JMenuItem close;
    // area for writing text area
    JTextArea textArea;



    TextEditor() {
        //Initialize frame
         frame = new JFrame();

        //Initialize menubar
        menuBar = new JMenuBar();

        //Initialize textarea
        textArea = new JTextArea();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        exit = new JMenu("Exit");



        //Initialize menu items for file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        // Add action listeners to menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize menu items foe edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");

        //Add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        //Add menu items to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        // Add this menues to menubar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(exit);

        close = new JMenuItem("Close");

        close.addActionListener(this);
        exit.add(close);

        // Add this menubar to the frame
        frame.setJMenuBar(menuBar);

        // Adding textArea to frame
        frame.add(textArea);

        // set boundries for frame
        frame.setBounds(100, 100, 1000,500);

        //Visibility of frame
        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close) {
            System.exit(0);
        }
        if(actionEvent.getSource() == newFile)
        {
            // create new window
            TextEditor newWindow = new TextEditor();
        }

        if(actionEvent.getSource() == openFile)
        {
            // open a text file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //get selected file
                File file = fileChooser.getSelectedFile();
                //get selected file path
                String filePath = file.getPath();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    // strings to store content from file
                    String intermidiate = "" , output = "" ;
                    //read content line by line
                    while((intermidiate = bufferedReader.readLine()) != null)
                    {
                        output += intermidiate + "\n";
                    }
                    //set output to text area
                    textArea.setText(output);
                }catch (Exception exception){
                        exception.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource() == saveFile)
        {
            // save a file
            // create file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                        BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                        textArea.write(outfile);
                        outfile.close();
                }catch (Exception exception){
                        exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
    // initialize text editor
    TextEditor textEditor = new TextEditor();
    }
}