head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoCampaignRegistAccListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;


import java.util.Calendar;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignRegistAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 手数料割引キャンペーン登録顧客照会サービス実装クラス<BR>
 * @@author 孟亜南 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListServiceImplTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignRegistAccListServiceImplTest(String name) 
    {
        super(name);
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminAccInfoCampaignRegistAccListServiceImplTest.class);
    
    /**
     * execute<BR>
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = ".testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AccInfoAccountBaseInfoReferenceRequest l_request = new WEB3AccInfoAccountBaseInfoReferenceRequest();
        
        try 
        {
            l_adminListServiceImpl.execute(l_request);
        } 
        catch (WEB3BaseException l_ex) 
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute<BR>
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = ".testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        try 
        {
            l_adminListServiceImpl.execute(l_request);
        } 
        catch (WEB3BaseException l_ex) 
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("12あああ");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("aaa");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("12あああ","" + l_accHistoryParams2.getCommCampaignName());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0003() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("ああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0004() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ123");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11222");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("12");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ123","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11222","" + l_accHistoryParams2.getAccountCode());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0005() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("02");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード + 口座開設区分
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0006() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("2");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード + 口座開設区分 + 徴収率
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0007() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.11);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード + 口座開設区分 + 徴収率
     * + 対象期間From + 対象期間To 
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0008() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        //対象期間From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070118","yyyyMMdd"),l_accHistoryParams2.getAppliStartDate()));
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070918","yyyyMMdd"),l_accHistoryParams2.getAppliEndDate()));    
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
            
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード + 口座開設区分 + 徴収率
     * + 対象期間From + 対象期間To + 登録タイプ
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0009() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        //対象期間From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        //登録タイプ
        l_request.registType = "9";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //登録タイプ
            l_accHistoryParams.setRegistType("9");
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //登録タイプ
            l_accHistoryParams1.setRegistType("8");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
                assertEquals("9","" + l_accHistoryParams2.getRegistType());
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070218","yyyyMMdd"),l_accHistoryParams2.getAppliStartDate()));
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070218","yyyyMMdd"),l_accHistoryParams2.getAppliEndDate()));    
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSearchCondition<BR>
     * 証券会社コード + 商品コード + キャンペーン名称 + 部店コード + 顧客コード + 扱者コード + 口座開設区分 + 徴収率
     * + 対象期間From + 対象期間To + 登録タイプ + 
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0010() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "145";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        //対象期間From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        //登録タイプ
        l_request.registType = "9";
        
        //有効区分
        l_request.activeDiv = "1";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //登録タイプ
            l_accHistoryParams.setRegistType("9");
            //有効区分
            l_accHistoryParams.setValidDiv("1");
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //登録タイプ
            l_accHistoryParams1.setRegistType("9");
            //有効区分
            l_accHistoryParams1.setValidDiv("2");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers,
                l_rowType);
//            
//            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
//                l_strSearchCondition,
//                l_strSortSearchCondition,
//                null,
//                l_strSearchConditionContainers);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("145","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
                assertEquals("9","" + l_accHistoryParams2.getRegistType());
                assertEquals("1","" + l_accHistoryParams2.getValidDiv());
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070218","yyyyMMdd"),l_accHistoryParams2.getAppliStartDate()));
                assertEquals(0, WEB3DateUtility.compare(WEB3DateUtility.getDate("20070218","yyyyMMdd"),l_accHistoryParams2.getAppliEndDate()));    
            }
            if (l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getAccInfoCampaignActionInfo
     */
    public void testGetAccInfoCampaignActionInfo_C0001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testGetAccInfoCampaignActionInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "143";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("143");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(66);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("143");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams1.setCampaignId(66);
            //商品コード
            l_commCampaignProductMstParams1.setCommProductCode("10");
            l_commCampaignProductMstParams1.setLastUpdater("admin");
            l_commCampaignProductMstParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
            
            List l_lisAccInfoCampaignActionInfoList = 
                l_adminListServiceImpl.getAccInfoCampaignActionInfo(l_strSearchCondition,
                l_strSearchConditionContainers, l_strSortSearchCondition, l_request.itemCode);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                if (("" + l_accHistoryParams2.getCampaignId()).equals("88"))
                {
                    assertEquals("1","" + l_accHistoryParams2.getAccountId());
                    assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                    assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                    assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                    assertEquals("143","" + l_accHistoryParams2.getBranchCode());
                    assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                    assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                    assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                    assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
                }
                if (("" + l_accHistoryParams2.getCampaignId()).equals("66"))
                {
                    assertEquals("15","" + l_accHistoryParams2.getAccountId());
                    assertEquals("66","" + l_accHistoryParams2.getCampaignId());
                    assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                    assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                    assertEquals("143","" + l_accHistoryParams2.getBranchCode());
                    assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                    assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                    assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                    assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
                }
            }
            if (l_lisAccInfoCampaignActionInfoList == null 
                || l_lisAccInfoCampaignActionInfoList.size() == 0 
                || l_lisAccInfoCampaignActionInfoList.size() == 1 )
            {
                fail();
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getAccInfoCampaignActionInfo
     */
    public void testGetAccInfoCampaignActionInfo_C0002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testGetAccInfoCampaignActionInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        
        //商品コード
//        l_request.itemCode = "10";
        
        //キャンペーン名称
        l_request.campaignName = "あああ";
        
        //部店コード
        l_request.branchCode = "144";
        
        //顧客コード
        l_request.accountCode = "11";
        
        //扱者コード
        l_request.traderCode = "01";
        
        //口座開設区分
        l_request.accountOpenDiv = "1";
        
        //徴収率
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create検索条件文字列
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create検索条件データコンテナ
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //createソート条件文字列
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(1);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams.setBranchCode("144");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ");
            //部店コード
            l_accHistoryParams1.setBranchCode("145");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("01");
            //口座開設区分
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(18.11);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            List l_lisAccInfoCampaignActionInfoList = 
                l_adminListServiceImpl.getAccInfoCampaignActionInfo(l_strSearchCondition,
                l_strSearchConditionContainers, l_strSortSearchCondition, l_request.itemCode);
            
            for (int i = 0; i < l_lisAccInfoCampaignActionInfoList.size(); i++)
            {
                CommCampaignAccHistoryParams l_accHistoryParams2 = 
                    (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(i);
                assertEquals("1","" + l_accHistoryParams2.getAccountId());
                assertEquals("88","" + l_accHistoryParams2.getCampaignId());
                assertEquals("123","" + l_accHistoryParams2.getInstitutionCode());
                assertEquals("あああ","" + l_accHistoryParams2.getCommCampaignName());
                assertEquals("144","" + l_accHistoryParams2.getBranchCode());
                assertEquals("11","" + l_accHistoryParams2.getAccountCode());
                assertEquals("01","" + l_accHistoryParams2.getSonarTraderCode());
                assertEquals("1","" + l_accHistoryParams2.getAccOpenKindDiv());
                assertEquals("" + 18.12, "" +l_accHistoryParams2.getAccountChargeRatio());
            }
            if (l_lisAccInfoCampaignActionInfoList == null || l_lisAccInfoCampaignActionInfoList.size() == 0)
            {
                fail();
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getItemCode
     */
    public void testGetItemCode_C0001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testGetAccInfoCampaignActionInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88L);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("51");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams1.setCampaignId(88L);
            //商品コード
            l_commCampaignProductMstParams1.setCommProductCode("10");
            l_commCampaignProductMstParams1.setLastUpdater("admin");
            l_commCampaignProductMstParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
            
            String[] l_strItemCode = null;
            l_strItemCode = l_adminListServiceImpl.getItemCode(88L);
            
            assertEquals("10", l_strItemCode[0]);
            assertEquals("51", l_strItemCode[1]);
        }
        catch (WEB3BaseException l_ex) 
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getItemCode
     */
    public void testGetItemCode_C0002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testGetAccInfoCampaignActionInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88L);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("51");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams1.setCampaignId(88L);
            //商品コード
            l_commCampaignProductMstParams1.setCommProductCode("10");
            l_commCampaignProductMstParams1.setLastUpdater("admin");
            l_commCampaignProductMstParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
            
            String[] l_strItemCode = l_adminListServiceImpl.getItemCode(96L);
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            assertEquals("テーブルに該当するデータがありません。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0001() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //顧客コード
        l_request.accountCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //部店コード
            l_accHistoryParams.setBranchCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //部店コード
            l_accHistoryParams1.setBranchCode("b");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            if (l_lisAccInfoCampaignActionInfoList.size() != 2)
            {
                fail();
            }
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());
            assertEquals("a",l_accHistoryParams2.getBranchCode());
            assertEquals("b",l_accHistoryParams3.getBranchCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0002() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            if (l_lisAccInfoCampaignActionInfoList.size() != 2)
            {
                fail();
            }
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());
            assertEquals("a",l_accHistoryParams2.getAccountCode());
            assertEquals("b",l_accHistoryParams3.getAccountCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0003() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "traderCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());

            assertEquals("a",l_accHistoryParams2.getSonarTraderCode());
            assertEquals("b",l_accHistoryParams3.getSonarTraderCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0004() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "collectRate";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(122D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());
            assertEquals("" + 122D,"" + l_accHistoryParams2.getAccountChargeRatio());
            assertEquals("" + 123D,"" + l_accHistoryParams3.getAccountChargeRatio());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0005() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountOpenDateFrom";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070105","yyyyMMdd"),l_accHistoryParams2.getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070108","yyyyMMdd"),l_accHistoryParams3.getAppliStartDate()));
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0006() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountOpenDateTo";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams2.getAccountId());
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070106","yyyyMMdd"),l_accHistoryParams2.getAppliEndDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070109","yyyyMMdd"),l_accHistoryParams3.getAppliEndDate()));
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0007() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //顧客コード
        l_request.accountCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("11");
            //部店コード
            l_accHistoryParams.setBranchCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("11");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //部店コード
            l_accHistoryParams1.setBranchCode("b");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            if (l_lisAccInfoCampaignActionInfoList.size() != 2)
            {
                fail();
            }
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());
            assertEquals("a",l_accHistoryParams3.getBranchCode());
            assertEquals("b",l_accHistoryParams2.getBranchCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0008() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            if (l_lisAccInfoCampaignActionInfoList.size() != 2)
            {
                fail();
            }
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());
            assertEquals("a",l_accHistoryParams3.getAccountCode());
            assertEquals("b",l_accHistoryParams2.getAccountCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0009() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "traderCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());

            assertEquals("a",l_accHistoryParams3.getSonarTraderCode());
            assertEquals("b",l_accHistoryParams2.getSonarTraderCode());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0010() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "collectRate";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(122D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(123D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());
            assertEquals("" + 122D,"" + l_accHistoryParams3.getAccountChargeRatio());
            assertEquals("" + 123D,"" + l_accHistoryParams2.getAccountChargeRatio());
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0011() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "targetPeriodFrom";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070105","yyyyMMdd"),l_accHistoryParams3.getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070108","yyyyMMdd"),l_accHistoryParams2.getAppliStartDate()));
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0012() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "targetPeriodTo";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams3 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            
            assertEquals("2",""+l_accHistoryParams3.getAccountId());
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070106","yyyyMMdd"),l_accHistoryParams3.getAppliEndDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070109","yyyyMMdd"),l_accHistoryParams2.getAppliEndDate()));
            
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createSortSearchCondition
     * @@throws DataNetworkException 
     * @@throws IllegalStateException 
     * @@throws DataQueryException 
     */
    public void testCreateSortSearchCondition_C0013() throws WEB3BaseException, DataNetworkException, DataQueryException, IllegalStateException
    {
        final String STR_METHOD_NAME = ".testCreateSortSearchCondition_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //証券会社コード
        String l_strInstitutionCode = "123";
        //部店コード
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "targetPeriodFrom";
            l_sortKey.ascDesc = "A";
            WEB3AccInfoSortKey l_sortKey1 = new WEB3AccInfoSortKey();
            l_sortKey1.keyItem = "targetPeriodTo";
            l_sortKey1.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams.setAccountCode("a");
            //部店コード
            l_accHistoryParams.setBranchCode("11");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams1.setAccountCode("b");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams2.setAccountId(18);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams2.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams2.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams2.setInstitutionCode("123");
            //顧客コード
            l_accHistoryParams2.setAccountCode("b");
            //徴収率
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams2.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //部店コード
            l_accHistoryParams2.setBranchCode("11");
            l_accHistoryParams2.setLastUpdater("admin");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey,l_sortKey1};
            
            //create検索条件文字列
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //createソート条件文字列
            String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                l_strSearchCondition,
                l_strSortSearchCondition,
                null,
                l_strSearchConditionContainers);
            
            CommCampaignAccHistoryParams l_accHistoryParams12 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(0);
            CommCampaignAccHistoryParams l_accHistoryParams13 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(1);
            CommCampaignAccHistoryParams l_accHistoryParams14 = 
                (CommCampaignAccHistoryParams)l_lisAccInfoCampaignActionInfoList.get(2);
            
            assertEquals("2",""+l_accHistoryParams12.getAccountId());
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070105","yyyyMMdd"),l_accHistoryParams12.getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070106","yyyyMMdd"),l_accHistoryParams12.getAppliEndDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070109","yyyyMMdd"),l_accHistoryParams13.getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070118","yyyyMMdd"),l_accHistoryParams13.getAppliEndDate()));   
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070113","yyyyMMdd"),l_accHistoryParams14.getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070102","yyyyMMdd"),l_accHistoryParams14.getAppliEndDate()));
        }
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0001()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        try
        {
            l_adminListServiceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0002()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "12";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(1222222222L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            l_adminListServiceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0003()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            
            l_adminListServiceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0004()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "1";
        //部店コード
        l_request.branchCode = "abb";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            
            l_adminListServiceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0005()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = new WEB3AdminAccInfoCampaignRegistAccListResponse();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "1";
        //部店コード
        l_request.branchCode = "bbb";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());
            
            l_response = l_adminListServiceImpl.getListScreen(l_request);
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("0",l_response.totalRecords);
            assertNull(l_response.registAccountInfo);
            
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0006()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = new WEB3AdminAccInfoCampaignRegistAccListResponse();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "3";
        //要求ページ番号
        l_request.pageIndex = "1";
        //部店コード
        l_request.branchCode = "bbb";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(19);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams.setAccountCode("7895461");
            //部店コード
            l_accHistoryParams.setBranchCode("bbb");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams1.setAccountCode("1234567");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("bbb");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams2.setAccountId(18);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams2.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams2.setCommCampaignName("あああ3");
            //証券会社コード
            l_accHistoryParams2.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams2.setAccountCode("1689725");
            //徴収率
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams2.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //部店コード
            l_accHistoryParams2.setBranchCode("bbb");
            l_accHistoryParams2.setLastUpdater("admin");
            //顧客名称
            l_accHistoryParams2.setFamilyName("abcff");
            //口座開設区分
            l_accHistoryParams2.setAccOpenKindDiv("1");
            //登録タイプ
            l_accHistoryParams2.setRegistType("0");
            //有効区分
            l_accHistoryParams2.setValidDiv("5");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams1.setCampaignId(88);
            //商品コード
            l_commCampaignProductMstParams1.setCommProductCode("11");
            l_commCampaignProductMstParams1.setLastUpdater("admin");
            l_commCampaignProductMstParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams2 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams2.setCampaignId(99);
            //商品コード
            l_commCampaignProductMstParams2.setCommProductCode("15");
            l_commCampaignProductMstParams2.setLastUpdater("admin");
            l_commCampaignProductMstParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams2);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams3 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams3.setCampaignId(19);
            //商品コード
            l_commCampaignProductMstParams3.setCommProductCode("16");
            l_commCampaignProductMstParams3.setLastUpdater("admin");
            l_commCampaignProductMstParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams3);
            
            l_response = l_adminListServiceImpl.getListScreen(l_request);
            
            //0
            WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo = l_response.registAccountInfo[0];
            
            assertEquals("19",l_registAccountInfo.campaignId);
            
            assertEquals("16",l_registAccountInfo.itemCode[0]);
            
            assertEquals("あああ1",l_registAccountInfo.campaignName);
            
            assertEquals("bbb",l_registAccountInfo.branchCode);
            
            assertEquals("789546",l_registAccountInfo.accountCode);
            
            assertNull(l_registAccountInfo.accountName);
            
            assertEquals("a",l_registAccountInfo.traderCode);
            
            assertNull(l_registAccountInfo.accountOpenDiv);
            
            assertEquals(WEB3StringTypeUtility.formatNumber(123D),l_registAccountInfo.collectRate);
            
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070105","yyyyMMdd"),l_registAccountInfo.targetPeriodFrom));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070106","yyyyMMdd"),l_registAccountInfo.targetPeriodTo));
            assertNull(l_registAccountInfo.registType);
            assertNull(l_registAccountInfo.activeDiv);
            
            //1
            WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo1 = l_response.registAccountInfo[1];
            
            assertEquals("88",l_registAccountInfo1.campaignId);
            
            assertEquals("10",l_registAccountInfo1.itemCode[0]);
            assertEquals("11",l_registAccountInfo1.itemCode[1]);
            
            assertEquals("あああ3",l_registAccountInfo1.campaignName);
            
            assertEquals("bbb",l_registAccountInfo1.branchCode);
            
            assertEquals("168972",l_registAccountInfo1.accountCode);
            
            assertEquals("abcff",l_registAccountInfo1.accountName);
            
            assertEquals("b",l_registAccountInfo1.traderCode);
            
            assertEquals("1",l_registAccountInfo1.accountOpenDiv);
            
            assertEquals(WEB3StringTypeUtility.formatNumber(122D),l_registAccountInfo1.collectRate);
            
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070109","yyyyMMdd"),l_registAccountInfo1.targetPeriodFrom));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070118","yyyyMMdd"),l_registAccountInfo1.targetPeriodTo));
            assertEquals("0",l_registAccountInfo1.registType);
            assertEquals("5",l_registAccountInfo1.activeDiv);
            
            //2
            WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo2 = l_response.registAccountInfo[2];
            
            assertEquals("99",l_registAccountInfo2.campaignId);
            
            assertEquals("15",l_registAccountInfo2.itemCode[0]);
            
            assertEquals("あああ2",l_registAccountInfo2.campaignName);
            
            assertEquals("bbb",l_registAccountInfo2.branchCode);
            
            assertEquals("123456",l_registAccountInfo2.accountCode);
            
            assertNull(l_registAccountInfo2.accountName);
            
            assertEquals("b",l_registAccountInfo2.traderCode);
            
            assertNull(l_registAccountInfo2.accountOpenDiv);
            
            assertEquals(WEB3StringTypeUtility.formatNumber(122D),l_registAccountInfo2.collectRate);
            
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070113","yyyyMMdd"),l_registAccountInfo2.targetPeriodFrom));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070102","yyyyMMdd"),l_registAccountInfo2.targetPeriodTo));
            assertNull(l_registAccountInfo2.registType);
            assertNull(l_registAccountInfo2.activeDiv);
            
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("3",l_response.totalRecords);
            
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0007()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = new WEB3AdminAccInfoCampaignRegistAccListResponse();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "1";
        //要求ページ番号
        l_request.pageIndex = "1";
        //部店コード
        l_request.branchCode = "bbb";
        l_request.itemCode = "16";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());
            
            //手数料割引キャンペーン顧客履歴(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams.setAccountId(2);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams.setCampaignId(19);
            //手数料割引キャンペーン名称
            l_accHistoryParams.setCommCampaignName("あああ1");
            //証券会社コード
            l_accHistoryParams.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams.setAccountCode("7895461");
            //部店コード
            l_accHistoryParams.setBranchCode("bbb");
            //扱者コード
            l_accHistoryParams.setSonarTraderCode("a");
            //対象期間From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //徴収率
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams1.setAccountId(15);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams1.setCampaignId(99);
            //手数料割引キャンペーン名称
            l_accHistoryParams1.setCommCampaignName("あああ2");
            //証券会社コード
            l_accHistoryParams1.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams1.setAccountCode("1234567");
            //徴収率
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams1.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //部店コード
            l_accHistoryParams1.setBranchCode("bbb");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //口座ID
            l_accHistoryParams2.setAccountId(18);
            //手数料割引キャンペーン条件ID
            l_accHistoryParams2.setCampaignId(88);
            //手数料割引キャンペーン名称
            l_accHistoryParams2.setCommCampaignName("あああ3");
            //証券会社コード
            l_accHistoryParams2.setInstitutionCode("bbb");
            //顧客コード
            l_accHistoryParams2.setAccountCode("1689725");
            //徴収率
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //扱者コード
            l_accHistoryParams2.setSonarTraderCode("b");
            //対象期間From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //対象期間To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //部店コード
            l_accHistoryParams2.setBranchCode("bbb");
            l_accHistoryParams2.setLastUpdater("admin");
            //顧客名称
            l_accHistoryParams2.setFamilyName("abcff");
            //口座開設区分
            l_accHistoryParams2.setAccOpenKindDiv("1");
            //登録タイプ
            l_accHistoryParams2.setRegistType("0");
            //有効区分
            l_accHistoryParams2.setValidDiv("5");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            //手数料割引キャンペーン商品マスタ(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams3 = new CommCampaignProductMstParams();
            //手数料割引キャンペーン条件ID
            l_commCampaignProductMstParams3.setCampaignId(19);
            //商品コード
            l_commCampaignProductMstParams3.setCommProductCode("16");
            l_commCampaignProductMstParams3.setLastUpdater("admin");
            l_commCampaignProductMstParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams3);
            
            l_response = l_adminListServiceImpl.getListScreen(l_request);
            
            //0
            WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo = l_response.registAccountInfo[0];
            
            assertEquals("19",l_registAccountInfo.campaignId);
            
            assertEquals("16",l_registAccountInfo.itemCode[0]);
            
            assertEquals("あああ1",l_registAccountInfo.campaignName);
            
            assertEquals("bbb",l_registAccountInfo.branchCode);
            
            assertEquals("789546",l_registAccountInfo.accountCode);
            
            assertNull(l_registAccountInfo.accountName);
            
            assertEquals("a",l_registAccountInfo.traderCode);
            
            assertNull(l_registAccountInfo.accountOpenDiv);
            
            assertEquals(WEB3StringTypeUtility.formatNumber(123D),l_registAccountInfo.collectRate);
            
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070105","yyyyMMdd"),l_registAccountInfo.targetPeriodFrom));
            assertEquals(0,WEB3DateUtility.compare(WEB3DateUtility.getDate("20070106","yyyyMMdd"),l_registAccountInfo.targetPeriodTo));
            assertNull(l_registAccountInfo.registType);
            assertNull(l_registAccountInfo.activeDiv);
            
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("1",l_response.totalRecords);
            
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getListScreen
     */
    public void testGetListScreen_C0008()
    {
        final String STR_METHOD_NAME = ".testGetListScreen_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = new WEB3AdminAccInfoCampaignRegistAccListResponse();
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "targetPeriodFrom";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        //ソートキー
        l_request.sortKeys = l_sortKeys;
        //ページ内表示行数
        l_request.pageSize = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        try
        {
            RowType l_rowAdmin = new RowType( "administrator", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdmin);
            
            RowType l_rowAdminPermiss = new RowType( "admin_permission", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdminPermiss);
            
            RowType l_rowAdministratorType = new RowType( "administrator_type", "master" );
            TestDBUtility.deleteAllAndCommit(l_rowAdministratorType);
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());
            
            l_response = l_adminListServiceImpl.getListScreen(l_request);
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("0",l_response.totalRecords);
            assertNull(l_response.registAccountInfo);
            
        }
        catch (WEB3BaseException l_ex)
        {
            // 予想結果と実際結果の比較
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者テーブルRowを作成.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();
        
        //管理者ID
        l_administratorParams.setAdministratorId(123456);
        //管理者コード
        l_administratorParams.setAdministratorCode("aaa");
        //証券会社ID
        l_administratorParams.setInstitutionId(987654);
        //証券会社コード
        l_administratorParams.setInstitutionCode("bbb");
        //管理者ログインＩＤ
        l_administratorParams.setLoginId(0L);
        //権限レベル
        l_administratorParams.setPermissionLevel("a");
                
        return l_administratorParams;
    }
    
    /**
     * 管理者権限Rowを作成.<BR>
     */
    public AdminPermissionParams getAdminPermissionRow()
    {
        AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
        
        //証券会社コード
        l_adminPermissionParams.setInstitutionCode("bbb");
        //権限レベル
        l_adminPermissionParams.setPermissionLevel("a");
        //機@能カテゴリコード
        l_adminPermissionParams.setTransactionCategory("C0903");
        //更新許可フラグ
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
        //作成日時
        l_adminPermissionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_adminPermissionParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_adminPermissionParams;
    }
    
    /**
     * 管理者タイプRowを作成.<BR>
     */
    public AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        
        //証券会社コード
        l_administratorTypeParams.setInstitutionCode("bbb");
        //権限レベル
        l_administratorTypeParams.setPermissionLevel("a");
        //ＤＩＲ管理者フラグ
        l_administratorTypeParams.setDirAdminFlag(1);
        //全部店許可フラグ
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        //作成日時
        l_administratorTypeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_administratorTypeParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_administratorTypeParams;
    }
}
@
