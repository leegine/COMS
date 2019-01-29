head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡メール送信サービス実装クラス(WEB3AioCashinNoticeMailSendServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 周勇 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー 
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeMailSendService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MailSendDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡メール送信サービスImpl)<BR>
 * 入金連絡メール送信サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeMailSendServiceImpl 
    implements WEB3AioCashinNoticeMailSendService 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeMailSendServiceImpl.class);

    /**
     * (createメール)<BR>
     * 入金連絡メール行をDBに登録する。 <BR>
     * <BR>
     * １）メールテーブルからレコードを取得する。 <BR>
     *<BR> 
     *  [条件] <BR>
     *      証券会社コード = 引数.入金連絡Params.証券会社コード <BR>
     *      送信メール区分 = "0701" <BR>
     *      識別ID = "----" <BR>
     * <BR>
     * ２）メール送信行オブジェクトを作成し、DBに挿入する。 <BR>
     * <BR>
     * メール送信行オブジェクトの編集内容は、 <BR>
     * DB更新仕様「入金連絡_メール送信テーブル.xls」 を参照。 <BR>
     * <BR>
     * @@param l_depositInformParams - (入金連絡行)<BR>
     * 入金連絡行オブジェクト<BR>
     * @@param l_emailAddress - (メールアドレス)<BR>
     * 入金連絡行オブジェクト<BR>
     * @@throws WEB3SystemLayerException
     * @@param l_mail - (メールアドレス)<BR>
     * メールアドレス<BR>
     * @@roseuid 40EE8D850176
     */
    public void createMail(
            DepositInformParams l_depositInformParams, 
            String l_strEmailAddress) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createMail(DepositInformParams l_depositInformParams, " +
            "String l_strEmailAddress) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_depositInformParams == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lisMailInfoRows = null;
        FinInstitutionRow l_finInstitutionRow = null;
        MainAccountRow l_mainAccountRow = null;
        try
        {
            String l_strWhereClause = "institution_code = ? " +
                    "and sendmail_div = ? and discernment_id = ?";
            Object l_bindVars[] = {
                    l_depositInformParams.getInstitutionCode(), 
                    WEB3SendmailDivDef.AIO_DEPOSIT_INFORM,
                    "----"};
            
            //１）メールテーブルからレコードを取得する。 
            //[条件] 
            //証券会社コード = 引数.入金連絡Params.証券会社コード 
            //送信メール区分 = "0701" 
            //識別ID = "----" 
            
            l_lisMailInfoRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    MailInfoRow.TYPE,
                    l_strWhereClause,                    
                    null,
                    l_bindVars);
            
            // 入金連絡.金融機@関コードに対応する金融機@関テーブル
            l_finInstitutionRow = FinInstitutionDao.findRowByPk(
                l_depositInformParams.getInstitutionCode(),
                l_depositInformParams.getFinInstitutionCode());
            
            // 入金連絡.証券会社コード、部店コード、顧客コードから取得した顧客オブジェクト
            l_mainAccountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_depositInformParams.getInstitutionCode(),
                l_depositInformParams.getBranchCode(),
                l_depositInformParams.getAccountCode());
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MailInfoRow l_mailInfoRow = (MailInfoRow) l_lisMailInfoRows.get(0);
        
        MailProcParams l_mailProcParams = new MailProcParams();
        
        //証券会社コード
        l_mailProcParams.setInstitutionCode(l_depositInformParams.getInstitutionCode());
        
        //部店コード
        l_mailProcParams.setBranchCode(l_depositInformParams.getBranchCode());
        
        //送信メール区分
        l_mailProcParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
        
        //識別ID
        l_mailProcParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
        
        //顧客コード
        l_mailProcParams.setAccountCode(l_depositInformParams.getAccountCode());
        
        //メールID
        l_mailProcParams.setMailId(Long.parseLong(l_depositInformParams.getOrderRequestNumber()));
        
        //年月日１
        l_mailProcParams.setDate1(l_depositInformParams.getTransferDate());
             
        //年月日２
        l_mailProcParams.setDate2(null);
        
        //年月日３
        l_mailProcParams.setDate3(null);
        
        //年月日４
        l_mailProcParams.setDate4(null);
        
        //数量
        l_mailProcParams.setQuantity(null);
        
        //金額
        l_mailProcParams.setAmount(l_depositInformParams.getAmount());
        
        //ID
        l_mailProcParams.setOrderId(null);
        
        //区分
        l_mailProcParams.setDivision(null);
        
        //名称1
        //入金連絡.金融機@関コードに対応する金融機@関名
        //※金融機@関テーブル.金融機@関名（漢字）
        l_mailProcParams.setName1(l_finInstitutionRow.getFinInstitutionNameKanji());
        
        //名称2
        //入金連絡.証券会社コード、部店コード、顧客コードから取得した顧客オブジェクトのget顧客表示名()にて得られた顧客名
        l_mailProcParams.setName2(l_mainAccountRow.getFamilyName());
        
        //電子メール送信ステイタス
        l_mailProcParams.setStatus(WEB3MailSendDef.NOT_ENFORCEMENT);
        
        //電子メール送信日時
        l_mailProcParams.setSendProcessDateTime(null);
        
        //再送区分
        l_mailProcParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
        
        //電子メール再送日時
        l_mailProcParams.setResendProcessDateTime(null);
        
        //emailアドレス
        l_mailProcParams.setEmailAddress(null);
        
        //送信emailアドレス
        l_mailProcParams.setSendEmailAddress(l_strEmailAddress);
        
        //件名
        l_mailProcParams.setSubject(null);
        
        //メール本文
//        l_mailProcParams.setMailText(null);
        
        //削除フラグ
        l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
        
        //作成日時
        l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日時
        l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            //入金連絡メール送信テーブルDBに挿入
            WEB3DataAccessUtility.insertRow(l_mailProcParams);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
}@
