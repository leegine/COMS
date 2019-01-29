head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ダイレクト指定登録確認リクエスト(WEB3AdminPvInfoDirectRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・ダイレクト指定登録確認リクエスト)<BR>
 * 管理者・ダイレクト指定登録確認リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistConfirmRequest extends WEB3GenRequest 
{
    /**
         * ログユーティリティ<BR>
         */
        private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectRegistConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_directRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (部店コード)<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     */
    public String accountCode;
    
    /**
     * (アップロードファ@イル)<BR>
     * <BR>
     * ※CSVファ@イルの配列<BR>
     */
    public String[] uploadFile;
    
    /**
     * (表示内容情報)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コード、顧客コードチェック<BR>
     * 　@this.部店コード != null または this.顧客コード != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@１－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.部店コード == null<BR>
     * 　@　@　@　@　@・this.部店コード.length != 3<BR>
     * 　@　@　@　@　@・this.部店コード != 数値<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * 　@１－２）this.顧客コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.顧客コード == null<BR>
     * 　@　@　@　@　@・this.顧客コード.length != 6<BR>
     * 　@　@　@　@　@・this.顧客コード != 数値<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * ２）アップロードファ@イルチェック<BR>
     * 　@this.部店コード == null かつ this.顧客コード == nullの場合<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@２－１）this.アップロードファ@イル == nullの場合、<BR>
     * 　@　@　@　@　@「アップロードファ@イルがnull」の例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * ３）入力内容のチェック<BR>
     * 　@３－１）this.表示内容情報.validate()をコールする。<BR>
     * @@roseuid 4160B28E00D6
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード、顧客コードチェック
        if(this.branchCode != null || this.accountCode != null)
        {
            //１－１）this.部店コードが以下の条件に該当する場合、部店コードエラー」の例外をスローする。
            if(this.branchCode == null || WEB3StringTypeUtility.getByteLength(this.branchCode) != 3 || !WEB3StringTypeUtility.isNumber(this.branchCode))
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00779.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    getClass().getName()+ "." + STR_METHOD_NAME);      
            }
        
            //１－２）this.顧客コードが以下の条件に該当する場合、「顧客コードエラー」の例外をスローする。
            if(this.accountCode == null || WEB3StringTypeUtility.getByteLength(this.accountCode) != 6 || !WEB3StringTypeUtility.isNumber(this.accountCode))
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00780.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    getClass().getName() + "." + STR_METHOD_NAME);      
            }
        }
        //２）アップロードファ@イルチェック
        //this.部店コード == null かつ this.顧客コード == nullの場合
        //２－１）this.アップロードファ@イル == nullの場合、「アップロードファ@イルがnull」の例外をスローする。
        if(this.branchCode == null && this.accountCode == null)
        {
            if(this.uploadFile == null)
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00976.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                    getClass().getName() + "." + STR_METHOD_NAME);      
            }
        }
        
        //３）入力内容のチェック
        //３－１）this.表示内容情報.validate()をコールする。
        this.displayContentsUnit.validate();
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4173439600FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoDirectRegistConfirmResponse(this);
    }
}
@
