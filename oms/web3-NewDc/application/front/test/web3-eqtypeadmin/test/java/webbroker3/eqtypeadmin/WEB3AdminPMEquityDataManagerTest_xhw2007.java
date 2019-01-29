head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminPMEquityDataManagerTest_xhw2007.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminPMEquityDataManagerTest_xhw2007.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminPMEquityDataManagerTest_xhw2007 extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminPMEquityDataManagerTest_xhw2007.class);
    WEB3AdminPMEquityDataManager l_dataManager = new WEB3AdminPMEquityDataManager();
    public WEB3AdminPMEquityDataManagerTest_xhw2007(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
        
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
//        TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
//        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//        TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
    }

    /*
     * "參數ソートキー=null
     * 察@看返回?"  參數ソートキー=null
     * 方法@返回?為null
     */
    public void testCreateForcedSettleSortCondition_case001()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = null;
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(null, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「部店コード」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="branchCode"
     * ソートキー.昇順／降順=“A"  
     * "方法@返回?為
     * " branch_id ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case002()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " branch_id ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "branchCode";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「部店コード」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="branchCode"
     * ソートキー.昇順／降順=“D"  
     * "方法@返回?為
     * " branch_id DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case003()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " branch_id DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "branchCode";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「顧客コード」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="accountCode"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " substr(account_id, 9, 6) ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case004()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " substr(account_id, 9, 6) ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "accountCode";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「顧客コード」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="accountCode"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " substr(account_id, 9, 6) DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case005()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " substr(account_id, 9, 6) DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "accountCode";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「強制決済理由」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="forcedSettleReason"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * “ forced_settle_reason_type ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case006()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " forced_settle_reason_type ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "forcedSettleReason";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「強制決済理由」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="forcedSettleReason"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " forced_settle_reason_type DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case007()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " forced_settle_reason_type DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "forcedSettleReason";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「市場コード」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="marketCode"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " market_id DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case008()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " market_id ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "marketCode";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「市場コード」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="marketCode"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " market_id ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case009()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " market_id DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "marketCode";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「銘柄コード」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="productCode"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " product_id ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case010()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " product_id ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "productCode";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「銘柄コード」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="productCode"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " product_id DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case011()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " product_id DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "productCode";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);
        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「口座区分」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="taxType"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " tax_type ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case012()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " tax_type ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "taxType";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「口座区分」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="taxType"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " tax_type DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case013()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " tax_type DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "taxType";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建区分」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="contractType"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " contract_type ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case014()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " contract_type ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractType";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建区分」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="contractType"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " contract_type DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case015()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " contract_type DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractType";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「弁済区分」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="repaymentDiv"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " repayment_type ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case016()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " repayment_type ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "repaymentDiv";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「弁済区分」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="repaymentDiv"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " repayment_type DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case017()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " repayment_type DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "repaymentDiv";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「弁済期限値」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="repaymentTimeLimit"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " repayment_num ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case018()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " repayment_num ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "repaymentTimeLimit";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「弁済期限値」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="repaymentTimeLimit"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " repayment_num DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case019()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " repayment_num DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "repaymentTimeLimit";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建日」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="openDate"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " open_date ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case020()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " open_date ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "openDate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建日」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="openDate"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " open_date DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case021()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " open_date DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "openDate";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「決済期日」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="closeDate"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " close_date ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case022()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " close_date ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "closeDate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「決済期日」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="closeDate"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " close_date DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case023()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " close_date DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "closeDate";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「作成日時」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="createDate"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " created_timestamp ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case024()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " created_timestamp ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "createDate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「作成日時」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="createDate"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " created_timestamp DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case025()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case025()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " created_timestamp DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "createDate";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「（非）承認日時」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="approveDate"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " approve_timestamp ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case026()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case026()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_timestamp ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveDate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「（非）承認日時」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="approveDate"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " approve_timestamp DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case027()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case027()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_timestamp DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveDate";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建株数」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="contractQuantity"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " contract_quantity ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case028()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case028()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " org_contract_quantity ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractQuantity";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建株数」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="contractQuantity"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " contract_quantity DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case029()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case029()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " org_contract_quantity DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractQuantity";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建単価」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="contractPrice"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " contract_price ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case030()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case030()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " original_contract_price ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractPrice";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建単価」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="contractPrice"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " contract_price DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case031()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case031()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " original_contract_price DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractPrice";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建代金」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="contractExecPrice"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " contract_amount ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case032()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case032()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " contract_amount ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractExecPrice";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「建代金」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="contractExecPrice"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " contract_amount DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case033()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case033()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " contract_amount DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "contractExecPrice";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「注文株数」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="orderQuantity"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " quantity ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case034()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case034()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " quantity ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "orderQuantity";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「注文株数」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="orderQuantity"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " quantity DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case035()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case035()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " quantity DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "orderQuantity";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「保証金率」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="marginDepositRate"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " margin_maintenance_rate ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case036()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case036()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " margin_maintenance_rate ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "marginCollateralRate";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「保証金率」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="marginDepositRate"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " margin_maintenance_rate DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case037()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case037()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " margin_maintenance_rate DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "marginCollateralRate";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「承認状態」
     * ソートキー.昇順／降順=“A"
     * "ソートキー.キー項目="approveState"
     * ソートキー.昇順／降順=“A"
     * "方法@返回?
     * " approve_status_type ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case038()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case038()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_status_type ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveState";
        l_strSortsKeys[0].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為1
     * 2）ソートキー.キー項目=「承認状態」
     * ソートキー.昇順／降順=“D"
     * "ソートキー.キー項目="approveState"
     * ソートキー.昇順／降順=“D"
     * "方法@返回?
     * " approve_status_type DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case039()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case039()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_status_type DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveState";
        l_strSortsKeys[0].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為3
     * 2）ソートキー.キー項目[0]=「承認状態」
     *   ソートキー.キー項目[1]=「保証金率」
     *   ソートキー.キー項目[2]=「注文株数」
     *   ソートキー.昇順／降順[0]="A"
     *   ソートキー.昇順／降順[1]="A"
     *   ソートキー.昇順／降順[2]="A"
     *   "ソートキー.キー項目[0]="approveState"
     *   ソートキー.キー項目[1]="marginDepositRate"
     *   ソートキー.キー項目[2]="orderQuantity"
     *   ソートキー.昇順／降順[0]="A"
     *   ソートキー.昇順／降順[1]="A"
     *   ソートキー.昇順／降順[2]="A" 
     *   "方法@返回?為
     *   " approve_status_type ASC ,  margin_maintenance_rate ASC ,  quantity ASC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case040()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case040()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_status_type ASC ,  margin_maintenance_rate ASC ,  quantity ASC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[3];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveState";
        l_strSortsKeys[0].ascDesc = "A";
        l_strSortsKeys[1] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[1].keyItem = "marginCollateralRate";
        l_strSortsKeys[1].ascDesc = "A";
        l_strSortsKeys[2] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[2].keyItem = "orderQuantity";
        l_strSortsKeys[2].ascDesc = "A";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "1）パラメータ.ソートキーの要素数為3
     * 2）ソートキー.キー項目[0]=「承認状態」
     *   ソートキー.キー項目[1]=「保証金率」
     *   ソートキー.キー項目[2]=「注文株数」
     *   ソートキー.昇順／降順[0]="D"
     *   ソートキー.昇順／降順[1]="D"
     *   ソートキー.昇順／降順[2]="D"
     *   "ソートキー.キー項目[0]="approveState"
     *   ソートキー.キー項目[1]="marginDepositRate"
     *   ソートキー.キー項目[2]="orderQuantity"
     *   ソートキー.昇順／降順[0]="D"
     *   ソートキー.昇順／降順[1]="D"
     *   ソートキー.昇順／降順[2]="D" 
     *   "方法@返回?
     *   " approve_status_type DESC ,  margin_maintenance_rate DESC ,  quantity DESC , last_updated_timestamp ASC "
     */
    public void testCreateForcedSettleSortCondition_case041()
    {
        final String STR_METHOD_NAME = ".testCreateForcedSettleSortCondition_case041()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strExpect = " approve_status_type DESC ,  margin_maintenance_rate DESC ,  quantity DESC , last_updated_timestamp ASC ";
        WEB3AdminForcedSettleSortKeyUnit[] l_strSortsKeys = new WEB3AdminForcedSettleSortKeyUnit[3];
        l_strSortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[0].keyItem = "approveState";
        l_strSortsKeys[0].ascDesc = "D";
        l_strSortsKeys[1] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[1].keyItem = "marginCollateralRate";
        l_strSortsKeys[1].ascDesc = "D";
        l_strSortsKeys[2] = new WEB3AdminForcedSettleSortKeyUnit(); 
        l_strSortsKeys[2].keyItem = "orderQuantity";
        l_strSortsKeys[2].ascDesc = "D";
        String l_strSortCondition =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_strSortsKeys);

        assertEquals(l_strExpect, l_strSortCondition);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
///*
//    /*
//     * 參數 強制決済注文一覧 = null
//     * throw BUSINESS_ERROR_80017 異常
//     */
//    public void testcreateForcedSettleTemporaryOrderUnitList_case001()
//    {
//        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        AdminEqForcedSettleOrderRow[] l_orderRowList = null;
//        try
//        {
//            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            l_ex.printStackTrace();
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * 參數 強制決済注文一覧.length=0 
//     * throw BUSINESS_ERROR_80017 異常
//     */
//    public void testcreateForcedSettleTemporaryOrderUnitList_case002()
//    {
//        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        AdminEqForcedSettleOrderRow[] l_orderRowList = new AdminEqForcedSettleOrderRow[0];
//        try
//        {
//            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            l_ex.printStackTrace();
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * "部店プリファ@レンステーブル中沒有
//     * 部店ID = パラメータ.強制決済注文一覧の0番目の要素.部店ID And
//     * プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
//     * プリファ@レンス名の連番 = 1（固定）的記?
//     * レコードが取得できなかった場合、基準建代金 = 0"
//     * 數據準備
//     * 參數 
//     * 1）強制決済注文一覧[0].branchID=1001
//     * 強制決済注文一覧[0].建代金=10
//     * 処理対象の要素.指値 == 1
//     * 數據庫部店プリファ@レンステーブル中沒有
//     * branchID=1001的記?。"
//     * 確認：
//     * 建代金超過フラグ = true
//     * 注文単価区分 = 1
//     */
//    public void testcreateForcedSettleTemporaryOrderUnitList_case003()
//    {
//        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            AdminEqForcedSettleOrderParams[] l_params = new AdminEqForcedSettleOrderParams[1];
//            l_params[0] = new AdminEqForcedSettleOrderParams();
//
//            //<----------------------數據準備------------------------->
//            //* 強制決済注文一覧[0].注文ID=10001
//            l_params[0].setOrderId(10001);
//
//            //* 強制決済注文一覧[0].部店ID=1001
//            l_params[0].setBranchId(1001);
//
//            //* 強制決済注文一覧[0].口座ID=1000001
//            l_params[0].setAccountId(1000001L);
//            
//            //* 強制決済注文一覧[0]..銘柄ID=200001
//            l_params[0].setProductId(200001L);
//            
//            //* 強制決済注文一覧[0].市場ID=444444
//            l_params[0].setMarketId(444444L);
//            
//            //* 強制決済注文一覧[0].税区分=1
//            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
//            
//            //* 強制決済注文一覧[0].建区分=1
//            l_params[0].setContractType(ContractTypeEnum.LONG);
//            
//            //* 強制決済注文一覧[0].建株数=500
//            l_params[0].setContractQuantity(500);
//            
//            //* 強制決済注文一覧[0].建単価=100
//            l_params[0].setContractPrice(100);
//            
//            //* 強制決済注文一覧[0].建代金=10
//            l_params[0].setContractAmount(10);
//            
//            //* 強制決済注文一覧[0].追証発生日=20070808
//            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
//            
//            //* 強制決済注文一覧[0].弁済区分=1
//            l_params[0].setRepaymentType("1");
//            
//            //* 強制決済注文一覧[0].弁済期限値=500
//            l_params[0].setRepaymentNum(500);
//            
//            //* 強制決済注文一覧[0].注文株数=50
//            l_params[0].setQuantity(50);
//            
//            //* 強制決済注文一覧[0].指値=1
//            l_params[0].setLimitPrice(1);
//            
//            //* 強制決済注文一覧[0].建日=20080808
//            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//            
//            //* 強制決済注文一覧[0].発注日=20090909
//            l_params[0].setBizDate("20090909");
//            
//            //* 強制決済注文一覧[0].作成日時=20080810
//            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
//            
//            //* 強制決済注文一覧[0].承認／非承認日時=20080811
//            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
//            
//            //* 強制決済注文一覧[0].承認状態区分=1
//            l_params[0].setApproveStatusType("1");
//            
//            //* 強制決済注文一覧[0].承認者コード=1002
//            l_params[0].setApproverCode("1002");
//            
//            //* 強制決済注文一覧[0].注文エラー理由コード=90001
//            l_params[0].setErrorReasonCode("90001");
//
//            //* 強制決済注文一覧[0].強制決済理由区分=1
//            l_params[0].setForcedSettleReasonType("1");
//            
//            //* 強制決済注文一覧[0].保証金率=8
//            l_params[0].setMarginMaintenanceRate(8);
//            
//            //* 強制決済注文一覧[0].追証経過日数=10
//            l_params[0].setAdditionalMarginAccruedDays(10);
//
//            //* 2)數據庫部店プリファ@レンステーブル中有
//            //* branchID=1001
//            //* プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
//            //* プリファ@レンス名の連番 = 1（固定） value=50的記?
//            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
//
//            //* 3)branch表中準備一條branchID=1001,brachCode=222,的記?
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(1001);
//            l_branchParams.setBranchCode("222");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            //* 4）mainAccount表中準備一條AccountId=1000001,AccountCode=1234567，表示顧客名=“xuhongwei”的記?
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1000001L);
//            l_mainAccountParams.setAccountCode("1234567");
//            l_mainAccountParams.setFamilyName("xuhongwei");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //* 5)銘柄表中準備一條銘柄ID=200001,銘柄コード=200002,銘柄名=1的記? 
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(200001);
//            l_productParams.setProductType(ProductTypeEnum.EQUITY);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_eqtypeProductParams.setProductId(200001);
//            l_eqtypeProductParams.setProductCode("200002");
//            l_eqtypeProductParams.setStandardName("1");
//            TestDBUtility.insertWithDel(l_eqtypeProductParams);
//
//            //* 6）市場表中準備一條市場ID=444444,市場コード=12的記?" 
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(444444);
//            l_marketParams.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
//                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params);
//
//            //<----------------------結果確認------------------------->
//            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //部店code=222
//            assertEquals("222", l_unit[0].branchCode);
//
//            //口座code=123456
//            assertEquals("123456", l_unit[0].accountCode);
//            
//            //顧客名=“xuhongwei”
//            assertEquals("xuhongwei", l_unit[0].accountName);
//            
//            //銘柄code=200002
//            assertEquals("200002", l_unit[0].productCode);
//            
//            //銘柄名=1
//            assertEquals("1", l_unit[0].productName);
//
//            //市場code=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //口座区分=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //建区分=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //建株数=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //建単価=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //建代金=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //追証発生日=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //弁済区分=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //弁済期限値=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //注文株数=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //注文単価=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //建日=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //発注日=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //作成日時=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //承認／非承認日時=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //承認状態区分=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //承認者コード=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //注文エラー理由コード=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //強制決済理由.強制決済理由区分=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //強制決済理由.保証金率=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //強制決済理由.追証経過日数=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //注文単価区分=1
//            assertEquals("1", l_unit[0].orderPriceDiv);
//
//            //建代金超過フラグ=true
//            assertEquals(true, l_unit[0].contractExecPriceOverFlag);
//        }
//        catch (Exception l_ex)
//        {
//            l_ex.printStackTrace();
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
    /*
     * "部店プリファ@レンステーブル中有
     * 部店ID = パラメータ.強制決済注文一覧の0番目の要素.部店ID And
     * プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
     * プリファ@レンス名の連番 = 1（固定）的記?" "1)參數 強制決済注文一覧.size=1
     * 強制決済注文一覧[0].注文ID=10001
     * 強制決済注文一覧[0].部店ID=1001
     * 強制決済注文一覧[0].口座ID=1000001
     * 強制決済注文一覧[0]..銘柄ID=200001
     * 強制決済注文一覧[0].市場ID=444444
     * 強制決済注文一覧[0].税区分=1
     * 強制決済注文一覧[0].建区分=1
     * 強制決済注文一覧[0].建株数=500
     * 強制決済注文一覧[0].建単価=100
     * 強制決済注文一覧[0].建代金=100
     * 強制決済注文一覧[0].追証発生日=20070808
     * 強制決済注文一覧[0].弁済区分=1
     * 強制決済注文一覧[0].弁済期限値=500
     * 強制決済注文一覧[0].注文株数=50
     * 強制決済注文一覧[0].指値=0
     * 強制決済注文一覧[0].建日=20080808
     * 強制決済注文一覧[0].発注日=20090909
     * 強制決済注文一覧[0].作成日時=20080810
     * 強制決済注文一覧[0].承認／非承認日時=20080811
     * 強制決済注文一覧[0].承認状態区分=1
     * 強制決済注文一覧[0].承認者コード=1002
     * 強制決済注文一覧[0].注文エラー理由コード=90001
     * 強制決済注文一覧[0].強制決済理由区分=1
     * 強制決済注文一覧[0].保証金率=8
     * 強制決済注文一覧[0].追証経過日数=10
     * 2)數據庫部店プリファ@レンステーブル中有
     * branchID=1001
     * プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
     * プリファ@レンス名の連番 = 1（固定） value=50的記?
     * 3)branch表中準備一條branchID=1001,brachCode=222222,的記?
     * 4）mainAccount表中準備一條AccountId=1000001,AccountCode=111111，表示顧客名=“xuhongwei”的記?
     * 5)銘柄表中準備一條銘柄ID=200001,銘柄コード=200002,銘柄名=1的記?
     * 6）市場表中準備一條市場ID=444444,市場コード=888888的記?"  
     * "方法@返回?強制決済注文照会情報中的各個屬性?如@下：
     * ID=10001
     * 部店code=222
     * 口座code=1234567
     * 顧客名=“xuhongwei”
     * 銘柄ID=200002
     * 銘柄名=1
     * 市場code=888888
     * 口座区分=1
     * 建区分=1
     * 建株数=500
     * 建単価=100
     * 建代金=100
     * 追証発生日=20070808
     * 弁済区分=1
     * 弁済期限値=500
     * 注文株数=50
     * 指値=0
     * 建日=20080808
     * 発注日=20090909
     * 作成日時=20080810
     * 承認／非承認日時=20080811
     * 承認状態区分=1
     * 承認者コード=1002
     * 注文エラー理由コード=90001
     * 強制決済理由.強制決済理由区分=1
     * 強制決済理由.保証金率=8
     * 強制決済理由.追証経過日数=10
     * 注文単価区分=0
     * 建代金超過フラグ=true"
     */
    public void testcreateForcedSettleTemporaryOrderUnitList_case004()
    {
        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminEqForcedSettleOrderParams[] l_params = new AdminEqForcedSettleOrderParams[1];
            l_params[0] = new AdminEqForcedSettleOrderParams();

            //<----------------------數據準備------------------------->
            //* 強制決済注文一覧[0].注文ID=10001
            l_params[0].setOrderId(10001);

            //* 強制決済注文一覧[0].部店ID=1001
            l_params[0].setBranchId(1001);

            //* 強制決済注文一覧[0].口座ID=1000001
            l_params[0].setAccountId(1000001L);
            
            l_params[0].setAccountCode("TT");

            //* 強制決済注文一覧[0]..銘柄ID=200001
            l_params[0].setProductId(200001L);
            
            l_params[0].setProductCode("LL");

            //* 強制決済注文一覧[0].市場ID=444444
            l_params[0].setMarketId(444444L);
            
            //* 強制決済注文一覧[0].税区分=1
            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
            
            //* 強制決済注文一覧[0].建区分=1
            l_params[0].setContractType(ContractTypeEnum.LONG);
            
            //* 強制決済注文一覧[0].建株数=500
            l_params[0].setContractQuantity(500);
            
            //* 強制決済注文一覧[0].建単価=100
            l_params[0].setContractPrice(100);
            
            //* 強制決済注文一覧[0].建代金=100
            l_params[0].setContractAmount(10);
            
            //* 強制決済注文一覧[0].追証発生日=20070808
            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].弁済区分=1
            l_params[0].setRepaymentType("1");
            
            //* 強制決済注文一覧[0].弁済期限値=500
            l_params[0].setRepaymentNum(500);
            
            //* 強制決済注文一覧[0].注文株数=50
            l_params[0].setQuantity(50);
            
            //* 強制決済注文一覧[0].指値=0
            l_params[0].setLimitPrice(0);
            
            //* 強制決済注文一覧[0].建日=20080808
            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].発注日=20090909
            l_params[0].setBizDate("20090909");
            
            //* 強制決済注文一覧[0].作成日時=20080810
            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].承認／非承認日時=20080811
            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].承認状態区分=1
            l_params[0].setApproveStatusType("1");
            
            //* 強制決済注文一覧[0].承認者コード=1002
            l_params[0].setApproverCode("1002");
            
            //* 強制決済注文一覧[0].注文エラー理由コード=90001
            l_params[0].setErrorReasonCode("90001");

            //* 強制決済注文一覧[0].強制決済理由区分=1
            l_params[0].setForcedSettleReasonType("1");
            
            //* 強制決済注文一覧[0].保証金率=8
            l_params[0].setMarginMaintenanceRate(8);
            
            //* 強制決済注文一覧[0].追証経過日数=10
            l_params[0].setAdditionalMarginAccruedDays(10);
            
            l_params[0].setForcedSettleReasonType("0");

            //* 2)數據庫部店プリファ@レンステーブル中有
            //* branchID=1001
            //* プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
            //* プリファ@レンス名の連番 = 1（固定） value=50的記?
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("50");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            //* 3)branch表中準備一條branchID=1001,brachCode=222,的記?
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            l_branchParams.setBranchCode("222");
            TestDBUtility.insertWithDel(l_branchParams);

            //* 4）mainAccount表中準備一條AccountId=1000001,AccountCode=1234567，表示顧客名=“xuhongwei”的記?
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1000001L);
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setFamilyName("xuhongwei");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 5)銘柄表中準備一條銘柄ID=200001,銘柄コード=200002,銘柄名=1的記? 
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(200001);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(200001);
            l_eqtypeProductParams.setProductCode("200002");
            l_eqtypeProductParams.setStandardName("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //* 6）市場表中準備一條市場ID=444444,市場コード=12的記?" 
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(444444);
            l_marketParams.setMarketCode("12");
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params, null);

            //<----------------------結果確認------------------------->
            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //部店code=222
