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
filename	WEB3EquityMarginExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文約定照会サービス(WEB3EquityMarginExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 崔遠鵬(中訊) 新規作成
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (株式・信用注文約定照会サービス)<BR>
 * 株式・信用注文約定照会サービスインターフェイス<BR>
 * <BR>
 * @@ author 崔遠鵬 <BR>
 * @@ version 1.0<BR>
 */
public interface WEB3EquityMarginExecuteReferenceService extends WEB3BusinessService
{
    /**
     * 株式・信用注文約定照会サービス処理を実施する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエスト<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 455D1AAE0318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
