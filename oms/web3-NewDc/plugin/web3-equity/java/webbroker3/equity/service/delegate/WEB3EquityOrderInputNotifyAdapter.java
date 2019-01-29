head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderInputNotifyAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文入力通知データアダプタ(WEB3EquityOrderInputNotifyAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 欒学峰(中訊) 作成
                   2006/08/29 柴雙紅(中訊) 仕様変更モデル970
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式注文入力通知データアダプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderInputNotifyAdapter 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderInputNotifyAdapter.class);
 
    /**
     * (株式注文入力通知キューParams) <BR>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceipt;
   
    /**
     * @@roseuid 40B43077006D
     */
    private  WEB3EquityOrderInputNotifyAdapter() 
    {
    
    }
    
    /**
     * (get執行条件)<BR>
     * <BR>
     * 【株式注文入力通知キューテーブル】執行条件(SONAR)に応じた<BR>
     * EqTypeExecutionConditionTypeを返す。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(株式注文通知キューParams.執行条件(SONAR))に<BR>
     * delegateする。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@throws WEB3SystemLayerException
     * @@roseuid 402714D7026F
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        EqTypeExecutionConditionType l_executionConditionType = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        try {
            l_executionConditionType = l_orderMgr.getExecutionConditionType(this.getHostEqtypeOrderReceipt().execution_condition);
        } catch (WEB3BaseException e) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "株式注文通知キューParams.執行条件(SONAR)が不正な値です。:["
                + this.getHostEqtypeOrderReceipt().execution_condition + "]");
        }


        log.exiting(STR_METHOD_NAME);
        return l_executionConditionType;
    }
   
    /**
     * (is売注文) <BR>
     * 【株式注文入力通知キューテーブル】売買区分より、 <BR>
     * AP層で使用するis売注文を返却する。 <BR>
     * 売買区分＝"現物株式 売付"の場合はtrue、 <BR>
     * 売買区分＝"現物株式 買付"の場合はfalseを返却する。 <BR>
     * 上記以外は例外をスローする。 <BR>
     * class：WEB3BusinessLayerException <BR>
     * tag：  BUSINESS_ERROR_00068 <BR>
     * <BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4027151C029E
     */
    public boolean isSellOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        String l_strTradeType = this.hostEqtypeOrderReceipt.getTradeType();
        if(l_strTradeType.equals(WEB3TradeTypeDef.SELL))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if(l_strTradeType.equals(WEB3TradeTypeDef.BUY))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00068,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get税区分)<BR>
     * <BR>
     * 【株式注文入力通知キューテーブル】税区分（特定口座区分）（以下「税区分」）より、<BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。 <BR>
     * <BR>
     * １）株式データアダプタ.getAP口座区分(税区分)により税区分を取得する。 <BR>
     * <BR>
     * ２）顧客オブジェクトを取得する。(*1) <BR>
     * <BR>
     * ３）株式発注審査個別チェック.validate特定口座開設()(*2)をコールする。<BR>
     * <BR>
     * ４）１）で取得した税区分を返却する。<BR>
     * <BR>
     * (*1) 顧客オブジェクト取得方法@はシーケンス図「（注文通知）get顧客」を参照。<BR>
     * <BR>
     * 　@　@証券会社オブジェクトは、拡張アカウントマネージャ.getInstitution( <BR>
     * 　@　@this.株式注文入力通知キューParams.証券会社コード)で取得。<BR>
     * <BR>
     * (*2)指定する引数は以下の通り<BR> 
     * 　@・補助口座 <BR>
     *   　@　@顧客.getSubAccount()の戻り値を設定<BR> 
     * 　@　@　@※シーケンス図「（株式注文通知サービス）calc概算受渡代金コール」参照。<BR>
     * 　@・税区分<BR>
     * 　@　@　@１）で取得した税区分<BR>
     * 　@・受渡日<BR>
     * 　@　@　@拡張プロダクトマネージャ.get取引銘柄(証券会社, this.株式注文入力通知キューParams.銘柄コード,<BR>
     * 　@　@this.get市場コード()).getDailyDeliveryDate( )をセット。<BR> 
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "getTaxDivision()";
        log.entering(STR_METHOD_NAME);

        TaxTypeEnum l_taxTypeEnum = null;
        //１）株式データアダプタ.getAP口座区分(税区分)により税区分を取得する。
        int l_intTaxType = this.getHostEqtypeOrderReceipt().getTaxType();
        String l_strTaxType = WEB3EquityDataAdapter.getApTaxType(l_intTaxType + "");
        //２）顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        Institution l_institution = null;
            
        try
        {
            l_institution =
                l_accMgr.getInstitution(this.getHostEqtypeOrderReceipt().getInstitutionCode());
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accMgr.getMainAccount(
                    l_institution.getInstitutionId(),
                    this.getHostEqtypeOrderReceipt().getBranchCode(),
                    this.getHostEqtypeOrderReceipt().getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //３）株式発注審査個別チェック.validate特定口座開設()(*2)をコールする。
        
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            //is信用口座開設()==true（＝信用客）
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //is信用口座開設()≠true（＝非信用客）の場合
            else
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        Date l_dailyDeliveryDate = null;
        try
        {
            EqTypeTradedProduct l_tradedProduct =
                l_productManager.getTradedProduct(
                    l_institution,
                    this.getHostEqtypeOrderReceipt().getProductCode(),
                    this.getMarketCode());
            l_dailyDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if((TaxTypeEnum.NORMAL.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else if((TaxTypeEnum.SPECIAL.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if((TaxTypeEnum.STOCK_OPTION.intValue() + "").equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.STOCK_OPTION;
        }

        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();     
        l_orderManagerReusableValidations.validateSpecialAccountEstablish(
            l_subAccount,
            l_taxTypeEnum,
            l_dailyDeliveryDate);        
        
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
   
    /**
     * 株式注文入力通知データアダプタインスタンスを生成する。 <BR>
     * <BR>
     * １）　@本インスタンスを生成しする。 <BR>
     * ２）　@生成したインスタンスに引数のキューデータをセットする。 <BR>
     * ３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、 <BR>
     * 本メソッドによってインスタンス化するように制限する） <BR>
     * <BR>
     * @@param l_hostEqtypeOrderReceipt - 
     * 【株式注文入力通知キューテーブル】データオブジェクト <BR>
     * @@return webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter
     * @@roseuid 402776A60369
     */
    public static WEB3EquityOrderInputNotifyAdapter create(HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceipt)
    {
        WEB3EquityOrderInputNotifyAdapter l_adapter =
            new WEB3EquityOrderInputNotifyAdapter();
        l_adapter.hostEqtypeOrderReceipt = l_hostEqtypeOrderReceipt;

        return l_adapter;
    }
   
    /**
     * (get市場コード)<BR>
     * <BR>
     * 【株式注文入力通知キューテーブル】市場コード（SONAR）より、<BR>
     * WebⅢの市場コードを返す。<BR>
     * <BR>
     * １）　@拡張金融オブジェクトマネージャ.get市場BySONAR( )により、<BR>
     * 　@　@　@市場オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get市場BySONAR( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@証券会社コード：　@株式注文入力通知キューParams.証券会社コード<BR>
     * 　@　@　@市場コード（SONAR）：　@株式注文入力通知キューParams.市場コード（SONAR）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２）　@取得した市場オブジェクト.市場コード を返す。<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3SystemLayerException
     * @@roseuid 4031D82E005D
     */
    public String getMarketCode() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market l_market = null;
        try
        {
	        l_market = l_gentradeFinObjectManager.getMarketBySONAR(
	            this.getHostEqtypeOrderReceipt().getInstitutionCode(),
	            this.getHostEqtypeOrderReceipt().getSonarMarketCode());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("市場オブジェクトの取得に失敗しました。証券会社コード:[" +
                this.getHostEqtypeOrderReceipt().getInstitutionCode() + "] " +
                "市場コード(SONAR):[" + this.getHostEqtypeOrderReceipt().getSonarMarketCode() +
                "]");
            throw l_ex;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
    }
   
    /**
     * (is指値) <BR>
     * 【株式注文入力通知キューテーブル】指値区分より、 <BR>
     * AP層で使用するis指値を返却する。 <BR>
     * 指値区分＝"1：指値"の場合はtrue、 <BR>
     * 指値区分＝"0：成行"はfalseを返却する。 <BR>
     * 上記以外は例外をスローする。 <BR>
     * class：WEB3BusinessLayerException <BR>
     * tag：  BUSINESS_ERROR_00069 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4087B4BF02BA
     */
    public boolean isLimitOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isLimitOrder()";
        log.entering(STR_METHOD_NAME);
        
        String l_strExecutionType = this.hostEqtypeOrderReceipt.getExecutionType();
        if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_strExecutionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strExecutionType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            WEB3BusinessLayerException l_businessLayerException =  
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00069,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指値区分 = " + l_strExecutionType);
            log.error(STR_METHOD_NAME,l_businessLayerException);
            throw l_businessLayerException;
        }
    }
    
    /**
     * (get値段条件)<BR>
     * <BR>
     * 【株式注文入力通知キューテーブル】値段条件(SONAR)に応じた<BR>
     * WEBⅢにおける値段条件を返す。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get値段条件(株式注文通知キューParams.値段条件(SONAR))に<BR>
     * delegateする。<BR>
     * @@return 値段条件
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strPriceConditionType = null;
        try
        {
	        l_strPriceConditionType =
	            l_orderMgr.getPriceConditionType(this.getHostEqtypeOrderReceipt().price_condition_type);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
	            "株式注文通知キューParams.値段条件(SONAR)が不正な値です。:["
	            + this.getHostEqtypeOrderReceipt().price_condition_type + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }
    
    
    /**
     * (get株式注文入力通知キューParams)<BR>
     * <BR>
     * @@return 株式注文入力通知キューParams
     */
    public HostEqtypeOrderReceiptParams getHostEqtypeOrderReceipt() {
        return this.hostEqtypeOrderReceipt;
    }

    /**
     * (set株式注文入力通知キューParams)<BR>
     * @@param params 株式注文入力通知キューParams
     */
    public void setHostEqtypeOrderReceipt(HostEqtypeOrderReceiptParams hostEqtypeOrderReceipt) {
        this.hostEqtypeOrderReceipt = hostEqtypeOrderReceipt;
    }
}
@
