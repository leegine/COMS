head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderQueue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���s����v�����[�_�[�̃C���X�^���X��ێ�����L���[(WEB3PreLoaderQueue.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/19 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * ���s����v�����[�_�[�̃C���X�^���X��ێ�����L���[�B
 * 
 * �v�����[�h�����s���镡���̃X���b�h����Q�Ƃ����B 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3PreLoaderQueue
{
    
    /**
     * �v�����[�_�[��ێ����郊�X�g
     */
    private LinkedList queue;
    
    /**
     * �R���X�g���N�^
     */
    WEB3PreLoaderQueue()
    {
        queue = new LinkedList();
    }
    
    /**
     * �L���[�̍Ō�Ƀv�����[�_�[��ǉ�����B
     */
    synchronized void push(WEB3PreLoader l_preLoader)
    {
        queue.addLast(l_preLoader);
    }
    
    /**
     * �L���[�̐擪����v�����[�_�[���擾���A�擪�̃v�����[�_�[���L���[����폜����B
     * �L���[����̏ꍇ�́A<code>null</code>��Ԃ��B
     */
    synchronized WEB3PreLoader pop()
    {
        WEB3PreLoader l_preLoader = null; 
        try
        {
            l_preLoader = (WEB3PreLoader) queue.removeFirst();
        } catch (NoSuchElementException e)
        {
        }
        return l_preLoader;
    }
    
    /**
     * �L���[���v�����[�_�[��ێ����Ȃ��ꍇ��<code>true</code>��Ԃ��B
     */
    synchronized boolean isEmpty()
    {
        return queue.isEmpty();
    }

}
@
