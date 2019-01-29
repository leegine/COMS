head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換確認レスポンスクラス(WEB3MutualSwitchingConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
                   2004/12/07 于美麗 (中訊) 残対応
                   2005/10/18 韋念瓊 (中訊) フィデリティ対応
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託乗換確認レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSwitchingConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * 解約注文内容警告区分<BR>
     * <BR>
     * null:エラー無し<BR>
     * 1:警告「解約金額拘束率の超過による"全部指定"変更」<BR>
     */
    public String sellWarningType;    
    
    /**
     * 概算買付口数（乗換先）<BR>
     */
    public String switchingEstimatedQty;
    
    /**
     * 概算受渡代金 <BR>
     */
    public String estimatedPrice;
    
    /**
     * 数量<BR>
     */
    public String mutualOrderQuantity;
    
    /**
     * 注文ID<BR>
     */
    public String orderId;

    /**
     * (投信乗換確認レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8AABD0130
     */
    public WEB3MutualSwitchingConfirmResponse() 
    {
     
    }
}
@
