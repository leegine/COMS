head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuldingProcDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO抽選テーブルの処理状況定数定義インタフェイス(WEB3IPOBookBuldingProcDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 孟東(中訊)　@新規作成
*/
package webbroker3.ipo.define;

/**
 * IPO抽選テーブルの処理状況 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3IPOBookBuldingProcDef
{
    /**
     * 01：抽選入力
     */
    public static final String LOT_INPUT = "01";

    /**
     * 02：抽選デーモン受付
     */
    public static final String LOT_DAEMON_ACCEPT = "02";

    /**
     * 03：抽選開始
     */
    public static final String LOT_BEGIN = "03";

    /**
     * 04：抽選終了
     */
    public static final String LOT_END = "04";

    /**
     * 11：確定入力
     */
    public static final String CONFIRM_INPUT = "11";

    /**
     * 12：確定デーモン受付
     */
    public static final String CONFIRM_DAEMON_ACCEPT = "12";

    /**
     * 13：確定開始
     */
    public static final String CONFIRM_BEGIN = "13";

    /**
     * 14：確定終了
     */
    public static final String CONFIRM_END = "14";
}
@
