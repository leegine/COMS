head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	ThreadFactory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ToStringで出力される文字列を生成するクラス(ThreadFactory.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.threadpool;

/*
 **
 * スレッド作成するファ@クトリインターフェース.
 */
public interface ThreadFactory
{

    /**
     * 与えるタスクにスレッドでタスクを実行する
     **/
    public Thread newThread(Runnable command);

}
@
