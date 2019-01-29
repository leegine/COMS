head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式カレンダー登録入力リクエスト(WEB3AdminFeqCalendarRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式カレンダー登録入力リクエスト)<BR>
 * 管理者外国株式カレンダー登録入力リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistInputRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_calendarRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L; 
    
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (年月)<BR>
     * 年月（YYYYMM）
     */
    public String period;
    
    /**
     * @@roseuid 42CE3A000167
     */
    public WEB3AdminFeqCalendarRegistInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）市場コード<BR>
     * <BR>
     *    this.市場コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * ２）年月<BR>
     * <BR>
     * ２－１）<BR>
     *    this.年月 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02027<BR>
     * <BR>
     * ２－２）<BR>
     *    this.年月.length() != 6<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02028<BR>
     * <BR>
     * ２－３）<BR>
     *    this.年月が日付として有りえない値<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02029<BR>
     * @@throws WEB3BaseException
     * @@roseuid 421C7286029E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）市場コード
        //   this.市場コード == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.debug("市場コードが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                getClass().getName() + STR_METHOD_NAME,
                "市場コードが未入力です。");
        }
        
        //２）年月
        //２－１）
        //   this.年月 == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.period))
        {
            log.debug("年月が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02027,
                getClass().getName() + STR_METHOD_NAME,
                "年月が未入力です。");
        }
        
        //２－２）
        //   this.年月.length() != 6
        //   の場合、例外をスローする。
        if (this.period.length() != 6)
        {
            String l_strMessage = "年月が6桁以外です.「" + this.period + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02028,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        
        //２－３）
        //   this.年月が日付として有りえない値
        //   の場合、例外をスローする。
        Date l_dat = null;
        try
        {
            l_dat = WEB3DateUtility.getDate(this.period, "yyyyMM");
        }
        catch(Exception l_ex)
        {
            String l_strMessage = "年月が日付として有りえない値です.「" + this.period + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02029,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_dat == null)
        {
            String l_strMessage = "年月が日付として有りえない値です.「" + this.period + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02029,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqCalendarRegistInputResponse(this);
    }
}
@
