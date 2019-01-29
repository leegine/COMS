head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）サービス(WEB3AioSonarCashTransForeignService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (SONAR入出金（外貨）サービス)<BR>
 * SONAR入出金（外貨）サービスインターフェイス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AioSonarCashTransForeignService extends WEB3BackBusinessService
{
    /**
     * SONAR入出金（外貨）サービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
