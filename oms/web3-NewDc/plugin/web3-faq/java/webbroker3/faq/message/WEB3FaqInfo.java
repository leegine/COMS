head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ情報(WEB3FaqInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (問合せ情報)<BR>
 * 問合せ情報<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqInfo extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInfo.class);
        
    /**
     * (問合せコード)<BR>
     * 問合せコード<BR>
     */
    public String faqCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;
    
    /**
     * (問合せ日時)<BR>
     * 問合せ日時<BR>
     */
    public Date faqDate;
    
    /**
     * (件名)<BR>
     * 件名<BR>
     */
    public String subject;
    
    /**
     * (機@能ＩＤ)<BR>
     * 機@能ＩＤ<BR>
     * <BR>
     * ※ 各社画面で任意に使用するコード。<BR>
     */
    public String transactionId;
    
    /**
     * (問合せ内容)<BR>
     * 問合せ内容<BR>
     */
    public String faqText;
    
    /**
     * (問合せ情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@return webbroker3.faq.message.WEB3FaqInfo
     * @@roseuid 41AC2A9F030C
     */
    public WEB3FaqInfo() 
    {
     
    }
    
    /**
     * 問合せ情報リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@顧客名のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01690<BR>
     * <BR>
     * ２）　@件名のチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@２－１）　@文字サイズが1,000byteより大きい場合（length > 1000）、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01692<BR>
     * <BR>
     * ３）　@問合せ内容のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01694<BR>
     * 　@３－２）　@文字サイズが2,000byteより大きい場合（length > 2000）、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01695<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6F38C03A6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@顧客名のチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.accountName))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01690, 
                this.getClass().getName(),
                "顧客名未指定です。");
        }
        
        //２）　@件名のチェック ※入力がある場合のみ
        //２－１）　@文字サイズが1,000byteより大きい場合（length > 1000）、例外をスローする。
        if (WEB3StringTypeUtility.isNotEmpty(this.subject) && WEB3StringTypeUtility.getByteLength(this.subject) > 1000)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01692, 
                this.getClass().getName(),
                "問合せ件名の値が上限値を超えています。[上限値] = 1000 " + 
                "[問合せ件名.length] = " + WEB3StringTypeUtility.getByteLength(this.subject));
        }
        
        //３）　@問合せ内容のチェック
        //３－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.faqText))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01694, 
                this.getClass().getName(),
                "問合せ内容が未指定です。");
        }
        
        //３－２）　@文字サイズが2,000byteより大きい場合（length > 2000）、例外をスローする。
        if (WEB3StringTypeUtility.isNotEmpty(this.faqText) && WEB3StringTypeUtility.getByteLength(this.faqText) > 2000)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01695, 
                this.getClass().getName(),
                "問合せ内容の値が上限値を超えています。[上限値] = 2000 " + 
                "[問合せ内容.length] = " + WEB3StringTypeUtility.getByteLength(this.faqText));
        }
       

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (validateメールアドレス)<BR>
     * メールアドレスの必須入力チェックを行う。<BR>
     * （※　@未ログインの場合のみ使用する）<BR>
     * <BR>
     * １）　@メールアドレスのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01700<BR>
     * 
     * @@roseuid 41AD5A480073
     */
    public void validateMailAddress() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateMailAddress() ";
        log.entering(STR_METHOD_NAME);

        //１）　@メールアドレスのチェック
        //１－１）　@未入力の場合、例外をスローする。

        if (WEB3StringTypeUtility.isEmpty(this.mailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700, 
                this.getClass().getName(),
                "メールアドレスが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
