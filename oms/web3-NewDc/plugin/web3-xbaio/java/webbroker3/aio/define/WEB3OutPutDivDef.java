head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3OutPutDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OutPutDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * (出力区分)<BR>
 * 画面にて選択された出力区分<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public interface WEB3OutPutDivDef
{
    /**
     * 0： 一覧<BR>
     */
    public static final String LIST_VIEW = "0";

    /**
     * 1： CSV<BR>
     */
    public static final String CSV_VIEW = "1";   
}
@
