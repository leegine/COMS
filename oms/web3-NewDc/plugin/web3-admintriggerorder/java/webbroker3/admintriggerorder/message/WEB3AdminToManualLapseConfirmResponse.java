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
filename	WEB3AdminToManualLapseConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効確認レスポンス(WEB3AdminToManualLapseConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (トリガー注文管理者・手動失効確認レスポンス)<BR>
 * トリガー注文管理者・手動失効確認レスポンス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualLapseConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
 
    /**
     * (対象注文件数)<BR>
     * 失効対象の注文件数<BR>
     */
    public String lapseTargetOrderNumber;
    
    /**
     * (現在時刻)<BR>
     * 現在時刻<BR>
     */
    public Date currentTime;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName = null;
    
    /**
     * (株式注文明細)<BR>
     */
    public WEB3AdminToEquityOrderRefUnit equityOrderUnit;
    
    /**
     * (先物OP注文明細)<BR>
     */
    public WEB3AdminToIfoOrderRefUnit ifoOrderUnit;
    
    /**
     * (株式建株明細)<BR>
     */
    public WEB3MarginContractUnit[] equityContractUnits;
    
    /**
     * (先物OP建玉明細)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] ifoContractUnits;
    
    /**
     * @@roseuid 44193091009C
     */
    public WEB3AdminToManualLapseConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToManualLapseConfirmResponse(WEB3AdminToManualLapseConfirmRequest l_request)
    {
        super(l_request);
    } 
}
@
