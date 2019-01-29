head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ詳細レスポンス(WEB3AdminAccOpenStateInquiryDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設状況問合せ詳細レスポンス)<BR>
 * 管理者口座開設状況問合せ詳細レスポンス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryDetailResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081607L;

    /**
     * (変更不可項目一覧)<BR>
     * 変更不可項目一覧<BR>
     * <BR>
     * ※ 伝票作成済で、変更が不可能な状態の項目シンボル名を配列にてセットする。<BR>
     * <BR>
     * ※ 指定される項目は、口座開設申込情報内のプロパティに限定する。<BR>
     * 　@　@口座開設申込情報が集約しているUnitオブジェクト内の個別の<BR>
     * プロパティが指定されることはない。<BR>
     */
    public String[] changeUnableItemList;

    /**
     * (更新可能フラグ)<BR>
     * 更新可能フラグ<BR>
     * <BR>
     * true：　@更新可能<BR>
     * false：　@更新不可<BR>
     */
    public boolean changeFlag;

    /**
     * (伝票作成可能フラグ)<BR>
     * 伝票作成可能フラグ<BR>
     * <BR>
     * true：　@伝票作成可能<BR>
     * false：　@伝票作成不可<BR>
     */
    public boolean voucherFlag;

    /**
     * (備考１)<BR>
     * 備考１<BR>
     * <BR>
     * ※（不備項目）備考欄<BR>
     */
    public String bikou1;

    /**
     * (備考２)<BR>
     * 備考２<BR>
     * <BR>
     * ※（不備項目）備考欄<BR>
     */
    public String bikou2;

    /**
     * (口座開設申込情報)<BR>
     * 口座開設申込情報<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * (不備項目情報一覧)<BR>
     * 不備項目情報一覧<BR>
     */
    public WEB3AccOpenInvalidItemInfo[] invalidItemInfo;

    /**
     * (伝票作成情報)<BR>
     * 伝票作成情報
     */
    public WEB3AccOpenVoucherInfo voucherInfo;

    /**
     * @@roseuid 41B45E76031C
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
