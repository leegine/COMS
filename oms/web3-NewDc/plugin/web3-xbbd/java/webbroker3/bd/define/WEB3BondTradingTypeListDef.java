head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondTradingTypeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文種別一覧 定数定義インタフェイス(WEB3BondTradingTypeListDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 趙林鵬(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 注文種別一覧 定数定義インタフェイス
 * 
 * @@author 趙林鵬(中訊)
 * @@version 1.0 
 */

public interface WEB3BondTradingTypeListDef
{
    /**
     * 401：買付
     */
    public final static String BUY = "401";

    /**
     * 402：売却
     */
    public final static String SELL = "402";
    
    /**
     * 404：応募
     */
    public final static String RECRUIT = "404";

}
@
