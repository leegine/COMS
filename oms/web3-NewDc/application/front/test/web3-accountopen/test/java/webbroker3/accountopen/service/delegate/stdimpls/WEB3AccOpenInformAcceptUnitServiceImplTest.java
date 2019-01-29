head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenInformAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccOpenAcceptRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenInformAcceptUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenInformAcceptUnitServiceImplTest.class);

    WEB3AccOpenInformAcceptUnitServiceImpl l_impl = null;

    public WEB3AccOpenInformAcceptUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AccOpenInformAcceptUnitServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testUpdateHostAccept_T01()
    {
        final String STR_METHOD_NAME = "testUpdateHostAccept_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_acceptParams =
                this.getHostAccOpenAcceptParams();
            TestDBUtility.insertWithDel(l_acceptParams);
            
            List l_lisRow1 = l_queryProcesser.doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    " status = ? ",
                    new Object[]{"9"});

            assertEquals(1, l_lisRow1.size());

            Object[] obj = {(HostAccOpenAcceptParams)l_lisRow1.get(0), "1"};

            Method method = WEB3AccOpenInformAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "updateAcceptQueue",
                    new Class[]{HostAccOpenAcceptParams.class, String.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
            
            List l_lisRow2 = l_queryProcesser.doFindAllQuery(
                HostAccOpenAcceptRow.TYPE,
                " status = ? ",
                new Object[]{"1"});
            
            assertEquals(1, l_lisRow2.size());

            log.debug(STR_METHOD_NAME + "------------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    public void testUpdateHostAccept_T02()
//    {
//        final String STR_METHOD_NAME = "testUpdateHostAccept_T02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
//            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
//            HostAccOpenAcceptParams l_acceptParams =
//                this.getHostAccOpenAcceptParams();
//            TestDBUtility.insertWithDel(l_acceptParams);
//            
//            List l_lisRow1 = l_queryProcesser.doFindAllQuery(
//                    HostAccOpenAcceptRow.TYPE,
//                    " status = ? ",
//                    new Object[]{"9"});
//
//            assertEquals(1, l_lisRow1.size());
//
//            Object[] obj = {(HostAccOpenAcceptParams)l_lisRow1.get(0), null};
//
//            Method method = WEB3AccOpenInformAcceptUnitServiceImpl.class.getDeclaredMethod(
//                    "updateAcceptQueue",
//                    new Class[]{HostAccOpenAcceptParams.class, String.class});
//            method.setAccessible(true);
//            method.invoke(l_impl, obj);
//            
//            List l_lisRow2 = l_queryProcesser.doFindAllQuery(
//                HostAccOpenAcceptRow.TYPE,
//                " status = ? ",
//                new Object[]{"1"});
//
//            List l_lisRow3 = l_queryProcesser.doFindAllQuery(
//                    HostAccOpenAcceptRow.TYPE,
//                    " status = ? ",
//                    new Object[]{"9"});
//            
//            assertEquals(0, l_lisRow2.size());
//            assertEquals(1, l_lisRow3.size());
//
//            log.debug(STR_METHOD_NAME + "------------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testUpdateVariousInform_T01()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_acceptParams =
                this.getHostAccOpenAcceptParams();
            l_acceptParams.setErrorCode("1001");
            l_acceptParams.setAcceptStatus("1");
            
            TestDBUtility.insertWithDel(l_acceptParams);
            
            List l_lisRow1 = l_queryProcesser.doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    " status = ? ",
                    new Object[]{"9"});

            assertEquals(1, l_lisRow1.size());

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_informParams = this.getVariousInformParams();
            l_informParams.setStatus(null);
            l_informParams.setErrorReasonCode(null);

            TestDBUtility.insertWithDel(l_informParams);

            Object[] obj = {(HostAccOpenAcceptParams)l_lisRow1.get(0), l_informParams};

            Method method = WEB3AccOpenInformAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "updateVariousInform",
                    new Class[]{HostAccOpenAcceptParams.class, VariousInformParams.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);

            List l_lisRow2 = l_queryProcesser.doFindAllQuery(
                VariousInformRow.TYPE,
                " status = ? and error_reason_code = ? ",
                new Object[]{"3", "1001"});

            assertEquals(1, l_lisRow2.size());

            log.debug(STR_METHOD_NAME + "------------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateVariousInform_T02()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_acceptParams =
                this.getHostAccOpenAcceptParams();
            l_acceptParams.setErrorCode("1001");
            l_acceptParams.setAcceptStatus("2");

            TestDBUtility.insertWithDel(l_acceptParams);

            List l_lisRow1 = l_queryProcesser.doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    " status = ? ",
                    new Object[]{"9"});

            assertEquals(1, l_lisRow1.size());

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_informParams = this.getVariousInformParams();
            l_informParams.setStatus(null);
            l_informParams.setErrorReasonCode(null);

            TestDBUtility.insertWithDel(l_informParams);

            Object[] obj = {(HostAccOpenAcceptParams)l_lisRow1.get(0), l_informParams};

            Method method = WEB3AccOpenInformAcceptUnitServiceImpl.class.getDeclaredMethod(
                    "updateVariousInform",
                    new Class[]{HostAccOpenAcceptParams.class, VariousInformParams.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);

            List l_lisRow2 = l_queryProcesser.doFindAllQuery(
                VariousInformRow.TYPE,
                " status = ? and error_reason_code = ? ",
                new Object[]{"4", "1001"});

            assertEquals(1, l_lisRow2.size());

            log.debug(STR_METHOD_NAME + "------------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyContactAccept_T01()
    {
        final String STR_METHOD_NAME = "testNotifyContactAccept_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_acceptParams =
                this.getHostAccOpenAcceptParams();
            l_acceptParams.setErrorCode("1001");
            l_acceptParams.setAcceptStatus("1");

            TestDBUtility.insertWithDel(l_acceptParams);

            List l_lisRow1 = l_queryProcesser.doFindAllQuery(
                    HostAccOpenAcceptRow.TYPE,
                    " status = ? ",
                    new Object[]{"9"});

            assertEquals(1, l_lisRow1.size());

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_informParams = this.getVariousInformParams();
            l_informParams.setStatus(null);
            l_informParams.setErrorReasonCode(null);
            TestDBUtility.insertWithDel(l_informParams);

            l_impl.notifyInformAccept(l_acceptParams, l_informParams, "1");

            log.debug(STR_METHOD_NAME + "------------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public HostAccOpenAcceptParams getHostAccOpenAcceptParams()
    {
        HostAccOpenAcceptParams l_acceptParams =
            new HostAccOpenAcceptParams();

        //データコード  request_code
        l_acceptParams.setRequestCode("8004");
        //証券会社コード institution_code
        l_acceptParams.setInstitutionCode("0D");
        //部店コード   branch_code
        l_acceptParams.setBranchCode("381");
        //顧客コード   account_code
        l_acceptParams.setAccountCode("2512246");
        //扱者コード   trader_code
        l_acceptParams.setTraderCode("1234");
        //識別コード   order_request_number
        l_acceptParams.setOrderRequestNumber("123456");
        //受付結果    accept_status
        l_acceptParams.setAcceptStatus("5");
        //エラーメッセージ    error_message
        l_acceptParams.setErrorCode("1234");
        //処理区分    status
        l_acceptParams.setStatus("9");

        return l_acceptParams;
    }

    public VariousInformParams getVariousInformParams()
    {
        VariousInformParams l_informParams = new VariousInformParams();
        //証券会社コード institution_code
        l_informParams.setInstitutionCode("0D");
        //連絡種別     inform_div
        l_informParams.setInformDiv("1");
        //識別コード   request_number
        l_informParams.setRequestNumber("GI82A");
        //部店コード   branch_code
        l_informParams.setBranchCode("381");
        //作成日時     created_timestamp
        l_informParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時    last_updated_timestamp
        l_informParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        //伝票作成状況   status
        //エラー理由コード   error_reason_code
        //伝票受信日時

        return l_informParams;
    }
}
@
