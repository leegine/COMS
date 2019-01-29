head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTBatoProductCodeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩銘柄コード管理(WEB3AdminFPTBatoProductCodeManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 武波 (中訊) 新規作成 モデルNo.023,No.033
Revision History : 2008/03/04 馮海濤 (中訊)　@仕様変更 モデルNo.038、モデルNo.041、モデルNo.051
*/
package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (電子鳩銘柄コード管理)<BR>
 * 電子鳩銘柄コード管理クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTBatoProductCodeManagement
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTBatoProductCodeManagement.class);

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    private String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    private String branchCode;

    /**
     * (書面区分)<BR>
     * 書面区分<BR>
     */
    private String documentDiv;

    /**
     * (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    private String batoProductCode;

    /**
     * (部店コードリスト)<BR>
     * 部店コードリスト<BR>
     */
    private String[] branchCodeList;

    /**
     * (管理者)<BR>
     *  管理者<BR>
     */
    private WEB3Administrator admin;

    /**
     *(書面情報)<BR>
     * 書面情報<BR>
     */
    private WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList;

    /**
     *(処理フラグ)<BR>
     * 処理フラグ<BR>
     */
    private String processFlag;

    /**
     * (delete電子鳩銘柄コード管理)<BR>
     * 電子鳩銘柄コード管理テーブルへのDELETEを行う。<BR>
     * <BR>
     * １）this.書面情報の長さの回数Loopを行う。（インデックス：idx）<BR>
     * <BR>
     * 　@１-１）以下条件で削除を行う。<BR>
     * <BR>
     * 　@　@　@　@[削除条件]<BR>
     * 　@　@　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@　@　@書面区分 = this.書面情報[idx].書面区分<BR>
     * 　@　@　@　@　@電子鳩銘柄コード = <BR>
     * 　@　@　@　@　@　@this.書面情報[idx].書面種類コード + this.書面情報[idx].書面通番 <BR>
     * @@throws WEB3BaseException
     */
    public void deleteBatoProductCodeAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteBatoProductCodeAdmin()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //クエリプロセッサを取得する。
            l_queryProcessor = Processors.getDefaultProcessor();

            //削除条件文字列
            StringBuffer l_strDeleteString = new StringBuffer();
            l_strDeleteString.append(" institution_code = ? ");
            l_strDeleteString.append(" and document_div = ? ");
            l_strDeleteString.append(" and bato_product_code = ? ");

            //削除条件データコンテナ
            Object[] l_deleteContainer = new Object[3];
            //証券会社コード = this.証券会社コード
            l_deleteContainer[0] = this.institutionCode;
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //書面区分 = this.書面情報[idx].書面区分
                l_deleteContainer[1] = this.documentUpdateList[i].documentDiv;
                //電子鳩銘柄コード = this.書面情報[idx].書面種類コード + this.書面情報[idx].書面通番
                l_deleteContainer[2] =
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber;

                l_queryProcessor.doDeleteAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_strDeleteString.toString(),
                    l_deleteContainer);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get電子鳩銘柄コード管理)<BR>
     * 電子鳩銘柄コード管理テーブルより検索を行う。 <BR>
     * <BR>
     * １）　@以下条件で電子鳩銘柄コード管理テーブルより検索を行う。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@部店コード = this.部店コード<BR>
     * 　@　@　@書面区分 = this.書面区分<BR>
     * 　@　@　@電子鳩銘柄コードコード = this.電子鳩銘柄コード<BR>
     * <BR>
     * ２）　@１）にて取得したListを返却する。<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoProductManagementRows = null;
        try
        {
            //１）　@以下条件で電子鳩銘柄コード管理テーブルより検索を行う。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[検索条件]
            //証券会社コード = this.証券会社コード
            //部店コード = this.部店コード
            //書面区分 = this.書面区分
            //電子鳩銘柄コードコード = this.電子鳩銘柄コード
            String l_strQueryString = " institution_code = ? and branch_code = ?"
                + " and document_div = ? and bato_product_code = ? ";

            Object[] l_queryContainers = {
                this.institutionCode,
                this.branchCode,
                this.documentDiv,
                this.batoProductCode};

            l_lisBatoProductManagementRows = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
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

        //２）　@１）にて取得したListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisBatoProductManagementRows;
    }

    /**
     * (get電子鳩銘柄コード管理（全部店）)<BR>
     * 電子鳩銘柄コード管理テーブルよりレコードを検索する。<BR>
     * 検索値のない場合は返却値用Listの要素にnullを追加する。<BR>
     * （返却値Listの長さは検索を行った回数と同じ数となる）<BR>
     * <BR>
     * <BR>
     * １）返却値用のListを生成する。<BR>
     * <BR>
     * <BR>
     * ２）部店コードリストの長さの回数Loopを行う。（インデックス：index）<BR>
     * <BR>
     * 　@２-１）部店コードをインスタンス変数にセットする。<BR>
     * 　@　@　@　@　@this.部店コード = this.部店コードリスト[index]<BR>
     * <BR>
     * 　@２-２）this.書面情報の長さの回数Loopを行う。（インデックス：idx）<BR>
     * <BR>
     * 　@　@２-２-１）書面区分をインスタンス変数にセットする<BR>
     * 　@　@　@　@　@　@　@　@this.書面区分 = this.書面情報[idx].書面区分<BR>
     * <BR>
     * 　@　@２-２-２）電子鳩銘柄コードをインスタンス変数にセットする<BR>
     * 　@　@　@　@　@　@　@　@this.電子鳩銘柄コード = this.書面情報[idx].書面種類コード +<BR>
     * 　@　@　@　@　@　@　@　@this.書面情報[idx].書面通番<BR>
     * <BR>
     * 　@　@２-２-３）検索を行う。<BR>
     * 　@　@　@　@　@　@　@　@this.get電子鳩銘柄コード管理() をコールする。<BR>
     * <BR>
     * 　@　@２-３-４）get電子鳩銘柄コード管理() の戻り値長さ > 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@戻り値を返却値用Listに追加する。<BR>
     * 　@　@　@　@　@　@　@get電子鳩銘柄コード管理() の戻り値長さ == 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@null を返却値用Listに追加する。<BR>
     * <BR>
     * <BR>
     * ３）返却値用Listを返却する。<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    private List getBatoProductManagementAllBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagementAllBranch()";
        log.entering(STR_METHOD_NAME);

        //１） 返却値用のListを生成する。
        List l_lisBatoProductManageMentAllBranch = new ArrayList();

        //２） 部店コードリストの長さの回数Loopを行う。（インデックス：index）
        for (int i = 0; i < this.branchCodeList.length; i++)
        {
            //２-１） 部店コードをインスタンス変数にセットする。
            //        this.部店コード = this.部店コードリスト[index]
            this.branchCode = this.branchCodeList[i];

            //２-２） this.書面情報の長さの回数Loopを行う。（インデックス：idx）
            for (int j = 0; j < this.documentUpdateList.length; j++)
            {
                //２-２-１） 書面区分をインスタンス変数にセットする
                //            this.書面区分 = this.書面情報[idx].書面区分
                this.documentDiv = this.documentUpdateList[j].documentDiv;

                //２-２-２） 電子鳩銘柄コードをインスタンス変数にセットする
                //        this.電子鳩銘柄コード =
                //        this.書面情報[idx].書面種類コード + this.書面情報[idx].書面通番
                this.batoProductCode =
                    this.documentUpdateList[j].documentCategory + this.documentUpdateList[j].documentNumber;

                //２-２-３） 検索を行う。
                //            this.get電子鳩銘柄コード管理() をコールする。
                List l_lisBatoProductManagements = this.getBatoProductManagement();

                if (l_lisBatoProductManagements.size() > 0)
                {
                    //２-３-４） get電子鳩銘柄コード管理() の戻り値長さ > 0 の場合、
                    //戻り値を返却値用Listに追加する。
                    l_lisBatoProductManageMentAllBranch.add(l_lisBatoProductManagements);
                }
                else
                {
                    //get電子鳩銘柄コード管理() の戻り値長さ == 0 の場合、
                    //null を返却値用Listに追加する。
                    l_lisBatoProductManageMentAllBranch.add(null);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //３） 返却値用Listを返却する。
        return l_lisBatoProductManageMentAllBranch;
    }

    /**
     * (get電子鳩銘柄コード管理一覧)<BR>
     * 電子鳩銘柄コード管理テーブルよりレコードを検索する。<BR>
     * <BR>
     * １）QueryProcessor#doFindAllQuery()をコールする。<BR>
     * <BR>
     * 　@　@[指定する引数]<BR>
     * 　@　@　@rowType： 電子鳩銘柄コード管理テーブルRowオブジェクト<BR>
     * 　@　@　@where： 引数.検索文字列<BR>
     * 　@　@　@orderBy： 引数.ソートキー<BR>
     * 　@　@　@conditions： null<BR>
     * 　@　@　@bindVars： 引数.検索データコンテナ<BR>
     * <BR>
     * ２）１）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_strQueryString - (検索文字列)<BR>
     * 検索文字列<BR>
     * @@param l_queryContainers - (検索データコンテナ)<BR>
     * 検索データコンテナ<BR>
     * @@param l_strSortKey - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getBatoProductManagementList(
        String l_strQueryString,
        Object[] l_queryContainers,
        String l_strSortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagementList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //１） QueryProcessor#doFindAllQuery()をコールする。
        List l_lisBatoProductManagementRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_strQueryString,
                    l_strSortKey,
                    null,
                    l_queryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTBatoProductCodeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTBatoProductCodeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBatoProductManagementRows;
    }

    /**
     * (get電子鳩銘柄コード管理一覧)<BR>
     * 電子鳩銘柄コード管理テーブルから一覧を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@電子鳩銘柄コード管理テーブルからレコードを取得<BR>
     * <BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@　@部店コード = this.部店コード<BR>
     * 　@　@　@　@書面区分 = this.書面区分<BR>
     * 　@　@　@　@電子鳩銘柄コード = this.電子鳩銘柄コード + "%"（前方一致）<BR>
     * <BR>
     * 　@　@　@[ソート条件]<BR>
     * 　@　@　@　@電子鳩銘柄コード.asc<BR>
     * <BR>
     * 　@　@　@※検索結果が取得できない場合、<BR>
     * 　@　@　@　@「電子鳩銘柄コード管理テーブルにレコードが存在しません」例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02999<BR>
     * <BR>
     * <BR>
     * ２）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * <BR>
     * ３）　@１）で取得したレコード分、Loop処理<BR>
     * <BR>
     * 　@３－１）　@電子鳩銘柄コード管理テーブル行の取得<BR>
     * <BR>
     * 　@３－２）　@電子鳩銘柄コード管理情報オブジェクトを生成し、以下にセット<BR>
     * <BR>
     * 　@　@電子鳩銘柄コード管理情報オブジェクト.電子鳩銘柄コード =<BR>
     * 　@　@　@　@電子鳩銘柄コード管理テーブル行.get電子鳩銘柄コード()<BR>
     * 　@　@電子鳩銘柄コード管理情報オブジェクト.有効区分 =<BR>
     * 　@　@　@　@電子鳩銘柄コード管理テーブル行.get有効区分() <BR>
     * 　@　@電子鳩銘柄コード管理情報オブジェクト.摘要 = 電子鳩銘柄コード管理テーブル行.摘要()<BR>
     * 　@　@電子鳩銘柄コード管理情報オブジェクト.書面種類通番 =<BR>
     * 　@　@　@　@電子鳩銘柄コード管理テーブル行.get電子鳩銘柄コード()の右4桁<BR>
     * <BR>
     * 　@３－３）　@電子鳩銘柄コード管理情報オブジェクトをListにadd()<BR>
     * <BR>
     * <BR>
     * ４）　@ArrayListオブジェクトを電子鳩銘柄コード管理情報[]に変換<BR>
     * <BR>
     * <BR>
     * ５）　@４）で変換した値をreturn<BR>
     * <BR>
     * @@return WEB3FPTBatoProductCodeAdminInfoUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FPTBatoProductCodeAdminInfoUnit[] getBatoProductCodeAdminInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductCodeAdminInfoUnit()";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoProductManagementRows = null;
        try
        {
            //１）　@以下条件で電子鳩銘柄コード管理テーブルより検索を行う。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[検索条件]
            //証券会社コード = this.証券会社コード
            //部店コード = this.部店コード
            //書面区分 = this.書面区分
            //電子鳩銘柄コード = this.電子鳩銘柄コード + "%"（前方一致）
            String l_strQueryString = " institution_code = ? and branch_code = ?"
                + " and document_div = ? and bato_product_code like ? || '%' ";

            Object[] l_queryContainers = {
                this.institutionCode,
                this.branchCode,
                this.documentDiv,
                this.batoProductCode};

            //[ソート条件]
            //電子鳩銘柄コード.asc
            l_lisBatoProductManagementRows = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
                l_strQueryString,
                " bato_product_code asc ",
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

        //※検索結果が取得できない場合、
        //「電子鳩銘柄コード管理テーブルにレコードが存在しません」例外をスローする。
        int l_intBatoProductManagementsSize = l_lisBatoProductManagementRows.size();
        if (l_intBatoProductManagementsSize == 0)
        {
            log.debug("電子鳩銘柄コード管理テーブルにレコードが存在しません。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コード管理テーブルにレコードが存在しません。");
        }

        //２）　@ArrayListオブジェクトの生成
        List l_lisBatoProductCodeAdminInfoUnits = new ArrayList();

        //３）　@１）で取得したレコード分、Loop処理
        Iterator l_iteratorBatoProductManagementRows =
            l_lisBatoProductManagementRows.iterator();
        while (l_iteratorBatoProductManagementRows.hasNext())
        {
            //３－１）　@電子鳩銘柄コード管理テーブル行の取得
            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_iteratorBatoProductManagementRows.next();

            //３－２）　@電子鳩銘柄コード管理情報オブジェクトを生成し、以下にセット
            WEB3FPTBatoProductCodeAdminInfoUnit l_batoProductCodeAdminInfoUnit =
                new WEB3FPTBatoProductCodeAdminInfoUnit();

            //電子鳩銘柄コード管理情報オブジェクト.電子鳩銘柄コード = 電子鳩銘柄コード管理テーブル行.get電子鳩銘柄コード()
            String l_strBatoProductCode = l_batoProductManagementRow.getBatoProductCode();
            l_batoProductCodeAdminInfoUnit.batoProductCode = l_strBatoProductCode;

            //電子鳩銘柄コード管理情報オブジェクト.有効区分 = 電子鳩銘柄コード管理テーブル行.get有効区分()
            l_batoProductCodeAdminInfoUnit.validFlag =
                l_batoProductManagementRow.getValidFlag();

            //電子鳩銘柄コード管理情報オブジェクト.摘要 = 電子鳩銘柄コード管理テーブル行.摘要()
            l_batoProductCodeAdminInfoUnit.remarks =
                l_batoProductManagementRow.getRemarks();

            //電子鳩銘柄コード管理情報オブジェクト.書面種類通番 = 電子鳩銘柄コード管理テーブル行.get電子鳩銘柄コード()の右4桁
            int l_intBatoProductCode = l_strBatoProductCode.length();
            l_batoProductCodeAdminInfoUnit.documentCategoryNumber =
                l_batoProductManagementRow.getBatoProductCode().substring(l_intBatoProductCode - 4);

            //３－３）　@電子鳩銘柄コード管理情報オブジェクトをListにadd()
            l_lisBatoProductCodeAdminInfoUnits.add(l_batoProductCodeAdminInfoUnit);
        }

        //４）　@ArrayListオブジェクトを電子鳩銘柄コード管理情報[]に変換
        WEB3FPTBatoProductCodeAdminInfoUnit[] l_batoProductCodeAdminInfoUnits =
            new WEB3FPTBatoProductCodeAdminInfoUnit[l_lisBatoProductCodeAdminInfoUnits.size()];
        l_lisBatoProductCodeAdminInfoUnits.toArray(l_batoProductCodeAdminInfoUnits);

        //５）　@４）で変換した値をreturn
        log.exiting(STR_METHOD_NAME);
        return l_batoProductCodeAdminInfoUnits;
    }

    /**
     * (insert電子鳩銘柄コード管理)<BR>
     * 電子鳩銘柄コード管理テーブルへのINSERTを行う。<BR>
     * <BR>
     * １）部店コードリストの長さの回数Loopを行う。（インデックス：index）<BR>
     * <BR>
     * 　@１-１）this.書面情報の長さの回数Loopを行う。（インデックス：idx）<BR>
     * <BR>
     * 　@　@１-１-１）電子鳩銘柄コード管理テーブルのParamsを生成し、以下内容をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@　@　@　@　@部店コード = this.部店コードリスト[index]<BR>
     * 　@　@　@　@　@　@　@書面区分 = this.書面情報[idx].書面区分<BR>
     * 　@　@　@　@　@　@　@電子鳩銘柄コード = this.書面情報[idx].書面種類コード + <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@this.書面情報[idx].書面通番<BR>
     * 　@　@　@　@　@　@　@更新者コード = this.管理者.get管理者コード() の戻り値<BR>
     * 　@　@　@　@　@　@　@作成日付 = 現在日時<BR>
     * 　@　@　@　@　@　@　@更新日付 = 現在日時<BR>
     * 　@　@　@　@　@　@　@有効区分 = this.書面情報[idx].有効区分<BR>
     * 　@　@　@　@　@　@　@摘要 = this.書面情報[idx].摘要<BR>
     * <BR>
     * 　@　@１-２-２）電子鳩銘柄コード管理テーブルのParamsの内容でINSERTを行う。<BR>
     * @@throws WEB3BaseException
     */
    public void insertBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //クエリプロセッサを取得する。
            l_queryProcessor = Processors.getDefaultProcessor();

            //１） 部店コードリストの長さの回数Loopを行う。（インデックス：index）
            for (int i = 0; i < this.branchCodeList.length; i++)
            {
                //１-１） this.書面情報の長さの回数Loopを行う。（インデックス：idx）
                for (int j = 0; j < this.documentUpdateList.length; j++)
                {
                    //１-１-１） 電子鳩銘柄コード管理テーブルのParamsを生成し、以下内容をセットする。
                    BatoProductManagementParams l_batoProductManagementParams =
                        new BatoProductManagementParams();

                    //証券会社コード = this.証券会社コード
                    l_batoProductManagementParams.setInstitutionCode(this.institutionCode);
                    //部店コード = this.部店コードリスト[index]
                    l_batoProductManagementParams.setBranchCode(this.branchCodeList[i]);
                    //書面区分 = this.書面情報[idx].書面区分
                    l_batoProductManagementParams.setDocumentDiv(this.documentUpdateList[j].documentDiv);
                    //電子鳩銘柄コード = this.書面情報[idx].書面種類コード + this.書面情報[idx].書面通番
                    l_batoProductManagementParams.setBatoProductCode(
                        this.documentUpdateList[j].documentCategory + this.documentUpdateList[j].documentNumber);
                    //更新者コード = this.管理者.get管理者コード() の戻り値
                    l_batoProductManagementParams.setLastUpdater(this.admin.getAdministratorCode());
                    //作成日付 = 現在日時
                    Date l_datNowTime = GtlUtils.getSystemTimestamp();
                    l_batoProductManagementParams.setCreatedTimestamp(l_datNowTime);
                    //更新日付 = 現在日時
                    l_batoProductManagementParams.setLastUpdatedTimestamp(l_datNowTime);
                    //有効区分 = this.書面情報[idx].有効区分
                    l_batoProductManagementParams.setValidFlag(this.documentUpdateList[j].validFlag);
                    //摘要 = this.書面情報[idx].摘要
                    l_batoProductManagementParams.setRemarks(this.documentUpdateList[j].remarks);

                    //１-２-２） 電子鳩銘柄コード管理テーブルのParamsの内容でINSERTを行う。
                    l_queryProcessor.doInsertQuery(l_batoProductManagementParams);
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード管理テーブルに電子鳩銘柄コードが存在するか判定する。<BR>
     * <BR>
     * <BR>
     * １）　@this.get電子鳩銘柄コード管理()をコールする。<BR>
     * <BR>
     * <BR>
     * ２） １）の戻り値長さ > 0 の場合 true、１）の戻り値長さ == 0 の場合 false を返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBatoProductCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBatoProductCode()";
        log.entering(STR_METHOD_NAME);

        //１）　@this.get電子鳩銘柄コード管理()をコールする。
        List l_lisBatoProductManagements = this.getBatoProductManagement();
        int l_intBatoProductManagementsSize = l_lisBatoProductManagements.size();

        //２） １）の戻り値長さ > 0 の場合 true、１）の戻り値長さ == 0 の場合 false を返却する。
        if (l_intBatoProductManagementsSize > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (set会社部店情報)<BR>
     * this.管理者が属している証券会社コード及び証券会社の全部店コードのリストを設定する。<BR>
     * <BR>
     * １）管理者より、証券会社コードを取得し、this.証券会社コード へ セットする。<BR>
     * 　@　@　@this.証券会社コード = 管理者.get証券会社コード() の戻り値<BR>
     * <BR>
     * ２）管理者より証券会社オブジェクトを取得する。<BR>
     * 　@　@　@this.管理者.get証券会社()<BR>
     * <BR>
     * ３）証券会社オブジェクトより部店オブジェクト配列を取得する。<BR>
     * 　@　@　@証券会社.getBranches()<BR>
     * <BR>
     * ４）Listを生成し、部店オブジェクト配列の全ての要素から部店コードを取得し、Listに追加する。<BR>
     * 　@　@　@部店[index].getBranchCode()<BR>
     * <BR>
     * ５）Listを配列に変換し、this.部店コードリスト へセットする。<BR>
     * @@throws WEB3BaseException
     */
    private void setInstBranchInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setInstBranchInfo()";
        log.entering(STR_METHOD_NAME);

        //１） 管理者より、証券会社コードを取得し、this.証券会社コード へ セットする。
        //    this.証券会社コード = 管理者.get証券会社コード() の戻り値
        this.institutionCode = this.admin.getInstitutionCode();

        //２） 管理者より証券会社オブジェクトを取得する。
        //       this.管理者.get証券会社()
        Institution l_institution = this.admin.getInstitution();

        //３） 証券会社オブジェクトより部店オブジェクト配列を取得する。
        //      証券会社.getBranches()
        Branch[] l_branchs = l_institution.getBranches();

        //４） Listを生成し、部店オブジェクト配列の全ての要素
        //    から部店コードを取得し、Listに追加する。
        List l_lisBranchs = new ArrayList();
        for (int i = 0; i < l_branchs.length; i++)
        {
            //     部店[index].getBranchCode()
            l_lisBranchs.add(l_branchs[i].getBranchCode());
        }

        //５） Listを配列に変換し、this.部店コードリスト へセットする。
        String[] l_branchCodes = new String[l_lisBranchs.size()];
        l_lisBranchs.toArray(l_branchCodes);
        this.branchCodeList = l_branchCodes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update電子鳩銘柄コード管理)<BR>
     * 電子鳩銘柄コード管理テーブルへのUPDATEを行う。<BR>
     * <BR>
     * １）部店コードをインスタンス変数にセットする。<BR>
     * 　@　@　@　@　@this.部店コード = this.管理者.get部店コード()<BR>
     * <BR>
     * ２）this.書面情報の長さの回数Loopを行う。（インデックス：idx）<BR>
     * <BR>
     * 　@２-１）書面区分をインスタンス変数にセットする<BR>
     * 　@　@　@　@　@　@　@this.書面区分 = this.書面情報[idx].書面区分<BR>
     * <BR>
     * 　@２-２）電子鳩銘柄コードをインスタンス変数にセットする<BR>
     * 　@　@　@　@　@　@　@this.電子鳩銘柄コード = this.書面情報[idx].書面種類コード +<BR>
     * 　@　@　@　@　@　@　@this.書面情報[idx].書面通番<BR>
     * <BR>
     * 　@２-３）検索を行う。<BR>
     * 　@　@　@　@　@　@　@this.get電子鳩銘柄コード管理() をコールする。<BR>
     * <BR>
     * 　@　@２-３-１）get電子鳩銘柄コード管理()の戻り値の長さ = 0 の場合、<BR>
     * 　@「更新・削除対象の書面種類の書面通番は既に削除されています。」例外をスローする。<BR>
     * <BR>
     * 　@　@２-３-２）get電子鳩銘柄コード管理()の戻り値の長さ > 0 の場合、有効区分を取得する。<BR>
     * <BR>
     * 　@　@　@２-３-２-１）取得した有効区分 == this.書面情報[idx].有効区分 の場合、continue<BR>
     * <BR>
     * 　@　@　@２-３-２-２）取得した有効区分 != this.書面情報[idx].有効区分 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下内容で更新を行う。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[更新値]<BR>
     * 　@　@　@　@　@　@　@　@有効区分 = this.書面情報[idx].有効区分<BR>
     * 　@　@　@　@　@　@　@　@更新者コード = this.管理者.get管理者コード() の戻り値<BR>
     * 　@　@　@　@　@　@　@　@更新日付 = 現在日時<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[更新条件]<BR>
     * 　@　@　@　@　@　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@書面区分 = this.書面区分<BR>
     * 　@　@　@　@　@　@　@　@電子鳩銘柄コード = this.電子鳩銘柄コード<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     */
    public void updateBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //クエリプロセッサを取得する。
            l_queryProcessor = Processors.getDefaultProcessor();

            //１） 部店コードをインスタンス変数にセットする。
            //          this.部店コード = this.管理者.get部店コード()
            this.branchCode = this.admin.getBranchCode();

            //２） this.書面情報の長さの回数Loopを行う。（インデックス：idx）
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //２-１） 書面区分をインスタンス変数にセットする
                //             this.書面区分 = this.書面情報[idx].書面区分
                this.documentDiv = this.documentUpdateList[i].documentDiv;
                //２-２） 電子鳩銘柄コードをインスタンス変数にセットする
                //  this.電子鳩銘柄コード = this.書面情報[idx].書面種類コード + this.書面情報[idx].書面通番
                this.batoProductCode =
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber;
                //２-３） 検索を行う。
                //   this.get電子鳩銘柄コード管理() をコールする。
                List l_lisBatoProductManagements = this.getBatoProductManagement();

                //２-３-１） get電子鳩銘柄コード管理()の戻り値の長さ = 0 の場合、
                //  「更新・削除対象の書面種類の書面通番は既に削除されています。」例外をスローする。
                if (l_lisBatoProductManagements.size() == 0)
                {
                    log.debug("更新・削除対象の書面種類の書面通番は既に削除されています。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03038,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "更新・削除対象の書面種類の書面通番は既に削除されています。");
                }
                else
                {
                    //２-３-２） get電子鳩銘柄コード管理()の戻り値の長さ > 0 の場合、有効区分を取得する。
                    BatoProductManagementRow l_batoProductManagementRow =
                        (BatoProductManagementRow)l_lisBatoProductManagements.get(0);
                    String l_strValidFlag = l_batoProductManagementRow.getValidFlag();
                    //２-３-２-1） 取得した有効区分 != this.書面情報[idx].有効区分 の場合、
                    //以下内容で更新を行う。
                    if (!l_strValidFlag.equals(this.documentUpdateList[i].validFlag))
                    {
                        Map l_updateMap = new HashMap();
                        //[更新値]
                        // 有効区分 = this.書面情報[idx].有効区分
                        l_updateMap.put("valid_flag", this.documentUpdateList[i].validFlag);
                        // 更新者コード = this.管理者.get管理者コード() の戻り値
                        l_updateMap.put("last_updater", this.admin.getAdministratorCode());
                        // 更新日付 = 現在日時
                        l_updateMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                        //[更新条件]
                        StringBuffer l_sbQueryString = new StringBuffer();
                        Object[] l_queryDatas = new Object[3];
                        //  証券会社コード = this.証券会社コード
                        l_sbQueryString.append(" institution_code = ? ");
                        l_queryDatas[0] = this.institutionCode;
                        //  書面区分 = this.書面区分
                        l_sbQueryString.append(" and document_div = ? ");
                        l_queryDatas[1] = this.documentDiv;
                        //  電子鳩銘柄コード = this.電子鳩銘柄コード
                        l_sbQueryString.append(" and bato_product_code = ? ");
                        l_queryDatas[2] = this.batoProductCode;

                        l_queryProcessor.doUpdateAllQuery(
                            BatoProductManagementRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDatas,
                            l_updateMap);
                    }
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate電子鳩銘柄コード管理行)<BR>
     * インスタンス変数へ設定済みの検索条件で電子鳩銘柄コード管理テーブルを検索し、<BR>
     * 更新対象レコードの各種チェックを行う。<BR>
     * <BR>
     * １）検索条件でレコードを取得する。<BR>
     * 　@　@this.get電子鳩銘柄コード管理（全部店）()をコールする。<BR>
     * <BR>
     * <BR>
     * ２）get電子鳩銘柄コード管理（全部店）() の戻り値List長さの回数Loopを行う。<BR>
     * <BR>
     * 　@２-１）this.処理フラグ == 0（登録） && 戻り値List[index] != null の場合、<BR>
     * 　@　@　@　@「作成中の書面種類の書面通番は既に登録されています。」例外をスローする。<BR>
     * <BR>
     * 　@２-２）this.処理フラグ == (1 || 2)（更新、削除） && 戻り値List[index] == null の場合、<BR>
     * 　@　@　@　@「更新・削除対象の書面種類の書面通番は既に削除されています。」例外をスローする。<BR>
     * <BR>
     * 　@２-３）this.処理フラグ == 2（削除） && 戻り値List[index].get有効区分 == 0（valid） の場合、<BR>
     * 　@　@　@　@「有効な書面種類が存在する為、削除できません。」例外をスローする。<BR>
     * <BR>
     * <BR>
     * ３）this.処理フラグ == 2（削除）の場合、交付履歴確認を行う。<BR>
     * <BR>
     * 　@３-１）this.書面情報の長さの回数Loopを行う。<BR>
     * <BR>
     * 　@　@３-１-１）書面交付管理テーブルへ検索を行う。<BR>
     * <BR>
     * 　@　@　@　@　@書面交付管理.get書面交付管理()をコールする<BR>
     * <BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@証券会社コード： this.証券会社コード<BR>
     * 　@　@　@　@　@　@書面区分： this.書面情報[idx].書面区分<BR>
     * 　@　@　@　@　@　@電子鳩銘柄コード： this.書面情報[idx].書面種類コード + <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@this.書面情報[idx].書面通番<BR>
     * 　@　@　@　@　@　@書面種類コード： this.書面情報[idx].書面種類コード<BR>
     * <BR>
     * <BR>
     * 　@　@３-１-２）get書面交付管理() の戻り値長さ > 0 の場合、<BR>
     * <BR>
     * 　@　@　@　@「交付履歴が存在する為、削除できません。」例外をスローする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateBatoProductManagementParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBatoProductManagementParams()";
        log.entering(STR_METHOD_NAME);

        //１） 検索条件でレコードを取得する。
        //this.get電子鳩銘柄コード管理（全部店）()をコールする。
        List l_lisBatoProductManagementAllBranchs = this.getBatoProductManagementAllBranch();

        //２） get電子鳩銘柄コード管理（全部店）() の戻り値List長さの回数Loopを行う。
        int l_intBatoProductManagementAllBranchCnt = l_lisBatoProductManagementAllBranchs.size();
        for (int i = 0; i < l_intBatoProductManagementAllBranchCnt; i++)
        {
            // ２-１） this.処理フラグ == 0（登録） && 戻り値List[index] != null の場合、
            //  「作成中の書面種類の書面通番は既に登録されています。」例外をスローする。
            if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.processFlag)
                && l_lisBatoProductManagementAllBranchs.get(i) != null)
            {
                log.debug("作成中の書面種類の書面通番は既に登録されています。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "作成中の書面種類の書面通番は既に登録されています。");
            }

            //２-２） this.処理フラグ == (1 || 2)（更新、削除） && 戻り値List[index] == null の場合、
            //    「更新・削除対象の書面種類の書面通番は既に削除されています。」例外をスローする。
            if ((WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.processFlag)
                || WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
                && l_lisBatoProductManagementAllBranchs.get(i) == null)
            {
                log.debug("更新・削除対象の書面種類の書面通番は既に削除されています。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03038,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新・削除対象の書面種類の書面通番は既に削除されています。");
            }

            //２-３）  this.処理フラグ == 2（削除） && 戻り値List[index].get有効区分 == 0（valid） の場合、
            //  「有効な書面種類が存在する為、削除できません。」例外をスローする。
            if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
            {
                BatoProductManagementRow l_batoProductManagementRow =
                    (BatoProductManagementRow)((List)l_lisBatoProductManagementAllBranchs.get(i)).get(0);
                if (WEB3EffectiveDivDef.EFFECTIVE.equals(l_batoProductManagementRow.getValidFlag()))
                {
                    log.debug("有効な書面種類が存在する為、削除できません。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03039,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "有効な書面種類が存在する為、削除できません。");
                }
            }
        }

        //３） this.処理フラグ == 2（削除）の場合、交付履歴確認を行う。
        if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
        {
            //３-１） this.書面情報の長さの回数Loopを行う。
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //書面交付管理.get書面交付管理()をコールする
                WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
                    new WEB3AdminFPTDocDeliveryManagement();
                List l_lisDocDivManagements = l_adminFPTDocDeliveryManagement.getDocDivManagement(
                    this.institutionCode,
                    this.documentUpdateList[i].documentDiv,
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber,
                    this.documentUpdateList[i].documentCategory);

                //３-１-２） get書面交付管理() の戻り値長さ > 0 の場合、
                //  「交付履歴が存在する為、削除できません。」例外をスローする。
                if (l_lisDocDivManagements.size() > 0)
                {
                    log.debug("交付履歴が存在する為、削除できません。");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03040,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "交付履歴が存在する為、削除できません。");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (電子鳩銘柄コード管理)<BR>
     * コンストラクタ<BR>
     * <BR>
     * this.証券会社コード = 引数.証券会社コード<BR>
     * this.部店コード = 引数.部店コード<BR>
     * this.書面区分 = 引数.書面区分<BR>
     * this.電子鳩銘柄コード = 引数.電子鳩銘柄コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strDocumentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strBatoProductCode - (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    public WEB3AdminFPTBatoProductCodeManagement(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocumentDiv,
        String l_strBatoProductCode)
    {
        this.institutionCode = l_strInstitutionCode;
        this.branchCode = l_strBranchCode;
        this.documentDiv = l_strDocumentDiv;
        this.batoProductCode = l_strBatoProductCode;
    }

    /**
     * (電子鳩銘柄コード管理)<BR>
     * ディフォルトコンストラクタ<BR>
     */
    public WEB3AdminFPTBatoProductCodeManagement()
    {

    }

    /**
     * (電子鳩銘柄コード管理)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）インスタンス変数へ引数をセットする。<BR>
     * 　@　@　@this.管理者 = 引数.管理者<BR>
     * 　@　@　@this.書面情報 = 引数.書面情報<BR>
     * 　@　@　@this.処理フラグ = 引数.処理フラグ<BR>
     * <BR>
     * ２）this.set会社部店情報()をコールする。<BR>
     * <BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者<BR>
     * @@param l_documentUpdateLists - (書面情報)<BR>
     * 書面情報<BR>
     * @@param l_strProcessFlag - (処理フラグ)<BR>
     * 処理フラグ<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AdminFPTBatoProductCodeManagement(
        WEB3Administrator l_admin,
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateLists,
        String l_strProcessFlag) throws WEB3BaseException
    {
        //１） インスタンス変数へ引数をセットする。
        //  this.管理者 = 引数.管理者
        this.admin = l_admin;
        //  this.書面情報 = 引数.書面情報
        this.documentUpdateList = l_documentUpdateLists;
        // this.処理フラグ = 引数.処理フラグ
        this.processFlag = l_strProcessFlag;

        //２） this.set会社部店情報()をコールする。
        this.setInstBranchInfo();
    }
}
@
