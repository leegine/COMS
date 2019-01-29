head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformConditionRegVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取報・取残電子交付・特定口座登録伝票(WEB3InformConditionRegVoucherTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/21 趙林鵬(中訊) 新規作成
*/

package webbroker3.inform;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostConditionRegVoucherDao;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformConditionRegVoucherTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3InformConditionRegVoucherTest.class);

    public WEB3InformConditionRegVoucherTest(String arg0)
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

    public void testSaveHostConditionRegVoucherCase0001()
    {
        final String STR_METHOD_NAME = "testSaveHostConditionRegVoucherCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);
            
            String l_strVoucherRequestNumber = "12345";
            WEB3InformConditionRegVoucher l_informConditionRegVoucher =
                new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, l_strVoucherRequestNumber);

            l_informConditionRegVoucher.saveHostConditionRegVoucher();

            HostConditionRegVoucherRow l_row = HostConditionRegVoucherDao.findRowByPk(
                "12345", "GI843", "0D","381","2512246");
            
            assertEquals("9999999999999", l_row.getAccOpenRequestNumber());
            assertEquals("0", l_row.getSerialNo());
            assertNull(l_row.getTraderCode());
            assertNull(l_row.getInvEReportDiv());
            assertNull(l_row.getRefundEReportDiv());
            assertEquals("0", l_row.getStatus());
            assertNull(l_row.getSendTimestamp());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(),"yyyyMMdd"));
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testDeleteHostConditionRegVoucherCase0001()
    {
        final String STR_METHOD_NAME = "testDeleteHostConditionRegVoucherCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setOrderRequestNumber(l_hostConditionRegVoucherParams.getOrderRequestNumber());
            l_variousInformParams.setRequestCode(l_hostConditionRegVoucherParams.getRequestCode());
            l_variousInformParams.setInstitutionCode(l_hostConditionRegVoucherParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_hostConditionRegVoucherParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_hostConditionRegVoucherParams.getAccountCode());
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3InformConditionRegVoucher l_informConditionRegVoucher =
                new WEB3InformConditionRegVoucher(l_variousInformParams);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("order_request_number = ? ");
            l_sbWhere.append("and request_code = ? ");
            l_sbWhere.append("and institution_code = ? ");
            l_sbWhere.append("and branch_code = ? ");
            l_sbWhere.append("and account_code = ? ");
            
            Object[] l_objWhereValues = {"1", "GI843", "0D","381","2512246"};
            
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisHostConditionRegVoucherBefore = l_processor.doFindAllQuery(
                    HostConditionRegVoucherRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhereValues);

            assertNotNull(l_lisHostConditionRegVoucherBefore);

            l_informConditionRegVoucher.deleteHostConditionRegVoucher();
            
            List l_lisHostConditionRegVoucherLater = l_processor.doFindAllQuery(
                    HostConditionRegVoucherRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhereValues);
            
            assertEquals(0,l_lisHostConditionRegVoucherLater.size());            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    } 
    
    //is伝票作成
    //　@this.取報・取残電子交付・特定口座登録行の以下項目が全てnullの場合、falseを返却する。
    public void testIsVoucherMakeCase0001()
    {
        final String STR_METHOD_NAME = "testIsVoucherMakeCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                new HostConditionRegVoucherParams();
            WEB3InformConditionRegVoucher l_voucher =
                new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, "");
            
            boolean l_blnIsVoucherMake = l_voucher.isVoucherMake();
            
            assertFalse(l_blnIsVoucherMake);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //１）に該当しない場合trueを返却する
    public void testIsVoucherMakeCase0002()
    {
        final String STR_METHOD_NAME = "testIsVoucherMakeCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                new HostConditionRegVoucherParams();
            l_hostConditionRegVoucherParams.setPosReportTermDiv("1");
            l_hostConditionRegVoucherParams.setPosReportCycleDiv("1");
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv("1");
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv("1");
            l_hostConditionRegVoucherParams.setTradingEReportDiv("1");
            l_hostConditionRegVoucherParams.setInvEReportDiv("1");
            l_hostConditionRegVoucherParams.setRefundEReportDiv("1");
            l_hostConditionRegVoucherParams.setEquityTaxDiv("1");
            l_hostConditionRegVoucherParams.setEquityTaxDivNext("1");
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat("1");
            l_hostConditionRegVoucherParams.setMarginTaxDiv("1");
            l_hostConditionRegVoucherParams.setMarginTaxDivNext("1");
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat("1");
            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv("1");
            WEB3InformConditionRegVoucher l_voucher =
                new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, "");
            
            boolean l_blnIsVoucherMake = l_voucher.isVoucherMake();
            
            assertTrue(l_blnIsVoucherMake);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //１）に該当しない場合trueを返却する
    public void testIsVoucherMakeCase0003()
    {
        final String STR_METHOD_NAME = "testIsVoucherMakeCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                new HostConditionRegVoucherParams();
            l_hostConditionRegVoucherParams.setPosReportTermDiv("1");
            l_hostConditionRegVoucherParams.setPosReportCycleDiv("1");
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv("1");
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv("1");
            l_hostConditionRegVoucherParams.setTradingEReportDiv("1");
            l_hostConditionRegVoucherParams.setInvEReportDiv("1");
            l_hostConditionRegVoucherParams.setRefundEReportDiv("1");
            l_hostConditionRegVoucherParams.setEquityTaxDiv("1");
            l_hostConditionRegVoucherParams.setEquityTaxDivNext("1");
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat("1");
            l_hostConditionRegVoucherParams.setMarginTaxDiv("1");
            l_hostConditionRegVoucherParams.setMarginTaxDivNext("1");
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat("1");
//            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv("1");
            WEB3InformConditionRegVoucher l_voucher =
                new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, "");
            
            boolean l_blnIsVoucherMake = l_voucher.isVoucherMake();
            
            assertTrue(l_blnIsVoucherMake);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
