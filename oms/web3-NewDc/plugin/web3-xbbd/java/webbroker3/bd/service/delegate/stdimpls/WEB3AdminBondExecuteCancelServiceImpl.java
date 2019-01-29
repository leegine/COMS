head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券約定取消サービスImpl(WEB3AdminBondExecuteCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 徐大方(中訊) 新規作成         
Revesion History : 2007/7/25 武波 (中訊) 仕様変更・モデルNo.237
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.bd.WEB3AdminBondDomesticOrderCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderCancelAcceptUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderCancelUpdateInterceptor;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3SalesofficeTpcheckDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者債券約定取消サービスImpl)<BR>
 * 管理者新規約定取消サービスImplクラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondExecuteCancelServiceImpl 
    implements WEB3AdminBondExecuteCancelService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelServiceImpl.class);
  
    /**
     * @@roseuid 44E3362E034B
     */
    public WEB3AdminBondExecuteCancelServiceImpl() 
    {
     
    }
    
    /**
     * 債券約定取消処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。 <BR>
     * <BR>
     * －validate約定取消() <BR>
     * －submit約定取消()
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD3F0341
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        //－validate約定取消()
        if (l_request instanceof WEB3AdminBondExecCancelConfirmRequest)
        {
            l_response = 
                this.validateExecuteCancel(
                    (WEB3AdminBondExecCancelConfirmRequest) l_request);
        }
        
        //－submit約定取消()
        else if (l_request instanceof WEB3AdminBondExecCancelCompleteRequest)
        {
            l_response = 
                this.submitExecuteCancel(
                    (WEB3AdminBondExecCancelCompleteRequest) l_request);
        }
        
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate債券約定取消)<BR>
     * 債券約定取消確認処理を行う。 <BR>
     * <BR>
     * シーケンス図「validate約定取消」 参照。
     * @@param l_request - (リクエスト)<BR>
     * 管理者債券約定取消確認リクエストデータ
     * @@return WEB3AdminBondExecCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD4A03AE
     */
    protected WEB3AdminBondExecCancelConfirmResponse validateExecuteCancel(
        WEB3AdminBondExecCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateExecuteCancel(WEB3AdminBondExecCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4 get債券注文単位By注文ID(long)
        //債券注文単位オブジェクトを取得 
        //[引数] 
        //注文ID：リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.5 get補助口座(口座ID : , 補助口座ID : )
        //補助口座オブジェクトを取得 
        //[get補助口座()の引数] 
        //口座ID：get債券注文単位By注文ID.get口座ID() 
        //補助口座ID：get債券注文単位By注文ID.get補助口座ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                    l_bondOrderUnit.getAccountId(), 
                    l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 

        //1.6 validate部店権限(部店コード : String)
        //管理者が指定した部店に取り扱えるかチェック 
        //[validate部店権限()の引数] 
        //部店コード：get補助口座.get取引店().get部店コード()
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.7 get債券銘柄(long)
        //注文債券銘柄オブジェクトを取得 
        //[get債券銘柄()の引数] 
        //銘柄ID：取得した債券注文単位.get銘柄ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProduct().getProductId());

        //1.8 validate管理者取扱可能銘柄(債券銘柄)
        //管理者取扱可能かチェック 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.9 validate取消可能状態(BondOrderUnit)
        //取消可能であるかどうかチェック 
        //[validate取消可能状態()の引数] 
        //債券注文単位：get債券注文単位By注文ID
        l_bondOrderManager.validateCancelPossibleStatus(l_bondOrderUnit);
        
        //1.10 get注文約定区分( )
        //注文約定区分を取得
        String l_strExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.11 get注文約定区分() == 約定済の場合
        double l_dblOtherTradingPower = 0.0D;
        boolean l_blnFlag = false;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strExecStatus))
        {
            //1.11.1 is余力チェック対象約定取消(拡張債券注文単位) == true
            if (this.isTpCheckNeedToExecuteCancel(l_bondOrderUnit))
            {
                //1.11.1.1 getDeliveryDate( )
                Date l_datDeliveryDate = l_bondOrderUnit.getDeliveryDate();
                
                //1.11.1.2 getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
                WEB3TPTradingPowerService l_tpTradingPowerService = 
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);
                l_dblOtherTradingPower =
                    l_tpTradingPowerService.getOtherTradingPower(
                        l_subAccount, 
                        l_datDeliveryDate);
                
                //1.11.1.3 get受渡代金(円貨)
                double l_dblEstimatedPrice = l_bondOrderUnit.getEstimatedPrice();
                
                //1.11.1.4 getその他商品買付可能額　@＜　@get受渡代金(円貨)　@の場合
                if (l_dblOtherTradingPower < l_dblEstimatedPrice)
                {
                    l_blnFlag = true;
                }       
            }
        }
        
        //1.12 to顧客情報(拡張債券注文単位)
        //顧客情報オブジェクトを取得 
        //[to顧客情報()の引数] 
        //債券注文単位：get債券注文単位By注文ID
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_helperService.toAccountInfo(l_bondOrderUnit);
        
        //1.13 to銘柄情報(債券銘柄)
        //銘柄情報オブジェクトを取得 
        //[引数] 
        //債券銘柄：get債券銘柄 
        WEB3AdminBondProductInfo l_bondProductInfo = 
            l_helperService.toProductInfo(l_bondProduct);
        
        //1.14 to注文情報(拡張債券注文単位)
        //注文情報オブジェクトを取得 
        //[get注文情報()の引数] 
        //債券注文単位：取得した債券注文単位オブジェクト
        WEB3AdminBondOrderInfo l_bondOrderInfo = 
            l_helperService.toOrderInfo(l_bondOrderUnit);
        
        //1.15 to約定情報(拡張債券注文単位)
        //約定情報オブジェクトを取得 
        //[get約定情報()の引数] 
        //債券注文単位：債券注文単位オブジェクト
        WEB3AdminBondOrderExecInfo l_orderExecInfo = 
            l_helperService.toOrderExecInfo(l_bondOrderUnit);
        
        //1.16 createレスポンス()
        WEB3AdminBondExecCancelConfirmResponse l_response = 
            (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
        
        //1.17 プロパティセット
        //createレスポンス()にて取得したレスポンスデータに以下のプロパティをセットする。  
        //顧客情報=to顧客情報
        //銘柄情報=to銘柄情報 
        //注文情報=to注文情報
        //約定情報=to約定情報
        //※約定情報.警告区分一覧には、以下に該当する警告区分を追加する。 
        //・getその他商品買付可能額　@＜　@get受渡代金(円貨)　@の時に"余力チェックNG"  
        l_response.accountInfo = l_bondAccountInfo;
        l_response.productInfo = l_bondProductInfo;
        l_response.orderInfo = l_bondOrderInfo;
        if (l_blnFlag)
        {
            l_orderExecInfo.warningDiv = 
                new String[] {WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG};
        }
        l_response.execInfo = l_orderExecInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit債券約定取消)<BR>
     * 債券約定取消完了処理を行う。 <BR>
     * <BR>
     * シーケンス図「submit約定取消」 参照。
     * @@param l_request - (リクエスト)<BR>
     * 管理者債券約定取消完了リクエストデータ
     * @@return WEB3AdminBondExecCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD5200B0
     */
    protected WEB3AdminBondExecCancelCompleteResponse submitExecuteCancel(
        WEB3AdminBondExecCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitExecuteCancel(WEB3AdminBondExecCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4 validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかチェックする 
        //[validate取引パスワード()の引数] 
        //パスワード：リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //1.6 get債券注文単位By注文ID(long)
        //債券注文単位オブジェクトを取得 
        //[get債券注文単位By注文ID()の引数]  
        //注文ID：リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.7 get補助口座(口座ID : , 補助口座ID : )
        //補助口座オブジェクトを取得 
        //[get補助口座()の引数] 
        //口座ID：get債券注文単位By注文ID.get口座ID() 
        //補助口座ID：get債券注文単位By注文ID.get補助口座ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                l_bondOrderUnit.getAccountId(), 
                l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 

        //1.8 validate部店権限(部店コード : String)
        //当該管理者が、指定の部店を取り扱えるかをチェックする。 
        //[validate部店権限()の引数] 
        //部店コード：get補助口座.get取引店().get部店コード()
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.9 get債券銘柄(long)
        //注文債券銘柄オブジェクトを取得 
        //[get債券銘柄()の引数] 
        //銘柄ID：get債券注文単位By注文ID.get銘柄ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProduct().getProductId());
        
        //1.10 validate管理者取扱可能銘柄(債券銘柄)
        //管理者取扱可能かチェック 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.11 validate取消可能状態(BondOrderUnit)
        //約定取消可能であるかどうかチェックする 
        //[引数] 
        //債券注文単位：get債券注文単位By注文ID
        l_bondOrderManager.validateCancelPossibleStatus(l_bondOrderUnit);
        
        //1.12 get注文約定区分( )
        //注文約定区分を取得
        String l_strExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.13 CancelOrderSpec(arg0 : long)
        //債券取消注文内容を生成 
        //引数：リクエストデータ.注文ID()
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(Long.parseLong(l_request.id));
        
        //1.14 get注文約定区分() == 約定済の場合 かつ 債券注文単位.債券タイプ == 外国債券 の場合
        double l_dblOtherTradingPower = 0.0D;
        boolean l_blnFlag = false;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strExecStatus)
            && BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            //1.14.1 債券管理者約定取消更新インタセプタ()
            //インタセプタを生成
            WEB3AdminBondExecuteCancelUpdateInterceptor l_interceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.14.2 プロパティセット
            //生成したインタセプタに下記のプロパティをセットする 
            //債券銘柄：get債券銘柄 
            //管理者：getInstanceFromログイン情報 
            //拡張債券注文単位：get債券注文単位By注文ID 
            l_interceptor.setBondProduct(l_bondProduct);
            l_interceptor.setAdministrator(l_admin);
            l_interceptor.setBondOrderUnit(l_bondOrderUnit);
            
            //1.14.3 is余力チェック対象約定取消(拡張債券注文単位) == true
            if (this.isTpCheckNeedToExecuteCancel(l_bondOrderUnit))
            {
                //1.14.3.1 getDeliveryDate( )
                Date l_datDeliveryDate = l_bondOrderUnit.getDeliveryDate();
                
                //1.14.3.2 getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
                l_dblOtherTradingPower =
                    l_tpTradingPowerService.getOtherTradingPower(
                        l_subAccount, 
                        l_datDeliveryDate);
                
                //1.14.3.3 get受渡代金(円貨)
                double l_dblEstimatedPrice = l_bondOrderUnit.getEstimatedPrice();
                
                //1.14.3.4 getその他商品買付可能額　@＜　@get受渡代金(円貨)　@の場合
                if (l_dblOtherTradingPower < l_dblEstimatedPrice)
                {
                    l_blnFlag = true;
                }            
            }
            
            //1.14.4 undo約定(BondOrderUnit, 債券管理者デフォルトインタセプタ)
            //約定取消をする 
            //[引数]  
            //債券注文単位： get債券注文単位By注文ID 
            //債券管理者デフォルトインタセプタ： 債券管理者約定取消更新インタセプタ
            WEB3AdminBondExecuteNotifyService l_service = 
                (WEB3AdminBondExecuteNotifyService)Services.getService(WEB3AdminBondExecuteNotifyService.class);
            l_service.undoExecute(
                l_bondOrderUnit, 
                l_interceptor);     
        }

        //1.15債券注文単位.債券タイプ == 外国債券 の場合
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            //債券管理者注文取消更新インタセプタ( )
            WEB3AdminBondOrderCancelUpdateInterceptor l_orderCancelUpdateInterceptor =
                new WEB3AdminBondOrderCancelUpdateInterceptor();

            //プロパティセット
            //生成したインスタンスに下記のプロパティをセットする。
            //管理者：getInstanceFromログイン情報
            l_orderCancelUpdateInterceptor.setAdministrator(l_admin);

            //setThreadLocalPersistenceEventInterceptor(
            //arg0 : BondOrderManagerPersistenceEventInterceptor)
            //[引数]
            //BondOrderManagerPersistenceEventInterceptor：
            //債券注文単位.債券タイプ == 外国債券 の場合、債券管理者注文取消更新インタセプタ
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_orderCancelUpdateInterceptor);
        }
        else
        {
            //債券注文単位.債券タイプ ≠ 外国債券
            // 国内債券管理者注文取消更新インタセプタ( )
            WEB3AdminBondDomesticOrderCancelUpdateInterceptor l_adminBondDomesticOrderCancelUpdateInterceptor =
                new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

            //プロパティセット
            //生成したインスタンスに下記のプロパティをセットする。
            //管理者：getInstanceFromログイン情報
            l_adminBondDomesticOrderCancelUpdateInterceptor.setAdministrator(l_admin);

            //setThreadLocalPersistenceEventInterceptor(
            //arg0 : BondOrderManagerPersistenceEventInterceptor)
            //[引数]
            //BondOrderManagerPersistenceEventInterceptor：
            //債券注文単位.債券タイプ ≠ 外国債券 の場合、国内債券管理者注文取消更新インタセプタ
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_adminBondDomesticOrderCancelUpdateInterceptor);
        }

        //1.18 submitCancelOrder(arg0 : SubAccount, arg1 : CancelOrderSpec,
        //arg2 : 論理ビュー::java::lang::String, arg3 : boolean)
        //債券取消注文をsubmitする 
        //[submitCancelOrderの引数]  
        //補助口座：get補助口座  
        //取消注文内容：生成した債券取消注文内容  
        //取引パスワード： 
        //取引パスワード設定 == ’取引パスワード使用’の場合、 
        //取得した補助口座.get顧客().getTradingPassword（）の戻り値を 
        //WEB3Crypt().decrypt()で復号したもの  
        //取引パスワード設定 == ’DEFAULT’の場合、リクエストデータ.暗証番号 
        //is発注審査省略：true
        
        //ログインタイプ属性を取得する
        OpLoginSecurityService l_securityService = 
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_adminService = (OpLoginAdminService) Services.getService(OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        String l_strAttribute = (String) l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
       
        //取引パスワード設定 == ”DEFAULT”
        String l_strTradingPassword = null;
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            l_strTradingPassword = l_request.password;
        }
        //取引パスワード設定 == ”取引パスワード使用” の場合
        else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            l_strTradingPassword = l_web3Crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        l_bondOrderManager.submitCancelOrder(
            l_subAccount, 
            l_cancelOrderSpec,
            l_strTradingPassword,
            true);

        //get債券注文単位By注文ID
        //[get債券注文単位By注文ID()の引数]
        //注文ID：リクエストデータ.注文ID
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3BondOrderUnit l_bondOrderUnitByOrderId =
            l_bondOrderManager.getBondOrderUnitByOrderId(l_lngOrderId);

        //getOrderStatus( )
        OrderStatusEnum l_orderStatusEnum = l_bondOrderUnitByOrderId.getOrderStatus();

        int l_intOrderStatusEnum = l_orderStatusEnum.intValue();
        String l_strOrderStatusEnum = l_intOrderStatusEnum + "";
        //取得した注文状態 == 受付済（取消注文）
        if (WEB3OrderStatusDef.ACCEPTED_CANCELORDER.equals(l_strOrderStatusEnum))
        {
            //1.19.1 債券管理者注文取消受付更新インタセプタ()
            WEB3AdminBondOrderCancelAcceptUpdateInterceptor l_orderCancelAcceptUpdateInterceptor = 
                new WEB3AdminBondOrderCancelAcceptUpdateInterceptor();
            
            //1.19.2 accept注文取消(long, 債券管理者デフォルトインタセプタ)
            //accept取消注文を実行 
            //[accept取消注文()の引数] 
            //注文ID：リクエストデータ.注文ID()  
            //債券管理者デフォルトインタセプタ : 生成した債券管理者注文取消受付更新インタセプタ
            WEB3AdminBondExecuteNotifyService l_service = 
                (WEB3AdminBondExecuteNotifyService)Services.getService(WEB3AdminBondExecuteNotifyService.class);
            l_service.acceptOrderCancel(Long.parseLong(l_request.id), l_orderCancelAcceptUpdateInterceptor);  
        }
        
        //1.20 余力再計算(補助口座 : 補助口座)
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount);  
              
        //1.21 createレスポンス()
        //管理者債券約定取消完了レスポンスを生成
        WEB3AdminBondExecCancelCompleteResponse l_response = 
            (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
        
        //1.22 プロパティセット
        //プロパティをセットする。   
        //更新時間：現在日時 
        //識別番号：リクエストデータ.注文ID 
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;
  
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (is余力チェック対象約定取消)<BR>
     * 余力チェック対象かチェックする。<BR>
     * <BR>
     * １）下記条件を満たす場合、trueを返す。<BR>
     * 　@　@引数.債券注文単位.get注文約定区分() ==　@約定済<BR>
     *　@　@かつ　@債券注文単位.get注文種別 = 債券売り注文<BR>
     *　@　@ かつ　@債券注文単位.get決済区分　@= 円貨 <BR>
     *　@　@かつ　@部店用プリファ@レンス.営業店取引余力チェック実施区分-債券(*)　@!= EXECUTE <BR>
     * <BR>
     * ２） 上記以外の場合、falseを返す。<BR>
     * <BR>
     * (*)「部店用プリファ@レンス.営業店取引余力チェック実施区分-債券」の取得方法@： <BR>
     * 部店用プリファ@レンステーブルを検索する。 <BR>
     * [検索条件] <BR>
     * 　@　@　@部店ID：債券注文単位.get部店ID <BR>
     *       プリファ@レンス名：営業店取引余力チェック実施区分-債券 <BR>
     *       プリファ@レンス連番 = 1 <BR>
     * <BR>
     * @@param l_bondOrderUnit - (拡張債券注文単位)<BR>
     * 拡張債券注文単位
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44BCA43001CA
     */
    protected boolean isTpCheckNeedToExecuteCancel(WEB3BondOrderUnit l_bondOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isTpCheckNeedToExecuteCancel(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
     
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //(*)「部店用プリファ@レンス.営業店取引余力チェック実施区分-債券」の取得方法@： 
        //部店用プリファ@レンステーブルを検索する。 
        //[検索条件] 
        //部店ID：債券注文単位.get部店ID 
        //プリファ@レンス名：営業店取引余力チェック実施区分-債券 
        //プリファ@レンス連番 = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_bondOrderUnit.getBranchId(),
                    "bond.salesoffice.tpcheck.div",
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        boolean l_blnValue = false;
        if (l_branchPreferencesRow == null || 
            !WEB3SalesofficeTpcheckDivDef.EXECUTE.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        
        //１）下記条件を満たす場合、trueを返す。
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()) &&
            OrderTypeEnum.BOND_SELL.equals(l_bondOrderUnit.getOrderType()) && 
            WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv()) &&
            l_blnValue)
        {           
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
              
    }
}
@
