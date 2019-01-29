head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeSubAccountForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeSubAccountForMock extends WEB3GentradeSubAccount
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeSubAccountForMock.class);
    
    public WEB3GentradeSubAccountForMock(long l_lngAccountId, long l_lngSubAccountId) throws DataQueryException, DataNetworkException
    {
        super(l_lngAccountId, l_lngSubAccountId);
    }
    
    public WEB3GentradeSubAccountForMock(SubAccountRow l_subAcctRow)
    {
        super(l_subAcctRow);
    }
    
    public WEB3GentradeBranch getWeb3GenBranch()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeSubAccount",
            "getWeb3GenBranch",
            new Class[] {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeSubAccount.getWeb3GenBranch()");
            return (WEB3GentradeBranch)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeSubAccount",
                "getWeb3GenBranch",
                new Class[] {}).asObject();
        }
        return super.getWeb3GenBranch();
    }
    
    public MainAccount getMainAccount()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeSubAccount",
                "getMainAccount", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeSubAccount", "getMainAccount",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeSubAccountForMock.getMainAccount()");
            return (MainAccount) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", "getMainAccount", new Class[]
                    {}).asObject();
        }
        return super.getMainAccount();
    }
    
    public Institution getInstitution()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeSubAccount",
                "getInstitution", new Class[]
                {}, new Object[]
                {});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeSubAccount", "getInstitution",
                new Class[]
                {}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeSubAccountForMock.getInstitution()");
            return (Institution) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", "getInstitution", new Class[]
                    {}).asObject();
        }
        return super.getInstitution();
    }
}
@
