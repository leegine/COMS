head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄登録入力レスポンス(WEB3AdminBondProductRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                     2006/10/08 周捷 (中訊) 仕様変更・モデル107
Revesion History : 2008/08/13 馮海濤 (中訊) 仕様変更・モデル260
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者債券銘柄登録入力レスポンス)<BR>
 * 管理者債券銘柄登録入力レスポンスクラス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistInputResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;

    /**
     * (債券銘柄更新情報)<BR>
     * 債券銘柄更新情報
     */
    public WEB3AdminBondProductUpdateInfo updateInfo;

    /**
     * (債券銘柄基本情報)<BR>
     * 債券銘柄基本情報
     */
    public WEB3AdminBondProductBasicInfo basicInfo;

    /**
     * (カストディアン一覧)<BR>
     * カストディアン一覧<BR>
     * 　@<BR>
     * 　@カストディアンの配列
     */
    public WEB3AdminBondCustodianUnit[] custodianList;

    /**
     * (債券自動約定枠履歴一覧)<BR>
     * 債券自動約定枠履歴一覧<BR>
     * <BR>
     * 　@債券自動約定枠履歴の配列
     */
    public WEB3AdminBondAutoExecLimitHistoryUnit[] autoExecLimitList;

    /**
     * (約定済残高)<BR>
     * 約定済残高
     */
    public String executedBalance;

    /**
     * (債券銘柄利率一覧)<BR>
     * 債券銘柄利率一覧<BR>
     * <BR>
     * 　@債券銘柄利率の配列
     */
    public WEB3AdminBondProductCouponUnit[] productCouponList;

    /**
     * (管理者コード)<BR>
     * 管理者コード
     */
    public String administratorCode;

    /**
     * (管理者最終更新日時)<BR>
     * 管理者最終更新日時
     */
    public Date lastUpdateTime;

    /**
     * (取扱区分リスト)<BR>
     * 取扱区分一覧を保持するString配列。<BR>
     * <BR>
     * 0：不可  1：管理者　@2：管理者/顧客
     */
    public String[] tradeHandleDivList;

    /**
     * (売買区分リスト)<BR>
     * 売買区分一覧を保持するString配列。<BR>
     * <BR>
     * 1:買付 2:売却　@3:応募 4：買付/売却
     */
    public String[] buySellDivList;

    /**
     * (カレンダー連携市場コードリスト)<BR>
     * カレンダー連携市場コードリスト
     */
    public String[] calLinkedDivList;

    /**
     * (自動約定枠区分リスト)<BR>
     * 自動約定枠区分リスト<BR>
     * <BR>
     * 0：非自動約定　@1：自動約定
     */
    public String[] autoExecDivList;

    /**
     * (取引時間チェック区分リスト)<BR>
     * 取引時間チェック区分リスト <BR>
     * <BR>
     * 0：取引時間をチェックする   1：取引時間をチェックしない
     */
    public String[] tradeTimeCheckDivList;

    /**
     * (応募勧誘形式リスト)<BR>
     * 応募勧誘形式リスト<BR>
     */
    public String[] recruitInvitationFormList;

    /**
     * (応募引受け区分リスト)<BR>
     * 応募引受け区分リスト<BR>
     */
    public String[] recruitAcceptDivList;

    /**
     * @@roseuid 44E3363D01A5
     */
    public WEB3AdminBondProductRegistInputResponse()
    {

    }

    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondProductRegistInputResponse(WEB3GenRequest l_request)
    {

        super(l_request);

    }
}
@
