head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQGatewayUtilsクラス(WEB3MQGatewayUtils.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
                   2005/04/18 山田　@卓司 (FLJ) スレッドセーフなDecimalFormatを提供するためのプロパティとメソッドを追加
*/
package webbroker3.mqgateway.stdimpls;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import jp.co.dir.ms.mq.WFMQConnection;
import jp.co.dir.ms.mq.WFMQException;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mqgateway.WEB3MQGatewayPlugin;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsDao;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3-MQ-Gatewayプラグインのユーティリティメソッドを定義したクラス
 * 
 * @@author Takuji Yamad (FLJ)
 */
class WEB3MQGatewayUtils
{

    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3MQGatewayUtils.class);

    private static final String CLASS_NAME =
        WEB3MQGatewayUtils.class.getClass().getName();

    /**
     * スレッド毎のDecimalFormatのインスタンスを保持する。
     */
    private static ThreadLocal decimalFormatHolder = new ThreadLocal()
    {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    private WEB3MQGatewayUtils()
    {
    }

    static String getMessageId(WEB3MQMessageSpec l_messageSpec)
        throws WEB3SystemLayerException
    {

        try
        {
            MqMessageIdMappingsRow l_row =
                MqMessageIdMappingsDao.findRowByPk(
                    l_messageSpec.getInstitutionCode(),
                    l_messageSpec.getDataCode());
            return l_row.getMessageId();
        } catch (DataFindException l_ex)
        {
            LOG.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                CLASS_NAME + ".getMessageId(WEB3MQMessageSpec)",
                l_ex.getMessage(),
                l_ex);
        } catch (DataException l_ex)
        {
            LOG.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                CLASS_NAME + ".getMessageId(WEB3MQMessageSpec)",
                l_ex.getMessage(),
                l_ex);
        }

    }

    static String getQueueId(WEB3MQMessageSpec l_messageSpec)
        throws WEB3SystemLayerException
    {
        String l_prefName =
            new StringBuffer()
                .append(WEB3MQGatewayPlugin.PREF_NAME_QUEUE_ID_PREFIX)
                .append(l_messageSpec.getInstitutionCode())
                .toString();
        try
        {
            SystemPreferencesRow l_row =
                SystemPreferencesDao.findRowByPk(l_prefName);
            return l_row.getValue();
        } catch (DataFindException l_ex)
        {
            LOG.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                CLASS_NAME + ".getQueueId(WEB3MQMessageSpec)",
                l_ex.getMessage(),
                l_ex);
        } catch (DataException l_ex)
        {
            LOG.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                CLASS_NAME + ".getQueueId(WEB3MQMessageSpec)",
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    static String toString(WFMQConnection l_mqCon)
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{");
        l_sb.append("QueueId=").append(l_mqCon.getQueueId());
        try
        {
            l_sb.append(",QueueManagerHost=").append(l_mqCon.getQueueManagerHost());
            l_sb.append(",QueueManagerName=").append(l_mqCon.getQueueManagerName());
            l_sb.append(",QueueManagerPort=").append(l_mqCon.getQueueManagerPort());
            l_sb.append(",QueueName=").append(l_mqCon.getQueueName());
        } catch (WFMQException l_mqe)
        {
        }
        l_sb.append("}");
        return l_sb.toString();
    }
    
    /**
     * スレッドセーフなDecimalFormatのインスタンスを取得する。
     * 
     * @@param l_strPattern パターン
     * @@return DecimalFormatのインスタンス
     */
    static DecimalFormat getDecimalFormat(String l_strPattern)
    {
        Map l_holder = (Map) decimalFormatHolder.get();
        DecimalFormat l_format = (DecimalFormat) l_holder.get(l_strPattern);
        if (l_format == null)
        {
            l_format = new DecimalFormat(l_strPattern);
            l_holder.put(l_strPattern, l_format);
        }
        return l_format;
    }

}
@
