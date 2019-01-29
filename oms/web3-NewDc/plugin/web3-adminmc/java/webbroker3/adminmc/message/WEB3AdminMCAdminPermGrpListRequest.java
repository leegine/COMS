head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ一覧リクエスト(WEB3AdminMCAdminPermGrpListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  賈元春 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者メニュー制御管理者権限グループ一覧リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ一覧リクエスト<BR>
 * 
 * @@author 賈元春
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpListRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * ( 要求ページ番号)<BR>
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
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 4198641E003E
     */
    public WEB3AdminMCAdminPermGrpListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@要求ページ番号チェック <BR>
     * 　@１－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@１－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * 　@１－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * ２）　@ページ内表示行数チェック <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092             <BR>
     * 　@２－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * ３）　@ソートキーのチェック <BR>
     * 　@３－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * 　@３－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * 　@３－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@３－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@３－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。 <BR>
     * 　@　@　@　@ 管理者タイプ情報.権限レベルコード <BR>
     * 　@　@　@　@ 管理者タイプ情報.権限レベル名称<BR>
     * 　@　@　@　@ 管理者タイプ情報.DIR管理者フラグ<BR>
     * 　@　@　@　@ 管理者タイプ情報.全部店許可フラグ<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00086          <BR>
     * <BR>
     * <BR>
     * @@roseuid 4177409802AF
     */
    public void validate() throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@要求ページ番号チェック
        //１－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
           this.pageIndex = "1"; 
            
        }
                 
        //１－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if(!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" 数字以外の文字が含まれる場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
                 
        //１－３） マイナス値の場合、例外をスローする。
        int l_pageIndex = Integer.parseInt(this.pageIndex);
        if (l_pageIndex <= 0)
        {
            log.error(" マイナス値の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                            this.getClass().getName() + STR_METHOD_NAME);
        }  

        //２）　@ページ内表示行数チェック
        //２－１）　@未入力の場合、例外をスローする。
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("ページ内表示行数チェック未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }  
        
        //２－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if(!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" 数字以外の文字が含まれる場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                            this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        //２－３）　@マイナス値の場合、例外をスローする。
        long l_pageSize = Long.parseLong(this.pageSize);
        if (l_pageSize <= 0)
        {
            log.error(" マイナス値の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                            this.getClass().getName() + STR_METHOD_NAME);
        }  
        
        //３）　@ソートキーのチェック 
        //３－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null || "".equals(this.sortKeys))
        {
            log.error("ソートキーのチェック未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }        
        //３－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        int l_length = this.sortKeys.length;
        if (l_length == 0 )
        {
            log.error("ソートキーの要素数 == 0.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                            this.getClass().getName() + STR_METHOD_NAME);            
        } 
        //３－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        for(int i = 0 ; i < l_length; i++)
        {
            //３－３－１）　@ソートキー.validate()をコールする。
            this.sortKeys[i].validate();
            
            //３－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            if(!(WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.DIR_ADMIN_FLAG.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.ALL_BRANCH_PERMISSION_FLAG.equals(this.sortKeys[i].keyItem)))
            {
                log.error("ソートキー.キー項目が下記の項目名以外の場合.");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                                this.getClass().getName() + STR_METHOD_NAME);                              
            }            
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641E008C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpListResponse(this);
    }
}
@
