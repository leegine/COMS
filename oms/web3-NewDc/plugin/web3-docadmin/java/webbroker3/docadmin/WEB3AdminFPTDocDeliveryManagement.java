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
filename	WEB3AdminFPTDocDeliveryManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付管理(WEB3AdminFPTDocDeliveryManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 何文敏 (中訊) 新規作成
Revision History : 2007/10/19 Inomata (SCS) モデル009
Revision History : 2007/11/06 武波 (中訊) モデルNo.011
Revision History : 2008/01/28 武波 (中訊) モデルNo.023
Revision History : 2008/03/04 馮海濤 (中訊) モデルNo.038
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocDeliveryManagementPK;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (書面交付管理)<BR>
 * 書面交付管理クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminFPTDocDeliveryManagement
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDeliveryManagement.class);

    /**
     * (書面交付管理)<BR>
     * ディフォルトコンストラクタ<BR>
     * @@roseuid 46F8D9280130
     */
    public WEB3AdminFPTDocDeliveryManagement()
    {

    }

    /**
     * (insert書面交付管理テーブル)<BR>
     * 書面交付管理テーブルのInsertを行う。<BR>
     * <BR>
     * QueryProcessor#doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     *   [引数]<BR>
     *     arg0： 引数.書面交付管理テーブルParams<BR>
     * @@param l_docDeliveryManagementParams - (書面交付管理テーブルParams)<BR>
     * 書面交付管理テーブルParams
     * @@throws WEB3BaseException
     * @@roseuid 46F8C8E8020D
     */
    public void insertDocDeliveryManagementParams(
        DocDeliveryManagementParams l_docDeliveryManagementParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertDocDeliveryManagementParams(DocDeliveryManagementParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_docDeliveryManagementParams);
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

        log.exiting(STR_METHOD_NAME);
    }


	/**
	 * (get書面交付管理行)<BR>
	 * 書面交付管理テーブルより検索を行う。<BR>
	 * <BR>
	 * １）　@以下条件で書面交付管理テーブルより検索を行う。<BR>
	 * <BR>
	 * 　@　@[条件]<BR>
	 * 　@　@　@　@口座ID = 引数:口座ID<BR>
     * 　@　@　@　@書面区分 = 引数:書面区分<BR>
     * 　@　@　@　@銘柄コード = 引数:銘柄コード<BR>
     * 　@　@　@　@書面交付日 = 引数:書面交付日<BR>
     * 　@　@　@　@書面種類コード = 引数:書面種類コード<BR>
	 * <BR>
	 * ２）　@検索結果が取得できた場合、取得した書面交付管理テーブル行を返却する。<BR>
	 * <BR>
	 * ３）　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * @@param l_accountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_documentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_productCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_docuDeliDate - (書面交付日)<BR>
     * 書面交付日<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
	 * @@throws WEB3BaseException
	 * @@return DocDivManagementRow
	 */
	public DocDeliveryManagementRow getDocDivManagementParams(
	        long l_accountId,
			String l_documentDiv,
			String l_productCode,
			Date l_docuDeliDate,
            String l_strDocumentCategory) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getDocDivManagementParams(long, String, String, Date, String)";
		log.entering(STR_METHOD_NAME);

		DocDeliveryManagementRow l_docDeliveryManagementRow = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

			DocDeliveryManagementPK l_docDeliveryManagementPK =
				new DocDeliveryManagementPK(l_accountId,
				l_documentDiv, l_productCode, new Timestamp(l_docuDeliDate.getTime()),
                l_strDocumentCategory);

			// 以下条件で書面交付管理テーブルより検索を行う。
			l_docDeliveryManagementRow =
				(DocDeliveryManagementRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_docDeliveryManagementPK);
		}
		catch (DataFindException l_ex)
		{
			// 検索結果が取得できなかった場合、nullを返却する。
			log.exiting(STR_METHOD_NAME);
			return null;
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

		// ２）　@検索結果が取得できた場合、取得した書面交付管理テーブル行を返却する。
		log.exiting(STR_METHOD_NAME);
		return l_docDeliveryManagementRow;

	}

    /**
     * (get書面交付管理)<BR>
     * 書面交付管理テーブルから書面種類をキーとしてレコードを検索する。（部店不問）<BR>
     * <BR>
     * １） 検索文字列を作成する。<BR>
     * <BR>
     * 　@　@"institution_code = ? and document_div = ? and product_code = ? and <BR>
     * 　@　@document_category = ? "<BR>
     * <BR>
     * ２） 検索データコンテナ（以下値を要素としたObject配列）を作成する。<BR>
     * <BR>
     * 　@　@　@・引数.証券会社コード<BR>
     * 　@　@　@・引数.書面区分<BR>
     * 　@　@　@・引数.電子鳩銘柄コード<BR>
     * 　@　@　@・引数.書面種類コード<BR>
     * <BR>
     * ３） 書面交付管理テーブルへ検索を行う。<BR>
     * <BR>
     * 　@　@　@this.get書面交付管理()をコールする<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@検索文字列： １） で作成した検索文字列<BR>
     * 　@　@　@　@　@検索データコンテナ： ２） で作成した検索データコンテナ<BR>
     * <BR>
     * ４） 検索結果Listを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strDocumentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strBatoProduct_code - (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     * @@return List
     * @@throws WEB3BaseException<BR>
     */
    public List getDocDivManagement(
            String l_strInstitutionCode,
            String l_strDocumentDiv,
            String l_strBatoProduct_code,
            String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagement(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 検索文字列を作成する。<BR>
        //"institution_code = ? and document_div = ? and product_code = ? and document_category = ? "
        String l_strQueryCondition = 
            " institution_code = ? and " +
            "document_div = ? and " +
            "product_code = ? and " +
            "document_category = ? ";

        //２） 検索データコンテナ（以下値を要素としたObject配列）を作成する。
        //・引数.証券会社コード
        //・引数.書面区分
        //・引数.電子鳩銘柄コード
        //・引数.書面種類コード
        Object[] l_queryContainers = new Object[4];
        l_queryContainers[0] = l_strInstitutionCode;
        l_queryContainers[1] = l_strDocumentDiv;
        l_queryContainers[2] = l_strBatoProduct_code;
        l_queryContainers[3] = l_strDocumentCategory;

        //３） 書面交付管理テーブルへ検索を行う。
        //this.get書面交付管理()をコールする
        //[引数]
        //検索文字列： １） で作成した検索文字列
        //検索データコンテナ： ２） で作成した検索データコンテナ
        List l_lisDocDivManagements = this.getDocDivManagement(l_strQueryCondition, l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_lisDocDivManagements;
    }

    /**
     * (get書面交付管理)<BR>
     * 書面交付管理テーブルより検索を行う。<BR>
     * <BR>
     * １） QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     *      [引数]<BR>
     *      rowType： 書面交付管理テーブルRowType<BR>
     *      where： 引数:検索文字列<BR>
     *      bindVars： 引数:検索データコンテナ<BR>
     * <BR>
     * ２） 取得したListを返却する。<BR>
     * @@param l_strQueryString - (検索文字列)<BR>
     * 検索文字列<BR>
     * @@param l_queryContainers - (検索データコンテナ)<BR>
     * 検索データコンテナ<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDivManagement(String l_strQueryString, Object[] l_queryContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagement(String, Object[])";
        log.entering(STR_METHOD_NAME);

        List l_lisDocDivManagements = null;
        try
        {
            //１） QueryProcessor#doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[引数]
            //rowType： 書面交付管理テーブルRowType
            //where： 引数:検索文字列
            //bindVars： 引数:検索データコンテナ
            l_lisDocDivManagements = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
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

        //２） 取得したListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisDocDivManagements;
    }

    /**
     * (delete書面交付管理)<BR>
     * 書面交付管理テーブルの削除を行う。<BR>
     * <BR>
     * １） 以下条件で書面交付管理テーブルより削除を行う。<BR>
     * <BR>
     *     [条件]<BR>
     *       口座ID = 引数:口座ID<BR>
     *       書面区分 = 引数:書面区分<BR>
     *       銘柄コード = 引数:銘柄コード<BR>
     *       書面交付日 = 引数:書面交付日<BR>
     *       書面種類コード = 引数:書面種類コード<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strDocumentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_datDocuDeliDate - (書面交付日)<BR>
     * 書面交付日<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     * @@throws WEB3BaseException
     */
    public void deleteDocDivManagement(
        long l_lngAccountId,
        String l_strDocumentDiv,
        String l_strProductCode,
        Date l_datDocuDeliDate,
        String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteDocDivManagement(long, String, String, Date, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[条件]
            //口座ID = 引数:口座ID
            //書面区分 = 引数:書面区分
            //銘柄コード = 引数:銘柄コード
            //書面交付日 = 引数:書面交付日
            String l_strQueryString = " account_id = ? and document_div = ?"
                + " and product_code = ? and delivery_date = ? and document_category = ? ";

            Object[] l_queryContainers = {
                new Long(l_lngAccountId),
                l_strDocumentDiv,
                l_strProductCode,
                l_datDocuDeliDate,
                l_strDocumentCategory};
            l_queryProcessor.doDeleteAllQuery(
                DocDeliveryManagementRow.TYPE,
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
    }
}
@
