head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.49.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CCオペレータ登録情報(WEB3AdminMCCCOperatorRegistUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成   
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
 * (CCオペレータ登録情報)<BR>
 * CCオペレータ登録情報<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistUnit extends Message 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistUnit.class);
    
    /**
     * (部店コード) <BR>
     * 部店コード <BR>
     */
    public String branchCode;
    
    /**
     * (オペレータコード)<BR>
     * オペレータコード<BR>
     */
    public String operatorCode;
    
    /**
     * (オペレータ名)<BR>
     * オペレータ名<BR>
     */
    public String operatorName;
    
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
     * (代行注文可能区分)<BR>
     * 代行注文可能区分<BR>
     * <BR>
     * 0：　@不可<BR>
     * 1：　@可能<BR>
     */
    public String accountOrderDiv;
    
    /**
     * (所属コード)<BR>
     * 所属コード<BR>
     */
    public String departmentCode;
    
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
     * @@roseuid 41986429033C
     */
    public WEB3AdminMCCCOperatorRegistUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_00833          <BR>
     * 　@１－２）　@文字数が3byteでない場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01024         <BR>
     * <BR>
     * ２）　@オペレータコードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01192         <BR>
     * <BR>
     * ３）　@オペレータ名のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01193         <BR>
     * 　@３－２）　@バイト数が40byteより大きい場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01194         <BR>
     * <BR>
     * ４）　@代行注文可能区分のチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01195         <BR>
     * 　@４－２）　@コード値が”可能”または、”不可”以外の場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01196         <BR>
     * <BR>
     * ５）　@所属コードのチェック<BR>
     * 　@５－１）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01197         <BR>
     * 　@５－２）　@文字数が2文字でない場合、例外をスローする。<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01198         <BR>
     * <BR>
     * @@roseuid 417E239802A8
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）部店コードのチェック
        //１－１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_00833    
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("部店コード未入力");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + l_strMethodName);      
        }
        //１－２）文字数が3byteでない場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01024  
        else if (WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error("部店コード文字数が3byteでない");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + l_strMethodName,
                "部店コードのサイズが不正です。" +
                " [部店コード] = " + this.branchCode);                 
        }
        
        //２）オペレータコードのチェック
        //２－１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01192        
        if (this.operatorCode == null || "".equals(this.operatorCode))
        {
            log.error("オペレータコード未入力");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01192,
                this.getClass().getName() + "." + l_strMethodName);                  
        }
       
        //３）オペレータ名のチェック
        //３－１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01193         
        if (this.operatorName == null || "".equals(this.operatorName))
        {
            log.error("オペレータ名未入力");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01193,
                this.getClass().getName() + "." + l_strMethodName);         
        }
        //３－２）バイト数が40byteより大きい場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01194         
        else if (WEB3StringTypeUtility.getByteLength(this.operatorName) > 40)
        {
            log.error("バイト数が40byteより大きい");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01194,
                this.getClass().getName() + "." + l_strMethodName);    
        }
        
        //４）代行注文可能区分のチェック
        //４－１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01195       
        if (this.accountOrderDiv == null || "".equals(this.accountOrderDiv))
        {
            log.error("代行注文可能区分未入力");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01195,
                this.getClass().getName() + "." + l_strMethodName);                
        }
        //４－２）コード値が”可能”または、”不可”以外の場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01196       
        else if (!(this.accountOrderDiv.equals("0")  || this.accountOrderDiv.equals("1")))
        {
            log.error("代行注文可能区分未入力コード値が”可能”または”不可”以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01196,
                this.getClass().getName() + "." + l_strMethodName);                
        }
        
        //５）所属コードのチェック
        //５－１）数字以外の文字が含まれる場合、例外をスローする。
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01197 
        if (this.departmentCode != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.departmentCode))
            {
                log.error("所属コード数字以外の文字が含まれる");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01197,
                    this.getClass().getName() + "." + l_strMethodName);                
            
            }
            //５－２）文字数が2文字でない場合、例外をスローする。
            //       class:WEB3BusinessLayerException
            //         tag:BUSINESS_ERROR_01198    
            else if (WEB3StringTypeUtility.getByteLength(this.departmentCode) != 2)
            {
                log.error("所属コード文字数が2文字でない");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01198,
                    this.getClass().getName() + "." + l_strMethodName);                  
            }
        }
                    
        log.exiting(l_strMethodName);
    }
    
    /**
     * (validateパスワード)<BR>
     * パスワード１，２をチェックする<BR>
     * <BR>
     * １）　@パスワード１，パスワード２のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *              class :  WEB3BusinessLayerException <BR>
     *              tag :    BUSINESS_ERROR_01910 <BR> 
     *              class :  WEB3BusinessLayerException <BR>
     *              tag :    BUSINESS_ERROR_019101 <BR>
     * 　@１－２）　@パスワード１とパスワード２が同一でない場合、例外をスローする。 <BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :    BUSINESS_ERROR_90211            <BR>
     * <BR>
     * @@roseuid 417E253E00B4
     */
    public void validatePassword() throws WEB3BaseException
    {
        String l_strMethodName = "validatePassword()";
        log.entering(l_strMethodName);
        
        //１）パスワード１，パスワード２のチェック
        //１－１）未入力の場合、例外をスローする。 
        //       class:WEB3BusinessLayerException          
        //         tag:BUSINESS_ERROR_01199     
        if (this.password1 == null || "".equals(this.password1.trim()))
        {
            //例外
            log.error("「パスワード１未入力」の例外をスローする。");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01910,
                this.getClass().getName() + l_strMethodName);
        }
         
        if (this.password2 == null || "".equals(this.password2.trim()))
        {
            //例外
            log.error("「パスワード２未入力」の例外をスローする。");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01911,
                this.getClass().getName() + l_strMethodName);
        }


        //１－２）パスワード１とパスワード２が同一でない場合、例外をスローする。 
        //       class:WEB3BusinessLayerException           
        //         tag:BUSINESS_ERROR_90211  
        else if (!this.password1.equals(this.password2))
        {
            log.error("パスワード１とパスワード２が同一でない");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90211,
                this.getClass().getName() + "." + l_strMethodName);                    
        }                   
        
        log.exiting(l_strMethodName); 
    }
}
@
