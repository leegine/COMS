head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS口座開設状況変更サービス(WEB3AdminInformPTSAccOpenStateChangeService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/27 柴双紅(中訊) 新規作成 モデルNo.129
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 管理者PTS口座開設状況変更サービス<BR>
 * 管理者PTS口座開設状況変更サービスインタフェイス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AdminInformPTSAccOpenStateChangeService extends WEB3BusinessService
{
    /**
     * 管理者PTS口座開設状況変更サービス処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A00E3601CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
