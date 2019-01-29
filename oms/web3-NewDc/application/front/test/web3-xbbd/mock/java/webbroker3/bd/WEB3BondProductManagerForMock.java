head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondProductManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3BondProductManagerForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 金傑 (中訊) 新規作成
*/
package webbroker3.bd;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondProduct;

import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券プロダクトマネージャクラスForMock
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3BondProductManagerForMock extends WEB3BondProductManager
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3BondProductManagerForMock.class);
    
	public BondProduct getBondProduct(long l_lngProductId)
    throws WEB3BaseException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.bd.WEB3BondProductManager",
                "getBondProduct",
                new Class[] {long.class},
                new Object[]{new Long(l_lngProductId)});
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondProductManager", "getBondProduct",
				new Class[] { long.class }))
		{
			log.debug("webbroker3.bd.WEB3BondProductManagerForMock.getBondProduct(long)");
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager", "getBondProduct",
					new Class[] { long.class }).asWEB3BaseException();
			
			return(BondProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager", "getBondProduct",
					new Class[] { long.class }).asObject();
		}
		
    	return super.getBondProduct(l_lngProductId);
	}
    
    public BondProduct getBondProduct(Institution institution, String l_strProductCode) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondProductManager", "getBondProduct",
                new Class[]
                { Institution.class, String.class }, new Object[]
                { institution, l_strProductCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondProductManager", "getBondProduct",
                new Class[]
                { Institution.class, String.class }))
        {
            log.debug("webbroker3.bd.WEB3BondProductManagerForMock.getBondProduct()");
            return (BondProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondProductManager", "getBondProduct", new Class[]
                    { Institution.class, String.class }).asObject();
        }
        return super.getBondProduct(institution, l_strProductCode);
    }
    
    public void validateProductSpec(String l_strProductId,
            WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", new Class[]
                { String.class, WEB3BondDomesticProductUpdateInfo.class }, new Object[]
                { l_strProductId, l_bondDomesticProductUpdateInfo });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondProductManager", "validateProductSpec",
                new Class[]
                { String.class, WEB3BondDomesticProductUpdateInfo.class }))
        {
            log.debug("webbroker3.bd.WEB3BondProductManagerForMock.validateProductSpec()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager",
                    "validateProductSpec", new Class[]
                    { String.class, WEB3BondDomesticProductUpdateInfo.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager",
                    "validateProductSpec", new Class[]
                    { String.class, WEB3BondDomesticProductUpdateInfo.class }).asVoid();
            return;
        }
        super.validateProductSpec(l_strProductId, l_bondDomesticProductUpdateInfo);
    }
    
    public void updateBondProductContent(String l_strProductId,
            WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo, String l_administratorCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondProductManager",
                "updateBondProductContent", new Class[]
                { String.class, WEB3BondDomesticProductUpdateInfo.class, String.class }, new Object[]
                { l_strProductId, l_bondDomesticProductUpdateInfo, l_administratorCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondProductManager", "updateBondProductContent",
                new Class[]
                { String.class, WEB3BondDomesticProductUpdateInfo.class, String.class }))
        {
            log.debug("webbroker3.bd.WEB3BondProductManagerForMock.updateBondProductContent()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager",
                    "updateBondProductContent", new Class[]
                    { String.class, WEB3BondDomesticProductUpdateInfo.class, String.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager",
                    "updateBondProductContent", new Class[]
                    { String.class, WEB3BondDomesticProductUpdateInfo.class, String.class }).asVoid();
            return;
        }
        super.updateBondProductContent(l_strProductId, l_bondDomesticProductUpdateInfo, l_administratorCode);
    }
    
    public WEB3BondDomesticBranchRecruitLimitInfo[] createAdminBondDomesticRecruitLimitInfo(long l_lngProductId,
            String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", new Class[]
                { long.class, String.class, String.class }, new Object[]
                { new Long(l_lngProductId), l_strInstitutionCode, l_strBranchCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", new Class[]
                { long.class, String.class, String.class }))
        {
            log.debug("webbroker3.bd.WEB3BondProductManagerForMock.createAdminBondDomesticRecruitLimitInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo", new Class[]
                    { long.class, String.class, String.class }).asWEB3BaseException();
            return (WEB3BondDomesticBranchRecruitLimitInfo[]) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.bd.WEB3BondProductManager", "createAdminBondDomesticRecruitLimitInfo", new Class[]
                    { long.class, String.class, String.class }).asObject();
        }
        return super.createAdminBondDomesticRecruitLimitInfo(l_lngProductId, l_strInstitutionCode, l_strBranchCode);
    }
	
    public Product toProduct(Row l_row)
    {
    	return super.toProduct(l_row);
    }
}
@
