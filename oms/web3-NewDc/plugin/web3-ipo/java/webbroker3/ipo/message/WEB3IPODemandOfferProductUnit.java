head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandOfferProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告購入申込銘柄明細メッセージ(WEB3IPODemandOfferProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>048
Revesion History : 2005/01/06 坂上(SRA) 残案件対応>>>055
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO申告購入申込銘柄明細メッセージクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPODemandOfferProductUnit extends Message
{
    
    /**
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * (銘柄ID)<BR>
     * IPO銘柄ID
     */
    public String ipoProductId;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 申告価格区分<BR>
     * 　@0：成行<BR>
     * 　@1：指値
     */
    public String demandPriceDiv;
    
    /**
     * 申告価格
     */
    public String demandPrice;
    
    /**
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 公開価格ディスカウント率
     */
    public String publicOfferingDiscountRate;
    
    /**
     * 公開価格決定日
     */
    public WEB3IPOTermUnit publicOfferingPriceDetermDate;
    
    /**
     * 当選数量
     */
    public String prizeQuantity;
    
    /**
     * 購入申込数量
     */
    public String offerQuantity;
    
    /**
     * 申込辞退日時
     */
    public Date offerCancelDate;
    
    /**
     * 申告相当額
     */
    public String demandEquivalentPrice;
    
    /**
     * 購入申込代金
     */
    public String offerPrice;
    
    /**
     * ブックビルディング開始日時
     */
    public Date bookBuildingStartDate;
    
    /**
     * ブックビルディング終了日時
     */
    public Date bookBuildingEndDate;
    
    /**
     * 未定決定区分<BR>
     * <BR>
     * ０： スケジュール未定<BR>
     * １： スケジュール決定<BR>
     */
    public String undecideDecideDiv;
    
    /**
     * 購入申込期間（当社指定）
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * IPOスケジュール<BR>
     * <BR>
     * 1：　@ブックビルディング開始前<BR>
     * 2：　@ブックビルディング期間中<BR>
     * 3：　@ブックビルディング終了<BR>
     * 4：　@公開価格決定<BR>
     * 5：　@抽選終了<BR>
     * 6：　@購入申込期間中<BR>
     * 7：　@購入申込終了<BR>
     * 8：　@公開
     */
    public String ipoScheduleDiv;
    
    /**
     * 申告申込状況区分<BR>
     * <BR>
     * 　@01：　@ブックビルディング申告済<BR>
     * 　@02：　@ブックビルディングキャンセル<BR>
     * 　@03：　@当選<BR>
     * 　@04：　@当選キャンセル<BR>
     * 　@05：　@申込済<BR>
     * 　@06：　@約定済<BR>
     * 　@07：　@補欠<BR>
     * 　@08：　@補欠キャンセル<BR>
     * 　@09：　@補欠申込済<BR>
     * 　@10：　@補欠約定済<BR>
     * 　@11：　@補欠落選<BR>
     * 　@12：　@落選<BR>
     */
    public String demandOfferStateDiv;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 　@0：　@一般<BR>
     * 　@1：　@特定
     */
    public String taxType;
    
    /**
     * 操作可能コード一覧<BR>
     * <BR>
     * 　@1：　@申告<BR>
     * 　@2：　@訂正・取消<BR>
     * 　@3：　@購入申込<BR>
     * 　@4：　@辞退
     */
    public String[] controlPossibleCodeList;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）
     */
    public String displayUnitDiv;
    
    /**
     * 購入申込数量変更可能フラグ<BR>
     * <BR>
     * true:購入申込数量入力可能(表示)<BR>
     * false：購入申込数量を当選数量に固定(非表示)
     */
    public boolean offerQuantityFlag;
    
    /**
     * 仮条件区分<BR>
     * <BR>
     * 1:円<BR>
     * 2:％
     */
    public String temporaryConditionDiv;
    
    /**
     * IPO停止<BR>
     * <BR>
     * 0：取扱中<BR>
     * 1：取扱停止中<BR>　@
     * 2：中止
     */
    public String ipoStopDiv;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40DC03550073
     */
    public WEB3IPODemandOfferProductUnit() 
    {
     
    }
}
@
