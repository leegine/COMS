head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocDeliveryManagementHistoryTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.sql.Timestamp;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementHistRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

public class WEB3AdminFPTDocDeliveryManagementHistoryTest extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDeliveryManagementHistoryTest.class);
    public WEB3AdminFPTDocDeliveryManagementHistoryTest(String arg0)
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

    public void testInsertDocDeliveryManagementHist()
    {
        final String STR_METHOD_NAME = "testInsertDocDeliveryManagementHist()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Date l_docuDeliDate = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
            Timestamp l_docuDeliDate2 = new Timestamp(l_docuDeliDate.getTime());
            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            DocDeliveryManagementHistParams l_params =
                new DocDeliveryManagementHistParams();
            l_params.setAccountId(12345678L);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("123");
            l_params.setDocumentDiv("1");
            l_params.setAccountCode("321012");
            l_params.setProductCode("231");
            l_params.setDeliveryDate(l_docuDeliDate2);
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setLastUpdater("123456");
            l_params.setCreatedTimestamp(l_docuDeliDate2);
            l_params.setLastUpdatedTimestamp(l_docuDeliDate2);
            l_params.setDocumentCategory("1");

            WEB3AdminFPTDocDeliveryManagementHistory l_docDeliveryManagementHistory =
                new WEB3AdminFPTDocDeliveryManagementHistory();
            l_docDeliveryManagementHistory.insertDocDeliveryManagementHist(l_params);
            DocDeliveryManagementHistRow l_docDeliveryManagementHistRow =
                l_docDeliveryManagementHistory.getDocDeliveryManagementHist(
                    12345678L, "1", "231", l_docuDeliDate2, l_docuDeliDate2, "1");
            assertEquals(l_docDeliveryManagementHistRow.getAccountId(), 12345678L);
            assertEquals(l_docDeliveryManagementHistRow.getDeleteFlag().intValue(), 0);

            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            l_docDeliveryManagementHistRow =
                l_docDeliveryManagementHistory.getDocDeliveryManagementHist(
                    12345678L, "1", "231", l_docuDeliDate2, l_docuDeliDate2, "1");
            assertNull(l_docDeliveryManagementHistRow);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
