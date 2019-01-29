head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済更新インタセプタ(WEB3AioSLRepayUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 何文敏 (中訊) 新規作成 仕様変更・モデルNo.759，No.784
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済更新インタセプタ)<BR>
 * 証券担保ローン返済更新インタセプタ<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayUpdateInterceptor.class);

    /**
     * (入出金注文内容)<BR>
     * 入出金注文内容<BR>
     */
    private WEB3AioNewOrderSpec aioOrderSpec;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    private Date orderBizDate;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    private Date deliveryDate;

    /**
     * (証券担保ローン返済更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。<BR>
     * <BR>
     * @@param l_aioOrderSpec - (入出金注文内容)<BR>
     * 入出金注文内容オブジェクト<BR>
     * <BR>
     * @@roseuid 46D7C3A6035E
     */
    public WEB3AioSLRepayUpdateInterceptor(WEB3AioNewOrderSpec l_aioOrderSpec)
    {
        this.aioOrderSpec = l_aioOrderSpec;
    }

    /**
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)を設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値<BR>
     * 　@をセットし、返却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     *  「証券担保ローン返済申込_注文単位テーブル仕様.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * <BR>
     * @@param l_persistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス<BR>
     * <BR>
     * @@return AioOrderUnitParams
     * @@roseuid 46D7C194025C
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_persistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType,"
            + "OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //「証券担保ローン返済申込_注文単位テーブル仕様.xls」参照。
        // 注文カテゴリ
        // 9：入出金
        l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
        // 発注日
        // 証券担保ローン更新インタセプタ.発注日
        l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            this.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
        // 受渡日
        // 証券担保ローン更新インタセプタ.受渡日
        l_aioOrderUnitParams.setDeliveryDate(this.deliveryDate);
        // 税区分
        // デフォルト：0
        l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        // ミニ株区分
        // 0：FALSE（ミニ株でない）
        l_aioOrderUnitParams.setMiniStockDiv(BooleanEnum.FALSE.intValue() + "");
        // 受注日時
        // ThreadLocalSystemAttributesRegistry.getAttributes(”xblocks.gtl.attributes.systemtimestamp”)の戻り値
        l_aioOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        // 扱者コード（SONAR）
        l_aioOrderUnitParams.setSonarTraderCode(null);
        // 識別コード
        l_aioOrderUnitParams.setOrderRequestNumber(null);
        // .comデビット決済取引番号
        l_aioOrderUnitParams.setComDebitNumber(null);
        // 保証金区分
        l_aioOrderUnitParams.setGuaranteeDiv(null);
        // 出金申込区分
        l_aioOrderUnitParams.setPaymentApplicationDiv(null);
        // 注文取消区分
        // 0：初期値
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        // 注文経路区分
        // セッションより取得した同項目の値
        OpLoginSecurityService l_opLoginService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_aioOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        // 注文エラー理由コード
        // 0000：正常
        l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        // MQステータス
        // 0:未送信
        l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        // 通貨コード
        l_aioOrderUnitParams.setCurrencyCode(null);
        // 入出金金額(円換算額)
        l_aioOrderUnitParams.setConvertAmount(null);

        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }

    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@roseuid 46D7C17802D5
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        this.orderBizDate = l_datOrderBizDate;
    }

    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@roseuid 46D7C1800354
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }
}
@
