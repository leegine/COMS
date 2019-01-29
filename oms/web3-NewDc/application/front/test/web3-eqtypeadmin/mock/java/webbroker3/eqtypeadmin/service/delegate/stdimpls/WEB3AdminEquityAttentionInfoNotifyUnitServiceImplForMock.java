head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityAttentionInfoNotifyHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/08 張少傑(中訊) 新規作成 モデルNo.219
*/


package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyUnitServiceImplForMock
    extends WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminEquityAttentionInfoNotifyUnitServiceImplForMock.class);

    public String notifyLimitRangeInfo(
        HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
        EqtypeTradedProductRow l_equityTradedProduct,
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
        ProductRow l_productRow) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, EqtypeTradedProductRow.class,
                        EqtypeTradedProductUpdqRow.class, ProductRow.class},
                new Object[] {l_hostAttentionInfoNotifyParams, l_equityTradedProduct});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo", new Class[] {HostAttentionInfoNotifyParams.class, EqtypeTradedProductRow.class,
                        EqtypeTradedProductUpdqRow.class, ProductRow.class}))
        {
            log.debug("webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.notifyLimitRangeInfo(" +
                    "HostAttentionInfoNotifyParams, WEB3EquityTradedProduct, EqtypeTradedProductUpdqRow, ProductRow)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, EqtypeTradedProductRow.class,
                        EqtypeTradedProductUpdqRow.class, ProductRow.class}).asWEB3BaseException();

            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, EqtypeTradedProductRow.class,
                        EqtypeTradedProductUpdqRow.class, ProductRow.class}).asWEB3BaseRuntimeException();

            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, EqtypeTradedProductRow.class,
                        EqtypeTradedProductUpdqRow.class, ProductRow.class}).asObject();
            
        }
        return super.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_equityTradedProduct, l_eqtypeTradedProductUpdqRow, l_productRow);
    }
}
@
