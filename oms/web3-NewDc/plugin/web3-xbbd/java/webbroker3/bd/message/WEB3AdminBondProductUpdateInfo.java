head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄更新情報(WEB3AdminBondProductUpdateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                     2006/10/08 周捷 (中訊) 仕様変更・モデル106、107
Revesion History : 2008/08/13 馮海濤 (中訊) 仕様変更　@モデル260
Revesion History : 2009/07/24 武波 (中訊) 仕様変更　@モデル261
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券銘柄更新情報)<BR>
 * 債券銘柄更新情報クラス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductUpdateInfo extends Message
{

    /**
     * (取扱区分)<BR>
     * 取扱区分<BR>
     * <BR>
     * 　@0：不可　@1：管理者　@2：管理者/顧客
     */
    public String tradeHandleDiv;

    /**
     * (取扱開始日時)<BR>
     * 取扱開始日時
     */
    public Date tradeStartDate;

    /**
     * (取扱終了日時)<BR>
     * 取扱終了日時
     */
    public Date tradeEndDate;

    /**
     * (応募開始日)<BR>
     * 応募開始日
     */
    public Date recruitStartDate;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (応募終了日)<BR>
     * 応募終了日
     */
    public Date recruitEndDate;

    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1:買付　@2:売却　@3:応募　@4:買付／売却
     */
    public String buySellDiv;

    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;

    /**
     * (買付単価)<BR>
     * 買付単価
     */
    public String buyPrice;

    /**
     * (売却単価)<BR>
     * 売却単価
     */
    public String sellPrice;

    /**
     * (申込単位)<BR>
     * 申込単位
     */
    public String tradeUnit;

    /**
     * (最低額面)<BR>
     * 最低額面
     */
    public String minFaceAmount;

    /**
     * (最高額面)<BR>
     * 最高額面
     */
    public String maxFaceAmount;

    /**
     * (カレンダー連携市場コード)<BR>
     * カレンダー連携市場コード
     */
    public String calendarLinkedDiv;

    /**
     * (買付受渡日移動日数)<BR>
     * 買付受渡日移動日数
     */
    public String buyDeliveryMove;

    /**
     * (売却受渡日移動日数)<BR>
     * 売却受渡日移動日数
     */
    public String sellDeliveryMove;

    /**
     * (自動約定区分)<BR>
     * 自動約定区分
     * <BR>
     * 0：非自動約定　@1：自動約定
     */
    public String autoExecDiv;

    /**
     * (自動約定枠)<BR>
     * 自動約定枠
     */
    public String autoExecLimit;

    /**
     * (カストディアンコード)<BR>
     * カストディアンコード
     */
    public String custodianCode;

    /**
     * (仲介手数料率)<BR>
     * 仲介手数料率
     */
    public String mediatorCommissionRate;

    /**
     * (仕入時の為替レート)<BR>
     * 仕入時の為替レート
     */
    public String fxRateAtStock;

    /**
     * (取引時間チェック区分)<BR>
     * 取引時間チェック区分
     */
    public String tradeTimeCheckDiv;

    /**
     * (応募勧誘形式)<BR>
     * 応募勧誘形式
     */
    public String recruitInvitationForm;

    /**
     * (応募引受け区分)<BR>
     * 応募引受け区分
     */
    public String recruitAcceptDiv;

    /**
     * @@roseuid 44E3363E02CE
     */
    public WEB3AdminBondProductUpdateInfo()
    {

    }
}
@
