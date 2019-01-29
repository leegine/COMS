head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3ForeignPositionContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨残高情報(WEB3ForeignPositionContract.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/03 張騰宇 (中訊) 新規作成 仕様変更モデル122
Revision History : 2007/08/07 金傑 (中訊)　@仕様変更モデル152
Revision History : 2007/08/17 金傑 (中訊)　@仕様変更モデル165
*/
package webbroker3.tradingpower;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.data.TpCashBalanceFrgnRow;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 外貨残高情報<BR>
 * (外貨残高情報)<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3ForeignPositionContract
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ForeignPositionContract.class);

    /**
     * (外貨残高Params)<BR>
     * 外貨残高Params<BR>
     */
    protected TpCashBalanceFrgnParams tpCashBalanceFrgnParams;

    /**
     * (外貨残高情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3ForeignPositionContract()
    {

    }

    /**
     * (get外貨残高Params)<BR>
     * get外貨残高Params<BR>
     * <BR>
     * this.外貨残高Paramsを返却する。<BR>
     * @@return TpCashBalanceFrgnParams
     */
    public TpCashBalanceFrgnParams getTpCashBalanceFrgnParams()
    {
        return tpCashBalanceFrgnParams;
    }

    /**
     * (set外貨残高Params)<BR>
     * set外貨残高Params<BR>
     * <BR>
     * パラメータ.外貨残高Paramsをthis.外貨残高Paramsにセットする。<BR>
     * @@param l_tpCashBalanceFrgnParams - (外貨残高Params)<BR>
     * 外貨残高Params<BR>
     */
    public void setTpCashBalanceFrgnParams(TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams)
    {
        this.tpCashBalanceFrgnParams = l_tpCashBalanceFrgnParams;
    }

    /**
     * (get外貨残高)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「get外貨残高」を返却する。 <BR>
     * <BR>
     * １）　@nullチェックを行う。 <BR>
     * this.外貨残高Paramsがnullの時、nullを返却する。 <BR>
     * <BR>
     * ２）　@引数チェックを行う。 <BR>
     * 引数が0以上5以下でない時、0を返却する。 <BR>
     * <BR>
     * ３）　@引数で指定された指定日(=n)の「外貨残高」を返却する。 <BR>
     * ［返却値］ <BR>
     * this.外貨残高Params.get外貨残高（T+n）<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * 指定日<BR>
     * @@return Double
     */
    public Double getForeignPositionBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getForeignPositionBalance(int)";
        log.entering(STR_METHOD_NAME);

        //引数で指定された指定日(=n)の、「外貨残高」を返却する。
        //nullチェックを行う。
        //this.外貨残高Paramsがnullの時、nullを返却する。
        if (this.tpCashBalanceFrgnParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        double l_dblCashBalance = 0;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //get外貨残高( T + 0 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //get外貨残高( T + 1 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //get外貨残高( T + 2 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //get外貨残高( T + 3 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //get外貨残高( T + 4 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //get外貨残高( T + 5 )を取得する。
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn5();
                break;
        }

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblCashBalance);
    }

    /**
     * (create外貨残高情報)<BR>
     * (staticメソッド)(create外貨残高情報) <BR>
     * <BR>
     * １）外貨残高情報オブジェクトを生成する。 <BR>
     * <BR>
     * 　@－コンストラクタ、外貨残高情報()コール  <BR>
     * <BR>
     * <BR>
     * ２）顧客勘定残高（マスタ情報）（外貨）テーブルを取得 <BR>
     * <BR>
     * 　@－顧客勘定残高（マスタ情報）（外貨）テーブルを以下の条件で検索する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@口座ID：引数.口座ID <BR>
     * 　@　@　@通貨コード：引数.通貨コード <BR>
     * <BR>
     * 　@　@[a.検索結果 == null または 検索結果.size() == 0 の場合]  <BR>
     * 　@　@　@外貨残高Params = null  <BR>
     * <BR>
     * 　@　@[ｂ.a以外 の場合]  <BR>
     * 　@　@　@外貨残高Params = （外貨残高Params）検索結果.get(0)  <BR>
     * <BR>
     * <BR>
     * ３）生成した外貨残高情報オブジェクトを返却する。<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public static WEB3ForeignPositionContract createForeignPositionContract(
        long l_lngAccountId,
        String l_strCurrencyCode) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createForeignPositionContract(long, String)";
        log.entering(STR_METHOD_NAME);

        // １）外貨残高情報オブジェクトを生成する。
        WEB3ForeignPositionContract l_foreignPositionContract = new WEB3ForeignPositionContract();

        // ２）顧客勘定残高（マスタ情報）（外貨）テーブルを取得
        try
        {

            // ［検索条件］
            // 　@　@ 口座ID：引数.口座ID
            //　@　@　@通貨コード：引数.通貨コード
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisTpCashBalanceFrgnRows = l_queryProcessor.doFindAllQuery(
                TpCashBalanceFrgnRow.TYPE,
                "account_id=? and currency_code=?",
                new Object[]{new Long(l_lngAccountId), l_strCurrencyCode});

            // 検索結果 == null または 検索結果.size() == 0 の場合
            if (l_lisTpCashBalanceFrgnRows == null || l_lisTpCashBalanceFrgnRows.size() == 0)
            {
                l_foreignPositionContract.tpCashBalanceFrgnParams = null;
            }

            // 以外 の場合
            else
            {
                l_foreignPositionContract.tpCashBalanceFrgnParams =
                    new TpCashBalanceFrgnParams((TpCashBalanceFrgnRow)l_lisTpCashBalanceFrgnRows.get(0));
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_foreignPositionContract;
    }
}
@
