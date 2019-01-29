head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformTest070608Wubo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformTest070608Wubo extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test.class);

    public WEB3InformTest070608Wubo(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * setUp<BR>
     * @@param l_strName
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * tearDown<BR>
     * @@param l_strName
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_getInformAddInfoUnit_C0001()
    {
        final String STR_METHOD_NAME = " test_getInformAddInfoUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("111");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
            InformCtrlItemMasterParams l_informCtrlItemMasterParams = TestDBUtility.getInformCtrlItemMasterRow();
            l_informCtrlItemMasterParams.setBranchCode("000");
            l_informCtrlItemMasterParams.setInstitutionCode("123");
            l_informCtrlItemMasterParams.setInformDiv("12");
            l_informCtrlItemMasterParams.setItemSymbolName("123");
            l_informCtrlItemMasterParams.setNecessaryFlag(1);
            l_informCtrlItemMasterParams.setItemCheckMode("14");
            l_informCtrlItemMasterParams.setItemSymbolName("ext_code1");
            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setExtCode1("111");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setAccountCode("111111");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("111111");
            l_mainAccountParams.setInstitutionCode("123");
            l_mainAccountParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("123");
            TestDBUtility.insertWithDel(l_productParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            WEB3Inform l_inform = new WEB3Inform(l_informInfoUnit);

            WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
