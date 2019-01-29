head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式保有資産一覧照会保有資産(WEB3EquityAssetUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                 : 2006/08/29 張騰宇(中訊) モデル 972
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式保有資産一覧照会保有資産）。<BR>
 * <BR>
 * 現物株式保有資産一覧照会保有資産　@データクラス
 * @@version 1.0
 */
public class WEB3EquityAssetUnit extends Message
{

    /**
     * 保有資産ID（非表示項目）<BR>
     */
    public String id;

    /**
     * (銘柄コード)
     */
    public String productCode;

    /**
     * (銘柄名)
     */
    public String productName;

    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (売付可能株数)
     */
    public String sellPossQuantity;

    /**
     * (注文中株数)
     */
    public String orderedQuantity;

    /**
     * (市場コード一覧)<BR>
     * 銘柄、取扱市場一覧<BR>
     */
    public String[] marketList;

    /**
     * (売付可能フラグ)<BR>
     * true：売付可能　@　@false：売付不可<BR>
     */
    public boolean sellPossFlag;

    /**
     * @@roseuid 409F5F4903C1
     */
    public WEB3EquityAssetUnit()
    {

    }
}
@
