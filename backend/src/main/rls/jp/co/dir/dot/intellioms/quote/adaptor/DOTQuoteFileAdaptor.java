/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFileAdaptor�N���X(DOTQuoteFileAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/19 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Startable;



/**
 * (�������t�@�C���A�_�v�^)<BR>
 * <BR>
 * ���������t�@�C���ɕۑ��E�t�@�C������ǂݍ��ރA�_�v�^�N���X�B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFileAdaptor extends Startable
{
    
    /**
     * (save)
     * <BR>
     * ���������t�@�C���ɕۑ�����B<BR>
     * 
     * @param l_quoteData �������
     */
    public void save(DOTQuoteData l_quoteData);
    
    /**
     * (load)
     * <BR>
     * ���������t�@�C������ǂݍ��ށB<BR>
     * 
     * @return <code>DOTQuoteData</code>��List�B
     */
    public List load();
    
    /**
     * (get�ŏI�����ݎ���)
     * <BR>
     * �t�@�C���̍ŏI�������ݎ�����Ԃ��B<BR>
     * 
     * @return Timestamp �ŏI�������ݎ���
     */
    public Timestamp getLastUpdatedTimestamp();
    

    /**
     * (is�t�@�C�������݂���)
     * <BR>
     * �t�@�C�������݂��邩�ǂ����ԋp����B
     * 
     * @return boolean �t�@�C�������true, �Ȃ����false
     */
    public boolean dataExists();
    
    
}
