head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuldingStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO抽選テーブルのステータス定数定義インタフェイス(WEB3IPOBookBuldingStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 孟東(中訊)　@新規作成
*/
package webbroker3.ipo.define;

/**
 * IPO抽選テーブルのステータス 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3IPOBookBuldingStatusDef
{
    /**
     * 0：正常終了
     */
    public static final String NORMAL_END = "0";

    /**
     * 1：処理中
     */
    public static final String DEALING = "1";

    /**
     * 9：異常終了
     */
    public static final String ABNORMAL_END = "9";
}
@
