head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageContextFactory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQMessageContextFactoryクラス(WEB3MQMessageContextFactory.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/08 山田　@卓司(FLJ) 新規作成
                  : 2005/03/07 山田　@卓司(FLJ) コンテキスト情報が設定されていない場合の処理を変更
                  : 2005/03/29 山田　@卓司(FLJ) DBアフィニティ情報の取得方法@を変更
                  : 2005/04/22 山田　@卓司(FLJ) DB障害時に正しいコンテキスト情報が設定されていない問題を修正
                  : 2005/05/10 山田　@卓司(FLJ) コンテキスト情報が設定されていない場合もOracleSIDを設定するように変更
                  : 2005/05/11 山田　@卓司(FLJ) コンテキスト情報が設定されていない場合も会社コードを設定するように変更
                  : 2005/06/03 山田　@卓司(FLJ) MultiPoolJndiNameLookupServiceがnullを返した場合の処理を修正
 */
package webbroker3.mqgateway.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.util.rac.ContextDrivenMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.MultiPoolDataSourceSettings;
import com.fitechlabs.xtrade.plugin.util.rac.MultiPoolJndiNameLookupService;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsDao;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsRow;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.system.data.AffinityRangeBasedMapRow;
import webbroker3.system.tune.affinity.ServerTypeEnum;

/**
 * WEB3MQMessageContextを生成するクラス。 
 * 
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3MQMessageContextFactory
{
    
    /**
     * MQメッセージに設定するコンテキスト情報を作成する。
     * 
     * @@param l_messageSpec MQメッセージ送信内容
     * @@return コンテキスト情報
     * @@throws WEB3SystemLayerException
     */
    WEB3MQMessageContext create(WEB3MQMessageSpec l_messageSpec) throws WEB3SystemLayerException
    {
        
        // 営業日を取得
        Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        // 設定されているJNDI名判別用のコンテキスト名と値を取得
        String l_strContextName = getContextName();
        String l_strContextValue = getContextValue();
        
        if (l_strContextName == null || l_strContextValue == null)
        {
            // コンテキスト情報が設定されていない場合はSIDと営業日のみ設定する
            return new WEB3MQMessageContext(
                l_messageSpec.getInstitutionCode(), 
                getDefaultOracleSid(), 
                null, 
                null, 
                l_datBizDate);
        }
        
        // シングルコンテキストの場合は、ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXTを使用
        MultiPoolDataSourceSettings l_dataSourceSettings = 
            (MultiPoolDataSourceSettings) Services.getService(MultiPoolDataSourceSettings.class);
        if (l_dataSourceSettings.isSingleContext())
        {
            l_strContextName = ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT;
        }
        
        // JNDI名、Oracle SID、サーバIDを取得
        String l_strJndiName = getJndiName(l_strContextName, l_strContextValue);
        String l_strOracleSid = getOracleSid(l_strJndiName);
        String l_strServerId = getServerId(l_strJndiName);
        
        // アカウントIDの範囲を取得
        AccountIdRange l_accountIdRange = 
            getAccountIdRange(l_strContextName, l_strContextValue, l_strServerId);
        long l_lngAccountIdStart = l_accountIdRange.accountIdStart;
        long l_lngAccountIdEnd = l_accountIdRange.accountIdEnd;
        
        // 会社コードを取得
        String l_strInstitutionCode = l_messageSpec.getInstitutionCode();

        // 生成したMQコンテキスト情報を返す
        return new WEB3MQMessageContext(
                l_strInstitutionCode, 
                l_strOracleSid,
                new Long(l_lngAccountIdStart), 
                new Long(l_lngAccountIdEnd), 
                l_datBizDate);
    }

    /**
     * 使用するDB接続プールのJNDI名を使用する。
     * 
     * @@param l_strContextName コンテキスト名
     * @@param l_strContextValue 値
     * 
     * @@return JNDI名
     */
    private String getJndiName(String l_strContextName, String l_strContextValue)
    {
        
        String l_strJndiName = null;
        MultiPoolJndiNameLookupService l_lookupService = (MultiPoolJndiNameLookupService) Services
                .getService(MultiPoolJndiNameLookupService.class);
        l_strJndiName =
            l_lookupService.getJndiName(l_strContextName, l_strContextValue);
        
        if (l_strJndiName == null)
        {
            l_strJndiName = getRoundRobinBasedJndiName();
        }
        
        return l_strJndiName;
    }

    /**
     * RoundRobinBasedMultiPoolDataSourceより使用するデータソースのJNDI名を取得する。
     * 
     * @@return JNDI名
     */
    private String getRoundRobinBasedJndiName()
    {
        RoundRobinBasedMultiPoolDataSource l_ds =
            (RoundRobinBasedMultiPoolDataSource) Services.getService(
                RoundRobinBasedMultiPoolDataSource.class);
        return l_ds.getJndiName();
    }

    /**
     * 使用するDB接続プールにマッピングされているOracleSIDを取得する。
     * 
     * @@param l_strJndiName
     *            DB接続プールのJNDI名
     * @@return Oracle SID
     * @@throws WEB3SystemLayerException
     */
    private String getOracleSid(String l_strJndiName) throws WEB3SystemLayerException
    {
        
        try
        {
            MpdsSettingsRow l_mpdsSetting = MpdsSettingsDao.findRowByPk(
                    "db.cluster.sid", l_strJndiName);
            return l_mpdsSetting.getSettingValue();
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getSid(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getSid(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * コンテキスト情報が設定されていない場合に使用されるOracleSIDを
     * ラウンドロビン・ロジックで取得する。
     * 
     * @@return OracleSID
     * @@throws WEB3SystemLayerException
     */
    private String getDefaultOracleSid() throws WEB3SystemLayerException
    {
        String l_strJndiName = getRoundRobinBasedJndiName();
        return getOracleSid(l_strJndiName);
    }
    
    /**
     * 使用するDB接続プールのサーバIDを取得する。
     * 
     * @@param l_strJndiName JNDI名
     * @@return サーバID
     * @@throws WEB3SystemLayerException
     */
    private String getServerId(String l_strJndiName) throws WEB3SystemLayerException
    {
        try
        {
            String l_strWhere = "setting_categ=? and setting_value=?";
            Object[] l_objBindVars = { "db.cluster.jndi_names", l_strJndiName };
            List l_rows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    MpdsSettingsRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_rows != null && l_rows.size() > 0)
            {
                MpdsSettingsRow row = (MpdsSettingsRow) l_rows.get(0);
                return row.getSettingName();
            }
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getServerId(String)"); 
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getServerId(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getServerId(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * RANGE_BASED_MPDS_MAPテーブルから以下の条件で検索したレコードのListを取得する。<BR>
     * <BR>
     * ＜検索条件＞<BR>
     * CTX_NAME = パラメータ.コンテキスト名<BR>
     * JNDI_NAME = パラメータ.JNDI名<BR>
     * ＜ソート条件＞<BR>
     * RANGE_ORDER_NO（昇順）<BR>
     * KEY_START（昇順） <BR>
     * 
     * @@param l_strContextName コンテキスト名
     * @@param l_strServerId サーバID
     * @@return 取得したレコードのList
     * @@throws WEB3SystemLayerException
     */
    private List getAffinityRangeBasedMaps(String l_strContextName, String l_strServerId) throws WEB3SystemLayerException
    {
        final String l_strWhere = "ctx_name=? and server_id=? and server_type=?";
        final String l_strOrderBy = "range_order_no, key_start";
        Object[] l_objBindVars = { 
                l_strContextName, 
                l_strServerId, 
                new Integer(ServerTypeEnum.IntValues.DB) };
        try
        {
            List l_rangeBasedMpdsMaps =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AffinityRangeBasedMapRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_objBindVars);
            return l_rangeBasedMpdsMaps;
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getAffinityRangeBasedMaps(String, String)", 
                    l_dfe.getMessage(),
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getAffinityRangeBasedMaps(String, String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * アカウントIDの範囲を取得する。
     * 
     * @@param l_strContextName コンテキスト名
     * @@param l_strContextValue 値
     * @@param l_strServerId サーバID
     * @@return アカウントIDの範囲
     * @@throws WEB3SystemLayerException
     */
    private AccountIdRange getAccountIdRange(String l_strContextName,
            String l_strContextValue, String l_strServerId)
            throws WEB3SystemLayerException
    {

        List l_rangeBasedMaps = getAffinityRangeBasedMaps(l_strContextName, l_strServerId);
        if (l_rangeBasedMaps == null || l_rangeBasedMaps.size() <= 0)
        {
            // RANGE_BASED_MPDS_MAPレコードが取得できない場合は例外をスロー
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getAccountIdRange(String, String)");
        }
        
        AffinityRangeBasedMapRow[] l_rows = 
            new AffinityRangeBasedMapRow[l_rangeBasedMaps.size()];
        l_rows = (AffinityRangeBasedMapRow[]) l_rangeBasedMaps.toArray(l_rows);
        
        // RANGE_ORDER_NOの最小値を取得
        long l_lngMinRagneOrderNo = l_rows[0].getRangeOrderNo();
        
        // 対象となるアカウントの現在のRANGE_ORDER_NOを取得
        int l_intIndex = 0;
        long l_lngAccountId = Long.parseLong(l_strContextValue);
        long l_lngRangeOrderNo = 0L;
        for (int i = 0; i < l_rows.length; i++)
        {
            if (l_rows[i].getKeyStart() <= l_lngAccountId
                && l_lngAccountId <= l_rows[i].getKeyEnd())
            {
                l_intIndex = i;
                l_lngRangeOrderNo = l_rows[i].getRangeOrderNo();
                break;
            }
        }
        
        AccountIdRange l_range = new AccountIdRange();
        if (l_lngMinRagneOrderNo == l_lngRangeOrderNo)
        {
            // 現在のRANGE_ORDER_NOがRANGE_ORDER_NOの最小値の場合
            l_range.accountIdStart = l_rows[0].getKeyStart();
            l_range.accountIdEnd = l_rows[0].getKeyEnd();
            for (int i = 0; i < l_rows.length; i++)
            {
                // この場合は、RANGE_ORDER_NOが同じ最後尾のレコードのKEY_ENDを設定
                if (l_lngRangeOrderNo == l_rows[i].getRangeOrderNo())
                {
                    l_range.accountIdEnd = l_rows[i].getKeyEnd();
                } else
                {
                    break;
                }
            }
            
        } else {
            // それ以外の場合は、対象となるアカウントを含むレコードから値を設定
            l_range.accountIdStart = l_rows[l_intIndex].getKeyStart();
            l_range.accountIdEnd = l_rows[l_intIndex].getKeyEnd();
        }
        
        return l_range;
        
    }
    
    /**
     * コンテキスト名を取得する。
     * 
     * @@return コンテキスト名
     */
    private String getContextName()
    {
        Object l_objContextName = ThreadLocalSystemAttributesRegistry
                .getAttribute(ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_CTX_NAME);
        if (l_objContextName != null)
        {
            return String.valueOf(l_objContextName);
        } else {
            return null;
        }
    }

    /**
     * アカウントIDまたはログインIDを取得する。
     * 
     * @@return アカウントIDまたはログインID
     */
    private String getContextValue()
    {
        Object l_objContextValue = ThreadLocalSystemAttributesRegistry
                .getAttribute(ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_KEY_VALUE);
        if (l_objContextValue != null)
        {
            return String.valueOf(l_objContextValue);
        } else {
            return null;
        }
    }
    
    /**
     * アカウントIDの範囲を表現するクラス
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class AccountIdRange
    {
        
        /**
         * アカウントID（自）
         */
        long accountIdStart;

        /** 
         * アカウントID（至）
         */
        long accountIdEnd;
        
    }

}@
