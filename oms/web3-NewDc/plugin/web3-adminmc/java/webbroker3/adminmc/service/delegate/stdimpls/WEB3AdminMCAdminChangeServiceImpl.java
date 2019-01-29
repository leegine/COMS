head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者変更サービスImpl(WEB3AdminMCAdminChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
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
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;


/**
 * (管理者メニュー制御管理者変更サービスImpl)<BR>
 * 管理者メニュー制御管理者変更サービス実装クラス<BR>
 * @@author 王敏
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCAdminChangeServiceImpl implements WEB3AdminMCAdminChangeService
{
    /**
      * ログ出力ユーティリティ。
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeServiceImpl.class);

    /**
     * @@roseuid 41986415037A
     */
    public WEB3AdminMCAdminChangeServiceImpl()
    {

    }

    /**
     * 管理者変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者変更入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者変更確認リクエストの場合 <BR>
     * 　@－validate管理者()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者変更完了リクエストの場合 <BR>
     * 　@－submit管理者()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DC8A500E6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        // 引数のリクエストデータが、管理者メニュー制御管理者変更入力リクエストの場合
        if (l_request instanceof WEB3AdminMCAdminChangeInputRequest)
        {
            // get入力画面()をコールする
            l_response = this.getInputScreen((WEB3AdminMCAdminChangeInputRequest)l_request);
        }
        // 引数のリクエストデータが、管理者メニュー制御管理者変更確認リクエストの場合
        else if (l_request instanceof WEB3AdminMCAdminChangeConfirmRequest)
        {
            // validate管理者()をコールする
            l_response = this.validateAdministrator((WEB3AdminMCAdminChangeConfirmRequest)l_request);
        }
        // 引数のリクエストデータが、管理者メニュー制御管理者変更完了リクエストの場合
        else if (l_request instanceof WEB3AdminMCAdminChangeCompleteRequest)
        {
            // submit管理者()をコールする
            l_response = this.submitAdministrator((WEB3AdminMCAdminChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                                                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 管理者変更入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者変更）get入力画面」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）get入力画面」<BR>
     *         具体位置    :1.6  管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できない場合、変更対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）get入力画面」<BR>
     *         具体位置    : 1.7(*1) 分岐フロー<BR>
     *         管理者が存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）get入力画面」<BR>
     *         具体位置    : 1.9.2(*2.1) 対象データがDIR管理者の場合<BR>
     *         （isDIR管理者() == true）、例外をスローする。<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DC8A500E8
     */
    protected WEB3AdminMCAdminChangeInputResponse getInputScreen(WEB3AdminMCAdminChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminMCAdminChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 get証券会社( )
        Institution l_institution = l_administartor.getInstitution();

        //1.5 validate部店権限
        l_administartor.validateBranchPermission(l_request.branchCode);

        //1.6 管理者
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
        	//1.7
             log.error("「管理者が存在しない」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                                   WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                                                   this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8 get管理者コード()
        String l_strAdministratorCode = l_administartor.getAdministratorCode();
        
        //1.9 get管理者コード()
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
        
        //1.10 (*2) ログイン中の管理者の場合（オペレータ.get管理者コード() == 削除対象.get管理者コード()）の場合、例外をスローする
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("「オペレータ.get管理者コード() == 削除対象.get管理者コード()」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        
        //1.8 isDIR管理者( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.9 ログイン中の管理者が通常管理者（isDIR管理者() == false）
        if (!l_blnisdiradmn)
        {
            //1.9.1 isDIR管理者( )
            boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
            if (l_blnisdiradmn2)
            {
                 //1.9.2 DIR管理者の管理者タイプの場合（isDIR管理者() == true）
                 log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                                                       WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                                                       this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.10 get管理者タイプ
        WEB3AdminMCAdminType[] l_admintype = WEB3AdminMCAdminType.getAdminType(l_institution.getInstitutionCode(), "permission_Level", l_blnisdiradmn);
        int l_recno = l_admintype.length;

        // 1.11 管理者メニュー制御管理者変更入力レスポンス
        WEB3AdminMCAdminChangeInputResponse l_response = (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
        //1.12 プロパティセット
        l_response.permissionLevelList = new String[l_recno];
        l_response.permissionLevelNameList = new String[l_recno];
        
        for (int i = 0; i < l_recno; i++)
        {
            //権限レベルコード一覧
            l_response.permissionLevelList[i] = l_admintype[i].getPermissionLevel();
            //権限レベル名一覧
            l_response.permissionLevelNameList[i] = l_admintype[i].getPermissionLevelName();
        }


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate管理者)<BR>
     * 管理者変更確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者変更）validate管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.6 管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できない場合、変更対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.7(*1) 分岐フロー<BR>
     *         管理者が存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01222           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.10(*2) 分岐フロー<BR>
     *         訂正対象データがオペレータ.get管理者コード() == 変更対象.get管理者コード()、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227 <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.12.2(*2.1) 分岐フロー<BR>
     *         訂正対象データがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01223           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.12.5(*2.2) 分岐フロー<BR>
     *         入力された管理者タイプがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）validate管理者」<BR>
     *         具体位置    : 1.11(*3) 分岐フロー<BR>
     *         メールアドレスが不正な場合（isMailAddress() == false）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse
     * @@roseuid 417DC8A500F6
     */
    protected WEB3AdminMCAdminChangeConfirmResponse validateAdministrator(WEB3AdminMCAdminChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdministrator(WEB3AdminMCAdminChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 get証券会社( )
        Institution l_institution = l_administartor.getInstitution();

        //1.5 validate部店権限
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);

        //1.6 管理者
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
           //1.7
             log.error("「管理者が存在しない」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 get管理者コード( )
        String l_strAdministratorCode = l_administartor.getAdministratorCode();

        //1.9 get管理者コード( )
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
         
        //1.10 ログイン中の管理者の場合（オペレータ.get管理者コード() == 変更対象.get管理者コード()）の場合、例外をスローする。
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("「オペレータ.get管理者コード() == 削除対象.get管理者コード()」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }

        //1.11 isDIR管理者( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.12 管理者が通常管理者（isDIR管理者() == false）場合
        if (!l_blnisdiradmn)
        {
             //1.12.1 isDIR管理者( )
             boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
             //1.12.2  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
             if (l_blnisdiradmn2)
             {
                 log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                               this.getClass().getName() + STR_METHOD_NAME);
             }

             //1.12.3 管理者タイプ(String, String)
             WEB3AdminMCAdminType l_adminmcadmintype = new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);

             //1.12.4 isDIR管理者( )
             boolean l_blnisdiradmntyp = l_adminmcadmintype.isDIRAdministrator();

             //1.12.5  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
             if (l_blnisdiradmntyp)
             {
                 log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                               this.getClass().getName() + STR_METHOD_NAME);
             }
        }

        if (l_request.adminRegistUnit.mailAddress != null)
        {
            //1.13 isMailAddress(String)
            boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
            //1.14 メールアドレスが不正な場合（isMailAddress() == false）
            if (!l_blnisMailAddress)
            {
                log.error("「メールアドレスが不正」の例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                              WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                              this.getClass().getName() + STR_METHOD_NAME);
            }            
        }

        //1.15 createResponse( )
        WEB3AdminMCAdminChangeConfirmResponse l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit管理者)<BR>
     * 管理者変更完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者変更）submit管理者」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.7 管理者(証券会社 : 証券会社, <BR>
     *         管理者コード : String)<BR>
     *         ※ 既存データチェック<BR>
     *         入力された管理者コードにて、<BR>
     *         管理者オブジェクトを生成する。<BR>
     *         生成できない場合、変更対象管理者がないと判断し、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01222          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.8(*1) 分岐フロー<BR>
     *         管理者が存在しない場合（オブジェクトが生成できない場合）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01222          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.11(*) 分岐フロー<BR>
     *         訂正対象データがログイン中の管理者の場合<BR>
     *             （オペレータ.get管理者コード() == 変更対象.get管理者コード()）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01227<BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.13.2(*2.1) 分岐フロー<BR>
     *         訂正対象データがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01223          <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.13.5(*2.2) 分岐フロー<BR>
     *         入力された管理者タイプがDIR管理者の場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者変更）submit管理者」<BR>
     *         具体位置    : 1.15(*3) 分岐フロー<BR>
     *         メールアドレスが不正な場合（isMailAddress() == false）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00777           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse
     * @@roseuid 417DC8A500F8
     */
    protected WEB3AdminMCAdminChangeCompleteResponse submitAdministrator(WEB3AdminMCAdminChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAdministrator(WEB3AdminMCAdminChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1validate()
        l_request.validate();

        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード（=管理者管理）)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR, true);

        //1.4 validate部店権限
        l_administartor.validateBranchPermission(l_request.adminRegistUnit.branchCode);

        //1.5 validate取引パスワード
        l_administartor.validateTradingPassword(l_request.password);

        //1.6 get証券会社( )
        Institution l_institution = l_administartor.getInstitution();

        //1.7 管理者
        WEB3Administrator l_administartor2 = null;
        try
        {
              l_administartor2 = new WEB3Administrator(l_institution, l_request.adminRegistUnit.administratorCode);
        }
        catch (WEB3SystemLayerException l_ex)
        {
           //1.8
             log.error("「管理者が存在しない」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01222,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 get管理者コード( )
        String l_strAdministratorCode = l_administartor.getAdministratorCode();

        //1.10 get管理者コード( )
        String l_strOtherAdministartorCode = l_administartor2.getAdministratorCode();
         
        //1.11 ログイン中の管理者の場合（オペレータ.get管理者コード() == 変更対象.get管理者コード()）の場合、例外をスローする。
        if (l_strAdministratorCode.equals(l_strOtherAdministartorCode))
        {
            log.error("「オペレータ.get管理者コード() == 削除対象.get管理者コード()」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01227,
                this.getClass().getName() + STR_METHOD_NAME);                
        }

        //1.12 isDIR管理者( )
        boolean l_blnisdiradmn = l_administartor.isDirAdministrator();

        //1.13 管理者が通常管理者（isDIR管理者() == false）場合
        if (!l_blnisdiradmn)
        {
             //1.13.1 isDIR管理者( )
             boolean l_blnisdiradmn2 = l_administartor2.isDirAdministrator();
             //1.13.2  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
             if (l_blnisdiradmn2)
             {
                 log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01223,
                               this.getClass().getName() + STR_METHOD_NAME);
             }

             //1.13.3 管理者タイプ(String, String)
             WEB3AdminMCAdminType l_adminmcadmintype =  new WEB3AdminMCAdminType(l_institution.getInstitutionCode(), l_request.adminRegistUnit.permissionLevel);

             //1.13.4 isDIR管理者( )
             boolean l_blnisdiradmntyp = l_adminmcadmintype.isDIRAdministrator();

             //1.13.5  DIR管理者の管理者タイプの場合（isDIR管理者() == true）
             if (l_blnisdiradmntyp)
             {
                 log.error("「DIR管理者の管理者タイプ」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                               this.getClass().getName() + STR_METHOD_NAME);
             }
        }
        
        if (l_request.adminRegistUnit.mailAddress != null)
        {
            //1.14 isMailAddress(String)
            boolean l_blnisMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.adminRegistUnit.mailAddress);
            //1.15 メールアドレスが不正な場合（isMailAddress() == false）
            if (!l_blnisMailAddress)
            {
                log.error("「メールアドレスが不正」の例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                              WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                              this.getClass().getName() + STR_METHOD_NAME);
            }            
        }

        //1.16 get管理者ＩＤ（）
        long l_lnadmnid = l_administartor2.getAdministratorId();

        //1.17 get管理者コード( )
        String l_admncode3 = l_administartor.getAdministratorCode();

        //1.18 doUpdateQuery(PrimaryKey, String, Object[], Map)
        Map l_changeMap = new HashMap();
        // 管理者名
        l_changeMap.put("name", l_request.adminRegistUnit.administratorName);
        // email_address
        l_changeMap.put("email_address", l_request.adminRegistUnit.mailAddress);            
        // 権限レベル
        l_changeMap.put("permission_level", l_request.adminRegistUnit.permissionLevel);
        // 更新者コード
        l_changeMap.put("last_updater", l_admncode3);
        //更新日時 = 処理日時
        l_changeMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        try
        {
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_processor.doUpdateQuery(new AdministratorPK(l_lnadmnid),
                                                 l_changeMap);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "管理者タイプを更新 error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                               WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                               l_dqe.getMessage(),
                                                               l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "管理者タイプを更新  error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                               WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                               l_dne.getMessage(),
                                                               l_dne);
        }

        //1.19 createResponse( )
        WEB3AdminMCAdminChangeCompleteResponse l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
