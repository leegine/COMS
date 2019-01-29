head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス登録共通リクエスト(WEB3AdminSrvRegiServiceRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2009/05/27 柴双紅(中訊) 仕様変更モデル424
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者サービス登録共通リクエスト)<BR>
 * サービス利用管理者サービス登録共通リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151450L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (摘要)
     */
    public String summary;
    
    /**
     * (抽選区分)<BR>
     * 0:無　@1:有<BR>
     */
    public String lotteryDiv;
    
    /**
     * (申込区分)<BR>
     * 0:不要　@1:要<BR>
     */
    public String applyDiv;
    
    /**
     * (メール区分（確認メール）)<BR>
     * 0:送信しない　@1:送信する<BR>
     */
    public String confirmMailDiv;
    
    /**
     * (メール区分（契約期限メール）)<BR>
     * 0:送信しない　@1:送信する<BR>
     */
    public String noticeMailDiv;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (第２URL)
     */
    public String url2;
    
    /**
     * (ハッシュ計算方式区分)<BR>
     * 0:指定無 <BR>
     * 1:MD2 <BR>
     * 2:MD5 <BR>
     * 3:SHA-1 <BR>
     * 4:SHA-256 <BR>
     * 5:SHA-384 <BR>
     * 6:SHA-512<BR>
     */
    public String hashCalHowToDiv;
    
    /**
     * (ハッシュ計算手順区分)<BR>
     * 0:指定無 <BR>
     * 1:電子鳩 <BR>
     * 2:通常計算（１）<BR> 
     * 3:通常計算（２） <BR>
     * 4:２段階計算<BR>
     * 5:ログイン認証<BR>
     * 7:シングルサインオン連携<BR>
     */
    public String hashCalOrderDiv;
    
    /**
     * (送信方法@区分)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST<BR> 
     * 3:特殊（１）－リテラクレア証券 MULTEX 専用<BR> 
     * 4:特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (送信パラメータ区分)<BR>
     * 0:無 <BR>
     * 1:有<BR>
     */
    public String sendParamDiv;
    
    /**
     * (暗号化顧客コード区分)<BR>
     * 0:無 <BR>
     * 1:有<BR>
     */
    public String cryptAccountCodeDiv;
    
    /**
     * (ハッシュ値一覧)<BR>
     */
    public WEB3SrvRegiExecKey[] hashList;
    
    /**
     * (送信パラメータ一覧)<BR>
     */
    public WEB3SrvRegiExecKey[] paramList;
    
    /**
     * (サービス利用管理者サービス登録共通リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F255EF03B8
     */
    public WEB3AdminSrvRegiServiceRegistCommonRequest() 
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
     *  1-2) this.申込区分=="要"であり、this.サービス区分の桁数!=2桁の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00880<BR>
     *  1-3) this.申込区分=="不要"であり、this.サービス区分の桁数!=4桁の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00881<BR>
     *  1-4) this.サービス区分に数値以外が格納されている場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00882<BR>
     * <BR>
     * 2) サービス名称のチェック<BR>
     *  2-1) this.サービス名称==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00883<BR>
     *  2-2) 1文字≦this.サービス名称の桁数≦100Byteではない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00884<BR>
     * <BR>
     * 3) 摘要のチェック<BR>
     *  3-1) this.申込区分=="要"であり、かつthis.摘要==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00885<BR>
     *  3-2) this.申込区分=="不要"であり、かつthis.摘要!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00886<BR>
     *  3-3) this.摘要に半角カナ以外がセットされている場合、例外をスローする。(*1)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00887<BR>
     *  3-4) this.摘要の文字数≦25文字ではない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00888<BR>
     * <BR>
     * 4) 申込区分のチェック<BR>
     *  4-1) this.申込区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00891<BR>
     *  4-2) this.申込区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"要"<BR>
     * 　@　@　@"不要"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00892<BR>
     * <BR>
     * 5) 抽選区分のチェック<BR>
     *  5-1) this.申込区分=="要"であり、かつthis.抽選区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00895<BR>
     *  5-2) this.抽選区分==null以外であり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"無"<BR>
     * 　@　@　@"有"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00896<BR>
     * <BR>
     * 4) メール区分（確認メール）のチェック<BR>
     *  4-1) this.メール区分（確認メール）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00956<BR>
     *  4-2) this.メール区分（確認メール）が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"送信しない"<BR>
     * 　@　@　@"送信する"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00927<BR>
     * <BR>
     * 5) メール区分（契約期限メール）のチェック<BR>
     *  5-1) this.メール区分（契約期限メール）==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00928<BR>
     *  5-2) this.メール区分（契約期限メール）が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"送信しない"<BR>
     * 　@　@　@"送信する"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00929<BR>
     * <BR>
     * 6) ハッシュ計算方式区分のチェック<BR> 
     * 6-1) this.ハッシュ計算方式区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01841<BR> 
     * 6-2) this.ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・指定無 <BR>
     * 　@　@・MD2 <BR>
     * 　@　@・MD5 <BR>
     * 　@　@・SHA-1 <BR>
     * 　@　@・SHA-256 <BR>
     * 　@　@・SHA-384 <BR>
     * 　@　@・SHA-512 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01842<BR>
     * <BR>
     * 7) ハッシュ計算手順区分のチェック<BR> 
     * 7-1) this.ハッシュ計算手順区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01843<BR>
     * 7-2) this.ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・指定無 <BR>
     * 　@　@・電子鳩 <BR>
     * 　@　@・通常計算（１）<BR> 
     * 　@　@・通常計算（２） <BR>
     * 　@　@・２段階計算 <BR>
     * 　@　@・ログイン認証 <BR>
     * 　@　@・シングルサインオン連携<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01844<BR>
     * <BR>
     * 8) 送信方法@区分のチェック<BR> 
     * 8-1) this.送信方法@区分==nullの場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01845<BR>
     * 8-2) this.送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・GET <BR>
     * 　@　@・POST <BR>
     * 　@　@・HTTP-REQUEST<BR> 
     * 　@　@・特殊（１）－リテラクレア証券 MULTEX 専用<BR> 
     * 　@　@・特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01846<BR>
     * <BR>
     * 9) 送信パラメータ区分のチェック<BR> 
     * 9-1) this.送信パラメータ区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01847<BR>
     * 9-2) this.送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・無 <BR>
     * 　@　@・有 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01848<BR>
     * <BR>
     * 10) 暗号化顧客コード区分のチェック<BR> 
     * 10-1) this.暗号化顧客コード区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01849<BR>
     * 10-2) this.暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@・無 <BR>
     * 　@　@・有 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01850<BR>
     * <BR>
     * 11) ハッシュ値一覧のチェック<BR> 
     * 　@ハッシュ値一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR> 
     * 11-1) ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01851<BR> 
     * 11-2) ハッシュ値一覧.利用キー==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01852<BR>
     * <BR>
     * 12) 送信パラメータ一覧のチェック<BR> 
     * 　@送信パラメータ一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR> 
     * 12-1) 送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01853<BR>
     * 12-2) 送信パラメータ一覧.利用キー==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01854<BR>
     * <BR>
     * 13) ハッシュ計算方式区分、ハッシュ計算手順区分のチェック<BR> 
     * 13-1) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ計算手順区分="指定無"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * 　@　@の場合、例外をスローする。 <BR>
     * 13-2) this.ハッシュ計算方式区分="指定無"であり、かつthis.ハッシュ計算手順区分!="指定無"<BR> 
     * 　@　@の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 40F4DC3D018F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1-1) this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("this.サービス区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }
                
        //1-2) this.申込区分=="要"であり、this.サービス区分の桁数!=2桁の場合、
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }    

        //1-3) this.申込区分=="不要"であり、this.サービス区分の桁数!=4桁の場合、
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
        this.serviceDiv.length() != 4)
        {
            log.debug("1-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }    

        //1-4) this.サービス区分に数値以外が格納されている場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.serviceDiv))
        {
            log.debug("1-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2) サービス名称のチェック
        //2-1) this.サービス名称==nullの場合、例外をスローする。
        if (this.serviceName == null || "".equals(serviceName.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00883,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2-2) 1文字≦this.サービス名称の桁数≦100Byteではない場合、例外をスローする。
        if (this.serviceName.length() < 1 ||
            WEB3StringTypeUtility.getByteLength(this.serviceName) > 100)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00884,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3) 摘要のチェック
        //3-1) this.申込区分=="要"であり、かつthis.摘要==nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.summary == null || "".equals(summary.trim())))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00885,
                getClass().getName() + STR_METHOD_NAME);     
        }

        // 3-2) this.申込区分=="不要"であり、かつthis.摘要!=nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.summary != null && summary.trim().length() != 0))
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00886,
                getClass().getName() + STR_METHOD_NAME);     
        }

        //3-3) this.摘要に半角カナ以外がセットされている場合、例外をスローする。
        if ((this.summary != null && summary.trim().length() != 0) && !WEB3StringTypeUtility.isSingle(this.summary))
        {
            log.debug("3-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00887,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-4) this.摘要の文字数≦25文字ではない場合、例外をスローする。
        if ((this.summary != null && summary.trim().length() != 0) && this.summary.length() > 25)
        {
            log.debug("3-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00888,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) 申込区分のチェック
        //4-1) this.申込区分==nullの場合、例外をスローする。
        if (this.applyDiv == null || "".equals(applyDiv.trim()))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00891,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4-2) this.申込区分が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv))
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00892,
                getClass().getName() + STR_METHOD_NAME);    
        }

        //5) 抽選区分のチェック
        //5-1) this.申込区分=="要"であり、かつthis.抽選区分==nullの場合、例外をスローする。
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.lotteryDiv == null || "".equals(lotteryDiv.trim())))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00895,
                getClass().getName() + STR_METHOD_NAME);      
        }

        //5-2) this.抽選区分==null以外であり、かつ以下の値以外の場合、例外をスローする。
        if ((this.lotteryDiv != null && lotteryDiv.trim().length() != 0) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                getClass().getName() + STR_METHOD_NAME);  
        }

        //4) メール区分（確認メール）のチェック
        //4-1) this.メール区分（確認メール）==nullの場合、例外をスローする。
        if (this.confirmMailDiv == null || "".equals(confirmMailDiv.trim()))
        {
            log.debug("this.メール区分（確認メール）==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00956,
                getClass().getName() + STR_METHOD_NAME);  
        }

        //4-2) this.メール区分（確認メール）が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.confirmMailDiv) &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.confirmMailDiv))
        {
            log.debug("this.メール区分（確認メール）が以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00927,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) メール区分（契約期限メール）のチェック
        //5-1) this.メール区分（契約期限メール）==nullの場合、例外をスローする。
        if (this.noticeMailDiv == null || "".equals(noticeMailDiv.trim()))
        {
            log.debug("this.メール区分（契約期限メール）==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00928,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5-2) this.メール区分（契約期限メール）が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv) &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.noticeMailDiv))
        {
            log.debug("this.メール区分（契約期限メール）が以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00929,
                getClass().getName() + STR_METHOD_NAME);
        } 
        
        //* 6) ハッシュ計算方式区分のチェック<BR> 
        //* 6-1) this.ハッシュ計算方式区分==nullの場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv))
        {
            log.debug("ハッシュ計算方式区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01841,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算方式区分==nullの場合、例外をスローする"); 
        }
        
        //* 6-2) this.ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
        //* 　@　@・指定無 <BR>
        //* 　@　@・MD2 <BR>
        //* 　@　@・MD5 <BR>
        //* 　@　@・SHA-1 <BR>
        //* 　@　@・SHA-256 <BR>
        //* 　@　@・SHA-384 <BR>
        //* 　@　@・SHA-512 <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) && 
            !WEB3HashCalHowToDivDef.MD2.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD5.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_1.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_256.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_384.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_512.equals(this.hashCalHowToDiv)) 
        {
            log.debug("ハッシュ計算方式区分!=nullであり、かつ以下の値以外の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01842,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算方式区分の値が不正");     
        }

        //* 7) ハッシュ計算手順区分のチェック<BR> 
        //* 7-1) this.ハッシュ計算手順区分==nullの場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv))
        {
            log.debug("ハッシュ計算手順区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01843,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算手順区分==nullの場合、例外をスローする"); 
        } 
        //* 7-2) this.ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
        //* 　@　@・指定無 <BR>
        //* 　@　@・電子鳩 <BR>
        //* 　@　@・通常計算（１）<BR> 
        //* 　@　@・通常計算（２） <BR>
        //* 　@　@・２段階計算 <BR>
        //* 　@　@・ログイン認証 <BR>
        //* 　@　@・シングルサインオン連携<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(this.hashCalOrderDiv))
        {
            log.debug("ハッシュ計算手順区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01844,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算手順区分の値が不正"); 
        }

        //* 8) 送信方法@区分のチェック<BR> 
        //* 8-1) this.送信方法@区分==nullの場合、例外をスローする。<BR> 
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv))
        {
            log.debug("送信方法@区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01845,
                getClass().getName() + STR_METHOD_NAME,
                "送信方法@区分==nullの場合、例外をスローする"); 
        } 
        
        //* 8-2) this.送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
        //* 　@　@・GET <BR>
        //* 　@　@・POST <BR>
        //* 　@　@・HTTP-REQUEST<BR> 
        //* 　@　@・特殊（１）－リテラクレア証券 MULTEX 専用<BR> 
        //* 　@　@・特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv) && 
            !WEB3SrvRegiSendHowToDivDef.GET.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.POST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.HTTP_REQUEST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL2.equals(this.sendHowToDiv))
        {
            log.debug("送信方法@区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01846,
                getClass().getName() + STR_METHOD_NAME,
                "送信方法@区分の値が不正"); 
        } 

        //* 9) 送信パラメータ区分のチェック<BR> 
        //* 9-1) this.送信パラメータ区分==nullの場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv))
        {
            log.debug("送信パラメータ区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01847,
                getClass().getName() + STR_METHOD_NAME,
                "送信パラメータ区分==nullの場合、例外をスローする"); 
        } 
        
        //* 9-2) this.送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
        //* 　@　@・無 <BR>
        //* 　@　@・有 <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
        {
            log.debug("送信パラメータ区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01848,
                getClass().getName() + STR_METHOD_NAME,
                "送信パラメータ区分の値が不正"); 
        } 

        // * 10) 暗号化顧客コード区分のチェック<BR> 
        //* 10-1) this.暗号化顧客コード区分==nullの場合、例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv))
        {
            log.debug("暗号化顧客コード区分==nullの場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01849,
                getClass().getName() + STR_METHOD_NAME,
                "暗号化顧客コード区分==nullの場合、例外をスローする"); 
        } 
        
        //* 10-2) this.暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR> 
        //* 　@　@・無 <BR>
        //* 　@　@・有 <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.cryptAccountCodeDiv))
        {
            log.debug("暗号化顧客コード区分!=nullであり、かつ以下の値以外の場合、例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01850,
                getClass().getName() + STR_METHOD_NAME,
                "暗号化顧客コード区分の値が不正"); 
        } 

        //* 11) ハッシュ値一覧のチェック<BR> 
        //* 　@ハッシュ値一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR> 
        if (this.hashList != null && this.hashList.length > 0)
        {
            int l_int = this.hashList.length;
            for (int i = 0; i < l_int; i++)
            {
                //11-1) ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする。 <BR>
                if (hashList[i].keyKindDiv == null)
                {
                    log.debug("ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01851,
                        getClass().getName() + STR_METHOD_NAME,
                        "ハッシュ値一覧.利用キー種別区分==nullの場合、例外をスローする"); 
                }
                
                //* 11-2) ハッシュ値一覧.利用キー==nullの場合、例外をスローする。 <BR>
                if (hashList[i].key == null)
                {
                    log.debug("ハッシュ値一覧.利用キー==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01852,
                        getClass().getName() + STR_METHOD_NAME,
                        "ハッシュ値一覧.利用キー==nullの場合、例外をスローする"); 
                }
                
                //障害対応 NO_U01987
				//11-3)ハッシュ値一覧.利用キーのバイト数が256バイト数以上の場合、例外をスローする。<BR>
				if ((hashList[i].key != null && hashList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(hashList[i].key) > 256)
				{
					log.debug("11-3) ハッシュ値一覧.利用キー＞256byte以上");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01831,
						getClass().getName() + STR_METHOD_NAME);
				}
            }

        }

        //* 12) 送信パラメータ一覧のチェック<BR> 
        //* 　@送信パラメータ一覧!=nullであり、かつ要素数>0件の場合、以下のチェックを実施する。<BR>
        if (this.paramList != null && this.paramList.length > 0)
        { 
            int l_int = this.paramList.length;
            for (int i = 0; i < l_int; i++)
            {
                //* 12-1) 送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする。 <BR>
                if (paramList[i].keyKindDiv == null)
                {
                    log.debug("送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01853,
                        getClass().getName() + STR_METHOD_NAME,
                        "送信パラメータ一覧.利用キー種別区分==nullの場合、例外をスローする"); 
                }
                
                //* 12-2) 送信パラメータ一覧.利用キー==nullの場合、例外をスローする。 <BR>
                if (paramList[i].key == null)
                {
                    log.debug("送信パラメータ一覧.利用キー==nullの場合、例外をスローする");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01854,
                        getClass().getName() + STR_METHOD_NAME,
                        "送信パラメータ一覧.利用キー==nullの場合、例外をスローする"); 
                }
                
				//障害対応 NO_U01987
                //12-3)送信パラメータ一覧.利用キーのバイト数が256バイト数以上の場合、例外をスローする。<BR>
				if ((paramList[i].key != null && paramList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(paramList[i].key) > 256)
				{
					log.debug("12-3) 送信パラメータ一覧.利用キー＞256byte以上");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01859,
						getClass().getName() + STR_METHOD_NAME);
				}
            }
        }

        //* 13) ハッシュ計算方式区分、ハッシュ計算手順区分のチェック<BR> 
        // * 13-1) this.ハッシュ計算方式区分!="指定無"であり、かつthis.ハッシュ計算手順区分="指定無"<BR> 
        if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
        {
            log.debug("ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ計算手順区分=指定無");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算方式区分!=指定無であり、かつthis.ハッシュ計算手順区分=指定無"); 
        }
        
        //13-2) this.ハッシュ計算方式区分="指定無"であり、かつthis.ハッシュ計算手順区分!="指定無"<BR> 
        if (WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
        {
            log.debug("ハッシュ計算方式区分=指定無であり、かつthis.ハッシュ計算手順区分!=指定無");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
                getClass().getName() + STR_METHOD_NAME,
                "ハッシュ計算方式区分=指定無であり、かつthis.ハッシュ計算手順区分!=指定無"); 
        }
        
        //障害対応 NO_U01985
        //14-1)this.URLのバイト数が256バイト数以上の場合、例外をスローする。<BR>
		if ((url != null && url.trim().length() != 0) && WEB3StringTypeUtility.getByteLength(url) > 256)
		{
			log.debug("14-1) URL ＞ 256byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
		
		//障害対応 NO_U01985
		//15-1)this.URL2のバイト数が256バイト数以上の場合、例外をスローする。<BR>
		if ((url2 != null && url2.trim().length() != 0) && WEB3StringTypeUtility.getByteLength(url2) > 256)
		{
			log.debug("15-1) URL2 ＞ 256byte以上");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F46E903C8
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
