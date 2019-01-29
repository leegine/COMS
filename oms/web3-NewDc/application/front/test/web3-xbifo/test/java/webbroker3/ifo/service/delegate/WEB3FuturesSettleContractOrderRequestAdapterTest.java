head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSettleContractOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3FuturesSettleContractOrderRequestAdapterTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 �����i���u�j�V�K�쐬
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesSettleContractOrderRequestAdapterTest extends TestBaseForMock
{
    private WEB3FuturesSettleContractOrderRequestAdapter l_adapter = null;

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderRequestAdapterTest.class);
    
    public WEB3FuturesSettleContractOrderRequestAdapterTest(String name)
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
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(new WEB3GenRequestForTest());
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
     * �����w���敨�ԍϒ����m�F���N�G�X�g��
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
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "5000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * �����w���敨�ԍϒ����m�F���N�G�X�g��
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
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * �����w���敨�ԍϒ����m�F���N�G�X�g��
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
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * �����w���敨�ԍϒ����������N�G�X�g��
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
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "6000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * �����w���敨�ԍϒ����������N�G�X�g��
     * ���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ
     * ���N�G�X�g�f�[�^.limitPrice==null
     * get�P�� = 0
     *
     */
    public void testGetPrice_C0006()
    {
        final String STR_METHOD_NAME = "testGetPrice_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * �����w���敨�ԍϒ����������N�G�X�g��
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
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
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
     * get�������� = 0
     * �����_���h�ȊO�̏ꍇ
     *
     */
    public void testGetOrderQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.closingOrder = "0";
            l_request.futOrderQuantity = "50000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
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
     * get�������� = 50000
     * �����w���敨�ԍϒ����m�F���N�G�X�g��
     *
     */
    public void testGetOrderQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = "50000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
            assertEquals(50000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get�������� = 0
     * �����w���敨�ԍϒ����m�F���N�G�X�g��
     *
     */
    public void testGetOrderQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����m�F���N�G�X�g
            WEB3FuturesCloseMarginConfirmRequest l_request = new WEB3FuturesCloseMarginConfirmRequest();
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
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
     * get�������� = 0
     * �����_���h�ȊO�̏ꍇ
     *
     */
    public void testGetOrderQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.closingOrder = "0";
            l_request.futOrderQuantity = "50000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
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
     * get�������� = 60000
     * �����_���h�̏ꍇ
     *
     */
    public void testGetOrderQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = "60000";
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
            assertEquals(60000,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get�������� = 0
     * �����_���h�̏ꍇ
     *
     */
    public void testGetOrderQuantity_C0006()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // �����w���敨�ԍϒ����������N�G�X�g
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = null;
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            double l_dblPrice = this.l_adapter.getOrderQuantity();
            assertEquals(0,l_dblPrice,0);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �e�o�FSYSTEM_ERROR_80005�F�e�[�u���ɊY������f�[�^������܂���B
     * 
     *
     */
    public void testGetContract_C0001()
    {
        final String STR_METHOD_NAME = "testGetContract_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "12345";
                       
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3IfoContractImpl l_ifoContractImpl = this.l_adapter.getContract();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����u���ʁv�I�ꍇ
     * 
     *
     */
    public void testGetContract_C0002()
    {
        final String STR_METHOD_NAME = "testGetContract_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams =TestDBUtility.getIfoContractRow();
            TestDBUtility.insertWithDel(l_ifoContractParams);
            WEB3FuturesCloseMarginCompleteRequest l_request = new WEB3FuturesCloseMarginCompleteRequest();
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "1001";
                       
            this.l_adapter = WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
            WEB3IfoContractImpl l_ifoContractImpl = this.l_adapter.getContract();
            assertEquals(1001,l_ifoContractImpl.getContractId());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
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
