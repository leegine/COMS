head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSummaryContractPerProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3IfoSummaryContractPerProductTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/02 ���z(���u) �V�K�쐬
*/
package webbroker3.ifodeposit;

import java.lang.reflect.Field;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoSummaryContractPerProductTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoSummaryContractPerProductTest.class);
    
    WEB3IfoSummaryContractPerProduct l_perProduct = null;

    public WEB3IfoSummaryContractPerProductTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_perProduct = new WEB3IfoSummaryContractPerProduct();
    }

    protected void tearDown() throws Exception
    {
        l_perProduct = null;
        super.tearDown();
    }

    //add��������
    //OptionSellContractQuantityTemp��[T+0]�̏ꍇ
    public void testAddOrderQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setCurrentBizDateBuyOrderQuantity(2);

            boolean l_blnIsBuy = true;
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            double l_dblQuantity = 3;
            
            l_perProduct.addOrderQuantity(
                l_blnIsBuy,
                l_datOrderBizDate,
                l_datCurrentBizDate,
                l_datNextBizDate,
                l_dblQuantity);
            
            
            assertEquals(5, l_perProduct.getCurrentBizDateBuyOrderQuantity(), 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //add��������
    //OptionSellOverContractQuantityTemp��[T+1]�̏ꍇ
    public void testAddOrderQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setNextBizDateBuyOrderQuantity(2);

            boolean l_blnIsBuy = true;
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            double l_dblQuantity = 3;
            
            l_perProduct.addOrderQuantity(
                l_blnIsBuy,
                l_datOrderBizDate,
                l_datCurrentBizDate,
                l_datNextBizDate,
                l_dblQuantity);
            
            
            assertEquals(5, l_perProduct.getNextBizDateBuyOrderQuantity(), 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //add��������
    //����[T+0]�̏ꍇ
    public void testAddOrderQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setCurrentBizDateSellOrderQuantity(2);

            boolean l_blnIsBuy = false;
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            double l_dblQuantity = 3;
            
            l_perProduct.addOrderQuantity(
                l_blnIsBuy,
                l_datOrderBizDate,
                l_datCurrentBizDate,
                l_datNextBizDate,
                l_dblQuantity);
            
            
            assertEquals(5, l_perProduct.getCurrentBizDateSellOrderQuantity(), 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //add��������
    //����[T+1]�̏ꍇ
    public void testAddOrderQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setNextBizDateSellOrderQuantity(2);

            boolean l_blnIsBuy = false;
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20080901", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("20080902", "yyyyMMdd");
            double l_dblQuantity = 3;
            
            l_perProduct.addOrderQuantity(
                l_blnIsBuy,
                l_datOrderBizDate,
                l_datCurrentBizDate,
                l_datNextBizDate,
                l_dblQuantity);
            
            
            assertEquals(5, l_perProduct.getNextBizDateSellOrderQuantity(), 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //n == 0�̏ꍇ
    public void testCalcBuyContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            
            int l_intReservedDate = 0;
            
            double l_dblBuyContractQuantity =
                l_perProduct.calcBuyContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblBuyContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //n == 1�A�܂���2�̏ꍇ (1)
    public void testCalcBuyContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 1;
            
            double l_dblBuyContractQuantity =
                l_perProduct.calcBuyContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblBuyContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //n == 1�A�܂���2�̏ꍇ (2)
    public void testCalcBuyContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 2;
            
            double l_dblBuyContractQuantity =
                l_perProduct.calcBuyContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblBuyContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //�ȊO�̎� (3)
    public void testCalcBuyContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 3;
            
            double l_dblBuyContractQuantity =
                l_perProduct.calcBuyContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblBuyContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //����.�w�����0�̎�
    public void testCalcSellContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setSellQuantity(2);
            l_perProduct.setCurrentBizDateSellOrderQuantity(3);
            
            int l_intReservedDate = 0;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcSellContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //����.�w�����1�܂���2�̎� (1)
    public void testCalcSellContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setSellQuantity(2);
            l_perProduct.setCurrentBizDateSellOrderQuantity(3);
            l_perProduct.setNextBizDateSellOrderQuantity(4);
            
            int l_intReservedDate = 1;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcSellContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //����.�w�����1�܂���2�̎� (2)
    public void testCalcSellContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setSellQuantity(2);
            l_perProduct.setCurrentBizDateSellOrderQuantity(3);
            l_perProduct.setNextBizDateSellOrderQuantity(4);
            
            int l_intReservedDate = 2;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcSellContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc��������
    //�ȊO�̎� (3)
    public void testCalcSellContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setSellQuantity(2);
            l_perProduct.setCurrentBizDateSellOrderQuantity(3);
            l_perProduct.setNextBizDateSellOrderQuantity(4);
            
            int l_intReservedDate = 3;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcSellContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�敨�̎�
    public void testCalcOptionBuyContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 0;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionBuyContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //����.�w�����0�̎�
    public void testCalcOptionBuyContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(2, "CALL_OPTIONS"));
            
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 0;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionBuyContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //����.�w�����1�̎�
    public void testCalcOptionBuyContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(3, "PUT_OPTIONS"));
            
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 1;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionBuyContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //����.�w�����2�̎�
    public void testCalcOptionBuyContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 2;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionBuyContractQuantity(l_intReservedDate);
            
            assertEquals(9, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //����.�w�����3�̎�
    public void testCalcOptionBuyContractQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantity_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            l_perProduct.setBuyQuantity(2);
            l_perProduct.setCurrentBizDateBuyOrderQuantity(3);
            l_perProduct.setNextBizDateBuyOrderQuantity(4);
            
            int l_intReservedDate = 3;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionBuyContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�敨�̎�
    public void testCalcOptionSellContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            l_perProduct.setSellQuantity(5);
            
            int l_intReservedDate = 0;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionSellContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�I�v�V�����̎�
    //����.�w�����0�̎�
    public void testCalcOptionSellContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(2, "CALL_OPTIONS"));
            
            l_perProduct.setSellQuantity(5);
            
            int l_intReservedDate = 0;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionSellContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�I�v�V�����̎�
    //����.�w�����1�̎�
    public void testCalcOptionSellContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(3, "PUT_OPTIONS"));
            
            l_perProduct.setSellQuantity(5);
            
            int l_intReservedDate = 1;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionSellContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�I�v�V�����̎�
    //����.�w�����2�̎�
    public void testCalcOptionSellContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            l_perProduct.setSellQuantity(5);
            
            int l_intReservedDate = 2;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionSellContractQuantity(l_intReservedDate);
            
            assertEquals(5, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V����������
    //�I�v�V�����̎�
    //����.�w�����3�̎�
    public void testCalcOptionSellContractQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantity_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            l_perProduct.setSellQuantity(5);
            
            int l_intReservedDate = 3;
            
            double l_dblSellContractQuantity =
                l_perProduct.calcOptionSellContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblSellContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����0�̎� +
    public void testCalcOptionBuyOverContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����0�̎� -
    public void testCalcOptionBuyOverContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����1�̎� +
    public void testCalcOptionBuyOverContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 1;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����1�̎� -
    public void testCalcOptionBuyOverContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 1;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����2�̎� +
    public void testCalcOptionBuyOverContractQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 2;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����2�̎� -
    public void testCalcOptionBuyOverContractQuantity_C0006()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 2;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����3�̎� +
    public void testCalcOptionBuyOverContractQuantity_C0007()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantity_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 3;

            double l_dblOptionBuyOverContractQuantity =
                l_perProductForTest.calcOptionBuyOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����0�̎� +
    public void testCalcOptionSellOverContractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 0;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����0�̎� -
    public void testCalcOptionSellOverContractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 0;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����1�̎� +
    public void testCalcOptionSellOverContractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 1;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����1�̎� -
    public void testCalcOptionSellOverContractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 1;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����2�̎� +
    public void testCalcOptionSellOverContractQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 2;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����2�̎� -
    public void testCalcOptionSellOverContractQuantity_C0006()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 2;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ�
    //����.�w�����3�̎� +
    public void testCalcOptionSellOverContractQuantity_C0007()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantity_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 3;

            double l_dblOptionSellOverContractQuantity =
                l_perProductForTest.calcOptionSellOverContractQuantity(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantity, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����0�̎�
    public void testCalcBuyOptionValue_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValue_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 0;

            double l_dblBuyOptionValue =
                l_perProductForTest.calcBuyOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblBuyOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����1�̎�
    public void testCalcBuyOptionValue_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValue_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 1;

            double l_dblBuyOptionValue =
                l_perProductForTest.calcBuyOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblBuyOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����2�̎�
    public void testCalcBuyOptionValue_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValue_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 2;

            double l_dblBuyOptionValue =
                l_perProductForTest.calcBuyOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblBuyOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����3�̎�
    public void testCalcBuyOptionValue_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValue_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 3;

            double l_dblBuyOptionValue =
                l_perProductForTest.calcBuyOptionValue(l_intReservedDate);
            
            assertEquals(0, l_dblBuyOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����0�̎�
    public void testCalcSellOptionValue_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValue_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 0;

            double l_dblSellOptionValue =
                l_perProductForTest.calcSellOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblSellOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����1�̎�
    public void testCalcSellOptionValue_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValue_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 1;

            double l_dblSellOptionValue =
                l_perProductForTest.calcSellOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblSellOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����2�̎�
    public void testCalcSellOptionValue_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValue_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 2;

            double l_dblSellOptionValue =
                l_perProductForTest.calcSellOptionValue(l_intReservedDate);
            
            assertEquals(6, l_dblSellOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʔ��I�v�V�������l
    //����.�w�����3�̎�
    public void testCalcSellOptionValue_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValue_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentPrice(1.1);
            l_perProductForTest.setUnitSize(2);
            
            int l_intReservedDate = 3;

            double l_dblSellOptionValue =
                l_perProductForTest.calcSellOptionValue(l_intReservedDate);
            
            assertEquals(0, l_dblSellOptionValue, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�敨�̏ꍇ
    //����.�w�����0�̎�
    public void testCalcOptionBuyContractQuantityTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantityTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("buyQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyContractQuantityTemp =
                l_perProduct.calcOptionBuyContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�I�v�V�����̏ꍇ
    //����.�w�����0�̎�
    public void testCalcOptionBuyContractQuantityTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantityTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("buyQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(2, "CALL_OPTIONS"));
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyContractQuantityTemp =
                l_perProduct.calcOptionBuyContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionBuyContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�I�v�V�����̏ꍇ
    //����.�w�����1�̎�
    public void testCalcOptionBuyContractQuantityTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantityTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("buyQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(3, "PUT_OPTIONS"));
            
            int l_intReservedDate = 1;

            double l_dblOptionBuyContractQuantityTemp =
                l_perProduct.calcOptionBuyContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionBuyContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�����̏ꍇ
    //����.�w�����2�̎�
    public void testCalcOptionBuyContractQuantityTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantityTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("buyQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            int l_intReservedDate = 2;

            double l_dblOptionBuyContractQuantityTemp =
                l_perProduct.calcOptionBuyContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionBuyContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�����̏ꍇ
    //����.�w�����3�̎�
    public void testCalcOptionBuyContractQuantityTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyContractQuantityTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("buyQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            int l_intReservedDate = 3;

            double l_dblOptionBuyContractQuantityTemp =
                l_perProduct.calcOptionBuyContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�敨�̏ꍇ
    //����.�w�����0�̎�
    public void testCalcOptionSellContractQuantityTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantityTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("sellQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(1, "FUTURES"));
            
            int l_intReservedDate = 0;

            double l_dblOptionSellContractQuantityTemp =
                l_perProduct.calcOptionSellContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�I�v�V�����̏ꍇ
    //����.�w�����0�̎�
    public void testCalcOptionSellContractQuantityTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantityTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("sellQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(2, "CALL_OPTIONS"));
            
            int l_intReservedDate = 0;

            double l_dblOptionSellContractQuantityTemp =
                l_perProduct.calcOptionSellContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionSellContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�I�v�V�����̏ꍇ
    //����.�w�����1�̎�
    public void testCalcOptionSellContractQuantityTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantityTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("sellQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(3, "PUT_OPTIONS"));
            
            int l_intReservedDate = 1;

            double l_dblOptionSellContractQuantityTemp =
                l_perProduct.calcOptionSellContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionSellContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�����̏ꍇ
    //����.�w�����2�̎�
    public void testCalcOptionSellContractQuantityTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantityTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("sellQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            int l_intReservedDate = 2;

            double l_dblOptionSellContractQuantityTemp =
                l_perProduct.calcOptionSellContractQuantityTemp(l_intReservedDate);
            
            assertEquals(5, l_dblOptionSellContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V�������������؋����s�����m�聄
    //�����̏ꍇ
    //����.�w�����3�̎�
    public void testCalcOptionSellContractQuantityTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellContractQuantityTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Field field = WEB3IfoSummaryContract.class.getDeclaredField("sellQuantityTemp");
            field.setAccessible(true);
            field.set(l_perProduct, new Double(5.0));

            l_perProduct.setIfoDerivativeType(new IfoDerivativeTypeEnum(0, "OTHER"));
            
            int l_intReservedDate = 3;

            double l_dblOptionSellContractQuantityTemp =
                l_perProduct.calcOptionSellContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎� +
    public void testCalcOptionBuyOverContractQuantityTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎� -
    public void testCalcOptionBuyOverContractQuantityTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 0;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎� +
    public void testCalcOptionBuyOverContractQuantityTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 1;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎� -
    public void testCalcOptionBuyOverContractQuantityTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 1;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎� +
    public void testCalcOptionBuyOverContractQuantityTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 2;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎� -
    public void testCalcOptionBuyOverContractQuantityTemp_C0006()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 2;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����3�̎� +
    public void testCalcOptionBuyOverContractQuantityTemp_C0007()
    {
        final String STR_METHOD_NAME = "testCalcOptionBuyOverContractQuantityTemp_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 3;

            double l_dblOptionBuyOverContractQuantityTemp =
                l_perProductForTest.calcOptionBuyOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionBuyOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎� -
    public void testCalcOptionSellOverContractQuantityTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 0;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎� +
    public void testCalcOptionSellOverContractQuantityTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 0;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎� -
    public void testCalcOptionSellOverContractQuantityTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 1;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎� +
    public void testCalcOptionSellOverContractQuantityTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 1;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎� -
    public void testCalcOptionSellOverContractQuantityTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestA l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestA();
            
            int l_intReservedDate = 2;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎� +
    public void testCalcOptionSellOverContractQuantityTemp_C0006()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 2;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(4, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����3�̎� +
    public void testCalcOptionSellOverContractQuantityTemp_C0007()
    {
        final String STR_METHOD_NAME = "testCalcOptionSellOverContractQuantityTemp_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestB l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestB();
            
            int l_intReservedDate = 3;

            double l_dblOptionSellOverContractQuantityTemp =
                l_perProductForTest.calcOptionSellOverContractQuantityTemp(l_intReservedDate);
            
            assertEquals(0, l_dblOptionSellOverContractQuantityTemp, 0);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 0;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 0;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 1;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 1;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 2;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0006()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 2;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����3�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcBuyOptionValueTemp_C0007()
    {
        final String STR_METHOD_NAME = "testCalcBuyOptionValueTemp_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 3;

            double l_dblBuyOptionValueTemp =
                l_perProductForTest.calcBuyOptionValueTemp(l_intReservedDate);
            
            assertEquals(0.0, l_dblBuyOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 0;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����0�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 0;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 1;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����1�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 1;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎�
    //�������Z�l==ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0005()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(0);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 2;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(13.2, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����2�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0006()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 2;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(6.6, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
    //����.�w�����3�̎�
    //�������Z�l!=ZERO�̏ꍇ
    public void testCalcSellOptionValueTemp_C0007()
    {
        final String STR_METHOD_NAME = "testCalcSellOptionValueTemp_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoSummaryContractPerProductForTestC l_perProductForTest =
                new WEB3IfoSummaryContractPerProductForTestC();
            
            l_perProductForTest.setCurrentBizDateLiquidationPrice(1.1);
            l_perProductForTest.setCurrentPrice(2.2);
            
            l_perProductForTest.setUnitSize(3);
            
            int l_intReservedDate = 3;

            double l_dblSellOptionValueTemp =
                l_perProductForTest.calcSellOptionValueTemp(l_intReservedDate);
            
            assertEquals(0.0, l_dblSellOptionValueTemp, 1);

        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3IfoSummaryContractPerProductForTestA extends WEB3IfoSummaryContractPerProduct
    {
        public double calcOptionBuyContractQuantity(int l_intReservedDate)
        {
            return 9;
        }
        
        public double calcOptionSellContractQuantity(int l_intReservedDate)
        {
            return 5;
        }
        
        public double calcOptionBuyContractQuantityTemp(int l_intReservedDate)
        {
            return 9;
        }
        
        public double calcOptionSellContractQuantityTemp(int l_intReservedDate)
        {
            return 5;
        }
        

    }
    
    class WEB3IfoSummaryContractPerProductForTestB extends WEB3IfoSummaryContractPerProduct
    {
        public double calcOptionBuyContractQuantity(int l_intReservedDate)
        {
            return 5;
        }
        
        public double calcOptionSellContractQuantity(int l_intReservedDate)
        {
            return 9;
        }
        
        public double calcOptionBuyContractQuantityTemp(int l_intReservedDate)
        {
            return 5;
        }
        
        public double calcOptionSellContractQuantityTemp(int l_intReservedDate)
        {
            return 9;
        }
    }
    
    class WEB3IfoSummaryContractPerProductForTestC extends WEB3IfoSummaryContractPerProduct
    {
        public double calcOptionBuyOverContractQuantity(int l_intReservedDate)
        {
            return 3;
        }
        
        public double calcOptionSellOverContractQuantity(int l_intReservedDate)
        {
            return 3;
        }
        
        public double calcOptionBuyOverContractQuantityTemp(int l_intReservedDate)
        {
            return 2;
        }
        
        public double calcOptionSellOverContractQuantityTemp(int l_intReservedDate)
        {
            return 2;
        }
    }
}
@
