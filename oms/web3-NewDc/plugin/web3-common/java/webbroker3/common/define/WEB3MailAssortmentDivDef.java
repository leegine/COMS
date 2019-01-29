head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MailAssortmentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール種別区分定数定義インタフェイス(WEB3MailAssortmentDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * メール種別区分定数定義インタフェイス<BR>
 * (メール種別テーブルのメール種別区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3MailAssortmentDivDef
{
    /**
     * 1:株式約定メール
     */
    public final static String EQUITY_ORDER_MAIL = "1";

    /**
     * 2:株式未約定メール
     */
    public final static String EQUITY_NOT_ORDER_MAIL = "2";

    /**
     * 3:先OP約定メール 
     */
    public final static String FUTURES_OPTION_ORDER_MAIL = "3";

    /**
     * 4:先OP未約定メール
     */
    public final static String FUTURES_OPTION_NOT_ORDER_MAIL = "4";

    /**
     * 5:重要メール
     */
    public final static String IMPORTANT_MAIL = "5";

    /**
     * 6:案内メール
     */
    public final static String GUIDE_MAIL = "6";

    /**
     * 7:電子交付メール
     */
    public final static String ELECTRONIC_DELIVER_MAIL = "7";
}
@
