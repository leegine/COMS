head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP新規建更新インタセプタクラス(WEB3IfoOpenContractUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 艾興 (中訊) 新規作成
Revesion History : 2006/07/13 郭英 (中訊) DB更新仕様No.087
Revesion History : 2007/01/30 柴双紅 (中訊) DB更新仕様No.131,138
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.667 DB更新仕様No.169
Revesion History : 2008/03/17 張騰宇 (中訊) DB更新仕様No.197
Revesion History : 2008/04/28 張騰宇 (中訊) モデル868 DB更新仕様No.207
Revesion History : 2008/05/07 張騰宇 (中訊) モデル884,885 DB更新仕様No.209
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP新規建更新インタセプタ)<BR>
 * 先物OP新規建更新インタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoOpenContractUpdateInterceptor
    extends WEB3IfoOrderUpdateInterceptor
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOpenContractUpdateInterceptor.class);

    /**
     * (先物OP新規建注文内容)<BR>
     */
    private WEB3IfoOpenContractOrderSpec ifoOpenContractOrderSpec;

    /**
     * (立会区分)<BR>
     * 1 ： 夕場（夕場実施する会社の夕場時間帯のみ設定）<BR>
     * null ： 上記以外<BR>
     */
    private String sessionType;

    /**
     * (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR） <BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用 <BR>
     * <BR>
     * 連続注文発注処理では、先物OP注文単位.扱者コード（SONAR）に <BR>
     * 予約注文単位.扱者コード（SONAR）を設定するため。 <BR>
     * <BR>
     * 連続注文発注処理以外はセットされないのでnullである。<BR>
     */
    private String sonarTraderCode;

    /**
     * (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル <BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用<BR>
     */
    private String orderChanel;

    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用<BR>
     */
    private String orderRootDiv;

    /**
     * @@roseuid 40C07C02038A
     */
    public WEB3IfoOpenContractUpdateInterceptor()
    {

    }

    /**
     * (先物OP新規建更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の先物OP新規建注文内容をプロパティにセットする。<BR>
     * @@param l_openContractOrderSpec - 先物OP新規建注文内容
     * @@return webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor
     * @@roseuid 405E8A1302BA
     */
    public WEB3IfoOpenContractUpdateInterceptor(WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        ifoOpenContractOrderSpec = l_openContractOrderSpec;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     *   注文単位.先物／オプション区分 == "先物"の場合 
     *      「先物新規建_注文単位テーブル仕様.xls」参照 
     *   注文単位.先物／オプション区分 == "オプション"の場合
     *      「OP新規建_注文単位テーブル仕様.xls」参照 
     * 
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 405E898A03A4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType "
                + "l_orderManagerPersistenceType, "
                + "OrderManagerPersistenceContext "
                + "l_orderManagerPersistenceContext, "
                + "IfoOrderUnitParams l_ifoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnitParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_futureDiv;
        String l_orderChannel;
        Date l_receivedDateTime;
        long l_accountId;
        String l_voucherNo;
        String l_sonarMarketCode;
        int l_orderRequestNumLen;
        //(先物／オプション区分)<BR>
        IfoProductRow l_IfoProductRow = null;

        try
        {
            l_IfoProductRow =
                IfoProductDao.findRowByPk(l_ifoOrderUnitParams.product_id);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        l_futureDiv = l_IfoProductRow.getFutureOptionDiv();
        l_ifoOrderUnitParams.setFutureOptionDiv(l_futureDiv);

        // (発注条件)<BR>
        l_ifoOrderUnitParams.setOrderConditionType(this.orderCond);
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            // (発注条件演算子)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            // (逆指値基準値タイプ)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(null);

            // (逆指値基準値)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            // （W指値）訂正指値)<BR>        
            l_ifoOrderUnitParams.setWLimitPrice(null);
            // リクエストタイプ
            l_ifoOrderUnitParams.setRequestType(null);
        }
        else
        {
            // (発注条件演算子)<BR>       
            l_ifoOrderUnitParams.setOrderCondOperator(this.orderCondOperator);

            // (逆指値基準値タイプ)<BR>      
            l_ifoOrderUnitParams.setStopPriceType(this.stopOrderBasePriceType);

            // (逆指値基準値)<BR>
            l_ifoOrderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);
            
            // （W指値）訂正指値)<BR>            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitPrice(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitPrice(this.wLimitPriceChange);
            }
            // リクエストタイプ
            l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
        }

        // 初回注文の注文チャネル
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

        if (this.orderChanel == null)
        {
            l_orderChannel =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_CHANNEL);
            l_ifoOrderUnitParams.setOrderChanel(l_orderChannel);
        }
        else
        {
            l_ifoOrderUnitParams.setOrderChanel(this.orderChanel);
        }

        // 受注日時<BR>
        l_receivedDateTime =
            (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_ifoOrderUnitParams.setReceivedDateTime(l_receivedDateTime);

        // 初回注文の手数料No<BR>
        if (this.commRevMstId == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_ifoOrderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());

        // 初回注文の手数料No枝番<BR>
        l_ifoOrderUnitParams.setCommTblSubNo(
            this.commRevMstId.getCommissionRevNo());

        //扱者コード（SONAR）<BR>
        l_accountId = l_ifoOrderUnitParams.account_id;
        MainAccount l_mainAccount = null;

        // 市場コード（SONAR）<br>
        MarketRow l_MarketRow = null;

        try
        {
            l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_accountId);
            l_MarketRow =
                (MarketRow)l_finApp
                    .getFinObjectManager()
                    .getMarket(l_ifoOrderUnitParams.getMarketId())
                    .getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        MainAccountRow l_MainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //インタセプタ.扱者コード（SONAR）=nullの場合 顧客.扱者コード（SONAR）
        if (this.sonarTraderCode == null)
        {
            //顧客.扱者コード（SONAR）
            l_ifoOrderUnitParams.setSonarTraderCode(l_MainAccountRow.getSonarTraderCode());
        }
        else
        {
            //インタセプタ.扱者コード（SONAR）
            l_ifoOrderUnitParams.setSonarTraderCode(this.sonarTraderCode);
        }

        // 注文単価<BR>            
        if (this.estimateDeliveryAmounCalcResult == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        l_ifoOrderUnitParams.setPrice(
            this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());
        
        WEB3HostReqOrderNumberManageService l_numberService =
                            (WEB3HostReqOrderNumberManageService) Services.getService(
                                WEB3HostReqOrderNumberManageService.class);
        String l_newOrderRequestNumber = null;
        try
        {
            l_newOrderRequestNumber =
                l_numberService.getNewNumber(
                    ifoOpenContractOrderSpec.getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.IFO);            
        }
        catch (WEB3BaseException l_wbex)
        {
            log.error(STR_METHOD_NAME, l_wbex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        l_ifoOrderUnitParams.order_request_number = l_newOrderRequestNumber;
        
        //概算受渡代金<br> 
        l_ifoOrderUnitParams.setEstimatedPrice(
            this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());

        // 市場コード（SONAR）<br>
        l_sonarMarketCode = l_MarketRow.getSonarMarketCode();
        l_ifoOrderUnitParams.setSonarMarketCode(l_sonarMarketCode);

        //手数料商品コード<br>
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
        {
            l_ifoOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.INDEX_OP);            
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitParams.getFutureOptionDiv()))
        {
            l_ifoOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.INDEX_FUTURES);
        }

        // 注文訂正・取消区分<br>      
        l_ifoOrderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //市場から確認済みの注文単価
        l_ifoOrderUnitParams.setConfirmedOrderPrice(null);

        //市場から確認済みの概算受渡代金
        l_ifoOrderUnitParams.setConfirmedEstimatedPrice(null);

        //市場から確認済みの執行条件
        l_ifoOrderUnitParams.setConfirmedExecConditionType(null);

        //決済順序
        l_ifoOrderUnitParams.setClosingOrder(null);

        
		//初回注文の注文単位ＩＤ
        l_ifoOrderUnitParams.setFirstOrderUnitId(ifoOpenContractOrderSpec.getFirstOrderUnitId());

        // 注文エラー理由コード<br>  
        l_ifoOrderUnitParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL);

        l_orderRequestNumLen = l_newOrderRequestNumber.length();
        l_voucherNo = l_newOrderRequestNumber.substring(l_orderRequestNumLen - 3);
        l_voucherNo = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER + l_voucherNo;
        l_ifoOrderUnitParams.setVoucherNo(l_voucherNo);

        //注文経路区分
        if (this.orderRootDiv == null)
        {
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            l_ifoOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        }
        else
        {
            l_ifoOrderUnitParams.setOrderRootDiv(this.orderRootDiv);
        }

        //取引コード（SONAR）
        l_ifoOrderUnitParams.setSonarTradedCode(
            WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //元発注条件
        l_ifoOrderUnitParams.setOrgOrderConditionType(null);
        
        //元発注条件演算子
        l_ifoOrderUnitParams.setOrgOrderCondOperator(null);
        
        //元逆指値基準値タイプ
        l_ifoOrderUnitParams.setOrgStopPriceType(null);
        
        //元逆指値基準値
        l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
        
        //元（W指値）訂正指値
        l_ifoOrderUnitParams.setOrgWLimitPrice(null);
        
        //元（W指値）執行条件
        l_ifoOrderUnitParams.setOrgWLimitExecCondType(null);
        
        //（W指値）執行条件
        //新規建注文内容.get（W指値）執行条件
        //※発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
        {
            l_ifoOrderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                l_ifoOrderUnitParams.setWLimitExecCondType(
                    this.ifoOpenContractOrderSpec.getWLimitExecCondType());
            }
        }
        
        //（W指値）切替前指値
        l_ifoOrderUnitParams.setWLimitBeforeLimitPrice(null);
        
        //（W指値）切替前執行条件
        l_ifoOrderUnitParams.setWLimitBeforeExecCondType(null);

        try
        {
            //発注経路区分:先物OP発注サービス.get発注経路区分()の戻り値
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSubmitOrderRouteDiv =
                l_ifoOrderService.getSubmitOrderRouteDiv(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_MarketRow.getMarketCode());
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        //夕場前繰越対象フラグ
        boolean l_blnEveningSessionCarryoverFlag = this.ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag();
        if (l_blnEveningSessionCarryoverFlag)
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.TRUE);
        }
        else
        {
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
                BooleanEnum.FALSE);
        }

        //立会区分
        l_ifoOrderUnitParams.setSessionType(this.sessionType);

        //日計り区分
        l_ifoOrderUnitParams.setDayTradeType(null);

        //注文期限区分
        l_ifoOrderUnitParams.setExpirationDateType(this.ifoOpenContractOrderSpec.getExpirationDateType());

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitParams;
    }

    /**
     * (get立会区分)<BR>
     * 立会区分を取得する。<BR>
     * <BR>
     * @@return String
     */
    public String getSessionType()
    {
        return this.sessionType;
    }

    /**
     * (set立会区分)<BR>
     * 立会区分を設定する。<BR>
     * @@param l_strSessionType - (立会区分)<BR>
     * 立会区分
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }

    /**
     * (set扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）をセットする。<BR>
     * @@param l_strSonarTraderCode - (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）<BR>
     */
    public void setSonarTraderCode(String l_strSonarTraderCode)
    {
        this.sonarTraderCode = l_strSonarTraderCode;
    }

    /**
     * (set初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネルをセットする。<BR>
     * @@param l_strOrderChanel - (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル<BR>
     */
    public void setOrderChanel(String l_strOrderChanel)
    {
        this.orderChanel = l_strOrderChanel;
    }

    /**
     * (set注文経路区分)<BR>
     * 注文経路区分をセットする。<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }
}
@
