head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualCancelAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3MutualCancelAcceptServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/05/14 武波 (中訊) 新規作成
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.data.HostXbmfCancelAcceptParams;
import webbroker3.mf.data.HostXbmfCancelAcceptRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualCancelAcceptServiceImplTest extends JunitTestBase
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptServiceImplTest.class);

    WEB3MutualCancelAcceptServiceImpl l_impl = new WEB3MutualCancelAcceptServiceImpl();
    WEB3MutualCancelAcceptServiceImpl.WEB3MutualCancelAcceptTransactionCallback l_back =
        l_impl.new WEB3MutualCancelAcceptTransactionCallback();
    public WEB3MutualCancelAcceptServiceImplTest(String arg0)
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

    public void testProcess_0001()
    {
        final String STR_METHOD_NAME = "testProcess_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);

            TestDBUtility.deleteAll(HostXbmfCancelAcceptParams.TYPE);
            HostXbmfCancelAcceptParams l_hostXbmfCancelAcceptParams =
                new HostXbmfCancelAcceptParams();
            l_hostXbmfCancelAcceptParams.setRequestCode("1");
            l_hostXbmfCancelAcceptParams.setInstitutionCode("0D");
            l_hostXbmfCancelAcceptParams.setBranchCode("381");
            l_hostXbmfCancelAcceptParams.setAccountCode("1111111");
            l_hostXbmfCancelAcceptParams.setTraderCode("1");
            l_hostXbmfCancelAcceptParams.setOrderRequestNumber("1");
            l_hostXbmfCancelAcceptParams.setAcceptStatus("1");
            l_hostXbmfCancelAcceptParams.setErrorMessage("1");
            l_hostXbmfCancelAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostXbmfCancelAcceptParams);
            l_back.process();

            String l_strWhere = "status = ?";

            Object[] l_objWhere = { WEB3StatusDef.DEALT };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfCancelAcceptRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            assertEquals("1", ((HostXbmfCancelAcceptRow)l_lisSearchResult.get(0)).getStatus());
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    
        log.exiting(STR_METHOD_NAME);
    }

    public void testProcess_0002()
    {
        final String STR_METHOD_NAME = "testProcess_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);

            TestDBUtility.deleteAll(HostXbmfCancelAcceptParams.TYPE);
            HostXbmfCancelAcceptParams l_hostXbmfCancelAcceptParams =
                new HostXbmfCancelAcceptParams();
            l_hostXbmfCancelAcceptParams.setRequestCode("1");
            l_hostXbmfCancelAcceptParams.setInstitutionCode("0D");
            l_hostXbmfCancelAcceptParams.setBranchCode("381");
            l_hostXbmfCancelAcceptParams.setAccountCode("1111111");
            l_hostXbmfCancelAcceptParams.setTraderCode("1");
            l_hostXbmfCancelAcceptParams.setOrderRequestNumber("2");
            l_hostXbmfCancelAcceptParams.setAcceptStatus("1");
            l_hostXbmfCancelAcceptParams.setErrorMessage("1");
            l_hostXbmfCancelAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostXbmfCancelAcceptParams);
            
            l_hostXbmfCancelAcceptParams.setRequestCode("1");
            l_hostXbmfCancelAcceptParams.setInstitutionCode("0D");
            l_hostXbmfCancelAcceptParams.setBranchCode("381");
            l_hostXbmfCancelAcceptParams.setAccountCode("1111111");
            l_hostXbmfCancelAcceptParams.setTraderCode("1");
            l_hostXbmfCancelAcceptParams.setOrderRequestNumber("1");
            l_hostXbmfCancelAcceptParams.setAcceptStatus("1");
            l_hostXbmfCancelAcceptParams.setErrorMessage("1");
            l_hostXbmfCancelAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostXbmfCancelAcceptParams);
            l_back.process();
            
            String l_strWhere = "status = ?";

            Object[] l_objWhere = { WEB3StatusDef.DEALT };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfCancelAcceptRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            assertEquals("1", ((HostXbmfCancelAcceptRow)l_lisSearchResult.get(0)).getStatus());
            assertEquals("1", ((HostXbmfCancelAcceptRow)l_lisSearchResult.get(1)).getStatus());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    
        log.exiting(STR_METHOD_NAME);
    }
}
@
