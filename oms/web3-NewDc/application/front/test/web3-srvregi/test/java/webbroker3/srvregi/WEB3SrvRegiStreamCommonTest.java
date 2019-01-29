head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiStreamCommonTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3SrvRegiStreamCommonTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/05/26 武波 (中訊) 新規作成
*/
package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiStreamCommon;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3SrvRegiStreamCommonTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamCommonTest.class);
    public WEB3SrvRegiStreamCommonTest(String arg0)
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

    WEB3SrvRegiStreamCommon l_srvRegiStreamCommon = new WEB3SrvRegiStreamCommon();
    public void testGetBondBalanceList_0001()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
            l_bondProductParams1.setProductId(1234);
            l_bondProductParams1.setProductCode("101010763");
            l_bondProductParams1.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams1.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams1);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertNull(l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondBalanceList_0002()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(12345);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            l_srvRegiStreamCommon.getBondBalanceList(l_subAccount);

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    public void testGetBondBalanceList_0003()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
            l_bondProductParams1.setProductId(1234);
            l_bondProductParams1.setProductCode("101010763");
            l_bondProductParams1.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams1.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams1);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setQuantityCannotSell(0);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("101010763*100000*100000", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondBalanceList_0004()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductCode("T01010763");
            l_bondProductParams.setProductId(1233);
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_bondProductParams.setProductCode("T01010763");
            l_bondProductParams.setProductId(1234);
            l_bondProductParams.setProductIssueCode("2");
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setAssetId(2);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setQuantityCannotSell(0);
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("T01010763*100*100:T01010763*100*100", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondBalanceList_0005()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            l_bondProductParams.setProductId(1234);
            l_bondProductParams.setProductIssueCode("2");
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setAssetId(2);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("A01010763*100*10000:A01010763*100*10000", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondBalanceList_0006()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            l_bondProductParams.setBondCategCode("1043");
            TestDBUtility.insertWithDel(l_bondProductParams);

            l_bondProductParams.setProductId(1234);
            l_bondProductParams.setProductIssueCode("2");
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setBondCategCode("1043");
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setAssetId(2);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetBondBalanceList_0007()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            l_bondProductParams.setProductCode("123456");
            TestDBUtility.insertWithDel(l_bondProductParams);


            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(100.4321);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("123456*100000*100432", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetBondBalanceList_0008()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            
            l_bondProductParams.setProductCode("123456");
            TestDBUtility.insertWithDel(l_bondProductParams);

            l_bondProductParams.setProductId(1234);
            l_bondProductParams.setProductIssueCode("2");
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductCode("123456");
            
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setAssetId(2);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(100.1234);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(100.4321);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("123456*100000*100432:123456*100000*100123", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBondBalanceList_0009()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            l_bondProductParams.setProductCode("T08888");
            TestDBUtility.insertWithDel(l_bondProductParams);


            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(210.9999);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("T08888*100*210", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBondBalanceList_0010()
    {
        final String STR_METHOD_NAME = "testGetBondBalanceList_0010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(1233);
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductIssueCode("1");
            l_bondProductParams.setProductCode("T08888");
            TestDBUtility.insertWithDel(l_bondProductParams);

            l_bondProductParams.setProductId(1234);
            l_bondProductParams.setProductIssueCode("2");
            l_bondProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_bondProductParams.setBondCategCode(WEB3BondCategCodeDef.DISCOUNT_FINANCE_BOND);
            l_bondProductParams.setProductCode("T09999");
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductId(1234);
            l_assetParams.setAssetId(2);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(200.8888);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            l_assetParams.setProductId(1233);
            l_assetParams.setAssetId(1);
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setProductType(ProductTypeEnum.BOND);
            l_assetParams.setBookValue(210.9999);
            l_assetParams.setQuantityCannotSell(0);
            TestDBUtility.insertWithDel(l_assetParams);

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("T08888*100*210:T09999*100*200", l_srvRegiStreamCommon.getBondBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetTradingPowerBalanceList_0001()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerBalanceList_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(1.1));

            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            assertEquals("1.1:1.1:1.1:1.1:1.1:1.1",
                l_srvRegiStreamCommon.getTradingPowerBalanceList(l_subAccount));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInfoServiceList_0001()
    {
        final String STR_METHOD_NAME = "testGetInfoServiceList_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);

            assertNull(l_srvRegiStreamCommon.getInfoServiceList(
                "0D", "381", "123456", new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime())));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInfoServiceList_0002()
    {
        final String STR_METHOD_NAME = "testGetInfoServiceList_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strSystemTimestamp = WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            SrvRegiApplicationParams l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationParams();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("381");
            l_srvRegiApplicationParams.setAccountCode("123456");
            l_srvRegiApplicationParams.setCancelDiv(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
            l_srvRegiApplicationParams.setEffectiveDiv(WEB3EffectiveDivDef.EFFECTIVE);
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate(
                l_strSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_srvRegiApplicationParams.setSrvDiv("1");
            l_srvRegiApplicationParams.setRegistId(123);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("381");
            l_srvRegiApplicationParams.setAccountCode("123456");
            l_srvRegiApplicationParams.setCancelDiv(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
            l_srvRegiApplicationParams.setEffectiveDiv(WEB3EffectiveDivDef.EFFECTIVE);
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate(
                l_strSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_srvRegiApplicationParams.setSrvDiv("1");
            l_srvRegiApplicationParams.setRegistId(124);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("381");
            l_srvRegiApplicationParams.setAccountCode("123456");
            l_srvRegiApplicationParams.setCancelDiv(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
            l_srvRegiApplicationParams.setEffectiveDiv(WEB3EffectiveDivDef.EFFECTIVE);
            l_srvRegiApplicationParams.setAppliEndDate(WEB3DateUtility.getDate(
                l_strSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_srvRegiApplicationParams.setSrvDiv("2");
            l_srvRegiApplicationParams.setRegistId(125);
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);

            assertEquals("1:2", l_srvRegiStreamCommon.getInfoServiceList(
                "0D", "381", "123456", new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime())));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondOrgUsedCryptPassword_0001()
    {
        final String STR_METHOD_NAME = "testGetBondOrgUsedCryptPassword_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            assertNull(l_srvRegiStreamCommon.getBondOrgUsedCryptPassword(
                "0D", "381", "123456", "1",  new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()), "1"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondOrgUsedCryptPassword_0002()
    {
        final String STR_METHOD_NAME = "testGetBondOrgUsedCryptPassword_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "getLoginId",
            new Class[] {},
            new Long(1001));

            LoginInfo l_loginInfo = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV","0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

//            Map l_mapLoginAttributes = new HashMap();
//            l_mapLoginAttributes.put("WEB3_ENCRYPTED_PASSWORD", "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginAttributes",
                new Class[] {long.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_srvRegiStreamCommon.getBondOrgUsedCryptPassword(
                "0D", "381", "123456", "1",
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()), null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03094, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondOrgUsedCryptPassword_0003()
    {
        final String STR_METHOD_NAME = "testGetBondOrgUsedCryptPassword_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Map l_map = new HashMap();
            l_map.put(WEB3LoginAttributeKeyDef.PASSWORD, "12345678");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
	            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
	            "getLoginAttributes",
	            new Class[] {long.class},
	            l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getLoginId",
                new Class[] {},
                new Long(1001));
            
            LoginInfo l_loginInfo = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            Map l_Midmap = new HashMap();
            l_map.put("TRADING_PWD_ENV","0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_Midmap);

            Map l_mapLoginAttributes = new HashMap();
            l_mapLoginAttributes.put("WEB3_ENCRYPTED_PASSWORD", "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginAttributes",
                new Class[] {long.class},
                l_mapLoginAttributes);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams();
            l_srvRegiKeyParams.setInstitutionCode("0D");
            l_srvRegiKeyParams.setSrvDiv("1");
            l_srvRegiKeyParams.setSrvUseKeyType(WEB3SrvUseKeyTypeDef.HSAH_VALUE);
            l_srvRegiKeyParams.setSrvUseId(1);
            l_srvRegiKeyParams.setSrvUseKey("1");
            l_srvRegiKeyParams.setLastUpdater("1");
            l_srvRegiKeyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_srvRegiKeyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_srvRegiKeyParams);
            String l_str = l_srvRegiStreamCommon.getBondOrgUsedCryptPassword(
                "0D", "381", "123456", "1",
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()), null);

            log.debug(l_str + "=========");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBondOrgTradingPowerCheck_0001()
    {
        final String STR_METHOD_NAME = "testValidateBondOrgTradingPowerCheck_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Institution l_institution = l_subAccount.getInstitution();

            Trader l_trader = new WEB3GentradeTrader(
                l_institution,
                l_traderParams.getTraderCode(),
                l_traderParams.getBranchCode());

            l_srvRegiStreamCommon.validateBondOrgTradingPowerCheck(
                l_subAccount, l_trader, 1.1,
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBondOrgTradingPowerCheck_0002()
    {
        final String STR_METHOD_NAME = "testValidateBondOrgTradingPowerCheck_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Institution l_institution = l_subAccount.getInstitution();

            Trader l_trader = new WEB3GentradeTrader(
                l_institution,
                l_traderParams.getTraderCode(),
                l_traderParams.getBranchCode());

            l_srvRegiStreamCommon.validateBondOrgTradingPowerCheck(
                l_subAccount, l_trader, 1.1,
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()),
                new Timestamp(WEB3DateUtility.getDate("20080508", "yyyyMMdd").getTime()));
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01187, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBondProductCode_C0001()
    {
        final String STR_METHOD_NAME = "testValidateBondProductCode_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDel(l_bondProductParams);
    
            WEB3SrvRegiStreamCommon l_srvRegiStreamCommon = new WEB3SrvRegiStreamCommon();
            boolean l_blReturn = l_srvRegiStreamCommon.validateBondProductCode("A01010764", "0D");
            assertFalse(l_blReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBondProductCode_C0002()
    {
        final String STR_METHOD_NAME = "testValidateBondProductCode_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDel(l_bondProductParams);
    
            WEB3SrvRegiStreamCommon l_srvRegiStreamCommon = new WEB3SrvRegiStreamCommon();
            boolean l_blReturn = l_srvRegiStreamCommon.validateBondProductCode("A01010763", "0D");
            assertTrue(l_blReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class LoginInfoImplTest implements LoginInfo
    {
    	public LoginTypeInfo getLoginTypeInfo()
    	{
    		return null;
    	}

        public long getLoginId()
        {
        	return 100;
        }

        public long getLoginTypeId()
        {
        	return 100;
		}

        public  String getUsername()
        {
        	return null;
        }

        public  String getInitialPassword()
        {
        	return null;
        }

        public  Set getSubordinateLoginGroups()
        {
        	return null;
        }

        public  boolean isDisabled()
        {
        	return false;
        }

        public  Set getReachableAccountIds()
        {
        	return null;
        }

        public  Set getReachableLoginIds()
        {
        	return null;
        }

        public Set getReachableLogins()
        {
        	return null;
        }

        public Map getAttributes()
        {
        	return null;
        }

        public boolean isAccountReachable(long l)
        {
        	return true;
        }

        public boolean hasFailedLogin()
        {
        	return true;
        }

        public int getFailureCount()
        {
        	return 0;
        }

        public Date getLastFailureTimestamp()
        {
        	return null;
        }
    }
}

@