//            assertEquals("222", l_unit[0].branchCode);
//
            //口座code=123456
            assertEquals("TT", l_unit[0].accountCode);
            
            //顧客名=“xuhongwei”
//            assertEquals("xuhongwei", l_unit[0].accountName);
            
            //銘柄code=200002
            assertEquals("LL", l_unit[0].productCode);
            
            //銘柄名=1
            assertEquals("1", l_unit[0].productName);
//
//            //市場code=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //口座区分=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //建区分=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //建株数=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //建単価=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //建代金=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //追証発生日=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //弁済区分=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //弁済期限値=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //注文株数=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //注文単価=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //建日=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //発注日=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //作成日時=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //承認／非承認日時=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //承認状態区分=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //承認者コード=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //注文エラー理由コード=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //強制決済理由.強制決済理由区分=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //強制決済理由.保証金率=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //強制決済理由.追証経過日数=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //注文単価区分=0
//            assertEquals("0", l_unit[0].orderPriceDiv);
//
//            //建代金超過フラグ=true
//            assertEquals(false, l_unit[0].contractExecPriceOverFlag);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testcreateForcedSettleTemporaryOrderUnitList_case014()
    {
        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminEqForcedSettleOrderParams[] l_params = new AdminEqForcedSettleOrderParams[1];
            l_params[0] = new AdminEqForcedSettleOrderParams();

            //<----------------------數據準備------------------------->
            //* 強制決済注文一覧[0].注文ID=10001
            l_params[0].setOrderId(10001);

            //* 強制決済注文一覧[0].部店ID=1001
            l_params[0].setBranchId(1001);

            //* 強制決済注文一覧[0].口座ID=1000001
            l_params[0].setAccountId(1000001L);
            
//            l_params[0].setAccountCode("TT");

            //* 強制決済注文一覧[0]..銘柄ID=200001
            l_params[0].setProductId(200001L);
            
//            l_params[0].setProductCode("LL");

            //* 強制決済注文一覧[0].市場ID=444444
            l_params[0].setMarketId(444444L);
            
            //* 強制決済注文一覧[0].税区分=1
            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
            
            //* 強制決済注文一覧[0].建区分=1
            l_params[0].setContractType(ContractTypeEnum.LONG);
            
            //* 強制決済注文一覧[0].建株数=500
            l_params[0].setContractQuantity(500);
            
            //* 強制決済注文一覧[0].建単価=100
            l_params[0].setContractPrice(100);
            
            //* 強制決済注文一覧[0].建代金=100
            l_params[0].setContractAmount(10);
            
            //* 強制決済注文一覧[0].追証発生日=20070808
            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].弁済区分=1
            l_params[0].setRepaymentType("1");
            
            //* 強制決済注文一覧[0].弁済期限値=500
            l_params[0].setRepaymentNum(500);
            
            //* 強制決済注文一覧[0].注文株数=50
            l_params[0].setQuantity(50);
            
            //* 強制決済注文一覧[0].指値=0
            l_params[0].setLimitPrice(0);
            
            //* 強制決済注文一覧[0].建日=20080808
            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].発注日=20090909
            l_params[0].setBizDate("20090909");
            
            //* 強制決済注文一覧[0].作成日時=20080810
            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].承認／非承認日時=20080811
            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            //* 強制決済注文一覧[0].承認状態区分=1
            l_params[0].setApproveStatusType("1");
            
            //* 強制決済注文一覧[0].承認者コード=1002
            l_params[0].setApproverCode("1002");
            
            //* 強制決済注文一覧[0].注文エラー理由コード=90001
            l_params[0].setErrorReasonCode("90001");

            //* 強制決済注文一覧[0].強制決済理由区分=1
            l_params[0].setForcedSettleReasonType("1");
            
            //* 強制決済注文一覧[0].保証金率=8
            l_params[0].setMarginMaintenanceRate(8);
            
            //* 強制決済注文一覧[0].追証経過日数=10
            l_params[0].setAdditionalMarginAccruedDays(10);
            
            l_params[0].setForcedSettleReasonType("0");

            //* 2)數據庫部店プリファ@レンステーブル中有
            //* branchID=1001
            //* プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
            //* プリファ@レンス名の連番 = 1（固定） value=50的記?
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("50");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            //* 3)branch表中準備一條branchID=1001,brachCode=222,的記?
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            l_branchParams.setBranchCode("222");
            TestDBUtility.insertWithDel(l_branchParams);

            //* 4）mainAccount表中準備一條AccountId=1000001,AccountCode=1234567，表示顧客名=“xuhongwei”的記?
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1000001L);
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setFamilyName("xuhongwei");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 5)銘柄表中準備一條銘柄ID=200001,銘柄コード=200002,銘柄名=1的記? 
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(200001);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(200001);
            l_eqtypeProductParams.setProductCode("200002");
            l_eqtypeProductParams.setStandardName("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //* 6）市場表中準備一條市場ID=444444,市場コード=12的記?" 
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(444444);
            l_marketParams.setMarketCode("12");
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params, null);

            //<----------------------結果確認------------------------->
            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //部店code=222
