head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データダウンロードリクエスト(WEB3AdminSrvRegiDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2005/04/04 内田 亨(SRA) 部店コード3桁チェック対応
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客データダウンロードリクエスト)<BR>
 * サービス利用管理者ダウンロードリクエストクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiDownloadRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiDownloadRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
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
     * 0:試用　@1:申込　@2:当選（本申込）　@3:落選　@4:取消　@6:全て<BR>
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
     * (サービス利用ソートキー)<BR>
     * 対象項目≪抽選無の場合≫"顧客","適用開始日"<BR>
     * 　@　@　@　@　@　@≪抽選有の場合≫"顧客","申込日"<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;
    
    /**
     * (サービス利用管理者ダウンロードリクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4100634E03A4
     */
    public WEB3AdminSrvRegiDownloadRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者ダウンロードレスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 410063BE00F5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiDownloadResponse();
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
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
     *  2-1) this.部店コード==nullの場合、例外をスローする。  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-2) this.部店コードの桁数!=3桁の場合、例外をスローする。 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * 3) 顧客コードのチェック<BR>
     * 　@this.顧客コード!=nullであり、かつ桁数＞6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) 登録状況区分のチェック<BR>
     * 4-1) this.登録状況区分==nullの場合、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01137<BR>
     * 4-2)　@this.登録状況区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"有料"<BR>
     * 　@　@　@"無料"<BR>
     * 　@　@　@"全て"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>
     * <BR>
     * 5) 申込抽選区分のチェック<BR>
     * 5-1) this.申込抽選区分==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01905<BR>
     * 5-2)　@this.申込抽選区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"試用"<BR>
     * 　@　@　@"申込"<BR>
     * 　@　@　@"当選（本申込）"<BR>
     * 　@　@　@"落選"<BR>
     * 　@　@　@"取消"<BR>
     * 　@　@　@"全て"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00849<BR>
     * 6) サービス利用ソートキーのチェック <BR>
     * 　@this.サービス利用ソートキー==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410063BE0104
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1)サービス区分のチェック
        //1-1) this.サービス区分==null
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
        //1-2) this.サービス区分
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
        //2-1) this.部店コード==nullの場合、例外をスローする。 
        if(this.branchCode == null || this.branchCode.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("部店コード==nullである.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2-2)this.部店コードの桁数!=3桁の場合、例外をスローする。 
        int l_intBranchCount = this.branchCode.length;
        for (int i = 0; i < l_intBranchCount; i++)
        {
            if(this.branchCode[i] == null || "".equals(this.branchCode[i].trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("部店コード==nullである.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
            
            if (this.branchCode[i].length() != 3)
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
        
        //3) 顧客コードのチェック
        if(this.accountCode != null && !"".equals(this.accountCode.trim()) && this.accountCode.length() > 6)
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
        //4-1)this.登録状況区分==nullの場合、例外をスローする
        if(this.registDiv == null || "".equals(this.registDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01137,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("登録状況区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        //4-2)this.登録状況区分が以下の値以外の場合、例外をスローする
        if(!(WEB3SrvRegiRigistDivDef.CHARGE.equals(this.registDiv)
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
        //5-1) this.申込抽選区分==nullの場合、例外をスローする。 
        if(this.applyLotteryDiv == null || "".equals(this.applyLotteryDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01905,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("申込抽選区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        //5-2)　@this.申込抽選区分が以下の値以外の場合、例外をスローする。
        if(!(WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(this.applyLotteryDiv)))
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
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス利用ソートキーが未指定です", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);        
    }
}
@
