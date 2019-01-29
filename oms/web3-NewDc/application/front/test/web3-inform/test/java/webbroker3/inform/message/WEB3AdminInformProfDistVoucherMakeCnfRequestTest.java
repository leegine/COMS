head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistVoucherMakeCnfRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票作成確認リクエスト(WEB3AdminInformProfDistVoucherMakeCnfRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    :
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminInformProfDistVoucherMakeCnfRequestTest extends
        TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformProfDistVoucherMakeCnfRequestTest.class);

    /**
     *
     * @@param arg0
     */
    public WEB3AdminInformProfDistVoucherMakeCnfRequestTest(String arg0)
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
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = " testValidate_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeCnfRequest l_adminInformProfDistVoucherMakeCnfRequest =
            new WEB3AdminInformProfDistVoucherMakeCnfRequest();

        WEB3InformDetailInfoUnit l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

        l_informDetailInfoUnit.informType = "1";
        l_informDetailInfoUnit.institutionCode = "381";

        l_adminInformProfDistVoucherMakeCnfRequest.informInfoUnit = l_informDetailInfoUnit;

        try
        {
            l_adminInformProfDistVoucherMakeCnfRequest.validate();

            assertTrue(true);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
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
    }

    /**
     *
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = " testValidate_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeCnfRequest l_adminInformProfDistVoucherMakeCnfRequest =
            new WEB3AdminInformProfDistVoucherMakeCnfRequest();

        WEB3InformDetailInfoUnit l_informDetailInfoUnit = null;

        l_adminInformProfDistVoucherMakeCnfRequest.informInfoUnit = l_informDetailInfoUnit;

        try
        {
            l_adminInformProfDistVoucherMakeCnfRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01816, l_exBLE.getErrorInfo());
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
    }

}
@
