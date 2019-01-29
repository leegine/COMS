head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOrderNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/01 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptRow;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsOrderNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOrderNotifyServiceImplTest.class);
    
    private WEB3OptionsOrderNotifyServiceImpl l_impl = null;

    public WEB3OptionsOrderNotifyServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3OptionsOrderNotifyServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * リクエストデータ = null
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3BackRequest l_request = null;
            
            l_impl.execute(l_request);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3BackRequest l_request = new WEB3OptionsOrderNotifyRequest();
            
            l_impl.execute(l_request);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * l_lisRecords.size() = 0
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderReceiptRow.TYPE);
            
            l_impl.new WEB3OptionsOrderNotifyTransactionCallback().process();
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 一件処理にてエラー発生
     */
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderReceiptRow.TYPE);
            HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams = TestDBUtility.getHostFotypeOrderReceiptRow();
            l_hostFotypeOrderReceiptParams.setStatus("0");
            l_hostFotypeOrderReceiptParams.setSonarTradedCode("51");
            l_hostFotypeOrderReceiptParams.setSonarMarketCode("G");
            l_hostFotypeOrderReceiptParams.setProductCode("160030005");
            TestDBUtility.insertWithDel(l_hostFotypeOrderReceiptParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            l_impl.new WEB3OptionsOrderNotifyTransactionCallback().process();
            
            HostFotypeOrderReceiptRow l_hostFotypeOrderReceiptRow = null;
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = " request_code = ? ";
            Object[] l_objWhere = {"EI821"};
            
            List l_lisRecords = l_processor.doFindAllQuery(
                    HostFotypeOrderReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_hostFotypeOrderReceiptRow = (HostFotypeOrderReceiptRow)l_lisRecords.get(0);
            
            assertEquals("9", l_hostFotypeOrderReceiptRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
