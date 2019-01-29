head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TaxTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 周勇 (中訊) 新規作成
Revesion History : 2006/08/30 栄イ(中訊)  メッセージ定義書による
*/

package webbroker3.common.define;

/**
 * 法@人等、一般口座、特定口座区分　@定数定義インタフェイス
 *
 * @@author 周勇
 * @@version 1.0
 */
public interface WEB3TaxTypeDef
{

    /**
     * 法@人等
     */
    public static final String OTHER = "△";

    /**
     * 一般口座
     */
    public static final String NORMAL = "0";

    /**
     * 特定口座
     */
    public static final String SPECIAL = "1";

    /**
     * 5:ストックオプショ
     */
    public static final String STOCK_OPTION = "5";
}
@
