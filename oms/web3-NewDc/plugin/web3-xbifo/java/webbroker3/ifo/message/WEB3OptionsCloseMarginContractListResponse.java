head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済建玉一覧レスポンスクラス(WEB3OptionsCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション返済建玉一覧レスポンス)<BR>
 * 株価指数オプション返済建玉一覧レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginContracList";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101920L;
        
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (銘柄名)<BR>
     */
    public String productName;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;
    
    /**
     * (決済順序)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     */
    public String closingOrder;
    
    /**
     * (返済建玉一覧明細行)<BR>
     */
    public webbroker3.ifo.message.WEB3OptionsCloseMarginContractGroup[] closeMarginContractGroups;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCloseMarginContractListResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
         */
    protected WEB3OptionsCloseMarginContractListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
