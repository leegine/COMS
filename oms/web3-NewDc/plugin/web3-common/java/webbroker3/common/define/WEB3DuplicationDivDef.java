head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DuplicationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査待ちテーブル・重複区分 定数定義インタフェイス(WEB3DuplicationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 重複区分 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3DuplicationDivDef
{

    /**
     * 1：顧客名＆生年月日
     */
    public final static String NAME_BORN_DATE = "1";

    /**
     * 2：メールアドレス
     */
    public final static String MAIL_ADDRESS = "2";  

    /**
     * 3：電話番号
     */
    public final static String TELEPHONE_NO = "3";  
}@
