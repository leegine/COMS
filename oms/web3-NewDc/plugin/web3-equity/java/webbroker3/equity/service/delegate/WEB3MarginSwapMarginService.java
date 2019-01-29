head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡サービス(WEB3MarginSwapMarginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （信用取引現引現渡サービス）。<BR>
 * <BR>
 * 信用取引現引現渡サービスインタフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginService extends WEB3BusinessService 
{
    
    /**
     * 信用取引現引現渡サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056920702E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
