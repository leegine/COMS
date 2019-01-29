head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBranchMarketRepayDealtCondForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeBranchMarketRepayDealtCondForMock extends WEB3GentradeBranchMarketRepayDealtCond
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3GentradeBranchMarketRepayDealtCondForMock.class);
   
    public WEB3GentradeBranchMarketRepayDealtCondForMock(
        BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow)
    {
        super(l_branchMarketRepayDealtCondRow);
        // TODO Auto-generated constructor stub
    }

    public WEB3GentradeBranchMarketRepayDealtCondForMock(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strMarketCode, String l_strRepaymentDiv, double l_dbRepaymentNum) throws WEB3SystemLayerException
    {
        super(l_strInstitutionCode, l_strBranchCode, l_strMarketCode, l_strRepaymentDiv, l_dbRepaymentNum);
        // TODO Auto-generated constructor stub
    }
    
    
    public static void mockGetBranchMarketRepayDealtCond(
        WEB3GentradeBranch l_genBranch,
        WEB3GentradeBranchMarketRepayDealtCond[] l_expectValue)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "mockGetBranchMarketRepayDealtCond(WEB3GentradeBranch, WEB3GentradeBranchMarketRepayDealtCond[])";
                
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionId(((BranchRow)l_genBranch.getDataSourceObject()).getInstitutionId());
        TestDBUtility.insertWithDel(l_institutionParams);
        
        //証券会社コード 
        String l_strInstitutionCode = 
            l_genBranch.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = l_genBranch.getBranchCode();

        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");   //証券会社コード
            l_sbWhere.append(" and  branch_code = ? ");   //部店コード

            Object[] l_objBranchMarketDealtCondWhere = { 
                l_strInstitutionCode,    //証券会社コード
                l_strBranchCode          //部店コード
                }; 

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_QueryProcessor.doDeleteAllQuery(
                BranchMarketRepayDealtCondRow.TYPE,
                l_sbWhere.toString(),
                l_objBranchMarketDealtCondWhere
                );
            
            for (int i = 0; i < l_expectValue.length; i++)
            {
                BranchMarketRepayDealtCondParams l_params = 
                    new BranchMarketRepayDealtCondParams((BranchMarketRepayDealtCondRow)l_expectValue[i].getDataSourceObject());
                TestDBUtility.insertWithDel(l_params);
            }
        }
        catch (DataException l_ex)
        {
            log.error("",l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeFeqBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
