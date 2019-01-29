head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTotalContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉集計Spec(WEB3GentradeTotalContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

/**
 * (建玉集計Spec)
 * 建玉集計の情報を表すクラス。
 */
public class WEB3GentradeTotalContractSpec
{

    /**
     * 銘柄コード
     */
    public String productCode;

    /**
     * 買建数量
     */
    public double marginLongNum;

    /**
     * 売建数量
     */
    public double marginShortNum;

    /**
     * コンストラクタ。<BR>
     * 引数の値を同プロパティにセットする。<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@param l_dblMarginLongNum - 買建数量
     * @@param l_dblMarginShortNum - 売建数量
     * @@roseuid 412D70BB02BE
     */
    public WEB3GentradeTotalContractSpec(
        String l_strProductCode,
        double l_dblMarginLongNum,
        double l_dblMarginShortNum)
    {
        this.productCode = l_strProductCode;
        this.marginLongNum = l_dblMarginLongNum;
        this.marginShortNum = l_dblMarginShortNum;
    }
}
@
