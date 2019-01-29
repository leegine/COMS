head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者タイプ(WEB3AdminMCAdminType)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 王敏 (中訊) 新規作成
                 : 2006/08/24 郭英 (中訊) モデルNo.021, 022, DB更新仕様No006
*/

package webbroker3.adminmc;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者タイプ)<BR>
 * 管理者タイプクラス<BR>
 * @@author 王敏
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCAdminType implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminType.class);

    /**
     * (管理者タイプ行)<BR>
     * 管理者タイプ行オブジェクト <BR>
     * <BR>
     * ※ 管理者タイプParamsクラスはDDLより自動生成する。<BR>
     * <BR>
     */
    private AdministratorTypeParams administratorTypeParams;

    /**
     * @@roseuid 419AA3DF0203
     */
    public WEB3AdminMCAdminType()
    {

    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.管理者タイプ行を返却する。 <BR>
     * <BR>
     * @@return Object
     * @@roseuid 4175F26300AE
     */
    public java.lang.Object getDataSourceObject()
    {
         return this.administratorTypeParams;
    }

    /**
     * (getDIR管理者フラグ)<BR>
     * DIR管理者フラグを取得する。<BR>
     * <BR>
     * this.管理者タイプ行.DIR管理者フラグを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getDIRAdminFlag()
    {
         return Integer.toString(this.administratorTypeParams.getDirAdminFlag());
    }
    
    /**
     * (get権限レベル)<BR>
     * 権限レベルを取得する。<BR>
     * <BR>
     * this.管理者タイプ行.権限レベルを返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 4177236C035B
     */
    public String getPermissionLevel()
    {
         return this.administratorTypeParams.getPermissionLevel();
    }

    /**
     * (get権限レベル名称)<BR>
     * 権限レベル名称を取得する。<BR>
     * <BR>
     * this.管理者タイプ行.権限レベル名称を返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 417607400050
     */
    public String getPermissionLevelName()
    {
         return this.administratorTypeParams.getPermissionLevelName();
    }

    /**
     * (isDIR管理者)<BR>
     * DIR管理者かを判定する。<BR>
     * <BR>
     * 　@－（this.管理者タイプ行.DIR管理者フラグ  = 1 ）の場合trueを返却する。<BR>
     * 　@－（this.管理者タイプ行.DIR管理者フラグ != 1 ）の場合falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 41760752010C
     */
    public boolean isDIRAdministrator()
    {
        final String STR_METHOD_NAME = "isDIRAdministrator()";
        log.entering(STR_METHOD_NAME);
        
         if (this.administratorTypeParams.getDirAdminFlag() == 1)
         {
             log.exiting(STR_METHOD_NAME);
             return true; 
         }

         log.exiting(STR_METHOD_NAME);
         return false;
    }

    /**
     * (is全部店許可)<BR>
     * 全部店許可かを判定する。<BR>
     * <BR>
     * 　@－（this.管理者タイプ行.全部店許可フラグ == BooleanEnum.TRUE）の場合trueを返却する。<BR>
     * 　@－（this.管理者タイプ行.全部店許可フラグ == BooleanEnum.FALSE）の場合falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 4176076A03BB
     */
    public boolean isAllBranchPermission()
    {
         BooleanEnum l_allbranchpermissionflag = this.administratorTypeParams.getAllBranchPermissionFlag();

         if (BooleanEnum.TRUE.equals(l_allbranchpermissionflag))
         {
             return true;
         }
         else
         {
             return false;
         }
    }

    /**
     * (管理者タイプ)<BR>
     * コンストラクタ。<BR>
     * 証券会社コード，権限レベルに該当する行を、管理者タイプテーブルより検索する。 <BR>
     * 該当行がない場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *     tag:          BUSINESS_ERROR_00398 <BR>
     * <BR>
     * 検索結果の管理者タイプ行オブジェクトを引数に指定して、コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType
     * @@roseuid 4176084A00DD
     */
    public WEB3AdminMCAdminType(String l_strInstitutionCode, String l_strPermissionLevel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AdminMCAdminType(String l_strInstitutionCode, String l_strPermissionLevel)";
        log.entering(STR_METHOD_NAME);

        if ((l_strInstitutionCode == null) || (l_strPermissionLevel == null))
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME);
        }

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_sbQueryString = "institution_code = ? and permission_level = ?";

            Object[] l_queryDataContainer = {l_strInstitutionCode, l_strPermissionLevel};

             l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorTypeRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            //該当行がない場合は、例外をスローする。
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                                                                  this.getClass().getName() + STR_METHOD_NAME);
        }

        AdministratorTypeParams  l_admntpprms = null;

        l_admntpprms = (AdministratorTypeParams) l_lisRecords.get(0);

        this.administratorTypeParams = l_admntpprms;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (管理者タイプ)<BR>
     * コンストラクタ。<BR>
     * 指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * <BR>
     * ※　@管理者タイプParamsはDDLより自動生成する。 <BR>
     * <BR>
     * @@param l_adminTypeLine - (管理者タイプ行)<BR>
     * 管理者タイプ行オブジェクト<BR>
     * <BR>
     * ※　@管理者タイプParamsはDDLより自動生成する。 <BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType
     * @@roseuid 4176099601B8
     */
    public WEB3AdminMCAdminType(AdministratorTypeParams l_administratorTypeParams)
    {
        this.administratorTypeParams = l_administratorTypeParams;
    }

    /**
     * (saveNew管理者タイプ)<BR>
     * （staticメソッド） <BR>
     * 引数の内容で管理者タイプテーブルに新規行を挿入（Insert）する。 <BR>
     * <BR>
     * 　@[挿入する行の内容] <BR>
     * 　@管理者タイプ.証券会社コード = 証券会社コード <BR>
     * 　@管理者タイプ.権限レベル = 権限レベル <BR>
     * 　@管理者タイプ.権限レベル名称 = 権限レベル名称 <BR>
     * 　@管理者タイプ.DIR管理者フラグ = DIR管理者フラグ(*1)(*2) <BR>
     * 　@管理者タイプ.全部店許可フラグ = 全部店許可フラグ(*3) <BR>
     * 　@管理者タイプ.更新者コード = 管理者コード <BR>
     * 　@管理者タイプ.作成日時 = 処理日時（※ TradingSystem.getSystemTimestamp()） <BR>
     * 　@管理者タイプ.更新日時 = 処理日時（※ TradingSystem.getSystemTimestamp()） <BR>
     * <BR>
     * (*1) DIR管理者フラグの値 <BR>
     * 通常管理者の場合　@　@　@　@　@　@： "0" <BR>
     * DIR管理者の場合　@　@　@　@　@　@ ： "1" <BR>
     * 通常管理者（申請者）の場合： "2" <BR>
     * 通常管理者（承認者）の場合： "3" <BR>
     * <BR>
     * (*2) DIR管理者フラグのデータ型  <BR>
     * 文字列から数値型へ変換する。 <BR>
     * <BR>
     * (*3) trueの場合、BooleanEnum.TRUE，falseの場合、BooleanEnum.FALSE。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_strPermissionLevelName - (権限レベル名称)<BR>
     * 権限レベル名称<BR>
     * @@param l_strDIRAdminFlag - (DIR管理者フラグ)<BR>
     * DIR管理者フラグ <BR>
     * <BR>
     * DIR管理者の場合　@　@　@　@　@　@ ： "1" <BR>
     * 通常管理者の場合　@　@　@　@　@　@： "0" <BR>
     * 通常管理者（申請者）の場合： "2" <BR>
     * 通常管理者（承認者）の場合： "3"<BR>
     * @@param l_blnAllBranchPermissionFlag - (全店許可フラグ)<BR>
     * 全部店許可フラグ<BR>
     * <BR>
     * 全部店許可の場合true，以外false。<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@roseuid 417710510138
     */
    public static void saveNewAdminType(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String l_strPermissionLevelName, 
        String l_strDIRAdminFlag, 
        boolean l_blnAllBranchPermissionFlag, 
        String l_strAdministratorCode) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveNewAdminType(String l_strInstitutionCode, String l_strPermissionLevel, String l_strPermissionLevelName, String l_strDIRAdminFlag, boolean l_blnAllBranchPermissionFlag, String l_strAdministratorCode))";
         log.entering(STR_METHOD_NAME);

         AdministratorTypeParams l_administratortypeparams = new AdministratorTypeParams();
         //管理者タイプ.証券会社コード = 証券会社コード
         l_administratortypeparams.setInstitutionCode(l_strInstitutionCode);
         //管理者タイプ.権限レベル = 権限レベル
         l_administratortypeparams.setPermissionLevel(l_strPermissionLevel);
         //管理者タイプ.権限レベル名称 = 権限レベル名称
         l_administratortypeparams.setPermissionLevelName(l_strPermissionLevelName);
         //管理者タイプ.DIR管理者フラグ = DIR管理者フラグ 
         l_administratortypeparams.setDirAdminFlag( Integer.parseInt(l_strDIRAdminFlag));

         //管理者タイプ.全部店許可フラグ = 全部店許可フラグ
         if (l_blnAllBranchPermissionFlag)
         {
              l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
         }
         else
         {
              l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
         }
         //管理者タイプ.更新者コード = 管理者コード
         l_administratortypeparams.setLastUpdater(l_strAdministratorCode);
         //管理者タイプ.作成日時 = 処理日時
         l_administratortypeparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
         //管理者タイプ.更新日時 = 処理日時
         l_administratortypeparams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

         //this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。
         try
         {
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             l_queryProcessor.doInsertQuery(l_administratortypeparams);
         }
         catch (DataQueryException l_ex)
         {
             log.error("DBへのアクセスに失敗しました。", l_ex);
 
             log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DBへのアクセスに失敗しました。", l_ex);
 
             log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save管理者タイプ)<BR>
     * （staticメソッド） <BR>
     * 引数の内容で管理者タイプテーブルを更新（Update）する。 <BR>
     * <BR>
     * 　@[更新内容] <BR>
     * 　@管理者タイプ.権限レベル名称 = 権限レベル名称 <BR>
     * 　@管理者タイプ.DIR管理者フラグ = DIR管理者フラグ(*1)(*2) <BR>
     * 　@管理者タイプ.全部店許可フラグ = 全部店許可フラグ(*3) <BR>
     * 　@管理者タイプ.更新者コード = 管理者コード <BR>
     * 　@管理者タイプ.更新日時 = 処理日時（※ TradingSystem.getSystemTimestamp()） <BR>
     * <BR>
     * (*1) DIR管理者フラグの値 <BR>
     * 通常管理者の場合　@　@　@　@　@　@： "0" <BR>
     * DIR管理者の場合　@　@　@　@　@　@ ： "1" <BR>
     * 通常管理者（申請者）の場合： "2" <BR>
     * 通常管理者（承認者）の場合： "3" <BR>
     * <BR>
     * (*2) DIR管理者フラグのデータ型  <BR>
     * 文字列から数値型へ変換する。 <BR>
     * <BR>
     * (*3) trueの場合、BooleanEnum.TRUE，falseの場合、BooleanEnum.FALSE。 <BR>
     * <BR>
     * 　@[更新対象行] <BR>
     * 　@管理者タイプ.証券会社コード = 証券会社コード And <BR>
     * 　@管理者タイプ.権限レベル = 権限レベル <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_strPermissionLevelName - (権限レベル名称)<BR>
     * 権限レベル名称<BR>
     * @@param l_strDIRAdminFlag - (DIR管理者フラグ)<BR>
     * DIR管理者フラグ <BR>
     * <BR>
     * DIR管理者の場合　@　@　@　@　@　@ ： "1" <BR>
     * 通常管理者の場合　@　@　@　@　@　@： "0" <BR>
     * 通常管理者（申請者）の場合： "2" <BR>
     * 通常管理者（承認者）の場合： "3"<BR>
     * @@param l_blnAllBranchPermissionFlag - (全店許可フラグ)<BR>
     * 全部店許可フラグ<BR>
     * <BR>
     * 全部店許可の場合true，以外false。<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@roseuid 4177125F001F
     */
    public static void saveAdminType(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String l_strPermissionLevelName, 
        String l_strDIRAdminFlag, 
        boolean l_blnAllBranchPermissionFlag, 
        String l_strAdministratorCode) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveAdminType(String l_strInstitutionCode, String l_strPermissionLevel, String l_strPermissionLevelName, String l_strDIRAdminFlag, boolean l_blnAllBranchPermissionFlag, String l_strAdministratorCode)";
         log.entering(STR_METHOD_NAME);
         try
         {
         	String l_strWhere = " institution_code = ? and permission_level = ?";
         	String l_strOrderBy = "";
         	Object[] l_objWhereValues = {l_strInstitutionCode, l_strPermissionLevel};
             List l_returnList = new ArrayList();

             // データ査詢
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorTypeRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

             AdministratorTypeParams l_administratortypeparams = null;

             l_administratortypeparams = new AdministratorTypeParams((AdministratorTypeRow) l_returnList.get(0));

             //管理者タイプ.権限レベル名称 = 権限レベル名称
             l_administratortypeparams.setPermissionLevelName(l_strPermissionLevelName);
             //管理者タイプ.DIR管理者フラグ = DIR管理者フラグ 
             l_administratortypeparams.setDirAdminFlag( Integer.parseInt(l_strDIRAdminFlag));
             //管理者タイプ.全部店許可フラグ = 全部店許可フラグ
             if (l_blnAllBranchPermissionFlag)
             {
                  l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
             }
             else
             {
                  l_administratortypeparams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
             }
             //管理者タイプ.更新者コード = 管理者コード
             l_administratortypeparams.setLastUpdater(l_strAdministratorCode);
             //管理者タイプ.更新日時 = 処理日時
             l_administratortypeparams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());

             //this.（管理者共通）アップロード行 の内容でDBに行を挿入（insert）する。
             l_processor.doUpdateQuery(l_administratortypeparams);
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "管理者タイプを検索 error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "管理者タイプを検索  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }

         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveDelete管理者タイプ)<BR>
     * （staticメソッド）<BR>
     * 引数の内容に該当する管理者タイプ行を物理DBより削除（Delete）する。<BR>
     * <BR>
     * 　@[更新対象行]<BR>
     * 　@管理者タイプ.証券会社コード = 証券会社コード And<BR>
     * 　@管理者タイプ.権限レベル = 権限レベル<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@roseuid 4177136F00DA
     */
    public static void saveDeleteAdminType(String l_strInstitutionCode, String l_strPermissionLevel) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "saveDeleteAdminType(String l_strInstitutionCode, String l_strPermissionLevel)";
         log.entering(STR_METHOD_NAME);

         try
         {
         	String l_strWhere = " institution_code = ? and permission_level = ?";
            Object[] l_objWhereValues = {l_strInstitutionCode, l_strPermissionLevel};

         	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             l_queryProcessor.doDeleteAllQuery(AdministratorTypeRow.TYPE,
                                                           l_strWhere.toString(),
                                                           l_objWhereValues);
         }
         catch (DataException de)
         {
             log.debug(STR_METHOD_NAME, de);
             throw new WEB3SystemLayerException(
                                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                   WEB3AdminMCAdminType.class.getName() + "." + STR_METHOD_NAME,
                                   de.getMessage(),
                                   de);
         }

         log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get管理者タイプ)<BR>
     * （staticメソッド）<BR>
     * <BR>
     * 以下の条件にて、管理者タイプテーブルを検索する。<BR>
     * 検索結果の行オブジェクトを指定し管理者タイプオブジェクトを生成，配列にて返却する。<BR>
     * <BR>
     * 　@[条件①@] ※DIR管理者の場合（isDIR管理者（オペレータ） == true）<BR>
     * 　@管理者タイプテーブル.証券会社コード = 証券会社コード<BR>
     * <BR>
     * 　@[条件②] ※DIR管理者でない場合（isDIR管理者（オペレータ） == false）<BR>
     * 　@管理者タイプテーブル.証券会社コード = 証券会社コード And<BR>
     * 　@管理者タイプテーブル.DIR管理者フラグ !=  1 (DIR管理者以外)<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@引数のソート条件の通り<BR>
     * 　@※ （ソート条件 == null）の場合、指定しない。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@param l_blnIsDIRAdministrator - (isDIR管理者（オペレータ）)<BR>
     * isDIR管理者（オペレータ）<BR>
     * <BR>
     * ※ ログイン中の管理者がDIR管理者であるかの判定。<BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminType[]
     * @@roseuid 41760A300215
     */
    public static WEB3AdminMCAdminType[] getAdminType(String l_strInstitutionCode, String l_strSortCond, boolean l_blnIsDIRAdministrator)
    throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "getAdminType(String l_strInstitutionCode, String l_strSortCond, boolean l_blnIsDIRAdministrator)";
         log.entering(STR_METHOD_NAME);
         
         String l_strWhere = "";
         Object[] l_objWhereValues = null;
         List l_returnList = new ArrayList();
        WEB3AdminMCAdminType[] l_adminmcadmintypes = null;
        
         try
         {
             if (l_blnIsDIRAdministrator)
             {
                 l_strWhere = " institution_code = ?";
                 l_objWhereValues = new Object[1];
                 l_objWhereValues[0] = l_strInstitutionCode;
             }
             else
             {
                 l_strWhere = " institution_code = ? and dir_admin_flag <> ?";
                 l_objWhereValues = new Object[2];
                 l_objWhereValues[0] = l_strInstitutionCode;
                 l_objWhereValues[1] = new Integer(1);
             }
             
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorTypeRow.TYPE, l_strWhere, l_strSortCond, null, l_objWhereValues);

             int l_intSize = l_returnList.size();
             l_adminmcadmintypes = new WEB3AdminMCAdminType[l_intSize];
             
             for (int i = 0; i < l_intSize; i++)
             {
                 l_adminmcadmintypes[i] = new WEB3AdminMCAdminType ((AdministratorTypeParams)l_returnList.get(i));
             }
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "管理者タイプを検索 error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "管理者タイプを検索  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }
         
         
         log.exiting(STR_METHOD_NAME);
         return l_adminmcadmintypes;
    }
    /**
     * (validate管理者タイプ削除)<BR>
     * 管理者タイプ削除可能チェック<BR>
     * <BR>
     * 　@管理者テーブルを以下の条件で検索する。<BR>
     * 　@該当行が存在すれば、登録中の管理者が使用している管理者タイプであると判定し、例外をスローする。<BR>
     *   class :  WEB3BusinessLayerException <BR>
     *     tag :             BUSINESS_ERROR_01282<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@管理者.証券会社コード = this.管理者タイプ行.証券会社コード And<BR>
     * 　@管理者.権限レベル = this.管理者タイプ行.権限レベル<BR>
     * <BR>
     * <BR>
     * @@return Void
     * @@roseuid 41A6C0A20164
     */
    public void validateAdminTypeDelete() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "validateAdminTypeDelete()";
         log.entering(STR_METHOD_NAME);
         
         String l_strWhere = "institution_code = ? and permission_level = ?";
         String l_strOrderBy = "";
         Object[] l_objWhereValues = {this.administratorTypeParams.getInstitutionCode(), this.administratorTypeParams.getPermissionLevel()};
         List l_returnList = new ArrayList();
         
         try
         {
             QueryProcessor l_processor = Processors.getDefaultProcessor();
             l_returnList = l_processor.doFindAllQuery(AdministratorRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

             if (l_returnList.size() > 0)
             {
                 log.error("「登録中の管理者が使用している管理者タイプである」の例外をスローする。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                                                       WEB3ErrorCatalog.BUSINESS_ERROR_01282,
                                                       this.getClass().getName() + STR_METHOD_NAME);
             }
         }
         catch (DataQueryException l_dqe)
         {
             String l_strMessage = "管理者タイプを検索 error";
             log.error(l_strMessage, l_dqe);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dqe.getMessage(),
                                                                l_dqe);
         }
         catch (DataNetworkException l_dne)
         {
             String l_strMessage = "管理者タイプを検索  error";
             log.error(l_strMessage, l_dne);

             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                                                WEB3AdminMCAdminType.class.getName() + STR_METHOD_NAME,
                                                                l_dne.getMessage(),
                                                                l_dne);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
}
@
