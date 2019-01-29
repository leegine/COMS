head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理一覧リクエスト(WEB3AdminFEqConAccountOpenMngListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外株口座開設管理一覧リクエスト)<BR>
 * 外株口座開設管理一覧リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngListRequest extends WEB3AdminFEqConAccountOpenMngListCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;
    
    /**
     * @@roseuid 423552E800CB
     */
    public WEB3AdminFEqConAccountOpenMngListRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngListRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * <BR>
     * ２）要求ページ番号<BR>
     * <BR>
     *   this.要求ページ番号 == null or<BR>
     *   this.要求ページ番号 <= 0<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）ページ内表示行数<BR>
     * <BR>
     *   this.ページ内表示行数 == null or<BR>
     *   this.ページ内表示行数 <= 0<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@roseuid 41E5E0840303
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()メソッドをコールする。 
        super.validate();
        
        //２）要求ページ番号
        //this.要求ページ番号 == null or 
        //this.要求ページ番号 <= 0
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        if(Long.parseLong(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号 <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号 <= 0");
        }

        //３）ページ内表示行数 
        //this.ページ内表示行数 == null or 
        //this.ページ内表示行数 <= 0
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未指定です。");
        }
        
        if(Long.parseLong(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数 <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数 <= 0");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座開設管理一覧レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngListResponse(this);
    }
}
@
