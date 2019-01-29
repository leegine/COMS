head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注Unit(WEB3EquityManualUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06　@魏新(中訊) 新規作成
                   2006/08/30 柴雙紅(中訊) 仕様変更モデル165
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;

/**
 * (株式手動発注Unit)<BR>
 * 株式手動発注Unit<BR>
 * 
 * @@author 魏新
 * @@version 1.0
 */
public class WEB3EquityManualUnit extends WEB3ManualCommonUnit 
{
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0:一般<BR>
     * 1:特定<BR>
     * 5:ストックオプション<BR>
     */
    public String taxType;
    
    /**
     * (値段条件)<BR>
     * 値段条件<BR>
     * <BR>
     * 0:指定なし<BR>
     * 1:現在値指値<BR>
     * 3:優先指値<BR>
     * 5:成行残数指値<BR>
     * 7:成行残数取消<BR>
     */
    public String priceCondType;
    
    /**
     * (概算簿価単価)<BR>
     * 概算簿価単価<BR> 
     */
    public String estimatedBookPrice;
    
    /**
     * (金利)<BR>
     * 金利<BR>
     * 信用取引区分が一般信用の時設定<BR>
     */
    public String interestRates;
    
    /**
     * (清算期間)<BR>
     * 清算期間<BR>
     * 一般信用かつ弁済期限が無期限の場合に表示<BR>
     */
    public String clearUpTerm;
    
    /**
     * (弁済)<BR>
     * 弁済<BR>
     * 信用取引の場合設定<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (信用取引建株明細)<BR>
     * 信用取引建株明細<BR>
     * <BR>
     * ※返済手動発注の場合設定<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityManualUnit() 
    {
     
    }
}
@
