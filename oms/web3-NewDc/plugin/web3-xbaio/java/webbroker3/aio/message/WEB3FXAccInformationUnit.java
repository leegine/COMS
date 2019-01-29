head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccInformationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座情報(WEB3FXAccInformationUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
Revesion History : 2008/09/26 武波 (中訊) 仕様変更・モデルNo.1017,1070
Revesion History : 2009/08/25 孟亞南 (中訊) 仕様変更・モデルNo.1191
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX口座情報) <BR>
 * FX口座情報 <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccInformationUnit extends Message
{
    /**
     * (コース区分) <BR>
     * 為替保証金取引コース区分 <BR>
     * <BR>
     * 0：DEFAULT <BR>
     * 1：1万通貨コース <BR>
     * 2：10万通貨コース <BR>
     * 3：CFDコース<BR>
     */
    public String fxCourseDiv;

    /**
     * (口座番号) <BR>
     * 為替保証金口座番号 <BR>
     */
    public String fxAccountCode;

    /**
     * (FX口座情報) <BR>
     * コンストラクタ。 <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit
     * @@roseuid 41B0393C0146
     */
    public WEB3FXAccInformationUnit()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccInformationUnit.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） コース区分チェック <BR>
     * this.コース区分＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01792 <BR>
     * <BR>
     * this.コース区分が下記の項目以外の場合、例外をthrowする。 <BR>
     * ・”0：DEFAULT” <BR>
     * ・”1：1万通貨コース” <BR>
     * ・”2：10万通貨コース” <BR>
     * ・”3：CFDコース”<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01793 <BR>
     * <BR>
     * ２） 口座番号チェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * ２－１） this.口座番号＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01794 <BR>
     * <BR>
     * ２－２） this.口座番号≠数字 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01105 <BR>
     * <BR>
     * ２－４）　@this.口座番号＞10桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01106 <BR>
     * <BR>
     * 
     * @@roseuid 41BD529E038A
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） コース区分チェック 
        // this.コース区分＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01792 
        if (WEB3StringTypeUtility.isEmpty(this.fxCourseDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01792,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.コース区分 = null"); 
        }

        // this.コース区分が下記の項目以外の場合、例外をthrowする。 
        // ・”0：DEFAULT” 
        // ・”1：1万通貨コース” 
        // ・”2：10万通貨コース”  
        //・”3：CFDコース”
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01793 
        if (!(WEB3GftTransStatusCourseDivDef.DEFAULT.equals(this.fxCourseDiv) ||
            WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(this.fxCourseDiv) ||
            WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(this.fxCourseDiv)
            || WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(this.fxCourseDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01793,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.コース区分が下記の項目 : " +
                "[0：DEFAULT" +
                "1：1万通貨コース" + 
                "2：10万通貨コース" +
                "3：CFDコース]" + "以外" +
                "リクエストデータ.コース区分 = " + this.fxCourseDiv); 
        }
 
        // ２） 口座番号チェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。 
        // ２－１） this.口座番号＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01794
        if (WEB3StringTypeUtility.isEmpty(this.fxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01794,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.口座番号 = null"); 
        }
 
        // ２－２） this.口座番号≠数字 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01105 
        if (!WEB3StringTypeUtility.isNumber(this.fxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01105,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.口座番号≠数字" + 
                "リクエストデータ.口座番号 = " + this.fxAccountCode); 
        }
 
        // ２－４）　@this.口座番号＞10桁 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01106
        if (this.fxAccountCode.length() > 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01106,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.口座番号＞10桁" + 
                "リクエストデータ.口座番号 = " + this.fxAccountCode); 
        }
        
        log.exiting(l_strMethodName);
    }
}@
