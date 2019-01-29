head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制UnitServiceImpl(WEB3AioSecurityTransferForceUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 屈陽 (中訊) 新規作成
Revesion History : 2007/03/16 何文敏 (中訊) モデルNo.715
Revesion History : 2007/03/28 何文敏(中訊)　@実装の問題  No.010
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor;
import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostCommodityDef;
import webbroker3.common.define.WEB3AioTransNotifyTransferFlagDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券振替強制UnitServiceImpl)<BR>
 * 証券振替強制UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceUnitServiceImpl implements WEB3AioSecurityTransferForceUnitService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceUnitServiceImpl.class); 
    
    /**
     * (submit注文)<BR>
     * 証券振替の注文を登録し、配列を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替強制）submit注文」 参照
     * @@param l_params - 代用振替強制キューテーブルの行オブジェクト
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public AioOrderUnit[] submitOrder(HostMrgsecTransNotifyParams l_params) throws WEB3BaseException
    {
        String l_strMethodName = "submitOrder(HostMrgsecTransNotifyParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 証券会社オブジェクトを取得する。 
        //InstitutionImpl(long)
        //[引数] 
        //証券会社コード： 引数.代用振替強制キューParams.証券会社コード 
        
        //InstitutionCode
        String l_strInstitutionCode = l_params.getInstitutionCode();
        
        //FinApp, AccountManager, Institution
        FinApp l_finApp = 
            (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();    
           
        Institution l_institution;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
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
        
        //1.2 代理入力者オブジェクトを取得する。 
        //TraderImpl(long, boolean)
        //[引数] 
        //証券会社：証券会社オブジェクト --l_institution 
        //扱者コード： 引数.代用振替強制キューParams.扱者コード --l_strTraderCode
        //部店コード： 引数.代用振替強制キューParams.部店コード --l_strBranchCode 
        
        //a> traderCode
        String l_strTraderCode = l_params.getTraderCode();
        
        //b> branchCode 
        String l_strBranchCode = l_params.getBranchCode();
        
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Trader l_trader = null;
        if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
        {
            try
            {
                l_trader = l_finObjMgr.getTrader(l_institution, l_strTraderCode, l_strBranchCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(" テーブルに該当するデータがありません: ", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //1.3 銘柄を取得する。 
        //get銘柄(ProductTypeEnum, String, Institution)
        //[引数] 
        //銘柄タイプ： 代用振替強制キューParams.商品区分に対応する銘柄タイプ 
        //銘柄コード： 代用振替強制キューParams.銘柄コード 
        //証券会社： 証券会社オブジェクト --l_institution
        
        //a> TradingModule
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> 銘柄タイプ
        String l_strCommodityDiv = l_params.getCommodityDiv();
        
        ProductTypeEnum l_productTypeEnum = null;
        if (WEB3AioHostCommodityDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.EQUITY;        
        }
        else if (WEB3AioHostCommodityDef.BOND.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.BOND;        
        }
        else if (WEB3AioHostCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.MUTUAL_FUND;        
        }
        
        //d> 銘柄コード
        String l_strProductCode = l_params.getProductCode();
        
        //e> get銘柄    
        Product l_product = l_productManager.getProduct(
            l_productTypeEnum,
            l_strProductCode,
            l_institution);
            
        //1.4 顧客オブジェクトを取得する。
        //MainAccountImpl(long) 
        //[引数] 
        //証券会社ID： 証券会社.getInstitutionId()の戻り値 --l_lngInstitutionId
        //部店コード： 引数.代用振替強制キューParams.部店コード --l_strBranchCode
        //顧客コード： 引数.代用振替強制キューParams.顧客コード --l_strAccountCode
        
        //a> 証券会社ID
        long l_lngInstitutionId = l_institution.getInstitutionId();
        
        //b> 顧客コード
        String l_strAccountCode = l_params.getAccountCode();
        
        MainAccount l_mainAccount;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngInstitutionId, l_strBranchCode, l_strAccountCode);
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
          
        //1.5 空のListを生成する。 
        List l_lisOrderUnits = new ArrayList();  
        
        //1.6 振替タイプ[]（=[1（入金）、2（出金）]） の要素毎にLoop処理
        AssetTransferTypeEnum[] l_transferTypeEnum = new AssetTransferTypeEnum[2];
        l_transferTypeEnum[0] = AssetTransferTypeEnum.CASH_IN;
        l_transferTypeEnum[1] = AssetTransferTypeEnum.CASH_OUT;
        
        for (int i = 0; i < 2; i++)
        {
            //1.6.1 入出金注文内容インスタンスを生成する。 
            //[引数] 
            //代理入力者： 扱者オブジェクト --l_trader
            //注文種別： --l_orderTypeEnum
            //引数.代用振替強制キューParams.預り区分=01（保護預り）の場合、1010（証券振替注文（代用有価証券から保護預り）） 
            //引数.代用振替強制キューParams.預り区分=04（代用）の場合、1009（証券振替注文（保護預りから代用有価証券）） 
            //振替タイプ： 振替タイプの要素 l_transferTypeEnum[i]
            //銘柄ID： （get銘柄()の戻り値）.銘柄ID --l_lngProductId
            //数量： --l_dblQuantity
            //１）銘柄タイプの判定
            //・引数.代用振替強制キューParams.
            //商品区分に対応する銘柄タイプ = "2"（投信）
            //の場合
            //(1) 入力単位の判定
            //（get銘柄()の戻り値）.入力単位 = 1（1）の場合、
            //引数.代用振替強制キューParams.数量
            //（get銘柄()の戻り値）.入力単位 = 2（1万）の場合、
            //引数.代用振替強制キューParams.数量 * 10000
            //(2) 振替タイプの判定
            //振替タイプ = 1（入金）の場合、
            //(1)で取得した数量
            //振替タイプ = 2（出金）の場合、
            //(1)で取得した数量 × -1
            //・引数.代用振替強制キューParams.
            //商品区分に対応する銘柄タイプ != "2"（投信以外）
            // の場合
            //(1) 振替タイプの判定
            //振替タイプ=1（入金）の場合、
            //引数.代用振替強制キューParams.数量
            //振替タイプ=2（出金）の場合、
            //引数.代用振替強制キューParams.数量 × -1
            //記述： null 
            //振替予定日： 現在日付 --WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp())
            //決済機@関ID： null 
            //注文ID： null 
    
            //a> 注文種別
            OrderTypeEnum l_orderTypeEnum;
            if (WEB3AioTransNotifyTransferFlagDef.SAFE_DEPOSIT.equals(l_params.getTransferFlag()))
            {
                l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
            }
            else 
            {
                l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
            }
            //b> 銘柄ID
            long l_lngProductId = l_product.getProductId();
            
            //c> 数量
            double l_lngQuantity = 0;
            // 　@・引数.代用振替強制キューParams.商品区分に対応する銘柄タイプ = "2"（投信）の場合
            if (WEB3AioHostCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
            {
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_product.getDataSourceObject();

                //（get銘柄()の戻り値）.入力単位 = 1（1）の場合、引数.代用振替強制キューParams.数量
                if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                {
                    l_lngQuantity = l_params.getQuantity();
                }
                //（get銘柄()の戻り値）.入力単位 = 2（1万）の場合、引数.代用振替強制キューParams.数量 * 10000
                else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getQuantity()));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(10000));
                    l_lngQuantity = l_bdResult.doubleValue();
                }
                // (2) 振替タイプの判定
                // 振替タイプ = 1（入金）の場合、１で取得した数量
                //振替タイプ = 2（出金）の場合、１で取得した数量 × -1
                if (AssetTransferTypeEnum.CASH_OUT.equals(l_transferTypeEnum[i]))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_lngQuantity));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(-1));
                    l_lngQuantity = l_bdResult.doubleValue(); 
                }
            }
            else
            {
                //振替タイプの判定振替タイプ=1（入金）の場合、
                if (AssetTransferTypeEnum.CASH_IN.equals(l_transferTypeEnum[i]))
                {
                    l_lngQuantity = l_params.getQuantity();
                }
                //振替タイプ = 2（出金）の場合、１で取得した数量 × -1
                else if (AssetTransferTypeEnum.CASH_OUT.equals(l_transferTypeEnum[i]))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getQuantity()));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(-1));
                    l_lngQuantity = l_bdResult.doubleValue(); 
                }
            }

            //new
            WEB3AioNewOrderSpec l_aioNewOrderSpec = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    l_orderTypeEnum,
                    l_transferTypeEnum[i],
                    l_lngProductId,
                    l_lngQuantity,
                    null,
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()),
                    null,
                    null);
                    
        
            //1.6.2 証券振替強制更新インタセプタのインスタンスを生成する。 
            //[引数] 
            //入出金注文内容： 入出金注文内容オブジェクト 
            WEB3AioSecurityTransferForceUpdateInterceptor l_aioSecurityTransferForceUpdateInterceptor = 
                new WEB3AioSecurityTransferForceUpdateInterceptor(l_aioNewOrderSpec);
                
            //1.6.3 以下のとおりにインタセプタのプロパティをセットする。
            
            //a> インタセプタ.発注日 = 入出金注文内容.振替予定日
            l_aioSecurityTransferForceUpdateInterceptor.setOrderBizDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //b> インタセプタ.受渡日 = 入出金注文内容.振替予定日
            l_aioSecurityTransferForceUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //c> インタセプタ.税区分 = （以下のとおり）
            //代用振替強制キューParams.特定口座区分 = 0（一般）の場合、1（一般）
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                l_aioSecurityTransferForceUpdateInterceptor.setTaxType(
                    TaxTypeEnum.NORMAL);
            }
            //代用振替強制キューParams.特定口座区分 = 1（特定）の場合、2（特定）
            else
            {
                l_aioSecurityTransferForceUpdateInterceptor.setTaxType(
                    TaxTypeEnum.SPECIAL);
            }
            
            //d> インタセプタ.識別コード = 代用振替強制キューParams.識別コード
            l_aioSecurityTransferForceUpdateInterceptor.setOrderRequestNumber(
                l_params.getOrderRequestNumber());
             
            //1.6.4 インタセプタをセットする。
            //[引数] 
            //証券振替強制更新インタセプタ： 証券振替強制更新インタセプタオブジェクト 
            
            //AioOrderManager
            WEB3AioOrderManager l_orderManager = 
                (WEB3AioOrderManager)l_tradingModule.getOrderManager();
                
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSecurityTransferForceUpdateInterceptor);
            
            //1.6.5 補助口座タイプを取得する。 
            //[引数] 
            //注文種別： --l_orderTypeEnum
            //引数.代用振替強制キューParams.預り区分=01（保護預り）の場合、1010（証券振替注文（代用有価証券から保護預り）） 
            //引数.代用振替強制キューParams.預り区分=04（代用）の場合、1009（証券振替注文（保護預りから代用有価証券）） 
            //振替タイプ： 振替タイプの要素 --l_transferTypeEnum[i]
            SubAccountTypeEnum l_subAccountTypeEnum = 
                this.getSubAccountType(
                    l_orderTypeEnum,
                    l_transferTypeEnum[i]);
                    
            //1.6.6 補助口座オブジェクトを取得する。 
            //[引数] 
            //補助口座タイプ： get補助口座タイプ()の戻り値 
            WEB3GentradeSubAccount l_subAccount;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(l_subAccountTypeEnum);
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

            //1.6.7 新規注文IDを取得する。 
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            //1.6.8 注文登録処理を行う。 
            //[引数] 
            //補助口座： 補助口座オブジェクト --l_subAccount
            //銘柄タイプ： 代用振替強制キューParams.商品区分に対応する銘柄タイプ --l_productTypeEnum
            //注文内容： 入出金注文内容オブジェクト --l_aioNewOrderSpec
            //注文ID： createNewOrderId()の戻り値 --l_lngNewOrderId
            //パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
            //isSkip発注審査： true 

            //パスワード
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strPasswordDecypt = 
                l_crypt.decrypt(l_mainAccount.getTradingPassword());
            
            l_orderManager.submitNewOrder(
                l_subAccount,
                l_productTypeEnum,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_strPasswordDecypt,
                true);
                
            //1.6.9 注文単位を取得する。 
            //(*配列の1番目の要素を取得する） 
            //[引数] 
            //注文ID： createNewOrderId()の戻り値 
            OrderUnit[] l_orderUnits = 
                l_orderManager.getOrderUnits(l_lngNewOrderId);
            //AioOrderUnit l_orderUnit = (AioOrderUnit)l_orderUnits[0];
                
            //1.6.10 リストに追加する。 
            //[引数] 
            //arg0： getOrderUnits()で取得した注文単位 
            l_lisOrderUnits.add((AioOrderUnit)l_orderUnits[0]);
        }
        
        //1.7 配列を取得する。 
        AioOrderUnit[] l_arrayOrderUnits = new AioOrderUnit[2];
        
        l_lisOrderUnits.toArray(l_arrayOrderUnits);    

		//1.8 余力の再計算を行う。 
		//[引数] 
		//補助口座： getSubAccount()の戻り値（保証金口座） 
		WEB3GentradeSubAccount l_subAccount;
		try
		{
			l_subAccount = 
				(WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
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
		WEB3TPTradingPowerReCalcService l_service =
			(WEB3TPTradingPowerReCalcService) Services.getService(
		WEB3TPTradingPowerReCalcService.class);      
        
		l_service.reCalcTradingPower(l_subAccount);
            
        log.exiting(l_strMethodName);
        
        return l_arrayOrderUnits;
    }
    
    /**
     * (get補助口座タイプ)<BR>
     * 引数から決定される補助口座タイプを返却する。<BR>
     * <BR>
     * １）引数.注文種別 = 1009（証券振替注文（保護預りから代用有価証券））の場合<BR>
     * <BR>
     * １－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    2（保証金口座）を返却する。<BR>
     * <BR>
     * １－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * <BR>
     * ２）引数.注文種別 = 1010（証券振替注文（代用有価証券から保護預り））の場合<BR>
     * <BR>
     * ２－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * 
     * ２－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    2（保証金口座）を返却する。
     * @@param l_orderType - 注文種別
     * @@param l_assetTransferType - 振替タイプ
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum
     * @@roseuid 416C9A4B0190
     */
    protected SubAccountTypeEnum getSubAccountType(OrderTypeEnum l_orderType, AssetTransferTypeEnum l_assetTransferType) 
    {
        String l_strMethodName = 
            "getSubAccountType(OrderTypeEnum l_orderType, AssetTransferTypeEnum l_assetTransferType)";
        log.entering(l_strMethodName); 
        
        //return 
        SubAccountTypeEnum l_subAccountTypeEnum;
        
        //１）引数.注文種別 = 1009（証券振替注文（保護預りから代用有価証券））の場合
        if (OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderType))
        {
            //１－１）引数.振替タイプ = 1（入金）の場合
            if (AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            {
                //2（保証金口座）を返却する。
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            //１－２）引数.振替タイプ = 2（出金）の場合
            else 
            {
                //1（預り金口座）を返却する。
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
        }
        //２）引数.注文種別 = 1010（証券振替注文（代用有価証券から保護預り））の場合           
        else
        {
            //２－１）引数.振替タイプ = 1（入金）の場合
            if (AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            {
                //1（預り金口座）を返却する。
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            //２－２）引数.振替タイプ = 2（出金）の場合
            else 
            {
                //2（保証金口座）を返却する。
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
        }   
                        
        log.exiting(l_strMethodName);
        
        return l_subAccountTypeEnum;        
    }
}
@
