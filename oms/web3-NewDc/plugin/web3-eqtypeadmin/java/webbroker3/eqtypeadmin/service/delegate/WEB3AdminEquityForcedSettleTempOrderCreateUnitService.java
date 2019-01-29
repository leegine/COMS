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
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成一件サービス(WEB3AdminEquityForcedSettleTempOrderCreateUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131
*/

package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;

/**
 * (強制決済仮注文作成一件サービス)<BR>
 * 強制決済仮注文作成一件サービスインターフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleTempOrderCreateUnitService extends Service
{
    /**
     * (exec仮注文作成)<BR>
     * 仮注文作成一件処理を実施する。<BR>
     * @@param l_eqtypeContractRow - (建株Row)<BR>
     * 建株Row<BR>
     * @@param l_strForcedSettleReasonType- (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@param l_contractForceSettleResult - (強制決済余力計算結果)<BR>
     * 余力で計算した建玉強制決済結果オブジェクト<BR>
     * @@param l_blnIsSuccOrderHandling - (is連続注文取扱可能)<BR>
     * 連続注文が取扱可能かどうかのフラグ<BR>
     * <BR>
     * true：　@連続注文取扱可能<BR>
     * false：　@連続注文取扱不可<BR>
     * @@roseuid 4610C94E012C
     */
    public void execTempOrderCreation(EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleReasonType,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        boolean l_blnIsSuccOrderHandling) throws WEB3BaseException;
}
@
