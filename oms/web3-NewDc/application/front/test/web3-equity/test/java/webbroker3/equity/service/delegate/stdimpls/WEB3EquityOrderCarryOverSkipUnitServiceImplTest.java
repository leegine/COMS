head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderCarryOverSkipUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityOrderCarryOverSkipUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/08 張少傑
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.equity.data.HostEquityCarryoverSkipParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderCarryOverSkipUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverSkipUnitServiceImplTest.class);
    public WEB3EquityOrderCarryOverSkipUnitServiceImplTest(String arg0)
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
    
    public void testUndoOrderCarryOver_C001()
    {
        final String STR_METHOD_NAME = "testUndoOrderCarryOver_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setInstitutionCode("0D");
            l_MarketParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            WEB3EquityOrderCarryOverSkipUnitServiceImpl l_impl = new WEB3EquityOrderCarryOverSkipUnitServiceImpl();
            HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams =
                new HostEquityCarryoverSkipParams();
            l_hostEquityCarryoverSkipParams.setInstitutionCode("0D");
            l_hostEquityCarryoverSkipParams.setSkipMarketCode("3");
            l_impl.undoOrderCarryOver(l_hostEquityCarryoverSkipParams);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCallOrderCarryOver_C001()
    {
        final String STR_METHOD_NAME = "testCallOrderCarryOver_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setInstitutionCode("0D");
            l_MarketParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams = new HostEquityCarryoverSkipParams();
            WEB3EquityOrderCarryOverSkipUnitServiceImpl l_impl =
                new WEB3EquityOrderCarryOverSkipUnitServiceImpl();
            l_hostEquityCarryoverSkipParams.setInstitutionCode("0D");
            l_hostEquityCarryoverSkipParams.setSkipMarketCode("3");
            l_impl.callOrderCarryOver(l_hostEquityCarryoverSkipParams);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
