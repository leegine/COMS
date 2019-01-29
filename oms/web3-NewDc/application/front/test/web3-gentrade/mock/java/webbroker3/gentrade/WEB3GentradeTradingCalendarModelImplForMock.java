head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingCalendarModelImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3GentradeTradingCalendarModelImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingCalendarModelImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mock.TestBaseForMock;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3GentradeTradingCalendarModelImplForMock extends WEB3GentradeTradingCalendarModelImpl
{
    /**
     * 取引カレンダ詳細を取得する。<BR>
     *（public TradingCalendarDetails getTradingCalendarDetails(long tradedProductId)<BR>
     * のオーバーライド）<BR>
     * <BR>
     * this.取引カレンダ詳細を返却する。<BR>
     * @@param l_lngTradedProductId - 取引銘柄ID<BR>
     * @@return TradingCalendarDetails <BR>
     */
    public TradingCalendarDetails getTradingCalendarDetails(long l_lngTradedProductId)
    {
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getTradingCalendarDetails",
            new Class[] {long.class},
            new Object[]{new Long(l_lngTradedProductId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getTradingCalendarDetails",
            new Class[] {long.class}))
        {
            
            //2)MockFor --〉 asVoid
            return (TradingCalendarDetails)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class}).asObject();
        }

        return super.getTradingCalendarDetails(l_lngTradedProductId);
    }
    
    public Date getCurrentBizDate(long l_lngTradedProductId)
    {
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Object[]{new Long(l_lngTradedProductId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class}))
        {
            
            //2)MockFor --〉 asVoid
            return (Date)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class}).asObject();
        }

        return super.getCurrentBizDate(l_lngTradedProductId);
    }
}
@
