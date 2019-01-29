head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqForeignCostRegistInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g�N���X
 * 
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistInputRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistInputRequestTest.class);

    WEB3AdminFeqForeignCostRegistInputRequest l_request;

	public WEB3AdminFeqForeignCostRegistInputRequestTest(String name)
	{
		super(name);
	}

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminFeqForeignCostRegistInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * �ُ�
     * 
     * �����敪=="3"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";
            l_request.dealingType = "3";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01403, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����
     * 
     * �����敪=="1"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";
            l_request.dealingType = "1";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����
     * 
     * �����敪=="2"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";
            l_request.dealingType = "2";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����
     * 
     * �����敪=="3"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.marketCode = null;
            l_request.costDiv="01";
            l_request.dealingType = "3";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
