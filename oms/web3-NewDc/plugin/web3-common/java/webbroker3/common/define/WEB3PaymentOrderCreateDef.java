head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentOrderCreateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約時出金注文生成定数定義インタフェイス(WEB3PaymentOrderCreateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 投資信託解約時出金注文生成定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3PaymentOrderCreateDef
{
    /**
     * 0：投資信託前受拘束なし
     */
    public static final String DEFAULT = "0";

    /**
     * 1：発注日以降から受渡日前日まで投資信託前受拘束あり
     */
    public static final String CREATE_ORDER = "1";
}
@
