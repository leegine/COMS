head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AdminAccInfoCampaignSearchConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キャンペーン検索条件テスト(WEB3AdminAccInfoCampaignSearchConditionTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.util.Date;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.mock.TestBaseForMock;

public class WEB3AdminAccInfoCampaignSearchConditionTest extends TestBaseForMock
{
    WEB3AdminAccInfoCampaignSearchCondition l_condition = new WEB3AdminAccInfoCampaignSearchCondition();

    public WEB3AdminAccInfoCampaignSearchConditionTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public void testResetCampaignCondition()
    {
        WEB3AccInfoCampaignSearchCondition l_SearchCondition
            = new WEB3AccInfoCampaignSearchCondition();
        
        String[] l_registerTypes = new String[1];
        l_registerTypes[0] = "registerTypes"; 

        l_SearchCondition.branchCode = "branchCode";
        l_SearchCondition.accountCode = "accountCode";
        l_SearchCondition.itemCode = "itemCode";
        l_SearchCondition.campaignName = "campaignName";
        l_SearchCondition.targetDate = new Date();
        l_SearchCondition.collectRate = "collectRate";
        l_SearchCondition.traderCode = "traderCode";
        l_SearchCondition.accountOpenDiv = "accountOpenDiv";
        l_SearchCondition.deleteFlag = "deleteFlag";
        
        l_condition.setCampaignCondition(l_SearchCondition, "CampaignSearchConditionId", "InstitutionCode", l_registerTypes);
        
        l_condition.setCampaignName("1");
        l_condition.setTraderCode("1");
        l_condition.setAccountOpenDiv("1");
        l_condition.setDeleteFlag("1");
        l_condition.setCommissionCampaignConditionId("1");
        
        String[] l_strItemCodes = new String[1];
        l_strItemCodes[0] = "ItemCodes"; 
        l_condition.setItemCode(l_strItemCodes);
        l_condition.setInstitutionCode("1");
        l_condition.setTargetDate(new Date());
        l_condition.setTargetPeriodFrom(null);
        l_condition.setAccountOpenDateFrom(new Date());
        l_condition.setCollectRate("1");
        

        l_condition.setRegisterType(l_registerTypes);
        l_condition.setBranchCode("1");
        
        l_condition.resetCampaignCondition();
        
        assertEquals(null, l_condition.institutionCode);
        assertEquals(null, l_condition.branchCode);
        assertEquals(null, l_condition.accountCode);
        assertEquals(null, l_condition.itemCodes);
        assertEquals(null, l_condition.campaignName);
        assertEquals(null, l_condition.targetDate);
        assertEquals(null, l_condition.targetPeriodFrom);
        assertEquals(null, l_condition.accountOpenDateFrom);
        assertEquals(null, l_condition.collectRate);
        assertEquals(null, l_condition.traderCode);
        assertEquals(null, l_condition.accountOpenDiv);
        assertEquals(null, l_condition.deleteFlag);
        assertEquals(null, l_condition.commissionCampaignConditionId);
        assertEquals(null, l_condition.registerTypes);
    }
    
    public void testSetCampaignCondition()
    {
        WEB3AccInfoCampaignSearchCondition l_SearchCondition
            = new WEB3AccInfoCampaignSearchCondition();
        
        String[] l_registerTypes = new String[1];
        l_registerTypes[0] = "registerTypes"; 

        l_SearchCondition.branchCode = "branchCode";
        l_SearchCondition.accountCode = "accountCode";
        l_SearchCondition.itemCode = "itemCode";
        l_SearchCondition.campaignName = "campaignName";
        
        Date l_date = new Date();
        l_SearchCondition.targetDate = l_date;
        l_SearchCondition.collectRate = "collectRate";
        l_SearchCondition.traderCode = "traderCode";
        l_SearchCondition.accountOpenDiv = "accountOpenDiv";
        l_SearchCondition.deleteFlag = "deleteFlag";
        
        l_condition.setCampaignCondition(l_SearchCondition, "CampaignSearchConditionId", "InstitutionCode", l_registerTypes);
        
        
        assertEquals("InstitutionCode", l_condition.institutionCode);
        assertEquals("branchCode", l_condition.branchCode);
        assertEquals("accountCode", l_condition.accountCode);
        assertEquals("itemCode", l_condition.itemCodes[0]);
        
        assertEquals("campaignName", l_condition.campaignName);
        assertEquals(l_date, l_condition.targetDate);

        assertEquals("collectRate", l_condition.collectRate);
        assertEquals("traderCode", l_condition.traderCode);
        assertEquals("accountOpenDiv", l_condition.accountOpenDiv);
        assertEquals("deleteFlag", l_condition.deleteFlag);
        assertEquals("CampaignSearchConditionId", l_condition.commissionCampaignConditionId);
        assertEquals(l_registerTypes, l_condition.registerTypes);
    }

    public void testSetCampaignName()
    {
        
        l_condition.setCampaignName("CampaignName");
        
        assertEquals("CampaignName", l_condition.campaignName);
    }

    public void testSetTraderCode()
    {
        
        l_condition.setTraderCode("TraderCode");
        
        assertEquals("TraderCode", l_condition.traderCode);
    }

    public void testSetAccountCode()
    {
        
        l_condition.setAccountCode("accountCode");
        
        assertEquals("accountCode", l_condition.accountCode);
    }

    public void testSetAccountOpenDiv()
    {
        
        l_condition.setAccountOpenDiv("AccountOpenDiv");
        
        assertEquals("AccountOpenDiv", l_condition.accountOpenDiv);
    }

    public void testSetDeleteFlag()
    {
        
        l_condition.setDeleteFlag("DeleteFlag");
        
        assertEquals("DeleteFlag", l_condition.deleteFlag);
    }

    public void testSetCommissionCampaignConditionId()
    {
        
        l_condition.setCommissionCampaignConditionId("CommissionCampaignConditionId");
        
        assertEquals("CommissionCampaignConditionId", l_condition.commissionCampaignConditionId);
    }

    public void testSetItemCode()
    {
        
        String[] l_strItemCodes = new String[1];
        l_strItemCodes[0] = "ItemCodes"; 
        
        l_condition.setItemCode(l_strItemCodes);
        
        assertEquals(l_strItemCodes, l_condition.itemCodes);
    }

    public void testSetInstitutionCode()
    {
        
        l_condition.setInstitutionCode("InstitutionCode");
        
        assertEquals("InstitutionCode", l_condition.institutionCode);
    }

    public void testSetTargetDate()
    {
        Date l_date = new Date(); 
        l_condition.setTargetDate(l_date);
        
        assertEquals(l_date, l_condition.targetDate);
    }

    public void testSetTargetPeriodFrom()
    {
        l_condition.setTargetPeriodFrom(null);
        
        assertEquals(null, l_condition.targetPeriodFrom);
    }

    public void testSetAccountOpenDateFrom()
    {
        Date l_date = new Date(); 
        l_condition.setAccountOpenDateFrom(l_date);
        
        assertEquals(l_date, l_condition.accountOpenDateFrom);
    }
    
    public void testSetCollectRate()
    {
        
        l_condition.setCollectRate("CollectRate");
        
        assertEquals("CollectRate", l_condition.collectRate);
    }

    public void testSetRegisterType()
    {
        
        String[] l_strRegisterType = new String[1];
        l_strRegisterType[0] = "RegisterType"; 

        l_condition.setRegisterType(l_strRegisterType);
        
        assertEquals(l_strRegisterType, l_condition.registerTypes);
    }

    public void testSetBranchCode()
    {
        
        l_condition.setBranchCode("BranchCode");
        
        assertEquals("BranchCode", l_condition.branchCode);
    }


}
@
