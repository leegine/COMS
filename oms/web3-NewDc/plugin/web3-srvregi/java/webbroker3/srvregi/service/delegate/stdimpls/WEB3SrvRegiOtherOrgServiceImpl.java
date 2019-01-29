head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用外部連携サービスImpl(WEB3SrvRegiOtherOrgServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 武波 新規作成 モデル310,313,314,315,317,318,319,320
Revision History : 2008/02/26 武波 仕様変更 モデル321
Revision History : 2008/03/03 武波 仕様変更 モデル330,343
Revision History : 2008/03/03 武波 仕様変更 モデル340
Revision History : 2008/03/19 武波 仕様変更 モデル354,356,358
Revision History : 2008/03/26 武波 実装の問題002
Revision History : 2008/03/28 武波 仕様変更 モデル364
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.OtherOrgInfoAdminRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用外部連携サービスImpl)<BR>
 * サービス利用外部連携サービス実装クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3SrvRegiOtherOrgServiceImpl
    implements WEB3SrvRegiOtherOrgService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiOtherOrgServiceImpl.class);

    /**
     * サービス利用外部連携サービスImpl<BR>
     */
    public WEB3SrvRegiOtherOrgServiceImpl()
    {

    }

    /**
     * (get外部連携情報)<BR>
     * 外部連携情報管理テーブルより、データを取得する。<BR>
     * <BR>
     * 1) 以下の条件で「外部連携情報管理テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * サービス区分 = 引数.サービス区分( )<BR>
     * 証券会社コード = 引数.証券会社コード<BR>
     * 部店コード = 引数.部店コード<BR>
     * 口座コード = 引数.口座コード<BR>
     * ステータス = 0：使用中<BR>
     * <BR>
     * 2) 検索結果が2件以上の場合、例外をスローする。<BR>
     * <BR>
     * 3) 戻り値の生成<BR>
     * 　@3-1) 検索結果が0件の場合、nullを返却する。<BR>
     * <BR>
     * 　@3-2) 検索結果が1件の場合、
     * 　@　@上記検索結果である外部連携情報Paramsオブジェクトを引数に、<BR>
     * 　@　@外部連携情報管理クラスのコンストラクタをコールする。<BR>
     * <BR>
     * 4) 引数.is行ロック=trueの場合<BR>
     * 　@　@生成した外部連携情報管理オブジェクト.createForUpdateParams( ) をコールする。<BR>
     * <BR>
     * 5) 生成した外部連携情報管理オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * is行ロック<BR>
     * true：行ロックをする。　@false：行ロックをしない。<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        String l_strServiceDiv,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfo(String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件で「外部連携情報管理テーブル」を検索する。
        //（引数.is行ロックがtrueの場合、select for updateで検索を行う。)
        //サービス区分 = 引数.サービス区分( )
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //口座コード = 引数.口座コード
        //ステータス = 0：使用中
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" srv_div = ? and ");

        l_sbQueryString.append(" institution_code = ? and ");

        l_sbQueryString.append(" branch_code = ? and ");

        l_sbQueryString.append(" account_code = ? and ");

        l_sbQueryString.append(" status = ? ");

        Object[] l_queryContainers = {l_strServiceDiv, l_strInstitutionCode,
            l_strBranchCode, l_strAccountCode, WEB3OtherOrgStatusDef.USING};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    null,
                    l_queryContainers);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2) 検索結果が2件以上の場合、例外をスローする。
        if (l_lisOtherOrgInfoAdminRows.size() > 1)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //3) 戻り値の生成
        //3-1) 検索結果が0件の場合、nullを返却する。
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3-2) 検索結果が1件の場合、
        //上記検索結果である外部連携情報Paramsオブジェクトを引数に、
        //外部連携情報管理クラスのコンストラクタをコールする。
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //4) 引数.is行ロック=trueの場合
        //生成した外部連携情報管理オブジェクト.createForUpdateParams( ) をコールする。
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //5) 生成した外部連携情報管理オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (get外部連携情報)<BR>
     * 引数で指定された通番、サービス区分に該当する<BR>
     * 外部連携情報管理オブジェクトを返す。<BR>
     * <BR>
     * 1)　@外部連携情報管理テーブルを検索し、外部連携情報管理Paramsを取得する。<BR>
     * 　@　@（引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@　@通番=引数.通番 and<BR>
     * 　@　@　@　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * <BR>
     * 　@1-1)　@検索結果=0件の場合<BR>
     * 　@　@　@　@nullを返却する。<BR>
     * 　@1-2)　@検索結果=1件の場合<BR>
     * 　@　@　@　@外部連携情報管理クラスのコンストラクタをコールし、<BR>
     * 　@　@　@　@　@　@　@外部連携情報管理オブジェクトを生成する。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@外部連携情報管理Row: 取得した外部連携情報管理Params<BR>
     * <BR>
     * 2)　@引数.is行ロック=trueの場合<BR>
     * 　@　@外部連携情報管理オブジェクト.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 3)　@外部連携情報管理オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * is行ロック<BR>
     * true：行ロックをする。　@false：行ロックをしない。<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfo(long, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 外部連携情報管理テーブルを検索し、外部連携情報管理Paramsを取得する。
        // （引数.is行ロックがtrueの場合、select for updateで検索を行う。)
        // [検索条件]
        //通番=引数.通番 and
        //サービス区分=引数.サービス区分
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" sequence_number = ? and ");

        l_sbQueryString.append(" srv_div = ? ");

        Object[] l_queryContainers = {new Long(l_lngSequenceNumber), l_strServiceDiv};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    null,
                    l_queryContainers);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1-1) 検索結果=0件の場合
        //nullを返却する。
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //1-2) 検索結果=1件の場合
        //外部連携情報管理クラスのコンストラクタをコールし、外部連携情報管理オブジェクトを生成する。
        //[引数]
        //外部連携情報管理Row: 取得した外部連携情報管理Params
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //2) 引数.is行ロック=trueの場合
        //外部連携情報管理オブジェクト.createForUpdateParams( )をコールする。
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //3) 外部連携情報管理オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (get外部連携未使用件数)<BR>
     * 外部連携情報管理テーブルのステータスが未使用のレコードの件数を返却<BR>
     * <BR>
     * 1) 以下の条件の「外部連携情報管理テーブル」のレコード件数を取得する。<BR>
     * [検索条件]<BR>
     * サービス区分 = 引数.サービス区分( )<BR>
     * ステータス = 9（未使用）<BR>
     * <BR>
     * 2）検索結果の件数を返却する。<BR>
     * 検索結果 = 0 の場合、0を返却する。<BR>
     * <BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@return Long
     * @@throws WEB3BaseException
     */
    public Long getOtherOrgUnUsedCount(
        String l_strServiceDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgUnUsedCount(String)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件の「外部連携情報管理テーブル」のレコード件数を取得する。
        //サービス区分 = 引数.サービス区分( )
        //ステータス = 9（未使用）
        String l_strQueryString = " srv_div = ? and status = ? ";

        Object[] l_queryContainers = {l_strServiceDiv, WEB3OtherOrgStatusDef.DEFAULT};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                OtherOrgInfoAdminRow.TYPE,
                l_strQueryString,
                l_queryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2）検索結果の件数を返却する。
        //検索結果 = 0 の場合、0を返却する。
        log.exiting(STR_METHOD_NAME);
        return new Long(l_lisOtherOrgInfoAdminRows.size());
    }

    /**
     * (get外部連携情報一覧)<BR>
     * 指定された条件に合致する外部連携情報管理ﾃｰﾌﾞﾙを検索し、<BR>
     * その結果を外部連携情報Paramsオブジェクトの配列にして返却する。<BR>
     * <BR>
     * 1) ソート条件の作成<BR>
     * 　@引数.ソート条件がnull以外であり、かつ要素数＞0の場合、<BR>
     * 　@引数.ソート条件の件数分、以下を繰り返す。<BR>
     * 　@1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * <BR>
     * 　@　@　@　@・通番　@　@　@　@　@　@　@　@=外部連携情報管理テーブル.通番<BR>
     * 　@　@　@　@・ID 　@　@　@　@　@　@　@　@　@=外部連携情報管理テーブル.ID<BR>
     * 　@　@　@　@・ステータス　@　@　@　@　@=外部連携情報管理テーブル.ステータス<BR>
     * 　@　@　@　@・部店コード　@　@　@　@ =外部連携情報管理テーブル.部店コード<BR>
     * 　@　@　@　@・口座コード　@　@　@　@ =外部連携情報管理テーブル.口座コード<BR>
     * 　@　@　@　@・適用期間From　@　@=外部連携情報管理テーブル.適用期間From<BR>
     * 　@　@　@　@・適用期間To　@　@　@ =外部連携情報管理テーブル.適用期間To<BR>
     * 　@　@　@　@・最終更新日　@　@　@ =外部連携情報管理テーブル.更新日付<BR>
     * 　@　@　@　@・最終更新者　@　@　@ =外部連携情報管理テーブル.更新者コード<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。<BR>
     * <BR>
     * 2) 以下の検索条件で、「外部連携情報管理テーブル」を検索する。<BR>
     * 　@[検索条件] <BR>
     * 　@　@○サービス区分=引数.サービス区分<BR>
     * 　@　@○証券会社コード=引数.証券会社コード<BR>
     * 　@　@○部店コード=null　@又は　@部店コード=引数.部店コード(*1)<BR>
     * <BR>
     * 　@　@○通番　@=　@引数.通番 ---------（ただし、引数.通番がnullでは無い場合に限る）<BR>
     * 　@　@○ID　@=　@引数.ID ---------（ただし、引数.IDがnullでは無い場合に限る）<BR>
     * 　@　@○ステータス　@=　@引数.ステータス ---------（ただし、引数.ステータスがnullでは無い場合に限る）<BR>
     * 　@　@○口座コード　@like　@引数.口座コード% ---------（ただし、引数.口座コードがnullでは無い場合に限る）<BR>
     * 　@　@○適用期間From　@≧　@引数.適用開始日（自） -（ただし、引数.適用開始日（自）がnullでは無い場合に限る）<BR>
     * 　@　@○適用期間From　@≦　@引数.適用開始日（至） -（ただし、引数.適用開始日（至）がnullでは無い場合に限る）<BR>
     * <BR>
     * 　@[並び順]<BR>
     * 　@　@1)で生成したソート条件(*3)<BR>
     * <BR>
     * (*1)引数.部店について<BR>
     * 　@・引数.部店コードの要素数が１しかない場合、以下のような条件で検索を行う。<BR>
     * 　@　@　@"部店コード=引数.部店コード　@and ..."<BR>
     * 　@・引数.部店コードの要素数が複数あった場合、以下のような条件で検索を行う。<BR>
     * 　@　@　@"部店コード in (引数.部店コード[n]、引数.部店コード[n+1]...)"<BR>
     * <BR>
     * (*2)引数.ソート条件=nullの場合、適用期間Fromの降順を指定する。<BR>
     * <BR>
     * <BR>
     * 3) 2)の検索結果を返却する。<BR>
     * <BR>
     * @@param l_strSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strID - (ID)<BR>
     * ID<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード一覧<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_tsAppliStartDate - (適用開始日（自）)<BR>
     * 適用開始日（自）<BR>
     * @@param l_tsAppliEndDate - (適用開始日（至）)<BR>
     * 適用開始日（至）<BR>
     * @@param l_sortKeys - (ソート条件)<BR>
     * 対象項目<BR>
     * ≪照会の場合≫<BR>
     * 　@通番／ID／ステータス／部店／顧客／適用開始日／適用終了日／最終更新日／最終更新者<BR>
     * ≪ﾀﾞｳﾝﾛｰﾄﾞの場合≫<BR>
     * 　@通番<BR>
     * @@return OtherOrgInfoAdminParams[]
     * @@throws WEB3BaseException
     */
    public OtherOrgInfoAdminParams[] getOtherOrgInfoList(
        String l_strSequenceNumber,
        String l_strServiceDiv,
        String l_strID,
        String l_strStatus,
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        WEB3SrvRegiSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfoList(String, String, String, String, String,"
            + " String[], String, Timestamp, Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        //1) ソート条件の作成
        //引数.ソート条件がnull以外であり、かつ要素数＞0の場合、
        //引数.ソート条件の件数分、以下を繰り返す。
        String l_strOrderBy = "";
        if (l_sortKeys != null && l_sortKeys.length > 0)
        {
            //1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。
            //○キー項目とテーブルの列名称との対応は以下の通り。
            //※キー項目文字列(シンボル名)は、メッセージ定義書を参照。
            //※テーブルの列物理名は、テーブルレイアウトを参照。
            int l_intLength = l_sortKeys.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER.equals(l_sortKeys[i].keyItem))
                {
                    //・通番　@　@　@　@　@　@　@　@=外部連携情報管理テーブル.通番
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("sequence_number asc");
                        l_strOrderBy = l_strOrderBy + " sequence_number asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("sequence_number desc");
                        l_strOrderBy = l_strOrderBy + " sequence_number desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ID.equals(l_sortKeys[i].keyItem))
                {
                    //・ID 　@　@　@　@　@　@　@　@　@=外部連携情報管理テーブル.ID
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("id asc");
                        l_strOrderBy = l_strOrderBy + " id asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("id desc");
                        l_strOrderBy = l_strOrderBy + " id desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.STATUS.equals(l_sortKeys[i].keyItem))
                {
                    //・ステータス　@　@　@　@　@=外部連携情報管理テーブル.ステータス
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("status asc");
                        l_strOrderBy = l_strOrderBy + " status asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("status desc");
                        l_strOrderBy = l_strOrderBy + " status desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    //・部店コード　@　@　@　@ =外部連携情報管理テーブル.部店コード
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    //・口座コード　@　@　@　@ =外部連携情報管理テーブル.口座コード
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE_FROM.equals(l_sortKeys[i].keyItem))
                {
                    //・適用期間From　@　@=外部連携情報管理テーブル.適用期間From
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE_TO.equals(l_sortKeys[i].keyItem))
                {
                    //・適用期間To　@　@　@ =外部連携情報管理テーブル.適用期間To
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
                {
                    //・最終更新日　@　@　@ =外部連携情報管理テーブル.更新日付
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortKeys[i].keyItem))
                {
                    //・最終更新者　@　@　@ =外部連携情報管理テーブル.更新者コード
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updater  asc");
                        l_strOrderBy = l_strOrderBy + " last_updater  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updater  desc");
                        l_strOrderBy = l_strOrderBy + " last_updater  desc";
                    }
                }

                if (i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }
        else
        {
            log.debug("appli_start_date desc");
            l_strOrderBy = " appli_start_date desc ";
        }

        //2) 以下の検索条件で、「外部連携情報管理テーブル」を検索する。
        //[検索条件]
        //○サービス区分=引数.サービス区分
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStrings = new ArrayList();
        l_sbQueryString.append(" srv_div = ? and ");
        l_lisQueryStrings.add(l_strServiceDiv);

        //○証券会社コード=引数.証券会社コード
        l_sbQueryString.append(" institution_code = ? and ");
        l_lisQueryStrings.add(l_strInstitutionCode);

        //○部店コード=null　@又は　@部店コード=引数.部店コード(*1)
        l_sbQueryString.append(" (branch_code is null ");

        //(*1)引数.部店について
        //・引数.部店コードの要素数が１しかない場合、以下のような条件で検索を行う。
        //"部店コード=引数.部店コード　@and ..."
        //・引数.部店コードの要素数が複数あった場合、以下のような条件で検索を行う。
        //"部店コード in (引数.部店コード[n]、引数.部店コード[n+1]...)"
        if (l_strBranchCodes.length == 1)
        {
            l_sbQueryString.append(" or branch_code = ? ");
            l_lisQueryStrings.add(l_strBranchCodes[0]);
        }
        else if (l_strBranchCodes.length > 1)
        {
            String l_strBranchCode = "";
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                l_lisQueryStrings.add(l_strBranchCodes[i]);
                if (i == l_strBranchCodes.length - 1)
                {
                    l_strBranchCode = l_strBranchCode + " ?";
                }
                else
                {
                    l_strBranchCode = l_strBranchCode + " ?,";
                }
            }
            l_sbQueryString.append(" or branch_code in (" + l_strBranchCode + ") ");
        }

        l_sbQueryString.append(" ) ");
        //○通番　@=　@引数.通番 ---------（ただし、引数.通番がnullでは無い場合に限る）
        if (l_strSequenceNumber != null)
        {
            l_sbQueryString.append(" and sequence_number = ? ");
            l_lisQueryStrings.add(l_strSequenceNumber);
        }

        //○ID　@=　@引数.ID ---------（ただし、引数.IDがnullでは無い場合に限る）
        if (l_strID != null)
        {
            l_sbQueryString.append(" and id = ? ");
            l_lisQueryStrings.add(l_strID);
        }

        //○ステータス　@=　@引数.ステータス ---------（ただし、引数.ステータスがnullでは無い場合に限る）
        if (l_strStatus != null)
        {
            l_sbQueryString.append(" and status = ? ");
            l_lisQueryStrings.add(l_strStatus);
        }

        //○口座コード　@like　@引数.口座コード% ---------（ただし、引数.口座コードがnullでは無い場合に限る）
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
            l_lisQueryStrings.add(l_strAccountCode);
        }

        //○適用期間From　@≧　@引数.適用開始日（自） -（ただし、引数.適用開始日（自）がnullでは無い場合に限る）
        if (l_tsAppliStartDate != null)
        {
            l_sbQueryString.append(" and appli_start_date >= ? ");
            l_lisQueryStrings.add(
                WEB3DateUtility.toDay(l_tsAppliStartDate));
        }

        //○適用期間From　@≦　@引数.適用開始日（至） -（ただし、引数.適用開始日（至）がnullでは無い場合に限る）
        if (l_tsAppliEndDate != null)
        {
            l_sbQueryString.append(" and appli_start_date <= ? ");
            l_lisQueryStrings.add(
                WEB3DateUtility.toDay(l_tsAppliEndDate));
        }

        Object[] l_queryContainers = new Object[l_lisQueryStrings.size()];
        l_lisQueryStrings.toArray(l_queryContainers);

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                OtherOrgInfoAdminRow.TYPE,
                l_sbQueryString.toString(),
                l_strOrderBy,
                null,
                l_queryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        OtherOrgInfoAdminParams[] l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams[l_lisOtherOrgInfoAdminRows.size()];

        for (int i = 0; i < l_lisOtherOrgInfoAdminRows.size(); i++)
        {
            OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
                (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(i);

            l_otherOrgInfoAdminParams[i] = new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);
        }
        //3) 2)の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_otherOrgInfoAdminParams;
    }

    /**
     * (get未使用通番情報)<BR>
     * 外部連携情報管理テーブルにおいて、ステータスが未使用のレコードの中で、<BR>
     * 通番が最小のレコード（外部連携情報管理オブジェクト）を返却する。<BR>
     * <BR>
     * 1)　@以下の条件で「外部連携情報管理テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * サービス区分 = 引数.サービス区分( )<BR>
     * 通番 = ( select MIN(通番) FROM 外部連携情報管理テーブル<BR>
     * 　@WHERE サービス区分 = 引数.サービス区分( ) AND ステータス = 9 ）<BR>
     * <BR>
     * 2) 戻り値の生成<BR>
     * 　@2-1) 検索結果が 0件の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * <BR>
     * 　@2-2)　@検索結果が 1件の場合、<BR>
     * 　@　@上記検索結果である未使用通番情報Paramsオブジェクトを引数に、<BR>
     * 　@　@　@外部連携情報管理クラスのコンストラクタをコールする。<BR>
     * <BR>
     * 3)　@引数.is行ロック = true の場合<BR>
     * 　@　@生成した外部連携情報管理オブジェクト.createForUpdateParams( ) をコールする。<BR>
     * <BR>
     * 4)　@生成した外部連携情報管理オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * is行ロック<BR>
     * true：行ロックをする。　@false：行ロックをしない。<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getUnUseSequenceNumberInfo(
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnUseSequenceNumberInfo(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件で「外部連携情報管理テーブル」を検索する。
        // （引数.is行ロックがtrueの場合、select for updateで検索を行う。)
        // [検索条件]
        //サービス区分=引数.サービス区分
        //通番 = ( select MIN(通番) FROM 外部連携情報管理テーブル WHERE サービス区分 = 引数.サービス区分
        //( ) AND ステータス = 9 ）
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" srv_div = ? and status = ? ");

        Object[] l_queryContainers = {l_strServiceDiv, WEB3OtherOrgStatusDef.DEFAULT};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            String l_strOrderBy = " sequence_number asc ";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    l_strOrderBy,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    l_strOrderBy,
                    null,
                    l_queryContainers);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2) 戻り値の生成
        //2-1) 検索結果が 0件の場合、例外をスローする。
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("外部連携情報を取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                getClass().getName() + "." + STR_METHOD_NAME,
                "外部連携情報を取得できません。");
        }

        //2-2) 検索結果が 1件の場合、
        //上記検索結果である未使用通番情報Paramsオブジェクトを引数に、外部連携情報管理クラスのコンストラクタをコールする。
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //3) 引数.is行ロック = true の場合
        //生成した外部連携情報管理オブジェクト.createForUpdateParams( ) をコールする。
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //4) 生成した外部連携情報管理オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (submit外部連携情報)<BR>
     * 外部連携情報管理テーブルのUPDATEを行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submit外部連携情報」参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 （サービス利用）submit外部連携情報: <BR>
     * 　@　@　@　@　@1.2.2　@get外部連携情報() = null の場合<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_blnIsNewApplyDiv - (新規申込区分)<BR>
     * 新規申込区分<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOtherOrgInfo(String, String, String,"
            + " String, Timestamp, Timestamp, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin = null;
        //引数.新規申込区分 = true の場合、以下の処理を行う。
        if (l_blnIsNewApplyDiv)
        {
            //get未使用通番情報(String, boolean)
            //サービス区分 = 引数.サービス区分
            //is行ロック = true
            l_srvRegiOtherOrgInfoAdmin =
                this.getUnUseSequenceNumberInfo(l_strServiceDiv, true);

            // setステータス(String)
            //0：使用中
            l_srvRegiOtherOrgInfoAdmin.setStatus(WEB3OtherOrgStatusDef.USING);

            //set証券会社コード(String)
            //証券会社コード = 引数.証券会社コード
            l_srvRegiOtherOrgInfoAdmin.setInstitutionCode(l_strInstitutionCode);

            //set部店コード(String)
            //部店コード = 引数.部店コード
            l_srvRegiOtherOrgInfoAdmin.setBranchCode(l_strBranchCode);

            //set口座コード(String)
            //口座コード = 引数.口座コード
            l_srvRegiOtherOrgInfoAdmin.setAccountCode(l_strAccountCode);
        }
        else
        {
            //上記以外の場合（引数.新規申込区分 = false の場合）
            //get外部連携情報(String, String, String, String, boolean)
            //サービス区分 = 引数.サービス区分
            //証券会社コード = 引数.証券会社コード
            //部店コード = 引数.部店コード
            //口座コード = 引数.口座コード
            //is行ロック = false
            l_srvRegiOtherOrgInfoAdmin = this.getOtherOrgInfo(
                l_strServiceDiv,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                false);

            if (l_srvRegiOtherOrgInfoAdmin == null)
            {
                log.debug("外部連携情報を取得できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "外部連携情報を取得できません。");
            }
        }

        //set適用期間From(Timestamp)
        //適用期間From = 引数.適用開始日
        l_srvRegiOtherOrgInfoAdmin.setAppliStartDate(
            new Timestamp(WEB3DateUtility.toDay(l_tsAppliStartDate).getTime()));

        //set適用期間To(Timestamp)
        //適用期間To = 引数.適用終了日
        l_srvRegiOtherOrgInfoAdmin.setAppliEndDate(
            new Timestamp(WEB3DateUtility.toDay(l_tsAppliEndDate).getTime()));

        // save外部連携情報管理( )
        l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
    }

    /**
     * (submit外部連携情報)<BR>
     * 外部連携情報管理テーブルのUPDATEを行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submit外部連携情報～削除変更～」参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strApplyLotteryDiv - (申込抽選区分)<BR>
     * 申込抽選区分<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_blnIsNewApplyDiv - (新規申込区分)<BR>
     * 新規申込区分<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strApplyLotteryDiv,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOtherOrgInfo(String, String, String,"
            + " String, String, Timestamp, Timestamp, boolean)";
        log.entering(STR_METHOD_NAME);

        //get外部連携情報(String, String, String, String, boolean)
        //サービス区分 = 引数.サービス区分
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //口座コード = 引数.口座コード
        //is行ロック = false
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            this.getOtherOrgInfo(
                l_strServiceDiv,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                false);

        //get外部連携情報() = null 以外の場合
        if (l_srvRegiOtherOrgInfoAdmin != null)
        {
            if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_strApplyLotteryDiv))
            {
                // setステータス(String)
                //1：無効
                l_srvRegiOtherOrgInfoAdmin.setStatus(WEB3OtherOrgStatusDef.INVALIDITY);

                //save外部連携情報管理( )
                l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
            }
            else
            {
                //submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
                //新規申込区分 = false
                this.submitOtherOrgInfo(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strServiceDiv,
                    l_tsAppliStartDate,
                    l_tsAppliEndDate,
                    false);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
