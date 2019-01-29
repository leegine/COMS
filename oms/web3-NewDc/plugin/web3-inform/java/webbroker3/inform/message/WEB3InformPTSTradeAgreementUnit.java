head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSTradeAgreementUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS取引同意質問情報(WEB3InformPTSTradeAgreementUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.123
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (PTS取引同意質問情報)<BR>
 * PTS取引同意質問情報
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSTradeAgreementUnit extends Message
{
    /**
     * (質問番号)<BR>
     * 質問番号
     */
    public String questionNumber;

    /**
     * (質問内容)<BR>
     * 質問内容
     */
    public String questionContents;

    /**
     * (質問回答)<BR>
     * 質問回答<BR>
     * <BR>
     * 1：はい<BR>
     * 2：いいえ
     */
    public String questionAnswer;

    /**
     * コンストラクタ。
     * @@roseuid 47B9271A02AF
     */
    public WEB3InformPTSTradeAgreementUnit()
    {

    }
}
@
