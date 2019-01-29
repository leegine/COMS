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
filename	WEB3GentradeAccOpenDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設区分(WEB3GentradeAccOpenDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/16 趙林鵬(中訊) 新規作成 モデルNo.339,341,342,343,344,345
*/

package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.gentrade.data.AccOpenDivDao;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設区分)<BR>
 * 口座開設区分クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3GentradeAccOpenDiv implements BusinessObject
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeAccOpenDiv.class);

    /**
     * (口座開設区分行)<BR>
     * 口座開設区分行オブジェクト<BR>
     * <BR>
     * ※ 口座開設区分ParamsクラスはDDLより自動生成する。<BR>
     * ※ DBレイアウト「口座開設区分テーブル仕様.xls」参照。<BR>
     */
    private AccOpenDivParams accOpenDivParams;

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.口座開設区分Paramsを返却する。<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.accOpenDivParams;
    }

    /**
     * (口座開設区分)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 口座開設区分オブジェクトを生成する。<BR>
     */
    public WEB3GentradeAccOpenDiv()
    {
        this.accOpenDivParams = new AccOpenDivParams();
    }

    /**
     * (口座開設区分)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の条件に一致する口座開設区分オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@引数の値をキーとして口座開設区分テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（口座開設区分Row）を this.口座開設区分Rowにセットする。<BR>
     * <BR>
     * ※データが取得出来なかった場合はnullをセットする。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strAccType - (口座種別)<BR>
     * 口座種別<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeAccOpenDiv(long l_lngAccountId, String l_strAccType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeAccOpenDiv(long, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@DB検索
        //引数の値をキーとして口座開設区分テーブルを検索する。
        AccOpenDivRow l_accOpenDivRow = null;

        try
        {
            l_accOpenDivRow =
                AccOpenDivDao.findRowByAccountIdAccType(l_lngAccountId, l_strAccType);
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

        //検索結果の行オブジェクト（口座開設区分Row）を this.口座開設区分Rowにセットする。
        //データが取得出来なかった場合はnullをセットする。
        if (l_accOpenDivRow != null)
        {
            this.accOpenDivParams = new AccOpenDivParams(l_accOpenDivRow);
        }
        else
        {
            this.accOpenDivParams = null;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get口座開設区分)<BR>
     * this.口座開設区分行.口座開設区分を返却する。<BR>
     * <BR>
     * ※this.口座開設区分行がnullの場合は、”0:未開設”を返却する。<BR>
     * @@return String
     */
    public String getAccOpenDiv()
    {
        final String STR_METHOD_NAME = "getAccOpenDiv()";
        log.entering(STR_METHOD_NAME);

        //this.口座開設区分行がnullの場合は、”0:未開設”を返却する。
        if (this.accOpenDivParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenDef.NOT_OPEN;
        }

        log.exiting(STR_METHOD_NAME);
        return this.accOpenDivParams.getAccOpenDiv();
    }

    /**
     * (insert口座開設区分)<BR>
     * 引数の内容で口座開設区分テーブル(acc_open_div)に行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「FX口座開設_口座開設区分テーブル.xls」<BR>
     * ((FX口座開設)口座開設区分テーブル_DB更新仕様)<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strAccType - (口座種別)<BR>
     * 口座種別<BR>
     * @@param l_strAccOpenDiv - (口座開設区分)<BR>
     * 口座開設区分<BR>
     * @@param l_strLastUpdater - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void insertAccOpenDiv(
        long l_lngAccountId, String l_strAccType, String l_strAccOpenDiv, String l_strLastUpdater)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertAccOpenDiv(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        AccOpenDivParams l_accOpenDivParams = new AccOpenDivParams();

        //引数.口座ID
        l_accOpenDivParams.setAccountId(l_lngAccountId);

        //引数.口座種別
        l_accOpenDivParams.setAccType(l_strAccType);

        //引数.口座開設区分
        l_accOpenDivParams.setAccOpenDiv(l_strAccOpenDiv);

        //処理日時(YYYY/MM/DD 00:00:00)
        Date l_datProcessDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        l_accOpenDivParams.setAccOpenDate(l_datProcessDate);

        //引数.更新者コード
        l_accOpenDivParams.setLastUpdater(l_strLastUpdater);

        //現在時刻
        l_accOpenDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //現在時刻
        l_accOpenDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
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
     * (update口座開設区分)<BR>
     * 口座開設区分テーブル.口座開設区分をupdateする。<BR>
     * <BR>
     * 更新する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「管理者・口座管理_口座開設区分テーブルDB更新仕様」を参照<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strAccType - (口座種別)<BR>
     * 口座種別<BR>
     * @@param l_strAccOpenDiv - (口座開設区分)<BR>
     * 口座開設区分<BR>
     * @@param l_strLastUpdater - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateAccOpenDiv(
        long l_lngAccountId, String l_strAccType, String l_strAccOpenDiv, String l_strLastUpdater)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAccOpenDiv(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AccOpenDivRow l_accOpenDivRow =
                AccOpenDivDao.findRowByPk(l_lngAccountId, l_strAccType);

            AccOpenDivParams l_accOpenDivParams = new AccOpenDivParams(l_accOpenDivRow);

            //引数.口座開設区分
            l_accOpenDivParams.setAccOpenDiv(l_strAccOpenDiv);

            //引数.更新者コード
            l_accOpenDivParams.setLastUpdater(l_strLastUpdater);

            //現在時刻
            l_accOpenDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_accOpenDivParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
    }
}
@
