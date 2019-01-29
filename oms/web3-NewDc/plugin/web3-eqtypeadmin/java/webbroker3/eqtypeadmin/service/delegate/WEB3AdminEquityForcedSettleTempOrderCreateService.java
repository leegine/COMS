head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成サービス(WEB3AdminEquityForcedSettleTempOrderCreateService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (強制決済仮注文作成サービス)<BR>
 * 強制決済仮注文作成サービスインターフェイス <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleTempOrderCreateService extends WEB3BackBusinessService
{    
    /**
     * 強制サービス仮注文作成サービスを実施する。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@roseuid 4603696C02A8
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
