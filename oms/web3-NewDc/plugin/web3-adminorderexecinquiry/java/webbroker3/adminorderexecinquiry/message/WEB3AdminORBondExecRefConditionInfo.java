head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefConditionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会検索条件情報(WEB3AdminORBondExecRefConditionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成  
Revesion History : 2007/07/10 劉立峰(中訊) 仕様変更モデルNo.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (債券管理者注文約定照会検索条件情報)<BR>
 * 注文約定照会検索するときの条件<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefConditionInfo extends Message
{
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String id;
    
    /**
     * (部店コード)<BR>
     * 部店コード <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>  
     * 取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (約定日)<BR>
     * 約定日
     */
    public Date executionUpdateDate;
    
    /**
     * (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * 401：買付　@402：売却　@404：応募
     */
    public String tradingType;
    
    /**
     * (注文約定区分)<BR>
     * 注文約定区分<BR>
     * <BR>
     * 0：未約定　@1：約定済　@2：取消済
     */
    public String executionState;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 1：円貨　@2：外貨
     */
    public String settleDiv;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (オペレータコード)<BR>
     * オペレータコード
     */
    public String operatorCode;
    
    /**
     * (扱者コード（SONAR）)<BR>.
     * 扱者コード（SONAR）
     */
    public String sonarTraderCode;
    
    /**
     * (管理者コード)<BR>
     * 管理者コード
     */
    public String administratorCode;
    
    /**
     * (債券タイプ)<BR>
     * 債券タイプ<BR>
     * 4：外国債券　@11：個人向け国債 12：社債
     */
    public String bondType;

    /**
     * @@roseuid 44E335A700AB
     */
    public WEB3AdminORBondExecRefConditionInfo() 
    {
     
    }
}
@
