head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerConnectorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （サーバーサイト管理）サーバーAccessor関連の初期化を行うためのプラグインクラス(Web3ServerConnectorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.affinity;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.handler.client.*;
import webbroker3.system.data.*;
import webbroker3.system.tune.affinity.util.*;
import webbroker3.util.*;

/**
 *  サーバーサイト管理Accessor関連の初期化を行うためのプラグイン
 */
public class WEB3ServerConnectorPlugin
    extends Plugin
{

    /**
     * ログ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ServerConnectorPlugin.class);

    /**
     *  サーバーサイト管理Accessorを作成するファ@クトリクラス
     */
    private static class Web3StaticClusterServerAccessorFactory
        implements ServerAccessorFactory
    {

        public ServerAccessor createAccessor(ServerAccessor initialAccessor)
        {
            String urls[] = ServerAccessorConfig.getConfigValueClusterUrls();
            if (urls == null || urls.length == 0)
            {
                log.warn("server_config table contains no values in category 'cluster.urls', returning supplied initial accessor instead, value=" +
                         initialAccessor);
                return initialAccessor;
            }

            Hashtable web3AffinityAccessors = new Hashtable(3);
            long timeoutOnFailureMillis = (long) ServerAccessorConfig.
                getConfigValueRetryAfterSeconds() * 1000L;
            for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
            {
                WEB3ServerTryOrderProviderCallbackStdImpl callback = new
                    WEB3ServerTryOrderProviderCallbackStdImpl();
                callback.setAccIdTryOrderMapInfos(loadAccIdTryOrderMapInfos());
                callback.setReqNumTryOrderMapInfos(loadReqNumTryOrderMapInfos());
                callback.setAccIdRangeTryOrderMapInfos(loadAccIdRangeTryOrderMapInfos());
                //webbroker3カスタマイズAffinityAccessorを作成する
                WEB3AffinityAccessor affinityAccessor = new WEB3AffinityAccessor(
                    WEB3ServerTryOrderProviderCallback.TAGNAMES[i],
                    urls,
                    timeoutOnFailureMillis,
                    callback);
                web3AffinityAccessors.put(WEB3ServerTryOrderProviderCallback.TAGNAMES[i],
                                          affinityAccessor);
            }
            //サーバーサイト管理Accessorを作成する
            WEB3ServerMgrAccessor serverAccessor = new WEB3ServerMgrAccessor(
                web3AffinityAccessors
                );
            //SocketPoolの場合、Socket属性を設定する
            setSocketPoolAccessor(serverAccessor.getAffinityAccessors());
            return serverAccessor;

        }

        /**
         * SocketPoolAccessor配置情報設定
         * @@param affinityAccessor web3AffinityAccessor
         */
        private void setSocketPoolAccessor(AffinityAccessor[] affinityAccessors)
        {

            try
            {
                for (int j = 0; j < affinityAccessors.length; j++)
                {
                    AffinityAccessor affinityAccessor = affinityAccessors[j];
                    ServerAccessor accessors[] = affinityAccessor.getServerAccessors();
                    for (int i = 0; i < accessors.length; i++)
                    {
                        ServerAccessor accessor = accessors[i];
                        if (accessor instanceof SocketPoolAccessor)
                        {
                            SocketPoolAccessor socketPoolAccessor = (SocketPoolAccessor)
                                accessor;
                            socketPoolAccessor.setMaxSize(ServerAccessorConfig.
                                getConfigValueSocketPoolClientMaxSize());
                            socketPoolAccessor.setTimeOut(ServerAccessorConfig.
                                getConfigValueSocketPoolTimeOut());
                            socketPoolAccessor.setBufferSize(ServerAccessorConfig.
                                getConfigValueSocketPoolBufferSize());
                        }
                    }
                }

            }
            catch (Exception e)
            {
                log.warn(
                    "Can not config individual SocketPoolAccessor of an Web3AffinityAccessor due to Exception: " +
                    e);
            }

        }

        /**
         * アカウントIDトライオーダマップ情報をロードする
         *
         * @@return アカウントIDトライオーダマップ情報
         * @@param size int
         */
        private WEB3AcctIdTryOrderMapInfo[] loadAccIdTryOrderMapInfos()
        {
            try
            {
                QueryProcessor qp = null;
                qp = Processors.getDefaultProcessor();
                String l_strWhere = " server_type = ? ";
                Object[] l_objWhere =
                    {
                    new Long(ServerTypeEnum.AP.intValue())};
                List list = qp.doFindAllQuery(AffinityRangeBasedMapRow.TYPE, l_strWhere,
                                              "key_start,key_end,range_order_no", null,
                                              l_objWhere);
                ArrayList lstAcctIdTryOrderMapInfos = new ArrayList();
                long key_start = 0;
                long key_end = 0;
                WEB3AcctIdTryOrderMapInfo mapInfo = null;
                ArrayList lstTryOrder = null;
                for (int i = 0; i < list.size(); i++)
                {
                    AffinityRangeBasedMapRow row = (AffinityRangeBasedMapRow) list.get(i);
                    if (row.getKeyStart() != key_start || row.getKeyEnd() != key_end)
                    {
                        if (mapInfo != null && lstTryOrder != null)
                        {
                            int intTryOrder[] = new int[lstTryOrder.size()];
                            for (int j = 0; j < intTryOrder.length; j++)
                            {
                                Long lngOrder = (Long) lstTryOrder.get(j);
                                if (lngOrder.intValue() > 0)
                                {
                                    intTryOrder[j] = lngOrder.intValue() - 1;
                                }
                                else
                                {
                                    intTryOrder[j] = lngOrder.intValue();
                                }
                            }
                            mapInfo.setAppServerTryOrder(intTryOrder);
                        }

                        mapInfo = new WEB3AcctIdTryOrderMapInfo();
                        long lngServerNo = Long.parseLong(row.getServerId());
                        lstTryOrder = new ArrayList();
                        lstTryOrder.add(new Long(lngServerNo));
                        mapInfo.setAccountIdStart(row.getKeyStart());
                        mapInfo.setAccountIdEnd(row.getKeyEnd());
                        key_start = row.getKeyStart();
                        key_end = row.getKeyEnd();
                        lstAcctIdTryOrderMapInfos.add(mapInfo);

                    }
                    else
                    {
                        long lngServerNo = Long.parseLong(row.getServerId());
                        lstTryOrder.add(new Long(lngServerNo));
                    }
                }
                if (mapInfo != null && lstTryOrder != null)
                {
                    int intTryOrder[] = new int[lstTryOrder.size()];
                    for (int j = 0; j < intTryOrder.length; j++)
                    {
                        Long lngOrder = (Long) lstTryOrder.get(j);
                        if (lngOrder.intValue() > 0)
                        {
                            intTryOrder[j] = lngOrder.intValue() - 1;
                        }
                        else
                        {
                            intTryOrder[j] = lngOrder.intValue();
                        }
                    }
                    mapInfo.setAppServerTryOrder(intTryOrder);
                }
                WEB3AcctIdTryOrderMapInfo[] ret = new WEB3AcctIdTryOrderMapInfo[
                    lstAcctIdTryOrderMapInfos.size()];
                if (log.ison())
                {
                    String l_strDubug = "loadAccIdTryOrderMapInfos:\n";
                    for (int i = 0; i < lstAcctIdTryOrderMapInfos.size(); i++)
                    {
                        l_strDubug += WEB3SystemObjectPrint.
                            printWEB3AcctIdTryOrderMapInfo( (WEB3AcctIdTryOrderMapInfo)
                            lstAcctIdTryOrderMapInfos.get(i));
                    }
                    log.debug(l_strDubug);
                }
                return (WEB3AcctIdTryOrderMapInfo[]) lstAcctIdTryOrderMapInfos.toArray(
                    ret);

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;

            }

        }

        /**
         * アカウントIDレンジトライオーダマップ情報をロードする
         *
         * @@return アカウントIDレンジトライオーダマップ情報
         * @@param size int
         */
        private WEB3AcctIdRangeTryOrderMapInfo[] loadAccIdRangeTryOrderMapInfos()
        {
            try
            {
                QueryProcessor qp = null;
                qp = Processors.getDefaultProcessor();
                String l_strWhere = " server_type = ? and range_order_no = 1 ";
                Object[] l_objWhere =
                    {
                    new Long(ServerTypeEnum.AP.intValue())};
                List list = qp.doFindAllQuery(AffinityRangeBasedMapRow.TYPE, l_strWhere,
                                              "key_start,key_end,server_id", null,
                                              l_objWhere);
                ArrayList lstAcctIdTryOrderMapInfos = new ArrayList();
                String serverId = null;
                WEB3AcctIdRangeTryOrderMapInfo mapInfo = null;
                Long accIdRangeStart = null;
                Long accIdRangeEnd = null;
                ArrayList lstTryOrder = null;
                for (int i = 0; i < list.size(); i++)
                {
                    AffinityRangeBasedMapRow row = (AffinityRangeBasedMapRow) list.get(i);
                    if (!row.getServerId().equals(serverId))
                    {
                        if (mapInfo != null && accIdRangeStart != null && lstTryOrder != null)
                        {
                            int intTryOrder[] = new int[lstTryOrder.size()];
                            for (int j = 0; j < intTryOrder.length; j++)
                            {
                                Long lngOrder = (Long) lstTryOrder.get(j);
                                if (lngOrder.intValue() > 0)
                                {
                                    intTryOrder[j] = lngOrder.intValue() - 1;
                                }
                                else
                                {
                                    intTryOrder[j] = lngOrder.intValue();
                                }
                            }
                            mapInfo.setAppServerTryOrder(intTryOrder);
                            mapInfo.setAccountIdEnd(accIdRangeEnd.longValue());
                        }

                        mapInfo = new WEB3AcctIdRangeTryOrderMapInfo();
                        long lngServerNo = Long.parseLong(row.getServerId());
                        lstTryOrder = new ArrayList();
                        lstTryOrder.add(new Long(lngServerNo));
                        mapInfo.setAccountIdStart(row.getKeyStart());
                        accIdRangeStart = new Long(row.getKeyStart());
                        accIdRangeEnd = new Long(row.getKeyEnd());
                        lstAcctIdTryOrderMapInfos.add(mapInfo);

                    }
                    else
                    {
                        accIdRangeEnd = new Long(row.getKeyEnd());
                    }

                    serverId=row.getServerId();
                }
                if (mapInfo != null && accIdRangeStart != null && lstTryOrder != null)
                {
                    int intTryOrder[] = new int[lstTryOrder.size()];
                    for (int j = 0; j < intTryOrder.length; j++)
                    {
                        Long lngOrder = (Long) lstTryOrder.get(j);
                        if (lngOrder.intValue() > 0)
                        {
                            intTryOrder[j] = lngOrder.intValue() - 1;
                        }
                        else
                        {
                            intTryOrder[j] = lngOrder.intValue();
                        }
                    }
                    mapInfo.setAppServerTryOrder(intTryOrder);
                    mapInfo.setAccountIdEnd(accIdRangeEnd.longValue());
                }
                WEB3AcctIdRangeTryOrderMapInfo[] ret = new WEB3AcctIdRangeTryOrderMapInfo[
                    lstAcctIdTryOrderMapInfos.size()];
                if (log.ison())
                {
                    String l_strDubug = "loadAccIdRangeTryOrderMapInfos:\n";
                    for (int i = 0; i < lstAcctIdTryOrderMapInfos.size(); i++)
                    {
                        l_strDubug += WEB3SystemObjectPrint.
                            printWEB3AcctIdTryOrderMapInfo( (WEB3AcctIdTryOrderMapInfo)
                            lstAcctIdTryOrderMapInfos.get(i));
                    }
                    log.debug(l_strDubug);
                }
                return (WEB3AcctIdRangeTryOrderMapInfo[]) lstAcctIdTryOrderMapInfos.
                    toArray(
                    ret);

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;

            }

        }

        /**
         * 注文識別コードトライオーダマップ情報をロードする
         *
         * @@return 注文識別コードトライオーダマップ情報
         * @@param size int
         */
        private WEB3ReqNumTryOrderMapInfo[] loadReqNumTryOrderMapInfos()
        {
            QueryProcessor qp = null;
            try
            {
                qp = Processors.getDefaultProcessor();
                List list = qp.doFindAllQuery(AffinityKeyBasedMapRow.TYPE, null,
                                              "key", null, null);
                ArrayList lstReqNumTryOrderMapInfos = new ArrayList();
                for (int i = 0; i < list.size(); i++)
                {
                    AffinityKeyBasedMapRow row = (AffinityKeyBasedMapRow) list.get(i);
                    WEB3ReqNumTryOrderMapInfo mapInfo = new WEB3ReqNumTryOrderMapInfo();
                    String key = row.getKey();
                    mapInfo.setHead2OfOrderRequestNumber(key);
                    int intTryOrder[] = new int[1];

                    long lngOrder = Long.parseLong(row.getAppId());
                    if (lngOrder > 0)
                    {
                        intTryOrder[0] = (int) lngOrder - 1;
                    }
                    else
                    {
                        intTryOrder[0] = (int) lngOrder;
                    }
                    mapInfo.setAppServerTryOrder(intTryOrder);
                    lstReqNumTryOrderMapInfos.add(mapInfo);

                }
                WEB3ReqNumTryOrderMapInfo[] ret = new WEB3ReqNumTryOrderMapInfo[
                    lstReqNumTryOrderMapInfos.size()];
                if (log.ison())
                {
                    String l_strDubug = "loadReqNumTryOrderMapInfos:\n";
                    for (int i = 0; i < lstReqNumTryOrderMapInfos.size(); i++)
                    {
                        l_strDubug += WEB3SystemObjectPrint.
                            printWEB3ReqNumTryOrderMapInfo( (WEB3ReqNumTryOrderMapInfo)
                            lstReqNumTryOrderMapInfos.get(i));
                    }
                    log.debug(l_strDubug);
                }
                return (WEB3ReqNumTryOrderMapInfo[]) lstReqNumTryOrderMapInfos.toArray(
                    ret);

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;
            }
        }

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        Plugin.plug(webbroker3.system.tune.affinity.WEB3ServerConnectorPlugin.class);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        Web3SystemMasterDatabaseExtensions.plug();
        ClientAccessorRegistry.registerClientAccessor(
            "web3-static-cluster", new Web3StaticClusterServerAccessorFactory());

    }

}
@
