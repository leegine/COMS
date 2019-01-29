head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理一覧共通リクエスト(WEB3AdminFEqConAccountOpenMngListCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座開設管理一覧共通リクエスト)<BR>
 * 外株口座開設管理一覧共通リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngListCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_common";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String[] branchCode;
    
    /**
     * (ステータス区分)<BR>
     * ステータス<BR>
     * <BR>
     * 0： 口座開設中<BR>
     * 1： 口座開設完了<BR>
     * 2： 口座開設エラー<BR>
     * 9： 削除<BR>
     * <BR>
     * ※ 全ステータス選択の場合は、null
     */
    public String statusDiv;
    
    /**
     * (申込日（自）)<BR>
     * 画面にて入力された申込日（自）<BR>
     * (YYYYMMDDhh)<BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyDateFrom;
    
    /**
     * (申込日（至）)<BR>
     * 画面にて入力された申込日（至）<BR>
     * (YYYYMMDDhh)<BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyDateTo;
    
    /**
     * @@roseuid 423552EB0271
     */
    public WEB3AdminFEqConAccountOpenMngListCommonRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngListCommonRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *   this.部店コード == null or<BR>
     *   this.部店コード.length == 0<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@ステータス区分のチェック<BR>
     * 　@未入力でない場合、以下のチェックを行う。<BR>
     * 　@２－１）　@以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・"口座開設中"<BR>
     * 　@　@・"口座開設完了"<BR>
     * 　@　@・"口座開設エラー"<BR>
     * 　@　@・"削除"<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01758<BR>
     * <BR>
     * ３）　@申込日(自)のチェック<BR>
     * 　@未入力でない場合、以下のチェックを行う。<BR>
     * 　@３－１）　@申込日（自）の日付部分(YYYYMMDDhh)が<BR>
     * 　@　@Date型に変換できなかった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01760<BR>
     * <BR>
     * ４）　@申込日(至)のチェック<BR>
     * 　@未入力でない場合、以下のチェックを行う。<BR>
     * 　@４－１）　@申込日（至）の日付部分(YYYYMMDDhh)が<BR>
     * 　@Date型に変換できなかった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01761<BR>
     * <BR>
     * ５）　@申込日(自～至)整合性のチェック<BR>
     * 　@申込日(自)、申込日(至)が共に未入力でない場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@５－１）　@申込日(自) > 申込日(至)の場合、例外をスローする。<BR>]
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01762<BR>
     * <BR>
     * 　@　@※上記比較は、文字列比較でよい。
     * @@throws WEB3BaseException
     * @@roseuid 41F8D48302E6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード
        //this.部店コード == null or 
        //this.部店コード.length == 0
        //の場合、例外をスローする。 
        if(this.branchCode == null || this.branchCode.length ==0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード == null or 部店コード.length == 0。");
        }

        //２）　@ステータス区分のチェック 
        //　@未入力でない場合、以下のチェックを行う。 
        //　@２－１）　@以下の値以外の場合、例外をスローする。 
        //　@　@・"口座開設中" 
        //　@　@・"口座開設完了" 
        //　@　@・"口座開設エラー" 
        //　@　@・"削除" 
        if(!WEB3StringTypeUtility.isEmpty(this.statusDiv))
        {
            if(!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DELETE.equals(this.statusDiv))
                )
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータス区分[" + this.statusDiv + "]。");
            }
        }

        //３）　@申込日(自)のチェック 
        //　@未入力でない場合、以下のチェックを行う。 
        //　@３－１）　@申込日（自）の日付部分(YYYYMMDDhh)が 
        //　@　@Date型に変換できなかった場合、例外をスローする。 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateFrom))
        {
			if(!WEB3StringTypeUtility.isDateStr(this.applyDateFrom.substring(0,8), "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Date型に変換できなかった。");
            }
            
        }
        
        //４）　@申込日(至)のチェック 
        //　@未入力でない場合、以下のチェックを行う。 
        //　@４－１）　@申込日（至）の日付部分(YYYYMMDDhh)が 
        //　@Date型に変換できなかった場合、例外をスローする。 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateTo))
        {
			if(!WEB3StringTypeUtility.isDateStr(this.applyDateTo.substring(0,8),"yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01761,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Date型に変換できなかった。");
            }
        }
        
        //５）　@申込日(自～至)整合性のチェック 
        //　@申込日(自)、申込日(至)が共に未入力でない場合、 
        //　@以下のチェックを行う。 
        //　@５－１）　@申込日(自) > 申込日(至)の場合、例外をスローする。 
        //　@　@※上記比較は、文字列比較でよい。 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateFrom) 
            && !WEB3StringTypeUtility.isEmpty(this.applyDateTo))
        {
            if(this.applyDateFrom.compareTo(this.applyDateTo) > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日(自)[" + this.applyDateFrom + "] > " + 
                    "申込日(至)[" + this.applyDateTo + "]。");
            }
        }
          
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423552EB02BF
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
