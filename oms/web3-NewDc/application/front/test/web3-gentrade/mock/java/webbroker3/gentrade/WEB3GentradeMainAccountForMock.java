head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeMainAccountForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeMainAccountForMock extends WEB3GentradeMainAccount
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeMainAccountForMock.class);
    
    public WEB3GentradeMainAccountForMock(long l_lngAccountLd)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngAccountLd);        
    }
    
    /**
     * コンストラクタ。<BR>
     * <BR> 
     * @@param l_row 顧客Rowオブジェクト
     * @@roseuid 403496F000D3
     */
    public WEB3GentradeMainAccountForMock(MainAccountRow l_row)
    {
        super(l_row);
    }
    
    public boolean isMarginAccountEstablished(String l_strRepaymentType)
    {
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished",
            new Class[] {String.class},
            new Object[]{l_strRepaymentType});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "isMarginAccountEstablished",
            new Class[] {String.class}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccount.isMarginAccountEstablished(String)" + 
                "l_strRepaymentType = " + l_strRepaymentType);
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class}).asBoolean();
        }
        return super.isMarginAccountEstablished(l_strRepaymentType);
    }
    
    public boolean isSpecialAccountEstablished(SubAccount l_subAccount) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "isSpecialAccountEstablished",
            new Class[] {SubAccount.class}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccount.isSpecialAccountEstablished(SubAccount)" + 
                "パラメータl_strRepaymentTypeはエラーを発生しました。" + l_subAccount);
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isSpecialAccountEstablished",
                    new Class[] {SubAccount.class}).asWEB3BaseException();
            
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {SubAccount.class}).asBoolean();
        }
        return super.isSpecialAccountEstablished(l_subAccount);
    }

    /**
     * (is特定口座開設(Mock)) <BR>
     * @@param l_deliveryDate - (受渡日) <BR>
     * @@param l_subAccount - (補助口座) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(
        Date l_deliveryDate,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(Date,SubAccount)-->ForMock";
        log.entering(STR_METHOD_NAME);
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "isSpecialAccountEstablished",
            new Class[] {Date.class, SubAccount.class},
            new Object[]{l_deliveryDate, l_subAccount});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeMainAccount",
            "isSpecialAccountEstablished",
            new Class[] {Date.class, SubAccount.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
        	log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.isSpecialAccountEstablished(Date,SubAccount)");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {Date.class, SubAccount.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isSpecialAccountEstablished",
                new Class[] {Date.class, SubAccount.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isSpecialAccountEstablished(l_deliveryDate, l_subAccount);
    }
    
    /**
     * (getログインＩＤ(Mock))<BR>
     * @@return long - ログインＩＤ<BR>
     */
    public long getLoginId() throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "getLoginId()-->ForMock";
		log.entering(STR_METHOD_NAME);

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "getLoginId",
				new Class[] {}))
		{
			log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.getLoginId()");
			return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.gentrade.WEB3GentradeMainAccount", "getLoginId", new Class[] {}).asLong();
		}
		log.exiting(STR_METHOD_NAME);
		return super.getLoginId();
	}
    
    public Branch getBranch() 
    {
        final String STR_METHOD_NAME = "getLoginId()-->ForMock";
        log.entering(STR_METHOD_NAME);
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "getBranch",
                new Class[] {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.getBranch()");
            
            return (Branch)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", "getBranch", new Class[] {}).asObject();
        }
        log.exiting(STR_METHOD_NAME);
        return super.getBranch();
    }
    
    public String getAccountCode()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                "getAccountCode", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "getAccountCode",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.getAccountCode()");
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", "getAccountCode", new Class[]
                    {}).asObject();
        }
        return super.getAccountCode();
    }
    
    public SubAccount getSubAccount(SubAccountTypeEnum l_subaccountTypeEnum) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount", new Class[]
                { SubAccountTypeEnum.class }, new Object[]
                { l_subaccountTypeEnum });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount",
                new Class[]
                { SubAccountTypeEnum.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.getSubAccount()");
            return (SubAccount) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", "getSubAccount", new Class[]
                    { SubAccountTypeEnum.class }).asObject();
        }
        return super.getSubAccount(l_subaccountTypeEnum);
    }
    
    public Institution getInstitution()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                "getInstitution", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "getInstitution",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.getInstitution()");
            return (Institution) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", "getInstitution", new Class[]
                    {}).asObject();
        }
        return super.getInstitution();
    }
    
    public boolean isRealCustomer()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                "isRealCustomer", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "isRealCustomer",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.isRealCustomer()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isRealCustomer", new Class[]
                    {}).asBoolean();
        }
        return super.isRealCustomer();
    }
    
    public boolean isDocumentDelivery(String l_documentDivision, String l_productCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                "isDocumentDelivery",
                new Class[]{ String.class, String.class },
                new Object[]{ l_documentDivision, l_productCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeMainAccount", "isDocumentDelivery",
                new Class[]{ String.class, String.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeMainAccountForMock.isRealCustomer()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isDocumentDelivery",
                    new Class[]{ String.class, String.class }).asBoolean();
        }
        return super.isRealCustomer();
    }
}
@
