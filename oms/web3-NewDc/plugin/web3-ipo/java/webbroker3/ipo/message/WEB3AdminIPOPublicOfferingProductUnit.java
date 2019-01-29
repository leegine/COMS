head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOPublicOfferingProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO公開銘柄明細メッセージデータ(WEB3AdminIPOPublicOfferingProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/01/06 坂上(SRA) 残案件対応>>>055
Revesion History : 2010/09/23 車進 (中訊) モデル No.181
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * 管理者IPO公開銘柄明細メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3AdminIPOPublicOfferingProductUnit extends Message
{
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * 公開市場コード<BR>
     * <BR>
     * 10：　@東証　@<BR>
     * 11：　@東証一部　@<BR>
     * 12：　@東証二部 <BR>　@
     * 13：　@マザーズ　@<BR>
     * 20：　@大証　@<BR>
     * 21：　@大証一部　@<BR>
     * 22：　@大証二部　@<BR>
     * 30：　@名証　@<BR>
     * 31：　@名証一部　@<BR>
     * 32：　@名証二部　@<BR>
     * 33：　@セントレックス<BR>
     * 40：　@福証　@<BR>
     * 41：　@Q-Board<BR>
     * 50：　@札証　@<BR>
     * 51：　@アンビシャス<BR>
     * 90：　@JASDAQ（スタンダード）
     * 91：　@JASDAQ（グロース）
     * <BR>
     */
    public String publicOfferingMarketCode;
    
    /**
     * ブックビルディング開始日時
     */
    public Date bookBuildingStartDate;
    
    /**
     * ブックビルディング終了日時
     */
    public Date bookBuildingEndDate;
    
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
    public Date publicOfferingPriceDetermDate;
    
    /**
     * 未定決定区分<BR>
     * <BR>
     * ０： スケジュール未定<BR>
     * １： スケジュール決定<BR>
     */
    public String undecideDecideDiv;
    
    /**
     * 抽選日
     */
    public WEB3IPOTermUnit lotDate;
    
    /**
     * 購入申込期間（当社指定）
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * 公開日
     */
    public WEB3IPOTermUnit publicOfferingDate;
    
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
     * 8：　@公開<BR>
     * <BR>
     */
    public String ipoScheduleDiv;
    
    /**
     * IPO停止<BR>
     * <BR>
     * 0：DEFAULT（開催中）<BR>
     * 1：取扱停止中<BR>
     * 2：取扱中止<BR>
     */
    public String ipoStopDiv;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C6917E0296
     */
    public WEB3AdminIPOPublicOfferingProductUnit() 
    {
     
    }
}
@
