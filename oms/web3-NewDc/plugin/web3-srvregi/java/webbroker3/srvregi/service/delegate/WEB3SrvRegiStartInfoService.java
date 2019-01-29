head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStartInfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用起動情報サービス(WEB3SrvRegiStartInfoService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2005/10/18 鈴木　@美由紀(SRA) フィデリティ対応
Revesion History : 2005/10/18 郭英　@(中訊) 仕様変更モデルNo.231対応
Revesion History : 2009/04/28 車進  (中訊) 仕様変更モデルNo.415
Revesion History : 2009/05/20 柴双紅(中訊) 仕様変更モデルNo.419対応
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.login.service.delegate.WEB3DigestKey;

/**
 * (サービス利用起動情報サービス) <BR>
 * サービス利用起動情報サービスインターフェイス <BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiStartInfoService extends Service
{

    /**
     * (get暗号化顧客コード) <BR>
     * （オリックス証券 ケンミレニアムにて使用） <BR>
     * <BR>
     * 引数.顧客コードに暗号化を施し、その結果を返却する。 <BR>
     * 
     * @@param l_strMainAccountCode -
     *            (顧客コード)
     * @@return String
     * @@roseuid 41B8FB2F0329
     */
    public String getCryptAccountCode(String l_strMainAccountCode);

    /**
     * (createハッシュ値) <BR>
     * 引数から指定された他ベンダーサービスのハッシュ値を求める。 <BR>
     * <BR>
     * シーケンス図「（サービス利用）createハッシュ値」参照 <BR>
     * 
     * @@param l_strInstitutionCode -
     *            (証券会社コード) <BR>
     *            証券会社コード <BR>
     * @@param l_strSrvDiv -
     *            (サービス区分) <BR>
     *            サービス区分 <BR>
     * @@param l_strBranchCode -
     *            部店コード
     * @@param l_strMainAccountCode -
     *            顧客コード
     * @@param l_tsCurrentTimestamp -
     *            現在日付
     * @@param l_strMarketCode
     *            市場コード
     * @@param l_strProductCode
     *            銘柄コード
     * @@param l_strType
     *            タイプ
     * @@param l_digestKey - (ダイジェストキー)<BR>
     * ダイジェストキー
     * @@param l_strSSIDValue - (SSID値)<BR>
     * SSID値
     * @@return String
     * @@roseuid 41B8FB2F032B
     */
    public String createHashValue(
        String l_strInstitutionCode, 
        String l_strSrvDiv,
        String l_strBranchCode, 
        String l_strMainAccountCode, 
        Timestamp l_tsCurrentTimestamp,
        String l_strMarketCode, 
        String l_strProductCode, 
        String l_strType, 
        WEB3DigestKey l_digestKey,
        String l_strSSIDValue) throws WEB3BaseException;

    /**
     * (replaceハッシュ計算方式) <BR>
     * 引数で指定されたハッシュ計算方式区分を、 <BR>
     * MessageDigestオブジェクト生成時に使用する文字列へと変換する。 <BR>
     * 
     * @@param l_strHashCalcMethodDiv -
     *            ハッシュ計算方式区分
     * @@return String
     * @@roseuid 41B8FB2F0339
     */
    public String replaceHashCalHowTo(String l_strHashCalHowToDiv);

    /**
     * (get制御日付) <BR>
     * 引数.現在日付の時間部分を判定し、 <BR>
     * ハッシュ計算・予約語変換の際に「現在日付」として用いる日付を返却する。 <BR>
     * 
     * @@param l_tsCurrentTimestamp -
     *            現在日付
     * @@return java.util.Date
     * @@roseuid 41BE4FA703B1
     */
    public Date getControlTimestamp(Timestamp l_tsCurrentTimestamp);
    
    /**
     * （create顧客IDハッシュ値）<BR>
     * 引数.顧客コード、証券会社コード、部店コードから
     * 顧客を取得し、顧客.顧客IDを引数.ハッシュ計算方式区分で
     * ハッシュ化して返却する。
     * 
     * @@param l_strHashCalHowTo
     *            ハッシュ計算方式区分
     * @@param l_strInstitusionCode
     *            証券会社コード
     * @@param l_strBranchCode
     *            部店コード
     * @@param l_strAccountCode
     *            顧客コード
     * @@return String
     */
    public String createAccountCodeHashValue(String l_strHashCalHowTo, String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode)throws WEB3BaseException;
    
    /**
     * (get暗号化保有銘柄情報)<BR>
     * 引数補助口座から、投信の保有銘柄情報を取得し、
     * 暗号化して返却する。
     *
     * @@param l_strInstitusionCode
     *            証券会社コード
     * @@param l_strBranchCode
     *            部店コード
     * @@param l_strAccountCode
     *            顧客コード
     * @@return String
     */
    public String getEncryptionMfAsset(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode)throws WEB3BaseException;

    /**
     * (getCDキー)<BR>
     * CDキー（ユーザー識別子）を作成して返却する。<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCDKey(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strInstitutionCode) throws WEB3BaseException;
}@
