head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSupplierServiceManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価アダプターの管理用メソッドを提供するサービスクラス(WEB3QuoteDataSupplierServiceManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/07/01 山田　@卓司(FLJ) 新規作成
                 : 2005/04/28 山田　@卓司(FLJ) 時価監視の開始・終了処理を追加
                 : 2009/04/23 齋藤　@栄三(FLJ) 時価システムQUICKへの移行
*/
package webbroker3.quoteadaptor.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * 時価アダプターの管理用メソッドを提供するサービスクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3QuoteDataSupplierServiceManager extends Service
{
    /**
     * 指定したサービスIDの時価サービスを開始する。
     * 
     * @@param id サービスID
     */
    public void startService(String id);
    
    /**
     * 登録されている全ての時価サービスを開始する。
     * 
     */
    public void startAllServices();
    
    /**
     * 指定したサービスIDの時価サービスを停止する。
     * 
     * @@param id サービスID
     */
    public void stopService(String id);
    
    /**
     * 登録されている全ての時価サービスを停止する。
     */
    public void stopAllServices();
    
    /**
     * キャッシュされている時価情報をファ@イルに出力する。
     */
    public void dump();
    
    /**
     * 時価情報の監視を開始する。
     */
    public void startMonitoring();
    
    /**
     * 時価情報の監視を終了する。
     */
    public void stopMonitoring();
    
    
    /**
     * RMMデータソース同士で切替する。
     * 
     */
    public WEB3QuoteFutureData changeRMMDataSource();
    
    /**
     * TCPとRMMのデータソースを切替える。
     * 
     */
    public String switchDataSourceProtocol();
}@
