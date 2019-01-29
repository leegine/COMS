head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiTradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引区分(WEB3SrvRegiTradingTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 馮海濤 新規作成 モデル370
*/

package webbroker3.srvregi.define;

/**
 * 取引区分
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public interface WEB3SrvRegiTradingTypeDef
{
    /**
     * 1：買付処理
     */
    public final static String BUY_PROCESS = "1";

    /**
     * 2：買付注文取消処理
     */
    public final static String BUY_ORDER_CANCEL_PROCESS = "2";

    /**
     * 3：目論見書閲覧チェック
     */
    public final static String PROSPECTUS_CHECK = "3";

}
@
