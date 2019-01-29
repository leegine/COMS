head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityClosingContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建株返済指定情報(WEB3EquityClosingContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 呉艶飛 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeClosingContractSpecImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;

/**
 * （建株返済指定情報）。<BR>
 * <BR>
 * 建株返済指定情報を表現する。
 * @@author 呉艶飛
 * @@version 1.0
 * xTradeのEqTypeClosingContractSpecImplを拡張したクラス。<BR>
 */
public class WEB3EquityClosingContractSpec extends EqTypeClosingContractSpecImpl 
{
     
    /**
     * (建株返済指定情報)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 行オブジェクト（EqTypeClosingContractSpecParams）を指定し
     * スーパークラスのコンストラクタをコールする。<BR>
     * @@param l_closingMarsinSpecInfoRow - 返済指定情報行オブジェクト<BR>
     * @@return WEB3EquityClosingContractSpec
     * @@roseuid 406A72A60362
     */
    public WEB3EquityClosingContractSpec(EqtypeClosingContractSpecRow l_closingMarsinSpecInfoRow) 
    {
        super(l_closingMarsinSpecInfoRow);
    }
}
@
