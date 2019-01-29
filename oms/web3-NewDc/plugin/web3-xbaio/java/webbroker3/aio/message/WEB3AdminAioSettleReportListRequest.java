head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート一覧リクエストクラス(WEB3AdminAioSettleReportListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioSettleReportSortkeyDef;
import webbroker3.aio.define.WEB3AioTransactionStatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (決済連携レポート一覧リクエスト)<BR>
 * 決済連携レポート一覧リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportListRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121545L;       

    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード
     */
    public String branchCode;
    
    /**
     * (決済機@関ID)<BR>
     * 画面にて選択された決済機@関ID
     */
    public String paySchemeId;
    
    /**
     * (顧客コード)<BR>
     * 画面にて入力された顧客コード
     */
    public String accountCode;
    
    /**
     * (注文日（自）)<BR>
     * 画面にて入力された注文日（自）
     */
    public Date minOrtderDate;
    
    /**
     * (注文日（至）)<BR>
     * 画面にて入力された注文日（至）
     */
    public Date maxOrtderDate;
    
    /**
     * (受渡日)<BR>
     * 画面にて入力された受渡日
     */
    public Date deliveryDate;
    
    /**
     * (.comデビット決済取引番号（自）)<BR>
     * 画面にて入力された決済取引番号（自）
     */
    public String minComDebitNumber;
    
    /**
     * (.comデビット決済取引番号（至）)<BR>
     * 画面にて入力された決済取引番号（至）
     */
    public String maxComDebitNumber;
    
    /**
     * (処理状態)<BR>
     * 画面にて選択された処理状態<BR>
     * <BR>
     * ０：未処理<BR>
     * １：決済完了<BR>
     * ２：決済中止<BR>
     * ３：エラー<BR>
     * ４：全て<BR>
     */
    public String transactionStatus;
    
    /**
     * (要求ページ番号)
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)
     */
    public String pageSize;  
    
    /**
     * (ソートキー)
     * AIOソートキーの配列 <BR>
     * ※キー項目 <BR>
     * ・顧客コード <BR>
     * ・.comデビット決済取引番号 <BR>
     * ・加盟店注文番号 <BR>
     * ・受付日時 <BR>
     */
    public WEB3AioSortKeyUnit[] sortKeys;  
        
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コードチェック<BR>
     *   リクエストデータ.部店コード = null or<BR>
     *   リクエストデータ.部店コード.length() != 3<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * ２）決済機@関IDチェック<BR>
     *   リクエストデータ.決済機@関ID = null or<BR>
     *   リクエストデータ.決済機@関ID.length() != 11 or<BR>
     *   リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     * <BR>
     * ３）顧客コードチェック<BR>
     *   リクエストデータ.顧客コード != null and<BR>
     *   (リクエストデータ.顧客コードに数字以外の文字がある or<BR>
     *    リクエストデータ.顧客コード.length() != 6)<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * <BR>
     * ４）注文日チェック<BR>
     *   リクエストデータ.注文日（自） != null and<BR>
     *   リクエストデータ.注文日（至） != null and<BR>
     *   リクエストデータ.注文日（自） > リクエストデータ.注文日（至）<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00781<BR>
     * <BR>
     * <BR>
     * ５）.comデビット決済取引番号チェック<BR>
     * ５－１）
     *   リクエストデータ..comデビット決済取引番号（自） != null and<BR>
     *   (リクエストデータ..comデビット決済取引番号（自）.length() != 15 or<BR>
     *    リクエストデータ..comデビット決済取引番号（自）に数字以外の文字が含まれる)<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00782<BR>
     * <BR>
     * <BR>
     * ５－２）<BR>
     *   リクエストデータ..comデビット決済取引番号（至） != null and<BR>
     *   (リクエストデータ..comデビット決済取引番号（至）.length() != 15 or<BR>
     *    リクエストデータ..comデビット決済取引番号（至）に数字以外の文字が含まれる)<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00787<BR>
     * <BR>
     * <BR>
     * ５－３）<BR>
     *   リクエストデータ..comデビット決済取引番号（自） != null and<BR>
     *   リクエストデータ..comデビット決済取引番号（至） != null and<BR>
     *   リクエストデータ..comデビット決済取引番号（自） > リクエストデータ..comデビット決済取引番号（至）<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00783<BR>
     * <BR>
     * <BR>
     * ５－４）<BR>
     *   リクエストデータ..comデビット決済取引番号（自） = null and<BR>
     *   リクエストデータ..comデビット決済取引番号（至） != null<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00788<BR>
     * <BR>
     * <BR>
     * ６）処理状態チェック<BR>
     *   リクエストデータ.処理状態 != （全て） and<BR>
     *   リクエストデータ.処理状態 != （決済完了） and<BR>
     *   リクエストデータ.処理状態 != （未処理） and<BR>
     *   リクエストデータ.処理状態 != （エラー） and<BR>
     *   リクエストデータ.処理状態 != （決済中止）<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00784<BR>
     * <BR>
     * <BR>
     * ７）相互チェック<BR>
     *   リクエストデータ.顧客コード = null and<BR>
     *   リクエストデータ.注文日（自） = null and<BR>
     *   リクエストデータ.注文日（至） = null and<BR>
     *   リクエストデータ.受渡日 = null and<BR>
     *   リクエストデータ..comデビット決済取引番号（自） = null<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00785<BR>
     * <BR>
     * <BR>
     * ８）要求ページ番号チェック<BR>
     * ８－１）<BR>
     *   リクエストデータ.要求ページ番号 = null or<BR>
     *   リクエストデータ.要求ページ番号 <= 0<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00786<BR>
     * <BR>
     * <BR>
     * ８－２）
     *   リクエストデータ.要求ページ番号に数字以外の文字がある  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * <BR>
     * ９）ページ内表示行数チェック<BR>
     * ９－１）<BR>
     *   リクエストデータ.ページ内表示行数 = null or<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *   リクエストデータ.ページ内表示行数 <= 0<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * ９－２）<BR>
     *   リクエストデータ.ページ内表示行数に数字以外の文字がある  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * １０）ソートキーチェック <BR>
     * <BR>
     * １０－１） <BR>
     * <BR>
     *  リクエストデータ.ソートキー = null or <BR>
     *  リクエストデータ.ソートキー.length() = 0 <BR>
     * <BR>
     *  の場合、例外をスローする。 <BR>
     * <BR>
     * １０－２）ソートキーの各要素について、以下のチェックを行う。 <BR>
     * <BR>
     * １０－２－１）キー項目のチェック <BR>
     * <BR>
     *  ソートキー.キー項目が以下の項目以外の場合、例外をスローする。 <BR>
     * <BR>
     * ・顧客コード <BR>
     * ・.comデビット決済取引番号 <BR>
     * ・加盟店注文番号 <BR>
     * ・受付日時 <BR>
     * <BR>
     * １０－２－２）昇順/降順のチェック <BR>
     * <BR>
     * ソートキー.昇順/降順 != ('A' or 'D') <BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * @@roseuid 40E530EB038C
     */
    public void validate() throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）部店コードチェック 
        //リクエストデータ.部店コード = null or 
        //リクエストデータ.部店コード.length() != 3 

        if (WEB3StringTypeUtility.isEmpty(this.branchCode) ||
            this.branchCode.length() != 3)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.部店コード = null or " +
                "リクエストデータ.部店コード.length() != 3" );
        }

        //２）決済機@関IDチェック 
        //リクエストデータ.決済機@関ID = null or 
        //リクエストデータ.決済機@関ID.length() != 11 or 
        //リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false 

        if (WEB3StringTypeUtility.isEmpty(this.paySchemeId) || 
            this.paySchemeId.length() != 11 ||
            !this.paySchemeId.startsWith("ComOndebi"))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.決済機@関ID = null or " +
                "リクエストデータ.決済機@関ID.length() != 11 or " +
                "リクエストデータ.決済機@関ID.startsWith(ComOndebi) = false");
        }
            
        //３）顧客コードチェック
        //リクエストデータ.顧客コード != null and 
        //(リクエストデータ.顧客コードに数字以外の文字がある or 
        //リクエストデータ.顧客コード.length() != 6)

        //=======remain zhou-yong NO.1 begin ============
        
        boolean l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.accountCode);
        
        if (WEB3StringTypeUtility.isNotEmpty(this.accountCode) &&
            (!l_blnIsDigit || this.accountCode.length() != 6))
        {            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.顧客コードに数字以外の文字がある or " +
                "リクエストデータ.顧客コード.length() != 6, " +
                "リクエストデータ.顧客コード = " + this.accountCode +  
                "リクエストデータ.顧客コード.length() = " + this.accountCode.length());
        }
        
        //=======remain zhou-yong NO.1 end ============
        
        //４）注文日チェック 
        //リクエストデータ.注文日（自） != null and 
        //リクエストデータ.注文日（至） != null and 
        //リクエストデータ.注文日（自） > リクエストデータ.注文日（至） 

        if (this.minOrtderDate != null &&
            this.maxOrtderDate != null &&
            this.minOrtderDate.compareTo(this.maxOrtderDate) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00781,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.注文日（自）[" + this.minOrtderDate + "] " +
                "> リクエストデータ.注文日（至）[" + this.maxOrtderDate + "]");
        }
        
        //５）.comデビット決済取引番号チェック 
        //５－１） 
        //リクエストデータ..comデビット決済取引番号（自） != null and 
        //(リクエストデータ..comデビット決済取引番号（自）.length() != 15 or 
        //リクエストデータ..comデビット決済取引番号（自）に数字以外の文字が含まれる) 
               
        l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.minComDebitNumber);
        
        if (WEB3StringTypeUtility.isNotEmpty(this.minComDebitNumber) &&
            (this.minComDebitNumber.length() != 15 ||
            !l_blnIsDigit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00782,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ..comデビット決済取引番号（自）.length() != 15 or " +
                "リクエストデータ..comデビット決済取引番号（自）に数字以外の文字が含まれる," +
                "リクエストデータ..comデビット決済取引番号（自）.length() = " + this.minComDebitNumber.length() +
                "リクエストデータ..comデビット決済取引番号（自）= " + this.minComDebitNumber);
        }
        
        //５－２）        
        //リクエストデータ..comデビット決済取引番号（至） != null and 
        //(リクエストデータ..comデビット決済取引番号（至）.length() != 15 or 
        //リクエストデータ..comデビット決済取引番号（至）に数字以外の文字が含まれる)
        l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.maxComDebitNumber);
         
        if (WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber) &&
            (this.maxComDebitNumber.length() != 15 ||
            !l_blnIsDigit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00787,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ..comデビット決済取引番号（至）.length() != 15 or " +
                "リクエストデータ..comデビット決済取引番号（至）に数字以外の文字が含まれ, " +
                "リクエストデータ..comデビット決済取引番号（至）.length()= " + this.maxComDebitNumber.length() + 
                "リクエストデータ..comデビット決済取引番号（至）= " + this.maxComDebitNumber);
        }
        
        //５－３） 
        //リクエストデータ..comデビット決済取引番号（自） != null and 
        //リクエストデータ..comデビット決済取引番号（至） != null and 
        //リクエストデータ..comデビット決済取引番号（自） > リクエストデータ..comデビット決済取引番号（至）
        if (WEB3StringTypeUtility.isNotEmpty(this.minComDebitNumber) &&
            WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber) &&
            Double.parseDouble(this.minComDebitNumber) > 
            Double.parseDouble(this.maxComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00783,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ..comデビット決済取引番号（自）[" + this.minComDebitNumber + "] " +
                "> リクエストデータ..comデビット決済取引番号（至）[" + this.maxComDebitNumber + "]");
        }
        
        //５－４）
        //リクエストデータ..comデビット決済取引番号（自） = null and 
        //リクエストデータ..comデビット決済取引番号（至） != null 
        if (WEB3StringTypeUtility.isEmpty(this.minComDebitNumber) &&
            WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00788,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ..comデビット決済取引番号（自）= null and " +
                "リクエストデータ..comデビット決済取引番号（至） != null, " +
                "リクエストデータ..comデビット決済取引番号（至） = " + this.maxComDebitNumber);
        }
        
        //６）処理状態チェック
        //リクエストデータ.処理状態 != （全て） and 
        //リクエストデータ.処理状態 != （決済完了） and 
        //リクエストデータ.処理状態 != （未処理） and 
        //リクエストデータ.処理状態 != （エラー） and 
        //リクエストデータ.処理状態 != （決済中止） 
                
        if (!WEB3AioTransactionStatusDef.ALL.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.SETTLE_COMPLETE.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.NOT_TRANSACTION.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.ERROR.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.SETTLE_STOP.equals(this.transactionStatus))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00784,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.処理状態 != (（全て）,（決済完了), （未処理）,（エラー）,（決済中止）), " +
                "リクエストデータ.処理状態 = " + this.transactionStatus);
        }

        //７）相互チェック 
        //リクエストデータ.顧客コード = null and 
        //リクエストデータ.注文日（自） = null and 
        //リクエストデータ.注文日（至） = null and 
        //リクエストデータ.受渡日 = null and 
        //リクエストデータ..comデビット決済取引番号（自） = null 
        if (WEB3StringTypeUtility.isEmpty(this.accountCode) &&
            this.minOrtderDate == null &&
            this.maxOrtderDate == null &&
            this.deliveryDate == null &&
            WEB3StringTypeUtility.isEmpty(this.minComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00785,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.顧客コード = null and " +
                "リクエストデータ.注文日（自） = null and " +
                "リクエストデータ.注文日（至） = null and " +
                "リクエストデータ.受渡日 = null and " +
                "リクエストデータ..comデビット決済取引番号（自） = null");
        }
        
        //８）要求ページ番号チェック 

        //８－１）
        //リクエストデータ.要求ページ番号 = null        
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))            
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号 = null");
        }

        //８－２） 
        //リクエストデータ.要求ページ番号に数字以外の文字がある 
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号に数字以外の文字, " +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);
        }
        
        //リクエストデータ.要求ページ番号 <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号 <= 0, " +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);
        }
        
        //９）ページ内表示行数チェック 

        //９－１）
        //リクエストデータ.ページ内表示行数 = null or 
        //リクエストデータ.ページ内表示行数 <= 0 
        
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.ページ内表示行数 = null or " +
                "リクエストデータ.ページ内表示行数 <= 0");
        }
        
        //９－２） 
        //リクエストデータ.ページ内表示行数に数字以外の文字がある
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.ページ内表示行数に数字以外の文字," +
                "リクエストデータ.ページ内表示行数 =" + this.pageSize);
        }
        
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数 <= 0," +
                "ページ内表示行数 = " + this.pageSize);
        }
        
        //１０）ソートキーチェック 
        //１０－１） 
        //リクエストデータ.ソートキー = null の場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");            
        }

        //リクエストデータ.ソートキー.length() = 0 の場合、例外をスローする。 
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //１０－２）ソートキーの各要素について、以下のチェックを行う。 
        //１０－２－１）キー項目のチェック 
        for (int i = 0; i < this.sortKeys.length; i++)
        {           
            //ソートキー.キー項目が以下の項目以外の場合、例外をスローする。 
            //・顧客コード 
            //・.comデビット決済取引番号 
            //・加盟店注文番号 
            //・受付日時
            if ((!WEB3AioSettleReportSortkeyDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.COMDEBIT_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.SHOP_ORDERID.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.RECEPTION_DATE.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");                        
            }
            //１０－２－２）昇順/降順のチェック 
            //ソートキー.昇順/降順 != ('A' or 'D') 
            //の場合、例外をスローする。 
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。"); 
            }
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB670202
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioSettleReportListResponse(this);
    }
}
@
