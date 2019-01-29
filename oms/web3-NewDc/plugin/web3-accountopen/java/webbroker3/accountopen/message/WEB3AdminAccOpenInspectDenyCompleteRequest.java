head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectDenyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査否認完了リクエスト(WEB3AdminAccOpenInspectDenyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 張秋穎 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設審査否認完了リクエスト)<BR>
 * 管理者口座開設審査否認完了リクエスト
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectDenyCompleteRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenInspectDenyCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_AccOpen_inspectDenyComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200606151150L;

	/**
     * (識別コード)<BR>
     * 識別コード<BR>
     * <BR>
     * 複数件数あり
     */
    public String[] requestNumber;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * (口座開設ソートキー)<BR>
     */
    public WEB3AccOpenSortKey[] sortKeys;
    
    /**
     * @@roseuid 44912C0E009C
     */
    public WEB3AdminAccOpenInspectDenyCompleteRequest() 
    {
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@識別コードのチェック <BR>
     * 　@１－１）　@（識別コードの要素数 == 0）の場合、 例外をスローする。<BR> 
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_02472<BR> 
     * <BR> 
     * 　@１－２）　@識別コードの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@１－２－１）　@（識別コード[index] == null）の場合、 例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00829<BR> 
     * <BR>
     * ２）　@ソートキーのチェック  <BR>
     * 　@２－１）　@ソートキーが未入力lの場合、例外をスローする。  <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00231 <BR> 
     * <BR>
     * 　@２－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。  <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00232 <BR> 
     * <BR>
     * 　@２－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@２－３－１）　@ソートキー.validate()をコールする。  <BR>
     * 　@　@　@２－３－２）　@ソートキー.キー項目が下記の項目以外の場合、 例外をスローする。<BR>  
     *　@　@　@　@　@識別コード（requestNumber）<BR> 
     *　@　@　@　@　@発生日時（occurredDate） <BR>
     * <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@　@tag: BUSINESS_ERROR_00086 <BR> 
     * 
     * @@throws WEB3BaseException 
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@識別コードのチェック  
        //１－１）　@（識別コードの要素数 == 0）の場合、 例外をスローする。  
        if (this.requestNumber == null || this.requestNumber.length == 0) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02472,
                getClass().getName() + STR_METHOD_NAME,
                "識別コードの要素数が0です。");
		}      
        
        //１－２）　@識別コードの要素数分、下記のチェックを繰り返して行う。        
        for(int i = 0; i < this.requestNumber.length; i++)
        {
            String l_strRequestNumber = this.requestNumber[i];
            
            //１－２－１）　@（識別コード[index] == null）の場合、 例外をスローする。
        	if (l_strRequestNumber == null) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    getClass().getName() + STR_METHOD_NAME,
                    "識別コードが未指定です。");
    		}
        }
              
        // ２） ソートキーのチェック
        // ２－１） ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //２－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //２－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            
            //２－３－１）　@ソートキー.validate()をコールする。
            l_accOpenSortKey.validate();
            
            //２－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
            //識別コード（requestNumber） 
            //発生日時（occurredDate）
            if (!(WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_accOpenSortKey.keyItem))) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    "キー項目に識別コード、発生日時の項目名以外の値が存在しています。");         
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenInspectDenyCompleteResponse(this);
    }

    
}
@
