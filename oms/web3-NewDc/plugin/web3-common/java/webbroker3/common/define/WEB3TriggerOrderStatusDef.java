head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注状況区分 定数定義インタフェイス(WEB3TriggerOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03 森川 昌平 (SRA) 新規作成
Revesion History : 2006/07/18 栄イ (中訊)インターフェイス申請依頼084を対応
Revesion History : 2006/07/20 栄イ (中訊)先物OP申請を対応
*/

package webbroker3.common.define;

/**
 * 発注状況区分　@定数定義インタフェイス<BR>
 * 
 * @@author  森川 昌平（SRA）
 * @@version 1.0
 */
public class WEB3TriggerOrderStatusDef
{
    /**
     * 1：　@待機@中
     */
    public final static String ORDER_WAITING = "1";
     
    /**
     * ２：　@発注中
     */
    public final static String ORDERING = "2";
    
    /**
     * 3：　@発注完了
     */
    public final static String ORDER_COMPLETE = "3";
    
    /**
     * 8：　@発注審査エラー
     */
    public final static String ORDER_VALIDATE_ERROR = "8";
    
    /**
     * 9：　@発注遅延エラー
     */
    public final static String ORDER_DELAY_ERROR = "9";
    
    /**
     * 13： ストップ注文失効
     */
    public final static String STOP_ORDER_INVALIDATION = "13";
    
    /**
     * 99：　@その他
     */
    public final static String OTHER = "99";
}
@
