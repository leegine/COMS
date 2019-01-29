head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
Revesion History : 2008/08/22 張少傑 (中訊) 仕様変更・モデルNo.247
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountListInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082143L;

    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     * ※　@画面に表示する初期値。<BR>
     */
    public Date trialStartDate;

    /**
     * (手数料コース一覧)<BR>
     * 変更可能手数料コース一覧<BR>
     * <BR>
     * ※　@証券会社が取り扱っている手数料コースコードの配列 <BR>
     * <BR>
     * 手数料コースコード <BR>
     * <BR>
     * 02：　@定率手数料（スタンダード） <BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ） <BR>
     * 03：　@約定代金合計 <BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR> 
     * 04：　@約定回数 <BR>
     * 05：　@一日定額制 <BR>
     * 06：　@少額ボックス <BR>
     * 07：　@現物1日合計＋信用1日注文毎<BR> 
     * 08：　@現物1日注文毎＋信用1日合計<BR>
     * 16：　@少額ボックス（キャンペーン） <BR>
     * 99：　@上記以外（リテラ・岩井のみ） <BR>
     * <BR>
     */
    public String[] commissionCourseList;

    /**
     * @@roseuid 418F386A003E
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse()
    {

    }

    /**
     * (管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse
     * @@roseuid 4151103000B9
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
