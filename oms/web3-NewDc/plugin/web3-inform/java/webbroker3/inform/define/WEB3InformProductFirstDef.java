head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformProductFirstDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 商品の頭1桁 定数定義インタフェイス(WEB3InformProductFirstDef.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/28 謝旋(中訊) 作成
*/

package webbroker3.inform.define;

/**
 * (商品の頭1桁)<BR>
 * 商品の頭1桁
 * @@author 謝旋
 * @@version 1.0
 */
public interface WEB3InformProductFirstDef
{
    /**
     * 2：投資信託
     */
    public static final String MUTUAL_FUND = "2";

    /**
     * 3：公社債
     */
    public static final String PUBLIC_AND_CORPORATE_BOODS = "3";

    /**
     * B：ｵｰﾌﾟﾝｺｰｽ
     */
    public static final String OPEN_COURSE  = "B";

    /**
     * D：国債ｺｰｽ
     */
    public static final String NATIONAL_BOND_COURSE  = "D";

    /**
     * E：公社債投信コース
     */
    public static final String UBLIC_AND_CORPORATE_Fund_COURSE = "E";

    /**
     * R：オープン株投コース
     */
    public static final String OPEN_KABUTOU_COURSE = "R";
}
@
