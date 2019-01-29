head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況一覧ﾚｽﾎﾟﾝｽ(WEB3AdminIPOLotResultOfferListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況一覧ﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）<BR>
     */
    public String displayUnitDiv;
    
    /**
     * 購入申込数量変更可能フラグ<BR>
     * <BR>
     * 　@true：　@購入申込数量入力可能（表示）<BR>
     * 　@false：　@購入申込数量を当選数量に固定（非表示）<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * 表示ページ番号
     */
    public String pageIndex;
    
    /**
     * (抽選結果一覧)
     */
    public WEB3IPOLotResultUnit[] lotResultList;
    
    /**
     * @@roseuid 4112DAD50017
     */
    public WEB3AdminIPOLotResultOfferListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40EE01E30233
     */
    public WEB3AdminIPOLotResultOfferListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
