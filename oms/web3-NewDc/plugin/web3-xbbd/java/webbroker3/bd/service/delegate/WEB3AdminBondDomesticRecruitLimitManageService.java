head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理サービス(WEB3AdminBondDomesticRecruitLimitManageService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.214
*/
package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者国内債券部店別応募枠管理サービス)<BR>
 * 管理者国内債券部店別応募枠管理サービスインターフェイス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AdminBondDomesticRecruitLimitManageService extends WEB3BusinessService
{

    /**
     * 管理者国内債券部店別応募枠管理サービス処理を実施する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * リクエストオブジェクト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4695AE800130
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
