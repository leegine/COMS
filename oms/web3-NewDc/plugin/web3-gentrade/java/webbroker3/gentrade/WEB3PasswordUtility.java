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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �p�X���[�h�̃`�F�b�N��ύX���̋@@�\���������郆�[�e�B���e�B�E�N���X(WEB3PasswordUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 �e�n(SRA) �V�K�쐬
Revesion History : 2006/11/21 �h�C (���u)�y���ʁz�d�l�ύX�Ǘ����f��No.216
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
 * �p�X���[�h�̃`�F�b�N��ύX���̋@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 * <BR>
 * @@author �e�n(SRA)
 * @@version 1.0
 */
public class WEB3PasswordUtility extends WEB3StringTypeUtility 
{
   
   /**
    * �ڋq���肷�܂����̃p�X���[�h�`�F�b�N����
    */
   public static final int PWD_CHECK_LENGTH = 2;
   
   /**
    * �`�F�b�N����
    */
   public static final int CHECK_NORMAL = 0;
   
   /**
    * �����`�F�b�N�G���[
    */
   public static final int CHECK_ERROR_LENGTH = 1;
   
   /**
    * ������G���[
    */
   public static final int CHECK_ERROR_CTYPE = 2;
   
   /**
    * ���݃p�X���[�h�Ɠ���
    */
   public static final int CHECK_ERROR_SAME_CURRENT = 3;
   
   /**
    * ���O�C�����Ɠ���
    */
   public static final int CHECK_ERROR_SAME_NAME = 4;
   
   /**
    * ���p�X���[�h�Ɠ���
    */
   public static final int CHECK_ERROR_SAME_BEFORE = 5;
   
   /**
    * ���p�X���[�h�Ɨގ�
    */
   public static final int CHECK_ERROR_SIMILAR_BEFORE = 6;
   
   /**
    * �ݒ�G���[�B<BR>
    * ���O�C���^�C�v�����ɕK�v�Ȑݒ肪�Ȃ��B�܂��́A�l���s���B<BR>
    */
   public static final int CHECK_ERROR_CONFIG = 99;
   
   /**
    * ���O�C��������������<BR>
    * �����F"yyyy.MM.dd HH:mm:ss"<BR>
    */
   public static SimpleDateFormat loginAttributeDateFormat = 
       GtlUtils.getThreadSafeSimpleDateFormat("yyyy.MM.dd HH:mm:ss");
   
   /**
    * WEB3���ʈÍ����N���X�B
    */
   protected static  WEB3Crypt web3Crypt = new WEB3Crypt();
   
   /**
    * �Ώۃ��[�U�̃��O�C��ID�B
    */
   protected long loginID;
   
   /**
    * �Ώۃ��[�U���������郍�O�C���^�C�v�̃Z�L�����e�B�E���x���B
    */
   protected String securityLevel;
   
   /**
    * �Ώۃ��[�U���������郍�O�C���^�C�v�̑����B
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
    * �����̃��O�C��ID�Ŏw�肳�ꂽ���[�U�̃��O�C���^�C�v�������擾���A<BR>
    * �����ɕێ�����B<BR>
    * �X�Ƀ��O�C���^�C�v��������Z�L�����e�B�E���x�����擾���A�����ɕێ�����B<BR>
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
           log.error(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x�����ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
           throw new RuntimeException("�Z�L�����e�B�E���x�����ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
       }
                       
       log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * �R�[�h�i������j�̒������ŏ����`�ő咷�͈̔͂ł��邩�`�F�b�N����B<BR>
    * �@@�@@�͈͊O�̏ꍇ�ACHECK_ERROR_LENGTH��Ԃ��B<BR>
    * <BR>
    * �`�F�b�N�����ɏ]���ăR�[�h�i������j�̎�ނ��Ó��ł��邩�m�F����B<BR>
    * �@@�����̂݁A�p���̂݁i���Ή��j�A�p�����A�p��������<BR>
    * �@@�����킪�Ó��łȂ��ꍇ�ACHECK_ERROR_CTYPE��Ԃ��B<BR>
    * �Ó��Ȓl�̏ꍇ�ACHECK_NORMAL��Ԃ��B<BR>
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
       
       /*-------- �R�[�h���̃`�F�b�N --------*/
       int l_codeLen = l_codeString.length();
       if ( (l_codeLen < l_codeMin) || (l_codeMax < l_codeLen) )
       {
           log.debug(STR_METHOD_NAME + " .... error. �����s��(LEN: " + l_codeLen + ", MIN: " + l_codeMin + ", MAX: " + l_codeMax + ").");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_LENGTH;
       }
       
       /*-------- ������̃`�F�b�N --------*/
       if (WEB3CodeCheckModeDef.NUMERIC.equals(l_checkMode))
       {   /* �����̂� */
           if ( !isDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. ������s���i�����ȊO���܂܂�Ă���j.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else if (WEB3CodeCheckModeDef.ALPHA_OR_NUMERIC.equals(l_checkMode))
       {   /* �p�����i���̂݁A�p�̂݉j */
           if ( !isLetterOrDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. ������s���i�p�����ȊO���܂܂�Ă���j.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else if (WEB3CodeCheckModeDef.ALPHA_AND_NUMERIC.equals(l_checkMode))
       {   /* �p�������� */
           if ( !isLetterAndDigit(l_codeString) )
           {
               log.debug(STR_METHOD_NAME + " .... error. ������s���i�p�����ȊO���܂܂�Ă���.�܂��͉p���������݂��Ă��Ȃ��j.");
               log.exiting(STR_METHOD_NAME);
               return CHECK_ERROR_CTYPE;
           }
       }
       else
       {   /* ���̑��A��`����Ă��Ȃ��`�F�b�N���� */
           log.error(STR_METHOD_NAME + " .... �`�F�b�N�����ɒ�`����Ă��Ȃ����̂��w�肳�ꂽ.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       
       log.exiting(STR_METHOD_NAME);
       return CHECK_NORMAL;
   }
   
   /**
    * ���O�C���^�C�v��������p�X���[�h�̍ŏ����A�ő咷�A�`�F�b�N�������擾����B<BR>
    * checkCode()���\�b�h���R�[�����ăp�X���[�h�l�̑Ó����𔻒肷��B<BR>
    * �Ԃ�l�����̂܂ܕԂ��B<BR>
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
       
       /*-------- �ݒ�̃`�F�b�N --------*/
       if ( l_tempMin == null )
       {
           log.error(STR_METHOD_NAME + " .... �p�X���[�h�ŏ������ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       if ( l_tempMax == null )
       {
           log.error(STR_METHOD_NAME + " .... �p�X���[�h�ő咷���ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_CONFIG;
       }
       if ( l_checkMode == null )
       {
           log.error(STR_METHOD_NAME + " .... �p�X���[�h�`�F�b�N�������ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
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
    * ���O�C����������R����O�܂ł̋��p�X���[�h���擾���A�V�p�X���[�h��<BR>
    * �����Ɨގ����Ă��Ȃ������`�F�b�N����B<BR>
    * �@@�V�p�X���[�h�̐擪����p�X���[�h�ŏ�������؂�o���B<BR>
    * �@@�؂�o����������ŋ��p�X���[�h�̐擪���畔�������񌟍�����B<BR>
    * �@@�����p�X���[�h��WEB3���ʂ̋@@�\�ŉ�ǂ���K�v������B<BR>
    * �@@�����p�X���[�h�ɕ��������񂪊܂܂�Ă���ꍇ�u�ގ��v�Ƃ���B<BR>
    * �ގ����Ă��Ȃ��ꍇtrue�A���ꂩ�Ɨގ����Ă���ꍇfalse�B<BR>
    * @@param l_newPassword - �V�p�X���[�h
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
           {   /* ���O�C���������ݒ� */
               log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[gen_cnt] + "���ݒ�.���������񌟍����f�i����j.");
               break;
           }
           
           l_oldPassword = web3Crypt.decrypt(l_oldPassword);
           if ( l_oldPassword.indexOf(l_newParts) != -1 )
           {   /* ���p�X���[�h���ɐV�p�X���[�h�̐擪�ƈ�v���镔�������񂪊܂܂�Ă��� */
               log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h��" + PWDOLD_ATTR_NAME[gen_cnt] + "�ɗގ����Ă���.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * ���O�C����������R����O�܂ł̋��p�X���[�h���擾���A�V�p�X���[�h��<BR>
    * �����ƈ�v���Ă��Ȃ������`�F�b�N����B<BR>
    * �@@�@@���V�p�X���[�h��WEB3���ʂ̋@@�\�ňÍ������A���ʂŔ�r����B<BR>
    * ��v���Ă��Ȃ��ꍇtrue�A���ꂩ�ƈ�v���Ă���ꍇfalse�B<BR>
    * @@param l_newPassword - �V�p�X���[�h
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
           {   /* ���O�C���������ݒ� */
               log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[gen_cnt] + "���ݒ�.�������r���f�i����j.");
               break;
           }
           
           if ( l_oldPassword.equals(l_newPassword) )
           {   /* ���p�X���[�h���̕���������ƐV�p�X���[�h����v���Ă��� */
               log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h��" + PWDOLD_ATTR_NAME[gen_cnt] + "�ƈ�v���Ă���.");
               log.exiting(STR_METHOD_NAME);
               return false;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * �Z�L�����e�B�E���x���ɏ]���ĐV�p�X���[�h�̒l�`�F�b�N�����s����B<BR>
    * �u��v�̏ꍇ<BR>
    * �@@�@@checkCode()���\�b�h�����s�B<BR>
    * �@@�@@�p�X���[�h�����͈͓��ł���<BR>
    * �@@�@@�����킪�`�F�b�N�����Ŏw�肳�ꂽ���̂ł��邩�`�F�b�N����B<BR>
    * �@@�@@�@@�@@�Ԃ�l���`�F�b�N����ȊO�̏ꍇ�A��������̂܂ܕԂ��B<BR>
    * �u����v�̏ꍇ<BR>
    * �@@�@@���݃p�X���[�h�ƈ�v���Ă��Ȃ����`�F�b�N����B<BR>
    * �@@�@@�@@�@@��v���Ă���ꍇ�ACHECK_ERROR_SAME_CURRENT��Ԃ��B<BR>
    * �u���ʁv�̏ꍇ<BR>
    * �@@�@@���O�C�����ƈ�v���Ă��Ȃ����`�F�b�N����B<BR>
    * �@@�@@�@@�@@��v���Ă���ꍇ�ACHECK_ERROR_SAME_NAME��Ԃ��B<BR>
    * �u��⍂�v�̏ꍇ<BR>
    * �@@�@@checkSameAsBefore()���\�b�h�����s�B<BR>
    * �@@�@@���R���㕪�̃p�X���[�h�ƈ�v���Ă��Ȃ����`�F�b�N����B<BR>
    * �@@�@@�@@�@@�Ԃ�l���s���̏ꍇ�ACHECK_ERROR_SAME_BEFORE��Ԃ��B<BR>
    * �u���v�̏ꍇ<BR>
    * �@@�@@checkSimilarToBefore()���\�b�h���s�B<BR>
    * �@@�@@���R���㕪�̃p�X���[�h�Ɨގ����Ă��Ȃ����`�F�b�N����B<BR>
    * �@@�@@�@@�@@�Ԃ�l���s���̏ꍇ�ACHECK_ERROR_SIMILAR_BEFORE��Ԃ��B<BR>
    * <BR>
    * �ォ�珇�Ƀ`�F�b�N�����s���A�w�肳�ꂽ�Z�L�����e�B�E���x���ɑΉ�����<BR>
    * �`�F�b�N�����s�������_�Œ��f����B<BR>
    * ���̎��_�Łu�s���v�Ɣ��肳��Ă��Ȃ��ꍇ�ACHECK_NORMAL��Ԃ��B<BR>
    * @@param l_loginName - ���O�C�����i���O�C����ʂœ��͂��ꂽ�l�j<BR>
    * �ڋq�R�[�h�i�U���j�ACC�I�y���[�^�R�[�h�A�Ǘ��҃R�[�h<BR>
    * @@param l_oldPassword - ���݃p�X���[�h
    * @@param l_newPassword - �V�p�X���[�h
    * @@return int
    * @@roseuid 4088C4C20204
    */
   public int checkPassword(String l_loginName, String l_oldPassword, String l_newPassword) 
   {
       final String STR_METHOD_NAME = "checkPassword(String, String, String)";
       log.entering(STR_METHOD_NAME);
       
       int l_retValue = CHECK_NORMAL;
       
       /*-------- �Z�L�����e�B�E���x���F�u��v�̃`�F�b�N --------*/
       if ( (l_retValue = checkPassword(l_newPassword)) != CHECK_NORMAL )
       {
           log.exiting(STR_METHOD_NAME);
           return l_retValue;
       }
       if (WEB3SecurityLevelDef.LOW.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x���F�u��v.�`�F�b�N�I��.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- �Z�L�����e�B�E���x���F�u����v�̃`�F�b�N --------*/
       if ( l_newPassword.equals(l_oldPassword) )
       {
           log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h�����݃p�X���[�h�Ɠ���.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_CURRENT;
       }
       if (WEB3SecurityLevelDef.LITTLE_LOW.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x���F�u����v.�`�F�b�N�I��.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- �Z�L�����e�B�E���x���F�u���ʁv�̃`�F�b�N --------*/
       if ( l_newPassword.equals(l_loginName) )
       {
           log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h�����O�C�����Ɠ���.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_NAME;
       }
       if (WEB3SecurityLevelDef.MIDDLE.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x���F�u���ʁv.�`�F�b�N�I��.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- �Z�L�����e�B�E���x���F�u��⍂�v�̃`�F�b�N --------*/
       if ( !checkSameAsBefore(l_newPassword) )
       {
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SAME_BEFORE;
       }
       if (WEB3SecurityLevelDef.LITTLE_HIGH.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x���F�u��⍂�v.�`�F�b�N�I��.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       /*-------- �Z�L�����e�B�E���x���F�u���v�̃`�F�b�N --------*/
       if ( !checkSimilarToBefore(l_newPassword) )
       {
           log.exiting(STR_METHOD_NAME);
           return CHECK_ERROR_SIMILAR_BEFORE;
       }
       if (WEB3SecurityLevelDef.HIGH.equals(securityLevel))
       {
           log.debug(STR_METHOD_NAME + " .... �Z�L�����e�B�E���x���F�u���v.�`�F�b�N�I��.");
           log.exiting(STR_METHOD_NAME);
           return CHECK_NORMAL;
       }
       
       log.error(STR_METHOD_NAME + " .... ����`�̃Z�L�����e�B�E���x��[" + securityLevel + "]���ݒ肳��Ă���.�iLOGIN_ID: " + loginID + "�j");
       log.exiting(STR_METHOD_NAME);
       return CHECK_ERROR_CONFIG;
   }
   
   /**
    * �����F�p�X���[�h�`�F�b�N�L���t���O���u�`�F�b�N����v�̏ꍇ<BR>
    * �@@�@@���͂��ꂽ�p�X���[�h�����萔�F�p�X���[�h�`�F�b�N������菬�����ꍇ<BR>
    * �@@�@@�@@�@@�s���ȃp�X���[�h�Ƃ���false�i�p�X���[�h�s��v�j��Ԃ��B<BR>
    * �@@�@@���肷�܂��ڋq�̃��O�C���������畜�����\�p�X���[�h���擾����B<BR>
    * �@@�@@�擾�����p�X���[�h�𕜍������A�ŏ��̃p�X���[�h�`�F�b�N�����̕���<BR>
    * �@@�@@�����񂪈�v���邩�`�F�b�N����B<BR>
    * �@@�@@�@@�@@�p�X���[�h����v���Ȃ��ꍇ�Afalse�i�p�X���[�h�s��v�j��Ԃ��B<BR>
    * <BR>
    * �����F�p�X���[�h�`�F�b�N�L���t���O���u�����p�X���[�h�`�F�b�N����v�̏ꍇ<BR>
    * �@@�@@���肷�܂��ڋq�̃��O�C���������畜�����\�����p�X���[�h���擾����B<BR>
    * �@@�@@�������\�����p�X���[�h���擾���擾�ł����ꍇ�A�擾�����p�X���[�h�𕜍������A<BR>
    * �@@�@@����.�ڋq�̃p�X���[�h�ƈ�v���邩�`�F�b�N����B<BR>
    * �@@�@@�@@�@@�p�X���[�h����v���Ȃ��ꍇ�Afalse�i�p�X���[�h�s��v�j��Ԃ��B<BR>
    * <BR>
    * ��L�Ńp�X���[�h�s��v�Ɣ��f����Ȃ������ꍇ<BR>
    * �@@�@@true�i�p�X���[�h��v�A�܂��̓`�F�b�N�s�v�j��Ԃ��B<BR>
    * @@param l_checkFlag - �p�X���[�h�`�F�b�N�L���t���O
    * @@param l_acceptLoginID - ���肷�܂��ڋq�̃��O�C��ID
    * @@param l_inputPassword - �ڋq�̃p�X���[�h
    * @@return boolean
    * @@roseuid 408C9AF20372
    */
   public boolean checkSetAccountPassword(String l_checkFlag, long l_acceptLoginID, String l_inputPassword) 
   {
       final String STR_METHOD_NAME = "checkSetAccountPassword(String, long, String)";
       log.entering(STR_METHOD_NAME);
       
       if (WEB3PwdCheckFlagDef.NO_CHECK.equals(l_checkFlag))
       {   /* �p�X���[�h�`�F�b�N�s�v */
           log.debug(STR_METHOD_NAME + " .... ���肷�܂����p�X���[�h�`�F�b�N�s�v.");
           log.exiting(STR_METHOD_NAME);
           return true;
       }
       else if (WEB3PwdCheckFlagDef.CHECK.equals(l_checkFlag))
       {
           if ( l_inputPassword.length() < PWD_CHECK_LENGTH )
           {   /* �p�X���[�h�����Z������. */
               log.debug(STR_METHOD_NAME + " .... error. ���͂��ꂽ�p�X���[�h���Z������.");
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
           {   /* �p�X���[�h�s��v */
               log.debug(STR_METHOD_NAME + " .... error. �p�X���[�h�̐擪����v���Ă��Ȃ�.");
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
               {   /* �p�X���[�h�s��v */
                   log.debug(STR_METHOD_NAME + " .... error. �p�X���[�h����v���Ă��Ȃ�.");
                   log.exiting(STR_METHOD_NAME);
                   return false;
               }
           }
       }
       else
       {
           log.error(STR_METHOD_NAME + " .... error. �p�X���[�h�`�F�b�N�L���t���O�F[" + l_checkFlag + "]");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�p�X���[�h�`�F�b�N�L���t���O�F[" + l_checkFlag + "]");
       }
       
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * ���O�C���^�C�v�������玟���擾����B<BR>
    * �@@�@@����p�X���[�h�ύX���{�t���O<BR>
    * �@@�@@�����Ԍo�ߌ�p�X���[�h�ύX���{�t���O<BR>
    * �@@�@@�p�X���[�h�L������<BR>
    * <BR>
    * ����p�X���[�h�ύX�t���O���u���{����v�̏ꍇ<BR>
    * �@@�@@���O�C�����[�U�̃��O�C����������ŏI���O�C���������擾����B<BR>
    * �@@�@@�ŏI���O�C���������擾�ł��Ȃ��ꍇ <BR>
    * �@@�@@�@@�@@���񃍃O�C���ł���Ƃ��āA�p�X���[�h�ύX�K�v�itrue�j��Ԃ��B<BR>
    * �@@�@@�ŏI���O�C�����������t���������łȂ��ꍇ<BR>
    * �@@�@@�@@�@@���񃍃O�C���ł���Ƃ��āA�p�X���[�h�ύX�K�v�itrue�j��Ԃ��B<BR>
    * <BR>
    * �����Ԍo�ߌ�p�X���[�h�ύX�t���O���u���{����v�̏ꍇ<BR>
    * �@@�@@���O�C�����[�U�̃��O�C����������O��p�X���[�h�ύX���t���擾����B<BR>
    * �@@�@@�O��p�X���[�h�ύX���t������ȏ����ł͂Ȃ��ADate�ϊ��Ɏ��s�����ꍇ<BR>
    * �@@�@@�@@�@@�v���I�ȃV�X�e���G���[��throw����B<BR>
    * �@@�@@<BR>
    * �@@�@@�擾�������t�Ƀp�X���[�h�L�����Ԃ̌��������Z���A�����Ƃ���B<BR>
    * �@@�@@�v�Z�������������ݓ��t���ȑO�̏ꍇ<BR>
    * �@@�@@�@@�@@�p�X���[�h�̗L���������؂ꂽ�Ƃ��ăp�X���[�h�ύX�K�v�itrue�j��Ԃ��B<BR>
    * <BR>
    * �ύX�s�v�ifalse�j��Ԃ��B<BR>
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
       
       /*-------- �ݒ�̃`�F�b�N --------*/
       if ( l_changeFirstFlag == null )
       {
           log.error(STR_METHOD_NAME + " .... ����p�X���[�h�ύX���{�t���O���ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       if ( l_changeIntervalFlag == null )
       {
           log.error(STR_METHOD_NAME + " .... �����Ԍo�ߌ�p�X���[�h�ύX���{�t���O���ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       if ( l_tempInterval == null )
       {
           l_tempInterval = "0";  /* �f�t�H���g�F0�i�L�����ԂȂ��j���Z�b�g */
           log.error(STR_METHOD_NAME + " .... �p�X���[�h�L�����Ԃ��ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
       }
       l_passwordInterval = Integer.parseInt(l_tempInterval);
       
       /*-------- �p�X���[�h�ύX�̕K�v������ --------*/
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);                                   
       HashMap             l_loginAttr    = new HashMap();
       l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
       
       if (WEB3PwdChangeDef.REQUIRED.equals(l_changeFirstFlag))
       {
           String l_lastLogin = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_LOGIN);
           // �ŏI���O�C���������擾�ł��Ȃ��ꍇ
           // �@@���񃍃O�C���ł���Ƃ��āA�p�X���[�h�ύX�K�v�itrue�j��Ԃ��B
           if (l_lastLogin == null)
           {
               log.debug(STR_METHOD_NAME + " .... �i����j�p�X���[�h�ύX�K�v.");
               log.exiting(STR_METHOD_NAME);
               return true;
           }
           try
           {
               loginAttributeDateFormat.parse(l_lastLogin);
           }
           catch ( ParseException pex )
           {   /* �����l�������ȊO�i�ŏI���O�C���������ݒ�|���񃍃O�C���j*/
               log.debug(STR_METHOD_NAME + " .... �i����j�p�X���[�h�ύX�K�v.");
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
           {   /* �p�X���[�h�L�����Ԓ��� */
               log.debug(STR_METHOD_NAME + " .... �i�����ԁj�p�X���[�h�ύX�K�v.");
               log.exiting(STR_METHOD_NAME);
               return true;
           }
       }
       
       log.exiting(STR_METHOD_NAME);
       return false;
   }
   
   /**
    * ���O�C���^�C�v��������p�X���[�h�ύX���ă��O�C�����{�t���O���擾����B<BR>
    * �t���O���u���{����v�̏ꍇtrue�A�u���{���Ȃ��v�̏ꍇfalse��Ԃ��B<BR>
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
           l_reloginFlag = WEB3ReloginFlagDef.RELOGIN;  /* �f�t�H���g�F�u�ă��O�C�����{�v���Z�b�g */
           log.error(STR_METHOD_NAME + " .... �p�X���[�h�ύX���ă��O�C�����{�t���O���ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
       }
       
       if (WEB3ReloginFlagDef.CONTINUE.equals(l_reloginFlag))
       {
           log.debug(STR_METHOD_NAME + " .... �ă��O�C���s�v.");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
       
       log.debug(STR_METHOD_NAME + " .... �ă��O�C���K�v.");
       log.exiting(STR_METHOD_NAME);
       return true;
   }
   
   /**
    * OpLoginAdminService���擾����B<BR>
    * OpLoginAdminService�̋@@�\���g�p���ăp�X���[�h��ύX����B<BR>
    * �@@�@@�Ԃ�l�����s�������ꍇ�́A���s(false)��Ԃ��B<BR>
    * <BR>
    * changeLAPassword()���\�b�h�����s���āA���O�C�������̃p�X���[�h��<BR>
    * �ύX����B<BR>
    * @@param l_oldPassword - ���݃p�X���[�h
    * @@param l_newPassword - �V�p�X���[�h
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
           log.debug(STR_METHOD_NAME + " .... �p�X���[�h�ύX���s.");
           log.exiting(STR_METHOD_NAME);
           return false;
       }
           
       changeLAPassword(l_newPassword);
       
       log.exiting(STR_METHOD_NAME);
       return true;    
   }
   
   /**
    * ��changePassword()����R�[������鎖��z�肵�Ă���B<BR>
    * �@@�P�ƂŎ��s����Ɩ{���ixTrade�Ǘ��j�̃p�X���[�h�ƈ�v���Ȃ��Ȃ�̂�<BR>
    * �@@��펞�̕�����ƂȂǈȊO�ł͒P�ƂŎg�p���Ă͂Ȃ�Ȃ��B<BR>
    * <BR>
    * �Z�L�����e�B�E���x�����u���v�܂��́u��⍂�v�̏ꍇ<BR>
    * �@@�@@�ȉ��A�R����`�Q���㕪�J��Ԃ��B<BR>
    * �@@�@@�@@�@@�Ώۂ��P�O�̐���̃p�X���[�h���擾����B<BR>
    * �@@�@@�@@�@@���擾�ł��Ȃ��ꍇ�͑����Ɏ��i�P�O�j����������B<BR>
    * �@@�@@�@@�@@�@@�p�X���[�h�ύX�񐔂����Ȃ���΁A���p�X���[�h���������݂��Ȃ��B<BR>
    * �@@�@@�@@�@@�擾�����p�X���[�h��Ώۂ̃p�X���[�h�Ƃ��đ������X�V����B<BR>
    * �@@�@@�P����O�p�X���[�h�ɕ������\�p�X���[�h���Z�b�g���đ������X�V����B<BR>
    * <BR>
    * �V�p�X���[�h��WEB3���ʂ̕����ňÍ�������B<BR> 
    * ���O�C�������̕������\�p�X���[�h���Í��������V�p�X���[�h�ōX�V����B<BR> 
    * <BR> 
    * �O��p�X���[�h�ύX���t�iLAST_PWDCHANGE�j���X�V����B<BR> 
    * <BR> 
    * �O��p�X���[�h�X�V�҃R�[�h�iLAST_PASSWORDCHANGE_UPDATER�j��<BR> 
    * �X�V����B<BR> 
    * �� �R�[���Z���^�[����̓��͂̏ꍇ�A���҃e�[�u��.���҃R�[�h�B<BR> 
    * �� �ڋq���͂̏ꍇ�A�ڋq�}�X�^�e�[�u��.�ڋq�R�[�h�B<BR> 
    * �� �Ǘ��ғ��͂̏ꍇ�A�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h�B<BR> 
    * <BR> 
    * @@param l_newPassword - �V�p�X���[�h�i�����j
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
       {   /* ���p�X���[�h���R����O�܂ŕێ� */
           for (int gen_cnt = 3; gen_cnt > 0; gen_cnt--)
           {
               String l_befPwd = (String)l_loginAttr.get(PWDOLD_ATTR_NAME[gen_cnt - 1]);
               if ( l_befPwd == null )
               {
                   continue;
               }
               log.debug(STR_METHOD_NAME + " .... ���p�X���[�h�ړ��i" + PWDOLD_ATTR_NAME[gen_cnt - 1] + " �� " + PWDOLD_ATTR_NAME[gen_cnt] + "�j");
               l_loginAttr.put(PWDOLD_ATTR_NAME[gen_cnt], l_befPwd);
           }
       }
       
       //���O�C�������̕������\�p�X���[�h���Í��������V�p�X���[�h�ōX�V����
       String l_encryptedPwd = web3Crypt.encrypt(l_newPassword);
       l_loginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD, l_encryptedPwd);
       
       // �O��p�X���[�h�ύX���t�iLAST_PWDCHANGE�j���X�V����
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
             
       // �ڋq���͂̏ꍇ�A�ڋq�}�X�^�e�[�u��.�ڋq�R�[�h���擾����B
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
           
           // �R�[���Z���^�[����̓��͂̏ꍇ�A���҃e�[�u��.���҃R�[�h���擾����B
           if(l_trader != null)
           {
               l_strLastUpdater = l_trader.getTraderCode();
           }
           //�Ǘ��ғ��͂̏ꍇ�A�Ǘ��҃e�[�u��.�Ǘ��҃R�[�h���擾����B
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
                       "�Ǘ��ҍs�I�u�W�F�N�g = null");
               }
               l_strLastUpdater = l_administratorRow.getAdministratorCode();
           }
           
       }
       
       //�O��p�X���[�h�X�V�҃R�[�h�iLAST_PASSWORDCHANGE_UPDATER�j
       //���X�V����
       l_loginAttr.put(
           WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER,
           l_strLastUpdater);
       
       l_adminService.setLoginAttributes(loginID, l_loginAttr);    
       
       log.exiting(STR_METHOD_NAME);
   }
}
@
