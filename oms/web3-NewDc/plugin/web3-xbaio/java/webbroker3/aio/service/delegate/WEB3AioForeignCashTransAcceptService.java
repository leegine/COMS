head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioForeignCashTransAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金受付サービス(WEB3AioForeignCashTransAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (外貨入出金受付サービス)<BR>
 * 外貨入出金受付サービスインターフェイス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AioForeignCashTransAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * 外貨入出金受付処理を行う。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}@
