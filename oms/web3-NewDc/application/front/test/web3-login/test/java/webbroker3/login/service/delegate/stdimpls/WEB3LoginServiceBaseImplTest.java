head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3LoginServiceBaseImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3LoginServiceBaseImplTest
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/14 稲本潤に (中訊) 新規作成
Revision History : 2007/08/30 周墨洋 (中訊) 仕様変更・モデル044
*/
package webbroker3.login.service.delegate.stdimpls;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidatorForMock;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.SonarTraderParams;
import webbroker3.gentrade.data.SonarTraderRow;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.login.message.WEB3AcceptInfo;
import webbroker3.login.message.WEB3DocumentDeliverInfoUnit;
import webbroker3.login.message.WEB3TraderInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (WEB3ログインサービスベース)<BR>
 * ログインサービスのベースクラス<BR>
 *  * <BR>
 * @@author      稲本潤に
 * @@version     1.00
 */
public class WEB3LoginServiceBaseImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3LoginServiceBaseImplTest.class);
    WEB3LoginServiceBaseImpl interceptor = null;

    public WEB3LoginServiceBaseImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.insertInstitution();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0001<BR>
     */
    public void testGetAcceptInfo_0001()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv(null);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //getTradingpowerCalcConditionRow
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            //LoginInfo
            LoginInfo l_loginInfo = new LoginInfo(){

                public LoginTypeInfo getLoginTypeInfo()
                {
                    return null;
                }

                public long getLoginId()
                {
                    return 0;
                }

                public long getLoginTypeId()
                {
                    return 0;
                }

                public String getUsername()
                {
                    return "";
                }

                public String getInitialPassword()
                {
                    return null;
                }

                public Set getSubordinateLoginGroups()
                {
                    return null;
                }

                public boolean isDisabled()
                {
                    return false;
                }

                public Set getReachableAccountIds()
                {
                    return null;
                }

                public Set getReachableLoginIds()
                {
                    return null;
                }

                public Set getReachableLogins()
                {
                    return null;
                }

                //LAST_LOGIN_TIME
                public Map getAttributes()
                {
                    Map map = new HashMap();
                    return map;
                }

                public boolean isAccountReachable(long arg0)
                {
                    return false;
                }

                public boolean hasFailedLogin()
                {
                    return false;
                }

                public int getFailureCount()
                {
                    return 0;
                }

                public Date getLastFailureTimestamp()
                {
                    return null;
                }};
                
                interceptor = new WEB3LoginServiceBaseImpl();
                WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
                assertEquals("0", l_acceptInfo.mobileAccOpenDiv);
                log.exiting(STR_METHOD_NAME);
        }
        catch(Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0002<BR>
     */
    public void testGetAcceptInfo_0002()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("0");
            
            //getTradingpowerCalcConditionRow
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            //LoginInfo
            LoginInfo l_loginInfo = new LoginInfo(){

                public LoginTypeInfo getLoginTypeInfo()
                {
                    return null;
                }

                public long getLoginId()
                {
                    return 0;
                }

                public long getLoginTypeId()
                {
                    return 0;
                }

                public String getUsername()
                {
                    return "";
                }

                public String getInitialPassword()
                {
                    return null;
                }

                public Set getSubordinateLoginGroups()
                {
                    return null;
                }

                public boolean isDisabled()
                {
                    return false;
                }

                public Set getReachableAccountIds()
                {
                    return null;
                }

                public Set getReachableLoginIds()
                {
                    return null;
                }

                public Set getReachableLogins()
                {
                    return null;
                }

                //LAST_LOGIN_TIME
                public Map getAttributes()
                {
                    Map map = new HashMap();
                    return map;
                }

                public boolean isAccountReachable(long arg0)
                {
                    return false;
                }

                public boolean hasFailedLogin()
                {
                    return false;
                }

                public int getFailureCount()
                {
                    return 0;
                }

                public Date getLastFailureTimestamp()
                {
                    return null;
                }};
                
                interceptor = new WEB3LoginServiceBaseImpl();
                WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
                assertEquals("0", l_acceptInfo.mobileAccOpenDiv);
                log.exiting(STR_METHOD_NAME);
        }
        catch(Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0001<BR>
     */
    public void testGetAcceptInfo_0003()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            
            //getTradingpowerCalcConditionRow
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            //LoginInfo
            LoginInfo l_loginInfo = new LoginInfo(){

                public LoginTypeInfo getLoginTypeInfo()
                {
                    return null;
                }

                public long getLoginId()
                {
                    return 0;
                }

                public long getLoginTypeId()
                {
                    return 0;
                }

                public String getUsername()
                {
                    return "";
                }

                public String getInitialPassword()
                {
                    return null;
                }

                public Set getSubordinateLoginGroups()
                {
                    return null;
                }

                public boolean isDisabled()
                {
                    return false;
                }

                public Set getReachableAccountIds()
                {
                    return null;
                }

                public Set getReachableLoginIds()
                {
                    return null;
                }

                public Set getReachableLogins()
                {
                    return null;
                }

                //LAST_LOGIN_TIME
                public Map getAttributes()
                {
                    Map map = new HashMap();
                    return map;
                }

                public boolean isAccountReachable(long arg0)
                {
                    return false;
                }

                public boolean hasFailedLogin()
                {
                    return false;
                }

                public int getFailureCount()
                {
                    return 0;
                }

                public Date getLastFailureTimestamp()
                {
                    return null;
                }};
                
                interceptor = new WEB3LoginServiceBaseImpl();
                WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
                assertEquals("1", l_acceptInfo.mobileAccOpenDiv);
                log.exiting(STR_METHOD_NAME);
        }
        catch(Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0004<BR>
     */
    public void testGetAcceptInfo_0004()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("7");
            
            //getTradingpowerCalcConditionRow
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            //LoginInfo
            LoginInfo l_loginInfo = new LoginInfo(){

                public LoginTypeInfo getLoginTypeInfo()
                {
                    return null;
                }

                public long getLoginId()
                {
                    return 0;
                }

                public long getLoginTypeId()
                {
                    return 0;
                }

                public String getUsername()
                {
                    return "";
                }

                public String getInitialPassword()
                {
                    return null;
                }

                public Set getSubordinateLoginGroups()
                {
                    return null;
                }

                public boolean isDisabled()
                {
                    return false;
                }

                public Set getReachableAccountIds()
                {
                    return null;
                }

                public Set getReachableLoginIds()
                {
                    return null;
                }

                public Set getReachableLogins()
                {
                    return null;
                }

                //LAST_LOGIN_TIME
                public Map getAttributes()
                {
                    Map map = new HashMap();
                    return map;
                }

                public boolean isAccountReachable(long arg0)
                {
                    return false;
                }

                public boolean hasFailedLogin()
                {
                    return false;
                }

                public int getFailureCount()
                {
                    return 0;
                }

                public Date getLastFailureTimestamp()
                {
                    return null;
                }};
                
                interceptor = new WEB3LoginServiceBaseImpl();
                interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
                log.exiting(STR_METHOD_NAME);
                fail();
        }
        catch(WEB3SystemLayerException e)
        {
            assertTrue(e.getException() instanceof WEB3BaseRuntimeException);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ((WEB3BaseRuntimeException)e.getException()).getErrorInfo());
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception e)
        {
            log.error(STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0005<BR>
     * 担保ローン口座開設区分 =（引数.顧客.証券担保ローン区分 == NULL）の場合、<BR>
     * 0：　@口座なし。<BR>
     */
    public void testGetAcceptInfo_0005()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(null);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("0", l_acceptInfo.securedLoanAccOpenDiv);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0006<BR>
     * 担保ローン口座開設区分 =（引数.顧客.証券担保ローン区分 == 0：未開設）の場合、<BR>
     * 0：　@口座なし。<BR>
     */
    public void testGetAcceptInfo_0006()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.NOT_OPEN);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("0", l_acceptInfo.securedLoanAccOpenDiv);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0007<BR>
     * 担保ローン口座開設区分 =（引数.顧客.証券担保ローン区分 == 1：開設）の場合、<BR>
     * データが取得できなかった場合、0：　@口座なし。<BR>
     */
    public void testGetAcceptInfo_0007()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.OPEN);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("0", l_acceptInfo.securedLoanAccOpenDiv);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0008<BR>
     * 担保ローン口座開設区分 =（引数.顧客.証券担保ローン区分 == 1：開設）の場合、<BR>
     * データが取得できた場合(データ = 1)、1：　@口座開設。<BR>
     */
    public void testGetAcceptInfo_0008()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0008";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.OPEN);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("100");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("1", l_acceptInfo.securedLoanAccOpenDiv);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0009<BR>
     * 担保ローン口座開設区分 =（引数.顧客.証券担保ローン区分 == 1：開設）の場合、<BR>
     * データが取得できた場合(データ > 1)、1：　@口座開設。<BR>
     */
    public void testGetAcceptInfo_0009()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0009";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("100");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("101");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("1", l_acceptInfo.securedLoanAccOpenDiv);
            assertNull(l_acceptInfo.sonarOperatorInfo);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get顧客情報)<BR>
     * testGetAcceptInfo_0010<BR>
     * （引数.顧客.証券担保ローン区分の値が上記以外）の場合、<BR>
     * 「パラメータ値不正」の例外をthrowする。<BR>
     */
    public void testGetAcceptInfo_0010()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0010";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("0", l_acceptInfo.securedLoanAccOpenDiv);
            log.exiting(STR_METHOD_NAME);

        }
        catch(WEB3SystemLayerException l_exSLE)
        {
            assertEquals(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_exSLE.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    
    public void testGetAcceptInfo_0011()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0011";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode("1");
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.OPEN);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("100");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("101");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            TestDBUtility.deleteAll(SonarTraderRow.TYPE);
            
            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("1", l_acceptInfo.securedLoanAccOpenDiv);
            assertNull(l_acceptInfo.sonarOperatorInfo);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetAcceptInfo_0012()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0012";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // MainAccountRow
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv(null);
            l_mainAccountParams.setFeqConAccOpenDiv(null);
            l_mainAccountParams.setSonarTraderCode("1");
            l_mainAccountParams.setForeignSecAccOpenDiv(null);
            l_mainAccountParams.setStockOptionAccOpenDiv(null);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
                WEB3SecuredLoanSecAccOpenDivDef.OPEN);

            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            //l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            // StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);

            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("100");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setStockLoanAccountCode("101");
            l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
            l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            TestDBUtility.deleteAll(SonarTraderRow.TYPE);
            SonarTraderParams l_sonarTraderParams = this.getSonarTraderParams();
            TestDBUtility.insertWithDel(l_sonarTraderParams);
            
            // LoginInfo
            LoginInfo l_loginInfo = new LoginInfoImplForMock();

            interceptor = new WEB3LoginServiceBaseImpl();

            WEB3AcceptInfo l_acceptInfo = interceptor.getAcceptInfo("1", l_mainAccountParams, l_loginInfo, "1");
            assertEquals("1", l_acceptInfo.securedLoanAccOpenDiv);
            assertEquals("1", l_acceptInfo.sonarOperatorInfo.operatorCode);
            assertEquals("jiddk", l_acceptInfo.sonarOperatorInfo.nameKana);
            assertEquals("jidck", l_acceptInfo.sonarOperatorInfo.nameKanji);
            log.exiting(STR_METHOD_NAME);

        }
        catch(Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetAcceptInfo_0013()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfo_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSNN");
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
     
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            WEB3GentradeOrderValidatorForMock mock = new WEB3GentradeOrderValidatorForMock();

            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams.getAccountId());
            
            mock.validateAccountForTrading(l_genMainAccount);

            
            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            String l_acceptCode = "1234567";
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            String l_orderRootDiv = "0";
            
            WEB3AcceptInfo acceptInfo =
                impl.getAcceptInfo(l_acceptCode, l_mainAccountRow, l_loginInfo, l_orderRootDiv);
            
            assertEquals(1, acceptInfo.documentDeliverList.length);
            assertEquals("000", acceptInfo.documentDeliverList[0].documentDiv);
            assertEquals("003", acceptInfo.documentDeliverList[0].documentCategory);
            assertEquals("0", acceptInfo.documentDeliverList[0].deliverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAcceptInfoCase1()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfoCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSNN");
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            EleDeliveryManagementParams l_EleDeliveryManagementParams = new EleDeliveryManagementParams();
            l_EleDeliveryManagementParams.setAccountId(333812512246L);
            l_EleDeliveryManagementParams.setAccountCode("2512246");
            l_EleDeliveryManagementParams.setInstitutionCode("0D");
            l_EleDeliveryManagementParams.setBranchCode("381");
            l_EleDeliveryManagementParams.setEleDelRegiFlag(0);
            l_EleDeliveryManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EleDeliveryManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_EleDeliveryManagementParams);
     
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            WEB3GentradeOrderValidatorForMock mock = new WEB3GentradeOrderValidatorForMock();

            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams.getAccountId());
            
            mock.validateAccountForTrading(l_genMainAccount);

            
            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            String l_acceptCode = "1234567";
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            String l_orderRootDiv = "0";
            
            WEB3AcceptInfo acceptInfo =
                impl.getAcceptInfo(l_acceptCode, l_mainAccountRow, l_loginInfo, l_orderRootDiv);
            
            assertEquals(1, acceptInfo.documentDeliverList.length);
            assertEquals("000", acceptInfo.documentDeliverList[0].documentDiv);
            assertEquals("003", acceptInfo.documentDeliverList[0].documentCategory);
            assertEquals("0", acceptInfo.documentDeliverList[0].deliverFlag);
            assertEquals("0", acceptInfo.edCheckFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAcceptInfoCase2()
    {
        final String STR_METHOD_NAME = " testGetAcceptInfoCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSNN");
        
        try
        {
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
     
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            WEB3GentradeOrderValidatorForMock mock = new WEB3GentradeOrderValidatorForMock();

            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams.getAccountId());
            
            mock.validateAccountForTrading(l_genMainAccount);

            
            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            String l_acceptCode = "1234567";
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            String l_orderRootDiv = "0";
            
            WEB3AcceptInfo acceptInfo =
                impl.getAcceptInfo(l_acceptCode, l_mainAccountRow, l_loginInfo, l_orderRootDiv);
            
            assertEquals(1, acceptInfo.documentDeliverList.length);
            assertEquals("000", acceptInfo.documentDeliverList[0].documentDiv);
            assertEquals("003", acceptInfo.documentDeliverList[0].documentCategory);
            assertEquals("0", acceptInfo.documentDeliverList[0].deliverFlag);
            assertNull(acceptInfo.edCheckFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getCCオペレータ情報
     */
    public void testGetTraderInfo_case0001()
    {
        final String STR_METHOD_NAME = " testGetTraderInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3LoginServiceBaseImpl l_loginServiceBaseImpl =
            new WEB3LoginServiceBaseImpl();

        TraderParams l_traderParams = new TraderParams();
        l_traderParams.setDepartmentCode("12");

        LoginInfo l_loginInfoImpl = new LoginInfoImplForMock();

        try
        {
            WEB3TraderInfo l_actualTactualraderInfo =
                l_loginServiceBaseImpl.getTraderInfo(l_traderParams, l_loginInfoImpl);

            assertEquals("12", l_actualTactualraderInfo.departmentCode);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    private class LoginInfoImplForMock implements LoginInfo
    {
        public LoginTypeInfo getLoginTypeInfo()
        {
            return null;
        }

        public long getLoginId()
        {
            return 0L;
        }

        public long getLoginTypeId()
        {
            return 0L;
        }

        public String getUsername()
        {
            return "";
        }

        public String getInitialPassword()
        {
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            return null;
        }

        public boolean isDisabled()
        {
            return true;
        }

        public Set getReachableAccountIds()
        {
            return null;
        }

        public Set getReachableLoginIds()
        {
            return null;
        }

        public Set getReachableLogins()
        {
            return null;
        }

        public Map getAttributes()
        {
            Map l_mapReturns = new HashMap();
            l_mapReturns.put(WEB3LoginAttributeKeyDef.LAST_LOGIN, "");
            return l_mapReturns;
        }

        public boolean isAccountReachable(long l)
        {
            return true;
        }

        public boolean hasFailedLogin()
        {
            return true;
        }

        public int getFailureCount()
        {
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            return null;
        }
    }
    
    public void insertInstitution()
    {
    	try
    	{
    		//InstitutionParams
	    	TestDBUtility.deleteAll(InstitutionParams.TYPE);
	    	InstitutionParams l_params = TestDBUtility.getInstitutionRow();
	    	TestDBUtility.insertWithDel(l_params);
	    	
	    	//BranchParams
	    	TestDBUtility.deleteAll(BranchParams.TYPE);
	    	BranchParams l_branchParams = TestDBUtility.getBranchRow();
	    	TestDBUtility.insertWithDel(l_branchParams);
	    	
    	}
    	catch(Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		fail();
    	}
    }
    
    public SonarTraderParams getSonarTraderParams()
    {
        SonarTraderParams params = new SonarTraderParams();
        // 証券会社コード  institution_codeVARCHAR2    2Notnull
        params.setInstitutionCode("0D");
        // 部店コード    branch_codeVARCHAR2 3Notnull
        params.setBranchCode("381");
        // 扱者コード    sonar_trader_codeVARCHAR2   5Notnull
        params.setSonarTraderCode("1");
        // 課    section_codeVARCHAR2    2NULL
        // 班    group_codeVARCHAR2  2NULL
        // 扱者名(カナ)  family_name_alt1VARCHAR2    16Notnull
        params.setFamilyNameAlt1("jiddk");
        // 扱者名(漢字)  family_nameVARCHAR2 10Notnull
        params.setFamilyName("jidck");
        
        // 新社員コード   sonar_last_updaterVARCHAR2  10NULL
        // 更新者コード   last_updaterVARCHAR2    20NULL
        // 作成日付 created_timestampDATE   Notnull
        params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        // 更新日付 last_updated_timestampDATE  Notnull
        params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return params;
    }
    
    public void testGetDocumentDeliverInfoUnit_0001()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");

            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit = impl.getDocumentDeliverInfoUnit(l_mainAccountRow, "001", false, "1");
            
            assertEquals(0, l_documentDeliverInfoUnit.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDocumentDeliverInfoUnit_0002()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);

            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");

            String l_strOrderRootDiv = "4";
            boolean l_blnMarginRegistFlag = false;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            assertEquals(0, l_documentDeliverInfoUnit.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0003()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("0030000");
        BatoProductManagementParams l_batoProductManagementParams1 = this.getBatoProductManagementRow();
        l_batoProductManagementParams1.setBatoProductCode("003SSSN");
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams1);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");

            String l_strOrderRootDiv = "4";
            boolean l_blnMarginRegistFlag = false;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0004()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = false;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02939, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0005()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
        l_docDeliveryManagementParams.setDocumentDiv("000");
        l_docDeliveryManagementParams.setProductCode("003SSSN");
        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = false;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            assertEquals(1, l_documentDeliverInfoUnit.length);
            assertEquals("000", l_documentDeliverInfoUnit[0].documentDiv);
            assertEquals("003", l_documentDeliverInfoUnit[0].documentCategory);
            assertEquals("1", l_documentDeliverInfoUnit[0].deliverFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0006()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("1");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = true;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02939, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0007()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("1");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = false;
            String l_strFutOpTradeRegist = "1";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            assertEquals(1, l_documentDeliverInfoUnit.length);
            assertEquals("000", l_documentDeliverInfoUnit[0].documentDiv);
            assertEquals("003", l_documentDeliverInfoUnit[0].documentCategory);
            assertEquals("2", l_documentDeliverInfoUnit[0].deliverFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0008()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("2");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = true;
            String l_strFutOpTradeRegist = "2";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02939, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0009()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("2");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "C";
            boolean l_blnMarginRegistFlag = true;
            String l_strFutOpTradeRegist = "0";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            assertEquals(1, l_documentDeliverInfoUnit.length);
            assertEquals("000", l_documentDeliverInfoUnit[0].documentDiv);
            assertEquals("003", l_documentDeliverInfoUnit[0].documentCategory);
            assertEquals("2", l_documentDeliverInfoUnit[0].deliverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDocumentDeliverInfoUnit_0010()
    {
        final String STR_METHOD_NAME = " testGetDocumentDeliverInfoUnit_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DocCategoryManagementParams l_docCategoryManagementParams1 = this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        DocCategoryManagementParams l_docCategoryManagementParams3 = this.getDocCategoryManagementRow();
        l_docCategoryManagementParams3.setDocumentCategory("003");
        l_docCategoryManagementParams3.setDeliveryTarget("0");
        
        DocDivManagementParams l_docDivManagementParams1 = this.getDocDivManagementRow();
        l_docDivManagementParams1.setDocumentCheckDiv("4");
        l_docDivManagementParams1.setDocumentCategory("003");
        l_docDivManagementParams1.setDocumentNumber("0");
        
        BatoProductManagementParams l_batoProductManagementParams = this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("003SSSN");
        
//        DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//        l_docDeliveryManagementParams.setDocumentDiv("000");
//        l_docDeliveryManagementParams.setProductCode("0000000");
//        l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
//        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        try
        {
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams3);
            
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams1);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3LoginServiceBaseImpl impl = new WEB3LoginServiceBaseImpl();
            MainAccountParams l_mainAccountRow = new MainAccountParams();
            l_mainAccountRow.setInstitutionCode("0D");
            l_mainAccountRow.setBranchCode("381");
            l_mainAccountRow.setAccountId(333812512246L);

            String l_strOrderRootDiv = "S";
            boolean l_blnMarginRegistFlag = true;
            String l_strFutOpTradeRegist = "0";
            
            WEB3DocumentDeliverInfoUnit[] l_documentDeliverInfoUnit =
                impl.getDocumentDeliverInfoUnit(
                    l_mainAccountRow,
                    l_strOrderRootDiv,
                    l_blnMarginRegistFlag,
                    l_strFutOpTradeRegist);
            
            assertEquals(1, l_documentDeliverInfoUnit.length);
            assertEquals("000", l_documentDeliverInfoUnit[0].documentDiv);
            assertEquals("003", l_documentDeliverInfoUnit[0].documentCategory);
            assertEquals("0", l_documentDeliverInfoUnit[0].deliverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 書面種類管理テーブル
     * @@return
     */
    public DocCategoryManagementParams getDocCategoryManagementRow()
    {
        DocCategoryManagementParams params = new DocCategoryManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentCategory("001");
        params.setDocumentCateName("あります");
        params.setDocumentCateNameE("arimasu");
        params.setDeliveryTarget("0");
        params.setLastUpdater("123456789");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        
        return params;
    }
    
    /**
     * 書面区分管理テーブル
     * @@return
     */
    public DocDivManagementParams getDocDivManagementRow()
    {
        DocDivManagementParams params = new DocDivManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentDiv("000");
        params.setDocumentCheckDiv("1");
        params.setDocumentNumber("0");
        params.setDocumentCategory("001");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        
        return params;
    }
    
    /**
     * 電子鳩銘柄コード管理テーブル
     * @@return
     */
    public BatoProductManagementParams getBatoProductManagementRow()
    {
        BatoProductManagementParams params = new BatoProductManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentDiv("000");
        params.setBatoProductCode("0000000");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setValidFlag("0");
        
        return params;
    }
}
@
