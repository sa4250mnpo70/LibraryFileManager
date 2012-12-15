/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.alam.library.file_manager;

import java.awt.Component;
import javax.swing.JFileChooser;

/**
 *
 * @author sohail.alam
 */
public interface LibraryFileManager {

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
    public JFileChooser showFileChooser(Component parent,
            String str_defaultFilename,
            boolean uniqueFilename,
            String str_ApproveButtonText,
            String str_ApproveButtonToolTipText,
            String str_DialogTitle) throws Exception;
    
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
    public boolean saveFileShowFileChooser(Component parent,
            String str_content,
            String str_defaultFilename,
            String str_ApproveButtonText,
            String str_ApproveButtonToolTipText,
            String str_DialogTitle) throws Exception;
    
}
