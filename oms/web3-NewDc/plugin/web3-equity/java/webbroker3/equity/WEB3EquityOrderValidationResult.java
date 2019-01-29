head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderValidationResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式発注審査結果(WEB3EquityOrderValidationResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 中尾寿彦(SRA) 新規作成
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;

/**
 * （株式発注審査結果）。<BR>
 * <BR>
 * 変更注文、取消注文など発注審査の結果を示すために利用するクラス。<BR>
 * （EqTypeOrderValidationResultの拡張クラス）
 * @@version 1.0
 */
public class WEB3EquityOrderValidationResult extends EqTypeOrderValidationResult
{
    /**
     * 空売り規制対象フラグ。<BR>
     */
    private boolean isShortSellingRestraint;

    /**
     * コンストラクタ。<BR>
     * <BR>
     * １）　@superクラスのコンストラクタをコールする。<BR>
     * <BR>
     * ２）　@引数の空売り規制対象フラグをプロパティにセットする。<BR>
     * <BR>
     * @@param l_result - (発注審査結果)
     * @@param isShortSellingRestraint - (空売り規制対象フラグ)
     */
    public WEB3EquityOrderValidationResult(ProcessingResult l_result, boolean isShortSellingRestraint)
    {
        super(l_result);
        this.isShortSellingRestraint = isShortSellingRestraint;
    }

    /**
     * (set空売り規制対象フラグ)<BR>
     * <BR>
     * 空売り規制対象フラグをセットする。<BR>
     * <BR>
     * @@param isShortSellingRestraint - (空売り規制対象フラグ)
     */
    public void setShortSellingRestraint(boolean isShortSellingRestraint)
    {
        this.isShortSellingRestraint = isShortSellingRestraint;
    }

    /**
     * (get空売り規制対象フラグ)<BR>
     * <BR>
     * 空売り規制対象フラグを取得する。<BR>
     * true：チェック結果NG（規制対象）、false：チェック結果OK（規制対象外）<BR>
     * <BR>
     * @@return boolean
     */
    public boolean getShortSellingRestraint()
    {
        return this.isShortSellingRestraint;
    }
}
@
