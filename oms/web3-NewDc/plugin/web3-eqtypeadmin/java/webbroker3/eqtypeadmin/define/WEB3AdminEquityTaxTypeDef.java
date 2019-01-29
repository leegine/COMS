head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税区分定数定義インタフェイス(WEB3AdminEquityTaxTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 張騰宇(中訊) 新規作成 仕様変更モデルNo.171
*/
package webbroker3.eqtypeadmin.define;

/**
 * 税区分 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityTaxTypeDef
{
    /**
     * "一般"
     */
    public final static String NORMAL = "一般";

    /**
     * "特定"
     */
    public final static String SPECIAL = "特定";
}
@
