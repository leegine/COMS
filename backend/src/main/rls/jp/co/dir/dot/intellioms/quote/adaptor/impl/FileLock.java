/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FileLock�N���X(FileLock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/14 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.File;
import java.io.IOException;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * �������̓ǂݍ��݁E�����݂��s���t�@�C�������b�N����N���X�B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class FileLock
{
    
    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());
    
    /** ���b�N�Ɏg�p����t�@�C�� */
    private final File lockFile;

    /**
     * �R���X�g���N�^
     */
    public FileLock(File l_lockFile)
    {
        this.lockFile = l_lockFile;
    }
    
    /**
     * �ǂݍ��݃��b�N��ݒ肷��B
     */
    boolean readLock()
    {
        
        boolean l_isLocked = true;
        
        try
        {
            
            // ���b�N�p�t�@�C�����쐬
            while (!lockFile.createNewFile())
            {
                
                // 100�~���b�ҋ@
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
     * �ǂݍ��݃��b�N����������B
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
     * �����݃��b�N��ݒ肷��B
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
     * �����݃��b�N����������B
     *  
     */
    void writeUnLock()
    {
    }

}
