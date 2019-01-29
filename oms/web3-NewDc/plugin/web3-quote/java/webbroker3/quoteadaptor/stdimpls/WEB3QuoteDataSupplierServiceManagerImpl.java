head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSupplierServiceManagerImpl.java;


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
Revision History : 2004/05/25 山田　@卓司(FLJ) 新規作成
                 : 2005/04/28 山田　@卓司(FLJ) 時価監視の開始・終了処理を追加
                 : 2005/05/17 山田　@卓司(FLJ) 接続ステータスの変更処理を追加
                 : 2005/05/24 山田　@卓司(FLJ) 監視サービスが既に開始されている場合は開始しないように変更
                 : 2009/04/23 齋藤　@栄三(FLJ) 時価システムQUICKへの移行
*/
package webbroker3.quoteadaptor.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import webbroker3.quoteadaptor.WEB3QuoteDataSource;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * 時価アダプターの管理用メソッドを提供するクラス<br>
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteDataSupplierServiceManagerImpl
    implements WEB3QuoteDataSupplierServiceManager
{
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteDataSupplierServiceManagerImpl.class);
    
    private final WEB3QuoteMonitor monitor;

    private int rmmIndex = 0;
    
    /**
     * コンストラクタ
     */
    WEB3QuoteDataSupplierServiceManagerImpl(WEB3QuoteMonitor monitor)
    {
        this.monitor = monitor;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierServiceManager#startService(String)
     */
    public void startService(String id)
    {
        WEB3QuoteStatusManager.getInstance().modifyStatus(
            QuoteStatus.CONNECTING);
        getQuoteDataSupplierService().startService(id);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierServiceManager#startAllServices()
     */
    public void startAllServices()
    {
        WEB3QuoteStatusManager.getInstance().modifyStatus(
            QuoteStatus.CONNECTING);
        getQuoteDataSupplierService().startAllServices();
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierServiceManager#stopService(String)
     */
    public void stopService(String id)
    {
        getQuoteDataSupplierService().stopService(id);
        WEB3QuoteStatusManager.getInstance().modifyStatus(
           QuoteStatus.CLOSED);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierServiceManager#stopAllService()
     */
    public void stopAllServices()
    {
        getQuoteDataSupplierService().stopAllServices();
        WEB3QuoteStatusManager.getInstance().modifyStatus(
            QuoteStatus.CLOSED);
    }

    /**
     * @@see webbroker3.quoteadaptor.stdimpls.WEB3QuoteDataSupplierServiceManager#dump()
     */
    public void dump()
    {
        getQuoteDataSupplierService().dump();
    }

    /**
     * @@see webbroker3.quoteadaptor.stdimpls.WEB3QuoteDataSupplierServiceManager#startMonitoring()
     */
    public void startMonitoring()
    {
        if (!monitor.isRunning())
        {
            monitor.start();
        }
    }
    
    /**
     * @@see webbroker3.quoteadaptor.stdimpls.WEB3QuoteDataSupplierServiceManager#stopMonitoring()
     */
    public void stopMonitoring()
    {
        monitor.stop();
    }
    
    /**
     * WEB3DefaultQuoteDataSupplierServiceを取得する。
     * 
     * @@return WEB3DefaultQuoteDataSupplierService
     */
    private WEB3DefaultQuoteDataSupplierService getQuoteDataSupplierService()
    {
        QuoteDataSupplierService supplier =
            GtlUtils
                .getTradingModule(ProductTypeEnum.EQUITY)
                .getQuoteDataSupplierService();
        if (supplier instanceof WEB3DefaultQuoteDataSupplierService)
        {
            return (WEB3DefaultQuoteDataSupplierService) supplier;
        } else
        {
            throw new IllegalStateException("Registered QuoteDataSupplierService is not instance of WEB3DefaultQuoteDataSupplierService.");
        }
    }
    
    /**
     * RMMデータソース同士で切替する。
     * 
     */
    public WEB3QuoteFutureData changeRMMDataSource()
    {
    	final WEB3QuoteFutureData l_data = new WEB3QuoteFutureData();
    	
    	Thread l_thread = new Thread()
		{
        	public void run()
        	{
                try
    	        {
    	            String l_realData = changeRMMDs();
    	            l_data.setRealData(l_realData);
    	        }
    	        catch (Throwable e)
    	        {
                    String l_strMessage = "change rmm primary/secondary failed. " + e.getMessage();
                    l_data.setRealData(l_strMessage);
    	            log.error(l_strMessage, e);
    	        }
        	}
		};
    	l_thread.setDaemon(true);
    	l_thread.start();
    	
    	return l_data;
    }
    
    /**
     * RMMデータソース同士で切替する。
     * 
     */
    private synchronized String changeRMMDs()
    {
    	String l_strMessage = null;
    	
        log.info("changing rmm primary/secondary.");

        String l_strProtocol = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + WEB3QuotePlugin.getLocalAddress());
        
        if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(l_strProtocol))
        {
            String l_strMsg = "Protocol Gap Error:Current Protocol=" + l_strProtocol; 
            log.error(l_strMsg);
            throw new IllegalStateException(l_strMsg);
        }

        List l_dataSources = getQuoteDataSupplierService().getRMMDsList();
        
        if(l_dataSources.isEmpty())
        {
            String l_strMsg = "None of the RMM QuoteDataSources are registered."; 
            log.error(l_strMsg);
            throw new IllegalStateException(l_strMsg);
        }

        WEB3QuoteDataSource l_currentDs = (WEB3QuoteDataSource)l_dataSources.get(rmmIndex);
        log.info("l_currentDs=" + l_currentDs);
        
        WEB3QuoteDataSource l_nextDs = null;
        
        int l_intLimit = l_dataSources.size() - 1;
        
        if(++rmmIndex > l_intLimit)
        {
            rmmIndex=0;
        }
        l_nextDs = (WEB3QuoteDataSource)l_dataSources.get(rmmIndex);
        log.info("l_nextDs=" + l_nextDs);
        
        //現行のデータソースが起動済 かつ 次のデータソースが未起動の場合
        if(l_currentDs.isStarted() && !l_nextDs.isStarted())
        {
            String l_strCurServiceId = l_currentDs.getServiceId();
            List l_curFeeders = l_currentDs.getFeeders();

            l_strMessage = "change rmm datasource from " + l_strCurServiceId + " " + l_curFeeders;
            
            String l_strNextServiceId = l_nextDs.getServiceId();

        	//現行のデータソースを停止する
            stopService(l_strCurServiceId);
            log.info("stopService called. service_id=" + l_strCurServiceId);

            //次のデータソースを起動する        
            startService(l_strNextServiceId);
            log.info("startService called. service_id=" + l_strNextServiceId);

            List l_nextFeeders = l_nextDs.getFeeders();

            l_strMessage += " to " + l_strNextServiceId + " " + l_nextFeeders + ".";
        	log.info(l_strMessage);
        }
        else
        {
            String l_strMsg = "RMM QuoteDataSources are illegalState. current=" + l_currentDs + " next=" + l_nextDs;
            log.error(l_strMsg);
            throw new IllegalStateException(l_strMsg);
        }
        log.info("change rmm datasource succeeded.");
        
        return l_strMessage;
    }
    
    /**
     * TCPとRMMのデータソースを切替える。
     * 
     */
    public synchronized String switchDataSourceProtocol()
    {
    	String l_strMessage = null;
    	
        String l_strBeforeRmmProtocol = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + WEB3QuotePlugin.getLocalAddress());

        //現在のステータスを取得
        QuoteStatus l_currentStatus = WEB3QuoteStatusManager.getInstance().getStatus();

        //プロパティを更新する
        switchProtocolConfig();
        
        //以前はTCPで動作していた場合(今回はRMM)
        if(WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(l_strBeforeRmmProtocol))
        {
        	l_strMessage = "switch protocol from TCP to RMM."; 
        	log.info(l_strMessage);
        		
            if(QuoteStatus.CONNECTING.equals(l_currentStatus)
                    || QuoteStatus.CONNECTED.equals(l_currentStatus))
            {
                //TCPを停止する
                List l_tcpDataSources = getQuoteDataSupplierService().getTCPDsList();
                for(int i=0;i<l_tcpDataSources.size();i++)
                {
                    WEB3QuoteDataSource l_tcpDs = (WEB3QuoteDataSource)l_tcpDataSources.get(i);
                    if(l_tcpDs.isStarted())
                    {
                        String l_strServiceId = l_tcpDs.getServiceId();
                        stopService(l_strServiceId);
                        log.info("stopService called. service_id=" + l_strServiceId);
                    }
                }
                
                List l_rmmDataSources = getQuoteDataSupplierService().getRMMDsList();
                //RMMのPrimaryを起動する
                for(int i=0;i<l_rmmDataSources.size();i++)
                {
                    WEB3QuoteDataSource l_rmmDs = (WEB3QuoteDataSource)l_rmmDataSources.get(i);
                    String l_strServiceId = l_rmmDs.getServiceId();
                    if(WEB3QuoteConstants.WEB3_QUOTE_RMM_PRIMARY_SERVICE.equals(l_strServiceId))
                    {
                        if(!l_rmmDs.isStarted())
                        {
                            startService(l_strServiceId);
                            rmmIndex = 0;
                            log.info("startService called. service_id=" + l_strServiceId);
                        }
                        else
                        {
                            String l_strMsg = "RMM QuoteDataSource is already started:" + l_rmmDs; 
                            log.error(l_strMsg);
                            throw new IllegalStateException(l_strMsg);
                        }
                    }
                }
            }
        }
        //以前はRMMで動作していた場合(今回はTCP)
        else
        {
        	l_strMessage = "switch protocol from RMM to TCP.";
        	log.info(l_strMessage);

        	if(QuoteStatus.CONNECTING.equals(l_currentStatus)
                    || QuoteStatus.CONNECTED.equals(l_currentStatus))
            {
                //RMMを停止する
                List l_rmmDataSources = getQuoteDataSupplierService().getRMMDsList();
                for(int i=0;i<l_rmmDataSources.size();i++)
                {
                    WEB3QuoteDataSource l_rmmDs = (WEB3QuoteDataSource)l_rmmDataSources.get(i);
                    if(l_rmmDs.isStarted())
                    {
                        String l_strServiceId = l_rmmDs.getServiceId();
                        stopService(l_strServiceId);
                        log.info("stopService called. service_id=" + l_strServiceId);
                    }
                }
                rmmIndex = 0;

                List l_tcpDataSources = getQuoteDataSupplierService().getTCPDsList();
                //TCPを起動する
                for(int i=0;i<l_tcpDataSources.size();i++)
                {
                    WEB3QuoteDataSource l_tcpDs = (WEB3QuoteDataSource)l_tcpDataSources.get(i);
                    String l_strServiceId = l_tcpDs.getServiceId();
                    if(WEB3QuoteConstants.WEB3_QUOTE_SERVICE.equals(l_strServiceId))
                    {
                        if(!l_tcpDs.isStarted())
                        {
                            startService(l_strServiceId);
                            log.info("startService called. service_id=" + l_strServiceId);
                        }
                        else
                        {
                            String l_strMsg = "TCP QuoteDataSource is already started:" + l_tcpDs; 
                            log.error(l_strMsg);
                            throw new IllegalStateException(l_strMsg);
                        }
                    }
                }
            }
        }
        log.info("switch protocol succeeded.");
        return l_strMessage;
    }
    

    /**
     * SYSTEM_PREFERENCESテーブルのプロトコル設定を反転する
     *
     */
    private void switchProtocolConfig()
    {

        String l_strProtocol = WEB3QuotePropertyManager.getProperty(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + WEB3QuotePlugin.getLocalAddress());
        
        final String l_strValue = WEB3QuoteConstants.QUOTE_PROTOCOL_TCP.equalsIgnoreCase(l_strProtocol) ?
                    WEB3QuoteConstants.QUOTE_PROTOCOL_RMM : WEB3QuoteConstants.QUOTE_PROTOCOL_TCP;

        try
        {

            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            //新規トランザクション
            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                {
                    public Object process()throws DataNetworkException, DataQueryException, DataCallbackException
                    {
                        l_qp.lockAccount(0, true);
    
                        Map l_changes = new HashMap();
                        l_changes.put("value", l_strValue);
                        l_changes.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                        //更新
                        int l_intUpdatedCount = 
                            l_qp.doUpdateQuery(new SystemPreferencesPK(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + WEB3QuotePlugin.getLocalAddress()),
                                               l_changes);
                        
                        //存在しない場合
                        if (l_intUpdatedCount == 0)
                        {
                            //インサート
                            SystemPreferencesParams l_params = new SystemPreferencesParams();
                            l_params.setName(WEB3QuotePlugin.QUOTE_PROTOCOL + "." + WEB3QuotePlugin.getLocalAddress());
                            l_params.setValue(l_strValue);
                            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());
                            l_qp.doInsertQuery(l_params);
                        }
    
                        return null;
                    }
                }
            );
            log.info("updated db protocol value. from=" + l_strProtocol + " to=" + l_strValue);
        }
        catch (DataException de)
        {
            log.error("update system_preferences failed. value from=" + l_strProtocol + " to=" + l_strValue + " " + de.getMessage(), de);
        }
    }
}
@
