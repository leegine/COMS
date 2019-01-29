head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知一件サービスImpl(WEB3TPReCalcNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerUpd;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

/**
 * (余力計算通知一件サービスImpl)
 */
public class WEB3TPReCalcNotifyUnitServiceImpl implements WEB3TPReCalcNotifyUnitService
{
    /**
     * （ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 423547C1020E
     */
    public WEB3TPReCalcNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify余力再計算)
     * @@param l_subAccount - (補助口座)
     * @@param 余力再計算キューParams - (余力再計算キューParams)
     * @@roseuid 42353FAB0394
     */
    public void notifyReCalc(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultExecNotifyParams l_params)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "notifyReCalc(WEB3GentradeSubAccount, TpCalcResultExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        /*
         * 口座ロックする
         */
        l_subAccount.serializeOperationsWithWait();

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //信用口座開設区分を取得
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        /*
         * 余力計算条件を生成
         */
        WEB3TPCalcCondition l_calcCond = null;

        //時価計算フラグ＝前日終値評価の場合
        if (l_params.real_calc_flag.equals(WEB3TPRealCalcFlagDef.CLOSING_PRICE) == true)
        {
            l_calcCond = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
        }
        //時価計算フラグ＝時価評価の場合
        else
        {
            l_calcCond = WEB3TPCalcCondition.createCalcConditionQuote(l_subAccount);
        }

        /*
         * 余力更新
         */
        WEB3TPTradingPowerUpd l_tpUpd =
            new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

        //現物顧客の場合
        if (l_blnMargin == false)
        {
            //余力更新内容を取得
            List l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity();
            //余力更新内容をテーブルに挿入
            l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);
        }
        //信用顧客の場合
        else
        {
            //余力更新内容を取得
            List l_updResults =
                l_tpUpd.calcTradingpowerUpdResultMargin(l_params.mark_to_market_div);
            //余力更新内容をテーブルに挿入
            l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);
        }

        /*
         * システムタイプスタンプを取得
         */
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
        Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();
        
        /*
         * 余力計算キューテーブル.処理区分を1：処理済に更新する。
         */
        l_params.setStatus(WEB3TPStatusDef.DEAL);
        l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        l_processor.doUpdateQuery(l_params);

        log.exiting(STR_METHOD_NAME);
    }
}
@
