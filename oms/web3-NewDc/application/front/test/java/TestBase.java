head	1.3;
access;
symbols;
locks; strict;
comment	@// @;


1.3
date	2011.04.19.05.39.14;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8404dad200173a2;
filename	TestBase.java;

1.2
date	2011.04.19.01.05.34;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	75c4dacdfde2230;
filename	TestBase.java;

1.1
date	2011.03.14.02.32.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	TestBase.java;


desc
@@


1.3
log
@*** empty log message ***
@
text
@import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.ServerConnectorPlugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolPlugin;
import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginCallback;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginPlugin;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.DevelopmentOpLoginSecurityServiceImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.LogoutRequest;
import com.fitechlabs.xtrade.plugin.security.oplogin.message.LogoutResponse;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradingPlugin;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradingPlugin;
import com.fitechlabs.xtrade.plugin.util.queries.QueryRequestPlugin;

import webbroker3.accountinfo.WEB3AccInfoAppPlugin;
import webbroker3.accountopen.WEB3AccOpenAppPlugin;
import webbroker3.adminmc.WEB3AdminMCAppPlugin;
import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecInquiryAppPlugin;
import webbroker3.admintriggerorder.WEB3AdminToAppPlugin;
import webbroker3.aio.WEB3AioAppPlugin;
import webbroker3.bd.WEB3XbbdAppPlugin;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.WEB3AdminDirSecAppPlugin;
import webbroker3.docadmin.WEB3DocAdminAppPlugin;
import webbroker3.eqtypeadmin.WEB3EqtypeAdminAppPlugin;
import webbroker3.equity.WEB3EquityAppPlugin;
import webbroker3.faq.WEB3FaqAppPlugin;
import webbroker3.feq.WEB3FeqAppPlugin;
import webbroker3.gentrade.WEB3GentradeAppPlugin;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoAppPlugin;
import webbroker3.ifoadmin.WEB3IfoAdminAppPlugin;
import webbroker3.ifodeposit.WEB3IfoDepositPlugin;
import webbroker3.inform.WEB3InformAppPlugin;
import webbroker3.ipo.WEB3IpoAppPlugin;
import webbroker3.login.WEB3LoginAppPlugin;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3AcceptLoginResponse;
import webbroker3.login.message.WEB3AdministratorLoginRequest;
import webbroker3.login.message.WEB3AdministratorLoginResponse;
import webbroker3.mailinfo.WEB3AdminMailInfoAppPlugin;
import webbroker3.mf.WEB3MutualFundAppPlugin;
import webbroker3.mqgateway.WEB3MQGatewayPlugin;
import webbroker3.point.WEB3PointAppPlugin;
import webbroker3.pvinfo.WEB3PvInfoAppPlugin;
import webbroker3.quoteadaptor.prototype.WEB3ProtoQuotePlugin;
import webbroker3.rlsgateway.WEB3RlsGatewayTestPlugin;
import webbroker3.servlet.WEB3HttpServletPlugin;
import webbroker3.slebase.SleBasedMarketAdapterBasePlugin;
import webbroker3.srvregi.WEB3SrvRegiAppPlugin;
import webbroker3.system.tune.affinity.WEB3AffinityPlugin;
import webbroker3.system.tune.affinity.WEB3AffinityTestPlugin;
import webbroker3.system.tune.threadpool.WEB3ThreadPoolPlugin;
import webbroker3.tradehistory.WEB3PlsbvsAppPlugin;
import webbroker3.tradehistory.WEB3TradeHistoryAppPlugin;
import webbroker3.trademanagement.WEB3TradeManagementAppPlugin;
import webbroker3.tradingpower.WEB3TPAssetTradingPowerPlugin;
import webbroker3.tradingpower.WEB3TPLibPlugin;
import webbroker3.tradingpoweradmin.WEB3AdminTPPlugin;
import webbroker3.trialcalc.WEB3TrialCalcAppPlugin;
import webbroker3.triggerorder.WEB3ToSuccAppPlugin;
import webbroker3.xbruito.WEB3RuitoAppPlugin;

public class TestBase extends TestCase implements TestConstants
{

    /** logger. */
    private static final Logit m_log = Logit.getInstance(TestBase.class);

    /** logger flag. */
    private static final boolean DBG = m_log.ison();

    public static final String LOG_START = ">>>>>>>>>>>>>>>>>start testing:";
    public static final String LOG_END = ">>>>>>>>>>>>>>>>>end testing:";

    /** メソッドの開始時 */
    public static final String TEST_START = ">>>>>>>>>>>>>>>>>start testing ";

    /** メソッドの終了時 */
    public static final String TEST_END = ">>>>>>>>>>>>>>>>>end testing ";

