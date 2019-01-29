head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenContractRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3FuturesOpenContractRequestAdapterTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 �����i���u�j�V�K�쐬
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOpenContractRequestAdapterTest extends TestBaseForMock
{
    
    private WEB3FuturesOpenContractRequestAdapter l_adapter = null;

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FuturesOpenContractRequestAdapterTest.class);
    
    public WEB3FuturesOpenContractRequestAdapterTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
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
     * �s���L���Irequest��
     * get�P�� = 0.0
     *
     */
    public void testGetPrice_C0001()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(new WEB3GenRequestForTest());
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0.0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�V�K�������m�F���N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ
     * ���N�G�X�g�f�[�^.limitPrice=5000
     * get�P�� = 5000
     * 
     *
     */
    public void testGetPrice_C0002()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������m�F���N�G�X�g
            WEB3FuturesOpenMarginConfirmRequest l_request = new WEB3FuturesOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "5000";
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(5000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�V�K�������m�F���N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ
     * ���N�G�X�g�f�[�^.limitPrice!=null
     * get�P�� = 0
     *
     */
    public void testGetPrice_C0003()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������m�F���N�G�X�g
            WEB3FuturesOpenMarginConfirmRequest l_request = new WEB3FuturesOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�V�K�������m�F���N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "���s"�̏ꍇ
     * 
     * get�P�� = 0
     *
     */
    public void testGetPrice_C0004()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������m�F���N�G�X�g
            WEB3FuturesOpenMarginConfirmRequest l_request = new WEB3FuturesOpenMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    /**
     * �����w���敨�V�K�������������N�G�X�g���N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ
     * ���N�G�X�g�f�[�^.limitPrice=6000
     * get�P�� = 6000
     * 
     *
     */
    public void testGetPrice_C0005()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������������N�G�X�g
            WEB3FuturesOpenMarginCompleteRequest l_request = new WEB3FuturesOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "6000";
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(6000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�V�K�������������N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ
     * ���N�G�X�g�f�[�^.limitPrice!=null
     * get�P�� = 0
     *
     */
    public void testGetPrice_C0006()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������������N�G�X�g
            WEB3FuturesOpenMarginCompleteRequest l_request = new WEB3FuturesOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�V�K�������������N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "���s"�̏ꍇ
     * 
     * get�P�� = 0
     *
     */
    public void testGetPrice_C0007()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�V�K�������������N�G�X�g
            WEB3FuturesOpenMarginCompleteRequest l_request = new WEB3FuturesOpenMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesOpenContractRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getPrice();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    
    
    private class WEB3GenRequestForTest extends WEB3GenRequest
    {

        public WEB3GenResponse createResponse()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }


}
@
