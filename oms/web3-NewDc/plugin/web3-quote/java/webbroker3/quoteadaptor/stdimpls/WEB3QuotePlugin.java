head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuotePlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価アダプターの管理用メソッドを提供するクラス(WEB3QuoteDataSupplierServiceManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                 : 2005/04/28 山田　@卓司(FLJ) 時価監視サービスの初期化処理を追加
                 : 2005/05/17 山田　@卓司(FLJ) ハートビート関連の属性名定義を追加
                 : 2005/05/17 山田　@卓司(FLJ) サーバ名取得ロジックを追加
                 : 2005/05/24 山田　@卓司(FLJ) 監視サービス用の属性名定義を追加
                 : 2005/05/24 山田　@卓司(FLJ) 監視サービスの初期化方法@を変更
                 : 2009/04/23 齋藤　@栄三(FLJ) 時価システムQUICKへの移行
*/
package webbroker3.quoteadaptor.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.stdimpls.data.WEB3QuoteSessionDatabaseExtensions;
import webbroker3.quoteadaptor.stdimpls.handler.WEB3QuoteMessageHandler;
import webbroker3.quoteadaptor.stdimpls.message.*;
import webbroker3.util.WEB3LogUtility;

/**
 * 標準実装時価サービスのプラグインクラス
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class WEB3QuotePlugin extends Plugin
{
    
    /**
     * 接続する時価サーバのアドレス
     */
    public static final String SERVER_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.server.address";
    
    /**
     * 接続する時価サーバのポート番号
     */
    public static final String SERVER_PORT_PREF_NAME = "webbroker3.quoteadaptor.server.port";
    
    /**
     * 時価サーバに接続するローカルのポート番号
     */
    public static final String LOCAL_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.local.address";
    
    /**
     * 時価サーバに接続するローカルのポート番号
     */
    public static final String LOCAL_PORT_PREF_NAME = "webbroker3.quoteadaptor.local.port";
    
    /**
     * 受信した時価情報を一時的に保存するキューの初期容量
     */
    public static final String QUEUE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.queue.size";
    
    /**
     * 時価情報をキャッシュするハッシュテーブルの初期容量
     */
    public static final String CACHE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.cache.size";
    
    /**
     * 時価情報を更新するスレッドの優先度
     */
    public static final String UPDATER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.updater.priority";
    
    /**
     * 時価情報を受信するスレッドの優先度
     */
    public static final String RECEIVER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.receiver.priority";
    
    /**
     * 時価サーバに再接続する間隔（通常）
     */
    public static final String RETRY_INTERVAL_NORMAL_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.normal";
    
    /**
     * 時価サーバに再接続する間隔（エコノミー）
     */
    public static final String RETRY_INTERVAL_ECONOMY_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.economy";
    
    /**
     * 再接続間隔を通常からエコノミーに変更する閾値
     */
    public static final String RETRY_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.retry.threshold";
    
    /**
     * 受信した時価情報をファ@イルに出力するか判定するフラグ
     */
    public static final String OUTPUT_FILE_REQUIRED_PREF_NAME = "webbroker3.quoteadaptor.output.file.required";
    
    /**
     * 受信した時価情報を出力するファ@イルが作成されるディレクトリ
     */
    public static final String OUTPUT_DIR_PREF_NAME = 
        "webbroker3.quoteadaptor.output.dir";
    
    /**
     * 日経２２５の時価情報が更新されているか監視する間隔
     */
    public static final String MONITOR_INTERVAL_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.interval";
    
    /**
     * 日経２２５の時価情報が更新されていない場合に警告を表示する閾値
     */
    public static final String MONITOR_WARNING_THRESHOLD_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.waring.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（東証）
     */
    public static final String MONITOR_COUNT_THRESHOLD_TSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.tse.threshold";

    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（大証）
     */
    public static final String MONITOR_COUNT_THRESHOLD_OSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.ose.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（名証）
     */
    public static final String MONITOR_COUNT_THRESHOLD_MSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.mse.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（HC）
     */
    public static final String MONITOR_COUNT_THRESHOLD_HC_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.hc.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（JASDAQ）
     */
    public static final String MONITOR_COUNT_THRESHOLD_JASDAQ_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.jasdaq.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（福証）
     */
    public static final String MONITOR_COUNT_THRESHOLD_FSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.fse.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする株式銘柄の個数（札証）
     */
    public static final String MONITOR_COUNT_THRESHOLD_SSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.sse.threshold";
    
    /**
     * 時価情報が更新されていない場合にチェックエラーとする先物OP銘柄の個数
     */
    public static final String MONITOR_COUNT_THRESHOLD_IFO_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.ifo.threshold";
    
    /**
     * 日経２２５の時価情報が更新されているか監視する時間帯（自）
     */
    public static final String MONITOR_START_TIME_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.start.time";

    /**
     * 日経２２５の時価情報が更新されているか監視する時間帯（至）
     */
    public static final String MONITOR_END_TIME_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.end.time";

    /**
     * ハートビートを実行する間隔
     */
    public static final String HEARTBEAT_INTERVAL = 
        "webbroker3.quoteadaptor.heartbeat.interval";

    /**
     * ハートビートのタイムアウト時間
     */
    public static final String HEARTBEAT_TIMEOUT = 
        "webbroker3.quoteadaptor.heartbeat.timeout";

    /**
     * ハートビートを実行するスレッドの優先度
     */
    public static final String HEARTBEAT_PRIORITY_PREF_NAME = 
        "webbroker3.quoteadaptor.heartbeat.priority";
    
    /** プロパティ名：RMMデータソースTOPICリスト */
    public static final String RMM_DS_TOPIC_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.topic.list";
    
    /** プロパティ名：該当RMMデータソースをプライマリとするAPホストリスト */
    public static final String RMM_DS_AP_PRIMARY_RECEIVER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.receiver.primary.ap.host.list";
    
    /** プロパティ名：該当RMMデータソースをセカンダリとするAPホストリスト */
    public static final String RMM_DS_AP_SECONDARY_RECEIVER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.receiver.secondary.ap.host.list";
    
    /** プロパティ名：RMMトピック名 */
    public static final String RMM_DS_TOPIC_NAME_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.topic.name";
    
    /** プロパティ名：RMMデスティネーション */
    public static final String RMM_DS_DESTINATION_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.destination";
    
    /** プロパティ名：RMM時価サーバリスト */
    public static final String RMM_DS_FEEDER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.feeder.list";
    
    /** プロパティ名：RMM時価サーバがリスンするメッセージポートリスト */
    public static final String RMM_DS_FEEDER_MSG_PORT_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.feeder.msg.port.list";
    
    /** プロパティ名：RMMメッセージキューサイズ */
    public static final String RMM_QUEUE_SIZE_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.queue.size";
    
    /** プロパティ名：RMM更新スレッドの優先度 */
    public static final String RMM_UPDATER_PRIORITY_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.updater.priority";
    
    /** プロパティ名：RMM初期化要求リトライ間隔 */
    public static final String RMM_INIT_RETRY_INTERVAL_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.init.retry.interval";
    
    /** プロパティ名：RMMデータソースを切替する初期化要求失敗閾値 */
    public static final String RMM_DS_CHANGE_INIT_THRESHOLD_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.change.init.threshold";
    
    /** プロパティ名：RMM受信コンフィグ接頭辞 */
    public static final String RMM_RCVD_CONFIG_PREFIX
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.config";
    
    /** プロパティ名：RMM受信ANCILLARYコンフィグ接頭辞 */
    public static final String RMM_RCVD_ANCILLARY_PREFIX
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.ancillary";
    
    /** プロパティ名：RMM受信トレースフラグ */
    public static final String RMM_RCVD_TRACE
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.trace";
    
    /** プロパティ名：時価プロトコル */
    public static final String QUOTE_PROTOCOL
        = "webbroker3.quoteadaptor.protocol";
    
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3QuotePlugin.class);
    
    private static final boolean DBG = log.ison();

    private static WEB3DefaultQuoteDataSupplierService quoteSupplier;
    
    /** ローカルホスト名 */
    private static String LOCAL_HOST_NAME;
    
    /** ローカルホスト名(固定長) */
    private static String LOCAL_HOST_NAME_FIXED_LEN;

    static
    {
        try
        {
            String hostName = InetAddress.getLocalHost().getHostName();
            LOCAL_HOST_NAME = hostName;

            if (hostName.length() > WEB3QuoteConstants.HOST_NAME_SIZE)
            {
                LOCAL_HOST_NAME_FIXED_LEN = hostName.substring(0, WEB3QuoteConstants.HOST_NAME_SIZE);
            } else
            {
                LOCAL_HOST_NAME_FIXED_LEN = hostName;
            }
        } catch (UnknownHostException e)
        {
        }

    }

    /**
     * Creates a new SeikoQuoteDataSupplierPlugin object.
     */
    private WEB3QuotePlugin()
    {
    }

    /**
     * Implementation of Plugin#plug() for this class.
     * @@exception Exception - Not thrown by this implementation.
     */
    public static void plug() throws Exception
    {
        plug(WEB3QuotePlugin.class);
    }

    /**
     * 時価サービス起動・停止の登録メッセージの登録、時価サービスの
     * 登録を行う。
     * @@exception Exception 発生することは無い。
     */
    public static void onPlug() throws Exception
    {
        // KernelPluginを登録
        KernelPlugin.plug();
        
        // Enumeratedを登録
        registerEnums();

        // メッセージとメッセージハンドラーを登録
        registerMessagesAndHandlers();
        
        // DB Extensionsを登録
        WEB3QuoteSessionDatabaseExtensions.plug();

        // 時価サービスをセットアップ
        setupQuoteService();
        
        // 接続中または接続済の場合は、サービス開始
        WEB3QuoteStatusManager statusManager = 
            WEB3QuoteStatusManager.getInstance();
        QuoteStatus currentStatus = statusManager.getStatus();
        if (QuoteStatus.CONNECTING.equals(currentStatus)
                || QuoteStatus.CONNECTED.equals(currentStatus))
        {
            WEB3QuoteDataSupplierServiceManager serviceManager =
                (WEB3QuoteDataSupplierServiceManager) Services.getService(WEB3QuoteDataSupplierServiceManager.class);

            String l_strProtocol = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + getLocalAddress());
            
            if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(l_strProtocol))
            {
                serviceManager.startService(WEB3QuoteConstants.WEB3_QUOTE_SERVICE);
            }
            else
            {
                serviceManager.startService(WEB3QuoteConstants.WEB3_QUOTE_RMM_PRIMARY_SERVICE);
            }
        }

        log.info("WEB3QuotePlugin started.");
    }

    /**
     * システム終了時に呼ばれ、全てのサービスを停止する。
     *
     * @@exception Exception if an error occurs
     */
    public static void onUnplug() throws Exception
    {
        if (quoteSupplier != null)
        {
            quoteSupplier.stopAllServices();
        }
    }

    // 時価サービスで使用するメッセージとメッセージハンドラーを登録
    private static void registerMessagesAndHandlers() throws Exception
    {
        //
        // messages
        //
        regClass(WEB3StartQuoteServiceRequest.class);
        regClass(WEB3StopQuoteServiceRequest.class);
        regClass(WEB3StartQuoteServiceBroadcastRequest.class);
        regClass(WEB3StopQuoteServiceBroadcastRequest.class);
        regClass(WEB3QuoteDumpRequest.class);
        regClass(WEB3StartQuoteMonitoringRequest.class);
        regClass(WEB3StopQuoteMonitoringRequest.class);
        regClass(WEB3SwitchQuoteProtocolRequest.class);
        regClass(WEB3ChangeRMMQuoteDataSourceRequest.class);
        
        //
        // handlers
        //
        regHandler(
            WEB3QuotePlugin.class,
            WEB3StartQuoteServiceRequest.class,
            WEB3QuoteMessageHandler.class,
            "handle");

        regHandler(
            WEB3QuotePlugin.class,
            WEB3StopQuoteServiceRequest.class,
            WEB3QuoteMessageHandler.class,
            "handle");

        regHandler(
            WEB3QuotePlugin.class,
            WEB3StartQuoteServiceBroadcastRequest.class,
            WEB3QuoteMessageHandler.class,
            "handle");

        regHandler(
            WEB3QuotePlugin.class,
            WEB3StopQuoteServiceBroadcastRequest.class,
            WEB3QuoteMessageHandler.class,
            "handle");

        regHandler(
                WEB3QuotePlugin.class,
                WEB3QuoteDumpRequest.class,
                WEB3QuoteMessageHandler.class,
                "handle");

        regHandler(
                WEB3QuotePlugin.class,
                WEB3StartQuoteMonitoringRequest.class,
                WEB3QuoteMessageHandler.class,
                "handle");

        regHandler(
                WEB3QuotePlugin.class,
                WEB3StopQuoteMonitoringRequest.class,
                WEB3QuoteMessageHandler.class,
                "handle");

        regHandler(
                WEB3QuotePlugin.class,
                WEB3SwitchQuoteProtocolRequest.class,
                WEB3QuoteMessageHandler.class,
                "handle");

        regHandler(
                WEB3QuotePlugin.class,
                WEB3ChangeRMMQuoteDataSourceRequest.class,
                WEB3QuoteMessageHandler.class,
                "handle");
    }

    // 時価サービスをセットアップ
    private static void setupQuoteService() throws AlreadyInstalledException
    {

        // 時価情報提供サービス
        WEB3DefaultQuoteDataSupplierService supplier =
            new WEB3DefaultQuoteDataSupplierService();

        // キャッシュストア
        WEB3QuoteCacheStore cache =
            new WEB3QuoteCacheStoreImpl(
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.CACHE_SIZE_PREF_NAME,
                    WEB3QuoteConstants.CACHE_SIZE));

        supplier.setCacheStore(cache);
        
        // TCPデータソース
        WEB3QuoteDataSource dataSource =
            new WEB3QuoteDataSourceImpl(
                WEB3QuoteConstants.WEB3_QUOTE_SERVICE,
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.SERVER_ADDRESS_PREF_NAME,
                    WEB3QuoteConstants.SERVER_ADDRESS),
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.SERVER_PORT_PREF_NAME,
                    WEB3QuoteConstants.SERVER_PORT),
                getLocalAddress(),
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.LOCAL_PORT_PREF_NAME,
                    WEB3QuoteConstants.LOCAL_PORT));

        supplier.addService(
            WEB3QuoteConstants.WEB3_QUOTE_SERVICE,
            dataSource,
            (WEB3QuoteEventHandler) supplier.getCacheStore());

        // RMMデータソース一覧
        String l_strDsList = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.RMM_DS_TOPIC_LIST_PREF_NAME);
        
        if(l_strDsList == null || "".equals(l_strDsList))
        {
            log.warn("no rmm datasource found. dsList=" + l_strDsList);
        }

        // データソース一覧
        String[] l_dsList = l_strDsList.split(WEB3QuoteConstants.DELIMITER);

        //PRIMARY SERVICE登録
        for(int i=0;i<l_dsList.length;i++)
        {
            //データソースID
            String l_strDs = l_dsList[i];
            
            //ループ中のデータソースをPrimaryにするホスト一覧
            String l_strPrimaryHosts = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RMM_DS_AP_PRIMARY_RECEIVER_LIST_PREF_NAME + "." + l_strDs);
            
            //自ホスト名が存在する場合
            if(l_strPrimaryHosts != null && l_strPrimaryHosts.indexOf(getLocalAddress()) >= 0)
            {
                WEB3QuoteDataSource l_rmmPrimaryDs =
                    new WEB3QuoteRMMDataSourceImpl(WEB3QuoteConstants.WEB3_QUOTE_RMM_PRIMARY_SERVICE, l_strDs);
                
                supplier.addService(
                        WEB3QuoteConstants.WEB3_QUOTE_RMM_PRIMARY_SERVICE,
                        l_rmmPrimaryDs,
                        (WEB3QuoteEventHandler) supplier.getCacheStore());
            }
        }
        
        //SECONDARY SERVICE登録
        for(int i=0;i<l_dsList.length;i++)
        {
            //データソースID
            String l_strDs = l_dsList[i];
            
            //ループ中のデータソースをSecondaryにするホスト一覧
            String l_strSecondaryHosts = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RMM_DS_AP_SECONDARY_RECEIVER_LIST_PREF_NAME + "." + l_strDs);
            
            //自ホスト名が存在する場合
            if(l_strSecondaryHosts != null && l_strSecondaryHosts.indexOf(getLocalAddress()) >= 0)
            {
                WEB3QuoteDataSource l_rmmSecondaryDs =
                    new WEB3QuoteRMMDataSourceImpl(WEB3QuoteConstants.WEB3_QUOTE_RMM_SECONDARY_SERVICE, l_strDs);
                
                supplier.addService(
                        WEB3QuoteConstants.WEB3_QUOTE_RMM_SECONDARY_SERVICE,
                        l_rmmSecondaryDs,
                        (WEB3QuoteEventHandler) supplier.getCacheStore());
            }
        }


        // 監視サービス
        WEB3QuoteMonitorSettings monitorSettings =
            WEB3QuoteMonitorSettings.getInstance();
        WEB3QuoteMonitor monitor = 
            new WEB3QuoteMonitor(cache, monitorSettings);

        // 管理サービスを登録
        WEB3QuoteDataSupplierServiceManager manager = 
            new WEB3QuoteDataSupplierServiceManagerImpl(monitor);
        Services.registerService(
            WEB3QuoteDataSupplierServiceManager.class,
            manager);

        // install quoteDataSupplierService with eqtype trading module.
        if (DBG)
        {
            log.debug("Install QuoteDataSupplierService with TradingModule.");
        }

        FinApp finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule tm = null;

        //
        // Installation on eqtype trading module.
        //
        try
        {
            tm = finApp.getTradingModule(ProductTypeEnum.EQUITY);
            tm.installQuoteDataSupplierService(supplier);
            if (DBG)
            {
                log.debug(
                    "Installed QuoteDataSupplierService with eqtype TradingModule.");
            }
        } catch (NotInstalledException nie)
        {
            log.error(
                "Failed in installing QuoteDataSupplier with eqtye TradingModule"
                    + nie);
        }
        
        //
        // Installation on IFO trading module.
        //
        try
        {
            tm = finApp.getTradingModule(ProductTypeEnum.IFO);
            tm.installQuoteDataSupplierService(supplier);
            if (DBG)
            {
                log.debug("Installed QuoteDataSupplierService with eqtype TradingModule.");
            }
        } catch (NotInstalledException nie)
        {
            log.error(
                "Failed in installing QuoteDataSupplier with IFO TradingModule"
                    + nie);
        }
        

        quoteSupplier = supplier;
    }

    // 時価サービスで使用するEnumeratedクラスを登録
    private static void registerEnums() throws Exception
    {
        EnumeratedManager mgr = EnumeratedManager.getInstance();
        mgr.addEnumeratedClass(AskPriceTitle.class);
        mgr.addEnumeratedClass(BidPriceTitle.class);
        mgr.addEnumeratedClass(CurrentPriceFlag.class);
        mgr.addEnumeratedClass(DataType.class);
        mgr.addEnumeratedClass(RealType.class);
        mgr.addEnumeratedClass(PutAndCall.class);
        mgr.addEnumeratedClass(IndexType.class);
        mgr.addEnumeratedClass(QuoteStatus.class);
    }
    
    public static String getLocalAddress()
    {
        return LOCAL_HOST_NAME;
    }
    
    /**
     * (getローカルホスト名By固定長)<BR>
     * <BR>
     * ローカルホストのホスト名を所定の固定長で取得する。<BR>
     * 
     * @@return ホスト名
     */
    public static String getLocalHostNameByFixedLen()
    {
        return LOCAL_HOST_NAME_FIXED_LEN;
    }
}
@
