head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IP別ログイン回数情報(WEB3AdminTraderAdminLoginCountReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (IP別ログイン回数情報)<BR>
 * IP別ログイン回数情報クラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountReferenceUnit extends Message
{

    /**
     * (ランク)<BR>
     * ログイン処理回数の順位。<BR>
     */
    public String rank;

    /**
     * (IPアドレス)<BR>
     * IPアドレス。<BR>
     */
    public String ipAddress;

    /**
     * (ログイン処理回数)<BR>
     * IPアドレス毎のログイン処理回数を保持。<BR>
     */
    public String loginCount;

    /**
     * @@roseuid 48D75CD60359
     */
    public WEB3AdminTraderAdminLoginCountReferenceUnit()
    {

    }
}
@
