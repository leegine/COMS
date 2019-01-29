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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン共通サービスImpl(WEB3AdminAccInfoCampaignCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/05 吉麗ナ (中訊) 新規作成
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
     * ログ出力ユーティリティ<BR>
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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

            //部店コード
            l_searchCondition.setBranchCode("369");
            //顧客コード
            l_searchCondition.setAccountCode("190600");
            //商品コード
            String[] l_strItemCodes = {"33"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //キャンペーン名称
            l_searchCondition.setCampaignName("sino");
            //対象日
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //徴収率
            l_searchCondition.setCollectRate("123.12345");
            //扱者コード
            l_searchCondition.setTraderCode("1906");
            //口座開設区分
            l_searchCondition.setAccountOpenDiv("3");
            //削除フラグ
            l_searchCondition.setDeleteFlag("1");
            //対象期間From条件追加
            l_searchCondition.setTargetPeriodFrom("20070101");
            //口座開設日From条件
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //登録タイプ条件追加
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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

            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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

            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "collectRate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "collectRate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "traderCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "traderCode";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateFrom";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateFrom";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateTo";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "accountOpenDateTo";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "registDate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "registDate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "updateDate";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "updateDate";
            l_sortKeys[0].ascDesc = "D";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);
            WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
                new WEB3AccInfoCampaignSearchCondition();
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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

            //部店コード
            l_searchCondition.setBranchCode("369");
            //顧客コード
            l_searchCondition.setAccountCode("190600");
            //商品コード
            String[] l_strItemCodes = {"33", "44"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //キャンペーン名称
            l_searchCondition.setCampaignName("sino");
            //対象日
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //徴収率
            l_searchCondition.setCollectRate("123.12345");
            //扱者コード
            l_searchCondition.setTraderCode("1906");
            //口座開設区分
            l_searchCondition.setAccountOpenDiv("3");
            //削除フラグ
            l_searchCondition.setDeleteFlag("1");
            //対象期間From条件追加
            l_searchCondition.setTargetPeriodFrom("20070101");
            //口座開設日From条件
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //登録タイプ条件追加
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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

            //部店コード
            l_searchCondition.setBranchCode("369");
            //顧客コード
            l_searchCondition.setAccountCode("190600");
            //商品コード
            String[] l_strItemCodes = {"33", "44"};
            l_searchCondition.setItemCode(l_strItemCodes);
            //キャンペーン名称
            l_searchCondition.setCampaignName("sino");
            //対象日
            l_searchCondition.setTargetDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //徴収率
            l_searchCondition.setCollectRate("123.12345");
            //扱者コード
            l_searchCondition.setTraderCode("1906");
            //口座開設区分
            l_searchCondition.setAccountOpenDiv("3");
            //削除フラグ
            l_searchCondition.setDeleteFlag("1");
            //対象期間From条件追加
            l_searchCondition.setTargetPeriodFrom("20070101");
            //口座開設日From条件
            l_searchCondition.setAccountOpenDateFrom(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            //登録タイプ条件追加
            l_searchCondition.setRegisterType(l_strRegistTypes);

            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //ソートキー
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
                new WEB3AdminAccInfoCampaignSearchCondition();
            //setCampaignCondition
            l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                    null,
                    "369",
                    null);

            //部店コード
            l_searchCondition.setBranchCode("369");
            //検索条件文字列
            String l_strSearchCondition = l_accInfoCampaignCommon.createSearchCondition(l_searchCondition);
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                l_accInfoCampaignCommon.createSearchContainers(l_searchCondition);
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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

        //キャンペーン検索条件オブジェクト(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //部店コード
        l_searchCondition.setBranchCode("369");
        //削除フラグ
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

        //キャンペーン検索条件オブジェクト(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //部店コード
        l_searchCondition.setBranchCode("369");
        //削除フラグ
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

        //キャンペーン検索条件オブジェクト(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //部店コード
        l_searchCondition.setBranchCode("369");
        //削除フラグ
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

        //キャンペーン検索条件オブジェクト(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //部店コード
        l_searchCondition.setBranchCode("369");
        //削除フラグ
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
            //手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン名称 = （引数）登録情報.手数料割引キャンペーン名称
            l_accInfoCampaignInfo.campaignName = "sino";
            //手数料割引キャンペーン条件マスタ行.証券会社コード = （引数）登録情報.証券会社コード
            l_accInfoCampaignInfo.institutionCode = "369";
            //手数料割引キャンペーン条件マスタ行.部店コード = （引数）登録情報.部店コード
            l_accInfoCampaignInfo.branchCode = "368";
            //手数料割引キャンペーン条件マスタ行.顧客コード = （引数）登録情報.顧客コード
            l_accInfoCampaignInfo.accountCode = "190600";
            //手数料割引キャンペーン条件マスタ行.顧客名称 = （引数）登録情報.顧客名称
            //手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） = （引数）登録情報.口座開設経過期間（月）
            //手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） = （引数）登録情報.口座開設経過期間（日）
            //手数料割引キャンペーン条件マスタ行.対象期間From = （引数）登録情報.対象期間From
            //手数料割引キャンペーン条件マスタ行.対象期間To = （引数）登録情報.対象期間To
            //手数料割引キャンペーン条件マスタ行.顧客徴収率 = （引数）登録情報.徴収率
            l_accInfoCampaignInfo.collectRate = "123.12345";
            //手数料割引キャンペーン条件マスタ行.扱者コード = （引数）登録情報.扱者コード
            l_accInfoCampaignInfo.traderCode = "1906";
            //手数料割引キャンペーン条件マスタ行.口座開設区分 = （引数）登録情報.口座開設区分
            //手数料割引キャンペーン条件マスタ行.口座開設日From = （引数）登録情報.口座開設日From
            //手数料割引キャンペーン条件マスタ行.口座開設日To = （引数）登録情報.口座開設日To
            //手数料割引キャンペーン条件マスタ行.登録タイプ = （引数）登録情報.登録タイプ
            l_accInfoCampaignInfo.registType = "1";
            //手数料割引キャンペーン条件マスタ行.処理区分 = "0"
            l_accInfoCampaignInfo.transactionDiv = "1906";
            //手数料割引キャンペーン条件マスタ行.更新者コード = （引数）更新者コード
            //手数料割引キャンペーン条件マスタ行.作成日時 = 現在日時
            //手数料割引キャンペーン条件マスタ行.更新日時 = 現在日時
            l_accInfoCampaignInfo.updateDate = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            String[] l_strItemCodes = {"33"};
            l_accInfoCampaignInfo.itemCode = l_strItemCodes;

            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            l_accInfoCampaignCommon.insertCampaignCondition(l_accInfoCampaignInfo, "admin");

            //検索条件文字列
            String l_strSearchCondition = " comm_product_code = ? and last_updater = ? ";
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_searchContainers =
                new Object[2];
            l_searchContainers[0] = "33";
            l_searchContainers[1] = "admin";
            if(l_searchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_searchContainers.toString());
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

            //検索条件文字列
            l_strSearchCondition = " institution_code = ? and regist_type = ? ";
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            String[] l_strSearchContainers = new String[2];
            l_strSearchContainers[0] = "369";
            l_strSearchContainers[1] = "1";
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
                for (int i = 0; i < l_strSearchContainers.length; i++)
                {
                    log.debug(i + ":" + l_strSearchContainers[i]);
                }
            }
            //ソートキー
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
            //手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン名称 = （引数）登録情報.手数料割引キャンペーン名称
            l_accInfoCampaignInfo.campaignName = "sino";
            //手数料割引キャンペーン条件マスタ行.証券会社コード = （引数）登録情報.証券会社コード
            l_accInfoCampaignInfo.institutionCode = "369";
            //手数料割引キャンペーン条件マスタ行.部店コード = （引数）登録情報.部店コード
            l_accInfoCampaignInfo.branchCode = "369";
            //手数料割引キャンペーン条件マスタ行.顧客コード = （引数）登録情報.顧客コード
            l_accInfoCampaignInfo.accountCode = "190600";
            //手数料割引キャンペーン条件マスタ行.顧客名称 = （引数）登録情報.顧客名称
            //手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） = （引数）登録情報.口座開設経過期間（月）
            l_accInfoCampaignInfo.accopenPassPeriodMonth = "01";
            //手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） = （引数）登録情報.口座開設経過期間（日）
            l_accInfoCampaignInfo.accopenPassPeriodDay = "07";
            //手数料割引キャンペーン条件マスタ行.対象期間From = （引数）登録情報.対象期間From
            l_accInfoCampaignInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070715", "yyyyMMdd");
            //手数料割引キャンペーン条件マスタ行.対象期間To = （引数）登録情報.対象期間To
            l_accInfoCampaignInfo.targetPeriodTo = WEB3DateUtility.getDate("20070815", "yyyyMMdd");
            //手数料割引キャンペーン条件マスタ行.顧客徴収率 = （引数）登録情報.徴収率
            l_accInfoCampaignInfo.collectRate = "123";
            //手数料割引キャンペーン条件マスタ行.扱者コード = （引数）登録情報.扱者コード
            l_accInfoCampaignInfo.traderCode = "1906";
            //手数料割引キャンペーン条件マスタ行.口座開設区分 = （引数）登録情報.口座開設区分
            //手数料割引キャンペーン条件マスタ行.口座開設日From = （引数）登録情報.口座開設日From
            l_accInfoCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20070816", "yyyyMMdd");
            //手数料割引キャンペーン条件マスタ行.口座開設日To = （引数）登録情報.口座開設日To
            l_accInfoCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070829", "yyyyMMdd");
            //手数料割引キャンペーン条件マスタ行.登録タイプ = （引数）登録情報.登録タイプ
            l_accInfoCampaignInfo.registType = "0";
            //手数料割引キャンペーン条件マスタ行.処理区分 = "0"
            l_accInfoCampaignInfo.transactionDiv = "1906";
            //手数料割引キャンペーン条件マスタ行.更新者コード = （引数）更新者コード
            String l_strUpdaterCode = "admin";
            //手数料割引キャンペーン条件マスタ行.作成日時 = 現在日時
            //手数料割引キャンペーン条件マスタ行.更新日時 = 現在日時
            l_accInfoCampaignInfo.updateDate = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            String[] l_strItemCodes = {"44"};
            l_accInfoCampaignInfo.itemCode = l_strItemCodes;

            l_commCampaignCondMstParams.setDeleteFlag("0");
            TestDBUtility.insertWithDel(this.l_commCampaignProductMstParams);
            TestDBUtility.insertWithDel(this.l_commCampaignCondMstParams);

            //updateCampaignCondition()
            l_accInfoCampaignCommon.updateCampaignCondition(l_accInfoCampaignInfo, l_strUpdaterCode);

            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            l_sortKeys[0] = new WEB3AccInfoSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";

            String l_strSortCondition = l_accInfoCampaignCommon.createSortCondition(l_sortKeys);
            log.debug(l_strSortCondition);

            //検索条件文字列
            String l_strSearchCondition = "campaign_id = ?";
            log.debug("検索条件文字列:" + l_strSearchCondition);

            //create検索データコンテナ
            Object[] l_strSearchContainers =
                new Object[1];
            l_strSearchContainers[0] = "1906";
            if(l_strSearchContainers != null)
            {
                log.debug("検索データコンテナ:" + l_strSearchContainers.toString());
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

                //コラム名： "acc_open_pass_month" / 値： （引数）変更後情報.口座開設経過期間（月）
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("acc_open_pass_month"), "01");
                //コラム名： "acc_open_pass_date" / 値： （引数）変更後情報.口座開設経過期間（日）
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("acc_open_pass_date"), "07");
                //コラム名： "appli_start_date" / 値： （引数）変更後情報.対象期間From
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAppliStartDate(), WEB3DateUtility.getDate("20070715", "yyyyMMdd"));
                //コラム名： "appli_end_date" / 値： （引数）変更後情報.対象期間To
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAppliEndDate(), WEB3DateUtility.getDate("20070815", "yyyyMMdd"));
                //コラム名： "account_charge_ratio" / 値： （引数）変更後情報.徴収率
                assertEquals(new Double(((CommCampaignCondMstRow)l_result.get(0)).getAccountChargeRatio()),
                        new Double(Double.parseDouble("123")));
                //コラム名： "acc_open_date_from " / 値： （引数）変更後情報.口座開設日From
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAccOpenDateFrom(), WEB3DateUtility.getDate("20070816", "yyyyMMdd"));
                //コラム名： "lacc_open_date_to" / 値： （引数）変更後情報.口座開設日To
                WEB3DateUtility.compareToDay(((CommCampaignCondMstRow)l_result.get(0)).getAccOpenDateTo(), WEB3DateUtility.getDate("20070829", "yyyyMMdd"));
                //コラム名： "status" / 値： "0"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getColumn("status"), "0");
                //コラム名： "last_updater" / 値： （引数）更新者コード
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

                // コラム名： "delete_flag" / 値： "1"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getDeleteFlag(), "1");
                //コラム名： "status" / 値： "0"
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getStatus(), "0");
                //コラム名： "last_updater" / 値： （引数）更新者コード
                assertEquals(((CommCampaignCondMstRow)l_result.get(0)).getLastUpdater(), "admin");
                //コラム名： "last_updated_timestamp" / 値： 現在日時
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
            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾘｸｴｽﾄ
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

            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ
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

            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ
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

            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ
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

            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ
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
            //キャンペーン検索条件オブジェクト(createSearchCondition)
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
            //ソートキー
            WEB3AccInfoSortKey[] l_sortKeys = null;

            //キャンペーン検索条件オブジェクト(createSearchCondition)
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

            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition = null;

            //検索条件文字列
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

            //キャンペーン検索条件オブジェクト(createSearchCondition)
            WEB3AdminAccInfoCampaignSearchCondition l_searchCondition = null;

            //create検索データコンテナ
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
            //手数料割引キャンペーン条件マスタ行.更新者コード = （引数）更新者コード
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
            //管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定一覧ﾘｸｴｽﾄ
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

        //キャンペーン検索条件オブジェクト(createSearchCondition)
        WEB3AdminAccInfoCampaignSearchCondition l_searchCondition =
            new WEB3AdminAccInfoCampaignSearchCondition();
        WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition =
            new WEB3AccInfoCampaignSearchCondition();

        //setCampaignCondition
        l_searchCondition.setCampaignCondition(l_campaignSearchCondition,
                null,
                "369",
                null);

        //部店コード
        l_searchCondition.setBranchCode("369");
        //削除フラグ
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
            //手数料割引キャンペーン条件マスタ行.更新者コード = （引数）更新者コード
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
