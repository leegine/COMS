head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡受付サービス(WEB3MarginSwapMarginAcceptService.java)
Author Name      : 2004/10/8 盧法@旭(中訊) 新規作成
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （信用取引現引現渡受付サービス）。<BR>
 * <BR>
 * 信用取引現引現渡受付サービスインタフェース<BR>
 *（トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 法@旭
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * 信用取引現引現渡受付サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4057ADC6015A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
