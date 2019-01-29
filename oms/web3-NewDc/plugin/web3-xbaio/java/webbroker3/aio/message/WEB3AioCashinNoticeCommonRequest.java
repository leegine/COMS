head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡共通リクエストクラス(WEB3AioCashinNoticeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー
                   2005/1/11 周勇 (中訊) 残対応   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入金連絡共通リクエスト)<BR>
 * 入金連絡共通リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCommonRequest extends WEB3GenRequest 
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410111316L;     
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeCommonRequest.class);
    
    /**
     * (振込日)<BR>
     * 画面にて入力された振込日
     */
    public Date transferDate;
    
    /**
     * (振込先金融機@関コード)<BR>
     * 画面にて選択された振込先金融機@関のコード
     */
    public String finInstitutionCode;
    
    /**
     * (入金額)<BR>
     * 画面にて入力された入金額
     */
    public String cashinAmt;
    
    /**
     * (メールアドレス)<BR>
     * 画面にて入力されたメールアドレス
     */
    public String emailAddress;
    
    /**
     * @@roseuid 4158E9B70229
     */
    public WEB3AioCashinNoticeCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）振込日チェック<BR>
     *   リクエストデータ.振込日 = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00775<BR>
     * <BR>
     * <BR>
     * ２）振込先金融機@関コードチェック<BR>
     *   リクエストデータ.振込先金融機@関コード = null or<BR>
     *   リクエストデータ.振込先金融機@関コード.length() != 15<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00776<BR>
     * <BR>
     * <BR>
     * ３）入金金額チェック<BR>
     *   リクエストデータ.入金金額に数字以外の文字が含まれる or<BR>
     *   リクエストデータ.入金金額 = null or<BR>
     *   リクエストデータ.入金金額 <= 0 or<BR>
     *   リクエストデータ.入金金額.length() > 10<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00766<BR>
     * <BR>
     * @@roseuid 40E2504801AE
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）振込日チェック
        //リクエストデータ.振込日 = null  の場合、例外をスローする
        if (this.transferDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00775,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振込日 = null");
        }
        //２）振込先金融機@関コードチェック
        //リクエストデータ.振込先金融機@関コード = null or
        //リクエストデータ.振込先金融機@関コード.length() != 15
        //の場合、例外をスローする    
        if (WEB3StringTypeUtility.isEmpty(this.finInstitutionCode) || 
            this.finInstitutionCode.length() != 15)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00776,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振込先金融機@関コード = null or " +
                "リクエストデータ.振込先金融機@関コード.length() != 15");
        }
        //３）入金金額チェック
        //リクエストデータ.入金金額に数字以外の文字が含まれる or
        //リクエストデータ.入金金額 = null or
        //リクエストデータ.入金金額 <= 0 or<BR>
        //リクエストデータ.入金金額.length() > 10
        //の場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.cashinAmt) ||
            !WEB3StringTypeUtility.isNumber(this.cashinAmt) ||              
            Double.parseDouble(this.cashinAmt) <= 0 || 
            this.cashinAmt.length() > 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.入金金額に数字以外の文字が含まれる or " +
                "リクエストデータ.入金金額 = null or " +
                "リクエストデータ.入金金額 <= 0 or " +
                "リクエストデータ.入金金額.length() > 10");
        }
        log.exiting(l_strMethodName);     
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 入金連絡共通レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B70247
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeCommonResponse(this);
    }
}
@