    /** 例外を受け取った場合 */
    public static final String ERROR = "[エラー内容] ";

    static {

        try
        {

            // initialize the plugin
            final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";

            final String jdbcUrl = getJdbcUrl();
            
            m_log.info("*** Plugin will use JDBC driver :" + jdbcDriver);
            m_log.info("*** >>>>>>> Plugin will use JDBC URL: " + jdbcUrl);
            
            int POOL_SIZE = 10;  
            
            OracleConnectionPoolDataSource oraclePoolSource = new OracleConnectionPoolDataSource(); 
            oraclePoolSource.setURL(jdbcUrl);
               
//            OracleConnectionCacheImpl oracleConnectionCache = new OracleConnectionCacheImpl(oraclePoolSource);  
//            oracleConnectionCache.setMaxLimit(POOL_SIZE);   
//            oracleConnectionCache.setCacheScheme(OracleConnectionCacheImpl.FIXED_RETURN_NULL_SCHEME);

            DataSources.setDefaultDataSource(oraclePoolSource);   

            // install the system plugins that we need
            KernelPlugin.plug();
            QueryRequestPlugin.plug();
            OpLoginPlugin.plug();

            //開発時に利用するOpLoginSecurityService をインストールする
            DevelopmentOpLoginSecurityServiceImpl.installDevelopmentOpLoginSecurityService();

            GenericTradingPlugin.plug();

            
            EqTypeTradingPlugin.plug();
            IfoTradingPlugin.plug();
            RuitoTradingPlugin.plug();
            FeqTradingPlugin.plug();
            AioTradingPlugin.plug();
            BondTradingPlugin.plug();
            MutualFundTradingPlugin.plug();   
            SleBasedMarketAdapterBasePlugin.plug();
            
            WEB3GentradeAppPlugin.plug();            
            WEB3LoginAppPlugin.plug();            
            WEB3HttpServletPlugin.plug();
            
            WEB3IfoDepositPlugin.plug();       
            WEB3TPAssetTradingPowerPlugin.plug();
            WEB3TPLibPlugin.plug();            
            WEB3AdminTPPlugin.plug();
            
            WEB3MQGatewayPlugin.plug();
//            WEB3MQGatewayForTestPlugin.plug();
            
            WEB3RlsGatewayTestPlugin.onPlug(); 
//            WEB3RlsGatewayPlugin.onPlug();
//            WEB3RlsOmsAdaptorPlugin.plug();  
            ServerConnectorPlugin.plug();   
            SocketPoolPlugin.plug();      
            WEB3ThreadPoolPlugin.plug();
            WEB3ProtoQuotePlugin.plug();
            //WEB3AffinityTestPlugin.onPrePlug();
//            WEB3AffinityPlugin.plug();
            WEB3AffinityTestPlugin.plug();
            //WEB3ServerConnectorPlugin.plug();
            
            
            WEB3EquityAppPlugin.plug();
            WEB3IfoAppPlugin.plug();
            WEB3MutualFundAppPlugin.plug();
            WEB3AioAppPlugin.plug();
            WEB3XbbdAppPlugin.plug();
            
            
            WEB3AdminToAppPlugin.plug();
            WEB3AdminDirSecAppPlugin.plug();
            WEB3ToSuccAppPlugin.plug();            
            WEB3AccInfoAppPlugin.plug();
            WEB3AccOpenAppPlugin.plug();
            WEB3AdminMCAppPlugin.plug();
            WEB3AdminOrderExecInquiryAppPlugin.plug();
//            WEB3ComplianceAuditAppPlugin.plug();
            WEB3DocAdminAppPlugin.plug();
            WEB3EqtypeAdminAppPlugin.plug();
            WEB3FaqAppPlugin.plug();
            WEB3IfoAdminAppPlugin.plug();
            WEB3InformAppPlugin.plug();
            WEB3IpoAppPlugin.plug();
            WEB3AdminMailInfoAppPlugin.plug();
            WEB3PointAppPlugin.plug();
            WEB3PvInfoAppPlugin.plug();
            WEB3SrvRegiAppPlugin.plug();
            WEB3PlsbvsAppPlugin.plug();
            WEB3TradeHistoryAppPlugin.plug();
            WEB3TradeManagementAppPlugin.plug();
            WEB3TrialCalcAppPlugin.plug();
//            WEB3FeqAppPlugin.plug();
            WEB3RuitoAppPlugin.plug();            
//            ErrorInfo errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00003;
        }
        catch (Throwable t)
        {
            m_log.error(t.getMessage(), t);
            throw new RuntimeException(t.getMessage());
        }
    }

