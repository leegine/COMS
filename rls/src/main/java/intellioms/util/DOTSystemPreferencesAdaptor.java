/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SystemPreferencesAdaptor�N���X(DOTSystemPreferencesAdaptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.fitechlabs.fin.intellioms.persist.PersistException;
import com.fitechlabs.fin.intellioms.persist.db.AbstractDbStore;
import com.fitechlabs.fin.intellioms.tx.TxManager;
import com.fitechlabs.fin.intellioms.tx.TxManagerException;
import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * (SYSTEM_PREFERENCES�A�_�v�^)<BR>
 * <BR>
 * SYSTEM_PREFERENCES�e�[�u���ɐݒ肳�ꂽ�ݒ�l�ɃA�N�Z�X���邽�߂̃A�_�v�^�B<BR>
 * SYSTEM_PREFERENCES�A�_�v�^�̃C���X�^���X�𐶐�������Ƃ��ɁA
 * SYSTEM_PREFERENCES�e�[�u���̓��e���f�[�^�x�[�X���烍�[�h����B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTSystemPreferencesAdaptor extends AbstractDbStore implements
    Startable
{
    
    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** Properties�A�_�v�^ */
    private DOTPropertiesAdaptor properties;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �y�V�[�P���X�}�z
     * �E(SYSTEM_PREFERENCES�A�_�v�^)����������
     * <BR>
     * @throws PersistException
     */
    public DOTSystemPreferencesAdaptor() throws PersistException
    {
    }

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param l_properties Properties
     */
    public DOTSystemPreferencesAdaptor(Properties l_properties)
    {
        setProperties(l_properties);
    }

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ��<code>null</code>��Ԃ��B
     * <BR>
     * @param l_strKey �L�[
     * @return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
     * <BR>
     * @param l_strKey �L�[
     * @param l_intDefaultValue �f�t�H���g�l
     * @return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return properties.getProperty(l_strKey, l_intDefaultValue);
    }

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
     * <BR>
     * @param l_strKey �L�[
     * @param l_intDefaultValue �f�t�H���g�l
     * @return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        return properties.getProperty(l_strKey, l_lngDefaultValue);
    }

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
     * <BR>
     * @param l_strKey �L�[
     * @param l_intDefaultValue �f�t�H���g�l
     * @return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }

    /**
     * (get�v���p�e�B�Z�b�g)<BR>
     * <BR>
     * �w�肵���ړ����Ŏn�܂�L�[�œo�^����Ă���
     * SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * <BR>
     * @param l_strPrefix �L�[�̐ړ���
     * @return �w�肵���ړ����Ŏn�܂�L�[�Ɛݒ�l���ݒ肳�ꂽ<code>Map</code>
     */
    public Map getProperties(String l_strPrefix)
    {
        return properties.getProperties(l_strPrefix);
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException
    {
        try
        {
            setProperties(load());
        } catch (PersistException l_pe)
        {
            throw new InitializationException(l_pe);
        }
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop()
    {
    }
    
    /**
     * (set�v���p�e�B�Z�b�g)<BR>
     * <BR>
     * �w�肵��Properties��Properties�A�_�v�^�𐶐����A�ݒ肷��B
     * <BR>
     * �y�V�[�P���X�}�z
     * �E(SYSTEM_PREFERENCES�A�_�v�^)����������
     * <BR>
     * @param l_properties
     */
    private void setProperties(Properties l_properties)
    {
        this.properties = new DOTPropertiesAdaptor(l_properties);
    }

    /**
     * (load)<BR>
     * <BR>
     * SYSTEM_PREFERECES�e�[�u���̓��e���f�[�^�x�[�X���烍�[�h����B
     * <BR>
     * �y�V�[�P���X�}�z
     * �E(SYSTEM_PREFERENCES�A�_�v�^)����������
     * <BR>
     * @return SYSTEM_PREFERENCES���烍�[�h�������e��
     *   �ݒ肵��<code>Properties</code>
     * @throws PersistException
     */
    private Properties load() throws PersistException
    {

        Properties l_properties = new Properties();

        Connection l_conn = null;
        PreparedStatement l_st = null;
        ResultSet l_rs = null;

        try
        {

            TxManager.begin();

            l_conn = getConnection();
            l_st = l_conn
                .prepareStatement("select name, value from system_preferences");

            for (l_rs = l_st.executeQuery(); l_rs.next();)
            {
                
                String l_strKey = l_rs.getString("name");
                String l_strValue = l_rs.getString("value");
                l_properties.setProperty(l_strKey, l_strValue);
                
            }

            TxManager.commit();

        } catch (SQLException l_sqle)
        {
            log.error("Unexpected exception occured while loading 'SYSTEM_PREFERENCES'.", l_sqle);
            rollback();
        } catch (TxManagerException l_txe)
        {
            log.error("Unexpected exception occured while loading 'SYSTEM_PREFERENCES'.", l_txe);
            rollback();
        } finally
        {
            close(l_rs);
            close(l_st);
        }

        return l_properties;

    }
    
}