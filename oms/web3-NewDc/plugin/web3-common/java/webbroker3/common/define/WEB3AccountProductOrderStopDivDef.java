head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountProductOrderStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  顧客銘柄別取引停止フラグ　@定数定義インタフェイス(WEB3AccountProductOrderStopDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客銘柄別取引停止フラグ　@定数定義インタフェイス<BR>
 * <BR>
 *    顧客銘柄別取引停止テーブル.買現物取引停止<BR>
 *    顧客銘柄別取引停止テーブル.売現物取引停止<BR>
 *    顧客銘柄別取引停止テーブル.買新規建取引停止<BR>
 *    顧客銘柄別取引停止テーブル.売新規建取引停止<BR>
 *    顧客銘柄別取引停止テーブル.買建返済（売返済）取引停止<BR>
 *    顧客銘柄別取引停止テーブル.売建返済（買返済）取引停止<BR>
 *    顧客銘柄別取引停止テーブル.現引取引停止<BR>
 *    顧客銘柄別取引停止テーブル.現渡取引停止<BR>
 *    顧客銘柄別取引停止テーブル.買ミニ株取引停止<BR>
 *    顧客銘柄別取引停止テーブル.売ミニ株取引停止<BR>
 * <BR>
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3AccountProductOrderStopDivDef
{
    /**
     * 0：DEFAULT
     */
    public final static String DEFAULT = "0";

    /**
     * 1：停止中
     */
    public final static String STOP_TRADE = "1";

}
@
