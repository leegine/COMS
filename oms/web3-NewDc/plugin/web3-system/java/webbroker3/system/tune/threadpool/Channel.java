head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	Channel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : チャネルインターフェース(Channel.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/06 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.threadpool;

/*
 * チャネルインターフェース
 */
public interface Channel
{

    /**
     * チャネルにアイテムを入れる
     **/
    public void put(Object item) throws InterruptedException;

    /**
     * 時間範囲内、チャネルが受け入れる可能アイテムを入れる
     **/
    public boolean offer(Object item, long msecs) throws InterruptedException;

    /**
     * チャネルからアイテムを引出する
     **/
    public Object take() throws InterruptedException;

    /**
     * 時間範囲内、チャネルが引出可能アイテムを引出する
     **/
    public Object poll(long msecs) throws InterruptedException;

    /**
     * チャネル頭からアイテムを取得する
     **/
    public Object peek();

}
@
