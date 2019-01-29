head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMultiCheckResults.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 複数銘柄目論見書閲覧チェック結果(WEB3GentradeMultiCheckResults.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/17 趙林鵬(中訊) 新規作成 モデルNo.330
*/

package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (複数銘柄目論見書閲覧チェック結果)<BR>
 * 複数銘柄目論見書閲覧チェック結果<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0 
 */
public class WEB3GentradeMultiCheckResults extends Message
{
    /**
     * (チェック結果)<BR>
     * チェック結果<BR>
     */
    public WEB3GentradeMultiDocCheckResultUnit[] checkResult;

    /**
     * 目論見書表示の際に使用するURL<BR>
     */
    public String url;

    /**
     * (ハッシュ値)<BR>
     * ハッシュ値<BR>
     */
    public String hashValue;

    /**
     * (障害中フラグ)<BR>
     * 障害中フラグ<BR>
     * <BR>
     * false：稼働中<BR>
     * true：障害中<BR>
     */
    public boolean batoFailureFlag;

    /**
     * 複数銘柄目論見書閲覧チェック結果<BR>
     * コンストラクタ<BR>
     */
    public WEB3GentradeMultiCheckResults() 
    {

    }
}@
