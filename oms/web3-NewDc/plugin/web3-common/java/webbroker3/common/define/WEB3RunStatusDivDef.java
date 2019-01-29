head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RunStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3RunStatusDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/5/6 王暁傑 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 実行ステータス区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王暁傑
 * @@version 1.0
 */
public interface WEB3RunStatusDivDef
{
    /**
     * 0：未処理　@　@
     */
    public static final String NOT_DEAL = "0";
    
    /**
     * 1：処理済
     */
    public static final String DEALED = "1";

    /**
     * 5：処理中
     */
    public static final String DEALING = "5";
    
    /**
     * 9：エラー
     */
    public static final String ERROR = "9";
}
@
