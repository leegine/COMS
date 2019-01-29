head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoKeyHeaderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キーヘッダ定数定義インタフェイス(WEB3AdminIfoKeyHeaderDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/04/10 張騰宇 (中訊) 新規作成 モデルNo.017
*/
package webbroker3.ifoadmin.define;

/**
 * キーヘッダ定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminIfoKeyHeaderDef
{
    /**
     * CSV出力日
     */
    public static final String CSV_OUTPUT_DATE= "CSV出力日";

    /**
     * 発生日付
     */
    public static final String OCCUR_DATE= "発生日付";
}
@
