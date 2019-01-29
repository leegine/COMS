head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ登録サービスImpl(WEB3AdminMCAdminPermGrpRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.WEB3AdminMCAdminType;

/**
 * (管理者メニュー制御管理者権限グループ登録サービスImpl)<BR>
 * 管理者メニュー制御管理者権限グループ登録サービス実装クラス<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistServiceImpl implements WEB3AdminMCAdminPermGrpRegistService 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistServiceImpl.class);
    
    /**
     * @@roseuid 419864130203
     */
    public WEB3AdminMCAdminPermGrpRegistServiceImpl() 
    {
     
    }
    
    /**
     * 管理者権限グループ登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ登録入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ登録確認リクエストの場合 <BR>
     * 　@－validate権限グループ()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ登録完了リクエストの場合 <BR>
     * 　@－submit権限グループ()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EE3F00FC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPermGrpRegistInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminMCAdminPermGrpRegistInputRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpRegistConfirmRequest)
        {
            l_response = this.validatePermGrp((WEB3AdminMCAdminPermGrpRegistConfirmRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpRegistCompleteRequest)
        {
            l_response = this.submitPermGrp((WEB3AdminMCAdminPermGrpRegistCompleteRequest)l_request);
        
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
     * (get入力画面)<BR>
     * 管理者権限グループ入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ登録）get入力画面」参照。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EE3F011B
     */
    protected WEB3AdminMCAdminPermGrpRegistInputResponse getInputScreen(WEB3AdminMCAdminPermGrpRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCAdminPermGrpRegistInputRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //1.2 validate権限(機@能カテゴリコード（=管理者権限管理 ） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
         
        
        //1.3 管理者メニュー制御管理者グループ登録入力レスポンス(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpRegistInputResponse l_response = new WEB3AdminMCAdminPermGrpRegistInputResponse(l_request);
                 
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (validate権限グループ)<BR>
     * 権限グループ登録確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ登録）validate権限グループ」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ登録）validate権限グループ」<BR>
     *         具体位置    : 1.8(*1) 分岐フロー<BR>
     *         該当行が既に存在する場合（管理者タイプ() != null）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01226           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse
     * @@roseuid 4175EE3F013B
     */
    protected WEB3AdminMCAdminPermGrpRegistConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate権限(機@能カテゴリコード)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        //1.4 get証券会社コード
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.5  isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.6  validateDIR管理者
        l_request.adminTypeUnit.validateDIRAdmin(l_blnDir);
        //1.7  get管理者タイプ   
        WEB3AdminMCAdminType l_adminMCAdminType = null; 

        //1.10 メッセージ 管理者メニュー制御管理者一覧レスポンス(WEB3GenRequest) 
        try
        {          
            l_adminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel);
        }
        catch (WEB3BusinessLayerException l_ex)
        {        
            WEB3AdminMCAdminPermGrpRegistConfirmResponse l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);  
            return l_response;           
        }
        //1.8 (*1) 該当行が既に存在する場合（管理者タイプ() != null）、例外をスローする。        
        log.error("該当行が既に存在する場合.");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
        WEB3ErrorCatalog.BUSINESS_ERROR_01226,
           this.getClass().getName() + STR_METHOD_NAME);            
        
    }
    
    /**
     * (submit権限グループ)<BR>
     * 権限グループ登録完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ登録）submit権限グループ」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ登録）submit権限グループ」<BR>
     *         具体位置    : 1.9(*1) 分岐フロー<BR>
     *         該当行が既に存在する場合（管理者タイプ() != null）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01226           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse
     * @@roseuid 4175EF03033E
     */
    protected WEB3AdminMCAdminPermGrpRegistCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3  validate権限(機@能カテゴリコード)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        //1.4 validate取引パスワード(String)
        l_administartor.validateTradingPassword(l_request.password);
        //1.5 get証券会社コード
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.6  isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.6  validateDIR管理者
        l_request.adminTypeUnit.validateDIRAdmin(l_blnDir);
        //1.7  get管理者タイプ   
        WEB3AdminMCAdminType l_adminMCAdminType = null;

        try
        {          
            l_adminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel);
        }
        catch (WEB3BusinessLayerException l_ex)
        {        
            //1.10  get管理者コード()
            String l_admncode = l_administartor.getAdministratorCode();
            //1.11  saveNew管理者タイプ(String, String, String, boolean, boolean, String)
            WEB3AdminMCAdminType.saveNewAdminType(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel, l_request.adminTypeUnit.permissionLevelName,
                                                l_request.adminTypeUnit.dirAdminFlag, l_request.adminTypeUnit.allBranchPermissionFlag, l_admncode);

           //1.12update処理可能機@能カテゴリ(String, String, 機@能カテゴリ情報[], String)
           WEB3AdminMCAdminPermUnitCreateService l_adminPermUnitCreateServiceImpl = (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
           l_adminPermUnitCreateServiceImpl.updateOperatePossibleTransactionCategory(l_strInstitutionCode, l_request.adminTypeUnit.permissionLevel,
                                                            l_request.transactionCategoryUnits, l_admncode);


            //1.13 メッセージ 管理者メニュー制御管理者一覧レスポンス(WEB3GenRequest) 
            WEB3AdminMCAdminPermGrpRegistCompleteResponse l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);  
            return l_response;         
          
        }
        //1.9 (*1) 該当行が既に存在する場合（管理者タイプ() != null）、例外をスローする。        
        log.error("該当行が既に存在する場合.");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
        WEB3ErrorCatalog.BUSINESS_ERROR_01226,
            this.getClass().getName() + STR_METHOD_NAME);            
        
        
    }
}
@
