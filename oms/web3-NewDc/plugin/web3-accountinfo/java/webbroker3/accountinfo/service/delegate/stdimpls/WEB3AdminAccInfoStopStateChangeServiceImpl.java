head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報停止状況変更サービス実装クラス(WEB3AdminAccInfoStopStateChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 李海波 (中訊) 新規作成
                   2006/01/16 呉艶飛(北京中訊) 仕様変更・モデル081
*/

package webbroker3.accountinfo.service.delegate.stdimpls;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountLockDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (管理者お客様情報停止状況変更サービスImpl)<BR>
 * 管理者お客様情報停止状況変更サービス実装クラス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoStopStateChangeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeServiceImpl.class);
        
    /**
     * @@roseuid 418F3A0A0138
     */
    public WEB3AdminAccInfoStopStateChangeServiceImpl() 
    {
     
    }
    
    /**
     * 停止状況変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報停止状況変更<BR>
     * 入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報停止状況変更<BR>
     * 確認リクエストの場合 <BR>
     * 　@－validate変更()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報停止状況変更<BR>
     * 完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41634C8C0269
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報停止状況変更入力リクエストの場合
        if(l_request instanceof WEB3AdminAccInfoStopStateChangeInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoStopStateChangeInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報停止状況変更確認リクエストの場合
        else if(l_request instanceof WEB3AdminAccInfoStopStateChangeConfirmRequest)
        {
            l_response = this.validateChange((WEB3AdminAccInfoStopStateChangeConfirmRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報停止状況変更完了リクエストの場合
        else if(l_request instanceof WEB3AdminAccInfoStopStateChangeCompleteRequest)
        {
            l_response = this.submitChange((WEB3AdminAccInfoStopStateChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get入力画面)<BR>
     * 停止状況変更入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（停止状況変更）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B32C2016B
     */
    protected WEB3AdminAccInfoStopStateChangeInputResponse 
        getInputScreen(WEB3AdminAccInfoStopStateChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoStopStateChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_gentradeMainAccount.getDataSourceObject();
        
        //6) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
                
        //7) create停止情報(顧客 : 顧客)
        WEB3AccInfoAccountBaseInfoCreatedService l_accInfoAccountBaseInfoCreatedService =
            (WEB3AccInfoAccountBaseInfoCreatedService) Services.getService(
                WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoStopInfo l_accInfoStopInfo = l_accInfoAccountBaseInfoCreatedService.createStopInfo(l_gentradeMainAccount);
        
        //8) 管理者お客様情報停止状況変更入力レスポンス(WEB3GenRequest)
        WEB3AdminAccInfoStopStateChangeInputResponse l_response =
            (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
        l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        l_response.accountName = l_mainAccountRow.getFamilyName();
        l_response.traderCode = l_mainAccountRow.getSonarTraderCode();
        l_response.stopInfo = l_accInfoStopInfo;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更)<BR>
     * 停止状況変更確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（停止状況変更）validate変更」参照。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse
     * @@roseuid 41634C8C026B
     */
    protected WEB3AdminAccInfoStopStateChangeConfirmResponse 
        validateChange(WEB3AdminAccInfoStopStateChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoStopStateChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //6) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //7) validateChange停止情報(顧客, 停止情報)
        validateChangeStopInfo(l_gentradeMainAccount, l_request.stopInfo);
        
        //8) createResponse( )
        WEB3AdminAccInfoStopStateChangeConfirmResponse l_response = 
            (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * 停止状況変更完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（停止状況変更）submit変更」参照。 <BR>
     * @@param l_request - 管理者お客様情報停止状況変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse
     * @@roseuid 41634C8C026D
     */
    protected WEB3AdminAccInfoStopStateChangeCompleteResponse 
        submitChange(WEB3AdminAccInfoStopStateChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoStopStateChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //5) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //6) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            
        //7) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //8) validateChange停止情報(顧客, 停止情報)
        WEB3AccInfoStopInfo l_stopInfo = l_request.stopInfo;
        validateChangeStopInfo(l_gentradeMainAccount, l_stopInfo);
        
        //9部店オブジェクトを取得する。        
        WEB3GentradeBranch l_branch = null;
        try
        {
            Institution l_institution = 
                l_gentradeAccountManager.getInstitution(l_strInstitutionCode);
            l_branch = 
                (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(l_institution, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        long l_lngBranchId = 0;
        if (l_branch != null)
        {
            l_lngBranchId = l_branch.getBranchId();
        }
        
        //10リアル連携を行うかどうか判定する。 
        boolean l_blnIsRealCooperation = this.IsRealCooperation(l_lngBranchId);
        
        //11メッセージ リアル連携を行う場合（isリアル連携()==true）
        if (l_blnIsRealCooperation)
        {
            //12変更があった項目のみのロック客Y客登録解除オブジェクトを生成する。
            WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.createChangeLockAccYAccRegisterRelease(l_gentradeMainAccount, l_stopInfo);
            
            if (l_release != null)
            {
                HostLockRegistParams l_registParams = (HostLockRegistParams)l_release.getDataSourceObject();
                
                //13createChangeロック客Y客登録解除（）の戻り値.属性 != null の場合、Y客登録解除を新規作成する。
                if (l_registParams.getAttribute() != null)
                {
                    //13.1ロック客Y客登録解除テーブルを更新する。(DB insert)  
                    l_release.saveNewYAccRegisterRelease(l_gentradeMainAccount, l_release);
                }
                
                //14createChangeロック客Y客登録解除（）の戻り値.管理解除区分、支店ロック、
                //注文認可の項目のいずれかが != null の場合、ロック客登録を新規作成する。
                if (l_registParams.getMngLockCancelDiv() != null
                        || l_registParams.getBranchLock() != null
                        || l_registParams.getOrderPermission() != null)
                {
                    //14.1ロック客Y客登録解除テーブルを更新する。(DB insert)  
                    l_release.saveNewLockAccRegisterRelease(l_gentradeMainAccount, l_release);
                }
            }
        }

        //15) getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_gentradeMainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
        QueryProcessor l_queryProcesser = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            //Ｙ客区分
            l_mainAccountParams.setYellowCustomer(l_stopInfo.yellowAccountDiv);
            //管理ロック
            l_mainAccountParams.setMngLockFlag(l_stopInfo.mngLockDiv);
            //管理ロック解除開始日
            l_mainAccountParams.setMngLockOffStartDate(l_stopInfo.mngLockCancelStartDate);
            //管理ロック解除終了日
            l_mainAccountParams.setMngLockOffEndDate(l_stopInfo.mngLockCancelEndDate);
            
//            BooleanEnum l_booleanEnum = null;
//            if (l_stopInfo.mngExpenseLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //管理ロック理由フラグ（立替金）
//            l_mainAccountParams.setMngLockFlagAdvance(l_booleanEnum);
//            
//            if(l_stopInfo.mngDepositLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //管理ロック理由フラグ（保証金未入）
//            l_mainAccountParams.setMngLockFlagUnpayMargin(l_booleanEnum);
//            
//            if (l_stopInfo.mngCollateralLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //管理ロック理由フラグ（適格担保不足）
//            l_mainAccountParams.setMngLockFlagShortSecurity(l_booleanEnum);
//            
//            if (l_stopInfo.mngReceiptLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //管理ロック理由フラグ（預り証長期未差替）
//            l_mainAccountParams.setMngLockFlagUnsubstitDepo(l_booleanEnum);
            //支店ロック
            l_mainAccountParams.setBranchLock(l_stopInfo.branchLockDiv);
            //注文認可
            l_mainAccountParams.setOrderPermission(l_stopInfo.orderPermitDiv);
            //停止状況更新者コード
            l_mainAccountParams.setEnableOrderLastUpdater(l_administrator.getAdministratorCode());
            
            Timestamp l_tsSystemTime = l_finApp.getTradingSystem().getSystemTimestamp();
            //停止状況更新日時
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(l_tsSystemTime);
            //更新日時
            l_mainAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);
            //停止状況登録理由 = 停止情報.停止状況登録理由
            l_mainAccountParams.setLockRegistrationReason(l_stopInfo.stopStateRegistReason);
            
            l_queryProcesser.doUpdateQuery(l_mainAccountParams);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        //createResponse( )
        WEB3AdminAccInfoStopStateChangeCompleteResponse l_response = 
            (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validateChange停止情報)<BR>
     * 変更項目のチェックを行う。 <BR>
     * <BR>
     * １）　@管理ロック解除開始日のチェック<BR>
     * 　@１－１）　@期間内であるかのチェック<BR>
     * 　@　@処理日(*1) <= 停止情報.管理ロック解除開始日 <= <BR>
     * 処理日の1ヶ月後(*2)であるかをチェックする。<BR>
     * 　@期間内に当てはまらない場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01171<BR>
     * <BR>
     * 　@１－２）　@営業日チェック<BR>
     * 　@　@管理ロック解除開始日が営業日でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01172<BR>
     * <BR>
     * ２）　@管理ロック解除終了日のチェック<BR>
     * 　@２－１）　@期間内であるかのチェック<BR>
     * 　@　@処理日(*1) <= 停止情報.管理ロック解除終了日 <= <BR>
     * 処理日の1ヶ月後(*2)であるかをチェックする。<BR>
     * 　@期間内に当てはまらない場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01173<BR>     
     * <BR>
     * 　@２－２）　@営業日チェック<BR>
     * 　@　@管理ロック解除終了日が営業日でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01174<BR>
     * <BR>
     * 　@(*1)　@処理日<BR>
     * 　@TradingSystem.getBizDate()<BR>
     * <BR>
     * 　@(*2)　@処理日の1ヵ月後<BR>
     * 　@TradingSystem.getBizDate()の翌月日付の前日。<BR>
     * 　@※ 処理日=2004/11/23の場合、2004/12/22<BR>
     * <BR>
     * 　@但し、翌月日付の前日が暦上日にならない場合は、暦上日に補正する。<BR>
     * 　@※ 処理日=2005/1/31の場合、2005/2/28に補正する。<BR>
     * <BR>
     * ３）　@管理ロック解除開始日／終了日の関連チェック<BR>
     * 　@管理ロック解除開始日，管理ロック解除終了日のどちらも月末日である場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01175<BR>
     * <BR>
     * ４）　@変更項目既存値チェック<BR>
     * 　@停止情報の以下の項目について、顧客行（顧客.getDataSourceObject()）の<BR>
     * 対応項目の値と比較する。<BR>
     * 　@すべて同じ値であれば、変更項目がないと判定し例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01176<BR>     
     * <BR>
     * 　@Ｙ客区分<BR>
     * 　@管理ロック区分　@※顧客行.管理ロック<BR>
     * 　@管理ロック理由フラグ（立替金）<BR>
     * 　@管理ロック理由フラグ（保証金未入）<BR>
     * 　@管理ロック理由フラグ（適格担保不足）<BR>
     * 　@管理ロック理由フラグ（預り証長期未差替）<BR>
     * 　@管理ロック解除開始日<BR>
     * 　@管理ロック解除終了日<BR>
     * 　@支店ロック区分　@※顧客行.支店ロック<BR>
     * 　@注文認可区分　@※顧客行.注文認可<BR>
     * <BR>
     * 　@※ 管理ロック理由フラグ各項目について。<BR>
     * 　@　@　@BooleanEnum.TRUEの場合はtrue，BooleanEnum.FALSEの場合は<BR>
     * falseであれば同じ値と判断すること。<BR>
     * 
     * ５）停止状況登録理由変更可能チェック <BR>
     * 　@ 停止情報.管理ロック解除開始日 == 顧客行.管理ロック解除開始日 AND <BR>
     *    停止情報.管理ロック解除終了日 == 顧客行.管理ロック解除終了日 AND <BR>
     *    停止情報.支店ロック区分 == 顧客行.支店ロック AND <BR>
     *    停止情報.注文認可区分 == 顧客行.注文認可 AND <BR>
     * 　@ 停止情報.停止状況登録理由 != 顧客行.停止状況登録理由 の場合、例外（停止状況登録理由変更不可エラー。）をスローする。<BR> 
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02237
     * <BR>
     * ６）　@ロック客Y客登録解除キュー未送信データのチェック <BR>
     * 　@６－１）　@停止情報.Y客区分 != 顧客行.Y客区分 の場合、以下の処理を実施する。 <BR>
     * 　@　@　@６－１－１）ロック客Y客登録解除.getロック客Y客登録解除（）をコールし、ロック客Y客登録解除オブジェクトを取得する。<BR> 
     * 　@　@　@[getロック客Y客登録解除（）に指定する引数] <BR>
     * 　@　@　@　@顧客 = 引数.顧客 <BR>
     * 　@　@　@　@データコード　@= GI847(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * 　@　@　@６－１－２）取得したロック客Y客登録解除オブジェクト.処理区分 == 0:未処理の場合、<BR>
     *          例外（SONAR未送信データがあるため変更不可エラー。）をスローする。<BR> 
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02238 
     * <BR>
     * 　@６－２）　@停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 OR <BR>
     *              停止情報.管理ロック解除終了日 != 顧客行.管理ロック解除終了日 OR <BR>
     *              停止情報.支店ロック区分 != 顧客行.支店ロック OR <BR>
     *              停止情報.注文認可区分 != 顧客行.注文認可 の場合、以下の処理を実施する。 <BR>
     * 　@　@　@６－２ー１）ロック客Y客登録解除.getロック客Y客登録解除（）をコールし、ロック客Y客登録解除オブジェクトを取得する。 <BR>
     * 　@　@　@[getロック客Y客登録解除（）に指定する引数] <BR>
     * 　@　@　@　@顧客 = 引数.顧客 <BR>
     * 　@　@　@　@データコード　@= GI846(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * 　@　@　@６－２ー２）取得したロック客Y客登録解除オブジェクト.処理区分 == 0:未処理の場合、<BR>
     *          例外（SONAR未送信データがあるため変更不可エラー。）をスローする。 <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02238  
     * @@param l_mainAccount - 顧客オブジェクト<BR>
     * @@param l_stopInfo - 停止情報メッセージオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41635BCB00B4
     */
    protected void validateChangeStopInfo(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeStopInfo(WEB3GentradeMainAccount, WEB3AccInfoStopInfo)";
        log.entering(STR_METHOD_NAME);
        
        /*
        * １）　@管理ロック解除開始日のチェック<BR>
        * 　@１－１）　@期間内であるかのチェック<BR>
        * 　@　@処理日(*1) <= 停止情報.管理ロック解除開始日 <= <BR>
        * 処理日の1ヶ月後(*2)であるかをチェックする。<BR>
        * 　@期間内に当てはまらない場合は例外をスローする。<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01171<BR>
        */
        //処理日
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Date l_datBizDate = l_finApp.getTradingSystem().getBizDate();
        //停止情報.管理ロック解除開始日
        Date l_datMngLockCancelStartDate = l_stopInfo.mngLockCancelStartDate;
        //停止情報.管理ロック解除終了日
        Date l_datMngLockCancelEndDate = l_stopInfo.mngLockCancelEndDate;
        if(l_datMngLockCancelStartDate != null && l_datMngLockCancelEndDate != null)
        {
            //処理日の1ヶ月後
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datBizDate);
            int l_intDay = l_calendar.get(Calendar.DATE);
            l_calendar.add(Calendar.MONTH, 1);
            Date l_dateNextMonthDay;
            if (l_intDay > l_calendar.getActualMaximum(Calendar.DATE))
            {
                l_dateNextMonthDay = l_calendar.getTime();
            }
            else
            {
                l_calendar.add(Calendar.DATE, -1);
                l_dateNextMonthDay = l_calendar.getTime();
            }
        
            int l_intCompare1 = WEB3DateUtility.compareToDay(l_datMngLockCancelStartDate, l_datBizDate);
            int l_intCompare2 = WEB3DateUtility.compareToDay(l_datMngLockCancelStartDate, l_dateNextMonthDay);
            if (l_intCompare1 < 0 || l_intCompare2 > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01171, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
            /*
            * 　@１－２）　@営業日チェック<BR>
            * 　@　@管理ロック解除開始日が営業日でない場合、例外をスローする。<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01172<BR>
            */
            String l_strBizDateType1 = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datMngLockCancelStartDate.getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType1))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01172, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
           /* 
            * ２）　@管理ロック解除終了日のチェック<BR>
            * 　@２－１）　@期間内であるかのチェック<BR>
            * 　@　@処理日(*1) <= 停止情報.管理ロック解除終了日 <= <BR>
            * 処理日の1ヶ月後(*2)であるかをチェックする。<BR>
            * 　@期間内に当てはまらない場合は例外をスローする。<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01173<BR>
            */
        

            int l_intCompare3 = WEB3DateUtility.compareToDay(l_datMngLockCancelEndDate, l_datBizDate);
            int l_intCompare4 = WEB3DateUtility.compareToDay(l_datMngLockCancelEndDate, l_dateNextMonthDay);
            if (l_intCompare3 < 0 || l_intCompare4 > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01173, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
       
           /* 
            * 　@２－２）　@営業日チェック<BR>
            * 　@　@管理ロック解除終了日が営業日でない場合、例外をスローする。<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01174<BR>
            */
            String l_strBizDateType2 = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datMngLockCancelEndDate.getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01174, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
           /* 
            * ３）　@管理ロック解除開始日／終了日の関連チェック<BR>
            * 　@管理ロック解除開始日，管理ロック解除終了日のどちらも月末日である場合、<BR>
            * 例外をスローする。<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01175<BR>
            */
            l_calendar.setTime(l_datMngLockCancelStartDate);
            int l_intMaxStartDate = l_calendar.getActualMaximum(Calendar.DATE);
            int l_intStartDate = l_calendar.get(Calendar.DATE);
            l_calendar.setTime(l_datMngLockCancelEndDate);
            int l_intMaxEndDate = l_calendar.getActualMaximum(Calendar.DATE);
            int l_intEndDate = l_calendar.get(Calendar.DATE);
        
            if (l_intMaxStartDate == l_intStartDate && l_intMaxEndDate == l_intEndDate)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01175, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        }

        
       /* 
        * ４）　@変更項目既存値チェック<BR>
        * 　@停止情報の以下の項目について、顧客行（顧客.getDataSourceObject()）の<BR>
        * 対応項目の値と比較する。<BR>
        * 　@すべて同じ値であれば、変更項目がないと判定し例外をスローする。<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01176<BR>
        * <BR>
        * 　@Ｙ客区分<BR>
        * 　@管理ロック区分　@※顧客行.管理ロック<BR>
        * 　@管理ロック理由フラグ（立替金）<BR>
        * 　@管理ロック理由フラグ（保証金未入）<BR>
        * 　@管理ロック理由フラグ（適格担保不足）<BR>
        * 　@管理ロック理由フラグ（預り証長期未差替）<BR>
        * 　@管理ロック解除開始日<BR>
        * 　@管理ロック解除終了日<BR>
        * 　@支店ロック区分　@※顧客行.支店ロック<BR>
        * 　@注文認可区分　@※顧客行.注文認可<BR>
        * <BR>
        * 　@※ 管理ロック理由フラグ各項目について。<BR>
        * 　@　@　@BooleanEnum.TRUEの場合はtrue，BooleanEnum.FALSEの場合は<BR>
        * falseであれば同じ値と判断すること。<BR>
        */
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //Ｙ客区分
        String l_strYellowAccount = l_stopInfo.yellowAccountDiv;
        //管理ロック区分
        String l_strMngLockDiv = l_stopInfo.mngLockDiv;
        //管理ロック理由フラグ（立替金）
        boolean l_blnMngExpenseLockReasonFlag = l_stopInfo.mngExpenseLockReasonFlag;
        //管理ロック理由フラグ（保証金未入）
        boolean l_blnMngDepositLockReasonFlag = l_stopInfo.mngDepositLockReasonFlag;
        //管理ロック理由フラグ（適格担保不足）
        boolean l_blnMngCollateralLockReasonFlag = l_stopInfo.mngCollateralLockReasonFlag;
        //管理ロック理由フラグ（預り証長期未差替）
        boolean l_blnMngReceiptLockReasonFlag = l_stopInfo.mngReceiptLockReasonFlag;
        //管理ロック解除開始日
        Date l_datMngLockCancelStart = l_stopInfo.mngLockCancelStartDate;
        //管理ロック解除終了日
        Date l_datMngLockCancelEnd = l_stopInfo.mngLockCancelEndDate;
        //支店ロック区分
        String l_strBranchLock = l_stopInfo.branchLockDiv;
        //注文認可区分
        String l_strOrderPermit = l_stopInfo.orderPermitDiv;
        if (l_strYellowAccount.equals(l_mainAccountRow.getYellowCustomer()) &&
            l_strMngLockDiv.equals(l_mainAccountRow.getMngLockFlag()) &&
            l_blnMngExpenseLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagAdvance()) &&
            l_blnMngDepositLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagUnpayMargin()) &&
            l_blnMngCollateralLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagShortSecurity()) &&
            l_blnMngReceiptLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagUnsubstitDepo()) &&
            WEB3DateUtility.compareToDay(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) == 0 &&
            WEB3DateUtility.compareToDay(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) == 0 &&
            l_strBranchLock.equals(l_mainAccountRow.getBranchLock()) && 
            l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission())
            )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01176, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        
        /*
         * ５）停止状況登録理由変更可能チェック 
         * 　@ 停止情報.管理ロック解除開始日 == 顧客行.管理ロック解除開始日 AND 
         *    停止情報.管理ロック解除終了日 == 顧客行.管理ロック解除終了日 AND 
         *    停止情報.支店ロック区分 == 顧客行.支店ロック AND 
         *    停止情報.注文認可区分 == 顧客行.注文認可 AND 
         * 　@ 停止情報.停止状況登録理由 != 顧客行.停止状況登録理由 の場合、例外（停止状況登録理由変更不可エラー。）をスローする。
         */
        String l_checkStopStateRestReason = null;
        if (l_stopInfo.stopStateRegistReason != null && !"".equals(l_stopInfo.stopStateRegistReason))
        {
            l_checkStopStateRestReason = l_stopInfo.stopStateRegistReason;
        }
        if (WEB3DateUtility.compare(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) == 0
                && WEB3DateUtility.compare(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) == 0
                && l_strBranchLock.equals(l_mainAccountRow.getBranchLock())
                && l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission())
                && !WEB3Toolkit.isEquals(l_checkStopStateRestReason,l_mainAccountRow.getLockRegistrationReason()))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02237, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        
        /*
         * ６）　@ロック客Y客登録解除キュー未送信データのチェック 
         * 　@６－１）　@停止情報.Y客区分 != 顧客行.Y客区分 の場合、以下の処理を実施する。 
         * 　@　@　@６－１－１）ロック客Y客登録解除.getロック客Y客登録解除（）をコールし、ロック客Y客登録解除オブジェクトを取得する。
         * 　@　@　@[getロック客Y客登録解除（）に指定する引数] 
         * 　@　@　@　@顧客 = 引数.顧客 
         * 　@　@　@　@データコード　@= GI847(WEB3HostRequestCodeDef) 
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            WEB3AccInfoLockAccYAccRegisterRelease l_reqisterRelease = 
                WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(
                    l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
            
            /*
             * ６－１－２）取得したロック客Y客登録解除オブジェクト.処理区分 == 0:未処理の場合、
             *     例外（SONAR未送信データがあるため変更不可エラー。）をスローする。
             */
            if (l_reqisterRelease != null)
            {
                if (WEB3StatusDef.NOT_DEAL.equals(l_reqisterRelease.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02238, 
                        this.getClass().getName() + STR_METHOD_NAME); 
                } 
            }

        }
        
        /*
         * ６－２）　@停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 OR 
         *      停止情報.管理ロック解除終了日 != 顧客行.管理ロック解除終了日 OR 
         *      停止情報.支店ロック区分 != 顧客行.支店ロック OR 
         *      停止情報.注文認可区分 != 顧客行.注文認可 の場合、以下の処理を実施する。 
         */
        if (WEB3DateUtility.compare(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) != 0
                || WEB3DateUtility.compare(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) != 0
                || !l_strBranchLock.equals(l_mainAccountRow.getBranchLock())
                || !l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission()))
        {
            /*
             * ６－２ー１）ロック客Y客登録解除.getロック客Y客登録解除（）をコールし、ロック客Y客登録解除オブジェクトを取得する。 
             * 　@　@　@[getロック客Y客登録解除（）に指定する引数] 
             * 　@　@　@　@顧客 = 引数.顧客 
             * 　@　@　@　@データコード　@= GI846(WEB3HostRequestCodeDef) 
             */
            WEB3AccInfoLockAccYAccRegisterRelease l_reqisterRelease = 
                WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(
                    l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
            
            /*
             * ６－２ー２）取得したロック客Y客登録解除オブジェクト.処理区分 == 0:未処理の場合、
             *      例外（SONAR未送信データがあるため変更不可エラー。）をスローする。 
             */
            if (l_reqisterRelease != null)
            {
                if (WEB3StatusDef.NOT_DEAL.equals(l_reqisterRelease.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02238, 
                        this.getClass().getName() + STR_METHOD_NAME); 
                } 
            }

        }
        
        
    }
    
    /**
     * 顧客ロック情報のリアル連携を行うかどうかを判別する。 <BR>
     * <BR>
     * [戻り値] <BR>
     * true： リアル連携を行う <BR>
     * false： リアル連携を行わない <BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 <BR>
     * <BR>
     * [条件] <BR>
     * 部店ID = 引き数.部店ID <BR>
     * プリファ@レンス名 = "account.lock.real.cooperation" <BR>
     * プリファ@レンス名の連番 = 1 <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”顧客ロック情報のリアル連携を行う。” の場合、true を返却する。 <BR>
     * <BR>
     * ３）それ以外の場合は、falseを返却する。 <BR>
     *    ※レコードが取得できなかった場合も含む。 <BR>
     * @@param l_lngBranchId<BR>
     * @@return boolean <BR>
     */
    
    protected boolean IsRealCooperation(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " IsRealCooperation(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ? ");
        l_sbWhere.append(" and name = ? ");
        l_sbWhere.append(" and name_serial_no = ? ");
        Object[] l_objWhere =
            {new Long(l_lngBranchId),
             WEB3BranchPreferencesNameDef.ACCOUNT_LOCK_REAL_COOPERATION,
             "1" };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                BranchPreferencesRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        //※レコードが取得できなかった場合も含む。 
        if(l_lstRecords == null || l_lstRecords.size() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            BranchPreferencesRow l_row = (BranchPreferencesRow)l_lstRecords.get(0);
            //２）取得したレコード.プリファ@レンスの値 == ”顧客ロック情報のリアル連携を行う。” の場合、true を返却する。 
            if (WEB3AccountLockDef.LOCK_REAL.equals(l_row.getValue()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //３）それ以外の場合は、falseを返却する。 
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
        }
       
    }
    private boolean isTrue(BooleanEnum l_booleanEnum)
    {
        
        return BooleanEnum.TRUE.equals(l_booleanEnum) ? true : false;
        
    }
}
@
