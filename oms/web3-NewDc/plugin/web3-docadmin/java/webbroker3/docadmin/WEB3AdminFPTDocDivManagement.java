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
filename	WEB3AdminFPTDocDivManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面区分管理(WEB3AdminFPTDocDivManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 何文敏 (中訊) 新規作成
Revision History : 2008/01/28 武波 (中訊) モデルNo.023,No.031
*/

package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.gentrade.data.DocDivManagementPK;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (書面区分管理)<BR>
 * 書面区分管理クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminFPTDocDivManagement
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDivManagement.class);

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
    private String docDiv;

    /**
     * (書面種類コード)<BR>
     * 書面種類コード<BR>
     */
    private String documentCategory;

    /**
     * (書面区分管理)<BR>
     * コンストラクタ<BR>
     * <BR>
     * this.証券会社コード = 引数.証券会社コード<BR>
     * this.部店コード = 引数.部店コード<BR>
     * this.書面区分 = 引数.書面区分<BR>
     * this.書面種類コード = 引数.書面種類コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strDocDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     * @@roseuid 46F3ADDE007E
     */
    public WEB3AdminFPTDocDivManagement(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocDiv,
        String l_strDocumentCategory)
    {
        final String STR_METHOD_NAME = "WEB3FPTDocumentDivAdmin(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.証券会社コード = 引数.証券会社コード
        this.institutionCode = l_strInstitutionCode;

        //this.部店コード = 引数.部店コード
        this.branchCode = l_strBranchCode;

        //this.書面区分 = 引数.書面区分
        this.docDiv = l_strDocDiv;

        //this.書面種類コード = 引数.書面種類コード
        this.documentCategory = l_strDocumentCategory;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (書面区分管理)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 46F391F6001F
     */
    public WEB3AdminFPTDocDivManagement()
    {

    }

    /**
     * (get書面区分管理テーブル行)<BR>
     * 書面区分管理テーブルより検索を行う。<BR>
     * <BR>
     * １）　@以下条件で書面区分管理テーブルより検索を行う。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@部店コード = this.部店コード<BR>
     * 　@　@　@書面区分 = this.書面区分<BR>
     * 　@　@　@書面種類コード = this.書面種類コード<BR>
     * <BR>
     * ２）　@検索結果が取得できた場合、取得した書面区分管理テーブル行を返却する。<BR>
     * <BR>
     * ３）　@検索結果が取得できなかった場合、例外を生成する。<BR>
     * @@throws WEB3BaseException
     * @@return DocDivManagementRow
     * @@roseuid 46F3AEF701C9
     */
    public DocDivManagementRow getDocDivManagementParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagementParams()";
        log.entering(STR_METHOD_NAME);

        DocDivManagementRow l_docDeliveryManagementRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            DocDivManagementPK l_docDivManagementPK =
                new DocDivManagementPK(this.institutionCode, this.branchCode, this.docDiv, this.documentCategory);

            // 以下条件で書面区分管理テーブルより検索を行う。
            l_docDeliveryManagementRow =
                (DocDivManagementRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_docDivManagementPK);
        }
        catch (DataFindException l_ex)
        {
            // 検索結果が取得できなかった場合、例外を生成する。
            log.debug("検索結果なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01279,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索結果なし。");
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

        // ２）　@検索結果が取得できた場合、取得した書面区分管理テーブル行を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_docDeliveryManagementRow;
    }

    /**
     * (get書面区分管理一覧)<BR>
     * 書面区分管理テーブルから証券会社毎に扱う書面区分を取得する。<BR>
     * <BR>
     * <BR>
     * <BR>
     * １）　@書面区分管理テーブルからレコードを取得<BR>
     * <BR>
     * [検索条件]<BR>
     * 証券会社コード = this.証券会社コード<BR>
     * 部店コード = this.部店コード<BR>
     * <BR>
     * [ソート条件]<BR>
     * 書面区分.asc<BR>
     * <BR>
     * ※検索結果が取得できない場合、「書面区分管理テーブルにレコードが存在しません」例外をスローする。<BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@BUSINESS_ERROR_02998<BR>
     * <BR>
     * ２）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * ３）　@１）で取得したレコード分、Loop処理<BR>
     * <BR>
     * 　@３－１）　@書面区分管理テーブル行の取得<BR>
     * <BR>
     * 　@３-２）　@書面種類名称の取得<BR>
     * 　@　@　@　@　@　@書面種類管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@[指定する引数]<BR>
     * 　@　@　@　@　@　@証券会社コード = this.証券会社コード<BR>
     * 　@　@　@　@　@　@部店コード = this.部店コードを要素とした長さ1のString配列<BR>
     * 　@　@　@　@　@　@書面種類コード = 書面区分管理テーブル行.get書面種類コード()<BR>
     * <BR>
     * 　@ ３-３） 書面区分管理情報オブジェクトを生成し、以下にセット<BR>
     * 　@　@書面区分管理情報オブジェクト.書面区分 = <BR>
     * 　@　@　@　@書面区分管理テーブル行.get書面区分<BR>
     * 　@　@書面区分管理情報オブジェクト.書面名称 = <BR>
     * 　@　@　@　@書面区分管理テーブル行.get書面名称<BR>
     * 　@　@書面区分管理情報オブジェクト.書面チェック区分 = <BR>
     * 　@　@　@　@書面区分管理テーブル行.get書面チェック区分<BR>
     * 　@　@書面区分管理情報オブジェクト.書面種類コード = <BR>
     * 　@　@　@　@書面区分管理テーブル行.get書面種類コード()<BR>
     * 　@　@書面区分管理情報オブジェクト.書面種類名称 = <BR>
     * 　@　@　@　@書面種類管理#get書面種類名称() の戻り値<BR>
     * <BR>
     * 　@３－４）　@書面区分管理情報オブジェクトをListにadd()<BR>
     * <BR>
     * ４）　@ArrayListオブジェクトを書面区分管理情報[]に変換<BR>
     * <BR>
     * ５）　@４）で変換した値をreturn<BR>
     * @@throws WEB3BaseException
     * @@return WEB3FPTDocumentDivAdminInfoUnit[]
     * @@roseuid 46F39433004C
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] getDocDivManagementLists() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagementLists()";
        log.entering(STR_METHOD_NAME);

        //１)一時保存用のListを作成。
        List l_lisReturns = new ArrayList();
        List l_lisDocDivManagementLists = new ArrayList();

        // 　@[検索条件]
        // 　@証券会社コード = this.証券会社コード
        // 　@部店コード = this.部店コード
        String l_strQueryCondition = " institution_code = ? and branch_code = ? ";
        String l_strSortCond = " document_div asc";

        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = this.institutionCode;
        l_objBindVars[1] = this.branchCode;

        try
        {
            // １） QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocDivManagementLists = l_queryProcessor.doFindAllQuery(
                DocDivManagementRow.TYPE,
                l_strQueryCondition,
                l_strSortCond,
                null,
                l_objBindVars);
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

        //※検索結果が取得できない場合、「書面区分管理テーブルにレコードが存在しません」例外をスローする。
        if (l_lisDocDivManagementLists.isEmpty())
        {
            log.debug("書面区分管理テーブルにレコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                "書面区分管理テーブルにレコードが存在しません。");
        }

        int l_intSize = l_lisDocDivManagementLists.size();
        // ３）　@１）で取得したレコード分、Loop処理
        for (int i = 0; i < l_intSize; i++)
        {
            // ３－１）　@書面区分管理テーブル行の取得
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementLists.get(i);
            // 　@３-２）  書面種類名称の取得オブジェクトの生成
            //証券会社コード = this.証券会社コード
            //部店コード = this.部店コードを要素とした長さ1のString配列
            //書面種類コード = 書面区分管理テーブル行.get書面種類コード()
            String[] l_strBranchCodes = new String[1];
            l_strBranchCodes[0] = this.branchCode;
            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement(
                    this.institutionCode,
                    l_strBranchCodes,
                    l_docDivManagementRow.getDocumentCategory());

            //３-３） 書面区分管理情報オブジェクトを生成し、以下にセット
            WEB3FPTDocumentDivAdminInfoUnit l_fptDocumentDivAdminInfoUnit =
                new WEB3FPTDocumentDivAdminInfoUnit();

            // 書面区分管理情報オブジェクト.書面区分 = 書面区分管理テーブル行.get書面区分
            l_fptDocumentDivAdminInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();

            // 書面区分管理情報オブジェクト.書面名称 = 書面区分管理テーブル行.get書面名称
            l_fptDocumentDivAdminInfoUnit.documentNames = l_docDivManagementRow.getDocumentName();

            // 書面区分管理情報オブジェクト.書面チェック区分 = 書面区分管理テーブル行.get書面チェック区分
            l_fptDocumentDivAdminInfoUnit.docuCheckDiv = l_docDivManagementRow.getDocumentCheckDiv();

            //書面区分管理情報オブジェクト.書面種類コード = 書面区分管理テーブル行.get書面種類コード()
            l_fptDocumentDivAdminInfoUnit.documentCategory =
                l_docDivManagementRow.getDocumentCategory();

            //書面区分管理情報オブジェクト.書面種類名称 = 書面種類管理#get書面種類名称() の戻り値
            l_fptDocumentDivAdminInfoUnit.documentCategoryName =
                l_docCategoryManagement.getDocumentCateName();

            // ３－４）　@書面区分管理情報オブジェクトをListにadd()
            l_lisReturns.add(l_fptDocumentDivAdminInfoUnit);
        }

        // ４）　@ArrayListオブジェクトを書面区分管理情報[]に変換
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            new WEB3FPTDocumentDivAdminInfoUnit[l_lisReturns.size()];
        l_lisReturns.toArray(l_fptDocumentDivAdminInfoUnits);

        // ５）　@４）で変換した値をreturn
        log.entering(STR_METHOD_NAME);
        return l_fptDocumentDivAdminInfoUnits;
    }
}
@
