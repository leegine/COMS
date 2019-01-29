head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注審査個別チェック(WEB3IfoOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   王暁傑 (Sinocom) 新規作成
Revesion History : 2006/07/12   肖志偉 (中訊) 仕様変更モデル455,469,473,496,516,519
Revesion History : 2006/09/22 郭英 (中訊) 仕様変更 モデル549
Revesion History : 2006/10/9 唐性峰(中訊)　@モデルNo.555
Revesion History : 2006/11/29 徐大方(中訊)　@モデルNo.582
Revesion History : 2007/01/25 張騰宇 (中訊) モデルNo.589,596,604
Revesion History : 2007/04/25 崔遠鵬 (中訊) 仕様変更モデルNo.635
Revesion History : 2007/06/08 肖志偉 (中訊) 仕様変更モデルNo.641,668,683,699,703,714,720,732
Revesion History : 2007/06/21 金傑 (中訊) 仕様変更モデルNo.748,749,750
Revesion History : 2007/06/22 金傑 (中訊) 仕様変更モデルNo.754
Revesion History : 2007/06/23 肖志偉 (中訊) 仕様変更モデルNo.755
Revesion History : 2007/11/27 于瀟 (中訊) 仕様変更モデルNo.820
Revesion History : 2008/04/22 張騰宇 (中訊) モデルNo.879,882
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
Revesion History : 2008/09/03 安陽(中訊) IFO小数点対応
Revesion History : 2008/12/19 張少傑(中訊) モデルNo.904
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DateRangeQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContract;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MultiChangeabilityDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TriggerorderWlimitorderCheckOrderCondPriceDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP発注審査個別チェック)<BR>
 * 先物OP発注審査個別チェッククラス<BR>
 * 発注審査の個々のチェックを実装。<BR>
 *（IfoProductTypeOrderManagerReusableValidationsの拡張クラス）<BR>
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3IfoOrderManagerReusableValidations extends IfoProductTypeOrderManagerReusableValidations
{

    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderManagerReusableValidations.class);

    /**
     * (validate先物OP口座開設)<BR>
     *  顧客が先物オプション口座を開設しているかをチェックする。<BR>
     *１）　@顧客オブジェクト取得<BR>
     *  補助口座.getMainAccount()にて顧客オブジェクトを取得する。<BR>
     *２）　@口座開設区分チェック<BR>
     *  顧客.is先物OP口座開設() == false の場合、例外をスローする。<BR>
     *  [顧客.is先物OP口座開設()に渡す引数]<BR>
     *      先物／オプション区分：引数.先物／オプション区分<BR>
     *  先物の場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00284<BR>
     *  オプションの場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00283<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strFuturesOptionDivision - 先物／オプション区分<BR>
     * 1：先物 2：オプション<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4055484303E4
     */
    public void validateFuturesOptionAccountOpen(SubAccount l_subAccount, String l_strFuturesOptionDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesOptionAccountOpen(l_subAccount,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_strFuturesOptionDivision == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        //顧客オブジェクトを取得する
        WEB3GentradeMainAccount l_mainAccount = null;
        l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        //顧客ROWオブジェクトを取得する
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDivision))
        {
            //先物／オプション区分：先物の場合
            if(!l_mainAccount.isIfoAccountOpen(l_strFuturesOptionDivision))
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00284);
                //例外をスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        else
        {
            //先物／オプション区分：オプションの場合
            if(!l_mainAccount.isIfoAccountOpen(l_strFuturesOptionDivision))
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00283);
                //例外をスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00283,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate上限数量)<BR>
     * 注文数量が上限数量を超えてないかチェックを行う。<BR>
     * <BR>
     * （数量 > 上限数量）の場合に例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00144<BR>
     * @@param l_dblMaxQuantity - 上限数量<BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40611F5B0145
     */
    public boolean validateMaxQuantity(double l_dblMaxQuantity, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMaxQuantity(l_dblMaxQuantity,l_dblQuantity)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_dblMaxQuantity = " + l_dblMaxQuantity);
        log.debug("l_dblQuantity = " + l_dblQuantity);

        boolean l_blnIsValidateMaxQuantity = false;

        if (l_dblQuantity > l_dblMaxQuantity)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00144);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00144, this.getClass().getName() + STR_METHOD_NAME);
        }
        else
        {
            l_blnIsValidateMaxQuantity = true;
        }

        return l_blnIsValidateMaxQuantity;
    }

    /**
     * (validate取引銘柄)<BR>
     * 取引銘柄のチェックを行い、<BR>
     * 先物OP取引銘柄オブジェクトを返却する。<BR>
     * （validateTradedProductのオーバーライド） <BR>
     * <BR>
     * １）　@先物OP取引銘柄取得<BR>
     * スーパークラスの処理にて取引銘柄オブジェクトを取得する。 <BR>
     * 取得できない場合は、非上場銘柄と判断し、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00735<BR>
     * <BR>
     * ２）　@売買停止チェック<BR>
     * 先物OP取引銘柄.is取引規制（is買建, is新規建）<BR>
     * にて売買規制中かを判定する。<BR>
     * trueが返却された場合、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00004<BR>
     * <BR>
     * ３）　@上場期間中かのチェック<BR>
     * 先物OP取引銘柄.is上場期間内()がfalseを返却した場合は例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00145<BR>
     * @@param l_ifoProduct - 先物OP銘柄
     * @@param l_market - 市場
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * <BR>
     * 買建かどうかの判定。<BR>
     * 買建の場合true、売建の場合false。<BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * <BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 40638D7701A7
     */
    public WEB3IfoTradedProductImpl validateTradedProduct(WEB3IfoProductImpl l_ifoProduct, WEB3GentradeMarket l_market, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(l_ifoProduct,l_market,l_blnIsBuyToOpenOrder,l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        if (l_ifoProduct == null || l_market == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("ProductCode = " + l_ifoProduct.getProductCode());
        log.debug("InstitutionID = " + l_market.getInstitution().getInstitutionId());
        log.debug("MarketCode = " + l_market.getMarketCode());
        log.debug("l_blnIsBuyToOpenOrder = " + l_blnIsBuyToOpenOrder);
        log.debug("l_blnIsOpenContract = " + l_blnIsOpenContract);

        //先物OP取引銘柄オブジェクト
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl =
                (WEB3IfoTradedProductImpl) ((WEB3IfoProductManagerImpl) super.getIfoProductManager()).getIfoTradedProduct(
                    l_market.getInstitution(),
                    l_ifoProduct.getProductCode(),
                    l_market.getMarketCode());

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00735,
                STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        log.debug("TradedProductID = " + l_ifoTradedProductImpl.getTradedProductId());

        boolean l_blnIsTradingSuspended = l_ifoTradedProductImpl.isTradingSuspended(l_blnIsBuyToOpenOrder, l_blnIsOpenContract);

        log.debug("isTradingSuspended = " + l_blnIsTradingSuspended);

        //売買停止チェック<BR>
        if (l_blnIsTradingSuspended)
        {
            //trueが返却された場合
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00004,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR);
        }

        boolean l_blnIsInListedTerm = l_ifoTradedProductImpl.isInListedTerm();

        log.debug("l_blnIsListed = " + l_blnIsInListedTerm);

        //上場期間中かのチェック
        if (l_blnIsInListedTerm == false)
        {
            //上場期間以外の場合
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00145);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00145, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_ifoTradedProductImpl;
    }

    /**
     * (validate数量)<BR>
     * 数量のチェックを行う。<BR>
     * 　@－数量が０またはマイナス値でないこと。 <BR>
     * 　@－数量が上限単位を超えていないこと。 <BR>
     * （validateQuantityのオーバーロード） <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）validate数量」参照。<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoProduct - 先物OP取引銘柄<BR>
     * 先物OP取引銘柄オブジェクト<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * <BR>
     * 買建かどうかの判定。<BR>
     * 買建の場合true、売建の場合false。<BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * <BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     *
     * @@throws WEB3BaseException
     * @@roseuid 40642B4100BC
     */
    public void validateQuantity(WEB3GentradeSubAccount l_subAccount, WEB3IfoTradedProductImpl l_ifoTradedProduct, double l_dblQuantity, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_subAccount,l_ifoProduct,l_dblQuantity,l_blnIsBuyToOpenOrder," + "l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //銘柄オブジェクトを取得する
        Product l_product = l_ifoTradedProduct.getProduct();

        //先物OP銘柄オブジェクトを取得
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) l_product;

        try
        {
            //数量チェック
            super.validateQuantity(l_dblQuantity);
        }
        catch (OrderValidationException l_ovex)
        {
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorMessage(l_ovex.getValidationResult().toString());
            log.error(STR_METHOD_NAME, l_ovex);

            //例外をスローする
            throw new WEB3BaseException(l_errorInfo, getClass().getName() + STR_METHOD_NAME);
        }

        //口座オブジェクトを取得する
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //部店オブジェクトを取得する
        Branch l_branch = l_mainAccount.getBranch();

        //（部店指数別）取扱条件オブジェクトを生成する
        WEB3GentradeBranchIndexDealtCond l_handlingCondBranchIndex = new WEB3GentradeBranchIndexDealtCond(l_branch.getBranchCode(), l_ifoTradedProduct);

        //注文の上限数量取得する
        double l_dblMaxQuantity = 0D;
        l_dblMaxQuantity = l_handlingCondBranchIndex.getMaxQuantity(l_blnIsBuyToOpenOrder, l_blnIsOpenContract);

        String l_strMaxQuantity = WEB3StringTypeUtility.formatNumber(l_dblMaxQuantity);
        log.debug("注文の上限数量 = " + l_strMaxQuantity);

        //不足数量チェック
        //下記条件の場合、例外をスローする。
        //引数.数量＞注文の上限数量
        if( l_dblQuantity > l_dblMaxQuantity)
        {
            if(l_blnIsBuyToOpenOrder && l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02001);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02001.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME); 
            }
            else if(!l_blnIsBuyToOpenOrder && l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02002);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02002.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else if(!l_blnIsBuyToOpenOrder && !l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02003);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02003.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else if(l_blnIsBuyToOpenOrder && !l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02004);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02004.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
        }   
        
        if ((l_blnIsOpenContract && l_blnIsBuyToOpenOrder && l_ifoProductImpl.isOptionProduct())
            || !l_blnIsOpenContract)
        {
            //オプションの新規建買または返済の場合処理終了
            log.debug("オプションの新規建買または返済の場合処理終了");
            return;
        }

        //総売建上限数量を取得
        double l_dblTotalOpenSellMaxQuantity = 0D;
        l_dblTotalOpenSellMaxQuantity = l_handlingCondBranchIndex.getTotalOpenSellMaxQuantity();
        log.debug("総売建上限数量 = " + l_dblTotalOpenSellMaxQuantity);

        //ポジションマネージャを取得する
        WEB3IfoPositionManagerImpl l_ifoPositionManager = (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        List l_lisContracts = null;
        l_lisContracts = l_ifoPositionManager.getContracts(l_mainAccount, DefaultSortKeySpec.NULL_SORT_KEY_SPEC, ProductTypeEnum.IFO);
        //建玉の数量の合計値
        double l_dblTotalContractQuantityCnt = 0D;
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {
            l_dblTotalContractQuantityCnt = 0D;
        }
        else
        {
            int i = 0;
            //建玉リストの長さ
            int l_intContractsLen = 0;
            l_intContractsLen = l_lisContracts.size();

            //建玉オブジェクト
            IfoContract l_ifoContract = null;
            //先物OP銘柄オブジェクト_建玉
            WEB3IfoProductImpl l_ifoProductImpl_Con = null;

            for (i = 0; i < l_intContractsLen; i++)
            {
                //建玉オブジェクトをリストから取得
                l_ifoContract = (IfoContract) l_lisContracts.get(i);

                //先物OP銘柄
                l_ifoProductImpl_Con = (WEB3IfoProductImpl) l_ifoContract.getProduct();

                log.debug("l_ifoContract.isShort() = " + l_ifoContract.isShort());
                log.debug("l_ifoContract.getQuantity() = " + l_ifoContract.getQuantity());
                log.debug("l_dblTotalContractQuantityCnt = " + l_dblTotalContractQuantityCnt);

                if (l_ifoProductImpl.isOptionProduct() == l_ifoProductImpl_Con.isOptionProduct() &&
                        l_ifoProductImpl.getUnderlyingProductCode().equals(l_ifoProductImpl_Con.getUnderlyingProductCode()))
                {
                    //同一銘柄タイプ＆＆同一原資産銘柄コード
                    if ((l_ifoProductImpl.isOptionProduct() && l_ifoContract.isShort())
                        || l_ifoProductImpl.isFuturesProduct())
                    {
                        //オプションの場合、売建玉(建玉.isShort() == true)のみを合計する。
                        //先物の場合、すべての建玉数量を合計する。
                        l_dblTotalContractQuantityCnt = l_dblTotalContractQuantityCnt + l_ifoContract.getQuantity();
                    }
                }
            }
            log.debug("After For l_dblTotalContractQuantityCnt = " + l_dblTotalContractQuantityCnt);
        }

        //OP注文マネージャオブジェクトを取得する
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //注文単位リストオブジェクト
        List l_lisOrderUnits = null;
        l_lisOrderUnits =
            l_optionOrderManagerImpl.getOpenOrderUnits(l_subAccount, DateRangeQueryParamsSpec.ALL_DATE_RANGES, PaginationQueryParamsSpec.ALL_PAGES, DefaultSortKeySpec.NULL_SORT_KEY_SPEC);

        //売建未約定数量の合計を計算する
        double l_dblOpenContractNotAcceptedCnt = 0D;

        if (l_lisOrderUnits == null || l_lisOrderUnits.size() == 0)
        {
            l_dblOpenContractNotAcceptedCnt = 0D;
        }
        else
        {
            //注文単位リストオブジェクト
            OrderUnit l_orderUnit = null;

            int j = 0;
            //注文単位リストの長さ
            int l_intOrdersLen = 0;
            l_intOrdersLen = l_lisOrderUnits.size();

            for (j = 0; j < l_intOrdersLen; j++)
            {
                l_orderUnit = (OrderUnit) l_lisOrderUnits.get(j);

                //約定数量を取得する。
                double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
                //注文数量を取得する。
                double l_dblOrderQuantity = l_orderUnit.getQuantity();

                if (Double.isNaN(l_dblExecutedQuantity))
                {
                    l_dblExecutedQuantity = 0;
                }
                
                //同一原資産銘柄コード(*2)の場合、未約定数量を合計する。
                //(*2)先物OP銘柄.get原資産銘柄コード() == 注文単位.get銘柄().get原資産銘柄コード()
                if (l_ifoProductImpl.getUnderlyingProductCode().equals(
                        ((WEB3IfoProductImpl)l_orderUnit.getProduct()).getUnderlyingProductCode()))
                {
                    if (l_ifoProductImpl.isOptionProduct() == true)
                    {
                        if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
                        {
                            //オプション新規売建注文の場合
                            log.debug("オプション新規売建注文の場合");
                            //(*4) 未約定数量※1を合計する
                            //※1 未約定数量の計算注文数量（getQuantity）　@－　@約定数量（getExecutedQuantity()）
                            l_dblOpenContractNotAcceptedCnt += (l_dblOrderQuantity - l_dblExecutedQuantity);
                        }
                    }
                    else if (l_ifoProductImpl.isFuturesProduct() == true)
                    {
                        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType())
                                || OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
                        {
                            //先物新規売建注文の場合
                            log.debug("先物新規売建注文の場合");
                            //(*4) 未約定数量※1を合計する
                            ////※1 未約定数量の計算注文数量（getQuantity）　@－　@約定数量（getExecutedQuantity()）
                            l_dblOpenContractNotAcceptedCnt += (l_dblOrderQuantity - l_dblExecutedQuantity);
                        }
                    }   
                }
                log.debug("l_dblOpenContractNotAcceptedCnt = " + l_dblOpenContractNotAcceptedCnt);
            }
        }

        //チェックする数量
        //引数.数量 ＋ 合計売建玉数量 ＋ 売建未約定数量の合計
        double l_dblCheckQuantity = 0D;
        l_dblCheckQuantity = l_dblQuantity + l_dblTotalContractQuantityCnt + l_dblOpenContractNotAcceptedCnt;

        log.debug("引数.数量 ＋ 合計売建玉数量 ＋ 売建未約定数量の合計 = " + l_dblCheckQuantity);

        //合計売建玉数量チェック
        //下記条件の場合（合計売建玉数量を超える場合）、例外をスローする。
        //（引数.数量 ＋ 合計売建玉数量 ＋ 売建未約定数量の合計）＞総売建上限数量
        String l_strTotalOpenSellMaxQuantity = WEB3StringTypeUtility.formatNumber(l_dblTotalOpenSellMaxQuantity);
        log.debug("総売建上限数量 = " + l_strTotalOpenSellMaxQuantity);
        
        if( l_dblCheckQuantity > l_dblTotalOpenSellMaxQuantity)
        {
            if(l_ifoProductImpl.isOptionProduct())
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02005);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02005.addText(l_strTotalOpenSellMaxQuantity),
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02006);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02006.addText(l_strTotalOpenSellMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate注文単価)<BR>
     * 指値のチェックを行う。<BR>
     * （validatePriceのオーバーロード）<BR>
     * <BR>
     * 　@－呼値チェック<BR>
     * 　@－値幅チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）validate注文単価」参照。<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄オブジェクト
     * @@param l_subAccount - 補助口座オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 4064306400FB
     */
    public void validateOrderUnitPrice(double l_dblLimitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct, SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderUnitPrice(l_dblLimitPrice,l_ifoTradedProduct,l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null || l_subAccount == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        log.debug(STR_METHOD_NAME + "l_dblLimitPrice = " + l_dblLimitPrice);
        try
        {
            //指値をチェックする
            if (l_dblLimitPrice == 0)
            {
                //０の場合処理を行わない
                log.debug(STR_METHOD_NAME + "指値が０の場合");

            }
            else
            {
                //０以外の場合以下処理を行く
                log.debug(STR_METHOD_NAME + "指値が０以外の場合");

                //WEB3IfoTradedProductImpl.javaオブジェクトを取得する
                //DatafindException,DataQueryException ,DateNetWorkException
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) (l_ifoTradedProduct.getProduct());

                WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

                double l_dblTickValue = 0; //刻み値
                //刻み値を取得する
                l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProductImpl, l_dblLimitPrice);
                log.debug("刻み値" + " l_dblTickValue = " + l_dblTickValue);

                //呼び値をチェックする
                this.validateTickValueDef(l_dblTickValue, l_dblLimitPrice);

                //値幅チェック実施する
                boolean l_isPriceRangeChecked = false;
                l_isPriceRangeChecked = l_ifoTradedProduct.isPriceRangeChecked();

                log.debug("値幅チェック" + " l_isPriceRangeChecked = " + l_isPriceRangeChecked);

                if (l_isPriceRangeChecked)
                {
                    //Is値幅チェック実施がTrueの場合
                    //以下処理を実施する
                    log.debug(STR_METHOD_NAME + "Is値幅チェック実施がTrueの場合");

                    //清算値を取得する
                    double l_dblLiquidationPrice = 0D;
                    l_dblLiquidationPrice = l_ifoTradedProduct.getLiquidationPrice();

                    log.debug("清算値" + " l_dblLiquidationPrice = " + l_dblLiquidationPrice);

                    //原資産時価を取得する
                    double l_dblUnderlyingCurrentPrice = 0D;
                    l_dblUnderlyingCurrentPrice = l_ifoTradedProduct.getUnderlyingCurrentPrice();

                    log.debug("原資産時価" + " l_dblUnderlyingCurrentPrice = " + l_dblUnderlyingCurrentPrice);

                    //基準値(単価)を取得する
                    double l_dblBasePrice = 0D;
                    l_dblBasePrice = this.calcBasePrice(l_dblLiquidationPrice, l_dblTickValue);

                    log.debug("基準値(単価)" + " l_dblBasePrice = " + l_dblBasePrice);

                    //基準値＜制限値幅＞を取得する
                    double l_dblBasePriceDeregPriceRange = 0D;
                    //基準値
                    double l_dblTempBasePrice = 0D;
                    IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();
                    if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoProductRow.getFutureOptionDiv()))
                    {
                        //先物ＯＰ銘柄.先物／オプション区分 == "先物"の場合、get清算値()の戻り値
                        l_dblTempBasePrice = l_dblLiquidationPrice;
                    }
                    else if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
                    {
                        //先物ＯＰ銘柄.先物／オプション区分 == "オプション"の場合、get原資産時価()の戻り値
                        l_dblTempBasePrice = l_dblUnderlyingCurrentPrice;
                    }
                    l_dblBasePriceDeregPriceRange =
                        this.calcBasePriceDeregPriceRange(l_dblTempBasePrice, l_dblTickValue);

                    log.debug("基準値＜制限値幅＞" + " l_dblBasePriceDeregPriceRange = " + l_dblBasePriceDeregPriceRange);

                    //制限値幅を取得する。
                    //[get制限値幅()に指定する引数]
                    //先物OP銘柄：　@（getProduct()の戻り値）
                    //単価：　@（calc基準値＜制限値幅＞()の戻り値）
                    double l_dblDeregulatedPriceRange = 0D;
                    l_dblDeregulatedPriceRange =
                        l_productManagerImpl.getDeregulatedPriceRange(l_ifoProductImpl, l_dblBasePriceDeregPriceRange);

                    log.debug("制限値幅" + " l_dblDeregulatedPriceRange = " + l_dblDeregulatedPriceRange);

                    //値幅上限を取得する
                    double l_dblStopHighPrice = 0D;
                    l_dblStopHighPrice = this.calcStopHighPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);

                    //値幅下限を取得する
                    double l_dblStopLowPrice = 0D;
                    l_dblStopLowPrice = this.calcStopLowPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);

                    log.debug("値幅上限" + " l_dblStopHighPrice = " + l_dblStopHighPrice);
                    log.debug("値幅下限" + " l_dblStopLowPrice = " + l_dblStopLowPrice);

                    //値幅をチェックする
                    this.validatePriceRange(l_dblLimitPrice, l_dblStopHighPrice, l_dblStopLowPrice);
                }
                else
                {
                    //Is値幅チェック実施がFalseの場合
                    //処理しない
                    log.debug(STR_METHOD_NAME + "Is値幅チェック実施がFalseの場合");
                }
            }
        }
        catch (WEB3BaseException l_webx)
        {
            throw new WEB3BaseException(
                l_webx.getErrorInfo(),
                STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能指数)<BR>
     * 部店で取扱可能な指数かを判定する。<BR>
     * <BR>
     * １）　@（部店指数別）取扱可能条件を生成する。<BR>
     * [コンストラクタの引数]<BR>
     * 部店コード：　@部店コード<BR>
     * 先物OP取引銘柄：　@先物OP取引銘柄<BR>
     * <BR>
     * ２）　@判定実施<BR>
     * 取得したオブジェクト.is.取扱可能() == false<BR>
     * の場合は取扱不可と判定し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00147<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄
     * @@throws WEB3BaseException
     * @@roseuid 406434C603CA
     */
    public void validateHandlingIndex(String l_strBranchCode, WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingIndex(l_strBranchCode,l_ifoTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        //（部店指数別）取扱可能条件を生成する。
        //        WEB3GentradeHandlingCondBranchIndex l_handlingCondBranchIndex =
        //            new WEB3GentradeHandlingCondBranchIndex(l_strBranchCode,l_ifoTradedProduct);
        WEB3GentradeBranchIndexDealtCond l_handlingCondBranchIndex = new WEB3GentradeBranchIndexDealtCond(l_strBranchCode, l_ifoTradedProduct);

        if (l_handlingCondBranchIndex == null)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_handlingCondBranchIndex.isHandlingPossible())
        {
            //is.取扱可能() == true
        }
        else
        {
            //is.取扱可能() == true
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00147);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00147, this.getClass().getName() + STR_METHOD_NAME);

        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate呼び値)<BR>
     * 刻み値で割り切れる値かをチェックする。<BR>
     * <BR>
     * （指値／刻み値）が整数でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00148<BR>
     * @@param l_dblTickValue - 刻み値<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4067B83900F4
     */
    public void validateTickValueDef(double l_dblTickValue, double l_dblLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTickValueDef(l_dblTickValue,l_dblLimitPrice)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        if (l_dblTickValue <= 0 || l_dblLimitPrice < 0)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("刻み値 = " + l_dblTickValue);
        log.debug("指値 = " + l_dblLimitPrice);
        log.debug("（指値／刻み値） = " + remainder(l_bdLimitPrice, l_bdTickValue).doubleValue());

        if (remainder(l_bdLimitPrice, l_bdTickValue).compareTo(new BigDecimal("0")) != 0)
        {
            //（指値／刻み値）が整数でない場合
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00148);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00148, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (calc基準値)<BR>
     * 清算値の値が刻み値で割り切れる値になるように計算する。<BR>
     * <BR>
     * ○（（清算値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（清算値／刻み値(*1)）×刻み値<BR>
     * 　@(*1)　@除算の計算結果を切り上げ。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（清算値／刻み値(*2)）×刻み値<BR>
     * 　@(*2)　@除算の計算結果を切り捨て。<BR>
     * @@param l_dblLiquidationPrice - 清算値<BR>
     * @@param l_dblTickValue - 刻み値<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067BE3502D8
     */
    public double calcBasePrice(double l_dblLiquidationPrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcBasePrice(l_dblLiquidationPrice,l_dblTickValue)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdLiquidationPrice = new BigDecimal(l_dblLiquidationPrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        if (l_dblTickValue <= 0 || l_dblLiquidationPrice < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        double l_dblBasePrice = 0D; //基準値

        log.debug(STR_METHOD_NAME + "（清算値／刻み値）の余り = " + remainder(l_bdLiquidationPrice, l_bdTickValue).doubleValue());
        log.debug(STR_METHOD_NAME + "刻み値／2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (remainder(l_bdLiquidationPrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //除算の計算結果を切り上げ
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //除算の計算結果を切り捨て
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.debug(STR_METHOD_NAME + "戻るの清算値 " + l_dblBasePrice);

        log.exiting(STR_METHOD_NAME);

        return l_dblBasePrice;
    }

    /**
     * (calc基準値＜制限値幅＞)<BR>
     * 基準値の値が刻み値で割り切れる値になるように計算する。<BR>
     * <BR>
     * ○（（基準値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（基準値／刻み値(*1)）×刻み値<BR>
     * 　@(*1)　@除算の計算結果を切り上げ<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[基準値]<BR>
     * 　@（基準値／刻み値(*2)）×刻み値<BR>
     * 　@(*2)　@除算の計算結果を切り捨て<BR>
     * @@param l_dblBasePrice - 基準値<BR>
     * @@param l_dblTickValue - 刻み値<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcBasePriceDeregPriceRange(double l_dblBasePrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcBasePriceDeregPriceRange(double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblBasePrice < 0 || l_dblTickValue < 0 || GtlUtils.Double.isZero(l_dblTickValue))
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //基準値＜制限値幅＞
        double l_dblBasePriceDeregPriceRange = 0D;
        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        log.debug(STR_METHOD_NAME + "（基準値／刻み値）の余り = " + remainder(l_bdBasePrice, l_bdTickValue).doubleValue());
        log.debug(STR_METHOD_NAME + "刻み値／2 = " + l_bdTickValue.divide(
            new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (remainder(l_bdBasePrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //除算の計算結果を切り上げ
            l_dblBasePriceDeregPriceRange =
                l_bdBasePrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //除算の計算結果を切り捨て
            l_dblBasePriceDeregPriceRange =
                l_bdBasePrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.debug(STR_METHOD_NAME + "戻るの基準値＜制限値幅＞ " + l_dblBasePriceDeregPriceRange);

        log.exiting(STR_METHOD_NAME);

        return l_dblBasePriceDeregPriceRange;
    }

    /**
     * (calc値幅上限)<BR>
     * 呼値単位の値幅上限値を計算する。<BR>
     * <BR>
     * １）　@値幅上限値計算<BR>
     * 　@値幅上限値 = 基準値＋制限値幅<BR>
     * <BR>
     * ２）　@刻み値取得<BR>
     * 先物OPプロダクトマネージャ.get刻み値)にて刻み値を取得する。<BR>
     * <BR>
     * [get刻み値()に指定する引数]<BR>
     * 先物OP銘柄：　@先物OP銘柄<BR>
     * 単価：　@（１）で算出した値幅上限値）<BR>
     * <BR>
     * ３）　@呼値単位の値幅上限値計算<BR>
     * <BR>
     * ○（値幅上限値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（値幅上限値／刻み値(*1)）×刻み値<BR>
     * 　@(*1)　@除算の計算結果を切り上げ。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（値幅上限値／刻み値(*2)）×刻み値<BR>
     * 　@(*2)　@除算の計算結果を切り捨て。<BR>
     * @@param l_dblBasePrice - 基準値<BR>
     * @@param l_dblDeregPriceRange - 制限値幅<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067C0720142
     */
    public double calcStopHighPrice(double l_dblBasePrice, double l_dblDeregPriceRange, WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcStopHighPrice(l_dblBasePrice,l_dblDeregPriceRange,l_ifoProduct)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdDeregPriceRange = new BigDecimal(l_dblDeregPriceRange + "");

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        if (l_ifoProduct == null || l_dblBasePrice < 0 || l_dblDeregPriceRange < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        //値幅上限値
        double l_dblStopHighPrice = 0D;
        //値幅上限値 = 基準値＋制限値幅
        BigDecimal l_bdStopHighPrice = l_bdBasePrice.add(l_bdDeregPriceRange);
        l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        log.debug("値幅上限値 = " + l_dblStopHighPrice);

        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        double l_dblTickValue = 0D; //刻み値
        //刻み値を取得する
        l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProduct, l_dblStopHighPrice);
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        log.debug("刻み値 = " + l_dblTickValue);

        log.debug("（値幅上限値／刻み値）の余り = " + remainder(l_bdStopHighPrice, l_bdTickValue).doubleValue());
        log.debug("刻み値／2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (l_dblTickValue <= 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (remainder(l_bdStopHighPrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //除算の計算結果を切り上げ
            l_bdStopHighPrice =
                l_bdStopHighPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue);
            l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        }
        else
        {
            //除算の計算結果を切り捨て
            l_bdStopHighPrice =
                l_bdStopHighPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue);
            l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        }

        log.debug("戻るの値幅上限値 = " + l_dblStopHighPrice);
        log.exiting(STR_METHOD_NAME);

        return l_dblStopHighPrice;
    }

    /**
     * (calc値幅下限)<BR>
     * 呼値単位の値幅下限値を計算する。<BR>
     * <BR>
     * １）　@値幅下限値計算<BR>
     * 　@値幅下限値 = 基準値－制限値幅<BR>
     * ※（上記の計算結果 <= 0）の場合、0を返却して処理を終了する。<BR>
     * <BR>
     * ２）　@刻み値取得<BR>
     * 先物OPプロダクトマネージャ.get刻み値()にて刻み値を取得する。<BR>
     * <BR>
     * [get刻み値()に指定する引数]<BR>
     * 先物OP銘柄：　@先物OP銘柄<BR>
     * 単価：　@（１）で算出した値幅下限値）<BR>
     * <BR>
     * ３）　@呼値単位の値幅下限値計算<BR>
     * <BR>
     * ○（値幅下限値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * 　@但し、計算結果がマイナス値になった場合は0を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（値幅下限値／刻み値(*1)）×刻み値<BR>
     * 　@(*1)　@除算の計算結果を切り上げ。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * 　@以下の計算結果を返却する。<BR>
     * 　@但し、計算結果がマイナス値になった場合は0を返却する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（値幅下限値／刻み値(*2)）×刻み値<BR>
     * 　@(*2)　@除算の計算結果を切り捨て。<BR>
     * @@param l_dblBasePrice - 基準値<BR>
     * @@param l_dblDeregPriceRange - 制限値幅<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067CB430384
     */
    public double calcStopLowPrice(double l_dblBasePrice, double l_dblDeregPriceRange, WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcStopLowPrice(l_dblBasePrice,l_dblDeregPriceRange,l_ifoProduct)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdDeregPriceRange = new BigDecimal(l_dblDeregPriceRange + "");

        if (l_ifoProduct == null || l_dblBasePrice < 0 || l_dblDeregPriceRange < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //値幅下限値
        double l_dblStopLowPrice = 0D;
        BigDecimal l_bdStopLowPrice = new BigDecimal("0");
        //値幅下限値 = 基準値 - 制限値幅
        l_bdStopLowPrice = l_bdBasePrice.subtract(l_bdDeregPriceRange);
        l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
        log.debug("値幅下限値 = " + l_dblStopLowPrice);

        if (l_dblStopLowPrice <= 0)
        {
            //値幅下限値が0以下の場合

            l_dblStopLowPrice = 0;
        }
        else
        {
            //値幅下限値が0より大きい値の場合

            WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            double l_dblTickValue = 0D; //刻み値
            //刻み値を取得する
            l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProduct, l_dblStopLowPrice);
            BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

            log.debug("刻み値" + l_dblTickValue);
            log.debug("（値幅下限値／刻み値）の余り = " + remainder(l_bdStopLowPrice, l_bdTickValue).doubleValue());
            log.debug("刻み値／2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

            if (l_dblTickValue <= 0)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

                //例外をスローする
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            }

            if (remainder(l_bdStopLowPrice, l_bdTickValue).compareTo(
                l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
            {
                //除算の計算結果を切り上げ
                l_bdStopLowPrice =
                    l_bdStopLowPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue);
                l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
            }
            else
            {
                //除算の計算結果を切り捨て
                l_bdStopLowPrice =
                    l_bdStopLowPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue);
                l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
            }
        }

        log.debug("戻るの値幅下限値 = " + l_dblStopLowPrice);
        log.exiting(STR_METHOD_NAME);

        return l_dblStopLowPrice;

        //return 0;
    }

    /**
     * (validate値幅)<BR>
     * 値幅の範囲内かをチェックする。<BR>
     *  <BR>
     * （値幅下限 <= 指値 <= 値幅上限）でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00031<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@param l_dblStopHighPrice - 値幅上限<BR>
     * @@param l_dblStopLowPrice - 値幅下限<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4067CBB6023C
     */
    public void validatePriceRange(double l_dblLimitPrice, double l_dblStopHighPrice, double l_dblStopLowPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePriceRange(l_dblLimitPrice,l_dblStopHighPrice,l_dblStopLowPrice)";
        log.entering(STR_METHOD_NAME);

        log.debug(STR_METHOD_NAME + "値幅上限 = " + l_dblStopHighPrice);
        log.debug(STR_METHOD_NAME + "指値 = " + l_dblLimitPrice);
        log.debug(STR_METHOD_NAME + "値幅下限 = " + l_dblStopLowPrice);

        if (l_dblLimitPrice < 0 || l_dblStopHighPrice < 0 || l_dblStopLowPrice < 0 || l_dblStopHighPrice < l_dblStopLowPrice)
        {
            //以下例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_dblLimitPrice >= l_dblStopLowPrice && l_dblLimitPrice <= l_dblStopHighPrice)
        {
            //値幅下限 <= 指値 <= 値幅上限 の場合
            log.debug("値幅下限 <= 指値 <= 値幅上限 の場合");
            //正しです
        }
        else
        {
            //指値 > 値幅上限 　@と　@指値 < 値幅下限の場合
            log.debug("指値 > 値幅上限 　@と　@指値 < 値幅下限の場合");
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00031);
            //以下例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00031, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * スーパークラスに自身のインスタンスを登録する。<BR>
     * <BR>
     * （プラグイン初期化時にコールされる）<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---<BR>
     * @@roseuid 407630CF030C
     */
    public void register()
    {
        super.setInstance(this);
    }

    /**
     * (validate注文取消可能状態)<BR>
     * 注文の取消が可能か注文状態であるかどうかをチェックする。<BR>
     * （validateOrderForCancellation()のオーバーライド）<BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@注文単位オブジェクトの要素数分、 <BR>
     * 注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。 <BR>
     * <BR>
     * ２－１） 注文有効状態のチェック <BR>
     * 　@　@　@・注文有効状態がOPEN以外の場合は取消不可とし、 <BR>
     * 　@　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00858<BR>
     * <BR>
     * ２－２） 注文状態のチェック <BR>
     * 市場開局／閉局によって以下の通りチェックを行う。 <BR>
     * <BR>
     * ==========================================================================  <BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合] <BR>
     * 　@　@かつ　@取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合]  <BR>
     * <BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@ACCEPTED(*) <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@ORDERING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@(*1)注文単位.発注条件＝"逆指値"の場合のみは、取消可能とする。 <BR>
     * <BR>
     * 　@　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済） <BR>
     *        の場合は取消不可とし、例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * 　@　@　@※注文単位.発注条件≠"逆指値"の場合のみ。 <BR>
     * <BR>
     * ==========================================================================  <BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）  <BR>
     * 　@　@かつ　@取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合]  <BR>
     * <BR>
     * 　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、  <BR>
     * 　@　@　@例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------  <BR>
     * 　@　@　@CANCEL_ACCEPTED  <BR>
     * 　@　@　@CANCELLING  <BR>
     * 　@　@　@MODIFYING  <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * <BR>
     * 　@　@・注文状態がMODIFY_ACCEPTEDの場合、  <BR>
     * 　@　@　@　@this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。  <BR>
     * 　@　@　@　@引数設定：　@注文単位．部店ID  <BR>
     * <BR>
     * 　@　@・先物OP発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は  <BR>
     * 　@　@　@取消不可とし、例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================  <BR>
     * －[市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合] <BR>
     * <BR>
     *  　@　@・注文状態が以下のいずれかに該当する場合は取消不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@ORDERING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * <BR>
     * ========================================================================== <BR>
     * ３）　@市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）  <BR>
     * 　@　@　@かつ　@市場発注済の注文(*2)  <BR>
     * 　@　@　@の場合のみ、  <BR>
     * 　@　@　@取消対象注文の発注経路が取消可能かどうかチェックする。  <BR>
     * <BR>
     * 　@　@(*2)市場発注済の注文  <BR>
     * 　@　@　@　@注文単位.市場から確認済みの数量≠NaNの場合、  <BR>
     * 　@　@　@　@市場発注済の注文と判定する。  <BR>
     * <BR>
     * ３－１）　@先物OP発注サービス.get発注先切替()にて、発注先切替クラスを取得する。  <BR>
     * <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * 　@　@　@＜get発注先切替()：引数設定仕様＞  <BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位  <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * <BR>
     * ３－２）　@３－１）の戻り値==nullの場合(*3)は、何もせずにそのままreturnする。  <BR>
     * 　@　@　@　@　@３－１）で取得したインスタンス.is訂正取消可能( )==falseの場合は取消不可とし、<BR>  
     * 　@　@　@　@　@例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@(*3)戻り値==nullの場合：以下が該当する。  <BR>
     * 　@　@　@　@－取消対象の注文単位.発注経路区分=="発注停止"の場合  <BR>
     * 　@　@　@　@－取消対象の注文単位が、フロント発注対応市場に対する注文で、  <BR>
     * 　@　@　@　@　@かつ　@SONAR入力注文の場合  <BR>
     * 　@　@　@　@　@（SONAR入力注文の場合、発注経路区分にはSONARの経路が設定されている）<BR>
     * @@param l_order - 注文<BR>
     * @@throws OrderValidationException
     * @@roseuid 40763AEB012E
     */
    public void validateOrderForCancellation(Order l_order) throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(l_order)";
        log.entering(STR_METHOD_NAME);
        if (l_order == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        //注文単位オブジェクトを取得する
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();

        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        
        //注文単位オブジェクトを取得する
        l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //注文単位オブジェクトの要素数分、
        //注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        int l_intOrderUnitsLen = l_orderUnits.length;
        //取引時間管理.is市場開局時間帯( )
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error("",l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        for (int i = 0; i < l_intOrderUnitsLen; i++)
        {
            OrderOpenStatusEnum l_orderOpenStatusEnum = null;
            l_orderOpenStatusEnum = l_orderUnits[i].getOrderOpenStatus();

            // 注文有効状態のチェック 
            //　@　@　@・注文有効状態がOPEN以外の場合は取消不可とし、 
            //　@　@　@　@例外をthrowする。 
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                log.debug("取消不可：注文有効状態がOPEN以外");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00858);
            }
            log.debug("注文有効状態：OPEN");

            //注文状態のチェック
            //市場開局／閉局によって以下の通りチェックを行う。
            int l_intStatus = l_orderUnit.getOrderStatus().intValue();
            // －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合]
            if (l_result)
            {
                try
                {
                    //取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合]
                    if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                    {
                        //CANCEL_ACCEPTED CANCELLING MODIFY_ACCEPTED MODIFYING ORDERING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                            || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                        {
                            log.debug("該当注文が取消不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                        // 注文状態がACCEPTEDで、発注条件が"逆指値"の場合のみ、取消可能とする。
                        if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("該当注文が取消不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                        //注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）
                        //　@　@の場合は訂正不可とし、例外をthrowする。
                        if (l_orderUnitRow.getConfirmedQuantityIsNull()
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("該当注文が取消不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                    }
                    //取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合
                    else
                    {
                        //・注文状態が以下のいずれかに該当する場合は訂正不可とし、例外をthrowする。
                        //　@　@　@CANCEL_ACCEPTED CANCELLING MODIFYING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))
                        {
                            log.debug("注文を受付られる状態ではありません。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }

                        //注文状態がMODIFY_ACCEPTEDの場合、
                        //this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。
                        //引数設定：　@注文単位.部店ID
                        if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        {
                            this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
                                l_orderUnit.getBranchId());
                        }
                        //先物OP発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は
                        //  訂正不可とし、例外をthrowする。
                        if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone(
                            (IfoOrderUnit)l_orderUnit))
                        {
                            log.debug("注文を受付られる状態ではありません。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
            // [市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合]
            else
            {
                //・注文状態が以下のいずれかに該当する場合は訂正不可とし、
                //　@　@　@例外をthrowする。
                if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                    || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                    || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED))
                {
                    log.debug("該当注文が取消不可です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                }
            }
        }

        //市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）
        //　@　@　@かつ　@市場発注済の注文(*2)
        //　@　@  の場合のみ、
        //　@  　@訂正対象注文の発注経路が訂正可能かどうかチェックする。
        if (l_result && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //先物OP発注サービス.get発注先切替()にて、発注先切替クラスを取得する。
            WEB3GentradeOrderSwitching l_orderSwitching = null;

            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((IfoOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }

            //戻り値==nullの場合(*3)は、何もせずにそのままreturnする。
            //　@で取得したインスタンス.is訂正取消可能( )==falseの場合は訂正不可とし、
            //　@例外をthrowする。
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 注文の訂正が可能な注文状態であるかどうかをチェックする。 <BR>
     * <BR>
     * １）　@引数の注文オブジェクトより、注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@注文単位オブジェクトの要素数分、 <BR>
     * 注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。 <BR>
     * <BR>
     * ２－１） 注文有効状態のチェック <BR>
     * 　@　@　@・注文有効状態がOPEN以外の場合は訂正不可とし、 <BR>
     * 　@　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00858<BR>
     * <BR>
     * ２－２） 注文状態のチェック <BR>
     * 市場開局／閉局によって以下の通りチェックを行う。 <BR>
     * <BR>
     * ========================================================================== <BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合 <BR>
     * 　@かつ　@取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合] <BR>
     * <BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@ACCEPTED(*) <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@ORDERING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@(*1)注文単位.発注条件＝"逆指値"の場合のみは、訂正可能とする。 <BR>
     * <BR>
     * 　@　@　@・注文単位.市場から確認済みの数量 == NaN（＝市場受付未済） <BR>
     * 　@　@　@　@の場合は訂正不可とし、例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * 　@　@　@　@※注文単位.発注条件≠"逆指値"の場合のみ。 <BR>
     * <BR>
     * ========================================================================== <BR>
     * －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合 <BR>
     * 　@かつ　@取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合] <BR>
     * <BR>
     * 　@　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * <BR>
     * 　@　@　@・注文状態がMODIFY_ACCEPTEDの場合、 <BR>
     * 　@　@　@　@this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。 <BR>
     * 　@　@　@　@引数設定：　@注文単位.部店ID <BR>
     * <BR>
     * 　@　@　@・先物OP発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は <BR>
     * 　@　@　@　@訂正不可とし、例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * ========================================================================== <BR>
     * －[市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合] <BR>
     * <BR>
     *  　@　@・注文状態が以下のいずれかに該当する場合は訂正不可とし、 <BR>
     * 　@　@　@例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@CANCEL_ACCEPTED <BR>
     * 　@　@　@CANCELLING <BR>
     * 　@　@　@MODIFY_ACCEPTED <BR>
     * 　@　@　@MODIFYING <BR>
     * 　@　@　@ORDERING <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * <BR>
     * ========================================================================== <BR>
     * ３）　@市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）  <BR>
     * 　@　@　@かつ　@市場発注済の注文(*2)  <BR>
     * 　@　@　@の場合のみ、  <BR>
     * 　@　@　@訂正対象注文の発注経路が訂正可能かどうかチェックする。  <BR>
     * <BR>
     * 　@　@(*2)市場発注済の注文  <BR>
     * 　@　@　@　@注文単位.市場から確認済みの数量≠NaNの場合、  <BR>
     * 　@　@　@　@市場発注済の注文と判定する。  <BR>
     * <BR>
     * ３－１）　@先物OP発注サービス.get発注先切替()にて、発注先切替クラスを取得する。  <BR>
     * <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * 　@　@　@＜get発注先切替()：引数設定仕様＞  <BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位  <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * <BR>
     * ３－２）　@３－１）の戻り値==nullの場合(*3)は、何もせずにそのままreturnする。  <BR>
     * 　@　@　@　@　@３－１）で取得したインスタンス.is訂正取消可能( )==falseの場合は訂正不可とし、  <BR>
     * 　@　@　@　@　@例外をthrowする。  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * 　@　@(*3)戻り値==nullの場合：以下が該当する。  <BR>
     * 　@　@　@　@－訂正対象の注文単位.発注経路区分=="発注停止"の場合  <BR>
     * 　@　@　@　@－訂正対象の注文単位が、フロント発注対応市場に対する注文で、  <BR>
     * 　@　@　@　@　@かつ　@SONAR入力注文の場合  <BR>
     * 　@　@　@　@　@（SONAR入力注文の場合、発注経路区分にはSONARの経路が設定されている）  <BR>
     * <BR>
     * ４） 遅延状況のチェック <BR>
     * 　@引数.isSkip遅延状況チェック==falseの場合のみ、以下処理を行なう。 <BR>
     * 　@（W指値切替処理からコールされた場合のみスキップする。） <BR>
     * <BR>
     * 　@OP注文マネージャ.is未発注遅延注文() == trueであれば訂正不可とし、例外をthrowする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * 　@　@　@＜is未発注遅延注文()：引数設定仕様＞  <BR>
     * <BR>
     * 　@　@　@注文単位：　@１）で取得した注文単位  <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * @@param l_order - (注文)
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * Skip遅延状況チェック <BR>
     * <BR>
     * true：遅延状況チェックをスキップする。（W指値切替処理からコールされた場合） <BR>
     * false：遅延状況チェックをスキップしない。<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayStatusCheck)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //注文単位オブジェクトを取得する
        OrderUnit[] l_orderUnits = null;
        l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //注文単位オブジェクトの要素数分、
        //注文有効状態（order_open_status）、及び注文状態（order_status）のチェックを行う。
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        int l_intOrderUnitsLen = l_orderUnits.length;
        //取引時間管理.is市場開局時間帯( )
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_ex.getErrorInfo());
        }
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        for (int i = 0; i < l_intOrderUnitsLen; i++)
        {
            //注文有効状態のチェック
            //　@　@　@・注文有効状態がOPEN以外の場合は訂正不可とし、
            //　@　@　@　@例外をthrowする
            OrderOpenStatusEnum l_orderOpenStatusEnum = null;
            l_orderOpenStatusEnum = l_orderUnits[i].getOrderOpenStatus();
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                //注文有効状態がOPEN以外の場合
                log.debug("訂正不可：注文有効状態がOPEN以外");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00858);
            }

            //注文状態のチェック
            //市場開局／閉局によって以下の通りチェックを行う。
            int l_intStatus = l_orderUnit.getOrderStatus().intValue();
            // －[市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）の場合]
            if (l_result)
            {
                try
                {
                    //取引所が取引中（取引時間管理.is取引所休憩時間帯( ) == false）の場合]
                    if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                    {
                        //CANCEL_ACCEPTED CANCELLING MODIFY_ACCEPTED MODIFYING ORDERING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                            || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                        {
                            log.debug("該当注文が訂正不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                        // 注文状態がACCEPTEDで、発注条件が"逆指値"の場合のみ、訂正可能。
                        if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("該当注文が訂正不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                        //注文単位.市場から確認済みの数量 == NaN（＝市場受付未済）
                        //　@　@の場合は訂正不可とし、例外をthrowする。
                        if (l_orderUnitRow.getConfirmedQuantityIsNull()
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("該当注文が訂正不可です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                    }
                    //取引所が休憩時間中（取引時間管理.is取引所休憩時間帯( ) == true）の場合
                    else
                    {
                        //・注文状態が以下のいずれかに該当する場合は訂正不可とし、例外をthrowする。
                        //　@　@　@CANCEL_ACCEPTED CANCELLING MODIFYING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))
                        {
                            log.debug("注文を受付られる状態ではありません。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }

                        //注文状態がMODIFY_ACCEPTEDの場合、
                        //this.validate市場送信済注文複数回訂正可能（休憩時間中）()をコールする。
                        //引数設定：　@注文単位.部店ID
                        if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        {
                            this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
                                l_orderUnit.getBranchId());
                        }
                        //先物OP発注サービス.is市場通知中注文IN休憩時間帯() == true（＝通知中）の場合は
                        //  訂正不可とし、例外をthrowする。
                        if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone(
                            (IfoOrderUnit)l_orderUnit))
                        {
                            log.debug("注文を受付られる状態ではありません。");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
            // [市場閉局時間帯（取引時間管理.is市場開局時間帯( ) == false）の場合]
            else
            {
                //・注文状態が以下のいずれかに該当する場合は訂正不可とし、
                //　@　@　@例外をthrowする。
                if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                    || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                    || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED))
                {
                    log.debug("該当注文が訂正不可です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                }
            }
        }

        //市場開局時間帯（取引時間管理.is市場開局時間帯( ) == true）
        //　@　@　@かつ　@市場発注済の注文(*2)
        //　@　@  の場合のみ、
        //　@  　@訂正対象注文の発注経路が訂正可能かどうかチェックする。
        if (l_result && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //先物OP発注サービス.get発注先切替()にて、発注先切替クラスを取得する。
            WEB3GentradeOrderSwitching l_orderSwitching = null;

            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((IfoOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }

            //戻り値==nullの場合(*3)は、何もせずにそのままreturnする。
            //で取得したインスタンス.is訂正取消可能( )==falseの場合は訂正不可とし、
            //例外をthrowする。
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                log.debug("注文を受付られる状態ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        //遅延状況のチェック
        //　@引数.isSkip遅延状況チェック == false の場合のみ、以下処理を行う。
        //　@（W指値切替処理からコールされた場合のみスキップする）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        if (!l_blnIsSkipDelayStatusCheck)
        {
            //OP注文マネージャ.is未発注遅延注文() == trueであれば訂正不可とし、例外をthrowする。
            boolean l_blnIsNotOrderedDelay = false;

            l_blnIsNotOrderedDelay =
                l_orderManager.isNotOrderedDelay((IfoOrderUnit)l_orderUnit);

            if (l_blnIsNotOrderedDelay)
            {
                log.debug("該当注文が訂正不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00156);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 訂正が可能な注文状態かをチェックする。  <BR>
     * （validateOrderForChangeability( )のオーバーライド） <BR>
     * <BR>
     * this.validate注文訂正可能状態()に処理を委譲（delegate）する。  <BR>
     * <BR>
     * [validate注文訂正可能状態()に指定する引数]   <BR>
     * 　@注文：　@パラメータ.注文   <BR>
     * 　@isSkip遅延状況チェック：　@false（固定）<BR>
     * @@param l_order - (注文)<BR>
     * @@throws OrderValidationException
     * @@roseuid 40763AEB0137
     */
    public void validateOrderForChangeability(Order l_order) throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        //this.validate注文訂正可能状態()に処理を委譲（delegate）する。
        this.validateOrderForChangeability(l_order, false);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate市場コード)<BR>
     * 市場コードのチェックを実施する。<BR>
     * 存在する場合は市場オブジェクトを返却する。<BR>
     * （validateMarketのオーバーロード）<BR>
     * <BR>
     * １）　@拡張金融オブジェクトマネージャ.get市場（）<BR>
     * にて該当する市場オブジェクトを生成する。<BR>
     * 市場が取得できない場合は、該当市場なしと判断し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00003<BR>
     * <BR>
     *  ２）　@市場オブジェクトを返却する。<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@return Market
     * @@throws WEB3BaseException
     * @@roseuid 40763B0E0222
     */
    public Market validateMarket(String l_strMarketCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(l_strMarketCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_strMarketCode = " + l_strMarketCode);
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        Market l_market = null;

        try
        {
            //throw NotFoundException
            l_market = l_gentradeFinObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME + l_nfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (validate銘柄コード)<BR>
     * 銘柄のチェックを行い、銘柄オブジェクトを返却する。<BR>
     * <BR>
     * １）　@銘柄オブジェクト取得<BR>
     * 先物プロダクトマネージャ.get銘柄()にて先物OP銘柄オブジェクトを取得する。<BR>
     * <BR>
     * [get銘柄()に指定する引数]<BR>
     * 証券会社：　@証券会社コードに該当する証券会社オブジェクト<BR>
     * 銘柄コード：　@銘柄コード<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 407640B3028F
     */
    public WEB3IfoProductImpl validateProductCode(String l_strProductCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_strProductCode = " + l_strProductCode);
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //先物プロダクトマネージャオブジェクトを取得する
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        //証券会社オブジェクトを取得する
        Institution l_institution = null;
        try
        {
            l_institution = l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);

            //先物OP銘柄オブジェクトを取得する
            WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);

            log.exiting(STR_METHOD_NAME);
            return l_ifoProductImpl;
        }
        catch (NotFoundException l_nfex)
        {
            //証券会社存在しない場合
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00301);
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate取引可能上限金額)<BR>
     * 売買代金が、会社・部店で一度に取引可能な<BR>
     * 上限金額を超えていないかチェックを行う。<BR>
     * <BR>
     * １）引数の「口座タイプ」、「先物/オプション区分」より、取引可能上限値を決定する。<BR>
     *      ・口座タイプ＝個人アカウント の場合<BR>
     *      先物/オプション区分＝”オプション”の場合： 部店.取引可能金額上限値（個人・OP）<BR>
     *      先物/オプション区分＝”先物”の場合：      部店.取引可能金額上限値（個人・先物）<BR>
     *      ・口座タイプ＝共用アカウント、法@人アカウント の場合<BR>
     *      先物/オプション区分＝”オプション”の場合： 部店.取引可能金額上限値（法@人・OP）<BR>
     *      先物/オプション区分＝”先物”の場合：      部店.取引可能金額上限値（法@人・先物）<BR>
     *      ※口座タイプ、先物/オプション区分が上記以外の場合： 例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00149<BR>
     * <BR>
     * ２）　@（１）で決定した取引可能金額上限値　@＜　@売買代金）の場合、<BR>
     * 取引可能上限値を超過していると判断し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00161<BR>
     * @@param l_branch - 部店<BR>
     * 部店オブジェクト<BR>
     *
     * @@param l_dblTurnover - 売買代金を指定する。<BR>
     * @@param l_mainAccountType - 口座タイプ
     * @@param String - 先物／オプション区分<BR>
     * 0：DEFAULT（先物オプション以外）
     * 1：先物
     * 2：オプション
     * @@throws WEB3BaseException
     * @@roseuid 40765EA0009B
     */
    public void validateOrderMaxAmount(WEB3GentradeBranch l_branch, double l_dblTurnover, MainAccountTypeEnum l_mainAccountType, String l_futuresOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderMaxPrice(l_branch,l_market,l_dblTurnover,l_mainAccountType)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_mainAccountType == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("部店 = " + l_branch.getBranchId());
        log.debug("売買代金 = " + l_dblTurnover);
        log.debug("口座タイプ = " + l_mainAccountType);

        long l_dblOrderMaxPrice = 0L;
        BranchRow l_branchRow = (BranchRow) l_branch.getDataSourceObject();

        //「口座タイプ」より、取引可能上限値を決定する
        if (MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountType))
        {
            //口座タイプ＝個人アカウント の場合
            log.debug("口座タイプ＝個人アカウント の場合");
            //取引可能金額上限値（個人）を取得する
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceIndOptionIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceIndOption();
                }
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceIndFutureIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceIndFuture();
                }
            }

        }
        else if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountType) || MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountType))
        {
            //口座タイプ＝共用アカウント、法@人アカウント の場合
            log.debug("口座タイプ＝共用アカウント、法@人アカウント の場合");
            //部店.取引可能金額上限値（法@人）
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceCorpOptionIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceCorpOption();
                }
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceCorpFutureIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceCorpFuture();
                }
            }
        }
        else
        {
            //口座タイプが上記以外の場合
            log.debug("口座タイプが上記以外の場合");
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00149);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00149, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("取引可能金額上限値 = " + l_dblOrderMaxPrice);
        if (l_dblOrderMaxPrice < l_dblTurnover)
        {
            //取引可能金額上限値　@＜　@売買代金の場合
            log.debug("取引可能金額上限値　@＜　@売買代金の場合");
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00161);
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00161, getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文条件)<BR>
     * 注文条件のチェックを行う。<BR>
     * 　@－執行条件のチェック<BR>
     * 　@－発注条件のチェック<BR>
     * 　@－特殊執行条件取扱停止チェック<BR>
     * 　@－出来るまで注文のチェック<BR>
     * 　@－成行規制チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP注文）validate注文条件」参照。<BR>
     * <BR>
     * １）　@取扱可能注文条件取得<BR>
     * 取扱可能注文条件オブジェクトを生成する。<BR>
     * <BR>
     * [コンストラクタの引数]<BR>
     * 証券会社コード：　@証券会社コード<BR>
     * 銘柄タイプ：　@ProductTypeEnum.”先物オプション”<BR>
     * 先物／オプション区分：　@<BR>
     * 　@先物OP取引銘柄.get銘柄().先物／オプション区分<BR>
     * 　@<BR>
     * ２）　@執行条件のチェック<BR>
     * （is取扱可能執行条件() == false）の場合、<BR>
     * 取扱可能な執行条件ではないと判定し<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00150<BR>
     * <BR>
     * ３）　@発注条件のチェック<BR>
     * （is取扱可能発注条件() == false）の場合、<BR>
     * 取扱可能な発注条件ではないと判定し<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00151<BR>
     * <BR>
     * ４）　@特殊執行条件取扱停止のチェック<BR> 
     * 特殊執行条件取扱停止テーブルを検索し、 <BR>
     * 対象の商品、発注条件が”停止中”だった場合、<BR> 
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02433<BR>
     * <BR>
     * ５）　@出来るまで注文のチェック<BR>
     * ※（引数.注文期限区分が出来るまで注文）の場合のみ実施する。<BR>
     * ５－１）　@注文取扱可能チェック<BR>
     * （is出来るまで注文取扱可能() == false）の場合、<BR>
     * 出来るまで注文取扱い不可と判定し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00151<BR>
     * ５－２）　@失効日のチェック（原注文発注日≠null）<BR>
     * （is出来るまで注文可能日 == false）の場合、<BR>
     * 不正な日付が指定されたと判定し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * ５－３）　@失効日のチェック（原注文発注日＝null）<BR>
     * （is出来るまで注文可能日 == false）の場合、<BR>
     * 不正な日付が指定されたと判定し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * ５－４）　@売買最終日のチェック<BR>
     * （注文失効日 > 先物OP取引銘柄.売買最終日）の場合、<BR>
     * 注文期限エラーと判定し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * ５－５）　@注文繰越スキップ銘柄チェック<BR>
     * （is繰越スキップ銘柄() == true）の場合、<BR>
     * 注文繰越スキップ銘柄の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00684<BR>
     * <BR>
     * ６）　@成行規制チェック<BR>
     * ※成行指定(*1)の場合のみ実施<BR>
     * （is成行注文可能() == false）の場合、<BR>
     * 成行規制中と判定し、例外をスローする。<BR>
     * <BR>
     * (*1)成行指定の判定<BR>
     * １）is成行==true<BR>
     * ２）（is成行==false）＆＆（引数.執行条件==不出来引け成行）<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00154<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_blnIsMarketOrder - (is成行)<BR>
     * （isMarketOrder）<BR>
     * 成行が指定されたかどうかの判定。<BR>
     * 成行の場合true、指値の場合false。<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)
     * 先物OP取引銘柄オブジェクト
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * （isOpenContract）<BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     *
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買建取引かどうかの判定。<BR>
     * 買建の場合true、売建の場合false。<BR>
     * @@param l_datOrderBizDate - 原注文発注日<BR>
     * @@param l_datExpirationDate - 注文失効日<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * 　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値<BR>
     * @@param l_executionConditionType - 執行条件<BR>
     * @@param l_strExpirationDateType - 注文期限区分<BR>
     * @@param l_firstOrderUnitId - 初回注文の注文単位ID<BR>
     * 初回注文の注文単位ID。<BR>
     * （繰越時にのみ設定される。以外はnull）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40766AB1029F
     */
    public void validateOrderCond(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        boolean l_blnIsMarketOrder,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyToOpenOrder,
        Date l_datOrderBizDate,
        Date l_datExpirationDate,
        String l_strOrderCond,
        IfoOrderExecutionConditionType l_executionConditionType,
        String l_strExpirationDateType,
        Long l_firstOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCond(WEB3GentradeSubAccount,long,boolean,"
                + "WEB3IfoTradedProductImpl,boolean,boolean,Date,Date,"
                + "String,IfoOrderExecutionConditionType,String,Long)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("証券会社コード = " + l_subAccount.getInstitution().getInstitutionCode());
        log.debug("引数の注文単位ID = " + l_lngOrderUnitId);
        log.debug("is成行 = " + l_blnIsMarketOrder);
        log.debug("先物OP取引銘柄.取引銘柄ID = " + l_ifoTradedProduct.getTradedProductId());
        log.debug("is新規建 = " + l_blnIsOpenContract);
        log.debug("is買建 = " + l_blnIsBuyToOpenOrder);
        log.debug("原注文発注日 = " + l_datOrderBizDate);
        log.debug("注文失効日 = " + l_datExpirationDate);
        log.debug("発注条件 = " + l_strOrderCond);
        log.debug("執行条件 = " + l_executionConditionType);
        log.debug("注文期限区分 = " + l_strExpirationDateType);

        IfoProductRow l_ifoProductRow = (IfoProductRow) (
            (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct()).getDataSourceObject();

        //１）　@取扱可能注文条件取得
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_ifoProductRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

        //２）　@執行条件のチェック
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionConditionType))
        {
            log.debug("is取扱可能執行条件() == falseの場合");
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("is取扱可能執行条件() == trueの場合");

        //３）　@発注条件のチェック
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderCond))
        {
            log.debug("is取扱可能発注条件() == falseの場合");
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("is取扱可能発注条件() == trueの場合");

        //４）validate特殊執行条件取扱停止()
        this.validateTriggerOrderStop(
            l_subAccount,
            l_lngOrderUnitId,
            l_strOrderCond,
            l_ifoProductRow.getFutureOptionDiv()
            );

        //　@当日限り注文の場合 
        if(l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
        {
            log.debug("当日限り注文の場合");
        }
        //　@出来るまで注文の場合 
        else if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            log.debug("出来るまで注文の場合");
            //５）　@出来るまで注文のチェック

            //set取引最終日()
            l_handlingOrderCond.setTradingEndDate(l_ifoTradedProduct.getLastTradingDate());

            //is出来るまで注文取扱可能<取引最終日考慮無>( )
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering())
            {
                log.debug("is出来るまで注文取扱可能() == falseの場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                    getClass().getName() + STR_METHOD_NAME);
            }

            log.debug("is出来るまで注文取扱可能() == trueの場合");

            //５－２）、５－３）　@失効日のチェック
            
            // 原注文発注日 != null の場合
            if (l_datOrderBizDate != null)
            {
                log.debug("原注文発注日 != null");
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOrderBizDate))
                {
                    log.debug("is出来るまで注文可能日 == falseの場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            // 原注文発注日 == null の場合
            else
            {
                log.debug("原注文発注日 == null");
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    log.debug("is出来るまで注文可能日 == falseの場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            log.debug("is出来るまで注文可能日 == trueの場合");

            //５－４）　@売買最終日のチェック
            
            //注文失効日 > 先物OP取引銘柄.売買最終日の場合、注文期限エラー
            if (WEB3DateUtility.compareToDay(l_datExpirationDate, l_ifoTradedProduct.getLastTradingDate()) > 0)
            {
                log.debug("注文失効日 > 先物OP取引銘柄.売買最終日の場合"); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                    getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("注文失効日 <= 先物OP取引銘柄.売買最終日の場合");    

            //５－５）　@注文繰越スキップ銘柄チェック
            if (l_lngOrderUnitId == 0)
            {
                //新規注文の場合のみチェックする。(パラメータ.注文単位ID == 0)
                if (l_ifoTradedProduct.isCarryOverSkipProduct())
                {
                    log.debug("is繰越スキップ銘柄() == trueの場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00684,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.debug("is繰越スキップ銘柄() == falseの場合");
            }
        }

        //validate夕場まで注文取扱可能(boolean, String, long, Long)
        this.validateEveningSessionOrderPossibleHandling(
            l_handlingOrderCond.isEveningSessionOrderPossibleHandling(),
            l_strExpirationDateType,
            l_lngOrderUnitId,
            l_firstOrderUnitId);

        //validate売買最終日<夕場>(先物OP取引銘柄, String)
        this.validateEveningSessionLastTradingDate(l_ifoTradedProduct, l_strExpirationDateType,l_datOrderBizDate);

        //成行、または、不出来引け成行の場合
        if ((l_blnIsMarketOrder)||
            ((!l_blnIsMarketOrder)&&l_executionConditionType.equals(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED)))
        {
            log.debug("成行、または、不出来引け成行の場合");
            
            //６）　@成行規制チェック
            if (!l_handlingOrderCond.isMarketOrderPossible(l_blnIsOpenContract, l_blnIsBuyToOpenOrder))
            {
                log.debug("is成行注文可能() == falseの場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00154,
                    getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("is成行注文可能() == trueの場合");
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get成行時計算単価)<BR>
     * 成行時の計算単価を取得する。<BR>
     * <BR>
     * 手続きについては、<BR>
     * 「基本設計計算式書（株価指数OP）.doc」　@6.成行時計算単価参照。<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄オブジェクト
     * @@param l_branch - 部店オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4076AB7C0118
     */
    public double getMakeOrderCalcUnitPrice(WEB3IfoTradedProductImpl l_ifoTradedProduct, WEB3GentradeBranch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMakeOrderCalcUnitPrice(l_ifoTradedProduct,l_branch)";

        log.entering(STR_METHOD_NAME);
        //パラメータをチェック
        if (l_ifoTradedProduct == null || l_branch == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("先物OP取引銘柄オブジェクト = " + l_ifoTradedProduct.getTradedProductId());
        log.debug("部店オブジェクト = " + l_branch.getBranchId());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //単価
        double l_dblUnitPrice = 0D;

        //部店IDを取得する
        long l_lngBranchID = 0L;
        l_lngBranchID = l_branch.getBranchId();

        //先物OP銘柄を取得する
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct();

        //手数料商品コードを取得する
        String l_strCommissionProductCode = null;
        l_strCommissionProductCode = l_ifoProductImpl.getCommissionProductCode();
        log.debug("手数料商品コード = " + l_strCommissionProductCode);

        //①@ 計算方式取得
        //会社部店商品テーブルオブジェクト
        InstBranchProductRow l_instBranchProductRow = null;

        //会社部店商品オブジェクトを取得する
        try
        {
            //throw DataFindException,DataQueryException,DataNetworkException
            l_instBranchProductRow = InstBranchProductDao.findRowByPk(l_lngBranchID, l_strCommissionProductCode);

            //OP注文マネージャオブジェクトを取得する
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            if (l_instBranchProductRow.getEstimatePriceCalcFormIsNull() == false)
            {

                //概算金額計算方式がある場合
                log.debug("概算金額計算方式がある場合");
                log.debug("概算金額計算方式 = " + l_instBranchProductRow.getEstimatePriceCalcForm());

                if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT == l_instBranchProductRow.getEstimatePriceCalcForm())
                {
                    //概算金額計算方式 = “割増拘束方式”の場合
                    log.debug("概算金額計算方式 = “割増拘束方式”の場合");
                    //時価を取得する
                    l_dblUnitPrice = l_ifoProductManagerImpl.getCurrentPrice(l_ifoTradedProduct);
                }
                else if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_instBranchProductRow.getEstimatePriceCalcForm())
                {
                    //概算金額計算方式 = “STOP高拘束方式”の場合
                    log.debug("概算金額計算方式 = “STOP高拘束方式”の場合");
                    //STOP高算出基準値を取得
                    double l_dblStopQuantityBasePrice = 0D;

                    if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() == true)
                    {
                        //場中の場合
                        log.debug("場中の場合");
                        //先物OP取引銘柄.基準値（終値） をSTOP高算出基準値とする
                        l_dblStopQuantityBasePrice = ((IfoTradedProductRow) l_ifoTradedProduct.getDataSourceObject()).getLastClosingPrice();
                    }
                    else
                    {
                        //引け後の場合
                        log.debug("引け後の場合");
                        //先物OPプロダクトマネージャ.get時価()(*1)にて時価を取得し、STOP高算出基準値とする
                        l_dblStopQuantityBasePrice = l_ifoProductManagerImpl.getCurrentPrice(l_ifoTradedProduct);
                    }

                    //STOP高算出

                    //制限値幅を取得
                    double l_dblDeregulatedPriceRange = 0D;
                    l_dblDeregulatedPriceRange = l_ifoProductManagerImpl.getDeregulatedPriceRange(l_ifoProductImpl, l_dblStopQuantityBasePrice);
                    log.debug("制限値幅 = " + l_dblDeregulatedPriceRange);

                    //刻み値を取得
                    double l_dblTickValue = 0D;
                    l_dblTickValue = l_ifoProductManagerImpl.getTickValue(l_ifoProductImpl, l_dblStopQuantityBasePrice);
                    log.debug("刻み値 = " + l_dblTickValue);

                    //値幅基準値を取得
                    double l_dblBasePrice = 0D;
                    l_dblBasePrice = this.calcBasePrice(l_dblStopQuantityBasePrice, l_dblTickValue);
                    log.debug("値幅基準値 = " + l_dblBasePrice);

                    //値幅上限（STOP高）算出
                    l_dblUnitPrice = this.calcStopHighPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);
                    log.debug("値幅上限（STOP高） = " + l_dblUnitPrice);
                }
                else
                {
                    //例外をスローする
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                //例外をスローする
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataFindException l_dfe)
        {
            //DBアクセスが失敗の場合
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DBアクセスが失敗の場合
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DBアクセスが失敗の場合
            //例外をスローする
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblUnitPrice;
    }

    /**
     * (validate銘柄ＩＤ)<BR>
     * 銘柄のチェックを行い、銘柄オブジェクトを返却する。<BR>
     * <BR>
     * １）　@銘柄オブジェクト取得<BR>
     * 先物プロダクトマネージャ.getProduct()<BR>
     * にて先物OP銘柄オブジェクトを取得する。<BR>
     * <BR>
     * [getProduct()に指定する引数]<BR>
     * 銘柄ＩＤ：　@銘柄コード<BR>
     * @@param l_lngProductID - 銘柄ＩＤ<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 407CDF9D0138
     */
    public WEB3IfoProductImpl validateProductID(long l_lngProductID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //先物プロダクトマネージャオブジェクトを取得する
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        try
        {
            l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoProductManagerImpl.getProduct(l_lngProductID);
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, getClass().getName() + STR_METHOD_NAME);
        }

        return l_ifoProductImpl;
    }

    /**
     * (validate市場ＩＤ)<BR>
     * 市場のチェックを実施する。<BR>
     * 存在する場合は市場オブジェクトを返却する。<BR>
     * （validateMarketのオーバーロード）<BR>
     * <BR>
     * １）　@拡張金融オブジェクトマネージャ.get市場（）<BR>
     * にて該当する市場オブジェクトを生成する。<BR>
     * 市場が取得できない場合は、該当市場なしと判断し、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00003<BR>
     * <BR>
     * ２）　@市場オブジェクトを返却する。<BR>
     * @@param l_lngMarketID - 市場ＩＤ<BR>
     * @@return Market
     * @@throws WEB3BaseException
     * @@roseuid 407CE7AD033C
     */
    public Market validateMarketID(long l_lngMarketID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarketID(l_lngMarketID)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_lngMarketID = " + l_lngMarketID);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Market l_market = null;
        try
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketID);

        }
        catch (NotFoundException l_nfex)
        {
            //市場が取得できない場合
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        return l_market;
    }

    /**
     * (validate訂正内容)<BR>
     * 訂正入力値が妥当であるかをチェックする。<BR>
     * <BR>
     * １）　@数量のチェック<BR>
     * isChange数量()をコールし、<BR>
     * 数量に訂正が入ったかを判定、訂正数量の妥当性をチェックする。<BR>
     * <BR>
     * [isChange数量()に指定する引数]<BR>
     * 注文単位：　@注文単位<BR>
     * 訂正数量：　@訂正数量<BR>
     * <BR>
     * ２）　@注文単価の判定<BR>
     * isChange単価()をコールし、<BR>
     * 注文単価に訂正が入ったかを判定する。<BR>
     * <BR>
     * [isChange単価()に指定する引数]<BR>
     * 注文単位：　@注文単位<BR>
     * 訂正指値：　@訂正指値<BR>
     * <BR>
     * ３）　@執行条件の判定<BR>
     * isChange執行条件()をコールし、<BR>
     * 執行条件に訂正が入ったかを判定する。<BR>
     * <BR>
     * [isChange執行条件()に指定する引数]<BR>
     * 注文単位：　@注文単位<BR>
     * 訂正執行条件：　@訂正執行条件<BR>
     * <BR>
     * ４）　@同時訂正チェック <BR>
     * （数量と注文単価または執行条件を訂正している場合のみ） <BR>
     * <BR>
     * 　@４－１）取引所が休憩時間帯(*1)かつ前場発注済注文(*2)の場合 <BR>
     * <BR>
     * 　@市場に通知済みの値を使用し、｢同時訂正｣であるかどうか判定する。 <BR>
     * <BR>
     * 　@（注文単位.市場から確認済みの数量　@!=　@訂正数量） && <BR>
     * 　@（注文単位.市場から確認済みの指値　@!=　@訂正指値） または <BR>
     * 　@（注文単位.市場から確認済みの数量　@!=　@訂正数量） && <BR>
     * 　@（注文単位.市場から確認済みの執行条件　@!=　@訂正執行条件）の場合、 <BR>
     * ｢同時訂正｣と判定する。 <BR>
     * <BR>
     * 　@　@(*1)取引所が休憩時間帯の場合 <BR>
     * 　@　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。 <BR>
     * <BR>
     * 　@　@(*2)前場発注済注文 <BR>
     * 　@　@　@　@　@注文単位.市場から確認済の数量 != NaNの場合は、前場発注済注文であると判定する。 <BR>
     * <BR>
     * 　@４－２）　@４－１）以外の場合 <BR>
     * <BR>
     * 　@（this.isChange数量()　@==　@true）　@&&　@（this.isChange単価()　@==　@true）、または、 <BR>
     * 　@（this.isChange数量()　@==　@true）　@&&　@（this.isChange執行条件()　@==　@ture）の場合、<BR>
     * 　@｢同時訂正｣と判定する。 <BR>
     * <BR>
     * 　@４－３）　@４－１）または４－２）で「同時訂正」と判定された場合、 <BR>
     * 以下処理を行なう。 <BR>
     * 　@注文単位.市場ＩＤに該当する市場オブジェクトを取得する。 <BR>
     * 　@取得した市場.同時訂正可能区分==”複数項目同時訂正不可”の場合 <BR>
     * 　@例外をスローする。 <BR>
     * 　@　@class :  WEB3BusinessLayerException<BR>
     * 　@　@tag　@ :  BUSINESS_ERROR_00687<BR>
     * 　@※休憩時間帯／休憩時間帯以外でエラーメッセージを分ける。<BR>
     * ５）　@発注条件が訂正されていないことのチェック <BR>
     * isChange発注条件()をコールし、<BR>
     * 発注条件に訂正が入ったかを判定する。 <BR>
     *<BR>
     * [isChange発注条件()に指定する引数] <BR>
     * 注文単位：　@注文単位 <BR>
     * 訂正発注条件：　@訂正発注条件 <BR>
     *<BR>
     *<BR>
     * ６）　@注文期限区分が訂正されていないことのチェック  <BR>
     * 原注文の注文期限区分(*)≠引数.訂正注文期限区分の場合 <BR>
     * 例外をスローすること。 <BR>
     * <BR>
     *  (*)原注文の注文期限区分 <BR>
     *　@ 　@先物OPデータアダプタ.get注文期限区分()をコールする。 <BR>
     *<BR>
     * 　@　@[get注文期限区分()に指定する引数] <BR>
     * 　@　@注文単位：　@引数.注文単位 <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_02102 <BR>
     *<BR>
     * ７）　@（発注済みの）逆指値注文が訂正されていないことのチェック <BR>
     * isChange逆指値注文()をコールし、 <BR>
     * 逆指値注文に訂正が入ったかを判定する。 <BR>
     *<BR>
     * [isChange逆指値注文()に指定する引数] <BR>
     * 注文単位：　@注文単位 <BR>
     * 訂正発注条件：　@訂正発注条件 <BR>
     * 訂正発注条件演算子：　@訂正発注条件演算子 <BR>
     * 訂正逆指値基準値タイプ：　@訂正逆指値基準値タイプ <BR>
     * 訂正逆指値基準値：　@訂正逆指値基準値 <BR>
     *<BR>
     *<BR>
     * ８）　@W指値注文が訂正されていないことのチェック <BR>
     * isChangeW指値条件()をコールし、 <BR>
     * W指値注文に訂正が入ったかを判定する。<BR>
     *<BR>
     * [isChangeW指値条件()に指定する引数] <BR>
     * 注文単位：　@注文単位 <BR>
     * 訂正発注条件：　@訂正発注条件 <BR>
     * 訂正発注条件演算子：　@訂正発注条件演算子 <BR>
     * 訂正逆指値基準値タイプ：　@訂正逆指値基準値タイプ <BR>
     * 訂正逆指値基準値：　@訂正逆指値基準値 <BR>
     * 訂正（W指値）訂正指値：　@訂正（W指値）訂正指値 <BR>
     * 訂正（W指値）執行条件：　@訂正（W指値）執行条件 <BR>
     *<BR>
     * ９）　@注文失効日付が訂正されていないことのチェック <BR>
     * isChange注文失効日()をコールし、 <BR>
     * 注文失効日付に訂正が入ったかを判定する。 <BR>
     *<BR>
     * [isChange注文失効日()に指定する引数] <BR>
     * 注文単位：　@注文単位 <BR>
     * 訂正注文失効日：　@訂正注文失効日 <BR>
     *<BR>
     * １０）　@返済数量内訳が訂正されていないことのチェック <BR>
     * isChange返済数量内訳()をコールし、 <BR>
     * 返済数量内訳に訂正が入ったかを判定する。 <BR>
     *<BR>
     * [isChange返済数量内訳()に指定する引数] <BR>
     * 注文単位：　@注文単位 <BR>
     * 訂正返済建玉エントリ：　@訂正返済建玉エントリ <BR>
     *<BR>
     * １１）　@訂正が入っているかのチェック <BR>
     * isChange数量()、isChange単価()、<BR>
     * isChange執行条件()、isChange逆指値注文()、 <BR>
     * isChangeW指値注文()、isChange注文失効日()、 <BR>
     * isChange返済数量内訳()<BR>
     * のすべてがfalseを返却した場合、 <BR>
     *　@ 訂正元注文から何も訂正されていないと判断し、<BR>
     *　@ 「訂正入力なし」の例外をスローする。 <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00039 <BR>
     *<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 原注文（訂正元注文）の注文単位オブジェクト<BR>
     * @@param l_dblQuantityAfterChange - 訂正数量
     * @@param l_dblLimitPrice - 訂正指値
     * @@param l_executionConditionType - 訂正執行条件
     * @@param l_strOrderConditionType - 訂正発注条件
     * @@param l_strOrderCondOperator - 訂正発注条件演算子
     * @@param l_strStopPriceType - 訂正逆指値基準値タイプ
     * @@param l_dblStopPrice - 訂正逆指値基準値
     * @@param l_dblWStopPrice - 訂正（W指値）訂正指値
     * @@param l_wLimitExecCondType - 訂正（W指値）執行条件
     * @@param l_datExpriationDate - 訂正注文失効日
     * @@param l_strExpirationDateType - 訂正注文期限区分
     * @@param l_modifiedSettleContractEntries - 訂正返済建玉エントリ
     * @@throws WEB3BaseException
     * @@roseuid 407CF81803DC
     */
    public void validateOrderChangeSpec(
        OrderUnit l_orderUnit,
        double l_dblQuantityAfterChange,
        double l_dblLimitPrice,
        IfoOrderExecutionConditionType l_executionConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice,
        double l_dblWStopPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        Date l_datExpriationDate,
        String l_strExpirationDateType,
        SettleContractEntry[] l_modifiedSettleContractEntries)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangeSpec(OrderUnit, double,double,IfoOrderExecutionConditionType," +
                "String ,String ,String ,double ,double ,Date ,String ,SettleContractEntry[] )";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        //１）　@数量のチェック 
        boolean l_blnIsChangeQuantity = 
            this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange);
        
        //２）　@注文単価の判定 
        //isChange単価()をコールし、 
        //注文単価に訂正が入ったかを判定する。 
        boolean l_blnIsChangeUnitPrice = 
            this.isChangeUnitPrice(l_orderUnit, l_dblLimitPrice);
        
        //３）　@執行条件の判定 
        boolean l_blnIsChangeExecCondType = 
            this.isChangeExecCondType(l_orderUnit, l_executionConditionType);

        //同時訂正チェック
        //（数量と注文単価または執行条件を訂正している場合のみ）
        //取引所が休憩時間帯(*1)かつ前場発注済注文(*2)の場合
        //市場に通知済みの値を使用し、｢同時訂正｣であるかどうか判定する。
        //  （注文単位.市場から確認済みの数量　@!=　@訂正数量） &&
        //　@（注文単位.市場から確認済みの指値　@!=　@訂正指値） または
        //　@（注文単位.市場から確認済みの数量　@!=　@訂正数量） &&
        //　@（注文単位.市場から確認済みの執行条件　@!=　@訂正執行条件）の場合、
        //｢同時訂正｣と判定する。
        //    (*1)取引所が休憩時間帯の場合
        //　@　@　@　@　@取引時間管理.is取引所休憩時間帯()==trueの場合は、休憩時間帯であると判定する。
        //　@　@(*2)前場発注済注文
        //　@　@　@　@　@注文単位.市場から確認済の数量 != NaNの場合は、前場発注済注文であると判定する。
        boolean l_blnIsSameTimeChange = false;
        boolean l_blnIsTradeCoseTimeZone =
            WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_blnIsTradeCoseTimeZone && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            if ((l_orderUnitRow.getConfirmedQuantity() != l_dblQuantityAfterChange
                && l_orderUnitRow.getConfirmedPrice() != l_dblLimitPrice)
                || (l_orderUnitRow.getConfirmedQuantity() != l_dblQuantityAfterChange
                && l_orderUnitRow.getExecutionConditionType() != l_executionConditionType))
            {
                l_blnIsSameTimeChange = true;
            }
        }
        //以外の場合
        //（this.isChange数量()　@==　@true）　@&&　@（this.isChange単価()　@==　@true）、または、
        //　@（this.isChange数量()　@==　@true）　@&&　@（this.isChange執行条件()　@==　@ture）の場合、
        //　@｢同時訂正｣と判定する。
        else if ((this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange)
            && this.isChangeUnitPrice(l_orderUnit, l_dblLimitPrice))
            || (this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange)
            && this.isChangeExecCondType(l_orderUnit, l_executionConditionType)))
        {
            l_blnIsSameTimeChange = true;
        }

        //「同時訂正」と判定された場合、
        //以下処理を行なう。
        if (l_blnIsSameTimeChange)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market = null;
            try
            {
                //注文単位.市場ＩＤに該当する市場オブジェクトを取得する。
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                //市場が取得できない場合
                //例外をスローする
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();

            //取得した市場.同時訂正可能区分==”複数項目同時訂正不可”の場合
            //例外をスローする
            //※休憩時間帯／休憩時間帯以外でエラーメッセージを分ける。 
            if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(
                l_marketRow.getChangeableType()))
            {
                //休憩時間帯
                if (l_blnIsTradeCoseTimeZone)
                {
                    log.debug("複数項目同時訂正不可（休憩時間帯）。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02197,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
                //休憩時間帯以外
                else
                {
                    log.debug("複数項目同時訂正不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        //５）　@発注条件が訂正されていないことのチェック 
        //isChange発注条件()をコールし、 
        //発注条件に訂正が入ったかを判定する。 
        this.isChangeOrderCondType(l_orderUnit, l_strOrderConditionType);
        
        //６）　@注文期限区分が訂正されていないことのチェック 
        //(*)原注文の注文期限区分 
        //　@先物OPデータアダプタ.get注文期限区分()をコールする。 
        String l_strOrgExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

        //原注文の注文期限区分(*)≠引数.訂正注文期限区分の場合 
        //例外をスローすること。 
        if (!l_strOrgExpirationDateType.equals(l_strExpirationDateType))
        {
            log.debug("注文期限区分が一致しないため、訂正不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02102,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文期限区分が一致しないため、訂正不可です。");
        }

        //７）　@逆指値注文が訂正されていないことのチェック 
        //isChange逆指値条件()をコールし、 
        //逆指値注文に訂正が入ったかを判定する。 
        boolean l_blnIsChangeStopCond = 
            isChangeStopCond(
                l_orderUnit,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceType,
                l_dblStopPrice);
        
        //８）　@W指値注文が訂正されていないことのチェック 
        //isChangeW指値条件()をコールし、 
        //W指値注文に訂正が入ったかを判定する。 
        //
        //[isChangeW指値条件()に指定する引数] 
        //注文単位：　@注文単位 
        //訂正発注条件：　@訂正発注条件 
        //訂正発注条件演算子：　@訂正発注条件演算子 
        //訂正逆指値基準値タイプ：　@訂正逆指値基準値タイプ 
        //訂正逆指値基準値：　@訂正逆指値基準値 
        //訂正（W指値）訂正指値：　@訂正（W指値）訂正指値 
        //訂正（W指値）執行条件：　@訂正（W指値）執行条件 
        boolean l_blnIsChangeWStopCond = 
            isChangeWStopCond(
                l_orderUnit,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceType,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType);
        
        //９）　@注文失効日付が訂正されていないことのチェック 
        //isChange注文失効日()をコールし、 
        //注文失効日付に訂正が入ったかを判定する。 
        boolean l_blnIsChangeExpirationDate = 
            isChangeExpirationDate(l_orderUnit,l_datExpriationDate);
        
        //１０）　@返済数量内訳が訂正されていないことのチェック 
        //isChange返済数量内訳()をコールし、 
        //返済数量内訳に訂正が入ったかを判定する。 
        //
        //[isChange返済数量内訳()に指定する引数] 
        //注文単位：　@注文単位 
        //訂正後返済建玉エントリ：　@訂正後返済建玉エントリ 
        boolean l_blnIsChangeEachQuantityOfCloseContract =
            isChangeEachQuantityOfCloseContract(l_orderUnit,l_modifiedSettleContractEntries);
       
        //１１）　@訂正が入っているかのチェック 
        //isChange数量()、isChange単価()、 
        //isChange執行条件()、isChange逆指値条件()、 
        //isChangeW指値条件()、isChange注文失効日()、 
        //isChange返済数量内訳() 
        //のすべてがfalseを返却した場合、  
        //　@訂正元注文から何も訂正されていないと判断し、  
        //　@「訂正入力なし」の例外をスローする。  
        if (!l_blnIsChangeQuantity && !l_blnIsChangeUnitPrice
            && !l_blnIsChangeExecCondType && !l_blnIsChangeStopCond
            && !l_blnIsChangeWStopCond && !l_blnIsChangeExpirationDate
            && !l_blnIsChangeEachQuantityOfCloseContract) 
        {
            log.debug("訂正された項目が１つもありません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00039,
                this.getClass().getName() + STR_METHOD_NAME);
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isChange数量)<BR>
     * 数量に訂正が入っているか判定する。<BR>
     * <BR>
     *（注文単位.getQuantity == 訂正数量）の場合、falseを返却する。<BR>
     * <BR>
     * 以外の場合、以下のチェックを行い、trueを返却する。<BR>
     * －（約定済数量(*1)　@＞　@訂正数量）の場合、例外をスローする。<BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00142 <BR>
     * －（注文数量(*2)　@＜　@訂正数量）の場合、例外をスローする。<BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00143 <BR>
     * <BR>
     * (*1) 約定数量<BR>
     * 注文単位.getExecutedQuantity()<BR>
     * (*2) 注文数量<BR>
     * 注文単位.getQuantity()<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 原注文（訂正元注文）の注文単位オブジェクト<BR>
     * @@param l_dblChangeQuantity - 訂正数量<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D098E0023
     */
    protected boolean isChangeQuantity(
        OrderUnit l_orderUnit,
        double l_dblChangeQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeQuantity(l_orderUnit,l_dblChangeQuantity)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //約定数量
        double l_dblExcutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExcutedQuantity))
        {
            l_dblExcutedQuantity = 0;
        }

        //注文数量
        double l_dblQuantity = l_orderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }

        log.debug("約定数量 = " + l_dblExcutedQuantity);
        log.debug("注文数量 = " + l_dblQuantity);
        log.debug("訂正数量 = " + l_dblChangeQuantity);

        // 注文単位.getQuantity == 訂正数量）の場合
        boolean l_blnIsChangeQuantity = true;
        if (l_dblQuantity == l_dblChangeQuantity)
        {
            //falseを返却する
            l_blnIsChangeQuantity = false;
        }
        // 以外の場合、以下のチェックを行い、trueを返却する
        else
        {
            //（約定済数量＞　@訂正数量）の場合
            if (l_dblExcutedQuantity > l_dblChangeQuantity)
            {
                //例外をスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00142,
                    this.getClass().getName() + STR_METHOD_NAME);

            }

            //（注文数量  ＜　@訂正数量）の場合
            if (l_dblQuantity < l_dblChangeQuantity)
            {
                //例外をスローする
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00143,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsChangeQuantity;
    }

    /**
     * (isChange単価)<BR>
     * 単価に訂正が入っているか判定する。<BR>
     * <BR>
     * （注文単位.getLimitPrice == 訂正指値）の場合、falseを返却する。<BR>
     * 以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 原注文（訂正元注文）の注文単位オブジェクト<BR>
     *
     * @@param l_dblChangeLimitPrice - 訂正指値<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D118C02E2
     */
    protected boolean isChangeUnitPrice(OrderUnit l_orderUnit, double l_dblChangeLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeUnitPrice(l_orderUnit,l_dblChangeLimitPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //例外をスローする
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        boolean l_blnIsChangeUnitPrice = false;

        //指値を取得する
        double l_dblLimitPrice = 0D;

        if (((IfoOrderUnitRow) l_orderUnit.getDataSourceObject()).getLimitPriceIsNull())
        {
            return false;
        }
        else
        {
            l_dblLimitPrice = l_orderUnit.getLimitPrice();
        }

        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblChangeLimitPrice = " + l_dblChangeLimitPrice);

        if (l_dblLimitPrice == l_dblChangeLimitPrice)
        {
            l_blnIsChangeUnitPrice = false;
        }
        else
        {
            l_blnIsChangeUnitPrice = true;
        }

        log.debug("l_blnIsChangeUnitPrice = " + l_blnIsChangeUnitPrice);

        log.exiting(STR_METHOD_NAME);

        return l_blnIsChangeUnitPrice;
    }

    /**
     * (isChange執行条件)<BR>
     * 執行条件に訂正が入っているか判定する。<BR>
     * <BR>
     *（注文単位.執行条件 == 訂正執行条件）の場合、falseを返却する。<BR>
     * 以外、trueを返却する。<BR>
     * @@param l_orderUnit - 原注文（訂正元注文）の注文単位オブジェクト
     * @@param l_orderExecutionConditionType - 訂正執行条件
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeExecCondType(
        OrderUnit l_orderUnit,
        IfoOrderExecutionConditionType l_orderExecutionConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeExecCondType(l_orderUnit,l_orderExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_orderExecutionConditionType == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 注文単位.執行条件
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        IfoOrderExecutionConditionType l_executionConditionType =
            l_ifoOrderUnitRow.getExecutionConditionType();

        //（注文単位.執行条件 == 訂正執行条件）の場合、falseを返却する。
        // 以外、trueを返却する。
        boolean isChangeExecCondType = true;
        if (l_orderExecutionConditionType.equals(l_executionConditionType))
        {
            isChangeExecCondType = false;
        }

        log.exiting(STR_METHOD_NAME);
        return isChangeExecCondType;
    }

    /**
     * 取扱可能な新規建注文かどうかのチェックを行う。<BR>
     *１）補助口座オブジェクトから、補助口座タイプを取得する。<BR>
     *２）以下の条件と一致する場合、例外をスローする。
     *補助口座タイプ != 7（先物証拠金口座） andis買建 == false<BR>
     * @@param l_subAccount
     * @@param l_blnIsBuy
     */
    public void validateHandlingOpenContractOrder(SubAccount l_subAccount, boolean l_blnIsBuy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingOpenContractOrder";
        log.entering(STR_METHOD_NAME);
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()) && !l_blnIsBuy)
        {
            //例外をスローする
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01094);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01094, getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (isChange発注条件)<BR>
     * 発注条件に訂正が入っているか判定する。<BR>
     * <BR>
     * （注文単位.発注条件 == 訂正発注条件）の場合、 <BR>
     * <BR>
     * falseを返却する。 <BR>
     * <BR>
     * 以外、例外をスローする。 <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00071 <BR>
     * @@param l_orderUnit - 原注文（訂正元注文）の注文単位オブジェクト <BR>
     * @@param l_strChangeOrderCondType - 訂正発注条件<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeOrderCondType(OrderUnit l_orderUnit, String l_strChangeOrderCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeOrderCond(OrderUnit l_orderUnit, String l_strChangeOrderCondType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_strChangeOrderCondType == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_strChangeOrderCondType.equals(l_orderUnitRow.getOrderConditionType()))
        {
            return false;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00071,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (isChange逆指値条件)<BR>
     * 逆指値の条件（逆指値基準値、発注条件演算子）に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）引数.訂正発注条件 ≠”逆指値”の場合は、<BR>
     *  以降の処理は行わずfalseを返す。<BR>
     * <BR>
     * ２）引数.訂正発注条件 ＝”逆指値”の場合、 <BR>
     *  発注済みの逆指値注文 <BR>
     * （注文単位.リクエストタイプ == ”1：時価サーバ”）の場合、 <BR>
     * <BR>
     * （注文単位.発注条件 == 訂正発注条件 && <BR>
     * 　@注文単位.発注条件演算子 == 訂正発注条件演算子 && <BR>
     * 　@注文単位.逆指値基準値タイプ == 訂正逆指値基準値タイプ && <BR>
     * 　@注文単位.逆指値基準値 == 訂正逆指値基準値）の場合、<BR>
     * <BR>
     * 上記条件にあてはまらない場合は例外をスローする。 <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00141 <BR>
     * <BR>
     * ３）（注文単位.発注条件 == 訂正発注条件 かつ <BR>
     * 　@　@　@注文単位.発注条件演算子 == 訂正発注条件演算子 かつ <BR>
     * 　@　@　@注文単位.逆指値基準値タイプ == 訂正逆指値基準値タイプ かつ <BR>
     * 　@　@　@注文単位.逆指値基準値 == 訂正逆指値基準値） の場合、 <BR>
     * <BR>
     *      falseを返却する。以外はtrueを返却する。 <BR>
     * @@param l_orderUnit - 原注文（訂正元注文）の注文単位オブジェクト  <BR>
     * @@param l_strOrderExecutionConditionType - 訂正発注条件<BR>
     * @@param l_strOrderCondOperator - 訂正発注条件演算子<BR>
     * @@param l_strStopPriceType - 訂正逆指値基準値タイプ<BR>
     * @@param l_dblStopPrice - 訂正逆指値基準値<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeStopCond(
        OrderUnit l_orderUnit,
        String l_strOrderExecutionConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeStopCond(l_orderUnit, l_strOrderConditionType, " +
                "l_strOrderCondOperator, l_strStopPriceType, l_dblStopPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        // １）引数.訂正発注条件 ≠”逆指値”の場合は、
        //     以降の処理は行わずfalseを返す。
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_strOrderExecutionConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // ２）　@引数.訂正発注条件 ＝”逆指値”の場合、発注済みの逆指値注文
        else
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // （注文単位.リクエストタイプ == ”1：時価サーバ”）の場合、
            if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
            {
                // 注文単位.発注条件 == 訂正発注条件 &&
                // 注文単位.発注条件演算子 == 訂正発注条件演算子 &&
                // 注文単位.逆指値基準値タイプ == 訂正逆指値基準値タイプ &&
                // 注文単位.逆指値基準値 == 訂正逆指値基準値の場合、
                // 上記条件にあてはまらない場合は例外をスローする。
                if (!(isEquals(l_strOrderExecutionConditionType, l_orderUnitRow.getOrderConditionType())
                    && isEquals(l_strOrderCondOperator, l_orderUnitRow.getOrderCondOperator())
                    && isEquals(l_strStopPriceType, l_orderUnitRow.getStopPriceType())
                    && l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice))
                {
                    //例外をスローする
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00141,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            // 条件成立の場合、falseを返却する。
            // 以外はtrueを返却する。
            if (isEquals(l_strOrderExecutionConditionType, l_orderUnitRow.getOrderConditionType())
                && isEquals(l_strOrderCondOperator, l_orderUnitRow.getOrderCondOperator())
                && isEquals(l_strStopPriceType, l_orderUnitRow.getStopPriceType())
                && l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    /**
     * (isChangeW指値条件)<BR>
     * W指値の条件（発注条件演算子、逆指値基準値、（W指値）訂正指値）に  <BR>
     * 訂正が入ったかをチェックする。  <BR>
     * <BR>
     * １）　@引数.訂正発注条件 ≠”W指値”の場合は、以降の処理は行わずfalseを返す。<BR>  
     * <BR>
     * ２）　@引数.訂正発注条件 ＝”W指値”の場合、 <BR>
     * 　@　@（注文単位.発注条件演算子） == （訂正発注条件演算子） かつ、<BR>
     * 　@　@（注文単位.逆指値基準値タイプ） == （訂正逆指値基準値タイプ）かつ、<BR>
     * 　@　@（注文単位.逆指値基準値） == （訂正逆指値基準値）かつ、<BR>
     * 　@　@（注文単位.（W指値）訂正指値） == （訂正（W指値）訂正指値）かつ<BR>
     * 　@　@（注文単位.（W指値）執行条件） == （訂正（W指値）執行条件）の場合、<BR>
     * 　@　@falseを返却する。以外はtrueを返却する。 <BR>
     * <BR>
     * @@param l_orderUnit - 原注文（訂正元注文）の注文単位オブジェクト  <BR>
     * @@param l_strOrderExecutionConditionType - 訂正発注条件<BR>
     * @@param l_strOrderCondOperator - 訂正発注条件演算子<BR>
     * @@param l_strStopPriceType - 訂正逆指値基準値タイプ<BR>
     * @@param l_dblStopPrice - 訂正逆指値基準値<BR>
     * @@param l_dblWStopPrice -  訂正（W指値）訂正指値<BR>
     * @@param l_wLimitExecCondType - 訂正（W指値）執行条件<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeWStopCond(
        OrderUnit l_orderUnit, 
        String l_strOrderExecutionConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice,
        double l_dblWStopPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeWStopCond";
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
        
        //１）　@引数.訂正発注条件 ≠”W指値”の場合は、以降の処理は行わずfalseを返す。
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderExecutionConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@引数.訂正発注条件 ＝”W指値”の場合
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            boolean l_blnIsWLimitExecCondType = false;
            IfoOrderExecutionConditionType l_WLimitExecCondType2 = 
                l_orderUnitRow.getWLimitExecCondType();
            if (l_WLimitExecCondType2 == null && l_wLimitExecCondType == null)
            {
                l_blnIsWLimitExecCondType = true;
            }
            else if (l_WLimitExecCondType2 == null || l_wLimitExecCondType == null)
            {
                l_blnIsWLimitExecCondType = false;
            }
            else 
            {
                l_blnIsWLimitExecCondType = l_WLimitExecCondType2.equals(l_wLimitExecCondType);
            }

            if ((isEquals(l_orderUnitRow.getOrderCondOperator(), l_strOrderCondOperator))
                && (isEquals(l_orderUnitRow.getStopPriceType(), l_strStopPriceType))
                && (l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice)
                && (l_orderUnitRow.getWLimitPrice() == l_dblWStopPrice)
                && l_blnIsWLimitExecCondType)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
    }

    /**
     * (isChange注文失効日)<BR>
     * 注文失効日に訂正が入ったかをチェックする。 <BR>
     *<BR>
     *（注文単位.注文失効日付） == （訂正注文失効日）<BR>
     * falseを返す。 <BR>
     *<BR>
     * 以外の場合、trueを返却する。 <BR>
     * @@param l_orderUnit - 原注文（訂正元注文）の注文単位オブジェクト  <BR>
     * @@param l_datExpirationDate - 訂正注文失効日<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeExpirationDate(OrderUnit l_orderUnit, Date l_datExpirationDate)
    {
        final String STR_METHOD_NAME = "isChangeExpirationDate(OrderUnit l_orderUnit, Date l_datExpirationDate)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3DateUtility.compareToDay(l_orderUnitRow.getExpirationDate(),l_datExpirationDate) == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * （isChange返済数量内訳）。<BR>
     * <BR>
     * 返済数量内訳に訂正が入ったかをチェックする。<BR>
     * 内訳に訂正がある場合はtrue、以外はfalseを返却する。<BR>
     * <BR>
     * １）新規建注文の場合、falseを返却する。<BR>
     * <BR>
     * ２）　@訂正前の返済指定情報オブジェクトを取得する。<BR>
     * 　@引数の注文単位.getContractsToClose( )で取得。<BR>
     * <BR>
     * ３）　@値の比較<BR>
     * 　@引数の訂正後返済指定エントリの要素数分LOOPし、値の比較を行う。<BR>
     * 　@３－１）　@（訂正前の返済指定情報.返済注文数量！=　@引数の訂正後返済建玉エントリ.訂正後株数）の場合、trueを返却する。<BR>
     * 　@３－２）　@３－１）以外の場合、falseを返却する。<BR>
     * <BR>
     * @@param l_orderUnit（注文単位）<BR>
     * 　@　@　@原注文（訂正元注文）の注文単位オブジェクト<BR>
     * @@param l_modifiedSettleContractEntries（訂正後返済建玉エントリ）<BR>
     * 　@　@　@訂正後返済建玉エントリの配列。<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     */
    public boolean isChangeEachQuantityOfCloseContract(
    OrderUnit l_orderUnit,
        SettleContractEntry[] l_modifiedSettleContractEntries)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "isChangeEachQuantityOfCloseContract(OrderUnit, SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCateg) ||
            OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCateg))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        
        IfoClosingContractSpec[] l_closingContractSpecs = null;
            IfoContractSettleOrderUnit l_ifoOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;
                
        l_closingContractSpecs = l_ifoOrderUnit.getContractsToClose();

        if (l_closingContractSpecs.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        for (int i = 0; i < l_closingContractSpecs.length; i++)
        {
            if (l_closingContractSpecs[i].getQuantity() !=
                l_modifiedSettleContractEntries[i].getQuantity())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (validate特殊執行条件取扱停止)
     * <BR>
     * 特殊執行条件注文の取扱停止が設定されているかどうかをチェックする。<BR> 
     * <BR>
     * １）　@発注条件指定なし<BR> 
     * 　@（パラメータ.発注条件 == "DEFAULT"）の場合、<BR> 
     * 　@処理を終了する。 <BR>
     * <BR>
     * ２）　@訂正（パラメータ.注文単位ID != 0）の場合、<BR> 
     * 　@以下の処理を行う。 <BR>
     * 　@２－１）　@パラメータ.注文単位IDに該当する注文単位を取得する。<BR> 
     * <BR>
     * 　@２－２）　@発注済の逆指値注文の場合、<BR> 
     * 　@　@（パラメータ.発注条件 == "逆指値" かつ<BR> 
     * 　@　@　@取得した注文単位.リクエストタイプ == "時価サーバ"）<BR> 
     * 　@　@　@処理を終了する。 <BR>
     * <BR>
     * 　@２－３）　@ストップ注文へ切替済のW指値注文の場合、<BR> 
     * 　@　@（パラメータ.発注条件 == "W指値" かつ <BR>
     * 　@　@　@OP注文マネージャ.isストップ注文有効() == true） <BR>
     * 　@　@　@処理を終了する。 <BR>
     * <BR>
     * 　@　@　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@　@　@注文単位：　@取得した注文単位 <BR>
     * <BR>
     * ３）　@DB検索<BR> 
     * 　@以下の条件にて、特殊執行条件取扱停止テーブルを検索する。<BR> 
     * 　@[条件] <BR>
     * 　@　@証券会社コード = パラメータ.補助口座..get証券会社().get証券会社コード()<BR> 
     * 　@　@　@　@And　@部店コード = パラメータ.補助口座.get取引店().get部店コード ()<BR> 
     * 　@　@　@　@And 設定対象種別 = "商品" <BR>
     * 　@　@　@　@And キー情報 = <BR>
     * 　@　@　@　@　@[パラメータ.先物／オプション区分 == "先物"の場合]<BR> 
     * 　@　@　@　@　@　@"先物" <BR>
     * 　@　@　@　@　@[上記以外] <BR>
     * 　@　@　@　@　@　@"オプション" <BR>
     * 　@　@　@　@And 削除フラグ = "DEFAULT"<BR> 
     * <BR>
     * ４）　@３）の検索結果の内、パラメータ.発注条件に<BR> 
     * 　@対応するテーブル項目のいずれかが"停止中"となっていた場合、<BR> 
     * 　@「指定された条件付注文での商品は取扱停止中」の例外をスローする。<BR> 
     * <BR>
     * 　@※検索結果が取得できなかった場合、処理を終了する。<BR> 
     * <BR>
     * 　@[対応する項目]<BR> 
     * 　@　@パラメータ.発注条件 == "逆指値" ⇒ 逆指値注文停止フラグ<BR> 
     * 　@　@パラメータ.発注条件 == "W指値" ⇒ W指値注文停止フラグ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_lngOrderUnitId - （注文単位ID）<BR>
     * 注文単位ID<BR>
     * @@param l_strOrderCond - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_futureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateTriggerOrderStop(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        String l_strOrderCond,
        String l_futureOptionDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTriggerOrderStop(WEB3GentradeSubAccount, long, String, String)";
        log.entering(STR_METHOD_NAME);

        //発注条件指定なしの場合、処理を終了する。
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCond))
        {
            log.debug("発注条件指定なしなので、処理終了");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //訂正注文の場合
        if (l_lngOrderUnitId != 0)
        {
            //パラメータ.注文単位IDに該当する注文単位を取得する。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単位ID:[" + l_lngOrderUnitId + "]に該当するレコードがありません");
            }

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            
            //発注済みの逆指値注文の場合、処理を終了する。
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond) &&
                WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
            {
                log.debug("発注済の逆指値注文なので、処理終了");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //ストップ注文へ切替済のW指値注文の場合,処理を終了する。
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond) &&
                l_orderManager.isStopOrderValid(l_ifoOrderUnit))
            {
                log.debug("ストップ注文へ切替済のW指値注文の場合、処理終了");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        
        // 起動サービスチェックを行う。
        Object l_objSkipTriggerOrderStop = 
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP);
        
        if(l_objSkipTriggerOrderStop != null) 
        {
            if(l_objSkipTriggerOrderStop.equals(BooleanEnum.TRUE)){
                log.debug("スキップ対象サービスなので、処理終了");
                log.exiting(STR_METHOD_NAME);
                return;
            }

        }

        //DB検索
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_lstBind = new ArrayList();

        //証券会社コード
        l_sbWhere.append(" institution_code = ? ");
        l_lstBind.add(l_subAccount.getInstitution().getInstitutionCode());
        //部店コード
        l_sbWhere.append(" and branch_code = ? ");
        l_lstBind.add(l_subAccount.getWeb3GenBranch().getBranchCode());
        //設定対象種別
        l_sbWhere.append(" and target_type = ? ");
        l_lstBind.add(WEB3TargetTypeDef.COMMODITY);
        //キー情報
        l_sbWhere.append(" and key = ? ");
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futureOptionDiv))
        {
            //パラメータ.先物／オプション区分 == "先物"の場合
            l_lstBind.add(WEB3CommodityDivDef.FUTURE);
        }
        else
        {
            //パラメータ.先物／オプション区分 == "オプション"の場合
            l_lstBind.add(WEB3CommodityDivDef.OPTION);
        }
        //削除フラグ
        l_sbWhere.append(" and delete_flag = ? ");
        l_lstBind.add(BooleanEnum.FALSE);

        List l_lisTriggerOrderStop = null;
        try
        {
            //特殊執行条件停止テーブルを検索する。
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisTriggerOrderStop =
                l_qp.doFindAllQuery(
                    TriggerOrderStopRow.TYPE,
                    l_sbWhere.toString(),
                    l_lstBind.toArray()
                    );
            
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBアクセスに失敗しました");
        }

        //※検索結果が取得できなかった場合、処理を終了する。
        if (l_lisTriggerOrderStop.isEmpty())
        {
            log.debug("該当データなしなので、処理終了");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //検索結果の内、パラメータ.発注条件に対応するテーブル項目の
        //いずれかが"停止中"となっていた場合、例外をthrowする。
        for (int i = 0; i < l_lisTriggerOrderStop.size(); i++)
        {
            TriggerOrderStopRow l_triggerOrderStopRow =
                (TriggerOrderStopRow) l_lisTriggerOrderStop.get(i);

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond) &&
                BooleanEnum.TRUE.intValue() == l_triggerOrderStopRow.getStopOrderStopFlag())
            {
                log.debug("逆指値注文： 停止中");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond) &&
                BooleanEnum.TRUE.intValue() == l_triggerOrderStopRow.getWlimitOrderStopFlag())
            {
                log.debug("Ｗ指値注文： 停止中");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.debug("特殊執行条件取扱停止なし");

        log.exiting(STR_METHOD_NAME);
    }

    private static boolean isEquals(String l_obj1, String l_obj2)
    {
        if (l_obj1 == null && l_obj2 == null)
        {
            return true;
        }

        if (l_obj1 == null || l_obj2 == null)
        {
            return false;
        }

        return l_obj1.equals(l_obj2);
    }
    
    /**
     * (validateW指値注文)<BR>
     * 発注条件としてW指値が指定された注文について、 <BR>
     * 以下のチェックを行う。 <BR>
     * <BR>
     * －（W指値）有効状態チェック　@※訂正時のみ <BR>
     * －（W指値）訂正指値がマイナス値でないこと 。 <BR>
     * －（W指値）訂正指値の呼値チェック <BR>
     * －（W指値）訂正指値の値幅チェック <BR>
     * －（W指値）執行条件取扱可能チェック <BR>
     * －（W指値）訂正指値注文単価区分チェック <BR>
     * －（W指値）成行注文可能チェック <BR>
     * －発注条件単価乖離チェック <BR>
     * －発注条件単価入力チェック <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（先物OP注文）validateW指値注文」参照。<BR>
     * <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（先物OP注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.2.5（W指値）有効状態チェック<BR>
     * 　@　@　@　@　@　@　@　@(*)入力画面表示時と現在の注文単位とでW指値注文有効状態に相違がある<BR>
     * 　@　@　@　@　@　@　@　@（getＷ指値用有効状態区分()の戻り値 != パラメータ.（W指値）有効状態区分である）<BR>
     * 　@　@　@　@　@　@　@　@場合、<BR>
     * 　@　@　@　@　@　@　@　@「訂正中にW指値注文有効状態が変更となった為、訂正不可」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@<BR>
     * 　@　@　@　@　@　@　@　@※入力⇒確認⇒完了の間でストップ注文が有効または、失効となってしまった<BR>
     * 　@　@　@　@　@　@　@　@　@（W指値切替処理実施済またはトリガー注文管理者手動失効済の）場合を考慮。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02494<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（先物OP注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.5is取扱可能執行条件(執行条件 : IfoExecutionConditionType)<BR>
     * 　@　@　@　@　@　@　@　@(*)（W指値）執行条件取扱可能チェック<BR>
     * 　@　@　@　@　@　@　@　@falseが返却された場合、<BR>
     * 　@　@　@　@　@　@　@　@「入力された（W指値）執行条件は取扱不可」<BR>
     * 　@　@　@　@　@　@　@　@　@の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02495<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（先物OP注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.6(*)リミット注文・注文単価区分チェック<BR>
     * 　@　@　@　@　@　@　@　@リミット注文の注文単価が成行の場合<BR>
     * 　@　@　@　@　@　@　@　@（パラメータ.指値 == 0）<BR>
     * 　@　@　@　@　@　@　@　@「Ｗ指値注文のリミット注文は成行指定不可」の<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。　@　@<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02496<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（先物OP注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.7is成行注文可能(is新規建 : boolean, is買建 : boolean)<BR>
     * 　@　@　@　@　@　@　@　@(*)（W指値）訂正指値成行可能チェック<BR>
     * 　@　@　@　@　@　@　@　@falseが返却された場合、<BR>
     * 　@　@　@　@　@　@　@　@「成行注文は取扱不可」<BR>
     * 　@　@　@　@　@　@　@　@　@の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_00154<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * シーケンス図 : 「（先物OP注文）validateW指値注文」 <BR>
     * 具体位置　@　@ : 1.8.1(*)指値と（W指値）訂正指値のチェック<BR>
     * 　@　@　@　@　@　@　@　@(*)パラメータ.指値 == パラメータ.（W指値）訂正指値の場合、<BR>
     * 　@　@　@　@　@　@　@　@「注文単価と（W指値）訂正指値が同値」の<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02498<BR>
     * =====================================================<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)<BR>
     * 注文単位ＩＤ<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件<BR>
     * @@param l_strWLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@param l_wLimitExecCondType - (（W指値）執行条件)<BR>
     * （W指値）執行条件<BR>
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * <BR>
     * ※訂正時のみ使用。新規注文登録時は、 <BR>
     * 　@null固定。<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄オブジェクト<BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * （isOpenContract） <BR>
     * 新規建取引かどうかの判定。 <BR>
     * 新規建の場合true、返済の場合false。 <BR>
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * （isBuyToOpenOrder） <BR>
     * 買建取引かどうかの判定。 <BR>
     * 買建の場合true、売建の場合false。 <BR>
     * @@throws WEB3BaseException 
     */
    public void validateWLimitPriceOrder(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        double l_dblLimitPrice,
        String l_strOrderCondition,
        double l_dblOrderCondPrice,
        String l_strWLimitPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        String l_strWlimitEnableStatusDiv,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyToOpenOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
            + "double, String, IfoOrderExecutionConditionType, boolean, "
            + "WEB3IfoTradedProductImpl, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        // 1.1(*)パラメータ.発注条件 != "W指値"の場合,処理を終了する。
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit = null;
        
        //1.2(*)訂正（パラメータ.注文単位ID != 0）の場合
        if (l_lngOrderUnitId != 0)
        {
          
            //1.2.1 (*)ストップ注文へ切替済のW指値注文（パラメータ.（W指値）有効状態区分 == "ストップ注文有効"）の場合
            if (WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.2.2 (*)ストップ注文失効済のW指値注文（パラメータ.（W指値）有効状態区分 == "ストップ注文失効済"）の場合
            if (WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //1.2.3 getOrderUnit(arg0 : long)
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単位ID:[" + l_lngOrderUnitId + "]に該当するレコードがありません");
            }

            //1.2.4 getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)(
            String l_strWlimitEnableStatusDiv2 = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);
            
            //1.2.5 （W指値）有効状態チェック
            if (!isEquals(l_strWlimitEnableStatusDiv, l_strWlimitEnableStatusDiv2))
            {
                log.debug("訂正中にW指値注文有効状態が変更となった為、訂正不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02494,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "訂正中にW指値注文有効状態が変更となった為、訂正不可。");  
            }
                
        }
        
        //1.3  validate注文単価()
        this.validateOrderUnitPrice(
            Double.parseDouble(l_strWLimitPrice), l_ifoTradedProduct, l_subAccount);
   
        IfoProductRow l_ifoProductRow = (IfoProductRow) (
            (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct()).getDataSourceObject();
        
        //1.4 取扱可能注文条件()
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_ifoProductRow.getFutureOptionDiv(),         
                WEB3MarginTradingDivDef.DEFAULT);
        
        //1.5is取扱可能執行条件()
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_wLimitExecCondType))
        {
            log.debug("入力された（W指値）執行条件は取扱不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02495,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力された（W指値）執行条件は取扱不可。"); 
        }
        
        //1.6 (*)リミット注文・注文単価区分チェック
        if (l_dblLimitPrice == 0)
        {
            log.debug("Ｗ指値注文のリミット注文は成行指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02496,
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｗ指値注文のリミット注文は成行指定不可。"); 
        }
        
        //1.7(*)ストップ注文が成行注文の場合
        double l_dblWLimitPrice = Double.parseDouble(l_strWLimitPrice);
        
        if (l_dblWLimitPrice == 0 || (l_dblWLimitPrice != 0 && 
            (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED).equals(l_wLimitExecCondType)))
        {
            if (!l_handlingOrderCond.isMarketOrderPossible(
                l_blnIsOpenContract, l_blnIsBuyToOpenOrder))          
            {   
                log.debug("成行注文は取扱不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00154,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "成行注文は取扱不可。"); 
            }
        }
        
        //1.8  (*)（W指値）訂正指値が指値（パラメータ.（W指値）訂正指値 != 0）の場合
        if (l_dblWLimitPrice != 0)
        {
            //1.8.1 (*)指値と（W指値）訂正指値のチェック
            if (l_dblWLimitPrice == l_dblLimitPrice)
            {
                log.debug("注文単価と（W指値）訂正指値が同値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02498,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価と（W指値）訂正指値が同値。"); 
            }
        }
        
        //1.9 validate発注条件単価
        this.validateOrderCondPrice(
            l_subAccount.getWeb3GenBranch(),
            l_dblLimitPrice,
            l_dblOrderCondPrice,
            l_ifoTradedProduct,
            l_blnIsBuyToOpenOrder);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate発注条件単価)<BR>
     * 発注条件が指定された注文について、<BR> 
     * 発注条件単価のチェックを行う。 <BR>
     * <BR>
     * －発注条件単価乖離チェック <BR>
     * －発注条件単価入力チェック <BR>
     * ※計算処理は全てBigDecimal型で計算すること。<BR> 
     * <BR>
     * １）　@発注条件単価チェック実施しない部店の場合、発注条件単価チェックを行わない。<BR>
     * 　@１－１）　@部店プリファ@レンステーブルから、発注条件単価チェック実施区分を取得する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@部店ID = パラメータ.部店.部店ID<BR>
     * 　@　@　@　@And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価チェック実施区分<BR>
     * 　@　@　@　@And　@プリファ@レンス名の連番 = 2（：先物・オプション）<BR>
     * <BR>
     * 　@　@　@※上記条件でレコードが取得できなかった場合、"チェックしない"とする。<BR>
     * <BR>
     * 　@１－２）発注条件単価チェック実施区分が"チェックしない"の場合リターンする。<BR>
     * <BR>
     * ２）　@発注条件単価乖離チェック <BR>
     * 　@パラメータ.指値の値と、パラメータ.発注条件単価との間に <BR>
     * 　@刻み値の整数倍以上の乖離があるかどうかチェックする。 <BR>
     * 　@２－１）　@刻み値を取得する。 <BR>
     * 　@　@先物OPプロダクトマネージャ.get刻み値()をコールする。<BR> 
     * <BR>
     * 　@　@[get刻み値()に指定する引数] <BR>
     * 　@　@　@先物OP銘柄：　@パラメータ.先物OP取引銘柄.getProduct() <BR>
     * 　@　@　@単価：　@パラメータ.指値 <BR>
     * <BR>
     * 　@２－２）　@乖離値を取得する。 <BR>
     * 　@　@乖離値 = (パラメータ.発注条件単価 - パラメータ.指値)の絶対値 <BR>
     * <BR>
     * 　@２－３）　@乖離値が刻み値*倍数以上かどうかチェックする。 <BR>
     * 　@　@乖離値が、以下の条件に該当しない場合、 <BR>
     * 　@　@「発注条件単価入力エラー（乖離値が指定の倍率未満）」の <BR>
     * 　@　@例外をスローする。 <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_02492<BR>
     * <BR>
     * 　@　@　@乖離値 >= （刻み値 * 倍率(*1)） <BR>
     * <BR>
     * 　@　@(*1)倍率 <BR>
     * 　@　@　@部店プリファ@レンステーブルを以下の条件で検索し、 <BR>
     * 　@　@　@取得したレコード.プリファ@レンスの値を倍率とする。 <BR>
     * <BR>
     * 　@　@　@[条件]<BR> 
     * 　@　@　@　@部店ID = パラメータ.部店.部店ID<BR>  
     * 　@　@　@　@And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価乖離倍率<BR> 
     * 　@　@　@　@And　@プリファ@レンス名の連番 = 2（：先物・オプション） <BR>
     * <BR>
     * 　@　@　@※検索結果が取得できなかった場合、倍率 = 1とする。<BR> 
     * <BR>
     * ３）　@発注条件単価入力チェック<BR> 
     * 　@パラメータ.指値の値と、パラメータ.発注条件単価とで<BR> 
     * 　@時価を挟み込んでいるかどうかチェックする。 <BR>
     * 　@３－１）　@時価を取得する。 <BR>
     * 　@　@先物OPプロダクトマネージャ.get時価()をコールする。<BR> 
     * <BR>
     * 　@　@[get時価()に指定する引数] <BR>
     * 　@　@　@先物OP取引銘柄：　@パラメータ.先物OP取引銘柄<BR> 
     * <BR>
     * 　@３－２）　@パラメータ.指値の値と、パラメータ.発注条件単価とで<BR> 
     * 　@　@時価を挟み込んでいるかどうかチェックする。 <BR>
     * 　@　@以下の条件を満たさない場合、 <BR>
     * 　@　@「発注条件単価／注文単価入力エラー（時価の挟み込み不正）」の<BR> 
     * 　@　@例外をスローする。 <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_02493<BR>
     * <BR>
     * 　@　@[買（パラメータ.is買建 == true）の場合]<BR> 
     * 　@　@　@パラメータ.指値 <= 取得した時価 < パラメータ.発注条件単価 <BR>
     * <BR>
     * 　@　@[売（上記以外）の場合]<BR> 
     * 　@　@　@パラメータ.指値 >= 取得した時価 > パラメータ.発注条件単価<BR> 
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄オブジェクト<BR>
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * （isBuyToOpenOrder） <BR>
     * 買建取引かどうかの判定。 <BR>
     * 買建の場合true、売建の場合false。 <BR>
     * @@throws WEB3BaseException 
     */
    protected void validateOrderCondPrice(
        WEB3GentradeBranch l_branch,
        double l_dblLimitPrice,
        double l_dblOrderCondPrice,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsBuyToOpenOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrderCondPrice(WEB3GentradeBranch, double, double," + 
            " WEB3IfoTradedProductImpl, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoTradedProduct == null || l_branch == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //発注条件単価チェック実施しない部店の場合、発注条件単価チェックを行わない。
        //    部店プリファ@レンステーブルから、発注条件単価チェック実施区分を取得する。
        //    [条件]  
        //    部店ID = パラメータ.部店.部店ID  
        //    And　@プリファ@レンス名 = プリファ@レンス名.W指値注文・発注条件単価チェック実施区分 
        //    And　@プリファ@レンス名の連番 = 2（：先物・オプション） 
        //    ※上記条件でレコードが取得できなかった場合、"チェックしない"とする。
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_CHECK_ORDER_COND_PRICE,
                    2);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //発注条件単価チェック実施区分が"チェックしない"の場合リターンする。
        if (l_branchPreferencesRow == null
            || WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            return;
        }

        //１）　@発注条件単価乖離チェック  
        //　@１－１）　@刻み値を取得する。 
        //　@　@先物OPプロダクトマネージャ.get刻み値()をコールする。 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
 
        WEB3IfoProductManagerImpl l_productMgr = 
            (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        double l_dblTickValue = 
            l_productMgr.getTickValue(
                (WEB3IfoProductImpl)l_ifoTradedProduct.getProduct(),
                l_dblLimitPrice);
        
        //　@１－２）　@乖離値を取得する。 
        //　@　@乖離値 = (パラメータ.発注条件単価 - パラメータ.指値)の絶対値 
        BigDecimal l_bdOrderCondPrice = new BigDecimal(l_dblOrderCondPrice + "");
        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice + "");
        
        //乖離値
        BigDecimal l_bdEstrangePrice = 
            (l_bdOrderCondPrice.subtract(l_bdLimitPrice)).abs();
        
        //刻み値
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");
        
        //　@　@(*1)倍率 
        //　@　@　@部店プリファ@レンステーブルを以下の条件で検索し、 
        //　@　@　@取得したレコード.プリファ@レンスの値を倍率とする。   
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_branch.getBranchId(), 
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_DIVERGENCERATE,
                    2);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        BigDecimal l_bdRate = null;
        if (l_branchReferencesRow == null)
        {
            //※検索結果が取得できなかった場合、倍率 = 1とする。
            l_bdRate = new BigDecimal(1);
        }
        else
        {
            
            l_bdRate = new BigDecimal(l_branchReferencesRow.getValue());
        }
        
        //　@１－３）　@乖離値が刻み値*倍数以上かどうかチェックする。 
        //　@　@乖離値が、以下の条件に該当しない場合、 
        //　@　@「発注条件単価入力エラー（乖離値が指定の倍率未満）」の 
        //　@　@例外をスローする。 
        //
        //　@　@　@乖離値 >= （刻み値 * 倍率(*1)）        
        
        if (l_bdEstrangePrice.compareTo(l_bdTickValue.multiply(l_bdRate)) < 0)
        {
            log.debug("発注条件単価入力エラー（乖離値が指定の倍率未満）。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02492, 
                getClass().getName() + STR_METHOD_NAME,
                "発注条件単価入力エラー（乖離値が指定の倍率未満）。");
        }
        
        //２）　@発注条件単価入力チェック 
        //　@パラメータ.指値の値と、パラメータ.発注条件単価とで 
        //　@時価を挟み込んでいるかどうかチェックする。 
        //　@２－１）　@時価を取得する。 
        //　@　@先物OPプロダクトマネージャ.get時価()をコールする。 
        double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_ifoTradedProduct);
        BigDecimal l_bdCurrentPrice = new BigDecimal(l_dblCurrentPrice + "");
        //　@２－２）　@パラメータ.指値の値と、パラメータ.発注条件単価とで 
        //　@　@時価を挟み込んでいるかどうかチェックする。 
        //　@　@以下の条件を満たさない場合、 
        //　@　@「発注条件単価／注文単価入力エラー（時価の挟み込み不正）」の 
        //　@　@例外をスローする。 
        //　@　@[買（パラメータ.is買建 == true）の場合] 
        //　@　@　@パラメータ.指値 <= 取得した時価 < パラメータ.発注条件単価 
        if (l_blnIsBuyToOpenOrder)
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) <= 0
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) < 0))
            {
                log.debug("発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493, 
                    getClass().getName() + STR_METHOD_NAME,
                    "発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
            }
        }
        
        //　@　@[売（上記以外）の場合] 
        //　@　@　@パラメータ.指値 >= 取得した時価 > パラメータ.発注条件単価 
        else 
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) >= 0 
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) > 0))
            {
                log.debug("発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493, 
                    getClass().getName() + STR_METHOD_NAME,
                    "発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate市場送信済注文複数回訂正可能（休憩時間中）)<BR>
     * 昼休み中に市場送信済注文の複数回訂正が可能かどうか判定する。 <BR>
     * <BR>
     * 　@１）部店プリファ@レンステーブルから以下の条件でレコードを取得する。 <BR>
     * 　@　@[条件]  <BR>
     * 　@　@　@部店ID = パラメータ.部店ID And <BR>
     * 　@　@　@プリファ@レンス名 = プリファ@レンス名.市場送信済注文複数回訂正可能（休憩時間中） And<BR>
     * 　@　@　@プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * 　@２）該当レコードが存在する場合のみ以下を行う。 <BR>
     * 　@　@取得レコード．プリファ@レンスの値＝訂正不可能の場合、 <BR>
     * 　@　@例外をthrowする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * @@param l_lngBranchId - (部店ID)
     * @@throws WEB3BaseException
     * @@throws OrderValidationException
     */
    public void validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long l_lngBranchId)
        throws WEB3BaseException, OrderValidationException
    {
        final String STR_METHOD_NAME =
            "validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //部店プリファ@レンステーブルから以下の条件でレコードを取得する。
            BranchPreferencesRow l_row =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.MULTI_CHANGEABILITY_OF_MARKET_NOTIFIED_ORDER_IN_BREAK_TIME,
                    1);

            //該当レコードが存在する場合のみ以下を行う。
            //取得レコード．プリファ@レンスの値＝訂正不可能の場合、例外をthrowする。
            if (l_row != null)
            {
                if (WEB3MultiChangeabilityDef.NOT_CHANGEABLE.equals(l_row.getValue()))
                {
                    log.debug("注文を受付られる状態ではありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate訂正時注文Rev上限)<BR>
     * 訂正時の注文Revが上限を超えないかどうかをチェックする。 <BR>
     * <BR>
     * １）　@引数の注文単位オブジェクトのcloneオブジェクトを生成し、 <BR>
     * 　@　@　@引数の訂正後の値をそれぞれ該当する項目にセットする。 <BR>
     * <BR>
     * 　@　@　@訂正数量：　@　@　@　@注文数量 <BR>
     * 　@　@　@訂正指値：　@　@　@　@指値 <BR>
     * 　@　@　@訂正執行条件：　@執行条件 <BR>
     * <BR>
     * ２）　@先物OP発注サービス.get訂正時注文Rev()をコールする。 <BR>
     * 　@　@　@引数には、１）で作成した注文単位オブジェクトをセットする。 <BR>
     * 　@　@　@※例外（上限回数オーバー等）がthrowされた場合は、 <BR>
     * 　@　@　@※その例外をそのままthrowする。<BR>
     * @@param l_ifoOrderUnit - 注文単位
     * 訂正前の注文単位オブジェクト。<BR>
     * @@param l_dblQuantity - (訂正数量)<BR>
     * 訂正数量<BR>
     * @@param l_dblPriceChange - (訂正指値)<BR>
     * 訂正指値<BR>
     * @@param l_changeExecCondType - (訂正執行条件)<BR>
     * 訂正執行条件<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevLimit(
        IfoOrderUnit l_ifoOrderUnit,
        double l_dblQuantity,
        double l_dblPriceChange,
        IfoOrderExecutionConditionType l_changeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrderRevLimit(" +
            "IfoOrderUnit, double, double, IfoOrderExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //引数の注文単位オブジェクトのcloneオブジェクトを生成し、
        //引数の訂正後の値をそれぞれ該当する項目にセットする。
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        IfoOrderUnitParams l_afterIfoOrderUnitParams =
            new IfoOrderUnitParams(l_ifoOrderUnitRow);

        l_afterIfoOrderUnitParams.setQuantity(l_dblQuantity);
        l_afterIfoOrderUnitParams.setLimitPrice(l_dblPriceChange);
        l_afterIfoOrderUnitParams.setExecutionConditionType(l_changeExecCondType);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_changeAfterOrderUnit =
            (IfoOrderUnit)l_orderManager.toOrderUnit(l_afterIfoOrderUnitParams);

        //先物OP発注サービス.get訂正時注文Rev()をコールする。
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        try
        {
            l_frontOrderService.getChangeOrderRev(l_changeAfterOrderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate売買最終日<夕場>)<BR>
     * 以下条件の場合、発注日と売買最終日のチェックを行なう。<BR>
     * －夕場まで注文、出来るまで注文の場合 <BR>
     * －夕場時間帯の注文の場合 <BR>
     * <BR>
     * １）発注日取得 <BR>
     * 　@取引時間管理.get発注日()にて発注日を取得する。<BR>
     *<BR>
     * ２）チェック判定
     * 　@・（取引時間管理.is夕場時間帯()==ture）の場合、 <BR>
     * 　@３）以降の処理を行なう。 <BR>
     * <BR>
     * 　@・(引数.注文期限区分==”夕場まで注文”)の場合、 <BR>
     * 　@３）以降の処理を行なう。 <BR>
     * <BR>
     * ・(引数.注文期限区分==”出来るまで注文”) かつ、 <BR>
     *    初回注文（引数の原注文発注日 == １）で取得した発注日　@または <BR>　@
     *    引数の原注文発注日 == null）の場合、<BR>
     * 　@３）以降の処理を行なう。 <BR>
     * <BR>
     * <BR>
     * ３）発注日上場期間中判定 <BR>
     * <BR>
     * 　@・１）で取得した発注日　@==　@引数の先物OP取引銘柄.売買最終日 <BR>
     * 　@・１）で取得した発注日　@==　@引数の先物OP取引銘柄.上場(登録)廃止日の前営業日(*) <BR>
     * 　@上記いずれかにあてはまる場合は<BR>
     * 　@『入力された銘柄は現在、上場期間中となっておりません。』の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00145<BR>
     * <BR>
     * (*)営業日計算.calc営業日()にて前営業日を算出する。 <BR>
     * <BR>
     * [営業日計算.calc営業日()への引数]  <BR>
     * 　@基準日 ： 引数の先物OP取引銘柄.上場(登録)廃止日  <BR>
     * 　@加算／減算日数 ： -1（前営業日）<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * 注文期限区分<BR>
     * @@param l_datOrgOrderBizDate - (原注文発注日)<BR>
     * 原注文の発注日<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionLastTradingDate(
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        String l_strExpirationDateType,
        Date l_datOrgOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateEveningSessionLastTradingDate("
            + "WEB3IfoTradedProductImpl, String, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）発注日取得
        //取引時間管理.get発注日()にて発注日を取得する。
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //初回注文
        boolean l_blnIsFirstOrderUnit = false;

        //引数の原注文発注日 == １）で取得した発注日　@または引数の原注文発注日 == null
        if ((WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datOrgOrderBizDate) == 0)
            || l_datOrgOrderBizDate == null)
        {
            l_blnIsFirstOrderUnit = true;
        }

        //２）チェック判定
        //取引時間管理.is夕場時間帯()==tureの場合、
        //引数.注文期限区分==”夕場まで注文”)の場合
        //引数.注文期限区分==”出来るまで注文”) かつ、初回注文の場合
        if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
            || (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType)
            || (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
                && l_blnIsFirstOrderUnit)))
        {
            //３）発注日上場期間中判定
            // ・１）で取得した発注日 == 引数の先物OP取引銘柄.売買最終日
            // ・１）で取得した発注日 == 引数の先物OP取引銘柄.上場(登録)廃止日の前営業日(*)
            IfoTradedProductRow l_ifoTradedProductRow =
                (IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();

            //引数の先物OP取引銘柄.上場(登録)廃止日の前営業日
            Date l_datPreUnlistedDate = new WEB3GentradeBizDate(l_ifoTradedProductRow.getUnlistedDate()).roll(-1);
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_ifoTradedProduct.getLastTradingDate()) == 0
                || WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datPreUnlistedDate) == 0)
            {
                //『入力された銘柄は現在、上場期間中となっておりません。』の例外をスローする。
                log.debug("入力された銘柄は現在、上場期間中となっておりません");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00145,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力された銘柄は現在、上場期間中となっておりません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate夕場まで注文取扱可能)<BR>
     * 夕場まで注文取扱可能チェックを行う。 <BR>
     * －「夕場まで注文」取扱可能会社であること。 <BR>
     * －（新規注文登録の場合のみ）現在の時間帯が夕場以外であること。<BR>
     * 　@　@※注文訂正や注文繰越、逆指値発注、W指値切替、連続注文発注処理時は対象外となる。<BR>
     * 　@　@※予約注文訂正の場合は、各訂正サービス内でチェックを行う。<BR>
     * <BR>
     * １）　@以下のいずれかに該当する場合は、何もせずにreturnする。<BR>
     * <BR>
     * 　@　@　@・注文訂正／W指値切替時 <BR>
     * 　@　@　@　@引数.注文単位ID != 0 の場合。 <BR>
     * <BR>
     * 　@　@　@・注文繰越時 <BR>
     * 　@　@　@　@引数.初回注文の注文単位ID != null　@かつ <BR>
     * 　@　@　@　@引数.初回注文の注文単位ID > 0 の場合。 <BR>
     * <BR>
     * 　@　@　@・逆指値発注時 <BR>
     * 　@　@　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()にて <BR>
     * 　@　@　@　@　@スキップvalidate特殊執行条件取扱停止 を取得した値 == TRUE の場合。<BR>
     * 　@　@　@　@　@-ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@　@　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP) <BR>
     * <BR>
     * <BR>
     * 　@　@　@・予約注文訂正／連続注文発注処理 <BR>
     * 　@　@　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()にて <BR>
     * 　@　@　@　@　@予約注文訂正フラグを取得した値 == TRUE の場合。 <BR>
     * 　@　@　@　@　@-ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@　@　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER) <BR>
     * <BR>
     * ２）　@上記以外の場合、夕場まで注文取扱可能チェックを行う。<BR>
     * <BR>
     * 　@(引数.注文期限区分=”夕場まで注文”)の場合のみ、 <BR>
     * 　@以下チェックを行う。　@ <BR>
     * <BR>
     * 　@(引数.is夕場まで注文取扱可能==false) <BR>
     * 　@または <BR>
     * 　@(取引時間管理.is夕場時間帯()==true) <BR>
     * 　@のいずれかにあてはまる場合、 <BR>
     * 　@『夕場まで注文は取り扱えません。』の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02816<BR>
     * @@param l_blnIsEveningSessionOrderHandlingPossible - (is夕場まで注文取扱可能)<BR>
     * false：夕場まで注文取扱不可能<BR>
     * true：夕場まで注文取扱可能<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * 注文期限区分<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID <BR>
     * （訂正時に、訂正対象の注文単位IDがセットされる。<BR>
     * 　@新規注文登録の場合は、0。）<BR>
     * @@param l_firstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 初回注文の注文単位ID。 <BR>
     * （繰越時にのみ設定される。以外はnull）<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionOrderPossibleHandling(
        boolean l_blnIsEveningSessionOrderHandlingPossible,
        String l_strExpirationDateType,
        long l_lngOrderUnitId,
        Long l_firstOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateEveningSessionOrderPossibleHandling("
            + "boolean, String, long, Long)";
        log.entering(STR_METHOD_NAME);

        //注文訂正／W指値切替時
        //引数.注文単位ID != 0 の場合
        if (l_lngOrderUnitId != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //注文繰越時
        //引数.初回注文の注文単位ID != null　@かつ
        //引数.初回注文の注文単位ID > 0 の場合。
        if (l_firstOrderUnitId != null && l_firstOrderUnitId.longValue() > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //逆指値発注時
        //　@　@　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()にて
        //　@　@　@　@　@スキップvalidate特殊執行条件取扱停止 を取得した値 == TRUE の場合
        //　@　@　@　@　@-ThreadLocalSystemAttributesRegistry.getAttribute(
        //　@　@　@　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP)
        Object l_objSkipTriggerOrderStop = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP);

        if (BooleanEnum.TRUE.equals(l_objSkipTriggerOrderStop))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // 　@　@　@・予約注文訂正／連続注文発注処理
        // 　@　@　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()にて
        // 　@　@　@　@　@予約注文訂正フラグを取得した値 == TRUE の場合。
        // 　@　@　@　@　@-ThreadLocalSystemAttributesRegistry.getAttribute(
        // 　@　@　@　@　@　@　@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER)
        Object l_objSuccChangeOrder = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER);

        if (BooleanEnum.TRUE.equals(l_objSuccChangeOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //２）　@上記以外の場合、夕場まで注文取扱可能チェックを行う
        //
        //　@(引数.注文期限区分=”夕場まで注文”)の場合のみ
        //　@以下チェックを行う
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType))
        {
            //　@(引数.is夕場まで注文取扱可能==false)
            //　@または
            //　@(取引時間管理.is夕場時間帯()==true)
            //　@のいずれかにあてはまる場合
            //　@『夕場まで注文は取り扱えません。』の例外をスローする。
            if (!l_blnIsEveningSessionOrderHandlingPossible
                || WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                log.debug("夕場まで注文は取り扱えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02816,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "夕場まで注文は取り扱えません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 二つの引数の余数運算をする。<BR>
     * @@param l_bdA - (被除数)<BR>
     * 被除数<BR>
     * @@param l_bdB - (除数)<BR>
     * 除数<BR>
     * @@return BigDecimal
     */
    private BigDecimal remainder(BigDecimal l_bdA, BigDecimal l_bdB)
    {
        final String STR_METHOD_NAME = "remainder(BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdN = l_bdA.divide(l_bdB, 0, BigDecimal.ROUND_DOWN);
        BigDecimal l_bdReturnValue = l_bdA.subtract(l_bdB.multiply(l_bdN));

        log.exiting(STR_METHOD_NAME);
        return l_bdReturnValue;
    }

}
@
