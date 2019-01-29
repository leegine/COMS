head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminTypeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者タイプ情報(WEB3AdminMCAdminTypeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22  温 顕 法@ (中訊) 新規作成
                 : 2006/08/28 肖志偉 (中訊) 仕様変更 モデル022
*/
package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;

/**
 * (管理者タイプ情報)<BR>
 * 管理者タイプ情報<BR>
 * @@author 温顕法@
 * @@version 1.0
 */
 
public class WEB3AdminMCAdminTypeUnit extends Message 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminTypeUnit.class); 
    
    /**
     * (権限レベルコード)<BR>
     * 権限レベルコード<BR>
     */
    public String permissionLevel;
    
    /**
     * (権限レベル名称)<BR>
     * 権限レベル名称<BR>
     * <BR>
     * ※グループ名称<BR>
     */
    public String permissionLevelName;
    
    /**
     * (DIR管理者フラグ)<BR>
     * DIR管理者フラグ <BR>
     * <BR>
     * 通常管理者：0<BR> 
     * DIR管理者：1 <BR>
     * 通常管理者（申請者）：2<BR> 
     * 通常管理者（承認者）：3 <BR>
     */
    public String dirAdminFlag;
    
    /**
     * (全部店許可フラグ)<BR>
     * 全部店許可フラグ<BR>
     * <BR>
     * 全部店許可の場合true，以外、false<BR>
     */
    public boolean allBranchPermissionFlag;
    
    /**
     * @@roseuid 419864230138
     */
    public WEB3AdminMCAdminTypeUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@権限レベルコードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01201 <BR>
     * 　@１－２）　@文字数が3byteでない場合（権限レベルコード.length != 3）、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01202 <BR>
     * 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01203<BR>
     * <BR>
     * ２）　@権限レベル名称のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01204 <BR>
     * 　@２－２）　@文字数が100byteより大きい場合（権限レベル名称.length > 100）、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01205  <BR>
     * <BR>
     * ３）　@DIR管理者フラグ，権限レベルコードのチェック<BR>
     * 　@３－１） DIR管理者の場合（this.isDIR管理者() == true）<BR>
     * 　@　@（権限レベルコードの開始文字 != '9'）の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :　@BUSINESS_ERROR_01206  <BR>
     * 　@３－２） 通常管理者の場合（this.isDIR管理者() == false） <BR>
     * 　@　@（権限レベルコードの開始文字 == '9'）の場合、例外をスローする。<BR>
     * 　@※ DIR管理者の権限レベルコードは'9'で始まる文字列でなければならない。<BR>
     * 　@　@　@また、通常管理者の場合は、'9'で始まる権限レベルコードは使用できない。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01207 <BR>
     * <BR>
     * <BR>
     * @@roseuid 41760FB50031
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
         // １）　@権限レベルコードのチェック<BR>
         // 　@１－１）　@未入力の場合、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01201 <BR>
         if (this.permissionLevel == null)
         {
            log.error(" 権限レベルコードが未入力 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                            this.getClass().getName() + STR_METHOD_NAME);
         }
         
         // 　@１－２）　@文字数が3byteでない場合（権限レベルコード.length != 3）、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01202 <BR>
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
         {
            log.error(" 文字数が3byteでない場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         // 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01203<BR>
         if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
         {
            log.error(" 数字以外の文字が含まれる場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                            this.getClass().getName() + STR_METHOD_NAME);
         }        
        
         // ２）　@権限レベル名称のチェック<BR>
         // 　@２－１）　@未入力の場合、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :  BUSINESS_ERROR_01204 <BR>
         if (this.permissionLevelName == null)
         {
            log.error(" 権限レベル名称が未入力 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01204,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         // 　@２－２）　@文字数が100byteより大きい場合（権限レベル名称.length > 100）、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag : BUSINESS_ERROR_01205  <BR>
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevelName) > 100)
         {
            log.error(" 文字数が100byteより大きい場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01205,
                            this.getClass().getName() + STR_METHOD_NAME);
         }         
         
         
         // ３）　@DIR管理者フラグ，権限レベルコードのチェック<BR>
         // 　@３－１） DIR管理者の場合（this.isDIR管理者() == true）<BR>
         // 　@　@（権限レベルコードの開始文字 != '9'）の場合、例外をスローする。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag :　@BUSINESS_ERROR_01206  <BR>
         if (this.isDIRAdmin() 
             && (this.permissionLevel.charAt(0) != WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME_FIRST))
         {
            log.error(" DIR管理者の場合, 権限レベルコードの開始文字 != '9'.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01206,
                            this.getClass().getName() + STR_METHOD_NAME);
         }          
         
         // 　@３－２） 通常管理者の場合（this.isDIR管理者() == false） <BR>
         // 　@　@（権限レベルコードの開始文字 == '9'）の場合、例外をスローする。<BR>
         // 　@※ DIR管理者の権限レベルコードは'9'で始まる文字列でなければならない。<BR>
         // 　@　@　@また、通常管理者の場合は、'9'で始まる権限レベルコードは使用できない。<BR>
         //                class :  WEB3BusinessLayerException <BR>
         //                tag : BUSINESS_ERROR_01207 <BR>     
         if (!this.isDIRAdmin() 
             && (this.permissionLevel.charAt(0) == WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME_FIRST))
         {
            log.error(" 通常管理者の場合, 権限レベルコードの開始文字 == '9'.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01207,
                            this.getClass().getName() + STR_METHOD_NAME);
         }          
         log.exiting(STR_METHOD_NAME);     
         
    }
    
    /**
     * (validateDIR管理者)<BR>
     * ＤＩＲ管理者チェックを行う<BR>
     * <BR>
     * 通常管理者（isDIR管理者（オペレータ） == false）の場合、<BR>
     * （this.isDIR管理者() == true）であれば、例外をスローする。<BR>
     * ※ 通常管理者は、DIR管理者の管理者タイプを更新できない。<BR>
     *        class :  WEB3BusinessLayerException <BR>
     *        tag :  BUSINESS_ERROR_01208             <BR>
     * <BR>
     * @@param isDIR管理者（オペレータ） - isDIR管理者（オペレータ）
     * 
     * ※ ログイン中の管理者がDIR管理者であるかの判定。
     * @@roseuid 4178C5DF014F
     */
    public void validateDIRAdmin(boolean l_blnIsDIRAdmin) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDIRAdmin()";

        log.entering(STR_METHOD_NAME);    	  
        // 通常管理者（isDIR管理者（オペレータ） == false）の場合、<BR>
        // this.isDIR管理者() == true）であれば、例外をスローする。<BR>
        // ※ 通常管理者は、DIR管理者の管理者タイプを更新できない。<BR>
        //        class :  WEB3BusinessLayerException <BR>
        //        tag :  BUSINESS_ERROR_01208             <BR>
         if (!(l_blnIsDIRAdmin) && (this.isDIRAdmin()))
         {
            log.error(" 通常管理者の場合 ,this.isDIR管理者() == true");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01208,
                            this.getClass().getName() + STR_METHOD_NAME);
         }        
         log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (isDIR管理者)<BR>
     * DIR管理者かを判定する。<BR> 
     * <BR>
     * 　@－（this.DIR管理者フラグ  = 1 ）の場合trueを返却する。<BR> 
     * 　@－（this.DIR管理者フラグ != 1 ）の場合falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isDIRAdmin()
    {
        final String STR_METHOD_NAME = " isDIRAdmin()";
        log.entering(STR_METHOD_NAME);
        
        //（this.DIR管理者フラグ  = 1 ）の場合trueを返却する。
        if (WEB3DirAdminFlagDef.DIR_ADMINISTRATOR.equals(this.dirAdminFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //（this.DIR管理者フラグ != 1 ）の場合falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
