head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^���ʃ��N�G�X�gTest(WEB3AdminSLProductRegistCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/27 ��іQ(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3AdminSLProductRegistCommonRequestTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductRegistCommonRequestTest.class);
    
    WEB3AdminSLProductRegistCommonRequest l_request = new WEB3AdminSLProductRegistCommonRequest();
    
    public WEB3AdminSLProductRegistCommonRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ���N�G�X�g.�����o�^���.�����R�[�h��null�̏ꍇ
     * ��O���X���[ 
     */
    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME = "testExcute_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = null;
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00079);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�����R�[�h�����p�����ȊO�̏ꍇ
     * ��O���X���[ 
     */
    public void testValidateCase0002()
    {
        final String STR_METHOD_NAME = "testExcute_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "abc";
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00815);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�����R�[�h�����p�����ȊO�̏ꍇ
     * ��O���X���[ 
     */
    public void testValidateCase0003()
    {
        final String STR_METHOD_NAME = "testExcute_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "�P�Q�R";
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00815);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�����^�C�v��null�̏ꍇ�A
     * ��O���X���[
     */
    public void testValidateCase0004()
    {
        final String STR_METHOD_NAME = "testExcute_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = null;
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01109);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�K�i�敪��null�̏ꍇ�A
     * ��O���X���[
     */
    public void testValidateCase0005()
    {
        final String STR_METHOD_NAME = "testExcute_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = null;
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02930);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�|�� != null ���� ���p�����ȊO 
     * ��O���X���[
     */
    public void testValidateCase0006()
    {
        final String STR_METHOD_NAME = "testExcute_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "abc";
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02924);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�K�p����from��null�̏ꍇ�A
     * ��O���X���[
     */
    public void testValidateCase0007()
    {
        final String STR_METHOD_NAME = "testExcute_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "456";
            l_unit.targetPeriodFrom = null;
            
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01444);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�K�p����from > ���N�G�X�g.�����o�^���.�K�p����to 
     * ��O���X���[
     */
    public void testValidateCase0008()
    {
        final String STR_METHOD_NAME = "testExcute_C0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "456";
            l_unit.targetPeriodFrom = WEB3DateUtility.getDate("20060203","yyyyMMdd");
            l_unit.targetPeriodTo = WEB3DateUtility.getDate("20060202","yyyyMMdd");
            
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01446);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.���R != null ����200Byte�ȏ�̏ꍇ�A��O���X���[
     * ��O���X���[
     */
    public void testValidateCase0009()
    {
        final String STR_METHOD_NAME = "testExcute_C0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "456";
            l_unit.targetPeriodFrom = WEB3DateUtility.getDate("20060203","yyyyMMdd");
            l_unit.targetPeriodTo = WEB3DateUtility.getDate("20060204","yyyyMMdd");
            
            l_unit.reason = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            log.info("======================" + WEB3StringTypeUtility.getByteLength(l_unit.reason));
            
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02926);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * �s�e�ُ�C����ʉ߁B
     */
    public void testValidateCase0010()
    {
        final String STR_METHOD_NAME = "testValidateCase0010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "456";
            l_unit.targetPeriodFrom = WEB3DateUtility.getDate("20060203","yyyyMMdd");
            l_unit.targetPeriodTo = WEB3DateUtility.getDate("20060204","yyyyMMdd");
            l_unit.reason = "asd";

            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * ���N�G�X�g.�����o�^���.���R != null ����200Byte�̏ꍇ�A��O���X���[
     * ��O���X���[
     */
    public void testValidateCase0011()
    {
        final String STR_METHOD_NAME = "testExcute_C0011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "456";
            l_unit.targetPeriodFrom = WEB3DateUtility.getDate("20060203","yyyyMMdd");
            l_unit.targetPeriodTo = WEB3DateUtility.getDate("20060204","yyyyMMdd");
            
            l_unit.reason = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            log.info("======================" + WEB3StringTypeUtility.getByteLength(l_unit.reason));
            
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02926);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * ���N�G�X�g.�����o�^���.�|�� != null ���� ���p�����ȊO 
     * ��O���X���[
     */
    public void testValidateCase0012()
    {
        final String STR_METHOD_NAME = "testExcute_C0012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SLProductInfoUnit l_unit = new WEB3SLProductInfoUnit();
            l_unit.productCode = "123";
            l_unit.productType = "1";
            l_unit.qualifiedDiv = "1";
            l_unit.weight = "�P�Q�R";
            l_request.stockLoanProductInfo = l_unit;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02924);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

}
@
