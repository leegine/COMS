head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeFeqBranchMarketDealtCondForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.util.List;

import com.bea.utils.misc.Process;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeFeqBranchMarketDealtCondForMock extends WEB3GentradeFeqBranchMarketDealtCond
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3GentradeFeqBranchMarketDealtCondForMock.class);
   
    public WEB3GentradeFeqBranchMarketDealtCondForMock(FeqBranchMarketDealtCondRow l_feqBranchMarketDealtCondRow)
    {
        super(l_feqBranchMarketDealtCondRow);
    }

    public WEB3GentradeFeqBranchMarketDealtCondForMock(String l_strInstitutionCode, String l_strBranchCode,
        String l_strMarketCode) throws WEB3SystemLayerException
    {
        super(l_strInstitutionCode, l_strBranchCode, l_strMarketCode);
    }

    /**
     *  (get（部店市場別.外株）取扱条件)のMockメソッド<BR>
     * <BR>
     * @@param l_branch 部店オブジェクト
     * @@param l_expectValue - 期待値
     */
    public static void mockGetFeqHandlingCondBranchMarket(WEB3GentradeBranch l_branch, 
        WEB3GentradeFeqBranchMarketDealtCond[] l_expectValue)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "mockGetFeqHandlingCondBranchMarket(WEB3GentradeBranch, WEB3GentradeFeqBranchMarketDealtCond[])";
                
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionId(((BranchRow)l_branch.getDataSourceObject()).getInstitutionId());
        TestDBUtility.insertWithDel(l_institutionParams);
        
        //証券会社コード 
        String l_strInstitutionCode = 
            l_branch.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = l_branch.getBranchCode();

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
                FeqBranchMarketDealtCondRow.TYPE,
                l_sbWhere.toString(),
                l_objBranchMarketDealtCondWhere
                );
            
            for (int i = 0; i < l_expectValue.length; i++)
            {
                FeqBranchMarketDealtCondParams l_params = 
                    new FeqBranchMarketDealtCondParams((FeqBranchMarketDealtCondRow)l_expectValue[i].getDataSourceObject());
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
        
    /**
     * (get取引中取扱可能市場)のMockメソッド<BR>
     * <BR>
     * @@param l_genBranch 部店オブジェクト
     * @@param l_productTypeEnum 銘柄タイプ
     * @@param l_strExpects - 期待値
     * @@throws WEB3BaseException 
     */
    public static void mockGetTradingHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum,
        String[] l_strExpects)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "mockGetTradingHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum, String[])";
                  
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarketDealtConds = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_strExpects.length];
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            for (int i = 0; i < l_strExpects.length; i++)
            {
                FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
                    TestDBUtility.getFeqBranchMarketDealtCondRow();
                
                l_feqBranchMarketDealtCondParams.setMarketCode(l_strExpects[i]);
                l_feqBranchMarketDealtCondParams.setMartCanDealtEquity("1");
                l_feqBranchMarketDealtConds[i] = new WEB3GentradeFeqBranchMarketDealtCondForMock(l_feqBranchMarketDealtCondParams);
                
                TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
                
                MarketParams l_marketParams = TestDBUtility.getMarketRow();
                l_marketParams.setMarketId(l_marketParams.getMarketId() + i);
                l_marketParams.setInstitutionCode(l_genBranch.getInstitution().getInstitutionCode());
                l_marketParams.setMarketCode(l_strExpects[i]);
                l_marketParams.setSuspension("0");
                TestDBUtility.insertWithDel(l_marketParams);
            }
            WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetFeqHandlingCondBranchMarket(l_genBranch,l_feqBranchMarketDealtConds);
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
