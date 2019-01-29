head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistVoucherInfoRefRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・伝票情報参照リクエスト(WEB3AdminInformProfDistVoucherInfoRefRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    :
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 */
public class WEB3AdminInformProfDistVoucherInfoRefRequestTest extends
        TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformProfDistVoucherInfoRefRequestTest.class);

    /**
     *
     * @@param arg0
     */
    public WEB3AdminInformProfDistVoucherInfoRefRequestTest(String arg0)
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

        WEB3AdminInformProfDistVoucherInfoRefRequest l_adminInformProfDistVoucherInfoRefRequest =
            new WEB3AdminInformProfDistVoucherInfoRefRequest();

        l_adminInformProfDistVoucherInfoRefRequest.branchCode = "381";
        l_adminInformProfDistVoucherInfoRefRequest.informType = "1";
        l_adminInformProfDistVoucherInfoRefRequest.requestNumber = "2";

        try
        {
            l_adminInformProfDistVoucherInfoRefRequest.validate();

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

}
@
