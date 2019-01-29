head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageByCustomerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客別ポイント管理サービスImpl(WEB3AdminPointManageByCustomerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.point.WEB3PointAdjust;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.data.PointTermDao;
import webbroker3.point.data.PointTermRow;
import webbroker3.point.data.PointTotalRow;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteRequest;
import webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmRequest;
import webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse;
import webbroker3.point.message.WEB3AdminPointAdjustInputRequest;
import webbroker3.point.message.WEB3AdminPointAdjustInputResponse;
import webbroker3.point.message.WEB3AdminPointHistoryDetail;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointManageDisplayRequest;
import webbroker3.point.message.WEB3AdminPointManageDisplayResponse;
import webbroker3.point.service.delegate.WEB3AdminPointManageByCustomerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (顧客別ポイント管理サービスImpl)<BR>
 * 顧客別ポイント管理サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointManageByCustomerServiceImpl implements WEB3AdminPointManageByCustomerService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointManageByCustomerServiceImpl.class);

    /**
     * 顧客別ポイント管理サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     *    get管理画面()<BR>
     *    get入力画面()<BR>
     *    validate調整()<BR>
     *    submit調整()<BR>
     *    get照会画面()<BR>
     * <BR>
     * 上記メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41945155009C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointManageDisplayRequest)
        {
            WEB3AdminPointManageDisplayResponse  l_response = 
                this.getManageScreen((WEB3AdminPointManageDisplayRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointAdjustInputRequest)
        {
            WEB3AdminPointAdjustInputResponse  l_response = 
                this.getInputScreen((WEB3AdminPointAdjustInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointAdjustConfirmRequest)
        {
            WEB3AdminPointAdjustConfirmResponse  l_response = 
                this.validateAdjust((WEB3AdminPointAdjustConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointAdjustCompleteRequest)
        {
            WEB3AdminPointAdjustCompleteResponse  l_response = 
                this.submitAdjust((WEB3AdminPointAdjustCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointHistoryReferenceRequest)
        {
            WEB3AdminPointHistoryReferenceResponse  l_response = 
                this.getReferenceScreen((WEB3AdminPointHistoryReferenceRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get管理画面)<BR>
     * 管理画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント管理）get管理画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointManageDisplayResponse
     * @@roseuid 4194547B0186
     */
    protected WEB3AdminPointManageDisplayResponse getManageScreen(WEB3AdminPointManageDisplayRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getManageScreen(WEB3AdminPointManageDisplayRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, false);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminPointManageDisplayResponse l_response = (WEB3AdminPointManageDisplayResponse)l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント管理）get入力画面」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント管理）get入力画面」): <BR>
     *         1.5 get顧客(String, String, String)<BR>
     *          顧客オブジェクトが取得できなかった場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustInputResponse
     * @@roseuid 419454E500CB
     */
    protected WEB3AdminPointAdjustInputResponse getInputScreen(WEB3AdminPointAdjustInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointAdjustInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.5 get顧客(String, String, String) QA:WEB3-POINT-A-CD-0006.xls
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get顧客(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
         
        //1.6 get利用可能ポイント(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException
        
        //1.7 createResponse( )
        WEB3AdminPointAdjustInputResponse l_response = (WEB3AdminPointAdjustInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 プロパティセット
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate調整)<BR>
     * 調整データの審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント管理）validate調整」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント管理）validate調整」): <BR>
     *         1.5 get顧客(String, String, String)<BR>
     *          顧客オブジェクトが取得できなかった場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustConfirmResponse
     * @@roseuid 4194551B0271
     */
    protected WEB3AdminPointAdjustConfirmResponse validateAdjust(WEB3AdminPointAdjustConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAdjust(WEB3AdminPointAdjustConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.5 get顧客(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        { 
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get顧客(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
         
        //1.6 get利用可能ポイント(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        
        //1.7 validate調整ポイント(String, long)
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException           
        log.debug("利用可能ポイント = " + l_lngUsePossiblePoint);
        l_applyManager.validateAdjustPoint(l_request.adjustPoint, l_lngUsePossiblePoint);    

        //1.8 createResponse( )
        WEB3AdminPointAdjustConfirmResponse l_response = (WEB3AdminPointAdjustConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.9 プロパティセット
        l_response.beforeAvailablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        
        long l_lngAdjustPoint = 0;
        if (l_request.adjustPoint != null || WEB3StringTypeUtility.isNumber(l_request.adjustPoint))
        {
            l_lngAdjustPoint = Long.parseLong(l_request.adjustPoint);
        }

        l_response.afterAvailablePoint = WEB3StringTypeUtility.formatNumber(
            l_lngAdjustPoint + l_lngUsePossiblePoint);
        log.debug("調整後のポイント = " + l_response.afterAvailablePoint);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit調整)<BR>
     * 調整データの登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント管理）submit調整」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント管理）submit調整」): <BR>
     *         1.6 get顧客(String, String, String)<BR>
     *          顧客オブジェクトが取得できなかった場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント管理）submit調整」): <BR>
     *         1.7 get利用可能ポイント(String, String, String)<BR>
     *          リクエストデータ.確認時調整前利用可能ポイント != <BR>
     *          get利用可能ポイント()の戻り値 の場合、<BR>
     *          例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01731<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointAdjustCompleteResponse
     * @@roseuid 4194555601F4
     */
    protected WEB3AdminPointAdjustCompleteResponse submitAdjust(WEB3AdminPointAdjustCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitAdjust(WEB3AdminPointAdjustCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, true);//WEB3BaseException
        
        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        //1.5 validate取引パスワード
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.6 get顧客(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get顧客(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
        
        //1.7 get利用可能ポイント(String, String, String)
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //QA:WEB3-POINT-A-CD-0006.xls
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        long l_lngUsePossiblePoint = 
            l_applyManager.getUsePossiblePoint(l_admin.getInstitutionCode(), l_request.branchCode, l_mainAccount.getAccountCode());//WEB3SystemLayerException
        log.debug("利用可能ポイント = " + l_lngUsePossiblePoint);
        
        if (l_request.beforeAvailablePoint == null || !WEB3StringTypeUtility.isNumber(l_request.beforeAvailablePoint))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01731,
                getClass().getName() + STR_METHOD_NAME);
        }
       
        long l_lngBeforeAvailablePoint = Long.parseLong(l_request.beforeAvailablePoint);
        log.debug("調整前のポイント = " + l_lngBeforeAvailablePoint);
        
        if (l_lngBeforeAvailablePoint != l_lngUsePossiblePoint)
        {
            String l_strMessage = "利用可能ポイントerror!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01731,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        //1.8 validate調整ポイント(String, long)
        l_applyManager.validateAdjustPoint(l_request.adjustPoint, l_lngUsePossiblePoint);
        
        //1.9 ポイント調整(String, String, String, long) QA:WEB3-POINT-A-CD-0006.xls
        int l_intAdjustPoint = 0;
        if (l_request.adjustPoint != null && WEB3StringTypeUtility.isNumber(l_request.adjustPoint))
        {
            l_intAdjustPoint = Integer.parseInt(l_request.adjustPoint);
        }

        WEB3PointAdjust l_applyAdjust = new WEB3PointAdjust(
            l_admin.getInstitutionCode(), 
            l_request.branchCode, 
            l_mainAccount.getAccountCode(), 
            l_intAdjustPoint);
            
        //1.10 saveNew調整(ポイント調整, 管理者)
        l_applyManager.saveNewAdjust(l_applyAdjust, l_admin);//WEB3SystemLayerException
        
        //1.11 createResponse( )
        WEB3AdminPointAdjustCompleteResponse l_response = (WEB3AdminPointAdjustCompleteResponse)l_request.createResponse();
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 照会画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント管理）get照会画面１，２」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「（ポイント管理）get照会画面１」): <BR>
     *         1.5 get顧客(String, String, String)<BR>
     *          顧客オブジェクトが取得できなかった場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointHistoryReferenceResponse
     * @@roseuid 4194558400BB
     */
    protected WEB3AdminPointHistoryReferenceResponse getReferenceScreen(WEB3AdminPointHistoryReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminPointHistoryReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_MANAGE_BY_CUSTOMER, false);//WEB3BaseException
        
        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.5 get顧客(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        { 
            if (l_accountManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_mainAccount = 
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCode, l_request.accountCode);//WEB3SystemLayerException
        }
        catch (WEB3BaseException l_e)
        {
            String l_strMessage = "get顧客(String, String, String)error! " 
                + "InstitutionCode = " + l_admin.getInstitutionCode() 
                + ",BranchCode = " + l_request.branchCode 
                + ",AccountCode = " + l_request.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage , 
                l_e);
        }                
        
        //1.6 get履歴表示期間(String)
        String[] l_strHistoryIndicationTerms = this.getHistoryIndicationTerm(l_admin.getInstitutionCode());
        
        //1.7 ArrayList( )
        ArrayList l_arrayListApply = new ArrayList();
        
        //1.8 取得した履歴表示期間の配列の要素毎にLoop処理
        int l_intCount = 0;
        if (l_strHistoryIndicationTerms != null)
        {
            l_intCount = l_strHistoryIndicationTerms.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            log.debug("履歴表示期間:" + l_strHistoryIndicationTerms[i]);
            
            //1.8.1 ポイント履歴明細( )
            WEB3AdminPointHistoryDetail l_historyDetail = new WEB3AdminPointHistoryDetail();
            
            //1.8.2 プロパティセット
            l_historyDetail.period = l_strHistoryIndicationTerms[i];
            
            List l_lisRecord = null;
            try
            {
                String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? and period = ?";
                //QA:WEB3-POINT-A-CD-0006.xls
                Object[] l_objBinds = new Object[]{
                    l_admin.getInstitutionCode(),
                    l_request.branchCode,
                    l_mainAccount.getAccountCode(),
                    l_strHistoryIndicationTerms[i]};
                
                l_lisRecord = Processors.getDefaultProcessor().doFindAllQuery(
                    PointTotalRow.TYPE,
                    l_strWhere,
                    l_objBinds);//DataFindException,DataQueryException,DataNetworkException
            }
            catch (DataFindException l_e)
            {
                log.error(STR_METHOD_NAME,l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }
            catch (DataQueryException l_e)
            {
                log.error(STR_METHOD_NAME,l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }
            catch (DataNetworkException l_e)
            {
                log.error(STR_METHOD_NAME,l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }
            if (l_lisRecord != null && l_lisRecord.size() > 1)
            {
                log.debug("テーブルに重複する該当データが存在します");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (l_lisRecord != null && l_lisRecord.size() > 0)
            {
                log.debug("ポイント合計テーブルのだータが存在するの場合");
                PointTotalRow l_row = (PointTotalRow)l_lisRecord.get(0);
                if (l_row == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //発生ポイント
                log.debug("合計獲得ポイント:" + l_row.getTotalGetPoint());
                if (!l_row.getTotalAdjustPointIsNull())
                {
                    int l_intTotalAdjustPoint = l_row.getTotalAdjustPoint();
                    log.debug("合計調整ポイント:" + l_intTotalAdjustPoint);
                    if (l_intTotalAdjustPoint > 0)
                    {
                        int l_intAccrualPoint = l_intTotalAdjustPoint;
                        if (!l_row.getTotalGetPointIsNull())
                        {
                            l_intAccrualPoint += l_row.getTotalGetPoint();
                        }
                        l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_intAccrualPoint);
                    }
                    else
                    {
                        if (!l_row.getTotalGetPointIsNull())
                        {
                            l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalGetPoint());
                        }
                        else
                        {
                            l_historyDetail.accrualPoint = "0";
                        }
                    }
                }
                else
                {
                    if (!l_row.getTotalGetPointIsNull())
                    {
                        l_historyDetail.accrualPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalGetPoint());
                    }
                    else
                    {
                        l_historyDetail.accrualPoint = "0";
                    }
                }
                log.debug("*************** 発生ポイント1:" + l_historyDetail.accrualPoint);
                
                //利用ポイント
                log.debug("引落確定申込ポイント:" + l_row.getWithdrawnApplyPoint());
                log.debug("引落確定調整ポイント:" + l_row.getWithdrawnAdjustPoint());
                int l_intUsedPoint = 0;
                if (!l_row.getWithdrawnAdjustPointIsNull())
                {
                    l_intUsedPoint += l_row.getWithdrawnAdjustPoint();
                }
                if (!l_row.getWithdrawnApplyPointIsNull())
                {
                    l_intUsedPoint += l_row.getWithdrawnApplyPoint();
                }
                l_historyDetail.usedPoint = WEB3StringTypeUtility.formatNumber(l_intUsedPoint);
                log.debug("*************** 利用ポイント1:" + l_historyDetail.usedPoint);
                
                //調整ポイント
                log.debug("合計調整ポイント:" + l_row.getTotalAdjustPoint());
                if (!l_row.getTotalAdjustPointIsNull())
                {
                    l_historyDetail.adjustPoint = WEB3StringTypeUtility.formatNumber(l_row.getTotalAdjustPoint());
                }
                else
                {
                    l_historyDetail.adjustPoint = "0";
                }
                log.debug("*************** 調整ポイント1:" + l_historyDetail.adjustPoint);
            }
            else
            {
                log.debug("ポイント合計テーブルのだータが存在しないの場合");
                log.debug("*************** 発生ポイント1:0");
                log.debug("*************** 利用ポイント1:0");
                log.debug("*************** 調整ポイント1:0");
                l_historyDetail.accrualPoint = "0";
                l_historyDetail.usedPoint = "0";
                l_historyDetail.adjustPoint = "0";
            }
            
            //1.8.3 add(arg0 : Object)
            l_arrayListApply.add(l_historyDetail);
        }
        
        //1.9 toArray
        WEB3AdminPointHistoryDetail[] l_historyDetails = new WEB3AdminPointHistoryDetail[l_arrayListApply.size()];
        l_arrayListApply.toArray(l_historyDetails);
        
        //below code is wroten by the シーケンス図「（ポイント管理）get照会画面２」
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 get未集計調整ポイント(String, String, String, String)
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strPreMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngNotTotalAdjustPoint_pre = l_applyManager.getNotTotalAdjustPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPreMonthDate);//WEB3BaseException
        log.debug("先月の未集計調整ポイント：" + l_lngNotTotalAdjustPoint_pre);
        
        //1.3 get未集計調整ポイント(String, String, String, String)
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strCurrMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngNotTotalAdjustPoint_curr = l_applyManager.getNotTotalAdjustPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strCurrMonthDate);//WEB3BaseException
        log.debug("今月の未集計調整ポイント：" + l_lngNotTotalAdjustPoint_curr);
        
        //１）今月と先月の年月を持つポイント履歴明細のindexを取得し、それぞれa、bとする。
        int l_intHistoryDetailIndex_curr = 0;
        int l_intHistoryDetailIndex_pre = 1;
        //2) 以下のとおりに、調整ポイントに加算する。WEB3StringTypeUtility.formatNumber
        if (l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint != null
            && !"".equals(l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint.trim()))
        {
            l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(
                    Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint) + l_lngNotTotalAdjustPoint_pre);
        }
        else
        {
            l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_pre);
        }
        log.debug("******** 先月の調整ポイント：" + l_historyDetails[l_intHistoryDetailIndex_pre].adjustPoint);
        
        if (l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint != null
            && !"".equals(l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint.trim()))
        {
            l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(
                    Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint) + l_lngNotTotalAdjustPoint_curr);
        }
        else
        {
            l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint = 
                WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_curr);
        }
        log.debug("******** 今月の調整ポイント：" + l_historyDetails[l_intHistoryDetailIndex_curr].adjustPoint);
        
        //３）今月の未集計調整ポイント>0の場合、今月の発生ポイントに加算する。
        if (l_lngNotTotalAdjustPoint_curr > 0)
        {
            log.debug("今月の未集計調整ポイント>0の場合");
            if (l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint != null
                && !"".equals(l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint.trim()))
            {
                l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint) + l_lngNotTotalAdjustPoint_curr);
            }
            else
            {
                l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_curr);
            }
        }
        log.debug("******** 今月の発生ポイント:" + l_historyDetails[l_intHistoryDetailIndex_curr].accrualPoint);
        
        //４）先月の未集計調整ポイント>0の場合、先月の発生ポイントに加算する。
        if (l_lngNotTotalAdjustPoint_pre > 0)
        {
            log.debug("先月の未集計調整ポイント>0の場合");
            if (l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint != null
                && !"".equals(l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint.trim()))
            {
                l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint) + l_lngNotTotalAdjustPoint_pre);
            }
            else
            {
                l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngNotTotalAdjustPoint_pre);
            }
        }
        log.debug("******** 先月の発生ポイント:" + l_historyDetails[l_intHistoryDetailIndex_pre].accrualPoint);
        
        //1.4 get申込ポイント(String, String, String, String)
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -2);
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        String l_strPre2MonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_pre2 = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPre2MonthDate);//WEB3BaseException
        log.debug("先ヶ月の申込ポイント:" + l_lngApplyPoint_pre2);
        
        //1.5 get申込ポイント(String, String, String, String)
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        l_strPreMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_pre = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strPreMonthDate);//WEB3BaseException
        log.debug("先月の申込ポイント:" + l_lngApplyPoint_pre);
        
        //1.6 get申込ポイント(String, String, String, String)
        l_cal.setTime(l_tsSystemTime);            
        l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        l_strCurrMonthDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");        
        long l_lngApplyPoint_curr = l_applyManager.getApplyPoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode(),
            l_strCurrMonthDate);//WEB3BaseException
        log.debug("今月の申込ポイント:" + l_lngApplyPoint_curr);
        
        //1.7 get有効期限月(String, String)
        String l_strValidTermMon = l_applyManager.getValidTermMon(l_admin.getInstitutionCode(), l_strCurrMonthDate);//WEB3BaseException
        log.debug("有効期限月:" + l_strValidTermMon);
        
        //１）get有効期限月()の戻り値と一致する年月を持つポイント履歴明細のindexを取得し、x とする。
        int l_intValidTermMonIndex = -1;
        l_intCount = 0;
        if (l_strHistoryIndicationTerms != null)
        {
            l_intCount = l_strHistoryIndicationTerms.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            if (l_strHistoryIndicationTerms[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (l_strHistoryIndicationTerms[i].equals(l_strValidTermMon))
            {
                l_intValidTermMonIndex = i;
                break;
            }
        }
        if (l_intValidTermMonIndex == -1)
        {
            String l_strMessage = "有効期限月error! " + l_strValidTermMon;
            log.debug(l_strMessage);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //２）各条件に従い、以下の処理を行う。
        //２－１）先々月の申込ポイント>0の場合
        if (l_lngApplyPoint_pre2 > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 2].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint) + l_lngApplyPoint_pre2);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_pre2);
            }
        }
        //２－２）先月の申込ポイント>0の場合
        if (l_lngApplyPoint_pre > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) + l_lngApplyPoint_pre);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_pre);
            }
        }
        //２－３）先月の未集計調整ポイント<0の場合
        if (l_lngNotTotalAdjustPoint_pre < 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) - l_lngNotTotalAdjustPoint_pre);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(0 - l_lngNotTotalAdjustPoint_pre);
            }
        }
        //２－４）今月の申込ポイント>0の場合
        if (l_lngApplyPoint_curr > 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) + l_lngApplyPoint_curr);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngApplyPoint_curr);
            }
        }
        //２－５）今月の未集計調整ポイント<0の場合
        if (l_lngNotTotalAdjustPoint_curr < 0)
        {
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) - l_lngNotTotalAdjustPoint_curr);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(0 - l_lngNotTotalAdjustPoint_curr);
            }
        }
        //(*4) 以下の処理を行う。
        long l_lngTempA = 0;
        long l_lngTempB = 0;
        long l_lngTempC = 0;
        //１）ポイント履歴明細[x+2].発生ポイント < ポイント履歴明細[x+2].利用ポイント の場合
        long l_lngAccrualPoint = 0;
        long l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex + 2].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 2].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            l_lngTempA = l_lngUsedPoint - l_lngAccrualPoint;
            l_historyDetails[l_intValidTermMonIndex + 2].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
            if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint) + l_lngTempA);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngTempA);
            }
        }
        //２）ポイント履歴明細[x+1].発生ポイント < ポイント履歴明細[x+1].利用ポイント の場合
        l_lngAccrualPoint = 0;
        l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex + 1].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex + 1].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            l_lngTempB = l_lngUsedPoint - l_lngAccrualPoint;
            l_historyDetails[l_intValidTermMonIndex + 1].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
            if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
                && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(
                        Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint) + l_lngTempB);
            }
            else
            {
                l_historyDetails[l_intValidTermMonIndex].usedPoint = 
                    WEB3StringTypeUtility.formatNumber(l_lngTempB);
            }
        }
        //３）ポイント履歴明細[x].発生ポイント < ポイント履歴明細[x].利用ポイント の場合
        l_lngAccrualPoint = 0;
        l_lngUsedPoint = 0;
        if (l_historyDetails[l_intValidTermMonIndex].accrualPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex].accrualPoint.trim()))
        {
            l_lngAccrualPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex].accrualPoint);
        }
        if (l_historyDetails[l_intValidTermMonIndex].usedPoint != null
            && !"".equals(l_historyDetails[l_intValidTermMonIndex].usedPoint.trim()))
        {
            l_lngUsedPoint = Long.parseLong(l_historyDetails[l_intValidTermMonIndex].usedPoint);
        }
        if(l_lngAccrualPoint < l_lngUsedPoint)
        {
            for (int i = l_intValidTermMonIndex; i >= 0; i--)
            {
                l_lngAccrualPoint = 0;
                l_lngUsedPoint = 0;
                if (l_historyDetails[i].accrualPoint != null
                    && !"".equals(l_historyDetails[i].accrualPoint.trim()))
                {
                    l_lngAccrualPoint = Long.parseLong(l_historyDetails[i].accrualPoint);
                }
                if (l_historyDetails[i].usedPoint != null
                    && !"".equals(l_historyDetails[i].usedPoint.trim()))
                {
                    l_lngUsedPoint = Long.parseLong(l_historyDetails[i].usedPoint);
                }
                if(l_lngAccrualPoint >= l_lngUsedPoint)
                {
                    break;
                }
                if (i > 0)
                {
                    l_lngTempC = l_lngUsedPoint - l_lngAccrualPoint;
                    l_historyDetails[i].usedPoint = WEB3StringTypeUtility.formatNumber(l_lngAccrualPoint);
                    if (l_historyDetails[i - 1].usedPoint != null
                        && !"".equals(l_historyDetails[i - 1].usedPoint.trim()))
                    {
                        l_historyDetails[i - 1].usedPoint = 
                            WEB3StringTypeUtility.formatNumber(
                                Long.parseLong(l_historyDetails[i - 1].usedPoint) + l_lngTempC);
                    }
                    else
                    {
                        l_historyDetails[i - 1].usedPoint = 
                            WEB3StringTypeUtility.formatNumber(l_lngTempC);
                    }
                }
            }
        }
                
        //1.8 get利用可能ポイント(String, String, String)
        long l_lngUsePossiblePoint =  l_applyManager.getUsePossiblePoint(
            l_admin.getInstitutionCode(),
            l_request.branchCode,
            l_mainAccount.getAccountCode());//WEB3BaseException
        
        //1.9 createResponse( )
        WEB3AdminPointHistoryReferenceResponse l_response = (WEB3AdminPointHistoryReferenceResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.10 プロパティセット
        l_response.availablePoint = WEB3StringTypeUtility.formatNumber(l_lngUsePossiblePoint);
        l_response.pointHistoryList = l_historyDetails;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get履歴表示期間)<BR>
     * 履歴表示期間となる年月（YYYYMM）の配列を取得する。<BR>
     * <BR>
     * １）ポイント有効期限テーブルから、履歴表示期間を取得する。<BR>
     * <BR>
     *    [取得条件]<BR>
     *    証券会社コード = 引数.証券会社コード<BR>
     * <BR>
     * ２）現在時刻から取得した年月（YYYYMM）を元に、１）で取得<BR>
     * した履歴表示期間の間の年月（YYYYMM）を算出する。<BR>
     * <BR>
     *    ※現在の年月から（履歴表示期間-1）ヶ月前までの年月を算出する。<BR>
     * <BR>
     * ３） ２）の算出結果を降順の配列にして返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@return String[]
     * @@roseuid 41AFC64303D0
     */
    protected String[] getHistoryIndicationTerm(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getHistoryIndicationTerm(String )";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // １）ポイント有効期限テーブルから、履歴表示期間を取得する。
            PointTermRow l_pointTermRow = PointTermDao.findRowByPk(l_strInstitutionCode);
            if (l_pointTermRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // ２）現在時刻から取得した年月（YYYYMM）を元に、１）で取得
            String l_strDisplayPeriod = l_pointTermRow.getDisplayPeriod();
            if (l_strDisplayPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            int l_intDisplayPeriod = Integer.parseInt(l_strDisplayPeriod);
            String[] l_strHistoryIndicationTerms = new String[l_intDisplayPeriod];

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Calendar l_cal = new GregorianCalendar();            
            l_cal.setTime(l_tsSystemTime);            
            for (int i = 0; i < l_intDisplayPeriod; i++)
            {
                Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
                l_strHistoryIndicationTerms[i] = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");
                l_cal.add(Calendar.MONTH, -1);            
            }
            
            // ３） ２）の算出結果を降順の配列にして返却する。
            log.exiting(STR_METHOD_NAME);
            return l_strHistoryIndicationTerms;
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }
}
@
