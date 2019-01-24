/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FileLockクラス(FileLock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/14 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.File;
import java.io.IOException;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * 時価情報の読み込み・書込みを行うファイルをロックするクラス。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class FileLock
{
    
    /** ロガー */
    private final Log log = Log.getLogger(getClass());
    
    /** ロックに使用するファイル */
    private final File lockFile;

    /**
     * コンストラクタ
     */
    public FileLock(File l_lockFile)
    {
        this.lockFile = l_lockFile;
    }
    
    /**
     * 読み込みロックを設定する。
     */
    boolean readLock()
    {
        
        boolean l_isLocked = true;
        
        try
        {
            
            // ロック用ファイルを作成
            while (!lockFile.createNewFile())
            {
                
                // 100ミリ秒待機
                Thread.sleep(100);
                
            }
            
        } catch (IOException l_ioe)
        {
            log.warn(l_ioe.getMessage(), l_ioe);
            l_isLocked = false;
        } catch (InterruptedException l_ie)
        {
            log.warn(l_ie.getMessage(), l_ie);
            l_isLocked = false;
        }
        
        return l_isLocked;
        
    }
    
    /**
     * 読み込みロックを解除する。
     *
     */
    void readUnLock()
    {
        boolean l_isDeleted = lockFile.delete();
        if (!l_isDeleted)
        {
            log.warn(lockFile.getPath() + " cound not delete.");
        }
    }
    
    /**
     * 書込みロックを設定する。
     */
    boolean writeLock()
    {
        
        boolean l_isLocked = true;
        
        try
        {
            
            while (lockFile.exists())
            {
                Thread.sleep(100);
            }
            
        } catch (InterruptedException l_ie)
        {
            log.warn(l_ie.getMessage(), l_ie);
            l_isLocked = false;
        }

        return l_isLocked;
    }
    
    /**
     * 書込みロックを解除する。
     *  
     */
    void writeUnLock()
    {
    }

}
