head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           :OP注文マネージャ(WEB3OptionOrderManagerImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 王暁傑 (Sinocom) 新規作成
Revesion History : 2004/07/31 王暁傑 (Sinocom) 対応名称: WEB3-共通 版本：V1.4
Revesion History : 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】
Revesion History : 2004/08/14 王暁傑 (Sinocom) 対応バグ  BUG140
Revesion History : 2004/08/14 王暁傑 (Sinocom) 対応名称  【株価指数オプション】ソースコードチェック指摘事項(JP)20040802.xls
Revesion History : 2006/07/12 肖志偉 (中訊) 仕様変更 モデル455,460,465,466,469,475,476,479,482,494,497,512,520
                                           トリガー注文管理者のＤＢ更新仕様010(モデル475)
Revesion History : 2006/07/28 肖志偉 (中訊) 仕様変更 モデル530
Revesion History : 2006/08/16 肖志偉 (中訊) 仕様変更 モデル546
Revesion History : 2006/09/22 郭英 (中訊) 仕様変更 モデル548
Revesion History : 2006/09/28 郭英 (中訊) 仕様変更 モデル564
Revesion History : 2006/10/09 肖志偉 (中訊) 仕様変更 モデル555
Revesion History : 2006/10/24 唐性峰 (中訊) トリガー注文ＤＢ更新仕様021,023
Revesion History : 2006/11/28 周捷 (中訊) 仕様変更 モデル574、581
Revesion History : 2006/11/30 徐大方 (中訊) トリガー注文ＤＢ更新仕様034
Revesion History : 2006/12/08 周捷 (中訊) 更新仕様025
Revesion History : 2007/01/25 唐性峰 (中訊) 仕様変更 モデルNo.589,No.593,No.599,No.601,No,604,No.610
Revesion History : 2007/06/08 趙林鵬 (中訊) モデルNo.642，648，650，660,699，695，693，684，677,ＤＢ更新仕様No.168
Revesion History : 2007/06/14 趙林鵬 (中訊) モデルNo.689，728, 733，732
Revesion History : 2007/06/21 趙林鵬 (中訊) モデルNo.738,744,ＤＢ更新仕様No186
Revesion History : 2007/06/28 趙林鵬 (中訊) モデルNo.759
Revesion History : 2007/06/28 孟亜南 (中訊) モデルNo.761
Revesion History : 2007/07/02 趙林鵬 (中訊) モデルNo.757,760
Revesion History : 2007/11/19 孟亞南 (中訊) モデルNo.801,805,816,817,818,819
Revesion History : 2007/11/27 孟亞南 (中訊) Javaソース（基本設計と合っていない実装）No.004,006
Revesion History : 2008/03/13 金傑(中訊) モデル 823,843
Revesion History : 2008/03/19 金傑(中訊) モデル 860
Revesion History : 2008/04/11 張騰宇(中訊) モデル 850
Revesion History : 2008/05/08 張騰宇(中訊) DB更新仕様045
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
Revesion History : 2008/09/03 安陽(中訊) IFO小数点対応
*/
package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradeauditCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.define.WEB3CarryoverProcessTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.rlsgateway.define.WEB3RlsNotifyOrderTypeDef;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (OP注文マネージャ)<BR>
 * オプション注文マネージャクラス<BR>
 * @@author  王暁傑
 * @@version 1.0
 */
public class WEB3OptionOrderManagerImpl extends IfoOrderManagerImpl
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderManagerImpl.class);

    /**
     * (validate新規建注文)<BR>
     * OP新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP注文）validate新規建注文」参照。<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_openContractOrderSpec - (先物OP新規建注文内容)
     * @@param l_ifoOrderUnit - 注文単位
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 404D67DD0090
     */
    public NewOrderValidationResult validateOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec,
        IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_openContractOrderSpec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }
        try
        {
            ProcessingResult l_processingResult = null;

            //新規建発注審査結果オブジェクト
            NewOrderValidationResult l_newOrderValidationResult = null;

            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //注文共通チェックを実施する
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("注文共通をチェックします");

            //市場コードを取得
            String l_strMarketCode = null;
            l_strMarketCode = l_openContractOrderSpec.getMarketCode();
            log.debug("市場コードを取得 = " + l_strMarketCode);

            //証券会社コードを取得
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_openContractOrderSpec.getInstitutionCode();
            log.debug("証券会社コードを取得 = " + l_strInstitutionCode);

            //市場オブジェクトを取得
            Market l_market = null;

            //市場コードのチェックを実施する
            l_market = l_ifoOrderManagerReusableValidations.validateMarket(l_strMarketCode, l_strInstitutionCode);
            log.debug("市場コードのチェックを実施する");
            log.debug("取得した市場の市場ID = " + l_market.getMarketId());

            //銘柄コードを取得する
            String l_strProductCode = null;
            l_strProductCode = l_openContractOrderSpec.getProductCode();
            log.debug("銘柄コードを取得 = " + l_strProductCode);

            //銘柄のチェックを行い、銘柄オブジェクトを返却する
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl =
                    l_ifoOrderManagerReusableValidations.validateProductCode(
                            l_strProductCode,
                            l_strInstitutionCode);
            log.debug("銘柄をチェックします");
            log.debug("取得した銘柄の銘柄ID = " + l_ifoProductImpl.getProductId());

            //買建かを判定する
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_openContractOrderSpec.isBuyToOpenOrder();
            log.debug("is買建 = " + l_blnIsBuyContract);

            //取扱可能な新規建注文かどうかのチェックを行う。
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount,l_blnIsBuyContract);

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl =
                    l_ifoOrderManagerReusableValidations.validateTradedProduct(
                            l_ifoProductImpl,
                            (WEB3GentradeMarket)l_market,
                            l_blnIsBuyContract,
                            true);
            log.debug("取引銘柄をチェックします");
            log.debug("取得した取引銘柄の取引銘柄ID = " + l_ifoTradedProductImpl.getTradedProductId());

            //部店コードを取得
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();
            log.debug("部店コードを取得 = " + l_strBranchCode);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_strBranchCode,
                    l_ifoTradedProductImpl);
            log.debug("入力指数が取扱可能かを判定します");
            
            //成行かどうかを判定する
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_openContractOrderSpec.isMarketOrder();
            log.debug("成行を判定します 結果がTrueの場合");

            //注文失効日を取得する
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_openContractOrderSpec.getOrderExpDate();
            log.debug("注文失効日を取得します = " + l_datOrderExpDate);

            //発注条件を取得する
            String l_strOrderCond = null;
            l_strOrderCond = l_openContractOrderSpec.getOrderCond();
            log.debug("発注条件を取得します = " + l_strOrderCond);

            //執行条件を取得する
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_openContractOrderSpec.getExecutionConditionType();
            log.debug("執行条件を取得します = " + l_executionConditionType);

            //注文期限区分を取得する
            String l_strExpirationDateType = null;
            l_strExpirationDateType = l_openContractOrderSpec.getExpirationDateType();
            log.debug("注文期限区分を取得します = " + l_strExpirationDateType);

            //注文条件のチェックを行う
            Date l_datFirstOrderBizDate = null;
            //注文繰越の場合
            if((l_openContractOrderSpec.getFirstOrderUnitId() != null) &&
                (l_openContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
                IfoOrderUnit l_orderUnit = null;
                try
                {
                    l_orderUnit = (IfoOrderUnit)getOrderUnit(
                    l_openContractOrderSpec.getFirstOrderUnitId().longValue());
                }
                catch (NotFoundException l_nfe)
                {
                    return new NewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));     
                }
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
            }

            //validate注文条件
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                0,
                l_blnIsMatketOrder,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_datFirstOrderBizDate,
                l_datOrderExpDate,
                l_strOrderCond,
                l_executionConditionType,
                l_strExpirationDateType,
                l_openContractOrderSpec.getFirstOrderUnitId());
                
            log.debug("注文条件をチェックします");

            if (l_ifoOrderUnit == null)
            {
                //数量を取得
                double l_dblQuantity = 0D;
                l_dblQuantity = l_openContractOrderSpec.getQuantity();
                log.debug("数量を取得します" + l_dblQuantity);

                //数量のチェックを行う
                l_ifoOrderManagerReusableValidations.validateQuantity(
                        l_subAccount,
                        l_ifoTradedProductImpl,
                        l_dblQuantity,
                        l_blnIsBuyContract,
                        true);
                log.debug("数量をチェックします");
            }

            //指値を取得する
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_openContractOrderSpec.getLimitPrice();
            log.debug("指値をチェックします" + l_dblLimitPrice);

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                    l_dblLimitPrice,
                    l_ifoTradedProductImpl,
                    l_subAccount);
            log.debug("指値をチェックします");

            //逆指値基準値を取得する。
            double l_dblStopOrderPrice = l_openContractOrderSpec.getStopOrderPrice();            
            
            //W指値の訂正指値を取得する
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_openContractOrderSpec.getWLimitPriceChange();
            log.debug("W指値の訂正指値を取得します = " + l_dblWLimitPriceChange);

            //（W指値）執行条件を取得する
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_openContractOrderSpec.getWLimitExecCondType();

            //validateW指値注文()
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract);
            
            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);

            log.exiting(STR_METHOD_NAME);

            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
    }

    /**
     * (validate返済注文)<BR>
     * OP返済注文発注審査を行う。<BR>
     * （validateOptionSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate返済注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@返済注文内容：　@パラメータ.返済注文内容<BR>
     * 　@建玉：　@null<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        return this.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, null);
    }

    /**
     * (validate返済注文)<BR>
     * （validateSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * OP返済注文発注審査<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP注文）validate返済注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_settleContractOrderSpec - (返済注文内容)<BR>
     * 返済注文内容オブジェクト<BR>
     * @@param l_ifoContractImpl - (先物OP建玉)<BR>
     * 先物OP建玉<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 404D8EFC0051
     */
    public NewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3IfoContractImpl l_ifoContractImpl)
    {
        final String STR_METHOD_NAME =
            "validateSettleContractOrder(WEB3GentradeSubAccount, " +
            "WEB3IfoSettleContractOrderSpec, WEB3IfoContractImpl)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_settleContractOrderSpec == null)
        {
            //例外をスローする
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        ProcessingResult l_processingResult = null;

        //新規建発注審査結果オブジェクト
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //先物OP発注審査個別チェックオブジェクトを作成する
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //注文共通チェックを実施する
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("注文共通チェックを実施します");

            // 反対取引以外（パラメータ.建玉 == null）の場合
            if (l_ifoContractImpl == null)
            {
                //返済注文内容に関連する返済建玉エントリの配列を取得する
                SettleContractEntry l_settleContractEntry = null;
                l_settleContractEntry = l_settleContractOrderSpec.getSettleContractEntries()[0];

                //建玉IDを取得
                long l_lngContractID = 0L;
                l_lngContractID = l_settleContractEntry.getContractId();

                //建玉IDに該当する建玉オブジェクトを取得する
                l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);
            }

            //市場IDを取得
            long l_lngMarketId = 0L;
            l_lngMarketId = l_ifoContractImpl.getMarketId();
            log.debug("市場IDを取得 = " + l_lngMarketId);

            //市場オブジェクトを取得
            Market l_market = null;
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);//throw NotFoundException

            //証券会社コードを取得
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_settleContractOrderSpec.getInstitutionCode();
            log.debug("証券会社コードを取得 = " + l_strInstitutionCode);

            //市場コードのチェックを実施する
            l_market =
                    l_ifoOrderManagerReusableValidations.validateMarket(l_market.getMarketCode(), l_strInstitutionCode);
            log.debug("市場コードのチェックを実施します");

            //先物OP銘柄オブジェクトを取得する
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();

            //銘柄コードを取得する
            String l_strProductCode = null;
            l_strProductCode = l_ifoProductImpl.getProductCode();
            log.debug("銘柄コードを取得する" + l_strProductCode);

            //銘柄のチェックを行い、銘柄オブジェクトを返却する
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(
                    l_strProductCode,
                    l_strInstitutionCode);

            //買建かを判定する
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();
            log.debug("買建かを判定する = " + l_blnIsBuyContract);

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl =
                    l_ifoOrderManagerReusableValidations.validateTradedProduct(
                            l_ifoProductImpl,
                            (WEB3GentradeMarket)l_market,
                            l_blnIsBuyContract,
                            false);

            //部店コードを取得
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();
            log.debug("部店コードを取得 = " + l_strBranchCode);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_strBranchCode,
                    l_ifoTradedProductImpl);
            log.debug("入力指数が取扱可能かを判定します");
            
            //成行かどうかを判定する
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_settleContractOrderSpec.isMarketOrder();
            log.debug("成行かどうかを判定する = " + l_blnIsMatketOrder);

            //注文失効日を取得する
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_settleContractOrderSpec.getOrderExpDate();
            log.debug("注文失効日を取得する = " + l_datOrderExpDate);

            //発注条件を取得する
            String l_strOrderCond = null;
            l_strOrderCond = l_settleContractOrderSpec.getOrderCond();
            log.debug("発注条件を取得する = " + l_strOrderCond);

            //執行条件を取得する
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_settleContractOrderSpec.getExecutionConditionType();
            log.debug("執行条件を取得する = " + l_executionConditionType);

            //注文期限区分を取得する
            String l_strExpirationDateType = null;
            l_strExpirationDateType = l_settleContractOrderSpec.getExpirationDateType();
            log.debug("注文期限区分を取得します = " + l_strExpirationDateType);

            Date l_datFirstOrderBizDate = null;
            //注文繰越の場合
            if((l_settleContractOrderSpec.getFirstOrderUnitId() != null) &&
                (l_settleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
                IfoOrderUnit l_orderUnit = null;
                l_orderUnit = (IfoOrderUnit)getOrderUnit(
                    l_settleContractOrderSpec.getFirstOrderUnitId().longValue());
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
            }

            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                    l_subAccount,
                    0,
                    l_blnIsMatketOrder,
                    l_ifoTradedProductImpl,
                    false,
                    l_blnIsBuyContract,
                    l_datFirstOrderBizDate,
                    l_datOrderExpDate,
                    l_strOrderCond,
                    l_executionConditionType,
                    l_strExpirationDateType,
                    l_settleContractOrderSpec.getFirstOrderUnitId());
            log.debug("注文条件のチェックを行う");

            //数量を取得
            double l_dblTotalQuantity = 0D;
            l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
            log.debug("数量を取得 = " + l_dblTotalQuantity);

            //数量のチェックを行う
            l_ifoOrderManagerReusableValidations.validateQuantity(
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblTotalQuantity,
                    l_blnIsBuyContract,
                    false);
            log.debug("数量のチェックを行ます");

            //指値を取得する
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_settleContractOrderSpec.getLimitPrice();
            log.debug("指値を取得する = " + l_dblLimitPrice);

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                   l_dblLimitPrice,
                   l_ifoTradedProductImpl,
                   l_subAccount);
            log.debug("指値のチェックを行う");

            //W指値の訂正指値を取得する
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_settleContractOrderSpec.getWLimitPriceChange();
            log.debug("W指値の訂正指値を取得する = " + l_dblWLimitPriceChange);

            //逆指値基準値を取得する。
            double l_dblStopOrderPrice = l_settleContractOrderSpec.getStopOrderPrice();
            
            String l_strWLimitPriceChange = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            // W指値）執行条件を取得する
			IfoOrderExecutionConditionType l_wLimitExecCondType = 
				l_settleContractOrderSpec.getWLimitExecCondType();

            // validateW指値注文()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
				l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());          

            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
        }
        catch (DataQueryException l_dqex)
        {
             log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
             return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
             log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
             return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);

        return l_newOrderValidationResult;
    }

    /**
     * (calc概算受渡代金)<BR>
     * 概算受渡金額を算出して返却する。<BR>
     * シーケンス図「（OP注文）calc概算受渡代金」参照。<BR>
     * １）　@戻り値クラス生成<BR>
     * 　@概算受渡代金計算結果インスタンスを生成する。<BR>
     * ２） is指値セット<BR>
     * 　@引数.手数料オブジェクトに指値かどうかのフラグをセットする。<BR>
     * 　@[setIs指値()に指定する引数]<BR>
     * 　@is指値：<BR>
     * 　@　@引数.計算単価が0の場合、false。<BR>
     * 　@　@以外、true。<BR>
     * <BR>
     * ３）　@計算単価取得<BR>
     * 　@成行（手数料.is指値() == false）の場合、<BR>
     * 　@発注審査個別チェック.get成行時計算単価()をコールし時価を取得する。<BR>
     * 　@取得した時価を計算単価とする。<BR>
     * <BR>
     * 　@[get成行時計算単価()に指定する引数]<BR>
     * 　@先物OP取引銘柄：　@先物OP取引銘柄<BR>
     * 　@部店：　@（手数料.get部店ID()に該当する部店オブジェクト）<BR>
     * <BR>
     * ３）　@諸費用計算用代金を取得する。<BR>
     * －新規建の場合（is返済注文 == false）<BR>
     * 　@計算サービス.calc拘束売買代金()にて拘束売買代金を計算する。<BR>
     * <BR>
     * 　@[calc拘束売買代金 引数]<BR>
     * 　@数量：　@数量<BR>
     * 　@計算単価：　@計算単価<BR>
     * 　@部店ＩＤ：　@手数料.get部店ID()<BR>
     * 　@手数料商品コード：　@手数料.get手数料商品コード()<BR>
     * 　@is指値：　@（手数料.is指値==trueの場合true、以外false）<BR>
     * 　@先物OP銘柄：　@先物OP取引銘柄<BR>
     * <BR>
     * 　@概算受渡代金計算結果の売買拘束代金に計算結果の拘束売買代金をセットする。<BR>
     * <BR>
     * －返済の場合（is返済注文 == true）<BR>
     * 　@計算サービス.calc売買代金()にて売買代金を計算する。<BR>
     * <BR>
     * 　@[calc売買代金 引数]<BR>
     * 　@数量：　@数量<BR>
     * 　@計算単価：　@計算単価<BR>
     * 　@先物OP銘柄：　@先物OP取引銘柄<BR>
     * <BR>
     * 　@概算受渡代金計算結果の売買拘束代金に0をセットする。<BR>
     * <BR>
     * ４）　@上限金額値チェック<BR>
     * 　@－（isSkip金額チェック == false）の場合のみ。<BR>
     * 　@発注審査個別チェック.validate取引可能上限金額()にて<BR>
     * 諸費用計算用代金の金額チェックを行う。<BR>
     * <BR>
     * 　@[validate取引可能上限金額 引数]<BR>
     * 　@部店：　@補助口座.get取引店()<BR>
     * 　@市場：　@取引銘柄.get市場()<BR>
     * 　@売買代金：　@３）の計算結果（返済：売買代金、新規建：売買拘束代金）<BR>
     * 　@口座タイプ：　@補助口座.get顧客().口座タイプ<BR>
     * <BR>
     * ５）　@手数料を取得する。<BR>
     * <BR>
     * 　@計算サービス.calc委託手数料()にて手数料を算出する。<BR>
     * <BR>
     * 　@[calc手数料 引数]<BR>
     * 　@手数料：　@手数料<BR>
     * 　@補助口座：　@補助口座<BR>
     * <BR>
     * ６）　@手数料にかかる消費税を取得する。<BR>
     * <BR>
     * 　@計算サービス.calc消費税()にて手数料を算出する。<BR>
     * <BR>
     * 　@[calc手数料 引数]<BR>
     * 　@金額：　@手数料金額（calc委託手数料()の戻り値）<BR>
     * 　@基準日：　@手数料.発注日<BR>
     * 　@補助口座：　@補助口座<BR>
     * <BR>
     * ７）　@概算受渡代金を算出する。<BR>
     * <BR>
     * 　@計算サービス.calc受渡代金にて概算受渡代金を算出する。<BR>
     * <BR>
     * 　@[calc受渡代金 引数]<BR>
     * 　@売買：　@売買<BR>
     * 　@諸費用計算用代金：　@３）で取得した諸費用計算用代金<BR>
     * 　@委託手数料：　@５）で取得した手数料<BR>
     * 　@委託手数料消費税：　@６）で取得した消費税<BR>
     * <BR>
     * 　@概算受渡代金計算結果.set概算受渡代金()にて計算結果をセットする。<BR>
     * <BR>
     * 　@概算受渡代金計算結果オブジェクトを返却する。<BR>
     * @@param l_commission - (手数料)
     * @@param l_dblLimitPrice - 指値<BR>
     * <BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     *
     * @@param l_subAccount - (補助口座)
     * @@param l_tradeProduct - 先物OP取引銘柄
     * @@param l_dblQuantity - 数量
     * @@param l_dealing - 売買
     * 　@SideEnum.BUY（買）
     * 　@SideEnum.SELL（売）
     *
     * @@param l_blnIsClosingContractOrder - (is返済注文)<BR>
     * 返済注文の場合はtrue、新規建注文の場合はfalseを指定する。<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）<BR>
     * 場合はtrueを指定する。<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 405939770399
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_tradeProduct,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsClosingContractOrder,
        boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "calcEstimateDeliveryAmount(l_commission,l_dblLimitPrice,l_subAccount,l_tradeProduct," +
            "l_dblQuantity,l_dealing,l_blnIsClosingContractOrder,l_blnIsSkipPriceCheck)";

        log.entering(STR_METHOD_NAME);

        if (l_commission == null || l_subAccount == null || l_tradeProduct ==null || l_dealing == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("l_commission.getCommissionProductCode() = " + l_commission.getCommissionProductCode());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //概算受渡代金計算結果インスタンスを生成する
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();

        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //部店IDを取得する
        long l_lngBranchID = 0L;
        l_lngBranchID = l_commission.getBranchId();
        log.debug("部店IDを取得する = " + l_lngBranchID);

        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_mainAccount.getBranch();
        //成行時計算単価
        double l_dblmakeOrderCalcUnitPrice = 0D;

        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        if (l_dblLimitPrice == 0D)
        {
            //成行時計算単価を取得する
            if (SideEnum.BUY.equals(l_dealing) && !l_blnIsClosingContractOrder)
            {
                l_dblmakeOrderCalcUnitPrice =
                    l_ifoOrderManagerReusableValidations.getMakeOrderCalcUnitPrice(l_tradeProduct, l_branch);
            }
            else if (SideEnum.SELL.equals(l_dealing) || l_blnIsClosingContractOrder)
            {
                l_dblmakeOrderCalcUnitPrice = l_productManager.getCurrentPrice(l_tradeProduct);
            }

            //計算単価が成行時計算単価を設定
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblmakeOrderCalcUnitPrice);
        }
        else
        {

            l_dblmakeOrderCalcUnitPrice = l_dblLimitPrice;
            //計算単価が指値を設定
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }

        //計算サービスオブジェクトを作成する
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider =
                (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

        //拘束売買代金
        double l_dblRestraintTurnOver = 0D;
        //売買代金
        double l_dblTurnOver = 0D;
        //諸経費計算用代金
        double l_dblExpensesCalcAmount = 0D;

        if (l_blnIsClosingContractOrder)
        {
            //返済の場合
            log.debug("返済の場合");
            l_dblTurnOver =
                l_ifoBizLogicProvider.calcTurnOver(l_dblQuantity, l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(), l_tradeProduct);
            log.debug("売買代金を取得します = " + l_dblTurnOver);

            //概算受渡代金計算結果の売買拘束代金が"0"を設定
            log.debug("概算受渡代金計算結果の売買拘束代金が\"0\"を設定");

            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(0);
            l_dblExpensesCalcAmount = l_dblTurnOver;
        }
        else
        {
            //新規建の場合
            log.debug("新規建の場合");
            //拘束売買代金を取得する
            log.debug("l_commission.getCommissionProductCode() = " + l_commission.getCommissionProductCode());
            l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver(
                    l_dblQuantity,
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_lngBranchID,
                    l_commission.getCommissionProductCode(),
                    l_commission.isLimitPrice(),
                    l_tradeProduct);

            log.debug("拘束売買代金を取得する = " + l_dblRestraintTurnOver);

            //概算受渡代金計算結果の売買拘束代金を設定
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);

            l_dblExpensesCalcAmount = l_dblRestraintTurnOver;
        }

        log.debug("手数料オブジェクトに諸経費計算用代金 = " + l_dblExpensesCalcAmount);

        //手数料オブジェクトに諸経費計算用代金をセットする
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);
        log.debug("手数料オブジェクトに諸経費計算用代金をセットする");

        //口座オブジェクト
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        if (l_blnIsSkipPriceCheck == false)
        {
            //isSkip金額チェック == falseの場合
            log.debug("isSkip金額チェック == falseの場合");
            //発注審査個別チェック.validate取引可能上限金額()にて諸費用計算用代金の金額チェックを行う。
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                l_branch,
                l_commission.getExpensesCalcAmount(),
                l_mainAccountRow.getAccountType(),
                WEB3FuturesOptionDivDef.OPTION);
            log.debug("発注審査個別チェック.validate取引可能上限金額()にて諸費用計算用代金の金額チェックを行う。");
        }

        //委託手数料を取得する
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
        double l_dblCommission = 0D;
        l_dblCommission = l_commission.getCommission();

        log.debug("委託手数料を取得する = " + l_dblCommission);

        //手数料にかかる消費税を取得する
        double l_dblSalesTax = 0D;
        l_dblSalesTax =
            l_ifoBizLogicProvider.calcSalesTax(
                l_dblCommission,
                l_commission.getOrderBizDate(),
                l_subAccount);

        log.debug("手数料にかかる消費税を取得する = " + l_dblSalesTax);


        //概算受渡代金を算出する
        double l_dblDeliveryAmount = 0D;
        l_dblDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(
                    l_dealing, l_dblExpensesCalcAmount, l_dblCommission, l_dblSalesTax);

        log.debug("概算受渡代金を算出する = " + l_dblDeliveryAmount);

        //概算受渡代金計算結果.set概算受渡代金()にて計算結果をセットする
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblDeliveryAmount);

        //手数料コースをセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());

        //手数料をセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());

        //手数料消費税をセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        log.exiting(STR_METHOD_NAME);

        //概算受渡代金計算結果オブジェクトを返却する
        return l_ifoEstimateDeliveryAmountCalcResult;
    }

    /**
     * (validate返済訂正注文)<BR>
     *（validateChangeSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate返済訂正注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate返済訂正注文()に指定する引数] <BR>
     * 補助口座：　@パラメータ.補助口座<BR>
     * 返済訂正内容：　@パラメータ.返済訂正内容<BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoChangeSettleContractOrderSpec - (返済訂正内容) <BR>
     * 返済訂正内容オブジェクト<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec)
    {
        final String STR_METHOD_NAME =
            " validateChangeSettleContractOrder(WEB3GentradeSubAccount, WEB3IfoChangeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate返済訂正注文()に処理を委譲（delegate）する。
        //[validate返済訂正注文()に指定する引数]
        //補助口座：　@パラメータ.補助口座
        //返済訂正内容：　@パラメータ.返済訂正内容
        //isSkip遅延状況チェック：　@false（固定）
        OrderValidationResult l_orderValidationResult =
            this.validateChangeSettleContractOrder(
                l_subAccount,
                l_settleContractChangeSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }
    /**
     * (validate返済訂正注文)<BR>
     * （validateChangeSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * -------------<BR>
     * シーケンス図 <BR>
     * 「（OP注文）validate返済訂正注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_settleContractChangeSpec - (返済訂正内容)<BR>
     * 返済訂正内容オブジェクト<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * 遅延状況チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 40679287025B
     */
    public OrderValidationResult validateChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateChangeSettleContractOrder(l_subAccount,l_settleContractChangeSpec,l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_settleContractChangeSpec == null)
        {
            //例外をスローする
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //注文共通チェックを実施する
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //注文IDを取得
            long l_lngOrderID = 0L;
            l_lngOrderID = l_settleContractChangeSpec.getOrderId();

            //注文オブジェクトを取得
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID);//throw NotFoundException

            //原注文のステイタスが訂正可能な状態かを判定する
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //注文単位IDを取得
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_settleContractChangeSpec.getOrderUnitId();

            //注文単位オブジェクトを取得
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);//throw NotFoundException

            //注文単位Rowオブジェクトを取得
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //先物OP銘柄オブジェクト
            WEB3IfoProductImpl l_ifoProductImpl = null;

            //銘柄のチェックを行い、銘柄オブジェクトを返却する
            l_ifoProductImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //市場オブジェクト
            WEB3GentradeMarket l_market = null;
            //市場のチェック
            l_market =
                (WEB3GentradeMarket)l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //返済注文訂正個別エントリに関連する返済建玉エントリの配列を取得する
            SettleContractEntry l_settleContractEntry = null;

            if (l_settleContractChangeSpec.getAfterChangeSettleContractEntries() == null || l_settleContractChangeSpec.getAfterChangeSettleContractEntries().length == 0)
            {
                return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
            }
            l_settleContractEntry = l_settleContractChangeSpec.getAfterChangeSettleContractEntries()[0];

            //建玉IDを取得
            long l_lngContractID = 0L;
            l_lngContractID = l_settleContractEntry.getContractId();

            //建玉IDに該当する建玉オブジェクトを取得する
            IfoContractImpl l_ifoContractImpl = null;
            l_ifoContractImpl = new IfoContractImpl(l_lngContractID);

            //売買建の区分を取得
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(
                l_ifoProductImpl,
                l_market,
                l_blnIsBuyContract,
                false);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_ifoTradedProductImpl);

            //is成行を取得する
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_settleContractChangeSpec.isAfterChangePriceMarket();

            //訂正失効日を取得する
            Date l_datChangeExpirationDate = l_settleContractChangeSpec.getChangeExpirationDate();

            //発注条件を取得する
            String l_strOrderCond = l_settleContractChangeSpec.getOrderCond();

            //訂正執行条件を取得する
            IfoOrderExecutionConditionType l_ChangeExecCondType = l_settleContractChangeSpec.getChangeExecCondType();

            //訂正後注文期限区分を取得する
            String l_strExpirationDateType = l_settleContractChangeSpec.getExpirationDateType();
            
            //OP注文マネージャ.get初回注文の注文単位(注文単位).発注日を取得する
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            
            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_ChangeExecCondType,
                l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //訂正後の数量を取得する
            double l_dblAfterChangeTotalQuantity = l_settleContractChangeSpec.getAfterChangeTotalQuantity();

            //数量が0または、マイナス値でないことのチェックを行う
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeTotalQuantity);

            //訂正後指値を取得する
            double l_dblAfterChangePrice = l_settleContractChangeSpec.getAfterChangePrice();

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblAfterChangePrice,
                l_ifoTradedProductImpl,
                l_subAccount);

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //訂正後の発注条件演算子を取得する
            String l_strOrderOperatorAfterChange =
                l_settleContractChangeSpec.getOrderCondOperator();

            //訂正後の逆指値基準値タイプを取得する
            String l_strStopOrderBasePriceTypeAfterChange =
                l_settleContractChangeSpec.getStopOrderBasePriceType();

            //訂正後の逆指値基準値を取得する
            double l_dblStopOrderBasePriceAfterChange =
                l_settleContractChangeSpec.getStopOrderBasePrice();

            //(W指値)訂正指値を取得する。
            double l_dblWLimitPriceChange = l_settleContractChangeSpec.getWLimitPriceChange();
            
            //訂正後の失効日を取得する
            Date l_datExpirationDateAfterChange = l_settleContractChangeSpec.getChangeExpirationDate();

            //（W指値）執行条件を取得する
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_settleContractChangeSpec.getWLimitExecCondType();
            
            //訂正後の発注期限区分を取得する
            String l_strAfterExpirationDateType = l_settleContractChangeSpec.getExpirationDateType();

            //訂正後の内訳数量を取得する。
            SettleContractEntry[] l_modifiedSettleContractEntries=l_settleContractChangeSpec.getAfterChangeSettleContractEntries();

            String l_strWStopPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
           
            //validateW指値注文
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWStopPrice,
                l_wLimitExecCondType,
                l_settleContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            //訂正入力値が妥当であるかチェックする
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWLimitPriceChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
                l_modifiedSettleContractEntries);

            //validate訂正時注文Rev上限(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //訂正時に注文Revが上限値を超えていないかどうかをチェックする。
            //[validate訂正時注文Rev上限()に指定する引数]
            //注文単位：　@原注文（訂正元）の注文単位オブジェクト
            //訂正数量：　@getAfterChangeTotalQuantity()
            //訂正指値：　@getAfterChangePrice()
            //訂正執行条件：　@get訂正執行条件()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate新規建訂正注文)<BR>
     *（validateChangeOrderのオーバーライド）<BR>
     * <BR>
     * this.validate新規建訂正注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate新規建訂正注文()に指定する引数]<BR>
     * 補助口座：　@パラメータ.補助口座<BR>
     * 新規建訂正内容：　@パラメータ.新規建訂正内容<BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト<BR>
     * @@param l_changeOrderSpec - (新規建訂正内容) <BR>
     * 新規建訂正内容オブジェクト<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        final String STR_METHOD_NAME =
            " validateChangeOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractChangeSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate新規建訂正注文()に処理を委譲（delegate）する。
        OrderValidationResult l_orderValidationResult =
            this.validateChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                false);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }
    /**
     * (validate新規建訂正注文)<BR>
     * （validateChangeOrderのオーバーライド）<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（OP注文）validate新規建訂正注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_openContractChangeSpec - 新規建訂正内容オブジェクト<BR>
     * 新規建訂正内容オブジェクト<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * 遅延状況チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。 <BR>
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406792CB026A
     */
    public OrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(l_subAccount,l_openContractChangeSpec,l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ifoOpenContractChangeSpec == null)
        {
            //例外をスローする
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを生成
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        try
        {
            //注文チェックを行う
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //注文ＩＤを取得
            long l_lngOrderID = l_ifoOpenContractChangeSpec.getOrderId();

            //注文オブジェクトを取得
            Order l_order = this.getOrder(l_lngOrderID);

            //原注文のステイタスが訂正可能な状態かを判定する。
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //注文単位ＩＤを取得
            long l_lngOrderUnitID = l_ifoOpenContractChangeSpec.getOrderUnitId();

            //注文単位オブジェクトを取得
            OrderUnit l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);
            
            //注文単位Rowオブジェクトを取得
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            //先物OP銘柄オブジェクト
            WEB3IfoProductImpl l_ifoProductImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //市場オブジェクト
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            IfoContractOpenOrderUnitImpl l_ifoContractOpenOrderUnitImpl =
                (IfoContractOpenOrderUnitImpl)l_orderUnit;

            //売買区分を取得
            boolean l_blnIsBuyContract = false;
            if (SideEnum.BUY.equals(l_ifoContractOpenOrderUnitImpl.getSide()))
            {
                l_blnIsBuyContract = true;
            }
            else
            {
                l_blnIsBuyContract = false;
            }

            //取引銘柄オブジェクトを取得
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_ifoProductImpl,
                    l_market,
                    l_blnIsBuyContract,
                    true);

            //入力指数が取扱可能かを判定する
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_ifoTradedProductImpl);

            //現在の補助口座で取扱可能な注文可どうかのチェックを行う。
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount,l_blnIsBuyContract);

            //is成行を取得する
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_ifoOpenContractChangeSpec.isAfterChangePriceMarket();

            //訂正失効日を取得する
            Date l_datChangeExpirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //発注条件を取得する
            String l_strOrderCond = l_ifoOpenContractChangeSpec.getOrderCond();

            //訂正執行条件を取得する
            IfoOrderExecutionConditionType l_ChangeExecCondType =
                l_ifoOpenContractChangeSpec.getChangeExecCondType();

            //訂正注文期限区分を取得する
            String l_strExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();
            
            //OP注文マネージャ.get初回注文の注文単位(注文単位).発注日を取得する
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            
            //注文条件のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_ChangeExecCondType,
                l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //訂正後数量を取得する
            double l_dblAfterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            //数量が0または、マイナス値でないことのチェックを行う
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeOriginalQuantity);

            //訂正後指値を取得する
            double l_dblAfterChangePrice = l_ifoOpenContractChangeSpec.getAfterChangePrice();

            //指値のチェックを行う
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblAfterChangePrice,
                l_ifoTradedProductImpl,
                l_subAccount);            

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //訂正後（W指値）訂正指値を取得する
            double l_dblWLimitPriceChange = l_ifoOpenContractChangeSpec.getWLimitPriceChange();

            //訂正後の発注条件演算子を取得する
            String l_strOrderOperatorAfterChange = l_ifoOpenContractChangeSpec.getOrderCondOperator();

            //訂正後の逆指値基準値タイプを取得する
            String l_strStopOrderBasePriceTypeAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePriceType();

            //訂正後の逆指値基準値を取得する
            double l_dblStopOrderBasePriceAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePrice();

            //（W指値）執行条件を取得する
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_ifoOpenContractChangeSpec.getWLimitExecCondType();
            
            //訂正後の失効日を取得する
            Date l_datExpirationDateAfterChange = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //訂正後の注文期限区分を取得する
            String l_strAfterExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();

            //is買建
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }
            
            String l_strWStopPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            //validateW指値注文()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWStopPrice,
                l_wLimitExecCondType,
                l_ifoOpenContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyToOpenOrder);
            
            //訂正入力値が妥当であるかチェックする
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWLimitPriceChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
                null);


            //validate訂正時注文Rev上限(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //訂正時に注文Revが上限値を超えていないかどうかをチェックする。
            //[validate訂正時注文Rev上限()に指定する引数]
            //注文単位：　@原注文（訂正元）の注文単位オブジェクト
            //訂正数量：　@getAfterChangeOriginalQuantity()
            //訂正指値：　@getAfterChangePrice()
            //訂正執行条件：　@get訂正執行条件()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (calc訂正時概算受渡代金)<BR>
     *注文訂正時の概算受渡金額を算出して返却する。<BR>
     *シーケンス図「（OP注文）calc訂正時概算受渡代金」参照。<BR>
     * @@param l_commission - (手数料)
     * @@param l_dblLimitPrice - (指値)<BR>
     * 0が指定された場合は、時価を計算単価として利用する。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄<BR>
     * @@param l_dblQuantity - 訂正注文数量<BR>
     * @@param l_dealing - 売買<BR>
     * 　@SideEnum.BUY（買）<BR>
     * 　@SideEnum.SELL（売）<BR>
     * @@param l_blnIsClosingContractOrder - is返済注文<BR>
     * 返済注文の場合はtrue、<BR>
     * 新規建注文の場合はfalseを指定する。<BR>
     * @@param l_dblExecQuantity - 注文単位.約定数量<BR>
     * @@param l_dblSumTransferredAssetBookValue - 注文単位.合計約定金額<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合<BR>
     * はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 4069228D0203
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsClosingContractOrder,
        double l_dblExecQuantity,
        double l_dblSumTransferredAssetBookValue,
        boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcChangeEstimateDeliveryAmount";

        BigDecimal l_bdSumTransferredAssetBookValue = new BigDecimal(l_dblSumTransferredAssetBookValue + "");

        //calc概算受渡代金をコール
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        //計算サービスオブジェクトを作成する
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //部店ＩＤを取得する。
        long l_lngBranchId = l_commission.getBranchId();

        WEB3GentradeBranch l_GentradeBranch = null;
        try
        {
            l_GentradeBranch = new WEB3GentradeBranch(l_lngBranchId);
        }
        catch (DataFindException l_nfex)
        {
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_nfex)
        {
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_nfex)
        {
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblCalcUnitPrice = 0;
        //（引数.指値() == 0）の場合のみ実施する。
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("引数.売買 = " + l_dealing);
        log.debug("is返済注文 = " + l_blnIsClosingContractOrder);
        if (l_dblLimitPrice == 0)
        {
            //引数.売買 == ”買” and引数.is返済注文 == false の場合、実施。
            if (SideEnum.BUY.equals(l_dealing) && !l_blnIsClosingContractOrder)
            {
                //成行時計算単価を取得する。
                l_dblCalcUnitPrice = l_ifoOrderManagerReusableValidations.getMakeOrderCalcUnitPrice(l_futuresOptionTradedProduct,l_GentradeBranch);
                log.debug("成行時計算単価 = " + l_dblCalcUnitPrice);
            }
            //引数.売買 == ”売” or引数.is返済注文 == true の場合、実施。
            else if (SideEnum.SELL.equals(l_dealing) || l_blnIsClosingContractOrder)
            {
                //計算単価（時価）を取得する。
                l_dblCalcUnitPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
                log.debug("計算単価（時価） = " + l_dblCalcUnitPrice);
            }

            //計算単価をセットする。
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCalcUnitPrice);
        }
        else
        {
            //計算単価をセットする。
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }

        //手数料商品コードを取得する。
        String l_strCommissionProductCode = l_commission.getCommissionProductCode();
        //拘束売買代金
        double l_dblRestraintTurnOver = 0;
        //売買代金
        double l_dblTurnOver = 0;
        //拘束売買代金を計算する。※　@新規建（is返済注文==false）の場合のみ実施。
        if (!l_blnIsClosingContractOrder)
        {
            l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver((l_dblQuantity - l_dblExecQuantity),
                        l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                        l_lngBranchId,
                        l_strCommissionProductCode,
                        l_commission.isLimitPrice(),
                        l_futuresOptionTradedProduct);
            BigDecimal l_bdRestraintTurnOver = new BigDecimal(l_dblRestraintTurnOver + "");

            //戻り値オブジェクトに拘束売買代金をセットする。
            //新規建の場合（is返済注文==false）、拘束売買代金。
            log.debug("拘束売買代金 = " + l_dblRestraintTurnOver);
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);
            //諸経費計算用代金：　@－新規建の場合（is返済注文==false）、拘束売買代金 + 引数.合計約定金額
            log.debug("諸経費計算用代金 －新規建の場合= " +
                l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
            l_commission.setExpensesCalcAmount(
                l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
        }
        else
        {
            //返済の場合（is返済注文==true）、0。
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(0);

            //売買代金を計算する。※　@返済（is返済注文==true）の場合のみ実施。
            l_dblTurnOver = l_ifoBizLogicProvider.calcTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
            BigDecimal l_bdTurnOver = new BigDecimal(l_dblTurnOver + "");

            log.debug("売買代金 = " + l_dblTurnOver);
            //諸経費計算用代金 -返済の場合（is返済注文==true）、売買代金 + 引数.合計約定金額
            log.debug("諸経費計算用代金 －返済の場合= " + l_bdTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
            l_commission.setExpensesCalcAmount(
                l_bdTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
        }

        //発注審査個別チェック.validate取引可能上限金額()にて諸費用計算用代金の金額チェックを行う。※　@（isSkip金額チェック == false）の場合のみ。
        if (!l_blnIsSkipPriceCheck)
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.OPTION);
        }

        //委託手数料を算出する。
        l_ifoBizLogicProvider.calcCommission(l_commission,l_subAccount);
        //委託手数料にかかる消費税を算出する。
        double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
        log.debug("委託手数料にかかる消費税 = " + l_dblSalesTax);
        //概算受渡代金を算出する。
        double l_dblDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(
                l_dealing,
                l_commission.getExpensesCalcAmount(),
                l_commission.getCommission(),
                l_dblSalesTax);
        log.debug("概算受渡代金 = " + l_dblDeliveryAmount);
        //戻り値オブジェクトに概算受渡代金をセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblDeliveryAmount);
        //手数料コースをセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
        //手数料をセットする。
        log.debug("手数料金額 = " + l_commission.getCommission());
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
        //手数料消費税をセットする。
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        //概算受渡代金計算結果オブジェクトを返却する。
        return l_ifoEstimateDeliveryAmountCalcResult;
    }

    /**
     * (validate取消注文)<BR>
     * （validateCancelOrderのオーバーライド）<BR>
     * <BR>
     * 取消発注審査を行う。<BR>
     * <BR>
     * -------------<BR>
     * シーケンス図 <BR>
     * 「（OP注文）validate取消注文」参照。<BR>
     *
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_cancelOrderSpec - 取消注文内容
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406B87F900B4
     */
    public OrderValidationResult validateCancelOrder(
        WEB3GentradeSubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec)
    {
        final String STR_METHOD_NAME = "validateCancelOrder(l_subAccount,l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));

        }
        //注文チェック結果オブジェクト
        OrderValidationResult l_orderValidationResult = null;

        //先物OP発注審査個別チェックオブジェクトを生成
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //注文チェックを行う
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("注文チェックを行う");

            //注文IDを取得
            long l_lngOrderID = 0L;
            l_lngOrderID = l_cancelOrderSpec.getOrderId();
            log.debug("注文IDを取得 = " + l_lngOrderID);

            //注文オブジェクトを取得
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID);

            //注文取消可能状態をチェック
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);
            log.debug("注文取消可能状態をチェック");

            //注文単位オブジェクト
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_order.getOrderUnits()[0];

            //注文単位ROWオブジェクト
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //市場のチェックを行い、市場オブジェクトを返却する。
            l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum,
            //先物／オプション区分 : String, 部店 : 部店, 立会区分 : String, 発注日 : Date)
            //銘柄タイプ：　@注文単位.銘柄タイプ
            //先物／オプション区分：　@注文単位.先物／オプション区分
            //部店：　@引数.補助口座.get取扱店()
            //立会区分：　@注文単位.立会区分
            //発注日：　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            l_orderValidationResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());

        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;

    }

    /**
     * (validate注文)<BR>
     * 注文共通チェックを実施する。<BR>
     * <BR>
     * 以下のチェックを行う。<BR>
     * 　@－システム停止中チェック<BR>
     * 　@－顧客のチェック（Ｙ客、管理ロック等）<BR>
     * 　@－先物OP口座開設チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）validate注文」参照。<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_strFuturesOptionDivision - 先物／オプション区分
     * 　@1：先物 2：オプション
     * @@throws WEB3BaseException
     * @@roseuid 4076560302BE
     */
    public void validateOrder(
        SubAccount l_subAccount,
        String l_strFuturesOptionDivision)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(l_subAccount,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //先物OP発注審査個別チェックオブジェクトを生成
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //受付時間チェック／システム売買停止チェック
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("受付時間チェック／システム売買停止チェック");

            l_ifoOrderManagerReusableValidations.commonFirstValidationsForAllOperations(l_subAccount);
            log.debug("commonFirstValidationsForAllOperations");

            //顧客が先物オプション口座を開設しているかをチェックする
            l_ifoOrderManagerReusableValidations.validateFuturesOptionAccountOpen(l_subAccount, l_strFuturesOptionDivision);
            log.debug("顧客が先物オプション口座を開設しているかをチェックする");
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);

            throw new WEB3BaseException(
                l_ovex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }


        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (create返済建玉エントリ)<BR>
     * 注文数量を優先順位に従って各建玉に配分し、返済建玉エントリの配列を作<BR>成する。
     * <BR>
     * <BR>
     * (*)
     * 画面より「ﾗﾝﾀﾞﾑ」指定された場合（注文数量==0）は
     * オーバーロードメソッドに委譲する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）create返済建玉エントリ」参照。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 対象注文単位ＩＤ。<BR>
     * （訂正注文の場合のみ仕様）<BR>
     *
     * @@param l_dblOrderQuantity - 注文数量
     * @@param l_settleContractEntry - (返済建玉)<BR>
     * 返済建玉オブジェクトの配列<BR>
     * （リクエストデータ）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 407B711D01E3
     */
    public SettleContractEntry[] createSettleContractEntry(
        long l_lngOrderUnitId,
        double l_dblOrderQuantity,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSettleContractEntry(l_lngOrderUnitId,l_dblOrderQuantity," +
            "l_futuresOptionsCloseMarginContractUnits)";
        log.entering(STR_METHOD_NAME);

        if (l_futuresOptionsCloseMarginContractUnits == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        else if (l_futuresOptionsCloseMarginContractUnits.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //先物OPポジションマネージャ
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        //返済建玉エントリの配列
        SettleContractEntry[] l_settleContractOrderEntrys = null;
        //返済建玉エントリの配列リスト
        ArrayList l_lisSettleContractOrderEntrys = new ArrayList();
        //返済建玉エントリオブジェクト
        SettleContractEntry l_settleContractOrderEntry = null;
        //返済建玉ID
        long l_lngContractID = 0L;
        //返済建玉数量
        double l_dblContractQuantity = 0D;
        //先物OP建玉オブジェクト
        WEB3IfoContractImpl l_ifoContractimpl = null;

        //注文数量（残数）：BigDecimalを生成する
        BigDecimal l_bdOrderQuantity = new BigDecimal(l_dblOrderQuantity + "");
        log.debug("注文数量（残数）：BigDecimalを生成する = " + l_bdOrderQuantity);

        //ランダム指定の場合（注文数量==0）、オーバーロードメソッドに委譲
        if (l_dblOrderQuantity == 0)
        {
            l_settleContractOrderEntrys =
                this.createSettleContractEntry(l_lngOrderUnitId, l_futuresOptionsCloseMarginContractUnits);
        }
        else
        {
            int i = 0;
            int l_intLen = 0;
            l_intLen = l_futuresOptionsCloseMarginContractUnits.length;

            try
            {
                //(*)訂正注文（注文単位ID > 0）の場合
                OrderUnit l_orderUnit = null;
                boolean l_isPartiallyExecuted = false;
                BigDecimal l_bdTotalClosingExecuteQuantity = new BigDecimal("0");
                if (l_lngOrderUnitId > 0)
                {
                    l_orderUnit = getOrderUnit(l_lngOrderUnitId);
                    
                    l_isPartiallyExecuted = l_orderUnit.isPartiallyExecuted();
                    
                    if (l_isPartiallyExecuted)
                    {
                        //(*1)合計返済約定数量
                        //　@引数の注文単位IDに該当する建株返済指定情報の一覧の内、
                        //　@処理対象の決済順位（index + 1）～最下位までの建株返済指定情報.返済約定数量のSUM値
                        int l_intIndex = 0;
                        
                        IfoContractSettleOrderUnitImpl l_contractSettleOrderUnit =
                            (IfoContractSettleOrderUnitImpl) l_orderUnit;
                        
                        IfoClosingContractSpec[] l_closingContractSpecs =
                            l_contractSettleOrderUnit.getContractsToClose();
                        
                        if (l_closingContractSpecs != null)
                        {
                            l_intIndex = l_closingContractSpecs.length;
                        }
                        
                        for (int j = 0; j < l_intIndex; j++)
                        {
                            l_bdTotalClosingExecuteQuantity =
                                l_bdTotalClosingExecuteQuantity.add(
                                    new BigDecimal(
                                        String.valueOf(
                                            l_closingContractSpecs[j].getExecutedQuantity())));
                        }
                    }
                }

                for (i = 0; i < l_intLen; i++)
                {
                    //返済建玉IDを取得
                    l_lngContractID =  Long.parseLong(l_futuresOptionsCloseMarginContractUnits[i].id);
                    log.debug("返済建玉IDを取得 = " + l_lngContractID);

                    //先物OP建玉オブジェクトを生成
                    l_ifoContractimpl =
                        (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(l_lngContractID);

                    //返済建玉数量を取得
                    l_dblContractQuantity = l_ifoContractimpl.getQuantity();
                    log.debug("返済建玉数量を取得 = " + l_dblContractQuantity);
                    log.debug("当該注文返済約定済数量 = " + l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId));
                    //返済可能数量：getQuantity() - getLockedQuantity() + getロック中数量(注文単位ＩＤ)　@+　@get返済約定済数量(注文単位ID)
                    BigDecimal l_bdQuantity = new BigDecimal(l_ifoContractimpl.getQuantity() + "");
                    BigDecimal l_bdLockedQuantity = new BigDecimal(l_ifoContractimpl.getLockedQuantity() + "");
                    BigDecimal l_bdLockedQuantityOrderUnitId =
                        new BigDecimal(l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId) + "");
                    BigDecimal l_bdClosingExecuteContract =
                        new BigDecimal(l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId) + "");
                    BigDecimal l_bdClosingContractAvailableQuantity =
                        l_bdQuantity.subtract(l_bdLockedQuantity).add(
                            l_bdLockedQuantityOrderUnitId).add(l_bdClosingExecuteContract);

                    log.debug("返済可能残高数量 = " + l_bdClosingContractAvailableQuantity);
                    log.debug("注文単位ID = " + l_lngOrderUnitId);

                    //新規注文（注文単位ID==0）の場合
                    if (l_lngOrderUnitId == 0)
                    {
                        log.debug("新規注文（注文単位ID==0）の場合");

                        //注文数量0の返済建玉エントリを生成しない
                        if (l_dblContractQuantity == 0)
                        {
                            log.debug("注文数量0の返済建玉エントリを生成しない");
                            continue;
                        }
                        //注文数量が0以外場合
                        else
                        {
                            log.debug("注文数量が0以外場合");
                            log.debug("注文数量（残数） = " + l_bdOrderQuantity);
                            //返済建玉エントリオブジェクトを生成
                            if (l_bdOrderQuantity.compareTo(l_bdClosingContractAvailableQuantity) >= 0)
                            {
                                l_settleContractOrderEntry =
                                    new SettleContractEntry(
                                            l_lngContractID,
                                            l_bdClosingContractAvailableQuantity.doubleValue());
                            }
                            else
                            {
                                l_settleContractOrderEntry =
                                    new SettleContractEntry(
                                            l_lngContractID,
                                            l_bdOrderQuantity.doubleValue());

                            }

                            log.debug("返済建玉エントリオブジェクトをリストに追加");

                            //返済建玉エントリオブジェクトをリストに追加
                            l_lisSettleContractOrderEntrys.add(l_settleContractOrderEntry);

                            l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingContractAvailableQuantity);

                            if (l_bdOrderQuantity.doubleValue() <= 0D)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        log.debug("新規注文（注文単位ID !=0）の場合");
                        log.debug("注文数量（残数） = " + l_bdOrderQuantity);

                        //返済建玉エントリオブジェクトを生成
                        BigDecimal l_bdClosingQuantity = null;
                        if (l_bdOrderQuantity.compareTo(l_bdClosingContractAvailableQuantity) >= 0)
                        {
                            l_bdClosingQuantity = 
                                new BigDecimal(
                                    String.valueOf(
                                        l_bdClosingContractAvailableQuantity.doubleValue()));
                        }
                        else
                        {
                            l_bdClosingQuantity = 
                                new BigDecimal(
                                    String.valueOf(
                                        l_bdOrderQuantity.doubleValue()));
                        }
                        
                        //(*)一部約定の訂正注文（注文単位ID != 0 && isPartiallyExecuted() == true）の場合、
                        //下位返済指定情報の約定数量の考慮を行う
                        if (l_isPartiallyExecuted)
                        {
                            //(*)下位の返済指定情報の約定数量の考慮
                            //　@①@未約定数量（残数）を算出する。
                            //　@　@未約定数量（残数） = 注文株数（残数） - 合計返済約定数量(*1)
                            BigDecimal l_bdUnExecutedQuantity =
                                l_bdOrderQuantity.subtract(l_bdTotalClosingExecuteQuantity);
                            
                            //　@②未約定数量（残数） + get返済約定済数量()の戻り値 < 上記フローにて決定した【返済注文数量】の場合、
                            //　@　@上記フローにて決定した【返済注文数量】 = 未約定数量（残数） + get返済約定済数量()の戻り値　@とする。
                            BigDecimal l_bdClosingExecuteContractCnt =
                                new BigDecimal(l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId) + "");
                            if ((l_bdUnExecutedQuantity.add(l_bdClosingExecuteContractCnt)).compareTo(l_bdClosingQuantity) < 0)
                            {
                                l_bdClosingQuantity =
                                    l_bdUnExecutedQuantity.add(l_bdClosingExecuteContractCnt);
                            }
                            
                            //合計返済約定数量(*1)より、get返済約定数量()の戻り値を減算する。
                            l_bdTotalClosingExecuteQuantity =
                                l_bdTotalClosingExecuteQuantity.subtract(l_bdClosingExecuteContractCnt);
                        }
                        
                        l_settleContractOrderEntry =
                            new SettleContractEntry(
                                    l_lngContractID,
                                    l_bdClosingQuantity.doubleValue());
                        
                        //返済建玉エントリオブジェクトをリストに追加
                        l_lisSettleContractOrderEntrys.add(l_settleContractOrderEntry);
                        log.debug("返済建玉エントリオブジェクトをリストに追加");

                        l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingQuantity);
                        //減算の結果マイナス値になった場合は残数0とする。
                        if (l_bdOrderQuantity.longValue() < 0)
                        {
                            l_bdOrderQuantity = new BigDecimal(0);
                        }

                    }

                }

                //返済可能数量チェック(注文数量（残数）が0以上の場合)
                if (l_bdOrderQuantity.longValue() > 0)
                {
                    log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01055);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01055,
                        getClass().getName() + STR_METHOD_NAME);
                }

                l_settleContractOrderEntrys = (SettleContractEntry[])l_lisSettleContractOrderEntrys.toArray(new SettleContractEntry[l_lisSettleContractOrderEntrys.size()]);

            }
            catch (NotFoundException l_dfex)
            {
                log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 決済順位／返済可能数量をチェックし、返済建玉エントリの配列を作成する。<BR>
     * <BR>
     * (*)<BR>
     * 画面より「ﾗﾝﾀﾞﾑ」指定された場合にオーバーロードメソッドより委譲される。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）create返済建玉エントリ（ﾗﾝﾀﾞﾑ指定）」参照。<BR>
     * @@param l_lngOrderUnitId - 対象注文単位ＩＤ。
     * （訂正注文の場合のみ仕様）
     *
     * @@param l_settleContractEntry - (返済建玉)
     * 返済建玉オブジェクトの配列
     * （リクエストデータ）
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry[]
     * @@roseuid 407B994403E7
     */
    public SettleContractEntry[] createSettleContractEntry(
        long l_lngOrderUnitId,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSettleContractEntry(l_lngOrderUnitId,l_futuresOptionsCloseMarginContractUnits)";
        log.entering(STR_METHOD_NAME);

        if (l_futuresOptionsCloseMarginContractUnits == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        else if (l_futuresOptionsCloseMarginContractUnits.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //先物OPポジションマネージャ
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        //返済建玉エントリのTreeMap
        TreeMap l_tmSettleContractOrderEntry = new TreeMap();
        //返済建玉エントリオブジェクト
        SettleContractEntry[] l_contractOrderEntry = null;
        SettleContractEntry l_settleContractOrderEntry = null;
        //返済建玉ID
        long l_lngContractID = 0L;
        //返済建玉数量
        String l_strContractQuantity = null;
        double l_dblContractQuantity = 0D;
        //決済順位
        String l_strSettlePriority = null;
        Integer l_intSettlePriority =null;
        //返済約定済数量
        double l_dblClosingExecuteContractCnt = 0D;
        //先物OP建玉オブジェクト
        WEB3IfoContractImpl l_ifoContractimpl = null;

        int l_intLen = l_futuresOptionsCloseMarginContractUnits.length;
        try
        {
            //パラメータの返済建玉[]要素毎のLoop処理
            for (int i = 0; i < l_intLen; i++)
            {
                //返済建玉IDを取得
                l_lngContractID = Long.parseLong(l_futuresOptionsCloseMarginContractUnits[i].id);
                //返済建玉数量を取得
                l_strContractQuantity = l_futuresOptionsCloseMarginContractUnits[i].contractOrderQuantity;
                //決済順位を取得
                l_strSettlePriority = l_futuresOptionsCloseMarginContractUnits[i].settlePriority;

                log.debug("返済建玉[" + i + "].ID = " + l_lngContractID);
                log.debug("返済建玉[" + i + "].数量 = " + l_strContractQuantity);
                log.debug("返済建玉[" + i + "].決済順位 = " + l_strSettlePriority);

                //新規注文（注文単位ID==0）の場合
                if ((l_lngOrderUnitId == 0)
                    && ((l_strContractQuantity == null) || l_strContractQuantity.equals("0")))
                {
                    //建玉数量がnullもしくは0の返済建玉エントリは生成しない
                    continue;
                }
                else
                {
                    if (l_strContractQuantity == null)
                    {
                        l_dblContractQuantity = 0D;
                    }
                    else
                    {
                        l_dblContractQuantity = Double.parseDouble(l_strContractQuantity);
                    }
                }

                //先物OP建玉オブジェクトを生成
                l_ifoContractimpl  =
                    (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(l_lngContractID);
                //返済約定済数量を取得
                l_dblClosingExecuteContractCnt = l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId);
                log.debug("返済約定済数量を取得 = " + l_dblClosingExecuteContractCnt);

                //約定数量が注文数量より多い場合
                if (l_dblContractQuantity < l_dblClosingExecuteContractCnt)
                {
                    log.error(this.getClass().getName() + STR_METHOD_NAME,
                            new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                                    getClass().getName() + STR_METHOD_NAME));

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //返済建玉.決済順位がnullもしくは0の場合
                if (l_strSettlePriority == null || l_strSettlePriority.equals("0"))
                {
                    log.debug("返済建玉.決済順位がnullもしくは0");
                    continue;
                }
                l_intSettlePriority = new Integer(l_strSettlePriority);

                //getの戻り値がnullでない場合、決済順位番号が重複していると判定
                if (l_tmSettleContractOrderEntry.get(l_intSettlePriority) != null)
                {
                   log.error(this.getClass().getName() + STR_METHOD_NAME,
                        new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                                getClass().getName() + STR_METHOD_NAME));
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                       getClass().getName() + STR_METHOD_NAME);
                }

                //（返済可能数量チェック）
                //返済可能残高数量：建玉数量－ロック中全数量＋当該注文ロック中数量＋当該注文返済約定数量()
                log.debug("建玉数量 = " + l_ifoContractimpl.getQuantity());
                log.debug("ロック中全数量 = " + l_ifoContractimpl.getLockedQuantity());
                log.debug("当該注文ロック中数量 = " + l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId));
                log.debug("当該注文返済約定済数量 = " + l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId));

                double l_dblClosingContractAvailableQuantity = 0D;
                l_dblClosingContractAvailableQuantity =
                    l_ifoContractimpl.getQuantity() -
                    l_ifoContractimpl.getLockedQuantity() +
                    l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId) +
                    l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId);

                log.debug("返済可能残高数量 = " + l_dblClosingContractAvailableQuantity);
                log.debug("返済建玉数量 = " + l_dblContractQuantity);

                //パラメータ.建玉単位の注文数量が返済可能残高数量を超えている場合
                if (l_dblContractQuantity > l_dblClosingContractAvailableQuantity)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //返済建玉エントリを生成する
                l_settleContractOrderEntry =
                    new SettleContractEntry(l_lngContractID,l_dblContractQuantity);
                l_tmSettleContractOrderEntry.put(l_intSettlePriority, l_settleContractOrderEntry);
            }

            l_contractOrderEntry = new SettleContractEntry[l_tmSettleContractOrderEntry.size()];
            l_tmSettleContractOrderEntry.values().toArray(l_contractOrderEntry);

        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractOrderEntry;
    }

    /**
     * (is取引可能顧客)<BR>
     * 顧客のチェック(Y客、管理ロック等)を行う。<BR>
     * 顧客チェックでエラーとなった場合、falseを返却する。<BR>
     * <BR>
     * (1)注文チェックオブジェクトの取得<BR>
     * getOrderValidatorにより、注文チェックオブジェクトを取得する<BR>
     * <BR>
     * (2)顧客チェック<BR>
     * 注文チェック.validate取引可能顧客(パラメータ.補助口座)をコールする<BR>
     * <BR>
     * validate取引可能顧客で例外がthrowされた場合にはfalseを、<BR>
     * されなかった場合にはtrueを返却する。<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407E534202FD
     */
    public boolean isTradedPossibleCustomer(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradedPossibleCustomer(l_subAccount)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        boolean l_isTradedPossibleCustomer = false;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        log.debug("補助口座ID = " + l_subAccount.getSubAccountId());

        //注文チェックオブジェクトの取得
        CommonOrderValidator l_commonOrderValidator = null;
        l_commonOrderValidator = l_finApp.getCommonOrderValidator();

        log.debug("Ordervalidater = " + this.getOrderValidator().getClass().getName());

        //注文チェック結果オブジェクトを生成
        OrderValidationResult l_orderValidateResult = null;

        log.debug("補助口座Status = " + l_subAccount.getSubAccountStatus());

        l_orderValidateResult = l_commonOrderValidator.validateSubAccountForTrading(l_subAccount);

        log.debug("注文チェック結果 = " + l_orderValidateResult.getProcessingResult().toString());

        if (OrderValidationResult.VALIDATION_OK_RESULT.equals(l_orderValidateResult))
        {
            //チェック結果がＯＫの場合
            l_isTradedPossibleCustomer = true;
        }
        else
        {
            ////チェック結果がＮＧの場合
            l_isTradedPossibleCustomer = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_isTradedPossibleCustomer;

    }

    /**
     * (create建玉明細ByOrder)<BR>
     * 注文に関連した建玉明細（照会用）を配列で取得する。<BR>
     * 指定注文が新規建注文の場合は、nullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）create建玉明細ByOrder」参照。<BR>
     * @@param l_lngOrderId - 注文ＩＤ
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@throws WEB3BaseException
     * @@roseuid 407F87C602DD
     */
    public WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(
        long l_lngOrderId)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnitByOrder(l_lngOrderId)";
        log.entering(STR_METHOD_NAME);

        log.debug("注文ID = " + l_lngOrderId);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //建玉明細の配列
        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits = null;

        //建玉明細のリスト
        ArrayList l_lisFuturesOptionsContractUnits = null;
        l_lisFuturesOptionsContractUnits = new ArrayList();

        //先物OPプロダクトマネージャオブジェクト
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = null;
        l_ifoProductManagerImpl =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).
                    getProductManager();

        //先物OP銘柄オブジェクト
        WEB3IfoProductImpl l_ifoProductImpl = null;

        try
        {

            OrderUnit[] l_orderUnits = null;
            l_orderUnits = this.getOrderUnits(l_lngOrderId);

            log.debug("注文単位配列オブジェクトを作成する");

            if (l_orderUnits == null || l_orderUnits.length == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //注文単位オブジェクトを作成する
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_orderUnits[0];

            //注文単位Rowオブジェクトを作成する
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //注文単位ID
            long l_lngOrderUnitId = l_ifoOrderUnitRow.getOrderUnitId();

            l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_ifoOrderUnitRow.getProductId());
            log.debug("先物OP銘柄オブジェクト = " + l_ifoProductImpl.getProductId());

            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;

            l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoProductManagerImpl.getTradedProduct(l_ifoOrderUnitRow.getProductId(), l_ifoOrderUnitRow.getMarketId());
            log.debug("先物OP取引銘柄オブジェクト = " + l_ifoTradedProductImpl.getTradedProductId());

            //時価情報を取得する
            WEB3IfoProductQuote l_currentInfo = l_ifoTradedProductImpl.getCurrentInfo(null);

            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()) ||
                OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                //新規建注文の場合
                log.debug("新規建注文の場合");
                return null;
            }
            else
            {
                log.debug("新規建注文以外の場合");

                //原資産銘柄コードを取得する
                String l_strUnderlyingProduct = null;
                l_strUnderlyingProduct =
                    l_ifoProductImpl.getUnderlyingProductCode();
                log.debug("原資産銘柄コードを取得する = " + l_strUnderlyingProduct);

                //取引カレンダコンテキスト内の原資産銘柄コードをリセットする
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProduct);

                IfoContractSettleOrderUnitImpl l_ifoContractSettleOrderUnitImpl = (IfoContractSettleOrderUnitImpl)l_orderUnit;

                //返済指定情報の配列を取得する
                IfoClosingContractSpec[] l_ifoClosingContractSpecs = null;
                l_ifoClosingContractSpecs =
                    l_ifoContractSettleOrderUnitImpl.getContractsToClose();

                if (l_ifoClosingContractSpecs == null || l_ifoClosingContractSpecs.length == 0)
                {
                    //例外をスローする
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //返済指定情報オブジェクト
                IfoClosingContractSpec l_ifoClosingContractSpec = null;
                //返済指定情報ROWオブジェクト
                IfoClosingContractSpecRow l_ifoClosingContractSpecRow = null;
                //先物OP建玉
                WEB3IfoContractImpl l_ifoContractImpl = null;
                //建玉ID
                long l_lngContractID = 0L;
                //建玉数量
                double l_dblContractQuantity = 0D;
                //返済注文数量
                double l_dblSettleQuantity = 0D;

                int i = 0;
                int l_intLen = 0;
                l_intLen = l_ifoClosingContractSpecs.length;
                log.debug("返済指定情報配列の長さ = " + l_intLen);

                //建玉明細のオブジェクト
                WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit = null;

                for (i = 0; i < l_intLen; i++)
                {
                    l_futuresOptionsContractUnit = new WEB3FuturesOptionsContractUnit();

                    l_ifoClosingContractSpec = l_ifoClosingContractSpecs[i];
                    l_ifoClosingContractSpecRow =
                        (IfoClosingContractSpecRow)l_ifoClosingContractSpec.getDataSourceObject();

                    //建玉IDを取得
                    l_lngContractID = l_ifoClosingContractSpecRow.getContractId();
                    
                    //返済注文数量を取得
                    l_dblSettleQuantity = l_ifoClosingContractSpecRow.getQuantity();

                    //建玉オブジェクトを取得
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);

                    //建玉数量を取得
                    l_dblContractQuantity = l_ifoContractImpl.getQuantity();

                    //建玉ID
                    l_futuresOptionsContractUnit.id = String.valueOf(l_lngContractID);
                    log.debug("建玉IDを設定 = " + l_lngContractID);

                    //建日
                    l_futuresOptionsContractUnit.openDate = WEB3DateUtility.toDay(l_ifoContractImpl.getOpenDate());
                    log.debug("建日を設定 = " + l_ifoContractImpl.getOpenDate());

                    //建玉数
                    l_futuresOptionsContractUnit.contractQuantity =
                        WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
                    log.debug("建玉数を設定 = " + l_dblContractQuantity);

                    //建単価
                    l_futuresOptionsContractUnit.contractPrice =
                        WEB3StringTypeUtility.formatNumber(l_ifoContractImpl.getContractPrice());
                    log.debug("建単価を設定 = " + l_ifoContractImpl.getContractPrice());
                    
                    //建手数料
                    BigDecimal l_bdContractCommission =
                        new BigDecimal(
                            l_ifoContractImpl.getContractCommission(l_dblSettleQuantity, l_lngOrderUnitId) + "");
                    BigDecimal l_bdContractCommissionConsumptionTax =
                        new BigDecimal(
                            l_ifoContractImpl.getContractCommissionConsumptionTax(l_dblSettleQuantity, l_lngOrderUnitId) + "");
                    double l_dblContractCommission =
                        l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue();
                    l_futuresOptionsContractUnit.contractCommission = 
                        WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                    log.debug("建手数料を設定 = " + l_dblContractCommission);

                    //時価を取得する
                    double l_dblPrice = 0D;
                    if(l_currentInfo != null)
                    {
                        l_dblPrice = l_currentInfo.getCurrentPrice();
                    }

                    //損益
                    double l_dblIncome;
                    l_dblIncome= 
                        l_ifoContractImpl.getEvaluateIncome(
                            l_dblPrice,
                            l_dblSettleQuantity);

                    l_futuresOptionsContractUnit.income =
                            WEB3StringTypeUtility.formatNumber(l_dblIncome);
                    log.debug("損益を設定 = " + l_futuresOptionsContractUnit.income);
                    
                    //損益（諸経費込）
                    BigDecimal l_bdIncome = new BigDecimal("" + l_dblIncome);
                    BigDecimal l_bdContractCommission1 = new BigDecimal("" + l_dblContractCommission);
                    l_futuresOptionsContractUnit.incomeCost =
                        WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdContractCommission1).doubleValue());
                    log.debug("損益（諸経費込）を設定 = " + (l_futuresOptionsContractUnit.incomeCost));
                    
                    //返済数量
                    l_futuresOptionsContractUnit.contractOrderQuantity =
                        WEB3StringTypeUtility.formatNumber(l_dblSettleQuantity);
                    log.debug("返済数量を設定 = " + l_futuresOptionsContractUnit.contractOrderQuantity);

                    //建約定金額
                    l_futuresOptionsContractUnit.contractExecPrice = 
                        WEB3StringTypeUtility.formatNumber(l_ifoContractImpl.getContractExecutedAmount(l_dblSettleQuantity));
                    log.debug("建約定金額を設定 = " + l_futuresOptionsContractUnit.contractExecPrice);

                    //返済約定数量
                    l_futuresOptionsContractUnit.contractExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoClosingContractSpec.getExecutedQuantity());
                    log.debug("返済約定数量を設定 = " + l_futuresOptionsContractUnit.contractExecQuantity);

                    //決済順位
                    if (l_ifoClosingContractSpecRow.getClosingSerialNoIsSet())
                    {
                        l_futuresOptionsContractUnit.settlePriority =
                            String.valueOf(l_ifoClosingContractSpecRow.getClosingSerialNo());
                    }
                    else
                    {
                        l_futuresOptionsContractUnit.settlePriority = "";
                    }
                    log.debug("決済順位を設定 = " + l_futuresOptionsContractUnit.settlePriority);

                    //立会区分
                    IfoContractRow l_ifoContractRow =
                        (IfoContractRow)l_ifoContractImpl.getDataSourceObject();
                    l_futuresOptionsContractUnit.sessionType = l_ifoContractRow.getSessionType();

                    l_lisFuturesOptionsContractUnits.add(l_futuresOptionsContractUnit);
                    log.debug("結果リストを追加");
                }
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        l_futuresOptionsContractUnits =
            (WEB3FuturesOptionsContractUnit[]) l_lisFuturesOptionsContractUnits.toArray(new WEB3FuturesOptionsContractUnit[l_lisFuturesOptionsContractUnits.size()]);
        log.exiting(STR_METHOD_NAME);

        return l_futuresOptionsContractUnits;
    }

    /**
     * (get注文単位)<BR>
     * 指定条件に一致する注文の注文単位オブジェクトを返却する。<BR>
     * （SONARからのリクエストキューに該当する行を取得する場合に利用）<BR>
     * <BR>
     * １）　@証券会社コードより証券会社ＩＤを取得する。<BR>
     * ２）　@部店コードより部店ＩＤを取得する。<BR>
     * ３）　@以下の条件で注文単位テーブルを検索し、<BR>
     * 　@一致した行で注文単位オブジェクトを生成し返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 　@注文単位.証券会社ID == （取得した証券会社ID）<BR>
     * 　@注文単位.部店ID == （取得した部店ID）<BR>
     * 　@注文単位.商品タイプ == パラメータ.商品タイプ<BR>
     * 　@注文単位.識別コード == パラメータ.識別コード<BR>
     * <BR>
     * 該当行が存在しなかった場合、複数行一致した場合は<BR>
     * 例外をスローする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_productType - (商品タイプ)M<BR>
     * （ProductTypeEnumにて定義）<BR>
     * @@param l_strDiscriminationCode - 識別コード
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 40836B1C0386
     */
    public OrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productType,
        String l_strDiscriminationCode)
            throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
                    "getOrderUnit(l_strSecutieCompanyCode,l_strBranchCode," +
                    "l_productType,l_strDiscriminationCode)";

        log.entering(STR_METHOD_NAME);

        log.debug("証券会社コード = " + l_strInstitutionCode);
        log.debug("部店コード = " + l_strBranchCode);
        log.debug("商品タイプ = " + l_productType);
        log.debug("識別 = " + l_strDiscriminationCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //結果の注文単位リスト
        List l_lisSearchResultOrderUnits = null;
        l_lisSearchResultOrderUnits = new ArrayList();

        //注文単位オブジェクト
        OrderUnit l_orderUnit = null;
        //部店オブジェクト
        Branch l_branch = null;
        //証券会社ID
        Long l_lngInstitutionID = null;
        //部店ID
        Long l_lngBranchID = null;

        try
        {
            //部店オブジェクトを取得
            //Throw NotFoundException
            l_branch =
                l_finApp.getAccountManager().getBranch(
                        l_finApp.getAccountManager().getInstitution(l_strInstitutionCode),
                        l_strBranchCode);
            //証券会社IDを取得
            l_lngInstitutionID = new Long(l_finApp.getAccountManager().getInstitution(l_strInstitutionCode).getInstitutionId());
            log.debug("証券会社IDを取得 = " + l_lngInstitutionID);
            //部店IDを取得
            l_lngBranchID= new Long(l_branch.getBranchId());
            log.debug("部店IDを取得 = " + l_lngBranchID);

            //検索条件を追加する。
            //証券会社ID = ?　@and　@部店ID = ? and 商品タイプ = ? and 識別コード = ?
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //部店ID = ?
            l_sbWhere.append(" and product_type = ? "); //商品タイプ = ?
            l_sbWhere.append(" and order_request_number = ? "); //識別コード = ?

            Object[] l_objIfoOrderUnitWhere = {
                    l_lngBranchID,
                    new Integer(l_productType.intValue()),
                    l_strDiscriminationCode};

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objIfoOrderUnitWhere
                );

            log.debug("結果注文単位リスト = " + l_lisSearchResultOrderUnits.getClass().getName());
            log.debug("結果注文単位リスト.長さ = " + l_lisSearchResultOrderUnits.size());

            //結果注文単位リストをチェックする
            if (l_lisSearchResultOrderUnits == null || l_lisSearchResultOrderUnits.size() == 0)
            {
                //例外をスローする
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }
            if (l_lisSearchResultOrderUnits.size() > 1)
            {
                //該当行が存在した場合、複数行一致した場合例外をスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                l_orderUnit = super.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(0));
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME, l_nfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }


        return l_orderUnit;
    }

    /**
     * get当日有効注文単位<BR>
     * （getTodayOpenOrderUnits）<BR>
     * <BR>
     * 指定された範囲の顧客から当日の有効注文を全て取得する。<BR>
     * <BR>
     * １）　@有効注文を取得する。<BR>
     * <BR>
     * 　@　@　@以下の条件とソート順で、注文単位テーブルより該当する注文単位行を取得する。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@ 部店ID in 引数.証券会社.getBranches()の戻り値のいずれか<BR>
     * 　@　@　@かつ　@先物／オプション区分 == 引数.先物／オプション区分<BR>
     * 　@　@　@かつ　@注文有効状態 == ”オープン”<BR>
     * 　@　@　@かつ　@引数.From口座ID <= 口座ID <BR>
     * 　@　@　@かつ　@口座ID <= 引数.To口座ID <BR>
     * 　@　@　@かつ　@発注日 == 業務日付(YYYYMMDD)(*1) <BR>
     * <BR>
     * 　@　@　@[order by]<BR>
     * 　@　@　@受注日時　@昇順<BR>
     * <BR>
     * 　@　@　@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * 　@　@　@　@ 当日の場中に発注した注文のみを取得するための条件<BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２）　@注文単位オブジェクトを取得する。<BR>
     * 　@　@this.toOrderUnit()により<BR>
     * 　@　@取得した注文単位行オブジェクトより、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@取得した注文単位オブジェクトの一覧を返却する。<BR>
     * <BR>
     * 　@　@※該当データなしの場合はnullを返す。 <BR>
     * @@param l_strFuturesOptionDivision - 先物／オプション区分<BR>
     * <BR>
     * 1：　@先物<BR>
     * 2：　@オプション<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_lngRangeFrom - (From口座ID)<BR>
     * From口座ID
     * @@param l_lngRangeTo - (To口座ID)<BR>
     * To口座ID
     * @@return OrderUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 408E0DE30368
     */
    public OrderUnit[] getTodayOpenOrderUnits(
        String l_strFuturesOptionDivision,
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTodayOpenOrderUnits(String, Institution, long, long)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strFuturesOptionDivision == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        Branch[] l_branchs = l_institution.getBranches();
        //戻るの注文単位配列
        OrderUnit[] l_returnOrderUnits = null;
        //戻るの注文単位リスト
        ArrayList l_lisReturnOrderUnits = new ArrayList();
        //検索結果の注文単位リスト
        List l_lisSearchResultOrderUnits = null;

        //１）　@有効注文を取得する。
        //検索条件を追加する。
        StringBuffer l_sbWhere = new StringBuffer();
        //空のArrayListを生成する。
        List l_lisQueryContainer = new ArrayList();

        l_sbWhere.append(" branch_id in (");
        int l_intCount = l_branchs.length;
        for (int i = 0; i < l_intCount; i++)
        {
            if (i == l_intCount - 1)
            {
                l_sbWhere.append(" ? ");
                l_lisQueryContainer.add(Long.toString(l_branchs[i].getBranchId()));
            }
            else
            {
                l_sbWhere.append(" ?, ");
                l_lisQueryContainer.add(Long.toString(l_branchs[i].getBranchId()));
            }
        }

        l_sbWhere.append(")");

        l_sbWhere.append(" and future_option_div = ? "); //先物／オプション区分 = ?
        l_sbWhere.append(" and order_open_status = ? "); //注文有効状態 == ”オープン
        l_sbWhere.append(" and account_id >= ? ");//引数.From口座ID <= 口座ID
        l_sbWhere.append(" and account_id <= ? ");//口座ID <= 引数.To口座ID
        l_sbWhere.append(" and biz_date = ? "); //発注日 == 業務日付(YYYYMMDD)

        //[order by] 受注日時
        String l_strOrderby = "received_date_time asc";

        //発注日 == 業務日付(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        l_lisQueryContainer.add(l_strFuturesOptionDivision);
        l_lisQueryContainer.add(OrderOpenStatusEnum.OPEN);
        l_lisQueryContainer.add(new Long(l_lngRangeFrom));
        l_lisQueryContainer.add(new Long(l_lngRangeTo));
        l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));

        Object[] l_queryContainer = new Object[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_queryContainer);
        try
        {
            //スロー DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            //スロー DataQueryExeption,DataNetworkException,
            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );
        }
        catch (DataNetworkException l_dnex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        if (l_lisReturnOrderUnits != null)
        {
            int l_intOrderUnitLength = l_lisSearchResultOrderUnits.size();
            List l_lstOrderUnit  = new ArrayList();
            for (int i = 0; i < l_intOrderUnitLength; i++)
            {
                OrderUnit l_orderUint = this.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i));
                l_lstOrderUnit.add(l_orderUint);
            }
            l_returnOrderUnits = new OrderUnit[l_lstOrderUnit.size()];
            l_lstOrderUnit.toArray(l_returnOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
        return l_returnOrderUnits;
    }

    /**
     * get当日有効注文顧客一覧<BR>
     * (getTodayOpenOrderMainAcounts)<BR>
     * <BR>
     * 指定証券会社の当日の有効注文を持つ顧客の一覧を取得する。<BR>
     * (引数のFrom口座ID～To口座IDの範囲)<BR>
     * １）　@有効注文を取得する。<BR>
     * 　@　@　@以下の条件とソート順で、注文単位テーブルより該当する注文単位行を取得する。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@ 部店ID in 引数.証券会社.getBranches()の戻り値のいずれか <BR>
     * 　@　@　@かつ　@先物／オプション区分 == 引数.先物／オプション区分<BR>
     * 　@　@　@かつ　@注文有効状態 == "オープン" <BR>
     * 　@　@　@かつ　@引数.From口座ID <= 口座ID <BR>
     * 　@　@　@かつ　@口座ID <= 引数.To口座ID <BR>
     * 　@　@　@かつ　@発注日 == 業務日付(YYYYMMDD) (*1)<BR>
     * 　@　@　@かつ　@立会区分 is null (*2) <BR>
     * <BR>
     * 　@　@　@[order by]<BR>
     *       口座ID 昇順<BR>
     * <BR>
     * 　@　@　@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * 　@　@　@　@ 当日の場中に発注した注文のみを取得するための条件<BR>
     * <BR>
     * 　@　@　@(*2)引数.注文繰越処理区分 == "夕場前注文繰越"の場合のみ、<BR>
     * 　@　@　@　@　@検索条件を追加する。<BR>
     * 　@　@　@　@　@※夕場時間帯に繰越処理が起動した場合に、<BR>
     * 　@　@　@　@　@　@夕場に入力された注文を繰越対象としない考慮。<BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２）　@顧客オブジェクトの一覧(重複なし)を作成する。 <BR>
     * <BR>
     * 　@　@２－１）　@返却用の顧客オブジェクトを格納するリストを作成する。<BR>
     * <BR>
     * 　@　@２－２）　@注文単位行ごとのLoop処理<BR>
     * 　@　@　@　@　@リストに未追加の顧客(注文単位行.口座IDが未追加)の場合のみ、 <BR>
     * 　@　@　@　@　@口座IDに該当する顧客オブジェクトを取得し、返却用のリストに追加する。<BR>
     * <BR>
     * ３）　@作成した顧客オブジェクトの一覧を返却する。 <BR>
     *       リストから顧客オブジェクトの配列を取得し、返却する。<BR>
     * <BR>
     * 　@　@　@※該当データなし時はnullを返却する。 <BR>
     * @@param l_strFuturesOptionDivision -先物／オプション区分<BR>
     * <BR>
     * 1：　@先物<BR>
     * 2：　@オプション<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_lngRangeFrom - From口座ID<BR>
     * @@param l_lngRangeTo - To口座ID<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@return MainAccount[]<BR>
     * @@throws WEB3BaseException
     */
    public MainAccount[] getTodayOpenOrderMainAcounts(
        String l_strFuturesOptionDivision,
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "getTodayOpenOrderMainAcounts(String, Institution, long, long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strFuturesOptionDivision == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //検索結果の注文単位リスト
        List l_lisSearchResultOrderUnits = null;
        //戻り値の顧客オブジェクト
        WEB3GentradeMainAccount[] l_mainAccounts = null;

        //１）有効注文を取得する。
        //検索条件を追加する。
        Branch[] l_branches = l_institution.getBranches();
        StringBuffer l_sbWhere = new StringBuffer();
        if (l_branches !=null)
        {
            if (l_branches.length == 1)
            {
                l_sbWhere.append("branch_id = ?");
            }

            if (l_branches.length > 1)
            {
                l_sbWhere.append("(branch_id = ?");

                for (int i = 1; i < l_branches.length; i++)
                {
                    l_sbWhere.append(" or branch_id = ?");
                }

                l_sbWhere.append(")");
            }

            l_sbWhere.append(" and future_option_div = ? "); //先物／オプション区分 = 引数.先物／オプション区分
            l_sbWhere.append(" and order_open_status = ? "); //注文有効状態 == ”オープン
            l_sbWhere.append(" and account_id >= ? "); //引数.From口座ID <= 口座ID
            l_sbWhere.append(" and account_id <= ? ");//口座ID <= 引数.To口座ID
            l_sbWhere.append(" and biz_date = ? "); //発注日 == 業務日付(YYYYMMDD)

            //引数.注文繰越処理区分 == "夕場前注文繰越"の場合のみ、検索条件を追加する。
            //立会区分 is null
            if (WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER.equals(
                l_strCarryoverProcessType))
            {
                l_sbWhere.append(" and session_type is null ");
            }
        }

        //[order by] 口座ID 昇順
        String l_strOrderby = "account_id asc";

        //発注日 == 業務日付(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //１）空のArrayListを生成する。<BR>
        List l_lstQueryContainer = new ArrayList();
        if (l_branches != null)
        {
            if (l_branches.length > 0)
            {
                for (int i = 0; i < l_branches.length; i++)
                {
                    l_lstQueryContainer.add(Long.toString(l_branches[i].getBranchId()));
                }
            }

            l_lstQueryContainer.add(l_strFuturesOptionDivision);
            l_lstQueryContainer.add(OrderOpenStatusEnum.OPEN);
            l_lstQueryContainer.add(new Long(l_lngRangeFrom));
            l_lstQueryContainer.add(new Long(l_lngRangeTo));
            l_lstQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
        }

        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );

            if (l_lisSearchResultOrderUnits != null && !l_lisSearchResultOrderUnits.isEmpty())
            {
                //２）　@顧客オブジェクトの一覧(重複なし)を作成する。
                long l_accountId = 0L;
                //２－１）　@返却用の顧客オブジェクトを格納するリストを作成する。
                List l_lisAccount = new ArrayList();
                //２－２）　@注文単位行ごとのLoop処理
                int l_intSize = l_lisSearchResultOrderUnits.size();
                for (int i = 0;i < l_intSize;i++)
                {
                    IfoOrderUnitRow l_orderUnitRow =
                        (IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i);
                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();
                        WEB3GentradeMainAccount l_mainAccount =
                        new WEB3GentradeMainAccount(l_accountId);
                        l_lisAccount.add(l_mainAccount);
                    }
                }
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                l_lisAccount.toArray(l_mainAccounts);
            }
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        log.exiting(STR_METHOD_NAME);
        //作成した顧客オブジェクトの一覧を返却する。
        return l_mainAccounts;
    }

    /**
     * get当日有効注文単位<BR>
     * （getTodayOpenOrderUnits）<BR>
     * <BR>
     * 指定顧客の当日の有効注文を全て取得する。<BR>
     * <BR>
     * １）　@有効注文を取得する。<BR>
     * 　@　@　@以下の条件とソート順で、注文単位テーブルより該当する注文単位行を取得する。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@ 口座ID == 引数.顧客.口座ID<BR>
     * 　@　@　@かつ　@先物／オプション区分 == 引数.先物／オプション区分<BR>
     * 　@　@　@かつ　@注文有効状態 == ”オープン”<BR>
     * 　@　@　@かつ　@発注日 == 業務日付(YYYYMMDD)(*1) <BR>
     * 　@　@　@かつ　@立会区分 is null (*2)<BR>
     * <BR>
     * 　@　@　@[order by]<BR>
     * 　@　@　@受注日時　@昇順<BR>
     * 　@　@　@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * 　@　@　@　@ 当日の場中に発注した注文のみを取得するための条件<BR>
     * <BR>
     * 　@　@　@(*2)引数.注文繰越処理区分 == "夕場前注文繰越"の場合のみ、<BR>
     * 　@　@　@　@　@検索条件を追加する。<BR>
     * 　@　@　@　@　@※夕場時間帯に繰越処理が起動した場合に、<BR>
     * 　@　@　@　@　@　@夕場に入力された注文を繰越対象としない考慮。<BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２）　@注文単位オブジェクトを取得する。<BR>
     * 　@　@this.toOrderUnit()により<BR>
     * 　@　@取得した注文単位行オブジェクトより、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@取得した注文単位オブジェクトの一覧を返却する。<BR>
     * <BR>
     * 　@　@※該当データなしの場合はnullを返す。 <BR>
     * @@param l_mainAccount<BR>
     * @@param l_strFuturesOptionDivision<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@return OrderUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public OrderUnit[] getTodayOpenOrderUnits(
        String l_strFuturesOptionDivision,
        MainAccount l_mainAccount,
        String l_strCarryoverProcessType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTodayOpenOrderUnits(String, MainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_strFuturesOptionDivision == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //戻り値の注文単位配列
        OrderUnit[] l_returnOrderUnits = null;
        //戻り値の注文単位リスト
        ArrayList l_lisReturnOrderUnits = new ArrayList();
        //検索結果の注文単位リスト
        List l_lisSearchResultOrderUnits = null;

        //１）　@有効注文を取得する。
        //検索条件を追加する。
        StringBuffer l_sbWhere = new StringBuffer();

        l_sbWhere.append("account_id = ? ");//口座ID == 引数.顧客.口座ID
        l_sbWhere.append(" and future_option_div = ? "); //先物／オプション区分 = 引数.先物／オプション区分
        l_sbWhere.append(" and order_open_status = ? "); //注文有効状態 == ”オープン
        l_sbWhere.append(" and biz_date = ? "); //発注日 == 業務日付(YYYYMMDD)
        //引数.注文繰越処理区分 == "夕場前注文繰越"の場合のみ、検索条件を追加する。
        //立会区分 is null
        if (WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER.equals(
            l_strCarryoverProcessType))
        {
            l_sbWhere.append(" and session_type is null ");
        }

        //[order by] 受注日時
        String l_strOrderby = "received_date_time asc";

        //発注日 == 業務日付(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //１）空のArrayListを生成する。<BR>
        List l_lstQueryContainer = new ArrayList();

        l_lstQueryContainer.add(new Long(l_mainAccount.getAccountId()));
        l_lstQueryContainer.add(l_strFuturesOptionDivision);
        l_lstQueryContainer.add(OrderOpenStatusEnum.OPEN);
        l_lstQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));


        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        if (l_lisReturnOrderUnits != null)
        {
            int l_intOrderUnitLength = l_lisSearchResultOrderUnits.size();
            List l_lstOrderUnit  = new ArrayList();
            for (int i = 0; i < l_intOrderUnitLength; i++)
            {
                OrderUnit l_orderUint = this.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i));
                l_lstOrderUnit.add(l_orderUint);
            }
            l_returnOrderUnits = new OrderUnit[l_lstOrderUnit.size()];
            l_lstOrderUnit.toArray(l_returnOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
        //３）　@取得した注文単位オブジェクトの一覧を返却する。
        return l_returnOrderUnits;
    }

    /**
     * (get注文単位一覧)<BR>
     * （getOrderUnitsのオーバーロード）<BR>
     * 指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。<BR>
     * 該当データが存在しない場合はNULLを返却する。<BR>
     * <BR>
     * (1)戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * (2)検索条件を追加する。<BR>
     * 　@　@(2-1）パラメータ.検索条件文字列に、口座ID、補助口座ID、銘柄タイプを<BR>
     * 追加した検索条件文字列を作成<BR>
     * <BR>
     * 　@　@検索条件文字列 ＝<BR>
     * 　@　@"account_id = ? and sub_account_id = ？ and product_type = ?"<BR>
     *     ＋ パラメータ.検索条件文字列<BR>
     * <BR>
     * <BR>
     * (2-2)文字列配列を生成し、口座ID、補助口座ID、銘柄タイプ、<BR>
     * パラメータ検索条件データコンテナの順にセットする。<BR>
     * <BR>
     * ※口座ID、補助口座IDはパラメータの補助口座オブジェクトより取得、<BR>
     * 銘柄タイプはパラメータ.銘柄タイプより設定する。<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery()により、<BR>
     * 注文単位オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,注文単位Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     *                                      パラメータ.ソート条件,<BR>
     *                                      null,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     *(4)ArrayListを生成する。<BR>
     *(5)取得した注文単位オブジェクトのListの要素数分以下の処理をLoopする。<BR>
     *    (5-1)　@OP注文マネージャ.toOrderUnit((*)注文単位ROW)メソッドをコールする。<BR>
     *     (5-2)　@(5-1)の戻り値をArrayListに追加する。 <BR>
     * (*)注文単位ROW・・・取得した注文単位オブジェクトをIfoOrderUnitRowにキャストする。<BR>
     *(6)ArrayListを返却する。 <BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_productType - 銘柄タイプ（ProductTypeEnumオブジェクト）
     * @@param l_strSearchCondition - 検索条件 文字列
     * @@param l_searchCondContainers - 検索条件
     * @@param l_strSortCondition - ソート条件
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40A09816033B
     */
    public List getOrderUnits(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondition,
        String[] l_searchCondContainers,
        String l_strSortCondition)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderUnits(l_subAccount,l_productType,l_strSearchCondition,l_searchConditions,l_strSortCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_searchCondContainers == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        if (l_searchCondContainers.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        List l_lisRecords = null;

        ArrayList l_lisResult = new ArrayList();

        try
        {
            //口座IDを取得する。
            Long l_lngAccountId = new Long(l_subAccount.getAccountId());
            //補助口座IDを取得する。
            Long l_lngSubAccountId = new Long(l_subAccount.getSubAccountId());

            //２）　@検索条件を追加する。
            //２－１）　@パラメータ.検索条件文字列に、口座ID、補助口座ID、銘柄タイプを追加した検索条件文字列を作成
            //"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?"を付加する。
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? "); //口座ID = ?
            l_sbWhere.append(" and sub_account_id = ? "); //補助口座ID = ?
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ = ?

            //※引数の補助口座オブジェクト、及び引数の銘柄タイプより設定する。
            Object[] l_objOrderUnitWhere = {
                l_lngAccountId, //口座ID
                l_lngSubAccountId, //補助口座ID
                new Integer(l_productType.intValue())  //銘柄タイプ
                };

            //２－２）　@引数.検索条件データコンテナの先頭に、
            //検索条件文字列先頭に付加したパラメータリストを追加する。
            int l_size = l_objOrderUnitWhere.length + l_searchCondContainers.length;
            Object[] l_objWhere = new Object[l_size];
            for (int l_iLoop = 0; l_iLoop < l_objOrderUnitWhere.length; l_iLoop++)
            {
                l_objWhere[l_iLoop] = l_objOrderUnitWhere[l_iLoop];
            }

            for (int l_jLoop = 0; l_jLoop < l_searchCondContainers.length; l_jLoop++)
            {
                l_objWhere[l_objOrderUnitWhere.length + l_jLoop ] = l_searchCondContainers[l_jLoop];
            }

            //Throw DataNetworkException,DatafindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            log.debug("検索条件 = " + l_sbWhere.toString() + l_strSearchCondition);

            for (int l_iLoop = 0; l_iLoop < l_objWhere.length; l_iLoop ++)
            {
                log.debug("l_objWhere" + l_iLoop + " = " + l_objWhere[l_iLoop]);
            }
            log.debug("l_strSortCondition = " + l_strSortCondition);

            //Throw DataQuetyException,DataFoundException
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString() + l_strSearchCondition,
                l_strSortCondition,
                null,
                l_objWhere
                );

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                return null;
            }

            for (int l_iLoop = 0; l_iLoop < l_lisRecords.size(); l_iLoop ++)
            {
                l_lisResult.add(super.toOrderUnit((IfoOrderUnitRow)l_lisRecords.get(l_iLoop)));
            }
        }
        catch (DataNetworkException l_dnex)
        {
            //DBアクセスが失敗の場合
            //例外をスローする
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DBアクセスが失敗の場合
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DBアクセスが失敗の場合
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_lisResult;
    }

    /**
     * (get注文履歴一覧)<BR>
     * 引数で指定された注文単位IDを持つ注文履歴を全て取得し、
     * 注文履歴番号で昇順にソートして返す。<BR>
     * <BR>
     * (1)パラメータ.注文単位.getOrderActions()で、指定の注文単位の<BR>
     * 注文履歴オブジェクトを全て取得する。<BR>
     * <BR>
     * (2)取得した注文履歴オブジェクトの配列を、注文履歴番号で
     * 昇順にソートして返する。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト。
     * @@return IfoOrderAction[]
     * @@throws WEB3BaseException
     * @@roseuid 40A46A3102ED
     */
    public OrderAction[] getOrderActions(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEffectiveOrderUnits(l_strInstitutionCode,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //注文履歴配列オブジェクト
        OrderAction[] l_orderActions = null;
        l_orderActions = l_orderUnit.getOrderActions();

        if (l_orderActions == null || l_orderActions.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("注文履歴配列オブジェクトを取得 = ");
        log.debug("注文履歴配列オブジェクトの長さ = " + l_orderActions.length);

        int i = 0;
        int j = 0;
        int l_intLen = 0;
        l_intLen = l_orderActions.length;
        long l_lngIOrderActionId = 0L;
        long l_lngJOrderActionId = 0L;
        OrderAction l_TmpOrderAction = null;

        //注文履歴番号で昇順にソート
        for (i = 0; i < l_intLen - 1; i++)
        {
            for (j = i + 1; j < l_intLen; j++)
            {
                l_lngIOrderActionId = ((OrderAction)l_orderActions[i]).getOrderActionId();
                l_lngJOrderActionId = ((OrderAction)l_orderActions[j]).getOrderActionId();
                if (l_lngIOrderActionId > l_lngJOrderActionId)
                {
                    l_TmpOrderAction = l_orderActions[i];
                    l_orderActions[i] = l_orderActions[j];
                    l_orderActions[j] = l_TmpOrderAction;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return (OrderAction[])l_orderActions;
    }

    /**
     * (get執行条件一覧)<BR>
     * 執行条件一覧を返却する。<BR>
     * （成行不可会社の場合、”不出来引け成行”は執行条件一覧より削除する）<BR>
     * 
     * １）引数の注文単価区分一覧が複数存在している場合（成行可能会社）<BR>
     * 引数の執行条件一覧をそのまま返却する。<BR>
     * ２）引数の注文単価区分一覧が１つのみ存在している場合（成行不可能会社）<BR>
     *　@２－１）引数の執行条件一覧に不出来引け成行が存在している場合は<BR>
     * 戻り値の執行条件一覧から削除する。<BR>
     *　@２－２）引数の執行条件一覧に不出来引け成行が存在していない場合は<BR>
     *  引数の執行条件一覧をそのまま返却する。<BR>
     * @@param l_strHandlingPossibleOrderPriceDivs - 注文単価区分一覧
     * @@param l_strHandlingPossibleExecConds - 執行条件一覧
     * @@return l_strReHandlingPossibleExecConds - 執行条件一覧
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public String[] getHandlingPossibleExecConds(String[] l_strHandlingPossibleOrderPriceDivs,String[] l_strHandlingPossibleExecConds)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleExecConds(l_strHandlingPossibleOrderPriceDivs,l_strHandlingPossibleExecConds)";
         log.entering(STR_METHOD_NAME);

        ArrayList l_lisHandlingPossibleExecConds = new ArrayList();
        
        if(l_strHandlingPossibleOrderPriceDivs.length > 1)
        {
            log.debug("取得した執行条件一覧を返却");
            return l_strHandlingPossibleExecConds;
        }
        else
        {
            for(int i=0;i<l_strHandlingPossibleExecConds.length;i++)
            {
                if(l_strHandlingPossibleExecConds[i].equals(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED))
                {
                    log.debug("取得した執行条件一覧の不出来引け成行を削除");
                }
                else
                {   
                    l_lisHandlingPossibleExecConds.add(l_strHandlingPossibleExecConds[i]);
                }
                
            }
            String[] l_strReHandlingPossibleExecConds = new String[l_lisHandlingPossibleExecConds.size()];
            l_lisHandlingPossibleExecConds.toArray(l_strReHandlingPossibleExecConds);
            return l_strReHandlingPossibleExecConds;
        }
    }   
    
     /**
     * (validate注文訂正可能状態)<BR>
     * 注文の訂正が可能な注文状態であるかどうかをチェックする。<BR>
     * （* 先物OP発注審査個別チェック.validate注文訂正可能状態()に委譲する。）<BR>
     * @@param l_order - 注文
     * @@throws WEB3BaseException
     * @@roseuid 40A332B2030C
     */
    public void validateOrderChangePossibleStatus(Order l_order)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderChangePossibleStatus(l_order)";

        log.entering(STR_METHOD_NAME);

        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        //先物OP発注審査個別チェック.validate注文訂正可能状態()に委譲する。
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //例外をスローする
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文取消可能状態)<BR>
     * 注文の取消が可能な注文状態であるかどうかをチェックする。<BR>
     * （* 先物OP発注審査個別チェック.validate注文取消可能状態()に委譲する。）<BR>
     * @@param l_order - 注文
     * @@throws WEB3BaseException
     * @@roseuid 40A332B202ED
     */
    public void validateOrderCancelPossibleStatus(Order l_order)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCancelPossibleStatus(l_order)";

        log.entering(STR_METHOD_NAME);

        //先物OP発注審査個別チェックオブジェクトを作成する
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        //先物OP発注審査個別チェック.validate注文取消可能状態()に委譲する
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //例外をスローする
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());

        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is内容通知済注文)<BR>
     * 発注済注文であるかを判定する。<BR>
     * 引数の注文単位が以下の条件に当てはまる場合true、<BR>
     * 以外はfalseを返却す。<BR>
     *  [発注済注文の条件]<BR>
     * 　@注文単位.執行条件 == 注文単位.市場から確認済の執行条件<BR>
     * 　@注文単位.指値 == 注文単位.市場から確認済の指値<BR>
     * 　@注文単位.注文数量 == 注文単位.市場から確認済の数量<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@return boolean
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public boolean isNotifyEndOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //注文単位ROWオブジェクト
        IfoOrderUnitRow l_orderUnitRow = null;
        l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        boolean l_blnIsNotifyEndOrder = false;

        if (l_orderUnitRow.getExecutionConditionType() == null ||
                l_orderUnitRow.getLimitPriceIsNull() == true ||
                l_orderUnitRow.getQuantityIsSet() == false)
        {
            //執行条件、指値、注文数量がNULLの場合
            log.debug("執行条件、指値、注文数量がNULLの場合");
            l_blnIsNotifyEndOrder = false;
        }
        else if (l_orderUnitRow.getExecutionConditionType().equals(l_orderUnitRow.getConfirmedExecConditionType()) &&
                l_orderUnitRow.getLimitPrice() == l_orderUnitRow.getConfirmedPrice() &&
                l_orderUnitRow.getQuantity() == l_orderUnitRow.getConfirmedQuantity())
        {
            //注文単位.執行条件 == 注文単位.市場から確認済の執行条件
            //注文単位.指値 == 注文単位.市場から確認済の指値
            //注文単位.注文数量 == 注文単位.市場から確認済の数量
            //以上の場合
            log.debug("発注済注文の場合");
            l_blnIsNotifyEndOrder = true;
        }
        else
        {
            //他の場合
            log.debug("発注済注文以外の場合");
            l_blnIsNotifyEndOrder = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnIsNotifyEndOrder;
    }

    /**
     * (update概算受渡代金)<BR>
     * オプションの概算受渡代金を更新する。<BR>
     * １）　@注文単位テーブルの下記項目を更新する。<BR>
     *  ・概算受渡代金<BR>
     *  ・注文単価<BR>
     *  ・市場から確認済みの概算受渡代金<BR>
     *  ・市場から確認済みの注文単価<BR>
     * ２）　@注文履歴テーブルの概算受渡代金を更新する。<BR>
     *  シーケンス図<BR>
     *  「（OP注文）update概算受渡代金」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@return void
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public void updateEstimateDeliveryAmount(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEstimateDeliveryAmount(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            // 概算受渡代金
            double l_dblEstimateDeliveryAmount = 0D;
            // 注文単価
            double l_dblPrice = 0D;

            log.debug("注文単位.getOrderOpenStatus() = " + l_orderUnit.getOrderOpenStatus());
            log.debug("注文単位.isFullyExecuted() = " + l_orderUnit.isFullyExecuted());
            log.debug("注文単位.失効区分 = " + l_orderUnit.getExpirationStatus());

            //(*1)全部出来、または、失効済の場合(引数.注文単位.isFullyExecuted() == true || 引数.注文単位.失効区分 == "マーケット拒否")
            if ((l_orderUnit.isFullyExecuted()) || 
                OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
            {
                log.debug("全部出来、または、失効済の場合");
                //1.1.1 get受渡金額合計(注文単位 : IfoOrderUnit)
                // 更新値の設定
                //   概算受渡代金 = get受渡金額合計()
                //   注文単価 = 注文単位.注文単価（既存値のまま）
                l_dblEstimateDeliveryAmount = this.getNetAmount((IfoOrderUnit)l_orderUnit);
                l_dblPrice = l_orderUnitRow.getPrice();

                log.debug("l_dblPrice   = " + l_dblPrice);
                log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);
            }
            //(*2)全部出来、または、失効済以外の場合((*1)以外)
            else
            {
                log.debug("全部出来、または、失効済以外の場合");
                //1.2.1 create手数料(注文単位ID : long)
                // 手数料オブジェクトを生成する。
                // [引数の設定]
                // 注文単位ID：　@引数.注文単位.注文単位ID
                WEB3IfoBizLogicProvider l_bizLogicProvider = (WEB3IfoBizLogicProvider)l_tradingMod.getBizLogicProvider();
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
                WEB3GentradeCommission l_cloneCommission = this.copyCommission(l_commission);
                
                //1.2.2 calc訂正時概算受渡代金を再計算する。
                //[引数]
                //  手数料： create手数料()の戻り値
                //  指値： 注文単位.指値
                //  補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
                //  先物OP取引銘柄： 注文単位.getTradedProduct()の戻り値
                //  数量： 注文単位.数量
                //  売買： 注文単位.getSide()
                //  is返済注文：
                //    (注文単位.注文カテゴリ == "OP新規建注文")の場合、false
                //    (注文単位.注文カテゴリ == "OP返済注文")の場合、true
                //  約定数量： 注文単位.約定数量
                //  合計約定金額： 注文単位.合計約定金額
                //  isSkip金額チェック： true(スキップする)
                boolean l_blnIsClosingContractOrder = false;
                if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    l_blnIsClosingContractOrder = false;
                }
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    l_blnIsClosingContractOrder = true;
                }
                WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());

                WEB3IfoEstimateDeliveryAmountCalcResult l_limitPriceResult = calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_orderUnitRow.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                    l_orderUnitRow.getQuantity(),
                    l_orderUnit.getSide(),
                    l_blnIsClosingContractOrder,
                    l_orderUnitRow.getExecutedQuantity(),
                    l_orderUnitRow.getExecutedAmount(),
                    true);

                //更新値の設定
                //概算受渡代金 = calc訂正時概算受渡代金()の戻り値の概算受渡代金計算結果.概算受渡代金
                //注文単価 = calc訂正時概算受渡代金()の戻り値の概算受渡代金計算結果.計算単価
                l_dblEstimateDeliveryAmount = l_limitPriceResult.getEstimateDeliveryAmount();
                l_dblPrice = l_limitPriceResult.getCalcUnitPrice();
                log.debug("l_dblPrice   = " + l_dblPrice);
                log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);

                //1.2.3  getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
                String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
                
                //1.2.4 (*)買建&&W指値リミット注文有効の場合
                //(注文単位.注文種別 == "OP新規買建注文" && getＷ指値用有効状態区分()の戻り値 == "リミット注文有効")
                //（W指値）訂正指値による訂正時概算受渡代金を取得する
                if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType())
                    && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
                {
                    //1.2.4.1 setIs指値(is指値 : boolean)
                    //  [引数の設定]
                    //  is指値：　@注文単位.(W指値)訂正指値 == 0の場合、false。以外、true。
                    if (l_orderUnitRow.getWLimitPrice() == 0)
                    {
                        l_cloneCommission.setIsLimitPrice(false);
                    }
                    else
                    {
                        l_cloneCommission.setIsLimitPrice(true);
                    }

                    //1.2.4.2 calc訂正時概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄, double, SideEnum, boolean, double, double, boolean)
                    //[引数]
                    //  手数料： W指値用手数料オブジェクト
                    //  指値： 注文単位.(W指値)訂正指値
                    //  補助口座： 注文単位.補助口座IDから取得される補助口座オブジェクト
                    //  先物OP取引銘柄： 注文単位.getTradedProduct()の戻り値
                    //  数量： 注文単位.数量
                    //  売買： 注文単位.getSide()
                    //  is返済注文：
                    //    (注文単位.注文カテゴリ == "OP新規建注文")の場合、false
                    //    (注文単位.注文カテゴリ == "OP返済注文")の場合、true
                    //  約定数量： 注文単位.約定数量
                    //  合計約定金額： 注文単位.合計約定金額
                    //  isSkip金額チェック： true(スキップする)
                    WEB3IfoEstimateDeliveryAmountCalcResult l_wLimitPriceResult = calcChangeEstimateDeliveryAmount(
                        l_cloneCommission,
                        l_orderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_orderUnitRow.getQuantity(),
                        l_orderUnit.getSide(),
                        l_blnIsClosingContractOrder,
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);

                    log.debug("(**)戻り値(*1) = " + l_limitPriceResult.getRestraintTurnover());
                    log.debug("(**)戻り値(*2) = " + l_wLimitPriceResult.getRestraintTurnover());
                    //更新値の設定
                    //概算受渡代金 = calc訂正時概算受渡代金()の戻り値の概算受渡代金計算結果(**).概算受渡代金
                    //注文単価 = calc訂正時概算受渡代金()の戻り値の概算受渡代金計算結果(**).計算単価
                    //(**)戻り値(*1)と戻り値(*2)の拘束売買代金を比較して、
                    //比較結果が高いほうの戻り値の概算受渡代金計算結果オブジェクトを使用する
                    if (l_limitPriceResult.getRestraintTurnover() < l_wLimitPriceResult.getRestraintTurnover())
                    {
                        l_dblEstimateDeliveryAmount = l_wLimitPriceResult.getEstimateDeliveryAmount();
                        l_dblPrice = l_wLimitPriceResult.getCalcUnitPrice();
                    }
                    log.debug("l_dblPrice   = " + l_dblPrice);
                    log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);
                }
            }

            //1.3 (*)注文単位テーブルの更新
            //以下の条件で注文単位テーブルを検索し、取得した行を下記更新内容で更新する。
            //[条件]
            //注文単位テーブル.注文単位ID =　@注文単位.注文単位ID
            //[更新内容]
            //注文単位テーブル.注文単価 = 上記で設定した注文単価
            //注文単位テーブル.市場から確認済みの注文単価 = 上記で設定した注文単価
            //注文単位テーブル.概算受渡代金 = 上記で設定した概算受渡代金
            //注文単位テーブル.市場から確認済みの概算受渡代金 = 上記で設定した概算受渡代金
            OrderUnit l_orderUnitSch = getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRowSch = (IfoOrderUnitRow)l_orderUnitSch.getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParam = new IfoOrderUnitParams(l_orderUnitRowSch);

            l_orderUnitParam.setPrice(l_dblPrice);
            l_orderUnitParam.setConfirmedOrderPrice(l_dblPrice);
            l_orderUnitParam.setEstimatedPrice(l_dblEstimateDeliveryAmount);
            l_orderUnitParam.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParam);

            log.debug("注文単位テーブルの更新" + l_orderUnit.getOrderUnitId());

            //1.4 (*)注文履歴テーブルの更新
            //以下の条件で注文履歴テーブルを検索し、取得した行を下記更新内容で更新する。
            //[条件]
            //注文履歴テーブル.注文単位ID　@    =　@注文単位.注文単位ID and
            //注文履歴テーブル.注文履歴番号　@=　@注文単位.注文履歴最終通番
            //[更新内容]
            //注文履歴テーブル.概算受渡代金　@= 上記で設定した概算受渡代金
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and order_action_serial_no = ? ");

            Object[] l_objWhere = {
                String.valueOf(l_orderUnit.getOrderUnitId()),
                String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhere.toString(),
                null,
                "FOR UPDATE",
                l_objWhere);
            if (l_lisRecords.size() > 0)
            {
                IfoOrderActionRow l_actionRow = (IfoOrderActionRow)l_lisRecords.get(0);
                IfoOrderActionParams l_actionParams = new IfoOrderActionParams(l_actionRow);
                l_actionParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                l_queryProcessor.doUpdateQuery(l_actionParams);
                log.debug("注文履歴テーブルの更新" + l_actionRow.getOrderActionId());
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notifyルールエンジンサーバ)<BR>
     * <BR>
     * （notifyRLS）<BR>
     * 条件付注文の執行、登録、訂正、取消を<BR>
     * ルールエンジンサーバに通知する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）notifyルールエンジンサーバ」参照。<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 注文単位オブジェクト。
     * @@param l_context - 処理<BR>
     * 処理。<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@throws WEB3BaseException
     */
    public void notifyRLS(
        IfoOrderUnit l_orderUnit,
        OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyRLS(IfoOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);

        if (OrderManagerPersistenceContext.FILL_ORDER.equals(l_context))
        {
            // notify親注文全部約定
            this.notifyParentOrderFullyExecuted(l_orderUnit);
        }
        else
        {
            // notify連続注文
            this.notifyToSuccOrder(l_orderUnit);
        }

        //パラメータ.注文単位.getDataSourceObject()が先物OP予約注文単位Rowの場合
        //処理を終了する
        Object l_objDataSourceObject = l_orderUnit.getDataSourceObject();
        if (l_objDataSourceObject instanceof RsvIfoOrderUnitRow)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //get発注条件
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoRow.getOrderConditionType(), 
                l_ifoRow.getOrgOrderConditionType());

        //(*)逆指値注文（get発注条件()の戻り値 == "逆指値"）の場合
        //1.1 notify逆指値注文()
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            this.notifyStopOrder(l_orderUnit);
        }
        //(*)W指値注文（get発注条件()の戻り値 == "W指値"）の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.2 notifyW指値注文()
            this.notifyWLimitOrder(l_orderUnit, l_context);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify逆指値注文)<BR>
     * <BR>
     * （notifyStopOrder）<BR>
     * 逆指値注文の登録、訂正、取消を<BR>
     * ルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@先物OP注文単位かどうかの判定　@<BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。　@<BR>
     * <BR>
     * 　@メソッドの戻り値の型が、<BR>
     * 　@先物OP注文単位Rowでない場合、<BR>
     * 　@処理対象外である為、処理を終了する。（return）<BR>
     * 　@※予約注文単位に対して、逆指値は設定不可。<BR>
     * <BR>
     * ２）　@ルールエンジンサーバへの通知要否チェック<BR>
     * 　@２－１）　@未発注の逆指値注文かどうかの判定　@<BR>
     * 　@１）の戻り値.発注条件 != "逆指値" または<BR>
     * 　@１）の戻り値.リクエストタイプ != "DEFAULT"の場合、<BR>
     * 　@処理対象外である為、処理を終了する。（return）<BR>
     * <BR>
     * 　@２－２）　@未発注の発注遅延注文かどうかの判定 <BR>
     * 　@　@OP注文マネージャ.is未発注遅延注文() == trueの場合、<BR>
     * 　@　@処理対象外である為、処理を終了する。（return）<BR>
     * <BR>
     * 　@　@[is未発注遅延注文()に指定する引数] <BR>
     * 　@　@　@注文単位：　@１）の戻り値 <BR>
     * <BR>
     * ３）　@補助口座を取得する。<BR>
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。<BR>
     * <BR>
     * 　@[getSubAccount()に指定する引数]<BR>
     * 　@　@arg0：　@１）の戻り値.口座ID<BR>
     * 　@　@arg1：　@１）の戻り値.補助口座ID<BR>
     * <BR>
     * ４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）<BR>
     * 　@を取得する。<BR>
     * <BR>
     * ５）　@ルールエンジンサーバに通知を行う。<BR>
     * 　@引数の注文単位.注文状態によって下記処理分岐を行う。<BR>
     * <BR>
     * 　@５－１）　@取消完了（注文有効状態="クローズ"）の場合<BR>
     * 　@　@取得したサービス.sendCancelConOrderMessage()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[sendCancelConOrderMessage()に指定する引数]<BR>
     * 　@　@　@補助口座：　@取得した補助口座<BR>
     * 　@　@　@条件付注文タイプ：　@"逆指値"<BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション"<BR>
     * 　@　@　@注文ID：　@１）の戻り値.注文ID<BR>
     * <BR>
     * 　@５－２）　@訂正完了（注文状態="発注済（変更注文）"）の場合<BR>
     * 　@　@取得したサービス.sendModifyConOrdersMessage()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[sendModifyConOrdersMessage()に指定する引数]<BR>
     * 　@　@　@補助口座：　@取得した補助口座<BR>
     * 　@　@　@条件付注文タイプ：　@"逆指値"<BR>
     * 　@　@　@親注文の銘柄タイプ：　@"先物オプション"<BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID<BR>
     * 　@　@　@子注文の銘柄タイプ一覧：　@null<BR>
     * 　@　@　@子注文の注文ID一覧：　@null<BR>
     * <BR>
     * 　@５－３）　@新規登録（注文状態="受付済（新規注文）"）の場合<BR>
     * 　@　@取得したサービス.sendRegisterConOrdersMessage()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[sendRegisterConOrdersMessage()に指定する引数]<BR>
     * 　@　@　@補助口座：　@取得した補助口座<BR>
     * 　@　@　@条件付注文タイプ：　@"逆指値"<BR>
     * 　@　@　@親注文の銘柄タイプ：　@"先物オプション"<BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID<BR>
     * 　@　@　@子注文の銘柄タイプ一覧：　@null<BR>
     * 　@　@　@子注文の注文ID一覧：　@null<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    private void notifyStopOrder(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        Object l_row = l_orderUnit.getDataSourceObject();
        if (!(l_row instanceof IfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_row;
        try
        {
	        l_orderUnitRow = (IfoOrderUnitRow)getOrderUnit(l_orderUnitRow.getOrderUnitId()).getDataSourceObject();
	    } 
	    catch (NotFoundException e) 
	    {
	        log.error("テーブルに該当するデータがありません。");
	        throw new WEB3SystemLayerException(
	            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
	            this.getClass().getName() + "." + STR_METHOD_NAME,
	            e.getMessage(),
	            e);
	    }
	    
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) ||
            !WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (this.isNotOrderedDelay(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);

        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
        {
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()));
        }
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update注文データ)<BR>
     * <BR>
     * （updateOrderData）<BR>
     * 指定された注文単位オブジェクトを使用し、QueryProcessorにより注文データ類の更新を行う。<BR>
     * －注文（ヘッダ）テーブル.更新日付のupdate<BR>
     * －注文単位テーブルを、引数の注文単位オブジェクトの内容でupdate<BR>
     * 　@※履歴を作成する場合は、注文単位.注文履歴最終通番を呼び出し側で<BR>
     * 　@　@　@カウントアップすること。<BR>
     * －注文履歴テーブルにレコードをinsert（引数.is履歴作成==trueの場合のみ）<BR>
     * <BR>
     * １）　@注文（ヘッダ）テーブルをupdateする。<BR>
     * <BR>
     * 　@　@注文ID==引数の注文単位.注文ID に該当する注文（ヘッダ）レコードをupdateする。<BR>
     * <BR>
     * 　@　@更新日付に、引数の注文単位オブジェクトの同項目の内容をセットしてupdateする。<BR>
     * <BR>
     * ２）　@注文単位テーブルをupdateする。<BR>
     * <BR>
     * 　@　@引数の注文単位オブジェクトの内容でupdateする。<BR>
     * <BR>
     * ３）　@引数のis履歴作成==trueの場合のみ、引数の注文単位オブジェクトを使用し<BR>
     * 　@　@注文履歴テーブルへ１レコードinsertする。<BR>
     * <BR>
     * 　@　@３－１）　@発注OK<BR>
     * 　@　@[引数の注文単位.注文状態 == "発注中（新規注文）" かつ<BR>
     * 　@　@　@引数の注文単位.リクエストタイプ == "時価サーバ"の場合]<BR>
     * <BR>
     * 　@　@　@DB更新仕様<BR>
     * 　@　@　@「逆指値注文発注(OK)_先物OP注文履歴テーブル.xls」参照。<BR>
     * <BR>
     * 　@　@３－２）　@管理者手動失効 <BR>
     * 　@　@　@[引数の注文単位.リクエストタイプ == "失効" かつ<BR>
     * 　@　@　@　@引数の注文単位.注文エラー理由コード ==<BR>
     * 　@　@　@　@　@"トリガー注文管理者手動失効済"の場合] <BR>
     * <BR>
     * 　@　@　@DB更新仕様 <BR>
     * 　@　@　@「手動失効_先物OP注文履歴テーブル.xls」参照。 <BR>
     * <BR>
     * 　@　@３－３）　@切替NG <BR>
     * 　@　@　@[引数の注文単位.注文状態 == "発注失敗（変更注文）" かつ <BR>
     * 　@　@　@　@引数の注文単位.リクエストタイプ == "失効"かつ<BR>
     * 　@　@　@　@引数の注文単位.注文エラー理由コード !=<BR>
     * 　@　@　@　@　@"トリガー注文管理者手動失効済"の場合] <BR>
     * 　@　@　@ <BR>
     * 　@　@　@DB更新仕様 <BR>
     * 　@　@　@「W指値注文切替（NG）_注文履歴テーブル仕様.xls」参照。<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 注文単位オブジェクト。
     * @@param l_blnIsCreateOrderAction - is履歴作成<BR>
     * 注文履歴テーブルにデータを登録するかどうかのフラグ。<BR>
     * （true：登録する、false：登録しない）
     * @@throws WEB3BaseException
     */
    public void updateOrderData(
        IfoOrderUnit l_orderUnit,
        boolean l_blnIsCreateOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(IfoOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "order_id=?";
            Object[] l_objWhere =
            {
                new Long(l_orderUnitRow.getOrderId())
            };
            HashMap l_map = new HashMap();
            l_map.put("last_updated_timestamp", l_orderUnitRow.getLastUpdatedTimestamp());
            l_processor.doUpdateAllQuery(
                IfoOrderRow.TYPE,
                l_strWhere,
                l_objWhere,
                l_map);

            l_processor.doUpdateQuery(l_orderUnitRow);

            if (l_blnIsCreateOrderAction)
            {
                //３－１）　@発注OK
                boolean l_blnOrderOK =
                    OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus()) &&
                    WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType());

                //３－２）　@管理者手動失効
                boolean l_blnInvalidate =
                    WEB3RequestTypeDef.INVALIDATE.equals(l_orderUnitRow.getRequestType())
                        && WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(
                            l_orderUnitRow.getErrorReasonCode());

                //３－３）　@切替NG
                boolean l_blnTransferNG =
                    OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                        && !WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(
                            l_orderUnitRow.getErrorReasonCode())
                        && WEB3RequestTypeDef.INVALIDATE.equals(l_orderUnitRow.getRequestType());

                if (l_blnOrderOK || l_blnInvalidate || l_blnTransferNG)
                {
                    IfoOrderActionParams l_orderActionParams =
                        new IfoOrderActionParams();
                    long l_lngOrderActionId = IfoOrderActionDao.newPkValue();
                    Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
                    l_orderActionParams.setOrderActionId(l_lngOrderActionId);
                    l_orderActionParams.setAccountId(l_orderUnitRow.getAccountId());
                    l_orderActionParams.setSubAccountId(l_orderUnitRow.getSubAccountId());
                    l_orderActionParams.setOrderId(l_orderUnitRow.getOrderId());
                    l_orderActionParams.setOrderUnitId(l_orderUnitRow.getOrderUnitId());
                    if (!l_orderUnitRow.getMarketIdIsNull())
                    {
                        l_orderActionParams.setMarketId(l_orderUnitRow.getMarketId());
                    }
                    l_orderActionParams.setOrderType(l_orderUnitRow.getOrderType());
                    if (l_blnOrderOK)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.SEND_TO_MKT);
                    }
                    else if (l_blnInvalidate)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
                    }
                    else if (l_blnTransferNG)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.MARKER_REFUSAL);
                    }
                    if (!l_orderUnitRow.getLimitPriceIsNull())
                    {
                        l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
                    }
                    l_orderActionParams.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType());
                    l_orderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
                    l_orderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
                    l_orderActionParams.setStopPriceType(l_orderUnitRow.getStopPriceType());
                    if (!l_orderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_orderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
                    }
                    if (!l_orderUnitRow.getWLimitPriceIsNull())
                    {
                        l_orderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
                    }
                    l_orderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
                    l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
                    if (!l_orderUnitRow.getConfirmedPriceIsNull())
                    {
                        l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
                    }
                    if (!l_orderUnitRow.getConfirmedQuantityIsNull())
                    {
                        l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
                    }
                    l_orderActionParams.setExecutedQuantity(null);
                    l_orderActionParams.setOrderStatus(l_orderUnitRow.getOrderStatus());
                    l_orderActionParams.setExpirationStatus(l_orderUnitRow.getExpirationStatus());
                    l_orderActionParams.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
                    l_orderActionParams.setExecutedPrice(null);
                    l_orderActionParams.setProductType(l_orderUnitRow.getProductType());
                    l_orderActionParams.setProductId(l_orderUnitRow.getProductId());
                    if (!l_orderUnitRow.getEstimatedPriceIsNull())
                    {
                        l_orderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
                    }
                    l_orderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
                    l_orderActionParams.setClosingOrder(l_orderUnitRow.getClosingOrder());
                    if (l_blnOrderOK || l_blnInvalidate)
                    {
                        l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                    }
                    else if (l_blnTransferNG)
                    {
                        l_orderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());
                    }
                    l_orderActionParams.setRequestType(l_orderUnitRow.getRequestType());
                    if (!l_orderUnitRow.getTraderIdIsNull())
                    {
                        l_orderActionParams.setTraderId(l_orderUnitRow.getTraderId());
                    }
                    l_orderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
                    l_orderActionParams.setCreatedTimestamp(l_tsSysTime);
                    l_orderActionParams.setLastUpdatedTimestamp(l_tsSysTime);

                    if (l_blnInvalidate || l_blnTransferNG)
                    {
                        l_orderActionParams.setOrgOrderConditionType(
                            l_orderUnitRow.getOrgOrderConditionType());
                        l_orderActionParams.setOrgOrderCondOperator(
                            l_orderUnitRow.getOrgOrderCondOperator());
                        l_orderActionParams.setOrgStopPriceType(
                            l_orderUnitRow.getOrgStopPriceType());
                        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
                        {
                            l_orderActionParams.setOrgStopOrderPrice(
                                l_orderUnitRow.getOrgStopOrderPrice());
                        }
                        if (!l_orderUnitRow.getOrgWLimitPriceIsNull())
                        {
                            l_orderActionParams.setOrgWLimitPrice(
                                l_orderUnitRow.getOrgWLimitPrice());
                        }
                        l_orderActionParams.setOrgWLimitExecCondType(
                            l_orderUnitRow.getOrgWLimitExecCondType());
                        l_orderActionParams.setWLimitExecCondType(
                            l_orderUnitRow.getWLimitExecCondType());
                        if (!l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
                        {
                            l_orderActionParams.setWLimitBeforeLimitPrice(
                                l_orderUnitRow.getWLimitBeforeLimitPrice());
                        }
                        l_orderActionParams.setWLimitBeforeExecCondType(
                            l_orderUnitRow.getWLimitBeforeExecCondType());
                    }

                    l_orderActionParams.setConfirmedExecConditionType(
                        l_orderUnitRow.getConfirmedExecConditionType());

                    l_orderActionParams.setExpirationDateType(
                        l_orderUnitRow.getExpirationDateType());

                    l_processor.doInsertQuery(l_orderActionParams);
                }
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert新規建注文キュー)<BR>
     * <BR>
     * （insertOpenContractHostOrder）<BR>
     * 先物OP注文取引キューテーブルに新規建注文のデータを登録する。<BR>
     * <BR>
     * １）　@this.getOrderUnits(注文ID)にて注文単位を取得する。<BR>
     * 　@※戻り値の0番目の要素を注文単位として使用する。<BR>
     * <BR>
     * ２）　@先物OP注文取引キューテーブルにデータを登録する。<BR>
     * <BR>
     * 　@２－１）　@オプションの場合（注文単位.先物／オプション区分="オプション"）<BR>
     * 　@　@更新する行の内容は、DB更新仕様<BR>
     * 　@　@「OP新規建_先物OP注文取引キューテーブル.xls」参照<BR>
     * <BR>
     * 　@２－２）　@先物の場合（注文単位.先物／オプション区分="先物"）<BR>
     * 　@　@更新する行の内容は、DB更新仕様<BR>
     * 　@　@「先物新規建_先物OP注文取引キューテーブル.xls」 参照。<BR>
     * @@param l_lngOrderId - 注文ID<BR>
     * 注文ID。
     * @@throws WEB3BaseException
     */
    public void insertOpenContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertOpenContractHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.OPTION_ORDER;// リクエストデータコード 
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//処理区分 "0：未処理"
            String l_strInstitutionCode = null;//証券会社コード
            String l_strBranchCode = null;//部店コード
            String l_strAccountCode = null;//顧客コード
            String l_strTraderCode = null;//扱者コード
            String l_strReceivedDateTimeDiv = null;//受注日区分
            String l_strOrderRequestNumber = null;//識別コード
            String l_strMarketCode = null;//市場
            String l_strProductCode = null;//銘柄コード
            String l_strTargetProductCode = null;//原資産銘柄コード
            String l_strDeliveryMonthYYYY = null;//限月（年）
            String l_strDeliveryMonthMM = null;//限月（月）
            IfoDerivativeTypeEnum l_futureOptionProductType = null;//先物オプション商品
            double l_dblStrikePrice = 0;//行使価格
            String l_strSplitType = null;//分割
            double l_dblSellOrderQuantity = 0;//売付数量
            double l_dblBuyOrderQuantity = 0;//買付数量
            double l_dblLimitPrice = 0;//指値
            String l_strExecutionCondition = null;//執行条件
            String l_strTransactionType = null;//取引区分
            String l_strTicketNumber = null;//伝票№
            String l_strContractCheck = null;//建玉チェック
            Timestamp l_tsReceivedDateTime = null;//受注日時
            String l_strOrderChannel = null;//注文チャネル
            String l_strCommisionNumber = null;//手数料№
            String l_strCommisionBranchNumber = null;//手数料№枝番
            String l_strCommisionProductCode = null;//手数料商品コード
            String l_strFuturesOptionDiv = null;
            int l_intOrder_action_serial_no = 0;//注文履歴番号
            
            IfoOrderImpl l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //先物オプション区分を取得
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());

            //対象データを取得する----------------------------[START]
            //throw NotFoundException
            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager.getMainAccount(
            l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //扱者コードを取得する
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();

            //受注日時を取得する
            l_tsReceivedDateTime = l_ifoOrderUnitRow.getReceivedDateTime();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsReceivedDateTime);

            //受注日区分を取得する
            //注文単位.発注日==受注日（受注日時の日付部分）の場合は　@0：当日。以外、1：前日。
            l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.YESTERDAY;

            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.TODAY;
            }

            //識別コードを取得する
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            //市場コードを取得する
            l_strMarketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //銘柄コードを取得する
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //原資産銘柄コードを取得する
            l_strTargetProductCode =  l_ifoProductImpl.getUnderlyingProductCode();

            //限月(年)を取得する
            l_strDeliveryMonthYYYY = l_ifoProductImpl.getMonthOfDelivery().substring(0,4);

            //限月(月)を取得する
            l_strDeliveryMonthMM =  l_ifoProductImpl.getMonthOfDelivery().substring(4);

            //先物オプション商品を取得する
            l_futureOptionProductType = l_ifoProductImpl.getDerivativeType();

            //行使価格を取得する
            l_dblStrikePrice = l_ifoProductImpl.getStrikePrice();

            //分割を取得する
            l_strSplitType = ((IfoProductRow) l_ifoProductImpl.getDataSourceObject()).getSplitType();

            //買付/売付数量を取得する
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
                //605：OP新規買建注文/601：先物新規買建注文
            {   //買付の場合
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
                //606：OP新規売建注文/602：先物新規売建注文
            {
                //売付の場合
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }

            //指値を取得する
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();

            //執行条件を取得する
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //寄付
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //引け
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //取引区分を取得する
            l_strTransactionType = l_ifoOrderUnitRow.getSonarTradedCode();

            //伝票№を取得する
            l_strTicketNumber = l_ifoOrderUnitRow.getVoucherNo();

            //注文チャネルを取得する
            l_strOrderChannel = l_ifoOrderUnitRow.getOrderChanel();

            //手数料№を取得する
            l_strCommisionNumber = l_ifoOrderUnitRow.getCommTblNo();

            //手数料№枝番を取得する
            l_strCommisionBranchNumber = l_ifoOrderUnitRow.getCommTblSubNo();

            //手数料商品コードを取得する
            l_strCommisionProductCode =
                    l_ifoOrderUnitRow.getCommProductCode();

            //注文履歴最終通番を取得する
            l_intOrder_action_serial_no =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            
            //データコードを取得する
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER;
            }
            //対象データを取得する--------------------------------[END]

            //キューテーブルへデータ準備
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
                new HostFotypeOrderAllParams();

            //setデータコード
            l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);

            //set口座ＩＤ
            //注文単位.口座ID
            l_hostFotypeOrderAllParams.setAccountId(l_ifoOrderUnitRow.getAccountId());

            //set証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
            //set部店コード
            l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
            //set顧客コード
            l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
            //set扱者コード
            l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
            //set市場コード
            l_hostFotypeOrderAllParams.setSonarMarketCode(l_strMarketCode);
            //set受注日区分
            l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strReceivedDateTimeDiv);
            //set識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //set銘柄コード
            l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);

            //set発注経路区分
            //注文単位.発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv());

            //set原資産銘柄コード
            l_hostFotypeOrderAllParams.setTargetProductCode(l_strTargetProductCode);
            //set限月（年）
            //注文単位.銘柄ＩＤに該当する銘柄.限月の年部分（YYYY）
            l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(l_strDeliveryMonthYYYY);
            //set限月（月）
            //注文単位.銘柄ＩＤに該当する銘柄.限月の月部分（MM）
            l_hostFotypeOrderAllParams.setDeliveryMonthMm(l_strDeliveryMonthMM);
            //set先物オプション商品
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
            }
            else if (IfoDerivativeTypeEnum.FUTURES.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
            }
            
            //set行使価格
            l_hostFotypeOrderAllParams.setStrikePrice(l_dblStrikePrice);
            //set分割
            l_hostFotypeOrderAllParams.setSplitType(l_strSplitType);
            //set売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
            //set買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
            //set指値
            l_hostFotypeOrderAllParams.setLimitPrice(l_dblLimitPrice);
            //set執行条件
            l_hostFotypeOrderAllParams.setExecutionCondition(l_strExecutionCondition);

            //逆指値基準値
            //NULL
            l_hostFotypeOrderAllParams.setStopOrderPrice(null);

            //（W指値）訂正指値
            //NULL
            l_hostFotypeOrderAllParams.setWLimitPrice(null);

            //set取引区分
            l_hostFotypeOrderAllParams.setTransactionType(l_strTransactionType);
            //set伝票№
            l_hostFotypeOrderAllParams.setTicketNumber(l_strTicketNumber);
            //set建玉チェック
            l_hostFotypeOrderAllParams.setContractCheck(l_strContractCheck);
            //set受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsReceivedDateTime);
            //set注文チャネル
            l_hostFotypeOrderAllParams.setOrderChanel(l_strOrderChannel);
            //set手数料№
            l_hostFotypeOrderAllParams.setCommisionNumber(l_strCommisionNumber);
            //set手数料№枝番
            l_hostFotypeOrderAllParams.setCommisionBranchNumber(l_strCommisionBranchNumber);
            //set手数料商品コード
            l_hostFotypeOrderAllParams.setCommisionProductCode(l_strCommisionProductCode);

            //訂正数量     change_quantity
            //null
            l_hostFotypeOrderAllParams.setChangeQuantity(null);

            //訂正指値    change_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeLimitPrice(null);

            //訂正執行条件     change_execution_condition
            //null
            l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);

            //訂正逆指値基準値        change_stop_order_price
            //null
            l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);

            //訂正（W指値）訂正指値        change_w_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);

            //取消区分        cancel_div
            //0：取消以外
            l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);

            //フロント発注取引所区分コード     front_order_exchange_code
            //先物OP発注サービス.getフロント発注取引所区分コード()の戻り値
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                l_ifoOrderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
            
            //フロント発注システム区分       front_order_system_code
            //先物OP発注サービス.getフロント発注システム区分()の戻り値
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                l_ifoOrderService.getFrontOrderSystemCode(l_market.getMarketCode()));

            //フロント発注取引区分コード      front_order_trade_code
            //1：株券売買
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);

            //自己委託区分      tradeaudit_code
            //0：委託
            l_hostFotypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);

            //社内処理項目      corp_code
            //先物OP発注サービス.get社内処理項目()の戻り値
            l_hostFotypeOrderAllParams.setCorpCode(
                l_ifoOrderService.getCorpCode((IfoOrderUnit)l_orderUnit));

            //（被訂正）社内処理項目     org_corp_code
            //null
            l_hostFotypeOrderAllParams.setOrgCorpCode(null);

            //仮想サーバNo.（JSOES）     virtual_server_number_jsoes
            //null
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);

            //市場発注No.       market_order_number
            //null
            l_hostFotypeOrderAllParams.setMarketOrderNumber(null);

            //AMG送信時刻        amg_send_time
            //null
            l_hostFotypeOrderAllParams.setAmgSendTime(null);

            //AMG入力保証受信時刻      amg_ack_time
            //null
            l_hostFotypeOrderAllParams.setAmgAckTime(null);

            //市場入力保証受信時刻      market_ack_time
            //null
            l_hostFotypeOrderAllParams.setMarketAckTime(null);

            //全訂正処理区分          all_order_change_div
            //0：全訂正以外
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
            
            //set注文履歴番号
            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrder_action_serial_no);
            //set処理区分
            l_hostFotypeOrderAllParams.setStatus(l_strStatus);
            

            log.debug("l_hostFotypeOrderAllParams" + l_hostFotypeOrderAllParams);

            //(*2) キューテーブルに行を挿入する。
            //挿入する行の内容は、DB更新仕様[OP新規建_先物OP注文キューテーブル.xls]参照。
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
        }
        catch (DataException l_de)
        {
            log.error("__an unexpected error__", l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__an unexpected error__", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert返済注文キュー)<BR>
     * <BR>
     * （insertSettleContractHostOrder）<BR>
     * 先物OP注文取引キューテーブルに返済注文のデータを登録する。<BR>
     * <BR>
     * １）　@this.getOrderUnits(注文ID)にて注文単位を取得する。<BR>
     * 　@※戻り値の0番目の要素を注文単位として使用する。<BR>
     * <BR>
     * ２）　@先物OP注文取引キューテーブルにデータを登録する。<BR>
     * <BR>
     * 　@２－１）　@オプションの場合（注文単位.先物／オプション区分="オプション"）<BR>
     * 　@　@挿入する行の内容は、DB更新仕様<BR>
     * 　@　@「OP返済_先物OP注文取引キューテーブル.xls」参照<BR>
     * <BR>
     * 　@２－２）　@先物の場合（注文単位.先物／オプション区分="先物"）<BR>
     * 　@　@更新する行の内容は、DB更新仕様<BR>
     * 　@　@「先物返済_先物OP注文取引キューテーブル.xls」 参照。<BR>
     * @@param l_lngOrderId - 注文ID<BR>
     * 注文ID。
     * @@throws WEB3BaseException
     */
    public void insertSettleContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertSettleContractHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.OPTION_ORDER;// リクエストデータコード
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//処理区分 "0：未処理"
            String l_strInstitutionCode = null;//証券会社コード
            String l_strBranchCode = null;//部店コード
            String l_strAccountCode = null;//顧客コード
            String l_strTraderCode = null;//扱者コード
            String l_strReceivedDateTimeDiv = null;//受注日区分
            String l_strOrderRequestNumber = null;//識別コード
            String l_strMarketCode = null;//市場
            String l_strProductCode = null;//銘柄コード
            String l_strTargetProductCode = null;//原資産銘柄コード
            String l_strDeliveryMonthYYYY = null;//限月（年）
            String l_strDeliveryMonthMM = null;//限月（月）
            IfoDerivativeTypeEnum l_futureOptionProductType = null;//先物オプション商品
            double l_dblStrikePrice = 0;//行使価格
            String l_strSplitType = null;//分割
            double l_dblSellOrderQuantity = 0;//売付数量
            double l_dblBuyOrderQuantity = 0;//買付数量
            double l_dblLimitPrice = 0;//指値
            String l_strExecutionCondition = null;//執行条件
            String l_strTransactionType = null;//取引区分
            String l_strTicketNumber = null;//伝票№
            String l_strContractCheck = null;//建玉チェック
            Timestamp l_tsReceivedDateTime = null;//受注日時
            String l_strOrderChannel = null;//注文チャネル
            String l_strCommisionNumber = null;//手数料№
            String l_strCommisionBranchNumber = null;//手数料№枝番
            String l_strCommisionProductCode = null;//手数料商品コード
            String l_strFuturesOptionDiv = null;
            int l_intOrder_action_serial_no = 0;//注文履歴番号

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;

            IfoProductImpl l_ifoProductImpl = null;

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            //注文取得
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //先物オプション区分を取得
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            //対象データを取得する----------------------------[START]
            //throw NotFoundException
            //銘柄オブジェクトを取得する
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                                    l_ifoOrderUnitRow.getProductId());

            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //証券会社コードを取得する
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            //部店コードを取得する
            l_strBranchCode = l_banch.getBranchCode();

            //顧客コードを取得する
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //扱者コードを取得する
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();

            //受注日時を取得する
            l_tsReceivedDateTime = l_ifoOrderUnitRow.getReceivedDateTime();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsReceivedDateTime);

            //受注日区分を取得する
            l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.TODAY;
            }
            //識別コードを取得する
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            //市場コードを取得する
            l_strMarketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //銘柄コードを取得する
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //原資産銘柄コードを取得する
            l_strTargetProductCode =  l_ifoProductImpl.getUnderlyingProductCode();

            //限月(年)を取得する
            l_strDeliveryMonthYYYY = l_ifoProductImpl.getMonthOfDelivery().substring(0,4);

            //限月(月)を取得する
            l_strDeliveryMonthMM =  l_ifoProductImpl.getMonthOfDelivery().substring(4);

            //先物オプション商品を取得する
            l_futureOptionProductType =l_ifoProductImpl.getDerivativeType();

            //行使価格を取得する
            l_dblStrikePrice = l_ifoProductImpl.getStrikePrice();

            //分割を取得する
            l_strSplitType = ((IfoProductRow) l_ifoProductImpl.getDataSourceObject()).getSplitType();

            //買付/売付数量を取得する
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();            
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //607：OP売建返済注文（買返済）/603：先物売建返済注文（買返済）
            {   //買付の場合
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) || 
            OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //608：OP買建返済注文（売返済）/604：先物買建返済注文（売返済）
            {
                //売付の場合
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }

            //指値を取得
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();

            //執行条件を取得する
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //寄付
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //引け
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //出来ずば引成(不成)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // 出来るまで注文(注文単位.初回注文の注文単位ID≠null)の場合、２：出合。
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //無条件
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //取引区分を取得する
            l_strTransactionType = l_ifoOrderUnitRow.getSonarTradedCode();

            //伝票№を取得する
            l_strTicketNumber = l_ifoOrderUnitRow.getVoucherNo();

            //建玉チェックを取得する
            //先物OPデータアダプタ.get日計り区分(注文単位.日計り区分)の戻り値
            l_strContractCheck = WEB3IfoDataAdapter.getDayTradeType(
                l_ifoOrderUnitRow.getDayTradeType());

            //注文チャネルを取得する
            l_strOrderChannel = l_ifoOrderUnitRow.getOrderChanel();

            //手数料№を取得する
            l_strCommisionNumber = l_ifoOrderUnitRow.getCommTblNo();

            //手数料№枝番を取得する
            l_strCommisionBranchNumber = l_ifoOrderUnitRow.getCommTblSubNo();

            //手数料商品コードを取得する
            l_strCommisionProductCode =
                    l_ifoOrderUnitRow.getCommProductCode();

            //注文履歴最終通番を取得する
            l_intOrder_action_serial_no =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();

            //データコードを取得する
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER;
            }
            //対象データを取得する-----------------------------------[END]            

            //キューテーブルへデータ準備
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
                new HostFotypeOrderAllParams();

            //setデータコード
            l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);

            //set口座ＩＤ
            //注文単位.口座ID
            l_hostFotypeOrderAllParams.setAccountId(l_ifoOrderUnitRow.getAccountId());

            //set証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
            //set部店コード
            l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
            //set顧客コード
            l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
            //set扱者コード
            l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
            //set受注日時
            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsReceivedDateTime);
            //set受注日区分
            l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strReceivedDateTimeDiv);
            //set識別コード
            l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //set市場コード
            l_hostFotypeOrderAllParams.setSonarMarketCode(l_strMarketCode);
            //set銘柄コード
            l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);

            //set発注経路区分
            //注文単位.発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv());

            //set原資産銘柄コード
            l_hostFotypeOrderAllParams.setTargetProductCode(l_strTargetProductCode);
            //set限月（年）
            l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(l_strDeliveryMonthYYYY);
            //set限月（月）
            l_hostFotypeOrderAllParams.setDeliveryMonthMm(l_strDeliveryMonthMM);
            //set先物オプション商品

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
            }
            else if (IfoDerivativeTypeEnum.FUTURES.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
            }
                        
            //set行使価格
            l_hostFotypeOrderAllParams.setStrikePrice(l_dblStrikePrice);
            //set分割
            l_hostFotypeOrderAllParams.setSplitType(l_strSplitType);
            //set売付数量
            l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
            //set買付数量
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
            //set指値
            l_hostFotypeOrderAllParams.setLimitPrice(l_dblLimitPrice);
            //set執行条件
            l_hostFotypeOrderAllParams.setExecutionCondition(l_strExecutionCondition);

            //逆指値基準値          stop_order_price
            //NULL
            l_hostFotypeOrderAllParams.setStopOrderPrice(null);

            //（W指値）訂正指値         w_limit_price
            //NULL
            l_hostFotypeOrderAllParams.setWLimitPrice(null);

            //set取引区分
            l_hostFotypeOrderAllParams.setTransactionType(l_strTransactionType);
            //set伝票№
            l_hostFotypeOrderAllParams.setTicketNumber(l_strTicketNumber);
            //set建玉チェック
            l_hostFotypeOrderAllParams.setContractCheck(l_strContractCheck);
            //set注文チャネル
            l_hostFotypeOrderAllParams.setOrderChanel(l_strOrderChannel);
            //set手数料№
            l_hostFotypeOrderAllParams.setCommisionNumber(l_strCommisionNumber);
            //set手数料№枝番
            l_hostFotypeOrderAllParams.setCommisionBranchNumber(l_strCommisionBranchNumber);
            //set手数料商品コード
            l_hostFotypeOrderAllParams.setCommisionProductCode(l_strCommisionProductCode);

            //訂正数量     change_quantity
            //null
            l_hostFotypeOrderAllParams.setChangeQuantity(null);

            //訂正指値    change_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeLimitPrice(null);

            //訂正執行条件     change_execution_condition
            //null
            l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);

            //訂正逆指値基準値        change_stop_order_price
            //null
            l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);

            //訂正（W指値）訂正指値        change_w_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);

            //取消区分        cancel_div
            //0：取消以外
            l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);

            //フロント発注取引所区分コード     front_order_exchange_code
            //先物OP発注サービス.getフロント発注取引所区分コード()の戻り値
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                l_ifoOrderService.getFrontOrderExchangeCode(l_market.getMarketCode()));

            //フロント発注システム区分       front_order_system_code
            //先物OP発注サービス.getフロント発注システム区分()の戻り値
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                l_ifoOrderService.getFrontOrderSystemCode(l_market.getMarketCode()));

            //フロント発注取引区分コード      front_order_trade_code
            //1：株券売買
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);

            //自己委託区分      tradeaudit_code
            //0：委託
            l_hostFotypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);

            //社内処理項目      corp_code
            //先物OP発注サービス.get社内処理項目()の戻り値
            l_hostFotypeOrderAllParams.setCorpCode(
                l_ifoOrderService.getCorpCode((IfoOrderUnit)l_orderUnit));

            //（被訂正）社内処理項目     org_corp_code
            //null
            l_hostFotypeOrderAllParams.setOrgCorpCode(null);

            //仮想サーバNo.（JSOES）     virtual_server_number_jsoes
            //null
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);

            //市場発注No.       market_order_number
            //null
            l_hostFotypeOrderAllParams.setMarketOrderNumber(null);

            //AMG送信時刻        amg_send_time
            //null
            l_hostFotypeOrderAllParams.setAmgSendTime(null);

            //AMG入力保証受信時刻      amg_ack_time
            //null
            l_hostFotypeOrderAllParams.setAmgAckTime(null);

            //市場入力保証受信時刻      market_ack_time
            //null
            l_hostFotypeOrderAllParams.setMarketAckTime(null);

            //全訂正処理区分          all_order_change_div
            //0：全訂正以外
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);

            //set注文履歴番号
            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrder_action_serial_no);
             //set処理区分
            l_hostFotypeOrderAllParams.setStatus(l_strStatus);

            log.debug("l_hostFotypeOrderAllParams =" +l_hostFotypeOrderAllParams);

            //(*2) OP返済_先物OP注文キューテーブルに行を挿入する。
            //挿入する行の内容は、DB更新仕様参照。
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__an unexpected error__", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataException l_de)
        {
            log.error("__an unexpected error__", l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate新規建注文)
     * （validateOpenContractOrderのオーバーライド）<BR>
     * <BR>
     * OP新規建発注審査を行う。<BR>
     * <BR>
     * this.validate新規建注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate新規建注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用新規建注文内容：　@パラメータ.新規建注文内容<BR>
     * 　@注文単位：　@null<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_openContractOrderSpec - 先物OP新規建注文内容
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_result =
            this.validateOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (remove繰越元注文単位)<BR>
     * 引数の注文単位オブジェクトのリストから、繰越元の注文単位オブジェクトを除去し、除去後のリストを返却する。<BR>
     * <BR>
     * １）　@注文単位Rowの取得<BR>
     * <BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * 　@パラメータ.注文単位一覧要素数分のLoop処理を行い、<BR>
     * 　@注文単位.getDataSourceObject()にて<BR>
     * 　@注文単位Rowを取得し、ArrayListに追加する。<BR>
     * <BR>
     * ２）　@this.remove繰越元注文単位()に処理を委譲する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文単位Params一覧：　@１）にて生成したArrayList.toArray()<BR>
     * @@param l_orderUnitList - 注文単位オブジェクトの一覧<BR>
     * @@return IfoOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A9018F
     */
    public IfoOrderUnit[] removeCarryOverOriginalOrderUnit(IfoOrderUnit[] l_orderUnitList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "removeCarryOverOriginalOrderUnit(IfoOrderUnit[])";

        log.entering(STR_METHOD_NAME);

        List  l_orderUnitListNew = new ArrayList();
        for (int i = 0; i < l_orderUnitList.length; i++)
        {
            l_orderUnitListNew.add(l_orderUnitList[i].getDataSourceObject());            
        }       
        IfoOrderUnitParams[] l_ifoOrderUnitParamsList =
            this.removeCarryOverOriginalOrderUnit((IfoOrderUnitParams[])l_orderUnitListNew.toArray(new IfoOrderUnitParams[0]));
        
        if (l_ifoOrderUnitParamsList == null)
        {
	        log.exiting(STR_METHOD_NAME);
	        return null;
	    }
        else
        {
            ArrayList l_orderUnitListReturn = new ArrayList();
            for (int i = 0; i < l_ifoOrderUnitParamsList.length; i++)
            {
                l_orderUnitListReturn.add(this.toOrderUnit(l_ifoOrderUnitParamsList[i]));
            }
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnit[])l_orderUnitListReturn.toArray(new IfoOrderUnit[0]);
        }
    }
    
    /**
     * (remove繰越元注文単位)
     *引数の注文単位オブジェクトのリストから、繰越元の注文単位オブジェクトを除去し、<BR>
     *除去後のリストを返却する。<BR>
     *<BR>
     *１）　@パラメータ.注文単位一覧 == nullの場合、<BR>
     *　@　@nullを返却して終了する。<BR>
     *<BR>
     *２）　@除去対象の判定 <BR>
     *<BR>
     *　@　@　@以下、パラメータ.注文単位一覧の要素数分のLoop処理。 <BR>
     *<BR>
     * 　@　@　@[先物OPデータアダプタ.get注文期限区分(注文単位)が"当日限り"の場合 ]<BR>
     *　@　@　@　@　@リストにそのまま残す。 <BR>
     *<BR>
     * 　@　@　@[先物OPデータアダプタ.get注文期限区分(注文単位)が"当日限り"以外の場合 ]<BR>
     *<BR>
     *　@　@　@　@[初回注文の場合] <BR>
     * 　@　@　@　@(注文単位.初回注文の注文単位ID == 0 || 注文単位.初回注文の注文単位ID == nullの場合)<BR>
     *　@　@　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、 <BR>
     *　@　@　@　@　@　@　@注文単位.注文単位ID == リスト中の注文単位.初回注文の注文単位ID <BR>
     *　@　@　@　@　@となるデータが存在した場合は、自身を除去対象とする。 <BR>
     *　@　@　@　@　@※繰越後の注文が存在する為。 <BR>
     *<BR>
     *　@　@　@　@[繰越済注文の場合] <BR>
     *　@　@　@　@(注文単位.初回注文の注文単位ID != 0の場合) <BR>
     *　@　@　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、 <BR>
     *　@　@　@　@　@　@　@注文単位.初回注文の注文単位ID == リスト中の注文単位.初回注文の注文単位ID <BR>
     *　@　@　@　@　@となるデータが存在した場合は、作成日時を比較し、<BR>
     *　@　@　@　@　@最新の注文単位以外を全て除去対象とする。<BR> 
     *　@　@　@　@　@※最新の繰越注文のみを表示する為。 <BR>
     *<BR>
     *３）　@リストからの除去対象と判定された繰越元の注文単位オブジェクトを、<BR>
     *　@　@注文単位一覧から全て除去する。<BR> 
     *　@　@※パラメータ.注文単位オブジェクトの並び順は顧客指定のソート条件によるため、<BR>
     *　@　@　@　@除去は最後に纏めて行う必要がある。<BR> 
     *<BR>
     *４）　@除去済の注文単位一覧を返却する。<BR>
     *　@　@※注文単位一覧の要素数が0になった場合はNULLを返却する。<BR>
     * @@param l_orderUnitList - 先物OP注文単位Paramsの配列<BR>
     * @@return IfoOrderUnitParams[]
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A9018F
     */
    public IfoOrderUnitParams[] removeCarryOverOriginalOrderUnit(IfoOrderUnitParams[] l_orderUnitList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "removeCarryOverOriginalOrderUnit(IfoOrderUnitParams[])";

        log.entering(STR_METHOD_NAME);

        //パラメータ.注文単位一覧 == nullの場合、
        //nullを返却して終了する。
        if (l_orderUnitList == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        List  l_orderUnitListNew = new ArrayList();
        for (int i = 0; i < l_orderUnitList.length; i++)
        {
            l_orderUnitListNew.add(l_orderUnitList[i]);            
        }       
        ListIterator l_iterator = l_orderUnitListNew.listIterator();
        while (l_iterator.hasNext())
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_iterator.next();
            //[先物OPデータアダプタ.get注文期限区分(注文単位)が"当日限り"の場合 ]
            //リストにそのまま残す。
            //[先物OPデータアダプタ.get注文期限区分(注文単位)が"当日限り"以外の場合 ]
            IfoOrderUnit l_ifoOrderUnit =
                (IfoOrderUnit)this.toOrderUnit(l_orderUnitRow);
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);

            if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
            {
                //[初回注文の場合]<BR> 
                //(注文単位.初回注文の注文単位ID == 0 || 注文単位.初回注文の注文単位ID == nullの場合)
                //リスト中(パラメータ.注文単位一覧)を検索し、<BR> 
                //注文単位.注文単位ID == リスト中の注文単位.初回注文の注文単位ID<BR> 
                //となるデータが存在した場合は、自身を除去対象とする。<BR> 
                // ※繰越後の注文が存在する為。<BR>
                if (l_orderUnitRow.getFirstOrderUnitIdIsNull()
                    || l_orderUnitRow.getFirstOrderUnitId() == 0)
                {
                    for (int j =0; j < l_orderUnitList.length; j++)
                    {
                        IfoOrderUnitRow l_orderUnitRowSource =
                                        (IfoOrderUnitRow)l_orderUnitList[j];
                        if (l_orderUnitRow.getOrderUnitId() == l_orderUnitRowSource.getFirstOrderUnitId())
                        {
                            l_iterator.remove();
                            break;                                                    
                        }
                    }
                }
                else
                {
                    //[繰越済注文の場合] <BR>
                    //(注文単位.初回注文の注文単位ID != 0の場合) <BR>
                    //リスト中(パラメータ.注文単位一覧)を検索し、 <BR>
                    //注文単位.初回注文の注文単位ID == リスト中の注文単位.初回注文の注文単位ID <BR>
                    //となるデータが存在した場合は、作成日時を比較し、最新の注文単位以外を全て除去対象とする。
                    //※最新の繰越注文のみを表示する為。<BR> 
 
                    for (int j =0; j < l_orderUnitList.length; j++)
                    {
                        IfoOrderUnitRow l_orderUnitRowSource =
                                        (IfoOrderUnitRow)l_orderUnitList[j];
                        if (l_orderUnitRow.getFirstOrderUnitId() == l_orderUnitRowSource.getFirstOrderUnitId())
                        {
                            if (WEB3DateUtility.compareToSecond(l_orderUnitRowSource.getCreatedTimestamp(), l_orderUnitRow.getCreatedTimestamp()) > 0)
                            {
                                l_iterator.remove();
                                break;  
                            }                                                  
                        }
                    }                   
                }
            }
        }
        if (l_orderUnitListNew.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnitParams[])l_orderUnitListNew.toArray(new IfoOrderUnitParams[0]);
        }
    }

   /**
     * (throw余力エラー詳細情報)<BR>
     * 引数で設定された取引余力結果・補助口座に応じて、<BR>
     * 該当するエラーコードを設定して例外をthrowをする。<BR>
     * <BR>
     * １）　@取引余力結果.判定フラグ＝trueの場合、<BR>
     * 　@そのままreturnする。（余力チェックOK）<BR>
     * ２）　@取引余力結果.判定フラグ＝falseの場合<BR>
     * 　@２－１）　@証拠金口座の場合<BR>
     * 　@　@補助口座.補助口座タイプ＝<BR>
     * 　@　@"株式オプション取引口座（先物証拠金）"であれば、<BR>
     * 　@　@「取引余力チェックエラー」の例外をthrowする。<BR>
     * 　@２－２）　@オプション買建口座の場合<BR>
     * 　@　@補助口座.補助口座タイプ≠<BR>
     * 　@　@"株式オプション取引口座（先物証拠金）"であれば、<BR>
     * 　@　@「新規建預かり金不足」の例外をthrowする。<BR>
     * @@param l_tpResult - (取引余力結果)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@throws WEB3BaseException
     */
   public void throwTpErrorInfo(
        WEB3TPTradingPowerResult l_tpResult,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
   {
        String STR_METHOD_NAME =
            "throwTpErrorInfo(WEB3TPTradingPowerResult, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_tpResult == null || l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
         }

        //取引余力結果.判定フラグ＝trueの場合
        if (l_tpResult.isResultFlg())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);

            //補助口座.補助口座タイプ=="株式オプション取引口座（先物証拠金）"
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01935,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

   /**
     * (get注文エラー理由コード)<BR>
     * 引数のエラーコードから注文エラー理由コードへの変換を行う。<BR>
     * （注文エラー理由コードのコード体系は、先物OP注文単位テーブルのDBレイアウトを参照）<BR>
     * <BR>
     * 変換仕様は以下の通り。<BR>
     * ---------------------------------------------------------------------<BR>
     * "正常"<BR>
     * 引数のエラーコード==null<BR>
     * <BR>
     *"値幅・刻み値エラー"<BR>
     * （BUSINESS_ERROR_00148 or<BR>
     * 　@BUSINESS_ERROR_00031）<BR>
     * <BR>
     * "預り金不足エラー"（※オプション買建口座の場合）<BR>
     * 　@BUSINESS_ERROR_01935<BR>
     * <BR>
     * "株価指数先物オプション残高不足エラー"<BR>
     * 　@BUSINESS_ERROR_01306<BR>
     * <BR>
     * "建玉残高不足エラー"<BR>
     * 　@BUSINESS_ERROR_00299<BR>
     * <BR>
     * "売買停止銘柄エラー"<BR>
     * 　@BUSINESS_ERROR_00004<BR>
     * <BR>
     * "市場変更銘柄エラー"<BR>
     * （BUSINESS_ERROR_00003 or<BR>
     * 　@BUSINESS_ERROR_00735）<BR>
     * <BR>
     *"注文繰越スキップ銘柄エラー"<BR>
     * 　@BUSINESS_ERROR_00684<BR>
     * <BR>
     * "発注日チェックエラー"<BR>
     * 　@BUSINESS_ERROR_00205<BR>
     * <BR>
     * "その他エラー"<BR>
     * 上記以外<BR>
     * @@param l_strErrorCode - (エラーコード)<BR>
     * WEB3ErrorCatalogに定義されているエラーコード。<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String getErrorReasonCode(
        String l_strErrorCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getErrorReasonCode(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strErrorCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            //"0000"（正常）
            return WEB3ErrorReasonCodeDef.NORMAL;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorCode().equals(l_strErrorCode))
        {
            //"0001"（値幅・刻み値エラー）
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01935.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0002""預り金不足エラー"（※オプション買建口座の場合）
            return WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0003""株価指数先物オプション残高不足エラー" 
            return WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00299.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0005"（建玉残高不足エラー" ）
            return WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0006"（売買停止銘柄エラー）
            return WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }                                
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00003.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00735.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0007"（市場変更銘柄エラー）
            return WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00684.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0011"（注文繰越スキップ銘柄エラー）
            return WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00205.getErrorCode().equals(l_strErrorCode))
        {
            //"0013"（発注日チェックエラー）
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.BIZ_DATE_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            //上記以外の発注審査でエラーが発生した場合は、"9001"（その他エラー）を設定する。
            return WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
    }

   /**
     * (update発注遅延)<BR>
     * <BR>
     * （updateOrderDelay）<BR>
     * 指定された注文IDに紐付く注文データ類を発注遅延のステータスに更新する。<BR>
     * １）　@パラメータ.注文IDに紐付く注文単位を取得する。<BR>
     * <BR>
     * ２）　@１）の注文単位のcloneを作成する。<BR>
     * <BR>
     * ３）　@２）にて作成したcloneに対し、更新値をセットする。<BR>
     * 　@DB更新仕様<BR>
     * 　@「逆指値注文発注（発注遅延）_先物OP注文単位テーブル.xls」＃<BR>
     * 　@　@（逆指値注文）[発注遅延]先物OP注文単位テーブル」参照。<BR>
     * ４）　@注文データをupdateする。<BR>
     * 　@this.update注文データ()をコールする。<BR>
     * 　@[update注文データ()に指定する引数]<BR>
     * 　@　@注文単位：　@３）にて作成した注文単位<BR>
     * 　@　@is履歴作成：　@false（作成しない）<BR>
     * <BR>
     * ５）　@３）にて作成した注文単位を使用し <BR>
     * 　@　@注文履歴テーブルへ１レコードinsertする。 <BR>
     * 　@　@更新内容は、DB更新仕様 <BR>
     * 　@　@「逆指値注文発注(発注遅延)_先物OP注文履歴テーブル.xls」参照。<BR>
     * 注文単位オブジェクト。<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 発注遅延のステータスに更新を行う注文ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderDelay(
        long l_lngOrderID)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderDelay(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@パラメータ.注文IDに紐付く注文単位を取得する。
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderID);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        IfoOrderUnitParams l_ifoOrderUnitParams =
            new IfoOrderUnitParams((IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject());

        //２）　@１）の注文単位のcloneを作成する。
        IfoOrderUnit l_cloneIfoOrderUnit =
            (IfoOrderUnit)super.toOrderUnit(l_ifoOrderUnitParams);

        //３）　@２）にて作成したcloneに対し、更新値をセットする。
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

        l_ifoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        // ４）　@注文データをupdateする。
        this.updateOrderData(l_cloneIfoOrderUnit, false);

        //５）　@３）にて作成した注文単位を使用し
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();

        //注文履歴ＩＤ
        long l_lngOrderActionId = 0;
        try
        {
            l_lngOrderActionId = IfoOrderActionDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        l_ifoOrderActionParams.setOrderActionId(l_lngOrderActionId);

        //口座ＩＤ
        l_ifoOrderActionParams.setAccountId(l_ifoOrderUnitParams.getAccountId());

        //補助口座ＩＤ
        l_ifoOrderActionParams.setSubAccountId(l_ifoOrderUnitParams.getSubAccountId());

        //注文ＩＤ
        l_ifoOrderActionParams.setOrderId(l_ifoOrderUnitParams.getOrderId());

        //注文単位ＩＤ
        l_ifoOrderActionParams.setOrderUnitId(l_ifoOrderUnitParams.getOrderUnitId());

        //市場ＩＤ
        if (!l_ifoOrderUnitParams.getMarketIdIsNull())
        {
            l_ifoOrderActionParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
        }

        //注文種別
        l_ifoOrderActionParams.setOrderType(l_ifoOrderUnitParams.getOrderType());

        //注文イベントタイプ
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.ORDER_DELAY);

        //注文単価
        if (!l_ifoOrderUnitParams.getLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());
        }

        //執行条件
        l_ifoOrderActionParams.setExecutionConditionType(
            l_ifoOrderUnitParams.getExecutionConditionType());

        //発注条件
        l_ifoOrderActionParams.setOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //発注条件演算子
        l_ifoOrderActionParams.setOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //逆指値基準値タイプ
        l_ifoOrderActionParams.setStopPriceType(l_ifoOrderUnitParams.getStopPriceType());

        //逆指値基準値
        if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //（W指値）訂正指値
        if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
        }

        //注文失効日付
        l_ifoOrderActionParams.setExpirationDate(l_ifoOrderUnitParams.getExpirationDate());

        //注文数量
        l_ifoOrderActionParams.setQuantity(l_ifoOrderUnitParams.getQuantity());

        //市場と確認済みの指値
        if (!l_ifoOrderUnitParams.getConfirmedPriceIsNull())
        {
            l_ifoOrderActionParams.setConfirmedPrice(l_ifoOrderUnitParams.getConfirmedPrice());
        }

        //市場と確認済みの数量
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderActionParams.setConfirmedQuantity(
                l_ifoOrderUnitParams.getConfirmedQuantity());
        }

        //約定数量
        l_ifoOrderActionParams.setExecutedQuantity(null);

        //注文状態
        l_ifoOrderActionParams.setOrderStatus(l_ifoOrderUnitParams.getOrderStatus());

        //注文失効ステータス
        l_ifoOrderActionParams.setExpirationStatus(l_ifoOrderUnitParams.getExpirationStatus());

        //注文履歴番号
        l_ifoOrderActionParams.setOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo());

        //約定単価
        l_ifoOrderActionParams.setExecutedPrice(null);

        //銘柄タイプ
        l_ifoOrderActionParams.setProductType(l_ifoOrderUnitParams.getProductType());

        //銘柄ＩＤ
        l_ifoOrderActionParams.setProductId(l_ifoOrderUnitParams.getProductId());

        //概算受渡代金
        if (!l_ifoOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_ifoOrderActionParams.setEstimatedPrice(
                l_ifoOrderUnitParams.getEstimatedPrice());
        }

        //注文訂正・取消区分
        l_ifoOrderActionParams.setModifyCancelType(
            l_ifoOrderUnitParams.getModifyCancelType());

        //決済順序
        l_ifoOrderActionParams.setClosingOrder(l_ifoOrderUnitParams.getClosingOrder());

        //注文エラー理由コード
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //リクエストタイプ
        l_ifoOrderActionParams.setRequestType(l_ifoOrderUnitParams.getRequestType());

        //取引者ID
        if (!l_ifoOrderUnitParams.getTraderIdIsNull())
        {
            l_ifoOrderActionParams.setTraderId(l_ifoOrderUnitParams.getTraderId());
        }

        //注文経路区分
        l_ifoOrderActionParams.setOrderRootDiv(l_ifoOrderUnitParams.getOrderRootDiv());

        //作成日付
        l_ifoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日付
        l_ifoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


        //元発注条件
        l_ifoOrderActionParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrgOrderConditionType());

        //元発注条件演算子
        l_ifoOrderActionParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrgOrderCondOperator());

        //元逆指値基準値タイプ
        l_ifoOrderActionParams.setOrgStopPriceType(l_ifoOrderUnitParams.getOrgStopPriceType());

        //元逆指値基準値
        if (!l_ifoOrderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getOrgStopOrderPrice());
        }

        //元（W指値）訂正指値
        if (!l_ifoOrderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getOrgWLimitPrice());
        }

        //元（W指値）執行条件
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_ifoOrderUnitParams.getOrgWLimitExecCondType());

        //（W指値）執行条件
        l_ifoOrderActionParams.setWLimitExecCondType(l_ifoOrderUnitParams.getWLimitExecCondType());

        //（W指値）切替前指値
        if (!l_ifoOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitParams.getWLimitBeforeLimitPrice());
        }

        //（W指値）切替前執行条件
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitParams.getWLimitBeforeExecCondType());

        //市場から確認済みの執行条件
        l_ifoOrderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitParams.getConfirmedExecConditionType());

        //注文期限区分
        l_ifoOrderActionParams.setExpirationDateType(
            l_ifoOrderUnitParams.getExpirationDateType());
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_ifoOrderActionParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
      * （get受渡金額合計）<BR>
      * <BR>
      * 引数で指定された注文単位に対する取引勘定明細.受渡代金の合計値を取得し、
      * 注文単位テーブルの概算受渡代金への設定用金額を返す。
      *  
      * １）　@引数の注文単位.注文カテゴリ == "先物新規建注文"の場合は、 
      * 注文単位.合計約定金額を返す。 
      * 引数の注文単位.注文カテゴリ != "先物新規建注文"の場合は、以下の処理を行う。
      *  
      * ２）　@先物OPトランザクションマネージャ.get受渡金額合計(引数の注文単位)により、
      * 受渡金額合計を取得する。
      *  
      * ３）　@注文単位テーブルの概算受渡代金への設定用金額を算出する。
      *  
      * ３－１）　@引数の注文単位.注文種別が以下に該当する場合は、
      * 　@　@　@　@　@２）で取得した値の符号を反転した値を返す。
      * 　@　@　@　@　@　@・OP新規買建注文（IDX_OPTIONS_BUY_TO_OPEN）
      * 　@　@　@　@　@　@・OP売建買返済注文（IDX_OPTIONS_BUY_TO_CLOSE）
      * 
      * ３－２）　@３－１）以外の場合は、
      * 　@　@　@　@　@２）で取得した値をそのまま返す。<BR>
      * <BR>
      * @@param l_ifoOrderUnit （注文単位）<BR>
      * 　@　@　@注文単位オブジェクト。<BR>
      * @@return double<BR>
      * @@throws WEB3BaseException<BR>
      */
    public double getNetAmount(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmount(l_ifoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // 注文単位.注文カテゴリ == "先物新規建注文"の場合
        if (l_ifoOrderUnit.getOrderCateg().equals(OrderCategEnum.IDX_FUTURES_OPEN)== true)
        {
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            return l_ifoOrderUnitRow.getExecutedAmount();
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionMgr
            = (WEB3IfoFinTransactionManagerImpl)l_tradingModule.getFinTransactionManager();

        //受渡金額の合計値を取得
        double l_dblDeliveryAmount = l_finTransactionMgr.getNetAmount(l_ifoOrderUnit);

        //注文単位.注文種別 == "OP新規買建注文" or "OP売建買返済注文"の場合
        if (l_ifoOrderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) ||
            l_ifoOrderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE))
        {
            //受渡金額合計値の符号を反転させる
            l_dblDeliveryAmount =
                new BigDecimal(l_dblDeliveryAmount + "").negate().doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblDeliveryAmount;
    }
     
    /**
     * （get初回注文の注文単位）。<BR>
     * <BR>
     * 引数で指定された注文単位オブジェクトの、初回注文の注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@引数の注文単位.初回注文の注文単位ID＝（null または 0）の場合<BR>
     * <BR>
     * 　@　@引数の注文単位オブジェクトを返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合、<BR>
     * <BR>
     * 　@　@this.getOrderUnit(引数の注文単位.初回注文の注文単位ID)で取得した<BR>
     * 　@　@注文単位オブジェクトを返却する。
     * @@param l_orderUnit 注文単位オブジェクト。
     * @@return OrderUnit
     * @@throws WEB3BaseException
     */
    public IfoOrderUnit getFirstOrderUnit(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFirstOrderUnit(IfoOrderUnit l_orderUnit)";
        
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || l_orderUnitRow.getFirstOrderUnitId() == 0)
            {
                //引数の注文単位.初回注文の注文単位ID＝（null または 0）の場合
                //引数の注文単位オブジェクトを返却する。
                log.exiting(STR_METHOD_NAME);
                return l_orderUnit;
            }
            else
            {
                //上記以外の場合
                log.exiting(STR_METHOD_NAME);
                return (IfoOrderUnit)this.getOrderUnit(l_orderUnitRow.getFirstOrderUnitId());
            }            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }

        
    }

    /**
     * (notifyW指値注文)<BR>
     * （notifyWLimitOrder） <BR>
     * W指値注文（ストップ注文）の登録、訂正、取消を <BR>
     * ルールエンジンサーバに通知する。 <BR>
     * <BR>
     * １）　@先物OP注文単位かどうかの判定　@ <BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     * 　@[メソッドの戻り値の型が先物OP注文単位Rowでない場合] <BR>
     * 　@　@処理対象外である為、処理を終了する。（return） <BR>
     * 　@　@※予約注文単位に対して、W指値は設定不可。<BR>
     * <BR>
     * 　@[先物OP注文単位Rowである場合] <BR>
     * 　@　@getOrderUnit().getDataSourceObject()をコールし、<BR>
     * 　@　@注文単位を取得し直す。 <BR>
     * <BR>
     * ２）　@ルールエンジンサーバへの通知要否チェック <BR>
     * 　@２－１）　@手動失効処理、取消失敗処理からコールされた場合、 <BR>
     * 　@　@（１)の戻り値.リクエストタイプ == "失効" かつ <BR>
     * 　@　@　@パラメータ.処理 == ("ORDER_EXPIRED",  <BR>
     * 　@　@　@　@"CANCEL_ORDER_REJECTED_BY_MKT")） <BR>
     * <BR>
     * 　@以降のチェックは実施しない。<BR>
     * <BR>
     * 　@２－２）　@　@切替未済のW指値注文かどうかの判定 
     * 　@　@切替未済のW指値注文でない場合、 
     * 　@　@（(１）の戻り値.発注条件 == "W指値" かつ 
     * 　@　@１）の戻り値.リクエストタイプ == "DEFAULT")以外） 
     * 　@　@処理対象外である為、処理を終了する。（return） 
     * <BR>
     * 　@２－３）　@未発注の発注遅延注文かどうかの判定<BR>
     * 　@　@OP注文マネージャ.is未発注遅延注文() == trueの場合、<BR>
     * 　@　@処理対象外である為、処理を終了する。（return） <BR>
     * <BR>
     * 　@　@[is未発注遅延注文()に指定する引数] <BR>
     * 　@　@　@注文単位：　@１）の戻り値<BR>
     * <BR>
     * ３）　@補助口座を取得する。 <BR>
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。 <BR>
     * <BR>
     * 　@[getSubAccount()に指定する引数] <BR>
     * 　@　@arg0：　@１）の戻り値.口座ID <BR>
     * 　@　@arg1：　@１）の戻り値.補助口座ID <BR>
     * <BR>
     * ４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService） <BR>
     * 　@を取得する。 <BR>
     * <BR>
     * ５）　@ルールエンジンサーバに通知を行う。 <BR>
     * 　@引数の処理、注文単位.注文状態によって下記処理分岐を行う。 <BR>
     * <BR>
     * 　@５－１）　@取消 <BR>
     * 　@　@引数の処理 == "ORDER_EXPIRED"（ストップ注文失効）、 <BR>
     * 　@　@"ORDER_INVALIDATED_BY_MKT"（失効）、 <BR>
     * 　@　@"CANCEL_ORDER_REJECTED_BY_MKT"（取消失敗）、 <BR>
     * 　@　@または取消受付済（注文状態="受付済（取消注文）"）の場合、 <BR>
     * 　@　@または取消確定、全部約定、注文受付エラー（注文有効状態="クローズ"）の場合、 <BR>
     * 　@　@取得したサービス.sendCancelConOrderMessage()メソッドを <BR>
     * 　@　@コールする。 <BR>
     * <BR>
     * 　@　@[sendCancelConOrderMessage()に指定する引数] <BR>
     * 　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値" <BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション" <BR>
     * 　@　@　@注文ID：　@１）の戻り値.注文ID <BR>
     * <BR>
     * 　@５－２）　@新規登録（注文状態="受付済（新規注文）"）の場合 <BR>
     * 　@　@取得したサービス.sendRegisterConOrdersMessage()メソッドを <BR>
     * 　@　@コールする。 <BR>
     * <BR>
     * 　@　@[sendRegisterConOrdersMessage()に指定する引数] <BR>
     * 　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値" <BR>
     * 　@　@　@親注文の銘柄タイプ：　@"先物オプション" <BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID <BR>
     * 　@　@　@子注文の銘柄タイプ一覧：　@null <BR>
     * 　@　@　@子注文の注文ID一覧：　@null <BR>
     * <BR>
     * 　@５－３）　@訂正完了 <BR>
     * 　@　@（注文状態="受付済（変更注文）" or "発注済（変更注文）"）の場合 <BR>
     * 　@　@取得したサービス.sendModifyConOrdersMessage()メソッドを <BR>
     * 　@　@コールする。 <BR>
     * 　@　@※市場送信済注文／市場未送信注文の訂正を考慮。 <BR>
     * <BR>
     * 　@　@[sendModifyConOrdersMessage()に指定する引数] <BR>
     * 　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値" <BR>
     * 　@　@　@親注文の銘柄タイプ：　@"先物オプション" <BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID <BR>
     * 　@　@　@子注文の銘柄タイプ一覧：　@null <BR>
     * 　@　@　@子注文の注文ID一覧：　@null <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@param l_context - (処理)<BR>
     * 処理。<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@throws WEB3BaseException
     */
    private void notifyWLimitOrder(
        IfoOrderUnit l_ifoOrderUnit, OrderManagerPersistenceContext l_context) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "notifyWLimitOrder(IfoOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@先物OP注文単位かどうかの判定　@ 
        //　@引数の注文単位.getDataSourceObject()をコールする。　@ 
        //　@[メソッドの戻り値の型が先物OP注文単位Rowでない場合] 
        //　@　@処理対象外である為、処理を終了する。（return） 
        //　@　@※予約注文単位に対して、W指値は設定不可。 
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        Object l_objRow = l_ifoOrderUnit.getDataSourceObject();
        if (!(l_objRow instanceof IfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //　@[先物OP注文単位Rowである場合] 
        //　@　@getOrderUnit().getDataSourceObject()をコールし、 
        //　@　@注文単位を取得し直す。
        IfoOrderUnitRow l_orderUnitRow = null;
        try 
        {
			l_orderUnitRow = 
			    (IfoOrderUnitRow)getOrderUnit(
			        l_ifoOrderUnit.getOrderUnitId()).getDataSourceObject();
		} 
        catch (NotFoundException e) 
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
		}
        
        String l_strRequestType = l_orderUnitRow.getRequestType();
        Long l_orderId = new Long(l_orderUnitRow.getOrderId());
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        
        //２）　@ルールエンジンサーバへの通知要否チェック
        //２－１）　@手動失効処理、取消失敗処理からコールされた場合、
        //（１)の戻り値.リクエストタイプ == "失効" かつ
        //パラメータ.処理 == ("ORDER_EXPIRED", 
        //"CANCEL_ORDER_REJECTED_BY_MKT")）
        //　@以降のチェックは実施しない。
        if ((OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context)
            ||OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT.equals(l_context))
            && WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            // 処理無し
        }
        else
        {
            //２－２）　@　@切替未済のW指値注文かどうかの判定
            //切替未済のW指値注文でない場合、
            //（(１）の戻り値.発注条件 == "W指値" かつ
            //１）の戻り値.リクエストタイプ == "DEFAULT")以外）
            //処理対象外である為、処理を終了する。（return）
            if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
                || (!WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType)))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //　@２－３）　@未発注の発注遅延注文かどうかの判定
            //　@　@OP注文マネージャ.is未発注遅延注文() == trueの場合、
            //　@　@処理対象外である為、処理を終了する。（return)
            if (this.isNotOrderedDelay(l_ifoOrderUnit))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }

        //３）　@補助口座を取得する。
        //　@拡張アカウントマネージャ.getSubAccount()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService） 
        //　@を取得する。 
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //５）　@ルールエンジンサーバに通知を行う。 
        //　@引数の処理、注文単位.注文状態によって下記処理分岐を行う。 
        //５－１）　@取消
        //引数の処理 == "ORDER_EXPIRED"（ストップ注文失効）、
        //"ORDER_INVALIDATED_BY_MKT"（失効）、
        //"CANCEL_ORDER_REJECTED_BY_MKT"（取消失敗）、
        //または取消受付済（注文状態="受付済（取消注文）"）の場合、
        //または取消確定、全部約定、注文受付エラー（注文有効状態="クローズ"）の場合、 
        //取得したサービス.sendCancelConOrderMessage()メソッドを
        //コールする。
        if (OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(l_context)
            || OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context)
            || OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT.equals(l_context)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
        {
            //　@　@取得したサービス.sendCancelConOrderMessage()メソッドを
            //　@　@コールする。
            //　@　@[sendCancelConOrderMessage()に指定する引数]
            //　@　@　@補助口座：　@取得した補助口座
            //　@　@　@条件付注文タイプ：　@"W指値"
            //　@　@　@銘柄タイプ：　@"先物オプション"
            //　@　@　@注文ID：　@１）の戻り値.注文ID
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId);
        }
        //５－２）　@新規登録（注文状態="受付済（新規注文）"）の場合
        //　@取得したサービス.sendRegisterConOrdersMessage()メソッドを
        //　@コールする。
        //　@[sendRegisterConOrdersMessage()に指定する引数]
        //　@　@補助口座：　@取得した補助口座
        //　@　@条件付注文タイプ：　@"W指値"
        //　@　@親注文の銘柄タイプ：　@"先物オプション"
        //　@　@親注文の注文ID：　@１）の戻り値.注文ID
        //　@　@子注文の銘柄タイプ一覧：　@null
        //　@　@子注文の注文ID一覧：　@null
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId,
                null,
                null);
        }
        //５－３）　@訂正完了
        //　@（注文状態="受付済（変更注文）" or "発注済（変更注文）"）の場合
        //　@取得したサービス.sendModifyConOrdersMessage()メソッドを
        //　@コールする。
        //　@※市場送信済注文／市場未送信注文の訂正を考慮。
        //　@　@[sendModifyConOrdersMessage()に指定する引数]
        //　@　@　@補助口座：　@取得した補助口座
        //　@　@　@条件付注文タイプ：　@"W指値"
        //　@　@　@銘柄タイプ：　@"先物オプション"
        //　@　@　@注文ID：　@１）の戻り値.注文ID
        //　@　@　@子注文の銘柄タイプ一覧：　@null 
        //　@　@　@子注文の注文ID一覧：　@null
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId,
                null,
                null);
        }

        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (isストップ注文有効)<BR>
     * W指値注文のストップ注文が有効かどうか判別する。 <BR>
     * <BR>
     * this.isストップ注文有効()に処理を委譲する。 <BR>
     * <BR>
     * [isストップ注文有効()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。<BR> 
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
    }
     
    /**
     * (isストップ注文有効)<BR>
     * W指値注文のストップ注文が有効かどうか判別する。 <BR>
     * <BR>
     * this.isストップ注文有効()に処理を委譲する。 <BR>
     * <BR>
     * [isストップ注文有効()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。<BR> 
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
    }
     
    /**
     * (isストップ注文有効)<BR>
     * W指値注文のストップ注文が有効かどうか判別する。<BR> 
     * ※発注条件 == "W指値"でない場合は、 <BR>
     * 　@一律falseを返却する。 <BR>
     * <BR>
     * １）　@W指値かどうかのチェック <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、 <BR>
     * 　@falseを返却する。 <BR>
     * <BR>
     * ２）　@ストップ注文が有効かどうかのチェック <BR>
     * 　@パラメータ.リクエストタイプ == "切替完了"の場合、<BR> 
     * 　@trueを返却する。 <BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean 
     */
    private boolean isStopOrderValid(
        String l_strOrderCondition, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderValid(String, String)";
        log.entering(STR_METHOD_NAME);        
        
        //１）　@W指値かどうかのチェック
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@ストップ注文が有効かどうかのチェック 
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (isストップ注文切替中)<BR>
     * W指値注文がストップ注文に切替中かどうかを判別する。<BR> 
     * <BR>
     * this.isストップ注文切替中()に処理を委譲する。<BR> 
     * <BR>
     * [isストップ注文切替中()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
     
    /**
     * (isストップ注文切替中)<BR>
     * W指値注文がストップ注文に切替中かどうかを判別する。 <BR>
     * <BR>
     * this.isストップ注文切替中()に処理を委譲する。 <BR>
     * <BR>
     * [isストップ注文切替中()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean 
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
     
    /**
     * (isストップ注文切替中)<BR>
     * W指値注文がストップ注文に切替中かどうかを判別する。 <BR>
     * ※発注条件 == "W指値"でない場合は、 <BR>
     * 　@一律falseを返却する。 <BR>
     * <BR>
     * １）　@W指値かどうかのチェック <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、 <BR>
     * 　@falseを返却する。 <BR>
     * <BR>
     * ２）　@ストップ注文に切替中かどうかのチェック <BR>
     * 　@パラメータ.リクエストタイプ == "時価サーバ"の場合、 <BR>
     * 　@trueを返却する。 <BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean 
     */
    private boolean isStopOrderSwitching(
        String l_strOrderCondition, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@W指値かどうかのチェック
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@ストップ注文が有効かどうかのチェック 
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (isストップ注文失効済)<BR>
     * W指値注文のストップ注文が失効済かどうか判別する。<BR> 
     * <BR>
     * this.isストップ注文失効済()に処理を委譲する。<BR> 
     * <BR>
     * [isストップ注文失効済()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。<BR> 
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR> 
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR> 
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return boolean  
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
     
    /**
     * (isストップ注文失効済)<BR>
     * W指値注文のストップ注文が失効済かどうか判別する。 <BR>
     * <BR>
     * this.isストップ注文失効済()に処理を委譲する。 <BR>
     * <BR>
     * [isストップ注文失効済()に指定する引数] <BR>
     * 　@発注条件：　@発注条件(*1) <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ <BR>
     * <BR>
     * (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する。<BR> 
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR> 
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR> 
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean  
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)発注条件は、先物OPデータアダプタ.get発注条件()により取得する
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    } 
     
    /**
     * (isストップ注文失効済)<BR>
     * W指値注文のストップ注文が失効済かどうか判別する。<BR> 
     * ※発注条件 == "W指値"でない場合は、 <BR>
     * 　@一律falseを返却する。 <BR>
     * <BR>
     * １）　@W指値かどうかのチェック <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、 <BR>
     * 　@falseを返却する。 <BR>
     * <BR>
     * ２）　@ストップ注文が失効済かどうかのチェック <BR>
     * 　@パラメータ.リクエストタイプ == "失効"の場合、<BR> 
     * 　@trueを返却する。 <BR>
     * 　@以外、falseを返却する。<BR> 
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean 
     */
    private boolean isStopOrderExpired(
        String l_strOrderCondition, String l_strRequestType) 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@W指値かどうかのチェック
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@ストップ注文が有効かどうかのチェック 
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (is手動発注可能)<BR>
     * 注文が手動発注可能であるかを判別する。<BR>
     * ※発注条件指定なしの場合は、一律falseを返却する。<BR>
     * <BR>
     * １）トリガー注文かどうかのチェック<BR>
     * <BR>
     * 　@パラメータ.注文単位.発注条件 == "DEFAULT(条件指定なし)"の場合、<BR>
     * 　@falseを返却する。<BR>
     * 　@(手動発注対象外)<BR>
     * <BR>
     * ２）逆指値注文の場合(パラメータ.注文単位.発注条件 == "逆指値")<BR>
     * <BR>
     * 　@【発注待ち／発注遅延】<BR>
     * 　@　@パラメータ.注文単位.リクエストタイプ == "DEFAULT"、かつ、<BR>
     * 　@　@パラメータ.注文単位.注文有効状態 == "オープン"、かつ、 <BR>
     * 　@　@取引時間管理.is立会時間帯() == trueの場合、trueを返却する。<BR>
     * 　@　@以外、falseを返却する。<BR>
     * <BR>
     * 　@※発注遅延（発注遅延無視でない会社）の場合も、上記状態に該当する。<BR>
     * 　@　@発注遅延無視の会社は発注遅延時には手動発注不可。 <BR>
     * 　@　@(発注済である為) <BR>
     * <BR>
     * ３）W指値注文の場合(パラメータ.注文単位.発注条件 == "W指値")<BR>
     * <BR>
     * 【切替遅延】 <BR>
     * 　@this.is未発注遅延注文(パラメータ.注文単位) == true、かつ、<BR>
     * 　@パラメータ.注文単位.注文有効状態 == "オープン"、かつ、 <BR>
     * 　@パラメータ.注文単位.注文状態 != <BR>
     * 　@　@（"受付済（取消注文）"、"発注中（取消注文）")、かつ、 <BR>
     * 　@　@取引時間管理.is立会時間帯() == trueの場合、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isManualOrderPossible(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isManualOrderPossible(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）トリガー注文かどうかのチェック
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
        String l_strRequestType = l_ifoOrderUnitRow.getRequestType();
        OrderOpenStatusEnum l_orderOpenStatus = l_ifoOrderUnitRow.getOrderOpenStatus();
        OrderStatusEnum l_orderStatus = l_ifoOrderUnitRow.getOrderStatus();

        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）逆指値注文の場合(パラメータ.注文単位.発注条件 == "逆指値")
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //   発注待ち/発注遅延
            //　@　@パラメータ.注文単位.リクエストタイプ == "DEFAULT"、かつ、
            //　@　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、
            //  取引時間管理.is立会時間帯() == trueの場合、trueを返却する。
            if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatus)
                && WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //３）W指値注文の場合(パラメータ.注文単位.発注条件 == "W指値")
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {

            //　@this.is未発注遅延注文(パラメータ.注文単位) == true、かつ、
            //　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、
            //　@パラメータ.注文単位.注文状態 != （"受付済（取消注文）"、"発注中（取消注文）")、かつ、
            //取引時間管理.is立会時間帯() == trueの場合、trueを返却する。
            if (this.isNotOrderedDelay(l_ifoOrderUnit)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatus)
                && !OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
                && !OrderStatusEnum.CANCELLING.equals(l_orderStatus)
                && WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //以外、fasleを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (update切替遅延)<BR>
     * （updateSwitchDelay） <BR>
     * 指定された注文IDに紐付く注文データ類を切替遅延のステータスに更新する。<BR>
     * <BR>
     * １）　@パラメータ.注文IDに紐付く注文単位を取得する。<BR>
     * <BR>
     * ２）　@注文状態チェック <BR>
     * 　@注文単位.リクエストタイプ == "DEFAULT" かつ<BR>
     * 　@（市場未送信(*)の場合、または、<BR>
     * 　@取得した注文単位.注文状態が以下のいずれかに該当する場合）、<BR>
     * 　@「受付中／訂正中／取消中の注文は切替処理不可」の例外をスローする。<BR>
     * 　@　@・"受付済（変更注文）" <BR>
     * 　@　@・"発注中（変更注文）" <BR>
     * 　@　@・"受付済（取消注文）" <BR>
     * 　@　@・"発注中（取消注文）" <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_02521<BR>
     * <BR>
     * 　@　@(*)注文単位.市場から確認済みの数量 == NaNの場合、<BR>
     * 　@　@　@　@市場未送信の注文と判定する。<BR>
     * <BR>
     * ３）　@１）の注文単位のcloneを作成する。  <BR>
     * <BR>
     * ４）　@３）にて作成したcloneに対し、更新値をセットする。  <BR>
     * 　@DB更新仕様  <BR>
     * 　@「W指値注文切替（切替遅延）_注文単位テーブル.xls」＃
     * 　@　@（W指値注文）[切替遅延]注文単位ﾃｰﾌﾞﾙ」参照。<BR>
     * <BR>
     * ５）　@注文データをupdateする。  <BR>
     * 　@this.update注文データ()をコールする。 <BR>
     * <BR>
     * 　@[update注文データ()に指定する引数]  <BR>
     * 　@　@注文単位：　@４）にて作成した注文単位  <BR>
     * 　@　@is履歴作成：　@false（作成しない） <BR>
     * <BR>
     * ６）　@４）にて作成した注文単位を使用し <BR>
     * 　@　@注文履歴テーブルへ１レコードinsertする。 <BR>
     * 　@　@更新内容は、DB更新仕様 <BR>
     * 　@　@「W指値注文切替（切替遅延）_注文履歴テーブル仕様.xls」参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateSwitchDelay(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchDelay(long)";
        log.entering(STR_METHOD_NAME);
         
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)this.getOrderUnits(l_lngOrderId)[0];
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        //２）　@注文状態チェック
        OrderStatusEnum l_orderStatus = l_ifoOrderUnit.getOrderStatus();

        if ((WEB3RequestTypeDef.DEFAULT.equals(l_ifoOrderUnitRow.getRequestType())
            && (l_ifoOrderUnitRow.getConfirmedQuantityIsNull()
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))))
        {
            log.debug("受付中／訂正中／取消中の注文は切替処理不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                getClass().getName() + STR_METHOD_NAME,
                "受付中／訂正中／取消中の注文は切替処理不可。");
        }

        //４）　@３）にて作成したcloneに対し、更新値をセットする。
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();

        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_ifoOrderUnitRow.getLastOrderActionSerialNo() + 1);
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);
        l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        IfoOrderUnit l_ifoOrderUnitClone =
            (IfoOrderUnit)this.toOrderUnit(l_ifoOrderUnitParams);

        //５）　@注文データをupdateする。
        this.updateOrderData(l_ifoOrderUnitClone, false);

        //６）　@４）にて作成した注文単位を使用し
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();

        //注文履歴ＩＤ
        long l_lngOrderActionId = 0;
        try
        {
            l_lngOrderActionId = IfoOrderActionDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        l_ifoOrderActionParams.setOrderActionId(l_lngOrderActionId);

        //口座ＩＤ
        l_ifoOrderActionParams.setAccountId(l_ifoOrderUnitParams.getAccountId());

        //補助口座ＩＤ
        l_ifoOrderActionParams.setSubAccountId(l_ifoOrderUnitParams.getSubAccountId());

        //注文ＩＤ
        l_ifoOrderActionParams.setOrderId(l_ifoOrderUnitParams.getOrderId());

        //注文単位ＩＤ
        l_ifoOrderActionParams.setOrderUnitId(l_ifoOrderUnitParams.getOrderUnitId());

        //市場ＩＤ
        if (!l_ifoOrderUnitParams.getMarketIdIsNull())
        {
            l_ifoOrderActionParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
        }

        //注文種別
        l_ifoOrderActionParams.setOrderType(l_ifoOrderUnitParams.getOrderType());

        //注文イベントタイプ
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.SWITCH_DELAY);

        //注文単価
        if (!l_ifoOrderUnitParams.getLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());
        }

        //執行条件
        l_ifoOrderActionParams.setExecutionConditionType(
            l_ifoOrderUnitParams.getExecutionConditionType());

        //発注条件
        l_ifoOrderActionParams.setOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //発注条件演算子
        l_ifoOrderActionParams.setOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //逆指値基準値タイプ
        l_ifoOrderActionParams.setStopPriceType(l_ifoOrderUnitParams.getStopPriceType());

        //逆指値基準値
        if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //（W指値）訂正指値
        if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
        }

        //注文失効日付
        l_ifoOrderActionParams.setExpirationDate(l_ifoOrderUnitParams.getExpirationDate());

        //注文数量
        l_ifoOrderActionParams.setQuantity(l_ifoOrderUnitParams.getQuantity());

        //市場と確認済みの指値
        if (!l_ifoOrderUnitParams.getConfirmedPriceIsNull())
        {
            l_ifoOrderActionParams.setConfirmedPrice(l_ifoOrderUnitParams.getConfirmedPrice());
        }

        //市場と確認済みの数量
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderActionParams.setConfirmedQuantity(
                l_ifoOrderUnitParams.getConfirmedQuantity());
        }

        //約定数量
        l_ifoOrderActionParams.setExecutedQuantity(null);

        //注文状態
        l_ifoOrderActionParams.setOrderStatus(l_ifoOrderUnitParams.getOrderStatus());

        //注文失効ステータス
        l_ifoOrderActionParams.setExpirationStatus(l_ifoOrderUnitParams.getExpirationStatus());

        //注文履歴番号
        l_ifoOrderActionParams.setOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo());

        //約定単価
        l_ifoOrderActionParams.setExecutedPrice(null);

        //銘柄タイプ
        l_ifoOrderActionParams.setProductType(l_ifoOrderUnitParams.getProductType());

        //銘柄ＩＤ
        l_ifoOrderActionParams.setProductId(l_ifoOrderUnitParams.getProductId());

        //概算受渡代金
        if (!l_ifoOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_ifoOrderActionParams.setEstimatedPrice(
                l_ifoOrderUnitParams.getEstimatedPrice());
        }

        //注文訂正・取消区分
        l_ifoOrderActionParams.setModifyCancelType(
            l_ifoOrderUnitParams.getModifyCancelType());

        //決済順序
        l_ifoOrderActionParams.setClosingOrder(l_ifoOrderUnitParams.getClosingOrder());

        //注文エラー理由コード
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //リクエストタイプ
        l_ifoOrderActionParams.setRequestType(l_ifoOrderUnitParams.getRequestType());

        //取引者ID
        if (!l_ifoOrderUnitParams.getTraderIdIsNull())
        {
            l_ifoOrderActionParams.setTraderId(l_ifoOrderUnitParams.getTraderId());
        }

        //注文経路区分
        l_ifoOrderActionParams.setOrderRootDiv(l_ifoOrderUnitParams.getOrderRootDiv());

        //作成日付
        l_ifoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日付
        l_ifoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


        //元発注条件
        l_ifoOrderActionParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrgOrderConditionType());

        //元発注条件演算子
        l_ifoOrderActionParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrgOrderCondOperator());

        //元逆指値基準値タイプ
        l_ifoOrderActionParams.setOrgStopPriceType(l_ifoOrderUnitParams.getOrgStopPriceType());

        //元逆指値基準値
        if (!l_ifoOrderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getOrgStopOrderPrice());
        }

        //元（W指値）訂正指値
        if (!l_ifoOrderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getOrgWLimitPrice());
        }

        //元（W指値）執行条件
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_ifoOrderUnitParams.getOrgWLimitExecCondType());

        //（W指値）執行条件
        l_ifoOrderActionParams.setWLimitExecCondType(l_ifoOrderUnitParams.getWLimitExecCondType());

        //（W指値）切替前指値
        if (!l_ifoOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitParams.getWLimitBeforeLimitPrice());
        }

        //（W指値）切替前執行条件
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitParams.getWLimitBeforeExecCondType());

        //市場から確認済みの執行条件
        l_ifoOrderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitParams.getConfirmedExecConditionType());

        //注文期限区分
        l_ifoOrderActionParams.setExpirationDateType(
            l_ifoOrderUnitParams.getExpirationDateType());
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_ifoOrderActionParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (isSONAR取消)<BR>
     * SONARからの取消通知かどうか判別する。<BR> 
     * <BR>
     * SONARからの取消通知の場合はtrueを、 <BR>
     * 以外、falseを返却する。 <BR>
     * <BR>
     * パラメータ.注文単位.注文状態が以下のいずれかに該当する <BR>
     * 場合、falseを返却する。 <BR>
     * 　@・"受付済（取消注文）" <BR>
     * 　@・"発注中（取消注文）" <BR>
     * <BR>
     * 上記以外、trueを返却する。 <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSONARCancel(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSONARCancel(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
         
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
         
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
         
        //"受付済（取消注文）"  "発注中（取消注文）"
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_ifoOrderUnitRow.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_ifoOrderUnitRow.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
         
        //上記以外、trueを返却する
        log.exiting(STR_METHOD_NAME);
        return true;
    }
     
    /**
     * Commissionのクローン
     * @@param l_commission
     * @@return WEB3GentradeCommission
     */
    private WEB3GentradeCommission copyCommission(WEB3GentradeCommission l_commission)
    {
        WEB3GentradeCommission l_cloneCommission = new WEB3GentradeCommission();
        l_cloneCommission.setBranchId(l_commission.getBranchId());
        l_cloneCommission.setCommission(l_commission.getCommission());
        l_cloneCommission.setCommissionCourseDiv(l_commission.getCommissionCourseDiv());
        l_cloneCommission.setCommissionNo(l_commission.getCommissionNo());
        l_cloneCommission.setCommissionProductCode(l_commission.getCommissionProductCode());
        l_cloneCommission.setCommissionRevNo(l_commission.getCommissionRevNo());
        l_cloneCommission.setCommitionPerUnit(l_commission.getCommitionPerUnit());
        l_cloneCommission.setDayTradeType(l_commission.getDayTradeType());
        l_cloneCommission.setExpensesCalcAmount(l_commission.getExpensesCalcAmount());
        l_cloneCommission.setInstitutionCode(l_commission.getInstitutionCode());
        l_cloneCommission.setIsLimitPrice(l_commission.isLimitPrice());
        l_cloneCommission.setMinCommission(l_commission.getMinCommission());
        l_cloneCommission.setOrderBizDate(l_commission.getOrderBizDate());
        l_cloneCommission.setOrderChannel(l_commission.getOrderChannel());
        l_cloneCommission.setOrgCommissionNo(l_commission.getOrgCommissionNo());
        l_cloneCommission.setOrgCommissionRevNo(l_commission.getOrgCommissionRevNo());
        l_cloneCommission.setOrgOrderChannel(l_commission.getOrgOrderChannel());
        l_cloneCommission.setPayType(l_commission.getPayType());
        l_cloneCommission.setQuantity(l_commission.getQuantity());
        l_cloneCommission.setSonarMarketCode(l_commission.getSonarMarketCode());
        l_cloneCommission.setSonarTradedCode(l_commission.getSonarTradedCode());
        l_cloneCommission.setUnderlyingProductCode(l_cloneCommission.getUnderlyingProductCode());
     
        return l_cloneCommission;        
    }

	/**
	 * (is出来るまで注文単位) <BR>
	 * (is出来るまで注文単位(注文単位)のオーバーロードメソッド) <BR>
	 * <BR>
	 * 「出来るまで注文」の注文かどうかを判定する。<BR>
	 * 「出来るまで注文」の場合はtrueを、「出来るまで注文」ではない場合はfalseを、<BR>
	 * それぞれ返却する。 <BR>
	 * <BR>
	 * １）　@this.getOrderUnit(引数.注文単位ID)で、注文単位オブジェクトを取得する。<BR>
	 * <BR>
	 * ２）　@this.is出来るまで注文単位(注文単位)にdelegateする。<BR>
	 * <BR>   
	 * @@param l_lngOrderUnitId - (注文単位ID) <BR>
	 * 注文単位オブジェクト.注文単位ID。<BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isCarriedOrderUnit(long l_lngOrderUnitId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCarriedOrderUnit(long l_lngOrderUnitId)";
		log.entering(STR_METHOD_NAME);
        
		IfoOrderUnit l_orderUnit;
		try
		{
			l_orderUnit = (IfoOrderUnit)getOrderUnit(l_lngOrderUnitId);
		}
		catch (NotFoundException nfe)
		{
			log.error(STR_METHOD_NAME,nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				nfe.getMessage(),
				nfe);
		}
        
		log.exiting(STR_METHOD_NAME);
        
		return this.isCarriedOrderUnit(l_orderUnit);
	}

	/**
	 * (is出来るまで注文単位) <BR>
	 * <BR>
	 * 「出来るまで注文」の注文かどうかを判定する。<BR>
	 * 「出来るまで注文」の場合はtrueを、「出来るまで注文」ではない場合はfalseを、<BR>
	 * それぞれ返却する。<BR>
	 * <BR>
	 * １）　@引数の注文単位.初回注文の注文単位ID≠null　@かつ、<BR>
     * 　@　@　@OP注文マネージャ.is夕場まで注文(引数の注文単位)＝falseの場合は、trueを返す。<BR>
     * <BR>
	 * ２）　@引数の注文単位.初回注文の注文単位ID＝nullの場合は、falseを返す。<BR>
	 * <BR>
	 * @@param l_ifoOrderUnit - (注文単位オブジェクト) <BR>
	 * 注文単位オブジェクト。 <BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isCarriedOrderUnit(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCarriedOrderUnit(IfoOrderUnit l_ifoOrderUnit)";
		log.entering(STR_METHOD_NAME);
        
		IfoOrderUnitRow l_orderUnitRow = 
			(IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
		boolean l_blnResult = false;
		if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
		{
			l_blnResult = false;
		}
		else if (!l_orderUnitRow.getFirstOrderUnitIdIsNull()
            && !this.isEveningSessionOrder(l_ifoOrderUnit))
		{
			l_blnResult = true;
		}
        
		log.exiting(STR_METHOD_NAME);

		return l_blnResult;
	}

    /**
     * (is遅延注文)<BR>
     * 遅延注文かどうか判別する。<BR>
     * <BR>
     * true：　@発注遅延あり<BR>
     * false：　@発注遅延なし<BR>
     * <BR>
     * パラメータ.注文単位.発注遅延フラグ == "遅延あり"の場合、<BR>
     * trueを返却する。以外、falseを返却する。　@<BR>
     * @@param l_ifoOrderUnit - (注文単位) <BR>
     * @@return boolean
     */
    public boolean isDelayOrder(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "isDelayOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        if (BooleanEnum.TRUE.equals(l_ifoOrderUnitRow.getSubmitOrderDelayFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is未発注遅延注文)<BR>
     * 未発注（逆指値発注処理／W指値切替処理未済）の<BR>
     * 発注遅延注文かどうか判別する。<BR>
     * <BR>
     * true：　@未発注の発注遅延注文<BR>
     * false：　@未発注の発注遅延注文以外<BR>
     * <BR>
     * パラメータ.注文単位.リクエストタイプ == "DEFAULT"　@かつ<BR>
     * OP注文マネージャ.is遅延注文(パラメータ.注文単位) == true<BR>
     * の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@param l_ifoOrderUnit - (注文単位) <BR>
     * @@return boolean
     */
    public boolean isNotOrderedDelay(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "isNotOrderedDelay(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        if (WEB3RequestTypeDef.DEFAULT.equals(l_ifoOrderUnitRow.getRequestType())
            && this.isDelayOrder(l_ifoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (getストップ注文失効時概算代金計算結果)<BR>
     * ストップ注文失効時の概算受渡代金を再計算する。 <BR>
     * （リミット注文の注文単価による計算を行う） <BR>
     * <BR>
     * １）　@パラメータ.注文単位.注文種別 != "OP新規買建注文"の場合、<BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * ２）　@上記以外、以下の処理を行う。 <BR>
     * 　@２－１）　@先物OP計算サービス.create手数料()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[create手数料()に指定する引数] <BR>
     * 　@　@　@注文単位ID：　@パラメータ.注文単位.注文単位ID <BR>
     * 　@　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * <BR>
     * 　@２－２）　@注文単位Rowを取得する。 <BR>
     * 　@　@パラメータ.注文単位.getDataSourceObject()をコールする。 <BR>
     * <BR>
     * 　@２－３）　@手数料.setIs指値()メソッドをコールする <BR>
     * <BR>
     * 　@　@[setIs指値()に指定する引数] <BR>
     * 　@　@　@is指値： <BR>
     * 　@　@　@　@[注文単位Row.市場から確認済みの指値 == nullの場合] <BR>
     * 　@　@　@　@　@注文単位.isMarketOrder()の戻り値 <BR>
     * 　@　@　@　@[注文単位Row.市場から確認済みの指値 == 0の場合] <BR>
     * 　@　@　@　@　@false（成行） <BR>
     * 　@　@　@　@[上記以外] <BR>
     * 　@　@　@　@　@true（指値） <BR>
     * <BR>
     * 　@２－４）　@this.calc訂正時概算受渡代金()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[calc訂正時概算受渡代金()に指定する引数] <BR>
     * 　@　@　@手数料：　@２－１）にて作成した手数料オブジェクト <BR>
     * 　@　@　@指値： <BR>
     * 　@　@　@　@[注文単位Row.市場から確認済みの指値 == nullの場合] <BR>
     * 　@　@　@　@　@注文単位Row.指値 <BR>
     * 　@　@　@　@[上記以外] <BR>
     * 　@　@　@　@　@注文単位Row.市場から確認済みの指値 <BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * 　@　@　@先物OP取引銘柄：　@注文単位.getTradedProduct() <BR>
     * 　@　@　@数量：　@注文単位Row.注文数量 <BR>
     * 　@　@　@売買：　@注文単位.getSide() <BR>
     * 　@　@　@is返済注文：　@false（固定） <BR>
     * 　@　@　@約定数量：　@注文単位Row.約定数量 <BR>
     * 　@　@　@合計約定金額：　@注文単位Row.合計約定金額 <BR>
     * 　@　@　@isSkip金額チェック：　@true（固定） <BR>
     * <BR>
     * 　@２－５）　@２－４）の戻り値を返却する。 <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException 
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult getStopOrderExpireEstimatedPrice(
        IfoOrderUnit l_ifoOrderUnit, WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopOrderExpireEstimatedPrice(IfoOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //１）　@パラメータ.注文単位.注文種別 != "OP新規買建注文"の場合、
        //nullを返却する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IfoBizLogicProvider l_logicProvider =
            (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult;

        if (!OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnit.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //２）　@上記以外、以下の処理を行う。
        //２－１）　@先物OP計算サービス.create手数料()メソッドをコールする。
        else
        {
            WEB3GentradeCommission l_genCommission =
                l_logicProvider.createCommission(
                    l_ifoOrderUnit.getOrderUnitId(),
                    l_ifoOrderUnit.getQuantity());

            //２－２）　@注文単位Rowを取得する。
            //パラメータ.注文単位.getDataSourceObject()をコールする。
            IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //　@２－３）　@手数料.setIs指値()メソッドをコールする
            boolean l_blnIsLimitPrice = true;
            //[注文単位Row.市場から確認済みの指値 == nullの場合]
            // 注文単位.isMarketOrder()の戻り値
            if (l_ifoRow.getConfirmedPriceIsNull())
            {
                l_blnIsLimitPrice = l_ifoOrderUnit.isMarketOrder();
            }
            else if (l_ifoRow.getConfirmedPrice() == 0)
            {
                l_blnIsLimitPrice = false;
            }
            l_genCommission.setIsLimitPrice(l_blnIsLimitPrice);

            //２－４）　@this.calc訂正時概算受渡代金()メソッドをコールする。
            //　@　@[calc訂正時概算受渡代金()に指定する引数]
            //　@　@　@手数料：　@２－１）にて作成した手数料オブジェクト
            //　@　@　@指値：
            //　@　@　@　@[注文単位Row.市場から確認済みの指値 == nullの場合]
            //　@　@　@　@　@注文単位Row.指値
            //　@　@　@　@[上記以外]
            //　@　@　@　@　@注文単位Row.市場から確認済みの指値
            //　@　@　@補助口座：　@パラメータ.補助口座
            //　@　@　@先物OP取引銘柄：　@注文単位.getTradedProduct()
            //　@　@　@数量：　@注文単位Row.注文数量
            //　@　@　@売買：　@注文単位.getSide()
            //　@　@　@is返済注文：　@false（固定）
            //　@　@　@約定数量：　@注文単位Row.約定数量
            //　@　@　@合計約定金額：　@注文単位Row.合計約定金額
            //　@　@　@isSkip金額チェック：　@true（固定）
            //２－５）　@２－４）の戻り値を返却する。
            double l_dblLimitPrice = 0.0D;
            if (l_ifoRow.getConfirmedPriceIsNull())
            {
                l_dblLimitPrice = l_ifoRow.getLimitPrice();
            }
            else
            {
                l_dblLimitPrice = l_ifoRow.getConfirmedPrice();
            }

            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct =
                (WEB3IfoTradedProductImpl)l_ifoOrderUnit.getTradedProduct();

            l_estimateDeliveryAmountCalcResult =
                this.calcChangeEstimateDeliveryAmount(
                    l_genCommission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    l_ifoRow.getQuantity(),
                    l_ifoOrderUnit.getSide(),
                    false,
                    l_ifoRow.getExecutedQuantity(),
                    l_ifoRow.getExecutedAmount(),
                    true);
        }

        log.exiting(STR_METHOD_NAME);
        return l_estimateDeliveryAmountCalcResult;
    }

    /**
     * (is日計り)<BR>
     * 建玉が日計りであるか判定する。<BR>
     * <BR>
     * １） 引数の返済建玉エントリ[0].IDから建玉オブジェクトを生成する(*) <BR>
     * <BR>
     * ２） １）で取得した建玉から、建玉.getTradedProduct()にて先物OP取引銘柄を取得する。 <BR>
     * <BR>
     * ３） ２）で取得した先物OP取引銘柄から、先物OP取引銘柄.get受渡日()にて受渡日を取得する。 <BR>
     * <BR>
     * ４） 取引時間管理.get発注日()から発注日を取得する。 <BR>
     * <BR>
     * ５） 建玉が日計りであるかの判定は、以下のとおりとする。 <BR>
     * <BR>
     * 　@　@［建玉.getOpenDate() == ４）で取得した発注日 and 建玉.getDeliveryDate() == ３）で取得した受渡日］<BR>
     * <BR>
     * 　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * 　@　@［上記以外］<BR>
     * <BR>
     * 　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * (*) 引数.返済建玉エントリは、決済順位（昇順）でソートされていることを前提条件とする。<BR>
     * @@param l_settleContractEntrys - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDayTrade(SettleContractEntry[] l_settleContractEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTrade(SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_settleContractEntrys == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数の返済建玉エントリ[0].IDから建玉オブジェクトを生成する(*)
        long l_lngContractId = l_settleContractEntrys[0].getContractId();

        try
        {
            WEB3IfoContractImpl l_ifoContractImpl =
                new WEB3IfoContractImpl(l_lngContractId);

            //２）１）で取得した建玉から、建玉.getTradedProduct()にて先物OP取引銘柄を取得する。
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();

            //３）２）で取得した先物OP取引銘柄から、先物OP取引銘柄.get受渡日()にて受渡日を取得する。
            Date l_datDeliveryDate = l_ifoTradedProduct.getDailyDeliveryDate();

            //４） 取引時間管理.get発注日()から発注日を取得する。
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //［建玉.getOpenDate() == ４）で取得した発注日 and 建玉.getDeliveryDate() == ３）で取得した受渡日］
            //trueを返却する。
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContractImpl.getDataSourceObject();
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_ifoContractImpl.getOpenDate()) == 0
                && WEB3DateUtility.compareToDay(l_datDeliveryDate, l_ifoContractRow.getDeliveryDate()) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get日計り区分)<BR>
     * 建玉が日計りであれば、「日計り区分：日計り」を返却する(*1) <BR>
     * <BR>
     * 　@this.is日計り()をコールする(*2) <BR>
     * <BR>
     * 　@［this.is日計り() == trueの場合］<BR>
     * <BR>
     * 　@　@「日計り」を返却する。 <BR>
     * <BR>
     * 　@［this.is日計り() == falseの場合］<BR>
     * <BR>
     * 　@　@nullを返却する。 <BR>
     * <BR>
     * (*1) 日計り区分は、「定数定義インタフェイス：WEB3ContractCheckDef」を参照<BR>
     * (*2) 引数は、当メソッドの引数.返済建玉エントリ <BR>
     * @@param l_settleContractEntrys - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public String getDayTradeType(SettleContractEntry[] l_settleContractEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayTradeType(SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        //［this.is日計り() == trueの場合］
        //「日計り」を返却する。
        if (this.isDayTrade(l_settleContractEntrys))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ContractCheckDef.DAY_TRADE;
        }

        //this.is日計り() == falseの場合］
        //nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (is夕場まで注文)<BR>
     * 「夕場まで注文」の注文かどうかを判定する。<BR>
     * 「夕場まで注文」の場合はtrueを、「夕場まで注文」ではない場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * １）　@OP注文マネージャ.get初回注文の注文単位()をコールする。<BR>
     * <BR>
     * 　@[get初回注文の注文単位()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ２）　@夕場まで注文かどうか判定する。 <BR>
     * 　@１）にて取得した注文単位.初回注文の注文単位ID == null かつ <BR>
     * 　@　@１）にて取得した注文単位.夕場前繰越対象フラグ == "夕場前繰越あり"の場合、<BR>
     * 　@true（夕場まで注文）を返却する。 <BR>
     * 　@以外、false（夕場まで注文でない）を返却する。<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isEveningSessionOrder(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEveningSessionOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@OP注文マネージャ.get初回注文の注文単位()をコールする。
        //   　@[get初回注文の注文単位()に指定する引数]
        //   　@　@注文単位：　@パラメータ.注文単位
        IfoOrderUnitRow l_firstOrderUnitRow =
            (IfoOrderUnitRow)this.getFirstOrderUnit(l_ifoOrderUnit).getDataSourceObject();

        //２）　@夕場まで注文かどうか判定する。
        //１）にて取得した注文単位.初回注文の注文単位ID == null かつ
        //１）にて取得した注文単位.夕場前繰越対象フラグ == "夕場前繰越あり"の場合、
        //true（夕場まで注文）を返却する。

        if (l_firstOrderUnitRow.getFirstOrderUnitIdIsNull()
            && BooleanEnum.TRUE.equals(l_firstOrderUnitRow.getEveningSessionCarryoverFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //以外、false（夕場まで注文でない）を返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is繰越対象注文)<BR>
     * 繰越対象注文であるか判定する。 <BR>
     * １）取引時間管理.get立会区分()をコールする。 <BR>
     * <BR>
     * 　@　@[get立会区分()に指定する引数]<BR>
     * 　@　@引数無し <BR>
     * <BR>
     * ２）夕場前注文繰越（get立会区分() == ”夕場”）の場合、<BR> 
     * 　@　@日中登録した当日限り注文（*1）は繰越対象外注文と判断するため、 <BR>
     * 　@　@falseを返却する。 <BR>
     * <BR>
     * 　@（*1）日中登録した当日限り注文 <BR>
     * 　@　@　@先物データアダプタ.get注文期限区分() == ”当日限り”かつ、 <BR>
     * 　@　@　@引数.注文単位.立会区分 == null <BR>
     * <BR>
     * 　@　@　@[get注文期限区分()に設定する引数] <BR>
     * 　@　@　@注文単位：　@引数.注文単位 <BR>
     * <BR>
     * ３）注文繰越（get立会区分() != ”夕場”）の場合、 <BR>
     * 　@　@引数.注文単位.注文失効日 <= 業務日付（*2） に該当する注文は、 <BR>
     * 　@　@繰越対象外注文と判断するため、 false を返却する。<BR> 
     * <BR>
     * 　@（*2） 業務日付は、GtlUtils.getTradingSystem().getBizDate()で取得。 <BR>
     * <BR>
     * ４）上記の条件に該当しない場合、true（=繰越対象注文）を返却する。<BR> 
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isCarryoverOrder(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarryoverOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //取引時間管理.get立会区分()をコールする
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();

        //注文期限区分
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);

        //注文単位
        IfoOrderUnitRow l_firstOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        //注文単位.注文失効日
        //(業務日付は GtlUtils.getTradingSystem().getBizDate()で取得)
        Timestamp l_tsExpirationTimestamp = l_ifoOrderUnit.getExpirationTimestamp();
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //夕場前注文繰越（get立会区分() == ”夕場”）の場合
        if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
        {
            //先物データアダプタ.get注文期限区分() == ”当日限り”かつ
            //引数.注文単位.立会区分 == null
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType)
                && l_firstOrderUnitRow.getSessionType() == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //注文繰越（get立会区分() != ”夕場”）の場合
        else
        {
            //引数.注文単位.注文失効日 <= 業務日付
            if (WEB3DateUtility.compareToDay(l_tsExpirationTimestamp, l_datBizDate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is日計り対象)<BR>
     * 建玉が日計り対象であるか判定する。 <BR>
     * 　@・true（日計り返済である）<BR>
     * 　@・false（日計り返済でない）<BR>
     * <BR>
     * １）パラメータ.建日 == 発注日（取引時間管理.get発注日()）<BR>
     * 　@　@かつ、立会区分 == 取引時間管理.get立会区分()の場合、 <BR>
     * 　@　@trueを返却する。<BR>
     * <BR>
     * ２）１）の条件に該当しない場合、<BR> 
     * 　@　@falseを返却する。 <BR>
     * <BR> 
     * @@param l_contractOpenDate - (建日)<BR>
     * @@param l_contractSessionType - (立会区分)<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDayTrade(Date l_contractOpenDate, String l_contractSessionType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTrade(Date l_contractOpenDate, String l_contractSessionType)";
        log.entering(STR_METHOD_NAME);

        if (WEB3DateUtility.compareToDay(l_contractOpenDate,
            WEB3GentradeTradingTimeManagement.getOrderBizDate()) == 0
            && WEB3Toolkit.isEquals(l_contractSessionType,
            WEB3GentradeTradingTimeManagement.getSessionType()))
        {
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get注文有効期限)<BR>
     * 注文有効期限最終日のみ指定可会社の場合、<BR>
     * パラメータ.注文有効期限と先物OP取引銘柄.売買最終日を比較して<BR>
     * 小さい方の値を返却する。<BR>
     * （注文有効期限最終日より売買最終日が小さい場合、<BR>
     * 売買最終日を注文有効期限最終日として使用する為）<BR>
     * <BR>
     * １）　@パラメータ.注文有効期限がnullの場合、<BR>
     * 　@　@以下２）～４）の処理は行わず、nullを返却する。<BR>
     * <BR>
     * ２）　@先物OP取引銘柄オブジェクトを生成する。<BR>
     * <BR>
     * 　@[get取引銘柄()に設定する引数]<BR>
     * 　@証券会社：  ログインセッションより取得した証券会社<BR>
     * 　@銘柄コード： パラメータ.銘柄コード<BR>
     * 　@市場コード： パラメータ.市場コード<BR>
     * <BR>
     * ３）　@取扱可能注文条件オブジェクトを生成する。<BR>
     * 　@[コンストラクタに設定する引数]<BR>
     * 　@証券会社コード：        ログインセッションより取得した証券会社コード<BR>
     * 　@銘柄タイプ：              ”先物オプション”<BR>
     * 　@先物／オプション区分： パラメータ.先物／オプション<BR>
     * 　@信用取引区分：        ”DEFAULT”<BR>
     * <BR>
     * ４）　@注文有効期限を返却する。<BR>
     * <BR>
     * 　@４－１）　@取扱可能注文条件.get出来るまで注文失効日指定()の戻り値が<BR>
     * 　@　@　@　@　@　@『最終日のみ指定可』の場合<BR>
     * <BR>
     * 　@　@４－１－１）　@先物OP取引銘柄.売買最終日とパラメータ.注文有効期限を<BR>
     * 　@　@　@　@　@　@　@　@　@比較して小さい方の値を返却する。<BR>
     * <BR>
     * 　@　@　@４－１－１－１）　@先物OP取引銘柄.売買最終日＜＝パラメータ.注文有効期限の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@先物OP取引銘柄.売買最終日を返却する。<BR>
     * <BR>
     * 　@　@　@４－１－１－２）　@先物OP取引銘柄.売買最終日＞パラメータ.注文有効期限の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@パラメータ.注文有効期限を返却する。<BR>
     * <BR>
     * 　@４－２）　@上記以外の場合、パラメータ.注文有効期限を返却する。<BR>
     * <BR>
     * @@param l_datExpirationDate - 注文有効期限<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@param l_strFutureOptionDiv - 先物／オプション<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate(
        Date l_datExpirationDate,
        String l_strProductCode,
        String l_strMarketCode,
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate(Date, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.注文有効期限がnullの場合
        //nullを返却する
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        //先物OP取引銘柄オブジェクトを生成する。
        //[get取引銘柄()に設定する引数]
        //証券会社：  ログインセッションより取得した証券会社
        //銘柄コード： パラメータ.銘柄コード
        //市場コード： パラメータ.市場コード
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        //口座マネージャを取得する
        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        long l_lngAccountId = l_opLoginSec.getAccountId();

        WEB3GentradeHandlingOrderCond l_handlingOrderCond = null;
        //売買最終日
        Date l_datLastTrading = null;
        try
        {
            //口座を取得する
            MainAccount l_mainAccount = l_accountMananger.getMainAccount(l_lngAccountId);
            //証券会社
            Institution l_institution = l_mainAccount.getInstitution();

            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                l_ifoProductManagerImpl.getIfoTradedProduct(l_institution, l_strProductCode, l_strMarketCode);

            //先物OP取引銘柄.売買最終日
            IfoTradedProductRow l_ifoTradedProductRow =
                (IfoTradedProductRow)l_ifoTradedProductImpl.getDataSourceObject();
            l_datLastTrading = l_ifoTradedProductRow.getLastTradingDate();

            //取扱可能注文条件オブジェクトを生成する。
            l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_institution.getInstitutionCode(), //証券会社コード
                ProductTypeEnum.IFO, //”先物オプション”
                l_strFutureOptionDiv,//パラメータ.先物／オプション
                WEB3MarginTradingDivDef.DEFAULT); //信用取引区分
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //取扱可能注文条件.get出来るまで注文失効日指定()
        String l_strCarriedOrderLapseDateSpec = l_handlingOrderCond.getOrderUntilDeadLineExpDay();

        //『最終日のみ指定可』の場合
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
        {
            //先物OP取引銘柄.売買最終日＜＝パラメータ.注文有効期限の場合、
            if (WEB3DateUtility.compareToDay(l_datLastTrading, l_datExpirationDate) <= 0)
            {
                //先物OP取引銘柄.売買最終日を返却する。
                return l_datLastTrading;
            }
            //先物OP取引銘柄.売買最終日＞パラメータ.注文有効期限の場合、
            else
            {
                //パラメータ.注文有効期限を返却する。
                return l_datExpirationDate;
            }
        }

        //以外の場合、パラメータ.注文有効期限を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_datExpirationDate;
    }

    /**
     * (notify親注文全部約定)<BR>
     * 引数で指定された注文が全部約定しており、かつ、<BR>
     * 有効な予約注文が設定されている親注文である場合に、<BR>
     * ルールエンジンサーバに親注文の全部約定を通知する。<BR>
     * ※全部約定かどうかは呼び出し元で判別すること。<BR>
     * <BR>
     * １）　@予約注文有無の判定 <BR>
     * 引数で指定された注文に有効な予約注文が設定されているか <BR>
     *（＝親注文であるか）を判定する。<BR>
     * <BR>
     * 　@１－１）　@予約注文確認要否の判定<BR>
     * 　@　@this.is予約注文確認要()をコールする。<BR>
     * <BR>
     * 　@　@[引数の設定]<BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * <BR>
     * 戻り値がfalseの場合、予約注文が設定されていないため処理を終了する（return)<BR>
     * ２）　@全部約定かどうかの判定<BR>
     * 　@引数の注文単位.isFullyExecuted()をコールする。<BR>
     * <BR>
     * 　@戻り値がfalseの場合、全部約定でないため処理を終了する（return)<BR>
     * ３）　@有効予約注文の確認 <BR>
     * 　@有効な予約注文の一覧を取得する。<BR>
     * <BR>
     * 先物OP予約注文更新サービスImpl.get有効予約注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@[引数の設定]親注文の注文ID：　@引数.注文単位.注文ID <BR>
     * <BR>
     * 　@戻り値がnullの場合、有効な予約注文が存在しないため処理を終了する(return) <BR>
     * <BR>
     * ４）　@全部約定の通知<BR>
     * ルールエンジンサーバに親注文の全部約定を通知する。<BR>
     * <BR>
     * WEB3RlsRequestSenderService.sendConOrderExecuteMessage()をコールする。<BR>
     * <BR>
     * [引数の設定]<BR>
     * 補助口座：　@引数.注文単位.口座ID、補助口座IDに該当する補助口座<BR>
     * 親注文の注文ID：　@引数.注文単位.注文ID<BR>
     * 銘柄タイプ：　@"先物オプション"<BR>
     * 親注文の識別コード：　@引数.注文単位.識別コード<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    private void notifyParentOrderFullyExecuted(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyParentOrderFullyExecuted(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // １）　@予約注文有無の判定
        // １－１）　@予約注文確認要否の判定
        // 戻り値がfalseの場合、予約注文が設定されていないため処理を終了する（return)
        if (!this.isReserveOrderExist(l_ifoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // ２）　@全部約定かどうかの判定
        // 引数の注文単位.isFullyExecuted()をコールする。
        // 戻り値がfalseの場合、全部約定でないため処理を終了する（return)
        if (!l_ifoOrderUnit.isFullyExecuted())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // ３）　@有効予約注文の確認
        // 有効な予約注文の一覧を取得する。
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        List l_lisOpenReserveIfoOrderUnits = l_orderUpdateService.getOpenReserveIfoOrderUnits(
            l_ifoOrderUnit.getOrderId());
        // 戻り値がnullの場合、有効な予約注文が存在しないため処理を終了する(return)
        if (l_lisOpenReserveIfoOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // ４）　@全部約定の通知
        // ルールエンジンサーバに親注文の全部約定を通知する。
        // WEB3RlsRequestSenderService.sendConOrderExecuteMessage()をコールする。
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(
                WEB3RlsRequestSenderService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accMgr.getSubAccount(l_ifoOrderUnit.getAccountId(), l_ifoOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // [引数の設定]
        // 補助口座：　@引数.注文単位.口座ID、補助口座IDに該当する補助口座
        // 親注文の注文ID：　@引数.注文単位.注文ID
        // 銘柄タイプ：　@"先物オプション"
        // 親注文の識別コード：　@引数.注文単位.識別コード
        l_rlsRequestSenderService.sendConOrderExecuteMessage(
            l_subAccount,
            new Long(l_ifoOrderUnit.getOrderId()),
            ProductTypeEnum.IFO,
            l_ifoOrderUnitRow.getOrderRequestNumber());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify連続注文)<BR>
     * 連続注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@予約注文単位かどうかの判定<BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。<BR>
     * メソッドの戻り値の型が、<BR>
     * 先物OP予約注文単位Rowでない場合、<BR>
     * 処理対象外である為、処理を終了する。（return）<BR>
     * <BR>
     * ２）　@補助口座を取得する。<BR>
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。<BR>
     * 　@[引数の設定]<BR>
     * 　@１）の戻り値.口座ID<BR>
     * 　@１）の戻り値.補助口座ID<BR>
     * <BR>
     * ３）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）を取得する。<BR>
     * <BR>
     * ４）ルールエンジンサーバに通知を行う。<BR>
     * [新規登録（１）の戻り値.注文状態 == "受付済（新規注文）"）の場合]<BR>
     * 　@３）で取得したサービス.sendRegisterConOrdersMessage()メソッドをコールする。<BR>
     * 　@[引数の設定]<BR>
     * 　@　@補助口座：　@２）で取得した補助口座<BR>
     * 　@　@条件付注文タイプ：　@"連続注文"<BR>
     * 　@　@親注文の銘柄タイプ：　@"先物オプション"<BR>
     * 　@　@親注文の注文ID：　@１）の戻り値.親注文の注文ID<BR>
     * 　@　@子注文の銘柄タイプ一覧：　@１）の戻り値.銘柄タイプのみを要素とする配列<BR>
     * 　@　@子注文の注文ID一覧：　@１）の戻り値.注文IDのみを要素とする配列<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    private void notifyToSuccOrder(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyToSuccOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // １）　@予約注文単位かどうかの判定
        RsvIfoOrderUnitRow l_rsvOrderUnitRow = null;
        if (!(l_ifoOrderUnit.getDataSourceObject() instanceof RsvIfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;    
        }
        l_rsvOrderUnitRow = (RsvIfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject();

        // ２）　@補助口座を取得する。
       //　@拡張アカウントマネージャ.getSubAccount()をコールする。
       //　@[引数の設定]
       //　@　@arg0：　@１）の戻り値.口座ID
       //　@　@arg1：　@１）の戻り値.補助口座ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accMgr.getSubAccount(
                l_rsvOrderUnitRow.getAccountId(), l_rsvOrderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）を取得する。
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(
                WEB3RlsRequestSenderService.class);

        // 注文状態 == "受付済（新規注文）"）の場合
        if (OrderStatusEnum.ACCEPTED.equals(l_rsvOrderUnitRow.getOrderStatus()))
        {
            //[引数の設定]
            //　@補助口座：　@２）で取得した補助口座
            //　@条件付注文タイプ：　@"連続注文"
            //　@親注文の銘柄タイプ：　@"先物オプション"
            //　@親注文の注文ID：　@１）の戻り値.親注文の注文ID
            //　@子注文の銘柄タイプ一覧：　@１）の戻り値.銘柄タイプのみを要素とする配列
            //　@子注文の注文ID一覧：　@１）の戻り値.注文IDのみを要素とする配列
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.EXECUTE,
                ProductTypeEnum.IFO,
                new Long(l_rsvOrderUnitRow.getParentOrderId()),
                new ProductTypeEnum[]{l_rsvOrderUnitRow.getProductType()},
                new Long[]{new Long(l_rsvOrderUnitRow.getOrderId())});
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is予約注文確認要)<BR>
     * 引数で指定された注文に予約注文が設定されている可能性があるか <BR>
     *（＝親注文の可能性があるか）を判定する。<BR>
     * <BR>
     * 予約注文が設定されている可能性がある場合trueを、<BR>
     * ない場合falseを、それぞれ返却する。<BR>
     * <BR>
     * １）　@予約注文確認要否の判定 <BR>
     * 引数の注文単位.予約注文設定フラグ ≠ "1：設定の可能性あり"の場合は、<BR>
     * 予約注文の設定なしのため、falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isReserveOrderExist(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isReserveOrderExist(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        // 引数の注文単位.予約注文設定フラグ ≠ "1：設定の可能性あり"の場合
        // 予約注文の設定なしのため、falseを返却する。
        if (!WEB3ReserveOrderExistFlagDef.SET_POSSIBLE.equals(l_ifoOrderUnitRow.getReserveOrderExistFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        // 以外、trueを返却する。
        return true;
    }
}
@
