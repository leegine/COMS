head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNewOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規注文処理区分定数定義インタフェイス(WEB3FeqNewOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 新規注文処理区分定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqNewOrderStatusDef
{
    /**
     * 0：未発注
     */
    public static final String NOT_ORDER = "0";
    
    /**
     * 1：発注中
     */
    public static final String ORDERING = "1";

    /**
     * 2：発注済
     */
    public static final String ORDERED = "2";

    /**
     * 3：発注エラー
     */
    public static final String ORDER_ERROR = "3";
}
@
