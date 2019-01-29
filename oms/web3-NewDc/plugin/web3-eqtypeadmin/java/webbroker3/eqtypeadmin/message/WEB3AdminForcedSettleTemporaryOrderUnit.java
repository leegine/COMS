head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleTemporaryOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済注文照会情報(WEB3AdminForcedSettletemporaryOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 趙林鵬 (中訊) 新規作成
Revesion History : 2007/07/24 何文敏 (中訊) 新規作成　@仕様変更モデルNo.159
Revesion History : 2007/08/27 柴双紅 (中訊) 仕様変更モデルNo.163
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (強制決済注文照会情報)<BR>
 * 強制決済注文照会情報クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleTemporaryOrderUnit extends Message
{
    
    /**
     * 注文ID<BR>
     */
    public String id;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     */
    public String taxType;
    
    /**
     * (建区分)<BR>
     * 建区分<BR>
     * <BR>
     * 1：　@買建<BR>
     * 2：　@売建<BR>
     */
    public String contractType;
    
    /**
     * (建日)<BR>
     * 建日<BR>
     */
    public Date openDate;
    
    /**
     * (決済期日)<BR>
     * 決済期日<BR>
     */
    public Date closeDate;
    
    /**
     * (建株数)<BR>
     * 建株数<BR>
     */
    public String contractQuantity;
    
    /**
     * (建単価)<BR>
     * 建単価<BR>
     */
    public String contractPrice;
    
    /**
     * (建代金)<BR>
     * 建代金<BR>
     */
    public String contractExecPrice;

    /**
     * (保証金預託率)<BR>
     * 保証金預託率<BR>
     */
    public String marginCollateralRate;

    /**
     * (追証発生日)<BR>
     * 追証発生日<BR>
     */
    public Date additionalOccurredDate;

    /**
     * (追証経過日数)<BR>
     * 追証経過日数<BR>
     */
    public String additionalElapsedDays;

    /**
     * (弁済区分)<BR>
     * 弁済区分<BR>
     * <BR>
     * 1：　@制度信用<BR>
     * 2：　@一般信用<BR>
     */
    public String repaymentDiv;
    
    /**
     * (弁済期限値)<BR>
     * 弁済期限値<BR>
     */
    public String repaymentTimeLimit;
    
    /**
     * (注文株数)<BR>
     * 注文株数<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：　@成行<BR>
     * 1：　@指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String orderPrice;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (作成日時)<BR>
     * 作成日時<BR>
     */
    public Date createDate;
    
    /**
     * (（非）承認日時)<BR>
     * （非）承認日時<BR>
     */
    public Date approveDate;
    
    /**
     * (承認状態)<BR>
     * 承認状態<BR>
     * <BR>
     * 0：　@未承認<BR>
     * 1：　@承認済<BR>
     * 2：　@非承認<BR>
     * 9：　@エラー<BR>
     */
    public String approveState;
    
    /**
     * (承認者コード)<BR>
     * 承認者コード<BR>
     */
    public String checker;
    
    /**
     * (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * <BR>
     * 0005：　@建株残高不足エラー<BR>
     * 0006：　@売買停止銘柄エラー<BR>
     * 0016：　@決済期日到来済エラー<BR>
     * 0017：　@現引・現渡注文登録済エラー<BR>
     * 9001：　@その他エラー<BR>
     */
    public String errorReason;
    
    /**
     * (基準評価額超過フラグ)<BR>
     * 基準評価額超過フラグ<BR>
     * <BR>
     * false：　@超過なし<BR>
     * true：　@基準評価額を超過<BR>
     */
    public boolean baseAssetOverFlag;

    /**
     * (概算評価額)<BR>
     * 概算評価額<BR>
     */
    public String estimatedAsset;

    /**
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     */
    public WEB3AdminForcedSettleReasonUnit forcedSettleReason;
    
    /**
     * @@roseuid 462CA4290341
     */
    public WEB3AdminForcedSettleTemporaryOrderUnit() 
    {
     
    }
}
@
