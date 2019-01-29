head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者トレードボーナスプラン照会サービス(WEB3AdminPointTradeBonusPlanReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 郭英(中訊) 新規作成
*/


package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者トレードボーナスプラン照会サービス)<BR>
 * 管理者トレードボーナスプラン照会サービスインターフェイス<BR>
 */
public interface WEB3AdminPointTradeBonusPlanReferenceService extends WEB3BusinessService
{
    
    /**
     * 管理者トレードボーナスプラン照会サービス処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A4FED8004C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
