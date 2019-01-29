head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換完了レスポンスクラス(WEB3MutualSwitchingCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託乗換完了レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_complete";
    
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
    protected WEB3MutualSwitchingCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * (投信乗換完了レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8B2DC0346
     */
    public WEB3MutualSwitchingCompleteResponse() 
    {
     
    }
}
@
