head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更サービスImpl(WEB3AdminMCAdminPermGrpChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/29 屈陽 (中訊) 新規作成
                 : 2006/08/28 肖志偉 (中訊) 仕様変更 モデル022
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者権限グループ変更サービスImpl)<BR>
 * 管理者メニュー制御管理者権限グループ変更サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeServiceImpl implements WEB3AdminMCAdminPermGrpChangeService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeServiceImpl.class);  
    
    /**
     * 管理者権限グループ変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更確認リクエストの場合 <BR>
     * 　@－validate権限グループ()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更完了リクエストの場合 <BR>
     * 　@－submit権限グループ()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41771E340280
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.error("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //１）リクエストデータの型により、以下の通りメソッドをコールする。
        
        //a> 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更入力リクエストの場合 
        //   get入力画面()をコールする。
        if (l_request instanceof WEB3AdminMCAdminPermGrpChangeInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminMCAdminPermGrpChangeInputRequest)l_request);         
        }       
        //b> 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更確認リクエストの場合 
        //   validate権限グループ()をコールする。
        else if (l_request instanceof WEB3AdminMCAdminPermGrpChangeConfirmRequest)
        {
            l_response = 
                validatePermGrp((WEB3AdminMCAdminPermGrpChangeConfirmRequest)l_request);         
        }   
        //c> 引数のリクエストデータが、管理者メニュー制御管理者権限グループ変更完了リクエストの場合 
        //   submit権限グループ()をコールする。
        else if (l_request instanceof WEB3AdminMCAdminPermGrpChangeCompleteRequest)
        {
            l_response = 
                submitPermGrp((WEB3AdminMCAdminPermGrpChangeCompleteRequest)l_request);
                    
        }
        else 
        {
            log.error("error in get needed request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者権限グループ変更入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ変更）get入力画面」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）get入力画面」<BR>
     *         具体位置    :  1.7.2(*2.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>              
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41771E340290
     */
    protected WEB3AdminMCAdminPermGrpChangeInputResponse getInputScreen(WEB3AdminMCAdminPermGrpChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        String l_strMethodName = "getInputScreen(WEB3AdminMCAdminPermGrpChangeInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 ログイン情報より管理者オブジェクトを取得する。
        //getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 管理者権限チェックを行う 
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード：機@能カテゴリコード.管理者権限管理 
        // is更新：true 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //リクエストデータ.権限レベルコード
        String l_strPermissionLevel = l_request.permissionLevel;
               
        //1.5 管理者タイプオブジェクトを生成する。 
        //[コンストラクタの引数] 
        //証券会社コード：get証券会社コード() 
        //権限レベル：リクエストデータ.権限レベルコード 
        
        WEB3AdminMCAdminType l_web3AdminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel);
        
        //1.6 ＤＩＲ管理者かを判定する。 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.7 分岐フロー 
        //DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (l_booIsDirByAdministrator == false)
        {
            //1.7.1 （管理者タイプが）ＤＩＲ管理者かを判定する。
            boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator(); 
            
            //1.7.2 分岐フロー 
            //DIR管理者の管理者タイプの場合（isDIR管理者() == true）、例外をスローする。
            //      class : WEB3BusinessLayerException <BR>
            //        tag : BUSINESS_ERROR_01225 
            if (l_booIsDirByAdminType == true)
            {
                log.error("fail isDIR by 管理者タイプ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                         
            }
        }
        
        //1.8 権限レベルを取得する。
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();

        //1.9 （管理者タイプが）権限レベルを取得する。
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        //1.10 分岐フロー
        //（管理者.get権限レベル() ==　@管理者タイプ.get権限レベル()）、例外をスローする。
        //     class :  WEB3BusinessLayerException 
        //       tag :  BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("管理者.get権限レベル() == 管理者タイプ.get権限レベル()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.11 管理者タイプ情報を生成する。 
        //[create管理者タイプ()に指定する引数] 
        //管理者タイプ：生成した管理者タイプオブジェクト 
        
        //a> 管理者権限情報作成サービスImpl
        WEB3AdminMCAdminPermUnitCreateService l_web3AdminMCAdminPermUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(
                WEB3AdminMCAdminPermUnitCreateService.class);
        //b> create管理者タイプ()
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit = 
            l_web3AdminMCAdminPermUnitCreateService.createAdminTypeUnit(l_web3AdminMCAdminType);
        
        //1.12 証券会社，権限レベルに該当する機@能カテゴリ情報を配列にて取得する。 
        //[get処理可能機@能カテゴリ一覧()に指定する引数] 
        //証券会社コード：get証券会社コード() 
        //権限レベル：リクエストデータ.権限レベルコード 
        //機@能カテゴリコード[]：null 
        WEB3AdminMCTransactionCategoryUnit[] l_web3AdminMCTransactionCategoryUnit = 
            l_web3AdminMCAdminPermUnitCreateService.createOperatePossibleTransactionCategoryUnit(
                l_strInstitutionCode, 
                l_strPermissionLevel, 
                null);
                
        //1.13 レスポンスデータを生成する。 
        WEB3AdminMCAdminPermGrpChangeInputResponse l_response = 
            (WEB3AdminMCAdminPermGrpChangeInputResponse)l_request.createResponse();
            
        //1.14 (*3)プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //管理者タイプ情報 = create管理者タイプ()の戻り値
        l_response.adminTypeUnit = l_web3AdminMCAdminTypeUnit;
        
        //処理可能機@能カテゴリ一覧 = create処理可能機@能カテゴリ一覧()の戻り値
        l_response.transactionCategoryUnits = 
            l_web3AdminMCTransactionCategoryUnit;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate権限グループ)<BR>
     * 権限グループ変更確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ変更）validate権限グループ」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）validate権限グループ」<BR>
     *         具体位置    : 1.7 分岐フロー<BR>
     *         DIR管理者フラグが変更されている場合
     *        （管理者タイプ.isDIR管理者() !=　@リクエストデータ.DIR管理者フラグ）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01284       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）validate権限グループ」<BR>
     *         具体位置    : 1.10 分岐フロー<BR>
     *         ログイン中管理者の権限レベルが指定されている場合
     *        （管理者.get権限レベル() ==　@管理者タイプ.get権限レベル()）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01285       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）validate権限グループ」<BR>
     *         具体位置    : 1.12.3 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :     BUSINESS_ERROR_01225      <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse
     * @@roseuid 41771E340292
     */
    protected WEB3AdminMCAdminPermGrpChangeConfirmResponse validatePermGrp(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        String l_strMethodName = "validatePermGrp(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 ログイン情報より管理者オブジェクトを取得する。
        //getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 管理者権限チェックを行う 
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード：機@能カテゴリコード.管理者権限管理 
        // is更新：true 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.5 管理者タイプを取得する。 
        //[コンストラクタの引数] 
        //証券会社コード：get証券会社コード() 
        //権限レベル：リクエストデータ.管理者タイプ情報.権限レベルコード
        String l_strPermissionLevel = l_request.adminTypeUnit.permissionLevel;
        WEB3AdminMCAdminType l_web3AdminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel); 
        
        //1.6 （管理者タイプが）ＤＩＲ管理者かを判定する。
        boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator();
        
        //1.7 分岐フロー
        //（管理者タイプ.isDIR管理者() !=　@リクエストデータ.isDIR管理者()）、例外をスローする。
        //      class :  WEB3BusinessLayerException 
        //        tag :  BUSINESS_ERROR_01284
        if (l_booIsDirByAdminType != l_request.adminTypeUnit.isDIRAdmin())
        {
            log.error("管理者タイプ.isDIR管理者() !=　@リクエストデータ.isDIR管理者()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01284,
                this.getClass().getName() + "." + l_strMethodName);               
        }
        
        //1.8 権限レベルを取得する。
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();

        //1.9 （管理者タイプが）権限レベルを取得する。
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        
        //1.10 分岐フロー
        //（管理者.get権限レベル() ==　@管理者タイプ.get権限レベル()）、例外をスローする。
        //     class :  WEB3BusinessLayerException 
        //       tag :  BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("管理者.get権限レベル() == 管理者タイプ.get権限レベル()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.11 ＤＩＲ管理者かを判定する。 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.12 分岐フロー 
        //DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (l_booIsDirByAdministrator == false)
        {
            //1.12.1 ＤＩＲ管理者チェックを行う 
            //[validateDIR管理者()に指定する引数] 
            //isDIR管理者（オペレータ）：isDIR管理者() 
            l_request.adminTypeUnit.validateDIRAdmin(l_booIsDirByAdministrator);
            
            //1.12.2 （管理者タイプが）ＤＩＲ管理者かを判定する。
            boolean l_booIsDirByAdminTypeAgain = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.12.3 分岐フロー
            //DIR管理者の管理者タイプの場合（isDIR管理者() == true）、例外をスローする。
            //     class : WEB3BusinessLayerException 
            //       tag : BUSINESS_ERROR_01225
            if (l_booIsDirByAdminTypeAgain == true)
            {
                log.error("fail isDIR by 管理者タイプ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                              
            }
        }
        
        //1.13 レスポンスデータを生成する。
        WEB3AdminMCAdminPermGrpChangeConfirmResponse l_response =
            (WEB3AdminMCAdminPermGrpChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit権限グループ)<BR>
     * 権限グループ変更完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ変更）submit権限グループ」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）validate権限グループ」<BR>
     *         具体位置    : 1.8 分岐フロー<BR>
     *         DIR管理者フラグが変更されている場合
     *        （管理者タイプ.isDIR管理者() !=　@リクエストデータ.DIR管理者フラグ）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01284       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）validate権限グループ」<BR>
     *         具体位置    : 1.11 分岐フロー<BR>
     *         ログイン中管理者の権限レベルが指定されている場合
     *        （管理者.get権限レベル() ==　@管理者タイプ.get権限レベル()）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01285       <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ変更）submit権限グループ」<BR>
     *         具体位置    : 1.13.3 分岐フロー <BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse
     * @@roseuid 41771E340294
     */
    protected WEB3AdminMCAdminPermGrpChangeCompleteResponse submitPermGrp(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "submitPermGrp(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。
        l_request.validate(); 
        
        //1.2 ログイン情報より、管理者オブジェクトを取得する。 
        //getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 管理者権限チェックを行う 
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード：機@能カテゴリコード.管理者権限管理 
        // is更新：true 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, true);
        
        //1.4 取引パスワードのチェックを行う 
        //[validate取引パスワード()に指定する引数] 
        //暗証番号: リクエストデータ.暗証番号
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.5 証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.6 管理者タイプを取得する。 
        //[コンストラクタの引数] 
        //証券会社コード：get証券会社コード() 
        //権限レベル：リクエストデータ.管理者タイプ情報.権限レベルコード
        String l_strPermissionLevel = l_request.adminTypeUnit.permissionLevel; 
        WEB3AdminMCAdminType l_web3AdminMCAdminType = 
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel);           
        
        //1.7（管理者タイプが）ＤＩＲ管理者かを判定する。
        boolean l_booIsDirByAdminType = l_web3AdminMCAdminType.isDIRAdministrator(); 
        
        //1.8 分岐フロー
        //（管理者タイプ.isDIR管理者() !=　@リクエストデータ.isDIR管理者()）、例外をスローする。
        //     class : WEB3BusinessLayerException
        //       tag : BUSINESS_ERROR_01284                  
        if (l_booIsDirByAdminType != l_request.adminTypeUnit.isDIRAdmin())
        {
            log.error("管理者タイプ.isDIR管理者() !=　@リクエストデータ.isDIR管理者()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01284,
                this.getClass().getName() + "." + l_strMethodName);               
        }
        
        //1.9 （管理者タイプが）権限レベルを取得する。
        String l_strPlFromAdminType = l_web3AdminMCAdminType.getPermissionLevel();
        
        //1.10 権限レベルを取得する。
        String l_strPlFromAdmin = l_web3Administrator.getPermissionLevel();
        
        //1.11 分岐フロー
        //（管理者.get権限レベル() ==　@管理者タイプ.get権限レベル()）、例外をスローする。
        //     class : WEB3BusinessLayerException 
        //       tag : BUSINESS_ERROR_01285 
        if (l_strPlFromAdmin.equals(l_strPlFromAdminType))
        {
            log.error("管理者.get権限レベル() == 管理者タイプ.get権限レベル()");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01285,
                this.getClass().getName() + "." + l_strMethodName);                           
        }
        
        //1.12 ＤＩＲ管理者かを判定する。 
        boolean l_booIsDirByAdministrator = l_web3Administrator.isDirAdministrator();
        
        //1.13 分岐フロー 
        //DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (l_booIsDirByAdministrator == false)
        {
            //1.13.1 ＤＩＲ管理者チェックを行う 
            //[validateDIR管理者()に指定する引数] 
            //isDIR管理者（オペレータ）：isDIR管理者() 
            l_request.adminTypeUnit.validateDIRAdmin(l_booIsDirByAdministrator);
            
            //1.13.2 （管理者タイプが）ＤＩＲ管理者かを判定する。
            boolean l_booIsDirByAdminTypeAgain = l_web3AdminMCAdminType.isDIRAdministrator();
            
            //1.13.3 分岐フロー
            //DIR管理者の管理者タイプの場合（isDIR管理者() == true）、例外をスローする。
            //     class : WEB3BusinessLayerException 
            //       tag : BUSINESS_ERROR_01225
            if (l_booIsDirByAdminTypeAgain == true)
            {
                log.error("fail isDIR by 管理者タイプ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + l_strMethodName);                              
            }
        }
        
        //1.14 管理者コードを取得する。 
        String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();
        
        //1.15 管理者タイプテーブルを更新する。
        //[save管理者タイプ()に指定する引数] 
        //a> 証券会社コード：get証券会社コード() 
        //b> 権限レベル：リクエストデータ.管理者タイプ情報.権限レベルコード        
        //c> 権限レベル名称：リクエストデータ.管理者タイプ情報.権限レベル名称 
        String l_strPermissionLevelName = l_request.adminTypeUnit.permissionLevelName;
        
        //d> DIR管理者フラグ：リクエストデータ.管理者タイプ情報.DIR管理者フラグ 
        String l_strDirAdminFlag = l_request.adminTypeUnit.dirAdminFlag;
        
        //e> 全部店許可フラグ：リクエストデータ.管理者タイプ情報.全部店許可フラグ 
        boolean l_booAllBranch = l_request.adminTypeUnit.allBranchPermissionFlag;        
        //f> 管理者コード：get管理者コード() 
        
        WEB3AdminMCAdminType.saveAdminType(
            l_strInstitutionCode, 
            l_strPermissionLevel, 
            l_strPermissionLevelName, 
            l_strDirAdminFlag,
            l_booAllBranch,
            l_strAdministratorCode);
            
        //1.16 管理者権限テーブルを更新する。
        //[update処理可能機@能カテゴリ()に指定する引数] 
        //a> 証券会社コード：get証券会社コード() 
        //b> 権限レベル：リクエストデータ.管理者タイプ情報.権限レベルコード 
        //c> 処理可能機@能カテゴリ：リクエストデータ.処理可能機@能カテゴリ一覧 
        WEB3AdminMCTransactionCategoryUnit[] l_adminMCTransactionCategoryUnit =
            l_request.transactionCategoryUnits;        
        //d> 管理者コード：get管理者コード() 

        WEB3AdminMCAdminPermUnitCreateService l_web3AdminMCAdminPermUnitCreateService =
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(
                WEB3AdminMCAdminPermUnitCreateService.class);
        l_web3AdminMCAdminPermUnitCreateService.updateOperatePossibleTransactionCategory(
            l_strInstitutionCode,
            l_strPermissionLevel,
            l_adminMCTransactionCategoryUnit,
            l_strAdministratorCode);
            
        //1.17 レスポンスデータを生成する。
        WEB3AdminMCAdminPermGrpChangeCompleteResponse l_response =
            (WEB3AdminMCAdminPermGrpChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
