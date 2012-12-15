/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.alam.library.file_manager.example;

import com.sohail.alam.library.file_manager.LibraryFileManagerImpl;

/**
 *
 * @author sohail.alam
 */
public class SaveFileShowFileChooserExample {

    public static void main(String args[]) {

        boolean isSaved = false;
        String data = "This is some test data!";
        LibraryFileManagerImpl fileManager = new LibraryFileManagerImpl();

        try {
            isSaved = fileManager.saveFileShowFileChooser(null, data, "Example",
                    "Save", "Save this example file",
                    "Example of SaveFileShowFileChooser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isSaved) {
            System.out.println("File Saved Successfully!");
        } else {
            System.out.println("File Save Failed!!");
        }
    }
}
