head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消通知データアダプタ(WEB3EquityChangeCancelNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 中尾寿彦(SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式訂正取消通知データアダプタ）。
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyDataAdapter
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityChangeCancelNotifyDataAdapter.class);

    /**
     * 株式訂正取消通知キューParams<BR>
     */
    private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    private WEB3EquityChangeCancelNotifyDataAdapter()
    {
    }

    /**
     * (create)<BR>
     * <BR>
     * 株式訂正取消通知データアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@生成したインスタンスに引数のキューデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、本メソッドによってインスタンス化するように制限する）<BR>
     * <BR>
     * @@param l_equityReceiveChangeCancelQueueParams - (株式訂正取消通知キューParams)<BR>
     * 【株式訂正取消通知キューテーブル】データオブジェクト
     * @@return WEB3EquityChangeCancelNotifyDataAdapter
     */
    public static WEB3EquityChangeCancelNotifyDataAdapter create(
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        final String STR_METHOD_NAME = "create(HostEqtypeOrderClmdReceiptParams)";
        log.entering(STR_METHOD_NAME);

        // １）　@本インスタンスを生成しする。
        WEB3EquityChangeCancelNotifyDataAdapter l_adapter =
            new WEB3EquityChangeCancelNotifyDataAdapter();

        // ２）　@生成したインスタンスに引数のキューデータをセットする。
        l_adapter.hostEqtypeOrderClmdReceiptParams =
            l_hostEqtypeOrderClmdReceiptParams;

        log.exiting(STR_METHOD_NAME);
        // ３）　@インスタンスを返却する。
        return l_adapter;
    }

    /**
     * (is指値)<BR>
     * <BR>
     * 株式訂正取消通知キューParams.訂正後指値＝0の場合、falseを返す。<BR>
     * 上記以外の場合は、trueを返す。<BR>
     * @@return boolean
     */
    public boolean isLimitPrice()
    {
        if (hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice() == 0.0D)
        {
            return false;
        }

        return true;
    }

    /**
     * (is成行)<BR>
     * <BR>
     * this.is指値() == trueの場合はfalseを、<BR>
     * this.is指値() == falseの場合はtrueを、返却する。<BR>
     * @@return boolean
     */
    public boolean isMarketOrder()
    {
        return (this.isLimitPrice() == false);
    }

    /**
     * (get執行条件)<BR>
     * <BR>
     * 【株式訂正取消通知キューテーブル】訂正後執行条件(SONAR)に応じた<BR>
     * EqTypeExecutionConditionTypeを返す。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(<BR>
     * 　@株式訂正取消通知キューParams.訂正後執行条件(SONAR))にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionType(hostEqtypeOrderClmdReceiptParams.getModifiedExecutionType());
    }

    /**
     * (get値段条件)<BR>
     * <BR>
     * 【株式注文訂正取消通知キューテーブル】訂正後値段条件(SONAR)に応じた<BR>
     * WEBⅢにおける値段条件を返す。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get値段条件(株式訂正取消通知キューParams.値段条件(SONAR))に<BR>
     * delegateする。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getPriceConditionType(hostEqtypeOrderClmdReceiptParams.getModifiedPriceConditionType());
    }
}
@
