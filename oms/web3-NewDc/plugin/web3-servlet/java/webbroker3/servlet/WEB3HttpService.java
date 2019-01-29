head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3HttpServiceクラス(WEB3HttpService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/29 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * HttpRequestとHttpResponseをパラメータとして受け取り、
 * 処理を行うServiceが実装するインターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3HttpService extends Service
{
    
    /**
     * このサービスに関連付けられたHTTPリクエストを処理する。
     * 
     * @@param request 処理対象のHttpリクエスト
     * @@param response 処理結果として返されるHttpレスポンス
     * @@throws ServletException
     * @@throws IOException
     */
    public void execute(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException;

}
@
