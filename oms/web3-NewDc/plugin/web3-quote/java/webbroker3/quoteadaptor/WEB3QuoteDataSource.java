head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSource.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor;

import java.util.List;

/**
 * システム内での時価情報の供給源を表すインターフェイス。<br>
 *
 * @@author Takuji Yamada
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
public interface WEB3QuoteDataSource
{

    /**
     * 時価情報の通知イベントを処理するハンドラを登録する。
     *
     * @@param handler 時価情報の通知イベントを処理するハンドラ。
     */
    public void registerHandler(WEB3QuoteEventHandler handler);

    /**
     * 時価情報の供給を開始する。
     *
     */
    public void start();

    /**
     * 時価情報の供給を停止する。
     *
     */
    public void stop();
    
    /**
     * サービスIDを取得する。
     * 
     */
    public String getServiceId();
    
    /**
     * サービススタート済か取得する。
     * 
     */
    public boolean isStarted();
    
    /**
     * 時価サーバ一覧を取得する。
     * 
     */
    public List getFeeders();

}
@
