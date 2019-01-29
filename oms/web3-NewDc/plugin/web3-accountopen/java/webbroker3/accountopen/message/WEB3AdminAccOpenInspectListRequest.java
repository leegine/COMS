head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査一覧リクエスト(WEB3AdminAccOpenInspectListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 張秋穎　@(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者口座開設審査一覧リクエスト)<BR>
 * 管理者口座開設審査一覧リクエスト
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectList";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenInspectListRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     * <BR>
     * 未入力の場合、全部店<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     * <BR>
     * 前方一致で検索可能<BR>
     */
    public String accountCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     * <BR>
     * 前方一致で検索可能
     */
    public String requestNumber;
    
    /**
     * (発生日（自）)<BR>
     * 発生日（自）<BR>
     * <BR>
     * YYYYMMDD形式
     */
    public Date occurredDateFrom;
    
    /**
     * (発生日（至）)<BR>
     * 発生日（至）<BR>
     * <BR>
     * YYYYMMDD形式
     */
    public Date occurredDateTo;
    
    /**
     * (審査種別)<BR>
     * 審査種別<BR>
     * <BR>
     * 0：全て　@1：同一客　@2：同一客（見込）　@3：Y客
     */
    public String reviewCode;
    
    /**
     * (審査担当者コード)<BR>
     * 審査担当者コード<BR>
     * <BR>
     * 前方一致での検索可能
     */
    public String checkerCode;
    
    /**
     * (審査状況)<BR>
     * 審査状況<BR>
     * <BR>
     * 0：全て　@1：審査待ち　@2：審査済み　@3：承認済　@4：否認済
     */
    public String checkStatus;
    
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
     * (口座開設ソートキー)<BR>
     */  
    public WEB3AccOpenSortKey[] sortKeys;
    
    /**
     * @@roseuid 44912C11008C
     */
    public WEB3AdminAccOpenInspectListRequest() 
    {
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@発生日（自），発生日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@１－１）　@（自） > （至）であれば、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02464<BR>
     * <BR>
     * <BR>
     * ２）　@部店コードのチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@２－１）　@半角数字以外が含まれる場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * <BR>
     * ３）　@顧客コードのチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@３－１）　@半角数字以外が含まれる場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_01331 <BR>
     * <BR>
     * <BR>
     * ４）　@識別コードのチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@４－１）　@半角数字以外が含まれる場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_01820 <BR>
     * <BR>
     * <BR>
     * ５）　@審査担当者コードのチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@５－１）　@半角英数字以外が含まれる場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02463 <BR>
     * <BR>
     * <BR>
     * ６）　@要求ページ番号チェック <BR>
     * 　@６－１）　@未入力の場合、 要求ページ番号に”１”をセットする。<BR> 
     * 　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00090 <BR>
     * <BR>
     * 　@６－３）　@マイナス値の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00616 <BR>
     * <BR>
     * <BR>
     * ７）　@ページ内表示行数チェック <BR>
     * 　@７－１）　@未入力の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02224 <BR>
     * <BR>
     * 　@７－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00092 <BR>
     * <BR>
     * 　@７－３）　@マイナス値の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00617 <BR>
     * <BR>
     * ８）　@ソートキーのチェック <BR> 
     * 　@８－１）　@ソートキーが未入力lの場合、例外をスローする。<BR> 
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00231 <BR> 
     * <BR>
     * 　@８－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR> 
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00232 <BR> 
     * <BR> 
     * 　@８－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@８－３－１）　@ソートキー.validate()をコールする。  <BR>
     * 　@　@　@８－３－２）　@ソートキー.キー項目が下記の項目以外の場合、 例外をスローする。<BR>  
     *　@　@　@　@　@識別コード（requestNumber） <BR>
     *　@　@　@　@　@発生日時（occurredDate） <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00086 <BR> 
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 4472BD1B0158
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@発生日（自），発生日（至）のチェック
        //　@※（自），（至）の両方に入力がある場合のみ
        //　@１－１）　@（自） > （至）であれば、例外をスローする。
        if (this.occurredDateFrom != null && this.occurredDateTo != null)
        {
        	if(WEB3DateUtility.compare(this.occurredDateFrom, this.occurredDateTo) > 0)
        	{
        		log.debug("発生日（自）は発生日（至）より大きいです。");
        		log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02464,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発生日（自）は発生日（至）より大きいです。");
        	}
        }
        
        //２）　@部店コードのチェック
        //　@※入力がある場合のみ
        //　@２－１）　@半角数字以外が含まれる場合、例外をスローする。
        if (this.branchCode != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.branchCode)
                || !WEB3StringTypeUtility.isDigit(this.branchCode))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    getClass().getName() + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }
        }
        
        //３）　@顧客コードのチェック
        //　@※入力がある場合のみ
        //　@３－１）　@半角数字以外が含まれる場合、例外をスローする。
        if (this.accountCode != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.accountCode)
                || !WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("顧客コード（至）の値が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                    getClass().getName() + STR_METHOD_NAME,
                    "顧客コード（至）の値が数字以外の値です。");
            }
        }
        
        //４）　@識別コードのチェック
        //　@※入力がある場合のみ
        //　@４－１）　@半角数字以外が含まれる場合、例外をスローする。
        if (this.requestNumber != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.requestNumber)
                || !WEB3StringTypeUtility.isDigit(this.requestNumber))
        	{
        		log.debug("識別コードの値が半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                	WEB3ErrorCatalog.BUSINESS_ERROR_01820,
                    getClass().getName() + STR_METHOD_NAME,
                    "識別コードの値が半角数字以外の値です。");
            }
        }
        
        //５）　@審査担当者コードのチェック
        //　@※入力がある場合のみ
        //　@５－１）　@半角英数字以外が含まれる場合、例外をスローする。
        if (this.checkerCode != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.checkerCode)
        		|| !WEB3StringTypeUtility.isLetterOrDigit(this.checkerCode))
            {
            	log.debug("審査担当者コードの値が半角英数字以外の値です。");
            	log.exiting(STR_METHOD_NAME);
            	throw new WEB3BusinessLayerException(
            	    WEB3ErrorCatalog.BUSINESS_ERROR_02463,
                    getClass().getName() + STR_METHOD_NAME,
                    "審査担当者コードの値が半角英数字以外の値です。");
            }
        }
        
        //６）　@要求ページ番号チェック 
        //　@６－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 
        if (this.pageIndex == null || this.pageIndex.length() == 0)
        {
        	this.pageIndex = "1";
        }
        
        //　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
        	log.debug("要求ページ番号が数字以外の値です。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //　@６－３）　@マイナス値の場合、例外をスローする。
        if (WEB3StringTypeUtility.isMinus(this.pageIndex))
        {
        	log.debug("要求ページ番号の値が0以下です。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //７）　@ページ内表示行数チェック 
        //　@７－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
        	log.debug("ページ内表示行数が未入力です。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        //　@７－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
        	log.debug("ページ内表示行数が数字以外の値です。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //　@７－３）　@マイナス値の場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
        	log.debug("ページ内表示行数の値が0以下です。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        
        //８）　@ソートキーのチェック  
        //８－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //８－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //８－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            
            //８－３－１）　@ソートキー.validate()をコールする。
            l_accOpenSortKey.validate();
            
            //８－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            //識別コード（requestNumber） 
            //発生日時（occurredDate）
            if (!(WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_accOpenSortKey.keyItem)))  
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    "キー項目に識別コード、発生日時の項目名以外の値が存在しています。");         
            }
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
        return new WEB3AdminAccOpenInspectListResponse(this);
    }

}
@
