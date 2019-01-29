head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報照会明細(WEB3AdminEqAttentionInfoRefDetail.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 孟亞南 (中訊) 新規作成 モデルNo.217
Revision History : 2009/02/11 李玉玲 (中訊) 仕様変更 モデルNo.235
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (注意情報照会明細)<BR>
 * 注意情報照会明細クラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefDetail extends Message
{
    /**
     * (注意情報種別)<BR>
     * 注意情報種別<BR>
     */
    public String attentionInfoType;

    /**
     * (注意情報区分コード)<BR>
     * 注意情報区分<BR>
     */
    public String attentionInfoDivCode;

    /**
     * (情報発生日時)<BR>
     * 情報発生日時<BR>
     * <BR>
     */
    public Date infoOccuredDate;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;

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
     * (処理結果区分)<BR>
     * 処理結果区分<BR>
     */
    public String attentionInfoProcResDiv;

    /**
     * (有効日)<BR>
     * 有効日<BR>
     */
    public Date validDate;

    /**
     * (注文受付再開日時（予定）)<BR>
     * 注文受付再開日時（予定）<BR>
     */
    public Date orderAcceptResumeScheduledDate;

    /**
     * (売買停止・再開日時)<BR>
     * 売買停止・再開日時<BR>
     */
    public Date buySellSuspendResumeDate;

    /**
     * (基準値（変更前）)<BR>
     * 基準値（変更前）<BR>
     */
    public String befChgBasePrice;

    /**
     * (基準値（変更後）)<BR>
     * 基準値（変更後）<BR>
     */
    public String aftChgBasePrice;

    /**
     * (制限値幅上限（変更前）)<BR>
     * 制限値幅上限（変更前）<BR>
     */
    public String befChgUpperPriceRange;

    /**
     * (制限値幅上限（変更後）)<BR>
     * 制限値幅上限（変更後）<BR>
     */
    public String aftChgUpperPriceRange;

    /**
     * (制限値幅下限（変更前）)<BR>
     * 制限値幅下限（変更前）<BR>
     */
    public String befChgLowerPriceRange;

    /**
     * (制限値幅下限（変更後）)<BR>
     * 制限値幅下限（変更後）<BR>
     */
    public String aftChgLowerPriceRange;

    /**
     * (評価単価（変更前）)<BR>
     * 評価単価（変更前）<BR>
     */
    public String befChgEvaluationPrice;

    /**
     * (評価単価（変更後）)<BR>
     * 評価単価（変更後）<BR>
     */
    public String aftChgEvaluationPrice;

    /**
     * (値幅チェック区分（変更前）)<BR>
     * 値幅チェック区分（変更前）<BR>
     */
    public String befChgPriceRangeCheckDiv;

    /**
     * (値幅チェック区分（変更後）)<BR>
     * 値幅チェック区分（変更後）<BR>
     */
    public String aftChgPriceRangeCheckDiv;

    /**
     * (値幅区分（変更前）)<BR>
     * 値幅区分（変更前）<BR>
     */
    public String befChgPriceRangeDiv;

    /**
     * (値幅区分（変更後）)<BR>
     * 値幅区分（変更後）<BR>
     */
    public String aftChgPriceRangeDiv;

    /**
     * (基準値（終値）（変更前）)<BR>
     * 基準値（終値）（変更前）<BR>
     */
    public String befChgLastClosingPrice;

    /**
     * (基準値（終値）（変更後）)<BR>
     * 基準値（終値）（変更後）<BR>
     */
    public String aftChgLastClosingPrice;

    /**
     * (基準値（updq）（終値）（変更前）)<BR>
     * 基準値（updq）（終値）（変更前）<BR>
     */
    public String befChgLastClosingPriceUpdq;

    /**
     * (基準値（updq）（終値）（変更後）)<BR>
     * 基準値（updq）（終値）（変更後）<BR>
     */
    public String aftChgLastClosingPriceUpdq;

    /**
     * (基準値（updq）（変更前）)<BR>
     * 基準値（updq）（変更前）<BR>
     */
    public String befChgBasePriceUpdq;

    /**
     * (基準値（updq）（変更後）)<BR>
     * 基準値（updq）（変更後）<BR>
     */
    public String aftChgBasePriceUpdq;

    /**
     * (表題)<BR>
     * 表題<BR>
     */
    public String title;

    /**
     * (本文)<BR>
     * 本文<BR>
     */
    public String text;

    /**
     * @@roseuid 49588AF0038A
     */
    public WEB3AdminEqAttentionInfoRefDetail()
    {

    }
}
@
