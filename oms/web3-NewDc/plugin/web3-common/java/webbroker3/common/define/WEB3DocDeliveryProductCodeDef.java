head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocDeliveryProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄コード定数定義インタフェイス(WEB3DocDeliveryProductCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/10 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 書面交付管理テーブルの銘柄コード 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3DocDeliveryProductCodeDef
{
    /**
     * 種別コードが目論見書の場合は銘柄コードが、<BR>
     * 金商法@書面の場合は、"0000000"（固定値）がセットされる。<BR>
     */
    public static final String DEFAULT = "0000000";
}
@
