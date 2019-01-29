head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整サービスImpl(WEB3AdminMutualTPAdjustServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成                   
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3AdminMutualTPAdjustConfirmInterceptor;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * 投信管理者余力調整サービスImpl
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3AdminMutualTPAdjustService 
{    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustServiceImpl.class);
        
    /**
     * 投資信託余力調整処理を実施する。 
     * <BR>
     * リクエストデータの型により、 以下のいずれかのメソッドをコールする。 <BR>
     * <BR>
     * 　@○投信管理者余力調整確認リクエストの場合 <BR>
     * 　@　@this.validate余力調整( ) <BR>
     * 　@○投信管理者余力調整完了リクエストの場合 <BR>
     * 　@　@this.submit余力調整( )<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        //リクエストデータの型により、 以下のいずれかのメソッドをコールする。 

        //○投信管理者余力調整確認リクエストの場合 
        //　@this.validate余力調整( ) 
        //○投信管理者余力調整完了リクエストの場合 
        //　@this.submit余力調整( )

        if (l_request instanceof WEB3AdminMutualTPAdjustConfirmRequest)
        {
            WEB3AdminMutualTPAdjustConfirmResponse l_adminMutualTPAdjustConfirmResponse =
                this.validateTPAdjust((WEB3AdminMutualTPAdjustConfirmRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_adminMutualTPAdjustConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminMutualTPAdjustCompleteRequest)
        {
            WEB3AdminMutualTPAdjustCompleteResponse l_adminMutualTPAdjustCompleteResponse =
                this.submitTPAdjust((WEB3AdminMutualTPAdjustCompleteRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_adminMutualTPAdjustCompleteResponse;
        }
        else
        {
            // パラメータ値が不正
            log.debug(STR_METHOD_NAME + " パラメータ値が不正する！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
    }
    
    /**
     * (validate余力調整)
     * 投資信託余力調整確認処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）余力調整確認」参照<BR>
     * ==========================================================<BR>
     *   1.4 顧客オブジェクトが取得できなかった場合、例外をスロー。<BR>
     *      『該当する顧客が存在しません。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.5 拡張投信銘柄が取得できなった場合、例外をスロー。<BR>
     *      『該当する銘柄が存在しません。」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00391<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.6 発注日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『発注日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02019<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.7 約定日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『約定日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02149<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.8 受渡日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『受渡日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02336<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.9 受渡日＜入力日チェック<BR>
     *       受渡日＜現在日付（システムタイムスタンプ）の場合、例外をスロー。<BR>
     *      『受渡日が入力日より前の日付です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02347<BR>
     *==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminMutualTPAdjustConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40BDAA350378
     */
    protected WEB3AdminMutualTPAdjustConfirmResponse validateTPAdjust(
        WEB3AdminMutualTPAdjustConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateTPAdjust(" +
            "WEB3AdminMutualTPAdjustConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。 
        //[引数]  
        //　@機@能カテゴリコード： 機@能カテゴリコード.投信（余力調整） 
        //　@is更新： true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST,
            true);
        
        //1.4 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //顧客オブジェクトを取得する。 
        //[引数] 
        //　@証券会社コード： 管理者オブジェクト.get証券会社コード() 
        //　@部店コード： 管理者オブジェクト.get部店コード() 
        //　@口座コード： リクエストデータ.顧客コード
        
        //拡張アカウントマネージャの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {        
            l_accMgr.getMainAccount(
                l_admin.getInstitutionCode(), 
                l_admin.getBranchCode(), 
                l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //顧客オブジェクトが取得できなかった場合、例外をスロー。
            //『該当する顧客が存在しません。』            
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.5 get投信銘柄(Institution, String)
        //拡張投信銘柄を取得する。  
        //［引数］  
        //　@証券会社： 管理者オブジェクト.get証券会社() 
        //　@銘柄コード： リクエストデータ.銘柄コード
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager(); 
      
        try
        {
           l_mutualFundProductManager.getMutualFundProduct(
                l_admin.getInstitution(), 
                l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            //拡張投信銘柄が取得できなった場合、例外をスロー。
            //『該当する銘柄が存在しません。」
            log.error("該当する銘柄が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       

        //1.6 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@日付： リクエストデータ.発注日
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.orderBizDate.getTime()));
        
        //発注日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『発注日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("発注日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注日が非営業日です。");
        }        
        
        //1.7 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@約定日： リクエストデータ.約定日
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.executionTimestamp.getTime()));
        
        //約定日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『約定日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("約定日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定日が非営業日です。");
        }
        //1.8 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@日付： リクエストデータ.受渡日
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.deliveryDate.getTime()));
        
        //受渡日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『受渡日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("受渡日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が非営業日です。");
        }
        //1.9 受渡日＜入力日チェック
        if (WEB3DateUtility
            .compare(
                l_request.deliveryDate,
                GtlUtils.clearTimeFields(GtlUtils.getSystemTimestamp()))
            < 0)
        {
            log.debug("受渡日が入力日より前の日付です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02347,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡日が入力日より前の日付です。");
        }
        
        //1.10 createResponse( )
        //レスポンスデータの生成
        WEB3AdminMutualTPAdjustConfirmResponse l_response = 
            (WEB3AdminMutualTPAdjustConfirmResponse) l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit余力調整)
     * 投資信託余力調整完了処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）余力調整完了」参照<BR>
     * ==========================================================<BR>
     *   1.5 顧客オブジェクトが取得できなかった場合、例外をスロー。<BR>
     *      『該当する顧客が存在しません。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.9 拡張投信銘柄が取得できなった場合、例外をスロー。<BR>
     *      『該当する銘柄が存在しません。」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00391<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.10 発注日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『発注日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02019<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.11 約定日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『約定日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02149<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.12 受渡日チェック<BR>
     *       get営業日区分()==”非営業日”の場合、例外をスロー。<BR>
     *      『受渡日が非営業日です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02336<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.13 受渡日＜入力日チェック<BR>
     *       受渡日＜現在日付（システムタイムスタンプ）の場合、例外をスロー。<BR>
     *      『受渡日が入力日より前の日付です。』<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02347<BR>
     *==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminMutualTPAdjustCompleteResponse
     * @@roseuid 40BDAA350378
     */
    protected WEB3AdminMutualTPAdjustCompleteResponse submitTPAdjust(
        WEB3AdminMutualTPAdjustCompleteRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitTPAdjust(" +
            "WEB3AdminMutualTPAdjustCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。 
        //[引数]  
        //　@機@能カテゴリコード： 機@能カテゴリコード.投信（余力調整） 
        //　@is更新： true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST,
            true);
        
        //1.4 validate取引パスワード(パスワード : String)
        //暗証番号のチェックを行う。
        //[引数]  
        //　@パスワード： リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);
         
        //1.5 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //顧客オブジェクトを取得する。 

        //[引数] 
        //　@証券会社コード： 管理者オブジェクト.get証券会社コード() 
        //　@部店コード： 管理者オブジェクト.get部店コード() 
        //　@口座コード： リクエストデータ.顧客コード
        //拡張アカウントマネージャの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //顧客オブジェクトが取得できなかった場合、例外をスロー。
            //『該当する顧客が存在しません。』
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6 lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        //口座をロックする。  
        //[引数]  
        //　@証券会社コード： 管理者オブジェクト.get証券会社コード() 
        //　@部店コード： 管理者オブジェクト.get部店コード() 
        //　@口座コード： 顧客オブジェクト.get口座コード()
        l_accMgr.lockAccount(
            l_admin.getInstitutionCode(),
            l_admin.getBranchCode(),
            l_mainAccount.getAccountCode());
        
        //1.7 is信用口座開設(弁済区分 : String)
        //[引数]  
        //　@弁済区分： 0（指定なし）
        boolean l_blnisMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.8 getSubAccount(arg0 : SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。
        //[引数]  
        //　@補助口座タイプ：   
        //　@is信用口座開設=true の場合、SubAccountTypeEnum.株式信用取引口座（保証金）   
        //　@is信用口座開設=false の場合、SubAccountTypeEnum.株式取引口座（預り金）  
        SubAccount l_subAccount = null; 
        try
        {            
            if (l_blnisMarginAccountEstablished)
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.9 get投信銘柄(Institution, String)
        //拡張投信銘柄を取得する。  
        //［引数］  
        //　@証券会社： 管理者オブジェクト.get証券会社() 
        //　@銘柄コード： リクエストデータ.銘柄コード
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager(); 
        
        WEB3MutualFundProduct l_mfProduct = null;
        
        try
        {
            l_mfProduct = (WEB3MutualFundProduct) 
                l_mutualFundProductManager.getMutualFundProduct(
                    l_admin.getInstitution(), 
                    l_request.mutualProductCode);
        }
        //拡張投信銘柄が取得できなった場合、例外をスロー。
        //『該当する銘柄が存在しません。」
        catch (NotFoundException l_ex)
        {
            log.error("該当する銘柄が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       

        //1.10 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@日付： リクエストデータ.発注日
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.orderBizDate.getTime()));
        
        //発注日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『発注日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("発注日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注日が非営業日です。");
        }        
        
        //1.11 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@約定日： リクエストデータ.約定日
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.executionTimestamp.getTime()));
        
        //約定日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『約定日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("約定日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定日が非営業日です。");
        }
        //1.12 get営業日区分(日付 : Timestamp)
        //[引数] 
        //　@日付： リクエストデータ.受渡日
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.deliveryDate.getTime()));
        
        //受渡日チェック
        //get営業日区分()==”非営業日”の場合、例外をスロー。
        //『受渡日が非営業日です。』
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("受渡日が非営業日です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が非営業日です。");
        }
        //1.13 受渡日＜入力日チェック
        if (WEB3DateUtility
            .compare(
                l_request.deliveryDate,
                GtlUtils.clearTimeFields(GtlUtils.getSystemTimestamp()))
            < 0)
        {
            log.debug("受渡日が入力日より前の日付です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02347,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡日が入力日より前の日付です。");
        }
        
        //1.14 投信管理者余力調整確定インタセプタオブジェクトを生成する。
        WEB3AdminMutualTPAdjustConfirmInterceptor l_tpAdjustConfirmInterceptor = 
            new WEB3AdminMutualTPAdjustConfirmInterceptor();
        
        //1.15 ＜プロパティ・セット＞
        //生成した投信管理者余力調整確定インタセプタに以下のプロパティを設定する。  

        //１）　@発注日を設定する。 
        //　@[set発注日に渡すパラメタ]   
        //　@　@発注日： リクエストデータ.発注日 
        l_tpAdjustConfirmInterceptor.setOrderBizDate(
            new Timestamp(l_request.orderBizDate.getTime()));

        //２）　@約定日を設定する。 
        //　@[set約定日に渡すパラメタ]   
        //　@　@約定日： リクエストデータ.約定日 
        l_tpAdjustConfirmInterceptor.setExecutionDate(
            new Timestamp(l_request.executionTimestamp.getTime()));

        //３）　@受渡日を設定する。   
        //　@[set受渡日に渡すパラメタ]   
        //　@　@受渡日： リクエストデータ.受渡日 
        l_tpAdjustConfirmInterceptor.setDeliveryDate(
            new Timestamp(l_request.deliveryDate.getTime()));

        //４）　@概算受渡代金を設定する。 
        //　@[set概算受渡代金に渡すパラメタ] 
        //　@　@概算受渡代金： リクエストデータ.精算金額 
        l_tpAdjustConfirmInterceptor.setEstimatedPrice(
            Double.parseDouble(l_request.settlePrice));
        
        //５）　@計算基準価額を設定する。 
        //　@[set計算基準価額に渡すパラメタ] 
        //　@　@計算基準価額： 取得した拡張投信銘柄オブジェクト.get買付基準価額() 
        double l_dblConstantValue = l_mfProduct.getConstantValue();
        l_tpAdjustConfirmInterceptor.setConstantValue(l_dblConstantValue);
        
        //６）　@基準価額適用日を設定する。 
        //　@[set基準価額適用日に渡すパラメタ] 
        //　@　@基準価額適用日： 取得した拡張投信銘柄オブジェクト.get基準価額適用日()
        Timestamp l_tsConstantValueAppDate = new Timestamp(
            l_mfProduct.getConstantValueAppDate().getTime());
        l_tpAdjustConfirmInterceptor.setConstantValueAppDate(l_tsConstantValueAppDate);
          
        //1.16 setThreadLocalPersistenceEventInterceptor(arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        //投信管理者余力調整確定インタセプタを、拡張投信注文マネージャに設定する。 
        //［引数］ 
        //投信注文確定インタセプタ： 投信管理者余力調整確定インタセプタオブジェクト
        
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_tpAdjustConfirmInterceptor);

        //1.17 拡張投信新規注文内容(Trader, boolean, String, double, QuantityTypeEnum, TaxTypeEnum)
        // 拡張投信新規注文内容を生成する。  

        // [拡張投信新規注文内容のコンストラクタに渡すパラメタ]  
        // 　@代理入力者： null  
        // 　@is買付： true 
        // 　@銘柄コード： リクエストデータ.銘柄コード 
        // 　@注文数量： リクエストデータ.精算金額 
        // 　@注文数量タイプ： QuantityTypeEnum.金額 
        // 　@税区分：  
        // 　@　@－顧客オブジェクト.is特定口座開設()==trueの場合、TaxTypeEnum.SPECIAL   
        // 　@　@－顧客オブジェクト.is特定口座開設()==falseの場合、TaxTypeEnum.NORMAL  
        // 　@　@［is特定口座開設に渡すパラメタ］  
        // 　@　@　@受渡日： リクエストデータ.受渡日  
        // 　@　@　@補助口座： 取得した補助口座オブジェクト  
        
        boolean l_blnIsSpecialAccount =
            l_mainAccount.isSpecialAccountEstablished(
                l_request.deliveryDate, 
                l_subAccount);
        
        log.debug("顧客オブジェクト.is特定口座開設()== " + l_blnIsSpecialAccount);
        
        TaxTypeEnum l_taxTypeEnum = null;
        
        if (l_blnIsSpecialAccount)
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        
        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec = 
            new WEB3MutualFundNewOrderSpec(
                null,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.settlePrice),
                QuantityTypeEnum.AMOUNT,
                l_taxTypeEnum);
           
        //1.18 submitNewOrder()
        //注文内容をDBへ登録する。 
        //［引数］ 
        //　@補助口座： 取得した補助口座オブジェクト  
        //　@銘柄タイプ： ProductTypeEnum.投資信託  
        //　@新規注文内容： 拡張投信新規注文内容  
        //　@注文ID： 拡張投信注文マネージャ.createNewOrderId()の戻り値 
        //　@取引パスワード： リクエストデータ.暗証番号 
        //　@is発注審査省略： true
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        OrderSubmissionResult l_orderSubmissionResult = 
            l_orderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);
        
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("該当拡張投信注文マネージャ.submitNewOrder()の戻り値" +
                ".getProcessingResult().isSuccessfulResult()" +
                "==falseの場合。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
               "新規注文失敗");
        }
         
        //1.19 余力再計算(補助口座 : 補助口座)
        //余力残高情報を更新する。 
        //[引数] 
        //　@補助口座： 取得した補助口座オブジェクト
        
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount); 
         
        //1.20 createResponse( )
        //レスポンスオブジェクトを生成する。
        WEB3AdminMutualTPAdjustCompleteResponse l_response = 
            (WEB3AdminMutualTPAdjustCompleteResponse) l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
