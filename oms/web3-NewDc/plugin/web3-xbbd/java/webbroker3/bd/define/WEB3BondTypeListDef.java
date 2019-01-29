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
filename	WEB3BondTypeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券タイプ一覧 定数定義インタフェイス(WEB3BondTypeListDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/11 劉立峰(中訊) 新規作成   モデルNo.100
*/

package webbroker3.bd.define;

public class WEB3BondTypeListDef
{
    /**
     * 4：外国債券
     */
    public static final String FOREIGN_BOND = "4";

    /**
     * 11：個人向け国債
     */
    public static final String INDIVIDUAL_GOVERNMENT_BOND = "11";

    /**
     * 12：社債
     */
    public static final String CORPORATE_BOND = "12";
}
@
