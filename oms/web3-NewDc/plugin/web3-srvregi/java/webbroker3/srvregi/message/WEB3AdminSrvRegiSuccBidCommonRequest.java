head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSuccBidCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス落札額更新共通リクエスト(WEB3AdminSrvRegiSuccBidCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者サービス落札額更新共通リクエスト)<BR>
 * サービス利用管理者サービス落札額更新共通リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSuccBidCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiSuccBidCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_succBidCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151452L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (抽選情報ID)<BR>
     * 通番<BR>
     */
    public String lotteryId;
    
    /**
     * (最高落札額)
     */
    public String maxSuccBidding;
    
    /**
     * (最低落札額)
     */
    public String minSuccBidding;
    
    /**
     * (加重平均額)
     */
    public String weightedAverage;
    
    /**
     * (サービス利用管理者サービス落札額更新共通リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F4EEA80279
     */
    public WEB3AdminSrvRegiSuccBidCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.サービス区分の桁数!=2桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) 抽選情報IDのチェック<BR>
     * 　@this.抽選情報ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00957<BR>
     * <BR>
     * 3) 最高落札額のチェック<BR>
     *  3-1) this.最高落札額!=nullであり、this.最高落札額に数値以外がセットされていた場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00958<BR>
     *  3-2) this最高落札額の桁数＞9桁の場合,例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00959<BR>
     * <BR>
     * 4) 最低落札額のチェック<BR>
     *  4-1) this.最低落札額!=nullであり、this.最低落札額に数値以外がセットされていた場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00960<BR>
     *  4-2) this最高落札額の桁数＞9桁の場合,例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00961<BR>
     * <BR>
     * 5) 加重平均額のチェック<BR>
     *  5-1) this.加重平均額!=nullであり、this.加重平均額に数値以外がセットされていた場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00962<BR>
     *  5-2) this最高落札額の桁数＞9桁の場合,例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00974<BR>
     * <BR>
     * 6) 最高落札額・最低落札額のチェック<BR>
     * 　@this.最高落札額とthis.最低落札額の両方!=nullであり、かつ<BR>
     * 　@this.最高落札額＜this.最低落札額の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00975<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4EEC003C1
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス区分のチェック
        //1-1) this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //1-2) this.サービス区分の桁数!=2桁の場合、例外をスローする。
        if (this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }

        // 2) 抽選情報IDのチェック
        //this.抽選情報ID==nullの場合、例外をスローする。
        if (this.lotteryId == null || lotteryId.trim().length() == 0)
        {
            log.debug("2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00957,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //Bug No:1434
        //3) 最高落札額のチェック
        //3-1) this.最高落札額!=nullであり、this.最高落札額に数値以外がセットされていた場合、例外をスローする。
        if (this.maxSuccBidding != null && !WEB3StringTypeUtility.isNumber(this.maxSuccBidding))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00958,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-2) this最高落札額の桁数＞9桁の場合,例外をスローする。
        if (this.maxSuccBidding != null && this.maxSuccBidding.length() > 9)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00959,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) 最低落札額のチェック
        //4-1) this.最低落札額!=nullであり、this.最低落札額に数値以外がセットされていた場合、例外をスローする。
        if (this.minSuccBidding != null && !WEB3StringTypeUtility.isNumber(this.minSuccBidding))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00960,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //4-2) this最高落札額の桁数＞9桁の場合,例外をスローする。
        if (this.minSuccBidding != null && this.minSuccBidding.length() > 9)
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00961,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) 加重平均額のチェック
        //5-1) this.加重平均額!=nullであり、this.加重平均額に数値以外がセットされていた場合、例外をスローする。
        if (this.weightedAverage != null && !WEB3StringTypeUtility.isNumber(this.weightedAverage))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00962,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //5-2) this最高落札額の桁数＞9桁の場合,例外をスローする
        if (this.weightedAverage != null && this.weightedAverage.length() > 9)
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00974,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) 最高落札額・最低落札額のチェック
        //this.最高落札額とthis.最低落札額の両方!=nullであり、かつ
        //this.最高落札額＜this.最低落札額の場合、例外をスローする。
        if ((this.maxSuccBidding != null && maxSuccBidding.trim().length() != 0) &&
            (this.minSuccBidding != null && minSuccBidding.trim().length() != 0) &&
            Integer.parseInt(this.maxSuccBidding) < Integer.parseInt(this.minSuccBidding))
        {
            log.debug("6)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00975,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F466D031C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
