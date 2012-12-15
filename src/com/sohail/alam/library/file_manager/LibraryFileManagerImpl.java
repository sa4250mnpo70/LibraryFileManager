/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.alam.library.file_manager;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author sohail.alam
 */
public class LibraryFileManagerImpl implements LibraryFileManager {

    /**
     * 
     * @param str_fileLocation
     * @return 
     */
    public File getFile(String str_fileLocation){
        File file;
        
        file = new File(str_fileLocation);
        
        return file;
    }
    
    /**
     *
     * <p>
     * <pre>
     * <b>Code Example:</b>
     * <code>
     * LibraryFileManagerImpl filemanager = new LibraryFileManagerImpl();
     * String content = "something to save.";
     * try {
     *     if (filemanager.saveFileShowFileChooser(MyClassName.this, content)) {
     *         System.out.println("Log File Created Successfully!");
     *     } else {
     *         System.out.println("Log File could not be created!");
     *     }
     * } catch (Exception e) {
     *   System.out.println("Log File could not be created!");
     * }
     * </code>
     * </pre> </p>
     *
     * @param parent
     * @param str_content 
     * @param str_defaultFilename 
     * @param str_ApproveButtonText 
     * @param str_ApproveButtonToolTipText 
     * @param str_DialogTitle 
     * @param content
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveFileShowFileChooser(Component parent,
            String str_content, 
            String str_defaultFilename, 
            String str_ApproveButtonText,
            String str_ApproveButtonToolTipText, 
            String str_DialogTitle) throws Exception {

        int overwriteFile;
        int returnValue;
        String current_date;
        String current_time;
        JFileChooser fileChooser;
        Calendar calendar;
        DateFormat date_Format;
        DateFormat time_Format;
        File file;
        Writer output;

        output = null;
        //Get Current Date and Time
        date_Format = new SimpleDateFormat("ddMMyyyy");
        time_Format = new SimpleDateFormat("HHmmss");
        calendar = Calendar.getInstance();
        current_date = date_Format.format(calendar.getTime());
        current_time = time_Format.format(calendar.getTime());
        //Configure the filename
        file = new File(str_defaultFilename
                + current_date + "_"
                + current_time + ".txt");
        //Configure the File Chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        fileChooser.setApproveButtonText(str_ApproveButtonText);
        fileChooser.setApproveButtonToolTipText(str_ApproveButtonToolTipText);
        fileChooser.setDialogTitle(str_DialogTitle);
        fileChooser.setSelectedFile(file);

        returnValue = fileChooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        //Add txt extension if not added by user
        if (file.getName().indexOf(".") == -1) {
            file = new File(file + ".txt");
        }

        try {
            // if file doesnt exists, then create it
            if (!file.exists()) {
                output = new BufferedWriter(new FileWriter(file));
                try {
                    output.write(str_content);
                } finally {
                    output.close();
                }
            } else { //Give user option to overwrite or append to the existing file.
                //Yes: 0, No: 1, Cancle: 2
                Object[] options = new Object[3];
                options[0] = "Append";
                options[1] = "Overwrite";
                options[2] = "Cancel";
                overwriteFile = JOptionPane.showOptionDialog(parent,
                        "File already exists! Do you want to Overwrite or Append the file?",
                        "Confirm File Appending!",
                        1, 3, null,
                        options, "");
                switch (overwriteFile) {
                    case 0:
                        output = new BufferedWriter(new FileWriter(file, true));
                        try {
                            output.append(str_content);
                        } finally {
                            output.close();
                        }
                        break;
                    case 1:
                        output = new BufferedWriter(new FileWriter(file));
                        try {
                            output.write(str_content);
                        } finally {
                            output.close();
                        }
                        break;
                    case 2:
                        output.close();
                        break;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            output.close();
        }
        return true;
    }
}