//            assertEquals("222", l_unit[0].branchCode);
//
            //口座code
            assertNull(l_unit[0].accountCode);
            
            //顧客名=“xuhongwei”
//            assertEquals("xuhongwei", l_unit[0].accountName);
            
            //銘柄code
            assertNull(l_unit[0].productCode);
            
            //銘柄名=1
            assertEquals("1", l_unit[0].productName);
//
//            //市場code=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //口座区分=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //建区分=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //建株数=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //建単価=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //建代金=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //追証発生日=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //弁済区分=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //弁済期限値=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //注文株数=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //注文単価=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //建日=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //発注日=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //作成日時=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //承認／非承認日時=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //承認状態区分=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //承認者コード=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //注文エラー理由コード=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //強制決済理由.強制決済理由区分=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //強制決済理由.保証金率=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //強制決済理由.追証経過日数=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //注文単価区分=0
//            assertEquals("0", l_unit[0].orderPriceDiv);
//
//            //建代金超過フラグ=true
//            assertEquals(false, l_unit[0].contractExecPriceOverFlag);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//
//    /*
//     * "部店プリファ@レンステーブル中有
//     * 部店ID = パラメータ.強制決済注文一覧の0番目の要素.部店ID And
//     * プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
//     * プリファ@レンス名の連番 = 1（固定）的記?" "1)參數 強制決済注文一覧.size=3
//     * 具體内容如@case62:重復三次"   方法@返回?強制決済注文照会情報中的各個屬性?length=3
//     */
//    public void testcreateForcedSettleTemporaryOrderUnitList_case005()
//    {
//        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            AdminEqForcedSettleOrderParams[] l_params = new AdminEqForcedSettleOrderParams[3];
//            l_params[0] = new AdminEqForcedSettleOrderParams();
//            l_params[1] = new AdminEqForcedSettleOrderParams();
//            l_params[2] = new AdminEqForcedSettleOrderParams();
//
//            for (int i = 0; i < 3; i++)
//            {
//                //<----------------------數據準備------------------------->
//                //* 強制決済注文一覧[i].注文ID=10001
//                l_params[i].setOrderId(10001);
//
//                //* 強制決済注文一覧[i].部店ID=1001
//                l_params[i].setBranchId(1001);
//
//                //* 強制決済注文一覧[i].口座ID=1000001
//                l_params[i].setAccountId(1000001L);
//
//                //* 強制決済注文一覧[i]..銘柄ID=200001
//                l_params[i].setProductId(200001L);
//
//                //* 強制決済注文一覧[i].市場ID=444444
//                l_params[i].setMarketId(444444L);
//                
//                //* 強制決済注文一覧[i].税区分=1
//                l_params[i].setTaxType(TaxTypeEnum.NORMAL);
//                
//                //* 強制決済注文一覧[i].建区分=1
//                l_params[i].setContractType(ContractTypeEnum.LONG);
//                
//                //* 強制決済注文一覧[i].建株数=500
//                l_params[i].setContractQuantity(500);
//                
//                //* 強制決済注文一覧[i].建単価=100
//                l_params[i].setContractPrice(100);
//                
//                if (i == 0)
//                {
//                    //* 強制決済注文一覧[i].建代金=10
//                    l_params[i].setContractAmount(100);
//                }
//                else if (i == 1)
//                {
//                    //* 強制決済注文一覧[i].建代金=10
//                    l_params[i].setContractAmount(10);
//                }
//                else if (i == 2)
//                {
//                    //* 強制決済注文一覧[i].建代金=50
//                    l_params[i].setContractAmount(50);
//                }
//                //* 強制決済注文一覧[i].追証発生日=20070808
//                l_params[i].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
//                
//                //* 強制決済注文一覧[i].弁済区分=1
//                l_params[i].setRepaymentType("1");
//                
//                //* 強制決済注文一覧[i].弁済期限値=500
//                l_params[i].setRepaymentNum(500);
//                
//                //* 強制決済注文一覧[i].注文株数=50
//                l_params[i].setQuantity(50);
//                
//                //* 強制決済注文一覧[i].指値=0
//                l_params[i].setLimitPrice(0);
//                
//                //* 強制決済注文一覧[i].建日=20080808
//                l_params[i].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                
//                //* 強制決済注文一覧[i].発注日=20090909
//                l_params[i].setBizDate("20090909");
//                
//                //* 強制決済注文一覧[i].作成日時=20080810
//                l_params[i].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
//                
//                //* 強制決済注文一覧[i].承認／非承認日時=20080811
//                l_params[i].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
//                
//                //* 強制決済注文一覧[i].承認状態区分=1
//                l_params[i].setApproveStatusType("1");
//                
//                //* 強制決済注文一覧[i].承認者コード=1002
//                l_params[i].setApproverCode("1002");
//                
//                //* 強制決済注文一覧[i].注文エラー理由コード=90001
//                l_params[i].setErrorReasonCode("90001");
//
//                //* 強制決済注文一覧[i].強制決済理由区分=1
//                l_params[i].setForcedSettleReasonType("1");
//                
//                //* 強制決済注文一覧[i].保証金率=8
//                l_params[i].setMarginMaintenanceRate(8);
//                
//                //* 強制決済注文一覧[i].追証経過日数=10
//                l_params[i].setAdditionalMarginAccruedDays(10);
//            }
//
//
//            //* 2)數據庫部店プリファ@レンステーブル中有
//            //* branchID=1001
//            //* プリファ@レンス名 = 信用取引強制決済基準建代金（強調表示判定用） And
//            //* プリファ@レンス名の連番 = 1（固定） value=50的記?
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            l_branchPreferencesParams.setBranchId(1001);
//            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
//            l_branchPreferencesParams.setNameSerialNo(1);
//            l_branchPreferencesParams.setValue("50");
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
//
//            //* 3)branch表中準備一條branchID=1001,brachCode=222,的記?
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(1001);
//            l_branchParams.setBranchCode("222");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            //* 4）mainAccount表中準備一條AccountId=1000001,AccountCode=1234567，表示顧客名=“xuhongwei”的記?
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1000001L);
//            l_mainAccountParams.setAccountCode("1234567");
//            l_mainAccountParams.setFamilyName("xuhongwei");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //* 5)銘柄表中準備一條銘柄ID=200001,銘柄コード=200002,銘柄名=1的記? 
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(200001);
//            l_productParams.setProductType(ProductTypeEnum.EQUITY);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_eqtypeProductParams.setProductId(200001);
//            l_eqtypeProductParams.setProductCode("200002");
//            l_eqtypeProductParams.setStandardName("1");
//            TestDBUtility.insertWithDel(l_eqtypeProductParams);
//
//            //* 6）市場表中準備一條市場ID=444444,市場コード=12的記?" 
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(444444);
//            l_marketParams.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
//                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params);
//
//            //<----------------------結果確認------------------------->
//            for (int i = 0; i < 3; i++)
//            {
//                //ID=10001
//                assertEquals("10001", l_unit[i].id);
//
//                //部店code=222
//                assertEquals("222", l_unit[i].branchCode);
//
//                //口座code=123456
//                assertEquals("123456", l_unit[i].accountCode);
//
//                //顧客名=“xuhongwei”
//                assertEquals("xuhongwei", l_unit[i].accountName);
//
//                //銘柄code=200002
//                assertEquals("200002", l_unit[i].productCode);
//
//                //銘柄名=1
//                assertEquals("1", l_unit[i].productName);
//
//                //市場code=12
//                assertEquals("12", l_unit[i].marketCode);
//
//                //口座区分=1
//                assertEquals("0", l_unit[i].taxType);
//
//                //建区分=1
//                assertEquals("1", l_unit[i].contractType);
//
//                //建株数=500
//                assertEquals("500", l_unit[i].contractQuantity);
//
//                //建単価=100
//                assertEquals("100", l_unit[i].contractPrice);
//
//                if (i == 0)
//                {
//                    //建代金=10
//                    assertEquals("100", l_unit[i].contractExecPrice); 
//                }
//                else if (i == 1)
//                {
//                    //建代金=10
//                    assertEquals("10", l_unit[i].contractExecPrice);   
//                }
//                else if (i == 2)
//                {
//                    //建代金=10
//                    assertEquals("50", l_unit[i].contractExecPrice);
//                }
//
//                //追証発生日=20070808
//                int l_intComResult =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                        l_unit[i].additionalOccurredDate);
//                assertEquals(0, l_intComResult);
//
//                //弁済区分=1
//                assertEquals("1", l_unit[i].repaymentDiv);
//                
//                //弁済期限値=500
//                assertEquals("500", l_unit[i].repaymentTimeLimit);
//                
//                //注文株数=50
//                assertEquals("50", l_unit[i].orderQuantity);
//                
//                //注文単価=0
////                assertEquals(0, l_unit[i].orderPrice());
//
//                //建日=20080808
//                int l_intComResult1 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                        l_unit[i].openDate);
//                assertEquals(0, l_intComResult1);
//                
//                //発注日=20090909
//                int l_intComResult2 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                        l_unit[i].orderBizDate);
//                assertEquals(0, l_intComResult2);
//                
//                //作成日時=20080810
//                int l_intComResult3 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                        l_unit[i].createDate);
//                assertEquals(0, l_intComResult3);
//
//                //承認／非承認日時=20080811
//                int l_intComResult4 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                        l_unit[i].approveDate);
//                assertEquals(0, l_intComResult4);
//
//                //承認状態区分=1
//                assertEquals("1", l_unit[i].approveState);
//
//                //承認者コード=1002
//                assertEquals("1002", l_unit[i].checker);
//
//                //注文エラー理由コード=90001
//                assertEquals("90001", l_unit[i].errorReason);
//
//                //強制決済理由.強制決済理由区分=1
//                assertEquals("1", l_unit[i].forcedSettleReason.forcedSettleReason);
//
//                //強制決済理由.保証金率=8
//                assertEquals("8", l_unit[i].forcedSettleReason.marginDepositRate);
//
//                //強制決済理由.追証経過日数=10
//                assertEquals("10", l_unit[i].forcedSettleReason.additionalElapsedDays);
//
//                //注文単価区分=0
//                assertEquals("0", l_unit[i].orderPriceDiv);
//
//                if (i == 0)
//                {
//                    //建代金超過フラグ=true
//                    assertEquals(true, l_unit[i].contractExecPriceOverFlag);    
//                }
//                else if (i == 1)
//                {
//                    //建代金超過フラグ=false
//                    assertEquals(false, l_unit[i].contractExecPriceOverFlag);    
//                }
//                else if (i == 2)
//                {
//                    //建代金超過フラグ=true
//                    System.out.println("contractExecPriceOverFlag==" + l_unit[i].contractExecPriceOverFlag);
//                    assertEquals(false, l_unit[i].contractExecPriceOverFlag);
//                }
//            }
//        }
//        catch (Exception l_ex)
//        {
//            l_ex.printStackTrace();
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /*
     * "1）注文ID一覧.size為0
     * 具體内容：
     * 2）
     * パラメータ.口座IDFrom == 2001
     * パラメータ.口座IDTo == 2008"
     * 返回結果為 throws SYSTEM_ERROR_80017
     */
    public void testGetForcedSettleOrderList_case000()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case000()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strOrderIdList = new String[0];
        WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = null;
        Long l_lngAccountIdFrom = new Long(2001);
        Long l_lngAccountIdTo = new Long(2008);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_strOrderIdList,
                l_sortsKeys,
                l_lngAccountIdFrom,
                l_lngAccountIdTo);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "1）注文ID一覧.size為1
     * 具體内容：
     * 1001
     * 2）
     * パラメータ.口座IDFrom == 2001
     * パラメータ.口座IDTo == 2008"
     * 強制決済注文Row表中沒有準備數據
     * 返回結果為null
     */
    public void testGetForcedSettleOrderList_case001()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strOrderIdList = new String[1];
        l_strOrderIdList[0] = "1001";
        WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = null;
        Long l_lngAccountIdFrom = new Long(2001);
        Long l_lngAccountIdTo = new Long(2008);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_strOrderIdList,
                l_sortsKeys,
                l_lngAccountIdFrom,
                l_lngAccountIdTo);

            assertEquals(null, l_row);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "1）注文ID一覧.size為3
     * 具體内容：
     * 1001
     * 1002
     * 1003
     * 1004
     * 2）
     * パラメータ.口座IDFrom == 2001
     * パラメータ.口座IDTo == 2008"   "強制決済注文Row表中準備數據如@下：
     * Row1:
     *   .口座ID=2006
     *   注文ID==1001
     *   Row2:
     *   .口座ID=2007
     *   注文ID==1002
     *   Row3:
     *   .口座ID=2006
     *   注文ID==1003
     *   Row4:
     *   .口座ID=2008
     *   注文ID==1004
     *   Row5:
     *   .口座ID=2009
     *   注文ID==1005
     *   方法@返回數據庫中的:
     *   Row1:
     *   Row2:
     *   Row3:
     *   Row:4
     *   四條記"
     */
    public void testGetForcedSettleOrderList_case002()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* "1）注文ID一覧.size為3
        //* 具體内容：
        //* 1001
        //* 1002
        //* 1003
        //* 1004
        String[] l_strOrderIdList = new String[4];
        l_strOrderIdList[0] = "1001";
        l_strOrderIdList[1] = "1002";
        l_strOrderIdList[2] = "1003";
        l_strOrderIdList[3] = "1004";

        //* 2）
        //* パラメータ.口座IDFrom == 2001
        //* パラメータ.口座IDTo == 2008"
        WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_sortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit();
        l_sortsKeys[0].keyItem = "openDate";
        l_sortsKeys[0].ascDesc = "A";
        Long l_lngAccountIdFrom = new Long(2001);
        Long l_lngAccountIdTo = new Long(2008);
        try
        {

            //"強制決済注文Row表中準備數據如@下：
            //* Row1:
            //    *   .口座ID=2005
            //    *   注文ID==1001
            //    *   建日 = 20080111
            // Row2:
            //    *   .口座ID=2007
            //    *   注文ID==1002
            //    *   建日 = 20070111
            // Row3:
            //    *   .口座ID=2006
            //    *   注文ID==1003
            //    *   建日 = 20060111
            // Row4:
            //    *   .口座ID=2008
            //    *   注文ID==1004
            //    *   建日 = 20090111
            // Row5:
            //    *   .口座ID=2009
            //    *   注文ID==1005
            //    *   建日 = 20010111
            //<-------------------測試前數據準備------------------------->
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1002);
            l_eqtypeOrderUnitParams.setAccountId(2006);
            l_eqtypeOrderUnitParams.setOrderUnitId(1002);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1003);
            l_eqtypeOrderUnitParams.setAccountId(2007);
            l_eqtypeOrderUnitParams.setOrderUnitId(1003);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1004);
            l_eqtypeOrderUnitParams.setAccountId(2008);
            l_eqtypeOrderUnitParams.setOrderUnitId(1004);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1005);
            l_eqtypeOrderUnitParams.setAccountId(2009);
            l_eqtypeOrderUnitParams.setOrderUnitId(1005);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
