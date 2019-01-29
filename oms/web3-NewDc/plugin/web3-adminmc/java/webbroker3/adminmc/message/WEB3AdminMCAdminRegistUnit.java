head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者登録情報(WEB3AdminMCAdminRegistUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者登録情報)<BR>
 * 管理者登録情報<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistUnit extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistUnit.class);

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
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;
    
    /**
     * (パスワード１)<BR>
     * パスワード１<BR>
     */
    public String password1;
    
    /**
     * (パスワード２)<BR>
     * パスワード２<BR>
     * <BR>
     * ※　@パスワード（確認）<BR>
     */
    public String password2;
    
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
     */
    public String errorCount;
    
    /**
     * (更新日時)<BR>
     * 更新日時<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * @@roseuid 4198642203A9
     */
    public WEB3AdminMCAdminRegistUnit() 
    {
     
    }
    
    /**
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * <BR>
     * ２）　@管理者コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01215           <BR>
     * <BR>
     * ３）　@管理者名のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01219            <BR>
     * 　@３－２）　@バイト数が100byteより大きい場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01209            <BR>
     * <BR>
     * ４）　@権限レベルコードのチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01201              <BR>
     * 　@４－２）　@文字数が3byteでない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01202           <BR>
     * 　@４－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01203          <BR>
     * <BR>
     * @@roseuid 417CB83C02B9
     */
    public void validate() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = " validate()"; 
         log.entering(STR_METHOD_NAME);  
         
         // １）　@部店コード未入力の場合
         if (this.branchCode == null || "".equals(this.branchCode))
         {
             //例外
             log.error("「部店コード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // ２） 管理者コード未入力の場合
         if (this.administratorCode == null || "".equals(this.administratorCode))
         {
             //例外
             log.error("「管理者コード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_01215,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // ３） 管理者名のチェック
         // ３－１）　@未入力の場合
         if (this.administratorName == null || "".equals(this.administratorName))
         {
             //例外
             log.error("「管理者名未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_01219,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // ３－２）　@バイト数が100byteより大きい場合
         if (WEB3StringTypeUtility.getByteLength(this.administratorName) > 100)
         {
             log.error("バイト数が100byteより大きい");
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01209,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // ４）　@権限レベルコードのチェック
         // ４－１）　@未入力の場合
         if (this.permissionLevel == null || "".equals(this.permissionLevel))
         {
             //例外
             log.error("「権限レベルコード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // ４－２）　@文字数が3byteでない場合
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
         {
             //例外
             log.error("「権限レベルコード文字数が3byteでない場合」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // ４－３）　@数字以外の文字が含まれる場合
         if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
         {
             //例外
             log.error("「権限レベルコード数字以外の文字が含まれる場合」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateパスワード)<BR>
     * パスワード１，２をチェックする<BR>
     * <BR>
     * １）　@パスワード１，パスワード２のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01910 <BR> 
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_019101 <BR>
     * 　@１－２）　@パスワード１とパスワード２が同一でない場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_90211             <BR>
     * <BR>
     * @@roseuid 417DAB780280
     */
    public void validatePassword() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePassword()"; 
        log.entering(STR_METHOD_NAME);  
        // １）　@パスワード１，パスワード２のチェック
        // １－１）　@未入力の場合
        if (this.password1 == null || "".equals(this.password1.trim()))
        {
            //例外
            log.error("「パスワード１未入力」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01910,
                this.getClass().getName() + STR_METHOD_NAME);
        }
         
        if (this.password2 == null || "".equals(this.password2.trim()))
        {
            //例外
            log.error("「パスワード２未入力」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01911,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // １－２）　@パスワード１とパスワード２が同一でない場合
        if (!this.password1.equals(this.password2))
        {
            //例外
            log.error("「パスワード１とパスワード２が同一でない場合」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_90211,
               this.getClass().getName() + STR_METHOD_NAME);
        }
         
         log.exiting(STR_METHOD_NAME);
    }
}
@
