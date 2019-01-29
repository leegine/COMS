head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブル検索結果リクエストクラス(WEB3AdminDirSecHostTableSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・キューテーブル検索結果リクエスト)<BR>
 * 管理者・キューテーブル検索結果リクエストクラス。
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableSearchListRequest extends WEB3AdminDirSecHostTableUpdateCommonRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableSearchListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (部店コード)<BR>
     * 部店コード。
     */
    public String branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード。
     */
    public String identityCode;
    
    /**
     * (作成日付From)<BR>
     * 作成日付From(YYYYMMDDHHMM)。
     */
    public String createDateFrom;
    
    /**
     * (作成日付To)<BR>
     * 作成日付To(YYYYMMDDHHMM)。
     */
    public String createDateTo;
    
    /**
     * (ステータス)<BR>
     * ステータス。
     */
    public String status;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数。
     */
    public String pageSize;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。
     */
    public String pageIndex;
    
    /**
     * (ソートキー)
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C87008C
     */
    public WEB3AdminDirSecHostTableSearchListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR> 
     * <BR>
     * ２）部店コードチェック <BR>
     * 　@２－１）　@this.部店コード == nullの場合、<BR> 
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * ３）検索条件チェック<BR>
     * 　@(this.部店コード != null && (this,識別コード == null <BR>
     * 　@&& this,作成日付From == null && this.作成日付To == null <BR>
     * 　@&& this.ステータス == null))の場合、例外をスロー<BR>
     * 　@　@　@　@　@「検索条件には部店コードの他に、1つ以上入力してください。」<BR>
     * 　@　@　@　@　@の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02426<BR>
     * <BR>
     * ４）ページ内表示行数チェック <BR>
     * 　@４－１）this.ページ内表示行数 == nullの場合、<BR> 
     * 　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@４－２）this.ページ内表示行数が数字以外の値であった場合、<BR> 
     * 　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@４－３）this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     * <BR>
     * ５）表示番号チェック<BR>
     * 　@５－１）this.表示ページ番号 == nullの場合、<BR> 
     * 　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@５－２）this.表示ページ番号が数字以外の値であった場合、<BR> 
     * 　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@５－３）this.表示ページ番号 <= 0であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR> 
     * <BR>
     * ６）　@ソートキーチェック<BR> 
     * 　@６－１）this.キューテーブルソートキー == nullであった場合<BR> 
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@６－２）this.キューテーブルソートキー.length == 0だった場合<BR> 
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@６－３）this.キューテーブルソートキーの全要素に対して<BR> 
     * 　@　@　@　@下記のチェックを行う。 <BR>
     * 　@　@６－３－１）キューテーブルソートキー.validate()をコールする。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 441652B402BF
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()をコールする。  
        super.validate();
        
        //２）部店コードチェック  
        //　@２－１）　@this.部店コード == nullの場合、  
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。  
        if (this.branchCode == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02174, 
                this.getClass().getName() + STR_METHOD_NAME, "部店コードがnullです。");
        }
        
        //３）検索条件チェック 
        //　@(this.部店コード != null && (this,識別コード == null && this,作成日付From == null &&  
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@this.作成日付To == null && this.ステータス == null))の場合、例外をスロー 
        //　@　@　@　@　@「検索条件には部店コードの他に、1つ以上入力してください。」の例外をスローする。 
        if (this.branchCode != null && (this.identityCode == null && this.createDateFrom == null 
            && this.createDateTo == null && this.status == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02426, 
                this.getClass().getName() + STR_METHOD_NAME, "検索条件には部店コードの他に、1つ以上入力してください。");
        }
        
        //４）ページ内表示行数チェック  
        //　@４－１）this.ページ内表示行数 == nullの場合、  
        //　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。  
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数の入力が不正です。");
        }
        
        //　@４－２）this.ページ内表示行数が数字以外の値であった場合、  
        //　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。  
        if (!WEB3StringTypeUtility.isNumber(this.pageSize)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数が数字以外の値です。");     
        }
        
        //　@４－３）this.ページ内表示行数 <= 0であった場合、  
        //　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。  
        if (Integer.parseInt(this.pageSize) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数の値が0以下です。");     
        }
        
        //５）表示番号チェック 
        //　@５－１）this.表示ページ番号 == nullの場合、  
        //　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。 
        if (this.pageIndex == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog. BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号が未指定です。");     
        }
        
        //　@５－２）this.表示ページ番号が数字以外の値であった場合、  
        //　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。  
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号が数字以外の値です。");     
        }
        
        //　@５－３）this.表示ページ番号 <= 0であった場合、  
        //　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。  
        if (Integer.parseInt(this.pageIndex) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号の値が0以下です。");     
        }
        
        //６）　@ソートキーチェック  
        //　@６－１）this.キューテーブルソートキー == nullであった場合  
        //　@　@　@　@「ソートキーがnull」の例外をスローする。  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーが未指定です。");     
        }
        
        //　@６－２）this.キューテーブルソートキー.length == 0だった場合  
        //　@　@　@　@「ソートキー.要素数が0」の例外をスローする。  
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーの要素数が０です。");     
        }
        
        //　@６－３）this.キューテーブルソートキーの全要素に対して  
        //　@　@　@　@下記のチェックを行う。  
        //　@　@６－３－１）キューテーブルソートキー.validate()をコールする。 
        for (int i = 0 ; i < l_intsortKeysLen ; i++) 
        {
            sortKeys[i].validate();    
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
        return new WEB3AdminDirSecHostTableSearchListResponse(this);
    }

}
@
