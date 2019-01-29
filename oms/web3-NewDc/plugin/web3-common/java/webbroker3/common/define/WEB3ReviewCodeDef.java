head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReviewCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査待ちテーブル・審査種別 定数定義インタフェイス(WEB3ReviewCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 審査種別 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3ReviewCodeDef
{

    /**
     * 0：チェックなし
     */
    public final static String DEFAULT = "0";

    /**
     * 1：同一顧客チェック
     */
    public final static String DUPLICATE_ACCOUNT_CHECK_MAIN = "1";

    /**
     * 2：同一顧客チェック（見込）
     */
    public final static String DUPLICATE_ACCOUNT_CHECK_EXP = "2";  

    /**
     * 3：Y客チェック
     */
    public final static String YELLOW_ACCOUNT_CHECK = "3";  
}@
