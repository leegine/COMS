head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会サービス(WEB3AdminToIfoOrderReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (トリガー注文管理者・先物OP注文照会サービス)<BR>
 * トリガー注文管理者・先物OP注文照会サービスインターフェイス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public interface WEB3AdminToIfoOrderReferenceService extends WEB3BusinessService
{
    
    /**
     * 先物OP注文照会処理を行う。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E355490150
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