//
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1002);
            l_eqTypeClosingContractSpecParams.setContractId(10002);
            l_eqTypeClosingContractSpecParams.setOrderId(1002);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1002);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1003);
            l_eqTypeClosingContractSpecParams.setContractId(10003);
            l_eqTypeClosingContractSpecParams.setOrderId(1003);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1003);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1004);
            l_eqTypeClosingContractSpecParams.setContractId(10004);
            l_eqTypeClosingContractSpecParams.setOrderId(1004);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1004);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1005);
            l_eqTypeClosingContractSpecParams.setContractId(10005);
            l_eqTypeClosingContractSpecParams.setOrderId(1005);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1005);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10002);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20060111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10003);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20090111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10004);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20010111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10005);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_adminEqrcedSettleOrderrow =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_strOrderIdList,
                l_sortsKeys,
                l_lngAccountIdFrom,
                l_lngAccountIdTo);

            //<-------------------返回結果確認------------------------->
            //*   方法@返回數據庫中的:
            //根據建日的升序排序
            //*   Row3:
            //*   Row2:
            //*   Row1:
            //*   Row:4
            //*   四條記"
            assertEquals(4, l_adminEqrcedSettleOrderrow.length);
            assertEquals(2007, l_adminEqrcedSettleOrderrow[0].getAccountId());
            assertEquals(1003, l_adminEqrcedSettleOrderrow[0].getOrderUnitId());
            assertEquals(2006, l_adminEqrcedSettleOrderrow[1].getAccountId());
            assertEquals(1002, l_adminEqrcedSettleOrderrow[1].getOrderUnitId());
            assertEquals(2005, l_adminEqrcedSettleOrderrow[2].getAccountId());
            assertEquals(1001, l_adminEqrcedSettleOrderrow[2].getOrderUnitId());
            assertEquals(2008, l_adminEqrcedSettleOrderrow[3].getAccountId());
            assertEquals(1004, l_adminEqrcedSettleOrderrow[3].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "1）注文ID一覧.size為3
     * 具體内容：
     * 1001
     * 1002
     * 1003
     * 1004
     * 1005
     * 2）
     * パラメータ.口座IDFrom == null
     * パラメータ.口座IDTo == 2008"   "強制決済注文Row表中準備數據如@下：
     * Row1:
     *   .口座ID=2006
     *   注文ID==1001
     *   Row2:
     *   .口座ID=2007
     *   注文ID==1002
     *   Row3:
     *   .口座ID=2006
     *   注文ID==1003
     *   Row4:
     *   .口座ID=2008
     *   注文ID==1004
     *   Row5:
     *   .口座ID=2009
     *   注文ID==1005
     *   方法@返回數據庫中的:
     *   Row5:
     *   Row3:
     *   Row2:
     *   Row1:
     *   Row:4
     *   四條記"
     */
    public void testGetForcedSettleOrderList_case003()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* "1）注文ID一覧.size為3
        //* 具體内容：
        //* 1001
        //* 1002
        //* 1003
        //* 1004
        //* 1004
        for (int i = 0; i < 2; i++)
        {
            String[] l_strOrderIdList = new String[5];
            l_strOrderIdList[0] = "1001";
            l_strOrderIdList[1] = "1002";
            l_strOrderIdList[2] = "1003";
            l_strOrderIdList[3] = "1004";
            if (i == 1)
            {
                l_strOrderIdList[4] = "1005";    
            }

            //* 2）
            //* パラメータ.口座IDFrom == 2001
            //* パラメータ.口座IDTo == 2008"
            WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
            l_sortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit();
            l_sortsKeys[0].keyItem = "openDate";
            l_sortsKeys[0].ascDesc = "A";
            Long l_lngAccountIdFrom = null;
            Long l_lngAccountIdTo = new Long(2008);
            try
            {

                //"強制決済注文Row表中準備數據如@下：
                //* Row1:
                //    *   .口座ID=2005
                //    *   注文ID==1001
                //    *   建日 = 20080111
                // Row2:
                //    *   .口座ID=2007
                //    *   注文ID==1002
                //    *   建日 = 20070111
                // Row3:
                //    *   .口座ID=2006
                //    *   注文ID==1003
                //    *   建日 = 20060111
                // Row4:
                //    *   .口座ID=2008
                //    *   注文ID==1004
                //    *   建日 = 20090111
                // Row5:
                //    *   .口座ID=2009
                //    *   注文ID==1005
                //    *   建日 = 20010111
                //<-------------------測試前數據準備------------------------->
                EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                    TestDBUtility.getEqtypeOrderUnitRow();
                //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
                l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
                l_eqtypeOrderUnitParams.setApproveStatusType("0");
                l_eqtypeOrderUnitParams.setOrderId(1001);
                l_eqtypeOrderUnitParams.setAccountId(2005);
                l_eqtypeOrderUnitParams.setOrderUnitId(1001);
                TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
                l_eqtypeOrderUnitParams.setOrderId(1002);
                l_eqtypeOrderUnitParams.setAccountId(2006);
                l_eqtypeOrderUnitParams.setOrderUnitId(1002);
                TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
                l_eqtypeOrderUnitParams.setOrderId(1003);
                l_eqtypeOrderUnitParams.setAccountId(2007);
                l_eqtypeOrderUnitParams.setOrderUnitId(1003);
                TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
                l_eqtypeOrderUnitParams.setOrderId(1004);
                l_eqtypeOrderUnitParams.setAccountId(2008);
                l_eqtypeOrderUnitParams.setOrderUnitId(1004);
                TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
                l_eqtypeOrderUnitParams.setOrderId(1005);
                l_eqtypeOrderUnitParams.setAccountId(2009);
                l_eqtypeOrderUnitParams.setOrderUnitId(1005);
                TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

                EqtypeClosingContractSpecParams
                    l_eqTypeClosingContractSpecParams =
                        TestDBUtility.getEqtypeClosingContractSpecRow();

                //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
                l_eqTypeClosingContractSpecParams.setContractId(10001);
                l_eqTypeClosingContractSpecParams.setOrderId(1001);
                l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
                l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
                TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
    //
                l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1002);
                l_eqTypeClosingContractSpecParams.setContractId(10002);
                l_eqTypeClosingContractSpecParams.setOrderId(1002);
                l_eqTypeClosingContractSpecParams.setOrderUnitId(1002);
                TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

                l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1003);
                l_eqTypeClosingContractSpecParams.setContractId(10003);
                l_eqTypeClosingContractSpecParams.setOrderId(1003);
                l_eqTypeClosingContractSpecParams.setOrderUnitId(1003);
                TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

                l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1004);
                l_eqTypeClosingContractSpecParams.setContractId(10004);
                l_eqTypeClosingContractSpecParams.setOrderId(1004);
                l_eqTypeClosingContractSpecParams.setOrderUnitId(1004);
                TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

                l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1005);
                l_eqTypeClosingContractSpecParams.setContractId(10005);
                l_eqTypeClosingContractSpecParams.setOrderId(1005);
                l_eqTypeClosingContractSpecParams.setOrderUnitId(1005);
                TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

                EqtypeContractParams l_eqTypeContractParams =
                    TestDBUtility.getEqtypeContractRow();
                TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
                l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
                l_eqTypeContractParams.setContractId(10001);
                l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
                l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
                TestDBUtility.insertWithDel(l_eqTypeContractParams);
                l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
                l_eqTypeContractParams.setContractId(10002);
                TestDBUtility.insertWithDel(l_eqTypeContractParams);
                l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20060111","yyyyMMdd"));
                l_eqTypeContractParams.setContractId(10003);
                TestDBUtility.insertWithDel(l_eqTypeContractParams);
                l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20090111","yyyyMMdd"));
                l_eqTypeContractParams.setContractId(10004);
                TestDBUtility.insertWithDel(l_eqTypeContractParams);
                l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20010111","yyyyMMdd"));
                l_eqTypeContractParams.setContractId(10005);
                TestDBUtility.insertWithDel(l_eqTypeContractParams);

                AdminEqForcedSettleOrderRow[] l_adminEqrcedSettleOrderrow =
                    WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_strOrderIdList,
                    l_sortsKeys,
                    l_lngAccountIdFrom,
                    l_lngAccountIdTo);

                //<-------------------返回結果確認------------------------->
                //*   方法@返回數據庫中的:
                //根據建日的升序排序
                //*   Row5:
                //*   Row3:
                //*   Row2:
                //*   Row1:
                //*   Row:4
                //*   四條記"
                if (i == 0)
                {
                    log.debug("i == 0");
                    assertEquals(4, l_adminEqrcedSettleOrderrow.length);
                    assertEquals(2007, l_adminEqrcedSettleOrderrow[0].getAccountId());
                    assertEquals(1003, l_adminEqrcedSettleOrderrow[0].getOrderUnitId());
                    assertEquals(2006, l_adminEqrcedSettleOrderrow[1].getAccountId());
                    assertEquals(1002, l_adminEqrcedSettleOrderrow[1].getOrderUnitId());
                    assertEquals(2005, l_adminEqrcedSettleOrderrow[2].getAccountId());
                    assertEquals(1001, l_adminEqrcedSettleOrderrow[2].getOrderUnitId());
                    assertEquals(2008, l_adminEqrcedSettleOrderrow[3].getAccountId());
                    assertEquals(1004, l_adminEqrcedSettleOrderrow[3].getOrderUnitId());
                    
                }
                if (i == 1)
                {
                    log.debug("i == 1");
                    assertEquals(5, l_adminEqrcedSettleOrderrow.length);
                    assertEquals(2009, l_adminEqrcedSettleOrderrow[0].getAccountId());
                    assertEquals(1005, l_adminEqrcedSettleOrderrow[0].getOrderUnitId());
                    assertEquals(2007, l_adminEqrcedSettleOrderrow[1].getAccountId());
                    assertEquals(1003, l_adminEqrcedSettleOrderrow[1].getOrderUnitId());
                    assertEquals(2006, l_adminEqrcedSettleOrderrow[2].getAccountId());
                    assertEquals(1002, l_adminEqrcedSettleOrderrow[2].getOrderUnitId());
                    assertEquals(2005, l_adminEqrcedSettleOrderrow[3].getAccountId());
                    assertEquals(1001, l_adminEqrcedSettleOrderrow[3].getOrderUnitId());
                    assertEquals(2008, l_adminEqrcedSettleOrderrow[4].getAccountId());
                    assertEquals(1004, l_adminEqrcedSettleOrderrow[4].getOrderUnitId());
                }

            }
            catch (Exception l_ex)
            {
                l_ex.printStackTrace();
                fail();
            }

        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    
    /*
     * 參數証券会社=null的場合
     * 參數証券会社=null
     * throws SYSTEM_ERROR_80017
     */
    public void testGetForcedSettleOrderList2_case001()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Institution l_institution = null;
        WEB3AdminForcedSettleReferenceRequest l_referenceRequest = null;

        try
        {
            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in ", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * 管理者・強制決済仮注文照会リクエストオブジェクト=null的場合
     * 管理者・強制決済仮注文照会リクエストオブジェクト=null
     * throws SYSTEM_ERROR_80017
     */
    public void testGetForcedSettleOrderList2_case002()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            WEB3AdminForcedSettleReferenceRequest l_referenceRequest = null;
            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in ", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "部店条件を検索条件に追加
     * get部店ID一覧()の戻り値 = nullの場合
     * "   清空數據庫中Branch表
     * "「条件に該当するデータが存在しない。」の業務エラーをスローする
     * throws BUSINESS_ERROR_01037 error"
     */
    public void testGetForcedSettleOrderList2_case003()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";
            TestDBUtility.deleteAll(BranchParams.TYPE);
            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in ", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
     * 2）パラメータ.リクエストデータ.発注日= nullの場合
     * 3）パラメータ.リクエストデータ.顧客コード = nullの場合
     * 4）パラメータ.リクエストデータ.銘柄コード= nullの場合
     * 5）パラメータ.リクエストデータ.承認状態 = nullの場合
     * 6）パラメータ.リクエストデータ.承認者コード= nullの場合
     * 7）パラメータ.リクエストデータ.作成日時From = nullの場合
     * 8）パラメータ.リクエストデータ.作成日時To = nullの場合
     * 9）パラメータ.リクエストデータ.承認日時From= nullの場合
     * 10）パラメータ.リクエストデータ.承認日時To = nullの場合
     * 11）パラメータ.リクエストデータ.強制決済理由= nullの場合
     * 12）パラメータ.リクエストデータ.決済期日= nullの場合
     * 13）パラメータ.リクエストデータ.注文エラー理由コード = nullの場合
     * 14）パラメータ.リクエストデータ.承認区分 = nullの場合
     * 15）パラメータ.リクエストデータ.口座区分 = nullの場合
     * 16）パラメータ.リクエストデータ.市場コード = nullの場合
     * 確認方法@返回?"
     * "強制決済注文Row表中準備兩條
     * branchID=1001
     * branchID=1002
     * 的數據
     * getBranchId（）方法@返回 {1001}"
     * 強制決済注文Row表中一條branchID為1001的數據被返回
     */
    public void testGetForcedSettleOrderList2_case004()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日= nullの場合
            //* 3）パラメータ.リクエストデータ.顧客コード = nullの場合
            //* 4）パラメータ.リクエストデータ.銘柄コード= nullの場合
            //* 5）パラメータ.リクエストデータ.承認状態 = nullの場合
            //* 6）パラメータ.リクエストデータ.承認者コード= nullの場合
            //* 7）パラメータ.リクエストデータ.作成日時From = nullの場合
            //* 8）パラメータ.リクエストデータ.作成日時To = nullの場合
            //* 9）パラメータ.リクエストデータ.承認日時From= nullの場合
            //* 10）パラメータ.リクエストデータ.承認日時To = nullの場合
            //* 11）パラメータ.リクエストデータ.強制決済理由= nullの場合
            //* 12）パラメータ.リクエストデータ.決済期日= nullの場合
            //* 13）パラメータ.リクエストデータ.注文エラー理由コード = nullの場合
            //* 14）パラメータ.リクエストデータ.承認区分 = nullの場合
            //* 15）パラメータ.リクエストデータ.口座区分 = nullの場合
            //* 16）パラメータ.リクエストデータ.市場コード = nullの場合
            l_referenceRequest.marketCode = "11";

            //強制決済注文Row準備數據
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1002);
            l_eqtypeOrderUnitParams.setAccountId(2006);
            l_eqtypeOrderUnitParams.setOrderUnitId(1002);
            l_eqtypeOrderUnitParams.setBranchId(1002);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1003);
            l_eqtypeOrderUnitParams.setAccountId(2007);
            l_eqtypeOrderUnitParams.setOrderUnitId(1003);
            l_eqtypeOrderUnitParams.setBranchId(1003);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1004);
            l_eqtypeOrderUnitParams.setAccountId(2008);
            l_eqtypeOrderUnitParams.setOrderUnitId(1004);
            l_eqtypeOrderUnitParams.setBranchId(1004);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1005);
            l_eqtypeOrderUnitParams.setAccountId(2009);
            l_eqtypeOrderUnitParams.setOrderUnitId(1005);
            l_eqtypeOrderUnitParams.setBranchId(1005);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
