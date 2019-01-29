head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotResultUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果明細メッセージデータ(WEB3IPOLotResultUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/12/20 鄭徳懇 (中訊) 仕様変更No.105修正
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 抽選結果明細メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOLotResultUnit extends Message
{
    
    /**
     * 部店コード
     */
    public String branchCode;
    
    /**
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * 顧客名
     */
    public String accountName;
    
    /**
     * 抽選結果区分<BR>
     * <BR>
     * 1：　@当選<BR>
     * 2：　@補欠<BR>
     * 3：　@落選<BR>
     * 21：　@補欠当選<BR>
     * 23：　@補欠落選
     */
    public String lotResultDiv;
    
    /**
     * 当選数量
     */
    public String prizeQuantity;
    
    /**
     * 購入申込数量
     */
    public String offerQuantity;
    
    /**
     * 購入申込辞退日時
     */
    public Date offerCancelDate;
    
    /**
     * 購入申込状況区分<BR>
     * <BR>
     * 1：　@購入申込<BR>
     * 2：　@辞退
     */
    public String offerStateDiv;
    
    /**
     * 受付状態区分<BR>
     * <BR>
     * 0：　@DEFAULT（----）<BR>
     * 1：　@SONAR送信済（受付中）<BR>
     * 2：　@SONAR反映済（受付済）
     */
    public String receiveStateDiv;
    
    /**
     * 優先順位
     */
    public String priority;
    
    /**
     * 扱者コード
     */
    public String traderCode;

    /**
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 信用区分
     */
    public String marginDiv;
    
    /**
     * 抽選番号
     */
    public String lotNumber;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40EE26B50009
     */
    public WEB3IPOLotResultUnit() 
    {
     
    }
}
@
