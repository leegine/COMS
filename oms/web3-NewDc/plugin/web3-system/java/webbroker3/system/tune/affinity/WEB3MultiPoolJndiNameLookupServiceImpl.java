head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3MultiPoolJndiNameLookupServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : カスタマイズしたRACデータソース特定クラス(WEB3MultiPoolJndiNameLookupServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/24 劉(FLJ) 新規作成
 */

package webbroker3.system.tune.affinity;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import com.fitechlabs.xtrade.plugin.util.rac.data.*;
import com.fitechlabs.xtrade.plugin.util.rac.stdimpl.*;
import webbroker3.system.data.*;
import webbroker3.util.*;

/**
 *  カスタマイズしたRACデータソース特定するクラス
 */
public class WEB3MultiPoolJndiNameLookupServiceImpl
    implements MultiPoolJndiNameLookupService
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility m_log =
        WEB3LogUtility.getInstance(WEB3MultiPoolJndiNameLookupServiceImpl.class);

    private static String SETTING_CATEG_DB = "db.cluster.jndi_names";

    /**
     * 定のコンテキスト名およびキーを基に利用するデータソースのJNDI名を取得します。
     */
    public String getJndiName(String l_strContextName, String l_strContextValue)
    {
        boolean DBG = m_log.ison();

        String l_strWhere = "ctx_name = ? and server_type = ?";
        Object[] l_objWhere =
            {
            l_strContextName, new Long(ServerTypeEnum.DB.intValue())};

        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List list = l_qp.doFindAllQuery(AffinityRangeBasedMapRow.TYPE,
                                            l_strWhere,
                                            "range_order_no, key_start, key_end", null,
                                            l_objWhere);
            if (list.size() <= 0)
            {
                return null;
            }

            List l_lstFilter = filterListUsingRange(list,
                Long.parseLong(l_strContextValue));

            String l_strJndiNamePrimay = null;
            for (int i = 0; i < l_lstFilter.size(); i++)
            {
                AffinityRangeBasedMapRow row = (AffinityRangeBasedMapRow)
                    l_lstFilter.
                    get(i);
                String jndiName = getJndiFromMpdsSettings(
                    row.getServerId());
                AffinityRangeBasedMapRow row0 = (AffinityRangeBasedMapRow) l_lstFilter.
                    get(
                    0);
                if (l_strJndiNamePrimay == null)
                {
                    l_strJndiNamePrimay = getJndiFromMpdsSettings( (
                        row0.getServerId()));
                }

                boolean isValid = RacDataSourcesManager.instance().isValid(
                    jndiName);
                if (isValid)
                {

                    if (i != 0)
                    {
                        m_log.warn("Primary data source " + l_strJndiNamePrimay +
                                   " for contextName,key:" + l_strContextName +
                                   "," +
                                   l_strContextValue + ", is not available. Will use " +
                                   (i + 1) +
                                   "th data source in the fall back lineup of data sources.");
                        return jndiName;
                    }
                    else
                    {
                        if (DBG)
                        {
                            m_log.debug("Primary data source " + l_strJndiNamePrimay +
                                        " for contextName,key:" +
                                        l_strContextName + "," +
                                        l_strContextValue + ", is available to be use.");
                        }
                        return l_strJndiNamePrimay;
                    }

                }

            }
            if (DBG)
            {
                m_log.debug(
                    "No valid mapping found in range_based_affinity_map for context,key=" +
                    l_strContextName + "," + l_strContextValue);
            }
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            m_log.error("Exception while accessing range_based_affinity_map.", e);
            throw new RuntimeException("Exception while accessing range_based_mpds_map.",
                                       e);
        }
    }

    private List filterListUsingRange(List l_lstMap, long l_lngContextValue)
    {
        boolean DBG = m_log.ison();
        ArrayList arraylist = new ArrayList();
        if (DBG)
        {
            m_log.debug("filterListUsingRange:all-rows\n");
            for (int i = 0; i < l_lstMap.size(); i++)
            {
                AffinityRangeBasedMapRow row = (AffinityRangeBasedMapRow) l_lstMap.get(i);
                m_log.debug(row.toString());
            }
        }
        m_log.debug("filterListUsingRange:filter-rows\n");
        for (int i = 0; i < l_lstMap.size(); i++)
        {
            AffinityRangeBasedMapRow row = (AffinityRangeBasedMapRow) l_lstMap.get(i);
            if (l_lngContextValue >= row.getKeyStart() &&
                l_lngContextValue <= row.getKeyEnd())
            {
                if (DBG)
                {
                    m_log.debug("ContextValue:" + l_lngContextValue);
                    m_log.debug("add row:" + row.toString());
                }
                arraylist.add(row);
            }
        }
        return arraylist;
    }

    private String getJndiFromMpdsSettings(String l_strName) throws Exception
    {

        String l_strWhere = "setting_categ = ? and setting_name = ?";
        Object[] l_objWhere =
            {
            SETTING_CATEG_DB, l_strName};
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        List list = l_qp.doFindAllQuery(MpdsSettingsRow.TYPE, l_strWhere,
                                        null, null, l_objWhere);
        if (list.size() == 0)
        {
            throw new NotFoundException();
        }
        else
        {
            MpdsSettingsRow row = (MpdsSettingsRow) list.get(0);
            return row.getSettingValue();
        }

    }

}
@
