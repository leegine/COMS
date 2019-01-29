head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteEventHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor;

/**
 * <p>
 * 時価情報通知イベントを処理するハンドラ。<br>
 * </p>
 *
 * @@author Yoshihara Tadafumi
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3QuoteEventHandler
{

    /**
     * 時価情報通知イベントを処理する。
     *
     * @@param event 時価情報通知イベント。
     */
    public void push(WEB3QuoteEvent event);

}
@
