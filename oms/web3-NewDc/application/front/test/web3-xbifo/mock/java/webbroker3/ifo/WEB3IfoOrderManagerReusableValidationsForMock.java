head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderManagerReusableValidationsForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP発注審査個別チェックForMock(WEB3IfoOrderManagerReusableValidationsForMock.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/01 徐宏偉 (中訊) 新規作成
 */
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP発注審査個別チェックForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoOrderManagerReusableValidationsForMock extends WEB3IfoOrderManagerReusableValidations
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoOrderManagerReusableValidationsForMock.class);

    /**
     * (validate先物OP口座開設(Mock))<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strFuturesOptionDivision - 先物／オプション区分<BR>
     * 1：先物 2：オプション<BR>
     * @@throws WEB3BaseException
     */
    public void validateFuturesOptionAccountOpen(SubAccount l_subAccount, String l_strFuturesOptionDivision)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateFuturesOptionAccountOpen(SubAccount, String)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen", new Class[]
                { SubAccount.class, String.class }, new Object[]
                { l_subAccount, l_strFuturesOptionDivision });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen", new Class[]
                { SubAccount.class, String.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateFuturesOptionAccountOpen", new Class[]
                    { SubAccount.class, String.class }).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateFuturesOptionAccountOpen", new Class[]
                    { SubAccount.class, String.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateFuturesOptionAccountOpen(l_subAccount, l_strFuturesOptionDivision);

    }

    /**
     * (validate上限数量(Mock))<BR>
     * 注文数量が上限数量を超えてないかチェックを行う。<BR>
     * @@param l_dblMaxQuantity - 上限数量<BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validateMaxQuantity(double l_dblMaxQuantity, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateMaxQuantity(double, double)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMaxQuantity", new Class[]
                { double.class, double.class }, new Object[]
                { new Double(l_dblMaxQuantity), new Double(l_dblQuantity) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMaxQuantity", new Class[]
                { double.class, double.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMaxQuantity", new Class[]
                    { double.class, double.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateMaxQuantity", new Class[]
                    { double.class, double.class }).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateMaxQuantity(l_dblMaxQuantity, l_dblQuantity);

    }

    /**
     * (validate取引銘柄(Mock))<BR>
     * 取引銘柄のチェックを行い、<BR>
     * 先物OP取引銘柄オブジェクトを返却する。<BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * <BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoTradedProductImpl validateTradedProduct(WEB3IfoProductImpl l_ifoProduct, WEB3GentradeMarket l_market,
            boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(l_ifoProduct, l_market, l_blnIsBuyToOpenOrder, l_blnIsOpenContract)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateTradedProduct", new Class[]
                { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }, new Object[]
                { l_ifoProduct, l_market, new Boolean(l_blnIsBuyToOpenOrder), new Boolean(l_blnIsOpenContract) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateTradedProduct", new Class[]
                { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class })
                    .asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            return (WEB3IfoTradedProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateTradedProduct", new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateTradedProduct(l_ifoProduct, l_market, l_blnIsBuyToOpenOrder, l_blnIsOpenContract);
    }

    /**
     * (validate数量(Mock))<BR>
     * 数量のチェックを行う。<BR>
     * 　@－数量が０またはマイナス値でないこと。 <BR>
     * 　@－数量が上限単位を超えていないこと。 <BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * <BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     * @@throws WEB3BaseException
     */
    public void validateQuantity(WEB3GentradeSubAccount l_subAccount, WEB3IfoTradedProductImpl l_ifoTradedProduct,
            double l_dblQuantity, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_subAccount, l_ifoTradedProduct, l_dblQuantity, l_blnIsBuyToOpenOrder,"
                + "l_blnIsOpenContract) -->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateQuantity", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                        boolean.class }, new Object[]
                { l_subAccount, l_ifoTradedProduct, new Double(l_dblQuantity), new Boolean(l_blnIsBuyToOpenOrder),
                        new Boolean(l_blnIsOpenContract) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateQuantity", new Class[]
                { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                        boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                            boolean.class }).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoTradedProductImpl.class, double.class, boolean.class,
                            boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateQuantity(l_subAccount, l_ifoTradedProduct, l_dblQuantity, l_blnIsBuyToOpenOrder,
                l_blnIsOpenContract);
    }

    /**
     * (validate注文単価(Mock))<BR>
     * 指値のチェックを行う。<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄オブジェクト
     * @@param l_subAccount - 補助口座オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateOrderUnitPrice(double l_dblLimitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct,
            SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderUnitPrice(l_dblLimitPrice,l_ifoTradedProduct,l_subAccount)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderUnitPrice", new Class[]
                { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }, new Object[]
                { new Double(l_dblLimitPrice), l_ifoTradedProduct, l_subAccount });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderUnitPrice", new Class[]
                { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderUnitPrice", new Class[]
                    { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderUnitPrice", new Class[]
                    { double.class, WEB3IfoTradedProductImpl.class, SubAccount.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderUnitPrice(l_dblLimitPrice, l_ifoTradedProduct, l_subAccount);

    }

    /**
     * (validate注文訂正可能状態(Mock))<BR>
     * 注文の訂正が可能な注文状態であるかどうかをチェックする。 <BR>
     * <BR>
     * @@param l_order - (注文)
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
     * Skip遅延状況チェック <BR>
     * <BR>
     * true：遅延状況チェックをスキップする。（W指値切替処理からコールされた場合） <BR>
     * false：遅延状況チェックをスキップしない。<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayStatusCheck)
            throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class, boolean.class }, new Object[]
                { l_order, new Boolean(l_blnIsSkipDelayStatusCheck) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            //            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
            //                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            //                "validateOrderForChangeability",
            //                new Class[] {Order.class, boolean.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class, boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

    }

    /**
     * (validate銘柄ＩＤ(Mock))<BR>
     * 銘柄のチェックを行い、銘柄オブジェクトを返却する。<BR>
     * @@param l_lngProductID - 銘柄ＩＤ<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoProductImpl validateProductID(long l_lngProductID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductID(long)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateProductID", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngProductID) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateProductID", new Class[]
                { long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID", new Class[]
                    { long.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            return (WEB3IfoProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateProductID", new Class[]
                    { long.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateProductID(l_lngProductID);
    }

    /**
     * (validate取扱可能指数(Mock))<BR>
     * 部店で取扱可能な指数かを判定する。<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄
     * @@throws WEB3BaseException
     */
    public void validateHandlingIndex(String l_strBranchCode, WEB3IfoTradedProductImpl l_ifoTradedProduct)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingIndex(String, WEB3IfoTradedProductImpl)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex", new Class[]
                { String.class, WEB3IfoTradedProductImpl.class }, new Object[]
                { l_strBranchCode, l_ifoTradedProduct });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex", new Class[]
                { String.class, WEB3IfoTradedProductImpl.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingIndex", new Class[]
                    { String.class, WEB3IfoTradedProductImpl.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingIndex", new Class[]
                    { String.class, WEB3IfoTradedProductImpl.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateHandlingIndex(l_strBranchCode, l_ifoTradedProduct);

    }

    /**
     * (validate注文条件(Mock))<BR>
     * 注文条件のチェックを行う。<BR>
     * @@param l_datOrderBizDate - 原注文発注日<BR>
     * @@param l_datExpirationDate - 注文失効日<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * 　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値<BR>
     * @@param l_executionConditionType - 執行条件<BR>
     * @@param l_strExpirationDateType - 注文期限区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateOrderCond(WEB3GentradeSubAccount l_subAccount, long l_lngOrderUnitId,
            boolean l_blnIsMarketOrder, WEB3IfoTradedProductImpl l_ifoTradedProduct, boolean l_blnIsOpenContract,
            boolean l_blnIsBuyToOpenOrder, Date l_datOrderBizDate, Date l_datExpirationDate, String l_strOrderCond,
            IfoOrderExecutionConditionType l_executionConditionType, String l_strExpirationDateType,
            Long l_firstOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderCond(WEB3GentradeSubAccount,long,boolean,"
                + "WEB3IfoTradedProductImpl,boolean,boolean,Date,Date,"
                + "String,IfoOrderExecutionConditionType,String)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderCond", new Class[]
                { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class, Date.class, Date.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, Long.class }, new Object[]
                { l_subAccount, new Long(l_lngOrderUnitId), new Boolean(l_blnIsMarketOrder), l_ifoTradedProduct,
                        new Boolean(l_blnIsOpenContract), new Boolean(l_blnIsBuyToOpenOrder), l_datOrderBizDate,
                        l_datExpirationDate, l_strOrderCond, l_executionConditionType, l_strExpirationDateType,
                        l_firstOrderUnitId });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderCond", new Class[]
                { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class, Date.class, Date.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, Long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderCond",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class, Date.class, Date.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, Long.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderCond",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, boolean.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class, Date.class, Date.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, Long.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderCond(l_subAccount, l_lngOrderUnitId, l_blnIsMarketOrder, l_ifoTradedProduct,
                l_blnIsOpenContract, l_blnIsBuyToOpenOrder, l_datOrderBizDate, l_datExpirationDate, l_strOrderCond,
                l_executionConditionType, l_strExpirationDateType, l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateW指値注文(Mock))<BR>
     * 発注条件としてW指値が指定された注文について、 <BR>
     * 以下のチェックを行う。 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)<BR>
     * 注文単位ＩＤ<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_strOrderCondition - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件<BR>
     * @@param l_strWLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@param l_wLimitExecCondType - (（W指値）執行条件)<BR>
     * （W指値）執行条件<BR>
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * <BR>
     * ※訂正時のみ使用。新規注文登録時は、 <BR>
     * 　@null固定。<BR>
     * @@param l_ifoTradedProduct - (先物OP取引銘柄)<BR>
     * 先物OP取引銘柄オブジェクト<BR>
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * （isOpenContract） <BR>
     * 新規建取引かどうかの判定。 <BR>
     * 新規建の場合true、返済の場合false。 <BR>
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * （isBuyToOpenOrder） <BR>
     * 買建取引かどうかの判定。 <BR>
     * 買建の場合true、売建の場合false。 <BR>
     * @@throws WEB3BaseException 
     */
    public void validateWLimitPriceOrder(WEB3GentradeSubAccount l_subAccount, long l_lngOrderUnitId,
            double l_dblLimitPrice, String l_strOrderCondition, double l_dblOrderCondPrice, String l_strWLimitPrice,
            IfoOrderExecutionConditionType l_wLimitExecCondType, String l_strWlimitEnableStatusDiv,
            WEB3IfoTradedProductImpl l_ifoTradedProduct, boolean l_blnIsOpenContract, boolean l_blnIsBuyToOpenOrder)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
                + "double, String, IfoOrderExecutionConditionType, boolean, "
                + "WEB3IfoTradedProductImpl, boolean, boolean)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class }, new Object[]
                { l_subAccount, new Long(l_lngOrderUnitId), new Double(l_dblLimitPrice), l_strOrderCondition,
                        new Double(l_dblOrderCondPrice), l_strWLimitPrice, l_wLimitExecCondType,
                        l_strWlimitEnableStatusDiv, l_ifoTradedProduct, new Boolean(l_blnIsOpenContract),
                        new Boolean(l_blnIsBuyToOpenOrder) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateWLimitPriceOrder", new Class[]
                { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                        IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                        boolean.class, boolean.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateWLimitPriceOrder",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, double.class, String.class, double.class, String.class,
                            IfoOrderExecutionConditionType.class, String.class, WEB3IfoTradedProductImpl.class,
                            boolean.class, boolean.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateWLimitPriceOrder(l_subAccount, l_lngOrderUnitId, l_dblLimitPrice, l_strOrderCondition,
                l_dblOrderCondPrice, l_strWLimitPrice, l_wLimitExecCondType, l_strWlimitEnableStatusDiv,
                l_ifoTradedProduct, l_blnIsOpenContract, l_blnIsBuyToOpenOrder);

    }

    /**
     * (validate訂正内容(Mock))<BR>
     * 訂正入力値が妥当であるかをチェックする。<BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 原注文（訂正元注文）の注文単位オブジェクト<BR>
     * @@param l_dblQuantityAfterChange - 訂正数量
     * @@param l_dblLimitPrice - 訂正指値
     * @@param l_executionConditionType - 訂正執行条件
     * @@param l_strOrderConditionType - 訂正発注条件
     * @@param l_strOrderCondOperator - 訂正発注条件演算子
     * @@param l_strStopPriceType - 訂正逆指値基準値タイプ
     * @@param l_dblStopPrice - 訂正逆指値基準値
     * @@param l_dblWStopPrice - 訂正（W指値）訂正指値
     * @@param l_wLimitExecCondType - 訂正（W指値）執行条件
     * @@param l_datExpriationDate - 訂正注文失効日
     * @@param l_strExpirationDateType - 訂正注文期限区分
     * @@param l_modifiedSettleContractEntries - 訂正返済建玉エントリ
     * @@throws WEB3BaseException
     */
    public void validateOrderChangeSpec(OrderUnit l_orderUnit, double l_dblQuantityAfterChange, double l_dblLimitPrice,
            IfoOrderExecutionConditionType l_executionConditionType, String l_strOrderConditionType,
            String l_strOrderCondOperator, String l_strStopPriceType, double l_dblStopPrice, double l_dblWStopPrice,
            IfoOrderExecutionConditionType l_wLimitExecCondType, Date l_datExpriationDate,
            String l_strExpirationDateType, SettleContractEntry[] l_modifiedSettleContractEntries)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangeSpec(ForMock)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderChangeSpec", new Class[]
                { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                        Date.class, String.class, SettleContractEntry[].class }, new Object[]
                { l_orderUnit, new Double(l_dblQuantityAfterChange), new Double(l_dblLimitPrice),
                        l_executionConditionType, l_strOrderConditionType, l_strOrderCondOperator, l_strStopPriceType,
                        new Double(l_dblStopPrice), new Double(l_dblWStopPrice), l_wLimitExecCondType,
                        l_datExpriationDate, l_strExpirationDateType, l_modifiedSettleContractEntries });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderChangeSpec", new Class[]
                { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class, String.class,
                        String.class, String.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                        Date.class, String.class, SettleContractEntry[].class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                            "validateOrderChangeSpec",
                            new Class[]
                            { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                                    String.class, String.class, String.class, double.class, double.class,
                                    IfoOrderExecutionConditionType.class, Date.class, String.class,
                                    SettleContractEntry[].class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                            "validateOrderChangeSpec",
                            new Class[]
                            { OrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class,
                                    String.class, String.class, String.class, double.class, double.class,
                                    IfoOrderExecutionConditionType.class, Date.class, String.class,
                                    SettleContractEntry[].class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateOrderChangeSpec(l_orderUnit, l_dblQuantityAfterChange, l_dblLimitPrice, l_executionConditionType,
                l_strOrderConditionType, l_strOrderCondOperator, l_strStopPriceType, l_dblStopPrice, l_dblWStopPrice,
                l_wLimitExecCondType, l_datExpriationDate, l_strExpirationDateType, l_modifiedSettleContractEntries);
    }

    /**
     * (validate訂正時注文Rev上限(Mock))<BR>
     * 訂正時の注文Revが上限を超えないかどうかをチェックする。 <BR>
     * <BR>
     * @@param l_ifoOrderUnit - 注文単位
     * 訂正前の注文単位オブジェクト。<BR>
     * @@param l_dblQuantity - (訂正数量)<BR>
     * 訂正数量<BR>
     * @@param l_dblPriceChange - (訂正指値)<BR>
     * 訂正指値<BR>
     * @@param l_changeExecCondType - (訂正執行条件)<BR>
     * 訂正執行条件<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevLimit(IfoOrderUnit l_ifoOrderUnit, double l_dblQuantity, double l_dblPriceChange,
            IfoOrderExecutionConditionType l_changeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrderRevLimit("
                + "IfoOrderUnit, double, double, IfoOrderExecutionConditionType)--ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateChangeOrderRevLimit", new Class[]
                { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }, new Object[]
                { l_ifoOrderUnit, new Double(l_dblQuantity), new Double(l_dblPriceChange), l_changeExecCondType });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateChangeOrderRevLimit", new Class[]
                { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateChangeOrderRevLimit", new Class[]
                    { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class })
                    .asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateChangeOrderRevLimit", new Class[]
                    { IfoOrderUnit.class, double.class, double.class, IfoOrderExecutionConditionType.class }).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateChangeOrderRevLimit(l_ifoOrderUnit, l_dblQuantity, l_dblPriceChange, l_changeExecCondType);
    }

    /**
     * (validate市場ＩＤ)<BR>
     * 市場のチェックを実施する。<BR>
     * @@param l_lngMarketID - 市場ＩＤ<BR>
     * @@return Market
     * @@throws WEB3BaseException
     */
    public Market validateMarketID(long l_lngMarketID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarketID(l_lngMarketID)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMarketID", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngMarketID) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateMarketID", new Class[]
                { long.class }))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID", new Class[]
                    { long.class }).asWEB3BaseException();

            //3)MockFor --〉 asBoolean
            return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations", "validateMarketID", new Class[]
                    { long.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateMarketID(l_lngMarketID);
    }

    public void validateHandlingOpenContractOrder(SubAccount l_subAccount, boolean l_blnIsBuy) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingOpenContractOrder", new Class[]
                { SubAccount.class, boolean.class }, new Object[]
                { l_subAccount, new Boolean(l_blnIsBuy) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingOpenContractOrder", new Class[]
                { SubAccount.class, boolean.class }))
        {
            log
                    .debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateHandlingOpenContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", new Class[]
                    { SubAccount.class, boolean.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", new Class[]
                    { SubAccount.class, boolean.class }).asVoid();
            return;
        }
        super.validateHandlingOpenContractOrder(l_subAccount, l_blnIsBuy);
    }

    public void validateOrderForChangeability(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForChangeability", new Class[]
                { Order.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateOrderForChangeability()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validateOrderForChangeability(l_order);
    }

    public void validateOrderForCancellation(Order l_order) throws OrderValidationException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForCancellation", new Class[]
                { Order.class }, new Object[]
                { l_order });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateOrderForCancellation", new Class[]
                { Order.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoOrderManagerReusableValidationsForMock.validateOrderForCancellation()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForCancellation", new Class[]
                    { Order.class }).asVoid();
            return;
        }
        super.validateOrderForCancellation(l_order);
    }

    /**
     * （スーパークラスに自身のインスタンスを登録する。）。<BR>
     * <BR>
     * （プラグイン初期化時にコールされる）<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        log.debug("プラグイン初期化時にコールされるregister");
        super.setInstance(this);
    }
}
@
