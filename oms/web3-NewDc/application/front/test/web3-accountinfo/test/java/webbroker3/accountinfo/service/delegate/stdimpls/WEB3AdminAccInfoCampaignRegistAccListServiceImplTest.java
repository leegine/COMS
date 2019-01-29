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
 * �萔�������L�����y�[���o�^�ڋq�Ɖ�T�[�r�X�����N���X<BR>
 * @@author �Ј��� 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListServiceImplTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignRegistAccListServiceImplTest(String name) 
    {
        super(name);
    }

    /**
     * ���O���[�e�B���e�B<BR>
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
     * �،���ЃR�[�h + ���i�R�[�h
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("12������");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("aaa");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("12������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0003() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("����");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0004() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������123");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11222");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("12");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������123","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0005() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("02");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h + �����J�݋敪
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0006() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("2");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h + �����J�݋敪 + ������
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0007() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.11);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h + �����J�݋敪 + ������
     * + �Ώۊ���From + �Ώۊ���To 
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0008() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        //�Ώۊ���From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h + �����J�݋敪 + ������
     * + �Ώۊ���From + �Ώۊ���To + �o�^�^�C�v
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0009() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        //�Ώۊ���From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        //�o�^�^�C�v
        l_request.registType = "9";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //�o�^�^�C�v
            l_accHistoryParams.setRegistType("9");
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //�o�^�^�C�v
            l_accHistoryParams1.setRegistType("8");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
     * �،���ЃR�[�h + ���i�R�[�h + �L�����y�[������ + ���X�R�[�h + �ڋq�R�[�h + ���҃R�[�h + �����J�݋敪 + ������
     * + �Ώۊ���From + �Ώۊ���To + �o�^�^�C�v + 
     * @@throws WEB3BaseException 
     */
    public void testCreateSearchCondition_C0010() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".testCreateSearchCondition_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListServiceImpl l_adminListServiceImpl = 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl();
        
        WEB3AdminAccInfoCampaignRegistAccListRequest l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "145";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        //�Ώۊ���From
        l_request.targetDate = WEB3DateUtility.getDate("20070218","yyyyMMdd");
        
        //�o�^�^�C�v
        l_request.registType = "9";
        
        //�L���敪
        l_request.activeDiv = "1";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
            //�o�^�^�C�v
            l_accHistoryParams.setRegistType("9");
            //�L���敪
            l_accHistoryParams.setValidDiv("1");
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20071118","yyyyMMdd"));
            //�o�^�^�C�v
            l_accHistoryParams1.setRegistType("9");
            //�L���敪
            l_accHistoryParams1.setValidDiv("2");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "143";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("143");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(66);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("143");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.12);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams1.setCampaignId(66);
            //���i�R�[�h
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
                    assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
                    assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        
        //���i�R�[�h
//        l_request.itemCode = "10";
        
        //�L�����y�[������
        l_request.campaignName = "������";
        
        //���X�R�[�h
        l_request.branchCode = "144";
        
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        //���҃R�[�h
        l_request.traderCode = "01";
        
        //�����J�݋敪
        l_request.accountOpenDiv = "1";
        
        //������
        l_request.collectRate = "18.12";
        
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        //create��������������
        String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
        //create���������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
        //create�\�[�g����������
        String l_strSortSearchCondition = l_adminListServiceImpl.createSortSearchCondition(l_sortKeys);
        
        try
        {
            RowType l_rowMst = new RowType( "comm_campaign_product_mst", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowMst);
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(1);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("144");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams.setAccountChargeRatio(18.12);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("145");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("01");
            //�����J�݋敪
            l_accHistoryParams1.setAccOpenKindDiv("1");
            //������
            l_accHistoryParams1.setAccountChargeRatio(18.11);
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
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
                assertEquals("������","" + l_accHistoryParams2.getCommCampaignName());
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
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88L);
            //���i�R�[�h
            l_commCampaignProductMstParams.setCommProductCode("51");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams1.setCampaignId(88L);
            //���i�R�[�h
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
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88L);
            //���i�R�[�h
            l_commCampaignProductMstParams.setCommProductCode("51");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams1.setCampaignId(88L);
            //���i�R�[�h
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
            assertEquals("�e�[�u���ɊY������f�[�^������܂���B",l_ex.getErrorInfo().getErrorMessage());
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("b");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "traderCode";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "collectRate";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(122D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountOpenDateFrom";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountOpenDateTo";
            l_sortKey.ascDesc = "A";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //�ڋq�R�[�h
        l_request.accountCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("11");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("11");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("b");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "accountCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "traderCode";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "collectRate";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //������
            l_accHistoryParams.setAccountChargeRatio(122D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(123D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "targetPeriodFrom";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
        l_request.branchCode = "11";
        
        try
        {
            WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
            l_sortKey.keyItem = "targetPeriodTo";
            l_sortKey.ascDesc = "D";
            
            RowType l_rowHistory = new RowType( "comm_campaign_acc_history", "session" );
            TestDBUtility.deleteAllAndCommit(l_rowHistory);
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
        
        //�،���ЃR�[�h
        String l_strInstitutionCode = "123";
        //���X�R�[�h
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
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("a");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("11");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("b");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("11");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams2.setAccountId(18);
            //�萔�������L�����y�[������ID
            l_accHistoryParams2.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams2.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams2.setInstitutionCode("123");
            //�ڋq�R�[�h
            l_accHistoryParams2.setAccountCode("b");
            //������
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams2.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams2.setBranchCode("11");
            l_accHistoryParams2.setLastUpdater("admin");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey,l_sortKey1};
            
            //create��������������
            String l_strSearchCondition = l_adminListServiceImpl.createSearchCondition(l_request);
            //create���������f�[�^�R���e�i
            Object[] l_strSearchConditionContainers = l_adminListServiceImpl.createSearchConditionContainers(l_request,l_strInstitutionCode);
            //create�\�[�g����������
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //���X�R�[�h
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //���X�R�[�h
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
        l_request.pageSize = "3";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //���X�R�[�h
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
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(19);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("7895461");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("bbb");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("1234567");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("bbb");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams2.setAccountId(18);
            //�萔�������L�����y�[������ID
            l_accHistoryParams2.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams2.setCommCampaignName("������3");
            //�،���ЃR�[�h
            l_accHistoryParams2.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams2.setAccountCode("1689725");
            //������
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams2.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams2.setBranchCode("bbb");
            l_accHistoryParams2.setLastUpdater("admin");
            //�ڋq����
            l_accHistoryParams2.setFamilyName("abcff");
            //�����J�݋敪
            l_accHistoryParams2.setAccOpenKindDiv("1");
            //�o�^�^�C�v
            l_accHistoryParams2.setRegistType("0");
            //�L���敪
            l_accHistoryParams2.setValidDiv("5");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams.setCampaignId(88);
            //���i�R�[�h
            l_commCampaignProductMstParams.setCommProductCode("10");
            l_commCampaignProductMstParams.setLastUpdater("admin");
            l_commCampaignProductMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams1.setCampaignId(88);
            //���i�R�[�h
            l_commCampaignProductMstParams1.setCommProductCode("11");
            l_commCampaignProductMstParams1.setLastUpdater("admin");
            l_commCampaignProductMstParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams2 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams2.setCampaignId(99);
            //���i�R�[�h
            l_commCampaignProductMstParams2.setCommProductCode("15");
            l_commCampaignProductMstParams2.setLastUpdater("admin");
            l_commCampaignProductMstParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_commCampaignProductMstParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams2);
            
            CommCampaignProductMstParams l_commCampaignProductMstParams3 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams3.setCampaignId(19);
            //���i�R�[�h
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
            
            assertEquals("������1",l_registAccountInfo.campaignName);
            
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
            
            assertEquals("������3",l_registAccountInfo1.campaignName);
            
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
            
            assertEquals("������2",l_registAccountInfo2.campaignName);
            
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //���X�R�[�h
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
            
            //�萔�������L�����y�[���ڋq����(comm_campaign_acc_history)
            CommCampaignAccHistoryParams l_accHistoryParams = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams.setAccountId(2);
            //�萔�������L�����y�[������ID
            l_accHistoryParams.setCampaignId(19);
            //�萔�������L�����y�[������
            l_accHistoryParams.setCommCampaignName("������1");
            //�،���ЃR�[�h
            l_accHistoryParams.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams.setAccountCode("7895461");
            //���X�R�[�h
            l_accHistoryParams.setBranchCode("bbb");
            //���҃R�[�h
            l_accHistoryParams.setSonarTraderCode("a");
            //�Ώۊ���From
            l_accHistoryParams.setAppliStartDate(WEB3DateUtility.getDate("20070105","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams.setAppliEndDate(WEB3DateUtility.getDate("20070106","yyyyMMdd"));
            //������
            l_accHistoryParams.setAccountChargeRatio(123D);
            l_accHistoryParams.setLastUpdater("admin");
            l_accHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams1 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams1.setAccountId(15);
            //�萔�������L�����y�[������ID
            l_accHistoryParams1.setCampaignId(99);
            //�萔�������L�����y�[������
            l_accHistoryParams1.setCommCampaignName("������2");
            //�،���ЃR�[�h
            l_accHistoryParams1.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams1.setAccountCode("1234567");
            //������
            l_accHistoryParams1.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams1.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070113","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams1.setBranchCode("bbb");
            l_accHistoryParams1.setLastUpdater("admin");
            l_accHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            CommCampaignAccHistoryParams l_accHistoryParams2 = new CommCampaignAccHistoryParams();
            //����ID
            l_accHistoryParams2.setAccountId(18);
            //�萔�������L�����y�[������ID
            l_accHistoryParams2.setCampaignId(88);
            //�萔�������L�����y�[������
            l_accHistoryParams2.setCommCampaignName("������3");
            //�،���ЃR�[�h
            l_accHistoryParams2.setInstitutionCode("bbb");
            //�ڋq�R�[�h
            l_accHistoryParams2.setAccountCode("1689725");
            //������
            l_accHistoryParams2.setAccountChargeRatio(122D);
            //���҃R�[�h
            l_accHistoryParams2.setSonarTraderCode("b");
            //�Ώۊ���From
            l_accHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070109","yyyyMMdd"));
            //�Ώۊ���To
            l_accHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070118","yyyyMMdd"));
            //���X�R�[�h
            l_accHistoryParams2.setBranchCode("bbb");
            l_accHistoryParams2.setLastUpdater("admin");
            //�ڋq����
            l_accHistoryParams2.setFamilyName("abcff");
            //�����J�݋敪
            l_accHistoryParams2.setAccOpenKindDiv("1");
            //�o�^�^�C�v
            l_accHistoryParams2.setRegistType("0");
            //�L���敪
            l_accHistoryParams2.setValidDiv("5");
            l_accHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_accHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accHistoryParams);
            TestDBUtility.insertWithDel(l_accHistoryParams1);
            TestDBUtility.insertWithDel(l_accHistoryParams2);
            
            //�萔�������L�����y�[�����i�}�X�^(comm_campaign_product_mst)
            CommCampaignProductMstParams l_commCampaignProductMstParams3 = new CommCampaignProductMstParams();
            //�萔�������L�����y�[������ID
            l_commCampaignProductMstParams3.setCampaignId(19);
            //���i�R�[�h
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
            
            assertEquals("������1",l_registAccountInfo.campaignName);
            
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        //�y�[�W���\���s��
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
            // �\�z���ʂƎ��ی��ʂ̔�r
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
     * �Ǘ��҃e�[�u��Row���쐬.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();
        
        //�Ǘ���ID
        l_administratorParams.setAdministratorId(123456);
        //�Ǘ��҃R�[�h
        l_administratorParams.setAdministratorCode("aaa");
        //�،����ID
        l_administratorParams.setInstitutionId(987654);
        //�،���ЃR�[�h
        l_administratorParams.setInstitutionCode("bbb");
        //�Ǘ��҃��O�C���h�c
        l_administratorParams.setLoginId(0L);
        //�������x��
        l_administratorParams.setPermissionLevel("a");
                
        return l_administratorParams;
    }
    
    /**
     * �Ǘ��Ҍ���Row���쐬.<BR>
     */
    public AdminPermissionParams getAdminPermissionRow()
    {
        AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
        
        //�،���ЃR�[�h
        l_adminPermissionParams.setInstitutionCode("bbb");
        //�������x��
        l_adminPermissionParams.setPermissionLevel("a");
        //�@@�\�J�e�S���R�[�h
        l_adminPermissionParams.setTransactionCategory("C0903");
        //�X�V���t���O
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
        //�쐬����
        l_adminPermissionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //�X�V����
        l_adminPermissionParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_adminPermissionParams;
    }
    
    /**
     * �Ǘ��҃^�C�vRow���쐬.<BR>
     */
    public AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        
        //�،���ЃR�[�h
        l_administratorTypeParams.setInstitutionCode("bbb");
        //�������x��
        l_administratorTypeParams.setPermissionLevel("a");
        //�c�h�q�Ǘ��҃t���O
        l_administratorTypeParams.setDirAdminFlag(1);
        //�S���X���t���O
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        //�쐬����
        l_administratorTypeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //�X�V����
        l_administratorTypeParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_administratorTypeParams;
    }
}
@
