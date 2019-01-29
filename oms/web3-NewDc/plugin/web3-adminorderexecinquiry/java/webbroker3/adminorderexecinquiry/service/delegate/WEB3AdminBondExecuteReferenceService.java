head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会サービス(WEB3AdminBondExecuteReferenceService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成    
*/

package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (債券管理者注文約定照会サービス)<BR>
 * 管理者債券注文約定照会サービスインタフェース<BR>
 *  
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public interface WEB3AdminBondExecuteReferenceService extends WEB3BusinessService
{
    
    /**
     * 債券管理者注文約定照会を行う
     * @@param l_request - リクエスト<BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B7510202CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
