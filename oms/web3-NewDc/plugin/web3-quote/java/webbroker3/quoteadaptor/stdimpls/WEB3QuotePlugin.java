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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�_�v�^�[�̊Ǘ��p���\�b�h��񋟂���N���X(WEB3QuoteDataSupplierServiceManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/04/28 �R�c�@@��i(FLJ) �����Ď��T�[�r�X�̏�����������ǉ�
                 : 2005/05/17 �R�c�@@��i(FLJ) �n�[�g�r�[�g�֘A�̑�������`��ǉ�
                 : 2005/05/17 �R�c�@@��i(FLJ) �T�[�o���擾���W�b�N��ǉ�
                 : 2005/05/24 �R�c�@@��i(FLJ) �Ď��T�[�r�X�p�̑�������`��ǉ�
                 : 2005/05/24 �R�c�@@��i(FLJ) �Ď��T�[�r�X�̏��������@@��ύX
                 : 2009/04/23 �V���@@�h�O(FLJ) �����V�X�e��QUICK�ւ̈ڍs
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
 * �W�����������T�[�r�X�̃v���O�C���N���X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class WEB3QuotePlugin extends Plugin
{
    
    /**
     * �ڑ����鎞���T�[�o�̃A�h���X
     */
    public static final String SERVER_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.server.address";
    
    /**
     * �ڑ����鎞���T�[�o�̃|�[�g�ԍ�
     */
    public static final String SERVER_PORT_PREF_NAME = "webbroker3.quoteadaptor.server.port";
    
    /**
     * �����T�[�o�ɐڑ����郍�[�J���̃|�[�g�ԍ�
     */
    public static final String LOCAL_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.local.address";
    
    /**
     * �����T�[�o�ɐڑ����郍�[�J���̃|�[�g�ԍ�
     */
    public static final String LOCAL_PORT_PREF_NAME = "webbroker3.quoteadaptor.local.port";
    
    /**
     * ��M�������������ꎞ�I�ɕۑ�����L���[�̏����e��
     */
    public static final String QUEUE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.queue.size";
    
    /**
     * ���������L���b�V������n�b�V���e�[�u���̏����e��
     */
    public static final String CACHE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.cache.size";
    
    /**
     * ���������X�V����X���b�h�̗D��x
     */
    public static final String UPDATER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.updater.priority";
    
    /**
     * ����������M����X���b�h�̗D��x
     */
    public static final String RECEIVER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.receiver.priority";
    
    /**
     * �����T�[�o�ɍĐڑ�����Ԋu�i�ʏ�j
     */
    public static final String RETRY_INTERVAL_NORMAL_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.normal";
    
    /**
     * �����T�[�o�ɍĐڑ�����Ԋu�i�G�R�m�~�[�j
     */
    public static final String RETRY_INTERVAL_ECONOMY_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.economy";
    
    /**
     * �Đڑ��Ԋu��ʏ킩��G�R�m�~�[�ɕύX����臒l
     */
    public static final String RETRY_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.retry.threshold";
    
    /**
     * ��M�������������t�@@�C���ɏo�͂��邩���肷��t���O
     */
    public static final String OUTPUT_FILE_REQUIRED_PREF_NAME = "webbroker3.quoteadaptor.output.file.required";
    
    /**
     * ��M�������������o�͂���t�@@�C�����쐬�����f�B���N�g��
     */
    public static final String OUTPUT_DIR_PREF_NAME = 
        "webbroker3.quoteadaptor.output.dir";
    
    /**
     * ���o�Q�Q�T�̎�����񂪍X�V����Ă��邩�Ď�����Ԋu
     */
    public static final String MONITOR_INTERVAL_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.interval";
    
    /**
     * ���o�Q�Q�T�̎�����񂪍X�V����Ă��Ȃ��ꍇ�Ɍx����\������臒l
     */
    public static final String MONITOR_WARNING_THRESHOLD_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.waring.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��i���؁j
     */
    public static final String MONITOR_COUNT_THRESHOLD_TSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.tse.threshold";

    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��i��؁j
     */
    public static final String MONITOR_COUNT_THRESHOLD_OSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.ose.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��i���؁j
     */
    public static final String MONITOR_COUNT_THRESHOLD_MSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.mse.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��iHC�j
     */
    public static final String MONITOR_COUNT_THRESHOLD_HC_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.hc.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��iJASDAQ�j
     */
    public static final String MONITOR_COUNT_THRESHOLD_JASDAQ_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.jasdaq.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��i���؁j
     */
    public static final String MONITOR_COUNT_THRESHOLD_FSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.fse.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ��銔�������̌��i�D�؁j
     */
    public static final String MONITOR_COUNT_THRESHOLD_SSE_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.eqtype.sse.threshold";
    
    /**
     * ������񂪍X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ���敨OP�����̌�
     */
    public static final String MONITOR_COUNT_THRESHOLD_IFO_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.ifo.threshold";
    
    /**
     * ���o�Q�Q�T�̎�����񂪍X�V����Ă��邩�Ď����鎞�ԑсi���j
     */
    public static final String MONITOR_START_TIME_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.start.time";

    /**
     * ���o�Q�Q�T�̎�����񂪍X�V����Ă��邩�Ď����鎞�ԑсi���j
     */
    public static final String MONITOR_END_TIME_PREF_NAME = 
        "webbroker3.quoteadaptor.monitor.end.time";

    /**
     * �n�[�g�r�[�g�����s����Ԋu
     */
    public static final String HEARTBEAT_INTERVAL = 
        "webbroker3.quoteadaptor.heartbeat.interval";

    /**
     * �n�[�g�r�[�g�̃^�C���A�E�g����
     */
    public static final String HEARTBEAT_TIMEOUT = 
        "webbroker3.quoteadaptor.heartbeat.timeout";

    /**
     * �n�[�g�r�[�g�����s����X���b�h�̗D��x
     */
    public static final String HEARTBEAT_PRIORITY_PREF_NAME = 
        "webbroker3.quoteadaptor.heartbeat.priority";
    
    /** �v���p�e�B���FRMM�f�[�^�\�[�XTOPIC���X�g */
    public static final String RMM_DS_TOPIC_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.topic.list";
    
    /** �v���p�e�B���F�Y��RMM�f�[�^�\�[�X���v���C�}���Ƃ���AP�z�X�g���X�g */
    public static final String RMM_DS_AP_PRIMARY_RECEIVER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.receiver.primary.ap.host.list";
    
    /** �v���p�e�B���F�Y��RMM�f�[�^�\�[�X���Z�J���_���Ƃ���AP�z�X�g���X�g */
    public static final String RMM_DS_AP_SECONDARY_RECEIVER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.receiver.secondary.ap.host.list";
    
    /** �v���p�e�B���FRMM�g�s�b�N�� */
    public static final String RMM_DS_TOPIC_NAME_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.topic.name";
    
    /** �v���p�e�B���FRMM�f�X�e�B�l�[�V���� */
    public static final String RMM_DS_DESTINATION_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.destination";
    
    /** �v���p�e�B���FRMM�����T�[�o���X�g */
    public static final String RMM_DS_FEEDER_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.feeder.list";
    
    /** �v���p�e�B���FRMM�����T�[�o�����X�����郁�b�Z�[�W�|�[�g���X�g */
    public static final String RMM_DS_FEEDER_MSG_PORT_LIST_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.feeder.msg.port.list";
    
    /** �v���p�e�B���FRMM���b�Z�[�W�L���[�T�C�Y */
    public static final String RMM_QUEUE_SIZE_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.queue.size";
    
    /** �v���p�e�B���FRMM�X�V�X���b�h�̗D��x */
    public static final String RMM_UPDATER_PRIORITY_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.updater.priority";
    
    /** �v���p�e�B���FRMM�������v�����g���C�Ԋu */
    public static final String RMM_INIT_RETRY_INTERVAL_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.init.retry.interval";
    
    /** �v���p�e�B���FRMM�f�[�^�\�[�X��ؑւ��鏉�����v�����s臒l */
    public static final String RMM_DS_CHANGE_INIT_THRESHOLD_PREF_NAME
        = "webbroker3.quoteadaptor.rmm.ds.change.init.threshold";
    
    /** �v���p�e�B���FRMM��M�R���t�B�O�ړ��� */
    public static final String RMM_RCVD_CONFIG_PREFIX
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.config";
    
    /** �v���p�e�B���FRMM��MANCILLARY�R���t�B�O�ړ��� */
    public static final String RMM_RCVD_ANCILLARY_PREFIX
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.ancillary";
    
    /** �v���p�e�B���FRMM��M�g���[�X�t���O */
    public static final String RMM_RCVD_TRACE
        = "webbroker3.quoteadaptor.rmm.ds.rcvd.trace";
    
    /** �v���p�e�B���F�����v���g�R�� */
    public static final String QUOTE_PROTOCOL
        = "webbroker3.quoteadaptor.protocol";
    
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3QuotePlugin.class);
    
    private static final boolean DBG = log.ison();

    private static WEB3DefaultQuoteDataSupplierService quoteSupplier;
    
    /** ���[�J���z�X�g�� */
    private static String LOCAL_HOST_NAME;
    
    /** ���[�J���z�X�g��(�Œ蒷) */
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
     * �����T�[�r�X�N���E��~�̓o�^���b�Z�[�W�̓o�^�A�����T�[�r�X��
     * �o�^���s���B
     * @@exception Exception �������邱�Ƃ͖����B
     */
    public static void onPlug() throws Exception
    {
        // KernelPlugin��o�^
        KernelPlugin.plug();
        
        // Enumerated��o�^
        registerEnums();

        // ���b�Z�[�W�ƃ��b�Z�[�W�n���h���[��o�^
        registerMessagesAndHandlers();
        
        // DB Extensions��o�^
        WEB3QuoteSessionDatabaseExtensions.plug();

        // �����T�[�r�X���Z�b�g�A�b�v
        setupQuoteService();
        
        // �ڑ����܂��͐ڑ��ς̏ꍇ�́A�T�[�r�X�J�n
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
     * �V�X�e���I�����ɌĂ΂�A�S�ẴT�[�r�X���~����B
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

    // �����T�[�r�X�Ŏg�p���郁�b�Z�[�W�ƃ��b�Z�[�W�n���h���[��o�^
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

    // �����T�[�r�X���Z�b�g�A�b�v
    private static void setupQuoteService() throws AlreadyInstalledException
    {

        // �������񋟃T�[�r�X
        WEB3DefaultQuoteDataSupplierService supplier =
            new WEB3DefaultQuoteDataSupplierService();

        // �L���b�V���X�g�A
        WEB3QuoteCacheStore cache =
            new WEB3QuoteCacheStoreImpl(
                WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.CACHE_SIZE_PREF_NAME,
                    WEB3QuoteConstants.CACHE_SIZE));

        supplier.setCacheStore(cache);
        
        // TCP�f�[�^�\�[�X
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

        // RMM�f�[�^�\�[�X�ꗗ
        String l_strDsList = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.RMM_DS_TOPIC_LIST_PREF_NAME);
        
        if(l_strDsList == null || "".equals(l_strDsList))
        {
            log.warn("no rmm datasource found. dsList=" + l_strDsList);
        }

        // �f�[�^�\�[�X�ꗗ
        String[] l_dsList = l_strDsList.split(WEB3QuoteConstants.DELIMITER);

        //PRIMARY SERVICE�o�^
        for(int i=0;i<l_dsList.length;i++)
        {
            //�f�[�^�\�[�XID
            String l_strDs = l_dsList[i];
            
            //���[�v���̃f�[�^�\�[�X��Primary�ɂ���z�X�g�ꗗ
            String l_strPrimaryHosts = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RMM_DS_AP_PRIMARY_RECEIVER_LIST_PREF_NAME + "." + l_strDs);
            
            //���z�X�g�������݂���ꍇ
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
        
        //SECONDARY SERVICE�o�^
        for(int i=0;i<l_dsList.length;i++)
        {
            //�f�[�^�\�[�XID
            String l_strDs = l_dsList[i];
            
            //���[�v���̃f�[�^�\�[�X��Secondary�ɂ���z�X�g�ꗗ
            String l_strSecondaryHosts = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.RMM_DS_AP_SECONDARY_RECEIVER_LIST_PREF_NAME + "." + l_strDs);
            
            //���z�X�g�������݂���ꍇ
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


        // �Ď��T�[�r�X
        WEB3QuoteMonitorSettings monitorSettings =
            WEB3QuoteMonitorSettings.getInstance();
        WEB3QuoteMonitor monitor = 
            new WEB3QuoteMonitor(cache, monitorSettings);

        // �Ǘ��T�[�r�X��o�^
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

    // �����T�[�r�X�Ŏg�p����Enumerated�N���X��o�^
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
     * (get���[�J���z�X�g��By�Œ蒷)<BR>
     * <BR>
     * ���[�J���z�X�g�̃z�X�g��������̌Œ蒷�Ŏ擾����B<BR>
     * 
     * @@return �z�X�g��
     */
    public static String getLocalHostNameByFixedLen()
    {
        return LOCAL_HOST_NAME_FIXED_LEN;
    }
}
@
