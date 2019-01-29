head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	ThreadFactoryBase.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �X���b�h�쐬����x�[�X�t�@@�N�g���N���X(ThreadFactoryBase.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.threadpool;

/*
 **
 * �X���b�h�쐬����x�[�X�t�@@�N�g���N���X.
 */
public class ThreadFactoryBase
{

    protected ThreadFactory threadFactory_ = new DefaultThreadFactory();

    protected static class DefaultThreadFactory
        implements ThreadFactory
    {
        public Thread newThread(Runnable command)
        {
            return new Thread(command);
        }
    }

    /**
     *  �X���b�h�쐬����t�@@�N�g����ݒ肷��.
     **/
    public synchronized ThreadFactory setThreadFactory(ThreadFactory factory)
    {
        ThreadFactory old = threadFactory_;
        threadFactory_ = factory;
        return old;
    }

    /**
     *  �X���b�h�쐬����t�@@�N�g�����擾����.
     **/
    public synchronized ThreadFactory getThreadFactory()
    {
        return threadFactory_;
    }

}
@
