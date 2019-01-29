head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTargetContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 対象建玉(WEB3TPTargetContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (対象建玉)
 */
public class WEB3TPTargetContract 
{
    
    /**
     * (新規建約定済フラグ)
     */
    private boolean contractExecutedFlag;
    
    /**
     * (対象建玉詳細)
     */
    private WEB3TPTargetContractDetail targetContractDetail;
    
    /**
     * @@roseuid 4104AB470167
     */
    public WEB3TPTargetContract() 
    {
     
    }
    
    /**
     * (create対象建玉)<BR>
     * 対象建玉を生成する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DC0649030D
     */
    public static WEB3TPTargetContract create() 
    {
        return new WEB3TPTargetContract();
    }
    
    /**
     * (is新規建約定済)<BR>
     * 新規建約定済か判定する。<BR>
     * @@return boolean
     * @@roseuid 40C966D00150
     */
    public boolean isContractExecuted() 
    {
        return contractExecutedFlag;
    }
    
    /**
     * (set新規建約定済フラグ)<BR>
     * 引数の新規建約定済フラグをセットする。<BR>
     * @@param l_isContractExecuted - (新規建約定済フラグ)
     * @@roseuid 40C966DD0056
     */
    public void setContractExecuted(boolean l_isContractExecuted) 
    {
        contractExecutedFlag = l_isContractExecuted;     
    }
    
    /**
     * (get対象建玉詳細)<BR>
     * 対象建玉詳細を取得する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail
     * @@roseuid 40C964E001CD
     */
    public WEB3TPTargetContractDetail getTargetContractDetail() 
    {
        return targetContractDetail;
    }
    
    /**
     * (set対象建玉詳細)<BR>
     * 引数の対象建玉詳細をセットする。<BR>
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@roseuid 40C965460373
     */
    public void setTargetContractDetail(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        targetContractDetail = l_targetContractDetail;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractExecutedFlag", isContractExecuted())
            .append("targetContractDetail", getTargetContractDetail())
            .toString();
    }
}
@
