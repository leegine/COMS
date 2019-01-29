head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客問合せ一覧リクエスト(WEB3AdminAccInfoLockAccountSearchListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoSearchCondTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報ロック顧客問合せ一覧リクエスト)<BR>
 * 管理者お客様情報ロック顧客問合せ一覧リクエスト<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchListRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountSearchListRequest.class); 
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;
    
    /**
     * (登録日（自）)<BR>
     * 登録日（自）<BR>
     */
    public Date registDateFrom;
    
    /**
     * (登録日（至）)<BR>
     * 登録日（至）<BR>
     */
    public Date registDateTo;
    
    /**
     * (検索条件区分)<BR>
     * 検索条件区分<BR>
     */
    public String searchCondType;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    
    /**
     * (管理者お客様情報ロック顧客問合せ一覧リクエスト)<BR>
     */
    public WEB3AdminAccInfoLockAccountSearchListRequest()
    {

    }
     
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoLockAccountSearchListResponse(this);
    }
     
    /**
     * validate<BR>
     * １）　@部店コードのチェック <BR>
     *      １－１）未入力の場合、エラーをスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>    
     * <BR>
     * ２）　@登録日（自）、登録日（至）のチェック <BR>
     * 　@　@　@２－１）　@どちらか一方のみの入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02221<BR>   
     * 　@　@　@　@　@　@※両方未入力か、両方入力されていなければならない。 <BR>
     * 　@　@　@２－２）登録日（自）＞登録日（至）の場合、エラーをスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02222<BR>   
     * 　@　@　@　@　@　@※登録日（自）、登録日（至）の両方に入力がある場合のみチェックする。<BR> 
     * <BR>
     * ３）　@検索条件区分のチェック <BR>
     * 　@　@　@３－１）検索条件区分に入力があり、 <BR>
     * 　@　@　@　@　@　@　@不正なコード値の場合は例外をスローする。 <BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02223<BR>   
     * <BR>
     * ４）　@要求ページ番号チェック  <BR>
     * 　@　@　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。  <BR>
     * 　@　@　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>   
     * 　@　@　@４－３）　@マイナス値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>   
     * <BR>
     * ５）　@ページ内表示行数チェック  <BR>
     * 　@　@　@５－１）　@未入力の場合、例外をスローする。  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02224<BR>   
     * 　@　@　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>   
     * 　@　@　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>   
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);         
        //１）　@部店コードのチェック <BR>
        //１－１）未入力の場合、エラーをスローする。
        if (branchCode == null || branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //２）　@登録日（自）、登録日（至）のチェック
        //２－１）　@どちらか一方のみの入力の場合、例外をスローする。
        if ((registDateFrom == null && registDateTo != null) || (registDateFrom != null && registDateTo == null))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02221, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "登録日（自）と登録日（至）両方を未入力か、同時に入力しなければならない。");
        }
        
        //２－２）登録日（自）＞登録日（至）の場合、エラーをスローする。
        if (WEB3DateUtility.compare(registDateFrom, registDateTo) == 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02222, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "登録日（自）が登録日（至）を超えています。");
        }
        
        //３）　@検索条件区分のチェック
        //３－１）検索条件区分に入力があり、不正なコード値の場合は例外をスローする。
        if (!WEB3AccInfoSearchCondTypeDef.ALL.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(searchCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02223, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "検索条件区分が不正なコード値です。");
        }
        
        
        //４）　@要求ページ番号チェック 
        //４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (WEB3StringTypeUtility.isEmpty(pageIndex))
        {
            this.pageIndex = "1";
        }
        //４－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "要求ページ番号が数字以外の値です。");
        }
        //４－３）　@マイナス値の場合、例外をスローする。
        long l_lngPageIndex = Long.parseLong(pageIndex);
        if (l_lngPageIndex < 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "要求ページ番号の値が0以下です。");
        }
        
        //５）　@ページ内表示行数チェック
        //５－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02224, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ページ内表示行数が未入力です。");
        }
        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ページ内表示行数が数字以外の値です。");
        }
        
        //５－３）　@マイナス値の場合、例外をスローする。
        long l_lngPageSize = Long.parseLong(pageSize);
        if (l_lngPageSize < 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ページ内表示行数の値が0以下です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
