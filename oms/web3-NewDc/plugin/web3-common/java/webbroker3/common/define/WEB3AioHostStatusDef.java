head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioHostStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioHostStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王蘭芬 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * キューテーブル処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioHostStatusDef
{
    /**
     * 0:処理中  
     */
    public static final String DEALING = "0";

    /**
     * 1:処理済み  
     */
    public static final String DEALT = "1";    

    /**
     * 3:未処理
     */
    public static final String NOT_DEAL = "3";    
}
@
