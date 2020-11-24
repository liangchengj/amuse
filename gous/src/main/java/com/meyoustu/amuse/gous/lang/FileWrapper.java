package com.meyoustu.amuse.gous.lang;

import com.meyoustu.amuse.gous.listen.TraverseDirListener;
import com.meyoustu.amuse.gous.mey.Share;

import java.io.File;
import java.net.URI;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/23 11:39
 */
public class FileWrapper extends File {

    public FileWrapper(String pathname) {
        super(pathname);
    }

    public FileWrapper(String parent, String child) {
        super(parent, child);
    }

    public FileWrapper(File parent, String child) {
        super(parent, child);
    }

    public FileWrapper(URI uri) {
        super(uri);
    }

    FileWrapper(File file) {
        super(file.getAbsolutePath());
    }

    public String getExtension() {
        if (isDirectory()) {
            return null;
        } else {
            return Share.subWithLastIndex(getName(), ".");
        }
    }

    public final void traverseDir(TraverseDirListener traverseDirListener) {
        if (isDirectory()) {
            for (File file : listFiles()) {
                if (file.isDirectory()) {
                    new FileWrapper(file.getAbsolutePath()).traverseDir(traverseDirListener);
                    System.gc();
                } else {
                    traverseDirListener.onTraversing(file);
                }
            }
        }
    }

}
