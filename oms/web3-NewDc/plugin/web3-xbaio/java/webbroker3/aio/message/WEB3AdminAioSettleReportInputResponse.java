head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート入力レスポンスクラス(WEB3AdminAioSettleReportInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (決済連携レポート入力レスポンス)<BR>
 * 決済連携レポート入力レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121148L;   
       
    /**
     * (部店コード)<BR>
     * 現在の部店コード
     */
    public String branchCode;

    /**
     * (決済機@関一覧)<BR>
     * 該当する証券会社が選択可能な決済機@関のリスト
     */
    public WEB3AioSettleInstitutionUnit[] settleInstitutionUnits;
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioSettleReportInputResponse(WEB3AdminAioSettleReportInputRequest l_request) 
    {
        super(l_request);
    }
    
}
@