    public TestBase(String name)
    {
        super(name);
    }
    /**
     * @@param l_strMarketCode String 市場コード
     * @@param l_strBranchCode String 部店コード
     * @@param l_strInstitutionCode String 証券会社コード
     * @@param l_tradingTimeType String 受付時間区分
     * @@param l_orderAcceptProduct String 注文受付商品
     * @@param l_OrderAcceptTransaction String 注文受付トランザクション
     * @@param l_productCode 銘柄コード 先物OP／投信のみ使用 以外は 0：DEFAULT
     */
    public void doSettingTradingClendarContext(
        String l_strMarketCode, String l_strBranchCode,
        String l_strInstitutionCode,
        String l_tradingTimeType,
        String l_productCode,
        String l_orderAcceptProduct, String l_OrderAcceptTransaction
        ) {

        //取引カレンダコンテキストに内容をセットする
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //証券会社コードをセットする
        l_context.setInstitutionCode(l_strInstitutionCode);

        //部店コードをセットする
        l_context.setBranchCode(l_strBranchCode);

        //市場コードをセットする
        l_context.setMarketCode(l_strMarketCode);

        //受付時間区分
        l_context.setTradingTimeType(l_tradingTimeType);

        // 注文受付商品をセットする
        l_context.setOrderAcceptProduct(l_orderAcceptProduct);

        // 銘柄コード
        l_context.setProductCode(l_productCode);

        // 注文受付トランザクションをセットする
        l_context.setOrderAcceptTransaction(l_OrderAcceptTransaction);

        // 取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //取引時間管理
        try {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * doClearTradingClendarContext
     */
    public void doClearTradingClendarContext() {

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

    }

    /**
     * doLogin
     *
     * @@param institutionCode String　@証券会社コード
     * @@param branchCode String　@部店コード
     * @@param acceptCode String　@受付コード
     * @@param xTradeUsername String　@ユーザ名
     * @@param acceptPassword String　@パスワード
     * @@param orderChannel String　@チャネル
     * @@return String　@セッションID
     */
    public String doLogin(String institutionCode,
                          String branchCode,
                          String acceptCode,
                          String xTradeUsername,
                          String acceptPassword,
                          String orderChannel) {

        final String l_strMethodName = "doLogin()";
        m_log.info(l_strMethodName + ": ENTER");

        WEB3AcceptLoginRequest loginReq = new WEB3AcceptLoginRequest();
        loginReq.institutionCode = institutionCode;
        loginReq.branchCode = branchCode;
        loginReq.acceptCode = acceptCode;
        loginReq.xTradeUsername = xTradeUsername;
        loginReq.acceptPassword = acceptPassword;
        loginReq.orderChannel = orderChannel;

        WEB3AcceptLoginResponse loginResp = (WEB3AcceptLoginResponse)
            MessageHandlerDispatcher.Handle(loginReq);
        String sessionId = loginResp.xTradeSessionID;
        OpLoginSecurityService service = (OpLoginSecurityService)
        Services.getService(OpLoginSecurityService.class);

        service.reauthenticateAndHandle(sessionId,
                                        OpLoginCallback.getNoOpInstance());

        m_log.info(l_strMethodName + ": EXIT");

        return sessionId;
    }
    
    /**
     * doAdminLogin　@管理者のログイン
     *
     * @@param institutionCode String　@証券会社コード
     * @@param branchCode String　@部店コード
     * @@param managerCode String　@管理者コード
     * @@param xTradeUsername String　@ユーザ名
     * @@param managerPassword String　@パスワード
     * @@param loginType String　@ログインタイプ  "0"：通常ログイン、"1"：パスワード変更の為のログイン
     * @@return String　@セッションID
     */
    public String doAdminLogin(String institutionCode,
                          String branchCode,
                          String managerCode,
                          String xTradeUsername,
                          String managerPassword,
                          String loginType) {

        final String l_strMethodName = "doAdminLogin()";
        m_log.info(l_strMethodName + ": ENTER");

        WEB3AdministratorLoginRequest loginReq = new WEB3AdministratorLoginRequest();
        //会社コード
        loginReq.institutionCode = institutionCode;
        //部店コード
        loginReq.branchCode = branchCode;        
        //管理者コード
        loginReq.administratorCode = managerCode;
        //xTradeユーザ名
        loginReq.xTradeUsername = xTradeUsername;        
        //管理者パスワード
        loginReq.administratorPassword = managerPassword;
        //ログインタイプ
        loginReq.loginType = loginType;

        WEB3AdministratorLoginResponse loginResp = (WEB3AdministratorLoginResponse)
            MessageHandlerDispatcher.Handle(loginReq);
        String sessionId = loginResp.xTradeSessionID;
        OpLoginSecurityService service = (OpLoginSecurityService)
        Services.getService(OpLoginSecurityService.class);

        service.reauthenticateAndHandle(sessionId,
                                        OpLoginCallback.getNoOpInstance());

        m_log.info(l_strMethodName + ": EXIT");

        return sessionId;
    }
    
    /**
     * doLogout
     *
     * @@param sessionId String セッションID
     */
    public void doLogout(String sessionId) {
        final String l_strMethodName = "doLogout()";
        m_log.info(l_strMethodName + ": ENTER");

        LogoutRequest logoutReq = new LogoutRequest();
        logoutReq.session_id = sessionId;
        LogoutResponse logoutResp = (LogoutResponse)
            MessageHandlerDispatcher.Handle(logoutReq);

        m_log.info(l_strMethodName + ": EXIT");
    }

    /**/
    public void dataLoader()
    {
        final String l_strMethodName = "dataLoader()";
        m_log.info(l_strMethodName + ": ENTER");

        String l_strCallMethodName = '/' + getName() + ".sql";
        String l_strCallClassName = getClass().getName();

        l_strCallClassName = l_strCallClassName.replace('.', '/');
        String l_strSqlFile = sqlPath + l_strCallClassName + l_strCallMethodName;
        
        File l_file = new File(l_strSqlFile);
        
        if(!l_file.exists())
        {
            System.out.println("当該テスト用SQL文("+ l_strSqlFile +")無し!!!");
            fail();
        }
        
        try
        {
            String l_strSql = new String(); 
            Process p = 
                Runtime.getRuntime().exec("sqlplus feqwxj/feqwxj@@daiwa @@" 
                    + l_strSqlFile);
            
            BufferedReader bufferedReader = 
                new BufferedReader(new InputStreamReader(p.getInputStream())); 
            
            while ( (l_strSql=bufferedReader.readLine()) != null) 
                System.out.println(l_strSql);
            p.waitFor();
        }
        catch(IOException ie)
        {
            System.out.println("OracleのSqlplusがnot found!!!");
            fail();
        }
        catch(InterruptedException ie)
        {
            System.out.println("Process wait error!!!");
            fail();
        } 

        m_log.info(l_strMethodName + ": EXIT");
    }
    /**
     * getJdbcUrlFromFile
     * @@return
     */
    private static String getJdbcUrl()
    {
        FileReader l_reader = null;
        String l_strUrl = null;
        String jdbcUrl = "jdbc:oracle:thin:";

        try
        {
            l_reader = new FileReader(new File("application/front/test/java/JdbcUrlFile.TXT"));
            BufferedReader l_bufferedreader = new BufferedReader(l_reader);
            l_strUrl = l_bufferedreader.readLine();
            jdbcUrl = jdbcUrl + l_strUrl;
        }
        catch (Exception l_ex)
        {
            System.out.println("get jdbcurl error!!!");
            fail();
        }
        finally
        {
            try
            {
                if (l_reader != null)
                {
                    l_reader.close();
                }
            }
            catch (IOException l_ex)
            {
                System.out.println("get jdbcurl error!!!");
                fail();
            }
        }

        System.out.println("jdbcUrl is " + jdbcUrl);
        return jdbcUrl;
    }
    
    private final String sqlPath = "plugin/web3-xbfeq/test/sql/";
}
@


1.2
log
@*** empty log message ***
@
text
@a43 1
//import webbroker3.compliance.audit.WEB3ComplianceAuditAppPlugin;
d69 1
a69 1
import webbroker3.rlsgateway.WEB3RlsGatewayPlugin;
d73 2
d161 1
a161 1
            //WEB3MQGatewayForTestPlugin.plug();
d163 3
a165 3
            //WEB3RlsGatewayTestPlugin.onPlug(); 
            WEB3RlsGatewayPlugin.onPlug();
            //WEB3RlsOmsAdaptorPlugin.plug();  
d171 2
a172 2
            //WEB3AffinityPlugin.plug();
            //WEB3AffinityTestPlugin.plug();
d205 1
a205 1
            WEB3FeqAppPlugin.plug();
d207 1
a207 1
            ErrorInfo errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00003;
@


1.1
log
@*** empty log message ***
@
text
@a7 1
import oracle.jdbc.pool.OracleConnectionCacheImpl;
d124 3
a126 3
            OracleConnectionCacheImpl oracleConnectionCache = new OracleConnectionCacheImpl(oraclePoolSource);  
            oracleConnectionCache.setMaxLimit(POOL_SIZE);   
            oracleConnectionCache.setCacheScheme(OracleConnectionCacheImpl.FIXED_RETURN_NULL_SCHEME);
d128 1
a128 1
            DataSources.setDefaultDataSource(oracleConnectionCache);   
@

