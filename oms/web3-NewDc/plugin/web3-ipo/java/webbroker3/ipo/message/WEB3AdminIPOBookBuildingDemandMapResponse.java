head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingDemandMapResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾚｽﾎﾟﾝｽ(WEB3AdminIPOBookBuildingDemandMapResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingDemandMapResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingDemandMap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121059L;
    
    /**
     * 抽選対象申告件数
     */
    public String lotTargetNumber;
    
    /**
     * 平均申告価格
     */
    public String averageDemandPrice;
    
    /**
     * 仮条件区分<BR>
     * <BR>
     * １：実価格（円）<BR>
     * ２：ディスカウント率（％）<BR>
     */
    public String temporaryConditionDiv;
    
    /**
     * 申告分布明細一覧
     */
    public WEB3IPODemandDistributionUnit distributionList;
    
    /**
     * @@roseuid 4112DF8C0276
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11AFF02EF
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
