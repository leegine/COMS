head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄ForMock(WEB3MutualFundProductForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信銘柄ForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductForMock extends WEB3MutualFundProduct
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductForMock.class);

    /**
     * (拡張投信銘柄(Mock))<BR>
     * コンストラクタ。<BR>
     * <BR>
     * super(銘柄Row)をコールする。<BR>
     * @@param l_productRow - 銘柄Row
     */
    public WEB3MutualFundProductForMock(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (拡張投信銘柄(Mock))<BR>
     * コンストラクタ。<BR>
     * <BR>
     * super(投信銘柄Row)をコールする。<BR>
     * @@param l_mutualFundProductRow - 投信銘柄Row
     */
    public WEB3MutualFundProductForMock(MutualFundProductRow l_mutualFundProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundProductRow);
    }

    /**
     *(get外貨MMF為替レート(Mock))<BR>
     * @@return 外貨MMF為替レートParams <BR>
     * @@throws WEB3BaseException 
     */
    public FrgnMmfExchangeRateParams getFrgnMmfExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFrgnMmfExchangeRate()-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {}))
        {
            log.exiting(STR_METHOD_NAME);
            //1）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "getFrgnMmfExchangeRate",
                new Class[] {}).asWEB3BaseException();

            //2)MockFor --〉 asObject
            return(FrgnMmfExchangeRateParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "getFrgnMmfExchangeRate",
                new Class[] {}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrgnMmfExchangeRate();
    }

    /**
     * (is買付制限有り(Mock))<BR>
     * 当銘柄に買付制限が課せられているかどうかを判定する。<BR>
     * <BR>
     * this.getDataSourceObject().get買付制限区分( )の戻り値が<BR>
     * "0：買付可能"の場合、falseを返却する。<BR>
     * this.getDataSourceObject().get買付制限区分( )の戻り値が<BR>
     * "1：乗換買付のみ可能"の場合、trueを返却する。<BR>
     * @@return boolean
     */
    public boolean isAcquiredDeregExistence()
    {
        final String STR_METHOD_NAME = "isAcquiredDeregExistence()-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {}))
        {
            log.exiting(STR_METHOD_NAME);
            //1）MockFor --〉 asWEB3BaseException

            //2)MockFor --〉 asObject
            return(boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "isAcquiredDeregExistence",
                new Class[] {}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isAcquiredDeregExistence();

    }
    public String getStandardName()
    {
        throw new UnsupportedOperationException("getStandardNameは利用できない！チームリーダへ連絡してください！");
    }
}
@