//
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1002);
            l_eqTypeClosingContractSpecParams.setContractId(10002);
            l_eqTypeClosingContractSpecParams.setOrderId(1002);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1002);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1003);
            l_eqTypeClosingContractSpecParams.setContractId(10003);
            l_eqTypeClosingContractSpecParams.setOrderId(1003);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1003);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1004);
            l_eqTypeClosingContractSpecParams.setContractId(10004);
            l_eqTypeClosingContractSpecParams.setOrderId(1004);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1004);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1005);
            l_eqTypeClosingContractSpecParams.setContractId(10005);
            l_eqTypeClosingContractSpecParams.setOrderId(1005);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1005);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10002);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20060111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10003);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20090111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10004);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20010111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10005);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);
            
            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /*
     * "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
     * 2）パラメータ.リクエストデータ.発注日= nullの場合
     * 3）パラメータ.リクエストデータ.顧客コード = nullの場合
     * 4）パラメータ.リクエストデータ.銘柄コード= nullの場合
     * 5）パラメータ.リクエストデータ.承認状態 = nullの場合
     * 6）パラメータ.リクエストデータ.承認者コード= nullの場合
     * 7）パラメータ.リクエストデータ.作成日時From = nullの場合
     * 8）パラメータ.リクエストデータ.作成日時To = nullの場合
     * 9）パラメータ.リクエストデータ.承認日時From= nullの場合
     * 10）パラメータ.リクエストデータ.承認日時To = nullの場合
     * 11）パラメータ.リクエストデータ.強制決済理由= nullの場合
     * 12）パラメータ.リクエストデータ.決済期日= nullの場合
     * 13）パラメータ.リクエストデータ.注文エラー理由コード = nullの場合
     * 14）パラメータ.リクエストデータ.承認区分 = nullの場合
     * 15）パラメータ.リクエストデータ.口座区分 = nullの場合
     * 16）パラメータ.リクエストデータ.市場コード != nullの場合
     * 確認方法@返回?"
     * "強制決済注文Row表中準備兩條
     * branchID=1001
     * branchID=1002
     * 的數據
     * getBranchId（）方法@返回 {1001}"
     * 方法@返回null
     */
    public void testGetForcedSettleOrderList2_case005()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("22");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(12346);
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日= nullの場合
            //* 3）パラメータ.リクエストデータ.顧客コード = nullの場合
            //* 4）パラメータ.リクエストデータ.銘柄コード= nullの場合
            //* 5）パラメータ.リクエストデータ.承認状態 = nullの場合
            //* 6）パラメータ.リクエストデータ.承認者コード= nullの場合
            //* 7）パラメータ.リクエストデータ.作成日時From = nullの場合
            //* 8）パラメータ.リクエストデータ.作成日時To = nullの場合
            //* 9）パラメータ.リクエストデータ.承認日時From= nullの場合
            //* 10）パラメータ.リクエストデータ.承認日時To = nullの場合
            //* 11）パラメータ.リクエストデータ.強制決済理由= nullの場合
            //* 12）パラメータ.リクエストデータ.決済期日= nullの場合
            //* 13）パラメータ.リクエストデータ.注文エラー理由コード = nullの場合
            //* 14）パラメータ.リクエストデータ.承認区分 = nullの場合
            //* 15）パラメータ.リクエストデータ.口座区分 = nullの場合
            //* 16）パラメータ.リクエストデータ.市場コード = nullの場合
            l_referenceRequest.marketCode = "22";

            //強制決済注文Row準備數據
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderId(1002);
            l_eqtypeOrderUnitParams.setAccountId(2006);
            l_eqtypeOrderUnitParams.setOrderUnitId(1002);
            l_eqtypeOrderUnitParams.setBranchId(1002);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
