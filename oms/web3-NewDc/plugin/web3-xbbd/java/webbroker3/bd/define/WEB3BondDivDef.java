head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券区分インタフェイス(WEB3BondDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/26 謝旋(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 債券区分 定数定義インタフェイス
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */

public interface WEB3BondDivDef
{
    /**
     * 0:全部
     */
    public static final String ALL_BOND = "0";

    /**
     * 1:個人向け国債以外
     */
    public static final String EXCEPT_INDIVIDUAL_GOVERNMENT_BOND = "1";

    /**
     * 2:個人向け国債のみ
     */
    public static final String DOMESTIC_BOND_INDIVIDUAL = "2";
}
@
