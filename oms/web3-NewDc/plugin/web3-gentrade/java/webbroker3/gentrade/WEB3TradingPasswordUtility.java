head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingPasswordUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : パスワードのチェックや変更等の機@能を実装するユーティリティ・クラス(WEB3TradingPasswordUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History :  2004/10/22 張麗維 (中訊)  新規作成
*/
package webbroker3.gentrade;

import java.util.HashMap;

import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3SecurityLevelDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;

public class WEB3TradingPasswordUtility extends WEB3PasswordUtility
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TradingPasswordUtility.class);

    /**
      *コンストラクタ 
      *１）　@引数のログインIDで指定されたユーザのログインタイプ属性を取得し、<BR>
      *プロパティにセットする。<BR> 
      * <BR>
      * ２）　@ログインタイプ属性からセキュリティ・レベル※を取得し、<BR>
      * 内部に保持する。 <BR>
      *  <BR>
      * ※　@セキュリティ・レベル <BR>
      * （ログインタイプ属性.属性名 == 取引パスワードセキュリティ・レベル）の <BR>
      * 行が存在する場合、 <BR>
      * －（ログインタイプ属性.属性名 == 取引パスワードセキュリティ・レベル） <BR>
      * の属性値を取引パスワードのセキュリティレベルとする。 <BR>
      *  <BR>
      * （ログインタイプ属性.属性名 == 取引パスワードセキュリティ・レベル）の <BR>
      * 行が存在しない場合、 <BR>
      * －（ログインタイプ属性.属性名 == セキュリティ・レベル）の属性値を <BR>
      * 取引パスワードのセキュリティレベルとする。 <BR>
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#WEB3PasswordUtility
      * @@param l_loginID
      */
    public WEB3TradingPasswordUtility(long l_loginID)
    {

        final String STR_METHOD_NAME = "WEB3TradingPasswordUtility(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数のログインIDで指定されたユーザのログインタイプ属性を取得し、
        //プロパティにセットする
        loginID = l_loginID;
        loginTypeAttrs = new HashMap();
        OpLoginAdminService l_adminService =
            (OpLoginAdminService) Services.getService(
                OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_adminService.getLoginInfo(loginID);
        loginTypeAttrs.putAll(
            l_adminService.getLoginTypeAttributes(
                l_loginInfo.getLoginTypeId()));

        //２）　@ログインタイプ属性からセキュリティ・レベル※を取得し、
        // 内部に保持する

        //get セキュリティ・レベル
        String l_strSecurityLevel =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL);

        //get 取引パスワードセキュリティ・レベル
        String l_strTradingPwdSecurityLevel =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_SECURITY_LEVEL);

        if (l_strTradingPwdSecurityLevel != null)
        {
            securityLevel = l_strTradingPwdSecurityLevel;
        }
        else
        {
            securityLevel = l_strSecurityLevel;
        }

        if (securityLevel == null)
        {
            log.error(
                STR_METHOD_NAME
                    + " .... セキュリティ・レベルが設定されていない.（LOGIN_ID: "
                    + loginID
                    + "）");
            throw new RuntimeException(
                "セキュリティ・レベルが設定されていない.（LOGIN_ID: " + loginID + "）");
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
      *（checkSimilarToBeforeのoverride） <BR>
      * <BR>
      * ログイン属性から３世代前までの旧取引パスワードを取得し、<BR>
      * 新パスワードがそれらと類似していない事をチェックする。 <BR>
      * 類似していない場合true、何れかと一致している場合falseを返却する。<BR> 
      *  <BR>
      * ※　@旧取引パスワード <BR>
      * 1世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD1）のログイン属性値 <BR>
      * ２世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD2）のログイン属性値 <BR>
      * ３世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD3）のログイン属性値 <BR>
      *  <BR>
      * ※　@旧パスワードの取得個所が違うのみで、以外の手続きは <BR>
      * スーパークラス（WEB3PasswordUtility）と同一処理とする。<BR> 
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#checkSimilarToBefore<BR>
      * @@param l_strNewPassword - (新パスワード)<BR>
      */
    public boolean checkSimilarToBefore(String l_strNewPassword) 
    {

        final String STR_METHOD_NAME = "checkSimilarToBefore(String)";
        log.entering(STR_METHOD_NAME);
        
        //1世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD1）のログイン属性値 
        //２世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD2）のログイン属性値 
        //３世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD3）のログイン属性値
        final String PWDOLD_ATTR_NAME[] = new String[] {
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3
        };
        
        //ログイン属性を取得する
        OpLoginAdminService l_adminService =
            (OpLoginAdminService) Services.getService(
                OpLoginAdminService.class);
        HashMap l_loginAttributes = new HashMap();
        l_loginAttributes.putAll(l_adminService.getLoginAttributes(loginID));
        
        //パスワード最小長よりパスワードを取得する
        int l_intPwdMinLength =
            Integer.parseInt(
                (String) loginTypeAttrs.get(
                    WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
        String l_strNewPwdParts =
            (l_strNewPassword.length() < l_intPwdMinLength)
                ? l_strNewPassword
                : l_strNewPassword.substring(0, l_intPwdMinLength);
        
        for (int i = 0; i < PWDOLD_ATTR_NAME.length; i++)
        {
            String l_oldPassword = (String)l_loginAttributes.get(PWDOLD_ATTR_NAME[i]);
            if ( l_oldPassword == null )
            {   /* ログイン属性未設定 */
                log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[i] + "未設定.部分文字列検索中断（正常）.");
                break;
            }
           
            l_oldPassword = web3Crypt.decrypt(l_oldPassword);
            if ( l_oldPassword.indexOf(l_strNewPwdParts) != -1 )
            {   /* 旧パスワード中に新パスワードの先頭と一致する部分文字列が含まれている */
                log.debug(STR_METHOD_NAME + " .... error. 新パスワードは" + PWDOLD_ATTR_NAME[i] + "に類似している.");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
        
    }
    
    /**
      *（checkSameAsBeforeのoverride） <BR>
      * <BR>
      * ログイン属性から３世代前までの旧パスワードを取得し、<BR>
      * 新パスワードがそれらと一致していない事をチェックする。<BR> 
      * 一致していない場合true、何れかと一致している場合falseを返却する。<BR> 
      * <BR>
      * ※　@旧取引パスワード <BR>
      * 1世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD1）のログイン属性値 <BR>
      * ２世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD2）のログイン属性値 <BR>
      * ３世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD3）のログイン属性値 <BR>
      *  <BR>
      * ※　@旧パスワードの取得個所が違うのみで、<BR>
      * 以外の手続きはスーパークラス（WEB3PasswordUtility）と同一処理とする。<BR> 
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#checkSameAsBefore
      * @@param l_strNewPassword - (新パスワード)
      */
    public boolean checkSameAsBefore(String l_strNewPassword) 
    {

        final String STR_METHOD_NAME = "checkSameAsBefore(String)";
        log.entering(STR_METHOD_NAME);
        
        //1世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD1）のログイン属性値 
        //２世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD2）のログイン属性値 
        //３世代前：　@（ログイン属性名 == TRADING_PASSWORD_OLD3）のログイン属性値
        final String PWDOLD_ATTR_NAME[] = new String[] {
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3
        };
        
        //ログイン属性を取得する
        OpLoginAdminService l_adminService =
            (OpLoginAdminService) Services.getService(
                OpLoginAdminService.class);
        HashMap l_loginAttr = new HashMap();
        l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
        
        l_strNewPassword = web3Crypt.encrypt(l_strNewPassword);
        for (int i = 0; i < PWDOLD_ATTR_NAME.length; i++)
        {
            String l_oldPassword = (String)l_loginAttr.get(PWDOLD_ATTR_NAME[i]);
            if ( l_oldPassword == null )
            {   /* ログイン属性未設定 */
                log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[i] + "未設定.文字列比較中断（正常）.");
                break;
            }
           
            if ( l_oldPassword.equals(l_strNewPassword) )
            {   /* 旧パスワード中の部分文字列と新パスワードが一致している */
                log.debug(STR_METHOD_NAME + " .... error. 新パスワードは" + PWDOLD_ATTR_NAME[i] + "と一致している.");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
       
        log.exiting(STR_METHOD_NAME);
        return true;
        
    }
    
    /**
      *（getPasswordMinLength） <BR>
      * <BR>
      * パスワード最小長を取得する。 <BR>
      * <BR>
      * this.ログインタイプ属性より、属性値.パスワード <BR>
      * 最小長（：PASSWORD_MIN_LENGTH）の属性値を返却する。 <BR>
      */
    public String getPasswordMinLength() 
    {
        String l_strPasswordMinLength =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
        return l_strPasswordMinLength;
    }
    
    /**
      *（getPasswordMaxLength） <BR>
      * <BR>
      * パスワード最大長を取得する。 <BR>
      *  <BR>
      * this.ログインタイプ属性より、属性値.パスワード<BR>
      * 最大長（：PASSWORD_MAX_LENGTH）の属性値を返却する。<BR>
      */
    public String getPasswordMaxLength() 
    {
        String l_strPasswordMaxLength =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
        return l_strPasswordMaxLength;
    }
    
    /**
      *（getPasswordCheckMode） <BR>
      * <BR>
      * パスワードチェック形式を取得する。 <BR>
      *  <BR>
      * this.ログインタイプ属性より、属性値.パスワードチェック<BR>
      * 形式（：PASSWORD_CHECK_MODE）の属性値を返却する。<BR>
      */
    public String getPasswordCheckMode() 
    {        
        String l_strPasswordCheckMode =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
        return l_strPasswordCheckMode;
    }
    
    /**
      *（saveOldTradingPasswords ） <BR>
      * <BR>
      * 3世代前までの取引パスワードをログイン属性に保存する。 <BR>
      * （セキュリティ・レベルが「高」または「やや高」の場合のみ処理実施）<BR> 
      *  <BR>
      * ※　@更新には、OpLoginAdminService.setLoginAttributes()メソッド<BR>
      * を使用する。<BR> 
      * ※　@更新内容は、【ｘTrade】補足資料.DB更新　@<BR>
      * 「暗証番号変更_ログイン属性.xls」参照。<BR> 
      * <BR>
      * @@param l_strCurrentPassword - (変更前の取引パスワード（現在値）)
      */
    public void saveOldTradingPasswords(String l_strCurrentPassword) 
    {
        final String STR_METHOD_NAME = "saveOldTradingPasswords(String)";
        log.entering(STR_METHOD_NAME);
        
        //getセキュリティ・レベル
        //①@ ログインタイプ属性.属性名 == 
        //    取引パスワードセキュリティ・レベル（：TRADING_PWDSECURITY_LEVEL）の行がある場合、
        //    ログインタイプ属性.属性名 == 
        //    取引パスワードセキュリティ・レベル（：TRADING_PWDSECURITY_LEVEL）の属性値。 
        //② ログインタイプ属性.属性名 == 
        //    取引パスワードセキュリティ・レベル（：TRADING_PWDSECURITY_LEVEL）の行がない場合、
        //    ログインタイプ属性.属性名 == セキュリティ・レベル（：SECURITY_LEVEL）の属性値。 
        String l_strSecurityLevel = 
            (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_SECURITY_LEVEL); 
        if(l_strSecurityLevel == null)
        {
            l_strSecurityLevel = 
                (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL); 
        }
        
        //セキュリティ・レベル※が”やや高”，”高”のいずれかの場合。
        if (WEB3SecurityLevelDef.LITTLE_HIGH.equals(l_strSecurityLevel)
            || WEB3SecurityLevelDef.HIGH.equals(l_strSecurityLevel))
        { 
            OpLoginAdminService l_adminService =
                (OpLoginAdminService) Services.getService(OpLoginAdminService.class);
            HashMap l_loginAttr = new HashMap();
            l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
            
            //ログイン属性名 == TRADING_PASSWORD_OLD2に該当する既存行がある場合
            //update ３世代前取引パスワード
            String l_strPwdOld2 =
                (String) l_loginAttr.get(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2);
            if (l_strPwdOld2 != null)
            {
                l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3,l_strPwdOld2);
            }
            
            //ログイン属性名 == TRADING_PASSWORD_OLD1に該当する既存行がある場合   
            //update ２世代前取引パスワード 
            String l_strPwdOld1 =
                (String) l_loginAttr.get(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1);
            if (l_strPwdOld1 != null)
            {
                l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,l_strPwdOld1);
            }
            
            //update １世代前取引パスワード 
            l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1, l_strCurrentPassword);
            
            l_adminService.setLoginAttributes(loginID, l_loginAttr); 

        }                                                            
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
