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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : スレッド作成するベースファ@クトリクラス(ThreadFactoryBase.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.threadpool;

/*
 **
 * スレッド作成するベースファ@クトリクラス.
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
     *  スレッド作成するファ@クトリを設定する.
     **/
    public synchronized ThreadFactory setThreadFactory(ThreadFactory factory)
    {
        ThreadFactory old = threadFactory_;
        threadFactory_ = factory;
        return old;
    }

    /**
     *  スレッド作成するファ@クトリを取得する.
     **/
    public synchronized ThreadFactory getThreadFactory()
    {
        return threadFactory_;
    }

}
@
