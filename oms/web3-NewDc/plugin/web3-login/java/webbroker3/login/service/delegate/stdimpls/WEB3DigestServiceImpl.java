head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3DigestServiceImpl�N���X(WEB3DigestServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 ��(FLJ) �V�K�쐬
                   2006/7/07 ��(FLJ) �������ꂽGUID��啶���ɂ���igetKey3()���\�b�h�j
*/
package webbroker3.login.service.delegate.stdimpls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.login.service.delegate.WEB3DigestService;
import webbroker3.util.WEB3LogUtility;


import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 *  ���̃N���X��WEB3�ɕK�v�ȃZ�L�����e�B�[�L�[�𐶐��A�F�؂Ȃǂ��s���T�[�r�X�ł���B
 * 
 *  @@author      ��(FLJ)
 *  @@version     1.0
 */
public class WEB3DigestServiceImpl implements WEB3DigestService
{
    /** ���O�o�̓I�u�W�F�N�g */
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3DigestServiceImpl.class);
    
    /** �����_�����̐�����B */    
    private static final SecureRandom numberGenerator = new SecureRandom();
    
    /** 16�i���̕\������ */
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};
    
    //���\�b�h���̒萔
    private final static String M_GET_RANDOM_KEY = "getRandomKey()";    
    private final static String M_IS_VALID_KEY = "isValidKey(WEB3DigestKey key)"; 
    private final static String M_GET_KEY_1 = "getKey1()"; 
    private final static String M_GET_KEY_3 = "getKey3()"; 
    private final static String M_GET_KEY_4 = "getKey4(final WEB3DigestKey key)"; 

    
    /**
     * Web3�ɕK�v�ȃL�[���쐬�A�擾
     * 
     * @@return �L�[
     */
    public WEB3DigestKey getRandomKey()
    {
        m_log.entering(M_GET_RANDOM_KEY);
        
        WEB3DigestKey result = new WEB3DigestKey();
        
        result.setKey1(getKey1());
        result.setKey3(getKey3());
        result.setKey4(getKey4(result));
        
        m_log.exiting(M_GET_RANDOM_KEY);
        return result;
    }
    
    /**
     * �L���ȃL�[�ł��邩���`���b�N����B
     * ���L������𖞂����L�[�͗L���L�[�ł���Ɣ��f�����B
     * �P�D�L�[�̐��������ƌ��ݎ����Ɣ�ׂČܕ��Ԉȓ��ł��邱�ƁB
     * �Q�D�L�[��SHA1�R�[�h�͌��݌v�Z���ꂽSHA1�R�[�h�Ɠ����ł��邱�ƁB
     * 
     * @@param l_key �`���b�N�����L�[�I�u�W�F�N�g
     * @@return �L���ł��邩�ǂ����B�L���̏ꍇ:true,������:false
     */
    public boolean isValidKey(WEB3DigestKey l_key)
    {
        m_log.entering(M_IS_VALID_KEY);
        
        boolean result = false;
        
        if(l_key != null && l_key.getKey1() != null && l_key.getKey3() != null)
        {
            //�t�H�[�}�b�g���w��
            SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat(WEB3DigestKey.TIME_FORMAT);
            
            //���̎������擾
            String l_nowString = l_dateFormat.format(new Date(System.currentTimeMillis()));
            
            //�L�[�̐��������ƌ��ݎ����Ɣ�ׂČܕ��Ԉȓ��ł��邩�𔻒f����B
            long l_interval = Math.abs(Long.parseLong(l_nowString) - Long.parseLong(l_key.getKey1()) );
            if( l_interval > 500)
            {
                return result;
            }
            
            //�L�[��SHA1�R�[�h�͌��݌v�Z���ꂽSHA1�R�[�h�Ɠ����ł��邩�𔻒f����B
            String key4 = getKey4(l_key);//web3�L�[�I�u�W�F�N�g�̃L�[1,2,3�ɂ���āA�V�����L�[4(SHA1�R�[�h)�𐶐�
            result = key4.equals(l_key.getKey4());//�V�����L�[4��web3�L�[�I�u�W�F�N�g�̎����Ă���Â��L�[���r
        }
        
        m_log.exiting(M_IS_VALID_KEY);
        return result;
    }
    
    /**
     * �L�[1(����������b)�𐶐�
     * 
     * @@return �L�[1
     */
    private String getKey1()
    {
        m_log.entering(M_GET_KEY_1);
        
        //�t�H�[�}�b�g���w��
        SimpleDateFormat format = GtlUtils.getThreadSafeSimpleDateFormat(WEB3DigestKey.TIME_FORMAT); 

        //���̎������擾
        Date date = new Date(System.currentTimeMillis());
        
        m_log.debug("Created Key1(date):"+date);
        m_log.exiting(M_GET_KEY_1);
        return format.format(date);
    }
    
    /**
     * �L�[3(GUID)�𐶐�
     * 
     * @@return �L�[3
     */    
    private String getKey3()
    {
        m_log.entering(M_GET_KEY_3);
        String result = null;
        
        byte[] randomBytes = new byte[16];
        numberGenerator.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
        randomBytes[6]  |= 0x40;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */

        long msb = 0;
        long lsb = 0;
        for (int i=0; i<8; i++)
        {
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        }
        for (int i=8; i<16; i++){
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);           
        }
        
        result = (longToHexString(msb >> 32, 8) +
                  longToHexString(msb >> 16, 4) +
                  longToHexString(msb, 4)       +
                  longToHexString(lsb >> 48, 4) +
                  longToHexString(lsb, 12));
        
        //2006/07/07 ���@@�C�� �������ꂽGUID��啶���ɂ���
        result = result.toUpperCase();
        
        m_log.debug("Created Key3(GUID):"+result);
        m_log.exiting(M_GET_KEY_3);
        return result;
    }
    
    /**
     * �L�[1,2,3�ɂ���āA�L�[4(SHA1�R�[�h)�𐶐�
     * 
     * @@param key
     *            �L�[1,2,3�������Ă���I�u�W�F�N�g
     * @@return �L�[1 �������G���[���������鎞�ɒ���0�̕������Ԃ�
     */
    private String getKey4(final WEB3DigestKey l_key)
    {
        m_log.entering(M_GET_KEY_4);
        StringBuffer result = new StringBuffer();
        
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("SHA1");

            md.update(l_key.getKey1().getBytes());
            md.update(l_key.getKey2().getBytes());
            md.update(l_key.getKey3().getBytes());
            byte[] byteTemp = md.digest();
            for (int i = 0; i < byteTemp.length; i++) {
                result.append(byteToHexString(byteTemp[i]));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            m_log.error("Error while Generating SHA1 Key." + e);
        }

        m_log.debug("Created Key4(SHA1):"+result.toString());    
        m_log.exiting(M_GET_KEY_4);
        return result.toString();
    }
    
    /**
     * ��������16�i���̕������Ԃ�
     */
    private String longToHexString(long val, int digits)
    {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
    
    /**
     * �o�C�g��16�i���̕�����ɕύX
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
          n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
      }

}
@
