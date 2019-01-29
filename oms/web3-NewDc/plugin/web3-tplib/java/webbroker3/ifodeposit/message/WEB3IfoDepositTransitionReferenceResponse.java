head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移参照画面表示レスポンス(WEB3IfoDepositTransitionReferenceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/27 hijikata(SRA) 夕場対応 モデルNo.055, No.085
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (証拠金推移参照画面表示レスポンス)<BR>
 * 証拠金推移参照画面表示レスポンスクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceResponse extends WEB3GenResponse
{
    
    public static final String PTYPE = "ifodeposit_transition_reference";

    /**
     * (証拠金推移明細)<BR>
     * 証拠金推移の明細一覧。<BR>
     */
    public WEB3IfoDepositTransitionReferenceUnit[] ifoDepositUnit;

    /**
     * (未入金額)<BR>
     * 未入金額。<BR>
     */
    public String nonPayAmt;

    /**
     * (本日請求額)<BR>
     * 本日請求額。<BR>
     */
    public String todayClaimAmt;

    /**
     * (翌日請求額)<BR>
     * 翌日請求額。<BR>
     */
    public String tomorrowClaimAmt;

    /**
     * (翌々日請求額)<BR>
     * 翌々日請求額。<BR>
     */
    public String dayAfterTomorrowClaimAmt;

    /**
     * (翌日請求額＜夕場＞)<BR>
     * 翌日請求額＜夕場＞。<BR>
     */
    public String tomorrowClaimAmtEve;

    /**
     * (証拠金振替余力額)<BR>
     * 証拠金振替余力額。<BR>
     */
    public String depositChangePower;

    /**
     * (受渡日)<BR>
     * 受渡日。<BR>
     * 証拠金計算の基準日を設定。<BR>
     */
    public java.util.Date deliveryDate;

    /**
     * (SPAN区分)<BR>
     * 0：SPAN非採用<BR>
     * 1：SPAN採用<BR>
     * @@see webbroker3.ifodeposit.define.WEB3IfoDepositSPANDivDef
     */
    public String spanDiv;

    /**
     * (証拠金不足確定FLAG)<BR>
     * T+0の証拠金不足が確定しているか(＝翌日請求額が確定値であるか)を表すフラグ。<BR>
     * 0：未確定 <BR>
     * 1：確定<BR>
     * <BR>
     * @@see webbroker3.ifodeposit.define.WEB3IfoDepositFixedIfoDepositFlgDiv
     */
    public String fixedIfoDepositFlg;
    
    /**
     * (指数別証拠金)<BR>
     * 指数別証拠金の一覧。<BR>
     * <BR>
     * SPAN使用不可の場合設定。<BR>
     */
    public WEB3IfoDepositPerIndexUnit[] ifoDepositPerIndexUnit;

    /**
     * @@roseuid 4186176E0166
     */
    public WEB3IfoDepositTransitionReferenceResponse()
    {

    }
}
@
