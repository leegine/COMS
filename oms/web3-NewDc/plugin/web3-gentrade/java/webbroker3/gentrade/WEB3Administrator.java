head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Administrator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者(WEB3Administrator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/14 鄒政 (中訊) 新規作成
Revesion History : 2006/08/30 栄イ (中訊) 仕様変更 モデル206を対応
Revesion History : 2006/10/24 栄イ (中訊) 仕様変更 モデル213を対応
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeDao;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * （WEB3Administrator）<BR>
 * 管理者クラス<BR>
 */
public class WEB3Administrator implements BusinessObject
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Administrator.class);

    /**
     * 管理者行オブジェクト<BR>
     * <BR>
     * ※ 管理者ParamsクラスはDDLより自動生成する。<BR>
     * ※ DBレイアウト「管理者テーブル仕様.xls」参照。<BR>
     */
    private AdministratorParams administratorParams;

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 管理者オブジェクトを生成する。<BR>
     * @@param l_administratorParams - 管理者行オブジェクト<BR>
     * @@return WEB3Administrator
     * @@roseuid 40C3F3020222
     */
    public WEB3Administrator(AdministratorParams l_administratorParams)
    {
        final String STR_METHOD_NAME = "WEB3Administrator(AdministratorParams)";
        if (l_administratorParams == null)
        {
            log.error("管理者行オブジェクト = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "管理者行オブジェクト = null");
        }
        this.administratorParams = l_administratorParams;
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 管理者オブジェクトを生成する。<BR>
     * @@param l_lngAdministratorId - 管理者ＩＤ<BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C3F2680167
     */
    public WEB3Administrator(long l_lngAdministratorId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3Administrator(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorRow l_administratorRow = 
                AdministratorDao.findRowByPk(l_lngAdministratorId); 
            this.administratorParams = new AdministratorParams(l_administratorRow);
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

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * コンストラクタ <BR>
     *  <BR>
     * 証券会社，管理者コードより、管理者インスタンスを生成する。<BR>
     *  <BR>
     * 以下の条件で管理者テーブルを検索し、取得した行 <BR>
     * オブジェクト（管理者行）を指定し、管理者インスタンスを生成し返却する。<BR>
     *  <BR>
     * [条件] <BR>
     *  <BR>
     * 証券会社コード = 証券会社.getInstitutionCode() <BR>
     * 管理者コード = 管理者コード<BR>
     *  <BR>
     * @@param l_institution - (証券会社)<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     */
    public WEB3Administrator(Institution l_institution, String l_strAdministratorCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3Administrator(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        AdministratorRow l_administratorRow = null;
        try
        {
            l_administratorRow =
                AdministratorDao.findRowByInstitutionCodeAdministratorCode(
                    l_institution.getInstitutionCode(),
                    l_strAdministratorCode);
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
        if(l_administratorRow == null)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "管理者行オブジェクト = null");
            throw l_sysException;
        }
        
        this.administratorParams = new AdministratorParams(l_administratorRow);
        log.exiting(STR_METHOD_NAME);
            
    }

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.管理者Paramsを返却する。<BR>
     * @@return Object
     * @@roseuid 40C3F2D30157
     */
    public Object getDataSourceObject()
    {
        return this.administratorParams;
    }

    /**
     * (getInstanceFromログイン情報)
     * （static method）<BR>
     * <BR>
     * ログイン情報より、ログインしている管理者の管理者インスタンスを <BR>
     * 取得する。管理者オブジェクトを生成する。 <BR>
     * <BR>
     * １） ログインＩＤ取得 <BR>
     * 　@   ログインセキュリティサービス（OpLoginSecurityService）より <BR>
     *      ログインＩＤを取得する。 <BR>
     * <BR>
     *         long l_loginId; <BR>
     *         OpLoginSecurityService l_opLoginSec = <BR>  
     *             (OpLoginSecurityService)Services.getService( <BR>
     *                 OpLoginSecurityService.class <BR>
     *                 ); <BR>
     *         l_loginId = l_opLoginSec.getLoginInfo().getLoginId(); <BR>
     *  <BR>
     * ２） ログインＩＤにて管理者テーブルを検索し、管理者行オブジェクトを <BR>
     *      取得する。ログインＩＤにて管理者テーブルを検索する <BR>
     *      （※ 管理者DAO.findRowByLoginId()）。<BR>
     *      取得した行オブジェクト（管理者行）を指定し、 <BR>
     *      管理者インスタンスを生成する。 <BR>
     *  <BR>
     * ３） 生成したインスタンスを返却する。 <BR>
     * @@return WEB3Administrator
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C3F16100BB
     */
    static public WEB3Administrator getInstanceFromLoginInfo()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInstanceFromLoginInfo()";

        //１）　@ログインＩＤ取得 
        //ログインセキュリティサービス（OpLoginSecurityService）よりログインＩＤを取得する。
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();

        //２）  ログインＩＤにて管理者テーブルを検索し、管理者行オブジェクトを
        //取得する。ログインＩＤにて管理者テーブルを検索する
        AdministratorRow l_administratorRow;
        try
        {
            l_administratorRow =
                AdministratorDao.findRowByLoginId(l_lngLoginId);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_administratorRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "管理者行オブジェクト = null");
        }
        //３）　@生成したインスタンスを返却する。
        return new WEB3Administrator(new AdministratorParams(l_administratorRow));

    }

    /**
     * (validate権限) <BR>
     * 管理者権限チェックを行う。 <BR>
     * （非推奨のメソッド）<BR>
     *  <BR> 
     * //TODO 既存部分の為のSTUB <BR> 
     * //TODO バックログ対応時にすべてvalidate権限(String, boolean)を<BR>
     * 使用するように修正する必要があります。 <BR>
     * @@roseuid 40C3F5C901A5
     */
    public void validateAuthority()
    {

    }
    
    /**
     * (validate権限) <BR>
     * 管理者権限チェックを行う。 <BR>
     *  <BR>
     * 以下の条件で管理者権限テーブルを検索する。<BR> 
     * 行が取得できなかった場合は、管理者に該当オペレーションの <BR>
     * 権限がないと判定し例外をスローする。<BR> 
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01056<BR>
     *  <BR>
     * [条件] <BR>
     *  <BR>
     * ○　@更新処理の場合（is更新 == true） <BR> 
     * 管理者権限テーブル.証券会社コード = this.管理者行.証券会社コード <BR>
     * 管理者権限テーブル.権限レベル = this.管理者行.権限レベル <BR>
     * 管理者権限テーブル.機@能カテゴリコード = 機@能カテゴリコード <BR>
     * 管理者権限テーブル.更新許可フラグ = BooleanEnum.TRUE <BR>
     *  <BR>
     * ○　@照会処理の場合（is更新 == false）<BR> 
     * 管理者権限テーブル.証券会社コード = this.管理者行.証券会社コード <BR>
     * 管理者権限テーブル.権限レベル = this.管理者行.権限レベル <BR>
     * 管理者権限テーブル.機@能カテゴリコード = 機@能カテゴリコード <BR>
     * 管理者権限テーブル.更新許可フラグ in （BooleanEnum.TRUE，BooleanEnum.FALSE）<BR> 
     *  <BR>
     * ※　@管理者権限ParamsクラスはDDLより自動生成する。<BR> 
     *  <BR>
     * @@param l_strTransactionCategory - (機@能カテゴリコード)<BR>
     *    ※ DBレイアウト「管理者権限テーブル.xls#（補足資料）<BR>
     *    機@能カテゴリコード一覧」参照。 
     * @@param l_isUpdate - (is更新)<BR>
     *   更新許可が必要な処理の場合はtrue、参照のみの場合はfalseを指定する。<BR> 
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateAuthority(String l_strTransactionCategory,boolean l_isUpdate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAuthority(String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            if (l_isUpdate)
            {
                //○　@更新処理の場合（is更新 == true） 
                // 管理者権限テーブル.証券会社コード = this.管理者行.証券会社コード 
                // 管理者権限テーブル.権限レベル = this.管理者行.権限レベル 
                // 管理者権限テーブル.機@能カテゴリコード = 機@能カテゴリコード 
                // 管理者権限テーブル.更新許可フラグ = BooleanEnum.TRUE 
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and permission_level = ? ");
                l_sbWhere.append(" and transaction_category = ? ");
                l_sbWhere.append(" and update_permission_flag = ? ");

                Object[] l_objWhere =
                    {
                        this.administratorParams.getInstitutionCode(),
                        this.administratorParams.getPermissionLevel(),
                        l_strTransactionCategory,
                        BooleanEnum.TRUE };

                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_lstRecords = l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                
                if(l_lstRecords.size() == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01056,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                 
                }

            }
            else
            {
                //○　@照会処理の場合（is更新 == false）
                //管理者権限テーブル.証券会社コード = this.管理者行.証券会社コード 
                //管理者権限テーブル.権限レベル = this.管理者行.権限レベル 
                //管理者権限テーブル.機@能カテゴリコード = 機@能カテゴリコード 
                //管理者権限テーブル.更新許可フラグ in （BooleanEnum.TRUE，BooleanEnum.FALSE）
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and permission_level = ? ");
                l_sbWhere.append(" and transaction_category = ? ");
                l_sbWhere.append(" and update_permission_flag in( ?,?)");

                Object[] l_objWhere =
                    {
                        this.administratorParams.getInstitutionCode(),
                        this.administratorParams.getPermissionLevel(),
                        l_strTransactionCategory,
                        BooleanEnum.FALSE,
                        BooleanEnum.TRUE};

                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_lstRecords = l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                
                if(l_lstRecords.size() == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01056,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                 
                }
            }
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
        
        log.exiting(STR_METHOD_NAME);
        return;

    }

    /**
     * (validateTradingPassword)<BR>
     * (validate取引パスワード)<BR>
     * 取引パスワードが正しいかのチェックを行う。<BR>
     * <BR>
     * 　@OpLoginSecurityServiceより、ログインタイプ属性を取得する。<BR>
     *  <BR>
     * －ログインタイプ属性.属性名 == 取引パスワード設定 <BR>
     *    （：TRADING_PWD_ENV）の属性値が”0：DEFAULT（取引 <BR>
     *    パスワード項目を使用しない）”の場合、ログインパスワードの <BR>
     *    照合※1する。<BR>
     *  <BR>
     * －ログインタイプ属性.属性名 == 取引パスワード設定 <BR>
     *    （：TRADING_PWD_ENV）の属性値が”1：取引パスワード使用” <BR>
     *    の場合、取引パスワードの照合※2する。<BR>
     *  <BR>
     * ※1 ログインパスワードの照合 <BR>
     * （OpLoginSecurityService.checkPassword() == false）の場合、<BR>
     * エラー結果を返却する。以外、正常終了を返却する。<BR>
     *  <BR>
     * ※2 取引パスワードの照合 <BR>
     * this.管理者行.取引パスワードを復号化（WEB3Crypt.decrypt()を使用）<BR>
     * する。復号化したパスワードと引数のパスワードが一致しない場合、<BR>
     * エラー結果を返却する。以外、正常終了を返却する。<BR>
     *  <BR>
     * @@param l_strPassword - 入力パスワード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40C3F66701D4
     */
    public void validateTradingPassword(String l_strPassword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPassword(String)";
        log.entering(STR_METHOD_NAME);
        
        //1) OpLoginSecurityServiceより、ログインタイプ属性を取得する
        
        //サービスを取得
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //ログインタイプ属性を取得する
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        
        //2) ログインパスワードの照合
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //（OpLoginSecurityService.checkPassword() == false）の場合、
            //エラー結果を返却する。以外、正常終了を返却する。
            if(l_securityService.checkPassword(l_strPassword) == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);     
            }

        }
        //2) 取引パスワードの照合
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            // this.管理者行.取引パスワードを復号化（WEB3Crypt.decrypt()を使用）
            //する。復号化したパスワードと引数のパスワードが一致しない場合、
            //エラー結果を返却する。以外、正常終了を返却する。
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strAdministratorPassword =
                l_crypt.decrypt(this.administratorParams.getTradingPassword());
            if ( ! l_strAdministratorPassword.equals(l_strPassword))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);   
            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワード設定(ログインタイプ属性) = " + l_strAttribute);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (get証券会社) <BR>
     * （getInstitution） <BR>
     * <BR>
     * this.管理者行.証券会社IDに該当する証券会社オブジェクトを返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Institution
     * @@throws WEB3SystemLayerException
     * @@roseuid 40C59C96004C
     */
    public Institution getInstitution() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInstitution()";

        long l_lngInstitionId = this.administratorParams.getInstitutionId();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Institution l_institution;
        try
        {
            //get証券会社オブジェクト
            l_institution =
                l_finApp.getAccountManager().getInstitution(l_lngInstitionId);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        
        return l_institution;
    }

    /**
     * (get証券会社コード) <BR>
     * （getInstitutionCode） <BR>
     * <BR>
     * this.管理者行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 40C59CCC03C7
     */
    public String getInstitutionCode()
    {
        return this.administratorParams.getInstitutionCode();
    }

    /**
     * (get管理者コード) <BR>
     * （getAdministratorCode） <BR>
     *  <BR>
     * this.管理者行.管理者コードを返却する。<BR>
     * @@return String
     * @@roseuid 40C7F5390203
     */
    public String getAdministratorCode()
    {
        return this.administratorParams.getAdministratorCode();
    }
    
    /**
     * (get部店コード) <BR>
     * （getBranchCode） <BR>
     *  <BR>
     * this.管理者行.部店コードを返却する。<BR>
     * @@return String
     */
    public String getBranchCode()
    {
        return this.administratorParams.getBranchCode();
    }

    /**
     * (isDIR管理者)<BR>
     * 管理者が"DIR管理者"である場合、trueを返却する。<BR> 
     * 管理者が"証券会社管理者"である場合、falseを返却する。<BR> 
     *  <BR>
     * １）　@管理者タイプテーブル検索 <BR>
     * 以下の条件で管理者タイプテーブルを検索し、該当する行の <BR>
     * 行オブジェクト（：管理者タイプParams）を返却する。 <BR>
     *  <BR>
     * [条件] <BR>
     * 管理者タイプテーブル.証券会社コード = this.管理者行.証券会社コード <BR>
     * 管理者タイプテーブル.権限レベル = this.管理者行.権限レベル <BR>
     *  <BR>
     * ※　@管理者タイプParamsクラスはDDLより自動生成する。<BR> 
     *  <BR>
     * ２）　@ＤＩＲ管理者フラグ返却 <BR>
     * 　@１）で取得した管理者タイプ行.ＤＩＲ管理者フラグを判定し、<BR>
     * 対応するboolean値を返却する。 <BR>
     * <BR>
     * 　@－（管理者タイプ行.ＤＩＲ管理者フラグ = 1 ）の場合、trueを返却する。<BR> 
     * 　@－（管理者タイプ行.ＤＩＲ管理者フラグ != 1 ）の場合、falseを返却する。<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return boolean
     * @@roseuid 40C7F5390203
     */
    public boolean isDirAdministrator() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isDirAdministrator()";        
        log.entering(STR_METHOD_NAME);
        
        //１）　@管理者タイプテーブル検索 
        //[検索条件] 
        //管理者タイプテーブル.証券会社コード = this.管理者行.証券会社コード 
        //管理者タイプテーブル.権限レベル = this.管理者行.権限レベル
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +"." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）　@ＤＩＲ管理者フラグ返却 
        //１）で取得した管理者タイプ行.ＤＩＲ管理者フラグを判定し、
        //対応するboolean値を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3DirAdminFlagDef.DIR_ADMINISTRATOR.equals(
            String.valueOf(l_administratorTypeRow.getDirAdminFlag()));
        
    }
    
    /**
     * (is全部店許可)<BR>
     *  <BR>
     * 該当管理者に全部店データを扱う権限があるかを判定する。<BR> 
     * 全部店データが扱える場合はtrue、自店データのみ扱える場合 <BR>
     * はfalseを返却する。 <BR>
     *  <BR>
     * １）　@管理者タイプテーブル検索 <BR>
     * 以下の条件で管理者タイプテーブルを検索し、該当する行の <BR>
     * 行オブジェクト（：管理者タイプParams）を返却する。 <BR>
     *  <BR>
     * [条件] <BR>
     * 管理者タイプテーブル.証券会社コード = this.管理者行.証券会社コード <BR>
     * 管理者タイプテーブル.権限レベル = this.管理者行.権限レベル <BR>
     *  <BR>
     * ※　@管理者タイプParamsクラスはDDLより自動生成する。<BR> 
     *  <BR>
     * ２）　@全部店許可フラグ返却 <BR>
     * １）で取得した管理者タイプ行.全部店許可フラグを判定し、<BR>
     * 対応するboolean値を返却する。 <BR>
     *  <BR>
     * －（管理者タイプ行.全部店許可フラグ == BooleanEnum.TRUE）の場合、<BR>
     * trueを返却する。 <BR>
     * －（管理者タイプ行.全部店許可フラグ == BooleanEnum.FALSE）の場合、<BR>
     * falseを返却する。<BR> 
     *  <BR> 
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public boolean isAllBranchsPermission()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isAllBranchsPermission()";
        log.entering(STR_METHOD_NAME);

        //１）　@管理者タイプテーブル検索
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());

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
        
        //２）　@全部店許可フラグ返却
        log.exiting(STR_METHOD_NAME);
        return BooleanEnum.TRUE.equals(
            l_administratorTypeRow.getAllBranchPermissionFlag());

    }
    
    /**
     * (validate部店権限)<BR>
     *  <BR>
     * 当該管理者が、指定の部店を取り扱えるかをチェックする。 <BR>
     *  <BR>
     * －全部店許可管理者の場合（this.is全部店許可() == true）<BR> 
     * 処理を行わず終了する。 <BR>
     *  <BR>
     * －自部店のみ取扱可の管理者の場合（this.is全部店許可 == false）<BR> 
     * this.get部店コード()，"000"（指定なし）以外の部店コードが引数に<BR> 
     * 含まれれば、部店権限がないと判定し例外をスローする。<BR> 
     * ※　@"000"：部店指定なし。<BR> 
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01074<BR> 
     * <BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * @@throws WEB3BaseException
     */
    public void validateBranchPermission(String[] l_strBranchCodes)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranchPermission(String[])";
        log.entering(STR_METHOD_NAME);
        
        //全部店許可管理者の場合（this.is全部店許可() == true）
        //処理を行わず終了する。
        if(isAllBranchsPermission() == true)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //自部店のみ取扱可の管理者の場合（this.is全部店許可 == false）
        //this.get部店コード()，"000"（指定なし）以外の部店コードが引数に
        //含まれれば、部店権限がないと判定し例外をスローする。
        int l_intLength = l_strBranchCodes.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if(( ! getBranchCode().equals(l_strBranchCodes[i])) 
                &&( ! WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCodes[i])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01074,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

        }       
             
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate部店権限)<BR>
     *  <BR>
     * 当該管理者が、指定の部店を取り扱えるかをチェックする。<BR>  
     *  <BR> 
     * －全部店許可管理者の場合（this.is全部店許可() == true）<BR>  
     * 処理を行わず終了する。<BR>  
     * <BR> 
     * －自部店のみ取扱可の管理者の場合（this.is全部店許可 == false）<BR>
     * （this.get部店コード() != 部店コード） and （部店コード != "000"）の<BR>
     * 場合、部店権限がないと判定し例外をスローする。<BR>
     * ※　@"000"：部店指定なし。 <BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_01074<BR> 
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@throws WEB3BaseException
     */
    public void validateBranchPermission(String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranchPermission(String)";
        log.entering(STR_METHOD_NAME);
        
        //全部店許可管理者の場合（this.is全部店許可() == true）
        //処理を行わず終了する。
        if(isAllBranchsPermission() == true)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //自部店のみ取扱可の管理者の場合（this.is全部店許可 == false）
        // （this.get部店コード() != 部店コード） and （部店コード != "000"）の
        //場合、部店権限がないと判定し例外をスローする。
        if( ( ! getBranchCode().equals(l_strBranchCode)) 
            &&( ! WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01074,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (get権限レベル)<BR>
     *  <BR>
     * this.管理者行.権限レベルを返却する。
     */
    public String getPermissionLevel()
    {
        return this.administratorParams.getPermissionLevel();
    }
    
    /**
     * (get管理者ＩＤ)<BR>
     *  <BR>
     * this.管理者行.管理者IDを返却する。
     */
    public long getAdministratorId()
    {
        return this.administratorParams.getAdministratorId();
    }
    
    /**
     * (getログインＩＤ)<BR>
     *  <BR>
     * this.管理者行.管理者ログインIDを返却する。
     */
    public long getLoginId()
    {
        return this.administratorParams.getLoginId();
    }
    
    /**
     * (get管理者) <BR>
     * （staticメソッド） <BR>
     *  <BR>
     * １）　@検索条件文字列の先頭に、証券会社条件を挿入する。<BR>
     *  <BR>
     * "証券会社ＩＤ = " + 管理者（オペレータ）.get証券会社ＩＤ(). 証券会社ＩＤ<BR> 
     *   + 検索条件文字列 <BR>
     *  <BR>
     * ２）　@管理者テーブル検索 <BR>
     * QueryProcessor.doFindAllQuery( )により、管理者行を取得する。<BR> 
     *  <BR>
     * [doFindAllQuery()に指定する引数] <BR>
     * rowType：　@管理者Row.TYPE <BR> 
     * where：　@１）で編集した検索条件文字列 <BR>
     * orderBy：　@ソート条件 <BR> 
     * conditions：　@null <BR> 
     * bindVars：　@検索条件データコンテナ <BR>
     *  <BR>
     * ３）　@検索結果より、条件に一致する要素の <BR>
     * 管理者List（：ArrayList）を編集する。<BR>
     *  <BR>
     * 管理者List（：ArrayList）を生成し、２）で取得した <BR>
     * 各要素について、３－１）～３－４）を実施する。<BR>
     *  <BR>
     * ３－１）　@対象要素の行オブジェクトを指定し、<BR>
     * 管理者オブジェクトを生成する。<BR>
     *  <BR>
     * ３－２）　@ログイン回数の判定 ※ログイン回数条件が指定されている場合<BR>
     * （ログインエラー回数 != null && ログインエラー回数 > 0） && <BR>
     * （管理者のログインエラー回数※1＜引数のログインエラー回数）であれば、<BR>
     * 対象要素について、以降の処理は行わない。（continue;） <BR>
     *  <BR>
     * ※1 管理者のログインエラー回数の取得 <BR>
     * 管理者.getログインＩＤ()にて取得したログインＩＤに一致する行を <BR>
     * ログインテーブルから取得する。取得した行のログインエラー回数。<BR>
     *  <BR>
     * ３－３）　@DIR管理者の判定 ※オペレータが通常管理者の場合、<BR>
     * DIR管理者の管理者行は取扱い不可。<BR>
     * （管理者（オペレータ）.isDIR管理者（） == false） && （３－１）で <BR>
     * 生成した管理者.isDIR管理者() == true）であれば、対象要素について、<BR>
     * 以降の処理は行わない。（continue;）<BR>
     *  <BR>
     * ３－４）　@部店の判定 ※オペレータが全部店許可でない場合、<BR>
     * 他部店の管理者行は取扱い不可。<BR>
     * （管理者（オペレータ）.is全部店許可 == false） &&（３－１）で生成した<BR>
     * 管理者.get部店コード() != 管理者（オペレータ）.get部店コード()）で <BR>
     * あれば、対象要素について、以降の処理は行わない。（continue;） <BR>
     *  <BR>
     * ３－５）　@管理者List（：ArrayList）に対象要素を追加（：add）する。<BR>
     *  <BR>
     * ４）　@管理者配列返却 <BR>
     * 管理者List（：ArrayList）を配列に変換（toArray()）し、返却する。<BR>
     *  <BR>
     * @@param l_operator - (管理者（オペレータ）) <BR>
     * @@param l_strWhere - (検索条件文字列) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_bindVars - (検索条件データコンテナ) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_strOrderBy - (ソート条件) <BR>
     *    ※ 指定しない場合null <BR>
     * @@param l_loginErrorTimes - (ログインエラー回数) <BR>
     *    ※ 指定しない場合null <BR>
     * @@throws WEB3SystemLayerException
     */
    static public WEB3Administrator[] getAdministrators(
        WEB3Administrator l_operator,
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy,
        Integer l_loginErrorTimes)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getAdministrators(WEB3Administrator, String, String[], String, Integer)";
        log.entering(STR_METHOD_NAME);

        //管理者（オペレータ）に証券会社ＩＤを取得 
        AdministratorParams l_administratorParams =
            (AdministratorParams) l_operator.getDataSourceObject();
        String l_strInstitutionId =
            String.valueOf(l_administratorParams.getInstitutionId());

        //１）　@検索条件文字列の先頭に、証券会社条件(証券会社ＩＤ)を挿入する。
        String l_strAdminitratorWhere;
        if (l_strWhere == null)
        {
            l_strAdminitratorWhere = " institution_id = ? ";
        }
        else
        {
            l_strAdminitratorWhere = " institution_id = ? " + l_strWhere;
        }
        String[] l_strAdminitratorBindVars;
        if (l_bindVars == null)
        {
            l_strAdminitratorBindVars = new String[] { l_strInstitutionId };
        }
        else
        {
            int l_intLength = l_bindVars.length;
            l_strAdminitratorBindVars = new String[l_intLength + 1];
            l_strAdminitratorBindVars[0] = l_strInstitutionId;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strAdminitratorBindVars[i + 1] = l_bindVars[i];
            }
        }

        try
        {
            //２）　@管理者テーブル検索 
            //QueryProcessor.doFindAllQuery( )により、管理者行を取得する。
            //  * rowType：　@管理者Row.TYPE 
            //  * where：　@１）で編集した検索条件文字列 
            //  * orderBy：　@ソート条件 
            //  * conditions：　@null 
            //  * bindVars：　@検索条件データコンテナ
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorRow.TYPE,
                    l_strAdminitratorWhere,
                    l_strOrderBy,
                    null,
                    l_strAdminitratorBindVars);

            List l_lstAdministrators = new ArrayList();

            //３）　@検索結果より、条件に一致する要素の 
            //管理者List（：ArrayList）を編集する。
            int l_intSize = l_lstRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //３－１）　@対象要素の行オブジェクトを指定し、
                //管理者オブジェクトを生成する。
                AdministratorRow l_tmpRow =
                    (AdministratorRow) l_lstRecords.get(i);
                AdministratorParams l_tmpParams = new AdministratorParams(l_tmpRow);
                WEB3Administrator l_tmpAdministrator = new  WEB3Administrator(l_tmpParams);

                //管理者のログインエラー回数の取得
                LoginRow l_loginRow = 
                    LoginDao.findRowByPk(l_tmpRow.getLoginId());

                //３－２）　@ログイン回数の判定
                if ((l_loginErrorTimes != null)
                    && (l_loginErrorTimes.intValue() > 0)
                    && (l_loginRow.getFailureCount() < l_loginErrorTimes.intValue()))
                {
                    continue;
                }
                
                //３－３）　@DIR管理者の判定
                if ((l_operator.isDirAdministrator() == false)
                    && (l_tmpAdministrator.isDirAdministrator() == true))
                {
                    continue;
                }
                
                //３－４）　@部店の判定
                if ((l_operator.isAllBranchsPermission() == false)
                    && (WEB3Toolkit.isEquals(l_operator.getBranchCode(),l_tmpAdministrator.getBranchCode()) == false))
                {
                    continue;
                }
                
                //３－５）　@管理者List（：ArrayList）に対象要素を追加（：add）する
                l_lstAdministrators.add(l_tmpAdministrator);

            }
            
            //４）　@管理者配列返却
            l_intSize = l_lstAdministrators.size();
            WEB3Administrator[] l_administrators = new WEB3Administrator[l_intSize];
            for(int i = 0; i < l_intSize; i ++)
            {
                l_administrators[i] = (WEB3Administrator)l_lstAdministrators.get(i);
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_administrators;
            
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Administrator.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * (getDIR管理者フラグ)<BR>
     * 管理者のDIR管理者フラグを返却する。<BR>
     *  <BR>
     * １）　@管理者タイプテーブル検索 <BR>
     * 　@以下の条件で管理者タイプテーブルを検索し、<BR>
     * 　@該当する行の行オブジェクト（：管理者タイプParams）を返却する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@管理者タイプテーブル.証券会社コード = this.管理者行.証券会社コード <BR>
     * 　@管理者タイプテーブル.権限レベル = this.管理者行.権限レベル <BR>
     * <BR>
     * 　@※　@管理者タイプParamsクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ２）　@ＤＩＲ管理者フラグ返却 <BR>
     * 　@１）で取得した管理者タイプ行.ＤＩＲ管理者フラグを返却する。 <BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return String
     */
    public String getDIRAdminFlag() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getDIRAdminFlag()";
        log.entering(STR_METHOD_NAME);

        //１）　@管理者タイプテーブル検索
        //[検索条件]
        //管理者タイプテーブル.証券会社コード = this.管理者行.証券会社コード
        //管理者タイプテーブル.権限レベル = this.管理者行.権限レベル
        AdministratorTypeRow l_administratorTypeRow;
        try
        {
            l_administratorTypeRow =
                AdministratorTypeDao.findRowByPk(
                    this.administratorParams.getInstitutionCode(),
                    this.administratorParams.getPermissionLevel());
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //２）　@ＤＩＲ管理者フラグ返却
        //１）で取得した管理者タイプ行.ＤＩＲ管理者フラグを返却する。
        log.exiting(STR_METHOD_NAME);
        return String.valueOf(l_administratorTypeRow.getDirAdminFlag());
    }
}
@
