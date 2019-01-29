head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformDetailInfoUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡情報クラス(WEB3InformDetailInfoUnitTest.java)
Author Name         : Daiwa Institute of Research
Revision History    :
*/

package webbroker3.inform.message;

import test.util.TestDBUtility;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3InformDetailInfoUnitTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3InformDetailInfoUnitTest.class);

    /**
     *
     * @@param arg0
     */
    public WEB3InformDetailInfoUnitTest(String arg0)
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
    public void testWEB3InformDetailInfoUnit_0001()
    {
        final String STR_METHOD_NAME = " testWEB3InformDetailInfoUnit_0001()";
        log.entering(STR_METHOD_NAME);

        VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();

        l_variousInformParams.setAccountCode("2512246");

        try
        {
            WEB3InformDetailInfoUnit l_informDetailInfoUnit =
                new WEB3InformDetailInfoUnit(l_variousInformParams);

            assertEquals("251224", l_informDetailInfoUnit.accountNumber);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public void testWEB3InformDetailInfoUnit_0002()
    {
        final String STR_METHOD_NAME = " testWEB3InformDetailInfoUnit_0002()";
        log.entering(STR_METHOD_NAME);

        VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();

        l_variousInformParams.setAccountCode(null);

        try
        {
            WEB3InformDetailInfoUnit l_informDetailInfoUnit =
                new WEB3InformDetailInfoUnit(l_variousInformParams);

            assertNull(l_informDetailInfoUnit.accountNumber);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

}
@
