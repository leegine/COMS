head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済保証金維持率割れ（場間）仮注文作成一件サービスImpl(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 孟亞南(中訊) 新規作成 仕様変更モデルNo.179
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済保証金維持率割れ（場間）仮注文作成一件サービスImpl)<BR>
 * 強制決済保証金維持率割れ（場間）仮注文作成一件サービスの実装クラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl
    extends WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
    implements WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.class);

    /**
     * @@roseuid 462CA423039E
     */
    public WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl()
    {

    }

    /**
     * (validate建玉強制決済)<BR>
     * 建を保有する顧客が本証不足かどうかの判定を行う。<BR>
     * <BR>
     * １）　@補助口座を取得する <BR>
     * 　@拡張アカウントマネージャ.get補助口座()をcallする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@口座ID：　@引数.建株Row.getAccountId()の戻り値<BR>
     * 　@　@補助口座ID：　@引数.建株Row.getSubAccountId()の戻り値<BR>
     * <BR>
     * ２）　@余力チェックを行う。<BR>
     * 　@取引余力サービス.validate建玉強制決済～場間～()<BR>
     * 　@をcallし、戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@補助口座：　@get補助口座()の戻り値 <BR>
     * <BR>
     * @@param l_eqtypeContractRow - (建株Row)<BR>
     * 建株Row
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettle(EqtypeContractRow l_eqtypeContractRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateContractForcedSettle(EqtypeContractRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeContractRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //補助口座を取得する
        //　@[引数]
        //　@　@口座ID：　@引数.建株Row.getAccountId()の戻り値
        //　@　@補助口座ID：　@引数.建株Row.getSubAccountId()の戻り値
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_eqtypeContractRow.getAccountId(),
                    l_eqtypeContractRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //余力チェックを行う。
        //　@取引余力サービス.validate建玉強制決済～場間～()
        //　@をcallし、戻り値を返却する。
        //　@[引数]
        //　@　@補助口座：　@get補助口座()の戻り値
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPContractForcedSettleResult l_contractForcedSettleResult =
            l_tradingPowerService.validateContractForcedSettleIntermission(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return l_contractForcedSettleResult;
    }
}
@
