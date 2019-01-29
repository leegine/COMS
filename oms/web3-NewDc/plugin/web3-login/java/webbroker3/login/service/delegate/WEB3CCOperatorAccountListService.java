head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧サービス(WEB3CCOperatorAccountListService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成 モデルNo.039
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (CCオペレータ対象顧客一覧サービス)<BR>
 * CCオペレータ対象顧客一覧サービス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public interface WEB3CCOperatorAccountListService extends WEB3BusinessService
{

    /**
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定してを
     * 返す。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46A0291701C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
