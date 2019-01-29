head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客一覧変更照会リクエスト(WEB3AdminSrvRegiCustomerReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2007/06/19 崔遠鵬(中訊) 仕様変更モデルNo.249
Revesion History : 2007/06/21 崔遠鵬(中訊) 仕様変更モデルNo.267
Revesion History : 2007/06/26 崔遠鵬(中訊) 仕様変更モデルNo.273
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者顧客一覧変更照会リクエスト)<BR>
 * サービス利用管理者顧客一覧変更照会リクエストクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerReferenceRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerReferenceRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (サービス利用ソートキー)<BR>
     * ≪抽選無の場合≫<BR>
     * 　@部店／顧客／申込属性区分／申込抽選区分／適用開始日／<BR>
     * 　@適用終了日／登録区分／利用料金／最終更新日／最終更新者"<BR>
     * <BR>
     * ≪抽選有の場合≫<BR>
     * 　@部店／顧客／申込抽選区分／申込日／<BR>
     * 　@適用開始日／適用終了日／登録区分／利用料金／<BR>
     * 　@最終更新日／最終更新者<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;
    
    /**
     * (部店コード)
     */
    public String[] branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (申込抽選区分)<BR>
     * 0:試用　@1:申込　@2:当選／本申込　@3:落選　@4:取消　@6:全て　@7:無料対象　@8:申込不可　@9:全て（属性用）<BR>
     */
    public String applyLotteryDiv;
    
    /**
     * (登録区分)<BR>
     * 0:有料　@1:無料　@2:全て<BR>
     */
    public String registDiv;
    
    /**
     * (適用開始日（自）)
     */
    public Date trialStartFrom;
    
    /**
     * (適用開始日（至）)
     */
    public Date trialStartTo;
    
    /**
     * (申込日（自）)
     */
    public Date applyDateFrom;
    
    /**
     * (申込日（至）)
     */
    public Date applyDateTo;
    
    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定 <BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * (サービス利用管理者顧客一覧変更照会リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE5A95014E
     */
    public WEB3AdminSrvRegiCustomerReferenceRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客一覧変更照会レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE5AD501FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerReferenceResponse();
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.サービス区分の桁数が、2桁以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) 部店コードのチェック<BR>
     *  2-1) this.部店コード!=nullであり、かつ桁数＞3桁の場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-2) this.顧客コード!=nullであり、かつthis.部店コード==nullの場合、<BR> 
     * 　@　@またはthis.部店コードの要素数==0の場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * 3) 顧客コードのチェック<BR>
     * 　@this.顧客コード!=nullであり、かつ桁数＞6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) 登録状況区分のチェック<BR>
     * 　@this.登録状況区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"有料"<BR>
     * 　@　@　@"無料"<BR>
     * 　@　@　@"全て"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>
     * <BR>
     * 5) 申込抽選区分のチェック<BR>
     * 　@this.申込抽選区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"試用"<BR>
     * 　@　@　@"申込"<BR>
     * 　@　@　@"当選（本申込）"<BR>
     * 　@　@　@"落選"<BR>
     * 　@　@　@"取消"<BR>
     * 　@　@　@"全て"<BR>
     * 　@　@　@"無料対象"<BR>
     * 　@　@　@"申込不可"<BR>
     * 　@　@　@"全て（属性用）"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00849<BR>
     * <BR>
     * 6) サービス利用ソートキーのチェック<BR>
     *  6-1) this.サービス利用ソートキー==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *  6-2) this.サービス利用ソートキーの要素数==0の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     *  6-3) this.サービス利用ソートキーの要素数分、以下を繰り返す。<BR>
     * 　@6-3-1) this.サービス利用ソートキー.キー項目==nullの場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * 　@6-3-2) this.サービス利用ソートキー.昇順／降順==nullの場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * 　@6-3-3) this.サービス利用ソートキー.昇順／降順が以下の値以外だった場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@"A:昇順"<BR>
     * 　@　@　@"D:降順"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * 7) 要求ページ番号チェック <BR>
     *  7-1) this.要求ページ番号==nullの値であれば例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     *  7-2) this.要求ページ番号が数字以外の値であれば例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 8) ページ内表示行数チェック <BR>
     *  8-1) this.ページ内表示行数==nullの値であれば例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *  8-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EE5AD50219
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) サービス区分のチェック
        // 1-1) this.サービス区分==nullの場合、例外をスローする。
        if(this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 1-2) this.サービス区分の桁数が、2桁以外の場合、例外をスローする。
        if(this.serviceDiv.length() != 2)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //2) 部店コードのチェック
        //2-1) this.部店コード!=nullであり、かつ桁数＞3桁の場合、例外をスローする。 
        if(this.branchCode != null)
        {
            int l_intBranchCount = this.branchCode.length;
            for (int i = 0; i < l_intBranchCount; i++)
            {
                if (this.branchCode[i] != null && this.branchCode[i].length() != 3)//U00871
                {
                    WEB3BaseException l_e = 
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("部店コードエラー.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }
        //2-2) this.顧客コード!=nullであり、かつthis.部店コード==nullの場合、またはthis.部店コードの要素数==0の場合、例外をスローする。 
        if (this.accountCode != null 
            && !"".equals(this.accountCode.trim())
            && (this.branchCode == null || this.branchCode.length == 0))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("部店コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //3) 顧客コードのチェック
        if(this.accountCode != null && !"".equals(this.accountCode.trim()) && this.accountCode.length() != 6)//U00871
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("顧客コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //4) 登録状況区分のチェック
        if(this.registDiv == null
            || "".equals(this.registDiv.trim())
            || !(WEB3SrvRegiRigistDivDef.CHARGE.equals(this.registDiv)
                || WEB3SrvRegiRigistDivDef.FREE.equals(this.registDiv)
                || WEB3SrvRegiRigistDivDef.EVERYTHING.equals(this.registDiv)))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01138,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("登録状況区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
            
        //5) 申込抽選区分のチェック
        if(this.applyLotteryDiv == null 
            || "".equals(this.applyLotteryDiv.trim())
            || !(WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(this.applyLotteryDiv)))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00849,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("申込抽選区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //6) サービス利用ソートキーのチェック
        // 6-1) this.サービス利用ソートキー==nullの場合、例外をスローする。
        if(this.sortKeys == null)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス利用ソートキーエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 6-2) this.サービス利用ソートキーの要素数==0の場合、例外をスローする。
        if(this.sortKeys.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス利用ソートキーエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 6-3) this.サービス利用ソートキーの要素数分、以下を繰り返す。
        WEB3SrvRegiSortKey l_sortKey = null;
        int l_intSortKeyCount = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeyCount; i++)
        {
            l_sortKey = sortKeys[i]; 

            //　@6-3-1) this.サービス利用ソートキー.キー項目==nullの場合、
            if(l_sortKey.keyItem == null || "".equals(l_sortKey.keyItem.trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("サービス利用ソートキーエラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            //　@6-3-2) this.サービス利用ソートキー.昇順／降順==nullの場合、
            if(l_sortKey.ascDesc == null || "".equals(l_sortKey.ascDesc.trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("サービス利用ソートキーエラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            //　@6-3-3) this.サービス利用ソートキー.昇順／降順が以下の値以外だった場合、
            if(!WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc)
                && !WEB3AscDescDef.DESC.equals(l_sortKey.ascDesc))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("サービス利用ソートキーエラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        //7) 要求ページ番号チェック 
        // 7-1) this.要求ページ番号==nullの値であれば例外をスローする。 
        if(this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("要求ページ番号エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 7-2) this.要求ページ番号が数字以外の値であれば例外をスローする。 
        if(!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("要求ページ番号エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //8) ページ内表示行数チェック 
        // 8-1) this.ページ内表示行数==nullの値であれば例外をスローする。 
        if(this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("ページ内表示行数エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 8-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。 
        if(!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("ページ内表示行数エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
