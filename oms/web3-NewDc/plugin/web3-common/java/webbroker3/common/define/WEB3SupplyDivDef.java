head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SupplyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 提供区分　@定数定義インタフェイス(WEB3SupplyDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 張威 (中訊) 新規作成
Revesion History : 2007/11/02 栄イ(中訊) サービス利用仕様変更管理台帳・ＤＢレイアウトNo029
*/
package webbroker3.common.define;

/**
 * 提供区分　@定数定義インタフェイス。
 *
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3SupplyDivDef
{
    /**
     * NULL：条件付けを行わない　@
     */
    public final static String NO_CONDITION_ATTACHED = "NULL";

    /**
     * 0：無料提供のみ（条件達成時に無料提供、未達成時に未提供）
     */
    public final static String FREE_SUPPLY = "0";

    /**
     * 1：有料／無料提供（条件達成時に無料提供、未達成時に有料提供）　@
     */
    public final static String CHARGE_OR_FREE_SUPPLY = "1";

    /**
     * 2：無料提供のみ(ウツミ屋)
     */
    public final static String CHARGE_SUPPLY_UTUMIYA = "2";

    /**
     * 3：有料／無料提供(ウツミ屋)
     */
    public final static String CHARGE_OR_FREE_SUPPLY__UTUMIYA = "3";
}@
