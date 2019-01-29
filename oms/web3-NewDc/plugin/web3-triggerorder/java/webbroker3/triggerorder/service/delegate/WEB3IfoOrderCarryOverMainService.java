head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越メインサービス(WEB3IfoOrderCarryOverMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/21 趙林鵬(中訊) 新規作成 モデルNo.669
Revision History : 2007/07/11 趙林鵬(中訊) モデルNo.774
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (先物OP注文繰越メインサービス)<BR>
 * 先物OP注文繰越メインサービスインタフェイス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3IfoOrderCarryOverMainService extends WEB3BackBusinessService
{
    /**
     * 先物OP注文繰越処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;

    /**
     * (create翌日注文)<BR>
     * 翌日注文を作成する。<BR>
     * @@param l_mainAccount  - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType) throws WEB3BaseException;
}
@
