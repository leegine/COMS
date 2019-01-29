head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankTelegramProcessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク電文処理サービスインターフェイス(WEB3AioMultiBankTelegramProcessService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 王蘭芬(中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー     
*/

package webbroker3.aio.service.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webbroker3.servlet.WEB3HttpService;

/**
 * (マルチバンク電文処理サービス)<BR>
 * マルチバンク電文処理サービスインターフェイス
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3AioMultiBankTelegramProcessService extends WEB3HttpService 
{
    
    /**
     * マルチバンク電文処理を行う。
     * @@param l_request - リクエストデータ
     * @@param l_response - レスポンスデータ
     * @@roseuid 41255C2201AA
     */
    public void execute(HttpServletRequest l_request, HttpServletResponse l_response)
        throws ServletException, IOException;
}
@