//
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1002);
            l_eqTypeClosingContractSpecParams.setContractId(10002);
            l_eqTypeClosingContractSpecParams.setOrderId(1002);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1002);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            l_eqTypeContractParams.setContractId(10002);
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);
            
            //<-------------------結果確認-------------------------->
            assertEquals(null, l_row);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
     * 2）パラメータ.リクエストデータ.発注日!= nullの場合
     * 3）パラメータ.リクエストデータ.顧客コード != nullの場合
     * 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
     * 5）パラメータ.リクエストデータ.承認状態 != nullの場合
     * 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
     * 7）パラメータ.リクエストデータ.作成日時From != nullの場合
     * 8）パラメータ.リクエストデータ.作成日時To != nullの場合
     * 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
     * 10）パラメータ.リクエストデータ.承認日時To != nullの場合
     * 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
     * 12）パラメータ.リクエストデータ.決済期日!= nullの場合
     * 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
     * 14）パラメータ.リクエストデータ.承認区分 != nullの場合
     * 15）パラメータ.リクエストデータ.口座区分 != nullの場合
     * 16）パラメータ.リクエストデータ.市場コード != nullの場合
     * 確認方法@返回?"
     * "強制決済注文Row表中準備多條
     * branchID=1001
     * branchID=1002
     * branchID=1003
     * branchID=1004
     * branchID=1005
     * 的數據
     * getBranchId（）方法@返回 {1001}"
     * 強制決済注文Row表中一條branchID為1001的數據被返回
     */
    public void testGetForcedSettleOrderList2_case006()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //強制決済注文Row準備數據
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日!= nullの場合
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3）パラメータ.リクエストデータ.顧客コード != nullの場合
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
            l_referenceRequest.productCode = "2";
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("333");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);

            //* 5）パラメータ.リクエストデータ.承認状態 != nullの場合
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7）パラメータ.リクエストデータ.作成日時From != nullの場合
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8）パラメータ.リクエストデータ.作成日時To != nullの場合
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10）パラメータ.リクエストデータ.承認日時To != nullの場合
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
            l_referenceRequest.forcedSettleReason = "9";
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("9");

            //* 12）パラメータ.リクエストデータ.決済期日!= nullの場合
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14）パラメータ.リクエストデータ.承認区分 != nullの場合
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15）パラメータ.リクエストデータ.口座区分 != nullの場合
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16）パラメータ.リクエストデータ.市場コード != nullの場合
            l_referenceRequest.marketCode = "11";


            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);

            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
            assertEquals(3303, l_row[0].getMarketId());
            assertEquals("9001", l_row[0].getErrorReasonCode());
            assertEquals(1, l_row[0].getTaxType().intValue());
            assertEquals(1, l_row[0].getOrderOpenStatus().intValue());
            assertEquals("9", l_row[0].getForcedSettleReasonType());
            assertEquals("11", l_row[0].getApproverCode());
            
            assertEquals("2203", l_row[0].getAccountCode());
            assertEquals("2", l_row[0].getProductCode());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    
    /**
     * パラメータ.リクエストデータ.強制決済理由 != nullの場合、
     * パラメータ.リクエストデータ.強制決済理由 == "追証(第一)期日超過"の場合 
     * 検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
     * データコンテナに"保証金維持率割れ（オンライン開始前・軽度）
     */
    public void testGetForcedSettleOrderList2_case007()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //強制決済注文Row準備數據
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日!= nullの場合
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3）パラメータ.リクエストデータ.顧客コード != nullの場合
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
            l_referenceRequest.productCode = "2";
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("333");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);

            //* 5）パラメータ.リクエストデータ.承認状態 != nullの場合
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7）パラメータ.リクエストデータ.作成日時From != nullの場合
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8）パラメータ.リクエストデータ.作成日時To != nullの場合
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10）パラメータ.リクエストデータ.承認日時To != nullの場合
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
            l_referenceRequest.forcedSettleReason = "90";
            //追証(第一)期日超過
            //保証金維持率割れ（オンライン開始前・軽度）
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS);
            

            //* 12）パラメータ.リクエストデータ.決済期日!= nullの場合
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14）パラメータ.リクエストデータ.承認区分 != nullの場合
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15）パラメータ.リクエストデータ.口座区分 != nullの場合
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16）パラメータ.リクエストデータ.市場コード != nullの場合
            l_referenceRequest.marketCode = "11";


            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);

            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
