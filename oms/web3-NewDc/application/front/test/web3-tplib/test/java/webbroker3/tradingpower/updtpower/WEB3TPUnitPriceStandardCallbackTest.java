head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPUnitPriceStandardCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]���P��<�W��>CallbackTest(WEB3TPUnitPriceStandardCallbackTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/01/24 �����Q(���u) �V�K�쐬   
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.FeqLastClosingPriceParams;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPUnitPriceStandardCallbackTest extends TestBaseForMock
{

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPUnitPriceStandardCallbackTest.class);

    WEB3TPUnitPriceStandardCallback l_callback;

    public WEB3TPUnitPriceStandardCallbackTest(String name)
    {
        super(name);
    }

    /**
     * �����^�C�v���O�����̏ꍇ
     */
    public void testGetUnitPrice_C001()
    {
        final String STR_METHOD_NAME = "testGetUnitPrice_C001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DB�̏���
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            l_processor.doDeleteAllQuery(FeqLastClosingPriceRow.TYPE);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams.setProductId(123L);
            l_feqLastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20080124", "yyyyMMdd"));
            l_feqLastClosingPriceParams.setOffshoreProductCode("1");
            l_feqLastClosingPriceParams.setFeqClosingPriceMarketCode("2");
            l_feqLastClosingPriceParams.setFeqClosingPriceMrktCodeS("3");
            l_feqLastClosingPriceParams.setFeqClosingPrice(5.0d);
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams);

            //���ۂ̃��\�b�h�����s
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            l_callback = new WEB3TPUnitPriceStandardCallback(l_condition);
            double l_result = l_callback.getUnitPrice(l_productParams);

            //��r
            assertEquals(l_result, 5.0d, 0);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �����^�C�v���O�����łȂ��ꍇ
     */
    public void testGetUnitPrice_C002()
    {
        final String STR_METHOD_NAME = "testGetUnitPrice_C002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DB�̏���
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setEstimationPrice(6.0d);
            TestDBUtility.insertWithDel(l_productParams);

            //���ۂ̃��\�b�h�����s
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            l_callback = new WEB3TPUnitPriceStandardCallback(l_condition);
            double l_result = l_callback.getUnitPrice(l_productParams);

            //��r
            assertEquals(l_result, 6.0d, 0);
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
