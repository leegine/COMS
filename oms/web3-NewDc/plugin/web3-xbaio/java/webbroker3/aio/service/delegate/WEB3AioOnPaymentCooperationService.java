head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携サービス(WEB3AioOnPaymentCooperationService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (出金連携サービス) <BR>
 * 出金連携サービスインターフェイス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3AioOnPaymentCooperationService extends WEB3BackBusinessService
{
    /**
     * 出金連携処理を行う。
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@roseuid 41BCF2CC0279
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}@
