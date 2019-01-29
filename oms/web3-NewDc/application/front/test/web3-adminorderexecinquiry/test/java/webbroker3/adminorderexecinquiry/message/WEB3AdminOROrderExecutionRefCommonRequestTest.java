head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminOROrderExecutionRefCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminOROrderExecutionRefCommonRequestTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/14 ���� �V�K�쐬
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminOROrderExecutionRefCommonRequestTest extends TestBaseForMock
{
	private WEB3AdminOROrderExecutionRefCommonRequestForTest l_request = null;
	
	/**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOROrderExecutionRefCommonRequestTest.class); 
	
    public WEB3AdminOROrderExecutionRefCommonRequestTest(String name)
	{
		super(name);
	}
    
    protected void setUp() throws Exception
    {
    	super.setUp();
    	this.l_request = new WEB3AdminOROrderExecutionRefCommonRequestForTest();
    	this.getData();
    }
    
    protected void tearDown() throws Exception
    {
    	super.tearDown();
    	this.l_request = null;
    }
    
    /**
     * this.l_request.orderRootDiv = "F"(IVR(���������d�b�j
     *
     */
    public void test_validate_C0001()
    {
		final String STR_METHOD_NAME = "test_validate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			this.l_request.validate();
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseException);
			fail();
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_validate_C0002()
    {
		final String STR_METHOD_NAME = "test_validate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			this.l_request.orderRootDiv = "0";
			this.l_request.validate();
		}
		catch (WEB3BusinessLayerException l_web3BusinessLayerException)
		{
			assertEquals(l_web3BusinessLayerException.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01472);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    

    //1.5�Ŗ{�I�̍X�y�����B���f��NO.099
//    public void testValidate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            l_request.branchCode = new String[]{"381", "123"};
//            l_request.orderRootDiv = "G";
//            WEB3AdminOROrderExecutionSortKeyUnit l_sortKeyUnit =
//                new WEB3AdminOROrderExecutionSortKeyUnit();
//            l_sortKeyUnit.keyItem = "456";
//            l_sortKeyUnit.ascDesc = WEB3AscDescDef.ASC;
//            l_request.sortKeys = new WEB3AdminOROrderExecutionSortKeyUnit[]{l_sortKeyUnit};
//            l_request.pageIndex = "12";
//            l_request.pageSize = "10";
//            l_request.validate();
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
   
    //���������敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
    //�u���������敪������`�̒l�v�̗�O���X���[����B
    //�E"��������"
    //�E"�o����܂Œ���"
    //�E"�[��܂Œ���"
    public void testValidate_ES_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_ES_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.expirationDateType = "4";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00209);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //���������敪�ɉ��L�̍��ڂ��ݒ�
    //�E"��������"
    //�E"�o����܂Œ���"
    //�E"�[��܂Œ���"
    //����ʉ�
    public void testValidate_ES_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_ES_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.expirationDateType = "3";
            l_request.validate();
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(STR_METHOD_NAME);
    }

    private void getData(){
    	
    	String [] l_strBranches ={"381"};
    	WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys = new WEB3AdminOROrderExecutionSortKeyUnit[1];
    	sortKeys[0] = new WEB3AdminOROrderExecutionSortKeyUnit();
    	sortKeys[0].keyItem = "01"; 
    	sortKeys[0].ascDesc = "A";
    	this.l_request.orderRootDiv = "F";
    	this.l_request.pageIndex = "1";
    	this.l_request.pageSize = "3";
    	this.l_request.branchCode = l_strBranches;
    	this.l_request.sortKeys = sortKeys;
    }
}
@
