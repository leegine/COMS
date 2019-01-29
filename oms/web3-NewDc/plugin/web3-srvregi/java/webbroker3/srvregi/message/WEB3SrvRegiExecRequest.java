head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス起動リクエスト(WEB3SrvRegiExecRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2005/10/05 鈴木 美由紀(SRA) トランスリンク対応
Revesion History : 2008/02/29 金シュ 仕様変更モデルNo.329
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiFutOpTaxDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMarginTaxDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用サービス起動リクエスト)<BR>
 * サービス利用サービス起動リクエストクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiExecRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiExecRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_exec";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151414L;
    
    /**
     * (ID)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * Token <BR>
     * （リテラクレア証券 MULTEX 専用）<BR>
     */
    public String token;
    
    /**
     * 信用口座区分 <BR>
     * 0:信用口座未開設 <BR>
     * 1:開設済み<BR>
     */
    public String marginTaxDiv;
    
    /**
     * 先物OP口座区分（大証）<BR> 
     * 0:先物OP口座未開設 <BR>
     * 1:オプション開設 <BR>
     * 2:先物開設 <BR>
     * 3:先物オプション開設<BR>
     */
    public String futOpTaxDiv;
    
    /**
     * (申込チェック区分)<BR> 
     * サービスを起動する際、顧客の申込状況チェックを行うかどうかを判定する。<BR> 
     * true:チェックを実施する <BR>
     * false:チェックを実施しない<BR>
     */
    public boolean applyCheckDiv;
    
    //オリックス.口座開設対応
    /**
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
    /**
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * タイプ<BR>
     */
    public String type;
    
    /**
     * SSID値<BR>
     */
    public String ssidValue;

    /**
     * モバイルフラグ<BR>
     * 1：モバイル<BR>
     * null：PC<BR>
     */
    public String mobileFlag;

    /**
     * (サービス利用サービス起動リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F77E1F013A
     */
    public WEB3SrvRegiExecRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用サービス起動レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F77EBC0224
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiExecResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) IDのチェック<BR>
     *  1-1) this.ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.IDの桁数が、2桁または4桁以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) 信用口座区分のチェック<BR> 
     * 2-1) this.信用口座区分==nullの場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01837<BR>
     * 2-2) this.信用口座区分が以下の値以外の場合、例外をスローする。<BR> 
　@　@ *     ・信用口座未開設 <BR>
     * 　@　@・開設済み <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01838<BR>
     * <BR>
     * 3) 先物OP口座区分（大証）のチェック<BR> 
     * 3-1) this.先物OP口座区分（大証）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01839<BR> 
     * 3-2) this.先物OP口座区分（大証）が以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・先物OP口座未開設 <BR>
     * 　@　@・オプション開設 <BR>
     * 　@　@・先物開設 <BR>
     * 　@　@・先物オプション開設<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01840<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 40F77EBC0205
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1-1) this.ID==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        //1-2) this.IDの桁数が、2桁または4桁以外の場合、例外をスローする。
        if (this.serviceDiv.length() != 2 &&
            this.serviceDiv.length() != 4)
        { 
            log.debug("******************************");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME);    
        }
        
        //2-1) this.信用口座区分==nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.marginTaxDiv))
        {
            log.debug("信用口座区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01837,
                getClass().getName() + STR_METHOD_NAME,
                "信用口座区分が未指定です。");   
        }
        
        //2-2) this.信用口座区分が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiMarginTaxDivDef.NOT_OPEN.equals(marginTaxDiv) && 
            !WEB3SrvRegiMarginTaxDivDef.OPEN.equals(marginTaxDiv))
        { 
            log.debug("信用口座区分が以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01838,
                getClass().getName() + STR_METHOD_NAME,
                "信用口座区分が存在しないコード値です。");     
        }
        
        //3-1) this.先物OP口座区分（大証）==nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.futOpTaxDiv))
        {
            log.debug("先物OP口座区分（大証）==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01839,
                getClass().getName() + STR_METHOD_NAME,
                "先物OP口座区分（大証）が未指定です。");   
        }
        
        //3-2) this.先物OP口座区分（大証）が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.OPTION_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.FUTURE_OPEN.equals(futOpTaxDiv) && 
            !WEB3SrvRegiFutOpTaxDivDef.FUTURE_OPTION_OPEN.equals(futOpTaxDiv))
        { 
            log.debug("先物OP口座区分（大証）が以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01840,
                getClass().getName() + STR_METHOD_NAME,
                "先物OP口座区分（大証）が存在しないコード値です。");     
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
