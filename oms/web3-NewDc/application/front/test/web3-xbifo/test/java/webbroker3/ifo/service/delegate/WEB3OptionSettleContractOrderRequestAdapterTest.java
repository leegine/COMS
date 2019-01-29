head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : XXXXXXXXXXXXXXXXXXXX(XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.java)
Author Name      : Daiwa Institute of Research
Revision History : 200X/XX/XX ��іQ(���u) �V�K�쐬 ���f��No.
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import test.util.TestDBUtility;

import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionSettleContractOrderRequestAdapterTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderRequestAdapterTest.class);
    
    public WEB3OptionSettleContractOrderRequestAdapterTest(String name)
    {
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
    
    /**
     * create
     * OP�ԍϒ������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B 
     */
    public void testCreateCase0001()
    {
        final String STR_METHOD_NAME = "testCreateCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        
        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();
        
        l_request.limitPrice = "1000";
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);
            
            assertEquals("1000", ((WEB3OptionsCloseMarginConfirmRequest)l_requestAdapter.request).limitPrice);
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
     * get����
     * ���N�G�X�g.�ԍό���[0].ID�ɊY�����錚�ʂ��擾���A�ԋp����B 
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetContractCase0001()
    {
        final String STR_METHOD_NAME = "testGetContractCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        
        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();
        
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0].id = "1111";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1111);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            WEB3IfoContractImpl l_ifoContractImpl = l_requestAdapter.getContract(); 
            
            assertEquals("1111", l_ifoContractImpl.getContractId() + "");
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
     * get����
     * ���N�G�X�g.�ԍό���[0].ID�ɊY�����錚�ʂ��擾���A�ԋp����B 
     * request instanceof WEB3OptionsCloseMarginCompleteRequest
     */
    public void testGetContractCase0002()
    {
        final String STR_METHOD_NAME = "testGetContractCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        
        WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();
        
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0].id = "1111";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1111);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            WEB3IfoContractImpl l_ifoContractImpl = l_requestAdapter.getContract(); 
            
            assertEquals("1111", l_ifoContractImpl.getContractId() + "");
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
     * get��������
     * ���N�G�X�g�f�[�^.���Ϗ������h�����_���h�̏ꍇ�A0��ԋp����B
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetOrderQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

        l_request.opOrderQuantity = "100";
        
        l_request.closingOrder = "0";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblOrderQuantity = l_requestAdapter.getOrderQuantity(); 
            
            assertEquals("0.0", l_dblOrderQuantity + "");
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
     * get��������
     * ���N�G�X�g�f�[�^.���Ϗ������h�����_���h�ȊO�̏ꍇ���N�G�X�g�f�[�^.�������ʂ�ԋp����B 
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetOrderQuantityCase0002()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

        l_request.opOrderQuantity = "100";
        
        l_request.closingOrder = "1";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblOrderQuantity = l_requestAdapter.getOrderQuantity(); 
            
            assertEquals("100.0", l_dblOrderQuantity + "");
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
     * get��������
     * ���N�G�X�g�f�[�^.���Ϗ������h�����_���h�̏ꍇ�A0��ԋp����B
     * request instanceof WEB3OptionsCloseMarginCompleteRequest
     */
    public void testGetOrderQuantityCase0003()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

        l_request.opOrderQuantity = "100";
        
        l_request.closingOrder = "0";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblOrderQuantity = l_requestAdapter.getOrderQuantity(); 
            
            assertEquals("0.0", l_dblOrderQuantity + "");
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
     * get��������
     * ���N�G�X�g�f�[�^.���Ϗ������h�����_���h�ȊO�̏ꍇ���N�G�X�g�f�[�^.�������ʂ�ԋp����B 
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetOrderQuantityCase0004()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

        l_request.opOrderQuantity = "100";
        
        l_request.closingOrder = "1";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblOrderQuantity = l_requestAdapter.getOrderQuantity(); 
            
            assertEquals("100.0", l_dblOrderQuantity + "");
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
     * get�P��
     *���N�G�X�g�f�[�^.�����P���敪=="�w�l"�̏ꍇ�́A���N�G�X�g�f�[�^.�����P����ԋp����B
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetPriceCase0001()
    {
        final String STR_METHOD_NAME = "testGetPriceCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

        l_request.limitPrice = "200";
        
        l_request.orderPriceDiv = "1";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblPrice = l_requestAdapter.getPrice(); 
            
            assertEquals("200.0", l_dblPrice + "");
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
     * get�P��
     * ���N�G�X�g�f�[�^.�����P���敪=="���s"�̏ꍇ�́A0��ԋp����.
     * request instanceof WEB3OptionsCloseMarginConfirmRequest
     */
    public void testGetPriceCase0002()
    {
        final String STR_METHOD_NAME = "testGetPriceCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

        l_request.limitPrice = "200";
        
        l_request.orderPriceDiv = "0";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblPrice = l_requestAdapter.getPrice(); 
            
            assertEquals("0.0", l_dblPrice + "");
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
     * get�P��
     * ���N�G�X�g�f�[�^.�����P���敪=="�w�l"�̏ꍇ�́A���N�G�X�g�f�[�^.�����P����ԋp����B
     * request instanceof WEB3OptionsCloseMarginCompleteRequest
     */
    public void testGetPriceCase0003()
    {
        final String STR_METHOD_NAME = "testGetPriceCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

        l_request.limitPrice = "200";
        
        l_request.orderPriceDiv = "1";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblPrice = l_requestAdapter.getPrice(); 
            
            assertEquals("200.0", l_dblPrice + "");
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
     * get�P��
     * ���N�G�X�g�f�[�^.�����P���敪=="���s"�̏ꍇ�́A0��ԋp����.
     * request instanceof WEB3OptionsCloseMarginCompleteRequest
     */
    public void testGetPriceCase0004()
    {
        final String STR_METHOD_NAME = "testGetPriceCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

        l_request.limitPrice = "200";
        
        l_request.orderPriceDiv = "0";
        
        try
        {
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter =
                WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

            double l_dblPrice = l_requestAdapter.getPrice(); 
            
            assertEquals("0.0", l_dblPrice + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    

}
@
