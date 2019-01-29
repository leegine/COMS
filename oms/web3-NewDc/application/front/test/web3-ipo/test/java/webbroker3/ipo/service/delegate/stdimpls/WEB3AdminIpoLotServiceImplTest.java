head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIpoLotServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIpoLotServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotServiceImplTest.class);

    private WEB3AdminIpoLotServiceImpl l_impl =
        new WEB3AdminIpoLotServiceImpl();

    public WEB3AdminIpoLotServiceImplTest(String arg0)
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

    /**
     * 抽選区分 = "新規抽選"　@の場合
     * IPO申告テーブルを検索する。 
     * 検索結果が0件の場合、例外をスローする。 
     */
    public void testValidateLotValidity_C0001()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setLotResult("0");
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33382L));
            l_lisBranchIds.add(new Long(33383L));

            String l_strLotDiv = "1";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02312, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 抽選区分 = "新規抽選"　@の場合
     * IPO申告テーブルを検索する。 
     * 引数.部店IDリスト.size() = 1
     * 検索結果 = 1件の場合
     */
    public void testValidateLotValidity_C0002()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ipoOrderParams.setLotResult("0");
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33381L));

            String l_strLotDiv = "1";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 抽選区分 = "新規抽選"　@の場
     * IPO申告テーブルを検索する。 
     * 引数.部店IDリスト.size() = 2
     * 検索結果 = 2件の場合
     */
    public void testValidateLotValidity_C0003()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setLotResult("0");
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            l_ipoOrderParams.setIpoOrderId(1234L);
            l_ipoOrderParams.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33381L));
            l_lisBranchIds.add(new Long(33383L));

            String l_strLotDiv = "1";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 抽選区分 = "繰上抽選"　@の場合
     * 検索結果が0件の場合、例外をスローする。 
     */
    public void testValidateLotValidity_C0004()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setLotResult("0");
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33382L));
            l_lisBranchIds.add(new Long(33383L));

            String l_strLotDiv = "2";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02333, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 抽選区分 = "繰上抽選"　@の場合
     * IPO申告テーブルを検索する。 
     * 引数.部店IDリスト.size() = 1
     * 検索結果 = 1件の場合
     */
    public void testValidateLotValidity_C0005()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setLotResult("2");
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ipoOrderParams.setOfferingDiv("0");
            l_ipoOrderParams.setLotResultRetry("0");
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33381L));

            String l_strLotDiv = "2";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 抽選区分 = "新規抽選"　@の場
     * IPO申告テーブルを検索する。 
     * 引数.部店IDリスト.size() = 2
     * 検索結果 = 2件の場合
     */
    public void testValidateLotValidity_C0006()
    {
        final String STR_METHOD_NAME = "testValidateLotValidity_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = TestDBUtility.getIpoOrderRow();
            l_ipoOrderParams.setLotResult("2");
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_ipoOrderParams.setOfferingDiv("0");
            l_ipoOrderParams.setLotResultRetry("0");
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            l_ipoOrderParams.setIpoOrderId(1234L);
            l_ipoOrderParams.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_ipoOrderParams);

            String l_strProductId = "123456";
            List l_lisBranchIds = new ArrayList();
            l_lisBranchIds.add(new Long(33381L));
            l_lisBranchIds.add(new Long(33383L));

            String l_strLotDiv = "2";

            l_impl.validateLotValidity(l_strProductId, l_strLotDiv, l_lisBranchIds);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
