head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3OrderReqNumberHead2ManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :識別コード頭２桁特定サービス実装クラス(WEB3OrderReqNumberHead2ManageServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */

package webbroker3.system.tune.affinity;

import java.net.*;
import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.db.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import com.fitechlabs.xtrade.plugin.util.rac.data.*;
import webbroker3.system.data.*;
import webbroker3.util.*;

/**
 * 識別コード頭２桁特定サービス実装クラス
 *
 * @@author 劉
 * @@version 1.0
 */
public class WEB3OrderReqNumberHead2ManageServiceImpl
    implements WEB3OrderReqNumberHead2ManageService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility m_log =
        WEB3LogUtility.getInstance(WEB3OrderReqNumberHead2ManageServiceImpl.class);

    private static String DEFAULT_HEADE_2 = "00";

    private static String SETTING_CATEG_AP = "ap.cluster.host_names";
    private static String SETTING_CATEG_DB = "db.cluster.jndi_names";

    /**
     * 識別コード頭２桁取得する
     */
    public String getOrderReqNumberHead2()
    {

        try
        {
            String l_sAppName = getAppName();
            String l_sDBName = getDBName();
            String l_strAppServerId = getServerIdFromServerConfig(SETTING_CATEG_AP,
                l_sAppName);
            String l_strDBServerId = getServerIdFromMpdsSettings(SETTING_CATEG_DB,
                l_sDBName);
            if (m_log.ison())
            {
                m_log.debug("locate AppServer , DBServer=" + l_strAppServerId + "," +
                            l_strDBServerId);
            }
            AffinityKeyBasedMapRow row = AffinityKeyBasedMapDao.findRowByPk(
                l_strAppServerId, l_strDBServerId);
            String l_sOrderReqNumberHead2 = row.getKey();
            if (m_log.ison())
            {
                m_log.debug("getOrderReqNumberHead2=" + l_sOrderReqNumberHead2);
            }
            if (l_sOrderReqNumberHead2 == null)
            {
                m_log.error("getOrderReqNumberHead2=null for (l_sAppName,l_sDBName)" +
                            l_sAppName + "," + l_sDBName);
            }
            return l_sOrderReqNumberHead2;
        }
        catch (Exception e)
        {
            m_log.error("getOrderReqNumberHead2=null for (" + e.getMessage() + ")", e);
            m_log.error("use default ReqNumberHead2=" + DEFAULT_HEADE_2);
            return DEFAULT_HEADE_2;
        }

    }

    private String getAppName()
    {
        try
        {
            InetAddress l_localHost = InetAddress.getLocalHost();
            return l_localHost.getHostName();
        }
        catch (UnknownHostException ex)
        {
            return "";
        }

    }

    private String getDBName()
    {
        String jndiName = "";
        String ctxName = ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT;
        Long keyValue = (Long) ThreadLocalSystemAttributesRegistry.getAttribute(
            ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_KEY_VALUE);
        if (keyValue != null)
        {
            MultiPoolJndiNameLookupService lookupSvc = (MultiPoolJndiNameLookupService)
                Services.getService(MultiPoolJndiNameLookupService.class);
            jndiName = lookupSvc.getJndiName(ctxName, keyValue.toString());
        }
        else
        {
            RoundRobinBasedMultiPoolDataSource lookupSvc = (
                RoundRobinBasedMultiPoolDataSource)
                Services.getService(RoundRobinBasedMultiPoolDataSource.class);
            jndiName = lookupSvc.getJndiName();
        }
        if (jndiName == null)
        {
            return "";
        }
        else
        {
            return jndiName;
        }
    }

    private String getServerIdFromMpdsSettings(String l_strCateg, String l_strValue) throws
        Exception
    {

        String l_strWhere = "setting_categ = ? and setting_value = ?";
        Object[] l_objWhere =
            {
            l_strCateg, l_strValue};
        QueryProcessor queryprocessor = Processors.getDefaultProcessor();
        List list = queryprocessor.doFindAllQuery(MpdsSettingsRow.TYPE, l_strWhere,
                                                  null, null, l_objWhere);
        if (list.size() == 0)
        {
            throw new NotFoundException();
        }
        else
        {
            MpdsSettingsRow row = (MpdsSettingsRow) list.get(0);
            return row.getSettingName();
        }

    }

    private String getServerIdFromServerConfig(String l_strCateg, String l_strValue) throws
        Exception
    {

        String l_strWhere = "CONFIG_CATEG = ? and CONFIG_VALUE = ?";
        Object[] l_objWhere =
            {
            l_strCateg, l_strValue};
        QueryProcessor queryprocessor = Processors.getDefaultProcessor();
        List list = queryprocessor.doFindAllQuery(ServerConfigRow.TYPE, l_strWhere,
                                                  null, null, l_objWhere);
        if (list.size() == 0)
        {
            throw new NotFoundException();
        }
        else
        {
            ServerConfigRow row = (ServerConfigRow) list.get(0);
            return row.getConfigName();
        }

    }

}
@
