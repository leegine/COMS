head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOtherOrderProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioOtherOrderProductDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/11 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * その他用注文件数テーブルの商品区分　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3AioOtherOrderProductDivDef {
    
    /**
     * 1：オンライン入金
     */
    public static final String ONLINE_CASHIN = "1";
    
    /**
     * 2：為替保証金
     */
    public static final String FX = "2";
    
    /**
     * 3：外国株式(外部連携)
     */
    public static final String FEQ_CON = "3";
    
    /**
     * 4：信用保証金HULFT伝送
     */
    public static final String MARGIN_GUARANTEE_HULFT = "4";
}
@
