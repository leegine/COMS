head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFromWebSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソートキーが示すキー項目(WEB3AccOpenFromWebSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/12 黄建 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * ソートキーが示すキー項目
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3AccOpenFromWebSortKeyDef
{
    /**
     * 発生日時
     */
    public static final String OCCRRRED_DATE = "occurredDate";

    /**
     * 識別コード
     */
    public static final String REQUEST_NUMBER = "requestNumber";
}
@
