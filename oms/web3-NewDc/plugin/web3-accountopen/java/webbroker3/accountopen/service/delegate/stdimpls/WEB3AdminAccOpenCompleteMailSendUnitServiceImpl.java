head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設完了メール送信UnitServiceImpl(WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設完了メール送信UnitServiceImpl)<BR>
 * 口座開設完了メール送信UnitService実装クラス<BR>
 * （トランザクション属性=TX_CREATE_NEW）<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendUnitServiceImpl implements WEB3AdminAccOpenCompleteMailSendUnitService 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B45E7101D4
     */
    public WEB3AdminAccOpenCompleteMailSendUnitServiceImpl() 
    {
     
    }
    
    /**
     * (sendMailProcess)<BR>
     * 指定顧客に口座開設完了メールを送信する。<BR>
     * <BR>
     * １）　@メール送信テーブル更新<BR>
     * 　@DB更新仕様の通りに、メール送信テーブルに行を追加（DB-insert）する。<BR>
     * <BR>
     * 　@DB更新仕様「完了メール送信_メール送信テーブル.xls」参照<BR>
     * <BR>
     * ２）　@顧客マスタ更新<BR>
     * 　@DB更新仕様の通りに、顧客マスタを更新（DB-update）する。<BR>
     * <BR>
     * 　@DB更新仕様「完了メール送信_顧客マスタ.xls」参照<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * @@roseuid 41A54BFB00BC
     */
    public void sendMailProcess(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " sendMailProcess(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //１）メール送信テーブル更新
        MailProcParams l_mailProcParams = new MailProcParams();
        
        MainAccountParams l_mainAccountParams = new MainAccountParams((MainAccountParams)l_mainAccount.getDataSourceObject());        
        
        //証券会社コード
        l_mailProcParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        
        //部店コード
        l_mailProcParams.setBranchCode(l_mainAccountParams.getBranchCode());
        
        //送信メール区分  口座開設完了メール（0202）
        l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL);
        
        //識別ID
        l_mailProcParams.setDiscernmentId("----");
        
        //口座コード
        l_mailProcParams.setAccountCode(l_mainAccountParams.getAccountCode());
        
        //メールID
        //証券会社コード，部店コード，口座コード，
        //送信メール区分にて既存データがある場合は既存データの最大値＋１。以外、0。
        l_mailProcParams.setMailId(0);
        
        //年月日１
        l_mailProcParams.setDate1(l_mainAccountParams.getAccountOpenDate());
        
        //年月日２
        l_mailProcParams.setDate2(null);
        
        //年月日３
        l_mailProcParams.setDate3(null);
        
        //年月日４
        l_mailProcParams.setDate4(null);
        
        //数量
        l_mailProcParams.setQuantity(null);
        
        //金額
        l_mailProcParams.setAmount(null);
        
        //ID
        l_mailProcParams.setOrderId(null);
        
        //区分
        l_mailProcParams.setDivision(null);
        
        //電子メール送信ステイタス
        l_mailProcParams.setStatus(WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS);
        
        //電子メール送信日時  
        l_mailProcParams.setSendProcessDateTime(null);
        
        //再送区分 
        l_mailProcParams.setResendStatus(null);
        
        //電子メール再送日時
        l_mailProcParams.setResendProcessDateTime(null);
        
        //emailアドレス 
        l_mailProcParams.setEmailAddress(l_mainAccountParams.getEmailAddress());
        
        //削除フラグ
        l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        //作成日時
        l_mailProcParams.setCreatedTimestamp(l_tsSystemTime);
        
        //更新日時
        l_mailProcParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            //DB更新仕様の通りに、メール送信テーブルに行を追加（DB-insert）する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(l_mailProcParams);//DataNetworkException,DataQueryException
        
            //２）顧客マスタ更新
            //口座開設完了メール送信ステータス
            //1：処理済（Email送信済）           
            l_mainAccountParams.setAccOpenSendMailStatus(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            
            l_queryProcessor.doUpdateQuery(l_mainAccountParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }        
        
        log.exiting(STR_METHOD_NAME);        
    }
}@
