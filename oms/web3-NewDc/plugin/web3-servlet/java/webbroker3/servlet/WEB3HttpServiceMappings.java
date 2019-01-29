head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpServiceMappings.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3HttpServiceMappingsクラス(WEB3HttpServiceMappings.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/01 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * HTTPリクエスト送信時のURLで指定するサービス名と、
 * そのHTTPリクエストを処理するサービスのマッピングを保持するクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3HttpServiceMappings extends Service
{
    
    /**
     * サービス名とWEB3HttpServiceのマッピングを登録する。
     * 
     * @@param serviceName サービス名
     * @@param serviceInterface WEB3HttpServiceのインターフェース
     */
    public void addService(String l_serviceName, Class l_serviceInterface);
    
    /**
     * 指定されたサービス名にマッピングされたWEB3HttpServiceを取得する。
     * 
     * @@param serviceName サービス
     * @@return 指定されたサービス名にマッピングされたWEB3HttpService
     */
    public WEB3HttpService getService(String l_serviceName);
    
    
    /**
     * デフォルトで使用するWEB3HttpServiceを登録する。
     * 
     * @@param serviceInterface デフォルトで使用するWEB3HttpService
     */
    public void setDefaultService(Class l_serviceInterface);
    
    /**
     * デフォルトで使用するWEB3HttpServiceを取得する。
     * 
     * @@return
     */
    public WEB3HttpService getDefaultService();
    
    /**
     * 指定されたサービス名とWEB3HttpServiceのマッピングを削除する。
     * 
     * @@param serviceName サービス名
     */
    public void removeService(String l_serviceName);
    
}@
