head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AcceptLoginServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/12 徐宏偉 (中訊) 新規作成
*/
package webbroker3.login.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.LoginRejectIpParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AcceptLoginServiceImplTest_xhw extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AcceptLoginServiceImplTest_xhw.class);

    WEB3AcceptLoginServiceImpl l_imple = new WEB3AcceptLoginServiceImpl();

    public WEB3AcceptLoginServiceImplTest_xhw(String arg0)
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

    /*
     * "部店プリファ@レンステーブルより、以下の属性のレコードを取得する
     * [取得条件]
     * 部店ID：引数.部店ID
     * 属性名：""login.rejectip.check.""＋引数.注文経路区分
     * 連番：1
     * レコードない
     * 方法@返回true"
     * "1)引数.部店ID = 62421
     * 2)引数.注文経路区分=1
     * 3)清空部店プリファ@レンステーブル"  方法@返回true
     */
    public void testIsRejectIp_case001()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "部店プリファ@レンステーブルより、以下の属性のレコードを取得する
     * [取得条件]
     * 部店ID：引数.部店ID
     * 属性名：""login.rejectip.check.""＋引数.注文経路区分
     * 連番：1
     * レコードの値が「チェックなし」の場合
     * "1)引数.部店ID = 62421
     * 2)引数.注文経路区分=1
     * 4)向部店プリファ@レンステーブル中插入條數據,内容如@下：
     * 部店ID=62421
     * 属性名=""login.rejectip.check.1""
     * 連番：1
     * 値=""0""
     * 方法@返回true
     */
    public void testIsRejectIp_case002()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";

        try
        {
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "取得したレコードの値が「チェックあり」
     * ログイン拒否IP tblを検索し、該当recある場合はtrue
     * "1)引数.部店ID = 62421
     * 2)引数.注文経路区分=1
     * 3）引数.IPアドレス=""xuhongwei@@sinocom.cn
     * 4)向部店プリファ@レンステーブル中插入條數據,内容如@下：
     * 部店ID=62421
     * 属性名=""login.rejectip.check.1""
     * 連番：1
     * 値=""1""
     * 5)向ログイン拒否IP表中插入一條數據,内容如@下：
     * 証券会社コード：62421
     * IPアドレス：""xuhongwei@@sinocom.cn
     * ステータス：0" 方法@返回true
     */
    public void testIsRejectIp_case003()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "0D";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "127.0.0.1";

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            LoginRejectIpParams l_params = new LoginRejectIpParams();
            l_params.setLoginRejectId(1001);
            //1 証券会社コードinstitution_codeVARCHAR22NOT
            l_params.setInstitutionCode("0D");
            //2 IPアドレスip_addressVARCHAR215NOT
            l_params.setIpAddress("127.0.0.1");
            //3 ステータスstatusVARCHAR21NOT
            l_params.setStatus("0");
            
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(GtlUtils.getSystemTimestamp());
            calendar.add(Calendar.DAY_OF_MONTH, -1 );            
            //4 適用開始日時appli_start_timestampDATENOT
            l_params.setAppliStartTimestamp(calendar.getTime());            
            calendar.add(Calendar.DAY_OF_MONTH, 2 );    
            //5 適用終了日時appli_end_timestampDATENOT
            l_params.setAppliEndTimestamp(calendar.getTime());
            
            l_params.setRegistDiv("1");
            l_params.setUpdatedDiv("1");
            
            //6 更新者コードlast_updaterVARCHAR220NOT
            l_params.setLastUpdater("12");
            //7 作成日付created_timestampDATENOT
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //8 更新日付last_updated_timestampDATENOT
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_params);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * "取得したレコードの値が「チェックあり」
     * ログイン拒否IP tblを検索し、該当rec不存在場合はfalse
     * "1)引数.部店ID = 62421
     * 2)引数.注文経路区分=1
     * 3）引数.IPアドレス=""xuhongwei@@sinocom.cn""
     * 4)向部店プリファ@レンステーブル中插入條數據,内容如@下：
     * 部店ID=62421
     * 属性名=""login.rejectip.check.1""
     * 連番：1
     * 値=""1""
     * 5)清空ログイン拒否IP表
     * 方法@返回false
     */
    public void testIsRejectIp_case004()
    {
        final String STR_METHOD_NAME = " testIsRejectIp_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        long l_lngBranchId = 62421;
        String l_strInstitutionCode = "123";
        String l_strName = "login.rejectip.check.1";
        String l_strValue = "1";
        String l_strRootDiv = "1";
        String l_strIpAddress = "xuhongwei@@sionocom.cn";

        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_lngBranchId);
            l_branchPreferencesParams.setName(l_strName);
            l_branchPreferencesParams.setValue(l_strValue);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            boolean l_blnReturn =
                l_imple.isRejectIp(l_lngBranchId, l_strInstitutionCode, l_strRootDiv, l_strIpAddress);
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
