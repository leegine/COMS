head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwitchElecDeliAppDtInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込日付情報(WEB3AdminInformAccSwitchElecDeliAppDtInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 張騰宇 (中訊) 新規作成 仕様変更モデル097
*/
package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (口座切替・電子交付申込日付情報)<BR>
 * 口座切替・電子交付申込日付情報<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminInformAccSwitchElecDeliAppDtInfo extends Message
{
    /**
     * (申込日時)<BR>
     * 申込日時<BR>
     */
    public Date applyDate;

    /**
     * (開始予定日)<BR>
     * 開始予定日<BR>
     */
    public Date startScheduleDate;

    /**
     * (税区分開設日)<BR>
     * 税区分開設日<BR>
     */
    public String taxTypeOpenDate;

    /**
     * (信用取引税区分開設日)<BR>
     * 信用取引税区分開設日<BR>
     */
    public String marginTaxTypeOpenDate;

    /**
     * (口座切替・電子交付申込日付情報)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo()
    {

    }
}
@
