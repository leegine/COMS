head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金サービス(WEB3AioSonarCashTransService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (SONAR入出金サービス)<BR>
 * SONAR入出金サービスインターフェイス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */
public interface WEB3AioSonarCashTransService extends WEB3BackBusinessService 
{
    
    /**
     * SONAR入出金サービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FF6E070138
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
