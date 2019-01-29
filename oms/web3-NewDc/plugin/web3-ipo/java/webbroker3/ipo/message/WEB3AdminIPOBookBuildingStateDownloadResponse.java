head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminIPOBookBuildingStateDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingStateDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121015L;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * 抽選対象申告件数
     */
    public String lotTargetNumber;
    
    /**
     * 取消件数
     */
    public String cancelNumber;
    
    /**
     * 全申告件数
     */
    public String allDemandNumber;
    
    /**
     * 平均申告価格
     */
    public String averageDemandPrice;
    
    /**
     * 抽選対象申告数量
     */
    public String lotTargetQuantity;
    
    /**
     * 取消数量
     */
    public String cancelQuantity;
    
    /**
     * 全申告数量
     */
    public String allDemandQuantity;
    
    /**
     * 出金余力保持者合計人数
     */
    public String paymentPowerHolderTotalNumber;
    
    /**
     * 出金余力保持者合計数量
     */
    public String paymentPowerHolderTotalQuantity;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）<BR>
     */
    public String displayUnitDiv;
    
    /**
     * 仮条件区分<BR>
     * <BR>
     * １：実価格（円）<BR>
     * ２：ディスカウント率（％）<BR>
     */
    public String temporaryConditionDiv;
    
    /**
     * @@roseuid 4112DAD602E0
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11B100272
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
