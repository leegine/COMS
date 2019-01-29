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
filename	WEB3AdminFPTDocCategoryManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面種類管理(WEB3AdminFPTDocCategoryManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 武波 (中訊) 新規作成 モデルNo.023,No.029,No.031
*/
package webbroker3.docadmin;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (書面種類管理)<BR>
 * 書面種類管理クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDocCategoryManagement
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocCategoryManagement.class);

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    private String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード配列<BR>
     */
    private String[] branchCode;

    /**
     * (書面種類コード)<BR>
     * 書面種類コード<BR>
     */
    private String documentCategory;

    /**
     * (get書面種類名称)<BR>
     * 書面種類管理テーブルより書面種類名称を取得する。<BR>
     * <BR>
     * １） this.get書面種類管理()をコールする。<BR>
     * <BR>
     * ２） １） にて取得したListの長さ > 0 の場合、取得した行より書面種類名称を返却する。<BR>
     * 　@　@　@（書面種類名称がnullの場合はnullを返却する。）<BR>
     * 　@　@　@Listの長さ == 0 の場合、<BR>
     * 　@　@　@「書面種類管理テーブルにレコードが存在しません。」例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_03000<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getDocumentCateName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentCateName()";
        log.entering(STR_METHOD_NAME);

        //１） this.get書面種類管理()をコールする。
        List l_lisDocCategoryManagements = this.getDocCategoryManagement();

        //２） １） にて取得したListの長さ > 0 の場合、取得した行より書面種類名称を返却する。
        //（書面種類名称がnullの場合はnullを返却する。）
        //Listの長さ == 0 の場合、
        //「書面種類管理テーブルにレコードが存在しません。」例外をスローする。
        if (l_lisDocCategoryManagements.size() == 0)
        {
            log.debug("書面種類管理テーブルにレコードが存在しません。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面種類管理テーブルにレコードが存在しません。");
        }


        DocCategoryManagementRow l_docCategoryManagementRow =
            (DocCategoryManagementRow)l_lisDocCategoryManagements.get(0);

        log.exiting(STR_METHOD_NAME);
        return l_docCategoryManagementRow.getDocumentCateName();
    }

    /**
     * (is書面種類コード)<BR>
     * 書面種類管理テーブルより書面種類名称を取得する。<BR>
     * <BR>
     * １） this.get書面種類管理()をコールする。<BR>
     * <BR>
     * ２） １） にて取得したListの長さ > 0 の場合 true、<BR>
     * 　@　@Listの長さ == 0 の場合 false を返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDocumentCategory() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDocumentCategory()";
        log.entering(STR_METHOD_NAME);

        //１） this.get書面種類管理()をコールする。
        List l_lisDocCategoryManagements = this.getDocCategoryManagement();

        //２） １） にて取得したListの長さ > 0 の場合 true、
        //Listの長さ == 0 の場合 false を返却する。
        if (l_lisDocCategoryManagements.size() > 0)
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
     * (get書面種類管理)<BR>
     * 書面種類管理テーブルよりレコードを取得する。<BR>
     * <BR>
     * １） 以下条件にて書面種類行を取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード ： this.証券会社コード<BR>
     * 　@　@部店コード ： this.部店コード[]の全ての要素(in検索)<BR>
     * 　@　@書面種類コード ： this.書面種類コード<BR>
     * <BR>
     * ２） １）にて取得したListを返却する。<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocCategoryManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocCategoryManagement()";
        log.entering(STR_METHOD_NAME);

        List l_lisDocCategoryManagementRows = null;
        try
        {
            //１） 以下条件にて書面種類行を取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[検索条件]
            //証券会社コード = this.証券会社コード
            //部店コード ： this.部店コード[]の全ての要素(in検索)
            //書面種類コード ： this.書面種類コード
            String l_strBranchCodeIn = "";
            Object[] l_queryContainers = new Object[this.branchCode.length + 2];
            l_queryContainers[0] = this.institutionCode;

            int l_intIndex = 0;
            for (int i = 0; i < this.branchCode.length; i++)
            {
                if (i == this.branchCode.length -1)
                {
                    l_strBranchCodeIn += " ? ";
                }
                else
                {
                    l_strBranchCodeIn += " ?, ";
                }
                l_intIndex += 1;
                l_queryContainers[l_intIndex] = this.branchCode[i];
            }

            l_queryContainers[l_queryContainers.length -1] = this.documentCategory;

            String l_strQueryString = " institution_code = ? and branch_code in ( "
                + l_strBranchCodeIn + " ) and document_category = ? ";

            l_lisDocCategoryManagementRows = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
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
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@１）にて取得したListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisDocCategoryManagementRows;
    }

    /**
     * (書面種類管理)<BR>
     * ディフォルトコンストラクタ
     */
    public WEB3AdminFPTDocCategoryManagement()
    {

    }

    /**
     * (書面種類管理)<BR>
     * コンストラクタ<BR>
     * <BR>
     * this.証券会社コード = 引数.証券会社コード<BR>
     * this.部店コード = 引数.部店コード<BR>
     * this.書面種類コード = 引数.書面種類コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     */
    public WEB3AdminFPTDocCategoryManagement(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strDocumentCategory)
    {
        this.institutionCode = l_strInstitutionCode;
        this.branchCode = l_strBranchCodes;
        this.documentCategory = l_strDocumentCategory;
    }
}
@
