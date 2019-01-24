/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : QuoteIndex�N���X(QuoteIndex.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/30 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.sql.Timestamp;

/**
 * (�������C���f�b�N�X)<BR>
 * <BR>
 * �������Ǘ��e�[�u���Ŏ��Ԗ��̎��������Ǘ�����Ƃ���
 * �L�[�Ƃ��Ďg�p�����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class QuoteIndex implements Comparable
{

    /**
     * �V�[�P���XNO�̃f�t�H���g�l
     */
    private static final long DEFAULT_SEQ_NO = -1;

    /** �X�V���� */
    private final long updatedTime;

    /** �V�[�P���XNO */
    private final long sequenceNo;

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_lngUpdatedTime �X�V����
     */
    public QuoteIndex(long l_lngUpdatedTime)
    {
        this(l_lngUpdatedTime, DEFAULT_SEQ_NO);
    }

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_lngUpdatedTime �X�V����
     * @param l_lngSequenceNo �V�[�P���XNO
     */
    public QuoteIndex(long l_lngUpdatedTime, long l_lngSequenceNo)
    {
        this.updatedTime = l_lngUpdatedTime;
        this.sequenceNo = l_lngSequenceNo;
    }

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_updatedTime �X�V����
     */
    public QuoteIndex(Timestamp l_updatedTime)
    {
        this(l_updatedTime, DEFAULT_SEQ_NO);
    }

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_updatedTime �X�V����
     * @param l_lngSequenceNo �V�[�P���XNO
     */
    public QuoteIndex(Timestamp l_updatedTime, long l_lngSequenceNo)
    {
        this(l_updatedTime.getTime(), l_lngSequenceNo);
    }

    /**
     * (get�X�V����)<BR>
     * <BR>
     * �X�V���Ԃ��擾����B<BR>
     *
     * @return �X�V����
     */
    public long getUpdatedTime()
    {
        return updatedTime;
    }

    /**
     * (get�V�[�P���XNO)<BR>
     * <BR>
     * �V�[�P���XNO���擾����B<BR>
     *
     * @return �V�[�P���XNO
     */
    public long getSequenceNo()
    {
        return sequenceNo;
    }

    /**
     * (compareTo(�������C���f�b�N�X))<BR>
     * <BR>
     * ���̎������C���f�b�N�X�Ǝw�肳�ꂽ�������C���f�b�N�X�̏������r����B<BR>
     *
     * @param l_target ��r�Ώۂ̎������C���f�b�N�X
     * @return ���̎������C���f�b�N�X���w�肳�ꂽ�������C���f�b�N�X���
     *         �������ꍇ�͕��̐����A�������ꍇ�̓[���A�傫���ꍇ�͐��̐���
     */
    public int compareTo(QuoteIndex l_target)
    {
        if (updatedTime == l_target.updatedTime)
        {
            return (sequenceNo < l_target.sequenceNo) ? -1
                : ((sequenceNo > l_target.sequenceNo) ? 1 : 0);
        } else
        {
            return (updatedTime < l_target.updatedTime) ? -1
                : 1;
        }
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object l_obj)
    {
        return compareTo((QuoteIndex) l_obj);
    }

    /**
     * @return �X�V���ԂƃV�[�P���XNO���������ꍇ��<code>true</code>�A
     *         ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     * @see Object#equals(Object)
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof QuoteIndex)
        {
            QuoteIndex l_target = (QuoteIndex) l_obj;
            return ((updatedTime == l_target.updatedTime)
                && (sequenceNo == l_target.sequenceNo));
        }
        return false;
    }

    /**
     * @return �X�V���ԂƃV�[�P���XNO���琶�����ꂽ�n�b�V���l��Ԃ��B
     * @see Object#hashCode()
     */
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 37 + ((int) (updatedTime ^ updatedTime >> 32 ));
        hash = hash * 37 + ((int) (sequenceNo ^ sequenceNo >> 32 ));
        return hash;
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("QuoteIndex[");
        l_sb.append("updatedTime=").append(new Timestamp(updatedTime));
        l_sb.append(", sequenceNo=").append(sequenceNo);
        l_sb.append("]");
        return l_sb.toString();
    }
}
