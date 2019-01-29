head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設情報作成サービス(WEB3AccOpenInfoCreatedService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
                 : 2006/06/08 周捷(中訊) 仕様変更・モデル048、050
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenMailAddressDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenTelNumberDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (口座開設情報作成サービス)<BR>
 * 口座開設情報作成サービスインタフェイス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public interface WEB3AccOpenInfoCreatedService extends Service 
{
    
    /**
     * (to口座開設申込情報)<BR>
     * 口座開設見込客オブジェクトより、口座開設申込情報を生成する。<BR>
     * <BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInfo
     * @@roseuid 41AC4CAF0381
     */
    public WEB3AccOpenApplyInfo toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen);
    
    /**
     * (to口座開設見込客)<BR>
     * 口座開設申込情報オブジェクトより、口座開設見込客オブジェクトを生成する。<BR>
     * <BR>
     * @@param l_accOpenApplyInfo - (口座開設申込情報)<BR> 
     * 口座開設申込情報メッセージデータオブジェクト<BR>
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException
     * @@roseuid 41AC4CAF0391
     */
    public WEB3AccOpenExpAccountOpen toAccOpenExpAccountOpen(WEB3AccOpenApplyInfo l_accOpenApplyInfo) throws WEB3BaseException;
    
    /**
     * (to伝票作成情報)<BR>
     * 口座開設伝票作成ステータスオブジェクトの配列より、伝票作成情報を生成する。<BR>
     * <BR>
     * @@param l_accOpenVoucherCreatedStatuses - 口座開設伝票作成ステータスオブジェクトの配列
     * @@return webbroker3.accountopen.message.WEB3AccOpenVoucherInfo
     * @@roseuid 41AC4CAF03B0
     */
    public WEB3AccOpenVoucherInfo toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses);
    
    /**
     * (to不備項目情報)<BR>
     * 口座開設不備オブジェクトより、不備項目情報の配列を生成する。<BR>
     * <BR>
     * @@param l_accOpenInvalidValues - 口座開設不備オブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo[]
     * @@roseuid 41AC4CAF03C0
     */
    public WEB3AccOpenInvalidItemInfo[] toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues l_accOpenInvalidValues);
    
    /**
     * (to口座開設不備)<BR>
     * 不備項目情報の配列より、口座開設オブジェクトを生成する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_accOpenInvalidItemInfo - 不備項目情報メッセージデータ
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@throws WEB3BaseException
     * @@roseuid 41AC4CAF03CF
     */
    public WEB3AccOpenInvalidValues toAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, WEB3AccOpenInvalidItemInfo[] l_accOpenInvalidItemInfo) throws WEB3BaseException;
    
    /**
     * (to列物理名)<BR>
     * メッセージオブジェクトの項目名を、対応する口座開設見込客テーブルの<BR>
     * 列物理名に変換する。<BR>
     * @@param l_strMessageItemName - メッセージ項目物理名<BR>
     * ※　@口座開設申込情報の各プロパティ物理名<BR>
     * ※　@Unitクラスの変数は、世帯主勤務先情報.役職名のように、<BR>
     * 「変数名.項目名」の形式で指定する。<BR>
     * @@return String
     * @@roseuid 41AC4CAF03DF
     */
    public String toColumnSymbolName(String l_strMessageItemName);
    
    /**
     * (toメッセージ項目名)<BR>
     * 口座開設見込客テーブルの列物理名を対応するメッセージオブジェクトの<BR>
     * 項目名に変換する。<BR>
     * @@param l_strColumnName - 列物理名<BR>
     * <BR>
     * ※　@口座開設見込客テーブルの列物理名<BR>
     * 
     * @@return String
     * @@roseuid 41AC4CB00016
     */
    public String toMessageItemName(String l_strColumnName);
    
    /**
     * (to口座開設審査待ち)<BR>
     * 口座開設審査待ちオブジェクトを生成する。<BR>
     * <BR>
     * @@return WEB3AccOpenJudgeWaiting
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenJudgeWaiting toAccOpenJudgeWaiting();
    
    /**
     * (to口座開設メールアドレス重複チェック)<BR>
     * 口座開設メールアドレス重複チェックオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenMailAddressDuplicationCheck toAccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode);
    
    /**
     * (to口座開設電話番号重複チェック)<BR>
     * 口座開設電話番号重複チェックオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return WEB3AccOpenTelNumberDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenTelNumberDuplicationCheck toAccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode);
      
}
@
