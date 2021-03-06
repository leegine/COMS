head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBalanceReferenceServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : ()åa¤ Ø\[VVXeæñ 
 File Name        : WEB3EquityBalanceReferenceServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/04/02 kvu (u) VKì¬  
 */
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBalanceReferenceServiceInterceptorTest extends TestBaseForMock
{
    /**
     * O[eBeB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3EquityBalanceReferenceServiceInterceptorTest.class);

    private WEB3EquityBalanceReferenceServiceInterceptor equityBalanceReferenceServiceInterceptor = null;

    public WEB3EquityBalanceReferenceServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.equityBalanceReferenceServiceInterceptor = new WEB3EquityBalanceReferenceServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // æøJ_ReLXg.sêR[h = null
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(123));
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                    new Class[]
                    {}, l_tsOrderAcceptTime);
            
            // ´\IÉ,?ü\´êÂ\
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            // ÝuÀlæ\IÉ
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            // ©è`ÉÝu
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(111);
            l_mainAccountParams.setInstitutionCode("0A");
            // üDBüÉ
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(111);
            l_institutionParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(111);
            l_branchParams.setInstitutionCode("0A");
            TestDBUtility.insertWithDel(l_branchParams);

            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "2");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext) equityBalanceReferenceServiceInterceptor.onCall(null, null);
            assertNull("l_context.getMarketCode()=null?", l_context.getMarketCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
