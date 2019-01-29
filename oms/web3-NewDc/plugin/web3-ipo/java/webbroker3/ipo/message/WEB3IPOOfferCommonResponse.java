head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO購入申込共通レスポンス(WEB3IPOOfferCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2010/09/23 車進 (中訊) モデルNo.181
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO購入申込共通レスポンスクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOOfferCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408102002L;
    
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
     * 12：　@東証二部　@<BR>
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
     */
    public String publicOfferingMarketCode;
    
    /**
     * 購入申込単位
     */
    public String offerUnit;
    
    /**
     * 当選数量
     */
    public String prizeQuantity;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112E684014E
     */
    public WEB3IPOOfferCommonResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3IPOOfferCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
