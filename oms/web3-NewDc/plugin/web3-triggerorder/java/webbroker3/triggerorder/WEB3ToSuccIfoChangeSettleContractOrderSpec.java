head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約返済注文訂正内容(WEB3ToSuccIfoChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 崔遠鵬 (中訊) 新規作成
Revision History : 2008/03/18 趙林鵬 (中訊) モデルNO.271，284, 291
*/
package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP予約返済注文訂正内容)<BR>
 * @@author 崔遠鵬
 * @@version 1.0
 */
public class WEB3ToSuccIfoChangeSettleContractOrderSpec extends IfoChangeSettleContractOrderSpec
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoChangeSettleContractOrderSpec.class);

    /**
     * @@param l_lngOrderId<BR>
     * @@param l_lngOrderUnitId<BR>
     * @@param l_dblAfterChangePrice<BR>
     * @@param l_newContractEntries<BR>
     */
    public WEB3ToSuccIfoChangeSettleContractOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblAfterChangePrice,
        SettleContractEntry[] l_newContractEntries)
    {
        super(l_lngOrderId, l_lngOrderUnitId, l_dblAfterChangePrice, l_newContractEntries);
    }

    /**
     * (扱者)<BR>
     * 代理入力扱者。<BR>
     */
    private WEB3GentradeTrader trader;

    /**
     * (訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金<BR>
     */
    private double modifiedEstimatedPrice;

    /**
     * (訂正後計算単価)<BR>
     * 訂正後計算単価<BR>
     */
    private double modifiedCalcUnitPrice;

    /**
     * (訂正後注文失効日)<BR>
     * 訂正後注文失効日<BR>
     */
    private Date modifiedExpirationDate;

    /**
     * (訂正後単価調整値)<BR>
     * 訂正後単価調整値<BR>
     */
    private Double modifiedPriceAdjustValue;

    /**
     * (初回注文の注文単位ID)<BR>
     * 初回注文の注文単位ID<BR>
     */
    private Long firstOrderUnitId;

    /**
     * (夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグ<BR>
     */
    private boolean eveningSessionCarryOverFlag;

    /**
     * (注文期限区分)<BR>
     * 注文期限区分<BR>
     */
    private String expirationDateType;

    /**
     * (create先物OP予約返済注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 先物OP予約返済注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（連続注文）create先物OP予約返済注文訂正内容」を参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_dblModifiedLimitPrice - (訂正後指値)<BR>
     * 訂正後指値<BR>
     * @@param l_settleContractEntries - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@param l_dblModifiedEstimatedPrice - (訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金<BR>
     * @@param l_dblModifiedCalcUnitPrice - (訂正後計算単価)<BR>
     * 訂正後計算単価<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正後注文失効日<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト<BR>
     * @@param l_modifiedPriceAdjustValue - (訂正後単価調整値)<BR>
     * 訂正後単価調整値<BR>
     * （単価調整値指定なし時はnullをセット）<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * 注文期限区分<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccIfoChangeSettleContractOrderSpec
     * @@throws WEB3BaseException
     */
    public static WEB3ToSuccIfoChangeSettleContractOrderSpec createIfoChangeSettleContractOrderSpec(
        long l_lngOrderId,
        double l_dblModifiedLimitPrice,
        SettleContractEntry[] l_settleContractEntries,
        double l_dblModifiedEstimatedPrice,
        double l_dblModifiedCalcUnitPrice,
        Date l_datModifiedExpirationDate,
        WEB3GentradeTrader l_trader,
        Double l_modifiedPriceAdjustValue,
        String l_strExpirationDateType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoChangeSettleContractOrderSpec(long, "
            + "double, SettleContractEntry[], double, double, double, Date, WEB3GentradeTrader,"
            + " Double, String) ";
        log.entering(STR_METHOD_NAME);

        //IfoChangeSettleContractOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : SettleContractEntry[])
        //注文ID：　@パラメータ.注文ID
        //注文単位ID：　@-1（注文未発注の状態を表す）
        //訂正後指値：　@パラメータ.訂正後指値
        //返済建玉エントリ：　@パラメータ.返済建玉エントリ
        WEB3ToSuccIfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec =
            new WEB3ToSuccIfoChangeSettleContractOrderSpec(
                l_lngOrderId,
                -1,
                l_dblModifiedLimitPrice,
                l_settleContractEntries);

        // set訂正後概算受渡代金(double)
        //訂正後概算受渡代金：　@パラメータ.訂正後概算受渡代金
        l_ifoChangeSettleContractOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);

        //set訂正後計算単価(double)
        //訂正後計算単価：　@パラメータ.訂正後計算単価
        l_ifoChangeSettleContractOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);

        //set訂正後注文失効日(Date)
        //訂正後注文失効日：　@パラメータ.訂正後注文失効日
        l_ifoChangeSettleContractOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);

        //set扱者(扱者)
        //扱者：　@パラメータ.代理入力者
        l_ifoChangeSettleContractOrderSpec.setTrader(l_trader);

        //set訂正後単価調整値(Double)
        //訂正後単価調整値：　@パラメータ.訂正後単価調整値
        l_ifoChangeSettleContractOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);

        //get初回注文の注文単位ID(注文期限区分 : String)
        //注文期限区分：　@パラメータ.注文期限区分
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_strExpirationDateType);

        //set初回注文の注文単位ID(Long)
        //初回注文の注文単位ID ： 先物OPデータアダプタ.get初回注文の注文単位ID()
        l_ifoChangeSettleContractOrderSpec.setFirstOrderUnitId(l_firstOrderUnitId);

        //OpLoginSecurityServiceより編集した部店ID
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //口座を取得する
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountMananger.getMainAccount(l_opLoginSecurityService.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3ToSuccIfoChangeSettleContractOrderSpec." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //部店ID
        long l_lngBranchId = l_mainAccount.getBranch().getBranchId();

        //get夕場前繰越対象フラグ(注文期限区分 : String, 部店ID : long)
        //注文期限区分：　@パラメータ.注文期限区分
        //部店ID：　@OpLoginSecurityServiceより編集した部店ID
        boolean l_blnIsEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(l_strExpirationDateType, l_lngBranchId);

        //set夕場前繰越対象フラグ(boolean)
        //夕場前繰越対象フラグ ： 先物OPデータアダプタ.get夕場前繰越対象フラグ()
        l_ifoChangeSettleContractOrderSpec.setEveningSessionCarryOverFlag(l_blnIsEveningSessionCarryOverFlag);

        //set注文期限区分(String)
        //注文期限区分：　@パラメータ.注文期限区分
        l_ifoChangeSettleContractOrderSpec.setExpirationDateType(l_strExpirationDateType);

        log.exiting(STR_METHOD_NAME);
        return l_ifoChangeSettleContractOrderSpec;
    }

    /**
     * (get訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金を取得する。<BR>
     * @@return double
     */
    public double getModifiedEstimatedPrice()
    {
        return this.modifiedEstimatedPrice;
    }

    /**
     * (set訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金をセットする。<BR>
     * @@param l_dblModifiedEstimatedPrice - (訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金<BR>
     */
    public void setModifiedEstimatedPrice(double l_dblModifiedEstimatedPrice)
    {
        this.modifiedEstimatedPrice = l_dblModifiedEstimatedPrice;
    }

    /**
     * (get訂正後計算単価)<BR>
     * 訂正後計算単価を取得する。<BR>
     * @@return double
     */
    public double getModifiedCalcUnitPrice()
    {
        return this.modifiedCalcUnitPrice;
    }

    /**
     * (set訂正後計算単価)<BR>
     * 訂正後計算単価をセットする。<BR>
     * @@param l_dblModifiedCalcUnitPrice - (訂正後計算単価)<BR>
     * 訂正後計算単価<BR>
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice)
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }

    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後注文失効日を取得する。<BR>
     * @@return Date
     */
    public Date getModifiedExpirationDate()
    {
        return this.modifiedExpirationDate;
    }

    /**
     * (set訂正後注文失効日)<BR>
     * 訂正後注文失効日をセットする。<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正後注文失効日<BR>
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate)
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }

    /**
     * (get扱者)<BR>
     * 扱者を取得する。<BR>
     * @@return WEB3GentradeTrader
     */
    public WEB3GentradeTrader getTrader()
    {
        return this.trader;
    }

    /**
     * (set扱者)<BR>
     * 扱者をセットする。<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者<BR>
     */
    public void setTrader(WEB3GentradeTrader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (get訂正後単価調整値)<BR>
     * 訂正後単価調整値を取得する。<BR>
     * @@return Double
     */
    public Double getModifiedPriceAdjustValue()
    {
        return this.modifiedPriceAdjustValue;
    }

    /**
     * (set訂正後単価調整値)<BR>
     * 訂正後単価調整値をセットする。<BR>
     * @@param l_modifiedPriceAdjustValue - (訂正後単価調整値)<BR>
     * 訂正後単価調整値<BR>
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue)
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }

    /**
     * (get初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDを取得する。<BR>
     * @@return Long
     */
    public Long getFirstOrderUnitId()
    {
        return this.firstOrderUnitId;
    }

    /**
     * (set初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDをセットする。<BR>
     * @@param l_firstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 初回注文の注文単位ID<BR>
     */
    public void setFirstOrderUnitId(Long l_firstOrderUnitId)
    {
        this.firstOrderUnitId = l_firstOrderUnitId;
    }

    /**
     * (get夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグを取得する。<BR>
     * @@return boolean
     */
    public boolean getEveningSessionCarryOverFlag()
    {
        return this.eveningSessionCarryOverFlag;
    }

    /**
     * (set夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグをセットする。<BR>
     * @@param l_blnIsEveningSessionCarryOverFlag - (夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグ<BR>
     */
    public void setEveningSessionCarryOverFlag(boolean l_blnIsEveningSessionCarryOverFlag)
    {
        this.eveningSessionCarryOverFlag = l_blnIsEveningSessionCarryOverFlag;
    }

    /**
     * (get注文期限区分)<BR>
     * 注文期限区分を取得する。<BR>
     * @@return String
     */
    public String getExpirationDateType()
    {
        return this.expirationDateType;
    }

    /**
     * (set注文期限区分)<BR>
     * 注文期限区分をセットする。<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * 注文期限区分<BR>
     */
    public void setExpirationDateType(String l_strExpirationDateType)
    {
        this.expirationDateType = l_strExpirationDateType;
    }
}
@
