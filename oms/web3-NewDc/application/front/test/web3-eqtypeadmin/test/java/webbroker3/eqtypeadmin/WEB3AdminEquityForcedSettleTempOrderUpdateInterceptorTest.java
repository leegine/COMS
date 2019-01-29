head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/28 ��{���� �V�K�쐬
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;

import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ω������X�V�C���^�Z�v�^)<BR>
 * �������ω������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * 
 * @@author ��{����
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest.class);
    WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor interceptor = null;

    public WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest(String arg0)
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
     * (�X�V�l�ݒ�)<BR>
     * OK<BR>
     * testMutate_0001<BR>
     */
    public void testMutate_0001()
    {
        final String STR_METHOD_NAME = " testMutate_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MarginSettleContractOrderSpec l_eqTypeSettleContractOrderSpec = null;
            WEB3GentradeCommission l_commission = null;
            WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = null;
            interceptor = new WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor(
                    l_eqTypeSettleContractOrderSpec,
                    l_commission, 
                    l_equityRealizedProfitAndLossPrice,
                    "",
                    0.0D,
                    "",
                    "",
                    //�������ϗ��R�敪
                    "S",
                    //���F��ԋ敪
                    "T",
                    //�ۏ؋��ێ���
                    new Double(0.123456D),
                    //�Ǐؔ�����
                    WEB3DateUtility.getDate("20070101","yyyyMMdd"),
                    //�Ǐ،o�ߓ���
                    new Integer(12));
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = interceptor.mutate(null, null, new EqtypeOrderUnitParams());
            
            //�������ϗ��R�敪
            assertEquals("S", l_eqtypeOrderUnitParams.getForcedSettleReasonType());
            //���F��ԋ敪
            assertEquals("T", l_eqtypeOrderUnitParams.getApproveStatusType());
            //�ۏ؋��ێ���
            assertEquals(0.123457, l_eqtypeOrderUnitParams.getMarginMaintenanceRate(), 0.00001);
            //�Ǐؔ�����
            assertEquals(WEB3DateUtility.getDate("20070101","yyyyMMdd"), l_eqtypeOrderUnitParams.getAdditionalMarginDate());
            //�Ǐ،o�ߓ���
            assertEquals(12L, l_eqtypeOrderUnitParams.getAdditionalMarginAccruedDays());
            //���������敪
            assertEquals("0", l_eqtypeOrderUnitParams.getForcedExpireType());
            //���F�҃R�[�h
            assertEquals(null, l_eqtypeOrderUnitParams.getApproverCode());
            //���F�^�񏳔F����
            assertEquals(null, l_eqtypeOrderUnitParams.getApproveTimestamp());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
