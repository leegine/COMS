head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityWlimitEnableStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値用有効状態区分 定数定義インタフェイス(WEB3EquityWlimitEnableStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/02 趙林鵬(中訊) 新規作成 モデル No.995,1008
*/

package webbroker3.equity.define;

/**
 * W指値用有効状態区分 定数定義インタフェイス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3EquityWlimitEnableStatusDivDef
{
    /**
     * 0：リミット注文有効
     */
    public static final String LIMIT_ENABLE = "0";

    /**
     * 1：ストップ注文有効 　@　@
     */
    public static final String STOP_ENABLE = "1";

    /**
     * 2：ストップ注文失効済 　@　@
     */
    public static final String STOP_UN_ENABLE = "2";

}
@
