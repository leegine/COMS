head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOriginalOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 原注文処理区分定数定義インタフェイス(WEB3FeqOriginalOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 原注文処理区分定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqOriginalOrderStatusDef
{
    /**
     * 0：取消中
     */
    public static final String CANCELING = "0";
    
    /**
     * 1：取消済
     */
    public static final String CANCELED = "1";

    /**
     * 2：取消エラー
     */
    public static final String CANCEL_ERROR = "2";
}
@
