head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʒ�����t�����Ɖ�N�G�X�g(WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/01 �ؕk (���u) �V�K�쐬 �d�l�ύX�E���f��215
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import webbroker3.mock.TestBaseForMock;

/**
 * (�N���X �Ǘ��Ғ�����t�����Ɖ�N�G�X�g) <BR>
 * �N���X �Ǘ��Ғ�����t�����Ɖ�N�G�X�g <BR>
 * <BR>
 * @@author �ؕk <BR>
 * @@version 1.0 <BR>
 */
public class WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest.class);

    WEB3AdminBondDomesticRecruitLimitManageCommonRequest l_request =
        new WEB3AdminBondDomesticRecruitLimitManageCommonRequest();

    /**
     *�@@WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest(String)<BR>
     * @@param arg0<BR>
     */

    public WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest(String arg0)
    {
        super(arg0);
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
     * this.����ID==null�̏ꍇ�A��O���X���[����B<BR>
     *
     */
    public void testValidate1()
    {
       final String STR_METHOD_NAME = " testValidate1()";
       log.entering(TEST_START + STR_METHOD_NAME);

       l_request.productId = null;
       try
       {
           l_request.validate();
           fail();
       }

       catch (WEB3BaseException l_ex)
       {
           log.error(TEST_END + STR_METHOD_NAME, l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(TEST_END + STR_METHOD_NAME, l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testValidate2()
    {
       final String STR_METHOD_NAME = " testValidate2()";
       log.entering(TEST_START + STR_METHOD_NAME);

       l_request.productId = "1";
       try
       {
           l_request.validate();

       }

       catch (WEB3BaseException l_ex)
       {
           log.error(TEST_END + STR_METHOD_NAME, l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       catch (Exception l_ex)
       {
           log.error(TEST_END + STR_METHOD_NAME, l_ex);
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
    }    
}
@
