head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当共通レスポンス(WEB3IPOLotCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者IPO抽選割当共通レスポンス)<BR>
 * 管理者IPO抽選割当共通レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3IPOLotCommonResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_lotCommon";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200512192100L;
    
    /**
     * (銘柄コード)<BR>
     * IPO銘柄コード。
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * IPO銘柄名。
     */
    public String productName;
    
    /**
     * (抽選区分)<BR>
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * １：新規抽選<BR>
     * ２：繰上抽選
     */
    public String lotDiv;
    
    /**
     * @@roseuid 4112E44A0271
     */
    public WEB3IPOLotCommonResponse() 
    {
     
    }
    
    /**
     * (管理者IPO抽選割当共通レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40DC03E50071
     */
    public WEB3IPOLotCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
