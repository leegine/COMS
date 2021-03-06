head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionSettleContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP返済注文リクエストアダプタ(WEB3OptionSettleContractOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 張騰宇 (中訊) 新規作成 モデル 849
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP返済注文リクエストアダプタ)<BR>
 * OP返済注文リクエストアダプタクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3OptionSettleContractOrderRequestAdapter
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderRequestAdapter.class);

    /**
     * (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3GenRequest request;

    /**
     * (コンストラクタ)。<BR>
     */
    protected WEB3OptionSettleContractOrderRequestAdapter()
    {

    }

    /**
     * OP返済注文リクエストアダプタインスタンスを生成する。 <BR>
     * <BR>
     * １）　@本インスタンスを生成する。 <BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。 <BR>
     * ３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3OptionSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    public static WEB3OptionSettleContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //１） 本インスタンスを生成する。
        WEB3OptionSettleContractOrderRequestAdapter l_settleContractOrderResquestAdapter =
            new WEB3OptionSettleContractOrderRequestAdapter();

        //２） 生成したインスタンスに、以下の項目を設定する。
        //リクエストデータ ： 引数.リクエスト
        l_settleContractOrderResquestAdapter.request = l_request;

        //３） インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderResquestAdapter;
    }

    /**
     * (get建玉)<BR>
     * 建玉オブジェクトを返却する。<BR>
     * <BR>
     * リクエスト.返済建玉[0].IDに該当する建玉を<BR>
     * 取得し、返却する。<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoContractImpl getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);

        WEB3IfoContractImpl l_ifoContract = null;
        String l_strID = null;

        if (this.request instanceof WEB3OptionsCloseMarginConfirmRequest)
        {
            l_strID = ((WEB3OptionsCloseMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3OptionsCloseMarginCompleteRequest)
        {
            l_strID = ((WEB3OptionsCloseMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        try
        {
            //リクエスト.返済建玉[0].IDに該当する建玉
            l_ifoContract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_strID));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }

    /**
     * (get注文数量)<BR>
     * リクエストデータ.決済順序、およびリクエストデータ.注文数量より、<BR> 
     * AP層で使用する注文数量を返却する。<BR>
     * <BR>
     * １）　@リクエストデータ.決済順序が”ランダム”の場合、0を返却する。<BR>
     * <BR>
     * ２）　@リクエストデータ.決済順序が”ランダム”以外の場合、<BR>
     * 　@　@　@リクエストデータ.注文数量を返却する。<BR>
     * @@return double
     */
    public double getOrderQuantity()
    {
        final String STR_METHOD_NAME = "getOrderQuantity()";
        log.entering(STR_METHOD_NAME);

        //注文数量
        double l_dblOrderQuantity = 0.0D;
        String l_strOrderQuantity = null;
        //決済順序
        String l_strClosingOrder = null;

        if (this.request instanceof WEB3OptionsCloseMarginConfirmRequest)
        {
            l_strClosingOrder = ((WEB3OptionsCloseMarginConfirmRequest)this.request).closingOrder;
            l_strOrderQuantity = ((WEB3OptionsCloseMarginConfirmRequest)this.request).opOrderQuantity;
        }
        else if (this.request instanceof WEB3OptionsCloseMarginCompleteRequest)
        {
            l_strClosingOrder = ((WEB3OptionsCloseMarginCompleteRequest)this.request).closingOrder;
            l_strOrderQuantity = ((WEB3OptionsCloseMarginCompleteRequest)this.request).opOrderQuantity;
        }

        //リクエストデータ.決済順序が”ランダム”以外の場合リクエストデータ.注文数量を返却する
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            if (l_strOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_strOrderQuantity);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。 <BR>
     * <BR>
     * リクエストデータ.注文単価区分=="指値"の場合は、<BR>
     * リクエストデータ.注文単価を返却する。<BR>
     * <BR>
     * リクエストデータ.注文単価区分=="成行"の場合は、<BR>
     * 0を返却する。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        String l_strOrderPriceDiv = null;
        double l_dblPrice = 0.0D;
        String l_strLimitPrice = null;

        if (this.request instanceof WEB3OptionsCloseMarginConfirmRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsCloseMarginConfirmRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsCloseMarginConfirmRequest)this.request).limitPrice;
        }
        else if (this.request instanceof WEB3OptionsCloseMarginCompleteRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsCloseMarginCompleteRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsCloseMarginCompleteRequest)this.request).limitPrice;
        }

        //リクエストデータ.注文単価区分 == "指値"の場合リクエストデータ.注文単価を返却する。
        //リクエストデータ.注文単価区分 == "成行"の場合0を返却する。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
}
@
