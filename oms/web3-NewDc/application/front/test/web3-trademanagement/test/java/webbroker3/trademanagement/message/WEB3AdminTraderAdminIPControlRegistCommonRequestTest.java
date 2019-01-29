head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�o�^���ʃ��N�G�X�g(WEB3AdminTraderAdminIPControlRegistCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlRegistCommonRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlRegistCommonRequestTest.class);

    WEB3AdminTraderAdminIPControlRegistCommonRequest l_request = new WEB3AdminTraderAdminIPControlRegistCommonRequest();
    
    public WEB3AdminTraderAdminIPControlRegistCommonRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.IP�A�h���X == null�̏ꍇ�A��O���X���[����B 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03122, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X���ȉ��̏ꍇ�A��O���X���[����B
     * WEB3StringTypeUtility.isIpAddress()==false 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "aa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03121, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X == null�̏ꍇ�A��O���X���[����B 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00889, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X�����p�����ȊO�̏ꍇ�A��O���X���[����B  
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "aa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03123, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X="1"
     * this.�K�p�J�n���� == null�̏ꍇ�A��O���X���[����B
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "1";
        //�K�p�J�n����
        l_request.startDate = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03124, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X="1"
     * this.�K�p�J�n����="20080808080800"
     * this.�K�p�I������ == null�̏ꍇ�A��O���X���[����B 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "1";
        //�K�p�J�n����
        l_request.startDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        //�K�p�I������
        l_request.endDate = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03125, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X="1"
     * this.�K�p�J�n����="20080808080800"
     * this.�K�p�I������ ��= ���ݓ����̏ꍇ�A��O���X���[����B 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "1";
        //�K�p�J�n����
        l_request.startDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        //�K�p�I������
        l_request.endDate = WEB3DateUtility.getDate("20080808080800", "yyyyMMddHHmmss");
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X="1"
     * this.�K�p�J�n����="20080808080800"
     * this.�K�p�J�n���� > this.�K�p�I�������̏ꍇ�A��O���X���[����B
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "1";
        //�K�p�J�n����
        l_request.startDate = WEB3DateUtility.getDate("21090908080800", "yyyyMMddHHmmss");
        //�K�p�I������
        l_request.endDate = WEB3DateUtility.getDate("21090808080800", "yyyyMMddHHmmss");
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03127, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.IP�A�h���X="1111"
     * this.�X�e�[�^�X="1"
     * this.�K�p�J�n����="20080808080800"
     * this.�K�p�J�n���� > this.�K�p�I�������̏ꍇ�A��O���X���[����B
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);

        //IP�A�h���X
        l_request.ipAddress = "1111";
        //�X�e�[�^�X
        l_request.status = "1";
        //�K�p�J�n����
        l_request.startDate = WEB3DateUtility.getDate("20080908080800", "yyyyMMddHHmmss");
        //�K�p�I������
        l_request.endDate = WEB3DateUtility.getDate("21090808080800", "yyyyMMddHHmmss");
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
