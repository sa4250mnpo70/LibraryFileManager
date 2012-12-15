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
     * This method is used for showing a Swing based File Chooser, which can be
     * used while saving/opening a file.
     *
     * <p>
     * <pre>
     * <b>Code Example:</b>
     * <code>
     * int returnValue = 0;
     * File file = null;
     * Writer output;
     * JFileChooser fileChooser = showFileChooser(null, "Example", true,
     *             "Save", "Save this example file",
     *             "Example of SaveFileShowFileChooser");
     * try {
     *     returnValue = fileChooser.showOpenDialog(parent);
     * } catch (Exception e) {
     *     throw e;
     * }
     * if (returnValue == JFileChooser.APPROVE_OPTION) {
     *     file = fileChooser.getSelectedFile();
     * }
     * output = new BufferedWriter(new FileWriter(file));
     * try {
     *     output.write("Hello This is a Test!");
     * } finally {
     *     output.close();
     * }
     * </code>
     *
     * @param parent The parent component - can be <code>null</code>
     * @param str_defaultFilename A string containing any default file name.
     * @param uniqueFilename if <code>true</code> then this method will add a
     * current date and time to the default file name to keep it unique with a
     * .txt extension, otherwise only the default name will be used without any
     * extension.
     * @param str_ApproveButtonText The text that will be shown in place of the
     * Approve button
     * @param str_ApproveButtonToolTipText The tooltip that will be shown on
     * mouse hover over the Approve Button
     * @param str_DialogTitle The title of the File Chooser dialog/window
     * @return a javax.swing.JFileChooser
     * @throws Exception Exception thrown can be handled as required
     */
    @Override
    public JFileChooser showFileChooser(Component parent,
            String str_defaultFilename,
            boolean uniqueFilename,
            String str_ApproveButtonText,
            String str_ApproveButtonToolTipText,
            String str_DialogTitle) throws Exception {

        String current_date;
        String current_time;
        JFileChooser fileChooser;
        Calendar calendar;
        DateFormat date_Format;
        DateFormat time_Format;
        File file;

        //Get Current Date and Time
        date_Format = new SimpleDateFormat("ddMMyyyy");
        time_Format = new SimpleDateFormat("HHmmss");
        calendar = Calendar.getInstance();
        current_date = date_Format.format(calendar.getTime());
        current_time = time_Format.format(calendar.getTime());
        //Configure the filename
        if (uniqueFilename) {
            file = new File(str_defaultFilename
                    + current_date + "_"
                    + current_time + ".txt");
        } else {
            file = new File(str_defaultFilename);
        }

        //Configure the File Chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        fileChooser.setApproveButtonText(str_ApproveButtonText);
        fileChooser.setApproveButtonToolTipText(str_ApproveButtonToolTipText);
        fileChooser.setDialogTitle(str_DialogTitle);
        fileChooser.setSelectedFile(file);
        return fileChooser;
    }

    /**
     * This method shows the user with a Swing based File Chooser GUI interface,
     * using which the user can specify the location where to save the file. <p>
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
     * @param parent The parent component - can be <code>null</code>
     * @param str_defaultFilename A string containing any default file name.
     * @param uniqueFilename if <code>true</code> then this method will add a
     * current date and time to the default file name to keep it unique with a
     * .txt extension, otherwise only the default name will be used without any
     * extension.
     * @param str_ApproveButtonText The text that will be shown in place of the
     * Approve button
     * @param str_ApproveButtonToolTipText The tooltip that will be shown on
     * mouse hover over the Approve Button
     * @param str_DialogTitle The title of the File Chooser dialog/window
     * @return <code>true</code> if file is properly saved else throws Exception
     * @throws Exception Exception thrown can be handled as required
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
        JFileChooser fileChooser = showFileChooser(parent, str_defaultFilename, true,
                str_ApproveButtonText, str_ApproveButtonToolTipText, str_DialogTitle);

        File file = null;
        Writer output;

        output = null;
        try {
            returnValue = fileChooser.showOpenDialog(parent);
        } catch (Exception e) {
            throw e;
        }
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
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
            output.close();
        }
    }
}
