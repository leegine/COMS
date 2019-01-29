head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderThread.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�����[�h�����s����X���b�h(WEB3PreLoaderThread.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/19 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import webbroker3.util.WEB3LogUtility;


/**
 * �v�����[�h�����s����X���b�h�B
 * 
 * �o�^���ꂽ�L���[����ɂȂ�܂ŁA�L���[����v�����[�_�[���擾���A
 * �擾�����v�����[�_�[�����s����B
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3PreLoaderThread extends Thread
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility LOG = WEB3LogUtility.getInstance(WEB3PreLoaderThread.class);
    
    private static final boolean DBG = LOG.ison();
    
    /**
     * ���s����v�����[�_�[��ێ�����L���[
     */
    private WEB3PreLoaderQueue queue;
    
    /**
     * ���̃X���b�h�̃X�e�[�^�X
     */
    private boolean isActive;
    
    public void run()
    {
        
        synchronized(this)
        {
            if (queue == null)
            {
                throw new IllegalStateException("preloader queue is not registered.");
            }
            isActive = true;
            
            if (DBG)
            {
                LOG.debug(getName() + " start loading.");
            }
            
        }
        
        try {
            
            while(isActive)
            {
                synchronized(this)
                {
                    WEB3PreLoader l_preLoader = queue.pop();
                    if (l_preLoader != null)
                    {
                        l_preLoader.execute();
                    } else
                    {
                        isActive = false;
                        break;
                    }
                }
            }
            
        } catch (Throwable l_th)
        {
            LOG.error("Unexpected exception occured.", l_th);
        }
        
        if (DBG)
        {
            LOG.debug(getName() + " stop loading.");
        }
            
    }
    
    /**
     * ���s����v�����[�_�[��ێ�����L���[��ݒ肷��B
     * 
     * @@param l_queue �L���[
     */
    synchronized void setQueue(WEB3PreLoaderQueue l_queue)
    {
        this.queue = l_queue;
    }
    
    /**
     * ���̃X���b�h�̃X�e�[�^�X��Ԃ��B
     * �o�^����Ă���L���[����ɂȂ����ꍇ�A<code>true</code>��Ԃ��B
     * 
     * @@return �X�e�[�^�X
     */
    synchronized boolean isActive()
    {
        return this.isActive;
    }
    
    
    
}
@
