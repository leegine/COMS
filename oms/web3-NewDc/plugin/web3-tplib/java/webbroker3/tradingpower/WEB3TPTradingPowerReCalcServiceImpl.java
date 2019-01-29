head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerReCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算サービスImpl(WEB3TPTradingPowerReCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPOccurredDivDef;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力再計算サービスImpl)<BR>
 */
public class WEB3TPTradingPowerReCalcServiceImpl implements WEB3TPTradingPowerReCalcService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerReCalcServiceImpl.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3TPTradingPowerReCalcServiceImpl()
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerReCalcServiceImpl()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (余力再計算)<BR>
     * <BR>
     *  １）余力計算キューParamsを生成し、以下の値を設定する。<BR>
     * <BR>
     * 　@[設定値]　@<BR>
     * 　@　@・口座ＩＤ = 引数.補助口座.getAccountId()<BR>
     * 　@　@・発生区分 = 1:業務アプリ<BR>
     * 　@　@・時価計算フラグ = 0:前日終値評価で再計算<BR>
     * 　@　@・値洗い区分 = 0:通常<BR>
     *　@ 　@・処理区分 = 0:未処理<BR>
     * <BR>
     * 　@２）生成した、余力計算キューParamsを、余力計算キューテーブルにInsertする。<BR>
     * <BR>
     * 　@　@※QueryProcessor.doInsertQuery(Params)をコール<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@Params：生成した余力計算キューParams<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerReCalcServiceImpl.reCalcTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        /*
         * 引数.補助口座がnullの場合
         */
        if (l_subAccount == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * システムタイプスタンプを取得
         */
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
        Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();
        
        /*
         * 余力計算キューParamsを生成する
         */
        TpCalcResultExecNotifyParams l_execNotifyPrams = new TpCalcResultExecNotifyParams();
        //口座ID
        l_execNotifyPrams.setAccountId(l_subAccount.getAccountId());
        //発生区分
        l_execNotifyPrams.setOccurredDiv(WEB3TPOccurredDivDef.WORK_APP);
        //時価計算フラグ
        l_execNotifyPrams.setRealCalcFlag(WEB3TPRealCalcFlagDef.CLOSING_PRICE);
        //値洗い区分
        l_execNotifyPrams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
        //処理区分
        l_execNotifyPrams.setStatus(WEB3TPStatusDef.NOT_DEAL);
        //作成日時
        l_execNotifyPrams.setCreatedTimestamp(l_systemTimeStamp);
        //更新日時
        l_execNotifyPrams.setLastUpdatedTimestamp(l_systemTimeStamp);
        
        /*
         * 生成した、余力計算キューParamsを、余力計算キューテーブルにInsertする。
         */
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_execNotifyPrams);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
