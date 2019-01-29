head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座情報マスタクラス(WEB3AccInfoMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 呉艶飛 (中訊) 新規作成
                   2006/10/9  齊珂   (中訊) モデルNo.124
                   2006/10/30 齊珂   (中訊) モデルNo.139
*/
package webbroker3.accountinfo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座情報マスタクラス)<BR>
 * 口座情報マスタクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3AccInfoMaster implements BusinessObject
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMaster.class);
    
    /**
     * (口座情報マスタ行オブジェクト)<BR>
     * 口座情報マスタ行オブジェクト <BR>
     * <BR>
     * ※ 口座情報マスタParamsクラスはDDLより自動生成する。<BR>
     */
    private AccountInfoMstParams accountInfoMstParams;
    
    /**
     *（getDataSourceObjectの実装)<BR>
     * getDataSourceObjectの実装 <BR>
     * <BR>
     * this.口座情報マスタ行を返却する。 <BR>
     */
    public Object getDataSourceObject()
    {
        return this.accountInfoMstParams;
    }
    
    /**
     * (口座情報マスタ)
     *コンストラクタ。
     */
    public WEB3AccInfoMaster(AccountInfoMstParams l_accountInfoMstParams) 
    {
        this.accountInfoMstParams = l_accountInfoMstParams;
    }
    /**
     * （static メソッド)<BR>
     * 口座情報マスタ新規行を生成する。<BR>
     * <BR><BR>
     * １）　@行オブジェクト生成<BR>
     * 　@口座情報マスタParamsオブジェクトを生成する。<BR>
     * <BR>
     * 　@※口座情報マスタParamsはDDLより自動生成する。<BR>
     * <BR>
     * ２）　@行オブジェクトにプロパティをセットする。 <BR>
     * 　@１）で生成した口座情報マスタParamsオブジェクトのプロパティに、以下の通りセットを行う。<BR> 
     * <BR>
     * 　@口座情報マスタParams.口座ＩＤ = 顧客.getAccountId()<BR>
     * 　@口座情報マスタParams.証券会社コード = 顧客.getInstitution().getInstitutionCode()<BR>
     * 　@口座情報マスタParams.部店コード = 顧客.getBranch().getBranchId()<BR>
     * 　@口座情報マスタParams.口座コード = 顧客.getAccountCode()<BR>
     * 　@口座情報マスタParams.作成日時 = TradingSystem.getSystemTimestamp() <BR>
     * 　@口座情報マスタParams.更新日時 = TradingSystem.getSystemTimestamp() <BR>
     * 　@口座情報マスタParams.更新者コード = 更新者コード<BR>
     * 　@口座情報マスタParams.顧客名（法@人名）カナ = 顧客.名前（苗字1）<BR>
     * 　@口座情報マスタParams.顧客正式名称１ = 携帯番号・勤務先情報.正式名称１<BR>
     * 　@口座情報マスタParams.顧客正式名称２ = 携帯番号・勤務先情報.正式名称２<BR>
     * 　@口座情報マスタParams.職業区分 = 携帯番号・勤務先情報.職業<BR>
     * 　@口座情報マスタParams.代表者名（漢字）姓 = 携帯番号・勤務先情報.代表者名（漢字）姓<BR>
     * 　@口座情報マスタParams.代表者名（漢字）名 = 携帯番号・勤務先情報.代表者名（漢字）名<BR>
     * 　@口座情報マスタParams.代表者名（カナ）姓 = 携帯番号・勤務先情報.代表者名（カナ）姓<BR>
     * 　@口座情報マスタParams.代表者名（カナ）名 = 携帯番号・勤務先情報.代表者名（カナ）名<BR>
     * 　@口座情報マスタParams.代表者権= 携帯番号・勤務先情報.代表者権<BR>
     * 　@口座情報マスタParams.取引責任者名（漢字）姓= 携帯番号・勤務先情報.取引先責任者名（漢字）姓<BR>
     * 　@口座情報マスタParams.取引責任者名（漢字）名= 携帯番号・勤務先情報.取引先責任者名（漢字）名<BR>
     * 　@口座情報マスタParams.取引責任者名（カナ）姓= 携帯番号・勤務先情報.取引先責任者名（カナ）姓<BR>
     * 　@口座情報マスタParams.取引責任者名（カナ）名= 携帯番号・勤務先情報.取引先責任者名（カナ）名<BR>
     * 　@口座情報マスタParams.取引責任者　@所属部署= 携帯番号・勤務先情報.取引責任者所属部署<BR>
     * 　@口座情報マスタParams.取引責任者　@役職= 携帯番号・勤務先情報.取引責任者役職<BR>
     * 　@口座情報マスタParams.取引責任者郵便番号= 携帯番号・勤務先情報.取引先責任者郵便番号<BR>
     * 　@口座情報マスタParams.取引責任者住所１= 携帯番号・勤務先情報.取引先責任者住所１<BR>
     * 　@口座情報マスタParams.取引責任者住所２= 携帯番号・勤務先情報.取引先責任者住所２<BR>
     * 　@口座情報マスタParams.取引責任者住所３= 携帯番号・勤務先情報.取引先責任者住所３<BR>
     * 　@口座情報マスタParams.取引責任者生年月日　@年号= 携帯番号・勤務先情報.取引先責任者生年月日年号<BR>
     * 　@口座情報マスタParams.取引責任者生年月日= 携帯番号・勤務先情報.取引先責任者生年月日<BR>
     * 　@口座情報マスタParams.取引責任者会社直通番号= 携帯番号・勤務先情報.取引先責任者会社直通番号<BR>
     * 　@口座情報マスタParams.その他連絡先（携帯、自宅等）= 携帯番号・勤務先情報.その他の連絡先（携帯、自宅等）<BR>
     * 　@口座情報マスタParams.勤務先名称 = null<BR>
     * 　@口座情報マスタParams.勤務先郵便番号 = null<BR>
     * 　@口座情報マスタParams.勤務先住所 = null<BR>
     * 　@口座情報マスタParams.所属 = 携帯番号・勤務先情報.所属 <BR>
     * 　@口座情報マスタParams.役職 = null<BR>
     * 　@口座情報マスタParams.勤務先電話番号 = null<BR>
     * 　@口座情報マスタParams.連絡先電話番号（携帯）= null<BR>
     * 　@口座情報マスタParams.連絡先優先順位 1位 = 携帯番号・勤務先情報.連絡先優先順位 1位<BR>
     * 　@口座情報マスタParams.連絡先優先順位 2位 = 携帯番号・勤務先情報.連絡先優先順位 2位<BR>
     * 　@口座情報マスタParams.連絡先優先順位 3位 = 携帯番号・勤務先情報.連絡先優先順位 3位<BR>
     * 　@口座情報マスタParams.国籍 = 携帯番号・勤務先情報.国籍<BR>
     * 　@口座情報マスタParams.国籍その他 = 携帯番号・勤務先情報.国籍その他<BR>
     * 　@口座情報マスタParams.FAX番号 = 携帯番号・勤務先情報.FAX番号 <BR>
     * 　@口座情報マスタParams.年収 = 携帯番号・勤務先情報.年収 <BR>
　@   * 　@口座情報マスタParams.金融資産額 = 携帯番号・勤務先情報.金融資産額 <BR>
　@   * 　@口座情報マスタParams.運用予定額 = 携帯番号・勤務先情報.運用予定額 <BR>
　@   * 　@口座情報マスタParams.投資目的 = 携帯番号・勤務先情報.投資目的 <BR>
　@   * 　@口座情報マスタParams.投資予定期間 = 携帯番号・勤務先情報.投資予定期間 <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（１） = 携帯番号・勤務先情報.投資経験の有無（１） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（２） = 携帯番号・勤務先情報.投資経験の有無（２） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（３） = 携帯番号・勤務先情報.投資経験の有無（３） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（４） = 携帯番号・勤務先情報.投資経験の有無（４） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（５） = 携帯番号・勤務先情報.投資経験の有無（５） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（６） = 携帯番号・勤務先情報.投資経験の有無（６） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（７） = 携帯番号・勤務先情報.投資経験の有無（７） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（８） = 携帯番号・勤務先情報.投資経験の有無（８） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（９） = 携帯番号・勤務先情報.投資経験の有無（９） <BR>
　@   * 　@口座情報マスタParams.投資経験の有無（１０） = 携帯番号・勤務先情報.投資経験の有無（１０） <BR>
　@   * 　@口座情報マスタParams.投資経験（１） = 携帯番号・勤務先情報.投資経験（１） <BR>
　@   * 　@口座情報マスタParams.投資経験（２） = 携帯番号・勤務先情報.投資経験（２） <BR>
　@   * 　@口座情報マスタParams.投資経験（３） = 携帯番号・勤務先情報.投資経験（３） <BR>
　@   * 　@口座情報マスタParams.投資経験（４） = 携帯番号・勤務先情報.投資経験（４） <BR>
　@   * 　@口座情報マスタParams.投資経験（５） = 携帯番号・勤務先情報.投資経験（５） <BR>
　@   * 　@口座情報マスタParams.投資経験（６） = 携帯番号・勤務先情報.投資経験（６） <BR>
　@   * 　@口座情報マスタParams.投資経験（７） = 携帯番号・勤務先情報.投資経験（７） <BR>
　@   * 　@口座情報マスタParams.投資経験（８） = 携帯番号・勤務先情報.投資経験（８） <BR>
　@   * 　@口座情報マスタParams.投資経験（９） = 携帯番号・勤務先情報.投資経験（９） <BR>
　@   * 　@口座情報マスタParams.投資経験（１０） = 携帯番号・勤務先情報.投資経験（１０） <BR>
　@   * 　@口座情報マスタParams.取引の種類（１） = 携帯番号・勤務先情報.取引の種類（１） <BR>
　@   * 　@口座情報マスタParams.取引の種類（２） = 携帯番号・勤務先情報.取引の種類（２） <BR>
　@   * 　@口座情報マスタParams.取引の種類（３） = 携帯番号・勤務先情報.取引の種類（３） <BR>
　@   * 　@口座情報マスタParams.取引の種類（４） = 携帯番号・勤務先情報.取引の種類（４） <BR>
　@   * 　@口座情報マスタParams.取引の種類（５） = 携帯番号・勤務先情報.取引の種類（５） <BR>
　@   * 　@口座情報マスタParams.取引の種類（６） = 携帯番号・勤務先情報.取引の種類（６） <BR>
　@   * 　@口座情報マスタParams.取引の種類（７） = 携帯番号・勤務先情報.取引の種類（７） <BR>
　@   * 　@口座情報マスタParams.取引の種類（８） = 携帯番号・勤務先情報.取引の種類（８） <BR>
　@   * 　@口座情報マスタParams.取引の種類（９） = 携帯番号・勤務先情報.取引の種類（９） <BR>
　@   * 　@口座情報マスタParams.取引の種類（１０） = 携帯番号・勤務先情報.取引の種類（１０） <BR>
　@   * 　@口座情報マスタParams.口座開設の動機@ = 携帯番号・勤務先情報.口座開設の動機@ <BR>
　@   * 　@口座情報マスタParams.口座開設の動機@の詳細 = 携帯番号・勤務先情報.口座開設の動機@の詳細 <BR>
　@   * 　@口座情報マスタParams.現在利用している証券会社 = 携帯番号・勤務先情報.現在利用している証券会社 <BR>
　@   * 　@口座情報マスタParams.インターネット取引区分 = 携帯番号・勤務先情報.インターネット取引区分 <BR>
　@   * 　@口座情報マスタParams.紹介支店 = 携帯番号・勤務先情報.紹介支店 <BR>
     * <BR>
     * ３）　@口座情報マスタオブジェクト返却<BR>
     * 　@行オブジェクトを指定し、口座情報マスタオブジェクトを生成し返却する。<BR>
     * @@param l_mainAccount - 顧客
     * @@param l_accInfoMobileOfficeInfo - 携帯番号・勤務先情報
     * @@param l_strUpdaterCode - 更新者コード
     * @@return AccountInfoMstParams
     */
    public static WEB3AccInfoMaster createNewAccInfoMaster (
        MainAccount l_mainAccount, 
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo, 
        String l_strUpdaterCode)
    {
        final String STR_METHOD_NAME = " createNewAccInfoMaster(MainAccount, WEB3AccInfoMobileOfficeInfo, String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@行オブジェクト生成
        // 　@口座情報マスタParamsオブジェクトを生成する。
        AccountInfoMstParams l_accInfoMstParams = new AccountInfoMstParams();
        
        //２）　@行オブジェクトにプロパティをセットする。
        //１）で生成した口座情報マスタParamsオブジェクトのプロパティに、以下の通りセットを行う。
        //口座情報マスタParams.口座ＩＤ = 顧客.getAccountId()
        l_accInfoMstParams.setAccountId(l_mainAccount.getAccountId());
        
        //口座情報マスタParams.証券会社コード = 顧客.getInstitution().getInstitutionCode()
        l_accInfoMstParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        
        //口座情報マスタParams.部店コード = 顧客.getBranch().getBranchId()
        l_accInfoMstParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        //口座情報マスタParams.口座コード = 顧客.getAccountCode()
        l_accInfoMstParams.setAccountCode(l_mainAccount.getAccountCode());
        
        //口座情報マスタParams.作成日時 = TradingSystem.getSystemTimestamp()
        l_accInfoMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //口座情報マスタParams.更新日時 = TradingSystem.getSystemTimestamp()
        l_accInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //口座情報マスタParams.更新者コード = 更新者コード
        l_accInfoMstParams.setLastUpdater(l_strUpdaterCode);
        
        //口座情報マスタParams.顧客名（法@人名）カナ = 顧客.名前（苗字1）
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_accInfoMstParams.setFamilyNameAlt1(l_row.getFamilyNameAlt1());
        
        //口座情報マスタParams.顧客正式名称１ = 携帯番号・勤務先情報.正式名称１ 
        l_accInfoMstParams.setRealName1(l_accInfoMobileOfficeInfo.accountRealName1);
        
        //口座情報マスタParams.顧客正式名称２ = 携帯番号・勤務先情報.正式名称２
        l_accInfoMstParams.setRealName2(l_accInfoMobileOfficeInfo.accountRealName2);
        
        //口座情報マスタParams.職業区分 = 携帯番号・勤務先情報.職業
        l_accInfoMstParams.setOccupationDiv(l_accInfoMobileOfficeInfo.occupationDiv);
        
        //口座情報マスタParams.代表者名（漢字）姓 = 携帯番号・勤務先情報.代表者名（漢字）姓
        l_accInfoMstParams.setRepresentFamilyName(l_accInfoMobileOfficeInfo.representFamilyName);
        
        //口座情報マスタParams.代表者名（漢字）名 = 携帯番号・勤務先情報.代表者名（漢字）名
        l_accInfoMstParams.setRepresentGivenName(l_accInfoMobileOfficeInfo.representName);
        
        //口座情報マスタParams.代表者名（カナ）姓 = 携帯番号・勤務先情報.代表者名（カナ）姓
        l_accInfoMstParams.setRepresentFamilyNameAlt1(l_accInfoMobileOfficeInfo.representFamilyNameKana);
        
        //口座情報マスタParams.代表者名（カナ）名 =  携帯番号・勤務先情報.代表者名（カナ）名
        l_accInfoMstParams.setRepresentGivenNameAlt1(l_accInfoMobileOfficeInfo.representNameKana);
        
        //口座情報マスタParams.代表者権= 携帯番号・勤務先情報.代表者権
        l_accInfoMstParams.setRepresentPower(l_accInfoMobileOfficeInfo.representPower);
        
        //口座情報マスタParams.取引責任者名（漢字）姓= 携帯番号・勤務先情報.取引先責任者名（漢字）姓
        l_accInfoMstParams.setDirectorFamilyName(l_accInfoMobileOfficeInfo.directorFamilyName);
        
        //口座情報マスタParams.取引責任者名（漢字）名= 携帯番号・勤務先情報.取引先責任者名（漢字）名
        l_accInfoMstParams.setDirectorGivenName(l_accInfoMobileOfficeInfo.directorName);
        
        //口座情報マスタParams.取引責任者名（カナ）姓= 携帯番号・勤務先情報.取引先責任者名（カナ）姓
        l_accInfoMstParams.setDirectorFamilyNameAlt1(l_accInfoMobileOfficeInfo.directorFamilyNameKana);
        
        //口座情報マスタParams.取引責任者名（カナ）名= 携帯番号・勤務先情報.取引先責任者名（カナ）名
        l_accInfoMstParams.setDirectorGivenNameAlt1(l_accInfoMobileOfficeInfo.directorNameKana);
        
        //口座情報マスタParams.取引責任者　@所属部署= 携帯番号・勤務先情報.取引責任者所属部署
        l_accInfoMstParams.setDirectorDepartment(l_accInfoMobileOfficeInfo.directorDepartment);
        
        //口座情報マスタParams.取引責任者　@役職= 携帯番号・勤務先情報.取引責任者役職 
        l_accInfoMstParams.setDirectorPost(l_accInfoMobileOfficeInfo.directorPosition);
        
        //口座情報マスタParams.取引責任者郵便番号= 携帯番号・勤務先情報.取引先責任者郵便番号
        l_accInfoMstParams.setDirectorZipCode(l_accInfoMobileOfficeInfo.directorZipCode);
        
        //口座情報マスタParams.取引責任者住所１= 携帯番号・勤務先情報.取引先責任者住所１ 
        l_accInfoMstParams.setDirectorAddress1(l_accInfoMobileOfficeInfo.directorAddress1);
        
        //口座情報マスタParams.取引責任者住所２= 携帯番号・勤務先情報.取引先責任者住所２ 
        l_accInfoMstParams.setDirectorAddress2(l_accInfoMobileOfficeInfo.directorAddress2);
        
        //口座情報マスタParams.取引責任者住所３= 携帯番号・勤務先情報.取引先責任者住所３
        l_accInfoMstParams.setDirectorAddress3(l_accInfoMobileOfficeInfo.directorAddress3);
        
        //口座情報マスタParams.取引責任者生年月日　@年号= 携帯番号・勤務先情報.取引先責任者生年月日年号
        l_accInfoMstParams.setDirectorEraBorn(l_accInfoMobileOfficeInfo.directorEraBorn);
        
        //口座情報マスタParams.取引責任者生年月日= 携帯番号・勤務先情報.取引先責任者生年月日
        l_accInfoMstParams.setDirectorBornDate(l_accInfoMobileOfficeInfo.directorBornDate);
        
        //口座情報マスタParams.取引責任者会社直通番号= 携帯番号・勤務先情報.取引先責任者会社直通番号
        l_accInfoMstParams.setDirectorCorpTelephone(l_accInfoMobileOfficeInfo.directorCorpDirect);
        
        //口座情報マスタParams.その他連絡先（携帯、自宅等）= 携帯番号・勤務先情報.その他の連絡先（携帯、自宅等）
        l_accInfoMstParams.setOtherContact(l_accInfoMobileOfficeInfo.directorOtherContact);
        
        //口座情報マスタParams.勤務先名称 = null
        l_accInfoMstParams.setOffice(null);
        
        //口座情報マスタParams.勤務先郵便番号 = null
        l_accInfoMstParams.setOfficeZipCode(null);
        
        //口座情報マスタParams.勤務先住所 = null
        l_accInfoMstParams.setOfficeAddress(null);
        
        //口座情報マスタParams.所属 = 携帯番号・勤務先情報.所属 
        l_accInfoMstParams.setDepartment(l_accInfoMobileOfficeInfo.department);
        
        //口座情報マスタParams.役職 = null
        l_accInfoMstParams.setPost(null);
        
        //口座情報マスタParams.勤務先電話番号 = null
        l_accInfoMstParams.setOfficeTelephone(null);
        
        //口座情報マスタParams.連絡先電話番号（携帯）= null
        l_accInfoMstParams.setMobile(null);
        
        //口座情報マスタParams.連絡先優先順位 1位 = 携帯番号・勤務先情報.連絡先優先順位 1位
        l_accInfoMstParams.setContactPriority1(l_accInfoMobileOfficeInfo.contactPriority1);
        
        //口座情報マスタParams.連絡先優先順位 2位 = 携帯番号・勤務先情報.連絡先優先順位 2位
        l_accInfoMstParams.setContactPriority2(l_accInfoMobileOfficeInfo.contactPriority2);
        
        //口座情報マスタParams.連絡先優先順位 3位 = 携帯番号・勤務先情報.連絡先優先順位 3位
        l_accInfoMstParams.setContactPriority3(l_accInfoMobileOfficeInfo.contactPriority3);
        
        //口座情報マスタParams.国籍 = 携帯番号・勤務先情報.国籍
        l_accInfoMstParams.setNationality(l_accInfoMobileOfficeInfo.nationality);
        
        //口座情報マスタParams.国籍その他 = 携帯番号・勤務先情報.国籍その他 
        l_accInfoMstParams.setNationalityOther(l_accInfoMobileOfficeInfo.nationalityOther);
        
		//   　@口座情報マスタParams.FAX番号 = 携帯番号・勤務先情報.FAX番号 
        l_accInfoMstParams.setFax(l_accInfoMobileOfficeInfo.faxTelephone);
        
		//   　@口座情報マスタParams.年収 = 携帯番号・勤務先情報.年収 
        l_accInfoMstParams.setAnnualIncomeDiv(l_accInfoMobileOfficeInfo.annualIncomeDiv);
        
		//   　@口座情報マスタParams.金融資産額 = 携帯番号・勤務先情報.金融資産額 
        l_accInfoMstParams.setAssetValueDiv(l_accInfoMobileOfficeInfo.assetValueDiv);
        
		//   　@口座情報マスタParams.運用予定額 = 携帯番号・勤務先情報.運用予定額 
        l_accInfoMstParams.setFundBudgetAmountDiv(l_accInfoMobileOfficeInfo.fundBundgetAmountDiv);
        
		//   　@口座情報マスタParams.投資目的 = 携帯番号・勤務先情報.投資目的 
        l_accInfoMstParams.setInvestPurposeDiv(l_accInfoMobileOfficeInfo.investPurposeDiv);
        
		//   　@口座情報マスタParams.投資予定期間 = 携帯番号・勤務先情報.投資予定期間 
        l_accInfoMstParams.setInvestPlanPeriodDiv(l_accInfoMobileOfficeInfo.investPlanPeriodDiv);
        
		//   　@口座情報マスタParams.投資経験の有無（１） = 携帯番号・勤務先情報.投資経験の有無（１） 
        Integer l_intExperienceFlag1 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag1 != null)
        {
            l_intExperienceFlag1 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag1);
        }
        l_accInfoMstParams.setExperienceFlag1(l_intExperienceFlag1);
        
        
		//   　@口座情報マスタParams.投資経験の有無（２） = 携帯番号・勤務先情報.投資経験の有無（２）
        Integer l_intExperienceFlag2 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag2 != null)
        {
            l_intExperienceFlag2 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag2);
        }
        l_accInfoMstParams.setExperienceFlag2(l_intExperienceFlag2);
        
		//   　@口座情報マスタParams.投資経験の有無（３） = 携帯番号・勤務先情報.投資経験の有無（３）
        Integer l_intExperienceFlag3 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag3 != null)
        {
            l_intExperienceFlag3 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag3);
        }
        l_accInfoMstParams.setExperienceFlag3(l_intExperienceFlag3);
        
		//   　@口座情報マスタParams.投資経験の有無（４） = 携帯番号・勤務先情報.投資経験の有無（４）
        Integer l_intExperienceFlag4 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag4 != null)
        {
            l_intExperienceFlag4 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag4);
        }
        l_accInfoMstParams.setExperienceFlag4(l_intExperienceFlag4);
        
		//   　@口座情報マスタParams.投資経験の有無（５） = 携帯番号・勤務先情報.投資経験の有無（５） 
        Integer l_intExperienceFlag5 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag5 != null)
        {
            l_intExperienceFlag5 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag5);
        }
        l_accInfoMstParams.setExperienceFlag5(l_intExperienceFlag5);
        
		//   　@口座情報マスタParams.投資経験の有無（６） = 携帯番号・勤務先情報.投資経験の有無（６） 
        Integer l_intExperienceFlag6 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag6 != null)
        {
            l_intExperienceFlag6 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag6);
        }
        l_accInfoMstParams.setExperienceFlag6(l_intExperienceFlag6);
        
		//   　@口座情報マスタParams.投資経験の有無（７） = 携帯番号・勤務先情報.投資経験の有無（７）
        Integer l_intExperienceFlag7 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag7 != null)
        {
            l_intExperienceFlag7 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag7);
        }
        l_accInfoMstParams.setExperienceFlag7(l_intExperienceFlag7);
        
		//   　@口座情報マスタParams.投資経験の有無（８） = 携帯番号・勤務先情報.投資経験の有無（８）
        Integer l_intExperienceFlag8 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag8 != null)
        {
            l_intExperienceFlag8 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag8);
        }
        l_accInfoMstParams.setExperienceFlag8(l_intExperienceFlag8);
        
		//   　@口座情報マスタParams.投資経験の有無（９） = 携帯番号・勤務先情報.投資経験の有無（９） 
        Integer l_intExperienceFlag9 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag9 != null)
        {
            l_intExperienceFlag9 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag9);
        }
        l_accInfoMstParams.setExperienceFlag9(l_intExperienceFlag9);
        
		//   　@口座情報マスタParams.投資経験の有無（１０） = 携帯番号・勤務先情報.投資経験の有無（１０）
        Integer l_intExperienceFlag10 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag10 != null)
        {
            l_intExperienceFlag10 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag10);
        }
        l_accInfoMstParams.setExperienceFlag10(l_intExperienceFlag10);
        
		//   　@口座情報マスタParams.投資経験（１） = 携帯番号・勤務先情報.投資経験（１） 
        l_accInfoMstParams.setExperienceDiv1(l_accInfoMobileOfficeInfo.experienceDiv1);
        
		//   　@口座情報マスタParams.投資経験（２） = 携帯番号・勤務先情報.投資経験（２）
        l_accInfoMstParams.setExperienceDiv2(l_accInfoMobileOfficeInfo.experienceDiv2);
        
		//   　@口座情報マスタParams.投資経験（３） = 携帯番号・勤務先情報.投資経験（３）
        l_accInfoMstParams.setExperienceDiv3(l_accInfoMobileOfficeInfo.experienceDiv3);
        
		//   　@口座情報マスタParams.投資経験（４） = 携帯番号・勤務先情報.投資経験（４）
        l_accInfoMstParams.setExperienceDiv4(l_accInfoMobileOfficeInfo.experienceDiv4);
        
		//   　@口座情報マスタParams.投資経験（５） = 携帯番号・勤務先情報.投資経験（５） 
        l_accInfoMstParams.setExperienceDiv5(l_accInfoMobileOfficeInfo.experienceDiv5);
        
		//   　@口座情報マスタParams.投資経験（６） = 携帯番号・勤務先情報.投資経験（６）
        l_accInfoMstParams.setExperienceDiv6(l_accInfoMobileOfficeInfo.experienceDiv6);
        
		//   　@口座情報マスタParams.投資経験（７） = 携帯番号・勤務先情報.投資経験（７） 
        l_accInfoMstParams.setExperienceDiv7(l_accInfoMobileOfficeInfo.experienceDiv7);
        
		//   　@口座情報マスタParams.投資経験（８） = 携帯番号・勤務先情報.投資経験（８）
        l_accInfoMstParams.setExperienceDiv8(l_accInfoMobileOfficeInfo.experienceDiv8);
        
		//   　@口座情報マスタParams.投資経験（９） = 携帯番号・勤務先情報.投資経験（９）
        l_accInfoMstParams.setExperienceDiv9(l_accInfoMobileOfficeInfo.experienceDiv9);
        
		//   　@口座情報マスタParams.投資経験（１０） = 携帯番号・勤務先情報.投資経験（１０） 
        l_accInfoMstParams.setExperienceDiv10(l_accInfoMobileOfficeInfo.experienceDiv10);
        
		//   　@口座情報マスタParams.取引の種類（１） = 携帯番号・勤務先情報.取引の種類（１）
        Integer l_intInterest1 = null;
        if (l_accInfoMobileOfficeInfo.interest1 != null)
        {
            l_intInterest1 = new Integer(l_accInfoMobileOfficeInfo.interest1);
        }
        l_accInfoMstParams.setInterestFlag1(l_intInterest1);
        
		//   　@口座情報マスタParams.取引の種類（２） = 携帯番号・勤務先情報.取引の種類（２）
        Integer l_intInterest2 = null;
        if (l_accInfoMobileOfficeInfo.interest2 != null)
        {
            l_intInterest2 = new Integer(l_accInfoMobileOfficeInfo.interest2);
        }
        l_accInfoMstParams.setInterestFlag2(l_intInterest2);
        
        
		//   　@口座情報マスタParams.取引の種類（３） = 携帯番号・勤務先情報.取引の種類（３）
        Integer l_intInterest3 = null;
        if (l_accInfoMobileOfficeInfo.interest3 != null)
        {
            l_intInterest3 = new Integer(l_accInfoMobileOfficeInfo.interest3);
        }
        l_accInfoMstParams.setInterestFlag3(l_intInterest3);
        
        
		//   　@口座情報マスタParams.取引の種類（４） = 携帯番号・勤務先情報.取引の種類（４）
        Integer l_intInterest4 = null;
        if (l_accInfoMobileOfficeInfo.interest4 != null)
        {
            l_intInterest4 = new Integer(l_accInfoMobileOfficeInfo.interest4);
        }
        l_accInfoMstParams.setInterestFlag4(l_intInterest4);
        
		//   　@口座情報マスタParams.取引の種類（５） = 携帯番号・勤務先情報.取引の種類（５）
        Integer l_intInterest5 = null;
        if (l_accInfoMobileOfficeInfo.interest5 != null)
        {
            l_intInterest5 = new Integer(l_accInfoMobileOfficeInfo.interest5);
        }
        l_accInfoMstParams.setInterestFlag5(l_intInterest5);
        
		//   　@口座情報マスタParams.取引の種類（６） = 携帯番号・勤務先情報.取引の種類（６） 
        Integer l_intInterest6 = null;
        if (l_accInfoMobileOfficeInfo.interest6 != null)
        {
            l_intInterest6 = new Integer(l_accInfoMobileOfficeInfo.interest6);
        }
        l_accInfoMstParams.setInterestFlag6(l_intInterest6);
        
		//   　@口座情報マスタParams.取引の種類（７） = 携帯番号・勤務先情報.取引の種類（７） 
        Integer l_intInterest7 = null;
        if (l_accInfoMobileOfficeInfo.interest7 != null)
        {
            l_intInterest7 = new Integer(l_accInfoMobileOfficeInfo.interest7);
        }
        l_accInfoMstParams.setInterestFlag7(l_intInterest7);
        
		//   　@口座情報マスタParams.取引の種類（８） = 携帯番号・勤務先情報.取引の種類（８）
        Integer l_intInterest8 = null;
        if (l_accInfoMobileOfficeInfo.interest8 != null)
        {
            l_intInterest8 = new Integer(l_accInfoMobileOfficeInfo.interest8);
        }
        l_accInfoMstParams.setInterestFlag8(l_intInterest8);
        
		//   　@口座情報マスタParams.取引の種類（９） = 携帯番号・勤務先情報.取引の種類（９） 
        Integer l_intInterest9 = null;
        if (l_accInfoMobileOfficeInfo.interest9 != null)
        {
            l_intInterest9 = new Integer(l_accInfoMobileOfficeInfo.interest9);
        }
        l_accInfoMstParams.setInterestFlag9(l_intInterest9);
        
		//   　@口座情報マスタParams.取引の種類（１０） = 携帯番号・勤務先情報.取引の種類（１０） 
        Integer l_intInterest10 = null;
        if (l_accInfoMobileOfficeInfo.interest10 != null)
        {
            l_intInterest10 = new Integer(l_accInfoMobileOfficeInfo.interest10);
        }
        l_accInfoMstParams.setInterestFlag10(l_intInterest10);
        
		//   　@口座情報マスタParams.口座開設の動機@ = 携帯番号・勤務先情報.口座開設の動機@ 
        l_accInfoMstParams.setAppliMotivatDiv(l_accInfoMobileOfficeInfo.appliMotivatDiv);
        
		//   　@口座情報マスタParams.口座開設の動機@の詳細 = 携帯番号・勤務先情報.口座開設の動機@の詳細 
        l_accInfoMstParams.setAppliMotivatDivDetail(l_accInfoMobileOfficeInfo.appliMotivatDetail);
        
		//   　@口座情報マスタParams.現在利用している証券会社 = 携帯番号・勤務先情報.現在利用している証券会社 
        l_accInfoMstParams.setUseInstitutionDiv(l_accInfoMobileOfficeInfo.useInstitutionDiv);
        
		//   　@口座情報マスタParams.インターネット取引区分 = 携帯番号・勤務先情報.インターネット取引区分 
        l_accInfoMstParams.setInternetTradeDiv(l_accInfoMobileOfficeInfo.internetTradeDiv);
        
		//   　@口座情報マスタParams.紹介支店 = 携帯番号・勤務先情報.紹介支店 
        l_accInfoMstParams.setIntroduceBranchCode(l_accInfoMobileOfficeInfo.introduceBranch);
        
        //３）　@口座情報マスタオブジェクト返却
        // 　@行オブジェクトを指定し、口座情報マスタオブジェクトを生成し返却する。
        log.exiting(STR_METHOD_NAME);
        return new WEB3AccInfoMaster(l_accInfoMstParams);
    }
    
    /**
     *（static メソッド）<BR>
     *顧客に該当する口座情報マスタを取得する。<BR>
     *<BR>
     *１）　@口座情報マスタテーブル検索<BR>
     *　@以下の条件で、口座情報マスタテーブルを検索する。<BR>
     *<BR>
     *　@[条件]<BR>
     *　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And<BR>
     *  部店コード= 顧客.getBranch().getBranchCode()  And <BR>
     *  口座コード = 顧客.getAccountCode() <BR>
     *<BR>
     *２）　@口座情報マスタオブジェクト返却 <BR>
     *　@取得した各行オブジェクトについて、口座情報マスタオブジェクトを生成し返却する。<BR> 
     *　@行が取得できなかった場合は、nullを返却する。 <BR>
     *　@行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。<BR> 
     * @@param l_mainAccount - 顧客
     * @@return AccountInfoMstParams
     */
    public static WEB3AccInfoMaster getAccInfoMaster(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccInfoMaster(MainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@口座情報マスタテーブル検索
        //　@以下の条件で、口座情報マスタテーブルを検索する
        
        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = "institution_code = ? and branch_code = ? and account_code = ?";
            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode()
                };

            //以下の条件で、口座情報マスタテーブルを検索する。
            //[条件]
            //　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And
            //  部店コード= 顧客.getBranch().getBranchCode()  And 
            //  口座コード = 顧客.getAccountCode() 
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AccountInfoMstRow.TYPE,
                l_strQueryString,
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //行が取得できなかった場合は、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //２）　@口座情報マスタオブジェクト返却 
        //取得した各行オブジェクトについて、口座情報マスタオブジェクトを生成し返却する。
        else if (l_lisRecords.size() == 1)
        {
            AccountInfoMstRow l_row = (AccountInfoMstRow)l_lisRecords.get(0);
            
            log.exiting(STR_METHOD_NAME);
            return new WEB3AccInfoMaster(new AccountInfoMstParams(l_row));
        }
        //行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。
        else
        {
            log.debug("データ不整合エラー。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME);
        }
    }
}
@
