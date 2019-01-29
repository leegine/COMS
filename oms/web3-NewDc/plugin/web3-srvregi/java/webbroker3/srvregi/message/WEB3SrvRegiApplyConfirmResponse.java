head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込確認レスポンス(WEB3SrvRegiApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2007/06/05 趙林鵬 (中訊) モデル.248
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用申込確認レスポンス)<BR>
 * サービス利用申込確認レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public class WEB3SrvRegiApplyConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151432L;
    
    /**
     * (適用開始日)
     */
    public Date trialStartDate;
    
    /**
     * (適用終了日)
     */
    public Date trialEndDate;
    
    /**
     * (確認時発注日)
     */
    public Date checkDate;
    
    /**
     * (税込入札額)<BR>
     * 対象サービスが抽選有・オークション有の場合のみ使用。<BR>
     * それ以外のサービスの場合、Nullを設定。<BR>
     */
    public String taxBidAmt;

    /**
     * (申込属性区分)<BR>
     * 1：無料対象　@2：申込不可<BR>
     */
    public String applyAttributeDiv;

    /**
     * (無料対象期間)<BR>
     * 無料対象期間<BR>
     */
    public String freeTargetPeriod;

    /**
     * (無料属性申込区分)<BR>
     * null or '0' ：通常申込　@'1'：無料属性申込<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (サービス利用申込確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1FD5B0120
     */
    public WEB3SrvRegiApplyConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
