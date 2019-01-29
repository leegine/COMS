head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部情報一覧リクエスト(WEB3AdminAccInfoInsiderInfoListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者お客様情報内部情報一覧リクエスト)<BR>
 * 管理者お客様情報内部者情報一覧リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListRequest.class);
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
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
     * お客様情報ソートキー<BR>
     * 対象項目：<BR>
     *     部店コード、<BR>
     *     顧客コード、<BR>
     *     銘柄コード<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    
    /**
     * (validate)<BR>
     *リクエストデータの整合性チェックを行う。<BR> 
     *<BR>
     *１）　@部店コードのチェック<BR>
     *　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *　@１－２）　@要素数が0の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     *　@１－３）　@各要素について、以下のチェックを行う。<BR>
     *　@　@１－３－１）　@桁数が3でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *　@　@１－３－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     *<BR>
     *２）　@顧客コードのチェック<BR>
     *　@未入力でない場合、以下のチェックを行う。<BR>
     *　@２－１）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     *<BR>
     *３）　@銘柄コードのチェック<BR>
     *　@未入力でない場合、以下のチェックを行う。<BR>
     *　@３－１）　@桁数が5でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     *　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00815<BR>
     *<BR>
     *４）　@ソートキーのチェック  <BR>
     *　@４－１）　@ソートキーが未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *　@４－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     *　@４－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。<BR>  
     *　@　@　@４－３－１）　@ソートキー.validate()をコールする。<BR> 
     *　@　@　@４－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     *　@　@　@　@ 内部者情報一覧Unit.部店コード<BR>
     *　@　@　@　@ 内部者情報一覧Unit.顧客コード<BR>
     *　@　@　@　@ 内部者情報一覧Unit.銘柄コード<BR>
     *　@　@　@　@ 内部者情報一覧Unit.関係コード<BR>
     *　@　@　@　@ 内部者情報一覧Unit.役員名<BR>
     *　@　@　@　@ 内部者情報一覧Unit.役職名<BR>
     *　@　@　@　@ 内部者情報一覧Unit.登録状況区分<BR>
     *<BR>
     *５）　@要求ページ番号チェック <BR>
     *　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     *　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     *　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     *<BR>
     *６）　@ページ内表示行数チェック <BR>
     *　@６－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     *　@６－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //部店コード未入力の場合、例外をスローする。
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コード未入力");  
        }
        
        //１－２）　@要素数が0の場合、例外をスローする。<BR>
        if (branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コード要素数が0の場合");
        }
        
        //*　@１－３）　@各要素について、以下のチェックを行う。<BR>
        for (int i = 0; i < branchCode.length; i++)
        {
            if (WEB3StringTypeUtility.getByteLength(branchCode[i]) != 3)
            {
                
                //*　@　@１－３－１）　@桁数が3でない場合、例外をスローする。<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コード桁数が3でない");
            }
            
            //１－３－２）　@数字以外の文字が含まれる場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(branchCode[i]))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コード数字以外の文字");
            }
        }
        
        //２）　@顧客コードのチェック<BR>
        //     未入力でない場合、以下のチェックを行う。<BR>
        //     ２－１）　@桁数が6でない場合、例外をスローする。<BR>
        if (accountCode != null && WEB3StringTypeUtility.getByteLength(accountCode) != 6)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード未入力でない場合,桁数が6でない場合");
        }
        
        //*　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
        if (accountCode != null && !WEB3StringTypeUtility.isDigit(accountCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード未入力でない場合,数字以外の文字が含まれる場合");
        }
        
        //３）　@銘柄コードのチェック<BR>
        //　@未入力でない場合、以下のチェックを行う。<BR>
        //　@３－１）　@桁数が5でない場合、例外をスローする。<BR>
        if (productCode != null && WEB3StringTypeUtility.getByteLength(productCode) != 5)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00439, 
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コード未入力でない場合,桁数が5でない場合");
        }
        
        //　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
        if (productCode != null && !WEB3StringTypeUtility.isDigit(productCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00815, 
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コード未入力でない場合,数字以外の文字が含まれる場合");
        }
        
        //４）　@ソートキーのチェック  <BR>
        //　@４－１）　@ソートキーが未入力の場合、例外をスローする。<BR>
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未入力の場合");
        }
        
        //　@４－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
        if (sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME,
                "（ソートキーの要素数 == 0）の場合");
        }
        
        //　@４－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。<BR>  
        //　@　@　@４－３－１）　@ソートキー.validate()をコールする。<BR> 
        //　@　@　@４－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。<BR>
        //　@　@　@　@ 内部者情報一覧Unit.部店コード<BR>
        //　@　@　@　@ 内部者情報一覧Unit.顧客コード<BR>
        //　@　@　@　@ 内部者情報一覧Unit.銘柄コード<BR>
        //　@　@　@　@ 内部者情報一覧Unit.関係コード<BR>
        //　@　@　@　@ 内部者情報一覧Unit.役員名<BR>
        //　@　@　@　@ 内部者情報一覧Unit.役職名<BR>
        //　@　@　@　@ 内部者情報一覧Unit.登録状況区分<BR>
        for (int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            
            if(!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.PRODUCT_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.RELATION_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.OFFICER_NAME.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.POST_NAME.equals(sortKeys[i].keyItem) &&
                !WEB3AccInfoKeyItemDef.REGIST_DIV.equals(sortKeys[i].keyItem)
                )
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキー.キー項目が下記の項目名以外の場合");
            }
        }
        
        //５）　@要求ページ番号チェック <BR>
        //　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
            log.debug("要求ページ番号 = " + this.pageIndex);
        }
        
        //　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。<BR> 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号数字以外の文字が含まれる");
        }
        
        //　@５－３）　@マイナス値の場合、例外をスローする。<BR>
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号マイナス値");
        }
        
        //６）　@ページ内表示行数チェック <BR>
        //　@６－１）　@未入力の場合、例外をスローする。 <BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数未入力");
        }
        
        //　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数数字以外の文字が含まれる");
        }
        
        //　@６－３）　@マイナス値の場合、例外をスローする。<BR>
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数マイナス値");
        }
        
    }

    /* (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoInsiderInfoListResponse(this);
    }
}
@
