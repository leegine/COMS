head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcConditionPerIndex.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 指数別証拠金計算条件クラス(WEB3IfoDepositCalcConditionPerIndex.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/27 hijikata(SRA) 夕場対応 モデルNo.056①@
 
 */
package webbroker3.ifodeposit;

/**
 * (指数別証拠金計算条件)<BR>
 * 会社・部店・指数別証拠金計算条件を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcConditionPerIndex
{

    /**
     * 原資産銘柄コード。
     */
    private String underlyingProductCode;

    /**
     * 規定証拠金。
     */
    private double ifoDepositPerUnit;

    /**
     * 規定証拠金レッド。
     */
    private double ifoDepositPerUnitRed;

    /**
     * 規定証拠金＜証拠金不足仮確定＞。
     */
    private double ifoDepositPerUnitTemp;

    /**
     * (指数別証拠金計算条件)<BR>
     * 
     * コンストラクタ。<BR>
     * @@roseuid 4112E91000DB
     */
    public WEB3IfoDepositCalcConditionPerIndex()
    {

    }

    /**
     * (set原資産銘柄コード)<BR>
     * 
     * 引数.原資産銘柄コードをthis.原資産銘柄コードにセットする。<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード。
     * @@roseuid 411327A60095
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        underlyingProductCode = l_strUnderlyingProductCode;
    }

    /**
     * (set規定証拠金)<BR>
     * 
     * 引数.規定証拠金をthis.規定証拠金にセットする。<BR>
     * @@param l_dblIfoDepositPerUnit - 規定証拠金
     * @@roseuid 4113271002E7
     */
    public void setIfoDepositPerUnit(double l_dblIfoDepositPerUnit)
    {
        ifoDepositPerUnit = l_dblIfoDepositPerUnit;
    }

    /**
     * (set規定証拠金レッド)<BR>
     * 
     * 引数.規定証拠金レッドをthis.規定証拠金レッドにセットする。<BR>
     * @@param l_dblIfoDepositPerUnitRed - 規定証拠金レッド
     * @@roseuid 41132769024A
     */
    public void setIfoDepositPerUnitRed(double l_dblIfoDepositPerUnitRed)
    {
        ifoDepositPerUnitRed = l_dblIfoDepositPerUnitRed;
    }

    /**
     * (set規定証拠金＜証拠金不足仮確定＞)<BR>
     * 
     * 引数.規定証拠金＜証拠金不足仮確定＞をthis.規定証拠金＜証拠金不足仮確定＞にセットする。<BR>
     * @@param l_dblIfoDepositPerUnitTemp - 規定証拠金＜証拠金不足仮確定＞
     */
    public void setIfoDepositPerUnitTemp(double l_dblIfoDepositPerUnitTemp)
    {
        ifoDepositPerUnitTemp = l_dblIfoDepositPerUnitTemp;
    }

    /**
     * (get原資産銘柄コード)<BR>
     * 
     * this.原資産銘柄コードを返却する。<BR>
     * @@return String
     * @@roseuid 4113285E0037
     */
    public String getUnderlyingProductCode()
    {
        return underlyingProductCode;
    }

    /**
     * (get規定証拠金)<BR>
     * 
     * this.規定証拠金を返却する。<BR>
     * @@return double
     * @@roseuid 4113289C01FC
     */
    public double getIfoDepositPerUnit()
    {
        return ifoDepositPerUnit;
    }

    /**
     * (get規定証拠金レッド)<BR>
     * 
     * this.規定証拠金レッドを返却する。<BR>
     * @@return double
     * @@roseuid 41132B0101ED
     */
    public double getIfoDepositPerUnitRed()
    {
        return ifoDepositPerUnitRed;
    }

	/**
	 * (get規定証拠金＜証拠金不足仮確定＞)<BR>
	 * 
	 * this.規定証拠金＜証拠金不足仮確定＞を返却する。<BR>
	 * @@return double
	 */
	public double getIfoDepositPerUnitTemp()
	{
		return ifoDepositPerUnitTemp;
	}
    
    /**
     * WEB3IfoDepositCalcConditionPerIndexの文字列表現を返す。 
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoDepositCalcConditionPerIndex={");
        l_sb.append("underlyingProductCode=").append(getUnderlyingProductCode());
        l_sb.append(",ifoDepositPerUnit=").append(getIfoDepositPerUnit());
        l_sb.append(",ifoDepositPerUnitRed=").append(getIfoDepositPerUnitRed());
        l_sb.append(",ifoDepositPerUnitTemp=").append(getIfoDepositPerUnitTemp());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
