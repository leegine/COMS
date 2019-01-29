head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者パスワードロック解除サービスImpl(WEB3AdminMCAdminPwdLockCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;

/**
 * (管理者メニュー制御管理者パスワードロック解除サービスImpl)<BR>
 * 管理者メニュー制御管理者パスワードロック解除サービス実装クラス<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelServiceImpl implements WEB3AdminMCAdminPwdLockCancelService 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPwdLockCancelServiceImpl.class);
        
    /**
     * @@roseuid 41986410036B
     */
    public WEB3AdminMCAdminPwdLockCancelServiceImpl() 
    {
    
    }
    
    /**
     * 管理者パスワードロック解除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * 
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸﾞ解除確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validate解除()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submit解除()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DE3BD01E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPwdLockCancelConfirmRequest)
        {
            l_response = this.validateCancel((WEB3AdminMCAdminPwdLockCancelConfirmRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPwdLockCancelCompleteRequest)
        {
            l_response = this.submitCancel((WEB3AdminMCAdminPwdLockCancelCompleteRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate解除)<BR>
     * 管理者パスワードロック解除確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者パスワードロック解除）validate解除」参照。 <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）validate解除」<BR>
     *         具体位置    : 1.6 管理者(証券会社 : 証券会社, 管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、管理者オブジェクトを生成する。<BR>
     *         生成できない場合、ロック解除対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）validate解除」<BR>
     *         具体位置    : 1.7(*1) 分岐フロー<BR>
     *         管理者コードが存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222          <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）validate解除」<BR>
     *         具体位置    : 1.9.2 (*2.1) 対象データがDIR管理者の場合<BR>
     *         （isDIR管理者() == true）、例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223          <BR>
     * ========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御ﾊﾟｽﾜｰﾄﾞ解除確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse
     * @@roseuid 417DE3BD01E2
     */
    protected WEB3AdminMCAdminPwdLockCancelConfirmResponse validateCancel(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request) throws WEB3BaseException  
    {

        final String STR_METHOD_NAME = " validateCancel(WEB3AdminMCAdminPwdLockCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate権限(機@能カテゴリコード)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,true);
        //1.4 get証券会社
        Institution l_institution = l_administartor.getInstitution();
        //1.5 validate部店権限
        l_administartor.validateBranchPermission(l_request.branchCode);
        //1.6 管理者(long)
        WEB3Administrator l_otheradministartor = null;
        try 
        {
            l_otheradministartor = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.7 (*1) 管理者コードが存在しない場合（オブジェクトが生成できない場合
            log.error("管理者コードが存在しない場合（オブジェクトが生成できない場合）.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);          
        }   
               
        //1.8  isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        // (*2) ログイン中の管理者が通常管理者（isDIR管理者() == false）場合
        if (!l_blnDir)
        {
             //1.9.1 isDIR管理者( )
             boolean l_otherblnDir = l_otheradministartor.isDirAdministrator();
             //1.9.2 (*2.1) 対象データがDIR管理者の場合（isDIR管理者() == true）、
             if (l_otherblnDir)
             {
                 log.error("対象データがDIR管理者の場合（isDIR管理者() == true）.");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                     this.getClass().getName() + STR_METHOD_NAME);          
             }        
            
        }
        
       //1.10 createResponse( )
       WEB3AdminMCAdminPwdLockCancelConfirmResponse l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
    
    /**
     * (submit解除)<BR>
     * 管理者パスワードロック解除完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者パスワードロック解除）submit解除」参照。<BR> 
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）submit解除」<BR>
     *         具体位置    : 1.7 管理者(証券会社 : 証券会社, 管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、管理者オブジェクトを生成する。<BR>
     *         生成できない場合、ロック解除対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）submit解除」<BR>
     *         具体位置    : 1.8(*1) 分岐フロー<BR>
     *         管理者コードが存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者パスワードロック解除）submit解除」<BR>
     *         具体位置    : 1.9.2 (*2.1) 分岐フロー<BR>
     *         対象データがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * ========================================================== <BR>        
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御ﾊﾟｽﾜｰﾄﾞ解除完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse
     * @@roseuid 417DE3BD01E4
     */
    protected WEB3AdminMCAdminPwdLockCancelCompleteResponse submitCancel(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate権限(機@能カテゴリコード)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        //1.4 get証券会社
        Institution l_institution = l_administartor.getInstitution();
        //1.5 validate部店権限
        l_administartor.validateBranchPermission(l_request.branchCode);
        //1.6 validate取引パスワード(String)
        l_administartor.validateTradingPassword(l_request.password);
        //1.7 管理者(long)
        WEB3Administrator l_otheradministartor = null;
        try
        {
            l_otheradministartor = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
            //1.8 (*1) 管理者コードが存在しない場合（オブジェクトが生成できない場合
            log.error("管理者コードが存在しない場合（オブジェクトが生成できない場合）.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);          
        }   
       
        //1.8  isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.9 (*2) ログイン中の管理者が通常管理者（isDIR管理者() == false）場合
        if (!l_blnDir)
        {
             //1.9.1 isDIR管理者( )
             boolean l_otherblnDir = l_otheradministartor.isDirAdministrator();
             //1.9.2 (*2.1) 対象データがDIR管理者の場合（isDIR管理者() == true）、
             if (l_otherblnDir)
             {
                 log.error("対象データがDIR管理者の場合（isDIR管理者() == true）.");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                     this.getClass().getName() + STR_METHOD_NAME);          
             }        
            
        }
        //1.10  getログインＩＤ()
        long l_loginId = l_otheradministartor.getLoginId();       
        //1.11  clearBadPassowrdHistory
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.clearBadPassowrdHistory(l_loginId);          
        //1.12  enableLogin(long)
        l_opLoginAdminService.enableLogin(l_loginId);
        
       //1.13 createResponse( )
       WEB3AdminMCAdminPwdLockCancelCompleteResponse l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
