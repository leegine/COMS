head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者一覧リクエスト(WEB3AdminMCAdminListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.adminmc.define.WEB3AdminMCAdminRegistUnitDef;


/**
 * (管理者メニュー制御管理者一覧リクエスト)<BR>
 * 管理者メニュー制御管理者一覧リクエスト<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminListRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (管理者コード)<BR>
     * 管理者コード<BR>
     */
    public String administratorCode;
    
    /**
     * (管理者名)<BR>
     * 管理者名<BR>
     */
    public String administratorName;
    
    /**
     * (権限レベルコード)<BR>
     * 権限レベルコード<BR>
     */
    public String permissionLevel;
    
    /**
     * (エラー回数)<BR>
     * エラー回数<BR>
     * <BR>
     * ※ログインエラー回数<BR>
     * <BR>
     */
    public String errorCount;
    
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
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41986419034B
     */
    public WEB3AdminMCAdminListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@文字数が3byteでない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   　@BUSINESS_ERROR_00834            <BR>
     * <BR>
     * ２）　@管理者名のチェック<BR>
     * 　@２－１）　@バイト数が100byteより大きい場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   　@BUSINESS_ERROR_01209            <BR>
     * <BR>
     * ３）　@エラー回数のチェック<BR>
     * 　@３－１）　@数字でない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01211           <BR>
     * 　@３－２）　@有効値が 0～9 の範囲内でない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01212             <BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092<BR>  
     * 　@５－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * ６）　@ソートキーのチェック <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。 <BR>
     * 　@　@　@　@ 管理者登録情報.部店コード <BR>
     * 　@　@　@　@ 管理者登録情報.管理者コード <BR>
     * 　@　@　@　@ 管理者登録情報.メールアドレス <BR>
     * 　@　@　@　@ 管理者登録情報.権限レベルコード<BR>
     * 　@　@　@　@ 管理者登録情報.エラー回数<BR>
     * 　@　@　@　@ 管理者登録情報.更新日時<BR>
     * 　@　@　@　@ 管理者登録情報.更新者コード<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_00086             <BR>
     * <BR>
     * @@roseuid 41773E97000F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        // １）　@部店コードのチェック<BR>
        // 　@１－１）　@文字数が3byteでない場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   　@BUSINESS_ERROR_01210            <BR>
        if (!(this.branchCode == null) && !(this.branchCode == "") && WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error(" 文字数が3byteでない場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                            this.getClass().getName() + STR_METHOD_NAME);
        }         

        // ２）　@管理者名のチェック<BR>
        // 　@２－１）　@バイト数が100byteより大きい場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   　@BUSINESS_ERROR_01209            <BR>
        if (!(this.administratorName == null) && !(this.administratorName == "") && WEB3StringTypeUtility.getByteLength(this.administratorName) > 100)
        {
            log.error(" 文字数が100byteより大きい場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01209,
                            this.getClass().getName() + STR_METHOD_NAME);
        }         

        // ３）　@エラー回数のチェック<BR>
        // 　@３－１）　@数字でない場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   BUSINESS_ERROR_01211           <BR>
        if (this.errorCount != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.errorCount))
            {
                log.error(" 数字でない場合 .");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01211,
                                this.getClass().getName() + STR_METHOD_NAME);
            }        
               
            // 　@３－２）　@有効値が 0～9 の範囲内でない場合、例外をスローする。<BR>
            //                class :  WEB3BusinessLayerException <BR>
            //                tag :  BUSINESS_ERROR_01212             <BR>
            int l_errorCount = Integer.parseInt(this.errorCount);
            if ((l_errorCount > 9) || (l_errorCount < 0))
            {
                log.error(" 有効値が 0～9 の範囲内でない場合 .");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01212,
                                this.getClass().getName() + STR_METHOD_NAME);
            }        

        }
        

        // ４）　@要求ページ番号チェック <BR>
        // 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null )
        {
           this.pageIndex = "1"; 
            
        }   

        // 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00090              <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" 数字以外の文字が含まれる場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // 　@４－３）　@マイナス値の場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00616            <BR>
        int l_pageIndex = Integer.parseInt(this.pageIndex);
        if (l_pageIndex <= 0)
        {
            log.error(" マイナス値の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // ５）　@ページ内表示行数チェック <BR>
        // 　@５－１）　@未入力の場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00091         <BR>
        if (this.pageSize == null )
        {
            log.error("ページ内表示行数チェック未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00092<BR>  
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" 数字以外の文字が含まれる場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // 　@５－３）　@マイナス値の場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00617          <BR>
        int l_pageSize = Integer.parseInt(this.pageSize);
        if (l_pageSize <= 0)
        {
            log.error(" マイナス値の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // ６）　@ソートキーのチェック <BR>
        // 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00231          <BR>
        if (this.sortKeys == null )
        {
            log.error("ソートキーのチェック未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00232          <BR>
        int l_length = this.sortKeys.length;
        if (l_length == 0 )
        {
            log.error("ソートキーの要素数 == 0.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
        for (int i = 0 ; i < l_length; i++)
        {
        // 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
            this.sortKeys[i].validate();

        // 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。 <BR>
        // 　@　@　@　@ 管理者登録情報.部店コード <BR>
        // 　@　@　@　@ 管理者登録情報.管理者コード <BR>
        // 　@　@　@　@ 管理者登録情報.メールアドレス <BR>
        // 　@　@　@　@ 管理者登録情報.権限レベルコード<BR>
        // 　@　@　@　@ 管理者登録情報.エラー回数<BR>
        // 　@　@　@　@ 管理者登録情報.更新日時<BR>
        // 　@　@　@　@ 管理者登録情報.更新者コード<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :  BUSINESS_ERROR_00086             <BR> 
              
            if (!(WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(this.sortKeys[i].keyItem)))
            {
                log.error("ソートキー.キー項目の値が管理者登録情報以外の場合のエラー.");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                                this.getClass().getName() + STR_METHOD_NAME);                        	   
            }
        }
    } 
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864190399
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminListResponse(this);
    }
}
@
