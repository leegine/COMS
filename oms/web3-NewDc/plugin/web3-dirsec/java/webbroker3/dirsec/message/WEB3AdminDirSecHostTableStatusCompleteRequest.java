head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブルステータス更新完了リクエストクラス(WEB3AdminDirSecHostTableStatusCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・キューテーブルステータス更新完了リクエスト)<BR>
 * 管理者・キューテーブルステータス更新完了リクエストオブジェクト。
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusCompleteRequest extends WEB3AdminDirSecHostTableUpdateCommonRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableStatusCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_status_complete";

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
     * 作成日付From（YYYYMMDDHHMM）。
     */
    public String createDateFrom;
    
    /**
     * (作成日付To)<BR>
     * 作成日付To（YYYYMMDDHHMM）。
     */
    public String createDateTo;
    
    /**
     * (ステータス)<BR>
     * ステータス。
     */
    public String status;
    
    /**
     * (更新後ステータス)<BR>
     * 更新後ステータス。
     */
    public String updateStatus;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号。
     */
    public String password;
    
    /**
     * (ソートキー)
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C87031C
     */
    public WEB3AdminDirSecHostTableStatusCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * <BR>
     * ２）　@部店コードチェック<BR> 
     * 　@２－１）　@this.部店コード == nullの場合、<BR> 
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * ３）　@更新後ステータスチェック<BR>
     * 　@３－１）　@this.更新後ステータス == nullの場合、<BR> 
     * 　@　@　@　@　@「更新後ステータスがnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02427<BR>
     * <BR>
     * ４）　@暗証番号チェック<BR>
     * 　@４－１）　@this.暗証番号 == nullの場合、<BR> 
     * 　@　@　@　@　@「暗証番号がnull」の例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01090<BR>
     * <BR>
     * ５）　@ソートキーチェック<BR> 
     * 　@５－１）this.キューテーブルソートキー == nullであった場合<BR> 
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@５－２）this.キューテーブルソートキー.length == 0だった場合<BR> 
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@５－３）this.キューテーブルソートキーの全要素に対して <BR>
     * 　@　@　@　@下記のチェックを行う。 <BR>
     * 　@　@５－３－１）キューテーブルソートキー.validate()をコールする。 <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4423A447013B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()をコールする。  
        super.validate();
        
        //２）　@部店コードチェック  
        //　@２－１）　@this.部店コード == nullの場合、  
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。  
        if (this.branchCode == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02174, 
                this.getClass().getName() + STR_METHOD_NAME, "部店コードがnullです。");    
        }
        
        //３）　@更新後ステータスチェック 
        //　@３－１）　@this.更新後ステータス == nullの場合、  
        //　@　@　@　@　@「更新後ステータスがnull」の例外をスローする。  
        if (this.updateStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02427, 
                this.getClass().getName() + STR_METHOD_NAME, "更新後ステータスがnullです。");   
        }
        
        //４）　@暗証番号チェック 
        //　@４－１）　@this.暗証番号 == nullの場合、  
        //　@　@　@　@　@「暗証番号がnull」の例外をスローする。  
        if (this.password == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, 
                this.getClass().getName() + STR_METHOD_NAME, "暗証番号が未指定です。");  
        }
        
        //４）　@ソートキーチェック  
        //　@４－１）this.キューテーブルソートキー == nullであった場合  
        //　@　@　@　@「ソートキーがnull」の例外をスローする。  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーが未指定です。");     
        }
        
        //　@４－２）this.キューテーブルソートキー.length == 0だった場合  
        //　@　@　@　@「ソートキー.要素数が0」の例外をスローする。  
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "ソートキーの要素数が０です。");     
        }
        
        //　@４－３）this.キューテーブルソートキーの全要素に対して  
        //　@　@　@　@下記のチェックを行う。  
        //　@　@４－３－１）キューテーブルソートキー.validate()をコールする。 
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
        return new WEB3AdminDirSecHostTableStatusCompleteResponse(this);
    }
}
@
