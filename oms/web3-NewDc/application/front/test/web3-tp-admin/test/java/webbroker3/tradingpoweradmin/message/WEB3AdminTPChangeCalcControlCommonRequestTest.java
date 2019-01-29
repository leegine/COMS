head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPChangeCalcControlCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX���ʃ��N�G�X�g�N���XTest(WEB3AdminTPPaymentRequisitionManageInfoListRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/31 ���N�|(���u) �V�K�쐬   
Revesion History : 2007/09/12 ��іQ(���u) ���h��NO.015
*/

package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPChangeCalcControlCommonRequestTest extends TestBaseForMock
{
	private WEB3AdminTPChangeCalcControlCommonRequest l_commonRequest = 
		new WEB3AdminTPChangeCalcControlCommonRequest();
	
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlCommonRequestTest.class);

	public WEB3AdminTPChangeCalcControlCommonRequestTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception
    {
        super.setUp();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //  �ڋq�]�͏���ID��null�̏ꍇ
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        	this.l_commonRequest.calcConditionId = null;
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01893,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //  �����~�敪��null�̏ꍇ
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        	this.l_commonRequest.calcConditionId = "12345";
        	this.l_commonRequest.tradingStop = null;
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01894,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //  �o���]�͋敪��null�̏ꍇ
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        	this.l_commonRequest.calcConditionId = "12345";
        	this.l_commonRequest.tradingStop = "12345";
        	this.l_commonRequest.paymentStop = null;
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01897,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //  ���̑����i���t�]�͋敪��null�̏ꍇ
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        	this.l_commonRequest.calcConditionId = "12345";
        	this.l_commonRequest.tradingStop = "12345";
        	this.l_commonRequest.paymentStop = "12345";
        	this.l_commonRequest.otherTradingStop = null;
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01898,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
