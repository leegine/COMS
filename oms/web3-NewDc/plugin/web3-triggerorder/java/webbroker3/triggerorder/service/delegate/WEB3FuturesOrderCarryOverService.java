head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文繰越サービスインタフェイス(WEB3FuturesOrderCarryOverService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/19 艾興 (中訊) 新規作成
Revesion History : 2007/06/28 金傑 (中訊) モデルNo.671
Revision History : 2007/07/12 趙林鵬(中訊) モデルNo.775
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (先物注文繰越サービス)<BR>
 * 株価指数先物注文繰越サービスインタフェイス<BR>
 */
public interface WEB3FuturesOrderCarryOverService extends WEB3IfoOrderCarryOverMainService 
{
    
    /**
     * 先物注文繰越サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物サービス）先物注文繰越」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A99EC50289
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
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
