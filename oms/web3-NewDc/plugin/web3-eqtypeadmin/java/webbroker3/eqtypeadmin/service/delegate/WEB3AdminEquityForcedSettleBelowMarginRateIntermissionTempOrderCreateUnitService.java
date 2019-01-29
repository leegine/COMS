head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済保証金維持率割れ（場間）仮注文作成一件サービス(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 孟亞南(中訊) 新規作成 仕様変更モデルNo.179
*/
package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;

/**
 * (強制決済保証金維持率割れ（場間）仮注文作成一件サービス)<BR>
 * 強制決済保証金維持率割れ（場間）仮注文作成一件サービス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService
    extends WEB3AdminEquityForcedSettleTempOrderCreateUnitService
{
    /**
     * (validate建玉強制決済)<BR>
     * 建を保有する顧客が本証不足かどうかの判定を行う。<BR>
     * <BR>
     * @@param l_eqtypeContractRow - 建株Row<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettle(EqtypeContractRow l_eqtypeContractRow)
        throws WEB3BaseException;
}
@
