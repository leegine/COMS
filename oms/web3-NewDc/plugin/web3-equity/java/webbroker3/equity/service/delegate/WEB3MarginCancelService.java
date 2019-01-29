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
filename	WEB3MarginCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引取消サービス(WEB3MarginCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 凌建平 (中訊) 新規作成
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （信用取引取消サービス）。<BR>
 * <BR>
 * 信用取引取消サービスインタフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3MarginCancelService extends WEB3BusinessService 
{
    
    /**
     * 信用取引取消サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405807B600AE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
