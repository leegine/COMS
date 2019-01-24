/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PropertiesManager�N���X(DOTPropertiesAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * (Properties�A�_�v�^)<BR>
 * <BR>
 * <code>java.util.Properties</code>�̃A�_�v�^�N���X�B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTPropertiesAdaptor
{
    
    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());
    
    /** Properties */
    private final Properties properties;
    
    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param l_properties �A�_�v�^�ɐݒ肷��v���p�e�B���X�g
     */
    public DOTPropertiesAdaptor(Properties l_properties)
    {
        this.properties = l_properties;
    }
    
    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肳�ꂽ�L�[�����v���p�e�B���擾����B<BR>
     * ���̃v���p�e�B�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * <BR>
     * @param l_strKey �L�[
     * @return �w�肳�ꂽ�L�[�l�����v���p�e�B�̒l
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }
    
    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肳�ꂽ�L�[�����v���p�e�B���擾����B<BR>
     * ���̃v���p�e�B�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B<BR>
     * <BR>
     * @param l_strKey �L�[
     * @param l_strDefaultValue �f�t�H���g�l
     * @return �w�肳�ꂽ�L�[�l�����v���p�e�B�̒l
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }
    
    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肳�ꂽ�L�[�����v���p�e�B���擾����B<BR>
     * ���̃v���p�e�B�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B<BR>
     * <BR>
     * @param l_strKey �L�[
     * @param l_intDefaultValue �f�t�H���g�l
     * @return �w�肳�ꂽ�L�[�l�����v���p�e�B�̒l
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        int l_intValue = l_intDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_intValue = Integer.parseInt(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.warn("This property should be number. [key=" + l_strKey + ", value=" + l_strValue + "]");
            }
        }
        return l_intValue;
    }
    
    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肳�ꂽ�L�[�����v���p�e�B���擾����B<BR>
     * ���̃v���p�e�B�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B<BR>
     * <BR>
     * @param l_strKey �L�[
     * @param l_lngDefaultValue �f�t�H���g�l
     * @return �w�肳�ꂽ�L�[�l�����v���p�e�B�̒l
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        long l_lngValue = l_lngDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_lngValue = Long.parseLong(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.warn("This property should be number. [key=" + l_strKey + ", value=" + l_strValue + "]");
            }
        }
       return l_lngValue;
    }
    
    /**
     * (get�v���p�e�B�Z�b�g)<BR>
     * <BR>
     * �w�肳�ꂽ�ړ����Ŏn�܂�L�[�œo�^����Ă���v���p�e�B����������B<BR>
     * <BR>
     * @param l_strPrefix �ړ���
     * @return �w�肳�ꂽ�ړ����Ŏn�܂�L�[�ƃv���p�e�B���ݒ肳�ꂽ�}�b�v
     */
    public Map getProperties(String l_strPrefix)
    {
        Map l_values = null;
        for (Enumeration l_e = properties.keys(); l_e.hasMoreElements();)
        {
            String l_strKey = (String) l_e.nextElement();
            if (l_strKey != null && l_strKey.startsWith(l_strPrefix))
            {
                if (l_values == null)
                {
                    l_values = new HashMap();
                }
                l_values.put(l_strKey, getProperty(l_strKey));
            }
        }
        return l_values;
    }
    
}
