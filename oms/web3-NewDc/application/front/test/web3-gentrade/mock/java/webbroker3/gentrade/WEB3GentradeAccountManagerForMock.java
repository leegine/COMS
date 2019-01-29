head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeAccountManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeAccountManagerForMock extends WEB3GentradeAccountManager
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeAccountManagerForMock.class);
    
    public MainAccount getMainAccount(long getMainAccount) throws NotFoundException
    {

		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getMainAccount", 
				new Class[] { long.class }, 
				new Object[] { new Long(getMainAccount)});
		
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {long.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getMainAccount(long)");    
        	
        	WEB3GentradeMainAccountForMock l_mainAccount = 
                     new WEB3GentradeMainAccountForMock((MainAccountRow)((MainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class}).asObject()).getDataSourceObject());
                 
                 return l_mainAccount;
        }
        return new WEB3GentradeMainAccountForMock(
            (MainAccountRow)super.getMainAccount(getMainAccount).getDataSourceObject());
    
    }
    
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws NotFoundException
    {
		
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getMainAccount", 
				new Class[] { long.class,long.class,String.class }, 
				new Object[] { new Long(l_lngInstitutionId),new Long(l_lngBranchId),l_strAccountCode});
		
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {long.class, long.class, String.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getMainAccount(long,long,String)"); 
        	WEB3GentradeMainAccountForMock l_mainAccount = 
                 new WEB3GentradeMainAccountForMock((MainAccountRow)((MainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class, long.class, String.class}).asObject()).getDataSourceObject());
             
             return l_mainAccount;
        }
        return new WEB3GentradeMainAccountForMock(
            (MainAccountRow)super.getMainAccount(l_lngInstitutionId, l_lngBranchId, l_strAccountCode).getDataSourceObject());
    }
    
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws NotFoundException
    {
    	
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getMainAccount", 
				new Class[] { long.class,String.class,String.class }, 
				new Object[] { new Long(l_lngInstitutionId),l_strBranchCode,l_strAccountCode});
    	
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {long.class, String.class, String.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getMainAccount(long,String,String)"); 
        	WEB3GentradeMainAccountForMock l_mainAccount = 
                 new WEB3GentradeMainAccountForMock((MainAccountRow)((MainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {long.class, String.class, String.class}).asObject()).getDataSourceObject());
             
             return l_mainAccount;
        }
        return new WEB3GentradeMainAccountForMock(
            (MainAccountRow)super.getMainAccount(l_lngInstitutionId, l_strBranchCode, l_strAccountCode).getDataSourceObject());
    }
    
    public WEB3GentradeMainAccount getMainAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getMainAccount", 
				new Class[] { String.class,String.class,String.class }, 
				new Object[] { l_strInstitutionCode,l_strBranchCode,l_strAccountCode});
    	
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getMainAccount(String,String,String)"); 
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class}).asWEB3BaseException();
        	
        	TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class}).asWEB3BaseException();
                    
        	WEB3GentradeMainAccountForMock l_mainAccount = 
                 new WEB3GentradeMainAccountForMock((MainAccountRow)((MainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class}).asObject()).getDataSourceObject());
             
             return l_mainAccount;
        }
        return new WEB3GentradeMainAccountForMock(
            (MainAccountRow)super.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode).getDataSourceObject());

    }
    
    public MainAccount getMainAccount(Institution l_inst, Branch l_branch, String l_strAccountCode)
    throws NotFoundException
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getMainAccount", 
				new Class[] { Institution.class,Branch.class,String.class }, 
				new Object[] { l_inst,l_branch,l_strAccountCode});
    	
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {Institution.class, Branch.class, String.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getMainAccount(Institution,Branch,String)");  
        	WEB3GentradeMainAccountForMock l_mainAccount = 
                 new WEB3GentradeMainAccountForMock((MainAccountRow)((MainAccount)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {Institution.class, Branch.class, String.class}).asObject()).getDataSourceObject());
             
             return l_mainAccount;
        }
        return new WEB3GentradeMainAccountForMock(
            (MainAccountRow)super.getMainAccount(l_inst, l_branch, l_strAccountCode).getDataSourceObject());

    }
    
    public SubAccount getSubAccount(
            long l_lngAccountId,
            SubAccountTypeEnum l_subAccountType)
            throws NotFoundException
    {        
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getSubAccount", 
				new Class[] { long.class,SubAccountTypeEnum.class }, 
				new Object[] {new Long(l_lngAccountId),l_subAccountType});
    	
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getSubAccount",
            new Class[] {long.class,SubAccountTypeEnum.class}))
        {
            SubAccount l_subAccount = (SubAccount) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", "getSubAccount",
                new Class[] { long.class, SubAccountTypeEnum.class }).asObject();
            return new WEB3GentradeSubAccountForMock((SubAccountRow)l_subAccount.getDataSourceObject());
        }
        return (SubAccount)super.getSubAccount(l_lngAccountId,l_subAccountType);        
    }
    
    public SubAccount getSubAccount(
        long l_lngAccountId,
        long l_lngSubAccountId)
        throws NotFoundException
    {
        
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getSubAccount", 
				new Class[] { long.class,long.class }, 
				new Object[] {new Long(l_lngAccountId),new Long(l_lngSubAccountId)});
    	
    	if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getSubAccount",
            new Class[] {long.class,long.class}))
        {
        	log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getSubAccount(long,long)");  
        	SubAccount l_subAccount = (SubAccount) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", "getSubAccount",
                new Class[] { long.class, long.class }).asObject();
            
            return new WEB3GentradeSubAccountForMock((SubAccountRow)l_subAccount.getDataSourceObject());
        }
        return new WEB3GentradeSubAccountForMock(
            (SubAccountRow)super.getSubAccount(l_lngAccountId,l_lngSubAccountId).getDataSourceObject());
    }
    
    public void lockAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "lockAccount(String,String,String)";
        
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"lockAccount", 
				new Class[] { String.class,String.class,String.class }, 
				new Object[] { l_strInstitutionCode,l_strBranchCode,l_strAccountCode});
		
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[] {String.class,String.class,String.class}))
        {
            log.entering(STR_METHOD_NAME);
            log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.lockAccount(String,String,String)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class}).asVoid();
            log.exiting(STR_METHOD_NAME);
            return;
        }
        super.lockAccount(l_strInstitutionCode,l_strBranchCode,l_strAccountCode); 
        
    }
    
    public Branch getBranch(long l_lngBranchId) throws NotFoundException
    {
        String STR_METHOD_NAME = "getBranch(long)";
		
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getBranch", 
				new Class[] { long.class }, 
				new Object[] { new Long(l_lngBranchId)});
		
        try
        {
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {long.class}))
            {
                log.entering(STR_METHOD_NAME);
                log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getBranch(long)");
                Branch l_branch = (Branch)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {long.class}).asObject();

                return new WEB3GentradeBranchForMock(l_branch.getBranchId());
            }
            return new WEB3GentradeBranchForMock(super.getBranch(l_lngBranchId).getBranchId());            
        }
        catch (DataException l_ex)
        {
            log.error("", l_ex);
            throw new NotFoundException();
        }
    }
    
    public Branch getBranch(Institution l_institution, String l_strBranchCode)
    throws NotFoundException
    {
        String STR_METHOD_NAME = "getBranch(Institution, String)";
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getBranch", 
				new Class[] {Institution.class,String.class}, 
				new Object[] {l_institution,l_strBranchCode});
        try
        {
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class}))
            {
                log.entering(STR_METHOD_NAME);
                log.debug("webbroker3.feq.WEB3GentradeAccountManagerForMock.getBranch(Institution,String)");
                Branch l_branch = (Branch)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {Institution.class, String.class}).asObject();

                return new WEB3GentradeBranchForMock(l_branch.getBranchId());
            }
            return new WEB3GentradeBranchForMock(
                super.getBranch(l_institution, l_strBranchCode).getBranchId());            
        }
        catch (DataException l_ex)
        {
            log.error("", l_ex);
            throw new NotFoundException();
        }
    }
    
    public WEB3GentradeBranch getWeb3GenBranch(
        String l_strInstitutionCode,
        String l_strBranchCode)
        throws NotFoundException
    {
        String STR_METHOD_NAME = "getWeb3GenBranch(String, String)";
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getWeb3GenBranch", 
				new Class[] {String.class,String.class}, 
				new Object[] {l_strInstitutionCode,l_strBranchCode});
        try
        {
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getWeb3GenBranch",
                new Class[] {String.class, String.class}))
            {
                log.entering(STR_METHOD_NAME);
                Branch l_branch = (Branch)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getWeb3GenBranch",
                    new Class[] {String.class, String.class}).asObject();

                return new WEB3GentradeBranchForMock(l_branch.getBranchId());
            }
            return new WEB3GentradeBranchForMock(
                super.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode).getBranchId());            
        }
        catch (DataException l_ex)
        {
            log.error("", l_ex);
            throw new NotFoundException();
        }
    }
    
    public Institution getInstitution(long l_lngInstitutionId)
        throws NotFoundException
    {
        String STR_METHOD_NAME = "getInstitution(long)";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getInstitution", 
				new Class[] {long.class}, 
				new Object[] {new Long(l_lngInstitutionId)});
        try
        {            
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {long.class}))
            {

                
                Institution l_institution = (Institution)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getInstitution",
                    new Class[] {long.class}).asObject();
    
                return new WEB3GentradeInstitutionForMock((InstitutionRow)l_institution.getDataSourceObject());
            }
            return new WEB3GentradeInstitutionForMock((InstitutionRow)
                super.getInstitution(l_lngInstitutionId).getDataSourceObject());
        }
        catch (DataException l_ex)
        {
            log.error("", l_ex);
            throw new NotFoundException();
        }
    }
    
    public Institution getInstitution(String l_strInstitutionCode)
    throws NotFoundException
    {
        String STR_METHOD_NAME = "getInstitution(String)";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.gentrade.WEB3GentradeAccountManager",
				"getInstitution", 
				new Class[] {String.class}, 
				new Object[] {l_strInstitutionCode});
        try
        {            
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class}))
            {                
                Institution l_institution = (Institution)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getInstitution",
                    new Class[] {String.class}).asObject();
    
                return new WEB3GentradeInstitutionForMock((InstitutionRow)l_institution.getDataSourceObject());
            }
            return new WEB3GentradeInstitutionForMock((InstitutionRow)
                super.getInstitution(l_strInstitutionCode).getDataSourceObject());
        }
        catch (DataException l_ex)
        {
            log.error("", l_ex);
            throw new NotFoundException();
        }
    }
}
@
