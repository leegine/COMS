head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者削除サービスImpl(WEB3AdminMCAdminDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30  賈元春 (中訊) 新規作成
*/
package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
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
import webbroker3.gentrade.data.AdministratorPK;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminDeleteService;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse;

/**
 * (管理者メニュー制御管理者削除サービスImpl)<BR>
 * 管理者メニュー制御管理者削除サービス実装クラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0
 */
public class WEB3AdminMCAdminDeleteServiceImpl implements WEB3AdminMCAdminDeleteService 
{

    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminDeleteServiceImpl.class);
    
    /**
     * @@roseuid 41986414030D
     */
    public WEB3AdminMCAdminDeleteServiceImpl() 
    {
     
    }
    
    /**
     * 管理者削除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者削除確認リクエストの場合 <BR>
     * 　@－validate管理者()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者削除完了リクエストの場合 <BR>
     * 　@－submit管理者()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DAE98004E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminDeleteConfirmRequest)
        {
            l_response = this.validateAdministrator((WEB3AdminMCAdminDeleteConfirmRequest)l_request);
        
        }
        else if(l_request instanceof WEB3AdminMCAdminDeleteCompleteRequest)
        {
            l_response = this.submitAdministrator((WEB3AdminMCAdminDeleteCompleteRequest)l_request);
        
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
     * (validate管理者)<BR>
     * 管理者削除確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者削除）validate管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）validate管理者」<BR>
     *         具体位置    : 1.6 管理者(証券会社 : 証券会社,<BR>
     *         管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できない場合、削除対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）validate管理者」<BR>
     *         具体位置    : 1.7(*1) 分岐フロー<BR>
     *         管理者コードが存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）validate管理者」<BR>
     *         具体位置    : 1.8 1.9 get管理者コード( )<BR>
     *         ※ オペレータの管理者情報は削除できない<BR>
     *         ログイン中の管理者の場合<BR>
     *         （オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01227          <BR>        
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）validate管理者」<BR>
     *         具体位置    : 1.10(*2) 分岐フロー<BR>
     *         ログイン中の管理者の場合<BR>
     *         （オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01227            <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）validate管理者」<BR>
     *         具体位置    : 1.12.2(*3.1) 分岐フロー<BR>
     *         対象データがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者削除確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse
     * @@roseuid 417DAE980052
     */
    protected WEB3AdminMCAdminDeleteConfirmResponse validateAdministrator(WEB3AdminMCAdminDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateAdministrator(WEB3AdminMCAdminDeleteConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者管理） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 get証券会社()
        Institution l_institution = l_administrator.getInstitution();
        
        //1.5 validate部店権限
        l_administrator.validateBranchPermission(l_request.branchCode);
        
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
        
        //1.8 get管理者コード()
        String l_administratorCode = l_administrator.getAdministratorCode();
        
        //1.9 get管理者コード()
        String l_otheradministartorCode = l_otheradministartor.getAdministratorCode();
        
        //1.10 (*2) ログイン中の管理者の場合（オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、例外をスローする
        if (l_administratorCode.equals(l_otheradministartorCode))
        {
            log.error("「オペレータ.get管理者コード() == 削除対象.get管理者コード()」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        
        //1.11 isDIR管理者()
        boolean l_blnisdiradmn = l_administrator.isDirAdministrator();
        
        //(*3) ログイン中の管理者が通常管理者（isDIR管理者() == false）場合のみ処理実施
        if (!l_blnisdiradmn)
        {
            //1.12.1 isDIR管理者()
            boolean l_otherBlnisdiradmn = l_otheradministartor.isDirAdministrator();
            if (l_otherBlnisdiradmn)
            {
                log.error("対象データがDIR管理者の場合（isDIR管理者() == true）、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                    this.getClass().getName() + STR_METHOD_NAME);                                
            }
        }
        
        //1.13 createResponse()
        WEB3AdminMCAdminDeleteConfirmResponse l_response = 
            (WEB3AdminMCAdminDeleteConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit管理者)<BR>
     * 管理者削除完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者削除）submit管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）submit管理者」<BR>
     *         具体位置    : 1.7 管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できない場合、削除対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）submit管理者」<BR>
     *         具体位置    : 1.8(*1) 分岐フロー<BR>
     *         管理者コードが存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）submit管理者」<BR>
     *         具体位置    : 1.9 1.10 get管理者コード( )<BR>
     *         ※ オペレータの管理者情報は削除できない<BR>
     *         ログイン中の管理者の場合<BR>
     *         （オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）submit管理者」<BR>
     *         具体位置    : 1.11(*2) 分岐フロー<BR>
     *         ログイン中の管理者の場合<BR>
     *         （オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01227            <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者削除）submit管理者」<BR>
     *         具体位置    : 1.13.2(*3.1) 分岐フロー<BR>
     *         対象データがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :   BUSINESS_ERROR_01223            <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者削除完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 417DAE98005D
     */
    protected WEB3AdminMCAdminDeleteCompleteResponse submitAdministrator(WEB3AdminMCAdminDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitAdministrator(WEB3AdminMCAdminDeleteCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者管理） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);
        
        //1.4 validate部店権限(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.6 get証券会社()
        Institution l_institution = l_administrator.getInstitution();
        
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

        //1.9 get管理者コード()
        String l_administratorCode = l_administrator.getAdministratorCode();
        
        //1.10 get管理者コード()
        String l_otheradministartorCode = l_otheradministartor.getAdministratorCode();
        
        //1.11 (*2) ログイン中の管理者の場合（オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、例外をスローする。
        if (l_administratorCode.equals(l_otheradministartorCode))
        {
            log.error("「オペレータ.get管理者コード() == 削除対象.get管理者コード()」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);               
        }
        
        //1.12 isDIR管理者()
        boolean l_blnisdiradmn = l_administrator.isDirAdministrator(); 
        
        //1.13 (*3) ログイン中の管理者が通常管理者（isDIR管理者() == false）場合のみ処理実施       
        if (!l_blnisdiradmn)
        {
            //1.13.1 isDIR管理者()
            boolean l_otherBlnisdiradmn = l_otheradministartor.isDirAdministrator();
            if (l_otherBlnisdiradmn)
            {
                log.error("対象データがDIR管理者の場合（isDIR管理者() == true）、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                    this.getClass().getName() + STR_METHOD_NAME);                                
            }            
        }
        
        //1.14 getログインＩＤ()
        long l_lngLoginId = l_otheradministartor.getLoginId();
        
        //1.15 removeLogin(long)
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.removeLogin(l_lngLoginId);
        
        //1.16 setLoginAttributes(long, Map)
        l_opLoginAdminService.setLoginAttributes(l_lngLoginId, null);
        
        //1.17 get管理者ＩＤ()
        long l_lngAdministratorId = l_otheradministartor.getAdministratorId();
        
        //1.18 doDeleteQuery(PrimaryKey, String, Object[])
        try
        {        
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteQuery(new AdministratorPK(l_lngAdministratorId));
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.19 createResponse()
        WEB3AdminMCAdminDeleteCompleteResponse l_reponse = 
            (WEB3AdminMCAdminDeleteCompleteResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
        
    }
}
@
