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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �p�X���[�h�̃`�F�b�N��ύX���̋@@�\���������郆�[�e�B���e�B�E�N���X(WEB3TradingPasswordUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History :  2004/10/22 ����� (���u)  �V�K�쐬
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
      *�R���X�g���N�^ 
      *�P�j�@@�����̃��O�C��ID�Ŏw�肳�ꂽ���[�U�̃��O�C���^�C�v�������擾���A<BR>
      *�v���p�e�B�ɃZ�b�g����B<BR> 
      * <BR>
      * �Q�j�@@���O�C���^�C�v��������Z�L�����e�B�E���x�������擾���A<BR>
      * �����ɕێ�����B <BR>
      *  <BR>
      * ���@@�Z�L�����e�B�E���x�� <BR>
      * �i���O�C���^�C�v����.������ == ����p�X���[�h�Z�L�����e�B�E���x���j�� <BR>
      * �s�����݂���ꍇ�A <BR>
      * �|�i���O�C���^�C�v����.������ == ����p�X���[�h�Z�L�����e�B�E���x���j <BR>
      * �̑����l������p�X���[�h�̃Z�L�����e�B���x���Ƃ���B <BR>
      *  <BR>
      * �i���O�C���^�C�v����.������ == ����p�X���[�h�Z�L�����e�B�E���x���j�� <BR>
      * �s�����݂��Ȃ��ꍇ�A <BR>
      * �|�i���O�C���^�C�v����.������ == �Z�L�����e�B�E���x���j�̑����l�� <BR>
      * ����p�X���[�h�̃Z�L�����e�B���x���Ƃ���B <BR>
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#WEB3PasswordUtility
      * @@param l_loginID
      */
    public WEB3TradingPasswordUtility(long l_loginID)
    {

        final String STR_METHOD_NAME = "WEB3TradingPasswordUtility(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����̃��O�C��ID�Ŏw�肳�ꂽ���[�U�̃��O�C���^�C�v�������擾���A
        //�v���p�e�B�ɃZ�b�g����
        loginID = l_loginID;
        loginTypeAttrs = new HashMap();
        OpLoginAdminService l_adminService =
            (OpLoginAdminService) Services.getService(
                OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_adminService.getLoginInfo(loginID);
        loginTypeAttrs.putAll(
            l_adminService.getLoginTypeAttributes(
                l_loginInfo.getLoginTypeId()));

        //�Q�j�@@���O�C���^�C�v��������Z�L�����e�B�E���x�������擾���A
        // �����ɕێ�����

        //get �Z�L�����e�B�E���x��
        String l_strSecurityLevel =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL);

        //get ����p�X���[�h�Z�L�����e�B�E���x��
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
                    + " .... �Z�L�����e�B�E���x�����ݒ肳��Ă��Ȃ�.�iLOGIN_ID: "
                    + loginID
                    + "�j");
            throw new RuntimeException(
                "�Z�L�����e�B�E���x�����ݒ肳��Ă��Ȃ�.�iLOGIN_ID: " + loginID + "�j");
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
      *�icheckSimilarToBefore��override�j <BR>
      * <BR>
      * ���O�C����������R����O�܂ł̋�����p�X���[�h���擾���A<BR>
      * �V�p�X���[�h�������Ɨގ����Ă��Ȃ������`�F�b�N����B <BR>
      * �ގ����Ă��Ȃ��ꍇtrue�A���ꂩ�ƈ�v���Ă���ꍇfalse��ԋp����B<BR> 
      *  <BR>
      * ���@@������p�X���[�h <BR>
      * 1����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD1�j�̃��O�C�������l <BR>
      * �Q����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD2�j�̃��O�C�������l <BR>
      * �R����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD3�j�̃��O�C�������l <BR>
      *  <BR>
      * ���@@���p�X���[�h�̎擾�����Ⴄ�݂̂ŁA�ȊO�̎葱���� <BR>
      * �X�[�p�[�N���X�iWEB3PasswordUtility�j�Ɠ��ꏈ���Ƃ���B<BR> 
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#checkSimilarToBefore<BR>
      * @@param l_strNewPassword - (�V�p�X���[�h)<BR>
      */
    public boolean checkSimilarToBefore(String l_strNewPassword) 
    {

        final String STR_METHOD_NAME = "checkSimilarToBefore(String)";
        log.entering(STR_METHOD_NAME);
        
        //1����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD1�j�̃��O�C�������l 
        //�Q����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD2�j�̃��O�C�������l 
        //�R����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD3�j�̃��O�C�������l
        final String PWDOLD_ATTR_NAME[] = new String[] {
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3
        };
        
        //���O�C���������擾����
        OpLoginAdminService l_adminService =
            (OpLoginAdminService) Services.getService(
                OpLoginAdminService.class);
        HashMap l_loginAttributes = new HashMap();
        l_loginAttributes.putAll(l_adminService.getLoginAttributes(loginID));
        
        //�p�X���[�h�ŏ������p�X���[�h���擾����
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
            {   /* ���O�C���������ݒ� */
                log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[i] + "���ݒ�.���������񌟍����f�i����j.");
                break;
            }
           
            l_oldPassword = web3Crypt.decrypt(l_oldPassword);
            if ( l_oldPassword.indexOf(l_strNewPwdParts) != -1 )
            {   /* ���p�X���[�h���ɐV�p�X���[�h�̐擪�ƈ�v���镔�������񂪊܂܂�Ă��� */
                log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h��" + PWDOLD_ATTR_NAME[i] + "�ɗގ����Ă���.");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
        
    }
    
    /**
      *�icheckSameAsBefore��override�j <BR>
      * <BR>
      * ���O�C����������R����O�܂ł̋��p�X���[�h���擾���A<BR>
      * �V�p�X���[�h�������ƈ�v���Ă��Ȃ������`�F�b�N����B<BR> 
      * ��v���Ă��Ȃ��ꍇtrue�A���ꂩ�ƈ�v���Ă���ꍇfalse��ԋp����B<BR> 
      * <BR>
      * ���@@������p�X���[�h <BR>
      * 1����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD1�j�̃��O�C�������l <BR>
      * �Q����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD2�j�̃��O�C�������l <BR>
      * �R����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD3�j�̃��O�C�������l <BR>
      *  <BR>
      * ���@@���p�X���[�h�̎擾�����Ⴄ�݂̂ŁA<BR>
      * �ȊO�̎葱���̓X�[�p�[�N���X�iWEB3PasswordUtility�j�Ɠ��ꏈ���Ƃ���B<BR> 
      *  <BR>
      * @@see webbroker3.util.WEB3PasswordUtility#checkSameAsBefore
      * @@param l_strNewPassword - (�V�p�X���[�h)
      */
    public boolean checkSameAsBefore(String l_strNewPassword) 
    {

        final String STR_METHOD_NAME = "checkSameAsBefore(String)";
        log.entering(STR_METHOD_NAME);
        
        //1����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD1�j�̃��O�C�������l 
        //�Q����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD2�j�̃��O�C�������l 
        //�R����O�F�@@�i���O�C�������� == TRADING_PASSWORD_OLD3�j�̃��O�C�������l
        final String PWDOLD_ATTR_NAME[] = new String[] {
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,
            WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3
        };
        
        //���O�C���������擾����
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
            {   /* ���O�C���������ݒ� */
                log.debug(STR_METHOD_NAME + " .... " + PWDOLD_ATTR_NAME[i] + "���ݒ�.�������r���f�i����j.");
                break;
            }
           
            if ( l_oldPassword.equals(l_strNewPassword) )
            {   /* ���p�X���[�h���̕���������ƐV�p�X���[�h����v���Ă��� */
                log.debug(STR_METHOD_NAME + " .... error. �V�p�X���[�h��" + PWDOLD_ATTR_NAME[i] + "�ƈ�v���Ă���.");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
       
        log.exiting(STR_METHOD_NAME);
        return true;
        
    }
    
    /**
      *�igetPasswordMinLength�j <BR>
      * <BR>
      * �p�X���[�h�ŏ������擾����B <BR>
      * <BR>
      * this.���O�C���^�C�v�������A�����l.�p�X���[�h <BR>
      * �ŏ����i�FPASSWORD_MIN_LENGTH�j�̑����l��ԋp����B <BR>
      */
    public String getPasswordMinLength() 
    {
        String l_strPasswordMinLength =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH);
        return l_strPasswordMinLength;
    }
    
    /**
      *�igetPasswordMaxLength�j <BR>
      * <BR>
      * �p�X���[�h�ő咷���擾����B <BR>
      *  <BR>
      * this.���O�C���^�C�v�������A�����l.�p�X���[�h<BR>
      * �ő咷�i�FPASSWORD_MAX_LENGTH�j�̑����l��ԋp����B<BR>
      */
    public String getPasswordMaxLength() 
    {
        String l_strPasswordMaxLength =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH);
        return l_strPasswordMaxLength;
    }
    
    /**
      *�igetPasswordCheckMode�j <BR>
      * <BR>
      * �p�X���[�h�`�F�b�N�`�����擾����B <BR>
      *  <BR>
      * this.���O�C���^�C�v�������A�����l.�p�X���[�h�`�F�b�N<BR>
      * �`���i�FPASSWORD_CHECK_MODE�j�̑����l��ԋp����B<BR>
      */
    public String getPasswordCheckMode() 
    {        
        String l_strPasswordCheckMode =
            (String) loginTypeAttrs.get(
                WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
        return l_strPasswordCheckMode;
    }
    
    /**
      *�isaveOldTradingPasswords �j <BR>
      * <BR>
      * 3����O�܂ł̎���p�X���[�h�����O�C�������ɕۑ�����B <BR>
      * �i�Z�L�����e�B�E���x�����u���v�܂��́u��⍂�v�̏ꍇ�̂ݏ������{�j<BR> 
      *  <BR>
      * ���@@�X�V�ɂ́AOpLoginAdminService.setLoginAttributes()���\�b�h<BR>
      * ���g�p����B<BR> 
      * ���@@�X�V���e�́A�y��Trade�z�⑫����.DB�X�V�@@<BR>
      * �u�Ïؔԍ��ύX_���O�C������.xls�v�Q�ƁB<BR> 
      * <BR>
      * @@param l_strCurrentPassword - (�ύX�O�̎���p�X���[�h�i���ݒl�j)
      */
    public void saveOldTradingPasswords(String l_strCurrentPassword) 
    {
        final String STR_METHOD_NAME = "saveOldTradingPasswords(String)";
        log.entering(STR_METHOD_NAME);
        
        //get�Z�L�����e�B�E���x��
        //�@@ ���O�C���^�C�v����.������ == 
        //    ����p�X���[�h�Z�L�����e�B�E���x���i�FTRADING_PWDSECURITY_LEVEL�j�̍s������ꍇ�A
        //    ���O�C���^�C�v����.������ == 
        //    ����p�X���[�h�Z�L�����e�B�E���x���i�FTRADING_PWDSECURITY_LEVEL�j�̑����l�B 
        //�A ���O�C���^�C�v����.������ == 
        //    ����p�X���[�h�Z�L�����e�B�E���x���i�FTRADING_PWDSECURITY_LEVEL�j�̍s���Ȃ��ꍇ�A
        //    ���O�C���^�C�v����.������ == �Z�L�����e�B�E���x���i�FSECURITY_LEVEL�j�̑����l�B 
        String l_strSecurityLevel = 
            (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_SECURITY_LEVEL); 
        if(l_strSecurityLevel == null)
        {
            l_strSecurityLevel = 
                (String)loginTypeAttrs.get(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL); 
        }
        
        //�Z�L�����e�B�E���x�������h��⍂�h�C�h���h�̂����ꂩ�̏ꍇ�B
        if (WEB3SecurityLevelDef.LITTLE_HIGH.equals(l_strSecurityLevel)
            || WEB3SecurityLevelDef.HIGH.equals(l_strSecurityLevel))
        { 
            OpLoginAdminService l_adminService =
                (OpLoginAdminService) Services.getService(OpLoginAdminService.class);
            HashMap l_loginAttr = new HashMap();
            l_loginAttr.putAll(l_adminService.getLoginAttributes(loginID));
            
            //���O�C�������� == TRADING_PASSWORD_OLD2�ɊY����������s������ꍇ
            //update �R����O����p�X���[�h
            String l_strPwdOld2 =
                (String) l_loginAttr.get(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2);
            if (l_strPwdOld2 != null)
            {
                l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD3,l_strPwdOld2);
            }
            
            //���O�C�������� == TRADING_PASSWORD_OLD1�ɊY����������s������ꍇ   
            //update �Q����O����p�X���[�h 
            String l_strPwdOld1 =
                (String) l_loginAttr.get(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1);
            if (l_strPwdOld1 != null)
            {
                l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD2,l_strPwdOld1);
            }
            
            //update �P����O����p�X���[�h 
            l_loginAttr.put(WEB3LoginAttributeKeyDef.TRADING_PASSWORD_OLD1, l_strCurrentPassword);
            
            l_adminService.setLoginAttributes(loginID, l_loginAttr); 

        }                                                            
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
