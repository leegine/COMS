head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウトメインリクエスト(WEB3AdminFPTForceLogoutMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * 管理者 書面未承諾 強制ログアウトメインリクエスト<BR>
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainRequest extends WEB3BackRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutMainRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_main";
    
    /**
     * 証券会社コード
     */
    public String institutionCode;
    
    /**
     * スレッドNo
     */
    public Long threadNo;
    
    /**
     * From口座ID
     */
    public Long accountIdFrom;
    
    /**
     * To口座ID
     */
    public Long accountIdTo;
    
    /**
     * 管理者ID
     */
    public Long adminId;
    
    /**
     * 管理者 書面未承諾 強制ログアウト対象銘柄条件
     */
    public WEB3AdminFPTForceLogoutProductCondition forceLogoutProductCondition;
    
    /**
     * @@roseuid 47DF4FB701EE
     */
    public WEB3AdminFPTForceLogoutMainRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     * 
     * 　@１－１）　@this.証券会社コード == nullの場合、
     * 　@　@「証券会社コードが未指定です。」の例外をスローする。
     * 
     * ２）　@スレッドNoチェック
     * 　@２－１）　@this.スレッドNo == nullの場合、
     * 　@　@「スレッド番号の指定なし。」の例外をスローする。
     * 
     * ３）　@From口座IDチェック
     * 　@３－１）　@this.From口座ID == nullの場合、
     * 　@　@「From口座IDが未入力」の例外をスローする。
     * 
     * ４）　@To口座IDチェック
     * 　@４－１）　@this.To口座ID == nullの場合、
     * 　@　@「To口座ID（至）が未入力」の例外をスローする。
     * 
     * ５）　@管理者 書面未承諾 強制ログアウト対象銘柄条件
     * 　@５－１）　@this.管理者 書面未承諾 強制ログアウト対象銘柄条件 == nullの場合、
     * 　@　@　@例外をスローする。
     * 
     * 　@５－２）　@this.管理者 書面未承諾 
     * 強制ログアウト対象銘柄条件.validate()をコールする。
     * @@roseuid 47CF77BE01B3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@証券会社コードチェック 
        //　@１－１）　@this.証券会社コード == nullの場合、 
        //　@　@「証券会社コードが未指定です。」の例外をスローする。 
        if (this.institutionCode == null) 
        {
            log.debug("証券会社コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }
        
        //２）　@スレッドNoチェック 
        //　@２－１）　@this.スレッドNo == nullの場合、 
        //　@　@「スレッド番号の指定なし。」の例外をスローする。 
        if (this.threadNo == null) 
        {
            log.debug("スレッド番号の指定なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "スレッド番号の指定なし。");
        }
        
        //３）　@From口座IDチェック 
        //　@３－１）　@this.From口座ID == nullの場合、 
        //　@　@「From口座IDが未入力」の例外をスローする。 
        if (this.accountIdFrom == null) 
        {
            log.debug("From口座IDが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02421,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "From口座IDが未入力です。");
        }
        
        //４）　@To口座IDチェック 
        //　@４－１）　@this.To口座ID == nullの場合、 
        //　@　@「To口座ID（至）が未入力」の例外をスローする。 
        if (this.accountIdTo == null) 
        {
            log.debug("To口座ID（至）が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02422,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "To口座ID（至）が未入力です。");
        }
        
        //５）　@管理者 書面未承諾 強制ログアウト対象銘柄条件チェック 
        //　@５－１）　@this.管理者 書面未承諾 強制ログアウト対象銘柄条件 == nullの場合、 
        //　@　@「書面未承諾強制ログアウト対象銘柄条件のチェックエラー」の例外をスローする。 
        if (this.forceLogoutProductCondition == null) 
        {
            log.debug("書面未承諾強制ログアウト対象銘柄条件のチェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420, //??
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面未承諾強制ログアウト対象銘柄条件のチェックエラー。");
        }
        
        //　@５－２）　@this.管理者書面未承諾強制ログアウト対象銘柄条件.validate()をコールする。
        this.forceLogoutProductCondition.validate();
        
        log.exiting(STR_METHOD_NAME);
     
    }

    /* (非 Javadoc)
     * @@see webbroker3.common.message.WEB3BackRequest#createResponse()
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminFPTForceLogoutMainResponse();
    }
}
@
