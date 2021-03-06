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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : //TODO(WEB3AdminPMEquityDataManagerTest_xhw2007.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/28 GÌ (u) VKì¬
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
 * XXXXXXNX//TODO
 *
 * @@author GÌ(u)
 * @@version 1.0
 */
public class WEB3AdminPMEquityDataManagerTest_xhw2007 extends TestBaseForMock
{

    /**
     * O[eBeB<BR>
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
     * "ÒÉ\[gL[=null
     * @@ÅÔñ?"  ÒÉ\[gL[=null
     * û@@Ôñ?×null
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uXR[hv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="branchCode"
     * \[gL[.¸^~=gA"  
     * "û@@Ôñ?×
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uXR[hv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="branchCode"
     * \[gL[.¸^~=gD"  
     * "û@@Ôñ?×
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÚqR[hv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="accountCode"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÚqR[hv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="accountCode"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u­§ÏRv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="forcedSettleReason"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
     * g forced_settle_reason_type ASC , last_updated_timestamp ASC "
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u­§ÏRv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="forcedSettleReason"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=usêR[hv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="marketCode"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=usêR[hv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="marketCode"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÁ¿R[hv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="productCode"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÁ¿R[hv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="productCode"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uûÀæªv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="taxType"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uûÀæªv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="taxType"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uæªv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="contractType"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uæªv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="contractType"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÙÏæªv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="repaymentDiv"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÙÏæªv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="repaymentDiv"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÙÏúÀlv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="repaymentTimeLimit"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÙÏúÀlv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="repaymentTimeLimit"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uúv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="openDate"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uúv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="openDate"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÏúúv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="closeDate"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÏúúv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="closeDate"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uì¬úv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="createDate"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uì¬úv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="createDate"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uiñj³Fúv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="approveDate"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uiñj³Fúv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="approveDate"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="contractQuantity"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="contractQuantity"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uP¿v
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="contractPrice"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uP¿v
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="contractPrice"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uãàv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="contractExecPrice"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uãàv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="contractExecPrice"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u¶v
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="orderQuantity"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u¶v
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="orderQuantity"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÛØà¦v
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="marginDepositRate"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=uÛØà¦v
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="marginDepositRate"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u³FóÔv
     * \[gL[.¸^~=gA"
     * "\[gL[.L[Ú="approveState"
     * \[gL[.¸^~=gA"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×1
     * 2j\[gL[.L[Ú=u³FóÔv
     * \[gL[.¸^~=gD"
     * "\[gL[.L[Ú="approveState"
     * \[gL[.¸^~=gD"
     * "û@@Ôñ?
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
     * "1jp[^.\[gL[Ìvf×3
     * 2j\[gL[.L[Ú[0]=u³FóÔv
     *   \[gL[.L[Ú[1]=uÛØà¦v
     *   \[gL[.L[Ú[2]=u¶v
     *   \[gL[.¸^~[0]="A"
     *   \[gL[.¸^~[1]="A"
     *   \[gL[.¸^~[2]="A"
     *   "\[gL[.L[Ú[0]="approveState"
     *   \[gL[.L[Ú[1]="marginDepositRate"
     *   \[gL[.L[Ú[2]="orderQuantity"
     *   \[gL[.¸^~[0]="A"
     *   \[gL[.¸^~[1]="A"
     *   \[gL[.¸^~[2]="A" 
     *   "û@@Ôñ?×
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
     * "1jp[^.\[gL[Ìvf×3
     * 2j\[gL[.L[Ú[0]=u³FóÔv
     *   \[gL[.L[Ú[1]=uÛØà¦v
     *   \[gL[.L[Ú[2]=u¶v
     *   \[gL[.¸^~[0]="D"
     *   \[gL[.¸^~[1]="D"
     *   \[gL[.¸^~[2]="D"
     *   "\[gL[.L[Ú[0]="approveState"
     *   \[gL[.L[Ú[1]="marginDepositRate"
     *   \[gL[.L[Ú[2]="orderQuantity"
     *   \[gL[.¸^~[0]="D"
     *   \[gL[.¸^~[1]="D"
     *   \[gL[.¸^~[2]="D" 
     *   "û@@Ôñ?
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
//     * ÒÉ ­§Ï¶ê = null
//     * throw BUSINESS_ERROR_80017 Ùí
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
//     * ÒÉ ­§Ï¶ê.length=0 
//     * throw BUSINESS_ERROR_80017 Ùí
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
//     * "Xvt@@Xe[uL
//     * XID = p[^.­§Ï¶êÌ0ÔÚÌvf.XID And
//     * vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
//     * vt@@X¼ÌAÔ = 1iÅèjIL?
//     * R[hªæ¾Å«È©Á½êAîãà = 0"
//     * Éõ
//     * ÒÉ 
//     * 1j­§Ï¶ê[0].branchID=1001
//     * ­§Ï¶ê[0].ãà=10
//     * ÎÛÌvf.wl == 1
//     * ÉÉXvt@@Xe[uL
//     * branchID=1001IL?B"
//     * mFF
//     * ãà´ßtO = true
//     * ¶P¿æª = 1
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
//            //<----------------------Éõ------------------------->
//            //* ­§Ï¶ê[0].¶ID=10001
//            l_params[0].setOrderId(10001);
//
//            //* ­§Ï¶ê[0].XID=1001
//            l_params[0].setBranchId(1001);
//
//            //* ­§Ï¶ê[0].ûÀID=1000001
//            l_params[0].setAccountId(1000001L);
//            
//            //* ­§Ï¶ê[0]..Á¿ID=200001
//            l_params[0].setProductId(200001L);
//            
//            //* ­§Ï¶ê[0].sêID=444444
//            l_params[0].setMarketId(444444L);
//            
//            //* ­§Ï¶ê[0].Åæª=1
//            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
//            
//            //* ­§Ï¶ê[0].æª=1
//            l_params[0].setContractType(ContractTypeEnum.LONG);
//            
//            //* ­§Ï¶ê[0].=500
//            l_params[0].setContractQuantity(500);
//            
//            //* ­§Ï¶ê[0].P¿=100
//            l_params[0].setContractPrice(100);
//            
//            //* ­§Ï¶ê[0].ãà=10
//            l_params[0].setContractAmount(10);
//            
//            //* ­§Ï¶ê[0].ÇØ­¶ú=20070808
//            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
//            
//            //* ­§Ï¶ê[0].ÙÏæª=1
//            l_params[0].setRepaymentType("1");
//            
//            //* ­§Ï¶ê[0].ÙÏúÀl=500
//            l_params[0].setRepaymentNum(500);
//            
//            //* ­§Ï¶ê[0].¶=50
//            l_params[0].setQuantity(50);
//            
//            //* ­§Ï¶ê[0].wl=1
//            l_params[0].setLimitPrice(1);
//            
//            //* ­§Ï¶ê[0].ú=20080808
//            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//            
//            //* ­§Ï¶ê[0].­ú=20090909
//            l_params[0].setBizDate("20090909");
//            
//            //* ­§Ï¶ê[0].ì¬ú=20080810
//            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
//            
//            //* ­§Ï¶ê[0].³F^ñ³Fú=20080811
//            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
//            
//            //* ­§Ï¶ê[0].³FóÔæª=1
//            l_params[0].setApproveStatusType("1");
//            
//            //* ­§Ï¶ê[0].³FÒR[h=1002
//            l_params[0].setApproverCode("1002");
//            
//            //* ­§Ï¶ê[0].¶G[RR[h=90001
//            l_params[0].setErrorReasonCode("90001");
//
//            //* ­§Ï¶ê[0].­§ÏRæª=1
//            l_params[0].setForcedSettleReasonType("1");
//            
//            //* ­§Ï¶ê[0].ÛØà¦=8
//            l_params[0].setMarginMaintenanceRate(8);
//            
//            //* ­§Ï¶ê[0].ÇØoßú=10
//            l_params[0].setAdditionalMarginAccruedDays(10);
//
//            //* 2)ÉÉXvt@@Xe[uL
//            //* branchID=1001
//            //* vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
//            //* vt@@X¼ÌAÔ = 1iÅèj value=50IL?
//            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
//
//            //* 3)branch\õêbranchID=1001,brachCode=222,IL?
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(1001);
//            l_branchParams.setBranchCode("222");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            //* 4jmainAccount\õêAccountId=1000001,AccountCode=1234567C\¦Úq¼=gxuhongweihIL?
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1000001L);
//            l_mainAccountParams.setAccountCode("1234567");
//            l_mainAccountParams.setFamilyName("xuhongwei");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //* 5)Á¿\õêÁ¿ID=200001,Á¿R[h=200002,Á¿¼=1IL? 
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
//            //* 6jsê\õêsêID=444444,sêR[h=12IL?" 
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(444444);
//            l_marketParams.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
//                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params);
//
//            //<----------------------ÊmF------------------------->
//            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //Xcode=222
//            assertEquals("222", l_unit[0].branchCode);
//
//            //ûÀcode=123456
//            assertEquals("123456", l_unit[0].accountCode);
//            
//            //Úq¼=gxuhongweih
//            assertEquals("xuhongwei", l_unit[0].accountName);
//            
//            //Á¿code=200002
//            assertEquals("200002", l_unit[0].productCode);
//            
//            //Á¿¼=1
//            assertEquals("1", l_unit[0].productName);
//
//            //sêcode=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //ûÀæª=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //æª=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //P¿=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //ãà=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //ÇØ­¶ú=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //ÙÏæª=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //ÙÏúÀl=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //¶=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //¶P¿=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //ú=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //­ú=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //ì¬ú=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //³F^ñ³Fú=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //³FóÔæª=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //³FÒR[h=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //¶G[RR[h=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //­§ÏR.­§ÏRæª=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //­§ÏR.ÛØà¦=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //­§ÏR.ÇØoßú=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //¶P¿æª=1
//            assertEquals("1", l_unit[0].orderPriceDiv);
//
//            //ãà´ßtO=true
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
     * "Xvt@@Xe[uL
     * XID = p[^.­§Ï¶êÌ0ÔÚÌvf.XID And
     * vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
     * vt@@X¼ÌAÔ = 1iÅèjIL?" "1)ÒÉ ­§Ï¶ê.size=1
     * ­§Ï¶ê[0].¶ID=10001
     * ­§Ï¶ê[0].XID=1001
     * ­§Ï¶ê[0].ûÀID=1000001
     * ­§Ï¶ê[0]..Á¿ID=200001
     * ­§Ï¶ê[0].sêID=444444
     * ­§Ï¶ê[0].Åæª=1
     * ­§Ï¶ê[0].æª=1
     * ­§Ï¶ê[0].=500
     * ­§Ï¶ê[0].P¿=100
     * ­§Ï¶ê[0].ãà=100
     * ­§Ï¶ê[0].ÇØ­¶ú=20070808
     * ­§Ï¶ê[0].ÙÏæª=1
     * ­§Ï¶ê[0].ÙÏúÀl=500
     * ­§Ï¶ê[0].¶=50
     * ­§Ï¶ê[0].wl=0
     * ­§Ï¶ê[0].ú=20080808
     * ­§Ï¶ê[0].­ú=20090909
     * ­§Ï¶ê[0].ì¬ú=20080810
     * ­§Ï¶ê[0].³F^ñ³Fú=20080811
     * ­§Ï¶ê[0].³FóÔæª=1
     * ­§Ï¶ê[0].³FÒR[h=1002
     * ­§Ï¶ê[0].¶G[RR[h=90001
     * ­§Ï¶ê[0].­§ÏRæª=1
     * ­§Ï¶ê[0].ÛØà¦=8
     * ­§Ï¶ê[0].ÇØoßú=10
     * 2)ÉÉXvt@@Xe[uL
     * branchID=1001
     * vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
     * vt@@X¼ÌAÔ = 1iÅèj value=50IL?
     * 3)branch\õêbranchID=1001,brachCode=222222,IL?
     * 4jmainAccount\õêAccountId=1000001,AccountCode=111111C\¦Úq¼=gxuhongweihIL?
     * 5)Á¿\õêÁ¿ID=200001,Á¿R[h=200002,Á¿¼=1IL?
     * 6jsê\õêsêID=444444,sêR[h=888888IL?"  
     * "û@@Ôñ?­§Ï¶ÆïîñIeÂ¢«?@@ºF
     * ID=10001
     * Xcode=222
     * ûÀcode=1234567
     * Úq¼=gxuhongweih
     * Á¿ID=200002
     * Á¿¼=1
     * sêcode=888888
     * ûÀæª=1
     * æª=1
     * =500
     * P¿=100
     * ãà=100
     * ÇØ­¶ú=20070808
     * ÙÏæª=1
     * ÙÏúÀl=500
     * ¶=50
     * wl=0
     * ú=20080808
     * ­ú=20090909
     * ì¬ú=20080810
     * ³F^ñ³Fú=20080811
     * ³FóÔæª=1
     * ³FÒR[h=1002
     * ¶G[RR[h=90001
     * ­§ÏR.­§ÏRæª=1
     * ­§ÏR.ÛØà¦=8
     * ­§ÏR.ÇØoßú=10
     * ¶P¿æª=0
     * ãà´ßtO=true"
     */
    public void testcreateForcedSettleTemporaryOrderUnitList_case004()
    {
        final String STR_METHOD_NAME = ".testcreateForcedSettleTemporaryOrderUnitList_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdminEqForcedSettleOrderParams[] l_params = new AdminEqForcedSettleOrderParams[1];
            l_params[0] = new AdminEqForcedSettleOrderParams();

            //<----------------------Éõ------------------------->
            //* ­§Ï¶ê[0].¶ID=10001
            l_params[0].setOrderId(10001);

            //* ­§Ï¶ê[0].XID=1001
            l_params[0].setBranchId(1001);

            //* ­§Ï¶ê[0].ûÀID=1000001
            l_params[0].setAccountId(1000001L);
            
            l_params[0].setAccountCode("TT");

            //* ­§Ï¶ê[0]..Á¿ID=200001
            l_params[0].setProductId(200001L);
            
            l_params[0].setProductCode("LL");

            //* ­§Ï¶ê[0].sêID=444444
            l_params[0].setMarketId(444444L);
            
            //* ­§Ï¶ê[0].Åæª=1
            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
            
            //* ­§Ï¶ê[0].æª=1
            l_params[0].setContractType(ContractTypeEnum.LONG);
            
            //* ­§Ï¶ê[0].=500
            l_params[0].setContractQuantity(500);
            
            //* ­§Ï¶ê[0].P¿=100
            l_params[0].setContractPrice(100);
            
            //* ­§Ï¶ê[0].ãà=100
            l_params[0].setContractAmount(10);
            
            //* ­§Ï¶ê[0].ÇØ­¶ú=20070808
            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].ÙÏæª=1
            l_params[0].setRepaymentType("1");
            