//            assertEquals(2005, l_row[0].getAccountId());
            assertEquals(3303, l_row[0].getMarketId());
            assertEquals("9001", l_row[0].getErrorReasonCode());
            assertEquals(1, l_row[0].getTaxType().intValue());
            assertEquals(1, l_row[0].getOrderOpenStatus().intValue());
//            assertEquals(3304148080001L, l_row[0].getProductId());
            assertEquals("1", l_row[0].getForcedSettleReasonType());
            assertEquals("11", l_row[0].getApproverCode());
            
            assertEquals("2203", l_row[0].getAccountCode());
            assertEquals("2", l_row[0].getProductCode());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ.リクエストデータ.強制決済理由 != nullの場合、
     * パラメータ.リクエストデータ.強制決済理由 == "追証(第一)期日超過"の場合 
     * 検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
     * データコンテナに"保証金維持率割れ（オンライン開始前・重度）" 
     */
    public void testGetForcedSettleOrderList2_case008()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //強制決済注文Row準備數據
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日!= nullの場合
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3）パラメータ.リクエストデータ.顧客コード != nullの場合
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
            l_referenceRequest.productCode = "2";
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("333");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);

            //* 5）パラメータ.リクエストデータ.承認状態 != nullの場合
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7）パラメータ.リクエストデータ.作成日時From != nullの場合
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8）パラメータ.リクエストデータ.作成日時To != nullの場合
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10）パラメータ.リクエストデータ.承認日時To != nullの場合
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
            l_referenceRequest.forcedSettleReason = "90";
            //追証(第一)期日超過
            //保証金維持率割れ（オンライン開始前・重度）
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS);
            

            //* 12）パラメータ.リクエストデータ.決済期日!= nullの場合
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14）パラメータ.リクエストデータ.承認区分 != nullの場合
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15）パラメータ.リクエストデータ.口座区分 != nullの場合
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16）パラメータ.リクエストデータ.市場コード != nullの場合
            l_referenceRequest.marketCode = "11";


            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);

            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
