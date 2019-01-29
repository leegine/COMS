head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProductTypeCodeSpecDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定商品コード(WEB3ProductTypeCodeSpecDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 指定商品コード 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ProductTypeCodeSpecDef
{

    /**
     * 2：投資信託
     */
    public final static String MUTUAL_FUND  = "2";

    /**
     * 3：公社債
     */
    public final static String PUBLIC_CORPORATE_BOND  = "3";

    /**
     * B：ｵｰﾌﾟﾝｺｰｽ
     */
    public final static String OPEN_COURSE  = "B";

    /**
     * D：国債ｺｰｽ
     */
    public final static String NATIONAL_BOND_COURSE  = "D";

}@
