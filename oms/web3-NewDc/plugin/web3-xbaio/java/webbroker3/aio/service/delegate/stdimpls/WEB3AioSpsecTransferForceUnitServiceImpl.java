head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制UnitServiceImpl(WEB3AioSpsecTransferForceUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 韋念瓊 (中訊) 新規作成
                   2006/10/26 何文敏 (中訊) 仕様変更・モデル665
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSpsecTransferForceUpdateInterceptor;
import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostSpsecCommodityDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CustdyDiv;
import webbroker3.common.define.WEB3IoDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3NameMethodDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (特定口座振替強制UnitServiceImpl)<BR>
 * 特定口座振替強制UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * を指定する。
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUnitServiceImpl implements WEB3AioSpsecTransferForceUnitService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUnitServiceImpl.class); 
    
    /**
     * (submit注文)<BR>
     * 特定口座振替の注文を登録し、その注文単位を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（特定口座振替強制）submit注文」 参照 
     * @@param l_params - 特定口座強制振替キューテーブルの行オブジェクト
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public AioOrderUnit[] submitOrder(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException
    {   
        String STR_METHOD_NAME = "submitOrder(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 証券会社オブジェクトを取得する。 
        //InstitutionImpl(long)
        //[引数] 
        //証券会社コード： 引数.特定口座強制振替キューParams.証券会社コード 
        
        FinApp l_finApp = 
            (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = GtlUtils.getAccountManager();  
           
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_params.getInstitutionCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String l_strBranchCode = l_params.getBranchCode();
        
        //1.2 銘柄を取得する。 
        //get銘柄(ProductTypeEnum, String, Institution)
        //[引数] 
        //銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ 
        //銘柄コード： 特定口座強制振替キューParams.銘柄コード 
        //証券会社： 証券会社オブジェクト 

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ 
        String l_strCommodityDiv = l_params.getCommodityDiv();
        
        ProductTypeEnum l_productType = null;
        if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.EQUITY;        
        }
        else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.BOND;
        }
        else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.MUTUAL_FUND;        
        }
        else if (WEB3AioHostSpsecCommodityDef.FOREIGN_EQUITY.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.FOREIGN_EQUITY;        
        }
        
        log.debug("銘柄タイプ： = " + l_productType);
        String l_strProductCode = l_params.getProductCode();
        
        Product l_product = l_productManager.getProduct(
                l_productType,
                l_strProductCode,
                l_institution);
            
        //1.3 顧客オブジェクトを取得する。
        //MainAccountImpl(long) 
        //[引数] 
        //証券会社ID： 証券会社.getInstitutionId()の戻り値 
        //部店コード： 引数.特定口座強制振替キューParams.部店コード 
        //顧客コード： 引数.特定口座強制振替キューParams.顧客コード 
        
        long l_lngInstitutionId = l_institution.getInstitutionId();        
        String l_strAccountCode = l_params.getAccountCode();
        
        MainAccount l_mainAccount;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(
                    l_lngInstitutionId, 
                    l_strBranchCode, 
                    l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4 入出金注文内容インスタンスを生成する。     
        
        //注文種別： （以下のとおり） 
        //１）引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合 
        //・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、OrderTypeEnum.”証券振替注文（一般口座から特定口座）” 
        //・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、OrderTypeEnum.”証券振替注文（特定口座から一般口座）” 
        //２）引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合 
        //・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、OrderTypeEnum.”証券振替注文（特定口座から一般口座）” 
        //・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、OrderTypeEnum.”証券振替注文（一般口座から特定口座）” 
        OrderTypeEnum l_orderType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
        {
            log.debug("引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合");
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”出”の場合");
                l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
            }
            else 
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”入”の場合");
                l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
            }
        }
        else
        {
            log.debug("引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合");
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”出”の場合");
                l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
            }
            else 
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”入”の場合");
                l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
            }
        }     
        log.debug("注文種別： = " + l_orderType);
        
        //振替タイプ： （以下のとおり） 
        //引数.特定口座強制振替キューParams.出入区分=”出” の場合、”出金” 
        //引数.特定口座強制振替キューParams.出入区分=”入” の場合、”入金” 
        AssetTransferTypeEnum l_transferType = null;
        if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
        {
            l_transferType = AssetTransferTypeEnum.CASH_OUT;
        }
        else
        {
            l_transferType = AssetTransferTypeEnum.CASH_IN;
        }
        log.debug("振替タイプ： = " + l_transferType);
        
        //銘柄ID： （get銘柄()の戻り値）.銘柄ID 
        long l_lngProductId = l_product.getProductId();
        log.debug("銘柄ID： = " + l_lngProductId);
        
        //数量： 
        //振替タイプ=”入金”の場合、引数.特定口座強制振替キューParams.数量 
        //振替タイプ=”出金”の場合、引数.特定口座強制振替キューParams.数量 × -1 
        long l_lngQuantity = 0;
        if (AssetTransferTypeEnum.CASH_IN.equals(l_transferType))
        {
            l_lngQuantity = l_params.getQuantity();                
        }
        else
        {
            l_lngQuantity = l_params.getQuantity() * (-1);
        }      
        log.debug("数量： = " + l_lngQuantity);
        
        //代理入力者： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null,
                l_orderType,
                l_transferType,
                l_lngProductId,
                l_lngQuantity,
                null,
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()),
                null,
                null);
                    
        
        //1.5 特定口座振替強制更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト 
        WEB3AioSpsecTransferForceUpdateInterceptor l_aioSpsecTransferForceUpdateInterceptor = 
            new WEB3AioSpsecTransferForceUpdateInterceptor(l_aioNewOrderSpec);
            
        //1.6 以下のとおりにインタセプタのプロパティをセットする。
        
        //インタセプタ.発注日 = 入出金注文内容.振替予定日
        l_aioSpsecTransferForceUpdateInterceptor.setOrderBizDate(
            l_aioNewOrderSpec.getEstimatedTransferDate());
        
        //インタセプタ.受渡日 = 入出金注文内容.振替予定日
        l_aioSpsecTransferForceUpdateInterceptor.setDeliveryDate(
            l_aioNewOrderSpec.getEstimatedTransferDate());
        
        //インタセプタ.税区分 = （以下のとおり）
        //引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合、”一般” 
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
        {
            log.debug("特定口座強制振替キューParams.特定口座区分 = '一般' の場合");
            l_aioSpsecTransferForceUpdateInterceptor.setTaxType(
                TaxTypeEnum.NORMAL);
        }
        //引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合、”特定” 
        else
        {
            log.debug("特定口座強制振替キューParams.特定口座区分 = '特定' の場合");
            l_aioSpsecTransferForceUpdateInterceptor.setTaxType(
                TaxTypeEnum.SPECIAL);
        }
        
        //インタセプタ.ミニ株区分 = 特定口座強制振替キューParams.ミニ株区分
        l_aioSpsecTransferForceUpdateInterceptor.setMiniStockDiv(
            l_params.getMiniStockDiv());
        
        //インタセプタ.識別コード = 特定口座強制振替キューParams.識別コード
        l_aioSpsecTransferForceUpdateInterceptor.setOrderRequestNumber(
            l_params.getOrderRequestNumber());
         
        //1.7 インタセプタをセットする。
        //[引数] 
        //特定口座振替強制更新インタセプタ： 特定口座振替強制更新インタセプタオブジェクト  
        
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
            
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSpsecTransferForceUpdateInterceptor);
        
        //1.8 補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： ”預り金口座”
        
        WEB3GentradeSubAccount l_subAccount;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.9 新規注文IDを取得する。 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.10 注文登録処理を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ 
        //注文内容： 入出金注文内容オブジェクト 
        //注文ID： createNewOrderId()の戻り値 
        //パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
        //isSkip発注審査： true 

        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strPasswordDecypt = 
            l_crypt.decrypt(l_mainAccount.getTradingPassword());
        
        l_orderManager.submitNewOrder(
            l_subAccount, 
            l_productType, 
            l_aioNewOrderSpec, 
            l_lngNewOrderId, 
            l_strPasswordDecypt, 
            true);
            
        //1.11 注文単位を取得する。 
        //(*配列の1番目の要素を取得する） 
        //[引数] 
        //注文ID： createNewOrderId()の戻り値 
        log.debug("createNewOrderId()の戻り値 = " + l_lngNewOrderId);
        OrderUnit[] l_orderUnits = 
            l_orderManager.getOrderUnits(l_lngNewOrderId);
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
            
        //1.12 is売付可能(特定口座強制振替キューParams)
        //売付可能の判定を行う。 
        //[引数] 
        //特定口座強制振替キュー： 特定口座強制振替キューParamsオブジェクト
        boolean l_blnIsSellPossible = this.isSellPossible(l_params);
        
        //1.13 保有資産テーブルを更新する。 
        //（保有資産テーブルに既存レコードが存在しない場合は、新規登録する。） 
        //[引数] 
        //注文単位： 注文単位オブジェクト
        //is売付可能の戻り値
        this.updateAsset(l_aioOrderUnit, l_blnIsSellPossible);

        //1.14 余力の再計算を行う。 
        //[引数] 
        //補助口座： getSubAccount()の戻り値 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);      
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        List l_lisAioOrderUnit = new ArrayList();
        
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            l_aioOrderUnit = (AioOrderUnit)l_orderUnits[i];                        
            l_lisAioOrderUnit.add(l_aioOrderUnit);
        }
        AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_orderUnits.length];
        l_lisAioOrderUnit.toArray(l_aioOrderUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnits;
    }
    
        
    /**
     * (submit取消)<BR>
     * 特定口座振替の注文の取消を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（特定口座振替強制）submit取消」 参照 
     * @@param l_params - 特定口座強制振替キューテーブルの行オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public void submitCancel(HostSpsecTransNotifyParams l_params) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitCancel(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
    	//1.1 該当する注文単位を取得する。 
        //[引数] 
        //特定口座強制振替キュー： 特定口座強制振替キューParamsオブジェクト 
        AioOrderUnit l_aioOrderUnit = this.getOrderUnit(l_params);
        
        //1.2 取消注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： 注文単位.注文ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_aioOrderUnit.getOrderId());
        
        //1.3 出金取消更新インタセプタのインスタンスを生成する。 
        WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
            new WEB3AioCashoutCancelUpdateInterceptor();
        
        //1.4 インタセプタをセットする。 
        //[引数] 
        //arg0： 出金取消更新インタセプタオブジェクト 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioCashoutCancelUpdateInterceptor);
        
        //1.5 補助口座オブジェクトを取得する。 
        //[引数] 
        //口座ID： 注文単位.口座ID 
        //補助口座ID： 注文単位.補助口座ID 
        WEB3GentradeAccountManager l_accManage = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_aioOrderUnit.getAccountId(),
                l_aioOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座オブジェクトを取得する:",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        //1.6 注文取消処理を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        //取消注文内容： 取消注文内容オブジェクト 
        //パスワード： 補助口座.getMainAccount().getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
        //isSkip発注審査： true 
        WEB3Crypt l_web3Crypt = new WEB3Crypt();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        String l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
        
        l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec, 
                l_strPassword, 
                true);
        try
        {
            l_aioOrderUnit = (AioOrderUnit)
                l_aioOrderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
        
        //1.7 is売付可能(特定口座強制振替キューParams)
        //売付可能の判定を行う。 
        //[引数] 
        //特定口座強制振替キュー： 特定口座強制振替キューParamsオブジェクト
        boolean l_blnIsSellPossible = this.isSellPossible(l_params);

        //1.8 保有資産テーブルを更新する。 
        //[引数] 
        //注文単位： 注文単位オブジェクト 
        //is売付可能()の戻り値 
        this.updateAsset(l_aioOrderUnit, l_blnIsSellPossible);
        
        //1.9 余力の再計算を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);      
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get注文単位)<BR>
     * 該当する注文単位を取得する。 <BR>
     * <BR>
     *１）以下のオブジェクトを取得する。<BR>
     * <BR>
     *１－１）証券会社オブジェクト<BR>
     * 拡張アカウントマネージャ.getInstitutiion()をコールする。<BR>
     *   <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.特定口座強制振替キュー.証券会社コード <BR>
     * <BR>
     *１－２）部店オブジェクト <BR>
     * 拡張アカウントマネージャ.get部店()をコールする。<BR>
     *  <BR>
     * [引数] <BR>
     * 証券会社コード： 引数.特定口座強制振替キュー.証券会社コード <BR>
     * 部店コード： 引数.特定口座強制振替キュー.部店コード <BR>
     * <BR>
     *１－３）顧客オブジェクト <BR>
     * <BR>
     * 拡張アカウントマネージャ.getMainAccount()をコールする。 <BR>
     * <BR>
     *[引数] <BR>
     * 証券会社： １－１）で取得した証券会社オブジェクト <BR>
     * 部店： １－２）で取得した部店オブジェクト <BR>
     * 顧客コード： 引数.特定口座強制振替キュー.顧客コード <BR>
     * <BR>
     *１－４）補助口座オブジェクト <BR>
     * 拡張アカウントマネージャ.getSubAccount()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 口座ID： 顧客.口座ID <BR>
     * 補助口座タイプ： 預り金口座 <BR>
     * <BR>
     *１－５）銘柄オブジェクト <BR>
     * AIOプロダクトマネージャ.get銘柄()をコールする。 <BR>
     * <BR>　@　@
     * [引数] <BR>
     * 銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ  <BR>
     * 銘柄コード： 特定口座強制振替キューParams.銘柄コード  <BR>
     * 証券会社： 証券会社オブジェクト  <BR>
     * <BR>
     * 
     *２）注文単位オブジェクトを取得する。 <BR>
     * <BR>
     *[取得条件] <BR>
     *   口座ID = 口座.口座ID <BR>
     *   補助口座ID = 補助口座.補助口座ID <BR>
     *   部店ID = 部店.部店ID <BR>
     *   銘柄タイプ = 引数.特定口座強制振替キュー.商品区分に対応する銘柄タイプ <BR>
     *   税区分 = （以下のとおり） <BR>
     *      引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合、”一般” <BR>
     *      引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合、”特定” <BR>
     * <BR>
     *   ミニ株区分 = 引数.特定口座強制振替キュー.ミニ株区分 <BR>
     *   注文種別 = （以下のとおり） <BR>
     *      １）引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合 <BR>
     *         ・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、 <BR>
     *			OrderTypeEnum.”証券振替注文（一般口座から特定口座）”<BR>
     *         ・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、 <BR>
     *			OrderTypeEnum.”証券振替注文（特定口座から一般口座）”<BR>
     * <BR>
     *      ２）引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合 <BR>
     * 	       ・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、 <BR>
     *			OrderTypeEnum.”証券振替注文（特定口座から一般口座）”<BR>
     * 	       ・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、 <BR>
     *			OrderTypeEnum.”証券振替注文（一般口座から特定口座）”<BR>
     * <BR>
     *   注文有効状態 = OrderOpenStatusEnum.”オープン” <BR>
     *   数量 = （以下のとおり） <BR>
     *		引数.特定口座強制振替キュー.出入区分 = ”出”の場合、 <BR>
     *		     引数.特定口座強制振替キュー.数量 <BR>
     *		引数.特定口座強制振替キュー.出入区分 = ”入”の場合、 <BR>
     *		     引数.特定口座強制振替キュー.数量 × -1 <BR>
     * <BR>
     * 銘柄ID： （get銘柄()の戻り値）.銘柄ID <BR>
     * <BR>
     *３）取得した注文単位を返却する。 <BR>
     * <BR>
     *   ※複数行一致した場合は、受注日時が一番古いものを返却する。 <BR>
     * <BR>
     * @@param l_params - 特定口座強制振替キューテーブルの行オブジェクト
     * @@return AioOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 416C9A4B0190
     */
    protected AioOrderUnit getOrderUnit(HostSpsecTransNotifyParams l_params) 
    	throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOrderUnit(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);    
        //拡張アカウントマネージャ取得する 
        WEB3GentradeAccountManager l_gentradeAccountManager =  
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        try
        {
            //１）以下のオブジェクトを取得する。 
            //１－１）証券会社オブジェクト 
            //拡張アカウントマネージャ.getInstitutiion()をコールする。 
            //[引数] 
            //証券会社コード： 引数.特定口座強制振替キュー.証券会社コード 
            Institution l_institution = l_gentradeAccountManager.getInstitution(
                    l_params.getInstitutionCode());
            
            //１－２）部店オブジェクト 
            //拡張アカウントマネージャ.get部店()をコールする。 
            //[引数] 
            //証券会社コード： 引数.特定口座強制振替キュー.証券会社コード 
            //部店コード： 引数.特定口座強制振替キュー.部店コード
            Branch l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                    l_params.getInstitutionCode(), 
                    l_params.getBranchCode());
            
            //１－３）顧客オブジェクト 
            //拡張アカウントマネージャ.getMainAccount()をコールする。 
            //[引数] 
            //証券会社： １－１）で取得した証券会社オブジェクト 
            //部店： １－２）で取得した部店オブジェクト 
            //顧客コード： 引数.特定口座強制振替キュー.顧客コード 
            MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_institution, 
                    l_branch, 
                    l_params.getAccountCode());
            
            //１－４）補助口座オブジェクト 
            //拡張アカウントマネージャ.getSubAccount()をコールする。 
            //[引数] 
            //口座ID： 顧客.口座ID 
            //補助口座タイプ： 預り金口座
            SubAccountTypeEnum l_subAccountType = null;
            WEB3GentradeMainAccount l_gentradeMainAcc = 
                (WEB3GentradeMainAccount)l_mainAccount;

            SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                    l_mainAccount.getAccountId(), 
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    
            //１－５）銘柄オブジェクト
            //AIOプロダクトマネージャ.get銘柄()をコールする。
            //[引数]
            //銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ 
            //銘柄コード： 特定口座強制振替キューParams.銘柄コード 
            //証券会社： 証券会社オブジェクト 
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
            WEB3AioProductManager l_productManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
            
            //銘柄タイプ： 特定口座強制振替キューParams.商品区分に対応する銘柄タイプ 
            String l_strCommodityDiv = l_params.getCommodityDiv();
        
            ProductTypeEnum l_productType = null;
            if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.EQUITY;        
            }
            else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.BOND;
            }
            else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.MUTUAL_FUND;        
            }
            else if (WEB3AioHostSpsecCommodityDef.FOREIGN_EQUITY.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.FOREIGN_EQUITY;        
            }
        
            log.debug("銘柄タイプ： = " + l_productType);
            String l_strProductCode = l_params.getProductCode();
        
            Product l_product = l_productManager.getProduct(
                    l_productType,
                    l_strProductCode,
                    l_institution);

                    
            //２）注文単位オブジェクトを取得する。
            List l_lisAioOrderUnitRows = null;
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and product_type = ? and tax_type = ? " +
                "and mini_stock_div = ? and order_type = ? " +
                "and order_open_status = ? and quantity = ? and product_id = ? ";
            
            //口座ID = 口座.口座ID
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            //補助口座ID = 補助口座.補助口座ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //部店ID = 部店.部店ID
            long l_lngBranchId = l_branch.getBranchId();           
            
            //銘柄タイプ = 引数.特定口座強制振替キュー.銘柄タイプ            
            
            //税区分 = （以下のとおり） 
            //引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合、”一般” 
            //引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合、”特定” 
            
            TaxTypeEnum l_taxType = null;
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            log.debug("税区分 = " + l_taxType);
            
            //ミニ株区分 = 引数.特定口座強制振替キュー.ミニ株区分 
            String l_strMiniStockDiv = l_params.getMiniStockDiv();
            log.debug("ミニ株区分 = " + l_strMiniStockDiv);
            
            //注文種別 = （以下のとおり） 
            //１）引数.特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合 
            //・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、
            //      OrderTypeEnum.”証券振替注文（一般口座から特定口座）” 
            //・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、
            //      OrderTypeEnum.”証券振替注文（特定口座から一般口座）” 
            //２）引数.特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合 
            //・引数.特定口座強制振替キュー.出入区分 = ”出”の場合、
            //      OrderTypeEnum.”証券振替注文（特定口座から一般口座）” 
            //・引数.特定口座強制振替キュー.出入区分 = ”入”の場合、
            //      OrderTypeEnum.”証券振替注文（一般口座から特定口座）” 

            OrderTypeEnum l_orderType = null;
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                log.debug("特定口座強制振替キュー.特定口座区分 = ”一般口座”の場合");
                if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
                {
                    l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
                }
                else 
                {
                    l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
                }
            }
            else
            {
                log.debug("特定口座強制振替キュー.特定口座区分 = ”特定口座”の場合");
                if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
                {
                    l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
                }
                else 
                {
                    l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
                }
            }
            log.debug("注文種別 = " + l_orderType);
            
            //注文有効状態 = OrderOpenStatusEnum.”オープン” 
            OrderOpenStatusEnum l_orderOpenStatus = OrderOpenStatusEnum.OPEN;
            log.debug("注文有効状態 = " + l_orderOpenStatus);
            
            //数量 = （以下のとおり） 
            //引数.特定口座強制振替キュー.出入区分 = ”出”の場合、引数.特定口座強制振替キュー.数量 
            //引数.特定口座強制振替キュー.出入区分 = ”入”の場合、引数.特定口座強制振替キュー.数量 × -1 
            long l_lngQuantity = 0;
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”出”の場合");
                l_lngQuantity = l_params.getQuantity();
            }
            else
            {
                log.debug("引数.特定口座強制振替キュー.出入区分 = ”入”の場合");
                l_lngQuantity = l_params.getQuantity() * (-1);
            }
            log.debug("数量 = " + l_lngQuantity);
            
            //銘柄ID： （get銘柄()の戻り値）.銘柄ID 
            long l_lngProductId = l_product.getProductId();
            log.debug("銘柄ID： = " + l_lngProductId);
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                l_productType,  
                l_taxType, 
                l_strMiniStockDiv,
                l_orderType, 
                l_orderOpenStatus, 
                new Long(l_lngQuantity),
                new Long(l_lngProductId)};
                
            try
            {
                l_lisAioOrderUnitRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);

            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBサーバとの通信に失敗した", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //３）取得した注文単位を返却する。 
            //※複数行一致した場合は、受注日時が一番古いものを返却する。
            AioOrderUnit l_aioOrderUnit = null;
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();                
            
            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.size() == 0)
            {
                log.debug("注文単位テーブルから、レコードを取得しない !");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            log.debug("l_lisAioOrderUnitRows.size() = " + l_lisAioOrderUnitRows.size());
            
            if (l_lisAioOrderUnitRows.size() > 1)
            {
                int l_intOldDateFlag = 0;
                AioOrderUnitRow l_oldAioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
                log.debug("注文単位Row = " + l_oldAioOrderUnitRow);
                
                Date l_datOldDate = l_oldAioOrderUnitRow.getReceivedDateTime();
                AioOrderUnitRow l_nextAioOrderUnitRow = null;
                                
                for (int i = 1; i < l_lisAioOrderUnitRows.size(); i++)
                {
                    l_nextAioOrderUnitRow = 
                        (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);
                    log.debug("注文単位Row = " + l_nextAioOrderUnitRow);
                    
                    Date l_datNextDat = l_nextAioOrderUnitRow.getReceivedDateTime();
                    
                    if (WEB3DateUtility.compareToDay(l_datNextDat, l_datOldDate) < 0)
                    {
                        l_datOldDate = l_datNextDat;
                        l_intOldDateFlag = i;
                    }
                }
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(l_intOldDateFlag);
                log.debug("取得した注文単位Row = " + l_aioOrderUnitRow);
                
                l_aioOrderUnit = (AioOrderUnit) l_orderManager.toOrderUnit(
                        l_aioOrderUnitRow);
            }
            else if (l_lisAioOrderUnitRows.size() == 1)
            {
                //注文単位オブジェクトを取得する。
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);         
                log.debug("注文単位Row = " + l_aioOrderUnitRow);
                
                l_aioOrderUnit = (AioOrderUnit) l_orderManager.toOrderUnit(
                        l_aioOrderUnitRow);
    
                log.exiting(STR_METHOD_NAME);
            }          
            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnit;
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
    }
    
    /**    
     *(update保有資産)<BR>
     * 保有資産の数量と簿価を更新する。 <BR>
     * ※既存レコードが存在しない場合は、新規登録する。 <BR>
     * <BR>
     * １）顧客オブジェクトを取得する。 <BR>
     * <BR>
     * 拡張アカウントマネージャ.get顧客()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 口座ID： 注文単位.口座ID <BR>
     * ２）補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * 顧客.getSubAccount()をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 補助口座タイプ： （以下のとおり） <BR>
     * 顧客.is信用口座開設()==trueの場合、”保証金口座” <BR>
     * 顧客.is信用口座開設()==falseの場合、”預り金口座” <BR>
     * <BR>
     * ３）対象となる保有資産レコードを取得する。 <BR>
     * <BR>
     * [取得条件] <BR>
     * 口座ID = 引数.注文単位.口座ID <BR>
     * 補助口座ID = 補助口座.補助口座ID <BR>
     * 銘柄ID = 引数.注文単位.銘柄ID <BR>
     * 税区分 = 引数.注文単位.税区分 <BR>
     * ミニ株区分 = 引数.注文単位.ミニ株区分 <BR>
     * <BR>
     * ４）保有資産レコードを更新（登録）する。 <BR>
     * <BR>
     * ４－１）保有資産レコードが取得できなかった場合 <BR>
     * <BR>
     * 保有資産レコードを登録する。 <BR>
     * 項目の設定方法@は、DB更新仕様参照。 <BR>
     * <BR>
     * ４－２）保有資産レコードが取得できた場合 <BR>
     * <BR>
     * 保有資産レコードを更新する。 <BR>
     * 項目の設定方法@は、DB更新仕様参照。 <BR>
     * ※注文登録時と注文取消時で異なる。 <BR>
     * 登録か取消かの判断は、注文単位.注文取消区分にて行う。 <BR>
     * <BR>
     * @@param l_aioOrderUnit - 注文単位オブジェクト
     * @@param l_blnSellPossible - is売付可能
     * @@throws WEB3BaseException
     * @@roseuid 416C9A4B0190
     */
    protected void updateAsset(AioOrderUnit l_aioOrderUnit, boolean l_blnSellPossible) 
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "updateAsset(AioOrderUnit l_aioOrderUnit, boolean l_blnSellPossible)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnit == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）顧客オブジェクトを取得する。
		//拡張アカウントマネージャ.get顧客()をコールする。
		//[引数] 
		//口座ID： 注文単位.口座ID 
		FinApp l_finApp = 
			(FinApp)Services.getService(FinApp.class);
		AccountManager l_accountManager = GtlUtils.getAccountManager();  
           
		MainAccount l_mainAccount;
		try
		{
			l_mainAccount = l_accountManager.getMainAccount(l_aioOrderUnit.getAccountId());
		}
		catch (NotFoundException l_ex)
		{
			log.error("___NotFoundException___" , l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}

		//２）補助口座オブジェクトを取得する。
		//顧客.getSubAccount()をコールする。
		//[引数] 
		//補助口座タイプ： 
		//顧客.is信用口座開設()==trueの場合、”保証金口座” 
		//顧客.is信用口座開設()==falseの場合、”預り金口座” 
        
		SubAccountTypeEnum l_subAccountType = null;        
		WEB3GentradeMainAccount l_gentradeMainAcc = 
			(WEB3GentradeMainAccount)l_mainAccount;
        
		boolean l_blnMarginAccount = 
			l_gentradeMainAcc.isMarginAccountEstablished(
				WEB3GentradeRepaymentDivDef.DEFAULT);
        
		if (l_blnMarginAccount)
		{
			log.debug("顧客.is信用口座開設()==trueの場合");
			l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
		}
		else
		{
			log.debug("顧客.is信用口座開設()==falseの場合");
			l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
		}                             

		WEB3GentradeSubAccount l_subAccount;
		try
		{
			l_subAccount = 
				(WEB3GentradeSubAccount)l_mainAccount.getSubAccount(l_subAccountType);
		}
		catch (NotFoundException l_ex)
		{
			log.error("___NotFoundException___" , l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}

        //３）対象となる保有資産レコードを取得する。
        //[取得条件] 
        //口座ID = 引数.注文単位.口座ID 
        //補助口座ID = 補助口座.補助口座ID 
        //銘柄ID = 引数.注文単位.銘柄ID 
        //税区分 = 引数.注文単位.税区分 
        //ミニ株区分 = 引数.注文単位.ミニ株区分 
        AioOrderUnitParams l_orderUnitParams = 
            (AioOrderUnitParams)l_aioOrderUnit.getDataSourceObject();
        List l_lisAssetRows = null;
        log.debug("注文単位.口座ID = " + l_orderUnitParams.getAccountId());
		log.debug("補助口座.補助口座ID = " + l_subAccount.getSubAccountId());
        log.debug("注文単位.銘柄ID = " + l_orderUnitParams.getProductId());
        log.debug("注文単位.税区分 = " + l_orderUnitParams.getTaxType());
        log.debug("注文単位.ミニ株区分 = " + l_orderUnitParams.getMiniStockDiv());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssetRows = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                "account_id = ? and sub_account_id = ? and " +
                "product_id = ? and tax_type = ? and " +
                "mini_stock_div = ?",
                null,
                new Object[] {
                    new Long(l_aioOrderUnit.getAccountId()), 
                    new Long(l_subAccount.getSubAccountId()), 
                    new Long(l_orderUnitParams.getProductId()), 
                    l_orderUnitParams.getTaxType(), 
                    l_orderUnitParams.getMiniStockDiv()
                    });
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("保有資産レコードを取得Size = " + l_lisAssetRows.size());
        
        //４）保有資産レコードを更新（登録）する。 
        //４－１）保有資産レコードが取得できなかった場合 
        //保有資産レコードを登録する。 
        //項目の設定方法@は、DB更新仕様参照。
        if (l_lisAssetRows.size() == 0)
        {
            log.debug("保有資産レコードが取得できなかった場合");
            AssetParams l_assetParams = new AssetParams();            
            //保有資産Params.口座ＩＤ = 注文単位.口座ID
            l_assetParams.setAccountId(l_orderUnitParams.getAccountId());
			//保有資産Params.補助口座ID = 補助口座.補助口座ID
			l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
            //保有資産Params.銘柄タイプ = 注文単位.銘柄タイプ
            l_assetParams.setProductType(l_orderUnitParams.getProductType());
            //is売付可能 == true の場合、
            if (l_blnSellPossible)
            {
                //保有資産Params.数量 = 注文単位.数量
                l_assetParams.setQuantity(l_orderUnitParams.getQuantity());
                //保有資産Params.売付不能数量 = 0
                l_assetParams.setQuantityCannotSell(0);
            }
            //is売付可能 == false の場合
            else
            {
                //保有資産Params.数量 = 0
                l_assetParams.setQuantity(0);
                //保有資産Params.売付不能数量 = 注文単位.数量
                l_assetParams.setQuantityCannotSell(l_orderUnitParams.getQuantity());
            }
            //保有資産Params.数量（簿価単価計算用） = 注文単位.数量
            l_assetParams.setQuantityForBookValue(l_orderUnitParams.getQuantity());
            //保有資産Params.簿価（簿価単価計算用） = 0
            l_assetParams.setBookValue(0);
            //保有資産Params.入力簿価単価 = null
            l_assetParams.setInputBookValue(null);
            //保有資産Params.簿価単価入力日時 = null
            l_assetParams.setInputTimestamp(null);
            //保有資産Params.名義書換料 = 0
            l_assetParams.setSetupFee(0);
            //保有資産Params.名義書換料消費税 = 0
            l_assetParams.setSetupFeeTax(0);
            //保有資産Params.口座管理費 = 0
            l_assetParams.setManagementFee(0);
            //保有資産Params.口座管理費消費税 = 0
            l_assetParams.setManagementFeeTax(0);
            //保有資産Params.銘柄ＩＤ = 注文単位.銘柄ＩＤ
            l_assetParams.setProductId(l_orderUnitParams.getProductId());
            //保有資産Params.税区分 = 注文単位.税区分
            l_assetParams.setTaxType(l_orderUnitParams.getTaxType());
            //保有資産Params.ミニ株区分 = 注文単位.ミニ株区分
            l_assetParams.setMiniStockDiv(l_orderUnitParams.getMiniStockDiv());
            //保有資産Params.分配金 = null
            l_assetParams.setProfitDistribution(null);
            //保有資産Params.30日未経過残高口数 = null
            l_assetParams.setCountBeforePenalty(null);   
            //保有資産Params.作成日付 = 現在日時
            l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //保有資産Params.更新日付 = 現在日時
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                long l_lngAssetId = AssetDao.newPkValue();
                l_assetParams.setAssetId(l_lngAssetId);
                WEB3DataAccessUtility.insertRow(l_assetParams);
            }
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //４－２）保有資産レコードが取得できた場合 
        //保有資産レコードを更新する。 
        //項目の設定方法@は、DB更新仕様参照。 
        //※注文登録時と注文取消時で異なる。 
        //登録か取消かの判断は、注文単位.注文取消区分にて行う。

        AioOrderUnitParams l_aioOrderUnitParams = 
            (AioOrderUnitParams) l_aioOrderUnit.getDataSourceObject();
        AssetRow l_assetRow = null;
        AssetParams l_assetParams = null;
        
        if (l_lisAssetRows.size() > 0)
        {
            log.debug("保有資産レコードが取得できた場合");
            log.debug("注文単位.注文取消区分 = " + l_aioOrderUnitParams.getCancelType());
            
            for (int i = 0; i < l_lisAssetRows.size(); i++)
            {
                l_assetRow = (AssetRow)l_lisAssetRows.get(i);
                l_assetParams = new AssetParams(l_assetRow);

                //注文登録時
                if (WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(
                        l_aioOrderUnitParams.getCancelType()))
                {
                    log.debug("注文登録時");
                    //==========================================================
                    //レコード更新時の数量計算方法@
                    //１）前提
                    //更新前の保有資産.数量(1)
                    double l_dblBefUpdQuantity = l_assetParams.getQuantity();
                    
                    //更新前の保有資産.売付不能数量(2)
                    double l_dblBefUpdCannotSell = l_assetParams.getQuantityCannotSell();
                    
                    //更新後の保有資産.数量(3)
                    double l_dblAftUpdQuantity = 0;
                    
                    //更新後の保有資産.売付不能数量(4)
                    double l_dblAftUpdCannotSell = 0;
                    
                    //２）計算方法@
                    //２－１）入庫の場合
                    if (AssetTransferTypeEnum.CASH_IN.equals(
                            l_aioOrderUnitParams.getTransferType()))
                    {
                        log.debug("入庫の場合");

                        //----数量のセート
                        //is売付可能 == true の場合
                        //(3)＝ (1) ＋ 注文単位.数量
                        //is売付可能 == false の場合
                        //(3)＝ (1)
                        l_dblAftUpdQuantity = l_blnSellPossible ?
                            (l_dblBefUpdQuantity + l_aioOrderUnitParams.getQuantity())
                            :l_dblBefUpdQuantity;

                        //----売付不能数量のセート
                        //is売付可能 == true の場合
                        //(4)＝ (2)
                        //is売付可能 == false の場合
                        //(4)＝ (2) + 注文単位.数量
                        l_dblAftUpdCannotSell = l_blnSellPossible ? 
                            l_dblBefUpdCannotSell
                            :(l_dblBefUpdCannotSell + l_aioOrderUnitParams.getQuantity());
                    }
                    //２－２）出庫の場合
                    else
                    {
                        log.debug("出庫の場合");
                        
                        //is売付可能 == true の場合
                        if (l_blnSellPossible)
                        {
                            //(3) ＝ (1)＋ 注文単位.数量 とする。
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity + l_aioOrderUnitParams.getQuantity();
                            //(4) ＝ (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                            //(3) ＜ 0 の場合
                            if (l_dblAftUpdQuantity < 0)
                            {
                                //(A) ＝ (3) とする
                                double l_dblQuantityA = l_dblAftUpdQuantity;
                                //(3) ＝ 0
                                //(4) ＝ (2) ＋ (A)
                                l_dblAftUpdQuantity = 0;
                                l_dblAftUpdCannotSell = l_dblBefUpdCannotSell + l_dblQuantityA;
                            }
                        }
                        //is売付可能 == false の場合
                        else
                        {
                            //(3) ＝ (1)
                            l_dblAftUpdQuantity = l_dblBefUpdQuantity;
                            //(4) ＝ (2) ＋ 注文単位.数量 とする。
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell + l_aioOrderUnitParams.getQuantity();
                        }
                    }                
                    //==========================================================
                    
                    //更新前の簿価(簿価単価計算)
                    double l_dblBefUpdBookValue = 
                        l_assetParams.getBookValue();
    
                    //更新前の数量(簿価単価計算用)
                    double l_dblBefUpdQuantiyBookValue = 
                        l_assetParams.getQuantityForBookValue();
                    
                    //保有資産Params.数量 = シート「数量計算」参照
                    l_assetParams.setQuantity(l_dblAftUpdQuantity);
                    log.debug("保有資産Params.数量 = " + l_assetParams.getQuantity());
                    
                    //保有資産Params.売付不能数量 = シート「数量計算」参照
                    l_assetParams.setQuantityCannotSell(l_dblAftUpdCannotSell);
                    log.debug("保有資産Params.売付不能数量 = " + 
                            l_assetParams.getQuantityCannotSell());
                    
                    //保有資産Params.数量（簿価単価計算用） = 数量＋売付不能数量
                    l_assetParams.setQuantityForBookValue(
                        l_assetParams.getQuantity() + 
                        l_assetParams.getQuantityCannotSell());
                    log.debug("保有資産Params.数量（簿価単価計算用） = " + 
                            l_assetParams.getQuantityForBookValue());
                    
                    //保有資産Params.簿価（簿価単価計算用） = 
                    //		(1)更新前簿価単価の計算
                    //         更新前の簿価(簿価単価計算用) ÷ 更新前の数量(簿価単価計算用)
                    //		(2)簿価計算
                    //         更新後の数量(簿価単価計算用) × (1)の簿価単価
                    double l_dblBookValue = 0.0;
                    if (l_dblBefUpdQuantiyBookValue != 0.0)
                    {
                        l_dblBookValue =  
                            l_dblBefUpdBookValue / l_dblBefUpdQuantiyBookValue;                        
                    }
                    double l_dblAftUpdBookValue = 
                        l_assetParams.getQuantityForBookValue() * l_dblBookValue;
                    l_assetParams.setBookValue(l_dblAftUpdBookValue);
                    
                    log.debug("保有資産Params.簿価（簿価単価計算用） = " + 
                            l_assetParams.getBookValue());
                    
                    //保有資産Params.更新日付 = 現在日時
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                }
                //注文取消時
                else if (WEB3ModifyCancelTypeDef.CANCELED.equals(
                        l_aioOrderUnitParams.getCancelType()))
                {
                    log.debug("注文取消時");
                    //==========================================================
                    //レコード更新時の数量計算方法@
                    //１）前提
                    //更新前の保有資産.数量(1)
                    double l_dblBefUpdQuantity = l_assetParams.getQuantity();
                    
                    //更新前の保有資産.売付不能数量(2)
                    double l_dblBefUpdCannotSell = l_assetParams.getQuantityCannotSell();
                    
                    //更新後の保有資産.数量(3)
                    double l_dblAftUpdQuantity = 0;
                    
                    //更新後の保有資産.売付不能数量(4)
                    double l_dblAftUpdCannotSell = 0;
                    
                    //２）計算方法@
                    //２－１）入庫取消の場合
                    if (AssetTransferTypeEnum.CASH_IN.equals(
                            l_aioOrderUnitParams.getTransferType()))
                    {
                        log.debug("入庫取消の場合");
                        if (l_blnSellPossible)
                        {
                            //(3)＝ (1) － 注文単位.数量
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity - l_aioOrderUnitParams.getQuantity();
                            //(4) ＝ (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                        }
                        else
                        {
                            //(3)＝ (1)
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity;
                            //(4) ＝ (2) － 注文単位.数量
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell - l_aioOrderUnitParams.getQuantity();
                        }
                    }
                    //２－２）出庫取消の場合
                    else
                    {                
                        log.debug("出庫取消の場合");
                        if (l_blnSellPossible)
                        {
                            //(3)＝ (1) － 注文単位.数量
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity - l_aioOrderUnitParams.getQuantity();
                            //(4) ＝ (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                        }
                        else
                        {
                            //(3)＝ (1)
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity;
                            //(4) ＝ (2) － 注文単位.数量
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell - l_aioOrderUnitParams.getQuantity();
                        }
                    }                
                    //==========================================================
                    
                    //更新前の簿価(簿価単価計算用)
                    double l_dblBefUpdBookValue = 
                        l_assetParams.getBookValue();
    
                    //更新前の数量(簿価単価計算用)
                    double l_dblBefUpdQuantiyBookValue = 
                        l_assetParams.getQuantityForBookValue();
    
                    //保有資産Params.数量 = シート「数量計算」参照
                    l_assetParams.setQuantity(l_dblAftUpdQuantity);
                    log.debug("保有資産Params.数量 = " + l_assetParams.getQuantity());
                    
                    //保有資産Params.売付不能数量 = シート「数量計算」参照
                    l_assetParams.setQuantityCannotSell(l_dblAftUpdCannotSell);
                    log.debug("保有資産Params.売付不能数量 = " + 
                            l_assetParams.getQuantityCannotSell());
                    
                    //保有資産Params.数量（簿価単価計算用） = 数量＋売付不能数量
                    l_assetParams.setQuantityForBookValue(
                        l_assetParams.getQuantity() + 
                        l_assetParams.getQuantityCannotSell());
                    log.debug("保有資産Params.数量（簿価単価計算用） = " + 
                            l_assetParams.getQuantityForBookValue());
                    
                    //保有資産Params.簿価（簿価単価計算用） = 
                    //		(1)更新前簿価単価の計算
                    //         更新前の簿価(簿価単価計算用) ÷ 更新前の数量(簿価単価計算用)
                    //		(2)簿価計算
                    //         更新後の数量(簿価単価計算用) × (1)の簿価単価
                    double l_dblBookValue = 0.0;
                    if (l_dblBefUpdQuantiyBookValue != 0.0)
                    {
                        l_dblBookValue =  
                            l_dblBefUpdBookValue / l_dblBefUpdQuantiyBookValue;                        
                    }
                    double l_dblAftUpdBookValue = 
                        l_assetParams.getQuantityForBookValue() * l_dblBookValue;
                    l_assetParams.setBookValue(l_dblAftUpdBookValue);
                    
                    log.debug("保有資産Params.簿価（簿価単価計算用） = " + 
                            l_assetParams.getBookValue());
                    
                    //保有資産Params.更新日付 = 現在日時
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                }
    
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_assetParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DataQueryException Error ", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DataNetworkException Error In", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (is売付可能)<BR>
     * １）特定口座強制振替キューParams.商品区分 == ”株” の場合 <BR>
     * <BR>
     * １－１） <BR>
     *    特定口座強制振替キューParams.ミニ株区分 == ”ミニ株” <BR>
     *    の場合、true を返却する。 <BR>
     * <BR>
     * １－２） <BR>
     *    特定口座強制振替キューParams.保管区分 == ”機@構” and   <BR>
     *    （特定口座強制振替キューParams.名義区分 == ”単位本人” or ”単位他人”） <BR>
     *    の場合、true を返却する。 <BR>
     * <BR>
     * １－３） <BR>
     *    それ以外の場合、false を返却する。 <BR>
     * <BR>
     * ２）特定口座強制振替キューParams.商品区分 == ”債券” の場合 <BR>
     * <BR>
     * ２－１）銘柄の取得 <BR>
     * <BR>
     * ２－１－１）証券会社オブジェクトの取得 <BR>
     * <BR>
     *    拡張アカウントマネージャ.getInstitution()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    証券会社コード： 特定口座強制振替キューParams.証券会社コード <BR>
     * <BR>
     * ２－１－２）銘柄オブジェクトの取得 <BR>
     * <BR>
     *    AIOプロダクトマネージャ.get銘柄()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    銘柄タイプ： 銘柄タイプ.”債券” <BR>
     *    銘柄コード： 特定口座強制振替キューParams.銘柄コード <BR>
     *    証券会社： 拡張アカウントマネージャ.getInstitution()の戻り値 <BR>
     * <BR>
     * ２－２）CB（銘柄.種別コード == ”転換社債・ﾜﾗﾝﾄ債”）の場合 <BR>
     * <BR>
     *    特定口座強制振替キューParams.保管区分 == ”（集）” or ”機@構” <BR>
     *    の場合、true を返却する。 <BR>
     * <BR>
     * ２－３）それ以外の債券（銘柄.種別コード != ”転換社債・ﾜﾗﾝﾄ債”）の場合 <BR>
     * <BR>
     *    特定口座強制振替キューParams.保管区分 == ”（集）” <BR>
     *    の場合、true を返却する。 <BR>
     * <BR>
     * ３）特定口座強制振替キューParams.商品区分 == ”投信”の場合 <BR>
     * <BR>
     *    特定口座強制振替キューParams.保管区分 == ”（集）” <BR>
     *    の場合、true を返却する。 <BR>
     * <BR>
     * @@param l_params - 特定口座強制振替キュー
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public boolean isSellPossible(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException
    {   
        String STR_METHOD_NAME = 
            "isSellPossible(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
        //１）特定口座強制振替キューParams.商品区分 == ”株” の場合
        if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_params.getCommodityDiv()))
        {            
            //１－１） 
            //特定口座強制振替キューParams.ミニ株区分 == ”ミニ株” 
            //の場合、true を返却する。
            if (WEB3MiniStockDivDef.MINI_STOCK.equals(l_params.getMiniStockDiv()))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }            
            //１－２） 
            //特定口座強制振替キューParams.保管区分 == ”機@構” and   
            //（特定口座強制振替キューParams.名義区分 == ”単位本人” or ”単位他人”） 
            //の場合、true を返却する。
            else if (WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_params.getCustodyDiv()) &&
                    (WEB3NameMethodDivDef.COMPANY_SELF.equals(l_params.getTitleDiv()) ||
                    WEB3NameMethodDivDef.COMPANY_OTHER.equals(l_params.getTitleDiv())))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }            
            //１－３） 
            //それ以外の場合、false を返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);       
                return false;
            }        
        }
        //２）特定口座強制振替キューParams.商品区分 == ”債券” の場合 
        else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_params.getCommodityDiv()))
        {

            //２－１）銘柄の取得     
            //２－１－１）証券会社オブジェクトの取得 
            //   拡張アカウントマネージャ.getInstitution()をコールする。 
            //   [引数] 
            //   証券会社コード： 特定口座強制振替キューParams.証券会社コード 
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);    
            //拡張アカウントマネージャ取得する 
            WEB3GentradeAccountManager l_accountManager =  
                (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(
                        l_params.getInstitutionCode());                
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }     
            //２－１－２）銘柄オブジェクトの取得 
            //AIOプロダクトマネージャ.get銘柄()をコールする。 
            //[引数] 
            //銘柄タイプ： 銘柄タイプ.”債券” 
            //銘柄コード： 特定口座強制振替キューParams.銘柄コード 
            //証券会社： 拡張アカウントマネージャ.getInstitution()の戻り値 
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioProductManager l_aioProductManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
            
            WEB3BondProduct l_product = (WEB3BondProduct)
                l_aioProductManager.getProduct(
                    ProductTypeEnum.BOND,
                    l_params.getProductCode(), 
                    l_institution);
            
            BondProductRow l_bondProductRow = 
                (BondProductRow)l_product.getDataSourceObject();
            
            //２－２）CB（銘柄.種別コード == ”転換社債・ﾜﾗﾝﾄ債”）の場合 
            if (WEB3BondCategCodeDef.CONVERSION_COMPANY_BOND_WARRANT_BOND.equals(
                    l_bondProductRow.getBondCategCode()))
            {
                //特定口座強制振替キューParams.保管区分 == ”（集）” or ”機@構” 
                //の場合、true を返却する。            
                if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()) || 
                    WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_params.getCustodyDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            //２－３）それ以外の債券（銘柄.種別コード != ”転換社債・ﾜﾗﾝﾄ債”）の場合 
            else
            {
                //特定口座強制振替キューParams.保管区分 == ”（集）” 
                //の場合、true を返却する。
                if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()))
                {
                    log.exiting(STR_METHOD_NAME);       
                    return true;
                }
            }
        }        
        //３）特定口座強制振替キューParams.商品区分 == ”投信”の場合 
        else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_params.getCommodityDiv()))
        {
            //特定口座強制振替キューParams.保管区分 == ”（集）” 
            //の場合、true を返却する。 
            if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }
        }
        return false;
    }
}
@
