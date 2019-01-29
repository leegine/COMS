head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込入力レスポンス(WEB3AccInfoEquityCommissionCourseChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
Revesion History : 2008/08/22 張少傑 (中訊) 仕様変更・モデルNo.248
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報株式委託手数料コース変更申込入力レスポンス)<BR>
 * お客様情報株式委託手数料コース変更申込入力レスポンス<BR>
 * <BR>
 * ※ 以下のどちらかの条件に当てはまる場合は、<BR>
 * 変更申込入力フォームを表示しない。<BR>
 * <BR>
 * 　@[条件]<BR>
 * 　@・（手数料コース変更申込情報一覧 == null）の場合<BR>
 * 　@または、<BR>
 * 　@・手数料コース変更申込情報一覧に、（取消可能フラグ == true）<BR>
 * の行が１件もない場合<BR>
 * <BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeInputResponse extends WEB3GenResponse
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
     * (変更可能手数料コース一覧)<BR>
     * 変更可能手数料コース一覧<BR>
     * ※　@証券会社が取り扱っている手数料コースコードの配列<BR>
     * <BR>
     * 手数料コースコード<BR>
     * <BR>
     * 02：　@定率手数料（スタンダード） <BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR> 
     * 03：　@約定代金合計 <BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR> 
     * 04：　@約定回数<BR> 
     * 05：　@一日定額制<BR> 
     * 06：　@少額ボックス<BR> 
     * 07：　@現物1日合計＋信用1日注文毎<BR> 
     * 08：　@現物1日注文毎＋信用1日合計<BR> 
     * 16：　@少額ボックス（キャンペーン）<BR> 
     * 99：　@上記以外（リテラ・岩井のみ）<BR>
     * <BR>
     */
    public String[] changeAbleCommissionCourseList;

    /**
     * (適用開始日一覧)<BR>
     * 適用開始日一覧<BR>
     * ※ 手数料コース一覧と連動する。<BR>
     * ※　@証券会社が取り扱っている各手数料コースの適用開始日配列。<BR>
     */
    public Date[] applyStartDateList;

    /**
     * (変更申込締切指定日一覧)<BR>
     * 変更申込締切指定日。<BR>
     * <BR>
     * ※ 手数料コース一覧と連動する。<BR>
     * ※　@証券会社が取り扱っている各手数料コースの<BR>
     * 変更申込締切指定日配列。<BR>
     * <BR>
     * 00：毎日<BR>
     * 00以外の場合、日付指定。毎月、指定日が締切日となる。<BR>
     * <BR>
     */
    public String[] changeOfferDeadlineDateList;

    /**
     * (変更申込締切時間一覧)<BR>
     * 変更申込締切時間<BR>
     * <BR>
     * ※ 手数料コース一覧と連動する。<BR>
     * ※　@証券会社が取り扱っている各手数料コースの<BR>
     * 変更申込締切時間配列。<BR>
     * <BR>
     * HHMMSS（24h）<BR>
     * <BR>
     */
    public String[] changeOfferDeadlineTimeList;

    /**
     * (変更適用開始日指定区分一覧)<BR>
     * 変更適用開始日指定区分<BR>
     * <BR>
     * ※ 手数料コース一覧と連動する。<BR>
     * ※　@証券会社が取り扱っている各手数料コースの<BR>
     * 変更適用開始日指定区分配列。<BR>
     * <BR>
     * 1：　@申込日の翌月（月初営業日）<BR>
     * 2：　@申込日の翌々月（月初営業日）<BR>
     * 9：　@日数指定 <BR>
     * 　@　@※ 申込日から、変更適用開始日数で指定した日数後<BR>
     *　@   ※ 日数指定の場合、：に続けて日数値をセット<BR>
     * <BR>
     */
    public String[] changeApplyStartDateDivList;

    /**
     * (手数料コース変更申込情報一覧)<BR>
     * 手数料コース変更申込情報一覧<BR>
     * <BR>
     * ※　@当該項目がnullの場合は、<BR>
     * ”申込済み手数料コース”フォームは表示しない。<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] commissionCourceChangeList;

    /**
     * @@roseuid 418F39F2006D
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse()
    {

    }

    /**
     * (お客様情報株式委託手数料コース変更申込入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 41368E0B0006
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
