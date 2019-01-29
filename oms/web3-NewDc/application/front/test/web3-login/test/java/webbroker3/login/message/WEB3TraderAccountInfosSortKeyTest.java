head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TraderAccountInfosSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[�̃e�X�g�N���X(WEB3TraderAccountInfosSortKeyTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬 ���f��No.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[�̃e�X�g�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3TraderAccountInfosSortKeyTest extends TestBaseForMock
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TraderAccountInfosSortKeyTest.class);

    /**
     * CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[
     */
    private WEB3TraderAccountInfosSortKey l_sortKey = null;

    /**
     * @@param arg0
     */
    public WEB3TraderAccountInfosSortKeyTest(String arg0)
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
    public void testValidate_case0001()
    {

        String STR_METHOD_NAME = " testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = null;

        try
        {
            l_sortKey.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_exBE.getErrorInfo());

            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
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
    public void testValidate_case0002()
    {

        String STR_METHOD_NAME = " testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = "productCode";
        l_sortKey.ascDesc = null;

        try
        {
            l_sortKey.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_exBE.getErrorInfo());

            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
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
    public void testValidate_case0003()
    {

        String STR_METHOD_NAME = " testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = "productCode";
        l_sortKey.ascDesc = "12";

        try
        {
            l_sortKey.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_exBE.getErrorInfo());

            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
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
    public void testValidate_case0004()
    {

        String STR_METHOD_NAME = " testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = "productCode";
        l_sortKey.ascDesc = "A";

        try
        {
            l_sortKey.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
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
