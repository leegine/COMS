head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS申込客一覧問合せサービス(WEB3AdminInformPTSAccountListService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.130
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者PTS申込客一覧問合せサービス)<BR>
 * 管理者PTS申込客一覧問合せサービスインタフェイス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public interface WEB3AdminInformPTSAccountListService extends WEB3BusinessService
{

    /**
     * 管理者PTS申込客一覧問合せサービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B5370F0387
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
