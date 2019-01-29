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
filename	WEB3FeqOrderAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文受付区分定数定義インタフェイス(WEB3FeqOrderAcceptTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 注文受付区分定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptTypeDef
{
    /**
     * 0:受付未済
     */
    public final static String EXEC_TYPE_NAN = "0";
    
    /**
     * 1:受付済
     */
    public final static String EXEC_TYPE_NOT_NAN = "1";
    
    /**
     * 2:受付エラー
     */
    public final static String EXEC_TYPE_ERROR = "2";

    /**
     * 9：訂正・取消されたデータ
     */
    public final static String CNANGE_CANCELED_DATA = "9";
}
@
