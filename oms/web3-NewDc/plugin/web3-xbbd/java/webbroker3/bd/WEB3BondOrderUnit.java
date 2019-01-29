head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張債券注文単位(WEB3BondOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                      : 2006/10/08 張騰宇 (中訊) モデル 110
*/

package webbroker3.bd;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.define.WEB3BondDealDivDef;

/**
 * (拡張債券注文単位)<BR>
 *
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3BondOrderUnit extends BondOrderUnitImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderUnit.class);

    /**
     * (拡張債券注文単位)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * super(債券注文単位ID)をコールする。 <BR>
     * @@param l_lngOrderUnitId - (債券注文単位ID)<BR>
     * 債券注文単位ID
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 44BC382502D7
     */
    public WEB3BondOrderUnit(long l_lngOrderUnitId) throws DataQueryException, DataNetworkException
    {
        super(l_lngOrderUnitId);
    }

    /**
     * (拡張債券注文単位)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * super(債券注文単位Row)をコールする。 <BR>
     * @@param l_row - (債券注文単位Row)<BR>
     * 債券注文単位Row
     * @@roseuid 44BC381103BE
     */
    public WEB3BondOrderUnit(BondOrderUnitRow l_row)
    {
        super(l_row);
    }

    /**
     * (get取引)<BR>
     * 取引を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get取引()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC39A301AA
     */
    public String getDealType()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getDealType();
    }

    /**
     * (get現地受渡日)<BR>
     * 現地受渡日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get現地受渡日()の戻り値を返す<BR>
     * @@return Date
     * @@roseuid 44BC3A0901B7
     */
    public Date getForeignDeliveryDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignDeliveryDate();
    }

    /**
     * (get注文ロック区分)<BR>
     * 注文ロック区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get注文ロック区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3A630127
     */
    public String getLockStatus()
    {
        String l_strLockStatus = ((BondOrderUnitRow)this.getDataSourceObject()).getLockStatus();
        return l_strLockStatus;
    }

    /**
     * (get注文約定区分)<BR>
     * 注文約定区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get注文約定区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3B05035D
     */
    public String getOrderExecStatus()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderExecStatus();
    }

    /**
     * (get現地発注日)<BR>
     * 現地発注日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get現地発注日()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3B3E0045
     */
    public String getForeignBizDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignBizDate();
    }

    /**
     * (get初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネルを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get初回注文の注文チャネル()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3B7702E9
     */
    public String getOrderChannel()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderChanel();
    }

    /**
     * (get受注日時)<BR>
     * 受注日時を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受注日時()の戻り値を返す。<BR>
     * @@return Date
     * @@roseuid 44BC3D000330
     */
    public Date getReceivedDateTime()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getReceivedDateTime();
    }

    /**
     * (get扱者コードSONAR)<BR>
     * 顧客の扱者コードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get扱者コードSONAR()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3D270273
     */
    public String getSonarTraderCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getSonarTraderCode();
    }

    /**
     * (get注文単価)<BR>
     * 注文単価を返す<BR>
     * @@return double
     * @@roseuid 44CFFD9A034F
     */
    public double getPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getPrice();
    }

    /**
     * (get識別コード)<BR>
     * 識別コードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get識別コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3D4E01D6
     */
    public String getOrderRequestNumber()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderRequestNumber();
    }

    /**
     * (get債券タイプ)<BR>
     * 債券タイプを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get債券タイプ()の戻り値を返す。<BR>
     * @@return BondTypeEnum
     * @@roseuid 44BC3D7700BD
     */
    public BondTypeEnum getBondType()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBondType();
    }

    /**
     * (get通貨コード)<BR>
     * 通貨コードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get通貨コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3D8301D6
     */
    public String getCurrencyCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCurrencyCode();
    }

    /**
     * (get決済区分)<BR>
     * 決済区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get決済区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3DAA0030
     */
    public String getSettlementDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getSettlementDiv();
    }

    /**
     * (get自動約定区分)<BR>
     * 自動約定区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get自動約定区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3DC3034C
     */
    public String getAutoExecDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAutoExecDiv();
    }

    /**
     * (get約定単価)<BR>
     * 約定単価を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get約定単価()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44BC3DDC037B
     */
    public double getExecutedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecutedPrice();
    }

    /**
     * (get基準為替レート)<BR>
     * 基準為替レートを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get基準為替レート()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44BC3DFF033C
     */
    public double getBaseFxRate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBaseFxRate();
    }

    /**
     * (get約定為替レート)<BR>
     * 約定為替レートを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get約定為替レート()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44BC3E2102A0
     */
    public double getExecFxRate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecFxRate();
    }

    /**
     * (get売買代金(円貨))<BR>
     * 売買代金(円貨)を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get売買代金(円貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44BC3E3E007D
     */
    public double getTradingPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getTradingPrice();
    }

    /**
     * (get売買代金(外貨))<BR>
     * 売買代金(外貨)を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get売買代金(外貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44BC3E660261
     */
    public double getForeignTradingPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignTradingPrice();
    }

    /**
     * (get経過利子(円貨))<BR>
     * 経過利子(円貨)を返却<BR>
     * <BR>
     * this.getDataSourceObject().get経過利子(円貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C710A30293
     */
    public double getAccruedInterest()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAccruedInterest();
    }

    /**
     * (get経過利子(外貨))<BR>
     * 経過利子(外貨)を返却<BR>
     * <BR>
     * this.getDataSourceObject().get経過利子(外貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C7101202DC
     */
    public double getForeignAccruedInterest()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignAccruedInterest();
    }

    /**
     * (get受渡代金(円貨))<BR>
     * 受渡代金(円貨)を返却<BR>
     * <BR>
     * this.getDataSourceObject().get受渡代金(円貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C7117E0104
     */
    public double getEstimatedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getEstimatedPrice();
    }

    /**
     * (get受渡代金(外貨))<BR>
     * 受渡代金(外貨)を返却<BR>
     * <BR>
     * this.getDataSourceObject().get受渡代金(外貨)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C71108018D
     */
    public double getForeignEstimatedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignEstimatedPrice();
    }

    /**
     * (get経過日数)<BR>
     * 経過日数を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get経過日数()の戻り値を返す。<BR>
     * @@return int
     * @@roseuid 44BC3E95034C
     */
    public int getElapsedDays()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getElapsedDays();
    }

    /**
     * (get基準日数)<BR>
     * 基準日数を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get基準日数()の戻り値を返す。<BR>
     * @@return int
     * @@roseuid 44BC3EBB035C
     */
    public int getCalcBaseDays()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCalcBaseDays();
    }

    /**
     * (get約定日)<BR>
     * 約定日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get約定日()の戻り値を返す。<BR>
     * @@return Date
     * @@roseuid 44BC3ED7035C
     */
    public Date getExecDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecDate();
    }

    /**
     * (get現地約定日)<BR>
     * 現地約定日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get現地約定日()の戻り値を返す。<BR>
     * @@return Date
     * @@roseuid 44BC3F000205
     */
    public Date getForeignExecDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignExecDate();
    }

    /**
     * (getカストディアンコード)<BR>
     * カストディアンコードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getカストディアンコード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3F1B038C
     */
    public String getCustodianCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCustodianCode();
    }

    /**
     * (get注文経路区分)<BR>
     * 注文経路区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get注文経路区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3F490310
     */
    public String getOrderRootDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderRootDiv();
    }

    /**
     * (get注文単位紹介区分)<BR>
     * OrderUnitIntroduceDivRowを返す。<BR>
     * <BR>
     * １）注文単位紹介区分テーブルを検索する。<BR>
     * 　@　@検索条件：<BR>
     * 　@　@　@注文単位紹介区分テーブル.注文単位ID＝this.getOrderUnitId<BR>
     * 　@　@　@注文単位紹介区分テーブル.銘柄タイプ　@＝this.getProductType<BR>
     * 　@　@　@　@※該当レコードが存在しない場合、エラーをスローしない。<BR>
     * <BR>
     * ２）戻り値を返す<BR>
     * 　@２－１）レコードが存在する場合、<BR>
     * 　@　@　@　@　@　@検索したRowオブジェクトを返す<BR>
     * 　@２－２）レコードが存在しない場合、<BR>
     * 　@　@　@　@　@　@nullを返す<BR>
     * @@return OrderUnitIntroduceDivRow
     * @@roseuid 44BC3F6E036F
     */
    public OrderUnitIntroduceDivRow getOrderUnitIntroduceDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitIntroduceDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strWhere = "order_unit_id = ? and product_type = ?";
        Object[] l_objbindVars =  new Object[2];
        l_objbindVars[0] = new Long(this.getOrderUnitId());
        l_objbindVars[1] = new Integer(this.getProductType().intValue());
        List l_lisRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                OrderUnitIntroduceDivRow.TYPE,
                l_strWhere,
                null,
                l_objbindVars);
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

        if (l_lisRow == null || l_lisRow.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return (OrderUnitIntroduceDivRow)l_lisRow.get(0);
        }
    }

    /**
     * (getHOST送信区分)<BR>
     * HOST送信区分を返す<BR>
     * <BR>
     * this.getDataSourceObject().getHOST送信区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CF21C301E1
     */
    public String getHostSendDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getHostSendDiv();
    }

    /**
     * (get管理者コード)<BR>
     * 管理者コードを返す<BR>
     * <BR>
     * this.getDataSourceObject().get管理者コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CFFE2C03DD
     */
    public String getAdminstratorCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAdministratorCode();
    }

    /**
     * (get注文エラー理由コード)<BR>
     * 注文エラー理由コードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get注文エラー理由コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3FBA017C
     */
    public String getErrorReasonCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getErrorReasonCode();
    }

    /**
     * (get債券注文種別判定)<BR>
     * get債券注文種別判定<BR>
     * <BR>
     * 債券注文種別判定（注文種別, 取引）を生成し、返す。<BR>
     * [コンストラクタの引数]<BR>
     * 注文種別：this.getOrderType<BR>
     * 取引　@　@：this.get取引<BR>
     * @@return WEB3BondOrderTypeJudge
     * @@throws WEB3BaseException
     * @@roseuid 44CAFC7A0343
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() throws WEB3BaseException
    {
        WEB3BondOrderTypeJudge l_judge =
            new WEB3BondOrderTypeJudge(this.getOrderType(), this.getDealType());
        return l_judge;
    }

    /**
     * (get取引区分)<BR>
     * 取引区分を取得する。<BR>
     * <BR>
     * １）this.get債券注文種別判定をコールし、債券注文種別判定を作成する。 <BR>
     * <BR>
     * ２）債券注文種別判定.is応募注文()の戻り値 == true の場合、 <BR>
     * "応募"（取引区分）を返却する。 <BR>
     * <BR>
     * ３）債券注文種別判定.is買付注文()の戻り値 == true の場合、<BR>
     * "買付"（取引区分）を返却する。 <BR>
     * <BR>
     * ４）債券注文種別判定.is売却注文()の戻り値 == true の場合、 <BR>
     * "売却"（取引区分）を返却する。 <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44CAFC7A0343
     */
    public String getDealDiv() throws WEB3BaseException
    {
        //１）this.get債券注文種別判定をコールし、債券注文種別判定を作成する。
        WEB3BondOrderTypeJudge l_judge = this.getBondOrderTypeJudge();

        //２）債券注文種別判定.is応募注文()の戻り値 == true の場合
        if (l_judge.isRecruitOrder())
        {
            return WEB3BondDealDivDef.RECRUIT;
        }

        //３）債券注文種別判定.is買付注文()の戻り値 == true の場合
        else if (l_judge.isBuyOrder())
        {
            return WEB3BondDealDivDef.BUY;
        }

        //４）債券注文種別判定.is売却注文()の戻り値 == true の場合
        else if (l_judge.isSellOrder())
        {
            return WEB3BondDealDivDef.SELL;
        }

        else
        {
            return null;
        }
    }

    /**
     * (get銘柄ID)<BR>
     * get銘柄ID<BR>
     * <BR>
     * this.getDataSourceObject().get銘柄ID()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 44BC3FBA017C
     */
    public long getProductId()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getProductId();
    }

    /**
     * (get発注日)<BR>
     * 発注日を返す<BR>
     * <BR>
     * this.getDataSourceObject().get発注日()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44BC3FBA017C
     */
    public String getBizDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBizDate();
    }

    /**
     * (get入金日)<BR>
     * 入金日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get入金日()の戻り値を返す。<BR>
     * @@return Date
     */
    public Date getPaymentDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getPaymentDate();
    }
}
@
