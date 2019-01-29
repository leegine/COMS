head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistVoucherMakeInpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name           : ä«óùé“ÅEå˚ç¿ì`ï[çÏê¨ì¸óÕÉäÉNÉGÉXÉg(WEB3AdminInformProfDistVoucherMakeInpRequestTest.java)
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
public class WEB3AdminInformProfDistVoucherMakeInpRequestTest extends
        TestBaseForMock
{

    /**
     * (ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉB)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformProfDistVoucherMakeInpRequestTest.class);

    /**
     *
     */
    public WEB3AdminInformProfDistVoucherMakeInpRequestTest(String arg0)
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

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "123";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

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

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "123";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = "1";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = "1";

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

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
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = " testValidate_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "123";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "3";
        l_adminInformProfDistVoucherMakeInpRequest.product = "1";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = "5";

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

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
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = " testValidate_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_exBLE.getErrorInfo());
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
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = " testValidate_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "ÇPÇQ";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_exBLE.getErrorInfo());
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
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = " testValidate_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "abc";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_exBLE.getErrorInfo());
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
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = " testValidate_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "4321";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_exBLE.getErrorInfo());
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
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = " testValidate_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835, l_exBLE.getErrorInfo());
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
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = " testValidate_0009()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "ÇRÇS";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_exBLE.getErrorInfo());
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
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = " testValidate_0010()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "abc";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_exBLE.getErrorInfo());
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
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = " testValidate_0011()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "987654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_exBLE.getErrorInfo());
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
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = " testValidate_0012()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "ÇTÇU";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02794, l_exBLE.getErrorInfo());
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
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = " testValidate_0013()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "+-";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02794, l_exBLE.getErrorInfo());
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
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = " testValidate_0014()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "99";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = null;
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02795, l_exBLE.getErrorInfo());
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
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = " testValidate_0015()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "ÇVÇW";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_exBLE.getErrorInfo());
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
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = " testValidate_0016()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "ab";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_exBLE.getErrorInfo());
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
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = " testValidate_0017()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = null;
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02835, l_exBLE.getErrorInfo());
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
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = " testValidate_0018()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "2";
        l_adminInformProfDistVoucherMakeInpRequest.product = null;
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00841, l_exBLE.getErrorInfo());
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
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = " testValidate_0019()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = "ÇPÇP";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02792, l_exBLE.getErrorInfo());
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
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = " testValidate_0020()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = "<>";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02792, l_exBLE.getErrorInfo());
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
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = " testValidate_0021()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = "100";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = null;

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02793, l_exBLE.getErrorInfo());
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
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = " testValidate_0022()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistVoucherMakeInpRequest l_adminInformProfDistVoucherMakeInpRequest =
            new WEB3AdminInformProfDistVoucherMakeInpRequest();

        l_adminInformProfDistVoucherMakeInpRequest.branchCode = "381";
        l_adminInformProfDistVoucherMakeInpRequest.accountCode = "654321";
        l_adminInformProfDistVoucherMakeInpRequest.specifyDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.productCode = "54321";
        l_adminInformProfDistVoucherMakeInpRequest.registDiv = "1";
        l_adminInformProfDistVoucherMakeInpRequest.product = "9";
        l_adminInformProfDistVoucherMakeInpRequest.transferDiv = "0";

        try
        {
            l_adminInformProfDistVoucherMakeInpRequest.validate();

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BusinessLayerException l_exBLE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBLE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01772, l_exBLE.getErrorInfo());
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
