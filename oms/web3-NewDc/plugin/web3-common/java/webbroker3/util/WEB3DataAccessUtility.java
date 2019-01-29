head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DataAccessUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : DB�A�N�Z�X���[�e�B���e�B�N���X(WEB3DataAccessUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.util;

import java.util.HashMap;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * DB�A�N�Z�X���[�e�B���e�B�N���X�B
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public final class WEB3DataAccessUtility
{

    /**
     * �R���X�g���N�^�B<BR>
     */
    private WEB3DataAccessUtility()
    {
    }

    /**
     * ���R�[�h��}������B<BR>
     *<BR>
     * @@param l_row �}���Ώۂ� Row �I�u�W�F�N�g
     * @@return �v���C�}���L�[�I�u�W�F�N�g
     */
    public static Object insertRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        return l_qp.doInsertQuery(l_row);
    }

    /**
     * ���R�[�h���X�V����B<BR>
     *<BR>
     * @@param l_row �X�V�Ώۂ� Row �I�u�W�F�N�g
     * @@return �X�V����
     */
    public static int updateRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doUpdateQuery(l_row);
    }

    /**
     * ���R�[�h�̈ꕔ���X�V����B<BR>
     *<BR>
     * @@param l_row �X�V�Ώۂ� Row �I�u�W�F�N�g
     * @@param l_changes �X�V�ӏ��i���ږ��ƒl�̃y�A�j
     * @@return �X�V����
     */
    public static int updateRow(Row l_row, HashMap l_changes) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        PrimaryKey l_pk = l_row.getPrimaryKey();
        return l_qp.doUpdateQuery(l_pk, l_changes);
    }

    /**
     * ���R�[�h���폜����B<BR>
     *<BR>
     * @@param l_row �폜�Ώۂ� Row �I�u�W�F�N�g
     * @@return �폜����
     */
    public static int deleteRow(Row l_row) throws DataNetworkException, DataQueryException
    {
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        PrimaryKey l_pk = l_row.getPrimaryKey();
        return l_qp.doDeleteQuery(l_pk);
    }
}
@
