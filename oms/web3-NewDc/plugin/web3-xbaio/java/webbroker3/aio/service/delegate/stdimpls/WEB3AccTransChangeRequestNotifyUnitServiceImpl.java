head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求通知UnitServiceImpl(WEB3AccTransChangeRequestNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferNotifyUpdateInterceptor;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (振替請求通知UnitServiceImpl)<BR>
 * 振替請求通知UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyUnitServiceImpl
    implements WEB3AccTransChangeRequestNotifyUnitService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyUnitServiceImpl.class);

    /**
     * (submit注文)<BR>
     * SONARからの振替注文の登録を行い、新規注文の注文IDを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求通知）submit注文」 参照<BR>
     * @@param l_hostTransferReceiptParams - (振替入力通知キューParamsオブジェクト)
     * @@param l_orderType - (注文種別)
     * @@param l_changeType - (振替タイプ)
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 413C2D4300FB
     */
    public long submitOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(" +
                "HostTransferReceiptParams l_hostTransferReceiptParams," +
                "OrderTypeEnum l_orderType," +
                "AssetTransferTypeEnum l_changeType)";
                
        log.entering(STR_METHOD_NAME);

        if (l_hostTransferReceiptParams == null 
                || l_orderType == null
                || l_changeType == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) 証券会社オブジェクトを取得する。 
        // [引数] 
        // 証券会社コード： 引数.振替入力通知キューParams.証券会社コード
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
                
        long l_lngNewOrderId = 0;
        try
        {                
            Institution l_institution = 
                l_accountManager.getInstitution(
                    l_hostTransferReceiptParams.getInstitutionCode());
                    
            // 1.2) 代理入力者オブジェクトを取得する。 
            // [引数] 
            // 証券会社：証券会社オブジェクト 
            // 扱者コード： 引数.振替入力通知キューParams.扱者コード 
            String l_strTraderCode = l_hostTransferReceiptParams.getTraderCode();
            // 部店コード： 引数.振替入力通知キューParams.部店コード
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
                l_trader = l_finObjectManager.getTrader(
                    l_institution,
                    l_hostTransferReceiptParams.getTraderCode(),
                    l_hostTransferReceiptParams.getBranchCode());
            }
                            
            // 1.3) 入出金用の商品IDを取得する。 
            // [引数] 
            // 証券会社： 証券会社オブジェクト
            long l_lngProductId = l_orderManager.getProductId(l_institution);
            
            // 1.4) 入出金注文内容インスタンスを生成する。 
            // [引数] 
            // 代理入力者：（以下のとおり） 
            // 振替入力通知キュー.扱者コード != ブランク の場合、扱者オブジェクト 
            // 振替入力通知キュー.扱者コード == ブランク の場合、null
            // 注文種別： 引数.注文種別 
            // 振替タイプ： 引数.振替タイプ 
            // 商品ID： get商品ID()の戻り値 
            // 金額： 引数.振替入力通知キューParams.振替金額 
            // 記述： （以下のとおり） 
            //引数.注文種別 == その他振替注文（1017 or 1018） の場合、引数.振替入力通知キューParams.摘要コード 
            //引数.注文種別 != その他振替注文（1017 or 1018） の場合、null 
            // 振替予定日： 振替入力通知キューParams.振替指示日付（※１） 
            // 決済機@関ID： null 
            // 注文ID： null 
            
            //（※１） 
            //振替入力通知キューParams.振替指示日付がNULLの場合は、現在日付をセットする。

            String l_strDescription = null;
            if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) || 
                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
            {
                l_strDescription = l_hostTransferReceiptParams.getRemarkCode();
            }
            else
            {
                l_strDescription = null;
            }
            
            Date l_datSysTime = 
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            
            Date l_datEstTransferDate = null;
            
            //振替入力通知キューParams.振替指示日付がNULLの場合は、現在日付をセットする。
            if (l_hostTransferReceiptParams.getTransferDate() == null)
            {
                l_datEstTransferDate = l_datSysTime;
            }
            else
            {
                l_datEstTransferDate = WEB3DateUtility.getDate(
                    l_hostTransferReceiptParams.getTransferDate(), "yyyyMMdd");
            }
            
            if (l_datEstTransferDate == null)
            {
				log.error("振替請求通知キュー.振替指示日付が不正 ");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80017,
					this.getClass().getName() + "." + STR_METHOD_NAME);
          	}
			
            WEB3AioNewOrderSpec l_AioNewOrderSpec = 
                new WEB3AioNewOrderSpec(
                    l_trader, 
                    l_orderType, 
                    l_changeType, 
                    l_lngProductId, 
                    l_hostTransferReceiptParams.getTransferAmount(), 
                    l_strDescription, 
                    WEB3DateUtility.toDay(l_datEstTransferDate), 
                    null, 
                    null);
                
            // 1.5) 顧客オブジェクトを取得する。 
            // [引数] 
            // 証券会社ID： 証券会社.getInstitutionId()の戻り値 
            // 部店コード： 引数.振替入力通知キューParams.部店コード 
            // 顧客コード： 引数.振替入力通知キューParams.顧客コード 
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_hostTransferReceiptParams.getBranchCode(),
                l_hostTransferReceiptParams.getAccountCode());
                
            // 1.6) 補助口座タイプを取得する。 
            // [引数] 
            // 注文種別： 引数.注文種別 
            // 振替タイプ： 引数.振替タイプ
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_orderType,l_changeType);
            log.debug("補助口座タイプ = " + l_subAccountType);
            
            // 1.7) 補助口座オブジェクトを取得する。 
            // [引数] 
            // 補助口座タイプ： get補助口座タイプ()の戻り値
            SubAccount l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
            
            // 1.8) 新規注文IDを取得する。
            l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            // 1.9) 振替通知更新インタセプタのインスタンスを生成する。 
            // [引数] 
            // 入出金注文内容： 入出金注文内容オブジェクト
            WEB3AioTransferNotifyUpdateInterceptor l_updateInterceptor = 
                new WEB3AioTransferNotifyUpdateInterceptor(l_AioNewOrderSpec);
            
            // 1.10) プロパティセット   
            //インタセプタ.発注日 = 現在日付
            l_updateInterceptor.setBizDate(l_datSysTime);
            
            l_updateInterceptor.setDeliveryDate(
                l_AioNewOrderSpec.getEstimatedTransferDate());
            
            l_updateInterceptor.setOrderRequestNumber(
                l_hostTransferReceiptParams.getOrderRequestNumber());
                
            // 1.11) インタセプタをセットする。 
            // [引数] 
            // 振替通知更新インタセプタ： 振替通知更新インタセプタオブジェクト
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            // 1.12) 注文登録処理を行う。 
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
                    l_AioNewOrderSpec,
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
        }
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngNewOrderId;
    }

    /**
     * (get補助口座タイプ)<BR>
     * 引数から決定される補助口座タイプを返却する。<BR>
     * <BR>
     * １）引数.注文種別 = 1005（振替注文（預り金から信用保証金））の場合<BR>
     * <BR>
     * １－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    2（保証金口座）を返却する。<BR>
     * <BR>
     * １－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * <BR>
     * ２）引数.注文種別 = 1006（振替注文（信用保証金から預り金））の場合<BR>
     * <BR>
     * ２－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * <BR>
     * ２－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    2（保証金口座）を返却する。<BR>
     * <BR>
     * ３）引数.注文種別 = 1007（振替注文（預り金から株先証拠金））の場合<BR>
     * <BR>
     * ３－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    7（証拠金口座）を返却する。<BR>
     * <BR>
     * ３－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * <BR>
     * ４）引数.注文種別 = 1008（振替注文（株先証拠金から預り金））の場合<BR>
     * <BR>
     * ４－１）引数.振替タイプ = 1（入金）の場合<BR>
     * <BR>
     *    1（預り金口座）を返却する。<BR>
     * <BR>
     * ４－２）引数.振替タイプ = 2（出金）の場合<BR>
     * <BR>
     *    7（証拠金口座）を返却する。<BR>
     * <BR>
     * ５）引数.注文種別が以下のいずれかの場合 <BR>
     * 　@　@　@1011（為替保証金振替注文（預り金から為替保証金）） <BR>
     * 　@　@　@1012（為替保証金振替注文（為替保証金から預り金）） <BR>
     *      1017（その他振替注文（預り金からX））<BR>
     *      1018（その他振替注文（Xから預り金））<BR>
     * <BR>
     * 1（預り金口座）を返却する。<BR>
     * <BR>
     * @@param l_orderType - (注文種別)
     * @@param l_changeType - (振替タイプ)
     * @@return SubAccountTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 413D44D7020B
     */
    protected SubAccountTypeEnum getSubAccountType(
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getSubAccountType(" 
                + "OrderTypeEnum l_orderType," 
                + "AssetTransferTypeEnum l_changeType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderType == null || l_changeType == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）引数.注文種別 = 1005（振替注文（預り金から信用保証金））の場合 
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderType))
        {
            //１－１）引数.振替タイプ = 1（入金）の場合 
            //2（保証金口座）を返却する。 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                //１－２）引数.振替タイプ = 2（出金）の場合 
                //1（預り金口座）を返却する。
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                }
            }
        }

        //２）引数.注文種別 = 1006（振替注文（信用保証金から預り金））の場合 
        if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
        {
            //２－１）引数.振替タイプ = 1（入金）の場合 
            //1（預り金口座）を返却する。 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            else
            {
                //２－２）引数.振替タイプ = 2（出金）の場合 
                //2（保証金口座）を返却する。
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
                }
            }
        }
        
        //３）引数.注文種別 = 1007（振替注文（預り金から株先証拠金））の場合 
        if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
        {
            //３－１）引数.振替タイプ = 1（入金）の場合 
            //7（証拠金口座）を返却する。 
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
            }
            else
            {
                //３－２）引数.振替タイプ = 2（出金）の場合 
                //1（預り金口座）を返却する。 
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                }
            }
        }
        
        //４）引数.注文種別 = 1008（振替注文（株先証拠金から預り金））の場合 
        if(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
        {
            //４－１）引数.振替タイプ = 1（入金）の場合 
            //1（預り金口座）を返却する。
            if(AssetTransferTypeEnum.CASH_IN.equals(l_changeType))
            {
                log.exiting(STR_METHOD_NAME);
                return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            else
            {
                //４－２）引数.振替タイプ = 2（出金）の場合 
                //7（証拠金口座）を返却する。 
                if(AssetTransferTypeEnum.CASH_OUT.equals(l_changeType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
                }
            }
        }
        
        //============= remain wei-nianqiong No.138 start ==================
        //５）引数.注文種別が以下のいずれかの場合 
        //　@1011（為替保証金振替注文（預り金から為替保証金）） 
        //　@1012（為替保証金振替注文（為替保証金から預り金）） 
        //  1017（その他振替注文（預り金からX））
        //  1018（その他振替注文（Xから預り金））
        //1（預り金口座）を返却する。
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
            OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
            OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
        {
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
        }
        //============= remain wei-nianqiong No.138 end ==================
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
