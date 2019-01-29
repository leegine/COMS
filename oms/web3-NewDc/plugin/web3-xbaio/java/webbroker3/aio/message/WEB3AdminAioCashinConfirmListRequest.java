head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認一覧リクエスト(WEB3AdminAioCashinConfirmListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3OutPutDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金連絡確認一覧リクエスト)<BR>
 * 入金連絡確認一覧リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmListRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101855L;      
    
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード（自）)<BR>
     * 画面にて入力された口座番号（顧客コード）（自）
     */
    public String minAccountCode;
    
    /**
     * (顧客コード（至）)<BR>
     * 画面にて入力された口座番号（顧客コード）（至）
     */
    public String maxAccountCode;
    
    /**
     * (連絡日時（自）)<BR>
     * 画面にて入力された連絡日時（自）
     */
    public Date minNoticeDate;
    
    /**
     * (連絡日時（至）)<BR>
     * 画面にて入力された連絡日時（至）<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (振込日（自）)<BR>
     * 画面にて入力された振込日（自）
     */
    public Date minTransferDate;
    
    /**
     * (振込日（至）)<BR>
     * 画面にて入力された振込日（至）
     */
    public Date maxTransferDate;
    
    /**
     * (振込先金融機@関コード)<BR>
     * 画面にて選択された金融機@関コード<BR>
     * <BR>
     * 「すべて」が選択された場合は、null<BR>
     */
    public String finInstitutionCode;
    
    /**
     * (出力区分)<BR>
     * 画面にて選択された出力区分<BR>
     * <BR>
     * 0： 一覧<BR>
     * 1： CSV<BR>
     */
    public String outputDiv;
    
    /**
     * (要求ページ番号)<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     */
    public String pageSize;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAioCashinConfirmListRequest.class);  
    
    /**
     * @@roseuid 4158EB630170
     */
    public WEB3AdminAioCashinConfirmListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コードチェック<BR>
     *   リクエストデータ.部店コードに数字以外の文字がある or<BR>
     *   リクエストデータ.部店コード = null or<BR>
     *   リクエストデータ.部店コード.length() != 3  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * ２）顧客コードチェック<BR>
     * ２－１）<BR>
     *   リクエストデータ.顧客コード（自） != null and<BR>
     *   (リクエストデータ.顧客コード（自）.length() != 6 or<BR>
     *    リクエストデータ.顧客コード（自）に数字以外の文字がある)  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00789<BR>
     * <BR>
     * <BR>
     * ２－２）<BR>
     *   リクエストデータ.顧客コード（至） != null and<BR>
     *   (リクエストデータ.顧客コード（至）.length() != 6 or<BR>
     *    リクエストデータ.顧客コード（至）に数字以外の文字がある)  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00794<BR>
     * <BR>
     * <BR>
     * ２－３）<BR>
     *   リクエストデータ.顧客コード（自） != null and<BR>
     *   リクエストデータ.顧客コード（至） != null and<BR>
     *   リクエストデータ.顧客コード（自） > リクエストデータ.顧客コード（自）（至）  <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00795<BR>
     * <BR>
     * <BR>
     * ２－４） 
     *    リクエストデータ.顧客コード（自） == null and<BR> 
     *    リクエストデータ.顧客コード（至） != null and<BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   WEB3ErrorCatalog.BUSINESS_ERROR_01385<BR>
     * <BR>
     * <BR>
     * ３）連絡日時チェック<BR>
     * ３－１）<BR>
     *  (リクエストデータ.連絡日時（自） != null and<BR>
     *   リクエストデータ.連絡日時（至） != null and<BR>
     *   リクエストデータ.連絡日時（自） > リクエストデータ.連絡日時（至）)  <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00796<BR>
     * <BR>
     * <BR>
     * ３－２）<BR>
     *   リクエストデータ.連絡日時（自） = null and<BR>
     *   リクエストデータ.連絡日時（至） != null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00797<BR>
     * <BR>
     *   <BR>
     * ４）振込日チェック<BR>
     * ４－１）<BR>
     *   リクエストデータ.振込日（自） != null and<BR>
     *   リクエストデータ.振込日（至） != null and<BR>
     *   リクエストデータ.振込日（自） > リクエストデータ.振込日（至）  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00799<BR>
     * <BR>
     * <BR>
     * ４－２）<BR>
     *   リクエストデータ.振込日（自） = null and<BR>
     *   リクエストデータ.振込日（至） != null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00800<BR>
     * <BR>
     * <BR>
     * ５）振込先金融機@関コードチェック<BR>
     *   リクエストデータ.振込先金融機@関コード != null and<BR>
     *   リクエストデータ.振込先金融機@関コード.length() != 15  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00776<BR>
     * <BR>
     * <BR>
     * ６）出力区分チェック<BR>
     *   リクエストデータ.出力区分 != 0（一覧） and<BR>
     *   リクエストデータ.出力区分 != 1（ｃｓｖ）  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00801<BR>
     * <BR>
     * <BR>
     * ７）要求ページ番号チェック<BR>
     * ７－１）<BR>
     *   リクエストデータ.出力区分 = 0（一覧） and<BR>
     *   （リクエストデータ.要求ページ番号 = null or<BR>
     *    リクエストデータ.要求ページ番号 <= 0）  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00802<BR>
     * <BR>
     * <BR>
     * ７－２）<BR>
     *   リクエストデータ.出力区分 = 0（一覧） and<BR>
     *   リクエストデータ.要求ページ番号に数字以外の文字がある  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00803<BR>
     * <BR>
     * <BR>
     * ８）ページ内表示行数チェック<BR>
     * ８－１）<BR>
     *   リクエストデータ.出力区分 = 0（一覧） and<BR>
     *   （リクエストデータ.ページ内表示行数 = null or<BR>
     *    リクエストデータ.ページ内表示行数 <= 0）   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00804<BR>
     * <BR>
     * <BR>
     * ８－２）<BR>
     *   リクエストデータ.出力区分 = 0（一覧） and<BR>
     *   リクエストデータ.ページ内表示行数に数字以外の文字がある  の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00805<BR>
     * <BR>
     * @@roseuid 40E281A80288
     */
    public void validate() throws WEB3BaseException
    {  
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        //１）部店コードチェック
        //リクエストデータ.部店コードに数字以外の文字がある or
        //リクエストデータ.部店コード = null or
        //リクエストデータ.部店コード.length() != 3  の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00779
        if ((WEB3StringTypeUtility.isNumber(this.branchCode) == false) ||
            WEB3StringTypeUtility.isEmpty(this.branchCode)||
             (this.branchCode.length() != 3))  
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.部店コードに数字以外の文字がある or " +
                "リクエストデータ.部店コード = null or " +
                "リクエストデータ.部店コード.length() != 3 , " +
                "リクエストデータ.部店コード = " + this.branchCode);  
        }
        
        //２）顧客コードチェック
        //２－１）
        //リクエストデータ.顧客コード（自） != null and
        //(リクエストデータ.顧客コード（自）.length() != 6 or
        //リクエストデータ.顧客コード（自）に数字以外の文字がある)  の場合、
        //例外をスローする。
         //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00789
        if (WEB3StringTypeUtility.isNotEmpty(this.minAccountCode) && 
            ((this.minAccountCode.length() != 6) ||
            (WEB3StringTypeUtility.isNumber(this.minAccountCode) == false)))       
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00789,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(リクエストデータ.顧客コード（自）.length() != 6 or" +
                "リクエストデータ.顧客コード（自）に数字以外の文字がある)" +
                "リクエストデータ.顧客コード（自）= " + this.minAccountCode);   
        }                 
              
        //２－２）
        //リクエストデータ.顧客コード（至） != null and
        //(リクエストデータ.顧客コード（至）.length() != 6 or
        //リクエストデータ.顧客コード（至）に数字以外の文字がある)  の場合、
        //例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00794
        if (WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode) && 
            ((this.maxAccountCode.length() != 6) ||
            (WEB3StringTypeUtility.isNumber(this.maxAccountCode) == false)))       
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00794,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(リクエストデータ.顧客コード（至）.length() != 6 or" +
                "リクエストデータ.顧客コード（至）に数字以外の文字がある)," +
                "リクエストデータ.顧客コード（至）= " + this.maxAccountCode);   
        }              
              
        // ２－３）
        //リクエストデータ.顧客コード（自） != null and
        //リクエストデータ.顧客コード（至） != null and
        //リクエストデータ.顧客コード（自） > リクエストデータ.顧客コード（至）
        //の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00795
        if (WEB3StringTypeUtility.isNotEmpty(this.minAccountCode) && 
            WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode) &&
            (Double.parseDouble(this.minAccountCode)) > 
            (Double.parseDouble(this.maxAccountCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.顧客コード（自）[" + this.minAccountCode + 
                "] > リクエストデータ.顧客コード（至）[" + this.maxAccountCode +"]");    
        }      

        //=======remain zhou-yong NO.1 begin ========
        
        //２－４）
        //リクエストデータ.顧客コード（自） == null and 
        //リクエストデータ.顧客コード（至） != null and
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.minAccountCode) && 
            WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01385,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.顧客コード（自）[" + this.minAccountCode + 
                "] > リクエストデータ.顧客コード（至）[" + this.maxAccountCode +"]");    
            
        }

        //=======remain zhou-yong NO.1 end ========
        
        //３）連絡日時チェック
       
        // ３－１）
        //(リクエストデータ.連絡日時（自） != null and
        //リクエストデータ.連絡日時（至） != null and
        // リクエストデータ.連絡日時（自） > リクエストデータ.連絡日時（至）)  
        // の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          // tag:   BUSINESS_ERROR_00796
        if ((this.minNoticeDate != null) && 
            (this.maxNoticeDate != null) &&
            (WEB3DateUtility.compare((this.minNoticeDate), (this.maxNoticeDate)) > 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00796,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.連絡日時（自）[" + this.minNoticeDate + "]" +
                " > リクエストデータ.連絡日時（至）[" + this.maxNoticeDate + "]");  
        }    
           
        //３－２）
        //リクエストデータ.連絡日時（自） = null and
        //リクエストデータ.連絡日時（至） != null  の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00797
        if ((this.minNoticeDate == null) && (this.maxNoticeDate != null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00797,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.連絡日時（自） = null and リクエストデータ.連絡日時（至） != null "
                + ", リクエストデータ.連絡日時（至）= " + this.maxNoticeDate);  
        }    
      
        // ４）振込日チェック
      
        // ４－１）
        //リクエストデータ.振込日（自） != null and
        //リクエストデータ.振込日（至） != null and
        //リクエストデータ.振込日（自） > リクエストデータ.振込日（至）  の場合、
        //例外をスローする。
         //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00799
        if ((this.minTransferDate != null) && 
            (this.maxTransferDate != null) &&
            (WEB3DateUtility.compareToDay((this.minTransferDate), (this.maxTransferDate)) > 0))   
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00799,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振込日（自）[" + this.minTransferDate + "] " +
                "> リクエストデータ.振込日（至）[" + this.maxTransferDate + "]");  
        }   
         
        //４－２）
        //リクエストデータ.振込日（自） = null and
        //リクエストデータ.振込日（至） != null  の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00800
        if ((this.minTransferDate) == null && 
            (this.maxTransferDate != null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振込日（自） = null and リクエストデータ.振込日（至） != null" +
                ", リクエストデータ.振込日（至） = " + this.maxTransferDate);  
        }    
          
        //５）振込先金融機@関コードチェック
        //リクエストデータ.振込先金融機@関コード != null and
        //リクエストデータ.振込先金融機@関コード.length() != 15  の場合、
        //例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00776
        if (WEB3StringTypeUtility.isNotEmpty(this.finInstitutionCode) && 
            (this.finInstitutionCode.length() != 15))
        {
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00776,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              "リクエストデータ.振込先金融機@関コード != null " +
              "and リクエストデータ.振込先金融機@関コード.length() != 15" + 
              ", リクエストデータ.振込先金融機@関コード.length() = " + 
              this.finInstitutionCode.length());
        } 
         
        //６）出力区分チェック
        //リクエストデータ.出力区分 != 0（一覧） and
        //リクエストデータ.出力区分 != 1（ｃｓｖ）  の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00801
        if (!WEB3OutPutDivDef.LIST_VIEW.equals(this.outputDiv) && 
            !WEB3OutPutDivDef.CSV_VIEW.equals(this.outputDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00801,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 != 0（一覧） and" +
                "リクエストデータ.出力区分 != 1（ｃｓｖ）, " +
                "リクエストデータ.出力区分 = " + this.outputDiv);
        }    
 
        //７）要求ページ番号チェック
        
        //７－１   
        //リクエストデータ.出力区分 = 0（一覧） and
        //リクエストデータ.要求ページ番号 = nullの場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00802 
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) &&
            WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧）and リクエストデータ.要求ページ番号 = null");
        } 
        
        //７－２）
        //リクエストデータ.出力区分 = 0（一覧） and
        //リクエストデータ.要求ページ番号に数字以外の文字がある  の場合、
        //例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00803
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) && 
            (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00803,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧） and " +
                "リクエストデータ.要求ページ番号に数字以外の文字, " +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);
        }   
        
        //７－３）
        //リクエストデータ.出力区分 = 0（一覧） and
        //リクエストデータ.要求ページ番号 <= 0）  の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00802 
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) &&
            (Double.parseDouble(this.pageIndex) <= 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧） and " +
                "リクエストデータ.要求ページ番号 <= 0, " +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);
            
        }
        //８）ページ内表示行数チェック
        
        //８－１）リクエストデータ.出力区分 = 0（一覧） and リクエストデータ.ページ内表示行数 = null
          //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00804
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) && 
            WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00804,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧） and リクエストデータ.ページ内表示行数 = null");
        }  
        
        //８－２）
        //リクエストデータ.出力区分 = 0（一覧） and
        //リクエストデータ.ページ内表示行数に数字以外の文字がある  の場合、
        //例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00805
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) && 
            (WEB3StringTypeUtility.isNumber(this.pageSize) == false))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00805,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧） and " +
                "リクエストデータ.ページ内表示行数に数字以外の文字," +
                "リクエストデータ.ページ内表示行数 = this.pageSize");
        } 
        
        // ８－３）
        //リクエストデータ.出力区分 = 0（一覧） and
        //リクエストデータ.ページ内表示行数 <= 0）   の場合、例外をスローする。
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00804
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) && 
             Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00804,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.出力区分 = 0（一覧） and" +
                "リクエストデータ.ページ内表示行数 <= 0, " +
                "リクエストデータ.ページ内表示行数 = " + this.pageSize);
        }    
    }
   
   /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金連絡確認一覧レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinConfirmListResponse(this);
    }
}
@
