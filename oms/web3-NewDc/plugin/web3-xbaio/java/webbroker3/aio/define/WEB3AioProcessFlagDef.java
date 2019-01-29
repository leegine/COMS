head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioProcessFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioProcessFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 周勇 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 出金余力チェックリクエストの処理フラグ　@定数定義インタフェイス
 *                                                                     
 * @@author 周勇
 * @@version 1.0
 */
public class WEB3AioProcessFlagDef
{
    /**
     * 0：全件データ処理
     */
    public static final String ALL_DATE_PROCESS = "0";
    
    /**
     * 1：当日振込分データ処理
     */
    public static final String DAY_BIZ_TRANSFER_PROCESS = "1";
    
    /**
     * 2：翌日振込分データ処理
     */
    public static final String NEXT_DATE_TRANSFER_PROCESS = "2";
}
@
