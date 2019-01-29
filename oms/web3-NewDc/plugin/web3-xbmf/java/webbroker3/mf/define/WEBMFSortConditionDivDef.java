head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEBMFSortConditionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソート条件区分 定数定義インタフェイス(WEBMFSortConditionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 于美麗(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * ソート条件区分　@定数定義インタフェイス
 *                                                                     
 * @@author 于美麗
 * @@version 1.0
 */
public interface WEBMFSortConditionDivDef
{
    /**
     * 管理者投信銘柄条件登録照会 
     */
    public static final String ADMIN_MUTUAL_COND_REF = "AdminMutualConditionsReference";

    /**
     * 買付一覧照会
     */
    public static final String MUTUAL_BUY_LIST = "MutualBuyList";

}
@
