head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知訂正共通リクエストクラス(WEB3AdminAioCashinChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 李俊 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入金通知訂正共通リクエスト)<BR>
 * 入金通知訂正共通リクエストクラス
 * 
 * @@author 李俊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinChangeCommonRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinChangeCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_aio_cashin_change_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211341L;
        
    /**
     * (入金通知明細一覧)<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    
    /**
     * @@roseuid 4158EB640294
     */
    public WEB3AdminAioCashinChangeCommonRequest() 
    {
     
    }
    
    /**
     * 入金通知明細の件数分チェックする。 <BR>
     * <BR>
     * １）部店コードチェック  <BR>
     *  入金通知明細.部店コードが以下の条件に該当する場合、  <BR>
     * 「部店コードエラー」の例外をスローする。  <BR>
     *  部店コード == null  <BR>
     *  部店コード.length != 3  <BR>
     *  部店コード != 数値  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）顧客コードチェック <BR>
     *  入金通知明細.顧客コードが以下の条件に該当する場合、  <BR>
     * 「顧客コードエラー」の例外をスローする。  <BR>
     *  顧客コード == null  <BR>
     *  顧客コード.length != 6  <BR>
     *  顧客コード != 数値  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * ３）入金通知明細.備考の最大文字数チェック <BR>
     * 入金通知明細.備考がnullでないとき <BR>
     * lengthが50文字以下でない場合 <BR>
     * 「備考のチェック」例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00487<BR>
     * 
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //入金通知明細の件数分チェックする。
        for (int i = 0; i < cashinNoticeList.length; i++)
        {
            
            WEB3AioCashinNoticeUnit2 l_cashinNotice
                = this.cashinNoticeList[i];
            
            //１）部店コードチェック 
            // 部店コード == null 
            // 部店コード.length != 3  
            // 部店コード != 数値 
            if (WEB3StringTypeUtility.isEmpty(l_cashinNotice.branchCode) ||
                l_cashinNotice.branchCode.length() != 3 ||
                !WEB3StringTypeUtility.isNumber(l_cashinNotice.branchCode))
            {
                log.exiting(STR_METHOD_NAME);   
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " リクエストデータ.入金通知明細[" + i +"] "+                    
                    "部店コード == null or" +
                    "リクエストデータ.部店コード.length() != 3 " +
                    "部店コード != 数値" );
            }      
            
            //２）顧客コードチェック 
            //入金通知明細.顧客コードが以下の条件に該当する場合、  
            //「顧客コードエラー」の例外をスローする。  
            // 顧客コード == null  
            // 顧客コード.length != 6  
            // 顧客コード != 数値  

            if (WEB3StringTypeUtility.isEmpty(l_cashinNotice.accountCode) ||
                l_cashinNotice.accountCode.length() != 6 ||
                !WEB3StringTypeUtility.isNumber(l_cashinNotice.accountCode))       
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " リクエストデータ.入金通知明細[" + i +"] "+
                    " 顧客コード == null  or " +
                    " 顧客コード.length != 6 or " +
                    " 顧客コード != 数値 " );   
            }  
            
            // ３）入金通知明細.備考の最大文字数チェック 
            // 入金通知明細.備考がnullでないとき 
            //lengthが50文字以下でない場合 
            //「備考のチェック」例外をスローする。
            
            if (WEB3StringTypeUtility.isNotEmpty(l_cashinNotice.remark) &&
                    l_cashinNotice.remark.length() > 50 )       
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " リクエストデータ.入金通知明細[" + i +"] "+
                    "入金通知明細.備考がnullでないとき length が50文字以下でない" 
                    );   
            }            
            
        }

    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;        
    }
}
@
