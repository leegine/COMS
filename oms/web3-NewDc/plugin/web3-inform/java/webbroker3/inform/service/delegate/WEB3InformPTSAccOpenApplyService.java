head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込サービス(WEB3InformPTSAccOpenApplyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.124
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (PTS口座開設申込サービス)<BR>
 * PTS口座開設申込サービスインタフェイス
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public interface WEB3InformPTSAccOpenApplyService extends WEB3BusinessService
{

    /**
     * PTS口座開設申込サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 47A00E1701CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
