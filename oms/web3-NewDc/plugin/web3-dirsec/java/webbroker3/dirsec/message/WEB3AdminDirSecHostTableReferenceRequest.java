head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブル一覧リクエストクラス(WEB3AdminDirSecHostTableReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・キューテーブル一覧リクエスト)<BR>
 * 管理者・キューテーブル一覧リクエストクラス。<BR>
 *
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableReferenceRequest extends WEB3GenRequest 
{   
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数。<BR>
     */
    public String pageSize;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;
    
    /**
     * (ソートキー)<BR>
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C86009C
     */
    public WEB3AdminDirSecHostTableReferenceRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）ページ内表示行数チェック<BR> 
     * 　@１－１）this.ページ内表示行数 == nullの場合、<BR> 
     * 　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR> 
     * <BR>
     * 　@１－２）this.ページ内表示行数が数字以外の値であった場合、<BR> 
     * 　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>  
     * <BR>
     * 　@１－３）this.ページ内表示行数 <= 0であった場合、<BR> 
     * 　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     * <BR>
     * ２）表示番号チェック<BR>
     * 　@２－１）this.表示ページ番号 == nullの場合、<BR> 
     * 　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR> 
     * <BR>
     * 　@２－２）this.表示ページ番号が数字以外の値であった場合、<BR> 
     * 　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>  
     * <BR>
     * 　@２－３）this.表示ページ番号 <= 0であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>  
     * <BR>
     * <BR>
     * ３）　@ソートキーチェック<BR> 
     * 　@３－１）this.キューテーブルソートキー == nullであった場合<BR> 
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR> 
     * <BR>
     * 　@３－２）this.キューテーブルソートキー.length == 0だった場合<BR> 
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR> 
     * <BR>
     * 　@３－３）this.キューテーブルソートキーの全要素に対して <BR>
     * 　@　@　@　@下記のチェックを行う。 <BR>
     * 　@　@３－３－１）キューテーブルソートキー.validate()をコールする。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44164B0F0020
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ページ内表示行数チェック  
        //　@１－１）this.ページ内表示行数 == nullの場合、  
        //　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。 
        if (this.pageSize == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数の入力が不正です。");     
        }
        
        //　@１－２）this.ページ内表示行数が数字以外の値であった場合、  
        //　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数が数字以外の値です。");     
        }
        
        //　@１－３）this.ページ内表示行数 <= 0であった場合、  
        //　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数の値が0以下です。");     
        }
        
        //２）表示番号チェック 
        //　@２－１）this.表示ページ番号 == nullの場合、  
        //　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。
        if (this.pageIndex == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog. BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号が未指定です。");     
        }
        
        //　@２－２）this.表示ページ番号が数字以外の値であった場合、  
        //　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。  
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号が数字以外の値です。");     
        }
        
        //　@２－３）this.表示ページ番号 <= 0であった場合、  
        //　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。  
        if (Integer.parseInt(this.pageIndex) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号の値が0以下です。");     
        }
        
        //３）　@ソートキーチェック  
        //　@３－１）this.キューテーブルソートキー == nullであった場合  
        //　@　@　@　@「ソートキーがnull」の例外をスローする。  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーが未指定です。");     
        }
        
        //　@３－２）this.キューテーブルソートキー.length == 0だった場合  
        //　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーの要素数が０です");     
        }
        
        //　@３－３）this.キューテーブルソートキーの全要素に対して  
        //　@　@　@　@下記のチェックを行う。  
        //　@　@３－３－１）キューテーブルソートキー.validate()をコールする。
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
        return new WEB3AdminDirSecHostTableReferenceResponse(this);
    }
}
@
