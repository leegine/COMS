head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却入力リクエスト(WEB3BondSellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券売却入力リクエスト)<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellInputRequest extends WEB3GenRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellInputRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellInput";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * 保有資産ID<BR>
     */
    public String id;
    
    /**
     * @@roseuid 44FBFD3902DE
     */
    public WEB3BondSellInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@ＩＤチェック<BR> 
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_01919<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44BF27C4005A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@ＩＤチェック<BR> 
        // 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産IDが未指定(null)です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondSellInputResponse(this);
    }
}
@
