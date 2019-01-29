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
filename	WEB3AdminFPTDocDeliveryManagementHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付管理履歴(WEB3AdminFPTDocDeliveryManagementHistory.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 武波 (中訊) 新規作成 仕様変更・モデル No.011
Revision History : 2008/01/28 武波 (中訊) モデルNo.023
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocDeliveryManagementHistPK;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementHistRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;


/**
 * (書面交付管理履歴)<BR>
 * 書面交付管理履歴クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDocDeliveryManagementHistory
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDeliveryManagementHistory.class);

    /**
     * (書面交付管理履歴)<BR>
     * ディフォルトコンストラクタ<BR>
     * @@roseuid 4727D4960121
     */
    public WEB3AdminFPTDocDeliveryManagementHistory()
    {

    }

    /**
     * (insert書面交付管理履歴テーブル)<BR>
     * 書面交付管理テーブルのInsertを行う。<BR>
     * <BR>
     * QueryProcessor#doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@arg0： 引数.書面交付管理履歴テーブルParams<BR>
     * @@param l_docDeliveryManagementHistParams - (書面交付管理履歴テーブルParams)<BR>
     * 書面交付管理履歴テーブルParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4726F2AF019C
     */
    public void insertDocDeliveryManagementHist(
        DocDeliveryManagementHistParams l_docDeliveryManagementHistParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertDocDeliveryManagementHist(DocDeliveryManagementHistParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１） QueryProcessor#doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[引数]
            //arg0： 引数.書面交付管理履歴テーブルParams
            l_queryProcessor.doInsertQuery(l_docDeliveryManagementHistParams);
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

    /**
     * (get書面交付管理履歴行)<BR>
     * 書面交付管理履歴テーブルより検索を行う。<BR>
     * <BR>
     * １） 以下条件で書面交付管理履歴テーブルより検索を行う。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@口座ID = 引数:口座ID<BR>
     * 　@　@　@書面区分 = 引数:書面区分<BR>
     * 　@　@　@銘柄コード = 引数:銘柄コード<BR>
     * 　@　@　@書面交付日 = 引数:書面交付<BR>
     * 　@　@　@作成日付 = 引数:作成日付<BR>
     * 　@　@　@書面種類コード = 引数.書面種類コード<BR>
     * <BR>
     * ２） 検索結果が取得できた場合、取得した書面交付管理履歴テーブル行を返却する。<BR>
     * <BR>
     * ３） 検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strDocumentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_datDocuDeliDate - (書面交付日)<BR>
     * 書面交付日<BR>
     * @@param l_datCurrentTime - (作成日付)<BR>
     * 作成日付<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     * @@return DocDeliveryManagementHistRow
     * @@throws WEB3BaseException
     * @@roseuid 4727CD88034B
     */
    public DocDeliveryManagementHistRow getDocDeliveryManagementHist(
        long l_lngAccountId,
        String l_strDocumentDiv,
        String l_strProductCode,
        Date l_datDocuDeliDate,
        Date l_datCurrentTime,
        String l_strDocumentCategory)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDocDeliveryManagementHist(long, String, String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        DocDeliveryManagementHistRow l_docDeliveryManagementHistRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[条件]
            //口座ID = 引数:口座ID
            //書面区分 = 引数:書面区分
            //銘柄コード = 引数:銘柄コード
            //書面交付日 = 引数:書面交付日
            //作成日付 = 引数:作成日付
            DocDeliveryManagementHistPK l_DocDeliveryManagementHistPK =
                new DocDeliveryManagementHistPK(
                    l_lngAccountId,
                    l_strDocumentDiv,
                    l_strProductCode,
                    new Timestamp(l_datDocuDeliDate.getTime()),
                    new Timestamp(l_datCurrentTime.getTime()),
                    l_strDocumentCategory);

            l_docDeliveryManagementHistRow =
                (DocDeliveryManagementHistRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_DocDeliveryManagementHistPK);
        }
        catch (DataFindException l_ex)
        {
            //３） 検索結果が取得できなかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
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

        //２） 検索結果が取得できた場合、取得した書面交付管理履歴テーブル行を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_docDeliveryManagementHistRow;
    }
}
@
