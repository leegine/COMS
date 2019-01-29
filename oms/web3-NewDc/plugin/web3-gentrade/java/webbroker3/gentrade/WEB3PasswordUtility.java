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
filename	WEB3PasswordUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : パスワードのチェックや変更等の機@能を実装するユーティリティ・クラス(WEB3PasswordUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 菊地(SRA) 新規作成
Revesion History : 2006/11/21 栄イ (中訊)【共通】仕様変更管理モデルNo.216
*/
package webbroker3.gentrade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CodeCheckModeDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.define.WEB3PwdCheckFlagDef;
import webbroker3.common.define.WEB3ReloginFlagDef;
import webbroker3.common.define.WEB3SecurityLevelDef;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * パスワードのチェックや変更等の機@能を実装するユーティリティ・クラス。<BR>
 * <BR>
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public class WEB3PasswordUtility extends WEB3StringTypeUtility 
{
   
   /**
    * 顧客成りすまし時のパスワードチェック桁数
    */
   public static final int PWD_CHECK_LENGTH = 2;
   
   /**
    * チェック正常
    */
   public static final int CHECK_NORMAL = 0;
   
   /**
    * 長さチェックエラー
    */
   public static final int CHECK_ERROR_LENGTH = 1;
   
   /**
    * 文字種エラー
    */
   public static final int CHECK_ERROR_CTYPE = 2;
   
   /**
    * 現在パスワードと同じ
    */
   public static final int CHECK_ERROR_SAME_CURRENT = 3;
   
   /**
    * ログイン名と同じ
    */
   public static final int CHECK_ERROR_SAME_NAME = 4;
   
   /**
    * 旧パスワードと同じ
    */
   public static final int CHECK_ERROR_SAME_BEFORE = 5;
   
   /**
    * 旧パスワードと類似
    */
   public static final int CHECK_ERROR_SIMILAR_BEFORE = 6;
   
   /**
    * 設定エラー。<BR>
    * ログインタイプ属性に必要な設定がない。または、値が不正。<BR>
    */
   public static final int CHECK_ERROR_CONFIG = 99;
   
   /**
    * ログイン属性時刻書式<BR>
    * 書式："yyyy.MM.dd HH:mm:ss"<BR>
    */
   public static SimpleDateFormat loginAttributeDateFormat = 
       GtlUtils.getThreadSafeSimpleDateFormat("yyyy.MM.dd HH:mm:ss");
   
   /**
    * WEB3共通暗号化クラス。
    */
   protected static  WEB3Crypt web3Crypt = new WEB3Crypt();
   
   /**
    * 対象ユーザのログインID。
    */
   protected long loginID;
   
   /**
    * 対象ユーザが所属するログインタイプのセキュリティ・レベル。
    */
   protected String securityLevel;
   
   /**
    * 対象ユーザが所属するログインタイプの属性。
    */
   protected HashMap loginTypeAttrs;
   
   /**
    * Logger
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3PasswordUtility.class);
   
   /**
    * @@roseuid 408DF0100299
    */
   protected WEB3PasswordUtility() 
   {
    
   }
   
   /**
    * 引数のログインIDで指定されたユーザのログインタイプ属性を取得し、<BR>
    * 内部に保持する。<BR>
    * 更にログインタイプ属性からセキュリティ・レベルを取得し、内部に保持する。<BR>
    * @@param l_loginID
    * @@roseuid 4088C004007D
    */
   public WEB3PasswordUtility(long l_loginID) 
   {
       final String STR_METHOD_NAME = "WEB3PasswordUtility(long)";
       log.entering(STR_METHOD_NAME);
       
       loginID        = l_loginID;
       loginTypeAttrs = new HashMap();
       
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);  
       LoginInfo           l_loginInfo    = l_adminService.getLoginInfo(loginID);
       
       loginTypeAttrs.putAll(l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId()));
       
       securityLevel = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL);
       if ( securityLevel == null )
       {
           log.error(STR_METHOD_NAME + " .... セキュリティ・レベルが設定されていない.（LOGIN_ID: " + loginID + "）");
           throw new RuntimeException("セキュリティ・レベルが設定されていない.（LOGIN_ID: " + loginID + "）");
       }
                       
       log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * コード（文字列）の長さが最小長～最大長の範囲であるかチェックする。<BR>
    * 　@　@範囲外の場合、CHECK_ERROR_LENGTHを返す。<BR>
    * <BR>
    * チェック方式に従ってコード（文字列）の種類が妥当であるか確認する。<BR>
    * 　@数字のみ、英字のみ（未対応）、英数字、英数字混在<BR>
    * 　@文字種が妥当でない場合、CHECK_ERROR_CTYPEを返す。<BR>
    * 妥当な値の場合、CHECK_NORMALを返す。<BR>
    * @@param l_codeMin
    * @@param l_codeMax
    * @@param l_checkMode
    * @@param l_codeString
    * @@return int
    * @@roseuid 40A2E93000F4
    */
   public static int checkCode(int l_codeMin, int l_codeMax, String l_checkMode, String l_codeString)
   {
       final String STR_METHOD_NAME = "checkCode(int, int, String, String)";
       log.entering(STR_METHOD_NAME);
       
       /*-------- コード長のチェック --------*/
       int l_codeLen = l_codeString.length();
       if ( (l_codeLen < l_codeMin) || (l_codeMax < l_codeLen) )
       {
           log.debug(STR_METHOD_NAME + " .... error. 長さ不正(LEN: " + l_codeLen + ", MIN: " + l_codeMin + ", MAX: " + l_codeMax + ").");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_LENGTH;
       }
       
       /*-------- 文字種のチェック --------*/
       if (WEB3CodeCheckModeDef.NUMERIC.equals(l_checkMode))
       {   /* 数字のみ */
           if ( !isDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. 文字種不正（数字以外が含まれている）.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else if (WEB3CodeCheckModeDef.ALPHA_OR_NUMERIC.equals(l_checkMode))
       {   /* 英数字（数のみ、英のみ可） */
           if ( !isLetterOrDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. 文字種不正（英数字以外が含まれている）.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else if (WEB3CodeCheckModeDef.ALPHA_AND_NUMERIC.equals(l_checkMode))
       {   /* 英数字混在 */
           if ( !isLetterAndDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. 文字種不正（英数字以外が含まれている.または英数字が混在していない）.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else
       {   /* その他、定義されていないチェック方式 */
           log.error(STR_METHOD_NAME + " .... チェック方式に定義されていないものが指定された.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       
       log.exiting(STR_METHOD_NAME);
       return CHECK_NORMAL;
   }
   
   /**
    * ログインタイプ属性からパスワードの最小長、最大長、チェック方式を取得する。<BR>
    * checkCode()メソッドをコールしてパスワード値の妥当性を判定する。<BR>
    * 返り値をそのまま返す。<BR>
    * @@param l_password - 
    * @@return int
    * @@roseuid 4088C09503B9
    */
   public int checkPassword(String l_password) 
   {
       final String STR_METHOD_NAME = "checkPassword(String)";
       log.entering(STR_METHOD_NAME);
       
       String l_tempMin   = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
       String l_tempMax   = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
       String l_checkMode = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
       
       /*-------- 設定のチェック --------*/
       if ( l_tempMin == null )
       {
           log.error(STR_METHOD_NAME + " .... パスワード最小長が設定されていない.（LOGIN_ID: " + loginID + "）");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       if ( l_tempMax == null )
       {
           log.error(STR_METHOD_NAME + " .... パスワード最大長が設定されていない.（LOGIN_ID: " + loginID + "）");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       if ( l_checkMode == null )
       {
           log.error(STR_METHOD_NAME + " .... パスワードチェック方式が設定されていない.（LOGIN_ID: " + loginID + "）");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       int    l_min = Integer.parseInt(l_tempMin);
       int    l_max = Integer.parseInt(l_tempMax);
       
       int l_result = checkCode(l_min, l_max, l_checkMode, l_password);
       log.exiting(STR_METHOD_NAME);
       return l_result;
   }
   
   /**
    * ログイン属性から３世代前までの旧パスワードを取得し、新パスワードが<BR>
    * それらと類似していない事をチェックする。<BR>
    * 　@新パスワードの先頭からパスワード最小長分を切り出す。<BR>
    * 　@切り出した文字列で旧パスワードの先頭から部分文字列検索する。<BR>
    * 　@※旧パスワードはWEB3共通の機@能で解読する必要がある。<BR>
    * 　@※旧パスワードに部分文字列が含まれている場合「類似」とする。<BR>
    * 類似していない場合true、何れかと類似している場合false。<BR>
    * @@param l_newPassword - 新パスワード
    * @@return boolean
    * @@roseuid 4088C3B00010
    */
   public boolean checkSimilarToBefore(String l_newPassword) 
   {
       final String PWDOLD_ATTR_NAME[] = new String[] {
           WEB3LoginAttributeKeyDef.PASSWORD_OLD1,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD2,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD3
       };
       final String STR_METHOD_NAME = "checkSimilarToBefore(String)";
       log.entering(STR_METHOD_NAME);
       
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
       HashMap             l_loginAttr    = new HashMap();
       l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
        
       int    l_min      = Integer.parseInt((String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
       String l_newParts = (l_newPassword.length() < l_min)? l_newPassword: l_newPassword.substring(0, l_min);
       
       for (int gen_cnt = 0; gen_cnt < PWDOLD_ATTR_NAME.length; gen_cnt++)
       {
           String l_oldPassword = (String)l_loginAttr.get(PWDOLD_ATTR_NAME[gen_cnt]);
           if ( l_oldPassword == null )
           {   /* ログイン属性未設定 */
               log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[gen_cnt] + "未設定.部分文字列検索中断（正常）.");
               break;
           }
           
           l_oldPassword = web3Crypt.decrypt(l_oldPassword);
           if ( l_oldPassword.indexOf(l_newParts) != -1 )
           {   /* 旧パスワード中に新パスワードの先頭と一致する部分文字列が含まれている */
               log.debug(STR_METHOD_NAME + " .... error. 新パスワードは" + PWDOLD_ATTR_NAME[gen_cnt] + "に類似している.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * ログイン属性から３世代前までの旧パスワードを取得し、新パスワードが<BR>
    * それらと一致していない事をチェックする。<BR>
    * 　@　@※新パスワードをWEB3共通の機@能で暗号化し、結果で比較する。<BR>
    * 一致していない場合true、何れかと一致している場合false。<BR>
    * @@param l_newPassword - 新パスワード
    * @@return boolean
    * @@roseuid 4088C261033C
    */
   public boolean checkSameAsBefore(String l_newPassword) 
   {
       final String PWDOLD_ATTR_NAME[] = new String[] {
           WEB3LoginAttributeKeyDef.PASSWORD_OLD1,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD2,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD3
       };
       final String STR_METHOD_NAME = "checkSameAsBefore(String)";
       log.entering(STR_METHOD_NAME);
       
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
       HashMap             l_loginAttr    = new HashMap();
       l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
       
       l_newPassword = web3Crypt.encrypt(l_newPassword);
       for (int gen_cnt = 0; gen_cnt < PWDOLD_ATTR_NAME.length; gen_cnt++)
       {
           String l_oldPassword = (String)l_loginAttr.get(PWDOLD_ATTR_NAME[gen_cnt]);
           if ( l_oldPassword == null )
           {   /* ログイン属性未設定 */
               log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[gen_cnt] + "未設定.文字列比較中断（正常）.");
               break;
           }
           
           if ( l_oldPassword.equals(l_newPassword) )
           {   /* 旧パスワード中の部分文字列と新パスワードが一致している */
               log.debug(STR_METHOD_NAME + " .... error. 新パスワードは" + PWDOLD_ATTR_NAME[gen_cnt] + "と一致している.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * セキュリティ・レベルに従って新パスワードの値チェックを実行する。<BR>
    * 「低」の場合<BR>
    * 　@　@checkCode()メソッドを実行。<BR>
    * 　@　@パスワード長が範囲内であり<BR>
    * 　@　@文字種がチェック方式で指定されたものであるかチェックする。<BR>
    * 　@　@　@　@返り値がチェック正常以外の場合、それをそのまま返す。<BR>
    * 「やや低」の場合<BR>
    * 　@　@現在パスワードと一致していないかチェックする。<BR>
    * 　@　@　@　@一致している場合、CHECK_ERROR_SAME_CURRENTを返す。<BR>
    * 「普通」の場合<BR>
    * 　@　@ログイン名と一致していないかチェックする。<BR>
    * 　@　@　@　@一致している場合、CHECK_ERROR_SAME_NAMEを返す。<BR>
    * 「やや高」の場合<BR>
    * 　@　@checkSameAsBefore()メソッドを実行。<BR>
    * 　@　@旧３世代分のパスワードと一致していないかチェックする。<BR>
    * 　@　@　@　@返り値が不正の場合、CHECK_ERROR_SAME_BEFOREを返す。<BR>
    * 「高」の場合<BR>
    * 　@　@checkSimilarToBefore()メソッド実行。<BR>
    * 　@　@旧３世代分のパスワードと類似していないかチェックする。<BR>
    * 　@　@　@　@返り値が不正の場合、CHECK_ERROR_SIMILAR_BEFOREを返す。<BR>
    * <BR>
    * 上から順にチェックを実行し、指定されたセキュリティ・レベルに対応する<BR>
    * チェックを実行した時点で中断する。<BR>
    * その時点で「不正」と判定されていない場合、CHECK_NORMALを返す。<BR>
    * @@param l_loginName - ログイン名（ログイン画面で入力された値）<BR>
    * 顧客コード（６桁）、CCオペレータコード、管理者コード<BR>
    * @@param l_oldPassword - 現在パスワード
    * @@param l_newPassword - 新パスワード
    * @@return int
    * @@roseuid 4088C4C20204
    */
   public int checkPassword(String l_loginName, String l_oldPassword, String l_newPassword) 
   {
       final String STR_METHOD_NAME = "checkPassword(String, String, String)";
       log.entering(STR_METHOD_NAME);
       
       int l_retValue = CHECK_NORMAL;
       
       /*-------- セキュリティ・レベル：「低」のチェック --------*/
       if ( (l_retValue = checkPassword(l_newPassword)) != CHECK_NORMAL )
       {
           log.exiting(STR_METHOD_NAME);
           return l_retValue;
       }
       if (WEB3SecurityLevelDef.LOW.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... セキュリティ・レベル：「低」.チェック終了.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- セキュリティ・レベル：「やや低」のチェック --------*/
       if ( l_newPassword.equals(l_oldPassword) )
       {
           log.debug(STR_METHOD_NAME + " .... error. 新パスワードが現在パスワードと同じ.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_CURRENT;
       }
       if (WEB3SecurityLevelDef.LITTLE_LOW.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... セキュリティ・レベル：「やや低」.チェック終了.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- セキュリティ・レベル：「普通」のチェック --------*/
       if ( l_newPassword.equals(l_loginName) )
       {
           log.debug(STR_METHOD_NAME + " .... error. 新パスワードがログイン名と同じ.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_NAME;
       }
       if (WEB3SecurityLevelDef.MIDDLE.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... セキュリティ・レベル：「普通」.チェック終了.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- セキュリティ・レベル：「やや高」のチェック --------*/
       if ( !checkSameAsBefore(l_newPassword) )
       {
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_BEFORE;
       }
       if (WEB3SecurityLevelDef.LITTLE_HIGH.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... セキュリティ・レベル：「やや高」.チェック終了.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- セキュリティ・レベル：「高」のチェック --------*/
       if ( !checkSimilarToBefore(l_newPassword) )
       {
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SIMILAR_BEFORE;
       }
       if (WEB3SecurityLevelDef.HIGH.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... セキュリティ・レベル：「高」.チェック終了.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       log.error(STR_METHOD_NAME + " .... 未定義のセキュリティ・レベル[" + securityLevel + "]が設定されている.（LOGIN_ID: " + loginID + "）");
       log.exiting(STR_METHOD_NAME);
       return CHECK_ERROR_CONFIG;
   }
   
   /**
    * 引数：パスワードチェック有無フラグが「チェックあり」の場合<BR>
    * 　@　@入力されたパスワード長が定数：パスワードチェック桁数より小さい場合<BR>
    * 　@　@　@　@不正なパスワードとしてfalse（パスワード不一致）を返す。<BR>
    * 　@　@成りすます顧客のログイン属性から復号化可能パスワードを取得する。<BR>
    * 　@　@取得したパスワードを復号化し、最初のパスワードチェック桁数の部分<BR>
    * 　@　@文字列が一致するかチェックする。<BR>
    * 　@　@　@　@パスワードが一致しない場合、false（パスワード不一致）を返す。<BR>
    * <BR>
    * 引数：パスワードチェック有無フラグが「初期パスワードチェックあり」の場合<BR>
    * 　@　@成りすます顧客のログイン属性から復号化可能初期パスワードを取得する。<BR>
    * 　@　@復号化可能初期パスワードを取得が取得できた場合、取得したパスワードを復号化し、<BR>
    * 　@　@引数.顧客のパスワードと一致するかチェックする。<BR>
    * 　@　@　@　@パスワードが一致しない場合、false（パスワード不一致）を返す。<BR>
    * <BR>
    * 上記でパスワード不一致と判断されなかった場合<BR>
    * 　@　@true（パスワード一致、またはチェック不要）を返す。<BR>
    * @@param l_checkFlag - パスワードチェック有無フラグ
    * @@param l_acceptLoginID - 成りすます顧客のログインID
    * @@param l_inputPassword - 顧客のパスワード
    * @@return boolean
    * @@roseuid 408C9AF20372
    */
   public boolean checkSetAccountPassword(String l_checkFlag, long l_acceptLoginID, String l_inputPassword) 
   {
       final String STR_METHOD_NAME = "checkSetAccountPassword(String, long, String)";
       log.entering(STR_METHOD_NAME);
       
       if (WEB3PwdCheckFlagDef.NO_CHECK.equals(l_checkFlag))
       {   /* パスワードチェック不要 */
           log.debug(STR_METHOD_NAME + " .... 成りすまし時パスワードチェック不要.");
           log.exiting(STR_METHOD_NAME);
           return true;
       }
       else if (WEB3PwdCheckFlagDef.CHECK.equals(l_checkFlag))
       {
           if ( l_inputPassword.length() < PWD_CHECK_LENGTH )
           {   /* パスワード長が短すぎる. */
               log.debug(STR_METHOD_NAME + " .... error. 入力されたパスワードが短すぎる.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
           if ( l_inputPassword.length() > PWD_CHECK_LENGTH )
           {
               l_inputPassword = l_inputPassword.substring(0, PWD_CHECK_LENGTH);
           }
       
           OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
           HashMap             l_loginAttr    = new HashMap();
           l_loginAttr.putAll(l_adminService.getLoginAttributes(l_acceptLoginID));
       
           String l_encryptedPwd = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.PASSWORD);
           String l_password     = web3Crypt.decrypt(l_encryptedPwd);
       
           if ( !l_password.startsWith(l_inputPassword) )
           {   /* パスワード不一致 */
               log.debug(STR_METHOD_NAME + " .... error. パスワードの先頭が一致していない.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
       }
       else if (WEB3PwdCheckFlagDef.INIT_PWD_CHECK.equals(l_checkFlag))
       {
           OpLoginAdminService l_adminService =
               (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
           HashMap l_loginAttr = new HashMap();
           l_loginAttr.putAll(l_adminService.getLoginAttributes(l_acceptLoginID));
           String l_strEncryptedInitPwd =
               (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);
           if (l_strEncryptedInitPwd != null)
           {
               String l_strPassword = web3Crypt.decrypt(l_strEncryptedInitPwd);
               if (!l_strPassword.equals(l_inputPassword))
               {   /* パスワード不一致 */
                   log.debug(STR_METHOD_NAME + " .... error. パスワードが一致していない.");
                   log.exiting(STR_METHOD_NAME);
                   return false;
               }
           }
       }
       else
       {
           log.error(STR_METHOD_NAME + " .... error. パスワードチェック有無フラグ：[" + l_checkFlag + "]");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "パスワードチェック有無フラグ：[" + l_checkFlag + "]");
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * ログインタイプ属性から次を取得する。<BR>
    * 　@　@初回パスワード変更実施フラグ<BR>
    * 　@　@一定期間経過後パスワード変更実施フラグ<BR>
    * 　@　@パスワード有効期間<BR>
    * <BR>
    * 初回パスワード変更フラグが「実施する」の場合<BR>
    * 　@　@ログインユーザのログイン属性から最終ログイン時刻を取得する。<BR>
    * 　@　@最終ログイン時刻を取得できない場合 <BR>
    * 　@　@　@　@初回ログインであるとして、パスワード変更必要（true）を返す。<BR>
    * 　@　@最終ログイン時刻が日付時刻書式でない場合<BR>
    * 　@　@　@　@初回ログインであるとして、パスワード変更必要（true）を返す。<BR>
    * <BR>
    * 一定期間経過後パスワード変更フラグが「実施する」の場合<BR>
    * 　@　@ログインユーザのログイン属性から前回パスワード変更日付を取得する。<BR>
    * 　@　@前回パスワード変更日付が正常な書式ではなく、Date変換に失敗した場合<BR>
    * 　@　@　@　@致命的なシステムエラーをthrowする。<BR>
    * 　@　@<BR>
    * 　@　@取得した日付にパスワード有効期間の月数を加算し、期限とする。<BR>
    * 　@　@計算した期限が現在日付より以前の場合<BR>
    * 　@　@　@　@パスワードの有効期限が切れたとしてパスワード変更必要（true）を返す。<BR>
    * <BR>
    * 変更不要（false）を返す。<BR>
    * @@return boolean
    * @@throws java.text.ParseException
    * @@roseuid 4088C8050119
    */
   public boolean isChangeNecessity() throws ParseException
   {
       final String STR_METHOD_NAME = "isChangeNecessity()";
       log.entering(STR_METHOD_NAME);
       
       String l_changeFirstFlag    = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PWDCHANGE_FIRST_FLAG);
       String l_changeIntervalFlag = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PWDCHANGE_INTERVAL_FLAG);
       String l_tempInterval       = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_INTERVAL);
       int    l_passwordInterval;
       
       /*-------- 設定のチェック --------*/
       if ( l_changeFirstFlag == null )
       {
           log.error(STR_METHOD_NAME + " .... 初回パスワード変更実施フラグが設定されていない.（LOGIN_ID: " + loginID + "）");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       if ( l_changeIntervalFlag == null )
       {
           log.error(STR_METHOD_NAME + " .... 一定期間経過後パスワード変更実施フラグが設定されていない.（LOGIN_ID: " + loginID + "）");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       if ( l_tempInterval == null )
       {
           l_tempInterval = "0";  /* デフォルト：0（有効期間なし）をセット */
           log.error(STR_METHOD_NAME + " .... パスワード有効期間が設定されていない.（LOGIN_ID: " + loginID + "）");
       }
       l_passwordInterval = Integer.parseInt(l_tempInterval);
       
       /*-------- パスワード変更の必要性判定 --------*/
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
       HashMap             l_loginAttr    = new HashMap();
       l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
       
       if (WEB3PwdChangeDef.REQUIRED.equals(l_changeFirstFlag))
       {
           String l_lastLogin = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_LOGIN);
           // 最終ログイン時刻を取得できない場合
           // 　@初回ログインであるとして、パスワード変更必要（true）を返す。
           if (l_lastLogin == null)
           {
               log.debug(STR_METHOD_NAME + " .... （初回）パスワード変更必要.");
               log.exiting(STR_METHOD_NAME);
               return true;
           }
           try
           {
               loginAttributeDateFormat.parse(l_lastLogin);
           }
           catch ( ParseException pex )
           {   /* 属性値が時刻以外（最終ログイン時刻未設定－初回ログイン）*/
               log.debug(STR_METHOD_NAME + " .... （初回）パスワード変更必要.");
               log.exiting(STR_METHOD_NAME);
               return true;
           }
       }
       
       if (WEB3PwdChangeDef.REQUIRED.equals(l_changeIntervalFlag))
       {
           String   l_lastChange = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE);
           Calendar l_changeDate = Calendar.getInstance();
           l_changeDate.setTime(loginAttributeDateFormat.parse(l_lastChange));
           l_changeDate.add(Calendar.MONTH, l_passwordInterval);
           
           Date now = new Date();
           if ( now.after(l_changeDate.getTime()) )
           {   /* パスワード有効期間超過 */
               log.debug(STR_METHOD_NAME + " .... （一定期間）パスワード変更必要.");
               log.exiting(STR_METHOD_NAME);
               return true;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return false;
   }
   
   /**
    * ログインタイプ属性からパスワード変更時再ログイン実施フラグを取得する。<BR>
    * フラグが「実施する」の場合true、「実施しない」の場合falseを返す。<BR>
    * @@return boolean
    * @@roseuid 4088CB5100BC
    */
   public boolean isReloginNecessity() 
   {
       final String STR_METHOD_NAME = "isReloginNecessity()";
       log.entering(STR_METHOD_NAME);
       
       String l_reloginFlag = (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.PWDCHANGE_RELOGIN_FLAG);
       if ( l_reloginFlag == null )
       {
           l_reloginFlag = WEB3ReloginFlagDef.RELOGIN;  /* デフォルト：「再ログイン実施」をセット */
           log.error(STR_METHOD_NAME + " .... パスワード変更時再ログイン実施フラグが設定されていない.（LOGIN_ID: " + loginID + "）");
       }
       
       if (WEB3ReloginFlagDef.CONTINUE.equals(l_reloginFlag))
       {
           log.debug(STR_METHOD_NAME + " .... 再ログイン不要.");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       
       log.debug(STR_METHOD_NAME + " .... 再ログイン必要.");
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * OpLoginAdminServiceを取得する。<BR>
    * OpLoginAdminServiceの機@能を使用してパスワードを変更する。<BR>
    * 　@　@返り値が失敗だった場合は、失敗(false)を返す。<BR>
    * <BR>
    * changeLAPassword()メソッドを実行して、ログイン属性のパスワードを<BR>
    * 変更する。<BR>
    * @@param l_oldPassword - 現在パスワード
    * @@param l_newPassword - 新パスワード
    * @@return boolean
    * @@throws 
    * com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException
    * @@throws java.lang.IllegalArgumentException
    * @@roseuid 408766CF03CC
    */
   public boolean changePassword(String l_oldPassword, String l_newPassword) throws IllegalSessionStateException, IllegalArgumentException 
   {
       final String STR_METHOD_NAME = "changePassword(String, String)";
       log.entering(STR_METHOD_NAME);
       
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);  
       if ( !l_adminService.changePassword(loginID, l_oldPassword, l_newPassword) )
       {
           log.debug(STR_METHOD_NAME + " .... パスワード変更失敗.");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
           
       changeLAPassword(l_newPassword);
       
       log.exiting(STR_METHOD_NAME);
       return true;    
   }
   
   /**
    * ※changePassword()からコールされる事を想定している。<BR>
    * 　@単独で実行すると本物（xTrade管理）のパスワードと一致しなくなるので<BR>
    * 　@非常時の復旧作業など以外では単独で使用してはならない。<BR>
    * <BR>
    * セキュリティ・レベルが「高」または「やや高」の場合<BR>
    * 　@　@以下、３世代～２世代分繰り返す。<BR>
    * 　@　@　@　@対象より１つ前の世代のパスワードを取得する。<BR>
    * 　@　@　@　@※取得できない場合は即座に次（１つ前）を処理する。<BR>
    * 　@　@　@　@　@パスワード変更回数が少なければ、旧パスワード属性も存在しない。<BR>
    * 　@　@　@　@取得したパスワードを対象のパスワードとして属性を更新する。<BR>
    * 　@　@１世代前パスワードに複合化可能パスワードをセットして属性を更新する。<BR>
    * <BR>
    * 新パスワードをWEB3共通の方式で暗号化する。<BR> 
    * ログイン属性の複合化可能パスワードを暗号化した新パスワードで更新する。<BR> 
    * <BR> 
    * 前回パスワード変更日付（LAST_PWDCHANGE）を更新する。<BR> 
    * <BR> 
    * 前回パスワード更新者コード（LAST_PASSWORDCHANGE_UPDATER）を<BR> 
    * 更新する。<BR> 
    * ※ コールセンターからの入力の場合、扱者テーブル.扱者コード。<BR> 
    * ※ 顧客入力の場合、顧客マスタテーブル.顧客コード。<BR> 
    * ※ 管理者入力の場合、管理者テーブル.管理者コード。<BR> 
    * <BR> 
    * @@param l_newPassword - 新パスワード（平文）
    * @@throws java.lang.IllegalArgumentException
    * @@roseuid 4087678503CC
    */
   public void changeLAPassword(String l_newPassword) throws IllegalArgumentException 
   {
       final String PWDOLD_ATTR_NAME[] = new String[] {
           WEB3LoginAttributeKeyDef.PASSWORD,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD1,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD2,
           WEB3LoginAttributeKeyDef.PASSWORD_OLD3
       };
       final String STR_METHOD_NAME = "changeLAPassword(String)";
       log.entering(STR_METHOD_NAME);
       
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
       HashMap             l_loginAttr    = new HashMap();
       l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
       
        if (WEB3SecurityLevelDef.HIGH.equals(securityLevel)
            || WEB3SecurityLevelDef.LITTLE_HIGH.equals(securityLevel))
       {   /* 旧パスワードを３世代前まで保持 */
           for (int gen_cnt = 3; gen_cnt > 0; gen_cnt--)
           {
               String l_befPwd = (String)l_loginAttr.get(PWDOLD_ATTR_NAME[gen_cnt - 1]);
               if ( l_befPwd == null )
               {
                   continue;
               }
               log.debug(STR_METHOD_NAME + " .... 旧パスワード移動（" + PWDOLD_ATTR_NAME[gen_cnt - 1] + " → " + PWDOLD_ATTR_NAME[gen_cnt] + "）");
               l_loginAttr.put(PWDOLD_ATTR_NAME[gen_cnt], l_befPwd);
           }
       }
       
       //ログイン属性の複合化可能パスワードを暗号化した新パスワードで更新する
       String l_encryptedPwd = web3Crypt.encrypt(l_newPassword);
       l_loginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD, l_encryptedPwd);
       
       // 前回パスワード変更日付（LAST_PWDCHANGE）を更新する
       String l_pwdChangeDate = loginAttributeDateFormat.format(GtlUtils.getSystemTimestamp());
       l_loginAttr.put(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE, l_pwdChangeDate);
       
       OpLoginSecurityService l_opLoginSec =
           (OpLoginSecurityService) Services.getService(
               OpLoginSecurityService.class);
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       MainAccount l_mainAccount = null;
       Trader l_trader = null;
       AdministratorRow l_administratorRow = null;
       String l_strLastUpdater = null;  
             
       // 顧客入力の場合、顧客マスタテーブル.顧客コードを取得する。
       if (l_opLoginSec.isAccountIdSet())
       {
           try
           {
               l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_opLoginSec.getAccountId());
               l_strLastUpdater = l_mainAccount.getAccountCode();
           }
           catch (NotFoundException nfe)
           {
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   nfe.getMessage(),
                   nfe);
           }
       }
       else
       {
           try
           {
               l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(loginID);
           }
           catch (NotFoundException nfe)
           {
           }
           
           // コールセンターからの入力の場合、扱者テーブル.扱者コードを取得する。
           if(l_trader != null)
           {
               l_strLastUpdater = l_trader.getTraderCode();
           }
           //管理者入力の場合、管理者テーブル.管理者コードを取得する。
           else
           {
               try
               {
                   l_administratorRow =
                       AdministratorDao.findRowByLoginId(loginID);
               }
               catch (DataException de)
               {
                   throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                       this.getClass().getName() +"." + STR_METHOD_NAME,
                       de.getMessage(),
                       de);
               }
               if (l_administratorRow == null)
               {
                   throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() +"." + STR_METHOD_NAME,
                       "管理者行オブジェクト = null");
               }
               l_strLastUpdater = l_administratorRow.getAdministratorCode();
           }
           
       }
       
       //前回パスワード更新者コード（LAST_PASSWORDCHANGE_UPDATER）
       //を更新する
       l_loginAttr.put(
           WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER,
           l_strLastUpdater);
       
       l_adminService.setLoginAttributes(loginID, l_loginAttr);    
       
       log.exiting(STR_METHOD_NAME);
   }
}
@
