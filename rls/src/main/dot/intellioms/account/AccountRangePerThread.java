/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W(AccountRangePerThread.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O (FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.sql.Timestamp;

import jp.co.dir.dot.intellioms.enums.PurposeType;



/**
 * (�X���b�h���ƃA�J�E���g�����W)
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class AccountRangePerThread
{
    /**
     * (�p�r���)
     */
    private PurposeType purposeType;

    /**
     * (�A�J�E���g�X�^�[�g)
     */
    private long accountStart;

    /**
     * (�A�J�E���g�G���h)
     */
    private long accountEnd;

    /**
     * (�X���b�h��)
     */
    private long threadNo;

    /**
     * (�쐬���t)
     */
    private Timestamp createdTimestamp;

    /**
     * (�X�V���t)
     */
    private Timestamp lastUpdatedTimestamp;



    /**
     *
     */
    public AccountRangePerThread()
    {
        super();
    }

    /**
     *
     */
    public AccountRangePerThread(AccountRangePerThread l_range)
    {
        purposeType = l_range.getPurposeType();
        accountStart = l_range.getAccountStart();
        accountEnd = l_range.getAccountEnd();
        threadNo = l_range.getThreadNo();
        createdTimestamp = l_range.getCreatedTimestamp();
        lastUpdatedTimestamp = l_range.getLastUpdatedTimestamp();
    }
    /**
     * @return purposeType ��߂��܂��B
     */
    public PurposeType getPurposeType()
    {
        return purposeType;
    }
    /**
     * @param purposeType purposeType ��ݒ�B
     */
    public void setPurposeType(PurposeType purposeType)
    {
        this.purposeType = purposeType;
    }
    /**
     * @return accountStart ��߂��܂��B
     */
    public long getAccountStart()
    {
        return accountStart;
    }
    /**
     * @param accountStart accountStart ��ݒ�B
     */
    public void setAccountStart(long accountStart)
    {
        this.accountStart = accountStart;
    }
    /**
     * @return accountEnd ��߂��܂��B
     */
    public long getAccountEnd()
    {
        return accountEnd;
    }
    /**
     * @param accountEnd accountEnd ��ݒ�B
     */
    public void setAccountEnd(long accountEnd)
    {
        this.accountEnd = accountEnd;
    }
    /**
     * @return threadNo ��߂��܂��B
     */
    public long getThreadNo()
    {
        return threadNo;
    }
    /**
     * @param threadNo threadNo ��ݒ�B
     */
    public void setThreadNo(long threadNo)
    {
        this.threadNo = threadNo;
    }
    /**
     * @return createdTimestamp ��߂��܂��B
     */
    public Timestamp getCreatedTimestamp()
    {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp createdTimestamp ��ݒ�B
     */
    public void setCreatedTimestamp(Timestamp createdTimestamp)
    {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return lastUpdatedTimestamp ��߂��܂��B
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp lastUpdatedTimestamp ��ݒ�B
     */
    public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp)
    {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof AccountRangePerThread)
        {
            AccountRangePerThread target = (AccountRangePerThread) obj;
            if (getAccountStart() == target.getAccountStart()
                && getAccountEnd() == target.getAccountEnd()
                && getThreadNo() == target.getThreadNo()
                && getPurposeType().equals(target.getPurposeType())
                )
            {

                if(getCreatedTimestamp() == null)
                {
                    if(target.getCreatedTimestamp() != null)
                    {
                        return false;
                    }
                }
                else
                {
                    if(! getCreatedTimestamp().equals(target.getCreatedTimestamp()))
                    {
                        return false;
                    }
                }

                if(getLastUpdatedTimestamp() == null)
                {
                    if(target.getLastUpdatedTimestamp() != null)
                    {
                        return false;
                    }
                }
                else
                {
                    if(! getLastUpdatedTimestamp().equals(target.getLastUpdatedTimestamp()))
                    {
                        return false;
                    }
                }

                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("AccountRangePerThread[");
        sb.append("purposeType=").append(this.getPurposeType());
        sb.append(", accountStart=").append(this.getAccountStart());
        sb.append(", accountEnd=").append(this.getAccountEnd());
        sb.append(", threadNo=").append(this.getThreadNo());
        sb.append(", createdTimestamp=").append(this.getCreatedTimestamp());
        sb.append(", lastUpdatedTimestamp=").append(this.getLastUpdatedTimestamp());
        sb.append("]");
        return sb.toString();
    }
}
