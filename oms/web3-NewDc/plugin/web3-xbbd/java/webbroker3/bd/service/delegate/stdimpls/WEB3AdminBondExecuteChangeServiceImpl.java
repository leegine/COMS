head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者約定変更サービスImpl(WEB3AdminBondExecuteChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 周捷(中訊) 新規作成
                   2006/10/12 徐大方(中訊)WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
                   2006/10/16 張騰宇 (中訊) モデルNo.106.108.129
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.WEB3AdminBondExecuteCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteChangeCommonInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;

/**
 * (管理者約定変更サービスImpl)<BR>
 * 管理者約定変更サービスImplクラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeServiceImpl 
    implements WEB3AdminBondExecuteChangeService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F00DA
     */
    public WEB3AdminBondExecuteChangeServiceImpl() 
    {
     
    }
    
    /**
     * 管理者約定変更サービス処理を実施する。<BR>
     * <BR>
     * 約定変更execute処理を行う。 <BR>
     * <BR>
     * シーケンス図「約定変更execute」参照 <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400C0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        WEB3GenResponse l_response = null;
        //1.1.リクエストデータは管理者約定変更入力リクエストである場合
        if (l_request instanceof WEB3AdminBondExecChangeInputRequest)
        {
            //1.1.1.get管理者約定変更入力画面(管理者約定変更入力リクエスト)
            l_response = 
                getExecuteChangeInputScreen((WEB3AdminBondExecChangeInputRequest) l_request);         
        }
        
        //1.2.リクエストデータは管理者約定変更確認リクエストである場合
        if (l_request instanceof WEB3AdminBondExecChangeConfirmRequest)
        {
            //1.2.1.validate管理者約定変更(管理者約定変更確認リクエスト)
            l_response = validateExecuteChange(
                (WEB3AdminBondExecChangeConfirmRequest) l_request);         
        }
        
        //1.3.リクエストデータは管理者約定変更完了リクエストである場合
        if (l_request instanceof WEB3AdminBondExecChangeCompleteRequest)
        {
            //1.3.1.submit管理者約定変更(管理者約定変更完了リクエスト)
            l_response = submitExecuteChange(
                (WEB3AdminBondExecChangeCompleteRequest) l_request);         
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get管理者約定変更入力画面)<BR>
     * get管理者約定変更入力画面処理を行う。 <BR>
     * <BR>
     * シーケンス図「get管理者約定変更入力画面」参照 <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (管理者約定変更入力リクエスト)<BR>
     * 管理者約定変更入力リクエスト<BR>
     * @@return WEB3AdminBondExecChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400E0
     */
    protected WEB3AdminBondExecChangeInputResponse getExecuteChangeInputScreen(
        WEB3AdminBondExecChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getExecuteChangeInputScreen(WEB3AdminBondExecChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.get債券注文単位By注文ID(long)
        //債券注文単位オブジェクトを取得 
        //[引数] 
        //注文ID：リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.6.to顧客情報(拡張債券注文単位)
        //顧客情報を取得 
        //[to顧客情報()の引数] 
        //債券注文単位：get債券注文単位By注文ID
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(
                WEB3AdminBondHelperService.class);
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_helperService.toAccountInfo(l_bondOrderUnit);
        
        //1.7.validate部店権限(部店コード : String)
        //当該管理者が、指定の部店を取り扱えるかをチェック 
        //[validate部店権限()の引数] 
        //部店コード：to顧客情報.部店コード
        l_admin.validateBranchPermission(l_bondAccountInfo.branchCode);
        
        //1.8.get債券銘柄(long)
        //債券銘柄オブジェクトを取得 
        //[get債券銘柄()の引数] 
        //銘柄ID：get債券注文単位By注文ID.get銘柄ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.9.validate管理者取扱可能銘柄(債券銘柄)
        //管理者取扱可能かチェック 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.10.validate約定可能状態(BondOrderUnit)
        //債券注文に対し約定可能であるかどうかチェック 
        //[引数] 
        //債券注文単位：get債券注文単位By注文ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.11.get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.12.to銘柄情報(債券銘柄)
        //銘柄情報オブジェクトを取得 
        //[引数] 
        //債券銘柄：get債券銘柄 
        WEB3AdminBondProductInfo l_bondProductInfo = 
            l_helperService.toProductInfo(l_bondProduct);
        
        //1.13.to注文情報(拡張債券注文単位)
        //注文情報オブジェクトを取得 
        //[get注文情報()の引数] 
        //債券注文単位：取得した債券注文単位オブジェクト
        WEB3AdminBondOrderInfo l_bondOrderInfo = l_helperService.toOrderInfo(l_bondOrderUnit);
        
        //1.14.to約定情報(拡張債券注文単位)
        //約定情報オブジェクトを取得 
        //[get約定情報()の引数] 
        //債券注文単位：債券注文単位オブジェクト
        WEB3AdminBondOrderExecInfo l_orderExecInfo = 
            l_helperService.toOrderExecInfo(l_bondOrderUnit);
        
        //1.15.getカストディアン一覧(証券会社)
        //カストディアン一覧リストを取得 
        //[getカストディアン一覧()の引数] 
        //証券会社：証券会社オブジェクト
        WEB3BondDataManagerService l_dataManagerService = 
            (WEB3BondDataManagerService) Services.getService(
                WEB3BondDataManagerService.class);
        List l_lisCustodians = l_dataManagerService.getCustodianList(l_institution);
        
        //1.16.toカストディアン一覧(List)
        //カストディアン一覧を取得 
        //[toカストディアン一覧()の引数] 
        //カストディアンリスト：getカストディアン一覧
        List l_lisCustodianList = l_helperService.toCustodianList(l_lisCustodians);
        
        //1.17.createレスポンス( )
        //管理者約定変更入力レスポンスオブジェクトを生成
        WEB3AdminBondExecChangeInputResponse l_response =
            (WEB3AdminBondExecChangeInputResponse) l_request.createResponse();
        
        WEB3AdminBondCustodianUnit[] l_custodianUnits = null;
        if (l_lisCustodianList != null && !l_lisCustodianList.isEmpty())
        {
            l_custodianUnits = 
                new WEB3AdminBondCustodianUnit[l_lisCustodianList.size()];
            l_lisCustodianList.toArray(l_custodianUnits);
        }
        //1.18.プロパティセット
        //顧客情報=to顧客情報
        l_response.accountInfo = l_bondAccountInfo;
        
        //債券銘柄=to債券銘柄
        l_response.productInfo = l_bondProductInfo;

        //注文情報=to注文情報
        l_response.orderInfo = l_bondOrderInfo;

        //約定情報=to約定情報
        l_response.execInfo = l_orderExecInfo;

        //顧客情報=to顧客情報
        l_response.inpOrderDate = l_datOrderBizDate;

        //カストディアン一覧=toカストディアン一覧
        l_response.custodianList = l_custodianUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate管理者約定変更)<BR>
     * validate管理者約定変更処理を行う。<BR> 
     * <BR>
     * シーケンス図「validate管理者約定変更処理」参照 <BR> 
     * --------------------------------------------------<BR>
     * @@param l_request - (管理者約定変更確認リクエスト)<BR>
     * 管理者約定変更確認リクエスト<BR>
     * @@return WEB3AdminBondExecChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400FF
     */
    protected WEB3AdminBondExecChangeConfirmResponse validateExecuteChange(
        WEB3AdminBondExecChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateExecuteChange(WEB3AdminBondExecChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.get債券注文単位By注文ID(long)
        //債券注文単位オブジェクトを取得 
        //[引数] 
        //注文ID：リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.5.get補助口座(口座ID : , 補助口座ID : )
        //補助口座オブジェクトを取得 
        //[get補助口座()の引数] 
        //口座ID：債券注文単位オブジェクト.get口座ID() 
        //補助口座ID：債券注文単位オブジェクト.get補助口座ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                    l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in 拡張アカウントマネージャから顧客を取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6.validate部店権限(部店コード : String)
        //管理者が指定した部店に取り扱えるかチェック 
        //[validate部店権限()の引数] 
        //部店コード：取得した補助口座.get取引店().get部店コード() 
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.7.get債券銘柄(long)
        //債券銘柄オブジェクトを取得 
        //[get債券銘柄()の引数] 
        //銘柄ID：get債券注文単位By注文ID.get銘柄ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.8.validate管理者取扱可能銘柄(債券銘柄)
        //管理者取扱可能かチェック 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.9.get発注日(確認時発注日 : Date)
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.inpOrderDate);
        
        //1.10.validate約定可能状態(BondOrderUnit)
        //債券注文に対し約定可能であるかどうかチェック 
        //[引数] 
        //債券注文単位：get債券注文単位By注文ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.11.validate数量(double, 債券銘柄)
        //数量をチェック 
        //[validate数量()の引数] 
        //注文数量：リクエストデータ.約定情報.約定数量 
        //債券銘柄：get債券銘柄
        double l_dblExecFaceAmount = 0D;
        double l_dblExecPrice = 0D;
        BigDecimal l_bdExecFxRate = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblExecFaceAmount = Double.parseDouble(l_request.execInfo.execFaceAmount);
        }
        if (l_request.execInfo.execPrice != null)
        {
            l_dblExecPrice = Double.parseDouble(l_request.execInfo.execPrice);
        }
        if (l_request.execInfo.execFxRate != null)
        {
        	l_bdExecFxRate = new BigDecimal(l_request.execInfo.execFxRate);
        }    
        l_bondOrderManager.validateQuantity(
            l_dblExecFaceAmount, 
            l_bondProduct);
        
        //1.12.validate約定上限数量(double, 拡張債券注文単位)
        //上限数量をチェック 
        //[validate上限数量()の引数] 
        //注文数量：リクエストデータ.約定情報.約定数量 
        //債券注文単位：get債券注文単位By注文ID
        l_bondOrderManager.validateExecuteMaxQuantity(
            l_dblExecFaceAmount, l_bondOrderUnit);
        
        //1.13validate単価(債券銘柄, String)
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.execInfo.execPrice);
        
        //1.14.validate為替レート(債券銘柄, String)
        //為替レートチェック 
        //[引数] 
        //債券銘柄：get債券銘柄 
        //為替レート：リクエストデータ.約定情報.約定為替レート
        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_request.execInfo.execFxRate);
        
        //1.15.get債券注文種別判定( )
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.16.create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄)
        //債券約定日情報を生成する 
        //[引数] 
        //発注日：get発注日 
        //債券注文種別判定：生成した債券注文種別判定 
        //債券銘柄：get債券銘柄 
        //決済区分：get債券注文単位By注文ID.get決済区分 
        //部店：取得した補助口座.get取引店()
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datOrderBizDate, l_orderTypeJudge, 
                l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                l_subAccount.getWeb3GenBranch());
        
        //1.17.reset約定日情報(約定情報, 債券約定日情報)
        //約定日情報を再設定 
        //[reset債券約定日情報()引数] 
        //約定情報：リクエストデータ.約定情報 
        //債券約定日情報：create債券約定日情報
        //債券注文種別判定：生成した債券注文種別判定 
        //債券銘柄：get債券銘柄 
        //決済区分：get債券注文単位By注文ID.get決済区分 
        //部店：取得した補助口座.get取引店()
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(
                WEB3AdminBondHelperService.class); 
        l_bondExecuteDateInfo = 
            l_helperService.resetExecuteDateInfo(l_request.execInfo, l_bondExecuteDateInfo, l_orderTypeJudge, 
                    l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                    l_subAccount.getWeb3GenBranch());
        
        //1.18.validate約定日(債券銘柄)
        //債券約定日情報をチェックする 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);
        
        //1.19is外貨建( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        BigDecimal l_bdGetFxRate = null;
        
        if (l_blnIsForeignCurrency)
        {
            //1.20.1get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_orderTypeJudge,
                l_bondOrderUnit.getSettlementDiv(),
                l_bdExecFxRate,
                true);
        }
        
        //1.21.calc受渡代金(債券注文種別判定, BigDecimal,
        //BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する 
        //[引数] 
        //債券注文種別判定：生成した債券注文種別判定 
        //数量：リクエスト.約定情報.約定数量 
        //注文単価：リクエスト.約定情報.約定単価 l_bdValidateExecPrice
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。） 
        //債券銘柄：get債券銘柄 
        //債券約定日情報：reset約定日情報 
        WEB3BondBizLogicProvider l_bizLogicProvider = 
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge, 
                new BigDecimal(String.valueOf(l_dblExecFaceAmount)),
                new BigDecimal(String.valueOf(l_dblExecPrice)),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.22.reset受渡代金(債券受渡代金計算結果, 約定情報, 債券銘柄)
        //債券受渡代金計算結果を再設定 
        //[reset受渡代金()] 
        //債券受渡代金計算結果：calc受渡代金 
        //約定情報：リクエスト.約定情報 
        //債券銘柄：get債券銘柄
        l_priceCalcResult = 
            l_helperService.resetEstimatedPrice(
                l_priceCalcResult, 
                l_request.execInfo, 
                l_bondProduct);
        
        //1.23.get注文約定区分()
        String l_strOrderExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.24.注文約定区分　@== 約定済　@の場合
        WEB3AdminBondExecuteCancelUpdateInterceptor l_cancelUpdateInterceptor = null;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.24.1債券管理者約定取消更新インタセプタ( )
            l_cancelUpdateInterceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.24.2プロパティセット
            //以下のプロパティをセットする 
            //管理者＝getInstanceFromログイン情報 
            //債券銘柄＝get債券銘柄 
            //拡張債券注文単位：get債券注文単位By注文ID 
            l_cancelUpdateInterceptor.setAdministrator(l_admin);
            l_cancelUpdateInterceptor.setBondProduct(l_bondProduct);
            l_cancelUpdateInterceptor.setBondOrderUnit(l_bondOrderUnit);
        }
        
        //1.25.債券管理者約定更新インタセプタ( )
        WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor = 
            new WEB3AdminBondExecuteUpdateInterceptor();
        
        //1.26.プロパティセット
        //生成したインタセプタに以下のプロパティをセットする。  
        //債券約定日情報：reset約定日情報 
        //債券受渡代金計算結果：reset受渡代金 
        //カストディアンコード：リクエストデータ.約定情報.カストディアン.カストディアンコード() 
        //債券銘柄：get債券銘柄 
        //管理者：getInstanceFromログイン情報
        l_executeUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_executeUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);
        l_executeUpdateInterceptor.setAdministrator(l_admin);
        
        boolean l_isSellFirstExecute = l_orderTypeJudge.isSellOrder() && WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus);
        
        //1.27.決済区分＝＝円貨　@かつ　@（売却注文で未約定であった）＝＝falseの場合
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv())
                && !l_isSellFirstExecute)
        {
            
            //1.27.1.債券管理者約定変更インタセプタ( )
            WEB3AdminBondExecuteChangeCommonInterceptor l_changeCommonInterceptor =
                new WEB3AdminBondExecuteChangeCommonInterceptor();
            
            //1.27.2.set債券管理者約定取消更新インタセプタ(債券管理者約定取消更新インタセプタ)
            //インタセプタをセットする 
            //引数：債券管理者約定取消更新インタセプタ 
            //　@　@　@　@(作成されてない時はnullになる)
            l_changeCommonInterceptor.setBondExecuteCancelUpdateInterceptor(l_cancelUpdateInterceptor);
            
            //1.27.3.set債券管理者約定更新インタセプタ(債券管理者新規約定更新インタセプタ)
            //インタセプタをセットする 
            //引数：債券管理者約定更新インタセプタ
            l_changeCommonInterceptor.setBondExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //1.27.4.BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //債券変更注文内容オブジェクトを生成 
            //[引数] 
            //注文ID：リクエストデータ.get注文ID() 
            //注文単位ID：get債券注文単位By注文ID,get注文単位ID() 
            //数量：リクエストデータ.約定情報.約定数量() 
            //単価：リクエストデータ.約定情報.約定単価() 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    Long.parseLong(l_request.id), 
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblExecFaceAmount,
                    l_dblExecPrice);
            
            //1.27.5.validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //余力チェック 
            //[validate取引余力()の引数] 
            //補助口座：get補助口座 
            //注文内容インタセプタ：債券管理者約定変更インタセプタ 
            //注文内容：BondChangeOrderSpec 
            //注文種別：get債券注文単位By注文ID.get注文種別 
            //余力更新フラグ：false
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objCommonInterceptors = new Object[]{l_changeCommonInterceptor};
            Object[] l_objChangeOrderSpecs= new Object[]{l_changeOrderSpec};
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objCommonInterceptors, 
                    l_objChangeOrderSpecs, 
                    l_bondOrderUnit.getOrderType(), 
                    false);
            
            //1.27.6.is判定フラグ( )
            //1.27.7. is判定フラグ＝＝falseの場合
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.24.7.1.add警告区分(String)
                //警告区分を追加 
                //[引数] 
                //警告区分：余力チェックNG
                l_priceCalcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        
        //1.28.to約定情報(債券約定日情報, 債券受渡代金計算結果, カストディアン, 拡張債券注文単位)
        //レスポンスの約定情報をセット 
        //引数： 
        //債券約定日情報=reset約定日情報 
        //債券受渡代金計算結果=reset受渡代金 
        //カストディアン=リクエスト.約定情報.カストディアン 
        //拡張債券注文単位＝get債券注文単位By注文ID()の戻り値  
        WEB3AdminBondOrderExecInfo l_orderExecInfo = l_helperService.toOrderExecInfo(
            l_bondExecuteDateInfo, l_priceCalcResult, l_request.execInfo.custodianInfo, l_bondOrderUnit);
        
        //1.29.createレスポンス( )
        //管理者約定変更確認レスポンスオブジェクトを生成
        WEB3AdminBondExecChangeConfirmResponse l_response = null;
        l_response = (WEB3AdminBondExecChangeConfirmResponse) l_request.createResponse();
        
        //1.30.プロパティセット
        //プロパティをセットする。
        //入力時発注日＝get発注日 
        //約定情報=to約定情報 
        l_response.inpOrderDate = l_datOrderBizDate;
        l_response.execInfo = l_orderExecInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit管理者約定変更)<BR>
     * submit管理者約定変更処理を行う。 <BR>
     * <BR>
     * シーケンス図「submit管理者約定変更処理」参照 <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (管理者約定変更完了リクエスト)<BR>
     * 管理者約定変更完了リクエスト<BR>
     * @@return WEB3AdminBondExecChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E4011E
     */
    protected WEB3AdminBondExecChangeCompleteResponse submitExecuteChange(
        WEB3AdminBondExecChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitExecuteChange(WEB3AdminBondExecChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックをする  
        //[validate権限()に指定する引数]  
        //機@能カテゴリコード：　@機@能カテゴリコード.債券（約定変更、約定取消）  
        //is更新：　@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかチェックする 
        //[validate取引パスワード()の引数] 
        //パスワード：リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.get債券注文単位By注文ID(long)
        //債券注文単位オブジェクトを取得 
        //[引数] 
        //注文ID：リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.6.get補助口座(口座ID : , 補助口座ID : )
        //補助口座オブジェクトを取得 
        //[get補助口座()の引数] 
        //口座ID：債券注文単位オブジェクト.get口座ID() 
        //補助口座ID：債券注文単位オブジェクト.get補助口座ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in 拡張アカウントマネージャから顧客を取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7.validate部店権限(部店コード : String)
        //管理者が指定した部店に取り扱えるかチェック 
        //[validate部店権限()の引数] 
        //部店コード：取得した補助口座.get取引店().get部店コード() 
        l_admin.validateBranchPermission(l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.8.get債券銘柄(long)
        //債券銘柄オブジェクトを取得 
        //[get債券銘柄()の引数] 
        //銘柄ID：get債券注文単位By注文ID.get銘柄ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.9.validate管理者取扱可能銘柄(債券銘柄)
        //管理者取扱可能かチェック 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.10.get発注日(確認時発注日 : Date)
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.inpOrderDate);
        
        //1.11.validate約定可能状態(BondOrderUnit)
        //債券注文に対し約定可能であるかどうかチェック 
        //[引数] 
        //債券注文単位：get債券注文単位By注文ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.12.validate数量(double, 債券銘柄)
        //数量をチェック 
        //[validate数量()の引数] 
        //注文数量：リクエストデータ.約定情報.約定数量 
        //債券銘柄：get債券銘柄
        double l_dblExecFaceAmount = 0D;
        double l_dblExecPrice = 0D;
        BigDecimal l_bdExecFxRate = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblExecFaceAmount = Double.parseDouble(l_request.execInfo.execFaceAmount);
        }
        if (l_request.execInfo.execPrice != null)
        {
            l_dblExecPrice = Double.parseDouble(l_request.execInfo.execPrice);
        }
        if (l_request.execInfo.execFxRate != null)
        {
        	l_bdExecFxRate = new BigDecimal(l_request.execInfo.execFxRate);
        }
        
        l_bondOrderManager.validateQuantity(l_dblExecFaceAmount, l_bondProduct);
        
        //1.13.validate約定上限数量(double, 拡張債券注文単位)
        //上限数量をチェック 
        //[validate上限数量()の引数] 
        //注文数量：リクエストデータ.約定情報.約定数量 
        //債券注文単位：get債券注文単位By注文ID
        l_bondOrderManager.validateExecuteMaxQuantity(l_dblExecFaceAmount, l_bondOrderUnit);
        
        //1.15 validate単価(債券銘柄, String)
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.execInfo.execPrice);
        
        //1.14.validate為替レート(債券銘柄, String)
        //為替レートチェック 
        //[引数] 
        //債券銘柄：get債券銘柄 
        //為替レート：リクエストデータ.約定情報.約定為替レート
        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_request.execInfo.execFxRate);
        
        //1.15.get債券注文種別判定( )
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.16.create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        //債券約定日情報を生成する 
        //[引数] 
        //発注日：get発注日 
        //債券注文種別判定：生成した債券注文種別判定 
        //債券銘柄：get債券銘柄
        //決済区分：get債券注文単位By注文ID.get決済区分 
        //部店：取得した補助口座.get取引店()
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datOrderBizDate, l_orderTypeJudge, 
                l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                l_subAccount.getWeb3GenBranch());
        
        //1.17.reset約定日情報(約定情報, 債券約定日情報)
        //約定日情報を再設定 
        //[reset債券約定日情報()引数] 
        //約定情報：リクエストデータ.約定情報 
        //債券約定日情報：create債券約定日情報
        //債券注文種別判定：生成した債券注文種別判定 
        //債券銘柄：get債券銘柄
        //決済区分：get債券注文単位By注文ID.get決済区分 
        //部店：取得した補助口座.get取引店()
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class); 
        l_bondExecuteDateInfo = 
            l_helperService.resetExecuteDateInfo(l_request.execInfo, l_bondExecuteDateInfo, l_orderTypeJudge, 
                    l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                    l_subAccount.getWeb3GenBranch());
        
        //1.18.validate約定日(債券銘柄)
        //債券約定日情報をチェックする 
        //[引数] 
        //債券銘柄：get債券銘柄
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);
        
        //1.20 is外貨建( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        BigDecimal l_bdGetFxRate = null;
        
        if (l_blnIsForeignCurrency)
        {
            //1.19.1get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_orderTypeJudge,
                l_bondOrderUnit.getSettlementDiv(),
                l_bdExecFxRate,
                true);
        }
        
        //1.22.calc受渡代金(債券注文種別判定, BigDecimal,
        //BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する 
        //[引数] 
        //債券注文種別判定：生成した債券注文種別判定 
        //数量：リクエスト.約定情報.約定数量 
        //注文単価：リクエスト.約定情報.約定単価 
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。） 
        //債券銘柄：get債券銘柄 
        //債券約定日情報：reset約定日情報 
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.BOND).getBizLogicProvider();
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge, 
                new BigDecimal(String.valueOf(l_dblExecFaceAmount)),
                new BigDecimal(String.valueOf(l_dblExecPrice)),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.23.reset受渡代金(債券受渡代金計算結果, 約定情報, 債券銘柄)
        //債券受渡代金計算結果を再設定 
        //[reset受渡代金()] 
        //債券受渡代金計算結果：calc受渡代金 
        //約定情報：リクエスト.約定情報 
        //債券銘柄：get債券銘柄
        l_priceCalcResult = 
            l_helperService.resetEstimatedPrice(l_priceCalcResult, l_request.execInfo, l_bondProduct);
        
        //1.24.get注文約定区分()
        String l_strOrderExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.25.注文約定区分　@== 約定済　@の場合
        WEB3AdminBondExecuteCancelUpdateInterceptor l_cancelUpdateInterceptor = null;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.25.1債券管理者約定取消更新インタセプタ( )
            l_cancelUpdateInterceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.25.2プロパティセット
            //以下のプロパティをセットする 
            //管理者＝getInstanceFromログイン情報 
            //債券銘柄＝get債券銘柄 
            //拡張債券注文単位：get債券注文単位By注文ID 
            l_cancelUpdateInterceptor.setAdministrator(l_admin);
            l_cancelUpdateInterceptor.setBondProduct(l_bondProduct);
            l_cancelUpdateInterceptor.setBondOrderUnit(l_bondOrderUnit);
        }
        
        //1.26.債券管理者約定更新インタセプタ( )
        WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor = 
            new WEB3AdminBondExecuteUpdateInterceptor();
        
        //1.27.プロパティセット
        //生成したインタセプタに以下のプロパティをセットする。  
        //債券約定日情報：reset約定日情報 
        //債券受渡代金計算結果：reset受渡代金 
        //カストディアンコード：リクエストデータ.約定情報.カストディアン.カストディアンコード() 
        //債券銘柄：get債券銘柄 
        //管理者：getInstanceFromログイン情報
        l_executeUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_executeUpdateInterceptor.setCustodianCode(l_request.execInfo.custodianInfo.custodianCode);
        }
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);
        l_executeUpdateInterceptor.setAdministrator(l_admin);
        
        boolean l_isSellFirstExecute = l_orderTypeJudge.isSellOrder() && WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus);
        
        //1.28.決済区分＝＝円貨　@かつ　@（売却注文で未約定であった）＝＝falseの場合
        WEB3TPTradingPowerResult l_tPTradingPowerResult = null;
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv())
                && !l_isSellFirstExecute)
        {
            
            //1.28.1.債券管理者約定変更インタセプタ( )
            WEB3AdminBondExecuteChangeCommonInterceptor l_changeCommonInterceptor =
                new WEB3AdminBondExecuteChangeCommonInterceptor();
            
            //1.28.2.set債券管理者約定取消更新インタセプタ(債券管理者約定取消更新インタセプタ)
            //インタセプタをセットする 
            //引数：債券管理者約定取消更新インタセプタ 
            //　@　@　@　@(作成されてない時はnullになる)
            l_changeCommonInterceptor.setBondExecuteCancelUpdateInterceptor(l_cancelUpdateInterceptor);
            
            //1.28.3.set債券管理者約定更新インタセプタ(債券管理者新規約定更新インタセプタ)
            //インタセプタをセットする 
            //引数：債券管理者約定更新インタセプタ
            l_changeCommonInterceptor.setBondExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //1.28.4.BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //債券変更注文内容オブジェクトを生成 
            //[引数] 
            //注文ID：リクエストデータ.get注文ID() 
            //注文単位ID：get債券注文単位By注文ID,get注文単位ID() 
            //数量：リクエストデータ.約定情報.約定数量() 
            //単価：リクエストデータ.約定情報.約定単価() 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    Long.parseLong(l_request.id), 
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblExecFaceAmount,
                    l_dblExecPrice);
            
            //1.28.5.validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //余力チェック 
            //[validate取引余力()の引数] 
            //補助口座：get補助口座 
            //注文内容インタセプタ：債券管理者約定変更インタセプタ 
            //注文内容：BondChangeOrderSpec 
            //注文種別：get債券注文単位By注文ID.get注文種別 
            //余力更新フラグ：true         
            Object[] l_objCommonInterceptors = new Object[]{l_changeCommonInterceptor};
            Object[] l_objChangeOrderSpecs= new Object[]{l_changeOrderSpec};
            
            l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objCommonInterceptors, 
                    l_objChangeOrderSpecs, 
                    l_bondOrderUnit.getOrderType(), 
                    true);
            
            //1.28.6.is判定フラグ( )
            //1.28.7. is判定フラグ＝＝falseの場合
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.28.7.1.add警告区分(String)
                //警告区分を追加 
                //[引数] 
                //警告区分：余力チェックNG
                l_priceCalcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        WEB3AdminBondExecuteNotifyService l_notifyService = 
            (WEB3AdminBondExecuteNotifyService) Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        //1.29.注文約定区分　@== 約定済　@の場合
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.29.1.undo約定(BondOrderUnit, 債券管理者デフォルトインタセプタ)
            //約定取消をする 
            //[引数]  
            //　@債券注文単位： get債券注文単位By注文ID 
            //　@債券管理者デフォルトインタセプタ： 債券管理者約定取消更新インタセプ                    
            l_notifyService.undoExecute(l_bondOrderUnit, l_cancelUpdateInterceptor);
        }
        else
        {
            //1.30.注文約定区分　@!= 約定済　@の場合
            //1.30.1.債券管理者注文受付更新インタセプタ( )
            WEB3AdminBondOrderAcceptUpdateInterceptor l_acceptUpdateInterceptor = 
                new WEB3AdminBondOrderAcceptUpdateInterceptor();
            
            //1.30.2.accept新規注文(long, 債券管理者デフォルトインタセプタ)
            //新規注文受付をする 
            //[引数]  
            //　@注文ID： リクエストデータ.注文ID 
            //　@債券管理者デフォルトインタセプタ： 債券管理者注文受付更新インタセプタ
            l_notifyService.acceptNewOrder(Long.parseLong(l_request.id), l_acceptUpdateInterceptor);
            
        }
        
        //1.31.notify約定(BondOrderUnit, 債券管理者デフォルトインタセプタ)
        //約定処理をする 
        //[引数]  
        //　@債券注文単位： get債券注文単位By注文ID 
        //　@債券管理者デフォルトインタセプタ： 債券管理者約定更新インタセプタ
        l_bondOrderUnit = l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        l_notifyService.notifyExecute(l_bondOrderUnit, l_executeUpdateInterceptor);

        //1.32.（is判定フラグ！＝null かつ　@is判定フラグ＝＝false）又は（（売却注文で未約定であった）＝＝true）の場合
        if((l_tPTradingPowerResult !=null && !l_tPTradingPowerResult.isResultFlg())
            || l_isSellFirstExecute)
        {
            //1.32.1 余力再計算(補助口座 : 補助口座)
            l_service.reCalcTradingPower(l_subAccount);  
        }

        //1.33.createレスポンス( )
        WEB3AdminBondExecChangeCompleteResponse l_response =
            (WEB3AdminBondExecChangeCompleteResponse) l_request.createResponse();
        
        //1.34.プロパティセット
        //プロパティをセットする。  
        //　@　@更新時間：現在日時 
        //　@　@識別番号：リクエストデータ.注文ID 
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
