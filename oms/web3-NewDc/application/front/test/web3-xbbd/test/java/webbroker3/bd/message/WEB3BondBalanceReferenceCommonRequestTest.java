head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBalanceReferenceCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�ʃ��N�G�X�g�e�X�g(WEB3BondBalanceReferenceCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 ���g (���u) �V�K�쐬
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�ʃ��N�G�X�g�e�X�g)<BR>
 * ���c���Ɖ�ʃ��N�G�X�g�e�X�g�N���X
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceCommonRequestTest extends TestBaseForMock
{

    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceCommonRequestTest.class);

    /**
     *�@@WEB3BondBalanceReferenceCommonRequestTest(String)<BR>
     * @@param l_str - (String)<BR>
     */
    public WEB3BondBalanceReferenceCommonRequestTest(String l_str)
    {
        super(l_str);
    }

    /**
     *�@@setUp()<BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *�@@tearDown()<BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * this.�Ɖ�敪��null�̏ꍇ�A��`�l�̃`�F�b�N���s���B
     * this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ�A
     */
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BondBalanceReferenceCommonRequest l_bondBalanceReferenceCommonRequest =
                new WEB3BondBalanceReferenceCommonRequest();
            l_bondBalanceReferenceCommonRequest.referenceType = "6";
            l_bondBalanceReferenceCommonRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082.error_code, l_ex.getErrorInfo().error_code);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * this.�Ɖ�敪��null�̏ꍇ�A��`�l�̃`�F�b�N���s���B
     * this.�Ɖ�敪���ȉ��̒l�̏ꍇ�A
     */
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BondBalanceReferenceCommonRequest l_bondBalanceReferenceCommonRequest =
                new WEB3BondBalanceReferenceCommonRequest();
            l_bondBalanceReferenceCommonRequest.referenceType = "5";
            l_bondBalanceReferenceCommonRequest.validate();
            assertNull(l_bondBalanceReferenceCommonRequest.createResponse());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * this.�Ɖ�敪=null�̏ꍇ�A��`�l�̃`�F�b�N���s���B
     * this.�Ɖ�敪���ȉ��̒l�̏ꍇ�A
     */
    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3BondBalanceReferenceCommonRequest l_bondBalanceReferenceCommonRequest =
                new WEB3BondBalanceReferenceCommonRequest();
            l_bondBalanceReferenceCommonRequest.referenceType = null;
            l_bondBalanceReferenceCommonRequest.validate();
            assertEquals("1", l_bondBalanceReferenceCommonRequest.referenceType);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
