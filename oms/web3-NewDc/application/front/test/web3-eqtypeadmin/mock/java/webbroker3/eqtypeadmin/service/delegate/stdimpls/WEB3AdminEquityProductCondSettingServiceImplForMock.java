head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityProductCondSettingServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityProductCondSettingServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/18 安陽(中訊) 新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfigUnit;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityProductCondSettingServiceImplForMock extends WEB3AdminEquityProductCondSettingServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminEquityProductCondSettingServiceImplForMock.class);
    
    
    public WEB3AdminPMProductCondConfigUnit createProductCondConfigUnit(
        String l_strMarketCode,
        String l_largeItemDiv,
        String l_strSmallItemDivList,
        ProductParams l_productParams,
        WEB3EquityProduct l_equityProduct,
        EqtypeTradedProductParams l_tradedProductsToday,
        EqtypeTradedProductParams l_tradedProductsTwoDaysLater,
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataFindException, DataQueryException,
        DataNetworkException, WEB3BaseException, NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl",
            "createProductCondConfigUnit",
            new Class[]{
                String.class,
                String.class,
                String.class,
                ProductParams.class,
                WEB3EquityProduct.class,
                EqtypeTradedProductParams.class,
                EqtypeTradedProductParams.class,
                EqtypeProductConditionParams.class},
            new Object[]{
                l_strMarketCode,
                l_largeItemDiv,
                l_strSmallItemDivList,
                l_productParams,
                l_equityProduct,
                l_tradedProductsToday,
                l_tradedProductsTwoDaysLater,
                l_eqtypeProductConditionParams});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl",
            "createProductCondConfigUnit",
            new Class[]{
                String.class,
                String.class,
                String.class,
                ProductParams.class,
                WEB3EquityProduct.class,
                EqtypeTradedProductParams.class,
                EqtypeTradedProductParams.class,
                EqtypeProductConditionParams.class}))
        {
            log.debug("webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImplForMock.createProductCondConfigUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl",
                "createProductCondConfigUnit",
                new Class[]{
                    String.class,
                    String.class,
                    String.class,
                    ProductParams.class,
                    WEB3EquityProduct.class,
                    EqtypeTradedProductParams.class,
                    EqtypeTradedProductParams.class,
                    EqtypeProductConditionParams.class}).asWEB3BaseException();
            return (WEB3AdminPMProductCondConfigUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl",
                "createProductCondConfigUnit",
                new Class[]{
                    String.class,
                    String.class,
                    String.class,
                    ProductParams.class,
                    WEB3EquityProduct.class,
                    EqtypeTradedProductParams.class,
                    EqtypeTradedProductParams.class,
                    EqtypeProductConditionParams.class}).asObject();
        }
        return super.createProductCondConfigUnit(
            l_strMarketCode,
            l_largeItemDiv,
            l_strSmallItemDivList,
            l_productParams,
            l_equityProduct,
            l_tradedProductsToday,
            l_tradedProductsTwoDaysLater,
            l_eqtypeProductConditionParams);
    }    

}
@
