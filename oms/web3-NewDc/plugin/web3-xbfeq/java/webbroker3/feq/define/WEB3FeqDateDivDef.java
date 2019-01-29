head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqDateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日付区分定数定義インタフェイス(WEB3FeqDateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/03 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 日付区分定数定義インタフェイス
 * 現地カレンダー情報(WEB3AdminFeqLocalCalendarUnit)用
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqDateDivDef
{
    /**
     * 0：非営業日
     */
    public static final String NO_BIZ_DATE = "0";

    /**
     * 1：営業日
     */
    public static final String BIZ_DATE = "1";
}
@
