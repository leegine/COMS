head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCostDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 諸経費区分定義インタフェイス(WEB3FeqCostDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/06 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 諸経費区分定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqCostDivDef
{
    /**
     * 01:現地手数料
     */
    public static final String FOREIGN_COMMISSION_FEE = "01";
    
    /**
     * 02:現地取引税
     */
    public static final String FOREIGN_TAX = "02";

    /**
     * 03:その他現地コスト1
     */
    public static final String FOREIGN_FEE_EXT1 = "03";

    /**
     * 04:その他現地コスト2
     */
    public static final String FOREIGN_FEE_EXT2 = "04";
}
@
