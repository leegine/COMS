head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccRegTaxationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 課税区分(WEB3AccRegTaxationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 課税区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AccRegTaxationDivDef
{

    /**
     * 0：旧分離
     */
    public final static String OLD_SEPARATION  = "0";

    /**
     * 1：旧総合
     */
    public final static String OLD_INTEGRATION  = "1";

    /**
     * 2：外国
     */
    public final static String FOREIGN_COUNTRY  = "2";

    /**
     * 5：非課税法@人
     */
    public final static String NOT_IMPOSITION_CORPORATE  = "5";

    /**
     * 6：絶対非課税
     */
    public final static String ABSOLUTE_NOT_IMPOSITION  = "6";

    /**
     * 9：無関係
     */
    public final static String NO_RELATION  = "9";

}@
