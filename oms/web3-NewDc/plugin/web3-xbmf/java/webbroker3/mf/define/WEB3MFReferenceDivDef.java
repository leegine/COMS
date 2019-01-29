head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFReferenceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MFReferenceDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/21 韋念瓊(中訊)　@新規作成
                   2006/09/19 周捷 (中訊) 仕様変更・モデル482
*/
package webbroker3.mf.define;

/**
 * 照会区分　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3MFReferenceDivDef
{
    /**
     * 0 : 買付一覧  
     */
    public static final String BUY_REFERENCE = "0";

    /**
     * 1 : 目論見書郵送請求一覧 
     */
    public static final String BOOK_REFERENCE = "1";
    
    /**
     * 買付のみ
     */
    public static final String BUY = "2";
    
    /**
     * 募集のみ
     */
    public static final String RECRUIT = "3";
}
@
