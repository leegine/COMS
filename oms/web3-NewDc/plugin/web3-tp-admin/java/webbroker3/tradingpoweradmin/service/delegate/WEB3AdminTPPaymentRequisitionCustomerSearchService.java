head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索サービス(WEB3AdminTPPaymentRequisitionCustomerSearchService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 安陽(中訊) 新規作成 モデルNo.027
*/

package webbroker3.tradingpoweradmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (入金請求顧客検索サービス)<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3AdminTPPaymentRequisitionCustomerSearchService extends WEB3BusinessService
{
    /**
     * 入金請求顧客検索サービス処理を行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
