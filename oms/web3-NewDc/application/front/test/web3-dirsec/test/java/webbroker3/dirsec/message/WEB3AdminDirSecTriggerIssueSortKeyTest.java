head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecTriggerIssueSortKeyTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueSortKeyTest.class);

    WEB3AdminDirSecTriggerIssueSortKey l_request;

    public WEB3AdminDirSecTriggerIssueSortKeyTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecTriggerIssueSortKey();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * this.�L�[���ځ�null�̏ꍇ�A�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
     */
    public void testValidate_Case293()
    {
        final String STR_METHOD_NAME = "testValidate_Case293";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.keyItem = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�����^�~����null�̏ꍇ�A�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
     */
    public void testValidate_Case294()
    {
        final String STR_METHOD_NAME = "testValidate_Case294";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.keyItem = "eclipse";
            l_request.ascDesc = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
     * �hA�F�����h
     * �hD�F�~���h
     */
    public void testValidate_Case295()
    {
        final String STR_METHOD_NAME = "testValidate_Case295";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.keyItem = "eclipse";
            l_request.ascDesc = "B";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
     * �hA�F�����h
     * �hD�F�~���h
     */
    public void testValidate_Case296()
    {
        final String STR_METHOD_NAME = "testValidate_Case296";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.keyItem = "eclipse";
            l_request.ascDesc = "A";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
     * �hA�F�����h
     * �hD�F�~���h
     */
    public void testValidate_Case297()
    {
        final String STR_METHOD_NAME = "testValidate_Case297";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.keyItem = "eclipse";
            l_request.ascDesc = "D";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
