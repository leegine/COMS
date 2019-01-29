head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替サービスImpl(WEB3AioSecurityTransferServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 屈陽 (中訊) 新規作成
Revesion History : 2007/04/06 何文敏 (中訊) モデルNo.719対応
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioBizLogicProvider;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSecurityTransferOrderUpdateInterceptor;
import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替サービスImpl)<BR>
 * 証券振替サービス実装クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferServiceImpl extends WEB3ClientRequestService implements WEB3AioSecurityTransferService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferServiceImpl.class); 
    
    /**
     * 証券振替サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、<BR>
     * またはsubmit注文()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4157799D00CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        //リクエストデータの型により、validate決済()または、start決済()
        if (l_request instanceof WEB3AioSecurityTransferConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3AioSecurityTransferConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioSecurityTransferCompleteRequest)
        {
            l_response =
                submitOrder((WEB3AioSecurityTransferCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 振替注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替）validate注文」 参照
     * <BR>
     * ========================================================<BR>
     *     シーケンス図(「（証券振替）validate注文」 」<BR>
     *     (validate注文) <BR>
     *     6) is信用口座開設(String)<BR>
     *     アイテムの定義<BR>
     *     信用口座を開設しているかのチェックを行う。<BR> 
     *     [引数] <BR>
     *     弁済区分： 0（指定なし)<BR>
     * <BR>
     *     戻り値=false の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00747<BR> 
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *     シーケンス図(「（証券振替）validate注文」 」<BR>
     *     (validate注文) <BR> 
     *     10) calc振替可能数量(long, ProductTypeEnum, long, TaxTypeEnum, String)<BR>
     *     アイテムの定義<BR>
     *     振替可能数量を算出する。<BR> 
     *     [引数]<BR> 
     *     口座ID： （get補助口座()の戻り値）.getAccountId()の戻り値<BR> 
     *     銘柄タイプ： リクエストデータ.商品タイプ <BR>
     *     銘柄ID： （get銘柄()の戻り値）.銘柄ID <BR>
     *     税区分： <BR>
     *     リクエストデータ.口座区分=nullの場合： 0（その他）<BR> 
     *     リクエストデータ.口座区分=”一般”の場合： 1（一般） <BR>
     *     リクエストデータ.口座区分=”特定”の場合： 顧客.税区分 <BR>
     *     預り区分： リクエストデータ.振替元預り区分 <BR>
     * <BR>
     *     振替可能数量取得<BR>
     *     リクエストデータ.振替数量 > 取得数量（calc振替可能数量()の戻り値） の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01305<BR> 
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *     シーケンス図(「（証券振替）validate注文」 」<BR>
     *     (validate注文) <BR> 
     *     18) validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],<BR>
     *     注文内容 : Object[], 注文種別 :OrderTypeEnum, 余力更新フラグ : boolean)<BR>
     *     アイテムの定義<BR>
     *     二階建チェックを行う。<BR>
     *     [引数] <BR>
     *     補助口座： 顧客.getSubAccount(補助口座タイプ=2（保証金口座）)の戻り値<BR> 
     *     注文内容インタセプタ： 振替元注文のインタセプタと振替先注文のインタセプタの配列<BR> 
     *     注文内容： 振替元注文の注文内容と振替先注文の注文内容の配列 <BR>
     *     注文種別：<BR> 
     *     リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券）<BR> 
     *     リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） <BR>
     *     余力更新フラグ： false<BR>
     * <BR>
     *     二階建チェック<BR>
     *     戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01306<BR> 
     * <BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioSecurityTransferConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41577A360109
     */
    protected WEB3AioSecurityTransferConfirmResponse validateOrder(WEB3AioSecurityTransferConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateOrder(WEB3AioSecurityTransferConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //FinApp, TradingModule, OrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 信用口座を開設しているかのチェックを行う。 
        //[引数] 
        //弁済区分： 0（指定なし） 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is信用口座開設(String)の戻り値=false の場合、例外をスローする。
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is信用口座開設(String)の戻り値=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is信用口座開設(String)の戻り値=false");            
        }
        
        //1.6 発注日を取得する。 
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 証券振替注文の1日の上限回数を超えてないかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        l_orderManager.validateInstitutionTransferPossibleCount(
            l_subAccount,
            l_datOrderBizDate);
            
        //1.8 銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄タイプ： リクエストデータ.商品タイプ 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;    
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //銘柄コード： リクエストデータ.銘柄コード 
        String l_strProductCode = l_request.productCode;
        //証券会社： 補助口座.getInsutitution()の戻り値 
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
       
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);

        
        // validate証券振替可能単位
        // リクエストデータ．商品タイプに対応する銘柄タイプ="投信"で 
        //（get銘柄()の戻り値）.投信銘柄マスタ.入力単位="2"（1万）の場合、 
        // 振替数量の単位チェックを行う。  
        // １）リクエストデータ．商品タイプに対応する
        // 銘柄タイプ == "投信" 且つ 投信銘柄マスタ.入力単位 == 2（1万）の場合
        // (1)引数.振替数量 / 10000 の剰余が0でない場合
        if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
        {
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_product.getDataSourceObject();
            if ( WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
            {
                this.validateSecurityTransferPossibleUnit(l_request.transferQuantity);
            }
        }

        //1.9 振替可能数量を算出する。 
        //[引数] 
        //a> 口座ID： （get補助口座()の戻り値）.getAccountId()の戻り値 
        long l_lngAccountId = l_subAccount.getAccountId();
        
        //b> 銘柄タイプ： リクエストデータ.商品タイプ ---l_enumInstrumentType in 1.8
        
        //c> 銘柄ID： （get銘柄()の戻り値）.銘柄ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> 税区分： 
        TaxTypeEnum l_taxTypeEnum;
        
        //リクエストデータ.口座区分=nullの場合： 0（その他）
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //リクエストデータ.口座区分=”一般”の場合： 1（一般） 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //リクエストデータ.口座区分=”特定”の場合：2（特定）
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> 預り区分： リクエストデータ.振替元預り区分 
        String l_strDepositDiv = l_request.depositDiv;
        
        //f> get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        double l_dblTransPossibleAmount =
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);
                
        //リクエストデータ.振替数量 > 取得数量（calc振替可能数量()の戻り値） の場合、例外をスローする。
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01305
        if (Double.parseDouble(l_request.transferQuantity) > l_dblTransPossibleAmount)
        {
            log.debug("振替数量は、取得数量より大きいです。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01305,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量[" + l_request.transferQuantity + "] > 取得数量（calc振替可能数量()の戻り値）["
                + l_dblTransPossibleAmount + "]");       
        }
        
        //1.10 代理入力者オブジェクトを取得する。 
        Trader l_trador = this.getTrader();
        
        //***************start of 振替元注文********************
        
        //1.11 入出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 --l_trador
        //注文種別： 
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //振替タイプ： 2（出金）--l_transferTypeEnumOut
        //銘柄ID： 銘柄.銘柄ID --l_lngProductId
        //金額： リクエストデータ.振替数量 × -1 --l_dblTransferQuantityOut
        //記述： null 
        //振替予定日： get発注日()の戻り値 --l_datOrderBizDate
        //決済機@関ID： null 
        //注文ID： null 

        //a> 注文種別
        OrderTypeEnum l_orderTypeEnum;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
        }
        else 
        {
            l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
        }
        
        //b> 振替タイプ
        AssetTransferTypeEnum l_transferTypeEnumOut = AssetTransferTypeEnum.CASH_OUT;
        
        //c> 金額
        double l_dblTransferQuantityOut = 
            (Double.parseDouble(l_request.transferQuantity)) * (-1);
        
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecOut =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumOut,
                l_lngProductId,
                l_dblTransferQuantityOut,
                null,
                l_datOrderBizDate,
                null,
                null);
                
        //1.12 証券振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecOut);
            
        //1.13 以下のとおりに、プロパティをセットする。
        
        //a> インタセプタ.発注日 = get発注日()の戻り値
        l_orderUpdateInterceptorOut.setOrderBizDat(l_datOrderBizDate);
        
        //b> インタセプタ.受渡日 = get発注日()の戻り値
        l_orderUpdateInterceptorOut.setDeliveryDate(l_datOrderBizDate);
        
        //c> インタセプタ.税区分 = （以下のとおり）
        //   リクエストデータ.口座区分=”一般”の場合： 1（一般）
        //   リクエストデータ.口座区分=”特定”の場合： 2（特定）
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> インタセプタ.識別コード = null    
        l_orderUpdateInterceptorOut.setOrderRequestNumber(null);
        
        //***************start of 振替先注文********************
        
        //1.14 入出金注文内容インスタンスを生成する。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 --l_trador
        //注文種別： --l_orderTypeEnum
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //振替タイプ： 1（入金）--l_transferTypeEnumIn 
        //銘柄ID： 銘柄.銘柄ID --l_lngProductId
        //金額： リクエストデータ.振替数量 --l_dblTransferQuantityIn
        //記述： null 
        //振替予定日： get発注日()の戻り値 --l_datOrderBizDate
        //決済機@関ID： null 
        //注文ID： null 
        
        //a> 振替タイプ
        AssetTransferTypeEnum l_transferTypeEnumIn = AssetTransferTypeEnum.CASH_IN;
        
        //b> 金額
        double l_dblTransferQuantityIn = 
            (Double.parseDouble(l_request.transferQuantity));
            
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecIn =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumIn,
                l_lngProductId,
                l_dblTransferQuantityIn,
                null,
                l_datOrderBizDate,
                null,
                null);    
                
        //1.15 証券振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecIn);    
            
        //1.16 以下のとおりに、プロパティをセットする。
        
        //a> インタセプタ.発注日 = get発注日()の戻り値
        l_orderUpdateInterceptorIn.setOrderBizDat(l_datOrderBizDate);
        
        //b> インタセプタ.受渡日 = get発注日()の戻り値
        l_orderUpdateInterceptorIn.setDeliveryDate(l_datOrderBizDate);
        
        //c> インタセプタ.税区分 = （以下のとおり）
        //   リクエストデータ.口座区分=”一般”の場合： 1（一般）
        //   リクエストデータ.口座区分=”特定”の場合： 2（特定）
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.SPECIAL);
        }
        
        //d> インタセプタ.識別コード = null    
        l_orderUpdateInterceptorIn.setOrderRequestNumber(null);
        
        //1.17 二階建チェックを行う。 
        //[引数] 
        //補助口座： 顧客.getSubAccount(補助口座タイプ=2（保証金口座）)の戻り値 --l_subAccount
        //注文内容インタセプタ： 振替元注文のインタセプタと振替先注文のインタセプタの配列 --l_orderUpdateInterceptor
        //注文内容： 振替元注文の注文内容と振替先注文の注文内容の配列 --l_aioNewOrderSpec
        //注文種別： --l_orderTypeEnum
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //余力更新フラグ： false 
        
        //a> 補助口座
        WEB3GentradeSubAccount l_subAccountCheck = 
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        //b> 取引余力サービス
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        //c> 注文内容インタセプタ
        WEB3AioSecurityTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //d> 注文内容
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_web3AioNewOrderSpecOut, l_web3AioNewOrderSpecIn}; 
            
        //validate
        WEB3TPTradingPowerResult l_result = l_service.validateTradingPower(
            l_subAccountCheck,
            l_orderUpdateInterceptor,
            l_aioNewOrderSpec,
            l_orderTypeEnum,
            false);
            
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01306
        if (l_result.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + l_strMethodName,
                "取引余力結果.判定フラグ == false");     
        }
            
        //1.18 新規の注文IDを取得する。 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.19 レスポンスデータを生成する。 
        WEB3AioSecurityTransferConfirmResponse l_response = 
            (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
        
        //1.20 以下のとおりに、プロパティをセットする。
        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate = l_datOrderBizDate;
        
        //レスポンス.注文ID = AIO注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = String.valueOf(l_lngNewOrderId);
     
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 振替注文の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替）submit注文」 参照
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSecurityTransferCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41577A95032B
     */
    protected WEB3AioSecurityTransferCompleteResponse submitOrder(WEB3AioSecurityTransferCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitOrder(WEB3AioSecurityTransferCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccountEQUITY = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //FinApp, TradingModule, OrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccountEQUITY);
        
        //1.4 顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccountEQUITY.getMainAccount();
        
        //1.5 信用口座を開設しているかのチェックを行う。 
        //[引数] 
        //弁済区分： 0（指定なし） 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is信用口座開設(String)の戻り値=false の場合、例外をスローする。
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is信用口座開設(String)の戻り値=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is信用口座開設(String)の戻り値=false");            
        }
        
        //1.6 発注日を取得する。 
        Date l_confirmBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7 証券振替注文の1日の上限回数を超えてないかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        l_orderManager.validateInstitutionTransferPossibleCount(
        l_subAccountEQUITY,
            l_confirmBizDate);
            
        //1.8 銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄タイプ： リクエストデータ.商品タイプ 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;    
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //銘柄コード： リクエストデータ.銘柄コード 
        String l_strProductCode = l_request.productCode;
        //証券会社： 補助口座.getInsutitution()の戻り値 
        Institution l_institution = l_subAccountEQUITY.getInstitution(); 
        
        //AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
       
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);
        
        //1.9 振替可能数量を算出する。 
        //[引数] 
        //a> 口座ID： （get補助口座()の戻り値）.getAccountId()の戻り値 
        long l_lngAccountId = l_subAccountEQUITY.getAccountId();
        
        //b> 銘柄タイプ： リクエストデータ.商品タイプ ---l_enumInstrumentType in 1.8
        
        //c> 銘柄ID： （get銘柄()の戻り値）.銘柄ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> 税区分： 
        TaxTypeEnum l_taxTypeEnum;
        
        //リクエストデータ.口座区分=nullの場合： 0（その他）
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //リクエストデータ.口座区分=”一般”の場合： 1（一般） 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //リクエストデータ.口座区分=”特定”の場合： 2（特定） 
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> 預り区分： リクエストデータ.振替元預り区分 
        String l_strDepositDiv = l_request.depositDiv;
        
        //get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // test log
        log.debug("l_lngAccountId = " + l_lngAccountId);
        log.debug("l_enumInstrumentType = " + l_enumInstrumentType);
        log.debug("l_lngProductId = " + l_lngProductId);
        log.debug("l_taxTypeEnum = " + l_taxTypeEnum);
        log.debug("l_strDepositDiv = " + l_strDepositDiv);
       
        double l_dblTransPossibleAmount = 
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);
        
        
        
                
        //リクエストデータ.振替数量 > 取得数量（calc振替可能数量()の戻り値） の場合、例外をスローする。
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01305
        if (Double.parseDouble(l_request.changeQuantity) > l_dblTransPossibleAmount)
        {
            log.debug("振替数量は、取得数量より大きいです。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01305,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替数量[" + l_request.changeQuantity + "] > 取得数量（calc振替可能数量()の戻り値）["
                + l_dblTransPossibleAmount + "]");       
        }
        
        //1.10 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 2（保証金口座） 
        WEB3GentradeSubAccount l_subAccountMARGIN = 
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        //1.11 代理入力者オブジェクトを取得する。 
        Trader l_trador = this.getTrader();
        
        //1.12 新規の識別コードを取得する。 
        //[引数] 
        //a> 証券会社コード： 補助口座.getInsutitution().getInstitutionCode()の戻り値 
        String l_strInstitutionCode = 
            l_subAccountMARGIN.getInstitution().getInstitutionCode();
        
        //b> 部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        String l_strBranchCode = 
            l_subAccountMARGIN.getWeb3GenBranch().getBranchCode();
        
        //銘柄タイプ： リクエストデータ.商品タイプ --l_enumInstrumentType
        
        //識別番号採番インターフェース
        WEB3HostReqOrderNumberManageService l_orderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
                
        String l_strNewNumber = l_orderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_strBranchCode,
            l_enumInstrumentType);
        
        //***************start of 振替元注文********************
        
        //1.13 入出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 --l_trador
        //注文種別： 
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //振替タイプ： 2（出金）--l_transferTypeEnumOut
        //銘柄ID： 銘柄.銘柄ID --l_lngProductId
        //金額： リクエストデータ.振替数量 × -1 --l_dblTransferQuantityOut
        //記述： null 
        //振替予定日： get発注日()の戻り値 --l_confirmBizDate
        //決済機@関ID： null 
        //注文ID： null 

        //a> 注文種別
        OrderTypeEnum l_orderTypeEnum;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
        }
        else 
        {
            l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
        }
        
        //b> 振替タイプ
        AssetTransferTypeEnum l_transferTypeEnumOut = AssetTransferTypeEnum.CASH_OUT;
        
        //c> 金額
        double l_dblTransferQuantityOut = 
            (Double.parseDouble(l_request.changeQuantity)) * (-1);
        
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecOut =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumOut,
                l_lngProductId,
                l_dblTransferQuantityOut,
                null,
                l_confirmBizDate,
                null,
                null);
                
        //1.14 証券振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecOut);
            
        //1.15 以下のとおりに、プロパティをセットする。
        
        //a> インタセプタ.発注日 = get発注日()の戻り値
        l_orderUpdateInterceptorOut.setOrderBizDat(l_confirmBizDate);
        
        //b> インタセプタ.受渡日 = get発注日()の戻り値
        l_orderUpdateInterceptorOut.setDeliveryDate(l_confirmBizDate);
        
        //c> インタセプタ.税区分 = （以下のとおり）
        //   リクエストデータ.口座区分=”一般”の場合： 1（一般）
        //   リクエストデータ.口座区分=”特定”の場合： 2（特定）
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> インタセプタ.識別コード = get新規識別コード()の戻り値    
        l_orderUpdateInterceptorOut.setOrderRequestNumber(l_strNewNumber);
        
        //***************start of 振替先注文********************
        
        //1.16 入出金注文内容インスタンスを生成する。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 --l_trador
        //注文種別： --l_orderTypeEnum
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //振替タイプ： 1（入金）--l_transferTypeEnumIn 
        //銘柄ID： 銘柄.銘柄ID --l_lngProductId
        //金額： リクエストデータ.振替数量 --l_dblTransferQuantityIn
        //記述： null 
        //振替予定日： get発注日()の戻り値 --l_confirmBizDate
        //決済機@関ID： null 
        //注文ID： null 
        
        //a> 振替タイプ
        AssetTransferTypeEnum l_transferTypeEnumIn = AssetTransferTypeEnum.CASH_IN;
        
        //b> 金額
        double l_dblTransferQuantityIn = 
            (Double.parseDouble(l_request.changeQuantity));
            
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecIn =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumIn,
                l_lngProductId,
                l_dblTransferQuantityIn,
                null,
                l_confirmBizDate,
                null,
                null);    
                
        //1.17 証券振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecIn);    
            
        //1.18 (*1)(*2)以下のとおりに、プロパティをセットする。
        
        //a> インタセプタ.発注日 = get発注日()の戻り値
        l_orderUpdateInterceptorIn.setOrderBizDat(l_confirmBizDate);
        
        //b> インタセプタ.受渡日 = get発注日()の戻り値
        l_orderUpdateInterceptorIn.setDeliveryDate(l_confirmBizDate);
        
        //c> インタセプタ.税区分 = （以下のとおり）
        //   リクエストデータ.口座区分=”一般”の場合： 1（一般）
        //   リクエストデータ.口座区分=”特定”の場合： 2（特定）
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> インタセプタ.識別コード = get新規識別コード()の戻り値    
        l_orderUpdateInterceptorIn.setOrderRequestNumber(l_strNewNumber);
        
        //1.19 二階建チェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値（保証金口座）--l_subAccountCheck
        //注文内容インタセプタ： 振替元注文のインタセプタと振替先注文のインタセプタの配列 
        //注文内容： 振替元注文の注文内容と振替先注文の注文内容の配列 
        //注文種別： 
        //リクエストデータ.預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //余力更新フラグ： true
        
        //a> 取引余力サービス
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        //b> 注文内容インタセプタ
        WEB3AioSecurityTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //c> 注文内容
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_web3AioNewOrderSpecOut, l_web3AioNewOrderSpecIn}; 
            
        //validate
        WEB3TPTradingPowerResult l_result = l_service.validateTradingPower(
            l_subAccountMARGIN,
            l_orderUpdateInterceptor,
            l_aioNewOrderSpec,
            l_orderTypeEnum,
            true);
            
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01306
        if (l_result.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + l_strMethodName,
                "取引余力結果.判定フラグ == false");     
        }   
        
        //1.20 振替元の証券振替注文を登録する。 
        //[引数] 
        //補助口座：--l_subAccountOut
        //リクエストデータ.振替元預り区分 = 1（保護）の場合、get補助口座()の戻り値（預り金口座） 
        //リクエストデータ.振替元預り区分 = 2（代用）の場合、get補助口座()の戻り値（保証金口座） 
        //銘柄タイプ： リクエストデータ.商品タイプ --l_enumInstrumentType
        //注文種別： --l_orderTypeEnum
        //リクエストデータ.振替元預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.振替元預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //注文内容：入出金注文内容（振替元注文）--l_web3AioNewOrderSpecOut 
        //インタセプタ：証券振替注文更新インタセプタ（振替元注文）-- l_orderUpdateInterceptorOut
        //注文ID： リクエストデータ.注文ID --l_lngOrderId
        //パスワード： リクエストデータ.暗証番号 --l_strPassword
        
        //a> 補助口座 
        SubAccount l_subAccountOut = null;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountEQUITY;        
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountMARGIN;        
        }
        
        //b> 注文ID
        long l_lngOrderId = Long.parseLong(l_request.orderId);
        
        //c> パスワード
        String l_strPassword = l_request.password;
        
        //test log
        log.debug("subAccount = " + l_subAccountOut);
        log.debug("enumInstrumentType = " + l_enumInstrumentType);
        log.debug("orderTypeEnum = " + l_orderTypeEnum);
        log.debug("web3AioNewOrderSpecOut = " + l_web3AioNewOrderSpecOut);
        log.debug("orderUpdateInterceptorOut = " + l_orderUpdateInterceptorOut);
        log.debug("OrderId = " + l_lngOrderId);
        log.debug("Password = " + l_strPassword);
        
        l_orderManager.submitTransferOrder(
            l_subAccountOut,
            l_enumInstrumentType,
            l_orderTypeEnum,
            l_web3AioNewOrderSpecOut,
            l_orderUpdateInterceptorOut,
            l_lngOrderId,
            l_strPassword);    
        
        //1.21 反対注文用の注文IDを取得する。  
        long lngNewOrderId = l_orderManager.createNewOrderId();
        //test log
        log.debug("2 OrderId = " + lngNewOrderId);
        
        //1.22 振替先の証券振替注文を登録する。 
        //[引数] 
        //補助口座： --l_subAccountOut
        //リクエストデータ.振替元預り区分 = 1（保護）の場合、get補助口座()の戻り値（保証金口座） 
        //リクエストデータ.振替元預り区分 = 2（代用）の場合、get補助口座()の戻り値（預り金口座） 
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountMARGIN;        
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountEQUITY;        
        }

        //銘柄タイプ： リクエストデータ.商品タイプ --l_enumInstrumentType
        //注文種別： --l_orderTypeEnum
        //リクエストデータ.振替元預り区分 = 1（保護）の場合、1009（証券振替注文（保護預りから代用有価証券） 
        //リクエストデータ.振替元預り区分 = 2（代用）の場合、1010（証券振替注文（代用有価証券から保護預り） 
        //注文内容：入出金注文内容（振替先注文） --l_web3AioNewOrderSpecIn
        //インタセプタ：証券振替注文更新インタセプタ（振替先注文）--l_orderUpdateInterceptorIn 
        //注文ID： createNewOrderId()の戻り値 --lngNewOrderId
        //パスワード： リクエストデータ.暗証番号 --l_strPassword

        l_orderManager.submitTransferOrder(
            l_subAccountOut,
            l_enumInstrumentType,
            l_orderTypeEnum,
            l_web3AioNewOrderSpecIn,
            l_orderUpdateInterceptorIn,
            lngNewOrderId,
            l_strPassword);
            
        //注文を取得する。 
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        try
        {
            Order l_order = l_orderManager.getOrder(l_lngOrderId);           

            //1.24 レスポンスデータを生成する。 
            WEB3AioSecurityTransferCompleteResponse l_response = 
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            
            //1.25 以下のとおりに、プロパティをセットする。
            //レスポンス.更新時間 = 注文.更新日付
            l_response.lastUpdatedTimestamp = ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        
            //レスポンス.注文ID = リクエストデータ.注文ID
            l_response.orderId = String.valueOf(l_lngOrderId);
            
            log.exiting(l_strMethodName);
            
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
    }    

    /**
     * (validate証券振替可能単位)
     * 振替数量の単位チェックを行う。
     * 
     * 引数.振替数量 / 10000 の剰余が0でない場合、
     * 例外をスローする。
     * 
     * @@param - (振替数量)
     * リクエストデータ．振替数量
     * @@throws WEB3BaseException 
     */
    private void validateSecurityTransferPossibleUnit(String l_strQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSecurityTransferPossibleUnit()";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_bdTransferQuantity = new BigDecimal(l_strQuantity);
        BigDecimal l_bdResult =
            l_bdTransferQuantity.divide(new BigDecimal(10000), 0);
        BigDecimal l_bdResult1 = l_bdResult.multiply(new BigDecimal(10000));

        if (l_bdResult1.compareTo(l_bdTransferQuantity) != 0)
        {
            log.debug("引数.振替数量 / 10000 の剰余が0でない");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替数量のサイズが不正です。");  
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
