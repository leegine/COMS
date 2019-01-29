head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginChangeCompleteRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :WEB3OptionsCloseMarginChangeCompleteRequestTest_OT.java
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import test.util.JunitTestBase;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginChangeCompleteRequestTest_OT extends JunitTestBase 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeCompleteRequestTest_OT.class);

    public WEB3OptionsCloseMarginChangeCompleteRequestTest_OT(String arg0)
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

    /*
     * Test method for 'webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest.validate()'
     */
    public void testValidate_case001()
    {
        final String STR_METHOD_NAME = "testValidate_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

    }
    public void testValidate_case002()
    {
        final String STR_METHOD_NAME = "testValidate_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    public void testValidate_case003()
    {
        final String STR_METHOD_NAME = "testValidate_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidate_case004()
    {
        final String STR_METHOD_NAME = "testValidate_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit[] l_aa = {};
            l_request.closeMarginContractUnits = l_aa;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidate_case005()
    {
        final String STR_METHOD_NAME = "testValidate_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "a";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit 
                l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            l_marginContractUnit.id = "1001";
            l_marginContractUnit.contractOrderQuantity = "123";
            l_marginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
            {l_marginContractUnit};
            l_request.closeMarginContractUnits = l_marginContractUnits;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidate_case006()
    {
        final String STR_METHOD_NAME = "testValidate_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "-10";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit 
                l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            l_marginContractUnit.id = "1001";
            l_marginContractUnit.contractOrderQuantity = "123";
            l_marginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
            {l_marginContractUnit};
            l_request.closeMarginContractUnits = l_marginContractUnits;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidate_case007()
    {
        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "0";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit 
                l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            l_marginContractUnit.id = "1001";
            l_marginContractUnit.contractOrderQuantity = "123";
            l_marginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
            {l_marginContractUnit};
            l_request.closeMarginContractUnits = l_marginContractUnits;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidate_case008()
    {
        final String STR_METHOD_NAME = "testValidate_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = null;
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit 
                l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            l_marginContractUnit.id = null;
            l_marginContractUnit.contractOrderQuantity = "123";
            l_marginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
            {l_marginContractUnit};
            l_request.closeMarginContractUnits = l_marginContractUnits;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
  
public void testValidate_case009()
{
    final String STR_METHOD_NAME = "testValidate_case009()";
    log.entering(TEST_START + STR_METHOD_NAME);

    WEB3OptionsCloseMarginChangeCompleteRequest l_request =
        new WEB3OptionsCloseMarginChangeCompleteRequest();
    try
    {
        //��������
        l_request.opOrderQuantity = null;
        
        //�����P���敪
        l_request.orderPriceDiv = "0";
        
        //�����P��
        l_request.limitPrice = null;
        
        //���s����
        l_request.execCondType = "1";
        
        //���������敪
        l_request.expirationDateType = "1";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p�v���~�A��/�����Y���i
        l_request.stopPremium_underlyingAssets = null;
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //W�w�l�p�v���~�A��/�����Y���i
        l_request.wlimitPremium_underlyingAssets = null;
        
        //W�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //W�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //W�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //W�w�l�p�����P��
        l_request.wLimitPrice = null;
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = null;
        
        //�h�c
        l_request.id = "1001";
        
        //�ԍό���
        WEB3FuturesOptionsCloseMarginContractUnit 
            l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
        
        l_marginContractUnit.id = "1001";
        l_marginContractUnit.contractOrderQuantity = "0";
        l_marginContractUnit.settlePriority = "1";
        WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
        {l_marginContractUnit};
        l_request.closeMarginContractUnits = l_marginContractUnits;
        l_request.validate();
        fail();
    }
    catch (WEB3BusinessLayerException l_ex) 
    {
        log.error(STR_METHOD_NAME,l_ex);
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00285, l_ex.getErrorInfo());
    }
    catch (Exception e)
    {
        log.exiting(STR_METHOD_NAME);
        fail();
    }
  }

public void testValidate_case0010()
{
    final String STR_METHOD_NAME = "testValidate_case0010()";
    log.entering(TEST_START + STR_METHOD_NAME);

    WEB3OptionsCloseMarginChangeCompleteRequest l_request =
        new WEB3OptionsCloseMarginChangeCompleteRequest();
    try
    {
        //��������
        l_request.opOrderQuantity = null;
        
        //�����P���敪
        l_request.orderPriceDiv = "0";
        
        //�����P��
        l_request.limitPrice = null;
        
        //���s����
        l_request.execCondType = "1";
        
        //���������敪
        l_request.expirationDateType = "1";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p�v���~�A��/�����Y���i
        l_request.stopPremium_underlyingAssets = null;
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //W�w�l�p�v���~�A��/�����Y���i
        l_request.wlimitPremium_underlyingAssets = null;
        
        //W�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //W�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //W�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //W�w�l�p�����P��
        l_request.wLimitPrice = null;
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = null;
        
        //�h�c
        l_request.id = "1001";
        
        //�ԍό���
        WEB3FuturesOptionsCloseMarginContractUnit 
            l_marginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
        
        l_marginContractUnit.id = "1001";
        l_marginContractUnit.contractOrderQuantity = null;
        l_marginContractUnit.settlePriority = null;
        WEB3FuturesOptionsCloseMarginContractUnit[] l_marginContractUnits =
        {l_marginContractUnit};
        l_request.closeMarginContractUnits = l_marginContractUnits;
        l_request.validate();
        fail();
    }
    catch (WEB3BusinessLayerException l_ex) 
    {
        log.error(STR_METHOD_NAME,l_ex);
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00285, l_ex.getErrorInfo());
    }
    catch (Exception e)
    {
        log.exiting(STR_METHOD_NAME);
        fail();
    }
  }

  public void testValidateATReserveOrder_case001()
  {
      final String STR_METHOD_NAME = "testValidateATReserveOrder_case001()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3OptionsCloseMarginChangeCompleteRequest l_request =
          new WEB3OptionsCloseMarginChangeCompleteRequest();
      try
      {
          l_request.validateATReserveOrder();
          fail();
      }
      catch (WEB3BusinessLayerException l_ex) 
      {
          log.error(STR_METHOD_NAME,l_ex);
          log.exiting(STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
      }
      catch (Exception e)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }

  }
  public void testValidateATReserveOrder_case002()
  {
    final String STR_METHOD_NAME = "testValidateATReserveOrder_case002()";
    log.entering(TEST_START + STR_METHOD_NAME);
    
    WEB3OptionsCloseMarginChangeCompleteRequest l_request =
        new WEB3OptionsCloseMarginChangeCompleteRequest();
    try
    {
        //��������
        l_request.opOrderQuantity = "2";
        
        //�����P���敪
        l_request.orderPriceDiv = "0";
        
        //�����P��
        l_request.limitPrice = null;
        
        //���s����
        l_request.execCondType = "1";
        
        //���������敪
        l_request.expirationDateType = "1";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p�v���~�A��/�����Y���i
        l_request.stopPremium_underlyingAssets = null;
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //W�w�l�p�v���~�A��/�����Y���i
        l_request.wlimitPremium_underlyingAssets = null;
        
        //W�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //W�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //W�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //W�w�l�p�����P��
        l_request.wLimitPrice = null;
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = null;
        
        l_request.validateATReserveOrder();
        fail();
    }
    catch (WEB3BusinessLayerException l_ex) 
    {
        log.error(STR_METHOD_NAME,l_ex);
        log.exiting(STR_METHOD_NAME);
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
    }
    catch (Exception e)
    {
        log.exiting(STR_METHOD_NAME);
        fail();
    }
  }
    public void testValidateATReserveOrder_case003()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateATReserveOrder_case004()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnit = {};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnit;
            l_request.validateATReserveOrder();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    public void testValidateATReserveOrder_case005()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00206, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    public void testValidateATReserveOrder_case006()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            //�m�F���P��
            l_request.checkPrice = "100";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateATReserveOrder_case007()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "3";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            //�m�F���P��
            l_request.checkPrice = "100";
            
            //�m�F��������
            l_request.checkDate = new Date(2004/07/07);
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex) 
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateATReserveOrder_case008()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            //�m�F���P��
            l_request.checkPrice = "100";
            
            //�m�F��������
            l_request.checkDate = new Date(2004/07/07);
            
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateATReserveOrder_case009()
    {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        WEB3OptionsCloseMarginChangeCompleteRequest l_request =
            new WEB3OptionsCloseMarginChangeCompleteRequest();
        try
        {
            //��������
            l_request.opOrderQuantity = "2";
            
            //�����P���敪
            l_request.orderPriceDiv = "0";
            
            //�����P��
            l_request.limitPrice = null;
            
            //���s����
            l_request.execCondType = "1";
            
            //���������敪
            l_request.expirationDateType = "1";
            
            //�����L������
            l_request.expirationDate = null;
            
            //���������敪
            l_request.orderCondType = "0";
            
            //�t�w�l�p�v���~�A��/�����Y���i
            l_request.stopPremium_underlyingAssets = null;
            
            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;
            
            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;
            
            //W�w�l�p�v���~�A��/�����Y���i
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W�w�l�p���������P��
            l_request.wlimitOrderCondPrice = null;
            
            //W�w�l�p�����������Z�q
            l_request.wlimitOrderCondOperator = null;
            
            //W�w�l�p�����P���敪
            l_request.wLimitOrderPriceDiv = null;
            
            //W�w�l�p�����P��
            l_request.wLimitPrice = null;
            
            //W�w�l�p���s����
            l_request.wlimitExecCondType = null;
            
            //W�w�l�p�L����ԋ敪
            l_request.wlimitEnableStatusDiv = null;
            
            //�h�c
            l_request.id = "1001";
            
            //�ԍό���
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            //�m�F���P��
            l_request.checkPrice = "100";
            
            //�m�F��������
            l_request.checkDate = new Date(2004/07/07);
            
            l_request.validateATReserveOrder();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
