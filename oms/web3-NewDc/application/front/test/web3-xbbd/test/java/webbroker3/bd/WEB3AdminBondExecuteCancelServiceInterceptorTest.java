head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondExecuteCancelServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondExecuteCancelServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelServiceInterceptorTest.class);

    public WEB3AdminBondExecuteCancelServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_onCall_0001()
    {
        final String STR_METHOD_NAME =
            " test_onCall_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(OrderUnitIntroduceDivParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BondOrderParams l_bondOrderParams = new BondOrderParams();
            l_bondOrderParams.setSubAccountId(1001L);
            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
            l_bondOrderParams.setOrderId(123L);
            l_bondOrderParams.setAccountId(1001L);
            l_bondOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_bondOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_bondOrderParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setAccountId(1001L);
            l_bondOrderUnitParams.setSubAccountId(1001L);
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_bondOrderUnitParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3AdminBondExecCancelConfirmRequest l_request = new WEB3AdminBondExecCancelConfirmRequest();
            l_request.id = "123";
            Method l_method = null;
            Object[] l_serviceParam = {l_request};
            WEB3AdminBondExecuteCancelServiceInterceptor l_interceptor =
                new WEB3AdminBondExecuteCancelServiceInterceptor();
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("381");
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tmpClendarContext.setMarketCode("0");

            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tmpClendarContext.setProductCode("0");

            //　@取引カレンダコンテキスト.受付時間区分 =

            //債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
            l_tmpClendarContext.setTradingTimeType("25");
//            l_tmpClendarContext.setTradingTimeType("36");
            l_tmpClendarContext.setOrderAcceptProduct("28");

            //　@取引カレンダコンテキスト.注文受付トランザクション = "00:DEFAULT"
            l_tmpClendarContext.setOrderAcceptTransaction("00");
            l_tmpClendarContext.setBizDateType("1");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            l_interceptor.onCall(l_method, l_serviceParam);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public void test_onCall_0002()
    {
        final String STR_METHOD_NAME =
            " test_onCall_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderId(123L);
            l_bondOrderUnitParams.setAccountId(1001L);
            l_bondOrderUnitParams.setSubAccountId(1001L);
            l_bondOrderUnitParams.setOrderExecStatus("1");
            l_bondOrderUnitParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
            l_bondOrderUnitParams.setBondType(BondTypeEnum.CB);
            l_bondOrderUnitParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3AdminBondExecCancelConfirmRequest l_request = new WEB3AdminBondExecCancelConfirmRequest();
            l_request.id = "123";
            Method l_method = null;
            Object[] l_serviceParam = {l_request};
            WEB3AdminBondExecuteCancelServiceInterceptor l_interceptor =
                new WEB3AdminBondExecuteCancelServiceInterceptor();
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("381");
            //  取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
            l_tmpClendarContext.setMarketCode("0");

            //  取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
            l_tmpClendarContext.setProductCode("0");

            //　@取引カレンダコンテキスト.受付時間区分 =

            //債券注文単位.債券タイプ == "外国債券"の場合、"25：債券"をセットする。
//            l_tmpClendarContext.setTradingTimeType("25");
            l_tmpClendarContext.setTradingTimeType("36");
            l_tmpClendarContext.setOrderAcceptProduct("28");

            //　@取引カレンダコンテキスト.注文受付トランザクション = "00:DEFAULT"
            l_tmpClendarContext.setOrderAcceptTransaction("00");
            l_tmpClendarContext.setBizDateType("1");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            l_interceptor.onCall(l_method, l_serviceParam);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
