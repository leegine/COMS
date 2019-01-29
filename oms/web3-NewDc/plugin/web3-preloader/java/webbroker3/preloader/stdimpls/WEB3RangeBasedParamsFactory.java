head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3RangeBasedParamsFactory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RangeBasedParamsFactory2クラス(WEB3RangeBasedParamsFactory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/04 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.stdimpls;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.preloader.WEB3DefaultPreLoaderParams;
import webbroker3.system.data.AffinityRangeBasedMapRow;
import webbroker3.system.tune.affinity.ServerTypeEnum;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3RangeBasedParamsFactory
{
    
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3RangeBasedParamsFactory.class);
    
    public WEB3DefaultPreLoaderParams createAccountIdRangeParams(String l_strHostName)
    {
        return createAccountIdRangeParams(l_strHostName, null);
    }
    
    public WEB3DefaultPreLoaderParams createAccountIdRangeParams(String l_strHostName, RowType l_rowType)
    {
        Range range = getRange(l_strHostName);
        return createRangeBasedParams(l_rowType, "account_id", range);
    }
    
    public WEB3DefaultPreLoaderParams createLoginIdRangeParams(String l_strHostName)
    {
        return createLoginIdRangeParams(l_strHostName, null);
    }
    
    public WEB3DefaultPreLoaderParams createLoginIdRangeParams(String l_strHostName, RowType l_rowType)
    {
        Range range = getRange(l_strHostName);
        return createRangeBasedParams(l_rowType, "login_id", range);
    }
    
    private WEB3DefaultPreLoaderParams createRangeBasedParams(RowType l_rowType, String l_strKeyName, Range l_range)
    {
        String l_strWhere = null;
        Object[] l_objBindVars = null;
        if (l_range != null)
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("?<=").append(l_strKeyName);
            l_sbWhere.append(" and ").append(l_strKeyName).append("<=?");
            l_strWhere = l_sbWhere.toString();
            l_objBindVars = new Object[] { l_range.start, l_range.end };
        }
        return new WEB3DefaultPreLoaderParams(
                l_rowType,
                l_strWhere, 
                null, 
                null, 
                l_objBindVars);
    }
    
    /**
     * 指定したサーバIDに割り当てられているアカウントIDの範囲を取得する。
     * 対象データが存在しない場合、<code>null</code>を返す。
     * 
     * @@param l_strHostName ホスト名
     * @@return アカウントIDの範囲
     */
    private Range getRange(String l_strHostName)
    {

        String l_strServerId = getServerId(l_strHostName);
        if (l_strServerId == null)
        {
            return null;
        }
        
        List l_lisAffinityRangeBasedMaps = 
            getAffinityRangeBasedMaps(l_strServerId);
        if (l_lisAffinityRangeBasedMaps == null 
                || l_lisAffinityRangeBasedMaps.size() <= 0)
        {
            return null;
        }
      
        int l_intSize = l_lisAffinityRangeBasedMaps.size();
        AffinityRangeBasedMapRow l_firstRow
            = (AffinityRangeBasedMapRow) l_lisAffinityRangeBasedMaps.get(0);
        int l_intPrimaryRangeOrderNo = l_firstRow.getRangeOrderNo();
        long l_lngKeyStrat = l_firstRow.getKeyStart();
        long l_lngKeyEnd = l_firstRow.getKeyEnd();
        for (int i = 1; i < l_intSize; i++)
        {
            AffinityRangeBasedMapRow l_row =
                (AffinityRangeBasedMapRow) l_lisAffinityRangeBasedMaps.get(i);
            if (l_intPrimaryRangeOrderNo == l_row.getRangeOrderNo())
            {
                l_lngKeyEnd = l_row.getKeyEnd();
            } else {
                break;
            }
        }

        Range l_range = new Range();
        l_range.start = new Long(l_lngKeyStrat);
        l_range.end = new Long(l_lngKeyEnd);
        return l_range;
    }
    
    /**
     * SERVER_CONFIGに設定されているAPサーバ・アフィニティ設定のサーバIDを取得する。
     * 対象データが存在しない場合は<code>null</code>を返す。
     * 
     * @@param l_strHostName ホスト名
     * @@return サーバID
     */
    private String getServerId(String l_strHostName)
    {
        String l_strServerId = null;
        try
        {
            Properties serverIds = 
                ServerConfig.getConfigCategory("ap.cluster.host_names");
            if (serverIds != null)
            {
                for (Iterator it = serverIds.entrySet().iterator(); it.hasNext();)
                {
                    Map.Entry l_entry = (Map.Entry) it.next();
                    if (l_strHostName.equals(l_entry.getValue()))
                    {
                        l_strServerId = (String) l_entry.getKey();
                        break;
                    }
                }
            }
        } catch (DataNetworkException l_dne)
        {
            LOG.error(l_dne.getMessage(), l_dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass() + ".getServerId(String)",
                    l_dne.getMessage(),
                    l_dne);
        }
        return l_strServerId;
    }
    
    /**
     * AFFINITY_RANGE_BASED_MAPテーブルより、指定したサーバIDのAPサーバ・アフィニティ設定
     * レコードを取得する。
     * 
     * @@param l_strServerId サーバID
     * @@return AFFINITY_RANGE_BASED_MAPレコード
     */
    private List getAffinityRangeBasedMaps(String l_strServerId)
    {
        String l_strWhere = "server_type=? and server_id=?";
        String l_strOrderBy = "range_order_no, key_start";
        Object[] l_objBindVars = { new Integer(ServerTypeEnum.AP.intValue()), l_strServerId };
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(
                    AffinityRangeBasedMapRow.TYPE, l_strWhere, l_strOrderBy,
                    null, l_objBindVars);
        } catch (DataException l_de)
        {
            LOG.error(l_de.getMessage(), l_de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass() + ".getServerId(String)",
                    l_de.getMessage(),
                    l_de);
        }
    }
    
    private class Range
    {
        Long start;
        Long end;
    }

}
@
