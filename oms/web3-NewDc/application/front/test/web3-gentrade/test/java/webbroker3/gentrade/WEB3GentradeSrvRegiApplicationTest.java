head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeSrvRegiApplicationTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス申込登録クラステスト(WEB3GentradeSrvRegiApplicationTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/19  栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス申込登録クラステスト)<BR>
 * 
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeSrvRegiApplicationTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeHandlingOrderCondTest.class);

    public WEB3GentradeSrvRegiApplicationTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);
    }

    /*
     * testSetFreeSrvDiv_Case0001
     */
    public void testSetFreeSrvDiv_Case0001()
    {
        final String STR_METHOD_NAME = " testSetFreeSrvDiv_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("381");
            l_srvRegiApplicationParams.setSrvDiv("1");
            l_srvRegiApplicationParams.setAccountCode("10001");
            l_srvRegiApplicationParams.setRegistId(10086);
            l_srvRegiApplicationParams.setFreeSrvDiv("1");
            l_srvRegiApplicationParams.setAppliStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_srvRegiApplicationParams.setAppliDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            l_srvRegiApplicationParams.setPaymentDiv("0");
            l_srvRegiApplicationParams.setAppliLotDiv("0");
            l_srvRegiApplicationParams.setEffectiveDiv("0");
            l_srvRegiApplicationParams.setCancelDiv("0");
            l_srvRegiApplicationParams.setLastUpdater("aaa");
            l_srvRegiApplicationParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_srvRegiApplicationParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            WEB3GentradeSrvRegiApplication l_srvRegiApplication = new WEB3GentradeSrvRegiApplication(
                l_srvRegiApplicationParams);
            l_srvRegiApplication.setFreeSrvDiv("2");
            String  l_strFreeSrvDiv = l_srvRegiApplication.getFreeSrvDiv();
            assertEquals("2", l_strFreeSrvDiv);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
