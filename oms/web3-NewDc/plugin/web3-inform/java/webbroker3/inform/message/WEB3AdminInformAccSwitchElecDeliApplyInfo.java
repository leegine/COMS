head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwitchElecDeliApplyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込情報(WEB3AdminInformAccSwitchElecDeliApplyInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 張騰宇 (中訊) 新規作成 仕様変更モデル097
Revision History : 2007/08/30 孫洪江 (中訊) 仕様変更モデル107
*/
package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (口座切替・電子交付申込情報)<BR>
 * 口座切替・電子交付申込情報<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminInformAccSwitchElecDeliApplyInfo extends Message
{
    /**
     * (モバイル専用口座開設区分)<BR>
     * モバイル専用口座開設区分<BR>
     * <BR>
     * 0： 未開設<BR>
     * 1： 開設<BR>
     */
    public String mobileAccoutDiv;

    /**
     * (取引報告書交付区分)<BR>
     * 取引報告書交付区分<BR>
     * <BR>
     * 0： 郵便配布<BR>
     * 1： 電子配布<BR>
     */
    public String tradingReportDiv;

    /**
     * (取引残高報告書交付区分)<BR>
     * 取引残高報告書交付区分<BR>
     * <BR>
     * 0： 郵便配布<BR>
     * 1： 郵便配布（受渡都度作成）<BR>
     * 9： 電子配布<BR>
     */
    public String positionReportDiv;

    /**
     * (取引残高報告書作成周期区分)<BR>
     * 取引残高報告書作成周期区分<BR>
     * <BR>
     * 1： 毎月<BR>
     * 3： 3ヶ月<BR>
     */
    public String positionReportCycleDiv;

    /**
     * (取引残高報告書預り証作成フラグ)<BR>
     * 取引残高報告書預り証作成フラグ<BR>
     * <BR>
     * 1： TRUE/作成<BR>
     * 0： FALSE/作成しない<BR>
     */
    public String certificateDepositDiv;

    /**
     * (取引残高報告書計算書作成フラグ)<BR>
     * 取引残高報告書計算書作成フラグ<BR>
     * <BR>
     * 1： TRUE/作成<BR>
     * 0： FALSE/作成しない<BR>
     */
    public String accountStatementDiv;

    /**
     * (税区分)<BR>
     * 税区分<BR>
     * <BR>
     * 0： その他<BR>
     * 1： 一般<BR>
     * 2： 特定<BR>
     * 3： 特定口座かつ源泉徴収 <BR>
     */
    public String taxType;

    /**
     * (税区分（次年）)<BR>
     * 税区分（次年）<BR>
     * <BR>
     * 0： その他<BR>
     * 1： 一般<BR>
     * 2： 特定<BR>
     * 3： 特定口座かつ源泉徴収<BR>
     */
    public String taxTypeNext;

    /**
     * (信用取引税区分)<BR>
     * 信用取引税区分<BR>
     * <BR>
     * 0： その他<BR>
     * 1： 一般<BR>
     * 2： 特定<BR>
     * 3： 特定口座かつ源泉徴収<BR>
     */
    public String marginTaxType;

    /**
     * (信用取引税区分（次年）)<BR>
     * 信用取引税区分（次年）<BR>
     * <BR>
     * 0： その他<BR>
     * 1： 一般<BR>
     * 2： 特定<BR>
     * 3： 特定口座かつ源泉徴収<BR>
     */
    public String marginTaxTypeNext;

    /**
     * (特定管理口座開設区分)<BR>
     * 特定管理口座開設区分<BR>
     * <BR>
     * 0： 未開設<BR>
     * 1： 開設<BR>
     */
    public String capitalGainTaxAccOpenDiv;

    /**
     * (口座切替・電子交付申込情報)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo()
    {

    }
}
@
