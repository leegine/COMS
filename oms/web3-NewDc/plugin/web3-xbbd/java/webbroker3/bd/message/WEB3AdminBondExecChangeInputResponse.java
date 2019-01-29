head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者約定変更入力レスポンス(WEB3AdminBondExecChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者約定変更入力レスポンス)<BR>
 * 管理者約定変更入力レスポンス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecChangeInputResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_change_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (顧客情報)<BR>
     * 顧客情報
     */
    public WEB3AdminBondAccountInfo accountInfo;
    
    /**
     * (銘柄情報)<BR>
     * 銘柄情報
     */
    public WEB3AdminBondProductInfo productInfo;
    
    /**
     * (注文情報)<BR>
     * 注文情報
     */
    public WEB3AdminBondOrderInfo orderInfo;
    
    /**
     * (約定情報)<BR>
     * 約定情報
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * (カストディアン一覧)<BR>
     * カストディアン一覧<BR>
     * <BR>
     * カストディアンの配列
     */
    public WEB3AdminBondCustodianUnit[] custodianList;
    
    /**
     * (入力時発注日)<BR>
     * 入力時発注日
     */
    public Date inpOrderDate;
    
    /**
     * @@roseuid 44E3363600AB
     */
    public WEB3AdminBondExecChangeInputResponse() 
    {
     
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondExecChangeInputResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
