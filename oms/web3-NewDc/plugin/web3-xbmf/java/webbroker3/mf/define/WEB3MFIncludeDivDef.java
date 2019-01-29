head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFIncludeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買取請求算入区分 定数定義インタフェイス(WEB3MFIncludeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 于美麗(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 買取請求算入区分　@定数定義インタフェイス
 * 取得日別残高テーブル の 買取請求算入区分
 *                                                                     
 * @@author 于美麗
 * @@version 1.0
 */
public interface WEB3MFIncludeDivDef
{
    /**
     * 0:買取請求不算入 
     */
    public static final String NOT_INCLUDE = "0";

    /**
     * 1:買取請求算入
     */
    public static final String INCLUDE = "1";

}
@
