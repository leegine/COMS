head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenIdConfirmFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 本人確認区分 定数定義インタフェイス(WEB3AccountOpenIdConfirmFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 本人確認区分 定数定義インタフェイス
 * 
 * @@author 郭英(中訊)
 * @@version 1.0
 */

public interface WEB3AccountOpenIdConfirmFlagDef
{
    /**
     * 0：未確認
     */
    public static final String NOT_CONFIRMED = "0";

    /**
     * 1：確認済み
     */
    public static final String CONFIRMED = "1";
}
@
