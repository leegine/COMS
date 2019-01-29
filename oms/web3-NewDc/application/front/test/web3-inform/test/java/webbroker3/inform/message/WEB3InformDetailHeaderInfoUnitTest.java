head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformDetailHeaderInfoUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡詳細情報クラス(WEB3InformDetailHeaderInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revision History    :
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3InformDetailHeaderInfoUnitTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3InformDetailHeaderInfoUnitTest.class);

    /**
     * @@param arg0
     */
    public WEB3InformDetailHeaderInfoUnitTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testWEB3InformDetailHeaderInfoUnit_0001()
    {
        final String STR_METHOD_NAME = " testWEB3InformDetailHeaderInfoUnit_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            TestDBUtility.deleteAllAndCommit(VariousInformParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();

        l_variousInformParams.setBranchCode("381");
        l_variousInformParams.setInstitutionCode("0D");
        l_variousInformParams.setInformDiv("12");
        l_variousInformParams.setRequestNumber("123");
        l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070618","yyyyMMdd"));
        l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070618","yyyyMMdd"));
        l_variousInformParams.setAccountCode("2512246");
        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(l_variousInformParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3InformDetailHeaderInfoUnit l_informDetailHeaderInfoUnit =
                new WEB3InformDetailHeaderInfoUnit(l_variousInformParams);

            java.util.Date l_expectedDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");

            assertEquals("123", l_informDetailHeaderInfoUnit.requestNumber);
            assertEquals(l_expectedDate, l_informDetailHeaderInfoUnit.receptionDate);
            assertEquals("251224", l_informDetailHeaderInfoUnit.accountNumber);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
                TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
                TestDBUtility.deleteAllAndCommit(VariousInformParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }


    /**
     *
     */
    public void testWEB3InformDetailHeaderInfoUnit_0002()
    {
        final String STR_METHOD_NAME = " testWEB3InformDetailHeaderInfoUnit_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            TestDBUtility.deleteAllAndCommit(VariousInformParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();

        l_variousInformParams.setBranchCode("381");
        l_variousInformParams.setInstitutionCode("0D");
        l_variousInformParams.setInformDiv("12");
        l_variousInformParams.setRequestNumber("123");
        l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070618","yyyyMMdd"));
        l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070618","yyyyMMdd"));
        l_variousInformParams.setAccountCode(null);
        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(l_variousInformParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3InformDetailHeaderInfoUnit l_informDetailHeaderInfoUnit =
                new WEB3InformDetailHeaderInfoUnit(l_variousInformParams);

            java.util.Date l_expectedDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");

            assertEquals("123", l_informDetailHeaderInfoUnit.requestNumber);
            assertEquals(l_expectedDate, l_informDetailHeaderInfoUnit.receptionDate);
            assertNull("251224", l_informDetailHeaderInfoUnit.accountNumber);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
                TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
                TestDBUtility.deleteAllAndCommit(VariousInformParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

}
@
