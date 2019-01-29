/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Db�p�����[�^(DOTAccountThreadDbParams.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.sql.Connection;

/**
 * Db�p�����[�^
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTAccountThreadDbParams
{
    /**
     * (�X���b�h���ƃA�J�E���g�����W)
     */
    private AccountRangePerThread accountRange;

    /**
     * (�R�l�N�V����)
     */
    private Connection connection;

    /**
     *
     */
    public DOTAccountThreadDbParams()
    {
        super();
    }

    /**
     * @return accountRange ��߂��܂��B
     */
    public AccountRangePerThread getAccountRange()
    {
        return accountRange;
    }
    /**
     * @param accountRange accountRange ��ݒ�B
     */
    public void setAccountRange(AccountRangePerThread accountRange)
    {
        this.accountRange = accountRange;
    }
    /**
     * @return connection ��߂��܂��B
     */
    public Connection getConnection()
    {
        return connection;
    }
    /**
     * @param connection connection ��ݒ�B
     */
    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }

    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof DOTAccountThreadDbParams)
        {
            DOTAccountThreadDbParams target = (DOTAccountThreadDbParams) obj;

            if(getAccountRange() == null)
            {
                if(target.getAccountRange() != null)
                {
                    return false;
                }
            }
            else
            {
                if(! getAccountRange().equals(target.getAccountRange()))
                {
                    return false;
                }
            }

            if(getConnection() == null)
            {
                if(target.getConnection() != null)
                {
                    return false;
                }
            }
            else
            {
                if(! getConnection().equals(target.getConnection()))
                {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DOTAccountThreadDbParams[");
        sb.append("accountRange=").append(this.getAccountRange());
        sb.append(", connection=").append(this.getConnection());
        sb.append("]");
        return sb.toString();
    }
}
