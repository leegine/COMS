head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecutionInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminFeqExecutionInputServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/05 武波 (中訊) 新規作成
*/
package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqExecutionInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionInputServiceImplTest.class);
    public WEB3AdminFeqExecutionInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_getInputScreen_c0001()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_c0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                true,
                true);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            l_feqOrderUnitParams.setOrderEmpCode("NW12345");
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setConfirmedPrice(0);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqOrderExecutionRow
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3AdminFeqExecutionInputRequest l_request =
                new WEB3AdminFeqExecutionInputRequest();
            l_request.managementCode = "12345";
            WEB3AdminFeqExecutionInputServiceImpl l_impl =
                new WEB3AdminFeqExecutionInputServiceImpl();
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02143);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
