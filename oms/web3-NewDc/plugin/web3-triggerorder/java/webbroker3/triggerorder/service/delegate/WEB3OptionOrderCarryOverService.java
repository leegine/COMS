head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文繰越サービス(WEB3OptionOrderCarryOverService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/12 張威 (中訊) 新規作成
Revesion History : 2007/06/21 金傑 (中訊) モデル670
Revision History : 2007/07/12 趙林鵬(中訊) モデルNo.775
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (OP注文繰越サービス)<BR>
 * OP注文繰越サービスインターフェイス<BR>
 *                                                                    
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3OptionOrderCarryOverService extends WEB3IfoOrderCarryOverMainService
{

    /**
     * OP注文繰越サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）オプション注文繰越」参照。<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 409B12C00159
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
    
    /**
     * (create翌日注文)<BR>
     * 翌日注文を作成する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
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
        String l_strCarryoverProcessType)
        throws WEB3BaseException;
}
@
