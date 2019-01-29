head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ削除サービスImpl(WEB3AdminMCAdminPermGrpDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;

/**
 * (管理者メニュー制御管理者権限グループ削除サービスImpl)<BR>
 * 管理者メニュー制御管理者権限グループ削除サービス実装クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpDeleteServiceImpl implements WEB3AdminMCAdminPermGrpDeleteService 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDeleteServiceImpl.class);
    
    /**
     * @@roseuid 419864130000
     */
    public WEB3AdminMCAdminPermGrpDeleteServiceImpl() 
    {
     
    }
    
    /**
     * 管理者権限グループ削除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ削除確認リクエストの場合 <BR>
     * 　@－validate権限グループ()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ削除完了リクエストの場合 <BR>
     * 　@－submit権限グループ()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4177365003C8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminPermGrpDeleteConfirmRequest)
        {            
            l_response = this.validatePermGrp((WEB3AdminMCAdminPermGrpDeleteConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpDeleteCompleteRequest)
        {
            l_response = this.submitPermGrp((WEB3AdminMCAdminPermGrpDeleteCompleteRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);           
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate権限グループ)<BR>
     * 権限グループ削除確認処理を実施する。<BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ削除）validate権限グループ」<BR>
     *         具体位置    :1.8.2(*2.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225          <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ削除確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse
     * @@roseuid 4177365003D8
     */
    protected WEB3AdminMCAdminPermGrpDeleteConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validatePermGrp(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request)";         
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3 validate権限(機@能カテゴリコード（=管理者権限管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();  
        
        //1.5 管理者タイプ(証券会社コード : String, 権限レベル : String)
        WEB3AdminMCAdminType l_web3AdminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.permissionLevel);

        //1.6 validate管理者タイプ削除( )
        l_web3AdminMCAdminType.validateAdminTypeDelete();
        
        //1.7 isDIR管理者( )
        boolean l_blIsDirAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.8 DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (l_blIsDirAdministrator == false)
        {
            //1.8.1 isDIR管理者( )
            l_blIsDirAdministrator = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.8.2 (*2.1) DIR管理者の管理者タイプの場合（isDIR管理者() == true）、例外をスローする。
            if (l_blIsDirAdministrator == true)
            {
                log.error("DIR管理者の管理者タイプの場合のエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + STR_METHOD_NAME);                     
            }            
        }
        
        //1.9 create管理者タイプ情報(管理者タイプ : 管理者タイプ)
        WEB3AdminMCAdminPermUnitCreateService l_service= (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class); 
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit = l_service.createAdminTypeUnit(l_web3AdminMCAdminType);
            
        //1.10 create処理可能機@能カテゴリ一覧(String, String, String[])
        WEB3AdminMCTransactionCategoryUnit[] l_AdminMCTransactionCategoryUnits = l_service.createOperatePossibleTransactionCategoryUnit(l_strInstitutionCode, l_request.permissionLevel, null);
            
        //1.11 管理者メニュー制御管理者グループ削除確認レスポンス(l_request : WEB3GenRequest)
        WEB3AdminMCAdminPermGrpDeleteConfirmResponse l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)(l_request.createResponse());
        
        //(*3)レスポンスデータプロパティに以下の通り値をセットする。
        l_response.adminTypeUnit = l_web3AdminMCAdminTypeUnit; 
        l_response.transactionCategoryUnits = l_AdminMCTransactionCategoryUnits; 
              
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit権限グループ)<BR>
     * 権限グループ削除完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ削除）submit権限グループ」参照。 <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ削除）submit権限グループ」<BR>
     *         具体位置    :1.9.2(*2.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225          <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ削除完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 4177365003DA
     */
    protected WEB3AdminMCAdminPermGrpDeleteCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitPermGrp(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request)";         
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();   
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者権限管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION,true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.6 管理者タイプ(証券会社コード : String, 権限レベル : String)
        WEB3AdminMCAdminType l_web3AdminMCAdminType = new WEB3AdminMCAdminType(l_strInstitutionCode, l_request.permissionLevel);
         
        //1.7 validate管理者タイプ削除( )
        l_web3AdminMCAdminType.validateAdminTypeDelete(); 
        
        //1.8 isDIR管理者( )
        boolean l_blIsDirAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.9 (*2) DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (l_blIsDirAdministrator == false)
        {
            //1.9.1 isDIR管理者( )
            l_blIsDirAdministrator = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.9.2 (*2.1) DIR管理者の管理者タイプの場合（isDIR管理者() == true）、例外をスローする  
            if (l_blIsDirAdministrator == true)
            {
                log.error("DIR管理者の管理者タイプの場合のエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + STR_METHOD_NAME);                     
            }                                             
        }
        
        //1.10 get管理者コード( )
        String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();
        
        //1.11 saveDelete管理者タイプ(String, String)
        WEB3AdminMCAdminType.saveDeleteAdminType(l_strInstitutionCode, l_request.permissionLevel);
        
        //1.12 update処理可能機@能カテゴリ
        WEB3AdminMCAdminPermUnitCreateService l_service= (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
        l_service.updateOperatePossibleTransactionCategory(l_strInstitutionCode, l_request.permissionLevel, null, l_strAdministratorCode);
        
        //1.13 createResponse()
        WEB3AdminMCAdminPermGrpDeleteCompleteResponse l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
