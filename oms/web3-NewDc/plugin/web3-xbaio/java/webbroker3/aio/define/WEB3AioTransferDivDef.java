head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransferDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 出金申込問合せ一覧リクエストの振込先区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTransferDivDef {
    
    /**
     * 0： ”全て”  
     */
    public static final String ALL = "0";
    
    /**
     * 1： ”郵貯”  
     */
    public static final String POSTAL_SAVINGS = "1";
    
    /**
     * 2： ”その他”（郵貯以外）  
     */
    public static final String OTHERS = "2";
}
@
