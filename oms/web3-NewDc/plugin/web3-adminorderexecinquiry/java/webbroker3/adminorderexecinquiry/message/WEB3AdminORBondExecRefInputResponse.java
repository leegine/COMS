head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会条件表示レスポンスクラス(WEB3AdminORBondExecRefInputResponse )
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/1 何文敏(中訊) 新規作成   
Revesion History : 2007/07/9 劉立峰(中訊) 仕様変更モデルNo.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 *（債券管理者注文約定照会条件表示レスポンスクラス)<BR>
 * 債券管理者注文約定照会条件表示レスポンスクラス<BR>
 *  
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefInputResponse extends  WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608091104L;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (注文種別一覧)<BR>
     * 注文種別一覧<BR>
     * <BR>
     * 401：買付　@　@　@　@402：売却　@　@　@　@404：応募
     */
    public String[] tradingTypeList;
    
    /**
     * (注文約定区分一覧)<BR>
     * 注文約定区分一覧<BR>
     * <BR>
     * 0：未約定　@1：約定済　@2：取消済
     */
    public String[] executionStateList;
    
    /**
     * (決済区分一覧)<BR>
     * 決済区分一覧<BR>
     * 1：円貨　@2：外貨
     */
    public String[] settleDivList;
    
    /**
     * (通貨コード一覧)<BR>
     * 通貨コード一覧
     */
    public String[] currencyCodeList;

    /**
     * (債券タイプ一覧)<BR>
     * 債券タイプ一覧<BR>
     * 4：外国債券　@11：個人向け国債 12：社債
     */
    public String[] bondTypeList;

    public WEB3AdminORBondExecRefInputResponse() 
    {
     
    }

    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminORBondExecRefInputResponse(WEB3AdminORBondExecRefInputRequest l_request)
    {
        super(l_request);
}
}
@
