head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AdminAccInfoCampaignCommonTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[�����ʃT�[�r�XImpl(WEB3AdminAccInfoCampaignCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/05 �g��i (���u) �V�K�쐬
*/
package webbroker3.accountinfo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignCondMstParams;
import webbroker3.accountinfo.data.CommCampaignCondMstRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignCommonTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignCommon.class);

    public WEB3AdminAccInfoCampaignCommonTest(String arg0)
    {
        super(arg0);
    }

    //CommCampaignCondMst
    CommCampaignCondMstParams l_commCampaignCondMstParams = new CommCampaignCondMstParams();

    //CommCampaignProductMst
    CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();

    protected void setUp() throws Exception
    {
        super.setUp();


        //CommCampaignCondMst
        TestDBUtility.deleteAllAndCommit(l_commCampaignCondMstParams.getRowType());
        l_commCampaignCondMstParams = TestDBUtility.getCommCampaignCondMstRow();
        l_commCampaignCondMstParams.setCampaignId(1906);
        l_commCampaignCondMstParams.setInstitutionCode("369");
        l_commCampaignCondMstParams.setBranchCode("369");
        l_commCampaignCondMstParams.setAccountCode("190600");
        l_commCampaignCondMstParams.setSonarTraderCode("1906");
        //CommCampaignProductMst
        TestDBUtility.deleteAll(l_commCampaignProductMstParams.getRowType());
        l_commCampaignProductMstParams = TestDBUtility.getCommCampaignProductMstRow();
        l_commCampaignProductMstParams.setCampaignId(1906);
        l_commCampaignProductMstParams.setCommProductCode("33");
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignItemMasterList(List)'
     */
    public void test_getCampaignItemMasterList_C0001()
    {
        final String STR_METHOD_NAME = " test_getCampaignItemMasterList_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        List l_lisCampaignConditionMasterList = new ArrayList();
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            List l_lisCampaignItemMasterList =
                l_accInfoCampaignCommon.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
            log.debug(l_lisCampaignItemMasterList.toString());
            if (l_lisCampaignItemMasterList != null)
            {
                int l_intlength = l_lisCampaignItemMasterList.size();
                assertEquals(l_intlength, 0);
            }
            else
            {
                log.debug(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignItemMasterList(List)'
     */
    public void test_getCampaignItemMasterList_C0002()
    {
        final String STR_METHOD_NAME = " test_getCampaignItemMasterList_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        List l_lisCampaignConditionMasterList = new ArrayList();
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        //CommCampaignCondMst
        l_lisCampaignConditionMasterList.add(0, l_commCampaignCondMstParams);

        try
        {
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            List l_lisCampaignItemMasterList =
                l_accInfoCampaignCommon.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
            if (l_lisCampaignItemMasterList != null)
            {
                int l_intlength = l_lisCampaignItemMasterList.size();
                assertEquals(l_intlength, 1);
            }
            else
            {
                log.debug(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignItemMasterList(List)'
     */
    public void test_getCampaignItemMasterList_C0003()
    {
        final String STR_METHOD_NAME = " test_getCampaignItemMasterList_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        List l_lisCampaignConditionMasterList = new ArrayList();
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //CommCampaignCondMst
            CommCampaignCondMstParams l_commCampaignCondMstParamsForSave1 = new CommCampaignCondMstParams();
            l_commCampaignCondMstParamsForSave1 = this.getCommCampaignCondMstRow();
            l_lisCampaignConditionMasterList.add(0, l_commCampaignCondMstParamsForSave1);

            l_commCampaignCondMstParams.setCampaignId(1907);
            l_commCampaignCondMstParams.setInstitutionCode("368");
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            CommCampaignCondMstParams l_commCampaignCondMstParamsForSave2 = new CommCampaignCondMstParams();
            l_commCampaignCondMstParamsForSave2 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParamsForSave2.setCampaignId(1907);
            l_commCampaignCondMstParamsForSave2.setInstitutionCode("368");
            l_lisCampaignConditionMasterList.add(1, l_commCampaignCondMstParamsForSave2);

            l_commCampaignCondMstParams.setCampaignId(1908);
            l_commCampaignCondMstParams.setInstitutionCode("367");
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            CommCampaignCondMstParams l_commCampaignCondMstParamsForSave3 = new CommCampaignCondMstParams();
            l_commCampaignCondMstParamsForSave3 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParamsForSave3.setCampaignId(1908);
            l_commCampaignCondMstParamsForSave3.setInstitutionCode("367");
            l_lisCampaignConditionMasterList.add(2, l_commCampaignCondMstParamsForSave3);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            l_commCampaignProductMstParams.setCampaignId(1907);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            l_commCampaignProductMstParams.setCampaignId(1908);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);

            List l_lisCampaignItemMasterList =
                l_accInfoCampaignCommon.getCampaignItemMasterList(l_lisCampaignConditionMasterList);

            if (l_lisCampaignItemMasterList != null)
            {
                int l_intlength = l_lisCampaignItemMasterList.size();
                assertEquals(l_intlength, 3);
            }
            else
            {
                log.debug(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0001()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            String[] l_strRegistTypes = {"1"};
            /*setCampaignCondition
             * setCampaignCondition(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strCampaignSearchConditionId,
            String l_strInstitutionCode,
            String[] l_strRegistTypes)
             * */
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    l_strRegistTypes);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //�ڋq�R�[�h
            l_searchCondition.setAccountCode("190600");
            //���i�R�[�h
            String[] l_strItemCodes = {"33"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //�L�����y�[������
            l_searchCondition.setCampaignName("sino");
            //�Ώۓ�
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //������
            l_searchCondition.setCollectRate("123.12345");
            //���҃R�[�h
            l_searchCondition.setTraderCode("1906");
            //�����J�݋敪
            l_searchCondition.setAccountOpenDiv("3");
            //�폜�t���O
            l_searchCondition.setDeleteFlag("1");
            //�Ώۊ���From�����ǉ�
            l_searchCondition.setTargetPeriodFrom("20070101");
            //�����J�ݓ�From����
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //�o�^�^�C�v�����ǉ�
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0002()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();

            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1910L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0003()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();

            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1910L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0004()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1909L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0005()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0005()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0006()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "collectRate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1909L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0007()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "collectRate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1910L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0008()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0008()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "traderCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1910L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0009()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0009()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "traderCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0010()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0010()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateFrom";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0011()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0011()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateFrom";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1908L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0012()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0012()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateTo";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0013()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0013()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateTo";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1908L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0014()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0014()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "registDate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0015()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0015()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "registDate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1908L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0016()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0016()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "updateDate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0017()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0017()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "updateDate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1908L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0018()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0018()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //CommCampaignCondMst
            this.getFiveCommCampaignCondMstRow();
            //CampaignProductMst
            this.getThreeCampaignProductMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            String[] l_strRegistTypes = {"1", "0"};
            /*setCampaignCondition
             * setCampaignCondition(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strCampaignSearchConditionId,
            String l_strInstitutionCode,
            String[] l_strRegistTypes)
             * */
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    l_strRegistTypes);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //�ڋq�R�[�h
            l_searchCondition.setAccountCode("190600");
            //���i�R�[�h
            String[] l_strItemCodes = {"33", "44"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //�L�����y�[������
            l_searchCondition.setCampaignName("sino");
            //�Ώۓ�
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //������
            l_searchCondition.setCollectRate("123.12345");
            //���҃R�[�h
            l_searchCondition.setTraderCode("1906");
            //�����J�݋敪
            l_searchCondition.setAccountOpenDiv("3");
            //�폜�t���O
            l_searchCondition.setDeleteFlag("1");
            //�Ώۊ���From�����ǉ�
            l_searchCondition.setTargetPeriodFrom("20070101");
            //�����J�ݓ�From����
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //�o�^�^�C�v�����ǉ�
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0019()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0019()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            this.getThreeCampaignProductMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[3];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_sortKeys[1] = new WEB3AccInfoSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            l_sortKeys[2] = new WEB3AccInfoSortKey();
            l_sortKeys[2].keyItem = "traderCode";
            l_sortKeys[2].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            String[] l_strRegistTypes = {"1"};
            /*setCampaignCondition
             * setCampaignCondition(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strCampaignSearchConditionId,
            String l_strInstitutionCode,
            String[] l_strRegistTypes)
             * */
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    l_strRegistTypes);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //�ڋq�R�[�h
            l_searchCondition.setAccountCode("190600");
            //���i�R�[�h
            String[] l_strItemCodes = {"33", "44"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //�L�����y�[������
            l_searchCondition.setCampaignName("sino");
            //�Ώۓ�
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //������
            l_searchCondition.setCollectRate("123.12345");
            //���҃R�[�h
            l_searchCondition.setTraderCode("1906");
            //�����J�݋敪
            l_searchCondition.setAccountOpenDiv("3");
            //�폜�t���O
            l_searchCondition.setDeleteFlag("1");
            //�Ώۊ���From�����ǉ�
            l_searchCondition.setTargetPeriodFrom("20070101");
            //�����J�ݓ�From����
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //�o�^�^�C�v�����ǉ�
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_createSortCondition_C0020()
    {

        final String STR_METHOD_NAME = " test_createSortCondition_C0020()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            this.getFiveCommCampaignCondMstRow();
            this.getThreeCampaignProductMstRow();
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[4];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateFrom";
            l_sortKeys[0].ascDesc = "A";
            l_sortKeys[1] = new WEB3AccInfoSortKey();
            l_sortKeys[1].keyItem = "accountOpenDateTo";
            l_sortKeys[1].ascDesc = "D";
            l_sortKeys[2] = new WEB3AccInfoSortKey();
            l_sortKeys[2].keyItem = "registDate";
            l_sortKeys[2].ascDesc = "A";
            l_sortKeys[3] = new WEB3AccInfoSortKey();
            l_sortKeys[3].keyItem = "updateDate";
            l_sortKeys[3].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //���X�R�[�h
            l_searchCondition.setBranchCode("369");
            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 5);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1908L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(1)).getCampaignId(), 1907L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(2)).getCampaignId(), 1909L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(3)).getCampaignId(), 1910L);
                assertEquals(((CommCampaignCondMstRow)l_result.get(4)).getCampaignId(), 1906L);
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createAccInfoCampaignInfo(List, List)'
     */
    public void test_createAccInfoCampaignInfo_C0001()
    {

        final String STR_METHOD_NAME = " test_createAccInfoCampaignInfo_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
                WEB3AdminAccInfoCampaignCommon.getInstance();

            List l_lisCampaignConditionMasterList = new ArrayList();
            List l_lisCampaignItemMasterList = new ArrayList();

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            if (l_accInfoCampaignInfos != null)
            {
                assertEquals(l_accInfoCampaignInfos.length, 0);
            }
            else
            {
                log.debug(STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                log.info(TEST_END + STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createAccInfoCampaignInfo(List, List)'
     */
    public void test_createAccInfoCampaignInfo_C0002()
    {

        final String STR_METHOD_NAME = " test_createAccInfoCampaignInfo_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);

            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
                WEB3AdminAccInfoCampaignCommon.getInstance();

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            if (l_accInfoCampaignInfos != null)
            {
                assertEquals(l_accInfoCampaignInfos.length, 1);
                assertEquals(l_accInfoCampaignInfos[0].campaignId, "1906");
                assertEquals(l_accInfoCampaignInfos[0].campaignName, "sino");
                assertEquals(l_accInfoCampaignInfos[0].institutionCode, "369");
                assertEquals(l_accInfoCampaignInfos[0].branchCode, "369");
                assertEquals(l_accInfoCampaignInfos[0].accountCode, "190600");
                assertEquals(l_accInfoCampaignInfos[0].accountName, null);
                assertEquals(l_accInfoCampaignInfos[0].itemCode[0], "33");
                assertEquals(l_accInfoCampaignInfos[0].targetPeriodFrom, null);
                assertEquals(l_accInfoCampaignInfos[0].targetPeriodTo, null);
                assertEquals(l_accInfoCampaignInfos[0].collectRate, "123.12345");
                assertEquals(l_accInfoCampaignInfos[0].accopenPassPeriodMonth, null);
                assertEquals(l_accInfoCampaignInfos[0].accopenPassPeriodDay, null);
                assertEquals(l_accInfoCampaignInfos[0].traderCode, "1906");
                assertEquals(l_accInfoCampaignInfos[0].accountOpenDiv, null);
                assertEquals(l_accInfoCampaignInfos[0].accountOpenDateFrom, null);
                assertEquals(l_accInfoCampaignInfos[0].accountOpenDateTo, null);
                assertEquals(l_accInfoCampaignInfos[0].registType, "0");
                assertEquals(l_accInfoCampaignInfos[0].deleteFlag, null);
                assertEquals(l_accInfoCampaignInfos[0].transactionDiv, null);
                assertEquals(l_accInfoCampaignInfos[0].registrant, "admin");
                assertEquals(WEB3DateUtility.compareToDay(l_accInfoCampaignInfos[0].updateDate,
                        WEB3DateUtility.getDate("20070101","yyyyMMdd")), 0);
            }
            else
            {
                log.debug(STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                log.info(TEST_END + STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createAccInfoCampaignInfo(List, List)'
     */
    public void test_createAccInfoCampaignInfo_C0003()
    {

        final String STR_METHOD_NAME = " test_createAccInfoCampaignInfo_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            boolean l_blnGetFiveRow = this.getFiveCommCampaignCondMstRow();
            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList = this.setFiveCommCampaignCondMstRowToList(
                    l_blnGetFiveRow, l_lisCampaignConditionMasterList);

            List l_lisCampaignItemMasterList = new ArrayList();
            boolean l_campaignProductMst = this.getThreeCampaignProductMstRow();
            l_lisCampaignItemMasterList = this.setThreeCampaignProductMstRow(
                    l_campaignProductMst, l_lisCampaignItemMasterList);

            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
                WEB3AdminAccInfoCampaignCommon.getInstance();

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            assertEquals(l_accInfoCampaignInfos.length, 5);
            assertEquals(l_accInfoCampaignInfos[0].campaignId, "1906");
            assertEquals(l_accInfoCampaignInfos[0].itemCode[0], "33");
            assertEquals(l_accInfoCampaignInfos[1].campaignId, "1907");
            assertEquals(l_accInfoCampaignInfos[1].itemCode[0], "33");
            assertEquals(l_accInfoCampaignInfos[2].campaignId, "1908");
            assertEquals(l_accInfoCampaignInfos[2].itemCode[0], "44");
            assertEquals(l_accInfoCampaignInfos[3].campaignId, "1909");
            assertEquals(l_accInfoCampaignInfos[3].itemCode, null);
            assertEquals(l_accInfoCampaignInfos[4].campaignId, "1910");
            assertEquals(l_accInfoCampaignInfos[4].itemCode, null);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.validateDeleteFlag(String)'
     */
    public void test_validateDeleteFlag_C0001()
    {
        final String STR_METHOD_NAME = " test_validateDeleteFlag_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);
            l_accInfoCampaignCommon.validateDeleteFlag("1906");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02727, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.validateDeleteFlag(String)'
     */
    public void test_validateDeleteFlag_C0002()
    {
        final String STR_METHOD_NAME = " test_validateDeleteFlag_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);
            l_accInfoCampaignCommon.validateDeleteFlag("1907");
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getAllRecordCount(WEB3AccInfoCampaignSearchCondition,
     * String, String[])'
     */
    public void test_getAllRecordCount_C0001()
    {
        final String STR_METHOD_NAME = " test_getAllRecordCount_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);

            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            l_campaignSearchCondition.itemCode = null;
            String[] l_strRegistTypes = {"1"};
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    l_strRegistTypes);
            int l_intGetAllRecordCount =
                l_accInfoCampaignCommon.getAllRecordCount(l_campaignSearchCondition, "369", l_strRegistTypes);
            assertEquals(l_intGetAllRecordCount, 1);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getAllRecordCount(WEB3AccInfoCampaignSearchCondition,
     * String, String[])'
     */
    public void test_getAllRecordCount_C0002()
    {
        final String STR_METHOD_NAME = " test_getAllRecordCount_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);

            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            l_campaignSearchCondition.itemCode = "33";
            String[] l_strRegistTypes = {"1"};
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    l_strRegistTypes);
            int l_intGetAllRecordCount =
                l_accInfoCampaignCommon.getAllRecordCount(l_campaignSearchCondition, "369", l_strRegistTypes);
            assertEquals(l_intGetAllRecordCount, 1);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignCondition(String)'
     */
    public void test_getCampaignCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_getCampaignCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);

            l_accInfoCampaignCommon.getCampaignCondition("1906");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02727, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignCondition(String)'
     */
    public void test_getCampaignCondition_C0002()
    {
        final String STR_METHOD_NAME = " test_getCampaignCondition_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);

            l_accInfoCampaignCommon.getCampaignCondition("1906");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignCondition(String)'
     */
    public void test_getCampaignCondition_C0003()
    {
        final String STR_METHOD_NAME = " test_getCampaignCondition_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);

            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = l_accInfoCampaignCommon.getCampaignCondition("1907");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getSameCampaignCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_getSameCampaignCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //���X�R�[�h
        l_searchCondition.setBranchCode("369");
        //�폜�t���O
        l_searchCondition.setDeleteFlag("0");

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getSameCampaignCondition(l_searchCondition);
            assertNull(l_accInfoCampaignInfos);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getSameCampaignCondition_C0002()
    {
        final String STR_METHOD_NAME = " test_getSameCampaignCondition_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //���X�R�[�h
        l_searchCondition.setBranchCode("369");
        //�폜�t���O
        l_searchCondition.setDeleteFlag("0");

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);
            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getSameCampaignCondition(l_searchCondition);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getSameCampaignCondition_C0003()
    {
        final String STR_METHOD_NAME = " test_getSameCampaignCondition_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //���X�R�[�h
        l_searchCondition.setBranchCode("369");
        //�폜�t���O
        l_searchCondition.setDeleteFlag("0");

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);
            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getSameCampaignCondition(l_searchCondition);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);

            assertEquals(l_accInfoCampaignInfos.length, l_accInfoCampaignInfosExpect.length);
            assertEquals(l_accInfoCampaignInfos[0].campaignId, l_accInfoCampaignInfosExpect[0].campaignId);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getSameCampaignCondition_C0004()
    {
        final String STR_METHOD_NAME = " test_getSameCampaignCondition_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //���X�R�[�h
        l_searchCondition.setBranchCode("369");
        //�폜�t���O
        l_searchCondition.setDeleteFlag(null);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag(null);
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);
            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getSameCampaignCondition(l_searchCondition);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);

            assertEquals(l_accInfoCampaignInfos.length, l_accInfoCampaignInfosExpect.length);
            assertEquals(l_accInfoCampaignInfos[0].campaignId, l_accInfoCampaignInfosExpect[0].campaignId);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_insertCampaignCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_insertCampaignCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            //�萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������ = �i�����j�o�^���.�萔�������L�����y�[������
            l_accInfoCampaignInfo.campaignName = "sino";
            //�萔�������L�����y�[�������}�X�^�s.�،���ЃR�[�h = �i�����j�o�^���.�،���ЃR�[�h
            l_accInfoCampaignInfo.institutionCode = "369";
            //�萔�������L�����y�[�������}�X�^�s.���X�R�[�h = �i�����j�o�^���.���X�R�[�h
            l_accInfoCampaignInfo.branchCode = "368";
            //�萔�������L�����y�[�������}�X�^�s.�ڋq�R�[�h = �i�����j�o�^���.�ڋq�R�[�h
            l_accInfoCampaignInfo.accountCode = "190600";
            //�萔�������L�����y�[�������}�X�^�s.�ڋq���� = �i�����j�o�^���.�ڋq����
            //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
            //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���From = �i�����j�o�^���.�Ώۊ���From
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To = �i�����j�o�^���.�Ώۊ���To
            //�萔�������L�����y�[�������}�X�^�s.�ڋq������ = �i�����j�o�^���.������
            l_accInfoCampaignInfo.collectRate = "123.12345";
            //�萔�������L�����y�[�������}�X�^�s.���҃R�[�h = �i�����j�o�^���.���҃R�[�h
            l_accInfoCampaignInfo.traderCode = "1906";
            //�萔�������L�����y�[�������}�X�^�s.�����J�݋敪 = �i�����j�o�^���.�����J�݋敪
            //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From = �i�����j�o�^���.�����J�ݓ�From
            //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To = �i�����j�o�^���.�����J�ݓ�To
            //�萔�������L�����y�[�������}�X�^�s.�o�^�^�C�v = �i�����j�o�^���.�o�^�^�C�v
            l_accInfoCampaignInfo.registType = "1";
            //�萔�������L�����y�[�������}�X�^�s.�����敪 = "0"
            l_accInfoCampaignInfo.transactionDiv = "1906";
            //�萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
            //�萔�������L�����y�[�������}�X�^�s.�쐬���� = ���ݓ���
            //�萔�������L�����y�[�������}�X�^�s.�X�V���� = ���ݓ���
            l_accInfoCampaignInfo.updateDate = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            String[] l_strItemCodes = {"33"};
            l_accInfoCampaignInfo.itemCode = l_strItemCodes;

            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            l_accInfoCampaignCommon.insertCampaignCondition(l_accInfoCampaignInfo, "admin");

            //��������������
            String l_strSearchCondition = " comm_product_code = ? and last_updater = ? ";
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_searchContainers =
                new Object[2];
            l_searchContainers[0] = "33";
            l_searchContainers[1] = "admin";
            if(l_searchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_searchContainers.toString());
                for (int i = 0; i < l_searchContainers.length; i++)
                {
                    log.debug(i + ":" + l_searchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignProductMstRow.TYPE, l_strSearchCondition, null, null, l_searchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignProductMstRow)l_result.get(0)).getLastUpdater(), "admin");
            }

            //��������������
            l_strSearchCondition = " institution_code = ? and regist_type = ? ";
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            String[] l_strSearchContainers = new String[2];
            l_strSearchContainers[0] = "369";
            l_strSearchContainers[1] = "1";
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstParams.TYPE, l_strSearchCondition, l_strSortCondition, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getBranchCode(), "368");
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getInstitutionCode(), "369");
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.updataCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.deleteCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_updateCampaignCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_updateCampaignCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_accInfoCampaignInfo.campaignId = "1906";
            //�萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������ = �i�����j�o�^���.�萔�������L�����y�[������
            l_accInfoCampaignInfo.campaignName = "sino";
            //�萔�������L�����y�[�������}�X�^�s.�،���ЃR�[�h = �i�����j�o�^���.�،���ЃR�[�h
            l_accInfoCampaignInfo.institutionCode = "369";
            //�萔�������L�����y�[�������}�X�^�s.���X�R�[�h = �i�����j�o�^���.���X�R�[�h
            l_accInfoCampaignInfo.branchCode = "369";
            //�萔�������L�����y�[�������}�X�^�s.�ڋq�R�[�h = �i�����j�o�^���.�ڋq�R�[�h
            l_accInfoCampaignInfo.accountCode = "190600";
            //�萔�������L�����y�[�������}�X�^�s.�ڋq���� = �i�����j�o�^���.�ڋq����
            //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "01";
            //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
            l_accInfoCampaignInfo.accopenPassPeriodDay = "07";
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���From = �i�����j�o�^���.�Ώۊ���From
            l_accInfoCampaignInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070715", "yyyyMMdd");
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To = �i�����j�o�^���.�Ώۊ���To
            l_accInfoCampaignInfo.targetPeriodTo = WEB3DateUtility.getDate("20070815", "yyyyMMdd");
            //�萔�������L�����y�[�������}�X�^�s.�ڋq������ = �i�����j�o�^���.������
            l_accInfoCampaignInfo.collectRate = "123";
            //�萔�������L�����y�[�������}�X�^�s.���҃R�[�h = �i�����j�o�^���.���҃R�[�h
            l_accInfoCampaignInfo.traderCode = "1906";
            //�萔�������L�����y�[�������}�X�^�s.�����J�݋敪 = �i�����j�o�^���.�����J�݋敪
            //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From = �i�����j�o�^���.�����J�ݓ�From
            l_accInfoCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20070816", "yyyyMMdd");
            //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To = �i�����j�o�^���.�����J�ݓ�To
            l_accInfoCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070829", "yyyyMMdd");
            //�萔�������L�����y�[�������}�X�^�s.�o�^�^�C�v = �i�����j�o�^���.�o�^�^�C�v
            l_accInfoCampaignInfo.registType = "0";
            //�萔�������L�����y�[�������}�X�^�s.�����敪 = "0"
            l_accInfoCampaignInfo.transactionDiv = "1906";
            //�萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
            String l_strUpdaterCode = "admin";
            //�萔�������L�����y�[�������}�X�^�s.�쐬���� = ���ݓ���
            //�萔�������L�����y�[�������}�X�^�s.�X�V���� = ���ݓ���
            l_accInfoCampaignInfo.updateDate = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            String[] l_strItemCodes = {"44"};
            l_accInfoCampaignInfo.itemCode = l_strItemCodes;

            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            //updateCampaignCondition()
            l_accInfoCampaignCommon.updateCampaignCondition(l_accInfoCampaignInfo, l_strUpdaterCode);

            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);

            //��������������
            String l_strSearchCondition = "campaign_id = ?";
            log.debug("��������������:" + l_strSearchCondition);

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                new Object[1];
            l_strSearchContainers[0] = "1906";
            if(l_strSearchContainers != null)
            {
                log.debug("�����f�[�^�R���e�i:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, null, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);

                //�R�������F "acc_open_pass_month" / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("acc_open_pass_month"), "01");
                //�R�������F "acc_open_pass_date" / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("acc_open_pass_date"), "07");
                //�R�������F "appli_start_date" / �l�F �i�����j�ύX����.�Ώۊ���From
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAppliStartDate(), WEB3DateUtility.getDate("20070715", "yyyyMMdd"));
                //�R�������F "appli_end_date" / �l�F �i�����j�ύX����.�Ώۊ���To
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAppliEndDate(), WEB3DateUtility.getDate("20070815", "yyyyMMdd"));
                //�R�������F "account_charge_ratio" / �l�F �i�����j�ύX����.������
                assertEquals(new Double(((CommCampaignCondMstRow)l_result.get(0)).getAccountChargeRatio()),
                        new Double(Double.parseDouble("123")));
                //�R�������F "acc_open_date_from " / �l�F �i�����j�ύX����.�����J�ݓ�From
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAccOpenDateFrom(), WEB3DateUtility.getDate("20070816", "yyyyMMdd"));
                //�R�������F "lacc_open_date_to" / �l�F �i�����j�ύX����.�����J�ݓ�To
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAccOpenDateTo(), WEB3DateUtility.getDate("20070829", "yyyyMMdd"));
                //�R�������F "status" / �l�F "0"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("status"), "0");
                //�R�������F "last_updater" / �l�F �i�����j�X�V�҃R�[�h
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getLastUpdater(), "admin");

            }

            //deleteCampaignCondition()
            l_accInfoCampaignCommon.deleteCampaignCondition("1906", l_strUpdaterCode);
            l_result = Processors.getDefaultProcessor().doFindAllQuery(
                    CommCampaignCondMstRow.TYPE, l_strSearchCondition, null, null, l_strSearchContainers);
            if (l_result != null)
            {
                int l_intLenth = l_result.size();
                assertEquals(l_intLenth, 1);
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getCampaignId(), 1906L);

                // �R�������F "delete_flag" / �l�F "1"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getDeleteFlag(), "1");
                //�R�������F "status" / �l�F "0"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getStatus(), "0");
                //�R�������F "last_updater" / �l�F �i�����j�X�V�҃R�[�h
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getLastUpdater(), "admin");
                //�R�������F "last_updated_timestamp" / �l�F ���ݓ���
                WEB3DateUtility.compareToDay(
                    ((CommCampaignCondMstRow)l_result.get(0)).getLastUpdatedTimestamp(),
                    GtlUtils.getSystemTimestamp());
            }

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignList(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getCampaignList_C0001()
    {
        final String STR_METHOD_NAME = " test_getCampaignList_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            //�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ꗗظ���
            WEB3AdminAccInfoCampaignIndiviListRequest l_adminAccInfoCampaignIndiviListRequest =
                new WEB3AdminAccInfoCampaignIndiviListRequest();

            l_adminAccInfoCampaignIndiviListRequest.campaignSearchItem = l_campaignSearchCondition;
            l_adminAccInfoCampaignIndiviListRequest.sortKeys = l_sortKeys;
            l_adminAccInfoCampaignIndiviListRequest.pageSize = "1";
            l_adminAccInfoCampaignIndiviListRequest.pageIndex = "0";

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignIndiviListRequest, "369", null);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            assertEquals(l_accInfoCampaignInfos.length, l_accInfoCampaignInfosExpect.length);
            if (l_accInfoCampaignInfos.length >= 0)
            {
                assertEquals(
                        l_accInfoCampaignInfos[0].campaignId,
                        l_accInfoCampaignInfosExpect[0].campaignId);
            }
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignList(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getCampaignList_C0002()
    {
        final String STR_METHOD_NAME = " test_getCampaignList_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            //�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���
            WEB3AdminAccInfoCampaignAccOpenListRequest l_adminAccInfoCampaignAccOpenListRequest =
                new WEB3AdminAccInfoCampaignAccOpenListRequest();
            l_adminAccInfoCampaignAccOpenListRequest.campaignSearchItem = l_campaignSearchCondition;
            l_adminAccInfoCampaignAccOpenListRequest.sortKeys = l_sortKeys;
            l_adminAccInfoCampaignAccOpenListRequest.pageSize = "1";
            l_adminAccInfoCampaignAccOpenListRequest.pageIndex = "0";

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignAccOpenListRequest, "369", null);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            assertEquals(l_accInfoCampaignInfos.length, l_accInfoCampaignInfosExpect.length);
            if (l_accInfoCampaignInfos.length >= 0)
            {
                assertEquals(
                        l_accInfoCampaignInfos[0].campaignId,
                        l_accInfoCampaignInfosExpect[0].campaignId);
            }
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignList(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getCampaignList_C0003()
    {
        final String STR_METHOD_NAME = " test_getCampaignList_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            //�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���
            WEB3AdminAccInfoCampaignAccOpenListRequest l_adminAccInfoCampaignAccOpenListRequest =
                new WEB3AdminAccInfoCampaignAccOpenListRequest();
            l_adminAccInfoCampaignAccOpenListRequest.campaignSearchItem = l_campaignSearchCondition;
            l_adminAccInfoCampaignAccOpenListRequest.sortKeys = l_sortKeys;
            l_adminAccInfoCampaignAccOpenListRequest.pageSize = "1";
            l_adminAccInfoCampaignAccOpenListRequest.pageIndex = "0";

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignAccOpenListRequest, "369", null);

            assertEquals(l_accInfoCampaignInfos, null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignList(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getCampaignList_C0004()
    {
        final String STR_METHOD_NAME = " test_getCampaignList_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            //�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���
            WEB3AdminAccInfoCampaignAccOpenListRequest l_adminAccInfoCampaignAccOpenListRequest =
                new WEB3AdminAccInfoCampaignAccOpenListRequest();
            l_adminAccInfoCampaignAccOpenListRequest.campaignSearchItem = l_campaignSearchCondition;
            l_adminAccInfoCampaignAccOpenListRequest.sortKeys = l_sortKeys;
            l_adminAccInfoCampaignAccOpenListRequest.pageSize = "1";
            l_adminAccInfoCampaignAccOpenListRequest.pageIndex = "0";

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignAccOpenListRequest, "368", null);

            assertEquals(l_accInfoCampaignInfos, null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.debug(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignList(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_getCampaignList_C0005()
    {

        final String STR_METHOD_NAME = " test_getCampaignList_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            //�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���
            WEB3AdminAccInfoCampaignIndiviListRequest l_adminAccInfoCampaignIndiviListRequest =
                new WEB3AdminAccInfoCampaignIndiviListRequest();
            l_adminAccInfoCampaignIndiviListRequest.campaignSearchItem = l_campaignSearchCondition;
            l_adminAccInfoCampaignIndiviListRequest.sortKeys = l_sortKeys;
            l_adminAccInfoCampaignIndiviListRequest.pageSize = "1";
            l_adminAccInfoCampaignIndiviListRequest.pageIndex = "0";

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignIndiviListRequest, "369", null);

            List l_lisCampaignConditionMasterList = new ArrayList();
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams);
            List l_lisCampaignItemMasterList = new ArrayList();
            l_lisCampaignItemMasterList.add(l_commCampaignProductMstParams);

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfosExpect =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            assertEquals(l_accInfoCampaignInfos.length, l_accInfoCampaignInfosExpect.length);
            if (l_accInfoCampaignInfos.length >= 0)
            {
                assertEquals(
                        l_accInfoCampaignInfos[0].campaignId,
                        l_accInfoCampaignInfosExpect[0].campaignId);
            }
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    
    }


    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.getCampaignCondition(String)'
     */
    public void test_deleteCampaignCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_deleteCampaignCondition_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    "1906",
                    "369",
                    null);

            l_accInfoCampaignCommon.deleteCampaignCondition("1906", "admin");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02727, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_exception_C0001()
    {

        final String STR_METHOD_NAME = " test_exception_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //�\�[�g�L�[
            WEB3AccInfoSortKey[] l_sortKeys = null;

            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition = null;

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);

            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_exception_C0002()
    {

        final String STR_METHOD_NAME = " test_exception_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition = null;

            //��������������
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSortCondition(WEB3AccInfoSortKey[])'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_exception_C0003()
    {

        final String STR_METHOD_NAME = " test_exception_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);

            //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition = null;

            //create�����f�[�^�R���e�i
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);

            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createAccInfoCampaignInfo(List, List)'
     */
    public void test_exception_C0004()
    {

        final String STR_METHOD_NAME = " test_exception_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
                WEB3AdminAccInfoCampaignCommon.getInstance();

            List l_lisCampaignConditionMasterList = null;
            List l_lisCampaignItemMasterList = new ArrayList();

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.createAccInfoCampaignInfo(List, List)'
     */
    public void test_exception_C0005()
    {

        final String STR_METHOD_NAME = " test_exception_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
                WEB3AdminAccInfoCampaignCommon.getInstance();

            List l_lisCampaignConditionMasterList = new ArrayList();
            List l_lisCampaignItemMasterList = null;

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.createAccInfoCampaignInfo(
                        l_lisCampaignConditionMasterList,
                        l_lisCampaignItemMasterList);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.updataCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.deleteCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_exception_C0006()
    {
        final String STR_METHOD_NAME = " test_exception_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            //�萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
            String l_strUpdaterCode = "admin";
            //deleteCampaignCondition()
            l_accInfoCampaignCommon.deleteCampaignCondition(null, l_strUpdaterCode);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'getCampaignList(WEB3GenRequest, String, String[])'
     */
    public void test_exception_C0007()
    {
        final String STR_METHOD_NAME = " test_exception_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            //�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ꗗظ���
            WEB3AdminAccInfoCampaignIndiviListRequest l_adminAccInfoCampaignIndiviListRequest = null;

            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getCampaignList(l_adminAccInfoCampaignIndiviListRequest, null, null);
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'getCampaignItemMasterList(List)'
     */
    public void test_exception_C0008()
    {
        final String STR_METHOD_NAME = " test_exception_C0008()";
        log.info(TEST_START + STR_METHOD_NAME);

        List l_lisCampaignConditionMasterList = new ArrayList();
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();

        try
        {
            l_lisCampaignConditionMasterList = null;
            List l_lisCampaignItemMasterList =
                l_accInfoCampaignCommon.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * Test method for 'getCampaignCondition(String)'
     */
    public void test_exception_C0009()
    {
        final String STR_METHOD_NAME = " test_exception_C0009()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            l_accInfoCampaignCommon.getCampaignCondition(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * Test method for 'getSameCampaignCondition(String)'
     */
    public void test_exception_C0010()
    {

        final String STR_METHOD_NAME = " test_exception_C0010()";
        log.info(TEST_START + STR_METHOD_NAME);

        //�L�����y�[�����������I�u�W�F�N�g(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //���X�R�[�h
        l_searchCondition.setBranchCode("369");
        //�폜�t���O
        l_searchCondition.setDeleteFlag("0");

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos =
                l_accInfoCampaignCommon.getSameCampaignCondition(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    
    }

    /*
     * Test method for 'getAllRecordCount(String)'
     */
    public void test_exception_C0011()
    {
        final String STR_METHOD_NAME = " test_exception_C0011()";
        log.info(TEST_START + STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            int l_intGetAllRecordCount =
                l_accInfoCampaignCommon.getAllRecordCount(null, null, null);
            assertEquals(l_intGetAllRecordCount, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    
    }

    public void test_exception_C0012()
    {

        final String STR_METHOD_NAME = " test_exception_C0012()";
        log.info(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = null;

            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            l_accInfoCampaignCommon.insertCampaignCondition(null, "admin");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    
    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.updataCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.deleteCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)'
     */
    public void test_exception_C0013()
    {
        final String STR_METHOD_NAME = " test_exception_C0013()";
        log.info(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon =
            WEB3AdminAccInfoCampaignCommon.getInstance();
        try
        {
            //�萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
            String l_strUpdaterCode = "admin";
            WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = new WEB3AccInfoCampaignInfo();

            l_accInfoCampaignInfo = null;
            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            //updateCampaignCondition()
            l_accInfoCampaignCommon.updateCampaignCondition(l_accInfoCampaignInfo, l_strUpdaterCode);

        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseException) l_ex).getErrorInfo());
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            log.info(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);

    }


    private CommCampaignCondMstParams getCommCampaignCondMstRow()
    {
        CommCampaignCondMstParams l_commCampaignCondMstParams = TestDBUtility.getCommCampaignCondMstRow();
        l_commCampaignCondMstParams.setCampaignId(1906);
        l_commCampaignCondMstParams.setInstitutionCode("369");
        l_commCampaignCondMstParams.setBranchCode("369");
        l_commCampaignCondMstParams.setAccountCode("190600");
        l_commCampaignCondMstParams.setSonarTraderCode("1906");
        l_commCampaignCondMstParams.setCampaignId(1906);
        l_commCampaignCondMstParams.setAccOpenKindDiv("3");
        l_commCampaignCondMstParams.setRegistType("1");
        l_commCampaignCondMstParams.setDeleteFlag("1");
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignCondMstParams.setAccOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignCondMstParams.setAccOpenDateTo(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_commCampaignCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));

        return l_commCampaignCondMstParams;
    }

    private boolean getFiveCommCampaignCondMstRow()
    {
        CommCampaignCondMstParams l_commCampaignCondMstParams = this.getCommCampaignCondMstRow();
        try
        {
            //No.1
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //No.2
            l_commCampaignCondMstParams.setCampaignId(1907);
            l_commCampaignCondMstParams.setAccountCode("190700");
            l_commCampaignCondMstParams.setAccountChargeRatio(125D);
            l_commCampaignCondMstParams.setSonarTraderCode("1907");
            l_commCampaignCondMstParams.setAccOpenDateFrom(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_commCampaignCondMstParams.setAccOpenDateTo(WEB3DateUtility.getDate("20061102","yyyyMMdd"));
            l_commCampaignCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //No.3
            l_commCampaignCondMstParams.setCampaignId(1908);
            l_commCampaignCondMstParams.setAccountCode("190800");
            l_commCampaignCondMstParams.setAccountChargeRatio(120D);
            l_commCampaignCondMstParams.setSonarTraderCode("1908");
            l_commCampaignCondMstParams.setAccOpenDateFrom(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_commCampaignCondMstParams.setAccOpenDateTo(WEB3DateUtility.getDate("20061002","yyyyMMdd"));
            l_commCampaignCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061002","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //No.4
            l_commCampaignCondMstParams.setCampaignId(1909);
            l_commCampaignCondMstParams.setAccountCode("191000");
            l_commCampaignCondMstParams.setAccountChargeRatio(134D);
            l_commCampaignCondMstParams.setSonarTraderCode("1909");
            l_commCampaignCondMstParams.setAccOpenDateFrom(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams.setAccOpenDateTo(WEB3DateUtility.getDate("20061202","yyyyMMdd"));
            l_commCampaignCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
            //No.5
            l_commCampaignCondMstParams.setCampaignId(1910);
            l_commCampaignCondMstParams.setAccountCode("190900");
            l_commCampaignCondMstParams.setAccountChargeRatio(100D);
            l_commCampaignCondMstParams.setSonarTraderCode("1910");
            l_commCampaignCondMstParams.setAccOpenDateFrom(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams.setAccOpenDateTo(WEB3DateUtility.getDate("20061202","yyyyMMdd"));
            l_commCampaignCondMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_commCampaignCondMstParams);
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            return false;
        }
        return true;

    }

    private boolean getThreeCampaignProductMstRow()
    {
        try
        {
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            l_commCampaignProductMstParams.setCampaignId(1907);
            l_commCampaignProductMstParams.setCommProductCode("44");
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
            l_commCampaignProductMstParams.setCampaignId(1908);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams);
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        return true;

    }

    private List setFiveCommCampaignCondMstRowToList(boolean l_blnGetFiveRow, List l_lisCampaignConditionMasterList)
    {
        CommCampaignCondMstParams l_commCampaignCondMstParams1 = this.getCommCampaignCondMstRow();
        if (l_blnGetFiveRow)
        {
            //No.1
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams1);
            //No.2
            CommCampaignCondMstParams l_commCampaignCondMstParams2 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParams2.setCampaignId(1907);
            l_commCampaignCondMstParams2.setAccountCode("190700");
            l_commCampaignCondMstParams2.setAccountChargeRatio(125D);
            l_commCampaignCondMstParams2.setSonarTraderCode("1907");
            l_commCampaignCondMstParams2.setAccOpenDateFrom(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_commCampaignCondMstParams2.setAccOpenDateTo(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_commCampaignCondMstParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_commCampaignCondMstParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061101","yyyyMMdd"));
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams2);
            //No.3
            CommCampaignCondMstParams l_commCampaignCondMstParams3 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParams3.setCampaignId(1908);
            l_commCampaignCondMstParams3.setAccountCode("190800");
            l_commCampaignCondMstParams3.setAccountChargeRatio(120D);
            l_commCampaignCondMstParams3.setSonarTraderCode("1908");
            l_commCampaignCondMstParams3.setAccOpenDateFrom(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_commCampaignCondMstParams3.setAccOpenDateTo(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_commCampaignCondMstParams3.setCreatedTimestamp(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_commCampaignCondMstParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061001","yyyyMMdd"));
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams3);
            //No.4
            CommCampaignCondMstParams l_commCampaignCondMstParams4 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParams4.setCampaignId(1909);
            l_commCampaignCondMstParams4.setAccountCode("191000");
            l_commCampaignCondMstParams4.setAccountChargeRatio(134D);
            l_commCampaignCondMstParams4.setSonarTraderCode("1909");
            l_commCampaignCondMstParams4.setAccOpenDateFrom(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams4.setAccOpenDateTo(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams4.setCreatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams4.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams4);
            //No.5
            CommCampaignCondMstParams l_commCampaignCondMstParams5 = this.getCommCampaignCondMstRow();
            l_commCampaignCondMstParams5.setCampaignId(1910);
            l_commCampaignCondMstParams5.setAccountCode("190900");
            l_commCampaignCondMstParams5.setAccountChargeRatio(100D);
            l_commCampaignCondMstParams5.setSonarTraderCode("1910");
            l_commCampaignCondMstParams5.setAccOpenDateFrom(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams5.setAccOpenDateTo(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams5.setCreatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_commCampaignCondMstParams5.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061201","yyyyMMdd"));
            l_lisCampaignConditionMasterList.add(l_commCampaignCondMstParams5);
        }
        return l_lisCampaignConditionMasterList;

    }

    private List setThreeCampaignProductMstRow(boolean l_blnGetFiveRow, List l_lisCampaignItemMasterList)
    {
            CommCampaignProductMstParams l_commCampaignCondMstParams1 = TestDBUtility.getCommCampaignProductMstRow();
            if (l_blnGetFiveRow)
            {
                l_commCampaignCondMstParams1.setCampaignId(1906);
                l_commCampaignCondMstParams1.setCommProductCode("33");
                CommCampaignProductMstParams l_commCampaignCondMstParams2 = TestDBUtility.getCommCampaignProductMstRow();
                l_commCampaignCondMstParams2.setCampaignId(1907);
                l_commCampaignCondMstParams2.setCommProductCode("33");
                CommCampaignProductMstParams l_commCampaignCondMstParams3 = TestDBUtility.getCommCampaignProductMstRow();
                l_commCampaignCondMstParams3.setCampaignId(1908);
                l_commCampaignCondMstParams3.setCommProductCode("44");
                l_lisCampaignItemMasterList.add(l_commCampaignCondMstParams1);
                l_lisCampaignItemMasterList.add(l_commCampaignCondMstParams2);
                l_lisCampaignItemMasterList.add(l_commCampaignCondMstParams3);
            }
        return l_lisCampaignItemMasterList;

    }

}
@
