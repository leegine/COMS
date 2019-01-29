head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioForeignCashTransOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金注文更新インタセプタ(WEB3AioForeignCashTransOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨入出金注文更新インタセプタ)<BR>
 * 外貨入出金注文更新インタセプタクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransOrderUpdateInterceptor
    extends WEB3AioCashTransOrderUpdateInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransOrderUpdateInterceptor.class);

    /**
     *通貨コード
     */
    private String currencyCode;

    /**
     * 入出金金額(円換算額)
     */
    private Double convertAmount;

    /**
     * (外貨入出金注文更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。<BR>
     * <BR>
     * @@param l_cashTransOrderSpec  - (入出金注文内容オブジェクト)
     */
    public WEB3AioForeignCashTransOrderUpdateInterceptor(
        WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        super(l_cashTransOrderSpec);
    }

    /**
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「債券出金連携_注文単位テーブル仕様.xls」参照。<BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE <BR>
     * <BR>
     *（OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_process - 処理<BR>
     *（OrderManagerPersistenceContextにて定数定義<BR>
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス<BR>
     * @@return AioOrderUnitParams
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // 【ｘTrade】補足資料.DB更新
            // 「債券出金連携_注文単位テーブル仕様.xls」参照。
            // 8 注文カテゴリ = 9：入出金（OrderCategEnum.CASH_IN_OUT）
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);

            // 15 発注日 = インタセプタ.発注日
            l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));

            // 16 受渡日 = インタセプタ.受渡日
            l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);

            // 18 税区分 = デフォルト：0
            l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

            // 19 ミニ株区分 = デフォルト：0
            l_aioOrderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

            // 20 受注日時 = ThreadLocalSystemAttributesRegistry.getAttribute
            //    (”xblocks.gtl.attributes.systemtimestamp”)の戻り値
            l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

            // 21 扱者コード（SONAR）= 顧客.扱者コード
            long l_lngAccountId = l_aioOrderUnitParams.getAccountId();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            AccountManager l_accountManager = l_finApp.getAccountManager();

            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);

            if (l_mainAccount == null)
            {
                log.debug("該当する顧客オブジェクトがありません");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);

            // 22 識別コード = インタセプタ.識別コード
            l_aioOrderUnitParams.setOrderRequestNumber(this.orderRequestNumber);

            // 23 .comデビット決済取引番号 = null（インタセプタ..comデビット決済取引番号）
            l_aioOrderUnitParams.setComDebitNumber(null);

            // 24 保証金区分 =  null（インタセプタ.保証金区分）
            l_aioOrderUnitParams.setGuaranteeDiv(null);

            // 25 出金申込区分 = null（インタセプタ.出金申込区分）
            l_aioOrderUnitParams.setPaymentApplicationDiv(null);

            // 26 注文取消区分 = 0:初期値
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

            // 27 注文経路区分 = A：管理者
            l_aioOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);

            // 28 注文エラー理由コード = 0000：正常
            l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            // 29 MQステータス  = 0:未送信
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);

            // 36 通貨コード = インタセプタ.通貨コード
            l_aioOrderUnitParams.setCurrencyCode(this.currencyCode);

            // 37 入出金金額(円換算額) = インタセプタ.入出金金額(円換算額)
            l_aioOrderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する顧客オブジェクトがありません", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

      log.exiting(STR_METHOD_NAME);
      return l_aioOrderUnitParams;
    }

    /**
     * (set通貨コード)<BR>
     * 通貨コードをセットする。<BR>
     * <BR>
     * @@param l_strCurrencyCode - 通貨コード
     */
    public void setCurrencyCode(String l_strCurrencyCode)
    {
        this.currencyCode = l_strCurrencyCode;
    }

    /**
     * (set入出金金額(円換算額))<BR>
     * 入出金金額(円換算額)をセットする。<BR>
     * <BR>
     * @@param l_convertAmount - 入出金金額(円換算額)
     */
    public void setConvertAmount(Double l_convertAmount)
    {
        this.convertAmount = l_convertAmount;
    }
}
@
