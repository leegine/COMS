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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : XXXXXXXXXXXXXXXXXXXX(XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.java)
Author Name      : Daiwa Institute of Research
Revision History : 200X/XX/XX 趙林鵬(中訊) 新規作成 モデルNo.
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
     * ログユーティリティ<BR>
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
     * OP返済注文リクエストアダプタインスタンスを生成する。 
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
     * get建玉
     * リクエスト.返済建玉[0].IDに該当する建玉を取得し、返却する。 
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
     * get建玉
     * リクエスト.返済建玉[0].IDに該当する建玉を取得し、返却する。 
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
     * get注文数量
     * リクエストデータ.決済順序が”ランダム”の場合、0を返却する。
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
     * get注文数量
     * リクエストデータ.決済順序が”ランダム”以外の場合リクエストデータ.注文数量を返却する。 
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
     * get注文数量
     * リクエストデータ.決済順序が”ランダム”の場合、0を返却する。
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
     * get注文数量
     * リクエストデータ.決済順序が”ランダム”以外の場合リクエストデータ.注文数量を返却する。 
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
     * get単価
     *リクエストデータ.注文単価区分=="指値"の場合は、リクエストデータ.注文単価を返却する。
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
     * get単価
     * リクエストデータ.注文単価区分=="成行"の場合は、0を返却する.
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
     * get単価
     * リクエストデータ.注文単価区分=="指値"の場合は、リクエストデータ.注文単価を返却する。
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
     * get単価
     * リクエストデータ.注文単価区分=="成行"の場合は、0を返却する.
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
