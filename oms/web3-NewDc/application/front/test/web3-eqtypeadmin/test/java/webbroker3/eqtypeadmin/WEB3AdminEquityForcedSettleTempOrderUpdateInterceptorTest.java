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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/28 稲本潤に 新規作成
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
 * (強制決済仮注文更新インタセプタ)<BR>
 * 強制決済仮注文登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 * 
 * @@author 稲本潤に
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
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
     * (更新値設定)<BR>
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
                    //強制決済理由区分
                    "S",
                    //承認状態区分
                    "T",
                    //保証金維持率
                    new Double(0.123456D),
                    //追証発生日
                    WEB3DateUtility.getDate("20070101","yyyyMMdd"),
                    //追証経過日数
                    new Integer(12));
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = interceptor.mutate(null, null, new EqtypeOrderUnitParams());
            
            //強制決済理由区分
            assertEquals("S", l_eqtypeOrderUnitParams.getForcedSettleReasonType());
            //承認状態区分
            assertEquals("T", l_eqtypeOrderUnitParams.getApproveStatusType());
            //保証金維持率
            assertEquals(0.123457, l_eqtypeOrderUnitParams.getMarginMaintenanceRate(), 0.00001);
            //追証発生日
            assertEquals(WEB3DateUtility.getDate("20070101","yyyyMMdd"), l_eqtypeOrderUnitParams.getAdditionalMarginDate());
            //追証経過日数
            assertEquals(12L, l_eqtypeOrderUnitParams.getAdditionalMarginAccruedDays());
            //強制失効区分
            assertEquals("0", l_eqtypeOrderUnitParams.getForcedExpireType());
            //承認者コード
            assertEquals(null, l_eqtypeOrderUnitParams.getApproverCode());
            //承認／非承認日時
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