            //* ­§Ï¶ê[0].ÙÏúÀl=500
            l_params[0].setRepaymentNum(500);
            
            //* ­§Ï¶ê[0].¶=50
            l_params[0].setQuantity(50);
            
            //* ­§Ï¶ê[0].wl=0
            l_params[0].setLimitPrice(0);
            
            //* ­§Ï¶ê[0].ú=20080808
            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].­ú=20090909
            l_params[0].setBizDate("20090909");
            
            //* ­§Ï¶ê[0].ì¬ú=20080810
            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].³F^ñ³Fú=20080811
            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].³FóÔæª=1
            l_params[0].setApproveStatusType("1");
            
            //* ­§Ï¶ê[0].³FÒR[h=1002
            l_params[0].setApproverCode("1002");
            
            //* ­§Ï¶ê[0].¶G[RR[h=90001
            l_params[0].setErrorReasonCode("90001");

            //* ­§Ï¶ê[0].­§ÏRæª=1
            l_params[0].setForcedSettleReasonType("1");
            
            //* ­§Ï¶ê[0].ÛØà¦=8
            l_params[0].setMarginMaintenanceRate(8);
            
            //* ­§Ï¶ê[0].ÇØoßú=10
            l_params[0].setAdditionalMarginAccruedDays(10);
            
            l_params[0].setForcedSettleReasonType("0");

            //* 2)ÉÉXvt@@Xe[uL
            //* branchID=1001
            //* vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
            //* vt@@X¼ÌAÔ = 1iÅèj value=50IL?
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("50");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            //* 3)branch\õêbranchID=1001,brachCode=222,IL?
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            l_branchParams.setBranchCode("222");
            TestDBUtility.insertWithDel(l_branchParams);

            //* 4jmainAccount\õêAccountId=1000001,AccountCode=1234567C\¦Úq¼=gxuhongweihIL?
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1000001L);
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setFamilyName("xuhongwei");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 5)Á¿\õêÁ¿ID=200001,Á¿R[h=200002,Á¿¼=1IL? 
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(200001);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(200001);
            l_eqtypeProductParams.setProductCode("200002");
            l_eqtypeProductParams.setStandardName("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //* 6jsê\õêsêID=444444,sêR[h=12IL?" 
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(444444);
            l_marketParams.setMarketCode("12");
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params, null);

            //<----------------------ÊmF------------------------->
            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //Xcode=222
//            assertEquals("222", l_unit[0].branchCode);
//
            //ûÀcode=123456
            assertEquals("TT", l_unit[0].accountCode);
            
            //Úq¼=gxuhongweih
//            assertEquals("xuhongwei", l_unit[0].accountName);
            
            //Á¿code=200002
            assertEquals("LL", l_unit[0].productCode);
            
            //Á¿¼=1
            assertEquals("1", l_unit[0].productName);
//
//            //sêcode=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //ûÀæª=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //æª=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //P¿=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //ãà=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //ÇØ­¶ú=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //ÙÏæª=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //ÙÏúÀl=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //¶=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //¶P¿=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //ú=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //­ú=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //ì¬ú=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //³F^ñ³Fú=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //³FóÔæª=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //³FÒR[h=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //¶G[RR[h=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //­§ÏR.­§ÏRæª=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //­§ÏR.ÛØà¦=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //­§ÏR.ÇØoßú=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //¶P¿æª=0
//            assertEquals("0", l_unit[0].orderPriceDiv);
//
//            //ãà´ßtO=true
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

            //<----------------------Éõ------------------------->
            //* ­§Ï¶ê[0].¶ID=10001
            l_params[0].setOrderId(10001);

            //* ­§Ï¶ê[0].XID=1001
            l_params[0].setBranchId(1001);

            //* ­§Ï¶ê[0].ûÀID=1000001
            l_params[0].setAccountId(1000001L);
            
//            l_params[0].setAccountCode("TT");

            //* ­§Ï¶ê[0]..Á¿ID=200001
            l_params[0].setProductId(200001L);
            
//            l_params[0].setProductCode("LL");

            //* ­§Ï¶ê[0].sêID=444444
            l_params[0].setMarketId(444444L);
            
            //* ­§Ï¶ê[0].Åæª=1
            l_params[0].setTaxType(TaxTypeEnum.NORMAL);
            
            //* ­§Ï¶ê[0].æª=1
            l_params[0].setContractType(ContractTypeEnum.LONG);
            
            //* ­§Ï¶ê[0].=500
            l_params[0].setContractQuantity(500);
            
            //* ­§Ï¶ê[0].P¿=100
            l_params[0].setContractPrice(100);
            
            //* ­§Ï¶ê[0].ãà=100
            l_params[0].setContractAmount(10);
            
            //* ­§Ï¶ê[0].ÇØ­¶ú=20070808
            l_params[0].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].ÙÏæª=1
            l_params[0].setRepaymentType("1");
            
            //* ­§Ï¶ê[0].ÙÏúÀl=500
            l_params[0].setRepaymentNum(500);
            
            //* ­§Ï¶ê[0].¶=50
            l_params[0].setQuantity(50);
            
            //* ­§Ï¶ê[0].wl=0
            l_params[0].setLimitPrice(0);
            
            //* ­§Ï¶ê[0].ú=20080808
            l_params[0].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].­ú=20090909
            l_params[0].setBizDate("20090909");
            
            //* ­§Ï¶ê[0].ì¬ú=20080810
            l_params[0].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].³F^ñ³Fú=20080811
            l_params[0].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            //* ­§Ï¶ê[0].³FóÔæª=1
            l_params[0].setApproveStatusType("1");
            
            //* ­§Ï¶ê[0].³FÒR[h=1002
            l_params[0].setApproverCode("1002");
            
            //* ­§Ï¶ê[0].¶G[RR[h=90001
            l_params[0].setErrorReasonCode("90001");

            //* ­§Ï¶ê[0].­§ÏRæª=1
            l_params[0].setForcedSettleReasonType("1");
            
            //* ­§Ï¶ê[0].ÛØà¦=8
            l_params[0].setMarginMaintenanceRate(8);
            
            //* ­§Ï¶ê[0].ÇØoßú=10
            l_params[0].setAdditionalMarginAccruedDays(10);
            
            l_params[0].setForcedSettleReasonType("0");

            //* 2)ÉÉXvt@@Xe[uL
            //* branchID=1001
            //* vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
            //* vt@@X¼ÌAÔ = 1iÅèj value=50IL?
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("50");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            //* 3)branch\õêbranchID=1001,brachCode=222,IL?
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            l_branchParams.setBranchCode("222");
            TestDBUtility.insertWithDel(l_branchParams);

            //* 4jmainAccount\õêAccountId=1000001,AccountCode=1234567C\¦Úq¼=gxuhongweihIL?
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1000001L);
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setFamilyName("xuhongwei");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 5)Á¿\õêÁ¿ID=200001,Á¿R[h=200002,Á¿¼=1IL? 
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(200001);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(200001);
            l_eqtypeProductParams.setProductCode("200002");
            l_eqtypeProductParams.setStandardName("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //* 6jsê\õêsêID=444444,sêR[h=12IL?" 
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(444444);
            l_marketParams.setMarketCode("12");
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params, null);

            //<----------------------ÊmF------------------------->
            //ID=10001
//            assertEquals("10001", l_unit[0].id);
//
//            //Xcode=222
//            assertEquals("222", l_unit[0].branchCode);
//
            //ûÀcode
            assertNull(l_unit[0].accountCode);
            
            //Úq¼=gxuhongweih
//            assertEquals("xuhongwei", l_unit[0].accountName);
            
            //Á¿code
            assertNull(l_unit[0].productCode);
            
            //Á¿¼=1
            assertEquals("1", l_unit[0].productName);
//
//            //sêcode=12
//            assertEquals("12", l_unit[0].marketCode);
//
//            //ûÀæª=1
//            assertEquals("0", l_unit[0].taxType);
//            
//            //æª=1
//            assertEquals("1", l_unit[0].contractType);
//            
//            //=500
//            assertEquals("500", l_unit[0].contractQuantity);
//            
//            //P¿=100
//            assertEquals("100", l_unit[0].contractPrice);
//            
//            //ãà=100
//            assertEquals("10", l_unit[0].contractExecPrice);
//            
//            //ÇØ­¶ú=20070808
//            int l_intComResult =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                    l_unit[0].additionalOccurredDate);
//            assertEquals(0, l_intComResult);
//
//            //ÙÏæª=1
//            assertEquals("1", l_unit[0].repaymentDiv);
//            
//            //ÙÏúÀl=500
//            assertEquals("500", l_unit[0].repaymentTimeLimit);
//            
//            //¶=50
//            assertEquals("50", l_unit[0].orderQuantity);
//            
//            //¶P¿=0
////            assertEquals(0, l_unit[0].orderPrice());
//
//            //ú=20080808
//            int l_intComResult1 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                    l_unit[0].openDate);
//            assertEquals(0, l_intComResult1);
//            
//            //­ú=20090909
//            int l_intComResult2 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                    l_unit[0].orderBizDate);
//            assertEquals(0, l_intComResult2);
//            
//            //ì¬ú=20080810
//            int l_intComResult3 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                    l_unit[0].createDate);
//            assertEquals(0, l_intComResult3);
//
//            //³F^ñ³Fú=20080811
//            int l_intComResult4 =
//                WEB3DateUtility.compareToDay(
//                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                    l_unit[0].approveDate);
//            assertEquals(0, l_intComResult4);
//
//            //³FóÔæª=1
//            assertEquals("1", l_unit[0].approveState);
//
//            //³FÒR[h=1002
//            assertEquals("1002", l_unit[0].checker);
//
//            //¶G[RR[h=90001
//            assertEquals("90001", l_unit[0].errorReason);
//
//            //­§ÏR.­§ÏRæª=1
//            assertEquals("1", l_unit[0].forcedSettleReason.forcedSettleReason);
//
//            //­§ÏR.ÛØà¦=8
//            assertEquals("8", l_unit[0].forcedSettleReason.marginDepositRate);
//
//            //­§ÏR.ÇØoßú=10
//            assertEquals("10", l_unit[0].forcedSettleReason.additionalElapsedDays);
//
//            //¶P¿æª=0
//            assertEquals("0", l_unit[0].orderPriceDiv);
//
//            //ãà´ßtO=true
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
//     * "Xvt@@Xe[uL
//     * XID = p[^.­§Ï¶êÌ0ÔÚÌvf.XID And
//     * vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
//     * vt@@X¼ÌAÔ = 1iÅèjIL?" "1)ÒÉ ­§Ï¶ê.size=3
//     * ïéàe@@case62:dO"   û@@Ôñ?­§Ï¶ÆïîñIeÂ¢«?length=3
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
//                //<----------------------Éõ------------------------->
//                //* ­§Ï¶ê[i].¶ID=10001
//                l_params[i].setOrderId(10001);
//
//                //* ­§Ï¶ê[i].XID=1001
//                l_params[i].setBranchId(1001);
//
//                //* ­§Ï¶ê[i].ûÀID=1000001
//                l_params[i].setAccountId(1000001L);
//
//                //* ­§Ï¶ê[i]..Á¿ID=200001
//                l_params[i].setProductId(200001L);
//
//                //* ­§Ï¶ê[i].sêID=444444
//                l_params[i].setMarketId(444444L);
//                
//                //* ­§Ï¶ê[i].Åæª=1
//                l_params[i].setTaxType(TaxTypeEnum.NORMAL);
//                
//                //* ­§Ï¶ê[i].æª=1
//                l_params[i].setContractType(ContractTypeEnum.LONG);
//                
//                //* ­§Ï¶ê[i].=500
//                l_params[i].setContractQuantity(500);
//                
//                //* ­§Ï¶ê[i].P¿=100
//                l_params[i].setContractPrice(100);
//                
//                if (i == 0)
//                {
//                    //* ­§Ï¶ê[i].ãà=10
//                    l_params[i].setContractAmount(100);
//                }
//                else if (i == 1)
//                {
//                    //* ­§Ï¶ê[i].ãà=10
//                    l_params[i].setContractAmount(10);
//                }
//                else if (i == 2)
//                {
//                    //* ­§Ï¶ê[i].ãà=50
//                    l_params[i].setContractAmount(50);
//                }
//                //* ­§Ï¶ê[i].ÇØ­¶ú=20070808
//                l_params[i].setAdditionalMarginDate(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
//                
//                //* ­§Ï¶ê[i].ÙÏæª=1
//                l_params[i].setRepaymentType("1");
//                
//                //* ­§Ï¶ê[i].ÙÏúÀl=500
//                l_params[i].setRepaymentNum(500);
//                
//                //* ­§Ï¶ê[i].¶=50
//                l_params[i].setQuantity(50);
//                
//                //* ­§Ï¶ê[i].wl=0
//                l_params[i].setLimitPrice(0);
//                
//                //* ­§Ï¶ê[i].ú=20080808
//                l_params[i].setOpenDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                
//                //* ­§Ï¶ê[i].­ú=20090909
//                l_params[i].setBizDate("20090909");
//                
//                //* ­§Ï¶ê[i].ì¬ú=20080810
//                l_params[i].setCreatedTimestamp(WEB3DateUtility.getDate("20080810", "yyyyMMdd"));
//                
//                //* ­§Ï¶ê[i].³F^ñ³Fú=20080811
//                l_params[i].setApproveTimestamp(WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
//                
//                //* ­§Ï¶ê[i].³FóÔæª=1
//                l_params[i].setApproveStatusType("1");
//                
//                //* ­§Ï¶ê[i].³FÒR[h=1002
//                l_params[i].setApproverCode("1002");
//                
//                //* ­§Ï¶ê[i].¶G[RR[h=90001
//                l_params[i].setErrorReasonCode("90001");
//
//                //* ­§Ï¶ê[i].­§ÏRæª=1
//                l_params[i].setForcedSettleReasonType("1");
//                
//                //* ­§Ï¶ê[i].ÛØà¦=8
//                l_params[i].setMarginMaintenanceRate(8);
//                
//                //* ­§Ï¶ê[i].ÇØoßú=10
//                l_params[i].setAdditionalMarginAccruedDays(10);
//            }
//
//
//            //* 2)ÉÉXvt@@Xe[uL
//            //* branchID=1001
//            //* vt@@X¼ = Mpæø­§Ïîãài­²\¦»èpj And
//            //* vt@@X¼ÌAÔ = 1iÅèj value=50IL?
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            l_branchPreferencesParams.setBranchId(1001);
//            l_branchPreferencesParams.setName("margin.forcedsettleorder.basecontractamount");
//            l_branchPreferencesParams.setNameSerialNo(1);
//            l_branchPreferencesParams.setValue("50");
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
//
//            //* 3)branch\õêbranchID=1001,brachCode=222,IL?
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(1001);
//            l_branchParams.setBranchCode("222");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            //* 4jmainAccount\õêAccountId=1000001,AccountCode=1234567C\¦Úq¼=gxuhongweihIL?
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1000001L);
//            l_mainAccountParams.setAccountCode("1234567");
//            l_mainAccountParams.setFamilyName("xuhongwei");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //* 5)Á¿\õêÁ¿ID=200001,Á¿R[h=200002,Á¿¼=1IL? 
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
//            //* 6jsê\õêsêID=444444,sêR[h=12IL?" 
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(444444);
//            l_marketParams.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
//                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_params);
//
//            //<----------------------ÊmF------------------------->
//            for (int i = 0; i < 3; i++)
//            {
//                //ID=10001
//                assertEquals("10001", l_unit[i].id);
//
//                //Xcode=222
//                assertEquals("222", l_unit[i].branchCode);
//
//                //ûÀcode=123456
//                assertEquals("123456", l_unit[i].accountCode);
//
//                //Úq¼=gxuhongweih
//                assertEquals("xuhongwei", l_unit[i].accountName);
//
//                //Á¿code=200002
//                assertEquals("200002", l_unit[i].productCode);
//
//                //Á¿¼=1
//                assertEquals("1", l_unit[i].productName);
//
//                //sêcode=12
//                assertEquals("12", l_unit[i].marketCode);
//
//                //ûÀæª=1
//                assertEquals("0", l_unit[i].taxType);
//
//                //æª=1
//                assertEquals("1", l_unit[i].contractType);
//
//                //=500
//                assertEquals("500", l_unit[i].contractQuantity);
//
//                //P¿=100
//                assertEquals("100", l_unit[i].contractPrice);
//
//                if (i == 0)
//                {
//                    //ãà=10
//                    assertEquals("100", l_unit[i].contractExecPrice); 
//                }
//                else if (i == 1)
//                {
//                    //ãà=10
//                    assertEquals("10", l_unit[i].contractExecPrice);   
//                }
//                else if (i == 2)
//                {
//                    //ãà=10
//                    assertEquals("50", l_unit[i].contractExecPrice);
//                }
//
//                //ÇØ­¶ú=20070808
//                int l_intComResult =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20070808", "yyyyMMdd"),
//                        l_unit[i].additionalOccurredDate);
//                assertEquals(0, l_intComResult);
//
//                //ÙÏæª=1
//                assertEquals("1", l_unit[i].repaymentDiv);
//                
//                //ÙÏúÀl=500
//                assertEquals("500", l_unit[i].repaymentTimeLimit);
//                
//                //¶=50
//                assertEquals("50", l_unit[i].orderQuantity);
//                
//                //¶P¿=0
////                assertEquals(0, l_unit[i].orderPrice());
//
//                //ú=20080808
//                int l_intComResult1 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080808", "yyyyMMdd"),
//                        l_unit[i].openDate);
//                assertEquals(0, l_intComResult1);
//                
//                //­ú=20090909
//                int l_intComResult2 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20090909", "yyyyMMdd"),
//                        l_unit[i].orderBizDate);
//                assertEquals(0, l_intComResult2);
//                
//                //ì¬ú=20080810
//                int l_intComResult3 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080810", "yyyyMMdd"),
//                        l_unit[i].createDate);
//                assertEquals(0, l_intComResult3);
//
//                //³F^ñ³Fú=20080811
//                int l_intComResult4 =
//                    WEB3DateUtility.compareToDay(
//                        WEB3DateUtility.getDate("20080811", "yyyyMMdd"),
//                        l_unit[i].approveDate);
//                assertEquals(0, l_intComResult4);
//
//                //³FóÔæª=1
//                assertEquals("1", l_unit[i].approveState);
//
//                //³FÒR[h=1002
//                assertEquals("1002", l_unit[i].checker);
//
//                //¶G[RR[h=90001
//                assertEquals("90001", l_unit[i].errorReason);
//
//                //­§ÏR.­§ÏRæª=1
//                assertEquals("1", l_unit[i].forcedSettleReason.forcedSettleReason);
//
//                //­§ÏR.ÛØà¦=8
//                assertEquals("8", l_unit[i].forcedSettleReason.marginDepositRate);
//
//                //­§ÏR.ÇØoßú=10
//                assertEquals("10", l_unit[i].forcedSettleReason.additionalElapsedDays);
//
//                //¶P¿æª=0
//                assertEquals("0", l_unit[i].orderPriceDiv);
//
//                if (i == 0)
//                {
//                    //ãà´ßtO=true
//                    assertEquals(true, l_unit[i].contractExecPriceOverFlag);    
//                }
//                else if (i == 1)
//                {
//                    //ãà´ßtO=false
//                    assertEquals(false, l_unit[i].contractExecPriceOverFlag);    
//                }
//                else if (i == 2)
//                {
//                    //ãà´ßtO=true
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
     * "1j¶IDê.size×0
     * ïéàeF
     * 2j
     * p[^.ûÀIDFrom == 2001
     * p[^.ûÀIDTo == 2008"
     * ÔñÊ× throws SYSTEM_ERROR_80017
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
     * "1j¶IDê.size×1
     * ïéàeF
     * 1001
     * 2j
     * p[^.ûÀIDFrom == 2001
     * p[^.ûÀIDTo == 2008"
     * ­§Ï¶Row\LõÉ
     * ÔñÊ×null
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
     * "1j¶IDê.size×3
     * ïéàeF
     * 1001
     * 1002
     * 1003
     * 1004
     * 2j
     * p[^.ûÀIDFrom == 2001
     * p[^.ûÀIDTo == 2008"   "­§Ï¶Row\õÉ@@ºF
     * Row1:
     *   .ûÀID=2006
     *   ¶ID==1001
     *   Row2:
     *   .ûÀID=2007
     *   ¶ID==1002
     *   Row3:
     *   .ûÀID=2006
     *   ¶ID==1003
     *   Row4:
     *   .ûÀID=2008
     *   ¶ID==1004
     *   Row5:
     *   .ûÀID=2009
     *   ¶ID==1005
     *   û@@ÔñÉÉI:
     *   Row1:
     *   Row2:
     *   Row3:
     *   Row:4
     *   lL"
     */
    public void testGetForcedSettleOrderList_case002()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* "1j¶IDê.size×3
        //* ïéàeF
        //* 1001
        //* 1002
        //* 1003
        //* 1004
        String[] l_strOrderIdList = new String[4];
        l_strOrderIdList[0] = "1001";
        l_strOrderIdList[1] = "1002";
        l_strOrderIdList[2] = "1003";
        l_strOrderIdList[3] = "1004";

        //* 2j
        //* p[^.ûÀIDFrom == 2001
        //* p[^.ûÀIDTo == 2008"
        WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_sortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit();
        l_sortsKeys[0].keyItem = "openDate";
        l_sortsKeys[0].ascDesc = "A";
        Long l_lngAccountIdFrom = new Long(2001);
        Long l_lngAccountIdTo = new Long(2008);
        try
        {

            //"­§Ï¶Row\õÉ@@ºF
            //* Row1:
            //    *   .ûÀID=2005
            //    *   ¶ID==1001
            //    *   ú = 20080111
            // Row2:
            //    *   .ûÀID=2007
            //    *   ¶ID==1002
            //    *   ú = 20070111
            // Row3:
            //    *   .ûÀID=2006
            //    *   ¶ID==1003
            //    *   ú = 20060111
            // Row4:
            //    *   .ûÀID=2008
            //    *   ¶ID==1004
            //    *   ú = 20090111
            // Row5:
            //    *   .ûÀID=2009
            //    *   ¶ID==1005
            //    *   ú = 20010111
            //<-------------------ªOÉõ------------------------->
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

            //<-------------------ÔñÊmF------------------------->
            //*   û@@ÔñÉÉI:
            //ªúI¡r
            //*   Row3:
            //*   Row2:
            //*   Row1:
            //*   Row:4
            //*   lL"
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
     * "1j¶IDê.size×3
     * ïéàeF
     * 1001
     * 1002
     * 1003
     * 1004
     * 1005
     * 2j
     * p[^.ûÀIDFrom == null
     * p[^.ûÀIDTo == 2008"   "­§Ï¶Row\õÉ@@ºF
     * Row1:
     *   .ûÀID=2006
     *   ¶ID==1001
     *   Row2:
     *   .ûÀID=2007
     *   ¶ID==1002
     *   Row3:
     *   .ûÀID=2006
     *   ¶ID==1003
     *   Row4:
     *   .ûÀID=2008
     *   ¶ID==1004
     *   Row5:
     *   .ûÀID=2009
     *   ¶ID==1005
     *   û@@ÔñÉÉI:
     *   Row5:
     *   Row3:
     *   Row2:
     *   Row1:
     *   Row:4
     *   lL"
     */
    public void testGetForcedSettleOrderList_case003()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* "1j¶IDê.size×3
        //* ïéàeF
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

            //* 2j
            //* p[^.ûÀIDFrom == 2001
            //* p[^.ûÀIDTo == 2008"
            WEB3AdminForcedSettleSortKeyUnit[] l_sortsKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
            l_sortsKeys[0] = new WEB3AdminForcedSettleSortKeyUnit();
            l_sortsKeys[0].keyItem = "openDate";
            l_sortsKeys[0].ascDesc = "A";
            Long l_lngAccountIdFrom = null;
            Long l_lngAccountIdTo = new Long(2008);
            try
            {

                //"­§Ï¶Row\õÉ@@ºF
                //* Row1:
                //    *   .ûÀID=2005
                //    *   ¶ID==1001
                //    *   ú = 20080111
                // Row2:
                //    *   .ûÀID=2007
                //    *   ¶ID==1002
                //    *   ú = 20070111
                // Row3:
                //    *   .ûÀID=2006
                //    *   ¶ID==1003
                //    *   ú = 20060111
                // Row4:
                //    *   .ûÀID=2008
                //    *   ¶ID==1004
                //    *   ú = 20090111
                // Row5:
                //    *   .ûÀID=2009
                //    *   ¶ID==1005
                //    *   ú = 20010111
                //<-------------------ªOÉõ------------------------->
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

                //<-------------------ÔñÊmF------------------------->
                //*   û@@ÔñÉÉI:
                //ªúI¡r
                //*   Row5:
                //*   Row3:
                //*   Row2:
                //*   Row1:
                //*   Row:4
                //*   lL"
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
     * ÒÉØïÐ=nullIê
     * ÒÉØïÐ=null
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
     * ÇÒE­§Ï¼¶ÆïNGXgIuWFNg=nullIê
     * ÇÒE­§Ï¼¶ÆïNGXgIuWFNg=null
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
     * "XððõðÉÇÁ
     * getXIDê()Ìßèl = nullÌê
     * "   ´óÉÉBranch\
     * "uðÉY·éf[^ª¶ÝµÈ¢BvÌÆ±G[ðX[·é
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
     * "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
     * 2jp[^.NGXgf[^.­ú= nullÌê
     * 3jp[^.NGXgf[^.ÚqR[h = nullÌê
     * 4jp[^.NGXgf[^.Á¿R[h= nullÌê
     * 5jp[^.NGXgf[^.³FóÔ = nullÌê
     * 6jp[^.NGXgf[^.³FÒR[h= nullÌê
     * 7jp[^.NGXgf[^.ì¬úFrom = nullÌê
     * 8jp[^.NGXgf[^.ì¬úTo = nullÌê
     * 9jp[^.NGXgf[^.³FúFrom= nullÌê
     * 10jp[^.NGXgf[^.³FúTo = nullÌê
     * 11jp[^.NGXgf[^.­§ÏR= nullÌê
     * 12jp[^.NGXgf[^.Ïúú= nullÌê
     * 13jp[^.NGXgf[^.¶G[RR[h = nullÌê
     * 14jp[^.NGXgf[^.³Fæª = nullÌê
     * 15jp[^.NGXgf[^.ûÀæª = nullÌê
     * 16jp[^.NGXgf[^.sêR[h = nullÌê
     * mFû@@Ôñ?"
     * "­§Ï¶Row\õ_
     * branchID=1001
     * branchID=1002
     * IÉ
     * getBranchIdijû@@Ôñ {1001}"
     * ­§Ï¶Row\êbranchID×1001IÉíÔñ
     */
    public void testGetForcedSettleOrderList2_case004()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú= nullÌê
            //* 3jp[^.NGXgf[^.ÚqR[h = nullÌê
            //* 4jp[^.NGXgf[^.Á¿R[h= nullÌê
            //* 5jp[^.NGXgf[^.³FóÔ = nullÌê
            //* 6jp[^.NGXgf[^.³FÒR[h= nullÌê
            //* 7jp[^.NGXgf[^.ì¬úFrom = nullÌê
            //* 8jp[^.NGXgf[^.ì¬úTo = nullÌê
            //* 9jp[^.NGXgf[^.³FúFrom= nullÌê
            //* 10jp[^.NGXgf[^.³FúTo = nullÌê
            //* 11jp[^.NGXgf[^.­§ÏR= nullÌê
            //* 12jp[^.NGXgf[^.Ïúú= nullÌê
            //* 13jp[^.NGXgf[^.¶G[RR[h = nullÌê
            //* 14jp[^.NGXgf[^.³Fæª = nullÌê
            //* 15jp[^.NGXgf[^.ûÀæª = nullÌê
            //* 16jp[^.NGXgf[^.sêR[h = nullÌê
            l_referenceRequest.marketCode = "11";

            //­§Ï¶RowõÉ
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
            
            //<-------------------ÊmF-------------------------->
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
     * "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
     * 2jp[^.NGXgf[^.­ú= nullÌê
     * 3jp[^.NGXgf[^.ÚqR[h = nullÌê
     * 4jp[^.NGXgf[^.Á¿R[h= nullÌê
     * 5jp[^.NGXgf[^.³FóÔ = nullÌê
     * 6jp[^.NGXgf[^.³FÒR[h= nullÌê
     * 7jp[^.NGXgf[^.ì¬úFrom = nullÌê
     * 8jp[^.NGXgf[^.ì¬úTo = nullÌê
     * 9jp[^.NGXgf[^.³FúFrom= nullÌê
     * 10jp[^.NGXgf[^.³FúTo = nullÌê
     * 11jp[^.NGXgf[^.­§ÏR= nullÌê
     * 12jp[^.NGXgf[^.Ïúú= nullÌê
     * 13jp[^.NGXgf[^.¶G[RR[h = nullÌê
     * 14jp[^.NGXgf[^.³Fæª = nullÌê
     * 15jp[^.NGXgf[^.ûÀæª = nullÌê
     * 16jp[^.NGXgf[^.sêR[h != nullÌê
     * mFû@@Ôñ?"
     * "­§Ï¶Row\õ_
     * branchID=1001
     * branchID=1002
     * IÉ
     * getBranchIdijû@@Ôñ {1001}"
     * û@@Ôñnull
     */
    public void testGetForcedSettleOrderList2_case005()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú= nullÌê
            //* 3jp[^.NGXgf[^.ÚqR[h = nullÌê
            //* 4jp[^.NGXgf[^.Á¿R[h= nullÌê
            //* 5jp[^.NGXgf[^.³FóÔ = nullÌê
            //* 6jp[^.NGXgf[^.³FÒR[h= nullÌê
            //* 7jp[^.NGXgf[^.ì¬úFrom = nullÌê
            //* 8jp[^.NGXgf[^.ì¬úTo = nullÌê
            //* 9jp[^.NGXgf[^.³FúFrom= nullÌê
            //* 10jp[^.NGXgf[^.³FúTo = nullÌê
            //* 11jp[^.NGXgf[^.­§ÏR= nullÌê
            //* 12jp[^.NGXgf[^.Ïúú= nullÌê
            //* 13jp[^.NGXgf[^.¶G[RR[h = nullÌê
            //* 14jp[^.NGXgf[^.³Fæª = nullÌê
            //* 15jp[^.NGXgf[^.ûÀæª = nullÌê
            //* 16jp[^.NGXgf[^.sêR[h = nullÌê
            l_referenceRequest.marketCode = "22";

            //­§Ï¶RowõÉ
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
            
            //<-------------------ÊmF-------------------------->
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
     * "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
     * 2jp[^.NGXgf[^.­ú!= nullÌê
     * 3jp[^.NGXgf[^.ÚqR[h != nullÌê
     * 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
     * 5jp[^.NGXgf[^.³FóÔ != nullÌê
     * 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
     * 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
     * 8jp[^.NGXgf[^.ì¬úTo != nullÌê
     * 9jp[^.NGXgf[^.³FúFrom!= nullÌê
     * 10jp[^.NGXgf[^.³FúTo != nullÌê
     * 11jp[^.NGXgf[^.­§ÏR!= nullÌê
     * 12jp[^.NGXgf[^.Ïúú!= nullÌê
     * 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
     * 14jp[^.NGXgf[^.³Fæª != nullÌê
     * 15jp[^.NGXgf[^.ûÀæª != nullÌê
     * 16jp[^.NGXgf[^.sêR[h != nullÌê
     * mFû@@Ôñ?"
     * "­§Ï¶Row\õ½
     * branchID=1001
     * branchID=1002
     * branchID=1003
     * branchID=1004
     * branchID=1005
     * IÉ
     * getBranchIdijû@@Ôñ {1001}"
     * ­§Ï¶Row\êbranchID×1001IÉíÔñ
     */
    public void testGetForcedSettleOrderList2_case006()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //­§Ï¶RowõÉ
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
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú!= nullÌê
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3jp[^.NGXgf[^.ÚqR[h != nullÌê
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
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

            //* 5jp[^.NGXgf[^.³FóÔ != nullÌê
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8jp[^.NGXgf[^.ì¬úTo != nullÌê
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9jp[^.NGXgf[^.³FúFrom!= nullÌê
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10jp[^.NGXgf[^.³FúTo != nullÌê
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11jp[^.NGXgf[^.­§ÏR!= nullÌê
            l_referenceRequest.forcedSettleReason = "9";
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("9");

            //* 12jp[^.NGXgf[^.Ïúú!= nullÌê
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14jp[^.NGXgf[^.³Fæª != nullÌê
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15jp[^.NGXgf[^.ûÀæª != nullÌê
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16jp[^.NGXgf[^.sêR[h != nullÌê
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

            //<-------------------ÊmF-------------------------->
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
     * p[^.NGXgf[^.­§ÏR != nullÌêA
     * p[^.NGXgf[^.­§ÏR == "ÇØ(æê)úú´ß"Ìê 
     * õð¶ñ += " and forced_settle_reason_type = in(? ,?)"
     * f[^ReiÉ"ÛØàÛ¦êiICJnOEyxj
     */
    public void testGetForcedSettleOrderList2_case007()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //­§Ï¶RowõÉ
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
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú!= nullÌê
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3jp[^.NGXgf[^.ÚqR[h != nullÌê
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
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

            //* 5jp[^.NGXgf[^.³FóÔ != nullÌê
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8jp[^.NGXgf[^.ì¬úTo != nullÌê
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9jp[^.NGXgf[^.³FúFrom!= nullÌê
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10jp[^.NGXgf[^.³FúTo != nullÌê
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11jp[^.NGXgf[^.­§ÏR!= nullÌê
            l_referenceRequest.forcedSettleReason = "90";
            //ÇØ(æê)úú´ß
            //ÛØàÛ¦êiICJnOEyxj
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS);
            

            //* 12jp[^.NGXgf[^.Ïúú!= nullÌê
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14jp[^.NGXgf[^.³Fæª != nullÌê
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15jp[^.NGXgf[^.ûÀæª != nullÌê
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16jp[^.NGXgf[^.sêR[h != nullÌê
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

            //<-------------------ÊmF-------------------------->
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
     * p[^.NGXgf[^.­§ÏR != nullÌêA
     * p[^.NGXgf[^.­§ÏR == "ÇØ(æê)úú´ß"Ìê 
     * õð¶ñ += " and forced_settle_reason_type = in(? ,?)"
     * f[^ReiÉ"ÛØàÛ¦êiICJnOEdxj" 
     */
    public void testGetForcedSettleOrderList2_case008()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //­§Ï¶RowõÉ
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
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú!= nullÌê
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3jp[^.NGXgf[^.ÚqR[h != nullÌê
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
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

            //* 5jp[^.NGXgf[^.³FóÔ != nullÌê
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8jp[^.NGXgf[^.ì¬úTo != nullÌê
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9jp[^.NGXgf[^.³FúFrom!= nullÌê
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10jp[^.NGXgf[^.³FúTo != nullÌê
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11jp[^.NGXgf[^.­§ÏR!= nullÌê
            l_referenceRequest.forcedSettleReason = "90";
            //ÇØ(æê)úú´ß
            //ÛØàÛ¦êiICJnOEdxj
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS);
            

            //* 12jp[^.NGXgf[^.Ïúú!= nullÌê
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14jp[^.NGXgf[^.³Fæª != nullÌê
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15jp[^.NGXgf[^.ûÀæª != nullÌê
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16jp[^.NGXgf[^.sêR[h != nullÌê
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

            //<-------------------ÊmF-------------------------->
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
     * p[^.NGXgf[^.­§ÏR != nullÌêA
     * p[^.NGXgf[^.­§ÏR == "ÇØ(æñ)úú´ß"Ìê 
     * õð¶ñ += " and forced_settle_reason_type = in(? ,?)"
     * f[^ReiÉ"ÛØàÛ¦êiêÔj" 
     */
    public void testGetForcedSettleOrderList2_case009()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //­§Ï¶RowõÉ
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
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú!= nullÌê
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3jp[^.NGXgf[^.ÚqR[h != nullÌê
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
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

            //* 5jp[^.NGXgf[^.³FóÔ != nullÌê
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8jp[^.NGXgf[^.ì¬úTo != nullÌê
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9jp[^.NGXgf[^.³FúFrom!= nullÌê
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10jp[^.NGXgf[^.³FúTo != nullÌê
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11jp[^.NGXgf[^.­§ÏR!= nullÌê
            l_referenceRequest.forcedSettleReason = "91";
            //ÇØ(æñ)úú´ß
            //"ÛØàÛ¦êiêÔj"
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET);
            

            //* 12jp[^.NGXgf[^.Ïúú!= nullÌê
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14jp[^.NGXgf[^.³Fæª != nullÌê
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15jp[^.NGXgf[^.ûÀæª != nullÌê
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16jp[^.NGXgf[^.sêR[h != nullÌê
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

            //<-------------------ÊmF-------------------------->
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
     * p[^.NGXgf[^.­§ÏR != nullÌêA
     * p[^.NGXgf[^.­§ÏR == "ÇØ(æñ)úú´ß"Ìê 
     * õð¶ñ += " and forced_settle_reason_type = in(? ,?)"
     * f[^ReiÉ"ÛØàÛ¦êiICJnOE@@èj" 
     */
    public void testGetForcedSettleOrderList2_case010()
    {
        final String STR_METHOD_NAME = ".testGetForcedSettleOrderList2_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //­§Ï¶RowõÉ
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
            //<-------------------------Éõ---------------------------->
            //* "1jgetXIDê()Ìßèl I ·x× 1óÀ×1001
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
            //* 2jp[^.NGXgf[^.­ú!= nullÌê
            l_referenceRequest.orderBizDate = WEB3DateUtility.getDate("20080808","yyyyMMdd");
            l_eqtypeOrderUnitParams.setBizDate("20080808");

            //* 3jp[^.NGXgf[^.ÚqR[h != nullÌê
            l_referenceRequest.accountCode = "2203";

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountId(2005);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //* 4jp[^.NGXgf[^.Á¿R[h!= nullÌê
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

            //* 5jp[^.NGXgf[^.³FóÔ != nullÌê
            l_referenceRequest.approveState = null;
            l_eqtypeOrderUnitParams.setApproveStatusType("0"); 

            //* 6jp[^.NGXgf[^.³FÒR[h!= nullÌê
            l_referenceRequest.checker = "11";
            l_eqtypeOrderUnitParams.setApproverCode("11");

            //* 7jp[^.NGXgf[^.ì¬úFrom != nullÌê
            l_referenceRequest.createDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808000000","yyyyMMddHHmmss"));

            //* 8jp[^.NGXgf[^.ì¬úTo != nullÌê
            l_referenceRequest.createDateTo = "20080808000000";
            
            //* 9jp[^.NGXgf[^.³FúFrom!= nullÌê
            l_referenceRequest.approveDateFrom = "20070707000000";
            l_eqtypeOrderUnitParams.setApproveTimestamp(WEB3DateUtility.getDate("20070707000000","yyyyMMddHHmmss"));

            //* 10jp[^.NGXgf[^.³FúTo != nullÌê
            l_referenceRequest.approveDateTo = "20080808000000";

            //* 11jp[^.NGXgf[^.­§ÏR!= nullÌê
            l_referenceRequest.forcedSettleReason = "91";
            //ÇØ(æñ)úú´ß
            //"ÛØàÛ¦êiICJnOE@@èj" 
            l_eqtypeOrderUnitParams.setForcedSettleReasonType(WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL);
            

            //* 12jp[^.NGXgf[^.Ïúú!= nullÌê
            l_referenceRequest.closeDate = WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss");
//            l_referenceRequest.closeDate = null;
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20080808000000","yyyyMMddHHmmss"));

            //* 13jp[^.NGXgf[^.¶G[RR[h != nullÌê
            l_referenceRequest.errorReason = "9001";
            l_eqtypeOrderUnitParams.setErrorReasonCode("9001");

            //* 14jp[^.NGXgf[^.³Fæª != nullÌê
            l_referenceRequest.approveType = "1";
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setApproveStatusType("0");

            //* 15jp[^.NGXgf[^.ûÀæª != nullÌê
            l_referenceRequest.taxType = "0";
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);

            //* 16jp[^.NGXgf[^.sêR[h != nullÌê
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

            //<-------------------ÊmF-------------------------->
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
