head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToManualOrderErrorCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手動発注エラーコード定数定義インタフェイス(WEB3ToManualOrderErrorCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 譚漢江 (中訊)　@新規作成
                 : 2006/08/24 唐性峰 (中訊)  對應 モデルNo.158
*/
package webbroker3.triggerorder.define;

/**
 * 手動発注エラーコード定数定義インタフェイス<BR>
 * 
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToManualOrderErrorCodeDef
{
    /**
     * 00：　@正常
     */
    public static final String NORMAL = "00";

    /**
     * 01：　@取消済エラー
     */
    public static final String CANCELED = "01";

    /**
     * 02：　@発注済エラー
     */
    public static final String ORDERED = "02";
    
    /**
     * 03：　@発注失敗
     */
    public static final String ORDER_FAILURE = "03";
    
    /**
     * 04：　@約定済エラー 
     */
    public static final String EXECUTED = "04";
    
    /**
     * 05：　@失効済エラー
     */
    public static final String EXPIREED = "05";

    /**
     * 90：　@該当注文なし
     */
    public static final String NOT_AVAILABLE = "90";

    /**
     * 99：　@その他エラー
     */
    public static final String OTHER = "99";

}
@
