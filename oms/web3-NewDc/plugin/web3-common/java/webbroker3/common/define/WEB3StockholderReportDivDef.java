head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StockholderReportDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株主報告(WEB3StockholderReportDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 株主報告 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3StockholderReportDivDef
{

    /**
     * 1：同意
     */
    public final static String AGREE  = "1";

    /**
     * 5：一部同意
     */
    public final static String PART_AGREE  = "5";

    /**
     * 9：非同意
     */
    public final static String DISAGREE  = "9";

}@
