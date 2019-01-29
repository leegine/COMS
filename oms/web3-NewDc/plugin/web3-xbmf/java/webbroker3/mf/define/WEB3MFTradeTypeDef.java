head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFTradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売買区分 定数定義インタフェイス(WEB3MFTradeTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 王蘭芬(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 売買区分(投信注文通知キューテーブル)　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3MFTradeTypeDef
{
    /**
     * 1：売付
     */
    public static final String SELL = "1";

    /**
     * 2：買付
     */
    public static final String BUY = "2";
    
    /**
     * 3:募集
     */
    public static final String RECRUIT = "3";
}
@
