head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecEndNotifyRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3IfoOrderCarryOverMainRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/22 �И��� (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecEndNotifyRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3IfoExecEndNotifyRequestTest.class);
    

    public WEB3IfoExecEndNotifyRequestTest(String name)
    {
        super(name);
    }

    /**
     * (�،���ЃR�[�h�`�F�b�N)<BR>
     * this.�،���ЃR�[�h == null �̏ꍇ�A�u�،���ЃR�[�h��null�v�̗�O��throw����B<BR>
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //�،���ЃR�[�h
        l_requset.institutionCode = null;
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (�敨�^�I�v�V�����敪�`�F�b�N)<BR>
     * this.�敨�^�I�v�V�����敪 != �i"1�F�敨" or "2�F�I�v�V����"�j �̏ꍇ�A<BR>
     * �u�敨�^�I�v�V�����敪���s���ł��v�̗�O��throw����B  <BR>
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //�،���ЃR�[�h
        l_requset.institutionCode = "11";
        //�敨�^�I�v�V�����敪
        l_requset.fuOpDiv = "3";
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02953,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (�o���I���敪�`�F�b�N)<BR>
     * this.�o���I���敪 != �i"1�F�[��O�o���I��" or "0�F�o���I���i�ŏI�j"�j �̏ꍇ�A<BR>
     * �u�o���I���敪���s���ł��v�̗�O��throw����B <BR>
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //�،���ЃR�[�h
        l_requset.institutionCode = "11";
        //�敨�^�I�v�V�����敪
        l_requset.fuOpDiv = "1";
        //�o���I���敪
        l_requset.execEndDiv = "2";
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02954,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (�o���I���敪�`�F�b�N)<BR>
     * this.�o���I���敪 = "1�F�[��O�o���I��" <BR>
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //�،���ЃR�[�h
        l_requset.institutionCode = "11";
        //�敨�^�I�v�V�����敪
        l_requset.fuOpDiv = "2";
        //�o���I���敪
        l_requset.execEndDiv = "1";
        try
        {
            l_requset.validate();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (�o���I���敪�`�F�b�N)<BR>
     * this.�o���I���敪 = 0�F�o���I���i�ŏI�j<BR>
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //�،���ЃR�[�h
        l_requset.institutionCode = "11";
        //�敨�^�I�v�V�����敪
        l_requset.fuOpDiv = "2";
        //�o���I���敪
        l_requset.execEndDiv = "0";
        try
        {
            l_requset.validate();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
