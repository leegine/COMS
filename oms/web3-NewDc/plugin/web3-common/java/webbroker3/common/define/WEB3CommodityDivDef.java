head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommodityDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品区分 定数定義インタフェイス(WEB3CommodityDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03 森川 昌平 (SRA) 新規作成
*/

package webbroker3.common.define;

/**
 * 商品区分定数定義インタフェイス
 * 
 * @@author  森川 昌平（SRA）
 * @@version 1.0
 */
public class WEB3CommodityDivDef
{
    /**
     * 1：　@現物株式
     */
    public static final String EQUITY = "1";

    /**
     * 2：　@信用取引
     */
    public static final String MARGIN = "2";

    /**
     * 3：　@先物
     */
    public static final String FUTURE = "3";

    /**
     * 4：　@オプション
     */
    public static final String OPTION = "4";
}
@
