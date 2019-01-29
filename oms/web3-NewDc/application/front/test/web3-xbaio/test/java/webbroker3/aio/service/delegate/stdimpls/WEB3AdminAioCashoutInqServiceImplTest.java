head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioCashoutInqServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AdminAioCashoutInqServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 èôçGàÃ (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AioCashoutInqUnit;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author èôçGàÃ(íÜêu)
 * @@version 1.0
 */
public class WEB3AdminAioCashoutInqServiceImplTest extends TestBaseForMock
{
    /**
     * ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉBÅB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqServiceImplTest.class);

    WEB3AdminAioCashoutInqServiceImpl l_impl = new WEB3AdminAioCashoutInqServiceImpl();

    public WEB3AdminAioCashoutInqServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * à¯êî:èoã‡ñ‚çáÇπñæç◊ÇÃóvëfï™à◊0
     */
    public void testSetPaymentPower_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderUnit_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "624";
        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnit = new WEB3AioCashoutInqUnit[0];

        try
        {
            this.l_impl.setPaymentPower(l_strInstitutionCode, l_aioCashoutInqUnit);    
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception..", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetPaymentPower_case002()
    {
        final String STR_METHOD_NAME = ".testSetPaymentPower_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "0D";
        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnit = new WEB3AioCashoutInqUnit[1];
        l_aioCashoutInqUnit[0] = new WEB3AioCashoutInqUnit();
        l_aioCashoutInqUnit[0].branchCode = "624";
        l_aioCashoutInqUnit[0].accountCode = "321";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPowerAioCashoutInput",
                    new Class[]
                        { WEB3GentradeSubAccount.class, Date.class},new Double(1.1));
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Double(100));
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            this.l_impl.setPaymentPower(l_strInstitutionCode, l_aioCashoutInqUnit);   
            assertEquals("1.1", l_aioCashoutInqUnit[0].paymentPower);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception..", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetPaymentPower_case003()
    {
        final String STR_METHOD_NAME = ".testSetPaymentPower_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "0D";
        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnit = new WEB3AioCashoutInqUnit[3];
        l_aioCashoutInqUnit[0] = new WEB3AioCashoutInqUnit();
        l_aioCashoutInqUnit[0].branchCode = "624";
        l_aioCashoutInqUnit[0].accountCode = "321";
        l_aioCashoutInqUnit[1] = new WEB3AioCashoutInqUnit();
        l_aioCashoutInqUnit[1].branchCode = "624";
        l_aioCashoutInqUnit[1].accountCode = "321";
        l_aioCashoutInqUnit[2] = new WEB3AioCashoutInqUnit();
        l_aioCashoutInqUnit[2].branchCode = "624";
        l_aioCashoutInqUnit[2].accountCode = "321";

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPowerAioCashoutInput",
                    new Class[]
                        { WEB3GentradeSubAccount.class, Date.class},new Double(1.1));
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Double(100));
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            this.l_impl.setPaymentPower(l_strInstitutionCode, l_aioCashoutInqUnit);
            assertEquals("1.1", l_aioCashoutInqUnit[0].paymentPower);
            assertEquals("1.1", l_aioCashoutInqUnit[1].paymentPower);
            assertEquals("1.1", l_aioCashoutInqUnit[2].paymentPower);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception..", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
