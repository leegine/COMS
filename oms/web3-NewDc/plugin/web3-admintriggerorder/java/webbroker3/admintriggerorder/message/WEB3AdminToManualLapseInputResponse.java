head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効入力レスポンス(WEB3AdminToManualLapseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・手動失効入力レスポンス)<BR>
 * トリガー注文管理者・手動失効入力レスポンス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualLapseInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
    
    /**
     * (条件注文種別一覧)<BR>
     * 条件注文種別一覧<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     * <BR>
     * ※APでは条件注文実施かどうかを判別する方法@が<BR>
     * 　@存在しない為、一律nullをセットする。<BR>
     * 　@（この項目は将来的に対応した場合に使用する。）<BR>
     */
    public String[] triggerOrderTypeList = null;
    
    /**
     * (商品区分一覧)<BR>
     * 商品区分一覧<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション <BR>
     */
    public String[] productDivList;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コード一覧<BR>
     */
    public String[] marketList = null;
    
    /**
     * @@roseuid 44192EEB036B
     */
    public WEB3AdminToManualLapseInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToManualLapseInputResponse(WEB3AdminToManualLapseInputRequest l_request)
    {
        super(l_request);
    } 
}
@
