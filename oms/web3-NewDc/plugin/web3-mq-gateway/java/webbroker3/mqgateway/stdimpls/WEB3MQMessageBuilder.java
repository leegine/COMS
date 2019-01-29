head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageBuilder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQMessageBuilderクラス(WEB3MQMessageBuilder.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
                 : 2005/03/07 山田　@卓司 (FLJ) コンテキスト情報の各フィールドにnullが設定されていた場合の対応
                 : 2005/04/18 山田　@卓司 (FLJ) ユーザーデータ部の口座IDをフォーマットしてから追加するように変更
                 : 2005/04/22 山田　@卓司 (FLJ) ユーザーデータ部の口座IDを3桁から15桁に変更
*/
package webbroker3.mqgateway.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3LogUtility;
import jp.co.dir.ms.mq.WFMQMaxasMessage;

/**
 * WFMQMaxasMessageのインスタンスを生成し、パラメータを設定する。
 * 
 * @@author Takuji Yamada (FLJ)
 */
class WEB3MQMessageBuilder
{
    
    
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";
    
    /** ユーザーデータ部に追加する半角スペースの定数定義 */
    private static final String BLANK = " ";
    
    /** ACCOUNT_IDを文字列に変換するときのパターン */
    private static final String PATTERN = "000000000000000";
    
    /** ログ出力 */
    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3MQMessageBuilder.class);
    
    /** デバックフラグ */
    private static final boolean DBG = LOG.ison();
    
    /**
     * WFMQMaxasMessageを生成する。
     * 
     * @@param l_messageSpec メッセージ送信内容
     * @@param l_messageContext コンテキスト情報
     * @@return 生成されたWFMQMaxasMessage
     * @@throws WEB3SystemLayerException
     */
    WFMQMaxasMessage create(WEB3MQMessageSpec l_messageSpec, WEB3MQMessageContext l_messageContext)
        throws WEB3SystemLayerException
    {
        String l_strMessageId = WEB3MQGatewayUtils.getMessageId(l_messageSpec);
        byte[] l_bytUserData = createUserData(l_messageSpec, l_messageContext);

        WFMQMaxasMessage l_message = new WFMQMaxasMessage();
        l_message.setMessageId(l_strMessageId);
        l_message.setCompanyCode(l_messageSpec.getInstitutionCode());
        l_message.setTransactionCode(l_messageSpec.getDataCode());
        l_message.setConvertFlag(true);
        l_message.setCharactorCode(0);
        l_message.setKurikoshiFlag(true);
        l_message.setGroupId("");
        l_message.setFuriKey("");
        l_message.setOriginInfo("");
        l_message.setUserData(l_bytUserData);
        
        if (DBG)
        {
            LOG.debug("created MaxasMessage=" + toString(l_message));
        }

        return l_message;
    }
    
    /**
     * WFMQMaxasMessageのユーザーデータ部分の生成する。
     * 
     * @@param l_messageSpec メッセージ送信内容
     * @@param l_messageContext コンテキスト情報
     * @@return WFMQMaxasMessageのユーザーデータ部分に設定するbyte配列
     */
    private byte[] createUserData(WEB3MQMessageSpec l_messageSpec, WEB3MQMessageContext l_messageContext)
    {
        
        String l_strAccountIdStart = null;
        String l_strAccountIdEnd = null;
        if (!l_messageContext.getAccountIdStartIsNull())
        {
            l_strAccountIdStart = 
                WEB3MQGatewayUtils.getDecimalFormat(PATTERN).format(
                        l_messageContext.getAccountIdStart());
        }
        if (!l_messageContext.getAccountIdEndIsNull())
        {
            l_strAccountIdEnd = 
                WEB3MQGatewayUtils.getDecimalFormat(PATTERN).format(
                        l_messageContext.getAccountIdEnd());
        }
        
        String l_strBizDate = null;
        if (l_messageContext.getBizDate() != null)
        {
            l_strBizDate = GtlUtils.getThreadSafeSimpleDateFormat(YYYYMMDD)
                    .format(l_messageContext.getBizDate());
        }
            
        StringBuffer l_sb = new StringBuffer();
        appendString(l_sb, l_messageContext.getInstitutionCode(), 2);
        appendString(l_sb, l_messageContext.getSid(), 6);
        appendString(l_sb, l_strAccountIdStart, 15);
        appendString(l_sb, l_strAccountIdEnd, 15);
        appendString(l_sb, l_strBizDate, 8);
        String l_strUserData = l_messageSpec.getUserData();
        if (l_strUserData != null)
        {
            l_sb.append(l_strUserData);
        }
        return l_sb.toString().getBytes();
    }
    
    /**
     * 指定されたStringBufferに文字列を追加する。
     * 
     * @@param l_sb 文字列を追加するStringBuffer
     * @@param l_strData 追加する文字列
     * @@param l_intLength 追加する文字列の長さ
     */
    private void appendString(StringBuffer l_sb, String l_strData, int l_intLength)
    {
        int l_intDataLength = l_strData != null ? l_strData.length() : 0;
        if (l_intDataLength >= l_intLength)
        {
            l_sb.append(l_strData.substring(0, l_intLength));
        } else {
            if (l_intDataLength > 0)
            {
                l_sb.append(l_strData);
            }
            for (int i = l_intDataLength; i < l_intLength; i++)
            {
                l_sb.append(BLANK);
            }
        }
    }
    
    /**
     * 指定したWFMQMaxasMessageの文字列表現を返す。（デバック用）
     * 
     * @@param l_maxasMessage WFMQMaxasMessage
     * @@return WFMQMaxasMessageの文字列表現
     */
    private String toString(WFMQMaxasMessage l_maxasMessage)
    {
        String l_strUserData = 
            l_maxasMessage.getUserData() != null 
                ? new String(l_maxasMessage.getUserData()) 
                : null;
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{");
        l_sb.append("messageId=").append(l_maxasMessage.getMessageId());
        l_sb.append(",companyCode=").append(l_maxasMessage.getCompanyCode());
        l_sb.append(",transactionCode=").append(l_maxasMessage.getTransactionCode());
        l_sb.append(",convertFlag=").append(l_maxasMessage.getConvertFlag());
        l_sb.append(",charactorCode=").append(l_maxasMessage.getCharactorCode());
        l_sb.append(",kurikoshiFlag=").append(l_maxasMessage.getKurikoshiFlag());
        l_sb.append(",groupId=").append(l_maxasMessage.getGroupId());
        l_sb.append(",furiKey=").append(l_maxasMessage.getFuriKey());
        l_sb.append(",originInfo=").append(l_maxasMessage.getOriginInfo());
        l_sb.append(",userData=").append(l_strUserData);
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
