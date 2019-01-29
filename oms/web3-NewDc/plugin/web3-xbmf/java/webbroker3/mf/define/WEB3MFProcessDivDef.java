head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFProcessDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MFProcessDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 Wei-NianQiong (sinocom)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 処理区分(投信)　@定数定義インタフェイス
 *                                                                     
 * @@author Wei-NianQiong
 * @@version 1.0
 */
public interface WEB3MFProcessDivDef
{
    /**
     * 1 : 買付
     */
    public static final String BUY = "1";

    /**
     * 2 : 解約
     */
    public static final String SELL = "2";

    /**
     * 3 : 乗換
     */
    public static final String SWITCHING = "3";
    
    /**
     * 4 : 募集
     */
    public static final String RECRUIT = "4";

}
@
