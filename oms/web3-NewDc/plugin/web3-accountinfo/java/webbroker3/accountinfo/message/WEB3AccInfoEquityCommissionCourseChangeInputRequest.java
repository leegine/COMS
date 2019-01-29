head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込入力リクエスト(WEB3AccInfoEquityCommissionCourseChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
Revesion History : 2008/08/21  楊夫志 (中訊) 仕様変更・モデルNo.245
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報株式委託手数料コース変更申込入力リクエスト)<BR>
 * お客様情報株式委託手数料コース変更申込入力リクエスト<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseChangeInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082158L;

    /**
     * (変更前手数料コース)<BR>
     * 手数料コース（手数料コースコード）<BR>
     * <BR>
     * 02：　@定率手数料（スタンダード）<BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR>
     * 03：　@約定代金合計<BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR>
     * 04：　@約定回数<BR>
     * 05：　@一日定額制<BR>
     * 06：　@少額ボックス<BR>
     * 07：　@現物1日合計＋信用1日注文毎<BR>
     * 08：　@現物1日注文毎＋信用1日合計<BR>
     * 16：　@少額ボックス(キャンペーン)　@<BR>
     * 99：　@上記以外（リテラ・岩井のみ）<BR>
     * <BR>
     * ※　@各コードの名称については、証券会社によって違う。<BR>
     * 　@　@Web層にて、名称に変換する。<BR>
     */
    public String beforCommissionCourse;

    /**
     * @@roseuid 418F39F1036B
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoEquityCommissionCourseChangeInputResponse(this);
    }
}
@
