head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携UnitServiceImpl (WEB3AioOnPaymentCooperationUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 李俊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOnPaymentCooperationCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductTypeOrderManagerReusableValidations;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金連携UnitServiceImpl) <BR>
 * 出金連携UnitService実装クラス <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor( <BR>
 *      TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * <BR>
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationUnitServiceImpl implements WEB3AioOnPaymentCooperationUnitService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationUnitServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioOnPaymentCooperationUnitServiceImpl()
    {
    }

    /**
     * 出金連携処理を行い、出金をグロスしDB更新を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金連携）出金連携」参照 <BR>
     * <BR>
     * @@param l_aioOrderUnits - 注文単位オブジェクト[ ]
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B2080071
     */
    public void execute(AioOrderUnit[] l_aioOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(AioOrderUnit[] l_aioOrderUnits)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnits == null || l_aioOrderUnits.length == 0)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get注文数量(AioOrderUnit[ ])
        //注文単位のグロス数量を計算する。
        Double l_dblOrderQuantity = getOrderQuantity(l_aioOrderUnits); 
        
        // 口座ID
        long l_lngAccountId =  l_aioOrderUnits[0].getAccountId();
        // 補助口座ID 
        long l_lngSubAccountId = l_aioOrderUnits[0].getSubAccountId();
        
        //1.6 補助口座を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);       
        AccountManager l_accountManager = l_finApp.getAccountManager();        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2 出金金額の範囲のチェックを行う。
        //入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();    
        try
        {
			l_reusableValidations.validatePaymentAmount(
                l_subAccount, 
		        l_dblOrderQuantity.doubleValue());
		}
        catch (WEB3BaseException l_ex)
        {
            log.debug("出金金額の範囲のチェック throw Exception", l_ex);
            return;
		} 
        
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //1.3 新規注文IDを取得する。
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.4 顧客オブジェクトを取得する。 
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        // 1.5  入出金注文内容インスタンスを生成する。 
        //[引数]  
        // 代理入力者： null 
        // 注文種別： 1001（出金注文） 
        // 振替タイプ： 2（出金）  
        // 商品ID： 引数.注文単位[0].getProduct( ).getProductId( ) 
        // 金額： get注文数量( )の戻り値  
        // 記述： null  
        // 振替予定日： 引数.注文単位[0].getEstimatedTransferDate( ) 
        // 決済機@関ID： null  
        // 注文ID： createNewOrderId( )の戻り値  

        long l_lngProductId = l_aioOrderUnits[0].getProduct().getProductId();
    
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.CASH_OUT, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                l_dblOrderQuantity.doubleValue(), 
                null, 
                l_aioOrderUnits[0].getEstimatedTransferDate(), 
                null, 
                new Long(l_lngNewOrderId));
        
		//1.7 get新規識別コード(String, String, ProductTypeEnum)
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        //(1)get 注文識別コード採番サービス 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get新規識別コード() = " + l_strNewNumber);
        
        //1.8 入出金注文更新インタセプタ(入出金注文内容)
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow)(l_aioOrderUnits[0].getDataSourceObject());
        
        String l_strBizDate =  l_aioOrderUnitRow.getBizDate(); 
        Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");  
        
        log.debug("注文単位.発注日 = " + 
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); 
        
        //1.9 set発注日(Date) 
        l_orderUpdateInterceptor.setBizDate(l_datBizDate);
        
        //1.10 set受渡日(Date)
        l_orderUpdateInterceptor.setDeliveryDate(l_aioOrderUnits[0].getDeliveryDate());
        
        //1.11 set識別コード(String)
        l_orderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
        
        //1.12 setThreadLocalPersistenceEventInterceptor( 入出金注文更新インタセプタ  )
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_orderUpdateInterceptor);
        
        // 1.13 注文登録処理を行う。 
        // [引数] 
        // 補助口座： 補助口座オブジェクト 
        // 商品タイプ： 5（現金） 
        // 注文内容： 入出金注文内容オブジェクト 
        // 注文ID： createNewOrderId()の戻り値 
        // パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
        // isSkip発注審査： true 
        WEB3Crypt l_web3Crypt = new WEB3Crypt();
        OrderSubmissionResult l_submissionResult =
            l_orderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_web3Crypt.decrypt(l_mainAccount.getTradingPassword()),
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文登録処理失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        //1.14 引数.注文単位[ ]の要素毎にLoop処理を行う
        for (int i = 0; i < l_aioOrderUnits.length; i++)
        {
            //1.14.1 取消注文内容インスタンスを生成する。 
            //[引数] 
            //注文ID： 注文単位[index].getOrderId 
            CancelOrderSpec l_cancelOrderSpec = 
                new CancelOrderSpec(l_aioOrderUnits[i].getOrderId());
           
            //1.14.2 出金連携取消更新インタセプタ( )
            WEB3AioOnPaymentCooperationCancelUpdateInterceptor
                l_aioOnPaymentCooperationCancelUpdateInterceptor = 
                    new WEB3AioOnPaymentCooperationCancelUpdateInterceptor();
            
            //1.14.3 set振替記述(String)
            l_aioOnPaymentCooperationCancelUpdateInterceptor.setDescription(
                WEB3AioDescriptionDef.CASHOUT_GROSS);
            
            //1.14.4 setThreadLocalPersistenceEventInterceptor(出金連携取消更新インタセプタ)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioOnPaymentCooperationCancelUpdateInterceptor);
            
            //1.14.5 submitCancelOrder(補助口座 : SubAccount, 取消注文内容 
            //: CancelOrderSpec, パスワード : String, isSkip発注審査 : boolean)
            //[引数]  
            // 補助口座： 補助口座オブジェクト 
            // 取消注文内容：　@取消注文内容オブジェクト 
            // パスワード：　@ パスワード： 顧客.getTradingPassword( )の戻り値をWEB3Crypt.decrypt( )で復号したもの 
            // isSkip発注審査：　@true
            OrderSubmissionResult l_submitCancelOrderResult =            
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_web3Crypt.decrypt(l_mainAccount.getTradingPassword()),
                true); 
            if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("注文内容取消処理失敗である");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
                    
      }
      //1.15 余力再計算
      //[引数]  
      // 補助口座： 補助口座オブジェクト
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);   
                
        log.exiting(STR_METHOD_NAME);        
  } 

    /**
     * (get注文数量) <BR>
     * 注文単位の数量をグロスする。 <BR>
     * <BR>
     * １）注文単位ごとのLoop処理。  <BR>
     * 　@　@引数.注文単位ごとのLoop処理にて、以下の値を取得する。 <BR>
     * <BR>
     * 　@　@合計数量 = 合計数量 + 注文単位[インデックス].注文数量  <BR>
     * <BR>
     * ２)合計数量を返却する。<BR>
     * 
     * @@param l_aioOrderUnits - 注文単位オブジェクト[ ]
     * @@return Double
     * @@roseuid 41C7B2080090
     */
    protected Double getOrderQuantity(AioOrderUnit[] l_aioOrderUnits)        
    {
        final String STR_METHOD_NAME = 
            "getOrderQuantity(AioOrderUnit[ ] l_aioOrderUnits)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblSumQuantity = 0;
        
        //１）注文単位ごとのLoop処理
        // 引数.注文単位ごとのLoop処理にて、以下の値を取得する
        for (int i = 0; i < l_aioOrderUnits.length; i++)
        {
            l_dblSumQuantity = l_dblSumQuantity + l_aioOrderUnits[i].getQuantity();            
        }
        log.exiting(STR_METHOD_NAME);        
        return new Double(l_dblSumQuantity);
        
    }    
}@
