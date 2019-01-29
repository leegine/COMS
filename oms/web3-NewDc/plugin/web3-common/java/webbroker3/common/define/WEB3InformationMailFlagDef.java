head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InformationMailFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 案内メール送信フラグ定数定義インタフェイス(WEB3InformationMailFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/18 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 案内メール送信フラグ定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3InformationMailFlagDef
{
    /**
     * 0：送信不要
     */
    public final static String FALSE = "0";

    /**
     * １：送信要
     */
    public final static String TRUE = "1";

    /**
     * 2：口座開設見込客．各社拡張項目（フラグ4）優先、デフォルト０：送信不要
     */
    public final static String EXT_ITEM_FLAG4_PRIORITY_FALSE = "2";

    /**
     * 3：口座開設見込客．各社拡張項目（フラグ4）優先、デフォルト１：送信要
     */
    public final static String EXT_ITEM_FLAG4_PRIORITY_TRUE = "3";
}
@
