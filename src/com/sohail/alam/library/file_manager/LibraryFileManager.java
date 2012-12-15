/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.alam.library.file_manager;

import java.awt.Component;
import java.io.File;

/**
 *
 * @author sohail.alam
 */
public interface LibraryFileManager {

    File getFile(String str_fileLocation);
    
    /**
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
    boolean saveFileShowFileChooser(Component parent,
            String str_content, 
            String str_defaultFilename, 
            String str_ApproveButtonText,
            String str_ApproveButtonToolTipText, 
            String str_DialogTitle) throws Exception;
    
}