//            assertEquals(2005, l_row[0].getAccountId());
            assertEquals(3303, l_row[0].getMarketId());
            assertEquals("9001", l_row[0].getErrorReasonCode());
            assertEquals(1, l_row[0].getTaxType().intValue());
            assertEquals(1, l_row[0].getOrderOpenStatus().intValue());
//            assertEquals(3304148080001L, l_row[0].getProductId());
            assertEquals("2", l_row[0].getForcedSettleReasonType());
            assertEquals("11", l_row[0].getApproverCode());
            
            assertEquals("2203", l_row[0].getAccountCode());
            assertEquals("2", l_row[0].getProductCode());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ.リクエストデータ.強制決済理由 != nullの場合、
     * パラメータ.リクエストデータ.強制決済理由 == "追証(第二)期日超過"の場合 
     * 検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
     * データコンテナに"保証金維持率割れ（場間）" 
     */
    public void testGetForcedSettleOrderList2_case009()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //強制決済注文Row準備數據
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日!= nullの場合
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3）パラメータ.リクエストデータ.顧客コード != nullの場合
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
            l_referenceRequest.productCode = "2";
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("333");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);

            //* 5）パラメータ.リクエストデータ.承認状態 != nullの場合
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7）パラメータ.リクエストデータ.作成日時From != nullの場合
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8）パラメータ.リクエストデータ.作成日時To != nullの場合
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10）パラメータ.リクエストデータ.承認日時To != nullの場合
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
            l_referenceRequest.forcedSettleReason = "91";
            //追証(第二)期日超過
            //"保証金維持率割れ（場間）"
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET);
            

            //* 12）パラメータ.リクエストデータ.決済期日!= nullの場合
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14）パラメータ.リクエストデータ.承認区分 != nullの場合
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15）パラメータ.リクエストデータ.口座区分 != nullの場合
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16）パラメータ.リクエストデータ.市場コード != nullの場合
            l_referenceRequest.marketCode = "11";


            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);

            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
//            assertEquals(2005, l_row[0].getAccountId());
            assertEquals(3303, l_row[0].getMarketId());
            assertEquals("9001", l_row[0].getErrorReasonCode());
            assertEquals(1, l_row[0].getTaxType().intValue());
            assertEquals(1, l_row[0].getOrderOpenStatus().intValue());
//            assertEquals(3304148080001L, l_row[0].getProductId());
            assertEquals("3", l_row[0].getForcedSettleReasonType());
            assertEquals("11", l_row[0].getApproverCode());
            
            assertEquals("2203", l_row[0].getAccountCode());
            assertEquals("2", l_row[0].getProductCode());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ.リクエストデータ.強制決済理由 != nullの場合、
     * パラメータ.リクエストデータ.強制決済理由 == "追証(第二)期日超過"の場合 
     * 検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
     * データコンテナに"保証金維持率割れ（オンライン開始前・法@定）" 
     */
    public void testGetForcedSettleOrderList2_case010()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //強制決済注文Row準備數據
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            //TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setApproveStatusType("0");
            l_eqtypeOrderUnitParams.setOrderId(1001);
            l_eqtypeOrderUnitParams.setBranchId(1001);
            l_eqtypeOrderUnitParams.setAccountId(2005);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            //<-------------------------數據準備---------------------------->
            //* "1）get部店ID一覧()の戻り値 的 長度為 1時候並且為1001
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            InstitutionImpl l_institution = new InstitutionImpl(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(1001);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminForcedSettleReferenceRequest l_referenceRequest =
                new WEB3AdminForcedSettleReferenceRequest();
            l_referenceRequest.branchCodeList = new String[1];
            l_referenceRequest.branchCodeList[0] = "624";

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            //* 2）パラメータ.リクエストデータ.発注日!= nullの場合
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3）パラメータ.リクエストデータ.顧客コード != nullの場合
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4）パラメータ.リクエストデータ.銘柄コード!= nullの場合
            l_referenceRequest.productCode = "2";
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("333");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);

            //* 5）パラメータ.リクエストデータ.承認状態 != nullの場合
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6）パラメータ.リクエストデータ.承認者コード!= nullの場合
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7）パラメータ.リクエストデータ.作成日時From != nullの場合
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8）パラメータ.リクエストデータ.作成日時To != nullの場合
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9）パラメータ.リクエストデータ.承認日時From!= nullの場合
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10）パラメータ.リクエストデータ.承認日時To != nullの場合
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11）パラメータ.リクエストデータ.強制決済理由!= nullの場合
            l_referenceRequest.forcedSettleReason = "91";
            //追証(第二)期日超過
            //"保証金維持率割れ（オンライン開始前・法@定）" 
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL);
            

            //* 12）パラメータ.リクエストデータ.決済期日!= nullの場合
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13）パラメータ.リクエストデータ.注文エラー理由コード != nullの場合
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14）パラメータ.リクエストデータ.承認区分 != nullの場合
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15）パラメータ.リクエストデータ.口座区分 != nullの場合
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16）パラメータ.リクエストデータ.市場コード != nullの場合
            l_referenceRequest.marketCode = "11";


            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams
                l_eqTypeClosingContractSpecParams =
                    TestDBUtility.getEqtypeClosingContractSpecRow();

            //TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setContractId(10001);
            l_eqTypeClosingContractSpecParams.setOrderId(1001);
            l_eqTypeClosingContractSpecParams.setClosingContractSpecId(1001);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(10001);
            l_eqTypeContractParams.setOpenDate(WEB3DateUtility.getDate("20080111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdminEqForcedSettleOrderRow[] l_row =
                WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                    l_institution,
                    l_referenceRequest);

            //<-------------------結果確認-------------------------->
            assertEquals(1, l_row.length);
            assertEquals(1001, l_row[0].getBranchId());
//            assertEquals(2005, l_row[0].getAccountId());
            assertEquals(3303, l_row[0].getMarketId());
            assertEquals("9001", l_row[0].getErrorReasonCode());
            assertEquals(1, l_row[0].getTaxType().intValue());
            assertEquals(1, l_row[0].getOrderOpenStatus().intValue());
//            assertEquals(3304148080001L, l_row[0].getProductId());
            assertEquals("4", l_row[0].getForcedSettleReasonType());
            assertEquals("11", l_row[0].getApproverCode());
            
            assertEquals("2203", l_row[0].getAccountCode());
            assertEquals("2", l_row[0].getProductCode());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
